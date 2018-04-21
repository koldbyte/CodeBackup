package com.koldbyte.codebackup.plugins.codechef;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.koldbyte.codebackup.core.entities.LanguagesEnum;
import com.koldbyte.codebackup.core.entities.Problem;
import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;
import com.koldbyte.codebackup.plugins.PluginInterface;
import com.koldbyte.codebackup.plugins.codechef.core.entities.CodechefProblem;
import com.koldbyte.codebackup.plugins.codechef.core.entities.CodechefSubmission;

public class CodechefPluginImpl implements PluginInterface {
    private static final String PROBLEMS_SOLVED_CONTAINER_DIV_CLASS = "problems-solved";
    private static final String LISTPAGE = "http://www.codechef.com/users/";

    @Override
    public List<Submission> getSolvedList(User user) {
        return fetchSubmissionsPrivate(user, false);
    }

    @Override
    public List<Submission> getAllSolvedList(User user) {
        return fetchSubmissionsPrivate(user, true);
    }

    private List<Submission> fetchSubmissionsPrivate(User user, Boolean fetchAll) {
        String url = LISTPAGE + user.getHandle();
        ArrayList<Submission> submissions = new ArrayList<Submission>();

        try {

            // fetch the main page
            Document doc = Jsoup.connect(url).timeout(10000).get();

            // fetch the div which contains the list of solved problems
            Elements elems = doc.getElementsByClass(PROBLEMS_SOLVED_CONTAINER_DIV_CLASS);
            Elements links = elems.select("a[href]");

            System.out.println("codechef: Found " + links.size()
                    + " Problems linked in your profile page");

            for (Element link : links) {

                try {
                    String linkurl = link.attr("abs:href");

                    if (linkurl.contains("status")) {
                        System.out.println("Processing: " + linkurl);

                        String problemId = link.text();
                        Problem problem = new CodechefProblem(problemId, "");

                        // if it is a proper "status" page, fetch this page
                        Document page = Jsoup.connect(linkurl).get();

                        Elements rows = page.getElementsByAttributeValueContaining("class", "kol");//page.select(".\"kol\"");

                        // loop through the rows to find the first Accepted
                        // submissions
                        int count = 0;
                        for (Element tr : rows) {
                            Elements tds = tr.getElementsByTag("td");

                            //TODO: Add a check to stop processing when encountering "No Recent Activity"
                            if (!tds.text().contains("No Recent Activity")) {

                                String result = tds.get(3).select("img").get(0)
                                        .attr("src");

                                if (result.contains("tick")) { // is a accepted solution
                                    String id = tds.get(0).text();
                                    String time = tds.get(1).text();
                                    String lang = tds.get(6).text();

                                    String solUrl = tds.get(7).select("a[href]")
                                            .attr("abs:href");
                                    // the solUrl is of pattern
                                    // http://www.codechef.com/viewsolution/2078521
                                    // It should be
                                    // http://www.codechef.com/viewplaintext/2078521
                                    solUrl = solUrl.replace("viewsolution",
                                            "viewplaintext");

                                    Submission sub = new CodechefSubmission(id, solUrl,
                                            problem, user);

                                    sub.setLanguage(LanguagesEnum.findExtension(lang));
                                    sub.setTimestamp(time);

                                    submissions.add(sub);
                                    count++;

                                    if (!fetchAll) {
                                        // We have found the AC submission for the current
                                        // problem break now from the for loop to avoid
                                        // adding more submissions of the same problem.
                                        break;
                                    }
                                }
                            }
                        }
                        //System.out.println("Fetch " + count + " AC solutions.");
                    }
                } catch (Exception e) {
                    System.err.println("Codechef: Error fetching Problem solution. Error: "
                            + e.getMessage() + ". Continuing processing next.");
                }

            }

        } catch (IOException e) {
            System.err.println("Codechef: Error fetching list. Error: "
                    + e.getMessage());
            // e.printStackTrace();
        }

        System.out.println("Codechef: Found " + submissions.size()
                + " Submissions");

        return submissions;
    }
}

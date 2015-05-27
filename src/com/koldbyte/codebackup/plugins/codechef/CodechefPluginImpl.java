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
	private final String LISTPAGE = "http://www.codechef.com/users/";

	public List<Submission> getSolvedList(User user) {
		String url = LISTPAGE + user.getHandle();
		ArrayList<Submission> submissions = new ArrayList<Submission>();

		try {

			// fetch the main page
			Document doc = Jsoup.connect(url).timeout(10000).get();

			// fetch the div which contains the list of solved problems
			Elements elems = doc.getElementsByClass("profile");
			Elements links = elems.select("a[href]");

			System.out.println("codechef: Found " + links.size()
					+ " Problems linked in your profile page");

			for (Element link : links) {
				String linkurl = link.attr("abs:href");

				if (linkurl.contains("status")) {
					String problemId = link.text();
					Problem problem = new CodechefProblem(problemId, "");

					// if it is a proper "status" page, fetch this page
					Document page = Jsoup.connect(linkurl).get();

					Elements rows = page.select(".kol");

					// loop through the rows to find the first Accepted
					// submissions
					for (Element tr : rows) {

						Elements tds = tr.getElementsByTag("td");

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

							// We have found the AC submission for the current
							// problem
							// break now from the for loop to avoid adding more
							// submissions of the same problem.

							break;
						}
					}
				}
			}

		} catch (IOException e) {
			System.err.println("codechef: Error fetching list." + " -> "
					+ e.getMessage());
			// e.printStackTrace();
		}
		System.out.println("codechef: Found " + submissions.size()
				+ " Submissions");

		return submissions;
	}

	@Override
	public List<Submission> getAllSolvedList(User user) {
		String url = LISTPAGE + user.getHandle();
		ArrayList<Submission> submissions = new ArrayList<Submission>();

		try {
			// fetch the main page
			Document doc = Jsoup.connect(url).timeout(10000).get();

			// fetch the div which contains the list of solved problems
			Elements elems = doc.getElementsByClass("profile");

			Elements links = elems.select("a[href]");

			System.out.println("codechef: Found " + links.size()
					+ " Problems linked in your profile page");

			for (Element link : links) {
				String linkurl = link.attr("abs:href");

				if (linkurl.contains("status")) {
					String problemId = link.text();
					Problem problem = new CodechefProblem(problemId, "");

					// if it is a proper "status" page
					// fetch this page
					Document page = Jsoup.connect(linkurl).get();
					Elements rows = page.select(".kol");

					for (Element tr : rows) {
						// Element tr = page.select(".kol").get(0);
						Elements tds = tr.getElementsByTag("td");

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
						}
					}
				}
			}

		} catch (IOException e) {
			System.err.println("codechef: Error fetching list." + " -> "
					+ e.getMessage());
			// e.printStackTrace();
		}
		System.out.println("codechef: Found " + submissions.size()
				+ " Submissions");
		return submissions;
	}
}

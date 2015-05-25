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
			Document doc = Jsoup.connect(url).get();

			// fetch the div which contains the list of solved
			Elements elems = doc.getElementsByClass("profile");

			Elements links = elems.select("a[href]");
			for (Element link : links) {
				String linkurl = link.attr("abs:href");
				if (linkurl.contains("status")) {
					String problemId = link.text();
					Problem problem = new CodechefProblem(problemId, "");
					// if it is a proper "status" page
					// fetch this page
					Document page = Jsoup.connect(linkurl).get();

					// TODO: extend app to process more than one submission
					// Currently let us fetch only the first submission
					Element tr = page.select(".kol").get(0);
					Elements tds = tr.getElementsByTag("td");
					String id = tds.get(0).text();
					String time = tds.get(1).text();
					String lang = tds.get(6).text();

					String solUrl = tds.get(7).select("a[href]")
							.attr("abs:href");
					// TODO: the solUrl is of pattern
					// http://www.codechef.com/viewsolution/2078521
					// It should be
					// http://www.codechef.com/viewplaintext/2078521
					solUrl = solUrl.replace("viewsolution", "viewplaintext");

					Submission sub = new CodechefSubmission(id, solUrl,
							problem, user);
					// TODO: Fix the code language
					sub.setLanguage(LanguagesEnum.findExtension(lang));
					sub.setTimestamp(time);
					submissions.add(sub);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("codechef: Error fetching list.");
			// e.printStackTrace();
		}
		System.out.println("codechef: Found " + submissions.size()
				+ " Submissions");
		return submissions;
	}
}

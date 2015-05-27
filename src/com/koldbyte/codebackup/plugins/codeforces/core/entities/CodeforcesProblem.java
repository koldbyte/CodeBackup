package com.koldbyte.codebackup.plugins.codeforces.core.entities;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;

import com.koldbyte.codebackup.core.entities.Problem;

/*
 * 
 * example of problemId -> 545-B
 */
public class CodeforcesProblem extends Problem {
	private final String HTTP = "http://";
	private final String PROBLEMURL = "codeforces.com/contest/:c/problem/:p";
	private final String GYMPROBLEMURL = "codeforces.com/gym/:c/problem/:p";

	@Override
	public String fetchProblemStatement() {

		/*-
		 * Codeforces problem page look like this 
		 *  <div class="problemindexholder" problemindex="B"> 
		 *  		<div class="ttypography"> 
		 *  			<div class="problem-statement"> ..... </div>
		 * 			</div> 
		 * 	</div>
		 */

		// fetch the page containing the problem statement
		Document doc;
		String url = getUrl();

		try {
			doc = Jsoup.connect(url).timeout(10000).get();

			// remove html entities from the code
			doc.outputSettings().escapeMode(EscapeMode.xhtml);

			Elements problems = doc.getElementsByClass("problemindexholder");

			System.out.println("codeforces: fetched problem " + problemId);

			this.setProblemStatement(problems.html());
		} catch (IOException e) {
			System.err.println("codeforces: Error fetching Problem Statement "
					+ problemId + " -> " + e.getMessage());
			// e.printStackTrace();
		}

		return this.problemStatement;
	}

	@Override
	public String getUrl() {
		if (url == null || url.isEmpty()) {
			String[] id = problemId.split("-");
			String problemUrl = PROBLEMURL;
			if (id[0].length() > 3) {
				problemUrl = GYMPROBLEMURL;
			}

			// replace ":c" with the contest id(like
			problemUrl = problemUrl.replace(":c", id[0]);

			// replace ":p" with the problem id (like A,B,C etc)
			problemUrl = problemUrl.replace(":p", id[1]);

			url = HTTP + problemUrl;
			this.setUrl(url);
		}
		return url;
	}

	@Override
	public String getProblemId() {
		if (problemId == null || problemId.isEmpty()) {
			String u = getUrl();
			u = u.replace("codeforces.com/contest/", "");
			u = u.replace("/problem/", "-");
			this.setProblemId(u);
		}
		return this.problemId;
	}

	public CodeforcesProblem(String problemId, String url) {
		super(problemId, url);
	}

	public CodeforcesProblem(String url) {
		super(url);
	}

}

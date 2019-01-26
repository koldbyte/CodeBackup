package com.koldbyte.codebackup.plugins.spoj.core.entities;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;

import com.koldbyte.codebackup.core.entities.Problem;

public class SpojProblem extends Problem {
	private final String HTTPS = "https://";
	private final String PROBLEMURL = "www.spoj.com/problems/:p/";

	@Override
	public String fetchProblemStatement() {
		String problem = getProblemId();
		String url = HTTPS + PROBLEMURL.replace(":p", problem);
		/*-
		 * Spoj Problem Page looks like this 
		 * 
		 * <div id="problem-body"> 
		 * 	.......
		 * </div>
		 */

		Document doc;

		try {
			doc = Jsoup.connect(url).timeout(10000).get();

			// remove html entities from the code
			doc.outputSettings().escapeMode(EscapeMode.xhtml);

			Element problemBody = doc.getElementById("problem-body");

			System.out.println("spoj: fetched problem " + problemId);

			this.setProblemStatement(problemBody.html());
		} catch (IOException e) {
			System.err.println("spoj: Error fetching Problem Statement "
					+ problemId + " -> " + e.getMessage());
			// e.printStackTrace();
		}

		return this.problemStatement;
	}

	@Override
	public String getUrl() {
		if (url == null || url.isEmpty()) {

			String problemUrl = PROBLEMURL;

			// replace ":p" with the problem id
			problemUrl.replace(":p", problemId);

			url = HTTPS + PROBLEMURL;
			this.setUrl(url);
		}
		return url;
	}

	@Override
	public String getProblemId() {
		if (problemId == null || problemId.isEmpty()) {
			String u = getUrl();
			u = u.replace(HTTPS, "");
			u = u.replace("www.spoj.com/problems/", "");
			u = u.replace("/", "");
			this.setProblemId(u);
		}
		return this.problemId;
	}

	public SpojProblem(String problemId, String url) {
		super(problemId, url);
	}

	public SpojProblem(String url) {
		super(url);
	}

}

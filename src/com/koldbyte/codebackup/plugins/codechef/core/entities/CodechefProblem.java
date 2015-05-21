package com.koldbyte.codebackup.plugins.codechef.core.entities;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.koldbyte.codebackup.core.entities.Problem;

public class CodechefProblem extends Problem {
	private final String HTTP = "http://";
	private final String PROBLEMURLPREFIX = "www.codechef.com/problems/";

	public CodechefProblem(String url) {
		super(url);
	}

	public CodechefProblem(String problemId, String url) {
		super(problemId, url);
	}

	@Override
	public String fetchProblemStatement() {
		/*-
		 * Codechef problem page looks like
		 * 
		 * 	<div class="primary-colum-width-left">
		 * 			......
		 * 	</div>
		 */
		Document doc;
		try {
			String u = getUrl();
			doc = Jsoup.connect(u).get();
			Elements problems = doc
					.getElementsByClass("primary-colum-width-left");
			this.setProblemStatement(problems.html());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.problemStatement;
	}

	@Override
	public String getUrl() {
		if (url == null || url.isEmpty()) {
			url = HTTP + PROBLEMURLPREFIX + problemId;
		}
		return url;
	}

	@Override
	public String getProblemId() {
		if (problemId == null || problemId.isEmpty()) {
			String u = getUrl();
			u = u.replace(HTTP, "");
			u = u.replace(PROBLEMURLPREFIX, "");
			this.setProblemId(u);
		}
		return super.getProblemId();
	}

}

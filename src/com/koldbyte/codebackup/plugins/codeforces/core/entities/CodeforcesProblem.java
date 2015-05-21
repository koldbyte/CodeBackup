package com.koldbyte.codebackup.plugins.codeforces.core.entities;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.koldbyte.codebackup.core.entities.Problem;

/*
 * 
 * example of problemId -> 545-B
 */
public class CodeforcesProblem extends Problem {
	private final String HTTP = "http://";
	private final String PROBLEMURL = "codeforces.com/contest/:c/problem/:p";

	
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
		try {
			doc = Jsoup.connect("http://example.com/").get();

			Elements problems = doc.getElementsByClass("problemindexholder");
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
			String[] id = problemId.split("-");
			String problemUrl = PROBLEMURL;

			// replace ":c" with the contest id(like
			problemUrl.replace(":c", id[0]);

			// replace ":p" with the problem id (like A,B,C etc)
			url = HTTP + PROBLEMURL + problemId;
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
		return super.getProblemId();
	}

	public CodeforcesProblem(String problemId, String url) {
		super(problemId, url);
		
	}

}

package com.koldbyte.codebackup.plugins.codechef.core.entities;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.koldbyte.codebackup.core.entities.Problem;
import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;

public class CodechefSubmission extends Submission {

	private final String HTTP = "http://";
	private final String SOLUTIONURLPREFIX = "www.codechef.com/viewsolution/";

	public CodechefSubmission(String submissionId, String submissionUrl,
			Problem problem, User user) {
		super(submissionId, submissionUrl, problem, user);
	}

	@Override
	public String fetchSubmittedCode() {
		String subUrl = getSubmissionUrl();
		try {
			Document doc = Jsoup.connect(subUrl).get();
			Elements elem = doc.select("#solutiondiv");
			elem = elem.select("pre");
			//TODO: Language can be set here also
			//String language = elem.attr("class");
			String code = elem.get(1).text();
			setCode(code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public String getSubmissionIdFromUrl() {
		// format http://www.codechef.com/viewsolution/1559626
		String url = submissionUrl;
		url = url.replace(HTTP, "");
		url = url.replace(SOLUTIONURLPREFIX, "");
		this.submissionId = url;
		return url;
	}

	@Override
	public String getSubmissionUrlFromId() {
		return HTTP + SOLUTIONURLPREFIX + submissionId;
	}

}

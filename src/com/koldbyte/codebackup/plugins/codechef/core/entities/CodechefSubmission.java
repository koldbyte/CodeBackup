package com.koldbyte.codebackup.plugins.codechef.core.entities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;

import com.koldbyte.codebackup.core.entities.Problem;
import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;

public class CodechefSubmission extends Submission {

	private final String HTTP = "http://";
	private final String SOLUTIONURLPREFIX = "www.codechef.com/viewplaintext/";

	public CodechefSubmission(String submissionId, String submissionUrl,
			Problem problem, User user) {
		super(submissionId, submissionUrl, problem, user);
	}

	@Override
	public String fetchSubmittedCode() {
		String subUrl = getSubmissionUrl();
		String code = "";

		try {
			Document doc = Jsoup.connect(subUrl).timeout(10000).get();

			// remove html entities from the code
			doc.outputSettings().escapeMode(EscapeMode.xhtml);

			code = doc.select("pre").text();

			System.out.println("codechef: fetched code " + submissionId);

			setCode(code.toString());
		} catch (Exception e) {
			System.err.println("codechef: Error Fetching code " + submissionId
					+ " -> " + e.getMessage());
			// e.printStackTrace();
		}

		return code.toString();
	}

	@Override
	public String getSubmissionIdFromUrl() {
		// format http://www.codechef.com/viewplaintext/5189360
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

package com.koldbyte.codebackup.plugins.codeforces.core.entities;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;

import com.koldbyte.codebackup.core.entities.Problem;
import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;

public class CodeforcesSubmission extends Submission {
	private final String SUBMISSIONURL = "http://codeforces.com/contest/:c/submission/:s";
	private final String GYMSUBMISSIONURL = "http://codeforces.com/gym/:c/submission/:s";

	@Override
	public String fetchSubmittedCode() {
		String url = submissionUrl;
		if (url == null || url.isEmpty()) {
			url = getSubmissionUrlFromId();
		}

		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();

			// remove html entities from the code
			doc.outputSettings().escapeMode(EscapeMode.xhtml);

			Elements elem = doc.select("pre.program-source");
			String code = elem.text();

			System.out.println("codeforces: fetched code " + submissionId);

			setCode(code);
		} catch (IOException e) {
			System.err.println("codeforces: Error Fetching code " + submissionId
					+ " -> " + e.getMessage());
			// e.printStackTrace();
		}

		return code;
	}

	@Override
	public String getSubmissionIdFromUrl() {
		String submissionId = submissionUrl.substring(11 + submissionUrl
				.indexOf("submission/", 0));
		return submissionId;
	}

	@Override
	public String getSubmissionUrlFromId() {
		String contestId = problem.getProblemId();
		String submissionUrl;
		if (contestId.length() > 3) {
			submissionUrl = GYMSUBMISSIONURL
					.replace(":c", contestId.toString()).replace(":s",
							submissionId);
		} else {
			submissionUrl = SUBMISSIONURL.replace(":c", contestId.toString())
					.replace(":s", submissionId);
		}
		return submissionUrl;
	}

	public CodeforcesSubmission(String submissionId, String submissionUrl,
			Problem problem, User user) {
		super(submissionId, submissionUrl, problem, user);
	}

}

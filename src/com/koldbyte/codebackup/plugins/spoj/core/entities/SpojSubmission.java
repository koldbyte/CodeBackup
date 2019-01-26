package com.koldbyte.codebackup.plugins.spoj.core.entities;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.koldbyte.codebackup.core.entities.Problem;
import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;

public class SpojSubmission extends Submission {
	private final String HTTPS = "https://";
	private final String SUBMITTEDURL = "www.spoj.com/files/src/save/:s/";
	private final String LOGINURL = HTTPS + "www.spoj.com/login";

	@Override
	public String fetchSubmittedCode() {
		/*
		 * Requires username and password to get the source code so simulate
		 * login
		 */

		try {
			Connection.Response loginForm = Jsoup.connect(LOGINURL)
					.timeout(10000).method(Connection.Method.GET).execute();

			// System.out.println("Spoj username - "+ ((SpojUser)
			// user).getUsername());
			loginForm = Jsoup.connect(LOGINURL).data("next", "/")
					.data("login_user", ((SpojUser) user).getUsername())
					.data("password", ((SpojUser) user).getPass())
					.data("autologin", "1").cookies(loginForm.cookies())
					.method(Connection.Method.POST).execute();

			// login done...now use the cookies to whenever u are fetching code
			String url = HTTPS + SUBMITTEDURL.replace(":s", submissionId);
			String code = Jsoup.connect(url).ignoreContentType(true)
					.cookies(loginForm.cookies()).method(Connection.Method.GET)
					.execute().body();

			if (code.isEmpty()) {
				System.err.println("Error! Invalid Spoj Credentials");
			} else {
				System.out.println("spoj: fetched code " + submissionId);
				setCode(code);
			}
		} catch (IOException e) {
			System.err.println("spoj: Error Fetching code" + submissionId
					+ " -> " + e.getMessage());
			// e.printStackTrace();
		}

		return code;
	}

	@Override
	public String getSubmissionIdFromUrl() {
		String url = getSubmissionUrl();
		url = url.replace(HTTPS, "");
		url = url.replace("www.spoj.com/files/src/save/", "");
		url = url.replace("/", "");
		return url;
	}

	@Override
	public String getSubmissionUrlFromId() {
		String subId = HTTPS + SUBMITTEDURL.replace(":s", getSubmissionId());
		return subId;
	}

	public SpojSubmission(String submissionId, String submissionUrl,
			Problem problem, User user) {
		super(submissionId, submissionUrl, problem, user);
	}

}

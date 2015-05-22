package com.koldbyte.codebackup.plugins.spoj.core.entities;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.koldbyte.codebackup.core.entities.Problem;
import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;

public class SpojSubmission extends Submission {
	private final String HTTP = "http://";
	private final String SUBMITTEDURL = "www.spoj.com/files/src/save/:s/";
	private final String LOGINURL = "http://www.spoj.com/login";

	@Override
	public String fetchSubmittedCode() {
		/*
		 * Requires username and password to get the source code so simulate
		 * login
		 */

		try {
			Connection.Response loginForm = Jsoup.connect(LOGINURL)
					.method(Connection.Method.GET).execute();

			System.out.println("Spoj username - "
					+ ((SpojUser) user).getUsername());
			loginForm = Jsoup.connect(LOGINURL).data("next", "/")
					.data("login_user", ((SpojUser) user).getUsername())
					.data("password", ((SpojUser) user).getPass())
					.data("autologin", "1").cookies(loginForm.cookies())
					.method(Connection.Method.POST).execute();

			// login done...now use the cookies to whenever u are fetching code
			String url = HTTP + SUBMITTEDURL.replace(":s", submissionId);
			String code = Jsoup.connect(url).ignoreContentType(true)
					.cookies(loginForm.cookies()).method(Connection.Method.GET)
					.execute().body();
			if(code.isEmpty()){
				System.out.println("Error! Invalid Spoj Credentials");
			}
			setCode(code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return code;
	}

	@Override
	public String getSubmissionIdFromUrl() {
		String url = getSubmissionUrl();
		url = url.replace(HTTP, "");
		url = url.replace("www.spoj.com/files/src/save/", "");
		url = url.replace("/", "");
		return url;
	}

	@Override
	public String getSubmissionUrlFromId() {
		String subId = HTTP + SUBMITTEDURL.replace(":s", getSubmissionId());
		return subId;
	}

	public SpojSubmission(String submissionId, String submissionUrl,
			Problem problem, User user) {
		super(submissionId, submissionUrl, problem, user);
	}

}

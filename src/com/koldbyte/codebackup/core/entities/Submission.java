package com.koldbyte.codebackup.core.entities;

public abstract class Submission {
	protected String submissionId;
	protected String submissionUrl;
	protected String timestamp;
	protected String code;
	protected LanguagesEnum language;
	protected Problem problem;
	protected User user;

	public Submission() {
		super();
	}

	public Submission(String submissionUrl) {
		super();
		this.submissionUrl = submissionUrl;
		this.submissionId = getSubmissionIdFromUrl();
	}

	public Submission(String submissionId, Problem problem) {
		super();
		this.submissionId = submissionId;
		this.submissionUrl = getSubmissionUrlFromId();
		this.problem = problem;
	}

	public Submission(String submissionId, String submissionUrl,
			Problem problem, User user) {
		super();
		this.submissionId = submissionId;
		this.submissionUrl = submissionUrl;
		this.problem = problem;
		this.user = user;

		if(submissionId == null || submissionId.isEmpty())
			this.submissionId = getSubmissionId();
		
		if(submissionUrl == null || submissionUrl.isEmpty())
			this.submissionUrl = getSubmissionUrlFromId();
	}

	public String getSubmissionUrl() {
		if (submissionUrl == null || submissionUrl.isEmpty()) {
			submissionUrl = getSubmissionUrlFromId();
		}
		return submissionUrl;
	}

	public User getUser() {
		return user;
	}

	public String getSubmissionId() {
		return submissionId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getCode() {
		if (code == null || code.isEmpty()) {
			code = fetchSubmittedCode();
		}
		return code;
	}

	public LanguagesEnum getLanguage() {
		return language;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setSubmissionUrl(String submissionUrl) {
		this.submissionUrl = submissionUrl;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setSubmissionId(String submissionId) {
		this.submissionId = submissionId;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setLanguage(LanguagesEnum language) {
		this.language = language;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public abstract String fetchSubmittedCode();

	public abstract String getSubmissionIdFromUrl();

	public abstract String getSubmissionUrlFromId();
}

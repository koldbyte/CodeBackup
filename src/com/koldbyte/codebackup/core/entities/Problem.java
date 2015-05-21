package com.koldbyte.codebackup.core.entities;

public abstract class Problem {
	protected String problemId;
	protected String url;
	protected String problemStatement;

	public Problem() {
		super();
	}

	public Problem(String url) {
		super();
		this.url = url;
	}

	public Problem(String problemId, String url) {
		super();
		this.problemId = problemId;
		this.url = url;
	}

	public String getProblemId() {
		return problemId;
	}

	public String getUrl() {
		return url;
	}

	public String getProblemStatement() {
		if (problemStatement == null || problemStatement.isEmpty()) {
			problemStatement = fetchProblemStatement();
		}
		return problemStatement;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setProblemStatement(String problemStatement) {
		this.problemStatement = problemStatement;
	}

	@Override
	public String toString() {
		return "Problem [problemId=" + problemId + ", url=" + url + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((problemId == null) ? 0 : problemId.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Problem other = (Problem) obj;
		if (problemId == null) {
			if (other.problemId != null)
				return false;
		} else if (!problemId.equals(other.problemId))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	public abstract String fetchProblemStatement();

}

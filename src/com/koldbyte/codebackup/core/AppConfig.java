package com.koldbyte.codebackup.core;

public class AppConfig {
	public static Boolean overWrite = false;
	public static Boolean fetchProblem = false;

	public static Boolean getOverWrite() {
		return overWrite;
	}

	public static Boolean getFetchProblem() {
		return fetchProblem;
	}

	public static void setOverWrite(Boolean overWrite) {
		AppConfig.overWrite = overWrite;
	}

	public static void setFetchProblem(Boolean fetchProblem) {
		AppConfig.fetchProblem = fetchProblem;
	}

}

package com.koldbyte.codebackup.core;
/*
 * Stores the user selected options from the UI
 */
public class AppConfig {
	public static Boolean overWrite = false;
	public static Boolean fetchProblem = false;
	public static Boolean fetchAllAC = false;

	public static Boolean getOverWrite() {
		return overWrite;
	}

	public static Boolean getFetchProblem() {
		return fetchProblem;
	}

	public static Boolean getFetchAllAC() {
		return fetchAllAC;
	}

	public static void setFetchAllAC(Boolean fetchAllAC) {
		AppConfig.fetchAllAC = fetchAllAC;
	}

	public static void setOverWrite(Boolean overWrite) {
		AppConfig.overWrite = overWrite;
	}

	public static void setFetchProblem(Boolean fetchProblem) {
		AppConfig.fetchProblem = fetchProblem;
	}

}

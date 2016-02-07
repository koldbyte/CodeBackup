package com.koldbyte.codebackup.plugins.codeforces.core.entities;

import com.koldbyte.codebackup.core.entities.User;

public class CodeforcesUser extends User {
	private final String HTTP = "http://";
	private final String PROFILEURLPREFIX = "codeforces.com/profile/";

	public CodeforcesUser(String handle) {
		super(handle);
	}

	public CodeforcesUser(String handle, String profileUrl) {
		super(handle, profileUrl);
	}

	@Override
	public String getHandleFromProfileUrl() {
		String handle = profileUrl;
		handle = handle.replace(HTTP, "");
		handle = handle.replace(PROFILEURLPREFIX, "");
		return handle;
	}

	@Override
	public String getProfileUrlFromHandle() {
		return HTTP + PROFILEURLPREFIX + this.handle;
	}

	@Override
	public Boolean isValidUser() {
		return true;
	}

}

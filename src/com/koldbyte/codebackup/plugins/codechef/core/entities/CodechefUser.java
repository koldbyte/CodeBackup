package com.koldbyte.codebackup.plugins.codechef.core.entities;

import com.koldbyte.codebackup.core.entities.User;

public class CodechefUser extends User {
	private final String HTTP = "http://";
	private final String PROFILEURLPREFIX = "www.codechef.com/users/";

	public CodechefUser(String handle) {
		super(handle);
	}

	public CodechefUser(String handle, String profileUrl) {
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

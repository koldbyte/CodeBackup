package com.koldbyte.codebackup.plugins.codechef.core.entities;

import com.koldbyte.codebackup.core.entities.User;

public class CodechefUser extends User {
	private final String HTTP = "http://";
	private final String PROFILEURLPREFIX = "www.codechef.com/users/";

	@Override
	public String getHandleFromProfileUrl() {
		return HTTP + PROFILEURLPREFIX + this.handle;
	}

	@Override
	public String getProfileUrlFromHandle() {
		String url = profileUrl;
		url = url.replace(HTTP, "");
		url = url.replace(PROFILEURLPREFIX, "");
		return url;
	}
}

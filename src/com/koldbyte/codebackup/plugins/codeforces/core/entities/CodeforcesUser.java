package com.koldbyte.codebackup.plugins.codeforces.core.entities;

import com.koldbyte.codebackup.core.entities.User;

public class CodeforcesUser extends User {
	private final String HTTP = "http://";
	private final String PROFILEURLPREFIX = "codeforces.com/profile/";
	
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

package com.koldbyte.codebackup.plugins.spoj.core.entities;

import com.koldbyte.codebackup.core.entities.User;

public class SpojUser extends User {
	private final String HTTPS = "https://";
	private final String PROFILEURL = "www.spoj.com/users/:u/";

	private String username;
	private String pass;

	public String getUsername() {
		return username;
	}

	public String getPass() {
		return pass;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public SpojUser(String handle) {
		super(handle);
	}

	public SpojUser(String handle, String profileUrl) {
		super(handle, profileUrl);
	}

	@Override
	public String getHandleFromProfileUrl() {
		String handle = profileUrl;
		handle = handle.replace(HTTPS, "");
		handle = handle.replace("www.spoj.com/users/", "");
		handle = handle.replace("/", "");
		return handle;
	}

	@Override
	public String getProfileUrlFromHandle() {
		return HTTPS + PROFILEURL.replace(":u", this.handle);
	}

	@Override
	public Boolean isValidUser() {
		return true;
	}

}

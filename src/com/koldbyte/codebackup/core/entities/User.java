package com.koldbyte.codebackup.core.entities;

public abstract class User {
	protected String handle;
	protected String profileUrl;

	public User() {
		super();
	}

	public User(String handle) {
		super();
		this.handle = handle;
		this.profileUrl = getProfileUrlFromHandle();
	}

	public User(String handle, String profileUrl) {
		super();
		this.handle = handle;
		this.profileUrl = profileUrl;
	}

	public String getHandle() {
		if (handle == null || handle.isEmpty()) {
			if (profileUrl != null && !profileUrl.isEmpty()) {
				handle = getHandleFromProfileUrl();
			}
		}
		return handle;
	}

	public String getProfileUrl() {
		if (profileUrl == null || profileUrl.isEmpty()) {
			if (handle != null && !handle.isEmpty()) {
				profileUrl = getProfileUrlFromHandle();
			}
		}
		return profileUrl;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public abstract String getHandleFromProfileUrl();

	public abstract String getProfileUrlFromHandle();

	public abstract Boolean isValidUser();
}

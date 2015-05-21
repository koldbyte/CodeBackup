package com.koldbyte.codebackup.core;

public class AppStatus {
	private AppStatus m_instance = null;

	String plugin;
	String group;
	String current;

	private AppStatus() {
		super();
	}

	public AppStatus getInstance() {
		if (m_instance == null) {
			m_instance = new AppStatus();
		}
		return m_instance;
	}

	public String getPlugin() {
		return plugin;
	}

	public String getGroup() {
		return group;
	}

	public String getCurrent() {
		return current;
	}

	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

}

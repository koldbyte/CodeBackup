package com.koldbyte.codebackup.plugins;

import com.koldbyte.codebackup.plugins.codechef.CodechefPluginImpl;
import com.koldbyte.codebackup.plugins.codeforces.CodeforcesPluginImpl;
import com.koldbyte.codebackup.plugins.spoj.SpojPluginImpl;

public enum PluginEnum {
	CODECHEF("codechef"), CODEFORCES("codeforces"), SPOJ("spoj");

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private PluginEnum(String name) {
		this.name = name;
	}

	public PluginInterface getPlugin() {
		switch (name) {
		case "codechef":
			return new CodechefPluginImpl();
		case "codeforces":
			return new CodeforcesPluginImpl();
		case "spoj":
			return new SpojPluginImpl();
		default:
			return null;
		}

	}
}

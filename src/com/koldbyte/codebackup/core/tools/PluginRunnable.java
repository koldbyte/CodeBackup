package com.koldbyte.codebackup.core.tools;

import java.util.List;

import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;
import com.koldbyte.codebackup.plugins.PluginEnum;
import com.koldbyte.codebackup.plugins.PluginInterface;

/*
 * This class will interface with all the plugins
 *  as defined in the PluginEnum.java to fetch their
 *  respective submission list.
 *  
 *  This class will also 
 */
public class PluginRunnable implements Runnable {
	private User user;
	private PluginEnum pluginEnum;
	private String dir;

	public PluginRunnable(String dir, User user, PluginEnum pluginEnum) {
		super();
		this.dir = dir;
		this.user = user;
		this.pluginEnum = pluginEnum;

	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public PluginRunnable() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PluginEnum getPluginEnum() {
		return pluginEnum;
	}

	public void setPluginEnum(PluginEnum pluginEnum) {
		this.pluginEnum = pluginEnum;
	}

	@Override
	public void run() {
		PluginInterface plugin = pluginEnum.getPlugin();
		if (user != null) {
			List<Submission> subs = plugin.getSolvedList(user);
			for (Submission sub : subs) {
				sub.fetchSubmittedCode();
				PersistHandler.save(pluginEnum.getName(), dir, sub);
			}
		}
	}
}

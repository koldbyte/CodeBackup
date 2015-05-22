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
public class PluginManager {
	private PluginManager m_instance = null;
	private User user;

	private PluginManager() {
		super();
	}

	public PluginManager getInstance() {
		if (m_instance == null) {
			m_instance = new PluginManager();
		}
		return m_instance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Submission> runPlugin(PluginEnum pluginEnum) {
		PluginInterface plugin = pluginEnum.getPlugin();
		if (user != null) {
			return plugin.getSolvedList(user);
		} else {
			return null;
		}
	}
}

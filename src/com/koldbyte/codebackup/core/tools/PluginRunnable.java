package com.koldbyte.codebackup.core.tools;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
	public JLabel progressIcon;
	private ImageIcon tick;

	public PluginRunnable(String dir, User user, PluginEnum pluginEnum) {
		super();
		this.dir = dir;
		this.user = user;
		this.pluginEnum = pluginEnum;
		tick = new ImageIcon(this.getClass().getResource("/tick.png"));
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

	public JLabel getProgressIcon() {
		return progressIcon;
	}

	public void setProgressIcon(JLabel progressIcon) {
		this.progressIcon = progressIcon;
	}

	@Override
	public void run() {
		PluginInterface plugin = pluginEnum.getPlugin();
		if (user != null) {
			new Logger().getInstance().addStatus("Started " + pluginEnum.name());
			List<Submission> subs = plugin.getSolvedList(user);
			for (Submission sub : subs) {
				sub.fetchSubmittedCode();
				PersistHandler.save(pluginEnum.getName(), dir, sub);
			}
			// Mark the status of the Plugin as finished successfully
			if (progressIcon != null) {
				progressIcon.setIcon(tick);
			}
			new Logger().getInstance().addStatus("Finished " + pluginEnum.name());
		}
	}
}

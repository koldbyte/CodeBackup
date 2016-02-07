package com.koldbyte.codebackup.core.tools;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import com.koldbyte.codebackup.core.AppConfig;
import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;
import com.koldbyte.codebackup.plugins.PluginEnum;
import com.koldbyte.codebackup.plugins.PluginInterface;

/*
 * This class will interface with all the plugins
 *  as defined in the PluginEnum.java to fetch their
 *  respective submission list.
 *  
 */
public class PluginWorker extends SwingWorker<Integer, Integer> {
	private User user;
	private PluginEnum pluginEnum;
	private String dir;
	private ImageIcon tick;
	private JLabel progressIcon;

	public PluginWorker(String dir, User user, PluginEnum pluginEnum,
			JLabel progress) {
		super();
		this.dir = dir;
		this.user = user;
		this.pluginEnum = pluginEnum;
		this.tick = new ImageIcon(this.getClass().getResource("/tick.png"));
		this.progressIcon = progress;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public PluginWorker() {
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
	protected Integer doInBackground() throws Exception {
		PluginInterface plugin = pluginEnum.getPlugin();
		if (user != null) {
			System.out.println("Started " + pluginEnum.name());
			List<Submission> subs;
			if (AppConfig.getFetchAllAC()) {
				System.out.println(pluginEnum.name() + ": Mode-> Fetch All Accepted submissions.");
				subs = plugin.getAllSolvedList(user);
			} else {
				System.out.println(pluginEnum.name() + ": Mode-> Fetch only the last Accepted submissions.");
				subs = plugin.getSolvedList(user);
			}
			for (Submission sub : subs) {
				sub.fetchSubmittedCode();
				PersistHandler.save(pluginEnum.getName(), dir, sub);
				if (AppConfig.fetchProblem == true) {
					PersistHandler.saveProblem(pluginEnum.getName(), dir, sub);
				}
			}

		}
		return 0;
	}

	@Override
	protected void done() {
		progressIcon.setIcon(tick);
		// Mark the status of the Plugin as finished successfully
		System.out.println("Finished " + pluginEnum.name());
		super.done();
	}
}

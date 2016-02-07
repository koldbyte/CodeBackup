package com.koldbyte.codebackup.plugins;

import java.util.List;

import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;
/*
 * This is the interface which every plugin has to implement.
 */
public interface PluginInterface {
	public List<Submission> getSolvedList(User user);
	public List<Submission> getAllSolvedList(User user);
}

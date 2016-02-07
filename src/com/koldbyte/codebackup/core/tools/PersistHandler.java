package com.koldbyte.codebackup.core.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.koldbyte.codebackup.core.AppConfig;
import com.koldbyte.codebackup.core.entities.Submission;

/*
 * This class is responsible to save the submission in a proper directory
 */
public class PersistHandler {
	public static void save(String pluginName, String dir, Submission sub) {
		/*
		 * final destination will look like dir / handle / ContestSite /
		 * problemId / problemId-submissionId.ext
		 */
		String sep = File.separator;
		// TODO: Allow user to specify custom format for the final destination
		String finalDestination = dir + sep + sub.getUser().getHandle() + sep
				+ pluginName + sep + sub.getProblem().getProblemId() + sep;
		String fileName = sub.getProblem().getProblemId() + "-"
				+ sub.getSubmissionId() + "."
				+ sub.getLanguage().getExtension();
		// Handle file name extensions for code
		File file = new File(finalDestination + fileName);

		// now make sure whole path is created
		file.getParentFile().mkdirs();
		if (!AppConfig.overWrite && file.exists() && file.length() != 0) {
			// skip this file
			System.out.println(pluginName + ": skipped overwriting code "
					+ sub.getSubmissionId());
		} else {
			// Saved the file
			try (FileWriter writer = new FileWriter(file)) {
				System.out.println(pluginName + ": saving code "
						+ sub.getSubmissionId());
				writer.write(sub.getCode());
			} catch (IOException e) {
				System.err.println(pluginName + ": Error saving code "
						+ sub.getSubmissionId() + "-> " + e.getMessage());
				// e.printStackTrace();
			}
		}
	}

	public static void saveProblem(String pluginName, String dir, Submission sub) {
		/*
		 * final destination will look like dir / handle / ContestSite /
		 * problemId / problemId-submissionId.ext
		 */
		String sep = File.separator;
		// TODO: Allow user to specify custom format for the final destination
		String finalDestination = dir + sep + sub.getUser().getHandle() + sep
				+ pluginName + sep + sub.getProblem().getProblemId() + sep;
		String fileName = sub.getProblem().getProblemId().trim() + "-Statement.html";
		System.out.println(fileName);
		File file = new File(finalDestination + fileName);
		// now make sure whole path is created
		file.getParentFile().mkdirs();
		if (!AppConfig.overWrite && file.exists() && file.length() != 0) {
			// skip this file
			// Will not skip if the file size is 0
			System.out.println(pluginName + ": skipped overwriting statement "
					+ sub.getProblem().getProblemId());
		} else {
			try (FileWriter writer = new FileWriter(file)) {
				System.out.println(pluginName + ": saving problem statement "
						+ sub.getSubmissionId());
				writer.write(sub.getProblem().getProblemStatement());
			} catch (IOException e) {
				System.err.println(pluginName
						+ ": Error saving problem statement "
						+ sub.getProblem().getProblemId() + " -> "
						+ e.getMessage());
				// e.printStackTrace();
			}
		}
	}
}

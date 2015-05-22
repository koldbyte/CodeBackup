package com.koldbyte.codebackup.core.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
				+ sub.getSubmissionId() + ".txt";
		File file = new File(finalDestination + fileName);
		// now make sure whole path is created
		file.getParentFile().mkdirs();
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(sub.getCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

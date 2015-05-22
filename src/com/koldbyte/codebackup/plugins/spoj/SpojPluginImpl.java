package com.koldbyte.codebackup.plugins.spoj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.koldbyte.codebackup.core.entities.Problem;
import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;
import com.koldbyte.codebackup.plugins.PluginInterface;
import com.koldbyte.codebackup.plugins.spoj.core.entities.SpojProblem;
import com.koldbyte.codebackup.plugins.spoj.core.entities.SpojSubmission;

public class SpojPluginImpl implements PluginInterface {

	private final String HTTP = "http://";
	private final String SUBMISSIONLIST = "www.spoj.com/status/:u/signedlist/";

	@Override
	public List<Submission> getSolvedList(User user) {
		List<Submission> subs = new ArrayList<Submission>();
		String url = HTTP + SUBMISSIONLIST.replace(":u", user.getHandle());

		try {
			Connection.Response response = Jsoup.connect(url)
					.method(Connection.Method.GET).execute();

			String lines[] = response.body().split("\n");
			// ignore first 9 lines 0...1...2......8
			if (lines.length < 9) {
				System.out.println("Error! Invalid Format of Spoj signedlist");
			} else {
				int i = 9; // start from 9th line
				Map<String, Boolean> problemsDone = new HashMap<String, Boolean>();
				String end = "\\------------------------------------------------------------------------------/";
				while (i < lines.length && lines[i].compareTo(end) != 0) {
					String subEntry[] = lines[i].split("\\|");
					// if solution is AC or status is a score
					String status = subEntry[4].trim();
					if (status.compareToIgnoreCase("AC") == 0
							|| status.matches("-?\\d+(\\.\\d+)?")) {
						String problem = subEntry[3].trim();
						if (!problemsDone.containsKey(problem)) {
							String sId = subEntry[1].trim();
							Problem p = new SpojProblem(problem, "");
							// TODO: fix language setting for spoj
							// String lang = subEntry[7].trim();
							String time = subEntry[2].trim();
							Submission submission = new SpojSubmission(sId, "",
									p, user);
							submission.setTimestamp(time);
							subs.add(submission);
							problemsDone.put(problem, true);
						}
					}
					i++;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return subs;
	}
}

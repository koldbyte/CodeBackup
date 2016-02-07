package com.koldbyte.codebackup.plugins.codeforces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.koldbyte.codebackup.core.entities.LanguagesEnum;
import com.koldbyte.codebackup.core.entities.Problem;
import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;
import com.koldbyte.codebackup.plugins.PluginInterface;
import com.koldbyte.codebackup.plugins.codeforces.core.entities.CodeforcesProblem;
import com.koldbyte.codebackup.plugins.codeforces.core.entities.CodeforcesSubmission;
import com.koldbyte.codebackup.utils.HTTPRequest;

public class CodeforcesPluginImpl implements PluginInterface {

	private final String url = "http://codeforces.com/api/user.status";
	private final String SUBMISSIONURL = "http://codeforces.com/contest/:c/submission/:s";
	private final String GYMSUBMISSIONURL = "http://codeforces.com/gym/:c/submission/:s";

	public List<Submission> getSolvedList(User user) {
		String urlParameters = "handle=" + user.getHandle();
		HTTPRequest request = new HTTPRequest(url + "?" + urlParameters, "");
		
		List<Submission> list = new ArrayList<Submission>();
		
		try {
			StringBuffer response = request.sendGet();
			
			JSONParser parser = new JSONParser();
			JSONObject responseObject = (JSONObject) parser.parse(response
					.toString());
			JSONArray submissions = (JSONArray) responseObject.get("result");
			
			Map<String, Boolean> problemsDone = new HashMap<String, Boolean>();
			
			for (Object o : submissions) {
				JSONObject submission = (JSONObject) o;
				String verdict = (String) submission.get("verdict");

				if (verdict.compareToIgnoreCase("ok") == 0) {
					JSONObject prob = (JSONObject) submission.get("problem");
					
					Long contestId = (Long) prob.get("contestId");
					String problemId = contestId.toString() + "-"
							+ (String) prob.get("index");
					if (!problemsDone.containsKey(problemId)) {
						Problem problem = new CodeforcesProblem(problemId, "");

						Long sId = (Long) submission.get("id");
						String submissionId = sId.toString();

						String submissionUrl;
						if (contestId.toString().length() > 3) {
							submissionUrl = GYMSUBMISSIONURL.replace(":c",
									contestId.toString()).replace(":s",
									submissionId);
						} else {
							submissionUrl = SUBMISSIONURL.replace(":c",
									contestId.toString()).replace(":s",
									submissionId);
						}
						// System.out.println("URL -> " + submissionUrl);
						String time = ((Long) submission
								.get("creationTimeSeconds")).toString();


						String lang = (String) submission
								.get("programmingLanguage");

						Submission theSubmission = new CodeforcesSubmission(
								submissionId, submissionUrl, problem, user);
						
						theSubmission.setTimestamp(time);
						theSubmission.setLanguage(LanguagesEnum
								.findExtension(lang));
						
						list.add(theSubmission);
						
						problemsDone.put(problemId, true);
					}
				}
			}

		} catch (Exception e) {
			System.err.println("codeforces: Error fetching list. " + " -> "
					+ e.getMessage());
			// e.printStackTrace();
		}
		System.out.println("codeforces: fetched List " + list.size());
		return list;
	}

	@Override
	public List<Submission> getAllSolvedList(User user) {
		String urlParameters = "handle=" + user.getHandle();

		HTTPRequest request = new HTTPRequest(url + "?" + urlParameters, "");
		List<Submission> list = new ArrayList<Submission>();
		
		try {
			StringBuffer response = request.sendGet();
			
			JSONParser parser = new JSONParser();
			JSONObject responseObject = (JSONObject) parser.parse(response
					.toString());
			JSONArray submissions = (JSONArray) responseObject.get("result");
			
			for (Object o : submissions) {
				JSONObject submission = (JSONObject) o;
				String verdict = (String) submission.get("verdict");

				if (verdict.compareToIgnoreCase("ok") == 0) {
					JSONObject prob = (JSONObject) submission.get("problem");
					
					Long contestId = (Long) prob.get("contestId");
					String problemId = contestId.toString() + "-"
							+ (String) prob.get("index");

					Problem problem = new CodeforcesProblem(problemId, "");

					Long sId = (Long) submission.get("id");
					String submissionId = sId.toString();

					String submissionUrl;
					if (contestId.toString().length() > 3) {
						submissionUrl = GYMSUBMISSIONURL.replace(":c",
								contestId.toString()).replace(":s",
								submissionId);
					} else {
						submissionUrl = SUBMISSIONURL.replace(":c",
								contestId.toString()).replace(":s",
								submissionId);
					}
					// System.out.println("URL -> " + submissionUrl);
					String time = ((Long) submission.get("creationTimeSeconds"))
							.toString();

					String lang = (String) submission
							.get("programmingLanguage");

					Submission theSubmission = new CodeforcesSubmission(
							submissionId, submissionUrl, problem, user);
					
					theSubmission.setTimestamp(time);
					theSubmission
							.setLanguage(LanguagesEnum.findExtension(lang));
					
					list.add(theSubmission);

				}
			}

		} catch (Exception e) {
			System.err.println("codeforces: Error fetching list. " + " -> "
					+ e.getMessage());
			// e.printStackTrace();
		}
		System.out.println("codeforces: fetched List " + list.size());
		return list;
	}
}

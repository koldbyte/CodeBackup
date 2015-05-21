package com.koldbyte.codebackup.plugins.codeforces;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;
import com.koldbyte.codebackup.plugins.PluginInterface;
import com.koldbyte.codebackup.utils.HTTPRequest;

public class CodeforcesPluginImpl implements PluginInterface {

	private final String url = "http://codeforces.com/api/user.status";

	@Override
	public List<Submission> getSolvedList(User user) {
		String urlParameters = "handle=" + user.getHandle();

		HTTPRequest request = new HTTPRequest(url, urlParameters);

		try {
			StringBuffer response = request.sendPost();
			JSONParser parser = new JSONParser();
			JSONObject submissions = (JSONObject) parser.parse(response.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return null;
	}

}

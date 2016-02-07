package com.koldbyte.codebackup.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRequest {
	private String userAgent = "Mozilla/5.0";
	private String url = "http://www.google.com/search?q=mkyong";
	private String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
	private int connectTimeout = 10000;
	private int socketTimeout = 10000;

	public HTTPRequest(String userAgent, String url, String urlParameters) {
		super();
		this.userAgent = userAgent;
		this.url = url;
		this.urlParameters = urlParameters;
	}

	public HTTPRequest(String url, String urlParameters) {
		super();
		this.url = url;
		this.urlParameters = urlParameters;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlParameters() {
		return urlParameters;
	}

	public void setUrlParameters(String urlParameters) {
		this.urlParameters = urlParameters;
	}

	// HTTP GET request
	public StringBuffer sendGet() throws Exception {		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// set Timeouts
		con.setConnectTimeout(connectTimeout);
		con.setReadTimeout(socketTimeout);

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", userAgent);

		// int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		// System.out.println(response.toString());

		return response;
	}

	// HTTP POST request
	public StringBuffer sendPost() throws Exception {
				
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// set Timeouts
		con.setConnectTimeout(connectTimeout);
		con.setReadTimeout(socketTimeout);

		// add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", userAgent);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		// int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'POST' request to URL : " + url);
		// System.out.println("Post parameters : " + urlParameters);
		// System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();


		return response;
	}
}

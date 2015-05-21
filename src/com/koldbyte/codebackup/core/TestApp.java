package com.koldbyte.codebackup.core;

import java.util.List;

import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;
import com.koldbyte.codebackup.plugins.PluginInterface;
import com.koldbyte.codebackup.plugins.codechef.CodechefPluginImpl;
import com.koldbyte.codebackup.plugins.codechef.core.entities.CodechefUser;
import com.koldbyte.codebackup.plugins.codeforces.CodeforcesPluginImpl;
import com.koldbyte.codebackup.plugins.codeforces.core.entities.CodeforcesUser;

public class TestApp {
	public static void main(String[] args) {
		// test1(); //passed //test explode function
		// test2(); //passed //test fetch list of submissions - codechef
		// test3(); //passed //test fetch of code - codechef
		// test4(); //passed //test fetch list of submissions - codeforces
		// test5(); //passed //test fetch of code - codeforces
	}

	public static void test1() {
		// System.out.println(StringUtils.explode(new StringBuffer("abcbcbd"),
		// "a", "d"));
	}

	public static void test2() {
		User u = new CodechefUser("bhaskardivya",
				"http://www.codechef.com/users/bhaskardivya");
		PluginInterface plugin = new CodechefPluginImpl();
		List<Submission> list = plugin.getSolvedList(u);
		for (Submission s : list) {
			System.out.println("Sub->" + s.getSubmissionId() + " ,problem->"
					+ s.getProblem().getProblemId() + ", url->"
					+ s.getSubmissionUrl());
		}
	}

	public static void test3() {
		User u = new CodechefUser("bhaskardivya",
				"http://www.codechef.com/users/bhaskardivya");
		PluginInterface plugin = new CodechefPluginImpl();
		List<Submission> list = plugin.getSolvedList(u);
		for (Submission s : list) {
			System.out.println("Sub->" + s.getSubmissionId() + " ,problem->"
					+ s.getProblem().getProblemId() + ", url->"
					+ s.getSubmissionUrl());
			System.out.println(s.getCode());
		}
	}
	
	public static void test4() {
		User u = new CodeforcesUser("koldbyte",
				"http://codeforces.com/profile/koldbyte");
		PluginInterface plugin = new CodeforcesPluginImpl();
		List<Submission> list = plugin.getSolvedList(u);
		for (Submission s : list) {
			System.out.println("Sub->" + s.getSubmissionId() + " ,problem->"
					+ s.getProblem().getProblemId() + ", url->"
					+ s.getSubmissionUrl());
			//System.out.println(s.getCode());
		}
	}
	
	public static void test5() {
		User u = new CodeforcesUser("koldbyte",
				"http://codeforces.com/profile/koldbyte");
		PluginInterface plugin = new CodeforcesPluginImpl();
		List<Submission> list = plugin.getSolvedList(u);
		for (Submission s : list) {
			System.out.println("Sub->" + s.getSubmissionId() + " ,problem->"
					+ s.getProblem().getProblemId() + ", url->"
					+ s.getSubmissionUrl());
			System.out.println(s.getCode());
		}
	}
}

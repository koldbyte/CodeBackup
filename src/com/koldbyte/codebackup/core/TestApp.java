package com.koldbyte.codebackup.core;

import java.util.List;

import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;
import com.koldbyte.codebackup.plugins.PluginInterface;
import com.koldbyte.codebackup.plugins.codechef.CodechefPluginImpl;
import com.koldbyte.codebackup.plugins.codechef.core.entities.CodechefUser;

public class TestApp {
	public static void main(String[] args) {
		// test1(); //passed
		// test2(); //passed
		// test3(); //passed
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
}

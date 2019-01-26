package com.koldbyte.codebackup.core;

import java.util.List;

import com.koldbyte.codebackup.core.entities.Problem;
import com.koldbyte.codebackup.core.entities.Submission;
import com.koldbyte.codebackup.core.entities.User;
import com.koldbyte.codebackup.plugins.PluginInterface;
import com.koldbyte.codebackup.plugins.codechef.CodechefPluginImpl;
import com.koldbyte.codebackup.plugins.codechef.core.entities.CodechefProblem;
import com.koldbyte.codebackup.plugins.codechef.core.entities.CodechefUser;
import com.koldbyte.codebackup.plugins.codeforces.CodeforcesPluginImpl;
import com.koldbyte.codebackup.plugins.codeforces.core.entities.CodeforcesProblem;
import com.koldbyte.codebackup.plugins.codeforces.core.entities.CodeforcesUser;
import com.koldbyte.codebackup.plugins.spoj.SpojPluginImpl;
import com.koldbyte.codebackup.plugins.spoj.core.entities.SpojProblem;
import com.koldbyte.codebackup.plugins.spoj.core.entities.SpojSubmission;
import com.koldbyte.codebackup.plugins.spoj.core.entities.SpojUser;

public class TestApp {
	public static void main(String[] args) {
		// test1(); //passed //test explode function

		/*
		 * Testing Codechef
		 */
		// test2(); //passed //test fetch list of submissions - codechef
		// test3(); //passed //test fetch of code - codechef
		// test6(); //passed but output contains some extra contents like
		// comments //test fetch of problem Statement Codechef

		/*
		 * Testing Codeforces
		 */
		// test4(); //passed //test fetch list of submissions - codeforces
		// test5(); //passed //test fetch of code - codeforces
		// test7(); //passed //test fetch of problem Statement Codeforces

		/*
		 * Testing Spoj
		 */

		// test8(); //passed //test fetch of problem Statement SPoj
		//test9(); // //test fetch list of submissions - spoj
		// test10(); // passed //test fetch of code - Spoj
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
			// System.out.println(s.getCode());
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

	public static void test6() {
		Problem problem = new CodechefProblem(
				"http://www.codechef.com/problems/AGENTS");
		System.out.println(problem.getProblemStatement());
	}

	public static void test7() {
		Problem problem = new CodeforcesProblem(
				"http://codeforces.com/contest/2/problem/B");
		System.out.println(problem.getProblemStatement());
	}

	public static void test8() {
		Problem problem = new SpojProblem("https://www.spoj.com/problems/FCTRL/");
		System.out.println(problem.getProblemStatement());
	}

	public static void test9() {
		User u = new SpojUser("koldbyte", "https://www.spoj.com/users/koldbyte/");
		PluginInterface plugin = new SpojPluginImpl();
		List<Submission> list = plugin.getSolvedList(u);
		for (Submission s : list) {
			System.out.println("Sub->" + s.getSubmissionId() + " ,problem->"
					+ s.getProblem().getProblemId() + ", url->"
					+ s.getSubmissionUrl());
			// System.out.println(s.getCode());
		}
	}

	public static void test10() {
		User u = new SpojUser("koldbyte", "https://www.spoj.com/users/koldbyte/");
		Problem p = new SpojProblem("https://www.spoj.com/problems/BITMAP/");
		((SpojUser) u).setUsername("koldbyte");
		((SpojUser) u).setPass("thisisrandompass");
		Submission s = new SpojSubmission("11808446",
				"www.spoj.com/files/src/save/11808446", p, u);
		System.out.println("Sub->" + s.getSubmissionId() + " ,problem->"
				+ s.getProblem().getProblemId() + ", url->"
				+ s.getSubmissionUrl());
		System.out.println("Code is ");
		System.out.println(s.getCode());

	}
}

package com.koldbyte.codebackup.core;

import com.koldbyte.codebackup.utils.StringUtils;

public class TestApp {
	public static void main(String[] args) {
		
		System.out.println(StringUtils.explode(new StringBuffer("abcbcbd"), "a", "d"));
	}
}

package com.koldbyte.codebackup.utils;

public class StringUtils {
	public static StringBuffer explode(StringBuffer content,String pre, String post){
		String result = content.substring(content.indexOf(pre) + 1, content.indexOf(post));
		return new StringBuffer(result);
	}
}

package com.koldbyte.codebackup.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class StringUtils {
	public static StringBuffer explode(StringBuffer content, String pre,
			String post) {
		String result = content.substring(content.indexOf(pre) + 1,
				content.indexOf(post));
		return new StringBuffer(result);
	}

	public static String br2nl(String html) {
		if (html == null)
			return html;
		Document document = Jsoup.parse(html);
		document.outputSettings(new Document.OutputSettings()
				.prettyPrint(false));// makes html() preserve linebreaks and
										// spacing
		document.select("br").append("\\n");
		document.select("p").prepend("\\n\\n");
		String s = document.html().replaceAll("\\\\n", "\n");
		return Jsoup.clean(s, "", Whitelist.none(),
				new Document.OutputSettings().prettyPrint(false));
	}
}

package org.fsps.webscrapper.searchEngine.parser;

import static org.jsoup.Jsoup.connect;

import java.util.ArrayList;
import java.util.List;

import org.fsps.webscrapper.searchEngine.page.TextualContent;
import org.fsps.webscrapper.searchEngine.page.WebPage;

public class JsoupParser implements WebPageParser {
	@Override
	public List<WebPage> parse(List<String> urls) {
		List<WebPage> result = new ArrayList<>();
		for(String url : urls) {
			WebPage page = null;
			try {
				page = parse(url);
			} catch(Exception e) {
			}
			if(page != null) {
				result.add(page);
			}
		}
		return result;
	}

	@Override
	public WebPage parse(String url) throws Exception {
		WebPage parsingResult = null;
		parsingResult = new TextualContent(connect(url).get(), url);
		return parsingResult;
	}
}

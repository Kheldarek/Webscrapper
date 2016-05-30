package org.fsps.webscrapper.searchingLogic.parser;

import static org.jsoup.Jsoup.connect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.fsps.webscrapper.searchingLogic.page.TextualContent;
import org.fsps.webscrapper.searchingLogic.page.WebPage;

public class JsoupParser implements WebPageParser {
	@Override
	public List<WebPage> parse(List<String> urls) {
		List<WebPage> result = new ArrayList<>();
		for(String url : urls) {
			WebPage page = null;
			try {
				page = parse(url);
			} catch(IOException ioe) {
			}
			if(page != null) {
				result.add(page);
			}
		}
		return result;
	}

	@Override
	public WebPage parse(String url) throws IOException {
		WebPage parsingResult = null;
		parsingResult = new TextualContent(connect(url).get(), url);
		return parsingResult;
	}
}

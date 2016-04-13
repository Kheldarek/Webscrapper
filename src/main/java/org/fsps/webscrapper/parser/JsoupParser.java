package org.fsps.webscrapper.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.fsps.webscrapper.page.WebPage;
import org.fsps.webscrapper.page.TextualContent;
import org.jsoup.Jsoup;

public class JsoupParser implements WebPageParser {

	@Override
	public WebPage parse(String url) throws IOException {
		WebPage parsingResult = new TextualContent(Jsoup.connect(url).get(), url);
		return parsingResult;
	}

	@Override
	public List<WebPage> parse(List<String> urls) throws IOException {
		List<WebPage> result = new ArrayList<>();
		for(String url : urls) {
			result.add(parse(url));
		}
		return result;
	}

}

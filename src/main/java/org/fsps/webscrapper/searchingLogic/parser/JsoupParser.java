package org.fsps.webscrapper.searchingLogic.parser;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.fsps.webscrapper.searchingLogic.page.WebPage;
import org.fsps.webscrapper.searchingLogic.page.TextualContent;
import static org.jsoup.Jsoup.connect;

public class JsoupParser implements WebPageParser {

	@Override
	public WebPage parse(String url) throws IOException, UnknownHostException {
		WebPage parsingResult = new TextualContent(connect(url).get(), url);
		return parsingResult;
	}

	@Override
	public List<WebPage> parse(List<String> urls) throws IOException, UnknownHostException {
		List<WebPage> result = new ArrayList<>();
		for(String url : urls) {
			result.add(parse(url));
		}
		return result;
	}

}

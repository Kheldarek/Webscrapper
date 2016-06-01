package org.fsps.webscrapper.searchEngine.parser;

import java.util.List;

import org.fsps.webscrapper.searchEngine.page.WebPage;

public interface WebPageParser {
	public List<WebPage> parse(List<String> urls);
	public WebPage parse(String url) throws Exception;
}

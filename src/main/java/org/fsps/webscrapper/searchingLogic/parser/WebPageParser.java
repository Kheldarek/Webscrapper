package org.fsps.webscrapper.searchingLogic.parser;

import java.io.IOException;
import java.util.List;

import org.fsps.webscrapper.searchingLogic.page.WebPage;

public interface WebPageParser {
	public List<WebPage> parse(List<String> urls);
	public WebPage parse(String url) throws  IOException;
}

package org.fsps.webscrapper.parser;

import java.io.IOException;
import java.util.List;

import org.fsps.webscrapper.page.WebPage;

public interface WebPageParser {
	public WebPage parse(String url) throws IOException;
	public List<WebPage> parse(List<String> urls) throws IOException;
}

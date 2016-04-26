package org.fsps.webscrapper.searchingLogic.parser;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.fsps.webscrapper.searchingLogic.page.WebPage;

public interface WebPageParser {
	public WebPage parse(String url) throws IOException, UnknownHostException;
	public List<WebPage> parse(List<String> urls) throws IOException, UnknownHostException;
}

package org.fsps.webscrapper.searchingLogic.searchEngine;

import java.util.List;

import org.fsps.webscrapper.searchingLogic.page.WebPage;

public interface BasicSearchEngine {
	public List<String> findByKeywords(List<String> keywords, List<WebPage> bunchOfPages);
	public List<String> findByKeywords(List<String> keywords, WebPage searchedPage);
	public List<String> findByText(String text, List<WebPage> bunchOfPages);
	public List<String> findByText(String text, WebPage searchedPage);
}

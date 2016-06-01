package org.fsps.webscrapper.searchEngine;

import java.util.List;

import org.fsps.webscrapper.searchEngine.page.WebPage;

public interface SearchEngine {
	public List<WebPage> findByKeywords(List<String> keywords, List<WebPage> bunchOfPages, int searchingLevel);
}
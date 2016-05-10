package org.fsps.webscrapper.searchingLogic;

import java.util.List;

import org.fsps.webscrapper.searchingLogic.page.WebPage;

public interface SearchEngine {
	public List<WebPage> findByKeywords(List<String> keywords, List<WebPage> bunchOfPages, int searchingLevel);
}
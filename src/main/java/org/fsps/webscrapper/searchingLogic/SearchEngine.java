package org.fsps.webscrapper.searchingLogic;

import org.fsps.webscrapper.searchingLogic.page.SearchBar;
import org.fsps.webscrapper.searchingLogic.page.TextualContent;

import java.util.List;

public interface SearchEngine {
	List<TextualContent> findByKeywords(List<String> keywords, List<SearchBar> searchBars);
}
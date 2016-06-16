package org.fsps.webscrapper.searchingLogic.parser;

import java.io.IOException;
import java.util.List;

import org.fsps.webscrapper.searchingLogic.page.SearchBar;
import org.fsps.webscrapper.searchingLogic.page.TextualContent;

public interface WebPageParser {
	List<SearchBar> parseSearchBar(List<String> urls);
	SearchBar parseSearchBar(String url) throws  IOException;
	List<TextualContent> parseResult(List<String> urls);
	TextualContent parseResult(String url) throws  IOException;
}

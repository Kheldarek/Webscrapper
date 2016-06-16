package org.fsps.webscrapper.presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.fsps.webscrapper.searchingLogic.SearchEngine;
import org.fsps.webscrapper.searchingLogic.page.SearchBar;
import org.fsps.webscrapper.searchingLogic.page.TextualContent;
import org.fsps.webscrapper.searchingLogic.page.WebPage;
import org.fsps.webscrapper.searchingLogic.parser.WebPageParser;
import org.fsps.webscrapper.view.SearchForm;

public class SearchEnginePresenter implements SeekPresenter {
	private SearchEngine searchEngine;
	private WebPageParser parser;

	public SearchEnginePresenter(SearchEngine searchEngine, WebPageParser parser) {
		this.searchEngine = searchEngine;
		this.parser = parser;
	}

	@Override
	public void findByKeywords(SearchForm sourceView, List<String> keywords, List<String> urls, int searchingLevel)
			throws IOException {
		List<SearchBar> bunchOfSearchPages = parser.parseSearchBar(urls);
        List<TextualContent> resultPages = searchEngine.findByKeywords(keywords, bunchOfSearchPages);
		List<List<String>> results = new ArrayList<>();
		List<String> resUrls = new ArrayList<>();
		for(TextualContent currentPage : resultPages) {
			resUrls.add(currentPage.getSourceUrl());
			results.add(currentPage.getParagraphsContaining(keywords));
		}
		sourceView.actualizeResultSet(results, resUrls);

	}
}

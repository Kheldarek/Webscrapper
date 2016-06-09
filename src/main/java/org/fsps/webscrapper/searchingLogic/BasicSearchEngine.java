package org.fsps.webscrapper.searchingLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import org.fsps.webscrapper.searchingLogic.page.WebPage;
import org.fsps.webscrapper.searchingLogic.parser.JsoupParser;
import org.fsps.webscrapper.searchingLogic.parser.WebPageParser;

public class BasicSearchEngine implements SearchEngine {
	private int currentLevel = 0;
	private int deepLevel;
	private List<WebPage> results = new ArrayList<>(0);
	private WebPageParser parser = new JsoupParser();

	public List<WebPage> findByKeywords(List<String> keywords, List<WebPage> startPages, int deepLevel) {
		this.deepLevel = deepLevel;
		search(keywords, startPages, this::searchTreeFromStartPage, 10);
		return results;
	}

	private void searchDeep(List<String> keywords, List<WebPage> startPages) {
		search(keywords, startPages, this::searchSublinks, 10);
	}

	private void search(List<String> keywords, List<WebPage> startPages,
			BiConsumer<List<String>, List<WebPage>> function, int urlsLimit) {
		for(WebPage currentPage : startPages) {
			if(currentPage.getParagraphsContaining(keywords).size() > 0 && !results.contains(currentPage)) {
				results.add(currentPage);
			}
			List<WebPage> links = parser.parse(currentPage.getLinksContaining(keywords, urlsLimit));
			function.accept(keywords, links);

		}
	}

	private void searchTreeFromStartPage(List<String> match, List<WebPage> pages) {
		searchDeep(match, pages);
		currentLevel = 0;
	}

	private void searchSublinks(List<String> keywords, List<WebPage> startPages) {
		if(currentLevel < deepLevel) {
			currentLevel++;
			searchDeep(keywords, startPages);
		}
	}
}

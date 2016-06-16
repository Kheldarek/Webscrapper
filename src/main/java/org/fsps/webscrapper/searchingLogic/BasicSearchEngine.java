package org.fsps.webscrapper.searchingLogic;

import org.fsps.webscrapper.searchingLogic.page.*;
import org.fsps.webscrapper.searchingLogic.parser.JsoupParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BasicSearchEngine implements SearchEngine {
	private JsoupParser parser;

    public BasicSearchEngine() {
        parser = new JsoupParser();
    }

	@Override
	public List<TextualContent> findByKeywords(List<String> keywords, List<SearchBar> searchBars) {
		List<String> searchLinks = getSearchLinks(keywords, searchBars);
		List<TextualContent> searchSemiResults = parser.parseResult(searchLinks);
		List<String> contentLinks = getSearchResultsURLS(keywords, searchSemiResults);
		return parser.parseResult(contentLinks);
	}

	private List<String> getSearchLinks(List<String> keywords, List<SearchBar> searchBars) {
		StringBuilder keywordsBuilder = buildKeywords(keywords);
		List<String> links = new ArrayList<>(searchBars.size());
		searchBars.forEach(searchBar -> links.add(searchBar.getSearchURL() + keywordsBuilder.toString()));
		return links;
	}

	private StringBuilder buildKeywords(List<String> keywords) {
		StringBuilder keywordsBuilder = new StringBuilder();
        keywords.forEach(keyword -> keywordsBuilder.append(keyword).append("+"));
		keywordsBuilder.deleteCharAt(keywordsBuilder.length() - 1);
		return keywordsBuilder;
	}

	private List<String> getSearchResultsURLS(List<String> keywords, List<TextualContent> searchSemiResults) {
		List<String> contentLinks = new ArrayList<>();
		for(TextualContent page : searchSemiResults) {
			page.getLinksContaining(keywords).stream().filter(linkFilter(contentLinks)).forEach(contentLinks::add);
		}
		return contentLinks;
	}

	private Predicate<String> linkFilter(List<String> contentLinks) {
		return link -> !contentLinks.contains(link);
	}
}

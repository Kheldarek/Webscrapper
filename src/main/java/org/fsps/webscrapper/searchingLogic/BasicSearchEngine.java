package org.fsps.webscrapper.searchingLogic;

import java.util.ArrayList;
import java.util.List;

import org.fsps.webscrapper.searchingLogic.page.WebPage;
import org.fsps.webscrapper.searchingLogic.parser.JsoupParser;
import org.fsps.webscrapper.searchingLogic.parser.WebPageParser;

public class BasicSearchEngine implements SearchEngine {
	private int currentLevel = 0;
	private List<WebPage> results = new ArrayList<>(0);
	private WebPageParser parser = new JsoupParser();
	
	public List<WebPage> findByKeywords(List<String> keywords, List<WebPage> startPages, int deepLevel) {
		for(WebPage currentPage : startPages) {
			if(currentPage.getParagraphsContaining(keywords).size() > 0 && !results.contains(currentPage)) {
				results.add(currentPage);
			}
			List<WebPage> links = parser.parse(currentPage.getLinksContaining(keywords));
			searchDeep(keywords, links, deepLevel);
			currentLevel = 0;
		}
		return results;
	}
	
	private void searchDeep(List<String> keywords, List<WebPage> startPages, int deepLevel) {
		for(WebPage currentPage : startPages) {
			if(currentPage.getParagraphsContaining(keywords).size() > 0 && !results.contains(currentPage)) {
				results.add(currentPage);
			}
			List<WebPage> links = parser.parse(currentPage.getLinksContaining(keywords));
			if(currentLevel < deepLevel) {
				currentLevel++;
				searchDeep(keywords, links, deepLevel);
			}
		}
	}
}

package org.fsps.webscrapper.searchingLogic.page;

import java.util.List;

public interface WebPage {
	String getSourceUrl();
	List<String> getLinks();
	List<String> getLinksContaining(List<String> keywords);
	List<String> getLinksContaining(String match);
	List<String> getParagraphs();
	List<String> getParagraphsContaining(List<String> keywords);
	List<String> getParagraphsContaining(String match);
}

package org.fsps.webscrapper.searchingLogic.page;

import java.util.List;

public interface WebPage {
	public String getSourceUrl();
	public List<String> getLinks();
	public List<String> getLinksContaining(List<String> keywords, int urlsLimit);
	public List<String> getLinksContaining(List<String> keywords);
	public List<String> getLinksContaining(String match);
	public List<String> getParagraphs();
	public List<String> getParagraphsContaining(List<String> keywords);
	public List<String> getParagraphsContaining(String match);
}

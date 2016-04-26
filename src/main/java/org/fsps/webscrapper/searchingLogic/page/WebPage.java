package org.fsps.webscrapper.searchingLogic.page;

import java.util.List;

public interface WebPage {
	public String getSourceUrl();
	public List<String> getLinks();
	public List<String> getLinksContaining(List<String> match);
	public List<String> getLinksContaining(String match);
	public List<String> getParagraphs();
	public List<String> getParagraphsContaining(List<String> match);
	public List<String> getParagraphsContaining(String match);
}

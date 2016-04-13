package org.fsps.webscrapper.page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TextualContent implements WebPage {
	private Document parsedPage;
	private String sourceUrl;

	public TextualContent(Document parsedPage, String sourceUrl) {
		this.parsedPage = parsedPage;
		this.sourceUrl = sourceUrl;
	}
	
	@Override
	public String getSourceUrl() {
		return sourceUrl;
	}
	
	@Override
	public List<String> getLinks() {
		List<String> result = getLinksContaining("");
		return result;
	}
	
	@Override
	public List<String> getLinksContaining(String match) {
		return getElementsContaining(match, "a", elem -> elem.attr("abs:href"));
	}

	@Override
	public List<String> getParagraphs() {
		return getParagraphsContaining("");
	}
	
	@Override
	public List<String> getParagraphsContaining(String match) {
		return getElementsContaining(match, "p", elem -> elem.text());
	}
	
	private List<String> getElementsContaining(String match, String tag, Function<Element, String> elementReader) {
		Elements taggedPageContent = parsedPage.getElementsByTag(tag);
		return createMatchingElemsList(match, elementReader, taggedPageContent);
	}

	private List<String> createMatchingElemsList(String match, Function<Element, String> elementReader, Elements pageContent) {
		List<String> result = new ArrayList<>(pageContent.size());
		for(Element taggedElement : pageContent) {
			if(taggedElement.toString().contains(match)) {
				result.add(elementReader.apply(taggedElement));
			}
		}
		return result;
	}

}

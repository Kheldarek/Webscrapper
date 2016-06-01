package org.fsps.webscrapper.searchingLogic.page;

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
		return getLinksContaining("");
	}
	
	@Override
	public List<String> getLinksContaining(List<String> keywords, int urlsLimit) {
		List<String> result = new ArrayList<>();
		for(String match : keywords) {
			result.addAll(getLinksContaining(match));
		}
		if(result.size() > urlsLimit) {
			return result.subList(0, urlsLimit);
		}
		return result;
	}

	@Override
	public List<String> getLinksContaining(List<String> keywords) {
		List<String> result = new ArrayList<>();
		for(String match : keywords) {
			result.addAll(getLinksContaining(match));
		}
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
	public List<String> getParagraphsContaining(List<String> keywords) {
		List<String> result = new ArrayList<>();
		for(String match : keywords) {
			result.addAll(getParagraphsContaining(match));
		}
		return result;
	}

	@Override
	public List<String> getParagraphsContaining(String match) {
		return getElementsContaining(match, "p", elem -> elem.text());
	}

	private List<String> getElementsContaining(String match, String tag, Function<Element, String> elementReader) {
		Elements taggedPageContent = parsedPage.getElementsByTag(tag);
		return createMatchingElemsList(match, elementReader, taggedPageContent);
	}

	private List<String> createMatchingElemsList(String match, Function<Element, String> elementReader,
			Elements pageContent) {
		List<String> result = new ArrayList<>(pageContent.size());
		for(Element taggedElement : pageContent) {
			if(taggedElement.toString().toLowerCase().contains(match.toLowerCase())) {
				result.add(elementReader.apply(taggedElement));
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if(object == null || !(object instanceof TextualContent)) {
			return false;
		}
		TextualContent secondPage = (TextualContent) object;
		if(isTheSameObject(secondPage) || areFieldsIdentical(secondPage)) {
			return true;
		}
		return false;
	}

	private boolean isTheSameObject(TextualContent secondPage) {
		return secondPage == this;
	}

	private boolean areFieldsIdentical(TextualContent secondPage) {
		return sourceUrl.equals(secondPage.sourceUrl) && parsedPage.equals(secondPage.parsedPage);
	}
}

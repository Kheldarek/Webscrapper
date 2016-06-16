package org.fsps.webscrapper.searchingLogic.page;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.stream.Stream;

/**
 * Created by Filip Sochal on 2016-06-16.
 */
public class SearchBar {
    private Document parsedPage;
    private String sourceUrl;

    public SearchBar(Document parsedPage, String sourceUrl) {
        this.parsedPage = parsedPage;
        this.sourceUrl = sourceUrl;
    }

    public String getSearchURL() {
        String result = "";
        Stream<Element> elems = parsedPage.getElementsByTag("form").stream().filter(this::searchFormFilter);
        for(Object elem : elems.toArray()) {
            result += getSearchLink((Element) elem);
        }
        return result;
    }

    private boolean searchFormFilter(Element elem) {
        String formHTML = elem.toString().toLowerCase();
        return formHTML.contains("search") || formHTML.contains("szuka");
    }

    private String getSearchLink(Element element) {
        Element elem = getSearchbar(element);
        String result = prepareSearchURL(element, elem);
        return result;
    }

    private String prepareSearchURL(Element element, Element elem) {
        String result = "";
        if(elem != null) {
            if((element.attr("action").charAt(0) == '/')) {
                result = sourceUrl;
            }
            result += element.attr("action") + "?" + elem.attr("name") + "=";
        }
        return result;
    }

    private Element getSearchbar(Element element) {
        Element elem = null;
        for(Element x : element.getElementsByTag("input")) {
            if(x.attr("type").contains("search") || x.attr("type").contains("text")
                    || x.attr("title").contains("search") || x.attr("title").toLowerCase().contains("szuka")) {
                if(!x.attr("type").toLowerCase().contains("submit")) {
                    elem = x;
                }
            }
        }
        return elem;
    }
}

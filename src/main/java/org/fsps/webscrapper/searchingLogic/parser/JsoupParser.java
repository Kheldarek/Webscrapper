package org.fsps.webscrapper.searchingLogic.parser;

import static org.jsoup.Jsoup.connect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.fsps.webscrapper.searchingLogic.page.SearchBar;
import org.fsps.webscrapper.searchingLogic.page.TextualContent;

public class JsoupParser implements WebPageParser {
	@Override
	public List<SearchBar> parseSearchBar(List<String> urls) {
        List<SearchBar> result = new ArrayList<>();
        for(String url : urls) {
            SearchBar page = null;
            try {
                page = parseSearchBar(url);
            } catch(IOException ioe) {
                System.err.println("Error while parsing page: " + url);
            }
            if(page != null) {
                result.add(page);
            }
        }
        return result;
	}

	@Override
	public SearchBar parseSearchBar(String url) throws IOException {
		return new SearchBar(connect(url).get(), url);
	}

	@Override
	public List<TextualContent> parseResult(List<String> urls) {
		List<TextualContent> result = new ArrayList<>();
		for(String url : urls) {
			TextualContent page = null;
			try {
				page = parseResult(url);
			} catch(IOException ioe) {
                System.err.println("Error while parsing page: " + url);
			}
			if(page != null) {
				result.add(page);
			}
		}
		return result;
	}

	@Override
	public TextualContent parseResult(String url) throws IOException {
		return new TextualContent(connect(url).get(), url);
	}
}

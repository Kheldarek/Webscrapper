package org.fsps.webscrapper.presenter;

import org.fsps.webscrapper.view.SearchForm;

import java.io.IOException;
import java.util.List;

public interface SeekPresenter {
	void findByKeywords(SearchForm source, List<String> keywords, List<String> urls, int searchingLevel) throws IOException;
}

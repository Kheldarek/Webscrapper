package org.fsps.webscrapper.presenter;

import java.io.IOException;
import java.util.List;

import org.fsps.webscrapper.view.SearchForm;

public interface SeekPresenter {
	public void findByKeywords(SearchForm source, List<String> keywords, List<String> urls, int searchingLevel) throws IOException;
}

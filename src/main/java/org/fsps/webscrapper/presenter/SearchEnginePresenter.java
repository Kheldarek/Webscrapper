package org.fsps.webscrapper.presenter;

import java.util.List;

import org.fsps.webscrapper.view.SearchForm;

public interface SearchEnginePresenter {
	public void findByKeywords(List<String> keywords, List<String> urls);
	public void setView(SearchForm source);
}

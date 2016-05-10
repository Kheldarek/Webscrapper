package org.fsps.webscrapper.view;

import java.util.Collection;
import java.util.List;

import org.fsps.webscrapper.presenter.SeekPresenter;

public interface SearchForm {
	public List<String> getUrls();
	public void actualizeResultSet(Collection<String> results);
	public void setPresenter(SeekPresenter presenter);

}

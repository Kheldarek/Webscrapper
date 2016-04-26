package org.fsps.webscrapper.view;

import java.util.Collection;
import java.util.List;

public interface SearchForm {
	public List<String> getUrls();
	public void actualizeResultSet(Collection<String> results);

}

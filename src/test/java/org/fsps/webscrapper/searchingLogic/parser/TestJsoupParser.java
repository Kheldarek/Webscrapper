package org.fsps.webscrapper.searchingLogic.parser;

import org.fsps.webscrapper.searchEngine.page.WebPage;
import org.fsps.webscrapper.searchEngine.parser.JsoupParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

public class TestJsoupParser {
	private static JsoupParser parser; 
	
	@BeforeClass
	public static void setUpParser() {
		parser = new JsoupParser();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testURLWithoutHttpPrefix() throws Exception {
		parser.parse("www.google.com");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testURLWithoutHttpAndWWWPrefix() throws Exception {
		parser.parse("google.com");
	}
	
	@Test
	public void testParserReturnWebPage() throws Exception {
		WebPage page = parser.parse("http://google.com");
		assertNotNull(page);
		page = parser.parse("http://www.google.com");
		assertNotNull(page);
	}
}

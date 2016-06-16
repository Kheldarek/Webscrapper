package org.fsps.webscrapper.searchingLogic.parser;

import org.fsps.webscrapper.searchingLogic.page.WebPage;
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
	public void testURLWithoutHttpPrefix() throws IOException {
		parser.parseResult("www.google.com");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testURLWithoutHttpAndWWWPrefix() throws IOException {
		parser.parseResult("google.com");
	}
	
	@Test
	public void testParserReturnWebPage() throws IOException {
		WebPage page = parser.parseResult("http://google.com");
		assertNotNull(page);
		page = parser.parseResult("http://www.google.com");
		assertNotNull(page);
	}
}

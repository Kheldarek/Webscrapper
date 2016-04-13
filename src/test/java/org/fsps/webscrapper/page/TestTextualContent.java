package org.fsps.webscrapper.page;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TestTextualContent {
	private static TextualContent testedPage;
	private final static String url = "http://www.fsps.org/";
	
	@BeforeClass
	public static void setUpTestedPage() throws IOException {
		Path testHtmlPath = Paths.get("src", "test", "resources", "testTextualContent.html");
		Document document = Jsoup.parse(new String(Files.readAllBytes(testHtmlPath), Charset.defaultCharset()), url);
		testedPage = new TextualContent(document, url);
	}
	
	@Test
	public void readAllParagraphs() {
		assertEquals(3, testedPage.getParagraphs().size());
	}
	
	@Test
	public void readParahraphsContainingOtherMarkupInside() {
		List<String> paragraphs = testedPage.getParagraphsContaining(",");
		String result = ""; 
		if(paragraphs.size() > 0) {
			result = paragraphs.get(0);
		}
		assertEquals("Bold, second paragraph!", result);
		if(paragraphs.size() > 1) {
			result = paragraphs.get(1);
		}
		assertEquals("Third, italic paragraph.", result);
	}
	
	@Test
	public void readAllLinks() {
		assertEquals(3, testedPage.getLinks().size());
	}
	
	@Test
	public void readLinksContainingMatchingText() {
		List<String> links = testedPage.getLinksContaining("go");
		String result = ""; 
		if(links.size() > 0) {
			result = links.get(0);
		}
		assertEquals("http://www.google.pl/", result);
	}
	
	@Test
	public void readRelativeLinksAsAbsolute() {
		List<String> links = testedPage.getLinksContaining("rel");
		String result = ""; 
		if(links.size() > 0) {
			result = links.get(0);
		}
		assertEquals(url + "relative_1", result);
		
		if(links.size() > 1) {
			result = links.get(1);
		}
		assertEquals(url + "relative_2", result);
	}
}

package org.fsps.webscrapper;

import org.fsps.webscrapper.page.TestTextualContent;
import org.fsps.webscrapper.parser.TestJsoupParser;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestTextualContent.class,
	TestJsoupParser.class
})
public class AppTest {}

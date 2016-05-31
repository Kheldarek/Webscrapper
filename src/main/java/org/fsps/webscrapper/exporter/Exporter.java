package org.fsps.webscrapper.exporter;

import org.fsps.webscrapper.view.mainWindow.WebscrapperGUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by psend on 30.05.2016.
 */
public class Exporter
{
	List<List<String>> paragraphs;
	List<String> urls;
	final static String HTML_BEGIN = "<html><head><title>Exported from Websrapper</title></head><body>";
	final static String HTML_END = "</body></html>";
	final static String HTML_PARAGRAPH = "<p>%s</p>";
	final static String HTML_LINK = "<a href = %s >%s</a>";

	public Exporter(List<List<String>> paragraphs, List<String> urls)
	{
		this.paragraphs = paragraphs;
		this.urls = urls;
	}

	public void ExportToHTML(String path)
	{
		String content = HTML_BEGIN;
		File file = new File(path);

		for (int i = 0; i < urls.size(); i++)
		{
			content = content + String.format(HTML_LINK, urls.get(i),urls.get(i) ) + "\n\n";
			for (String tmp : paragraphs.get(i))
			{
				content += String.format(HTML_PARAGRAPH, tmp)  +  "\n\n" ;
			}
		}

		content += HTML_END;
		SavetoFile(file,content);

	}

	private void SavetoFile(File file, String content)
	{
		try
		{
			FileWriter fileWriter = null;
			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex)
		{
			System.err.println(ex.getMessage());
		}
	}

	public void ExportToTxt(String path)
	{
		File file = new File(path);
		String content = "";
		for (int i = 0; i < urls.size(); i++)
		{
			content += urls.get(i) + "\n\n";
			for (String tmp : paragraphs.get(i))
			{
				content += tmp + "\n\n";
			}
		}

		SavetoFile(file,content);



	}


}

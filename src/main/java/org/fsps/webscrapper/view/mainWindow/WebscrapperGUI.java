package org.fsps.webscrapper.view.mainWindow;
/**
 * Created by psend on 26.04.2016.
 */

import java.io.IOException;
import java.util.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.fsps.webscrapper.exporter.Exporter;
import org.fsps.webscrapper.presenter.SearchEnginePresenter;
import org.fsps.webscrapper.searchingLogic.BasicSearchEngine;
import org.fsps.webscrapper.searchingLogic.parser.JsoupParser;
import org.fsps.webscrapper.view.FileSelector.FileSelector;
import org.fsps.webscrapper.presenter.SeekPresenter;
import org.fsps.webscrapper.view.SearchForm;

public class WebscrapperGUI implements SearchForm
{
	private Scene scene;
	private Button searchBtn;
	public TextField urls, keywords;
	public TextArea results;
	private VBox vbox;
	private MenuBar menu;
	private Menu file, edit, help;
	private SeekPresenter presenter;
	private int depth = 2;
	private List<List<String>> paragraphsList;
	private List<String> urlsList;
	private Exporter exporter;
	private Alert searchAlert;


	public WebscrapperGUI() throws IOException
	{
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("webscrapperGU.fxml"));
		scene = new Scene(root, 800, 600);
		searchBtn = (Button) root.getBottom();
		vbox = (VBox) root.getTop();
		urls = (TextField) vbox.getChildren().get(1);
		keywords = (TextField) vbox.getChildren().get(2);
		results = (TextArea) root.getCenter();
		results.setEditable(false);
		results.setWrapText(true);
		menu = (MenuBar) vbox.getChildren().get(0);
		file = menu.getMenus().get(0);
		edit = menu.getMenus().get(1);
		help = menu.getMenus().get(2);
		SetEvents();

	}

	private void ChangeDepth()
	{
		TextInputDialog dialog = new TextInputDialog(depth + "");
		dialog.setTitle("Change Search Depth");
		dialog.setHeaderText("How deep do You want to search?");
		dialog.setContentText("Enter depth level");


		Optional<String> result = dialog.showAndWait();
		if (result.isPresent())
		{
			try
			{
				depth = Integer.parseInt(result.get());
			} catch (NumberFormatException e)
			{
				depth = 2;
			}
		}

	}

	private void SetEvents()
	{
		WebscrapperGUI mainwindow = this;

		file.getItems().get(0).setOnAction(event -> {
			keywords.setText("");
			FileSelector root = new FileSelector(mainwindow, false);
			Stage stage = new Stage();
			root.start(stage);
		});
		file.getItems().get(1).setOnAction(event -> {
			urls.setText("");
			FileSelector root = new FileSelector(mainwindow, true);
			Stage stage = new Stage();
			root.start(stage);
		});

		file.getItems().get(2).setOnAction(event -> Platform.exit());

		edit.getItems().get(0).setOnAction(event -> {
			urls.setText("");
			keywords.setText("");
			results.setText("");
		});
		edit.getItems().get(1).setOnAction(event -> ChangeDepth());
		help.getItems().get(0).setOnAction(event -> {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("Webscrapper");
			alert.setContentText("Metasearch Engine developed by Piotr Sendrowski and Filip Sochal");
			alert.showAndWait();
		});



		searchBtn.setOnAction(event -> {



			presenter = new SearchEnginePresenter(new BasicSearchEngine(), new JsoupParser());


				Platform.runLater(()->ShowSearchDialog());
				new Thread(() -> {
					try
					{
						presenter.findByKeywords(mainwindow, CreateKeywordsList(), CreateUrlList(), depth);
					}
					catch(Exception e)
					{
						System.err.println(e.getMessage());
					}
				}).start();




				//Search


		});

	}
	private void ShowSearchDialog()
	{


			searchAlert = new Alert(Alert.AlertType.INFORMATION);
			searchAlert.setTitle("Searching");
			searchAlert.setHeaderText("We are scraping pages now");
			searchAlert.setContentText("Searching....");
			searchAlert.showAndWait();

	}
	public void CloseSearchAlert()
	{
		searchAlert.close();
	}
	private void SetExportEvents()
	{

		edit.getItems().get(2).setOnAction(event -> {
			String path="exportTXT";
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Choose TXT file path");
			dialog.setHeaderText("Enter path to save your search results");
			dialog.setContentText("File name");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent())
			{
				path = result.get()+".txt";
			}

			exporter.ExportToTxt(path);
		});

		edit.getItems().get(3).setOnAction(event -> {

			String path="exportHTML";
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Choose HTML file path");
			dialog.setHeaderText("Enter path to save your search results");
			dialog.setContentText("File name");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent())
			{
					path = result.get()+".html";
			}
			exporter.ExportToHTML(path);
		});
	}

	public Scene getScene()
	{
		return scene;
	}

	@Override
	public List<String> getUrls()
	{
		return null;
	}

	@Override
	public void actualizeResultSet(List<List<String>> results, Collection<String> urls)
	{

		paragraphsList = results;
		urlsList = (List<String>)urls;
		exporter = new Exporter(paragraphsList,urlsList);
		SetExportEvents();
		Platform.runLater(() -> CloseSearchAlert());
		ShowResults(results, (List<String>) urls);

	}

	private void ShowResults(List<List<String>> res, List<String> urls)
	{
		results.setText("");
		String res_to_show = new String();
		ArrayList<String> tmp = (ArrayList<String>) urls;
		for (Collection<String> x : res)
		{
			res_to_show += tmp.get(res.indexOf(x)) + "\n";
			for (String y : x)
				res_to_show += y + "\n";
			res_to_show += "\n";
		}
		results.setText(res_to_show);
	}

	private List<String> CreateKeywordsList()
	{
		String rawKeywords = keywords.getText();
		return Arrays.asList(rawKeywords.split("[;]"));

	}

	private List<String> CreateUrlList()
	{
		return Arrays.asList(urls.getText().split("[;]"));

	}

	@Override
	public void setPresenter(SeekPresenter presenter)
	{
		this.presenter = presenter;
	}


}

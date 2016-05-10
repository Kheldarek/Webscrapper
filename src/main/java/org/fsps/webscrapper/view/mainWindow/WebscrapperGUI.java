package org.fsps.webscrapper.view.mainWindow;
/**
 * Created by psend on 26.04.2016.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    public TextField  urls,keywords;
    public TextArea results;
    private VBox vbox;
    private MenuBar menu;
    private Menu file,edit,help;
    private SeekPresenter presenter;


	
	public WebscrapperGUI() throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("webscrapperGU.fxml"));
		scene = new Scene(root, 800,600);
        searchBtn = (Button) root.getBottom();
        vbox = (VBox)root.getTop();
        urls = (TextField)vbox.getChildren().get(1);
        keywords = (TextField)vbox.getChildren().get(2);
        results = (TextArea)root.getCenter();
        results.setEditable(false);
        results.setWrapText(true);
        menu = (MenuBar) vbox.getChildren().get(0);
        file = menu.getMenus().get(0);
        edit = menu.getMenus().get(1);
        help = menu.getMenus().get(2);
        WebscrapperGUI mainwindow = this;
        file.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                keywords.setText("");
                FileSelector root= new FileSelector(mainwindow,false);
                Stage stage = new Stage();
                root.start(stage);
            }
        });
        file.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                urls.setText("");
                FileSelector root= new FileSelector(mainwindow,true);
                Stage stage = new Stage();
                root.start(stage);
            }
        });
		edit.getItems().get(0).setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				urls.setText("");
				keywords.setText("");
				results.setText("");
			}
		});

        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                presenter = new SearchEnginePresenter(new BasicSearchEngine(),new JsoupParser());
                try
                {
                presenter.findByKeywords(mainwindow,CreateKeywordsList(),CreateUrlList(),4);


                //Search
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
        }});

    }
	
	public Scene getScene() {
		return scene;
	}
    
    @Override
    public List<String> getUrls() {
        return null;
    }

    @Override
    public void actualizeResultSet(List<List<String>> results, Collection<String> urls)
    {
        ShowResults(results,(List<String>)urls);
    }

    private void ShowResults(List<List<String>> res, List<String> urls)
    {
        results.setText("");
		String res_to_show = new String();
        ArrayList<String> tmp = (ArrayList<String>) urls;
        for (Collection<String> x:res)
        {
            res_to_show += tmp.get(res.indexOf(x)) + "\n";
            for(String y : x)
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
	public void setPresenter(SeekPresenter presenter) {
		this.presenter = presenter;
	}


}
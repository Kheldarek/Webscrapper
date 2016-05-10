package org.fsps.webscrapper.GUI.mainWindow;
/**
 * Created by psend on 26.04.2016.
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fsps.webscrapper.GUI.FileSelector.FileSelector;
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


	
	public WebscrapperGUI() throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("webscrapperGU.fxml"));
		scene = new Scene(root, 800,600);
        searchBtn = (Button) root.getBottom();
        vbox = (VBox)root.getTop();
        urls = (TextField)vbox.getChildren().get(1);
        keywords = (TextField)vbox.getChildren().get(2);
        results = (TextArea)root.getCenter();
        results.setEditable(false);
        menu = (MenuBar) vbox.getChildren().get(0);
        file = menu.getMenus().get(0);
        edit = menu.getMenus().get(1);
        help = menu.getMenus().get(2);
        WebscrapperGUI x = this;
        file.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                keywords.setText("");
                FileSelector root= new FileSelector(x,false);
                Stage stage = new Stage();
                root.start(stage);
            }
        });
        file.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                urls.setText("");
                FileSelector root= new FileSelector(x,true);
                Stage stage = new Stage();
                root.start(stage);
            }
        });

        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                CreateUrlList();
                CreateKeywordsList();
                //Search
            }
        });

    }
	
	public Scene getScene() {
		return scene;
	}
    
    @Override
    public List<String> getUrls() {
        return null;
    }

    @Override
    public void actualizeResultSet(Collection<String> results)
    {

    }

    private void ShowResults(List<String> res)
    {
        String res_to_show = new String();
        for (String x:res)
        {
            res_to_show += x + "\n\n";
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


}

package org.fsps.webscrapper.view;
/**
 * Created by psend on 26.04.2016.
 */

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class WebscrapperGUI implements SearchForm {
	private Scene scene;
	
	public WebscrapperGUI() throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("webscrapperGU.fxml"));
		scene = new Scene(root, 800,600);
    }
	
	public Scene getScene() {
		return scene;
	}
    
    @Override
    public List<String> getUrls() {
        return null;
    }

    @Override
    public void actualizeResultSet(Collection<String> results) {

    }
}

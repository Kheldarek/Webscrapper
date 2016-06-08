package org.fsps.webscrapper;

import org.fsps.webscrapper.view.mainWindow.WebscrapperGUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		 primaryStage.setScene(new WebscrapperGUI().getScene());
		 primaryStage.setTitle("Webscrapper");
         primaryStage.show();
	}
}

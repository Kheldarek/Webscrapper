package org.fsps.webscrapper.view;/**
 * Created by psend on 26.04.2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collection;
import java.util.List;

public class WebscrapperGUI extends Application implements SearchForm
{
    public void start(Stage primaryStage) {
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass()
                    .getResource("webscrapperGU.fxml"));
            Scene scene = new Scene(root, 800,600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public List<String> getUrls()
    {
        return null;
    }

    @Override
    public void actualizeResultSet(Collection<String> results)
    {

    }



}

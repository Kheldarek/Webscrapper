package org.fsps.webscrapper.view.FileSelector;

/**
 * Created by psend on 10.05.2016.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fsps.webscrapper.view.mainWindow.WebscrapperGUI;

public final class FileSelector extends Application {

	WebscrapperGUI x;
	boolean openUrl;

	public FileSelector(WebscrapperGUI gui, boolean url) {
		x = gui;
		openUrl = url;
	}

	@Override
	public void start(final Stage stage) {
		stage.setTitle("Choose File");

		final FileChooser fileChooser = new FileChooser();

		final Button openButton = new Button("Open file");
		final Button openMultipleButton = new Button("Open files");

		openButton.setOnAction(e -> {
			configureFileChooser(fileChooser);
			File file = fileChooser.showOpenDialog(stage);
			if(file != null) {
				openFile(file);
			}
		});

		openMultipleButton.setOnAction(e -> {
			configureFileChooser(fileChooser);
			List<File> list = fileChooser.showOpenMultipleDialog(stage);
			if(list != null) {
				for(File file : list) {
					openFile(file);
				}
			}
		});

		final GridPane inputGridPane = new GridPane();

		GridPane.setConstraints(openButton, 0, 0);
		GridPane.setConstraints(openMultipleButton, 1, 0);
		inputGridPane.setHgap(6);
		inputGridPane.setVgap(6);
		inputGridPane.getChildren().addAll(openButton, openMultipleButton);

		final Pane rootGroup = new VBox(12);
		rootGroup.getChildren().addAll(inputGridPane);
		rootGroup.setPadding(new Insets(12, 12, 12, 12));

		stage.setScene(new Scene(rootGroup));
		stage.show();
	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Choose File");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}

	private void openFile(File file) {
		StringBuilder contents = new StringBuilder();

		try {
			BufferedReader input = new BufferedReader(new FileReader(file));
			try {
				String line = null;

				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(";");
				}
			} finally {
				input.close();
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		String tmp;
		if(openUrl) {
			tmp = x.urls.getText();
			x.urls.setText(tmp + ";" + contents.toString());
		} else {
			tmp = x.keywords.getText();
			x.keywords.setText(tmp + ";" + contents.toString());
		}

	}
}

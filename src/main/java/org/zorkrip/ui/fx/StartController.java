package org.zorkrip.ui.fx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    public Button start;
    public Button quit;
    public Button credits;
    public Button settings;
    Stage stage;
    Parent root;
    private MediaView mediaView;

    public void play() throws IOException {


        stage = (Stage) start.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/loadview.fxml"));
        Scene scene = stage.getScene();  // reuse the existing scene
        scene.setRoot(root);

    }


    public void credits() throws IOException {

        stage = (Stage) credits.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/credits.fxml"));
        Scene scene = stage.getScene();  // reuse the existing scene
        scene.setRoot(root);


    }

    public void settings() throws IOException {
        stage = (Stage) start.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/settings.fxml"));
        Scene scene = stage.getScene();  // reuse the existing scene

        scene.setRoot(root);
    }

    public void quit() {
        Platform.exit();
    }
}

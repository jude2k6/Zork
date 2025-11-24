package org.zorkrip.ui.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    public Button start;
    public Button quit;
    public Button credits;

    public void play() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) start.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/loadview.fxml"));

        Scene scene = stage.getScene();  // reuse the existing scene
        scene.setRoot(root);


    }

    public void quit() {
    }

    public void credits() throws IOException {

        Stage stage;
        Parent root;
        stage = (Stage) credits.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/credits.fxml"));

        Scene scene = stage.getScene();  // reuse the existing scene
        scene.setRoot(root);


    }
}

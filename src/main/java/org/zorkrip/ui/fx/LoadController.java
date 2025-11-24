package org.zorkrip.ui.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.IOException;

public class LoadController {
    public Button newgame;
    public Button loadgame;
    public Button back;

    public void newGame() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) back.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/game.fxml"));

        Scene scene = stage.getScene();  // reuse the existing scene
        scene.setRoot(root);

    }

    public void loadGame() throws IOException {

        Stage stage;
        Parent root;
        stage = (Stage) back.getScene().getWindow();


        DirectoryChooser  directoryChooser= new DirectoryChooser();
        directoryChooser.setTitle("Open Resource File");
        Shared.loadPath= directoryChooser.showDialog(stage).getAbsolutePath();


        root = FXMLLoader.load(getClass().getResource("/game.fxml"));

        Scene scene = stage.getScene();  // reuse the existing scene
        scene.setRoot(root);




    }

    public void back() throws IOException {

        Stage stage;
        Parent root;
        stage = (Stage) back.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/start.fxml"));

        Scene scene = stage.getScene();  // reuse the existing scene
        scene.setRoot(root);


    }
}

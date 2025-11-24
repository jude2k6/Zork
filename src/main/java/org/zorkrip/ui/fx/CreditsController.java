package org.zorkrip.ui.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CreditsController {


    public Button back;

    public void back(ActionEvent actionEvent) throws IOException {

        Stage stage;
        Parent root;
        stage = (Stage) back.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/start.fxml"));

        Scene scene = stage.getScene();  // reuse the existing scene
        scene.setRoot(root);




    }
}

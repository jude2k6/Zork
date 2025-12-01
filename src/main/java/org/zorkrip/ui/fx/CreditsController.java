package org.zorkrip.ui.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CreditsController {


    public Button backButton;

    public void back() throws IOException {

        Stage stage;
        Parent root;
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/start.fxml")));

        Scene scene = stage.getScene();  // reuse the existing scene
        scene.setRoot(root);


    }
}

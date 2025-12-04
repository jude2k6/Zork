package org.zorkrip.ui.fx;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CreditsController {


    public Button backButton;
    public VBox creditsBox;
    Stage stage;
    @FXML
    private AnchorPane root;



    public void initialize(){


        DoubleBinding fontScale = root.widthProperty()
                .add(root.heightProperty())
                .divide(1500);

        backButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));
        creditsBox.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));

    }
    public void back() throws IOException {


        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/start.fxml")));

        Scene scene = stage.getScene();  // reuse the existing scene
        scene.setRoot(root);


    }
}

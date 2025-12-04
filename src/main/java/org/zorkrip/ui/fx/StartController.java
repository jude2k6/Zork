package org.zorkrip.ui.fx;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    @FXML
    public Button start;
    public Button quit;
    public Button credits;
    public Button settings;
    Stage stage;
    private MediaView mediaView;
    @FXML
    private AnchorPane root;


    public void initialize() {
        DoubleBinding fontScale = root.widthProperty()
                .add(root.heightProperty())
                .divide(1500);

        // Scale button fonts dynamically
        start.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));
        credits.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));
        settings.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));
        quit.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));

        DoubleBinding widthScale = root.widthProperty().divide(3); // tweak divisor
        start.prefWidthProperty().bind(widthScale);
        credits.prefWidthProperty().bind(widthScale);
        settings.prefWidthProperty().bind(widthScale);
        quit.prefWidthProperty().bind(widthScale);

    }


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

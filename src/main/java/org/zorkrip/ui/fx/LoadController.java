package org.zorkrip.ui.fx;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadController {

    public Button newgame;
    public Button loadgame;
    public Button back;
    @FXML
    private AnchorPane root;






    public void initialize() {
        DoubleBinding fontScale = root.widthProperty()
                .add(root.heightProperty())
                .divide(1500);
        newgame.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));
        loadgame.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));
        back.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));

        DoubleBinding widthScale = root.widthProperty().divide(3); // tweak divisor
        back.prefWidthProperty().bind(widthScale);
        newgame.prefWidthProperty().bind(widthScale);
        loadgame.prefWidthProperty().bind(widthScale);


    }

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


        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Resource File");
        Shared.loadPath = directoryChooser.showDialog(stage).getAbsolutePath();


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

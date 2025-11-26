package org.zorkrip.ui.fx;



import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.zorkrip.engine.GameEngine;
import org.zorkrip.engine.ZorkEngine;

import java.io.IOException;
import java.util.Objects;

public class GameController {

    public TextArea console;
    public AnchorPane rootPane;
    public TextArea inventory;
    public Button quitButton;
    final GameEngine game;

    public String inventoryString;


    @FXML
    public TextField userInput;
    public Button enterButton;

    public GameController() {
        if (!(Shared.loadPath == null)) {
            game = new ZorkEngine(Shared.loadPath);
        } else {
            game = new ZorkEngine();

        }

    }

    public void initialize() {
        console.appendText(game.getWelcomeMessage());

        userInput.setOnAction(e -> enterCommand());
        updateInventory(inventoryString);
    }


    public void enterCommand() {

        String command = userInput.getText();
        String s = game.handleInput(command);
        console.appendText(">"+command+"\n");
        console.appendText(s);
        userInput.clear();
        updateInventory(inventoryString);

    }

    public void updateInventory(String inventoryString) {
            if(!Objects.equals(inventoryString, game.viewInventory())){
                inventoryString= game.viewInventory();
                inventory.clear();
                inventory.setText(inventoryString);
            }

    }


    public void quitGame() throws IOException {

        Stage stage;
        Parent root;
        stage = (Stage) quitButton.getScene().getWindow();


        DirectoryChooser directoryChooser= new DirectoryChooser();
        directoryChooser.setTitle("Open Resource File");
        Shared.savePath= directoryChooser.showDialog(stage).getAbsolutePath();
        game.saveGameInterface(Shared.savePath);
        root = FXMLLoader.load(getClass().getResource("/start.fxml"));
        Scene scene = stage.getScene();
        scene.setRoot(root);

    }
}

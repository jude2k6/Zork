package org.zorkrip.ui.fx;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.zorkrip.engine.GameEngine;
import org.zorkrip.engine.ZorkEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameController {

    final GameEngine game;

    @FXML
    public TextArea console;
    public AnchorPane rootPane;
    public TextArea inventory;
    public Button quitButton;
    public String inventoryString;
    public TextField userInput;
    public Button enterButton;


    public ArrayList<String> commandHistory;
    public int commandIndex;

    public GameController() {
        if (!(Shared.loadPath == null)) {
            game = new ZorkEngine(Shared.loadPath);
        } else {
            game = new ZorkEngine();

        }
        commandHistory = new ArrayList<>();
    }

    public void initialize() {
        console.appendText(game.getWelcomeMessage());

        userInput.setOnAction(e -> enterCommand());
        updateInventory(inventoryString);

        userInput.setOnKeyPressed(event -> {
            if (commandHistory.isEmpty()) return;

            if (event.getCode() == KeyCode.UP) {
                commandIndex = Math.max(0, commandIndex - 1);
                userInput.setText(commandHistory.get(commandIndex));
                event.consume();

            }
            if (event.getCode() == KeyCode.DOWN) {

                commandIndex = Math.min(commandHistory.size() - 1, commandIndex + 1);
                userInput.setText(commandHistory.get(commandIndex));
                event.consume();
            }


        });

    }


    public void enterCommand() {

        String command = userInput.getText();
        String s = game.handleInput(command);
        console.appendText(">" + command + "\n");
        console.appendText(s);
        commandHistory.add(command);
        commandIndex = commandHistory.size() - 1;
        userInput.clear();
        updateInventory(inventoryString);


    }

    public void updateInventory(String inventoryString) {
        if (!Objects.equals(inventoryString, game.viewInventory())) {
            inventoryString = game.viewInventory();
            inventory.clear();
            inventory.setText(inventoryString);
        }

    }


    public void quitGame() throws IOException {

        Stage stage;
        Parent root;
        stage = (Stage) quitButton.getScene().getWindow();


        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Resource File");
        Shared.savePath = directoryChooser.showDialog(stage).getAbsolutePath();
        game.saveGameInterface(Shared.savePath);
        root = FXMLLoader.load(getClass().getResource("/start.fxml"));
        Scene scene = stage.getScene();
        scene.setRoot(root);

    }
}

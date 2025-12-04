package org.zorkrip.ui.fx;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.zorkrip.engine.GameEngine;
import org.zorkrip.engine.ZorkEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameController {

    final GameEngine game;
    private final ArrayList<String> commandHistory;
    @FXML
    public TextArea console;
    public VBox rootPane;
    public TextArea inventory;
    public Button quitButton;
    public String inventoryString;
    public TextField userInput;
    public Button enterButton;
    public ImageView consoleImage;
    private int commandIndex;
    private String room;
    private Image image;


    public GameController() {
        if (!(Shared.loadPath == null)) {
            game = new ZorkEngine(Shared.loadPath);
        } else {
            game = new ZorkEngine();

        }
        commandHistory = new ArrayList<>();
        room = game.viewRoom();
    }

    public void initialize() {

        DoubleBinding fontScale = rootPane.widthProperty()
                .add(rootPane.heightProperty())
                .divide(1500);
        enterButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));
        userInput.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));
        inventory.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));
        console.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));
        quitButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));

        DoubleBinding widthScale = rootPane.widthProperty().divide(3); // tweak divisor
        enterButton.prefWidthProperty().bind(widthScale);


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
        consoleImage.setPreserveRatio(false);
        consoleImage.setSmooth(true);
        consoleImage.setCache(true);
        consoleImage.fitWidthProperty().bind(rootPane.widthProperty());
        consoleImage.fitHeightProperty().bind(rootPane.heightProperty().divide(2));
        updateImage();

    }


    public void enterCommand() {

        String command = userInput.getText();

        if (command.isEmpty()) {
            return;
        }
        String response = game.handleInput(command);
        updateImage();
        room = game.viewRoom();

        console.appendText(">" + command + "\n\n");
        console.appendText(response);


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


    public void updateImage() {

        if (!game.viewRoom().equals(room)) {

            image = new Image(getClass().getResource("/images/" + game.viewRoom() + ".png").toExternalForm());
            consoleImage.setImage(image);
        }
        if (image == null) {
            image = new Image(getClass().getResource("/images/" + game.viewRoom() + ".png").toExternalForm());
            consoleImage.setImage(image);
        }


    }
}

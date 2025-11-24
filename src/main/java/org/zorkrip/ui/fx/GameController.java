package org.zorkrip.ui.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.zorkrip.engine.ZorkEngine;

public class GameController {

    public TextArea console;
    ZorkEngine game;


    @FXML
    public TextField userInput;
    public Button enterButton;

    public GameController(){
        if (!(Shared.loadPath==null)){
            game = new ZorkEngine(Shared.loadPath);
        }
        else{
            game = new ZorkEngine();

        }

    }

    public void initialize(){
        console.appendText(game.printWelcome());
    }


    public void enterCommand() {

        String command = userInput.getText();
        String s = game.play(command);
        console.appendText(s);

    }
}

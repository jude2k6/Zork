package org.zorkrip.ui.fx;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    public static void main(String[] args) {
        launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        new BackgroundMusic().init();
        Parent root = FXMLLoader.load(getClass().getResource("/start.fxml"));
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Zork-Rip");


        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/dark.css").toExternalForm());
        primaryStage.show();

    }

}

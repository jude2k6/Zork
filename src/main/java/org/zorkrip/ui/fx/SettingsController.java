package org.zorkrip.ui.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;

public  class SettingsController {
    @FXML
    public Slider masterVolumeSlider;
    public Slider sfxVolumeSlider;
    public Slider musicVolumeSlider;
    public Button applyButton;
    public Button backButton;
    public RadioButton theme;
    Stage stage;


    Parent root;

    public double masterVolume = 100;



    public void initialize() {

        masterVolumeSlider.setValue(masterVolume);
        sfxVolumeSlider.setValue(BackgroundMusic.getMediaPlayer().getVolume()*100);
        musicVolumeSlider.setValue(BackgroundMusic.getMediaPlayer().getVolume()*100);
        theme.setSelected(true);


    }



    public void apply(ActionEvent actionEvent) {

        BackgroundMusic.getMediaPlayer().setVolume((masterVolumeSlider.getValue()/100*musicVolumeSlider.getValue()/100));
        stage = (Stage) backButton.getScene().getWindow();
        Scene scene = stage.getScene();



        if (!theme.isSelected()){
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/light.css").toExternalForm());
        }
        else {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/dark.css").toExternalForm());
        }
    }

    public void back() throws IOException {


        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/start.fxml"));
        Scene scene = stage.getScene();
        scene.setRoot(root);


    }
}

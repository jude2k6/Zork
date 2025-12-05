package org.zorkrip.ui.fx;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    public final double masterVolume = 100;
    @FXML
    public Slider masterVolumeSlider;
    public Slider sfxVolumeSlider;
    public Slider musicVolumeSlider;
    public Button applyButton;
    public Button backButton;
    public RadioButton theme;
    Stage stage;
    @FXML
    private AnchorPane root;

    public void initialize() {

        DoubleBinding fontScale = root.widthProperty()
                .add(root.heightProperty())
                .divide(1500);
        applyButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));
        backButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontScale.multiply(14), "px;"));

        DoubleBinding widthScale = root.widthProperty().divide(4); // tweak divisor
        applyButton.prefWidthProperty().bind(widthScale);
        backButton.prefWidthProperty().bind(widthScale);

        masterVolumeSlider.setValue(masterVolume);
        sfxVolumeSlider.setValue(BackgroundMusic.getMediaPlayer().getVolume() * 100);
        musicVolumeSlider.setValue(BackgroundMusic.getMediaPlayer().getVolume() * 100);
        theme.setSelected(true);


    }


    public void apply() {

        BackgroundMusic.getMediaPlayer().setVolume((masterVolumeSlider.getValue() / 100 * musicVolumeSlider.getValue() / 100));
        stage = (Stage) backButton.getScene().getWindow();
        Scene scene = stage.getScene();


        if (!theme.isSelected()) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/light.css").toExternalForm());
        } else {
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

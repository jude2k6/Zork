package org.zorkrip.ui.fx;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BackgroundMusic {

    private static MediaPlayer mediaPlayer;

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void init() {
        try {
            var resource = getClass().getResource("/audio.wav");
            if (resource == null) {
                System.out.println("Background music not found.");
                return;
            }

            Media media = new Media(resource.toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();

        } catch (Throwable t) {
            // MUST catch Throwable — JavaFX Media throws Errors
            System.out.println("Background music disabled.");
        }
    }
}


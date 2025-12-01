package org.zorkrip.ui.fx;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BackgroundMusic {

    private static MediaPlayer mediaPlayer;


    public void init(){
        Media media = new Media(getClass().getResource("/audio.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}

package controller;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import java.net.URL;

//TO BE FIXED
public class loadingViewController {

    @FXML private MediaView mediaView;
    private MediaPlayer mediaPlayer;

    public void startLoading(Runnable onFinished) {
        // 1. Get video path from resources
        URL videoUrl = getClass().getResource("/video/loading.mp4");

        if (videoUrl == null) {
            System.err.println("Loading video not found in /video/loading.mp4!");
            if (onFinished != null) onFinished.run();
            return;
        }

        Media media = new Media(videoUrl.toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        // 2. Play video and cap at 2 seconds (or 4 seconds as you have set)
        mediaPlayer.setStopTime(Duration.seconds(2));

        // 3. When finished, clean up media and trigger SceneController callback
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            if (onFinished != null) {
                onFinished.run();
            }
        });

        mediaPlayer.play();
    }
}
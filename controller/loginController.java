// controller/loginController.java
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import model.Profile;

import java.net.URL;
import java.util.List;

public class loginController {
    @FXML private Button createProfileButton;
    @FXML private Button exitProgramButton;
    @FXML private MediaView loginVideoView;

    private SceneController sceneController;
    private List<Profile> profiles;
    private MediaPlayer mediaPlayer;

    public void init(SceneController sceneController, List<Profile> profiles) {
        init(sceneController, profiles, true);
    }

    public void init(SceneController sceneController, List<Profile> profiles, boolean playVideo) {
        this.sceneController = sceneController;
        this.profiles = profiles;

        if (playVideo) {
            playLoginVideo();
        }
    }

    public void playLoginVideo() {
        if (loginVideoView == null) {
            return;
        }

        URL videoUrl = getClass().getResource("/video/login.mp4");
        if (videoUrl == null) {
            videoUrl = getClass().getResource("/video/loading.mp4");
        }

        if (videoUrl == null) {
            System.err.println("Login video not found in /video/login.mp4 or /video/loading.mp4.");
            return;
        }

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        Media media = new Media(videoUrl.toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        loginVideoView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void handleCreateProfile() {
        sceneController.showCreateProfile();
    }

    public void handleExitProgram() {
        System.exit(0);
    }
}
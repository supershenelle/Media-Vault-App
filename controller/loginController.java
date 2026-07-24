// controller/loginController.java
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Profile;

import java.util.List;

public class loginController {
    @FXML private Button createProfileButton;
    @FXML private Button exitProgramButton;

    private SceneController sceneController;
    private List<Profile> profiles;

    public void init(SceneController sceneController, List<Profile> profiles) {
        init(sceneController, profiles, true);
    }

    public void init(SceneController sceneController, List<Profile> profiles, boolean playVideo) {
        this.sceneController = sceneController;
        this.profiles = profiles;
    }

    public void playLoginVideo() {
        // Login screen no longer uses a media background in this build.
    }

    public void handleCreateProfile() {
        sceneController.showCreateProfile();
    }

    public void handleExitProgram() {
        System.exit(0);
    }
}
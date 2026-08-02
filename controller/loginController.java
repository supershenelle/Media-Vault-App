// controller/loginController.java
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Profile;

import java.util.List;

/**
 * controller for the login/landing screen.
 */
public class loginController {
    @FXML private Button createProfileButton;
    @FXML private Button exitProgramButton;

    //private loginView loginView;
    private SceneController sceneController;
    private List<Profile> profiles; // wherever your saved profiles come from

    /**
     * gives access to scene controller and profile
     * @param sceneController used for changing scenes
     * @param profiles list of profiles
     */
    public void init(SceneController sceneController, List<Profile> profiles) {
        this.sceneController = sceneController;
        this.profiles = profiles;
    }

    /**
     * switches to createProfileView using the sceneController
     */
    public void handleCreateProfile() {
        sceneController.showCreateProfile();
    }

    /**
     * closes the program
     */
    public void handleExitProgram() {
        System.exit(0);
    }

    /*
    public void init(SceneController sceneController, List<Profile> profiles) {
        this.sceneController = sceneController;
        this.profiles = profiles;
    }

    public loginController(SceneController sceneController, loginView loginView, List<Profile> profiles) {
        this.sceneController = sceneController;
        this.loginView = loginView;
        this.profiles = profiles;

        loginView.getLoginButton().setOnAction(e -> handleLogin());
        loginView.getCreateProfileButton().setOnAction(e -> sceneController.showCreateProfile());
    }

    private void handleLogin() {
        String username = loginView.getUsernameField().getText().trim();

        if (username.isEmpty()) {
            loginView.getErrorLabel().setText("Please enter a username.");
            return;
        }

        Profile match = profiles.stream()
                .filter(p -> p.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);

        if (match == null) {
            loginView.getErrorLabel().setText("Profile not found.");
            return;
        }

        // success — switch scene to main app
        sceneController.showMainView(match);
//        mainView mainView = new mainView(match.getLibrary());
//        stage.setScene(mainView.getScene());



    }
    */
}
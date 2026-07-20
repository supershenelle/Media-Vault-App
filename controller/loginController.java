// controller/loginController.java
package controller;

import model.Profile;
import view.loginView;

import java.util.List;

public class loginController {
    private loginView loginView;
    private SceneController sceneController;
    private List<Profile> profiles; // wherever your saved profiles come from

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

}
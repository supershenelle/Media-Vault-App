package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Profile;

import java.util.List;

/**
 * controller for createProfileView.
 * validates the new profile's fields, checks for username duplicates,
 * adds the new Profile to the  list, and navigates to the main view.
 */
public class createProfileController {
    @FXML private TextField usernameField;
    @FXML private TextField displayNameField;
    @FXML private TextArea bioField;
    @FXML private Label errorLabel;
    @FXML private Button confirmButton;

    private SceneController sceneController;
    private List<Profile> profiles;

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
     * validates username, display name, and bio. if not empty, creates a new profile, adds it
     * to the list, and switch to main view.
     */
    public void handleConfirm() {
        String username = usernameField.getText().trim();
        String displayName = displayNameField.getText().trim();
        String bio = bioField.getText().trim();

        // input validation
        if (username.isEmpty()) {
            errorLabel.setText("Username cannot be empty.");
            return;
        }
        else if(displayName.isEmpty()){
            errorLabel.setText("Display name cannot be empty.");
            return;
        }
        else if(bio.isEmpty()){
            errorLabel.setText("Bio cannot be empty.");
            return;
        }

        // check if may same username
        for (Profile p : profiles) {
            if (p.getUsername().equalsIgnoreCase(username)) {
                errorLabel.setText("That username is already taken.");
                return;
            }
        }

        // create new profile then add it to list
        Profile newProfile = new Profile(username, displayName, bio);
        profiles.add(newProfile);

        // switch to main view
        sceneController.showMainView(newProfile);
    }
}

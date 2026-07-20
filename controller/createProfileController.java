package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Profile;

import java.util.List;

public class createProfileController {
    @FXML private TextField usernameField;
    @FXML private TextField displayNameField;
    @FXML private TextArea bioField;
    @FXML private Label errorLabel;
    @FXML private Button confirmButton;

    private SceneController sceneController;
    private List<Profile> profiles;

    public void init(SceneController sceneController, List<Profile> profiles) {
        this.sceneController = sceneController;
        this.profiles = profiles;
    }

    public void handleConfirm() {
        String username = usernameField.getText().trim();
        String displayName = displayNameField.getText().trim();
        String bio = bioField.getText().trim();

        if (username.isEmpty()) {
            errorLabel.setText("Username cannot be empty.");
            return;
        }
        else if(displayName.isEmpty()){
            errorLabel.setText("displayName cannot be empty.");
            return;
        }
        else if(bio.isEmpty()){
            errorLabel.setText("bio cannot be empty.");
            return;
        }

        for (Profile p : profiles) {
            if (p.getUsername().equalsIgnoreCase(username)) {
                errorLabel.setText("That username is already taken.");
                return;
            }
        }

        Profile newProfile = new Profile(username, displayName, bio);
        profiles.add(newProfile);

        sceneController.showMainView(newProfile);
    }
}

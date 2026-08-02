package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Profile;

import java.io.IOException;
import java.util.List;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private List<Profile> profiles;

    public SceneController(Stage stage, List<Profile> profiles) {
        this.stage = stage;
        this.profiles = profiles;
    }

    /**
     * Shows the Scene Builder FXML screen (loginView.fxml)
     */
    public void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginView.fxml"));
            Parent root = loader.load();

            // Connect your login controller if needed
            loginController logController = loader.getController();
            if (logController != null) {
                logController.init(this, profiles);
            }

            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            stage.sizeToScene();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Switches to createProfileView when create profile button is clicked
     */
    public void showCreateProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/createProfileView.fxml"));
            Parent root = loader.load();

            createProfileController profileController = loader.getController();
            profileController.init(this, profiles);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            stage.sizeToScene();

        } catch (IOException e) {
            e.printStackTrace();
            showLogin();
        }
    }

    /**
     * switches to main view after show create profile
     * @param profile is the profile to be used (created in createProfileController)
     */
    public void showMainView(Profile profile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
            Parent root = loader.load();

            mainViewController mainController = loader.getController();
            if (mainController != null) {
                mainController.init(profile, stage, this);
            }

            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
            stage.centerOnScreen();
            stage.sizeToScene();

        } catch (IOException e) {
            e.printStackTrace();
            showLogin();
        }
    }
}
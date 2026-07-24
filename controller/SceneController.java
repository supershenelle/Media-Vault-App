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
    private boolean playLoginVideoOnNextLogin;

    public SceneController(Stage stage, List<Profile> profiles) {
        this.stage = stage;
        this.profiles = profiles;
    }

    public void showLogin() {
        showLogin(false);
    }

    public void showLoginAfterProfileCreation() {
        showLogin(true);
    }

    private void showLogin(boolean playVideo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginView.fxml"));
            Parent root = loader.load();

            loginController controller = loader.getController();
            if (controller != null) {
                controller.init(this, profiles, playVideo);
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

    public void showCreateProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/createProfileView.fxml"));
            Parent root = loader.load();

            createProfileController controller = loader.getController();
            controller.init(this, profiles);

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

    // 2. Shows the main app screen after logging in (mainView.fxml)
    public void showMainView(Profile profile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
            Parent root = loader.load();

            mainViewController controller = loader.getController();
            if (controller != null) {
                controller.init(profile, stage);
            }

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
}
package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Profile;
import view.loginView;

import java.io.IOException;
import java.util.List;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private List<Profile> profiles;

    public SceneController(Stage stage, List<Profile> profiles) {
        this.stage = stage;
        this.profiles = profiles;
    }
    public void showLogin() {
        loginView loginView = new loginView();
        new loginController(this, loginView, profiles);
        stage.setScene(loginView.getScene());
    }

    public void showMainView(Profile profile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
            Parent root = loader.load();

            mainViewController controller = loader.getController();
            controller.init(profile, stage);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showLogin();
        }
    }
}

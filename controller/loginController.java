// controller/loginController.java
package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Profile;
import view.loginView;
import view.mainView;

import java.io.IOException;
import java.util.List;

public class loginController {
    private loginView loginView;
    private Stage stage;
    private List<Profile> profiles; // wherever your saved profiles come from

    public loginController(Stage stage, loginView loginView, List<Profile> profiles) {
        this.stage = stage;
        this.loginView = loginView;
        this.profiles = profiles;

        loginView.getLoginButton().setOnAction(e -> handleLogin());
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
//        mainView mainView = new mainView(match.getLibrary());
//        stage.setScene(mainView.getScene());


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView2.fxml"));
            Parent root = loader.load();

            mainView2Controller controller = loader.getController();
            controller.init(match, stage);

            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            loginView.getErrorLabel().setText("Failed to load main view.");
        }


    }

}
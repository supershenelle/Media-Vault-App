// MediaVaultApp.java
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.loginController;
import view.loginView;
import model.Profile;

import java.util.ArrayList;
import java.util.List;

public class MediaVaultApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(new Profile("shen", "Shen", "bio")); // temp, later loaded from file

        loginView loginView = new loginView();
        new loginController(primaryStage, loginView, profiles);

        primaryStage.setTitle("Media Vault");
        primaryStage.setScene(loginView.getScene());
        primaryStage.show();

        /*testing ko lang mainview2...
        Parent root = FXMLLoader.load(getClass().getResource("mainView2.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

         */

    }

    public static void main(String[] args) {
        launch(args);
    }
}
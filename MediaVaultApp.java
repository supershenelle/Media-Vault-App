// MediaVaultApp.java
import controller.SceneController;
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

        primaryStage.centerOnScreen(); // para magdisplay lang sa center ng screen m
        primaryStage.setMinWidth(800); // para di masyado maliit
        primaryStage.setMinHeight(600);

        primaryStage.setTitle("Media Vault");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setMaxWidth(800);
        primaryStage.setMaxHeight(600);
        
        SceneController sceneController = new SceneController(primaryStage, profiles);
        sceneController.showLogin();
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
// MediaVaultApp.java
import controller.SceneController;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Profile;

import java.util.ArrayList;
import java.util.List;

public class MediaVaultApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        List<Profile> profiles = new ArrayList<>();
        // profiles.add(new Profile("shen", "Shen", "bio")); // temp, later loaded from file
        Font.loadFont(getClass().getResourceAsStream("/css/highcruiser.otf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/css/pixel1.TTF"), 12);
        Font.loadFont(getClass().getResourceAsStream("/css/pixel2.TTF"), 12);
        Font.loadFont(getClass().getResourceAsStream("/css/pixel3.TTF"), 12);
        Font.loadFont(getClass().getResourceAsStream("/css/pixel4.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/css/pixelmix.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/css/pixelmix_bold.ttf"), 12);

        primaryStage.centerOnScreen(); // para magdisplay lang sa center ng screen m
        primaryStage.setMinWidth(800); // para di masyado maliit
        primaryStage.setMinHeight(600);

        primaryStage.setTitle("Media Vault");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        
        SceneController sceneController = new SceneController(primaryStage, profiles);
        sceneController.showLogin();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
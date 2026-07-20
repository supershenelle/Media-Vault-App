package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Library;
import model.Media;
import model.Profile;

import java.io.IOException;

public class mainViewController {
    @FXML private Label usernameLabel;
    @FXML private Label displayNameLabel;
    @FXML private Label bioLabel;
    @FXML private ListView<Media> library;

    @FXML private Button addButton;
    @FXML private Button removeButton;
    @FXML private Button filterButton;
    @FXML private Button rateButton;
    @FXML private Button updateStatusButton;
    @FXML private Button updateDiscographyButton;
    @FXML private Button summaryButton;
    @FXML private Button logOutButton;

    private Profile profile;
    private Stage stage;

    public void init(Profile profile, Stage stage) {
        this.profile = profile;
        this.stage = stage;
        Library lib = profile.getLibrary();

        usernameLabel.setText("Username: @" + profile.getUsername());
        displayNameLabel.setText("Display Name: " + profile.getDisplayName());
        bioLabel.setText("Bio: " + profile.getBio());
        library.getItems().clear();
        library.getItems().addAll(lib.getEntries());
    }

    public void handleAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mediaTypeView.fxml"));
            Parent root = loader.load();
            mediaTypeController typeController = loader.getController();

            Stage typeStage = new Stage();
            typeStage.setTitle("Add Media");
            typeStage.setScene(new Scene(root));
            typeStage.showAndWait(); // pauses here until typeStage.close() runs

            if (!typeController.isConfirmed()) return; // user closed without picking

            switch (typeController.getSelectedType()) {
//                case "model.Movie" -> addMovie();
//                case "model.Videogame" -> addVideogame();
//                case "Music Artist" -> addMusicArtist();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleRemove() {

    }

    public void handleFilter() {

    }

    public void handleRate() {

    }

    public void handleUpdateStatus() {

    }

    public void handleUpdateDiscography() {

    }

    public void handleSummary() {

    }

    public void handleLogout() {

    }
}

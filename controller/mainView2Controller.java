package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Library;
import model.Media;
import model.Profile;

public class mainView2Controller {
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

    private void handleAdd() {

    }

    private void handleRemove() {

    }

    private void handleFilter() {

    }

    private void handleRate() {

    }

    private void handleUpdateStatus() {

    }

    private void handleUpdateDiscography() {

    }

    private void handleSummary() {

    }

    private void handleLogout() {

    }
}

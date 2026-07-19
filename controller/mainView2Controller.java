package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class mainView2Controller {
    @FXML private Label usernameLabel;
    @FXML private Label displayNameLabel;
    @FXML private Label bioLabel;
    @FXML private ListView<String> library;

    @FXML private Button addButton;
    @FXML private Button removeButton;
    @FXML private Button filterButton;
    @FXML private Button rateButton;
    @FXML private Button updateStatusButton;
    @FXML private Button updateDiscographyButton;
    @FXML private Button summaryButton;
    @FXML private Button logOutButton;
}

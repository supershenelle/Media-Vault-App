package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addAlbumController {
    @FXML
    private TextField titleField;
    @FXML private TextField genreField;
    @FXML private TextField yearField;
    @FXML private TextField trackCountField;
    @FXML private Label errorLabel;
    @FXML private Button confirmButton;

    private String title;
    private String genre;
    private int year;
    int yearValue; // for validation
    private int trackCount;
    int trackCountValue; // for validation
    private boolean confirmed = false;

    public void handleConfirm() {
        String titleText = titleField.getText().trim();
        String genreText = genreField.getText().trim();
        String yearText = yearField.getText().trim();
        String trackCountText = trackCountField.getText().trim();

        if (titleText.isEmpty()) {
            errorLabel.setText("Album title cannot be empty.");
            return;
        }
        if (genreText.isEmpty()) {
            errorLabel.setText("Genre cannot be empty.");
            return;
        }

        try {
            yearValue = Integer.parseInt(yearText);
        } catch (NumberFormatException e) {
            errorLabel.setText("Year must be a valid number.");
            return;
        }

        try {
            trackCountValue = Integer.parseInt(trackCountText);
        } catch (NumberFormatException e) {
            errorLabel.setText("Track count must be a valid number.");
            return;
        }

        if (trackCountValue < 1) {
            errorLabel.setText("Track count must be at least 1.");
            return;
        }

        title = titleText;
        genre = genreText;
        year = yearValue;
        trackCount = trackCountValue;
        confirmed = true;

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}

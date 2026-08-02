package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Media;

import java.util.ArrayList;

public class rateController {
    @FXML private ListView<String> entryListView;
    @FXML private TextField ratingField;
    @FXML private TextArea reviewField;
    @FXML private Label errorLabel;
    @FXML private Button confirmButton;
    @FXML private Button cancelButton;

    private ArrayList<Media> entries;
    private Media selectedEntry;
    private boolean confirmed = false;

    /**
     * populates list view with the titles of the entries
     * @param entries contains the completed media entries available to rate/review
     */
    public void init(ArrayList<Media> entries) {
        this.entries = entries;

        for (Media media : entries) {
            entryListView.getItems().add(media.getTitle());
        }
    }

    /**
     * validates the selected entry and the rating input, then applies the
     * rating and review to the selected media entry and closes the window.
     * displays error message and returns if selection or rating is invalid.
     */
    public void handleConfirm() {
        int selectedIndex;
        int rating;
        boolean ratingSet;
        String ratingText;
        Media entry;

        selectedIndex = entryListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex < 0) {
            errorLabel.setText("Please select an entry to rate/review.");
            return;
        }

        ratingText = ratingField.getText().trim();

        try {
            rating = Integer.parseInt(ratingText);
        } catch (NumberFormatException e) {
            errorLabel.setText("Rating must be a valid number (1-5).");
            return;
        }

        // get media object to be rated then set rating
        entry = entries.get(selectedIndex);
        ratingSet = entry.setRating(rating);

        if (!ratingSet) {
            errorLabel.setText("Rating must be between 1 and 5.");
            return;
        }

        // set review and set confirm to true
        entry.setReview(reviewField.getText().trim());

        selectedEntry = entry;
        confirmed = true;

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    /**
     * set confirm to false, and closes the window
     */
    public void handleCancel() {
        confirmed = false;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     *  get  media entry that was reviewed/rated
     * @return media object
     */
    public Media getSelectedEntry() {
        return selectedEntry;
    }

    /**
     * check whether successfully confirmed
     * @return true if confirmed, false otherwise
     */
    public boolean isConfirmed() {
        return confirmed;
    }
}

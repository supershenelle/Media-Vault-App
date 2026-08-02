package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Media;

import java.util.ArrayList;

public class addFavoriteController {
    @FXML private ListView<String> entryListView;
    @FXML private Label errorLabel;
    @FXML private Button toggleButton;
    @FXML private Button closeButton;

    private ArrayList<Media> entries;

    public void init(ArrayList<Media> entries) {
        this.entries = entries;
        refreshList();
    }

    /**
     * refresh/update the list view
     */
    private void refreshList() {
        entryListView.getItems().clear();
        for (Media media : entries) {
            String heart = "♡ ";
            if (media.isFavorite()) {
                heart = "❤ ";
            }
            entryListView.getItems().add(heart + media.getTitle());
        }
    }

    /**
     * favorites/unfavorites selected media entry in list view
     */
    public void handleToggle() {
        int selectedIndex = entryListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex < 0) {
            errorLabel.setText("Please select an entry to favorite/unfavorite.");
            return;
        }

        Media entry = entries.get(selectedIndex); // get selected media entry
        entry.toggleFavorite(); // toggle faovorite
        errorLabel.setText("");
        refreshList(); //refresh the list para updated
        entryListView.getSelectionModel().select(selectedIndex);
    }

    public void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

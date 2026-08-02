package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Media;

import java.util.ArrayList;

/**
 * controller for removeMediaView.
 * lets the user pick one entry (already filtered by media type)
 * from a list view and remove it from the library.
 */
public class removeMediaController {
    @FXML private ListView<String> entryListView;
    @FXML private Label errorLabel;
    @FXML private Button removeButton;
    @FXML private Button cancelButton;

    private ArrayList<Media> entries;
    private Media selectedEntry;
    private boolean confirmed = false;

    /**
     * display in list view the titles of the media entries
     * @param entries contains the media entries to display
     */
    public void init(ArrayList<Media> entries) {
        this.entries = entries;

        for (int i = 0; i < entries.size(); i++) {
            entryListView.getItems().add(entries.get(i).getTitle());
        }
    }

    /**
     * get selected list item, stores the corresponding media entry
     * to be removed, mark confirmed as true, and closes the window.
     */
    public void handleRemove() {

        int selectedIndex = entryListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex < 0) {
            errorLabel.setText("Please select an entry to remove.");
            return;
        }

        selectedEntry = entries.get(selectedIndex);
        confirmed = true;

        Stage stage = (Stage) removeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * set confirm to false and closes window
     */
    public void handleCancel() {
        confirmed = false;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * get selected media entry
     * @return selected media entry
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

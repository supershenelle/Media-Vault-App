package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Media;

import java.util.ArrayList;

public class removeMediaController {
    @FXML private ListView<String> entryListView;
    @FXML private Label errorLabel;
    @FXML private Button removeButton;
    @FXML private Button cancelButton;

    private ArrayList<Media> entries;
    private Media selectedEntry;
    private boolean confirmed = false;

    public void init(ArrayList<Media> entries) {
        this.entries = entries;

        for (int i = 0; i < entries.size(); i++) {
            entryListView.getItems().add(entries.get(i).getTitle());
        }
    }

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

    public void handleCancel() {
        confirmed = false;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    public Media getSelectedEntry() {
        return selectedEntry;
    }

    public boolean isConfirmed() {
        return confirmed;
    }



}

package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import model.Media;
import model.Status;

import java.util.ArrayList;

public class updateStatusController {
    @FXML private ListView<String> entryListView;
    @FXML private RadioButton plannedRadio;
    @FXML private RadioButton inProgressRadio;
    @FXML private RadioButton completedRadio;
    @FXML private Label errorLabel;
    @FXML private Button confirmButton;
    @FXML private Button cancelButton;

    private ArrayList<Media> entries;
    private Media selectedEntry;
    private boolean confirmed = false;

    public void init(ArrayList<Media> entries) {
        this.entries = entries;

        // add title sa list view then display current status
        for (Media media : entries) {
            entryListView.getItems().add(media.getTitle() + " (" + media.getStatus().getDisplay() + ")");
        }
    }

    public void handleConfirm() {
        Status newStatus;
        int selectedIndex;
        boolean updated;

        selectedIndex = entryListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex < 0) {
            errorLabel.setText("Please select an entry to update.");
            return;
        }

        if (plannedRadio.isSelected()) {
            newStatus = Status.PLANNED;
        } else if (inProgressRadio.isSelected()) {
            newStatus = Status.IN_PROGRESS;
        } else if (completedRadio.isSelected()) {
            newStatus = Status.COMPLETED;
        } else {
            errorLabel.setText("Please select a status.");
            return;
        }

        // get media object na napili sa list view then update the status
        Media entry = entries.get(selectedIndex);
        updated = entry.setStatus(newStatus);

        if (!updated) {
            errorLabel.setText("Cannot mark as completed yet (check discography progress).");
            return;
        }

        selectedEntry = entry;
        confirmed = true;

        Stage stage = (Stage) confirmButton.getScene().getWindow();
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

package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Library;
import model.Media;
import model.Status;

import java.util.ArrayList;

/**
 * controller for the summaryView.
 * computes and displays total entry count, counts per status, and the
 * average rating across all rated Completed entries.
 */
public class summaryController {
    @FXML private Label totalLabel;
    @FXML private Label plannedLabel;
    @FXML private Label inProgressLabel;
    @FXML private Label completedLabel;
    @FXML private Label averageRatingLabel;
    @FXML private Button closeButton;
    @FXML private ListView<String> entryListView;
    @FXML private Label detailsLabel;

    private ArrayList<Media> entries;

    int plannedCount = 0;
    int inProgressCount = 0;
    int completedCount = 0;
    int completedRatedCount = 0;
    int completedRatingTotal = 0;

    /**
     * summarize library's entries by status and rating, then updates the summary labels with the computed totals/average
     * @param library the library to summarize
     */
    public void init(Library library) {
        
        entries = library.getEntries();

        for (Media media : library.getEntries()) {
            if (media.getStatus() == Status.PLANNED)
                plannedCount++;

            else if (media.getStatus() == Status.IN_PROGRESS)
                inProgressCount++;

            else if (media.getStatus() == Status.COMPLETED) {
                completedCount++;
                if (media.getRating() > 0) {
                    completedRatedCount++;
                    completedRatingTotal += media.getRating();
                }
            }

            entryListView.getItems().add("[" + media.getType() + "] " + media.getTitle());
        }

        totalLabel.setText("Total Entries: " + library.getEntries().size());
        plannedLabel.setText("Planned: " + plannedCount);
        inProgressLabel.setText("In Progress: " + inProgressCount);
        completedLabel.setText("Completed: " + completedCount);

        if (completedRatedCount > 0) {
            double average = (double) completedRatingTotal / completedRatedCount;
            averageRatingLabel.setText(String.format("Average Rating (Completed): %.2f", average));
        } else {
            averageRatingLabel.setText("Average Rating (Completed): N/A");
        }

    }

    /**
     * displays the full details (displayInfo()) of whichever entry is
     * currently selected in the list view.
     */
    public void handleEntrySelection() {
        int selectedIndex = entryListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex < 0 || selectedIndex >= entries.size())
            return;

        Media selected = entries.get(selectedIndex);
        detailsLabel.setText(selected.displayInfo());
    }

    /**
     * closes the window
     */
    public void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

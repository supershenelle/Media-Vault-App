package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Library;
import model.Media;
import model.Status;

public class summaryController {
    @FXML private Label totalLabel;
    @FXML private Label plannedLabel;
    @FXML private Label inProgressLabel;
    @FXML private Label completedLabel;
    @FXML private Label averageRatingLabel;
    @FXML private Button closeButton;

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
     * closes the window
     */
    public void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

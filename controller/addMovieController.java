package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Movie;
import model.Status;

/**
 * controller for the "Add Movie Entry"
 * validates user input and builds a movie object on confirm.
 */
public class addMovieController {
    @FXML private TextField titleField;
    @FXML private TextField directorField;
    @FXML private TextField yearField;
    @FXML private TextArea descriptionField;
    @FXML private RadioButton planningRadio;
    @FXML private RadioButton inProgressRadio;
    @FXML private Label errorLabel;
    @FXML private Button confirmButton;

    private Movie movie;
    private boolean confirmed = false;

    /**
     * validates all fields and if valid, creates a new Movie with the chosen status
     * and closes the window. displays an error message and returns early if any field is invalid.
     */
    public void handleConfirm() {
        String title = titleField.getText().trim();
        String director = directorField.getText().trim();
        String yearText = yearField.getText().trim();
        String description = descriptionField.getText().trim();
        Status status;
        int year;

        if (title.isEmpty()) {
            errorLabel.setText("Title cannot be empty.");
            return;
        }
        if (director.isEmpty()) {
            errorLabel.setText("Director cannot be empty.");
            return;
        }

        try {
            year = Integer.parseInt(yearText);
        } catch (NumberFormatException e) {
            errorLabel.setText("Year must be a valid number.");
            return;
        }

        if (description.isEmpty()) {
            errorLabel.setText("Description cannot be empty.");
            return;
        }

        if (planningRadio.isSelected()) {
            status = Status.PLANNED;
        }
        else {
            status = Status.IN_PROGRESS;
        }

        movie = new Movie(title, director, year, description, status);
        confirmed = true;

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    /**
     * get movie object
     * @return movie object
     */
    public Movie getResult() {
        return movie;
    }

    /**
     * check whether successfully confirmed
     * @return true if confirmed, false otherwise
     */
    public boolean isConfirmed() {
        return confirmed;
    }
}

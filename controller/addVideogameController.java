package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Movie;
import model.Status;
import model.Videogame;

public class addVideogameController {
    @FXML private TextField titleField;
    @FXML private TextField developerField;
    @FXML private TextField yearField;
    @FXML private TextArea descriptionField;
    @FXML private TextField hoursField;
    @FXML private RadioButton planningRadio;
    @FXML private RadioButton inProgressRadio;
    @FXML private Label errorLabel;
    @FXML private Button confirmButton;

    private Videogame videogame;
    private boolean confirmed = false;

    public void handleConfirm() {
        String title = titleField.getText().trim();
        String developer = developerField.getText().trim();
        String yearText = yearField.getText().trim();
        String hoursText = hoursField.getText().trim();
        String description = descriptionField.getText().trim();
        Status status;
        int year;
        int hours;

        if (title.isEmpty()) {
            errorLabel.setText("Title cannot be empty.");
            return;
        }
        if (developer.isEmpty()) {
            errorLabel.setText("Director cannot be empty.");
            return;
        }

        try {
            year = Integer.parseInt(yearText);
        } catch (NumberFormatException e) {
            errorLabel.setText("Year must be a valid number.");
            return;
        }

        try {
            hours = Integer.parseInt(hoursText);
        } catch (NumberFormatException e) {
            errorLabel.setText("hours played must be a valid number.");
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

        videogame = new Videogame(title, developer, year, description, hours, status);
        confirmed = true;

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    public Videogame getResult() {
        return videogame;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class mediaTypeController {
    @FXML
    private RadioButton movieRadioButton, videogameRadioButton, musicArtistRadioButton;
    @FXML
    private Button confirmButton;
    private String selectedType;
    private boolean confirmed = false;

    public void handleConfirm(ActionEvent event) {
        if (movieRadioButton.isSelected()) {
            selectedType = "model.Movie";
        } else if (videogameRadioButton.isSelected()) {
            selectedType = "model.Videogame";
        } else if (musicArtistRadioButton.isSelected()) {
            selectedType = "Music Artist";
        } else {
            return;
        }

        confirmed = true;

        // close the dialog/window this is inside of
        javafx.stage.Stage stage = (javafx.stage.Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    public String getSelectedType() {
        return selectedType;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}

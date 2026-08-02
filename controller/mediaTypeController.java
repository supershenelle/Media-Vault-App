package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

/**
 * controller for the media type selection. used whenever user to choose
 * which media type (Movie, Videogame, or Music Artist) they want to add/remove/update.
 */
public class mediaTypeController {
    @FXML private RadioButton movieRadioButton, videogameRadioButton, musicArtistRadioButton;
    @FXML private Button confirmButton;
    private String selectedType;
    private boolean confirmed = false;

    /**
     * get which radio button is selected, stores the corresponding media type,
     * marks the selection as confirmed, then closes the window.
     */
    public void handleConfirm() {
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
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    /**
     * get media type user selected
     * @return media type
     */
    public String getSelectedType() {
        return selectedType;
    }

    /**
     * check whether the user confirmed selection
     * @return true if confirmed, false otherwise
     */
    public boolean isConfirmed() {
        return confirmed;
    }
}

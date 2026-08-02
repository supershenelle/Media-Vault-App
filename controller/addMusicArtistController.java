package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Album;
import model.MusicArtist;
import model.Status;

import java.io.IOException;

/**
 * controller for the "Add Artist Entry".
 * validates artist details, opens the addAlbumView  to collect
 * one or more albums, and builds a MusicArtist object on confirm.
 */
public class addMusicArtistController {
    @FXML private TextField nameField;
    @FXML private TextArea descriptionField;
    @FXML private RadioButton planningRadio;
    @FXML private RadioButton inProgressRadio;
    @FXML private ListView<String> albumListView;
    @FXML private Label errorLabel;
    @FXML private Button addAlbumButton;
    @FXML private Button confirmButton;

    private MusicArtist musicArtist;
    private boolean confirmed = false;

    /**
     * validates the artist's details input, creates MusicArtist
     * object if not yet created, then opens the addAlbumView so the
     * user can input an album. if confirmed,add album to discography
     * and album list view is refreshed.
     */
    public void handleAddAlbum() {
        // input validation
        String name = nameField.getText().trim();
        String description = descriptionField.getText().trim();
        Status status;

        if (name.isEmpty()) {
            errorLabel.setText("Artist name cannot be empty.");
            return;
        }
        if (description.isEmpty()) {
            errorLabel.setText("Description cannot be empty.");
            return;
        }
        if (!planningRadio.isSelected() && !inProgressRadio.isSelected()) {
            errorLabel.setText("Please select a status.");
            return;
        }

        if (musicArtist == null) {

            if (planningRadio.isSelected()) {
                status = Status.PLANNED;
            } else {
                status = Status.IN_PROGRESS;
            }
            // create music artist object if not yet created
            musicArtist = new MusicArtist(name, description, status);
        }

        // open add album view to create albums
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addAlbumView.fxml"));
            Parent root = loader.load();
            addAlbumController albumController = loader.getController();

            Stage albumStage = new Stage();
            albumStage.setTitle("Add Album");
            albumStage.setScene(new Scene(root));
            albumStage.showAndWait();

            if (!albumController.isConfirmed())
                return;

            // add album to discography
            musicArtist.addAlbum(albumController.getTitle(), albumController.getGenre(), albumController.getYear(), albumController.getTrackCount());

            // display albums in listview
            albumListView.getItems().clear();

            for (Album album : musicArtist.getAlbums()) {

                String text = album.getTitle()
                        + " (" + album.getGenre()
                        + ", " + album.getYear()
                        + ", " + album.getTrackCount()
                        + " tracks)";

                albumListView.getItems().add(text);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * validates the artist's input details and that at least one album has been added.
     * displays an error message and returns early if invalid.
     */
    public void handleConfirm() {
        // input validation
        String name = nameField.getText().trim();
        String description = descriptionField.getText().trim();

        if (name.isEmpty()) {
            errorLabel.setText("Artist name cannot be empty.");
            return;
        }
        if (description.isEmpty()) {
            errorLabel.setText("Description cannot be empty.");
            return;
        }
        if (!planningRadio.isSelected() && !inProgressRadio.isSelected()) {
            errorLabel.setText("Please select a status.");
            return;
        }
        if (musicArtist == null || musicArtist.getAlbums().isEmpty()) {
            errorLabel.setText("Please add at least one album.");
            return;
        }

        musicArtist.setName(name);
        musicArtist.setDescription(description);

        confirmed = true;

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    /**
     * get music artist object
     * @return music artist object
     */
    public MusicArtist getResult() {
        return musicArtist;
    }

    /**
     * check whether successfully confirmed
     * @return true if confirmed, false otherwise
     */
    public boolean isConfirmed() {
        return confirmed;
    }


}

package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Album;
import model.Media;
import model.MusicArtist;

import java.util.ArrayList;

/**
 * controller for updateDiscographyView.
 * lets the user select an artist, then one of that artist's albums,
 * and log how many songs have been listened to in that album.
 */
public class updateDiscographyController {

    @FXML private ListView<String> artistListView;
    @FXML private ListView<String> albumListView;
    @FXML private TextField songsListenedField;
    @FXML private Label errorLabel;
    @FXML private Button confirmButton;
    @FXML private Button cancelButton;

    private ArrayList<MusicArtist> artists;
    private MusicArtist selectedArtist;
    private Album selectedAlbum;
    private boolean confirmed = false;

    /**
     * populate artist list view with the artist names
     * @param entries the music artists in the library
     */
    public void init(ArrayList<MusicArtist> entries) {
        this.artists = entries;

        // get artist to artistListView
        for (MusicArtist artist : artists) {
            artistListView.getItems().add(artist.getName());
        }

    }

    /**
     * clears the album list and input field, then repopulates the album list view
     * with that artist's albums and current listening progress.
     */
    public void handleArtistSelection() {

        errorLabel.setText("");
        albumListView.getItems().clear();
        songsListenedField.clear();

        // get selected artist index
        int index = artistListView.getSelectionModel().getSelectedIndex();

        if (index < 0 || index >= artists.size()) {
            return;
        }

        // get selected music artist object
        selectedArtist = artists.get(index);

        // display in album list view
        for (Album album : selectedArtist.getAlbums()) {
            albumListView.getItems().add(album.getTitle() + " (" + album.getSongsListened() + "/" + album.getTrackCount() + " tracks)");
        }
    }

    /**
     * validates artist info, then updates the album's listening progress and closes the window.
     * displays an error message and returns early if any selection/input is invalid.
     */
    public void handleConfirm() {
        int artistIndex;
        int albumIndex;
        String songsText;

        artistIndex = artistListView.getSelectionModel().getSelectedIndex();
        if (artistIndex < 0) {
            errorLabel.setText("Please select an artist.");
            return;
        }

        albumIndex = albumListView.getSelectionModel().getSelectedIndex();
        if (albumIndex < 0) {
            errorLabel.setText("Please select an album.");
            return;
        }

        selectedArtist = artists.get(artistIndex);
        selectedAlbum = selectedArtist.getAlbums().get(albumIndex);

        songsText = songsListenedField.getText().trim();
        int songsListened;

        try {
            songsListened = Integer.parseInt(songsText);
        } catch (NumberFormatException e) {
            errorLabel.setText("Songs listened must be a valid number.");
            return;
        }

        if (songsListened < 0 || songsListened > selectedAlbum.getTrackCount()) {
            errorLabel.setText("Must be between 0 and " + selectedAlbum.getTrackCount() + ".");
            return;
        }

        // set songs listened and confirm to true
        selectedAlbum.setSongsListened(songsListened);
        confirmed = true;

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    /**
     * set confirmed to false, and closes the window
     */
    public void handleCancel() {
        confirmed = false;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * get the artist whose discography was updated
     * @return the selected artist object
     */
    public MusicArtist getSelectedArtist() {
        return selectedArtist;
    }

    /**
     * get the album whose listening progress was updated
     * @return the selected album object
     */
    public Album getSelectedAlbum() {
        return selectedAlbum;
    }

    /**
     * check whether successfully confirmed
     * @return true if confirmed, false otherwise
     */
    public boolean isConfirmed() {
        return confirmed;
    }
}

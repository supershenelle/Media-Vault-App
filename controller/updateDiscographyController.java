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

    public void init(ArrayList<MusicArtist> entries) {
        this.artists = entries;

        // get artist to artistListView
        for (MusicArtist artist : artists) {
            artistListView.getItems().add(artist.getName());
        }

    }

    public void handleArtistSelection() {

        errorLabel.setText("");
        albumListView.getItems().clear();
        songsListenedField.clear();

        int index = artistListView.getSelectionModel().getSelectedIndex();

        if (index < 0 || index >= artists.size()) {
            return;
        }

        selectedArtist = artists.get(index);

        for (Album album : selectedArtist.getAlbums()) {
            albumListView.getItems().add(album.getTitle() + " (" + album.getSongsListened() + "/" + album.getTrackCount() + " tracks)");
        }
    }

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

        selectedAlbum.setSongsListened(songsListened);
        confirmed = true;

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    public void handleCancel() {
        confirmed = false;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public MusicArtist getSelectedArtist() {
        return selectedArtist;
    }

    public Album getSelectedAlbum() {
        return selectedAlbum;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}

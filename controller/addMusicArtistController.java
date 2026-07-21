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

    
    public void handleAddAlbum() {
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

            musicArtist = new MusicArtist(name, description, status);
        }

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
        public void handleConfirm() {
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

        public MusicArtist getResult() {
            return musicArtist;
        }

        public boolean isConfirmed() {
            return confirmed;
        }


}

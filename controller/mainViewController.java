package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mainViewController {
    @FXML private Label usernameLabel;
    @FXML private Label displayNameLabel;
    @FXML private Label bioLabel;
    // @FXML private ListView<Media> library;

    /*
    @FXML private Button addButton;
    @FXML private Button removeButton;
    @FXML private Button filterButton;
    @FXML private Button rateButton;
    @FXML private Button updateStatusButton;
    @FXML private Button updateDiscographyButton;
    @FXML private Button summaryButton;
    @FXML private Button logOutButton;
     */

    // para sa recent activity
    @FXML private Label movie1, movie2, movie3;
    @FXML private Label game1, game2, game3;
    @FXML private Label disco1, disco2, disco3;

    private Profile profile;

    public void init(Profile profile, Stage stage) {
        this.profile = profile;
        // Library lib = profile.getLibrary();

        usernameLabel.setText("Username: @" + profile.getUsername());
        displayNameLabel.setText("Display Name: " + profile.getDisplayName());
        bioLabel.setText("Bio: " + profile.getBio());

        updateRecent();
    }

    public void updateRecent() {
        Label[] movieBox = {movie1, movie2, movie3};
        Label[] gameBox = {game1, game2, game3};
        Label[] discoBox = {disco1, disco2, disco3};

        List<Media> movies = new ArrayList<>();
        List<Media> games = new ArrayList<>();
        List<Media> disco = new ArrayList<>();

        for (Media item : profile.getLibrary().getEntries())
        {
            if (item instanceof Movie)
                movies.add(item);

            else if (item instanceof Videogame)
                games.add(item);

            else if (item instanceof MusicArtist)
                disco.add(item);
        }

        fillBoxes(movies, movieBox);
        fillBoxes(games, gameBox);
        fillBoxes(disco, discoBox);
    }

    private void fillBoxes(List<Media> items, Label[] labels) {
        int size = items.size();
        for (int i = 0; i < labels.length; i++)
        {
            if (i < size)
            {
                Media recent = items.get(size - 1 - i);
                labels[i].setText(recent.getTitle());
            }

            else
                labels[i].setText("---");
        }
    }


    public void handleAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mediaTypeView.fxml"));
            Parent root = loader.load();
            mediaTypeController typeController = loader.getController();

            Stage typeStage = new Stage();
            typeStage.setTitle("Add Media");
            typeStage.setScene(new Scene(root));
            typeStage.showAndWait(); // pauses here until typeStage.close() runs

            if (!typeController.isConfirmed()) return; // user closed without picking

            switch (typeController.getSelectedType()) {
                case "model.Movie" -> addMovie();
                case "model.Videogame" -> addVideogame();
                case "Music Artist" -> addMusicArtist();
            }
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addMovie() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addMovieView.fxml"));
            Parent root = loader.load();
            addMovieController movieController = loader.getController();

            Stage movieStage = new Stage();
            movieStage.setTitle("Add Film");
            movieStage.setScene(new Scene(root));
            movieStage.showAndWait();

            if (!movieController.isConfirmed())
                return;

            profile.getLibrary().addEntry(movieController.getResult());
            updateRecent();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addVideogame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addVideogameView.fxml"));
            Parent root = loader.load();
            addVideogameController videogameController = loader.getController();

            Stage movieStage = new Stage();
            movieStage.setTitle("Add Game");
            movieStage.setScene(new Scene(root));
            movieStage.showAndWait();

            if (!videogameController.isConfirmed()) return;

            profile.getLibrary().addEntry(videogameController.getResult());
            updateRecent();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addMusicArtist() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addMusicArtistView.fxml"));
            Parent root = loader.load();
            addMusicArtistController musicArtistController = loader.getController();

            Stage artistStage = new Stage();
            artistStage.setTitle("Add Music Artist");
            artistStage.setScene(new Scene(root));
            artistStage.showAndWait();

            if (!musicArtistController.isConfirmed()) return;

            profile.getLibrary().addEntry(musicArtistController.getResult());
            updateRecent();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void handleRemove() {
        try {
            // get media type na idedelete
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mediaTypeView.fxml"));
            Parent root = loader.load();
            mediaTypeController typeController = loader.getController();

            Stage typeStage = new Stage();
            typeStage.setTitle("Remove Media");
            typeStage.setScene(new Scene(root));
            typeStage.showAndWait();

            if (!typeController.isConfirmed()) return; // close if di pumili

            String type = typeController.getSelectedType();
            ArrayList<Media> matches = profile.getLibrary().filterByType(type);

            if (matches.isEmpty()) {
                System.out.println("No entries found for that media type."); // lagyan to ng label
                return;
            }

            // open yung removeMediaView
            FXMLLoader removeLoader = new FXMLLoader(getClass().getResource("/view/removeMediaView.fxml"));
            Parent removeRoot = removeLoader.load();
            removeMediaController removeController = removeLoader.getController();
            removeController.init(matches);

            Stage removeStage = new Stage();
            removeStage.setTitle("Remove Media");
            removeStage.setScene(new Scene(removeRoot));
            removeStage.showAndWait();

            if (!removeController.isConfirmed()) return;

            // Step 3: remove and refresh
            profile.getLibrary().removeEntry(removeController.getSelectedEntry());
            updateRecent();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleFilter() {

    }

    public void handleRate() {

    }

    public void handleUpdateStatus() {

    }

    public void handleUpdateDiscography() {

    }

    public void handleSummary() {

    }

    public void handleLogout() {

    }

    
}

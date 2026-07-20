package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mainViewController {
    @FXML private Label usernameLabel;
    @FXML private Label displayNameLabel;
    @FXML private Label bioLabel;
    @FXML private ListView<Media> library;

    @FXML private Button addButton;
    @FXML private Button removeButton;
    @FXML private Button filterButton;
    @FXML private Button rateButton;
    @FXML private Button updateStatusButton;
    @FXML private Button updateDiscographyButton;
    @FXML private Button summaryButton;
    @FXML private Button logOutButton;

    // para sa recent activity
    @FXML private Label movie1, movie2, movie3;
    @FXML private Label game1, game2, game3;
    @FXML private Label disco1, disco2, disco3;

    private Profile profile;
    private Stage stage;

    public void init(Profile profile, Stage stage) {
        this.profile = profile;
        this.stage = stage;
        Library lib = profile.getLibrary();

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
//                case "model.Videogame" -> addVideogame();
//                case "Music Artist" -> addMusicArtist();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addMovie() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addMovieView.fxml"));
            Parent root = loader.load();
            addMovieController movieController = loader.getController();

            Stage movieStage = new Stage();
            movieStage.setTitle("Add Film");
            movieStage.setScene(new Scene(root));
            movieStage.showAndWait();

            if (!movieController.isConfirmed()) return;

            profile.getLibrary().addEntry(movieController.getResult());
            updateRecent();

        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleRemove() {

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

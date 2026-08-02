package controller;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mainViewController {
    @FXML private Label usernameLabel;
    @FXML private Label displayNameLabel;
    @FXML private Label bioLabel;
    @FXML private Label errorLabel;
    private SceneController sceneController;
    private Stage mainStage;
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

    // para sa favorites
    @FXML private Label movie11, movie21, movie31;
    @FXML private Label game11, game21, game31;
    @FXML private Label disco11, disco21, disco31;

    private Profile profile;

    /**
     * store profile/stage/scene controller, sets profile info in the labels,
     * and updates recently added.
     * @param profile contains profile that is currently being used
     * @param stage contains the primary stage
     * @param sceneController contains scene controller to navigate back to login
     */
    public void init(Profile profile, Stage stage, SceneController sceneController) {
        this.profile = profile;
        mainStage = stage;
        this.sceneController = sceneController;
        // Library lib = profile.getLibrary();

        usernameLabel.setText("Username: @" + profile.getUsername());
        displayNameLabel.setText("Display Name: " + profile.getDisplayName());
        bioLabel.setText("Bio: " + profile.getBio());

        updateRecent();
    }

    /**
     * fill all labels with the 3 most recent media entries per type
     */
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

    /**
     * fill all labels with the 3 most recent favorited media entries per type
     */
    public void updateFavorites(){
        Label[] favMovieBox = {movie11, movie21, movie31};
        Label[] favGameBox = {game11, game21, game31};
        Label[] favDiscoBox = {disco11, disco21, disco31};

        List<Media> favMovies = new ArrayList<>();
        List<Media> favGames = new ArrayList<>();
        List<Media> favDisco = new ArrayList<>();

        for (Media item : profile.getLibrary().getFavorites())
        {
            if (item instanceof Movie)
                favMovies.add(item);

            else if (item instanceof Videogame)
                favGames.add(item);

            else if (item instanceof MusicArtist)
                favDisco.add(item);

            fillBoxes(favMovies, favMovieBox);
            fillBoxes(favGames, favGameBox);
            fillBoxes(favDisco, favDiscoBox);
        }
    }

    /**
     * fills a fixed-size array of labels with the most recent items from
     * a list (newest first), setting any remaining labels to a placeholder
     * @param items the media items to display
     * @param labels the fixed-size label array to fill (the 3 recent-activity/favorite boxes)
     */
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

    /**
     * shows a temporary error message on screen for 3 seconds, then clears it
     * @param message the error message to display
     */
    private void showError(String message)
    {
        if (errorLabel != null) {
            errorLabel.setText(message);

            // Hide message automatically after 3 seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> errorLabel.setText(""));
            pause.play();
        }
    }

    /**
     * opens the media type selection, then opens to the correct
     * add-entry form (movie/videogame/music artist) based on the user's choice.
     * does nothing if the user closes the type popup without choosing.
     */
    public void handleAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mediaTypeView.fxml"));
            Parent root = loader.load();
            mediaTypeController typeController = loader.getController();

            Stage typeStage = new Stage();
            typeStage.setTitle("Add Media");
            typeStage.setScene(new Scene(root));
            typeStage.showAndWait(); // pauses here until typeStage.close() runs

            if (!typeController.isConfirmed()) // user closed without picking
                return;

            // switch to add(media form) based on what user picked
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

    /**
     * open addMovieView and adds the movie entry to library and refresh updateRecently
     */
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

            profile.getLibrary().addEntry(movieController.getResult()); // add entry to library
            updateRecent();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * open addVideogameView and adds the videogame entry to library and refresh updateRecently
     */
    public void addVideogame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addVideogameView.fxml"));
            Parent root = loader.load();
            addVideogameController videogameController = loader.getController();

            Stage movieStage = new Stage();
            movieStage.setTitle("Add Game");
            movieStage.setScene(new Scene(root));
            movieStage.showAndWait();

            if (!videogameController.isConfirmed())
                return;

            profile.getLibrary().addEntry(videogameController.getResult()); // add entry to library
            updateRecent();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * open addMusicArtistView and adds the music artist entry to library and refresh updateRecently
     */
    public void addMusicArtist() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addMusicArtistView.fxml"));
            Parent root = loader.load();
            addMusicArtistController musicArtistController = loader.getController();

            Stage artistStage = new Stage();
            artistStage.setTitle("Add Music Artist");
            artistStage.setScene(new Scene(root));
            artistStage.showAndWait();

            if (!musicArtistController.isConfirmed())
                return;

            profile.getLibrary().addEntry(musicArtistController.getResult()); // add entry to library
            updateRecent();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * opens the media type selection so user can choose which type to remove,
     * checks that matching entries exist, then opens removeMediaView.
     * if confirmed, removes the chosen entry from the library
     * and refreshes updateRecent.
     */
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

            if (!typeController.isConfirmed())
                return; // close if di pumili

            String type = typeController.getSelectedType();
            ArrayList<Media> matches = profile.getLibrary().filterByType(type);

            // display error if no media entry for that type
            if (matches.isEmpty()) {
                showError("No entries found for that media type.");
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

            if (!removeController.isConfirmed())
                return;

            // remove entry and refresh
            profile.getLibrary().removeEntry(removeController.getSelectedEntry());
            updateRecent();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * opens displayFilterView, so the user can filter entries by status or media type
     */
    public void handleFilter() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/displayFilterView.fxml"));
            Parent root = loader.load();
            displayFilterController filterController = loader.getController();
            filterController.init(profile.getLibrary());

            Stage filterStage = new Stage();
            filterStage.setTitle("Display / Filter Entries");
            filterStage.setScene(new Scene(root));
            filterStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * filters the library to Completed entries and opens rateView.
     * shows an error if there are no completed entries.
     */
    public void handleRate() {
        try {
            // filter objects that are completed
            ArrayList<Media> completed = profile.getLibrary().filterByStatus(Status.COMPLETED);

            if (completed.isEmpty()) {
                showError("No completed entries available to rate/review.");
                return;
            }

            // load rateView
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/rateView.fxml"));
            Parent root = loader.load();
            rateController rateReviewController = loader.getController();
            rateReviewController.init(completed);

            Stage rateStage = new Stage();
            rateStage.setTitle("Rate and Review");
            rateStage.setScene(new Scene(root));
            rateStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * opens the media type selection to choose which type to update, checks that matching entries exist,
     * then opens updateStatusView on those entries. refreshes the recent-activity boxes if confirmed.
     * shows an error if there are no entries of the chosen type.
     */
    public void handleUpdateStatus() {
        try {
            // get media type (or wag na papiliin media type? lagay nalang lahat ng medias in the listview)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mediaTypeView.fxml"));
            Parent root = loader.load();
            mediaTypeController typeController = loader.getController();

            Stage typeStage = new Stage();
            typeStage.setTitle("Update Media Status");
            typeStage.setScene(new Scene(root));
            typeStage.showAndWait();

            if (!typeController.isConfirmed())
                return;

            // get filtered media objects
            String type = typeController.getSelectedType();
            ArrayList<Media> matches = profile.getLibrary().filterByType(type);

            if (matches.isEmpty()) {
                showError("No entries found for that media type.");
                return;
            }

            // load update status window
            FXMLLoader statusLoader = new FXMLLoader(getClass().getResource("/view/updateStatusView.fxml"));
            Parent statusRoot = statusLoader.load();
            updateStatusController statusController = statusLoader.getController();
            statusController.init(matches);

            Stage statusStage = new Stage();
            statusStage.setTitle("Update Media Status");
            statusStage.setScene(new Scene(statusRoot));
            statusStage.showAndWait();

            if (!statusController.isConfirmed())
                return;

            updateRecent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * filters the library down to Music Artist entries, casts to MusicArtist to use music
     * artist methods, and opens updateDiscographyView. refreshes updateRecent if confirmed.
     * shows an error if there are no artists in the library.
     */
    public void handleUpdateDiscography() {
        try {
            // get only artists media objects in the library
            ArrayList<Media> matches = profile.getLibrary().filterByType("Music Artist"); // filter by type returns media class so dapat media

            if (matches.isEmpty()) {
                showError("No artists found in library.");
                return;
            }

            // make it music artists class para magamit music artists methods
            ArrayList<MusicArtist> artists = new ArrayList<>();
            for (Media media : matches) {
                artists.add((MusicArtist) media);
            }
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/updateDiscographyView.fxml"));
            Parent root = loader.load();
            updateDiscographyController discographyController = loader.getController();
            discographyController.init(artists);

            Stage discographyStage = new Stage();
            discographyStage.setTitle("Update Artist Discography Logs");
            discographyStage.setScene(new Scene(root));
            discographyStage.showAndWait();

            if (!discographyController.isConfirmed())
                return;

            updateRecent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * opens summaryView, which computes and displays entry counts and average completed rating
     */
    public void handleSummary() {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/summaryView.fxml"));
        Parent root = loader.load();
        summaryController summaryController1 = loader.getController(); // HUHU SAME NAME PALA SILA
        summaryController1.init(profile.getLibrary());

        Stage summaryStage = new Stage();
        summaryStage.setTitle("Library Summary");
        summaryStage.setScene(new Scene(root));
        summaryStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * opens addFavoriteView, then refreshes the favorites boxes once window closes.
     * shows an error if the library has no entries yet.
     */
    public void handleFavorite(){
            try {
                // get media entries in library
                ArrayList<Media> all = profile.getLibrary().getEntries();
                if (all.isEmpty()) {
                    showError("No entries in library yet.");
                    return;
                }

                // load addFavoriteView
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addFavoriteView.fxml"));
                Parent root = loader.load();
                addFavoriteController favController = loader.getController();
                favController.init(all);

                Stage favStage = new Stage();
                favStage.setTitle("Toggle Favorites");
                favStage.setScene(new Scene(root));
                favStage.showAndWait();

                updateFavorites();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    /**
     * returns to login screen
     */
    public void handleLogout() {
        sceneController.showLogin();
    }

    
}

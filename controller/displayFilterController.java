package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import model.Library;
import model.Media;
import model.Status;

import java.util.ArrayList;

public class displayFilterController {
    @FXML private RadioButton plannedRadio;
    @FXML private RadioButton inProgressRadio;
    @FXML private RadioButton completedRadio;
    @FXML private ListView<String> statusListView;

    @FXML private RadioButton movieRadio;
    @FXML private RadioButton videogameRadio;
    @FXML private RadioButton artistRadio;
    @FXML private ListView<String> typeListView;

    @FXML private Button closeButton;

    private Library library;


    public void init(Library library) {
        this.library = library;
    }

    public void handleStatusFilter() {
        Status status;

        if (plannedRadio.isSelected()) {
            status = Status.PLANNED;
        } else if (inProgressRadio.isSelected()) {
            status = Status.IN_PROGRESS;
        } else if (completedRadio.isSelected()) {
            status = Status.COMPLETED;
        } else {
            return;
        }

        ArrayList<Media> results = library.filterByStatus(status);
        refreshListView(statusListView, results);
    }

    public void handleTypeFilter() {
        String type;

        if (movieRadio.isSelected()) {
            type = "model.Movie";
        } else if (videogameRadio.isSelected()) {
            type = "model.Videogame";
        } else if (artistRadio.isSelected()) {
            type = "Music Artist";
        } else {
            return;
        }

        ArrayList<Media> results = library.filterByType(type);
        refreshListView(typeListView, results);
    }

    public void refreshListView(ListView<String> listView, ArrayList<Media> results) {
        listView.getItems().clear();

        if (results.isEmpty())
        {
            listView.getItems().add("No matching entries found");
        }
        else
        {
            for (Media media : results)
            {
                listView.getItems().add(media.toString());
            }
        }
    }

    public void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

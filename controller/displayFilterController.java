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

/**
 * controller for the displayFilterView.
 * shows two independent filtered lists side by side: one filtered by
 * status (Planned/In Progress/Completed) and one filtered by media type.
 */
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

    /**
     * stores profile's library so it can filter entries
     * @param library the library to filter and display
     */
    public void init(Library library) {
        this.library = library;
    }

    /**
     * reads which status radio button is selected, filters the library
     * by that status, and refreshes the status list view.
     */
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

    /**
     * reads which media type radio button is selected, filters the library
     * by that type, and refreshes the type list view.
     */
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

    /**
     * clears and repopulates list view with the given filtered results.
     * @param listView the list view to update
     * @param results the filtered media entries to display
     */
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

    /**
     * closes the window
     */
    public void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

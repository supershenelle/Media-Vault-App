package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import model.Library;

public class mainView {
    private Library library;

    public mainView(Library library) {
        this.library = library;
    }

    public Scene getScene() {
        StackPane root = new StackPane(new Label("Logged in! Library has " + library.getEntries().size() + " entries."));
        return new Scene(root, 800, 600);
    }
}
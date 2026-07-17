// view/loginView.java
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class loginView {
    private TextField usernameField = new TextField();
    private Button loginButton = new Button("Log In");
    private Label errorLabel = new Label();

    public Scene getScene() {
        Label title = new Label("Media Vault");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        usernameField.setPromptText("Enter username");
        usernameField.setMaxWidth(200);

        errorLabel.setStyle("-fx-text-fill: red;");

        VBox root = new VBox(15, title, usernameField, loginButton, errorLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));

        return new Scene(root, 800, 600);
    }

    public TextField getUsernameField() { return usernameField; }
    public Button getLoginButton() { return loginButton; }
    public Label getErrorLabel() { return errorLabel; }
}
// view/loginView.java
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.util.Objects;

public class loginView {
    private TextField usernameField = new TextField();
    private Button loginButton = new Button("Log In");
    private Label errorLabel = new Label();

    public Scene getScene() {
        Text title1 = new Text("Media");
        title1.setStyle("-fx-font-size: 80px; -fx-fill: #A04747;");
        Text title2 = new Text("Vault");
        title2.setStyle("-fx-font-size: 80px; -fx-fill: #A04747;");

        VBox titleContainer = new VBox(title1, title2);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.getStyleClass().add("titleContainer");
        titleContainer.setMaxWidth(350);

        usernameField.setPromptText("Enter username");
        usernameField.setMaxWidth(200);

        errorLabel.setStyle("-fx-text-fill: red;");

        VBox root = new VBox(15, titleContainer, usernameField, loginButton, errorLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        return scene;
    }

    public TextField getUsernameField() { return usernameField; }
    public Button getLoginButton() { return loginButton; }
    public Label getErrorLabel() { return errorLabel; }
}
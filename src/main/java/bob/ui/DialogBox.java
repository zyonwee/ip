package bob.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

//@@author {Wang Haitao iP}-reused
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView avatar;

    private DialogBox(String text, String avatarPath, boolean isUser) { // Changed to avatarPath
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up the dialog label
        dialog.setText(text);
        dialog.setWrapText(true);
        dialog.setMinHeight(Label.USE_PREF_SIZE); // Important: Use preferred size for height
        dialog.setPrefWidth(250.0); // Set preferred width
        dialog.setMaxWidth(250.0); // Set maximum width
        dialog.setPadding(new Insets(10));
        dialog.setStyle("-fx-background-radius: 15; -fx-background-color: #E8E8E8;");


        // Set up the avatar ImageView
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(avatarPath))); // Load image from path
        avatar.setImage(image);
        avatar.setFitHeight(80); // Set fixed size
        avatar.setFitWidth(80);
        avatar.setPreserveRatio(true);
        avatar.setStyle("-fx-background-radius: 20;"); // Keep rounded corners style


        // Set background based on user/bob
        String backgroundColor = isUser ? "#A0D28E" : "#C0C0C0";

        // Dotted border (common style)
        String borderStyle = String.format("-fx-border-color: %s; -fx-border-style: dotted; -fx-border-width: 5;", backgroundColor); // Light grey dotted border
        this.setStyle(this.getStyle() + borderStyle); // Add to existing styles

        // Set HBox properties
        this.setSpacing(10);
        this.setPadding(new Insets(5, 10, 5, 10));
        this.setFillHeight(true); // Important: Allow the HBox to fill height
        this.setMinHeight(USE_PREF_SIZE); // Use preferred size for height
        this.setPrefWidth(Region.USE_COMPUTED_SIZE); // Use computed size for width
        this.setMaxWidth(Double.MAX_VALUE); // Allow maximum width
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.setStyle("-fx-background-radius: 15; -fx-background-color: #DCF8C6;");
    }

    public static DialogBox getUserDialog(String text) {
        var db = new DialogBox(text, "/images/user.png", true); // Pass true for user
        db.setAlignment(Pos.TOP_RIGHT);
        db.dialog.setAlignment(Pos.CENTER_RIGHT);
        return db;
    }

    public static DialogBox getBobDialog(String text) {
        var db = new DialogBox(text, "/images/bob.png", false); // Pass false for Bob
        db.flip();
        return db;
    }
}
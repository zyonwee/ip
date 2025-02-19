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

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

//@@author {Wang Haitao iP}-reused
/**
 * Represents a dialog box in the chat interface.  A dialog box contains a message
 * and an avatar.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView avatar;

    /**
     * Constructs a new DialogBox.
     *
     * @param text      The text message to display in the dialog box.
     * @param avatarPath The path to the avatar image.  This path should be relative to the
     *                   resources' directory.  For example: "/images/user.png".
     * @param isUser    A boolean indicating whether this dialog box is for the user (true)
     *                  or Bob (false). This is used to style the dialog box differently.
     */
    private DialogBox(String text, String avatarPath, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace(); // Handle appropriately in a real application
        }

        // Set up the dialog label
        dialog.setText(text);
        dialog.setWrapText(true);
        dialog.setMinHeight(Label.USE_PREF_SIZE);
        dialog.setPrefWidth(250.0);
        dialog.setMaxWidth(250.0);
        dialog.setPadding(new Insets(10));
        dialog.setStyle("-fx-background-radius: 15; -fx-background-color: #E8E8E8;");

        // Set up the avatar ImageView
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(avatarPath)));
        avatar.setImage(image);
        avatar.setFitHeight(80);
        avatar.setFitWidth(80);
        avatar.setPreserveRatio(true);
        avatar.setStyle("-fx-background-radius: 20;");

        // Set background and border based on user/bob
        String backgroundColor = isUser ? "#A0D28E" : "#C0C0C0";
        String borderStyle = String.format("-fx-border-color: %s; -fx-border-style: dotted; -fx-border-width: 5;", backgroundColor);
        this.setStyle(this.getStyle() + borderStyle);

        // Set HBox properties
        this.setSpacing(10);
        this.setPadding(new Insets(5, 10, 5, 10));
        this.setFillHeight(true);
        this.setMinHeight(USE_PREF_SIZE);
        this.setPrefWidth(Region.USE_COMPUTED_SIZE);
        this.setMaxWidth(Double.MAX_VALUE);
    }

    /**
     * Flips the order of the children in the HBox (avatar and dialog) and aligns
     * the dialog box to the left.  This is used to display Bob's messages on the left.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.setStyle("-fx-background-radius: 15; -fx-background-color: #DCF8C6;");
    }

    /**
     * Creates a new DialogBox for the user.
     *
     * @param text The text message to display.
     * @return A DialogBox instance configured for the user.
     */
    public static DialogBox getUserDialog(String text) {
        var db = new DialogBox(text, "/images/user.png", true);
        db.setAlignment(Pos.TOP_RIGHT);
        db.dialog.setAlignment(Pos.CENTER_RIGHT);
        return db;
    }

    /**
     * Creates a new DialogBox for Bob.
     *
     * @param text The text message to display.
     * @return A DialogBox instance configured for Bob.
     */
    public static DialogBox getBobDialog(String text) {
        var db = new DialogBox(text, "/images/bob.png", false);
        db.flip();
        return db;
    }
}
package bob.ui;

import bob.Bob;
import bob.exceptions.BobException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.layout.Region;

//@@author {Wang Haitao iP}-reused
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bob bob;

    @FXML
    public void initialize() {
        // Configure ScrollPane
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(dialogContainer);

        // Configure VBox (dialogContainer)
        dialogContainer.setSpacing(10);
        dialogContainer.setPadding(new Insets(10));
        dialogContainer.setMinWidth(Region.USE_PREF_SIZE);
        dialogContainer.setPrefWidth(Region.USE_COMPUTED_SIZE);
        dialogContainer.setMaxWidth(Double.MAX_VALUE);

        // Bind scroll pane to follow the bottom
        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) ->
                scrollPane.setVvalue(1.0));
    }

    public void setBob(Bob b) {
        bob = b;
        DialogBox welcomeMessage = DialogBox.getBobDialog("Hello! I'm Bob. Bob the Princess\n" +
                "What can I do for you?");
        dialogContainer.getChildren().add(welcomeMessage);
    }

    @FXML
    private void handleUserInput() throws BobException {

        String input = userInput.getText().trim();

        if (!input.isEmpty()) {
            String response = bob.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input),
                    DialogBox.getBobDialog(response)
            );
            userInput.clear();
        }
    }
}
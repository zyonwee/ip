package bob;

import bob.commands.Command;
import bob.exceptions.BobException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.tasks.TaskList;
import bob.ui.DialogBox;
import bob.ui.Ui;

/**
 * The main class for the Bob application.
 */
public class Bob {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Bob object with the specified file path for task storage.
     * Loads tasks from the storage file or creates a new empty task list if loading fails.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Bob(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (BobException e) {
            DialogBox.getBobDialog(e.getMessage());
            tasks = new TaskList(); // Initialize to an empty list if loading fails
        }
    }

    //@@author {Wang Haitao iP}-reused
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            StringBuilder response = new StringBuilder();

            // Capture the response instead of printing to console
            ui.captureResponse(() -> {
                try {
                    c.execute(tasks, ui, storage);
                } catch (BobException e) {
                    Ui.appendResponse(e.getMessage());
                }
            }, response);

            if (c.isExit()) {
                System.exit(0);
            }
         return response.toString();
    } catch (BobException e) {
        return "OOPS!!! " + e.getMessage();
    }
}



}
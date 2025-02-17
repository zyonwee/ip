package bob;

import bob.commands.Command;
import bob.exceptions.BobException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.tasks.TaskList;
import bob.ui.Ui;

/**
 * The main class for the Bob application.
 */
public class Bob {
    private final Storage storage;
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
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BobException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(); // Initialize to an empty list if loading fails
        }
    }

    /**
     * Runs the Bob application.
     * Displays the welcome message, reads and processes commands from the user until the exit command is given.
     * Handles potential BobExceptions during command execution.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BobException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine(); // Show line after each command (including errors)
            }
        }
    }

    /**
     * Main method to start the Bob application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Bob("data/bobTasks.txt").run(); // Consider making the file path configurable
    }
}
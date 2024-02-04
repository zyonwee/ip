package bob.commands;

import bob.exceptions.BobException;
import bob.storage.Storage;
import bob.ui.Ui;
import bob.tasks.Deadline;
import bob.tasks.Task;
import bob.tasks.TaskList;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructs an AddDeadlineCommand object.
     * Parses the command arguments to extract the description and deadline.
     *
     * @param arguments The command arguments string.  Expected format: "deadline <description> /by <time>"
     * @throws BobException If the arguments are invalid or missing.
     */
    public AddDeadlineCommand(String arguments) throws BobException {
        try {
            String[] parts = arguments.split(" /by ");
            if (parts.length < 2) {
                throw new BobException("Did you forget your '/by' command? So un-attentive!");
            }
            this.description = parts[0];
            this.by = parts[1];
        } catch (Exception e) {
            throw new BobException("Invalid deadline format. Use: 'deadline <description> /by <time>'");
        }
    }

    /**
     * Executes the add deadline command.
     * Creates a new Deadline task, adds it to the task list, saves the updated list, and displays a message.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @return
     * @throws BobException If an error occurs during execution (e.g., saving the task list).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        Task task = new Deadline(description, by);
        TaskList.add(task); // Assuming TaskList.add is a static method
        storage.save(tasks);
        Ui.appendResponse("You better remember to do it or I'll spank you"); // Consider a more professional message
        return null;
    }
}
package bob.commands;

import bob.exceptions.BobException;
import bob.storage.Storage;
import bob.ui.Ui;
import bob.tasks.Task;
import bob.tasks.TaskList;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand object.
     * Parses the command arguments to extract the index of the task to unmark.
     *
     * @param arguments The command arguments string. Expected format: "unmark <task_number>"
     * @throws BobException If the argument is not a valid integer or if the task number is invalid.
     */
    public UnmarkCommand(String arguments) throws BobException {
        try {
            this.index = Integer.parseInt(arguments) - 1; // Subtract 1 for 0-based indexing
        } catch (NumberFormatException e) {
            throw new BobException("Invalid task number. Please enter a valid integer.");
        }
    }

    /**
     * Executes the unmark command.
     * Unmarks the specified task as done, saves the updated task list, and displays a confirmation message.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws BobException If the task number is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        if (index < 0 || index >= tasks.size()) {
            throw new BobException("Task does not exist. Please provide a valid task number.");
        }
        Task task = tasks.get(index).unmarkAsDone(); // Assuming TaskList.get() returns a Task object
        storage.save(tasks);
        ui.showLine();
        System.out.println("You lied! You have not started to " + task.description + "\n"); // Consider a more professional message
    }
}
package bob.commands;

import bob.exceptions.BobException;
import bob.storage.Storage;
import bob.ui.Ui;
import bob.tasks.Task;
import bob.tasks.TaskList;
import bob.tasks.ToDo;

/**
 * Represents a command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs an AddTodoCommand object.
     *
     * @param arguments The command arguments string.  Expected format: "todo <description>"
     * @throws BobException If the description is empty.
     */
    public AddTodoCommand(String arguments) throws BobException {
        if (arguments.isEmpty()) {
            throw new BobException("Please write a valid description");
        }
        this.description = arguments;
    }

    /**
     * Executes the add todo command.
     * Creates a new ToDo task, adds it to the task list, saves the updated list, and displays a confirmation message.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @return
     * @throws BobException If an error occurs during execution (e.g., saving the task list).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        Task task = new ToDo(description);
        TaskList.add(task); // Assuming TaskList.add is a static method
        storage.save(tasks);
        Ui.appendResponse("No deadline? I guess we are never doing this then..."); // Consider a more professional message
        return null;
    }
}
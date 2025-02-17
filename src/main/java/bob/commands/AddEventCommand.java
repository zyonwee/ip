package bob.commands;

import bob.exceptions.BobException;
import bob.storage.Storage;
import bob.ui.Ui;
import bob.tasks.Event;
import bob.tasks.Task;
import bob.tasks.TaskList;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an AddEventCommand object.
     * Parses the command arguments to extract the description, start time, and end time.
     *
     * @param arguments The command arguments string. Expected format: "event <description> /from <start> /to <end>"
     * @throws BobException If the arguments are invalid, missing, or in the wrong order.
     */
    public AddEventCommand(String arguments) throws BobException {
        try {
            String[] parts = arguments.split(" /from | /to ");
            if (parts.length < 3) {
                throw new BobException("Did you forget your '/from' and '/to' command? So un-attentive!\n" +
                        "Ps. the order matters!");
            }
            this.description = parts[0];
            this.from = parts[1];
            this.to = parts[2];
        } catch (Exception e) {
            throw new BobException("Invalid event format. Use: 'event <description> /from <start> /to <end>'");
        }
    }

    /**
     * Executes the add event command.
     * Creates a new Event task, adds it to the task list, saves the updated list, and displays a confirmation message.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws BobException If an error occurs during execution (e.g., saving the task list).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        Task task = new Event(description, from, to);
        TaskList.add(task); // Assuming TaskList.add is a static method
        storage.save(tasks);
        ui.showLine();
        System.out.println("Am I invited? Nah... no one cares bout this damsel"); // Consider a more professional message
    }
}
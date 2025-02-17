package bob.commands;

import bob.exceptions.BobException;
import bob.storage.Storage;
import bob.ui.Ui;
import bob.tasks.Task;
import bob.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param arguments The keyword to search for.
     * @throws BobException If the keyword is empty.
     */
    public FindCommand(String arguments) throws BobException {
        if (arguments.isEmpty()) {
            throw new BobException("Please provide a keyword to search for.");
        }
        this.keyword = arguments.trim(); // Trim whitespace
    }

    /**
     * Executes the find command.
     * Searches the task list for tasks containing the keyword and displays the matching tasks.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage handler (not used by this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) { // Use getTasks() to get a copy
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) { // Case-insensitive search
                matchingTasks.add(task);
            }
        }

        ui.showLine();
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
        ui.showLine();
    }
}
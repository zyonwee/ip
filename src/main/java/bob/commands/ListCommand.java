package bob.commands;

import bob.storage.Storage;
import bob.ui.Ui;
import bob.tasks.TaskList;

/**
 * Represents the list command.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * Displays the list of tasks.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage handler (not used by this command).
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showTaskList(tasks);
        return null;
    }
}
package bob.commands;

import bob.storage.Storage;
import bob.ui.Ui;
import bob.tasks.TaskList;

/**
 * Represents the exit command.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * Displays the goodbye message.
     *
     * @param tasks   The task list (not used by this command).
     * @param ui      The user interface.
     * @param storage The storage handler (not used by this command).
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showGoodbye();
        return null;
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return true because this is the exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
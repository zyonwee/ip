package bob.commands;

import bob.exceptions.BobException;
import bob.storage.Storage;
import bob.ui.Ui;
import bob.tasks.TaskList;

/**
 * Abstract base class for all commands.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @return
     * @throws BobException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BobException;

    /**
     * Checks if the command is an exit command.
     *
     * @return {true} if the command is an exit command, {false} otherwise.  The default implementation returns {false}.
     */
    public boolean isExit() {
        return false;
    }
}
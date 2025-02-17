package bob.commands;

import bob.storage.Storage;
import bob.ui.Ui;
import bob.tasks.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}

package bob.commands;

import bob.exceptions.BobException;
import bob.storage.Storage;
import bob.ui.Ui;
import bob.tasks.Task;
import bob.tasks.TaskList;
import bob.tasks.ToDo;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String arguments) throws BobException {
        if (arguments.isEmpty()) {
            throw new BobException("Please write a valid description");
        }
        this.description = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        Task task = new ToDo(description);
        TaskList.add(task);
        storage.save(tasks);
        ui.showLine();
        System.out.println("No deadline? I guess we are never doing this then...");
    }
}

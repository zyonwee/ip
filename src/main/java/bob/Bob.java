package bob;

import bob.commands.Command;
import bob.exceptions.BobException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.tasks.TaskList;
import bob.ui.Ui;

public class Bob {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BobException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BobException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Bob("data/bobTasks.txt").run();
    }
}
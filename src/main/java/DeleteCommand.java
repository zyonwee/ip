public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String arguments) throws BobException {
        try {
            this.index = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new BobException("Invalid task number. Please enter a valid integer.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        if (index < 0 || index >= tasks.size()) {
            throw new BobException("Task does not exist. Please provide a valid task number.");
        }
        Task task = tasks.delete(index);
        storage.save(tasks);
        ui.showLine();
        System.out.println("Fine... stop " + task.description + " then...");
    }
}

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String arguments) throws BobException {
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
        Task task = tasks.get(index).markAsDone();
        ui.showLine();
        System.out.println("Took you long enough to complete " + task.description + "\n");
    }
}

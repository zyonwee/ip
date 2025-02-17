public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String arguments) throws BobException {
        try {
            String[] parts = arguments.split(" /by ");
            if (parts.length < 2) {
                throw new BobException("Did you forget your '/by' command? So un-attentive!");
            }
            this.description = parts[0];
            this.by = parts[1];
        } catch (Exception e) {
            throw new BobException("Invalid deadline format. Use: 'deadline <description> /by <time>'");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        Task task = new Deadline(description, by);
        TaskList.add(task);
        storage.save(tasks);
        ui.showLine();
        System.out.println("You better remember to do it or I'll spank you");
    }
}

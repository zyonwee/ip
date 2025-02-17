public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String arguments) throws BobException {
        try {
            String[] parts = arguments.split(" /from | /to ");
            if (parts.length < 3) {
                throw new BobException("Did you forget your '/from' and '/to' command? So un-attentive!\n" +
                        "Ps. the order matters!");
            }
            this.description = parts[0];
            this.from = parts[1];
            this.to = parts[2];
        } catch (Exception e) {
            throw new BobException("Invalid event format. Use: 'event <description> /from <start> /to <end>'");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        Task task = new Event(description, from, to);
        TaskList.add(task);
        storage.save(tasks);
        ui.showLine();
        System.out.println("Am I invited? Nah... no one cares bout this damsel");
    }
}

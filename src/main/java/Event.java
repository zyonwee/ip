public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = DateUtils.processDate(from);
        this.to = DateUtils.processDate(to);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                DateUtils.getFileString(from) + " | " + DateUtils.getFileString(to);
    }
}
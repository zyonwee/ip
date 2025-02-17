package bob.tasks;

import bob.utils.DateUtils;

/**
 * Represents an event task. An event task has a description, a start time, and an end time.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Constructs an Event object.
     *
     * @param description The description of the event.
     * @param from        The start time of the event. The format of this time should be handled by {@link DateUtils#processDate(String)}.
     * @param to          The end time of the event. The format of this time should be handled by {@link DateUtils#processDate(String)}.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = DateUtils.processDate(from);
        this.to = DateUtils.processDate(to);
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task, including the description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the event task formatted for saving to a file.
     *
     * @return A string representation of the event task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                DateUtils.getFileString(from) + " | " + DateUtils.getFileString(to);
    }
}
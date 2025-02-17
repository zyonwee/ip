package bob.tasks;

import bob.utils.DateUtils;

/**
 * Represents a deadline task.  A deadline task has a description and a due date.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructs a Deadline object.
     *
     * @param description The description of the task.
     * @param by          The due date of the task.  The format of this date should be handled by {@link DateUtils#processDate(String)}.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DateUtils.processDate(by);
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task, including the description and due date.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the deadline task formatted for saving to a file.
     *
     * @return A string representation of the deadline task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + DateUtils.getFileString(by);
    }
}
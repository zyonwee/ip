package bob.tasks;

/**
 * Represents a todo task. A todo task has a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string representation of the todo task, including the description.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Returns a string representation of the todo task formatted for saving to a file.
     *
     * @return A string representation of the todo task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
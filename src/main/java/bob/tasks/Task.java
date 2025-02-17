package bob.tasks;

import bob.exceptions.BobException;

/**
 * Abstract base class for all task types (ToDo, Deadline, Event).
 */
public abstract class Task {
    public final String description;
    public boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     *
     * @return This Task object (for chaining).
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Unmarks the task as done.
     *
     * @return This Task object (for chaining).
     */
    public Task unmarkAsDone() {
        this.isDone = false;
        return this;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task, including the status icon and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task to a string format for saving to a file.
     *
     * @return A string representation of the task for file storage.
     */
    public abstract String toFileString();

    /**
     * Parses a string representation of a task from a file and returns the corresponding Task object.
     *
     * @param taskString The string representation of the task from the file.
     * @return The Task object.
     * @throws BobException If the task string is invalid or cannot be parsed.
     */
    public static Task fromString(String taskString) throws BobException {
        String[] parts = taskString.split(" \\| ");
        if (parts.length < 3) {
            throw new BobException("Invalid task format in file: " + taskString); // Include the faulty string
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = switch (type) {
            case "T" -> new ToDo(description);
            case "D" -> {
                if (parts.length < 4) {
                    throw new BobException("Invalid deadline format in file: " + taskString); // Include faulty string
                }
                yield new Deadline(description, parts[3]);
            }
            case "E" -> {
                if (parts.length < 5) {
                    throw new BobException("Invalid event format in file: " + taskString); // Include faulty string
                }
                yield new Event(description, parts[3], parts[4]);
            }
            default -> throw new BobException("Unknown task type in file: " + taskString); // Include faulty string
        };

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
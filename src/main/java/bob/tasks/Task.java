package bob.tasks;

import bob.exceptions.BobException;

public abstract class Task {
    public final String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public Task unmarkAsDone() {
        this.isDone = false;
        return this;
    }

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
     * Parses a string representation of a task and returns the corresponding Bob.Task object.
     *
     * @param taskString The string representation of the task.
     * @return The Bob.Task object.
     * @throws BobException If the task string is invalid.
     */
    public static Task fromString(String taskString) throws BobException {
        String[] parts = taskString.split(" \\| ");
        if (parts.length < 3) {
            throw new BobException("Invalid task format in file.");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = switch (type) {
            case "T" -> new ToDo(description);
            case "D" -> {
                if (parts.length < 4) {
                    throw new BobException("Invalid deadline format in file.");
                }
                yield new Deadline(description, parts[3]);
            }
            case "E" -> {
                if (parts.length < 5) {
                    throw new BobException("Invalid event format in file.");
                }
                yield new Event(description, parts[3], parts[4]);
            }
            default -> throw new BobException("Unknown task type in file.");
        };

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
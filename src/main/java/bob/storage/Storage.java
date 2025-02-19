package bob.storage;

import bob.exceptions.BobException;
import bob.tasks.Task;
import bob.tasks.TaskList;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private final Path filePath; // Use Path instead of String

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     * @throws IllegalArgumentException if the file path is null or empty.
     */
    public Storage(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        this.filePath = Path.of(filePath); // Initialize as a Path
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file. Returns an empty list if the file does not exist.
     * @throws BobException If an error occurs during file reading or task parsing.
     */
    public List<Task> load() throws BobException {
        if (!Files.exists(filePath)) {
            return new ArrayList<>(); // Return empty list if file doesn't exist
        }

        try {
            return Files.lines(filePath, StandardCharsets.UTF_8)
                    .map(line -> {
                        try {
                            return Task.fromString(line);
                        } catch (BobException e) {
                            throw new RuntimeException("Error parsing task from file: " + e.getMessage(), e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new BobException("Error loading tasks from file: " + e.getMessage(), e); // Include original exception
        } catch (RuntimeException re) { // Catch the RuntimeException
            if (re.getCause() instanceof BobException) {
                throw (BobException) re.getCause(); // Unwrap and rethrow the BobException
            } else {
                throw new BobException("Error loading tasks: " + re.getMessage(), re); // Or wrap in a new BobException
            }
        }
    }


    /**
     * Saves the tasks to the file.
     *
     * @param tasks The task list to save. Must not be null.
     * @throws BobException If an error occurs during file writing.
     */
    public void save(TaskList tasks) throws BobException {
        if (tasks == null) {
            throw new IllegalArgumentException("Task list cannot be null");
        }

        try {
            List<String> lines = tasks.getTasks().stream()
                    .map(task -> {
                        if (task == null) {
                            throw new IllegalArgumentException("Task in list cannot be null");
                        }
                        return task.toFileString();
                    })
                    .collect(Collectors.toList());

            Files.write(filePath, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            throw new BobException("Error saving tasks to file: " + e.getMessage(), e); // Include original exception
        } catch (IllegalArgumentException iae) {
            throw new BobException("Error saving tasks: " + iae.getMessage(), iae);
        }
    }
}
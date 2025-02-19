package bob.storage;

import bob.exceptions.BobException;
import bob.tasks.Task;
import bob.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file. Returns an empty list if the file does not exist.
     * @throws BobException If an error occurs during file reading or task parsing.
     */
    public List<Task> load() throws BobException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Check if the file exists *before* attempting to read it.
        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                try {
                    Task task = Task.fromString(line);
                    tasks.add(task);
                } catch (BobException e) {
                    throw new BobException("Error parsing task from file: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new BobException("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The task list to save.  Must not be null.
     * @throws BobException If an error occurs during file writing.
     */
    public void save(TaskList tasks) throws BobException {
        assert tasks != null : "Task list cannot be null"; // Check for null task list
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                assert task != null : "Task in list cannot be null"; // Check for null tasks in the list
                fw.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            throw new BobException("Error saving tasks to file: " + e.getMessage());
        }
    }
}
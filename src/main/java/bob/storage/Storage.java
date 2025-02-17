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
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file. Returns an empty list if the file does not exist.
     * @throws BobException If an error occurs during file reading.
     */
    public List<Task> load() throws BobException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                try { // Handle potential exceptions during Task.fromString
                    Task task = Task.fromString(line);
                    tasks.add(task);
                } catch (BobException e) {
                    throw new BobException("Error parsing task from file: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new BobException("Error loading tasks from file: " + e.getMessage()); // Include specific message
        }
        return tasks;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The task list to save.
     * @throws BobException If an error occurs during file writing.
     */
    public void save(TaskList tasks) throws BobException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                fw.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            throw new BobException("Error saving tasks to file: " + e.getMessage()); // Include specific message
        }
    }
}
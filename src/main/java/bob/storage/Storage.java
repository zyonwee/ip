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

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws BobException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = Task.fromString(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new BobException("Error loading tasks from file.");
        }
        return tasks;
    }

    public void save(TaskList tasks) throws BobException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                fw.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            throw new BobException("Error saving tasks to file.");
        }
    }
}
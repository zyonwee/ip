import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        TaskList.tasks = tasks;
    }

    public static void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Converts the task list to a string format for saving to a file.
     *
     * @return A string representation of the task list for file storage.
     */
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toFileString()).append("\n");
        }
        return sb.toString();
    }
}
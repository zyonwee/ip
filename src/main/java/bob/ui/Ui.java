package bob.ui;

import bob.tasks.TaskList;

import java.util.Scanner;

/**
 * Handles user interface interactions, including displaying messages and reading commands.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Bob.Bob the Princess\nWhat can I do for you?\n");
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Please don't trouble me again!\n");
    }

    /**
     * Displays a separator line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Reads a command from the user.
     *
     * @return The command string entered by the user.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Displays the task list.
     *
     * @param tasks The task list to display.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You didn't tell me what to do yet");
            return;
        }
        System.out.println("Woah woah woah,\nlet me get this straight... You want me to :\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("\nI think you're asking for way too much >.<\n"); // Consider a more professional message
    }
}
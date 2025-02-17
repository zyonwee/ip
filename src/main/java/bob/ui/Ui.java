package bob.ui;

import bob.tasks.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________";

    public void showWelcome() {
        System.out.println("Hello! I'm Bob.Bob the Princess\nWhat can I do for you?\n");
    }

    public void showGoodbye() {
        System.out.println("Bye. Please don't trouble me again!\n");
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You didn't tell me what to do yet");
            return;
        }
        System.out.println("""
                Woah woah woah,
                let me get this straight... You want me to :
                """);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("\nI think you're asking for way too much >.<\n");
    }
}
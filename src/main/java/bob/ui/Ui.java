package bob.ui;

import bob.tasks.TaskList;

/**
 * Handles user interface interactions, including displaying messages and reading commands.
 * All methods are static and append output to a StringBuilder.
 */
public class Ui {
    private static StringBuilder capturedResponse; // Static to hold the captured StringBuilder

    /**
     * Appends the welcome message to the captured StringBuilder.
     */
    public static void showWelcome() {
        appendResponse("Hello! I'm Bob. Bob the Princess\nWhat can I do for you?\n");
    }

    /**
     * Appends the goodbye message to the captured StringBuilder.
     */
    public static void showGoodbye() {
        appendResponse("Bye. Please don't trouble me again!\n");
    }


    /**
     * Appends an error message to the captured StringBuilder.
     *
     * @param message The error message to append.
     */
    public static void showError(String message) {
        appendResponse(message + "\n");
    }


    /**
     * Appends the task list as a formatted string to the captured StringBuilder.
     *
     * @param tasks The task list to format.
     */
    public static void showTaskList(TaskList tasks) {
        if (tasks == null || tasks.isEmpty()) {
            appendResponse("You didn't tell me what to do yet\n");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Woah woah woah,\n");
        sb.append("let me get this straight... You want me to :\n");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }

        sb.append("\nI think you're asking for way too much >.<\n"); // Consider a more professional message
        appendResponse(sb.toString());
    }

    /**
     * Sets the StringBuilder to capture the UI output.
     *
     * @param response The StringBuilder to capture the output.
     */
    public static void setResponseCapture(StringBuilder response) {
        capturedResponse = response;
    }

    public void captureResponse(Runnable action, StringBuilder response) {
        capturedResponse = response;
        action.run();
        capturedResponse = null;
    }
    /**
     * Appends the given string to the captured StringBuilder if one is set.
     * @param text The text to append.
     */
    public static void appendResponse(String text) {
        if (capturedResponse != null) {
            capturedResponse.append(text);
        }
    }

    //@@author {Wang Haitao iP}-reused
    /**
     * Clears the captured response.
     */
    public static void clearResponseCapture() {
        if (capturedResponse != null) {
            capturedResponse.setLength(0); // Efficiently clear the StringBuilder
        }
    }
}
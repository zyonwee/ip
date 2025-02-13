import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bob {


    static String fullName = "Bob the Princess"; // Name of Bot
    static String greetMsg = "Hello! I'm " + fullName + "\n" +
            "What can I do for you?\n" ; // Greeting Message
    static String exitMsg =  "Bye. Please don't trouble me again!\n"; // Exit Message
    static boolean isListen = true; // Check if Bot should continue to listen for commands
    static List<Task> taskList = new ArrayList<>(); // List of user inputs

    /**
     * OnStart will display greeting message
     * On end display exit message
     */
    public static void main(String[] args) {
        System.out.println(greetMsg);
        handleCommand();
        System.out.println(exitMsg);
    }

    /**
     * Handles the user input
     */
    public static void handleCommand() {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        while (isListen && sc.hasNextLine()) {
            try {
                String command = sc.nextLine();  // Read user input

                // Use CommandType.fromString to determine the command type
                CommandType type = CommandType.fromString(command);

                switch (type) {
                    case EXIT:
                        isListen = false;
                        break;
                    case LIST:
                        listCommands();
                        break;
                    case MARK:
                        // Handle mark command
                        try {
                            int taskNumber = Integer.parseInt(command.substring(5));
                            markTaskAsDone(taskNumber);
                        } catch (NumberFormatException e) {
                            throw new BobException("Invalid task number. Please enter a valid integer.");
                        } catch (IndexOutOfBoundsException e) {
                            throw new BobException("Task does not exist. Please provide a valid task number.");
                        } catch (Exception e) {
                            throw new BobException("An unexpected error occurred while processing the task.");
                        }
                        break;
                    case UNMARK:
                        // Handle unmark command (similar to MARK)
                        try {
                            int taskNumber = Integer.parseInt(command.substring(7));
                            unmarkTaskAsDone(taskNumber);
                        } catch (NumberFormatException e) {
                            throw new BobException("Invalid task number. Please enter a valid integer.");
                        } catch (IndexOutOfBoundsException e) {
                            throw new BobException("Task does not exist. Please provide a valid task number.");
                        } catch (Exception e) {
                            throw new BobException("An unexpected error occurred while processing the task.");
                        }
                        break;
                    case TODO:
                        // Handle todo command
                        String desc = command.substring(5);
                        if (desc.isEmpty()) {
                            throw new BobException("Please write a valid description");
                        }
                        addToDo(desc);
                        break;
                    case DEADLINE:
                        // Handle deadline command
                        try {
                            String cleanString = command.substring(9);
                            int byIndex = cleanString.indexOf("/by");
                            String description = cleanString.substring(0, byIndex - 1);
                            String byString = cleanString.substring(byIndex + 3);
                            addDeadline(description, byString);
                        } catch (Exception e) {
                            System.out.println("Did you forget your '/by' command? So un-attentive!" + e.toString());
                        }
                        break;
                    case EVENT:
                        // Handle event command
                        try {
                            String cleanString = command.substring(6);
                            int fromIndex = cleanString.indexOf("/from");
                            int toIndex = cleanString.indexOf("/to");
                            String description = cleanString.substring(0, fromIndex - 1);
                            String eventFrom = cleanString.substring(fromIndex + 5, toIndex - 1);
                            String eventTo = cleanString.substring(toIndex + 3);
                            addEvent(description, eventFrom, eventTo);
                        } catch (Exception e) {
                            System.out.println("Did you forget your '/from' and '/to' command? So un-attentive!\n" +
                                    "Ps. the order matters!");
                        }
                        break;
                    case DELETE:
                        // Handle delete command (similar to MARK)
                        try {
                            int taskNumber = Integer.parseInt(command.substring(7));
                            deleteTask(taskNumber);
                        } catch (NumberFormatException e) {
                            throw new BobException("Invalid task number. Please enter a valid integer.");
                        } catch (IndexOutOfBoundsException e) {
                            throw new BobException("Task does not exist. Please provide a valid task number.");
                        } catch (Exception e) {
                            throw new BobException("An unexpected error occurred while processing the task.");
                        }
                        break;
                    case INVALID:
                        throw new BobException("What are you trying to say? You speak alien? Try English...");
                    default:
                        throw new AssertionError("Unknown command type");
                }
            } catch (BobException e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Echoes the User's Message
     */
    public static void echoMsg(String command){
        Task newTask = new Task(command);
        // Add the command to the list of Commands
        taskList.add(newTask);
        // Echo
        System.out.println("Are you sure you want me to " + command + "? " +
                "\nWhy can't you do it yourself? :3\n");
     }


    /**
     * List our the history of User's Commands in this manner:
     * <Int Index>. [<Task Status>] <Task Description>
     */
    public static void listCommands() throws BobException {
        if (taskList.isEmpty()){
            throw new BobException("You didn't tell me what to do yet");
        }
        System.out.println("Woah woah woah,\nlet me get this straight... You want me to :\n");
        int index = 1; // Index of Command
        for (Task s : taskList) {
            System.out.println(index + ". " + s.toString());
            index += 1;
        }
        System.out.println("\nI think you're asking for way to much >.<\n");
    }

    /**
     * Mark Task as done
     */
    public static void markTaskAsDone(int taskNumber){
        // User would see that taskNumber starts from index 1
        int taskIndex = taskNumber - 1;
        Task task = taskList.get(taskIndex).markAsDone();
        taskList.set(taskIndex , task);
        System.out.println("Took you long enough to complete " + task.description + "\n");
    }

    /**
     * Unmark Task as done
     */
    public static void unmarkTaskAsDone(int taskNumber){
        // User would see that taskNumber starts from index 1
        int taskIndex = taskNumber - 1;
        Task task = taskList.get(taskIndex).unmarkAsDone();
        taskList.set(taskIndex , task);
        System.out.println("You lied! You have not started to " + task.description + "\n");
    }

    /**
     * Adds Todo task to TaskList
     * @param desc of ToDo Task
     */
    public static void addToDo(String desc) throws BobException {
        Task task = new ToDo(desc);
        taskList.add(task);
        task.saveTask();
        System.out.println("No deadline? I guess we are never doing this then...");

    }

    /**
     * Adds Deadline task to TaskList
     * @param desc of ToDo Task
     * @param by any string
     */
    public static void addDeadline(String desc, String by) {
        Task task = new Deadline(desc, by);
        taskList.add(task);
        System.out.println("You better remember to do it or I'll spank you");
    }

    /**
     * Adds Event task to TaskList
     * @param desc of ToDo Task
     * @param from any string
     * @param to any string
     */
    public static void addEvent(String desc, String from, String to) {
        Task task = new Event(desc, from, to);
        taskList.add(task);
        System.out.println("Am I invited? Nah... no one cares bout this damsel");
    }

    /**
     * Removes a task from the list
     * @param taskNumber to remove (User's PoV)
     */
    public static void deleteTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        System.out.println("Fine... stop " + taskList.get(taskIndex).description + " then...");

        taskList.remove(taskIndex);
    }


}

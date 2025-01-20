import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bob {


    static String fullName = "Bob the Princess"; // Name of Bot
    static String greetMsg = "Hello! I'm " + fullName + "\n" +
            "What can I do for you?\n" ; // Greeting Message
    static String exitMsg =  "Bye. Please don't trouble me again!\n"; // Exit Message
    static boolean listenMode = true; // Check if Bot should continue to listen for commands
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
    public static void handleCommand(){

        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        while (listenMode && sc.hasNextLine()) {
            String command = sc.nextLine();  // Read user input

            // On exit command
            if (command.equals("bye")){
                listenMode = false;
            }
            // On list command
            else if (command.equals("list")){
                listCommands();
            }
            // If it starts with "mark "
            else if (command.startsWith("mark ")){

                // Try and Catch for invalid Task Number
                try {
                    int taskNumber = Integer.parseInt(command.substring(5));
                    markTaskAsDone(taskNumber);

                } catch(Exception e) {
                    System.out.println("You've gotta pick a valid task!");
                 }

            }
            else if (command.startsWith("unmark ")){
                // Try and Catch for invalid Task Number
                try {
                    int taskNumber = Integer.parseInt(command.substring(7));
                    unmarkTaskAsDone(taskNumber);

                } catch(Exception e) {
                    System.out.println("You've gotta pick a valid task!");
                }
            }
            // ToDo command
            else if (command.startsWith("todo ")) {
                addToDo(command.substring(5));
            }
            // Deadline command
            else if (command.startsWith("deadline ")) {
                try {
                    // Remove the header "deadline "
                    String cleanString = command.substring(9);

                    // Find the start index of "/by"
                    int byIndex = cleanString.indexOf("/by");

                    // Extract "description"
                    String description = cleanString.substring(0, byIndex-1);

                    // Extract "by"
                    String byString = cleanString.substring(byIndex + 3); // +3 to skip "/by "

                    addDeadline(description, byString);
                } catch (Exception e) {
                    System.out.println("Did you forget your '/by' command? So un-attentive!");
                }
            }
            // event command
            else if (command.startsWith("event ")) {
                try {
                    // Remove the header "deadline "
                    String cleanString = command.substring(6);

                    // Find the start index of "/from" and "/to"
                    int fromIndex = cleanString.indexOf("/from");
                    int toIndex = cleanString.indexOf("/to");

                    // Extract "description"
                    String description = cleanString.substring(0, fromIndex-1);

                    // Extract "from"
                    String eventFrom = cleanString.substring(fromIndex + 5, toIndex-1); // +3 to skip "/by "

                    // Extract "to"
                    String eventTo = cleanString.substring(toIndex + 3);

                    addEvent(description, eventFrom, eventTo);
                } catch (Exception e) {
                    System.out.println("Did you forget your '/from' and '/to' command? So un-attentive!\n" +
                            "Ps. the order matters!");
                }
            }
            // Echos Command
            else {
                echoMsg(command);
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
    public static void listCommands(){
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
    public static void addToDo(String desc) {
        Task task = new ToDo(desc);
        taskList.add(task);
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


}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bob {


    static String fullName = "Bob the Princess"; // Name of Bot
    static String greetMsg = "Hello! I'm " + fullName + "\n" +
            "What can I do for you? \n" ; // Greeting Message
    static String exitMsg =  "Bye. Please don't trouble me again!\n"; // Exit Message
    static boolean listenMode = true; // Check if Bot should continue to listen for commands
    static List<Task> taskList = new ArrayList<Task>(); // List of user inputs

    /**
     * OnStart will display greeting message
     * On end display exit message
     * @param args
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
        while (listenMode) {
            Scanner sc = new Scanner(System.in);  // Create a Scanner object
            String command = sc.nextLine();  // Read user input
            // On exit command
            if (command.equals("bye")){
                listenMode = false;
            }
            // On list command
            else if (command.equals("list")){
                listCommands();
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
        System.out.println("Woah woah woah, \nlet me get this straight... You want me to :\n");
        int index = 0; // Index of Command
        for (Task s : taskList) {
            System.out.println(Integer.toString(index) + ". ["+ s.getStatusIcon()+ "] " + s.description);
            index += 1;
        }
        System.out.println("\nI think you're asking for way to much >.<\n");
    }


}

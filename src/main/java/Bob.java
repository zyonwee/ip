import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bob {


    static String fullName = "Bob the Princess"; // Name of Bot
    static String greetMsg = "Hello! I'm " + fullName + "\n" +
            "What can I do for you? \n" ; // Greeting Message
    static String exitMsg =  "Bye. Please don't trouble me again!\n"; // Exit Message
    static boolean echoMode = true; // Check if Bot should continue to echo
    static List<String> commandList = new ArrayList<String>(); // List of user inputs

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
        while (echoMode) {
            Scanner sc = new Scanner(System.in);  // Create a Scanner object
            String command = sc.nextLine();  // Read user input
            // On exit command
            if (command.equals("bye")){
                echoMode = false;
            } else {

                echoMsg(command);
            }


        }
    }

    /**
     * Echoes the User's Message
     * @return boolean indicates if bot should exit echoMode
     */
    public static void echoMsg(String echo){
        // Echo
        System.out.println("Are you sure you want me to " + echo + "? " +
                "\nWhy can't you do it yourself? :3");
     }


}

import java.util.Scanner;

public class Bob {


    static String fullName = "Bob the Princess"; // Name of Bot
    static String greetMsg = "Hello! I'm " + fullName + "\n" +
            "What can I do for you? \n" ; // Greeting Message
    static String exitMsg =  "Bye. Please don't trouble me again!\n"; // Exit Message
    static boolean echoMode = true; // Check if Bot should continue to echo

    /**
     * OnStart will display greeting message
     * Thereafter, echo user's input
     * If user input bye, display exit message
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(greetMsg);
        while (echoMode) {
            echoMode = echoMsg();
        }
        System.out.println(exitMsg);
    }

    /**
     * Echoes the User's Message
     * @return boolean indicates if bot should exit echoMode
     */
    public static boolean echoMsg(){
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String echo = sc.nextLine();  // Read user
        if (echo.equals("bye")){
            return  false;
        }
        System.out.println("Are you sure you want me to " + echo + "? " +
                "\nWhy can't you do it yourself? :3");
        return true;
    }


}

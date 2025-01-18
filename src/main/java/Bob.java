import java.util.Scanner;

public class Bob {


    static String fullName = "Bob the Princess";
    static String greetMsg = "Hello! I'm " + fullName + "\n" +
            "What can I do for you? \n" ;
    static String exitMsg =  "Bye. Hope to see you again soon!\n";

    /**
     * OnStart will display greeting message
     * Thereafter, echo user's input
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(greetMsg);
        boolean shouldEcho = true;
        while (shouldEcho) {
            echoMsg();
        }
    }

    /**
     * Echoes the User's Message
     */
    public static void echoMsg(){
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String echo = sc.nextLine();  // Read user
        System.out.println("Are you sure you want me to " + echo + "? " +
                "\nWhy can't you do it yourself? :3");
    }


}

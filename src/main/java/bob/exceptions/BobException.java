package bob.exceptions;

public class BobException extends Exception {
    // Parameterless Constructor
    public BobException() {}

    // Constructor that accepts a message
    public BobException(String message)
    {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}

package bob.exceptions;

/**
 * Custom exception class for Bob application.
 */
public class BobException extends Exception {

    /**
     * Constructs a new BobException with no detail message.
     */
    public BobException() {
        super(); // Call superclass constructor
    }

    /**
     * Constructs a new BobException with the specified detail message.
     *
     * @param message The detail message.
     */
    public BobException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of this BobException.  This overrides the default behavior
     * to return just the message, rather than the class name and message.
     *
     * @return A string representation of this BobException (just the message).
     */
    @Override
    public String toString() {
        return getMessage();
    }
}
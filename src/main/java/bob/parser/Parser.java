package bob.parser;

// Importing more than 6 packages from bob.commands
import bob.commands.*;
import bob.exceptions.BobException;

/**
 * Parses user input and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the given command string and returns the corresponding Command object.
     *
     * @param fullCommand The full command string entered by the user.
     * @return The Command object corresponding to the parsed command.
     * @throws BobException If the command is invalid or cannot be parsed.
     */
    public static Command parse(String fullCommand) throws BobException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        // Consider a more professional message
        return switch (commandWord) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(arguments);
            case "unmark" -> new UnmarkCommand(arguments);
            case "todo" -> new AddTodoCommand(arguments);
            case "deadline" -> new AddDeadlineCommand(arguments);
            case "event" -> new AddEventCommand(arguments);
            case "delete" -> new DeleteCommand(arguments);
            case "find" -> new FindCommand(arguments);
            default ->
                    throw new BobException("What are you trying to say? You speak alien? Try English..."); // Consider a more professional message
        };
    }
}
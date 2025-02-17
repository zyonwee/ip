package bob.commands;

/**
 * Enum representing the different types of commands.
 */
public enum CommandType {
    /**
     * Represents the exit command.
     */
    EXIT("bye"),
    /**
     * Represents the list command.
     */
    LIST("list"),
    /**
     * Represents the mark command.
     */
    MARK("mark"),
    /**
     * Represents the unmark command.
     */
    UNMARK("unmark"),
    /**
     * Represents the todo command.
     */
    TODO("todo"),
    /**
     * Represents the deadline command.
     */
    DEADLINE("deadline"),
    /**
     * Represents the event command.
     */
    EVENT("event"),
    /**
     * Represents the delete command.
     */
    DELETE("delete"),
    /**
     * Represents an invalid command.
     */
    INVALID("invalid"),
    /**
     * Represents an find command.
     */
    FIND("find");

    private final String commandString;

    CommandType(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Returns the CommandType corresponding to the given command string.
     *
     * @param command The command string to parse.
     * @return The corresponding CommandType, or INVALID if no match is found.
     */
    public static CommandType fromString(String command) {
        String[] parts = command.split("\\s+"); // Split by whitespace
        if (parts.length > 0) {
            String firstWord = parts[0];
            for (CommandType type : CommandType.values()) {
                if (type.commandString.equals(firstWord)) {
                    return type;
                }
            }
        }
        return INVALID;
    }

    /**
     * Returns the command string associated with this CommandType.
     * @return The command string.
     */
    public String getCommandString() {
        return commandString;
    }

}
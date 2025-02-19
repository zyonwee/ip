package bob.commands;

import java.util.Arrays;

/**
 * Enum representing the different types of commands.
 */
public enum CommandType {
    EXIT("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    INVALID("invalid"),
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
        if (command == null || command.isEmpty()) {
            return INVALID;
        }

        return Arrays.stream(command.split("\\s+"))
                .findFirst()
                .map(String::trim)
                .map(firstWord -> Arrays.stream(CommandType.values())
                        .filter(type -> type.commandString.equals(firstWord))
                        .findFirst()
                        .orElse(INVALID))
                .orElse(INVALID);
    }

    /**
     * Returns the command string associated with this CommandType.
     * @return The command string.
     */
    public String getCommandString() {
        return commandString;
    }
}
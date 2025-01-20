public enum CommandType {
    EXIT("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    INVALID("invalid");

    private final String commandString;

    CommandType(String commandString) {
        this.commandString = commandString;
    }

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
}
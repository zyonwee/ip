package bob.parser;

// importing all because Parser requires over 6 packages in bob.commands
import bob.commands.*;
import bob.exceptions.BobException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseExitCommand() throws BobException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void testParseListCommand() throws BobException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testParseMarkCommand() throws BobException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testParseUnmarkCommand() throws BobException {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void testParseAddTodoCommand() throws BobException {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(AddTodoCommand.class, command);
    }

    @Test
    public void testParseAddDeadlineCommand() throws BobException {
        Command command = Parser.parse("deadline return book /by 2023-12-31");
        assertInstanceOf(AddDeadlineCommand.class, command);
    }

    @Test
    public void testParseAddEventCommand() throws BobException {
        Command command = Parser.parse("event project meeting /from 2023-10-01 /to 2023-10-02");
        assertInstanceOf(AddEventCommand.class, command);
    }

    @Test
    public void testParseDeleteCommand() throws BobException {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void testParseInvalidCommand() {
        BobException exception = assertThrows(BobException.class, () -> Parser.parse("invalid command"));
        assertEquals("What are you trying to say? You speak alien? Try English...", exception.getMessage());
    }

    @Test
    public void testParseEmptyCommand() {
        BobException exception = assertThrows(BobException.class, () -> Parser.parse(""));
        assertEquals("What are you trying to say? You speak alien? Try English...", exception.getMessage());
    }

    @Test
    public void testParseMarkCommandInvalidTaskNumber() {
        BobException exception = assertThrows(BobException.class, () -> Parser.parse("mark abc"));
        assertEquals("Invalid task number. Please enter a valid integer.", exception.getMessage());
    }

    @Test
    public void testParseUnmarkCommandInvalidTaskNumber() {
        BobException exception = assertThrows(BobException.class, () -> Parser.parse("unmark abc"));
        assertEquals("Invalid task number. Please enter a valid integer.", exception.getMessage());
    }

    @Test
    public void testParseDeleteCommandInvalidTaskNumber() {
        BobException exception = assertThrows(BobException.class, () -> Parser.parse("delete abc"));
        assertEquals("Invalid task number. Please enter a valid integer.", exception.getMessage());
    }
}
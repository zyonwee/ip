package bob.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class DateUtilsTest {

    private static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy"); // Corrected pattern

    @Test
    void processDate_validDate() {
        String input = "2024-03-15";
        String expected = LocalDate.parse(input, FILE_FORMAT).format(DISPLAY_FORMAT);
        String actual = DateUtils.processDate(input);
        assertEquals(expected, actual);
    }

    @Test
    void processDate_invalidDate() {
        String input = "2024/03/15"; // Invalid format
        String actual = DateUtils.processDate(input);
        assertEquals(input, actual); // Should return the original string
    }

    @Test
    void processDate_invalidDate_empty() {
        String input = ""; // Invalid format
        String actual = DateUtils.processDate(input);
        assertEquals(input, actual); // Should return the original string
    }


    @Test
    void processDate_invalidDate_future() {
        String input = DateUtils.processDate(LocalDate.now().plusDays(1).format(FILE_FORMAT)); // Invalid format - Future Date
        String actual = DateUtils.processDate(input);
        assertEquals(input, actual); // Should return the original string
    }

    @Test
    void processDate_nullInput() {
        assertNull(DateUtils.processDate(null));
    }

    @Test
    void processDate_whitespace() {
        String input = "  2024-03-15  ";
        String expected = LocalDate.parse(input.trim(), FILE_FORMAT).format(DISPLAY_FORMAT);
        String actual = DateUtils.processDate(input);
        assertEquals(expected, actual);
    }


    @Test
    void getFileString_validDate() {
        String input = "2024-03-15";
        String expected = "2024-03-15"; // File format should be the same
        String actual = DateUtils.getFileString(input);
        assertEquals(expected, actual);
    }

    @Test
    void getFileString_invalidDate() {
        String input = "2024/03/15"; // Invalid format
        String actual = DateUtils.getFileString(input);
        assertEquals(input, actual); // Should return the original string
    }

    @Test
    void getFileString_nullInput() {
        assertNull(DateUtils.getFileString(null));
    }

    @Test
    void getFileString_whitespace() {
        String input = "  2024-03-15  ";
        String expected = "2024-03-15";
        String actual = DateUtils.getFileString(input);
        assertEquals(expected, actual);
    }

    @Test
    void getFileString_invalidDate_empty() {
        String input = ""; // Invalid format
        String actual = DateUtils.getFileString(input);
        assertEquals(input, actual); // Should return the original string
    }


}
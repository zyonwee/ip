package bob.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for handling date formatting and parsing.
 */
public class DateUtils {
    private static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Processes a date string, attempting to parse it as a date and format it for display.
     * If parsing fails, the original string is returned.
     *
     * @param input The date string to process.
     * @return The formatted date string if parsing is successful, or the original string otherwise. Else null.
     */
    public static String processDate(String input) {
        if (input == null) {
            return null;
        }
        try {
            LocalDate date = LocalDate.parse(input.trim(), FILE_FORMAT);
            return date.format(DISPLAY_FORMAT);
        } catch (DateTimeParseException e) {
            return input;
        }
    }

    /**
     * Processes a date string, attempting to parse it as a date and format it for file storage.
     * If parsing fails, the original string is returned.
     *
     * @param input The date string to process.
     * @return The date string formatted for file storage if parsing is successful, or the original string otherwise.
     */
    public static String getFileString(String input) {
        if (input == null) {
            return null;
        }
        try {
            LocalDate date = LocalDate.parse(input.trim(), FILE_FORMAT);
            return date.format(FILE_FORMAT);
        } catch (DateTimeParseException e) {
            return input;
        }
    }
}
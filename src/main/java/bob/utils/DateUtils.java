package bob.utils;// Bob.DateUtils.java
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Takes a string that might be a date and returns either:
     * - A formatted date string if the input is a valid date
     * - The original string if it's not a valid date
     * @param input The string to process
     * @return Formatted date string or original string
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
     * Gets the file format string for a date. Returns the original string if not a valid date.
     * @param input The string to process
     * @return Date in file format or original string
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

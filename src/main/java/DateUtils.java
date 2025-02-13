import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private final static DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Checks if input is type LocalDate and returns the formatted date if valid.
     * @param s The string to check
     * @return Formatted date as a string (e.g., "Oct 15 2019") if the input is a valid LocalDate
     */
    public static LocalDate checkIsDate(String s) {
        if (s == null) {
            return null;
        }
        String res = s.trim();
        try {
            return LocalDate.parse(res, inputFormat);
        } catch (DateTimeParseException e) {
            System.out.println(e.toString() + "\n" + res);
            return null;
        }
    }

    /**
     * Formats a LocalDate to the desired output format.
     * @param date The LocalDate to format
     * @return A string representing the date in the format "MMM dd yyyy" (e.g., "Oct 15 2019")
     */
    public static String formatDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }
}

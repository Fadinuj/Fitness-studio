package gym;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for handling date and time operations.
 */
public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); // Formatter for date and time
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // Formatter for ISO date and time
    private static final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Formatter for date only
    private static final DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formatter for ISO date only

    /**
     * Formats a date from "dd-MM-yyyy HH:mm" to "yyyy-MM-dd HH:mm".
     *
     * @param date The input date string in "dd-MM-yyyy HH:mm" format.
     * @return The formatted date string in "yyyy-MM-dd HH:mm" format.
     */
    public static String formatDate(String date) {
        LocalDateTime sessionDate = LocalDateTime.parse(date, formatter);
        return sessionDate.format(formatter2);
    }

    /**
     * Formats a date from "dd-MM-yyyy" to "yyyy-MM-dd".
     *
     * @param date The input date string in "dd-MM-yyyy" format.
     * @return The formatted date string in "yyyy-MM-dd" format.
     */
    public static String formatDate1(String date) {
        LocalDate sessionDate = LocalDate.parse(date, formatter1);
        return sessionDate.format(formatter3);
    }

    /**
     * Checks if a given date is expired (i.e., in the past).
     *
     * @param date1 The input date string in "dd-MM-yyyy HH:mm" format.
     * @return True if the date is in the past, false otherwise.
     * @throws IllegalArgumentException if the date format is invalid.
     */
    public static boolean isExpiredDate(String date1) {
        try {
            LocalDateTime sessionDate = LocalDateTime.parse(date1, formatter);
            return sessionDate.isBefore(LocalDateTime.now());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: dd-MM-yyyy HH:mm");
        }
    }

    /**
     * Converts a date from "dd-MM-yyyy HH:mm" to "yyyy-MM-dd HH:mm".
     *
     * @param date The input date string in "dd-MM-yyyy HH:mm" format.
     * @return The formatted date string in "yyyy-MM-dd HH:mm" format.
     */
    public String dateStartWithYear(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
        return dateTime.format(outputFormatter);
    }

    /**
     * Calculates the age of a person based on their birth date.
     *
     * @param birthDate The birth date string in "dd-MM-yyyy" format.
     * @return The age in years.
     */
    public static int getAge(String birthDate) {
        String cleanedDate = birthDate.trim();
        LocalDate birth = LocalDate.parse(cleanedDate, formatter1);
        LocalDate today = LocalDate.now();
        return Period.between(birth, today).getYears();
    }
}

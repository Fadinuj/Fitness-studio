package gym;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Format a date into a readable string
    public static String formatDate(String date) {
        LocalDateTime sessionDate = LocalDateTime.parse(date, formatter);
        return sessionDate.format(formatter2);
    }

    public static String formatDate1(String date) {
        // Parse the date as LocalDate since it doesn't contain time
        LocalDate sessionDate = LocalDate.parse(date, formatter1);
        // Format the date to the desired format
        return sessionDate.format(formatter3);
    }

    // Check if a given date is expired (in the past)
    public static boolean isExpiredDate(String date1) {
        try {
            LocalDateTime sessionDate = LocalDateTime.parse(date1, formatter);
            return sessionDate.isBefore(LocalDateTime.now());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: dd-MM-yyyy HH:mm");
        }
    }
    public String dateStartWithYear(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
        return dateTime.format(outputFormatter);
    }


    public static int getAge(String birthDate) {
        // Trim the input string to remove leading/trailing spaces
        String cleanedDate = birthDate.trim();
        LocalDate birth = LocalDate.parse(cleanedDate, formatter1);
        LocalDate today = LocalDate.now();
        return Period.between(birth, today).getYears();
    }
}

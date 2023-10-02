package fa.training.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validation {
    public static boolean isValidName(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                System.out.println("Name only contains letter and spaces");
                return false;
            }
        }
        return true;
    }

    public static LocalDate validDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Date is wrong format " + format);
            return null;
        }
    }
}

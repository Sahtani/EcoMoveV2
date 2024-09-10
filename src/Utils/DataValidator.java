package Utils;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.LocalDate;

public class DataValidator {

    public static boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email != null && !email.trim().isEmpty() && pattern.matcher(email).matches();
    }

    public static boolean validatePhoneNumber(int phoneNumber) {
          String phoneString = String.valueOf(phoneNumber);
        return phoneString.length() == 10;
    }

    public static LocalDate promptForValidDate(String prompt, Scanner scanner) {
        LocalDate date = null;
        boolean isValid = false;
        do {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().strip();
                if (input.isEmpty()) {
                    return null;
                }
                date = LocalDate.parse(input);
                isValid = true;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        } while (!isValid);
        return date;
    }

    public static boolean validateDate(LocalDate startDate, LocalDate endDate) {
        if (endDate == null) {
            return true;
        }
        return startDate.isBefore(endDate);
    }
}

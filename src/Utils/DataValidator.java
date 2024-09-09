package Utils;

import java.util.regex.Pattern;

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
}

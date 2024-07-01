package model.unbosque.edu.co;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+{}[]|:;'<>,.?/";

    public static String generatePassword(int length, boolean includeUppercase, boolean includeLowercase, boolean includeDigits, boolean includeSpecial) {
        StringBuilder password = new StringBuilder(length);
        StringBuilder charPool = new StringBuilder();

        SecureRandom random = new SecureRandom();

        if (includeUppercase) {
            charPool.append(UPPER);
        }
        if (includeLowercase) {
            charPool.append(LOWER);
        }
        if (includeDigits) {
            charPool.append(DIGITS);
        }
        if (includeSpecial) {
            charPool.append(SPECIAL);
        }

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }

        return password.toString();
    }
}

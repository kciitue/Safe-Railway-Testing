package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Utilities {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final String ALL_CHARS = UPPER + LOWER + DIGITS + SPECIAL;
    private static final String[] DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com"};
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomEmail(int userLength) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Generate random username
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < userLength; i++) {
            username.append(chars.charAt(random.nextInt(chars.length())));
        }

        // Choose random domain
        String domain = DOMAINS[random.nextInt(DOMAINS.length)];

        return username.toString() + "@" + domain;
    }

    public static String generateRandomPassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Độ dài mật khẩu phải từ 8 ký tự trở lên.");
        }

        StringBuilder password = new StringBuilder(length);

        // Đảm bảo có ít nhất một ký tự từ mỗi nhóm
        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        // Các ký tự còn lại chọn ngẫu nhiên từ ALL_CHARS
        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        return password.toString();
    }

    public static String generateRandomAlphanumericPID(int length) {
        if (length < 8) {
            return null;
        }

        String chars = DIGITS;
        StringBuilder pid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            pid.append(chars.charAt(random.nextInt(chars.length())));
        }

        return pid.toString();
    }

    public static String getDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        return today.format(formatter);
    }
}

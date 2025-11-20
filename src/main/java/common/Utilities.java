package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    private static final String EMAIL_CHARS = UPPER + LOWER + DIGITS;
    private static final String[] DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com"};
    private static final SecureRandom random = new SecureRandom();


    private static int genRandomLength(int min, int max){

        return random.nextInt(max-min + 1) + min;
    }

    public static String generateRandomEmail() {
        String domain = DOMAINS[random.nextInt(DOMAINS.length)];

        int maxLength = 32 - domain.length() - 1;
        int minLength = 6;
        int userLength = genRandomLength(minLength, maxLength);

        StringBuilder username = new StringBuilder();
        for (int i = 0; i < userLength; i++) {
            username.append(EMAIL_CHARS.charAt(random.nextInt(EMAIL_CHARS.length())));
        }


        return username.toString() + "@" + domain;
    }

    public static String generateRandomPassword() {
        int length = genRandomLength(8, 64);

        StringBuilder password = new StringBuilder(length);

        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        return password.toString();
    }

    public static String generateRandomPID() {
        int length = genRandomLength(9, 20);

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

    public static void scrollToCenter() {
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
    }
}

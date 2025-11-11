package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utilities {
    public static String getDisposableEmail(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.disposablemail.com/");

        // Dùng XPath để lấy phần tử email
        By emailLocator = By.xpath("//*[@id='email']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailLocator));

        WebElement emailElement = driver.findElement(emailLocator);
        return emailElement.getText();
    }
}

package pageObjects.Railway;

import common.Constant;
import common.Utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends GeneralPage {
    //Locators
    private final By txtEmail = By.xpath("//input[@id='email']");
    private final By txtPassword = By.xpath("//input[@id='password']");
    private final By txtConfirmPw = By.xpath("//input[@id='confirmPassword']");
    private final By txtPID = By.xpath("//input[@id='pid']");
    private final By btnRegister = By.xpath("//input[@type='submit']");
    private final By lblRegisterErrorMsg = By.xpath("//p[@class='message error']");
    private final By lblSuccessfullyMsg = By.xpath("//div[@id='content']/p");
    private final By lblErrorPwMsg = By.xpath("//label[@for='password' and @class='validation-error']");
    private final By lblErrorPidMsg = By.xpath("//label[@for='pid' and @class='validation-error']");

    private final WebDriver driver = Constant.WEBDRIVER;
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    //Elements
    public WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(txtEmail);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(txtPassword);
    }

    public WebElement getTxtConfirmPw() {
        return Constant.WEBDRIVER.findElement(txtConfirmPw);
    }

    public WebElement getTxtPID() {
        return Constant.WEBDRIVER.findElement(txtPID);
    }

    public WebElement getBtnRegister() {
        return Constant.WEBDRIVER.findElement(btnRegister);
    }

    public WebElement getLblRegisterErrorMsg() {
        return Constant.WEBDRIVER.findElement(lblRegisterErrorMsg);
    }

    public WebElement getLblSuccessfullyMsg() {
        return Constant.WEBDRIVER.findElement(lblSuccessfullyMsg);
    }

    public WebElement getLblErrorPwMsg() {
        return Constant.WEBDRIVER.findElement(lblErrorPwMsg);
    }

    public WebElement getLblErrorPidMsg() {
        return Constant.WEBDRIVER.findElement(lblErrorPidMsg);
    }

    //Methods
    private WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }



    private void type(By locator, String value) {
        WebElement el = waitForVisible(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'}); arguments[0].focus();", el);
        checkAlert();
        el.clear();
        el.sendKeys(value);
    }

    public RegisterPage register(String username, String password, String confirmpw, String pid) {
        // Khởi tạo wait
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;

        // Nhập email
        WebElement emailField = this.getTxtEmail();
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(username);

        // Nhập password
        WebElement passwordField = this.getTxtPassword();
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);


        // Scroll đến Confirm Password field và wait
        WebElement confirmPwField = this.getTxtConfirmPw();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", confirmPwField);

        // Wait cho element visible và enabled
        wait.until(ExpectedConditions.visibilityOf(confirmPwField));
        wait.until(ExpectedConditions.elementToBeClickable(confirmPwField));

        // Click vào field trước để focus (quan trọng!)
        try {
            confirmPwField.click();
            Thread.sleep(300); // Đợi một chút sau khi click
        } catch (Exception e) {
            // Nếu click thất bại, dùng JavaScript
            js.executeScript("arguments[0].click();", confirmPwField);
        }

        // Nhập confirm password bằng JavaScript (an toàn hơn)
        js.executeScript("arguments[0].value = arguments[1];", confirmPwField, confirmpw);

        // Scroll đến PID field và wait
        WebElement pidField = this.getTxtPID();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", pidField);
        wait.until(ExpectedConditions.visibilityOf(pidField));
        wait.until(ExpectedConditions.elementToBeClickable(pidField));

        // Click và nhập PID
        try {
            pidField.click();
            Thread.sleep(300);
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", pidField);
        }
        js.executeScript("arguments[0].value = arguments[1];", pidField, pid);

        // Scroll đến button Register và click
        WebElement registerBtn = this.getBtnRegister();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", registerBtn);
        wait.until(ExpectedConditions.elementToBeClickable(registerBtn));

        // Click button
        try {
            registerBtn.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", registerBtn);
        }

        return new RegisterPage();
    }

    private void checkAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {}
    }

    public String getSuccessfullyMsg() {
        return this.getLblSuccessfullyMsg().getText();
    }

    public String getRegisterErrorMsg() {
        return this.getLblRegisterErrorMsg().getText();
    }

    public String getErrorPwMsg() {
        return this.getLblErrorPwMsg().getText();
    }

    public String getErrorPidMsg() {
        return this.getLblErrorPidMsg().getText();
    }
}

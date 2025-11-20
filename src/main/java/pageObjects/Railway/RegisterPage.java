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
    public RegisterPage register(String email, String password, String confirmPass, String pid) {

        type(txtEmail, email);
        type(txtPassword, password);
        type(txtConfirmPw, confirmPass);
        type(txtPID, pid);

        WebElement btn = waitForVisible(btnRegister);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        btn.click();

        return this;
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

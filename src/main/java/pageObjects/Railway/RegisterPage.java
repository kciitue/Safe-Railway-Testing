package pageObjects.Railway;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.charset.StandardCharsets;

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
    public RegisterPage register(String username, String password, String confirmpw, String pid) {
        this.getTxtEmail().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getTxtConfirmPw().sendKeys(confirmpw);
        this.getTxtPID().sendKeys(pid);
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.scrollBy(0,400)");
        this.getBtnRegister().click();

        return new RegisterPage();
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

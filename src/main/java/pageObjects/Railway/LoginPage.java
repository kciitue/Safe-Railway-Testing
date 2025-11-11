package pageObjects.Railway;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends GeneralPage{
    //Locators
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='Login']");
    private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final  By hlForgotPw = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");
    //Elements
    public WebElement getTxtUsername() {
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }

    public WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }

    public WebElement getLblLoginErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
    }

    public WebElement getHlForgotPw() {
        return Constant.WEBDRIVER.findElement(hlForgotPw);
    }
    //Methods
    public HomePage login(String username, String password) {
        //Submit login credentials
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();

        //Land on Home page
        return new HomePage();
    }

    public String getErrorMessage() {
        return this.getLblLoginErrorMsg().getText();
    }

    public ForgotPwPage gotoForgotPwPage() {
        this.getHlForgotPw().click();
        return new ForgotPwPage();
    }
}

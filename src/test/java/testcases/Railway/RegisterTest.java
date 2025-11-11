package testcases.Railway;

import common.Constant;
import common.Utilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import pageObjects.Railway.*;

public class RegisterTest {
    String email;
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");

        WebDriver tempDriver = new ChromeDriver();
        tempDriver.manage().window().maximize();
        String email = Utilities.getDisposableEmail(tempDriver);
        tempDriver.quit();

        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
        Constant.WEBDRIVER.get(Constant.RAILWAY_URL);

        this.email = email;
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");

        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC07() {
        System.out.println("TC07 - User can create new account");
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        String actualMsg = registerPage.register(email, Constant.PASSWORD, Constant.PASSWORD,"4628522948").getSuccessfullyMsg();
        String expectedMsg = "Thank you for registering your account";

        Assert.assertEquals(actualMsg, expectedMsg, "Message should be displayed as expected");
    }

    @Test
    public void TC08() {
        System.out.println("TC08 - User can't login with an account hasn't been activated");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        GeneralPage loginResult = loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        String actualMsg = loginResult.getErrorMessage();
        String expectedMsg = "Invalid username or password. Please try again.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message should be displayed as expected");
    }

    @Test
    public void TC09() {
        System.out.println("TC09 - User can change password");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        HomePage loggedPage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        ChangePasswordPage changePasswordPage = loggedPage.gotoChangePasswordPage();

        changePasswordPage.changePassword(Constant.PASSWORD, "06062000");
        String actualMsg = changePasswordPage.getSuccessMsg();
        String expectedMsg = "Your password has been updated";

        Assert.assertEquals(actualMsg, expectedMsg, "Message should be displayed as expected");
    }

    @Test
    public void TC10() {
        System.out.println("TC10 - User can't create account with 'Confirm password' is not the same with 'Password'");
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        RegisterPage regResult = registerPage.register("harding.jhostin@dunefee.com", "17062019", "12345678","1536223823");
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.scrollBy(0,300)");

        String actualMsg = regResult.getRegisterErrorMsg();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message should be displayed as expected");
    }

    @Test
    public void TC11() {
        System.out.println("TC11 - User can't create account while password and PID fields are empty");
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        RegisterPage regResult = registerPage.register(email, "", "","");
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.scrollBy(0,500)");

        String actualRegMsg = regResult.getRegisterErrorMsg();
        String expectedRegMsg = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualRegMsg, expectedRegMsg, "Error message should be displayed as expected");

        String actualPwMsg = regResult.getErrorPwMsg();
        String expectedPwMsg = "Invalid password length.";
        Assert.assertEquals(actualPwMsg, expectedPwMsg, "Error message should be displayed as expected");

        String actualPidMsg = regResult.getErrorPidMsg();
        String expectedPidMsg = "Invalid ID length.";
        Assert.assertEquals(actualPidMsg, expectedPidMsg, "Error message should be displayed as expected");
    }

//    @Test
//    public void TC12() {
//        System.out.println("TC12 - Errors display when password reset token is blank");
//        HomePage homePage = new HomePage();
//        homePage.open();
//
//        LoginPage loginPage = homePage.gotoLoginPage();
//
//
//
//    }

//    @Test
//    public void TC13() {
//
//    }

    @Test
    public void TC14() {
        System.out.println("TC14 - User can book 1 ticket at a time");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        HomePage loggedPage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        BookTicketPage bookTicketPage = loggedPage.gotoBookTicketPage();

    }
}

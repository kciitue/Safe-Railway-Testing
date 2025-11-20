package testcases.Railway;

import common.Constant;
import common.Utilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import pageObjects.Railway.*;

public class RegisterTests {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
        Constant.WEBDRIVER.get(Constant.RAILWAY_URL);
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
        Utilities.scrollToCenter();

        String email = Utilities.generateRandomEmail();
        String password = Utilities.generateRandomPassword();
        String pid = Utilities.generateRandomPID();

        String actualMsg = registerPage.register(email, password, password,pid).getSuccessfullyMsg();
        String expectedMsg = "Thank you for registering your account";

        Assert.assertEquals(actualMsg, expectedMsg, "Message should be displayed as expected");
    }

    @Test
    public void TC08() {
        System.out.println("TC08 - User can't login with an account hasn't been activated");
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        Utilities.scrollToCenter();

        String email = Utilities.generateRandomEmail();
        String password = Utilities.generateRandomPassword();
        String pid = Utilities.generateRandomPID();

        RegisterPage resultPage = registerPage.register(email, password, password, pid);

        LoginPage loginPage = resultPage.gotoLoginPage();
        Utilities.scrollToCenter();

        HomePage loginResult = loginPage.login(email, password);

        String actualMsg = loginResult.getErrorMessage();
        String expectedMsg = "Invalid username or password. Please try again.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message should be displayed as expected");
    }

    @Test
    public void TC09() {
        System.out.println("TC09 - User can change password");
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        Utilities.scrollToCenter();

        String email = Utilities.generateRandomEmail();
        String password = Utilities.generateRandomPassword();
        String pid = Utilities.generateRandomPID();

        RegisterPage resultPage = registerPage.register(email, password, password, pid);

        LoginPage loginPage = resultPage.gotoLoginPage();
        Utilities.scrollToCenter();

        HomePage loggedPage = loginPage.login(email, password);
        String newPassword = Utilities.generateRandomPassword();

        ChangePasswordPage changePasswordPage = loggedPage.gotoChangePasswordPage();

        changePasswordPage.changePassword(password, newPassword);

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
        Utilities.scrollToCenter();

        String email = Utilities.generateRandomEmail();
        String password = Utilities.generateRandomPassword();
        String pid = Utilities.generateRandomPID();
        String confirmPassword = Utilities.generateRandomPassword();

        RegisterPage regResult = registerPage.register(email, password, confirmPassword,pid);

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
        Utilities.scrollToCenter();

        String email = Utilities.generateRandomEmail();

        RegisterPage regResult = registerPage.register(email, "", "","");

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

    @Test
    public void TC12() {
        System.out.println("TC12 - Errors display when password reset token is blank");
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        Utilities.scrollToCenter();

        String email = Utilities.generateRandomEmail();
        String password = Utilities.generateRandomPassword();
        String pid = Utilities.generateRandomPID();

        RegisterPage resultPage = registerPage.register(email, password, password, pid);

        LoginPage loginPage = resultPage.gotoLoginPage();
        Utilities.scrollToCenter();

        ForgotPwPage forgotPwPage = loginPage.gotoForgotPwPage();
        Utilities.scrollToCenter();

        forgotPwPage.gotoPwChangeForm();

        Assert.assertTrue(false,"Error page displays after click 'Send Instructions' button");
    }

    @Test
    public void TC13() {
        System.out.println("TC13 - Errors display if password and confirm password don't match when resetting password");
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        Utilities.scrollToCenter();

        String email = Utilities.generateRandomEmail();
        String password = Utilities.generateRandomPassword();
        String pid = Utilities.generateRandomPID();

        RegisterPage resultPage = registerPage.register(email, password, password, pid);

        LoginPage loginPage = resultPage.gotoLoginPage();
        Utilities.scrollToCenter();

        ForgotPwPage forgotPwPage = loginPage.gotoForgotPwPage();
        Utilities.scrollToCenter();

        forgotPwPage.gotoPwChangeForm();

        Assert.assertTrue(false,"Error page displays after click 'Send Instructions' button");
    }
}

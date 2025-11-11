package testcases.Railway;

import common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import pageObjects.Railway.*;

public class LoginTest {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");

        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");

        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;

        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - User can't login with blank Username textbox");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        GeneralPage loginResult = loginPage.login("", Constant.PASSWORD);
        String actualMsg = loginResult.getErrorMessage();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC03() {
        System.out.println("TC03 - User cannot log into Railway with invalid password");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        GeneralPage loginResult = loginPage.login(Constant.USERNAME, "123456780");
        String actualMsg = loginResult.getErrorMessage();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC04() {
        System.out.println("TC04 - Login page displays when un-logged User clicks on Book ticket tab");
        HomePage homePage = new HomePage();
        homePage.open();

        GeneralPage resultPage = homePage.gotoBookTicketUnlogged();

        Assert.assertNotNull(resultPage, "User should be redirected to LoginPage");
    }

    @Test
    public void TC05() {
        System.out.println("TC05 - System shows message when user enters wrong password several times");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        for (int i = 1; i <= 4; i++) {
            System.out.println("Lần đăng nhập thứ " + i);
            loginPage.login(Constant.USERNAME, "123456780");
        }

        String actualMsg = loginPage.getErrorMessage();
        String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC06() {
        System.out.println("TC06 - Additional pages display once user logged in");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        HomePage loggedPage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        //Check tab
        Assert.assertTrue(loggedPage.isMyTicketTabDisplayed(), "My Ticket tab should be displayed");
        Assert.assertTrue(loggedPage.isChangePasswordTabDisplayed(), "Change Password tab should be displayed");
        Assert.assertTrue(loggedPage.isLogoutTabDisplayed(), "Logout tab should be displayed");

        MyTicketPage myTicketPage = loggedPage.gotoMyTicketPage();
        Assert.assertTrue(myTicketPage.isMyTicketPageDisplayed(), "User should be redirected to My Ticket page");

        ChangePasswordPage changePasswordPage = loggedPage.gotoChangePasswordPage();
        Assert.assertTrue(changePasswordPage.isChangePwPageDiplayed(), "User should be redirected to Change Password page");
    }

}

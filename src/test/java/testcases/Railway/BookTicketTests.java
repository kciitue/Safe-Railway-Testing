package testcases.Railway;

import common.Constant;
import common.Utilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.Railway.*;

public class BookTicketTests {
    String email;
    String password;
    String pid;
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");

        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
        Constant.WEBDRIVER.get(Constant.RAILWAY_URL);

        String email = Utilities.generateRandomEmail();
        String password = Utilities.generateRandomPassword();
        String pid = Utilities.generateRandomPID();

        this.email = email;
        this.password = password;
        this.pid = pid;
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");

        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC14() {
        System.out.println("TC14 - User can book 1 ticket at a time");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        Utilities.scrollToCenter();

        HomePage loggedPage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        TicketPricePage ticketPricePage = loggedPage.gotoTicketPricePage();
        Utilities.scrollToCenter();

        ticketPricePage.clickCheckPrice("Sài Gòn", "Sài Gòn to Nha Trang");
        int ticketPrice = ticketPricePage.getPriceByDescription("Soft bed with air conditioner");

        BookTicketPage bookTicketPage = ticketPricePage.gotoBookTicketPage();

        BookTicketPage bookSuccessfully = bookTicketPage.bookTicket();
        Utilities.scrollToCenter();

        int totalPrice = bookSuccessfully.calculateTotalPrice(ticketPrice, 1);

        String actualMsg = bookSuccessfully.getBookSuccessMsg();
        String expectedMsg = "Ticket booked successfully!";

        String actualDepartStation = bookSuccessfully.getCellValue(Constant.WEBDRIVER, BookTicketPage.TicketColumn.departStation.getHeaderText());
        String expectedDepartStation = "Sài Gòn";

        String actualArriveStation = bookSuccessfully.getCellValue(Constant.WEBDRIVER, BookTicketPage.TicketColumn.arriveStation.getHeaderText());
        String expectedArriveStation = "Nha Trang";

        String actualSeatType = bookSuccessfully.getCellValue(Constant.WEBDRIVER, BookTicketPage.TicketColumn.seatType.getHeaderText());
        String expectedSeatType = "Soft bed with air conditioner";

        String actualDepartDate = bookSuccessfully.getCellValue(Constant.WEBDRIVER, BookTicketPage.TicketColumn.departDate.getHeaderText());
        String expectedDepartDate = bookSuccessfully.expectedDepartDate(7);

        String actualBookDate = bookSuccessfully.getCellValue(Constant.WEBDRIVER, BookTicketPage.TicketColumn.bookDate.getHeaderText());
        String expectedBookDate = Utilities.getDate();

        String actualExpiredDate = bookSuccessfully.getCellValue(Constant.WEBDRIVER, BookTicketPage.TicketColumn.expiredDate.getHeaderText());
        String expectedExpiredDate = bookSuccessfully.expectedExpiredDate();

        String actualAmount = bookSuccessfully.getCellValue(Constant.WEBDRIVER, BookTicketPage.TicketColumn.ticketAmount.getHeaderText());
        String expectedAmount = "1";

        String actualTotalPrice = bookSuccessfully.getCellValue(Constant.WEBDRIVER, BookTicketPage.TicketColumn.totalPrice.getHeaderText());
        String expectedTotalPrice = Integer.toString(totalPrice);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualMsg, expectedMsg, "Wrong message");
        softAssert.assertEquals(actualDepartStation, expectedDepartStation, "Depart Station mismatch!");
        softAssert.assertEquals(actualArriveStation, expectedArriveStation, "Arrive Station mismatch!");
        softAssert.assertEquals(actualSeatType, expectedSeatType, "Seat Type mismatch!");
        softAssert.assertEquals(actualDepartDate, expectedDepartDate, "Total Price mismatch!");
        softAssert.assertEquals(actualBookDate, expectedBookDate, "Book Date mismatch!");
        softAssert.assertEquals(actualExpiredDate, expectedExpiredDate, "Expired Date mismatch");
        softAssert.assertEquals(actualAmount, expectedAmount, "Amount mismatch!");
        softAssert.assertEquals(actualTotalPrice, expectedTotalPrice, "Price mismatch!");

        softAssert.assertAll();
    }

    @Test
    public void TC15() {
        System.out.println("TC16 - User can cancel a ticket");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        Utilities.scrollToCenter();

        HomePage loggedPage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        TimetablePage timetablePage = loggedPage.gotoTimetablePage();
        BookTicketPage bookTicketPage = timetablePage.clickBookTicket(TimetablePage.Station.HUE, TimetablePage.Station.SAI_GON);

        String actualDepartStation = bookTicketPage.getSelectedText(bookTicketPage.getSelectedDepartSt());
        String expectedDepartStation = TimetablePage.Station.HUE.getName();

        String actualArriveStation = bookTicketPage.getSelectedText(bookTicketPage.getSelectedArriveSt());
        String expectedArriveStation = TimetablePage.Station.SAI_GON.getName();

        Assert.assertEquals(actualDepartStation, expectedDepartStation, "Station mismatch!");
        Assert.assertEquals(actualArriveStation, expectedArriveStation, "Station mismatch!");
    }

    @Test
    public void TC16() {
        System.out.println("TC16 - User can cancel a ticket");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        Utilities.scrollToCenter();

        HomePage loggedPage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        BookTicketPage bookTicketPage = loggedPage.gotoBookTicketPage();
        bookTicketPage.bookTicket();
        String ticketID = bookTicketPage.getID();

        MyTicketPage myTicketPage = bookTicketPage.gotoMyTicketPage();
        Utilities.scrollToCenter();

        myTicketPage.cancelTicket(ticketID);

        myTicketPage.isTicketVisible(ticketID);
    }
}

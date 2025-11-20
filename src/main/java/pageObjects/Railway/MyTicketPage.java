package pageObjects.Railway;

import common.Constant;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MyTicketPage extends GeneralPage {
    //Locators
    private final By headerManageTicket = By.xpath("//*[@id='content']/h1");


    //Elements
    public WebElement getHeaderManageTicket() {
        return Constant.WEBDRIVER.findElement(headerManageTicket);
    }

    //Methods
    public boolean isMyTicketPageDisplayed() {
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.scrollBy(0,100)");
        return getHeaderManageTicket().isDisplayed();
    }

    public MyTicketPage cancelTicket(String id) {
        String xpath = String.format("//input[@onclick='DeleteTicket(%s);']", id);
        WebElement cancelButton = Constant.WEBDRIVER.findElement(By.xpath(xpath));
        cancelButton.click();

        Alert alert = Constant.WEBDRIVER.switchTo().alert();
        alert.accept(); // Nhấn OK

        return new MyTicketPage();
    }

    public boolean isTicketVisible(String ticketId) {
        String xpath = String.format("//input[@type='button' and contains(@onclick,'DeleteTicket(%s)')]", ticketId);
        return !Constant.WEBDRIVER.findElements(By.xpath(xpath)).isEmpty();
    }
}

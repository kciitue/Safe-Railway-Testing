package pageObjects.Railway;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyTicketPage extends GeneralPage {
    //Locators
    private final By headerManageTicket = By.xpath("//h1[text()='Manage ticket']");

    //Elements
    public WebElement getHeaderManageTicket() {
        return Constant.WEBDRIVER.findElement(headerManageTicket);
    }

    //Methods
    public boolean isMyTicketPageDisplayed() {
        return getHeaderManageTicket().isDisplayed();
    }
}

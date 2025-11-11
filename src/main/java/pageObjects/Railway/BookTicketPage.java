package pageObjects.Railway;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BookTicketPage extends GeneralPage {
    //Locators
    private final By ddDepartDate = By.xpath("//select[@name='Date']");
    private final By ddDepartFrom = By.xpath("//*[@id='content']/div[1]/form/fieldset/ol/li[2]/select");
    private final By ddArriveAt = By.xpath("//*[@id='ArriveStation']/select");
    private final By ddSeatType = By.xpath("//*[@id='content']/div[1]/form/fieldset/ol/li[4]/select");
    private final By ddTicketAmount = By.xpath("//*[@id='content']/div[1]/form/fieldset/ol/li[5]/select");
    private final By btnBookTicket = By.xpath("//*[@id='content']/div[1]/form/fieldset/input");

    //Elements
    public WebElement getDdDepartDate() {
        return Constant.WEBDRIVER.findElement(ddDepartDate);
    }

    public WebElement getDdDepartFrom() {
        return Constant.WEBDRIVER.findElement(ddDepartFrom);
    }

    public WebElement getDdArriveAt() {
        return Constant.WEBDRIVER.findElement(ddArriveAt);
    }

    public WebElement getDdSeatType() {
        return Constant.WEBDRIVER.findElement(ddSeatType);
    }

    public WebElement getDdTicketAmount() {
        return Constant.WEBDRIVER.findElement(ddTicketAmount);
    }

    public WebElement getBtnBookTicket() {
        return Constant.WEBDRIVER.findElement(btnBookTicket);
    }

    //Methods
    public String getDepartFrom() {
        return this.getDdDepartFrom().getText();
    }

    public String getArriveAt() {
        return this.getDdArriveAt().getText();
    }

    public String getSeatType() {
        return this.getDdSeatType().getText();
    }

    public String getTicketAmount() {
        return  this.getDdTicketAmount().getText();
    }
}

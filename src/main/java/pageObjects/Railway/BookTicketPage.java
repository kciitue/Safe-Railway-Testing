package pageObjects.Railway;

import common.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookTicketPage extends GeneralPage {
    //Locators
    private final By cbDepartDate = By.xpath("//select[@name='Date']");
    private final By cbDepartFrom = By.xpath("//*[@id='content']/div[1]/form/fieldset/ol/li[2]/select");
    private final By cbArriveAt = By.xpath("//*[@id='ArriveStation']/select");
    private final By cbSeatType = By.xpath("//*[@id='content']/div[1]/form/fieldset/ol/li[4]/select");
    private final By cbTicketAmount = By.xpath("//select[@name='TicketAmount']");
    private final By btnBookTicket = By.xpath("//*[@id='content']/div[1]/form/fieldset/input");
    private final By headerBookSuccessMsg = By.xpath("//*[@id='content']/h1");
    private final By selectedDepartStation = By.xpath("//select[@name='DepartStation']");
    private final By selectedArriveStation = By.xpath("//select[@name='ArriveStation']");
    //Elements
    public WebElement getCbDepartDate() {
        return Constant.WEBDRIVER.findElement(cbDepartDate);
    }

    public WebElement getCbDepartFrom() {
        return Constant.WEBDRIVER.findElement(cbDepartFrom);
    }

    public WebElement getCbArriveAt() {
        return Constant.WEBDRIVER.findElement(cbArriveAt);
    }

    public WebElement getCbSeatType() {
        return Constant.WEBDRIVER.findElement(cbSeatType);
    }

    public WebElement getCbTicketAmount() {
        return Constant.WEBDRIVER.findElement(cbTicketAmount);
    }

    public WebElement getBtnBookTicket() {
        return Constant.WEBDRIVER.findElement(btnBookTicket);
    }

    public WebElement getHeaderBookSuccessMsg() {
        return Constant.WEBDRIVER.findElement(headerBookSuccessMsg);
    }

    public WebElement getSelectedDepartSt() {
        return Constant.WEBDRIVER.findElement(selectedDepartStation);
    }

    public WebElement getSelectedArriveSt() {
        return Constant.WEBDRIVER.findElement(selectedArriveStation);
    }

    //Methods
    public String selectDepartFrom(String station) {
        Select select = new Select(getCbDepartFrom());
        select.selectByVisibleText(station);
        return select.getFirstSelectedOption().getText();
    }

    public String selectArriveAt(String station) {
        Select select = new Select(getCbArriveAt());
        select.selectByVisibleText(station);
        return select.getFirstSelectedOption().getText();
    }

    public String selectSeatType(String type) {
        Select select = new Select(getCbSeatType());
        select.selectByVisibleText(type);
        return select.getFirstSelectedOption().getText();
    }

    public String selectTicketAmount(String amount) {
        Select select = new Select(getCbTicketAmount());
        select.selectByVisibleText(amount);
        return select.getFirstSelectedOption().getText();
    }

    public String selectDepartDate(int offsetDays) {
        LocalDate today = LocalDate.now();
        LocalDate targetDate = today.plusDays(offsetDays);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        String dateToSelect = targetDate.format(formatter);

        Select select = new Select(getCbDepartDate());

        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {
            if (option.getText().equals(dateToSelect)) {
                option.click();
                return option.getText();
            }
        }
        return null;
    }

    public String expectedExpiredDate() {
        LocalDate today = LocalDate.now();
        String expectedExpiredDate = today.plusDays(3)
                .format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
        return expectedExpiredDate;
    }

    public String expectedDepartDate(int offsetDay) {
        LocalDate today = LocalDate.now();
        LocalDate departDate= today.plusDays(offsetDay);
        String expectedDepartDate = departDate.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
        return expectedDepartDate;
    }

    public BookTicketPage bookTicket() {
        this.selectDepartDate(7);
        this.selectDepartFrom("Sài Gòn");
        this.selectArriveAt("Nha Trang");
        this.selectSeatType("Soft bed with air conditioner");
        this.selectTicketAmount("1");
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.scrollBy(0,100)");
        this.getBtnBookTicket().click();

        return new BookTicketPage();
    }

    public String getBookSuccessMsg() {
        return this.getHeaderBookSuccessMsg().getText();
    }

    //Enum
    public enum TicketColumn {
        departStation("Depart Station"),
        arriveStation("Arrive Station"),
        seatType("Seat Type"),
        departDate("Depart Date"),
        bookDate("Book Date"),
        expiredDate("Expired Date"),
        ticketAmount("Amount"),
        totalPrice("Total Price");

        private final String headerText;

        TicketColumn(String headerText) {
            this.headerText = headerText;
        }

        public String getHeaderText() {
            return headerText;
        }
    }

    public int getColumnIndex(WebDriver driver, String headerText) {
        String headerXpath = String.format("//table[@class='MyTable WideTable']//tr//th[text()='%s']", headerText);

        // Check exist
        List<WebElement> tableHeaders = driver.findElements(By.xpath(headerXpath));
        if (tableHeaders.isEmpty()) {
            throw new NoSuchElementException("Header is not existed!");
        }

        WebElement tableHeader = tableHeaders.get(0);

        //Get list of siblings
        String siblingXpath = String.format("%s/preceding-sibling::th", headerXpath);
        List<WebElement> precedingSiblings = tableHeader.findElements(By.xpath(siblingXpath));

        return precedingSiblings.size() + 1;
    }

    public String getCellValue(WebDriver driver ,String headerText) {
        int columnIndex = getColumnIndex(driver, headerText);
        String cellXpath = String.format("//table[@class='MyTable WideTable']//tr[position()>1]//td[%d]", columnIndex);

        List<WebElement> cells = driver.findElements(By.xpath(cellXpath));
        if (cells.isEmpty()) {
            throw new NoSuchElementException("Cell is not existed!");
        }

        return cells.get(0).getText();
    }

    public int calculateTotalPrice(int unitPrice, int quantity) {
        return unitPrice * quantity;
    }

    public String getSelectedText(WebElement element) {
        WebElement dropdown = element;
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public String getID() {
        String url = Constant.WEBDRIVER.getCurrentUrl();
        String ticketID = url.split("id=")[1];
        return ticketID;
    }
}
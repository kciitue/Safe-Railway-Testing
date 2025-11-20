package pageObjects.Railway;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class TimetablePage extends GeneralPage {

    public enum Station {
        SAI_GON(1, "Sài Gòn"),
        PHAN_THIET(2, "Phan Thiết"),
        NHA_TRANG(3, "Nha Trang"),
        DA_NANG(4, "Đà Nẵng"),
        HUE(5, "Huế"),
        QUANG_NGAI(6, "Quảng Ngãi");

        private final int id;
        private final String name;

        Station(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public BookTicketPage clickBookTicket(Station depart, Station arrive) {
        String xpath = String.format("//a[@href='BookTicketPage.cshtml?id1=%d&id2=%d']",
                depart.getId(), arrive.getId());

        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.scrollBy(0,600)");

        WebElement bookLink = Constant.WEBDRIVER.findElement(By.xpath(xpath));
        bookLink.click();
        return new BookTicketPage();
    }
}

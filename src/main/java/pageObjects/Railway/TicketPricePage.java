package pageObjects.Railway;

import common.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class TicketPricePage extends GeneralPage {
    public void clickCheckPrice(String listBigText, String listSmallText) {
        String xpathBig = String.format(
                "//li[@class='ListBig' and contains(normalize-space(.), '%s')]",
                listBigText
        );

        String xpathSmall = String.format(
                "%s/following::li[@class='ListSmall' and contains(normalize-space(.), '%s')][1]",
                xpathBig, listSmallText
        );

        // Tìm button Check tương ứng (BoxLink)
        String xpathButton = xpathSmall + "/following::a[contains(@class,'BoxLink')][1]";

        WebElement checkButton = Constant.WEBDRIVER.findElement(By.xpath(xpathButton));
        checkButton.click();
    }

    //Lay gia ve
    public int getPriceByDescription(String description) {
        String xpathRow = String.format("//tr[td[2][normalize-space(text())='%s']]", description);
        WebElement row = Constant.WEBDRIVER.findElement(By.xpath(xpathRow));
        String seatCode = row.findElement(By.xpath("./td[1]")).getText().replace(":", "").trim();

        List<WebElement> seatTypeCells = Constant.WEBDRIVER.findElements(By.xpath("//tr[th[normalize-space(text())='Seat type']]/td"));
        int columnIndex = -1;
        for (int i = 0; i < seatTypeCells.size(); i++) {
            String cellText = seatTypeCells.get(i).getText().trim();
            if (cellText.equalsIgnoreCase(seatCode)) {
                columnIndex = i;
                break;
            }
        }

        if (columnIndex == -1) {
            throw new NoSuchElementException("Không tìm thấy mã ghế trong hàng Seat type: " + seatCode);
        }

        List<WebElement> priceCells = Constant.WEBDRIVER.findElements(By.xpath("//tr[th[normalize-space(text())='Price (VND)']]/td"));
        if (columnIndex >= priceCells.size()) {
            throw new NoSuchElementException("Không có giá vé tại cột: " + (columnIndex + 1));
        }

        String priceText = priceCells.get(columnIndex).getText().replaceAll("[^\\d]", "");

        return Integer.parseInt(priceText);
    }
}

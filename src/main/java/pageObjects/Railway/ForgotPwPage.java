package pageObjects.Railway;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ForgotPwPage extends GeneralPage {
    //Locators
    private final By txtEmail = By.xpath("//input[@id='email']");
    private final By btnSendInstructions = By.xpath("//input[@type='submit']");

    //Elements
    public WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(txtEmail);
    }

    public WebElement getBtnSendInstructions() {
        return Constant.WEBDRIVER.findElement(btnSendInstructions);
    }

    //Methods
    public PwChangeFormPage gotoPwChangeForm() {
        this.getBtnSendInstructions().click();
        return new PwChangeFormPage();
    }
}

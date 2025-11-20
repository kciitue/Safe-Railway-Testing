package pageObjects.Railway;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChangePasswordPage extends GeneralPage {
    //Locators
    private final By headerChangePW = By.xpath("//h1[text()='Change password']");
    private final By _txtCurrentPW = By.xpath("//input[@id='currentPassword']");
    private final By _txtNewPW = By.xpath("//input[@id='newPassword']");
    private final By _txtConfirmPW = By.xpath("//input[@id='confirmPassword']");
    private final By btnChangePw = By.xpath("//input[@type='submit']");
    private final By lblSuccessMsg = By.xpath("//p[@class='message success']");
    //Elements
    public WebElement getHeaderChangePW() {
        return Constant.WEBDRIVER.findElement(headerChangePW);
    }

    public WebElement getTxtCurrentPW() {
        return Constant.WEBDRIVER.findElement(_txtCurrentPW);
    }

    public WebElement getTxtNewPW() {
        return Constant.WEBDRIVER.findElement(_txtNewPW);
    }

    public WebElement getTxtConfirmPW() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPW);
    }

    public WebElement getBtnChangePw() {
        return Constant.WEBDRIVER.findElement(btnChangePw);
    }

    public WebElement getLblSuccessMsg() {
        return Constant.WEBDRIVER.findElement(lblSuccessMsg);
    }
    //Methods
    public boolean isChangePwPageDiplayed() {
        return getHeaderChangePW().isDisplayed();
    }

    public ChangePasswordPage changePassword(String currentPw, String newPw) {
        this.getTxtCurrentPW().sendKeys(currentPw);
        this.getTxtNewPW().sendKeys(newPw);
        this.getTxtConfirmPW().sendKeys(newPw);
        this.getBtnChangePw().click();

        return this;
    }

    public String getSuccessMsg() {
        return this.getLblSuccessMsg().getText();
    }
}

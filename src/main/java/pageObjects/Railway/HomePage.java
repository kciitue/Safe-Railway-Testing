package pageObjects.Railway;

import common.Constant;

public class HomePage extends GeneralPage{
    //Locators

    //Elements

    //Methods
    public HomePage open() {
        Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
        return this;
    }

    public String getErrorMessage() {
        return this.getLblLoginErrorMsg().getText();
    }
}

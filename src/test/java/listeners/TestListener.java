package listeners;

import common.Constant;
import common.Utilities;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;


public class TestListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        captureScreenshot(result, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        captureScreenshot(result, "FAIL");
    }

    public void captureScreenshot(ITestResult result, String status) {
        WebDriver driver = Constant.WEBDRIVER;

        if (driver == null) {
            System.out.println("Driver is null, cannot take screenshot.");
            return;
        }

        try {
            try {
                Utilities.scrollToCenter();

                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                System.out.println("Error when scroll: " + ex.getMessage());
            }
            String testName = result.getName();

            String fileName = testName + ".png";

            String filePath = "test-output/screenshots/" + fileName;
            File destFile = new File(filePath);

            new File("test-output/screenshots/").mkdirs();

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(srcFile, destFile);

            System.out.println("Saved: " + destFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error when shooting screen: " + e.getMessage());
        }
    }
}

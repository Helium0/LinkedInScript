package driver;

import org.openqa.selenium.WebDriver;

public class DriverManagement {


    private static WebDriver driver;


    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static WebDriver getDriver() {
        return driver;
    }



    public static void quitDriver() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

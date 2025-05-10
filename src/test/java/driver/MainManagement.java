package driver;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import projectHelper.AdditionalBrowserOptions;

public class MainManagement {


    @BeforeTest
    public void openBrowser() {
        WebDriver driver = DriverFactory.getBrowser("chrome", AdditionalBrowserOptions.browserArguments());
        DriverManagement.setDriver(driver);
        driver.get("https://www.linkedin.com/");
    }



    @AfterTest
    public void quitBrowser() {

    }



}

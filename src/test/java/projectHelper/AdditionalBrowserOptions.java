package projectHelper;

import org.openqa.selenium.chrome.ChromeOptions;

public class AdditionalBrowserOptions {


    private static ChromeOptions options() {
        return new ChromeOptions();
    }

    public static ChromeOptions browserArguments() {
        ChromeOptions browserOptions = options();
        browserOptions.addArguments("user-data-dir=C:\\Selenium\\ChromeProfile");

        return browserOptions;
    }

}

package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {


    public static WebDriver getBrowser(String browserName, ChromeOptions options) {
        return switch (browserName.toLowerCase()) {
            case "chrome" -> new ChromeDriver(options);
            case "firefox" -> new FirefoxDriver();
            case "edge" -> new EdgeDriver();
            default -> throw new RuntimeException("This browser doesn`t exist. Please write correct one");
        };
    }
}

package projectHelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionClass {


    public static Actions actions(WebDriver driver) {
        return new Actions(driver);
    }

}

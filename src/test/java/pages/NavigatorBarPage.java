package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import projectHelper.ActionClass;

public class NavigatorBarPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='Szukaj']")
    private WebElement searchBar;



    public NavigatorBarPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void typeTextInToSearchBar(String text) {
         searchBar.sendKeys(text);
    }

    public PersonsFinderPage clickToExpandAllPersons() {
        ActionClass.actions(driver).keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
        return new PersonsFinderPage(driver);
    }




}

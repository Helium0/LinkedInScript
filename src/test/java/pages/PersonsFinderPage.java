package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonsFinderPage {


    private WebDriver driver;

    @FindBy(xpath = "//a[text()='Zobacz wszystkie wyniki osób']")
    private By seeAllPersonsButton;


    public PersonsFinderPage (WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }





}

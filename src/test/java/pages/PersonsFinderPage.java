package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import projectHelper.WaitsGenerator;
import java.util.List;

public class PersonsFinderPage {


    private WebDriver driver;

    @FindBy(xpath = "//a[text()='Zobacz wszystkie wyniki osób']")
    private WebElement seeAllPersonsButton;

    @FindBy(xpath = "//ul[@role='list']//li")
    private List<WebElement> personsList;

    @FindBy(xpath = "//button[@aria-label='Dalej']")
    private WebElement nextPageButton;

    @FindBy(xpath = ".//span[text()='Nawiąż kontakt']")
    private WebElement connectionButton;



    public List<WebElement> getPersonsList() {
        return personsList;
    }

    public PersonsFinderPage (WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }


    public void clickOnAllPersonsButton(WebDriver driver) {
        WaitsGenerator.waitForElement(driver,seeAllPersonsButton).click();
    }


    public void personsListWebelements() {
        WaitsGenerator.waitForWebElements(driver,personsList);
    }

    public void clickOnNextPageButton() {
        nextPageButton.click();
    }

    public AdditionalUserMessagePage clickToMakeConnectionWithPerson() {
        connectionButton.click();
        return new AdditionalUserMessagePage(driver);
    }



}

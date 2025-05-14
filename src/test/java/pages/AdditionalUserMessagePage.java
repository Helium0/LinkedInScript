package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import projectHelper.WaitsGenerator;

import java.util.List;

public class AdditionalUserMessagePage {

    private WebDriver driver;

    @FindBy(xpath = "//button[@aria-label='Wyślij bez notatki']")
    private WebElement sendWithoutNoteButton;

    @FindBy(xpath = ".//button[@aria-label='Dodaj notatkę']")
    private WebElement userNoteButton;

    @FindBy(xpath = "//button[@aria-label='Odrzuć']")
    private WebElement cancelButton;

    @FindBy(xpath = "//label[contains(., 'wpisz jego(jej) adres e-mail')]")
    private List<WebElement> emailLabel;

    @FindBy(xpath = "//span[text()='Spersonalizuj zaproszenie dla użytkownika ']")
    private WebElement personalizedUserTextMessage;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement userEmailInput;

    @FindBy(id = "custom-message")
    private WebElement customUserMessage;


    public AdditionalUserMessagePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement sendCustomUserMessage() {
        return customUserMessage;
    }

    public WebElement noteButton() {
        return sendWithoutNoteButton;
    }

    public WebElement visibleText() {
        return personalizedUserTextMessage;
    }

    public void addUserNoteButton() {
        userNoteButton.click();
    }

    public WebElement clickOnCancelButton() {
        return cancelButton;
    }

    public void usersEmailLabel(String text) {
        if(emailLabel.isEmpty()) {
            WaitsGenerator.waitForElement(driver,userEmailInput).sendKeys(text);
        }
    }



}

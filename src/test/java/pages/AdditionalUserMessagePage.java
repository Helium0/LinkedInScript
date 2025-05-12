package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdditionalUserMessagePage {

    private WebDriver driver;

    @FindBy(xpath = "//button[@aria-label='Wyślij bez notatki']")
    private WebElement sendWithoutNoteButton;

    @FindBy(xpath = "//button[@aria-label='Odrzuć']")
    private WebElement cancelButton;

    public AdditionalUserMessagePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public WebElement noteButton() {
        return sendWithoutNoteButton;
    }

    public WebElement clickOnCancelButton() {
        return cancelButton;
    }
}

package tests;

import driver.DriverManagement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.AdditionalUserMessagePage;
import pages.NavigatorBarPage;
import pages.PersonsFinderPage;
import projectHelper.FileGenerator;
import projectHelper.GenerateDate;
import projectHelper.WaitsGenerator;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;


public class PersonalizedInvitations {

    private NavigatorBarPage navigatorBarPage;
    private PersonsFinderPage personsFinderPage;
    private AdditionalUserMessagePage additionalUserMessagePage;
    private final By ALL_PERSONS_BUTTON = By.xpath("//a[text()='Zobacz wszystkie wyniki osób']");
    private final By PERSONS_LIST = By.xpath("//ul[@role='list']//li");
    private int invitations = 1;
    private final String TEXT_IN_SEARCH_BAR = "it hr";

    @Test
    public void multiple() throws InterruptedException, FileNotFoundException {
        String date = GenerateDate.actuallDate();
        WebDriver driver = DriverManagement.getDriver();
        navigatorBarPage = new NavigatorBarPage(driver);
        personsFinderPage = new PersonsFinderPage(driver);
        additionalUserMessagePage = new AdditionalUserMessagePage(driver);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        navigatorBarPage.typeTextInToSearchBar(TEXT_IN_SEARCH_BAR);
        navigatorBarPage.clickToExpandAllPersons();
        WaitsGenerator.waitForElementByXpath(driver,ALL_PERSONS_BUTTON).click();
        WaitsGenerator.waitForElementByXpath(driver,PERSONS_LIST);
        PrintWriter write = FileGenerator.writerFile(date+"_personalizedInvitations.txt");
        write.println("Wysłanie spersonalizowanych zaproszeń w dniu: "+date);

        for (int i = 0; i <= personsFinderPage.getPersonsList().size(); i++) {
            try {

                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@dir='ltr']/span[@aria-hidden='true']")));
                Thread.sleep(500);
                WebElement element = personsFinderPage.getPersonsList().get(i);
                WebElement user = element.findElement(By.xpath(".//span[@dir='ltr']/span[@aria-hidden='true']"));
                WebElement userBar = element.findElement(By.xpath(".//div[contains(@class,'t-14 t-black')]"));
                write.println("\n");
                write.println("Nazwa użytkownika: "+user.getText());
                write.println("Zawód/Tagi: "+userBar.getText());
                try {
                    personsFinderPage.clickToMakeConnectionWithPerson();
                    try {
                        WaitsGenerator.waitForElement(driver,additionalUserMessagePage.visibleText());
                        additionalUserMessagePage.addUserNoteButton();
                        WaitsGenerator.waitForVisibilityElement(driver, additionalUserMessagePage.sendCustomUserMessage())
                                .sendKeys("Hej " + user.getText() + "\n" +
                                        "przesyłam tę wiadomość w dniu: " + date + "\n" +
                                        "Używając skryptu w Java/Selenium, " +
                                        "moim celem było zautomatyzowanie wysyłania spersonalizowanych zaproszeń =)");
                        WaitsGenerator.waitForElement(driver, additionalUserMessagePage.clickOnCancelButton()).click();
                        write.println("Zaproszenie "+invitations+" wysłane =)");
                        if (invitations == 3) {
                            break;
                        }

                        invitations += 1;
                    } catch (TimeoutException e1) {
                        additionalUserMessagePage.usersEmailLabel("Nie znam adresu e-mail nie mogę wysłać zaproszenia =(");
                        WaitsGenerator.waitForElement(driver, additionalUserMessagePage.clickOnCancelButton()).click();
                        write.println("Nie znam adresu e-mail aby wysłać zaproszenie");
                        }

                } catch (NoSuchElementException | ElementNotInteractableException e) {
                    write.println("Osoba już jest w Twoich znajomych lub można było dodać tylko do obserwujących "
                    +"\n"+"Nie wysłano zaproszenia");
                }

            } catch (StaleElementReferenceException | NoSuchElementException e) {

            }
        }
        write.close();
    }
}

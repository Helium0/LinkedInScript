package tests;

import driver.DriverManagement;
import driver.MainManagement;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import pages.NavigatorBarPage;
import pages.PersonsFinderPage;
import projectHelper.WaitsGenerator;



public class FriendInvitations extends MainManagement {

    private int page = 1;
    private NavigatorBarPage navigatorBarPage;
    private PersonsFinderPage personsFinderPage;
    private final String TEXT_IN_SEARCH_BAR = "it hr";
    private final By LOC = By.xpath(".//span[@dir='ltr']/span[@aria-hidden='true']");
    private By PAGE = By.xpath("//li[@data-test-pagination-page-btn='" + page + "']//span");
    private int invitations = 0;
    private boolean enabledButton = true;


    @Test
    public void sendFriendInvitations() throws InterruptedException {
        WebDriver driver = DriverManagement.getDriver();
        navigatorBarPage = new NavigatorBarPage(driver);
        personsFinderPage = new PersonsFinderPage(driver);
        navigatorBarPage.typeTextInToSearchBar(TEXT_IN_SEARCH_BAR);
        navigatorBarPage.clickToExpandAllPersons();
        personsFinderPage.clickOnAllPersonsButton(driver);
        personsFinderPage.personsListWebelements();
        try {

            for (int b = 0; b < Integer.parseInt(driver.findElement(By.xpath("//li[@data-test-pagination-page-btn='" + page + "']//span")).getText()); b++) {
                for (int i = 0; i < personsFinderPage.getPersonsList().size(); i++) {
                    try {
                        WaitsGenerator.waitForElementByXpath(driver, LOC);
                        Thread.sleep(500);
                        WebElement element = personsFinderPage.getPersonsList().get(i);
                        WebElement user = element.findElement(By.xpath(".//span[@dir='ltr']/span[@aria-hidden='true']"));
                        WebElement userBar = element.findElement(By.xpath(".//div[contains(@class,'t-14 t-black')]"));
                        System.out.println("\n");
                        System.out.println("Nazwa uzytkownika: " + user.getText());
                        System.out.println("Tagi uzytkownika: " + userBar.getText());
                        try {
                            WebElement contactButton = element.findElement(By.xpath(".//span[text()='Nawiąż kontakt']"));
                            contactButton.click();
//                        WaitsGenerator.waitForElementByXpath(driver,By.xpath("//span[text()='Wyślij bez notatki']")).click();
                            if (driver.findElement(By.xpath("//button[@aria-label='Wyślij bez notatki']")).isEnabled() != enabledButton) {
                                System.out.println("Nie mozna wyslac zaproszenia przycisk nieaktywny");
                                WaitsGenerator.waitForElementByXpath(driver, By.xpath("//button[@aria-label='Odrzuć']")).click();

                            } else {
                                invitations += 1;
                                WaitsGenerator.waitForElementByXpath(driver, By.xpath("//button[@aria-label='Odrzuć']")).click();
                                System.out.println("Wyslano zaproszenie do: " + user.getText() + " | " + invitations);
                                if (invitations == 200) {
                                    break;
                                }
                            }

                        } catch (NoSuchElementException | ElementNotInteractableException e) {
                            System.out.println("Osoba już jest w Twoich znajomych lub można było dodać tylko do obserwujących "
                                    + "\n" + "Nie wysłano zaproszenia");
                        }
                    } catch (StaleElementReferenceException | NoSuchElementException e) {

                    }
                }
                if (invitations == 200) {
                    break;
                } else {
                    driver.findElement(By.xpath("//button[@aria-label='Dalej']")).click();
                    WaitsGenerator.waitForElementByXpath(driver, PAGE);
                }
                page += 1;

            }
        } catch (StaleElementReferenceException | NoSuchElementException e) {

        }
        System.out.println("Wysłano: "+invitations+" zaproszen do znajomych");





    }

}

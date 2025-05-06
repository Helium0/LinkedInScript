package tests;

import driver.DriverFactory;
import driver.DriverManagement;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class PersonalizedInvitations {

    private final By ALL_PERSONS_BUTTON = By.xpath("//a[text()='Zobacz wszystkie wyniki osób']");
    private final By PERSONS_LIST = By.xpath("//ul[@role='list']//li");



    @Test
    public void multiple() throws InterruptedException, FileNotFoundException {
        Date date = new Date();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\patry\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("profile-directory=Profile 22");
        String automatDate = date.toString().replace("-", "").replace(":", "_");
        WebDriver driver = DriverFactory.getBrowser("chrome", options);
        DriverManagement.setDriver(driver);
        driver = DriverManagement.getDriver();
        driver.get("https://www.linkedin.com/");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement te = driver.findElement(By.xpath("//input[@placeholder='Szukaj']"));
        te.sendKeys("it hr");
        Actions actions = new Actions(driver);
        actions.click(te).keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(ALL_PERSONS_BUTTON)).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(PERSONS_LIST));
        PrintWriter write = new PrintWriter("invited_persons.txt");
        write.println("Wysłanie spersonalizowanych zaproszeń w dniu: "+automatDate);
        List<WebElement> persons = driver.findElements(By.xpath("//ul[@role='list']//li"));
        int invitations = 1;
        for (int i = 0; i <= persons.size(); i++) {
            try {

                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@dir='ltr']/span[@aria-hidden='true']")));
                Thread.sleep(500);
                WebElement element = persons.get(i);
                WebElement user = element.findElement(By.xpath(".//span[@dir='ltr']/span[@aria-hidden='true']"));
                WebElement userBar = element.findElement(By.xpath(".//div[contains(@class,'t-14 t-black')]"));
                write.println("\n");
                write.println("Nazwa użytkownika: "+user.getText());
                write.println("Zawód/Tagi: "+userBar.getText());
                try {
                    WebElement contactButton = element.findElement(By.xpath(".//span[text()='Nawiąż kontakt']"));
                    contactButton.click();

                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

                    try {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//span[text()='Spersonalizuj zaproszenie dla użytkownika ']")));

                        driver.findElement(By.xpath(".//button[@aria-label='Dodaj notatkę']")).click();

                        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("custom-message")));

                        WebElement text = driver.findElement(By.id("custom-message"));
                        text.sendKeys("Hej " + user.getText() + "\n" +
                                "przesyłam tę wiadomość w dniu: " + automatDate + "\n" +
                                "Używając skryptu w Java/Selenium, " +
                                "moim celem było zautomatyzowanie wysyłania spersonalizowanych zaproszeń =)");
//                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Wyślij zaproszenie']"))).click();
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Odrzuć']"))).click();
                        write.println("Zaproszenie "+invitations+" wysłane =)");
                        if (invitations == 5) {
                            break;
                        }

                        invitations += 1;


                    } catch (TimeoutException e1) {

                        List<WebElement> emailLabel = driver.findElements(
                                By.xpath("//label[contains(., 'wpisz jego(jej) adres e-mail')]"));

                        if (!emailLabel.isEmpty()) {
                            WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(
                                    By.xpath("//input[@name='email']")));
                            emailInput.sendKeys("Nie znam adresu e-mail nie mogę wysłać zaproszenia =(");

                            wait.until(ExpectedConditions.elementToBeClickable(
                                    By.xpath("//button[@aria-label='Odrzuć']"))).click();
                            write.println("Nie znam adresu e-mail aby wysłać zaproszenie");
                        }
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

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.objects.InboxObjects;
import page.objects.SentObjects;

public class SentPage {

    WebDriver driver;
    WebDriverWait wait;
    InboxObjects inboxObjects;
    SentObjects sentObjects;

    public SentPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,5, 500);
        inboxObjects = PageFactory.initElements(driver, InboxObjects.class);
        sentObjects = PageFactory.initElements(driver, SentObjects.class);
    }

    public void createNewMessageAndSendIt(String email, String subject, String body) {
        wait.until(ExpectedConditions.elementToBeClickable(sentObjects.newMessageButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(sentObjects.to));
        sentObjects.to.sendKeys(email);
        sentObjects.subject.sendKeys(subject);
        sentObjects.body.sendKeys(body);
        wait.until(ExpectedConditions.elementToBeClickable(sentObjects.sendButton)).click();
    }

    public boolean verifyMessageWithSubjectIsSent(String subject) {
        String path = "//*[contains(text()," + "'" + subject + "'" + ")]";
        WebElement xpath = driver.findElement(By.xpath(path));
        wait.until(ExpectedConditions.visibilityOf(xpath));

        return driver.findElements(By.xpath(path)).size() != 0;
    }
}

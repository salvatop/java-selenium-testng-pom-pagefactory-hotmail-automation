package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.objects.InboxObjects;


public class InboxPage {

    final public String INBOX_URL = "https://outlook.live.com/mail/0/inbox";

    InboxObjects inboxObjects;
    WebDriver driver;
    WebDriverWait wait;

    public InboxPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,5, 500);
        inboxObjects = PageFactory.initElements(driver, InboxObjects.class);
    }

    public void logout(){
        wait.until(ExpectedConditions.elementToBeClickable(inboxObjects.showLogoutButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(inboxObjects.logoutButton)).click();
    }

    public boolean verifyMessageWithSubjectIsReceived(String subject){
        String xpath = "//*[contains(text()," + "'" + subject + "'" + ")]";
        return driver.findElements(By.xpath(xpath)).size() != 0;
    }
}

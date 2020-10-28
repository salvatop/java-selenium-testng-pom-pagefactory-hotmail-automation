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
        System.out.println("Click on the account manager button");
        wait.until(ExpectedConditions.elementToBeClickable(inboxObjects.showLogoutButton)).click();
        System.out.println("Sign out!");
        wait.until(ExpectedConditions.elementToBeClickable(inboxObjects.logoutButton)).click();
    }

    public boolean verifyMessageWithSubjectIsReceived(String subject){
        String xpath = "//*[contains(text()," + "'" + subject + "'" + ")]";
        if(driver.findElements(By.xpath(xpath)).size() != 0) {
            System.out.println("Message with subject " + subject + " exists!");
            return true;
        } else {
            System.out.println("Message not found!");
            return false;
        }
    }
}

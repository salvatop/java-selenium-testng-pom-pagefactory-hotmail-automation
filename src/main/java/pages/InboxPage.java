package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.objects.InboxObjects;


public class InboxPage {

    final public String INBOX_URL = "https://outlook.live.com/mail/0/inbox";

    InboxObjects inboxObjects;
    WebDriver driver;

    public InboxPage(WebDriver driver){
        this.driver = driver;
        inboxObjects = PageFactory.initElements(driver, InboxObjects.class);
    }

    public void logout(){
        inboxObjects.logoutButton.click();
    }

    public boolean verifyMessageWithSubjectIsReceived(String subject){

        return false;
    }
}

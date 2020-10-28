package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.objects.InboxObjects;
import page.objects.SentObjects;

public class SentPage {

    final public String SENT_URL = "https://outlook.live.com/mail/0/sentitems";

    InboxObjects inboxObjects;
    SentObjects sentObjects;
    WebDriver driver;

    public SentPage(WebDriver driver){
        this.driver = driver;
        inboxObjects = PageFactory.initElements(driver, InboxObjects.class);
        sentObjects = PageFactory.initElements(driver, SentObjects.class);
    }

    public void createNewMessageAndSendIt(String email, String subject, String body) {
        WebDriverWait wait = new WebDriverWait(driver,5, 500);
        wait.until(ExpectedConditions.elementToBeClickable(inboxObjects.newMessageButton)).click();
    }

    public boolean verifyMessageIsSent(){

        return false;
    }
}

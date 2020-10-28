package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import page.objects.AccountObjects;

public class AccountPage {

    final public String ACCOUNT_URL = "https://account.microsoft.com/";
    AccountObjects accountObjects;
    WebDriver driver;

    public AccountPage(WebDriver driver){
        this.driver = driver;
        accountObjects = PageFactory.initElements(driver, AccountObjects.class);
    }
    public String getEmailText() {
        return accountObjects.accountEmail.getText();
    }

    public WebElement getEmailWebElement() {
        return accountObjects.accountEmail;
    }
}

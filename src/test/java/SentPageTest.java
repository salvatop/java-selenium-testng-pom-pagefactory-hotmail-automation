import browser.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.InboxPage;
import pages.LoginPage;
import pages.SentPage;


public class SentPageTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    InboxPage inboxPage;
    AccountPage accountPage;
    SentPage sentPage;

    @Parameters({"browser"})
    @BeforeSuite
    public void setUp(String browser) {
        Reporter.log("TestNG is launching the browser driver");
        this.driver = Browser.getDriver(browser);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        inboxPage = PageFactory.initElements(driver, InboxPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);
        sentPage = PageFactory.initElements(driver, SentPage.class);
        wait = new WebDriverWait(driver,5, 500);
    }
    @AfterSuite
    public void tearDown(){
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Parameters({"username", "password", "subject", "body"})
    @Test
    public void sendEmail(String username, String password, String subject, String body) {
        loginPage.login(username, password);

        driver.get(accountPage.ACCOUNT_URL);
        wait.until(ExpectedConditions.visibilityOf(accountPage.getEmailWebElement()));
        Assert.assertEquals(accountPage.getEmailText(), username);

        driver.get(inboxPage.INBOX_URL);
        sentPage.createNewMessageAndSendIt(username, subject, body);

        Assert.assertTrue(sentPage.verifyMessageIsSent());

        inboxPage.logout();
    }
}

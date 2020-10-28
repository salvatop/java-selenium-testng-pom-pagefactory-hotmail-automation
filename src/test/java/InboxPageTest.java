import browser.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
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

public class InboxPageTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    InboxPage inboxPage;
    AccountPage accountPage;
    SentPage sentPage;

    @BeforeSuite
    @Parameters({"browser"})
    public void setUp(String browser) {
        Reporter.log("TestNG is launching the browser driver");
        this.driver = Browser.getDriver(browser);
        wait = new WebDriverWait(driver,5, 500);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        inboxPage = PageFactory.initElements(driver, InboxPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);
        sentPage = PageFactory.initElements(driver, SentPage.class);
    }
    @AfterSuite
    public void tearDown(){
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    @Parameters({"username", "password", "subject"})
    public void testReceiveEmail(String username, String password, String subject) {
        System.out.println("Login");
        loginPage.login(username, password);
        System.out.println("Go to sent message folder");
        driver.get(sentPage.SENT_URL);
        System.out.println("Verify if the massage with the expected subject is present");
        Assert.assertTrue(inboxPage.verifyMessageWithSubjectIsReceived(subject));
        inboxPage.logout();
    }
}

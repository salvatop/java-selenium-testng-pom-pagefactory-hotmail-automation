import browser.Browser;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.InboxPage;
import pages.LoginPage;
import pages.SentPage;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class InboxPageTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    InboxPage inboxPage;
    AccountPage accountPage;
    SentPage sentPage;
    Logger logger;

    @BeforeSuite
    @Parameters({"browser", "isFileLoggerEnabled"})
    public void setUp(String browser, String isFileLoggerEnabled) throws IOException {
        this.driver = Browser.getDriver(browser);
        wait = new WebDriverWait(driver,5, 500);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        inboxPage = PageFactory.initElements(driver, InboxPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);
        sentPage = PageFactory.initElements(driver, SentPage.class);

        if(isFileLoggerEnabled.equals("true")) {
            boolean append = true;
            FileHandler handler = new FileHandler("InboxPageTest.txt", append);
            logger = Logger.getLogger("InboxPageTest.class");
            logger.addHandler(handler);
        } else {
            logger = Logger.getLogger("InboxPageTest.class");
        }

        logger.info("Launching the browser driver");
    }
    @AfterSuite
    public void tearDown(){
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test(groups = { "receive" } )
    @Parameters({"username", "password", "subject", "sentUrl", "loginUrl"})
    public void testReceiveEmail(String username, String password, String subject, String sentUrl, String loginUrl) {

        try {
            logger.info("Login");
            driver.get(loginUrl);
        } catch (Exception exception) {
            logger.info("url wrong " + exception.getLocalizedMessage());
        }
        try {
            loginPage.login(username, password);
            logger.info("Logged successfully with user: " + username);
        } catch (NoSuchElementException exception) {
            logger.info("login failed: " + exception.getLocalizedMessage());
        }
        try {
            logger.info("Go to sent message folder");
            driver.get(sentUrl);
            logger.info("Verify if the massage with the expected subject is present");
            Assert.assertTrue(inboxPage.verifyMessageWithSubjectIsReceived(subject));
            logger.info("Message with subject " + subject + " exists!");
            inboxPage.logout();
            logger.info("logoff");
        } catch (Exception exception) {
            logger.info(exception.getLocalizedMessage());
        }
    }
}

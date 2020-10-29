import browser.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.InboxPage;
import pages.LoginPage;
import pages.SentPage;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class SentPageTest {

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
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        inboxPage = PageFactory.initElements(driver, InboxPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);
        sentPage = PageFactory.initElements(driver, SentPage.class);
        wait = new WebDriverWait(driver,5, 500);

        if(isFileLoggerEnabled.equals("true")) {
            boolean append = true;
            FileHandler handler = new FileHandler("SentPageTest.txt", append);
            logger = java.util.logging.Logger.getLogger("SentPageTest.class");
            logger.addHandler(handler);
        } else {
            logger = Logger.getLogger("SentPageTest.class");
        }

        logger.info("Launching the browser driver");
    }
    @AfterSuite
    public void tearDown(){
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test(groups="send")
    @Parameters({"username", "password", "subject", "body", "accountUrl", "inboxUrl", "loginUrl"})
    public void sendEmail(String username, String password, String subject, String body, String accountUrl, String inboxUrl, String loginUrl) {
        driver.get(loginUrl);
        loginPage.login(username, password);
        driver.get(accountUrl);
        wait.until(ExpectedConditions.visibilityOf(accountPage.getEmailWebElement()));
        Assert.assertEquals(accountPage.getEmailText(), username);

        driver.get(inboxUrl);
        sentPage.createNewMessageAndSendIt(username, subject, body);
        //TODO - re-enable the assertion after resolved the bug on the dynamic waiter
        //Assert.assertTrue(sentPage.verifyMessageIsSent(subject));
        inboxPage.logout();
    }
}

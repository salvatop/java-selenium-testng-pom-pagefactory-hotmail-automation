import browser.Browser;
import org.openqa.selenium.TimeoutException;
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
import pages.LoginPage;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LoginPageTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    AccountPage accountPage;
    Logger logger;

    @BeforeSuite
    @Parameters({"browser", "isFileLoggerEnabled"})
    public void setUp(String browser, String isFileLoggerEnabled) throws IOException {
        this.driver = Browser.getDriver(browser);
        wait = new WebDriverWait(driver,5, 500);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);

        if(isFileLoggerEnabled.equals("true")) {
            boolean append = true;
            FileHandler handler = new FileHandler("LoginPageTest.txt", append);
            logger = java.util.logging.Logger.getLogger("LoginPageTest.class");
            logger.addHandler(handler);
        } else {
            logger = Logger.getLogger("LoginPageTest.class");
        }

        logger.info("Launching the browser driver");

    }
    @AfterSuite
    public void tearDown(){
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test(groups = { "login" } )
    @Parameters({"username", "password", "accountUrl", "loginUrl"})
    public void testLoginWithCorrectCredentials(String username, String password, String accountUrl, String loginUrl) {
        try {
            logger.info("Go to login page");
            driver.get(loginUrl);
        }catch (NoSuchElementException exception) {
            logger.info("wrong url" + exception.getLocalizedMessage());
        }
        try {
            loginPage.login(username, password);
            logger.info("Logged successfully with user: " + username);
        } catch (TimeoutException exception) {
            logger.info("login failed" + exception.getLocalizedMessage());
        }
        try {
            logger.info("Go to account page");
            driver.get(accountUrl);
            wait.until(ExpectedConditions.visibilityOf(accountPage.getEmailWebElement()));
            Assert.assertEquals(accountPage.getEmailText(), username);
            logger.info("Account verified");

         } catch (Exception exception) {
            logger.info("login error" +exception.getLocalizedMessage());
        }


    }
}

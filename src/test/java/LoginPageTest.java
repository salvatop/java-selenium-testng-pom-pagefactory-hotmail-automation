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
import pages.LoginPage;

public class LoginPageTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    AccountPage accountPage;

    @Parameters({"browser"})
    @BeforeSuite
    public void setUp(String browser) {
        Reporter.log("TestNG is launching the chrome driver");
        this.driver = Browser.getDriver(browser);
        wait = new WebDriverWait(driver,5, 500);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);

    }
    @AfterSuite
    public void tearDown(){
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Parameters({"username", "password"})
    @Test
    public void testLoginWithCorrectCredentials(String username, String password) {
        loginPage.login(username, password);
        driver.get(accountPage.ACCOUNT_URL);
        wait.until(ExpectedConditions.visibilityOf(accountPage.getEmailWebElement()));
        Assert.assertEquals(accountPage.getEmailText(), username);
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.objects.LoginObjects;

public class LoginPage  {

    final public String LOGIN_URL = "https://login.live.com/";

    /** The login page. */
    LoginObjects loginObjects;
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        loginObjects = PageFactory.initElements(driver, LoginObjects.class);
    }

    public void fillIn(WebElement element, String text){
        element.sendKeys(text);
    }

    public void click(WebElement element){
        element.click();
    }

    public void login(String username,String password){
        WebDriverWait wait = new WebDriverWait(driver,5, 500);
        driver.get(LOGIN_URL);

        fillIn(loginObjects.txtUsername, username);
        click(loginObjects.btnNext);

        wait.until(ExpectedConditions.elementToBeClickable(loginObjects.txtPassword));

        fillIn(loginObjects.txtPassword, password);
        wait.until(ExpectedConditions.elementToBeClickable(loginObjects.btnNext)).click();

        wait.until(ExpectedConditions.visibilityOf(loginObjects.lblStaySignedIn));
        wait.until(ExpectedConditions.elementToBeClickable(loginObjects.btnNext)).click();
    }
}

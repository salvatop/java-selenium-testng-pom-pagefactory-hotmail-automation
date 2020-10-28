package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.objects.LoginObjects;

public class LoginPage  {

    LoginObjects loginObjects;
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        loginObjects = PageFactory.initElements(driver, LoginObjects.class);
        wait = new WebDriverWait(driver,5, 500);
    }

    public void fillIn(WebElement element, String text){
        element.sendKeys(text);
    }

    public void click(WebElement element){
        element.click();
    }

    public void login(String username,String password){
        fillIn(loginObjects.txtUsername, username);
        click(loginObjects.btnNext);
        wait.until(ExpectedConditions.elementToBeClickable(loginObjects.txtPassword));
        fillIn(loginObjects.txtPassword, password);
        wait.until(ExpectedConditions.elementToBeClickable(loginObjects.btnNext)).click();
        wait.until(ExpectedConditions.visibilityOf(loginObjects.lblStaySignedIn));
        wait.until(ExpectedConditions.elementToBeClickable(loginObjects.btnNext)).click();
    }
}

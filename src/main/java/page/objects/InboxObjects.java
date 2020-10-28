package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class InboxObjects {

    @FindBy(how = How.XPATH,using = "//button//*[contains(text(), 'Account manager')]")
    public WebElement showLogoutButton;

    @FindBy(how = How.XPATH,using = "//*[contains(text(), 'Sign out')]")
    public WebElement logoutButton;

}

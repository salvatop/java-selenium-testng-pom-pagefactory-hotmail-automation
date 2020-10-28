package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class InboxObjects {

    @FindBy(how = How.XPATH,using = "//*[@id = 'id__5']")
    public WebElement newMessageButton;

    @FindBy(how = How.XPATH,using = "//*[@id = 'id__5']")
    public WebElement logoutButton;

}

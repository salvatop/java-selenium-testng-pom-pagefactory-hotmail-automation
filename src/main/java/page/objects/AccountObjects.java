package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountObjects {

    @FindBy(how = How.XPATH,using = "//span[contains(text(), '@hotmail.com')]") //xml file
    public WebElement accountEmail;
}

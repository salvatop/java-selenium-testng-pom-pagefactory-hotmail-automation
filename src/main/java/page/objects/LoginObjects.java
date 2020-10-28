package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginObjects {

        /** The username text field. */
        @FindBy(how = How.XPATH,using = "//*[contains(@name, 'loginfmt')]")
        public WebElement txtUsername;

        /** The password text field. */
        @FindBy(how = How.XPATH,using = "//*[contains(@id, 'i0118')]")
        public WebElement txtPassword;

        /** The button for signing in. */
        @FindBy(how= How.XPATH,using = "//*[contains(@id, 'idSIButton9')]")
        public WebElement btnNext;

        /** Label stay signed in. */
        @FindBy(how= How.XPATH,using = "//div[contains(text(), 'Stay signed in?')]")
        public WebElement lblStaySignedIn;
}

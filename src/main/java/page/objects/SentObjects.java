package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class SentObjects {

    @FindBy(how = How.XPATH,using = "//*[@id = 'id__5']")
    public WebElement newMessageButton;

    @FindBy(how = How.XPATH,using = "//*[contains(@class,'ms-BasePicker-input pickerInput_34694d2a')]")
    public WebElement to;

    @FindBy(how = How.XPATH,using = "//div//*[@placeholder = 'Add a subject']")
    public WebElement subject;

    @FindBy(how = How.XPATH,using = "//*[contains(@class,'_4utP_vaqQ3UQZH0GEBVQe B1QSRkzQCtvCtutReyNZ')]")
    public WebElement body;

    @FindBy(how = How.XPATH,using = "//button[@title= 'Send']")
    public WebElement sendButton;
}

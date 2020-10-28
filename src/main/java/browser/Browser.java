package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class Browser {

    static WebDriver driver;

    public static WebDriver getDriver(String driverToUse) {

        if (driverToUse.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            return driver = new FirefoxDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--incognito");
            //options.addArguments("--headless");
            WebDriverManager.chromedriver().setup();
            return driver = new ChromeDriver(options);
        }
    }
}

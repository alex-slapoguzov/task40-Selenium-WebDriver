import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class RmsysTest {

    private WebDriver driver;
    private String URL = "https://rmsys.issoft.by/";
    private String userName = "EugenBorisik";
    private String password = "qwerty12345";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void rmsysLoginWithIdLocatorTest() {
        WebElement userNameFildByIdLocator = driver.findElement(By.id("Username"));
        WebElement passwordFildByIdLocator = driver.findElement(By.id("Password"));

        userNameFildByIdLocator.sendKeys(userName);
        passwordFildByIdLocator.sendKeys(password);
        passwordFildByIdLocator.submit();
    }

    @Test
    public void rmsysLoginWithNameLocatorTest() {
        WebElement userNameFildByNameLocator = driver.findElement(By.name("Username"));
        WebElement passwordFildByNameLocator = driver.findElement(By.name("Password"));

        userNameFildByNameLocator.sendKeys(userName);
        passwordFildByNameLocator.sendKeys(password);
        passwordFildByNameLocator.submit();
    }

    @Test
    public void rmsysLoginWithCssLocatorTest() {
        WebElement userNameFildByCssLocator = driver.findElement(By.cssSelector("#user-box>#Username"));
        WebElement passwordFildByCssLocator = driver.findElement(By.cssSelector("#password-box>#Password"));

        userNameFildByCssLocator.sendKeys(userName);
        passwordFildByCssLocator.sendKeys(password);
        passwordFildByCssLocator.submit();
    }

    @Test
    public void rmsysLoginWithXpathLocatorTest() {
        WebElement userNameFildByXpathLocator = driver.findElement(By.xpath("//input[@id=\"Username\"]"));
        WebElement passwordFildByXpathLocator = driver.findElement(By.xpath("//input[@id=\"Password\"]"));

        userNameFildByXpathLocator.sendKeys(userName);
        passwordFildByXpathLocator.sendKeys(password);
        passwordFildByXpathLocator.submit();
    }
}

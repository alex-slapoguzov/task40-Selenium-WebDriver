import dataProvider.DataProviderClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class RmsysTest {

    private final static String URL = "https://192.168.100.26/";
    private final static String userName = "EugenBorisik";
    private final static String password = "qwerty12345";
    private final static String URL_HOME_PAGE = "https://192.168.100.26/Home/Index";
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void rmsysLoginTest() {
        WebElement userNameFildByIdLocator = driver.findElement(By.id("Username"));
        WebElement passwordFildByIdLocator = driver.findElement(By.id("Password"));

        userNameFildByIdLocator.sendKeys(userName);
        passwordFildByIdLocator.sendKeys(password);
        passwordFildByIdLocator.submit();
        try {
            Thread.sleep(1000);    // It's implicitlyWait because we don't expect to happen a certain event
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#welcomeDivContent .sign-out")));
        Assert.assertEquals(driver.getCurrentUrl(), URL_HOME_PAGE,"[Current URL is different from " + URL_HOME_PAGE + "]");
    }

    @Test
    public void addExplicitWaiterTest() {
        WebElement userNameFildByIdLocator = driver.findElement(By.id("Username"));
        WebElement passwordFildByIdLocator = driver.findElement(By.id("Password"));
        WebElement logInButton = driver.findElement(By.id("SubmitButton"));

        userNameFildByIdLocator.sendKeys(userName);
        passwordFildByIdLocator.sendKeys(password);
        logInButton.click();

        WebElement officeTab = driver.findElement(By.cssSelector(".tabs-menu #officeMenu"));
        officeTab.click();
        new WebDriverWait(driver, 15, 2700).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#search-container>#input-search")));
        WebElement sortBy = driver.findElement(By.cssSelector(".sbHolder.sbOfficeHolderSelector"));
        Assert.assertTrue(sortBy.isDisplayed(), "Field Sort By isn't displayed");
    }

    @Test(dataProvider = "dataForLogin", dataProviderClass = DataProviderClass.class)
    public void rmsysLoginWithDataProviderTest(String userName, String password) {
        WebElement userNameFildByIdLocator = driver.findElement(By.id("Username"));
        WebElement passwordFildByIdLocator = driver.findElement(By.id("Password"));
        WebElement logInButton = driver.findElement(By.id("SubmitButton"));

        userNameFildByIdLocator.sendKeys(userName);
        passwordFildByIdLocator.sendKeys(password);
        logInButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), URL_HOME_PAGE,"[Current URL is different from " + URL_HOME_PAGE + "]");
    }
}

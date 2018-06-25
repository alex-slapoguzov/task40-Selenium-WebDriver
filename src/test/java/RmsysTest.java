import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class RmsysTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private String URL = "https://192.168.100.26/";
    private String userName = "EugenBorisik";
    private String password = "qwerty12345";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
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

        WebElement singOutLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#welcomeDivContent .sign-out")));
    }
}

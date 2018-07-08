import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class AlertTest {

    private final static String SUCCESSFULY_MESSAGE = "You successfuly clicked an alert";
    private final static String OK_MESSAGE = "You clicked: Ok";
    private final static String TEXT = "Hello world!";
    private final static String YOU_ENTERED = "You entered: ";
    private final static String URL = "https://the-internet.herokuapp.com/javascript_alerts";
    private final static By jsAlertButtonLocator = By.xpath("//button[@onclick=\"jsAlert()\"]");
    private final static By jsConfirmButtonLocator = By.xpath("//button[@onclick=\"jsConfirm()\"]");
    private final static By jsPromptButtonLocator = By.xpath("//button[@onclick=\"jsPrompt()\"]");
    private final static By inputResult = By.xpath("//p[@id=\"result\"]");
    private WebDriver driver;
    private Alert alert;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void openTab() {
        driver.get(URL);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

    @Test
    public void JsAlertTest() {
        driver.findElement(jsAlertButtonLocator).click();
        alert = driver.switchTo().alert();
        alert.accept();
        String actualMessage = driver.findElement(inputResult).getText();
        Assert.assertEquals(actualMessage, SUCCESSFULY_MESSAGE, "Message is different!");
    }

    @Test
    public void JsConfirmTest() {
        driver.findElement(jsConfirmButtonLocator).click();
        alert = driver.switchTo().alert();
        alert.accept();
        String actualMessage = driver.findElement(inputResult).getText();
        Assert.assertEquals(actualMessage, OK_MESSAGE, "Message is different!");
    }

    @Test
    public void JsPromptTest() {
        driver.findElement(jsPromptButtonLocator).click();
        alert = driver.switchTo().alert();
        alert.sendKeys(TEXT);
        alert.accept();
        String actualMessage = driver.findElement(inputResult).getText();
        Assert.assertEquals(actualMessage, YOU_ENTERED + TEXT, "Message is different!");
    }
}

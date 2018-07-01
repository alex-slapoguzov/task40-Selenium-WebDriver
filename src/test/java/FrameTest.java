import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class FrameTest {

    private final static String URL = "https://the-internet.herokuapp.com/iframe";
    private final static String TEXT = "Hello world!";
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

    @Test
    public void fremesTest() {
        WebElement frameLocator = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(frameLocator);
        WebElement inputField = driver.findElement(By.id("tinymce"));
        inputField.sendKeys(TEXT);
        Assert.assertTrue(inputField.getText().contains(TEXT), "Text " + "[" + TEXT + "]" + " is different!");
    }
}

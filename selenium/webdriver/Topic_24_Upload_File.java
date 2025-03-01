package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import javax.print.DocFlavor;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Topic_24_Upload_File {
    WebDriver driver;

    String projectPath = System.getProperty("user.dir");
    String uploadPath = projectPath + "\\uploadFiles\\";
    String bitcoinName = "bitcoin.png";
    String jackName = "jack.jpg";
    String satoshiName = "satoshi-nakamoto.jpg";

    String bitcoinPath = uploadPath + bitcoinName;
    String jackPath = uploadPath + jackName;
    String satoshiPath = uploadPath + satoshiName;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Single() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFile = By.xpath("//input[@type='file']");

        // mới load file lên
        driver.findElement(uploadFile).sendKeys(bitcoinPath);
        driver.findElement(uploadFile).sendKeys(jackPath);
        driver.findElement(uploadFile).sendKeys(satoshiPath);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + bitcoinName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + jackName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + satoshiName + "']")).isDisplayed());

        // upload từng file
        List<WebElement> startButtons = driver.findElements(By.xpath("//button[@class='btn btn-primary start']//span[text()='Start']"));

        for(WebElement button : startButtons){
            button.click();
            Thread.sleep(2000);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + bitcoinName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + jackName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + satoshiName + "']")).isDisplayed());
    }

    @Test
    public void TC_01_Multiple() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFile = By.xpath("//input[@type='file']");

        // mới load file lên
        driver.findElement(uploadFile).sendKeys(bitcoinPath + "\n" + jackPath + "\n" + satoshiPath);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + bitcoinName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + jackName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + satoshiName + "']")).isDisplayed());

        // upload từng file
        List<WebElement> startButtons = driver.findElements(By.xpath("//button[@class='btn btn-primary start']//span[text()='Start']"));

        for(WebElement button : startButtons){
            button.click();
            Thread.sleep(2000);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + bitcoinName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + jackName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + satoshiName + "']")).isDisplayed());
    }

    @Test
    public void TC_03_Multiple() throws InterruptedException {
        driver.get("https://limewire.com/");

        By uploadFile = By.xpath("//label[contains(@for,'files')]/preceding-sibling::input");

        // mới load file lên
        driver.findElement(uploadFile).sendKeys(bitcoinPath + "\n" + jackPath + "\n" + satoshiPath);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

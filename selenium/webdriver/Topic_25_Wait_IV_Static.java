package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_25_Wait_IV_Static {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {
        // Step 1: click vào button Start
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Loading icon xuất hiện trong 5s
        sleepInSecond(0);

        // Verify Hello World Text
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_02() throws InterruptedException {
        // Step 1: click vào button Start
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Loading icon xuất hiện trong 5s
        sleepInSecond(3);

        // Verify Hello World Text
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_03() throws InterruptedException {
        // Step 1: click vào button Start
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Loading icon xuất hiện trong 5s
        sleepInSecond(5);

        // Verify Hello World Text
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_04() throws InterruptedException {
        // Step 1: click vào button Start
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Loading icon xuất hiện trong 5s
        sleepInSecond(10);

        // Verify Hello World Text
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    public void sleepInSecond(long timeInSecond) throws InterruptedException {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

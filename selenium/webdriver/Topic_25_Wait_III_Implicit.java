package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_25_Wait_III_Implicit {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void TC_01() throws InterruptedException {
        // Step 1: click vào button Start
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Loading icon xuất hiện trong 5s

        // Verify Hello World Text
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        // Step 1: click vào button Start
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Loading icon xuất hiện trong 5s

        // Verify Hello World Text
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_03() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // Step 1: click vào button Start
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Loading icon xuất hiện trong 5s

        // Verify Hello World Text
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_04() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Step 1: click vào button Start
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Loading icon xuất hiện trong 5s

        // Verify Hello World Text
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

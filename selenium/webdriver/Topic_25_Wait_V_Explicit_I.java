package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_25_Wait_V_Explicit_I {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01() throws InterruptedException {
        // chờ cho 1 Alert được xuất hiện trong HTML + sau đó switch vào
        explicitWait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        // element clickable (button/ checkbox/ radio/ link/ image/...)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));
        driver.findElement(By.cssSelector("")).click();

        // element visible (All element)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());

        // element selected (checkbox/ radio)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isSelected());

        // invisible (All element)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isDisplayed());

        // element size
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 15));
        Assert.assertEquals(driver.findElements(By.cssSelector("")).size(), 15);

        // attribute
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""), "value", "John"));
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomAttribute("value"), "John");

        // text
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), "Selenium"));
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(), "Selenium");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

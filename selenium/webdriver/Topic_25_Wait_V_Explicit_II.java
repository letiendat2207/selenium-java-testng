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

public class Topic_25_Wait_V_Explicit_II {
    WebDriver driver;
    WebDriverWait explicitWait;

    By startButton = By.cssSelector("div#start>button");
    By loadingIcon = By.cssSelector("div#loading");
    By helloWorldText = By.cssSelector("div#finish>h4");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Thời gian Explicit bằng 0")
    public void TC_01() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(0));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        driver.findElement(startButton).click();

        // 1 - chờ cho step trước hoàn thành (loading icon biến mất) - không quan tâm step sau (hello text)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

        // 2 - chờ cho step sau xuất hiện (hello text hiển thị) - không quan tâm step trước (loading icon)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldText));

        Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");
    }

    @Test(description = "Thời gian Explicit ngắn hơn điều kiện xảy ra")
    public void TC_02() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        driver.findElement(startButton).click();

        // 1 - chờ cho step trước hoàn thành (loading icon biến mất) - không quan tâm step sau (hello text)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

        // 2 - chờ cho step sau xuất hiện (hello text hiển thị) - không quan tâm step trước (loading icon)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldText));

        Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");

    }

    @Test(description = "Thời gian Explicit bằng thời gian điều kiện xảy ra")
    public void TC_03() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        driver.findElement(startButton).click();

        // 1 - chờ cho step trước hoàn thành (loading icon biến mất) - không quan tâm step sau (hello text)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

        // 2 - chờ cho step sau xuất hiện (hello text hiển thị) - không quan tâm step trước (loading icon)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldText));

        Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");

    }

    @Test(description = "Thời gian Explicit dài hơn điều kiện xảy ra")
    public void TC_04() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        driver.findElement(startButton).click();

        // 1 - chờ cho step trước hoàn thành (loading icon biến mất) - không quan tâm step sau (hello text)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

        // 2 - chờ cho step sau xuất hiện (hello text hiển thị) - không quan tâm step trước (loading icon)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldText));

        Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

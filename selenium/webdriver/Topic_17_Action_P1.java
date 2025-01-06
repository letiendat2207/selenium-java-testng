package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class Topic_17_Action_P1 {
    WebDriver driver;
    Select select;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:/Users/DAT/AppData/Local/Microsoft/Edge/User Data/");
        edgeOptions.addArguments("--profile-directory=Profile 5");
        driver = new EdgeDriver(edgeOptions);

        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        action = new Actions(driver);
    }

    @Test
    public void TC_01_Hover() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        Thread.sleep(3000);

        action.moveToElement(driver.findElement(By.cssSelector("input#age")))
                .pause(Duration.ofSeconds(3))
                .perform();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover() throws InterruptedException {
        driver.get("http://www.myntra.com/");
        Thread.sleep(5000);

        action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']")))
                .pause(Duration.ofSeconds(3))
                .perform();
        Thread.sleep(3000);

        action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']")))
                .perform();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(), "Kids Home Bath");
    }

    @Test
    public void TC_03_Hover() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        Thread.sleep(5000);

        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu")))
                .pause(Duration.ofSeconds(3))
                .perform();
        Thread.sleep(3000);

        action.moveToElement(driver.findElement(By.cssSelector("a[title='Sách Trong Nước']")))
                .pause(Duration.ofSeconds(3))
                .perform();
        Thread.sleep(3000);

        action.click(driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Kỹ Năng Sống']")))
                .perform();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Tâm lý - Kỹ năng sống']/parent::li/following-sibling::li/strong")).getText(),
                "KỸ NĂNG SỐNG");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
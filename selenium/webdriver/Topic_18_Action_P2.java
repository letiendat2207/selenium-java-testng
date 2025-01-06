package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_18_Action_P2 {
    WebDriver driver;
    Select select;
    Actions action;

    @BeforeClass
    public void beforeClass() {
//        EdgeOptions edgeOptions = new EdgeOptions();
//        edgeOptions.addArguments("--user-data-dir=C:/Users/DAT/AppData/Local/Microsoft/Edge/User Data/");
//        edgeOptions.addArguments("--profile-directory=Profile 5");
//        driver = new EdgeDriver(edgeOptions);

        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        action = new Actions(driver);
    }

    @Test
    public void TC_01_Click() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        Thread.sleep(3000);

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable li"));
        action.clickAndHold(numbers.get(0)).pause(Duration.ofSeconds(2))
                        .moveToElement(numbers.get(3)).pause(Duration.ofSeconds(2))
                        .release().perform();

        Thread.sleep(1000);

        List<WebElement> numbersSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
        Assert.assertEquals(numbersSelected.size(), 4);
    }

    @Test
    public void TC_02_Click() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        Thread.sleep(3000);

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable li"));

        String osName = System.getProperty("os.name");
        Keys keys = null;

        if(osName.contains("Windows")){
            keys = Keys.CONTROL;
        } else{
            keys = Keys.COMMAND;
        }

        action.keyDown(keys).perform();
        action.click(numbers.get(0)).pause(Duration.ofSeconds(1))
                .click(numbers.get(2)).pause(Duration.ofSeconds(1))
                .click(numbers.get(5)).pause(Duration.ofSeconds(1))
                .click(numbers.get(10)).pause(Duration.ofSeconds(1))
                        .perform();
        action.keyUp(keys).perform();
        Thread.sleep(1000);

        List<WebElement> numbersSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
        Assert.assertEquals(numbersSelected.size(), 4);
    }

    @Test
    public void TC_03_Double_Click() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        Thread.sleep(3000);

        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
        if(driver.toString().contains("Firefox")){
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", (doubleClickButton));
        }

        action.doubleClick(doubleClickButton).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_04_Right_Click() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        Thread.sleep(3000);

        // verify quit menu không hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        // click chuột phải
        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one")))
                .pause(Duration.ofSeconds(2)).perform();

        // verify quit menu hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        // hover vào quit menu
        action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit")))
                .pause(Duration.ofSeconds(2))
                .perform();

        // verify quit menu có sự kiện hover
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        // click vào quit menu
        action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit")))
                .pause(Duration.ofSeconds(2)).perform();

        // accept alert
        driver.switchTo().alert().accept();
        Thread.sleep(3000);

        // verify quit menu không còn hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

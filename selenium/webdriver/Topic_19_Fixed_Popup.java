package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_19_Fixed_Popup {
    WebDriver driver;
    Select select;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_Popup_NotInDom() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(3000);
        By loginDialog = By.cssSelector("div#custom-dialog div[role='dialog']");

        Assert.assertTrue(driver.findElement(loginDialog).isDisplayed());

        driver.findElement(By.cssSelector("input[autocomplete='username']")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("input[autocomplete='new-password']")).sendKeys("automationfc");
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("div.auth-form button[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");
        Thread.sleep(3000);


        driver.findElement(By.cssSelector("div#custom-dialog h2>button")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElements(loginDialog).size(), 0);
    }

    @Test
    public void TC_02_Fixed_Popup_InDom() throws InterruptedException {
        driver.get("https://zingpoll.com/");

        driver.findElement(By.cssSelector("a#Loginform")).click();
        Thread.sleep(3000);

        By loginDialog = By.cssSelector("form#loginForm");
        Assert.assertTrue(driver.findElement(loginDialog).isDisplayed());

        driver.findElement(By.cssSelector("button[onclick='ResetForm()']")).click();
        Thread.sleep(3000);
        Assert.assertFalse(driver.findElement(loginDialog).isDisplayed());
    }

    @Test
    public void TC_03_Tiki() throws InterruptedException {
        driver.get("https://tiki.vn/");

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(3000);

        By loginDialog = By.cssSelector("div[role='dialog']");
        Assert.assertTrue(driver.findElement(loginDialog).isDisplayed());

        Thread.sleep(3000);
        driver.findElement(By.xpath("//p[text()='Đăng nhập bằng email']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][2]")).getText(), "Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("img.close-img")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElements(loginDialog).size(),0);


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

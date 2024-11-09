package webdriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_07_WebBrowser_Element {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Verify_Url() {
        // Truy cập vào trang
        driver.get("https://live.techpanda.org/");

        // Click MY ACCOUNT link tại footer
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Verify url của Login Page
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");

        // Click CREATE AN ACCOUNT button
        driver.findElement(By.xpath("//a[@class='button']")).click();

        // Verify url của Register Page
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Verify_Title() {
        // Truy cập vào trang
        driver.get("https://live.techpanda.org/");

        // Click MY ACCOUNT link tại footer
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Verify title của Login Page
        Assert.assertEquals(driver.getTitle(), "Customer Login");

        // Click CREATE AN ACCOUNT button
        driver.findElement(By.xpath("//a[@class='button']")).click();

        // Verify title của Register Page
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_03_Navigate_Function() {
        // Truy cập vào trang
        driver.get("https://live.techpanda.org/");

        // Click MY ACCOUNT link tại footer
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Click CREATE AN ACCOUNT button
        driver.findElement(By.xpath("//a[@class='button']")).click();

        // Verify url của Register Page
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");

        // Back lại trang Login Page
        driver.navigate().back();

        // Verify url của Login Page
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");

        // Forward tới trang Register Page
        driver.navigate().forward();

        // Verify title của Register Page
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_04_Get_Page_Source_Code() {
        // Truy cập vào trang
        driver.get("https://live.techpanda.org/");

        // Click MY ACCOUNT link tại footer
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Verify Login Page chứa text
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        // Click CREATE AN ACCOUNT button
        driver.findElement(By.xpath("//a[@class='button']")).click();

        // Verify Register Page chứa text
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

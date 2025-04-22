package basic;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_11_MultiplePlatform {

    WebDriver driver;
    Select select;
    String environmentUrl;
    Platform platform;

    @Parameters({"browser", "environmentName", "platformName"})
    @BeforeClass
    public void beforeClass(String browserName, @Optional("Prod") String environmentName, @Optional("Windows") String platformName) {

        System.out.println("Browser name = " + browserName);
        System.out.println("Environment Name = " + environmentName);
        System.out.println("Platform Name = " + platformName);

        switch (browserName.toUpperCase()){
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "CHROME":
                driver = new ChromeDriver();
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not supported");
        }

        switch (environmentName.toUpperCase()){
            case "DEV":
                environmentUrl = "https://dev.fahasa.com";
                break;
            case "TESTING":
                environmentUrl = "https://test.fahasa.com";
                break;
            case "STAGING":
                environmentUrl = "https://staging.fahasa.com";
                break;
            case "PROD":
                environmentUrl = "https://www.fahasa.com";
                break;
            default:
                throw new RuntimeException("Environment name is not supported");
        }

        switch (platformName.toUpperCase()){
            case "WINDOWS":
                platform = Platform.WINDOWS;
                break;
            case "MAC":
                platform = Platform.MAC;
                break;
            case "LINUX":
                platform = Platform.LINUX;
                break;
            case "ANDROID":
                platform = Platform.ANDROID;
                break;
            case "IOS":
                platform = Platform.IOS;
                break;
            default:
                throw new RuntimeException("Platform name is not supported");
        }

        driver.manage().window().maximize();

        // đi đến trang Fahasa
        driver.get(environmentUrl + "/customer/account/create");
    }

    @Test
    public void TC_01_Button() throws InterruptedException {

        // email và password
        String email = "dat@gmail.com";
        String password = "123456@X";

        // click vào tab đăng nhập
        driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

        // chờ cho đến khi button login hiển thị trong vòng 10s, button này chưa thể click được
        By loginButton = By.cssSelector("button.fhs-btn-login");
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.
                        not(ExpectedConditions.elementToBeClickable(loginButton)));

        // verify button login bị disabled
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        // tìm đến background color của button login
        String loginButtonColor = driver.findElement(loginButton).getCssValue("background-color");

        // chuyển màu background color của button login từ hệ RGB sang hệ HEX
        String loginButtonColorHex = Color.fromString(loginButtonColor).asHex().toUpperCase();

        // verify màu background color của button login
        Assert.assertEquals(loginButtonColorHex, "#000000");

        // điền username và password
        driver.findElement(By.cssSelector("input#login_username")).sendKeys(email);
        driver.findElement(By.cssSelector("input#login_password")).sendKeys(password);

        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(loginButton));

        // verify button login được enabled
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        loginButtonColor = driver.findElement(loginButton).getCssValue("background-color");
        loginButtonColorHex = Color.fromString(loginButtonColor).asHex().toUpperCase();

        // veirfy màu background color của button login
        Assert.assertEquals(loginButtonColorHex, "#C92127");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

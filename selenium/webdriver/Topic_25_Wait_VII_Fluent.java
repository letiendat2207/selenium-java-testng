package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_25_Wait_VII_Fluent {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        // Trong vòng 5s cứ mỗi 1/3s sẽ tìm chữ HelloWorld hiển thị
        fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(333))
                .ignoring(NoSuchElementException.class);

        String helloText = fluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                System.out.println("-------------Đang tìm element và get text----------");
                return webDriver.findElement(By.cssSelector("div#finish>h4")).getText();
            }
        });

        Assert.assertEquals(helloText, "Hello World!");

        boolean helloStatus = fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                System.out.println("-------------Đang tìm element và get text----------");
                return webDriver.findElement(By.cssSelector("div#finish>h4")).getText().equals("Hello World!");
            }
        });

        Assert.assertTrue(helloStatus);
    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://automationfc.github.io/fluent-wait/");

        // Đếm ngược giây từ 12s về 0s => thỏa mãn điều kiện
        // Cứ 1/10s kiểm tra status của count down time text
        fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        boolean countDownTextStatus = fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                System.out.print("Đang tìm element và get text: ");
                String countDownText = webDriver.findElement(By.cssSelector("div#javascript_countdown_time")).getText();
                System.out.println(countDownText);
                return countDownText.equals("01:01:00");
            }
        });

        Assert.assertTrue(countDownTextStatus);
    }

    // Tìm element với Polling Time là 1s kiểm tra 1 lần
//    public WebElement findElement(By by){
//        // Khai báo + khởi tạo
//        FluentWait fluentWait = new FluentWait(driver);
//
//        // Config time / Polling / Exception
//        fluentWait.withTimeout(Duration.ofSeconds(15))
//                .pollingEvery(Duration.ofSeconds(1))
//                .ignoring(NoSuchElementException.class);
//
//        // Condition
//        return (WebElement) fluentWait.until(new Function<WebDriver, WebElement>() {
//            @Override
//            public WebElement apply(WebDriver driver) {
//                return driver.findElement(by);
//            }
//        });
//    }

    // Kiểm tra Element hiển thị: isDisplayed()
//    public boolean isElementDisplayed(By by){
//        // Khai báo + khởi tạo
//        FluentWait fluentWait = new FluentWait(driver);
//
//        // Config time / Polling / Exception
//        fluentWait.withTimeout(Duration.ofSeconds(15))
//                .pollingEvery(Duration.ofMillis(100))
//                .ignoring(NoSuchElementException.class);
//
//        return (boolean) fluentWait.until(new Function<WebDriver, Boolean>() {
//            @Override
//            public Boolean apply(WebDriver driver) {
//                return driver.findElement(by).isDisplayed();
//            }
//        });
//    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

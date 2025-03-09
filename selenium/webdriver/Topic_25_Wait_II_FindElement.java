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

public class Topic_25_Wait_II_FindElement {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        // explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_FindElement_OneElement() throws InterruptedException {
        // Tìm thấy 1 element
        // Vào sẽ tìm thấy element ngay chứ không cần chờ hết timeout của Implicit
        driver.findElement(By.cssSelector("input#email"));
    }

    @Test
    public void TC_02_FindElement_NoElement() throws InterruptedException {
        // Tìm thấy 0 element
        // Vào sẽ không thấy element và sẽ tìm đi tìm lại mỗi 0.5s 1 lần cho đến khi hết time-out là 10s
        // Hết time-out sẽ đánh failed test case tại vị trí này luôn và show ra lỗi NoSuchElementException
        driver.findElement(By.cssSelector("input#emailAddress"));
    }

    @Test
    public void TC_03_FindElement_Elements() throws InterruptedException {
        // Tìm thấy nhiều hơn 1 element
        // Nó sẽ luôn thao tác với element đầu tiên
        driver.findElement(By.cssSelector("input:not([type='hidden'])")).sendKeys("Selenium");
        // driver.findElement(By.cssSelector("a[title='My Account']")).click();
    }

    @Test
    public void TC_04_FindElements_OneElement() throws InterruptedException {
        // Tìm thấy 1 element
        // Trả về 1 element và cũng không cần chờ hết time-out
        List<WebElement> elementList = driver.findElements(By.cssSelector("input#email"));
        System.out.println(elementList.size());
    }

    @Test
    public void TC_05_FindElements_NoElement() throws InterruptedException {
        // Tìm thấy 0 element
        // Vào sẽ không thấy element và sẽ tìm đi tìm lại mỗi 0.5s 1 lần cho đến khi hết time-out là 10s
        // Không đánh failed testcase mà sẽ trả về 1 list rỗng (empty) = 0
        List<WebElement> elementList = driver.findElements(By.cssSelector("input#emailAddress"));
        System.out.println(elementList.size());
        Assert.assertEquals(elementList.size(), 0);
    }

    @Test
    public void TC_06_FindElements_Elements() throws InterruptedException {
        // Tìm thấy nhiều hơn 1 element
        List<WebElement> checkboxElements = driver.findElements(By.cssSelector("input:not([type='hidden'])"));
        for (WebElement element : checkboxElements){
            element.sendKeys("Selenium");
        }

        List<WebElement> myAccountList =  driver.findElements(By.cssSelector("a[title='My Account']"));
        myAccountList.get(1).click();
    }

    @AfterClass
    public void afterClass() {
       driver.quit();
    }
}

package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import javax.print.DocFlavor;
import javax.swing.plaf.TableHeaderUI;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Topic_25_Wait_I_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Visible() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(5000);

        // Điều kiện 1: Element có hiển thị trên UI và có trong HTML
        // Nếu 1 element thỏa mãn điều kiện số 1, thì element đó gọi là hiển thị (visible/ displayed) => có trên UI
        // Vì có trên UI thì 100% phải có trong HTML

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(3000);
        // chờ cho Element hiển thị (visible)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='tel']")));
    }

    @Test
    public void TC_02_Invisible_HTML() throws InterruptedException {
        driver.get("https://live.techpanda.org/");
        Thread.sleep(5000);

        // Điều kiện 2: Element không hiển thị trên UI nhưng vẫn có trong HTML
        // Nếu 1 element thỏa mãn điều kiện số 2 hoặc 3 thì element đó gọi là Không Hiển Thị (Invisible/ Undisplayed) => Không có trên UI

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#header-account a[title='My Account']")));
    }

    @Test
    public void TC_03_Invisible_Not_HTML() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(5000);

        // Điều kiện 3: Element ko hiển thị trên UI và ko có trong HTML
        // Nếu 1 element thỏa mãn điều kiện số 2 hoặc 3 thì element đó gọi là Không Hiển Thị (Invisible/ Undisplayed) => Không có trên UI

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        Thread.sleep(3000);

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='tel']")));
    }

    @Test
    public void TC_04_Present() throws InterruptedException {
        driver.get("https://live.techpanda.org/");
        Thread.sleep(5000);

        // Nếu 1 element thỏa mãn điều kiện số 1 hoặc điều kiện 2 thì element đó gọi là Present => Có trong HTML

        // Điều kiện 1: Element có hiển thị trên UI và có trong HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.footer a[title='My Account']")));

        // Điều kiện 2: Element không hiển thị trên UI nhưng vẫn có trong HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#header-account a[title='My Account']")));
    }

    @Test
    public void TC_05_Staleness() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(5000);

        // Điều kiện 3: Element ko hiển thị trên UI và ko có trong HTML
        // Điều kiện cần: invisible not in HTML
        // Điều kiện đủ: Element tại thời điểm A nó có trong HTML (Present) và sau đó dùng element kiểm tra tại thời điểm B thì ko còn trong HTML nữa

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(3000);

        // phone textbox xuất hiện trong HTML (present)
        WebElement phoneTextbox = driver.findElement(By.cssSelector("input[name='tel']"));

        // click vào button login với email
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        Thread.sleep(3000);

        explicitWait.until(ExpectedConditions.stalenessOf(phoneTextbox));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

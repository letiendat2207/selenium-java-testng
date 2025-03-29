package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Date;

public class Topic_25_Wait_VI_Mixing {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Only_Implicit() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Dùng timeout của Implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // nhận 10s của Implicit
        driver.findElement(By.cssSelector("input#email"));
    }

    @Test
    public void TC_02_Only_Explicit() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Dùng timeout của Explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // nhận 0s của Implicit vì TC02 ko set Implicit mà set Explicit
        driver.findElement(By.cssSelector("input#email"));
    }

    @Test
    public void TC_03_Implicit_More_Explicit() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Dùng timeout của Implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // Dùng timeout của Explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Start = " + getDateTimeNow());

        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_04_Implicit_Less_Explicit() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Dùng timeout của Implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Dùng timeout của Explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(13));

        System.out.println("Start = " + getDateTimeNow());

        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_05_Implicit_Equal_Explicit() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Dùng timeout của Implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // Dùng timeout của Explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(20));

        System.out.println("Start = " + getDateTimeNow());

        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_06_Only_Explicit_By() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Dùng timeout của Explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Start = " + getDateTimeNow());

        try {
        // Tham số truyền vào là By thì dùng timeout của Explicit
        // Tìm ko thấy Element thì mất 10s timeout
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_07_Only_Explicit_WebElement() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Dùng timeout của Explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Start = " + getDateTimeNow());

        try {
        // Tham số truyền vào là WebElement thì dùng timeout của Implicit
        // Mà Implicit ko set thì ko tìm thấy nên timeout là 0s
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#advice-required-entry-email"))));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    public String getDateTimeNow() {
        Date date = new Date();
        return  date.toString();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

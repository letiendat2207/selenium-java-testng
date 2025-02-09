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

public class Topic_19_Random_Popup {
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
    public void TC_01_DeHieu_IN_DOM() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        Thread.sleep(5000);

        By popup = By.cssSelector("div.modal-content");

        if(driver.findElement(popup).isDisplayed()){
            // Điều kiện: Nếu popup hiển thị thì close đi rồi click vào đăng nhập
            System.out.println("Điều kiện: Nếu popup hiển thị thì close đi rồi click vào đăng nhập");
            driver.findElement(By.cssSelector("div.modal-content button.close")).click();
            Thread.sleep(3000);
        }

        // Nếu popup không hiển thị thì click vào đăng nhập luôn
        System.out.println("Nếu popup không hiển thị thì click vào đăng nhập luôn");
        driver.findElement(By.cssSelector("a.signin-site-menu")).click();

        // Verify form đăng nhập hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("input#email_login_v3")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#password_login_v3")).isDisplayed());
    }

    @Test
    public void TC_02_KMPlayer_IN_DOM() throws InterruptedException {
        driver.get("https://www.kmplayer.com/home");
        Thread.sleep(5000);

        By popup = By.cssSelector("div.pop-container");

        if(driver.findElement(popup).isDisplayed()){
            // Điều kiện: Nếu popup hiển thị thì close đi rồi click vào đăng nhập
            System.out.println("Điều kiện: Nếu popup hiển thị thì close đi rồi click vào đăng nhập");
            driver.findElement(By.cssSelector("div.close")).click();
            Thread.sleep(3000);
        }

        // Nếu popup không hiển thị thì hover vào PC và click vào KMPlayer
        new Actions(driver).moveToElement(driver.findElement(By.cssSelector("li.pc.pc64x"))).perform();
        driver.findElement(By.xpath("//li[@class='pc']/a[text()='KMPlayer']")).click();

        // Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("div.sub>h1")).getText(), "KMPlayer - Video Player for PC");
    }

    @Test
    public void TC_03_JavaCodeGeeks_Not_In_DOM() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");

        Thread.sleep(5000);

        By popup = By.xpath("//div[@data-title='Newsletter-Books Anime Brief' and @data-page='1']");

        if(driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()){
            // Điều kiện: Nếu popup hiển thị thì close đi rồi click vào đăng nhập
            System.out.println("Điều kiện: Nếu popup hiển thị thì close đi rồi click vào đăng nhập");
            driver.findElement(By.xpath("//a[text()='×']")).click();
            Thread.sleep(3000);
        }

        // Nếu popup không hiển thị
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Selenium");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button#search-submit")).click();
        Thread.sleep(3000);

        List<WebElement> articles = driver.findElements(By.cssSelector("ul#posts-container h2.post-title>a"));
        for (WebElement article : articles){
            System.out.println(article.getText());
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

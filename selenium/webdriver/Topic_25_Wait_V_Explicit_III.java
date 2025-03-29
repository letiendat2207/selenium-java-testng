package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class Topic_25_Wait_V_Explicit_III {
    WebDriver driver;
    WebDriverWait explicitWait;

    String projectPath = System.getProperty("user.dir");
    String uploadPath = projectPath + "\\uploadFiles\\";
    String bitcoinName = "bitcoin.png";
    String jackName = "jack.jpg";
    String satoshiName = "satoshi-nakamoto.jpg";

    String bitcoinPath = uploadPath + bitcoinName;
    String jackPath = uploadPath + jackName;
    String satoshiPath = uploadPath + satoshiName;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait cho Calendar hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));

        // Wait cho text được hiển thị
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "No Selected Dates to display."));
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");

        // Wait để click vào 1 ngày/ tháng/ năm hiện tại
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='23']")));
        driver.findElement(By.xpath("//td/a[text()='23']")).click();

        // Wait để loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id, 'RadCalendar1')]/div[@class='raDiv']")));

        // Wait cho text được cập nhật lại ngày hiện tại
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "Sunday, March 23, 2025"));
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Sunday, March 23, 2025");

        // Wait cái ngày được chọn đã được selected rồi - ko phải dạng checkbox nên verify bằng Attribute
        explicitWait.until(ExpectedConditions.attributeContains(By.xpath("//a[text()='23']/parent::td"), "class", "rcSelected"));
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='23']/parent::td")).getDomAttribute("class").contains("rcSelected"));
    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://gofile.io/?t=uploadFiles");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Wait cho tất cả loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#index_loader"))));

        // Load file lên
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(bitcoinPath + "\n" + jackPath + "\n" + satoshiPath);

        // Wait cho tất cả thanh progress bar biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#fileList div.file-progressbar"))));

        // Wait cho text Upload Complete xuất hiện
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.text-center>h2"), "Upload Complete"));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.text-center>h2")).getText(), "Upload Complete");

        driver.get(driver.findElement(By.cssSelector("a.linkSuccessCard")).getDomAttribute("href"));

        // Wait cho loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("main#index_main div.animate-spin"))));


        // Wait ảnh đầu tiên xuất hiện thì 3 ảnh sẽ xuất hiện
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='truncate']/a[text()='" + bitcoinName + "']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']/a[text()='" + bitcoinName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']/a[text()='" + jackName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']/a[text()='" + satoshiName + "']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

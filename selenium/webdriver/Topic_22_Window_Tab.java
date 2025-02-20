package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.print.DocFlavor;
import java.time.Duration;
import java.util.Set;

public class Topic_22_Window_Tab {
    WebDriver driver;
    Select select;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        System.out.println("Driver ID = " + driver.toString());
    }

    @Test
    public void TC_01_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // lấy ra window ID của 1 cửa sổ đang Active (driver đang đứng đó) - đang ở tab Github
        String githubID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(3000);

        // lấy ra window ID của 2 tab / window
        switchToWindowByID(githubID);
        driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Selenium");

        // quay lại tab Github
        String googleID = driver.getWindowHandle();
        switchToWindowByID(googleID);
        driver.findElement(By.cssSelector("input#mail")).sendKeys("letiendat@gmail.com");

        System.out.println("URL Github = " + driver.getCurrentUrl());
        System.out.println("title Github = " + driver.getTitle());

        // click chuyển tab qua Facebook
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(3000);

        String facebookTitle = "Facebook – log in or sign up";
        switchToWindowByTitle(facebookTitle);
        driver.findElement(By.cssSelector("input#email")).sendKeys("letiendat@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("letiendat");

        System.out.println("URL Facebook = " + driver.getCurrentUrl());
        System.out.println("title Facebook = " + driver.getTitle());

        // close đi 2 tab Facebook và Google, giữ lại Github
        //String facebookID = driver.getWindowHandle();
        closAllExceptMain(githubID);

    }

    @Test
    public void TC_02_TechPanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");

        // parent page ID
        String mobilePageID = driver.getWindowHandle();

        // click vào Mobile tab
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        // click vào add to compare của điện thoại Sony Xperia
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();

        // verify message xuất hiện
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");

        // click vào add to compare của điện thoại Samsung Galaxy
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();

        // click vào compare button
        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        Thread.sleep(5000);

        // switch qua comparison page
        switchToWindowByTitle("Products Comparison List - Magento Commerce");

        // verify title của window mới sau khi switch qua
//        System.out.println("URL Compare = " + driver.getCurrentUrl());
//        System.out.println("title Compare = " + driver.getTitle());

        String comparisonPageTitle = driver.getTitle();
        Assert.assertEquals(comparisonPageTitle,  "Products Comparison List - Magento Commerce");

        // close window mới và chuyển về window parent
        closAllExceptMain(mobilePageID);

        // click Clear All và accept Alert
        driver.findElement(By.xpath("//a[contains(text(),'Clear All')]")).click();
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        Thread.sleep(3000);

        // verify message xuất hiện
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");

    }

    @Test
    public void TC_03_Cambridge() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");
        String parentID = driver.getWindowHandle();

        driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();

        switchToWindowByTitle("Login");

        driver.findElement(By.xpath("//div[@id='login_content']//input[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@data-bound-to='loginID' and @role='alert']")).getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@data-bound-to='password' and @role='alert']")).getText(), "This field is required");

        closAllExceptMain(parentID);

        driver.findElement(By.xpath("//input[@placeholder='Tìm kiếm Tiếng Anh']")).sendKeys("bitcoin");

        driver.findElement(By.cssSelector("button[type='submit']>i.i-search")).click();

        Assert.assertEquals( driver.findElement(By.cssSelector("div.pr.dictionary span.headword>span.hw.hw")).getText(), "bitcoin");
    }

    @Test
    public void TC_04_Havard() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");
        String parentID = driver.getWindowHandle();
        System.out.println(parentID);
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("a.anon-only")).click();
        Thread.sleep(5000);

        switchToWindowByID(parentID);
        Assert.assertEquals(driver.findElement(By.xpath("//img[@id='prompt-logo-center']/following-sibling::h1")).getText(), "DCE Login Portal");

        closAllExceptMain(parentID);

        Assert.assertEquals(driver.findElement(By.cssSelector("p.sam-wait__message")).getText(), "Authentication was not successful. Please try again.");

        driver.findElement(By.xpath("//button[contains(@class,'sam-wait__close')]")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("Data Science");
        new Select(driver.findElement(By.cssSelector("select#crit-srcdb"))).selectByVisibleText("Extension January & Spring Term 2025");
        new Select(driver.findElement(By.cssSelector("select#crit-session"))).selectByVisibleText("Any Part of Term");
        driver.findElement(By.cssSelector("button#search-button")).click();
        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='CSCI E-63C']/following-sibling::span")).getText(), "Elements of Data Science and Statistical Learning with R");

    }

    // dùng cho 2 tab trở lên
    private void switchToWindowByTitle(String expectedTitle) throws InterruptedException {
        Set<String> allWindows = driver.getWindowHandles();

        for(String window : allWindows){
            driver.switchTo().window(window);
            String pageTitle = driver.getTitle();   // lấy ra title của active window hiện tại
            if(pageTitle.equals(expectedTitle)){
                Thread.sleep(3000);
                break;
            }
        }
    }

    // dùng cho 2 tab/ window
    private void switchToWindowByID(String windowID) {
        Set<String> allWindows = driver.getWindowHandles();

        for(String window : allWindows){
            if(!window.equals(windowID)){
                driver.switchTo().window(window);
                break;
            }
        }
    }

    // clos tất cả tab/window ngoại trừ trang gốc
    private void closAllExceptMain(String windowID) {
        Set<String> allWindows = driver.getWindowHandles();

        for(String window : allWindows){
            if(!window.equals(windowID)){
                driver.switchTo().window(window);
                driver.close();
            }
        }

        driver.switchTo().window(windowID);
    }

//    @AfterClass
//    public void afterClass() {
//        driver.quit();
//    }
}

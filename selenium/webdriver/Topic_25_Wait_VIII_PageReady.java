package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.time.Duration;

public class Topic_25_Wait_VIII_PageReady {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:/Users/DAT/AppData/Local/Microsoft/Edge/User Data/");
        edgeOptions.addArguments("--profile-directory=Profile 7");
        driver = new EdgeDriver(edgeOptions);

        // driver = new EdgeDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Element_Invisible() throws InterruptedException {
        driver.get("https://api.orangehrm.com/");

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#project h1"), "OrangeHRM REST API Documentation"));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");
    }

    @Test
    public void TC_02_All_Element_Invisible() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Nếu như tất cả các page trong application đều có loading icon thì nên viết 1 hàm dùng chung (Reusable Method)
        // Để gọi ra dùng cho tất cả các page

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='username']")));
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
        Assert.assertTrue(isAllLoadingIconInvisible());

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']")));
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        Assert.assertTrue(isAllLoadingIconInvisible());

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span.oxd-topbar-header-breadcrumb>h6"), "PIM"));
        Assert.assertEquals(driver.findElement(By.cssSelector("span.oxd-topbar-header-breadcrumb>h6")).getText(), "PIM");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Leave']")));
        driver.findElement(By.xpath("//span[text()='Leave']")).click();
        Assert.assertTrue(isAllLoadingIconInvisible());

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span.oxd-topbar-header-breadcrumb>h6"), "Leave"));
        Assert.assertEquals(driver.findElement(By.cssSelector("span.oxd-topbar-header-breadcrumb>h6")).getText(), "Leave");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Time']")));
        driver.findElement(By.xpath("//span[text()='Time']")).click();
        Assert.assertTrue(isAllLoadingIconInvisible());

        explicitWait.until(ExpectedConditions.textToBe(By.xpath("//span[@class='oxd-topbar-header-breadcrumb']/h6[contains(@class, 'module')]"), "Time"));
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='oxd-topbar-header-breadcrumb']/h6[contains(@class, 'module')]")).getText(), "Time");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Recruitment']")));
        driver.findElement(By.xpath("//span[text()='Recruitment']")).click();
        Assert.assertTrue(isAllLoadingIconInvisible());

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span.oxd-topbar-header-breadcrumb>h6"), "Recruitment"));
        Assert.assertEquals(driver.findElement(By.cssSelector("span.oxd-topbar-header-breadcrumb>h6")).getText(), "Recruitment");
    }

    @Test
    public void TC_03_Page_Ready() throws InterruptedException {
        driver.get("https://admin-demo.nopcommerce.com");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#Email")));
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();
        Assert.assertTrue(isPageLoadedSuccess());

        // Chuyển qua trang Product
        driver.get("https://admin-demo.nopcommerce.com/Admin/Product/List");
        Assert.assertTrue(isPageLoadedSuccess());

        // Chuyển qua trang Categories
        driver.get("https://admin-demo.nopcommerce.com/Admin/Category/List");
        Assert.assertTrue(isPageLoadedSuccess());

        // Chuyển qua trang ProductReview
        driver.get("https://admin-demo.nopcommerce.com/Admin/ProductReview/List");
        Assert.assertTrue(isPageLoadedSuccess());
    }

    public boolean isAllLoadingIconInvisible(){
        return explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

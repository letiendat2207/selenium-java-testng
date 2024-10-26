package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_WebBrowser_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Browser() {
        //Mở ra 1 URL bất kỳ
        driver.get("https://www.facebook.com/");

        //Đóng browser - không quan tâm có bao nhiêu tab hay windows
        driver.quit();

        //Đóng browser - chỉ đóng tab/window hiện tại
        //Nếu chỉ có 1 tab/window thì cũng tương tự đóng browser
        driver.close();

        //Tìm 1 element với locator là tham số truyền vào
        driver.findElement(By.cssSelector(""));

        //Tìm nhiều element với locator là tham số truyền vào
        driver.findElements(By.cssSelector(""));

        //Sử dụng luôn, không cần lưu trữ
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");

        //Lấy ra URL ở page hiện tại
        String homePageUrl = driver.getCurrentUrl();
        System.out.println("Page URL = " + driver.getCurrentUrl());

        //Lấy ra title ở page hiện tại
        driver.getTitle();
        System.out.println("Page title = " + driver.getTitle());

        //Lấy ra Window ID ở page hiện tại
        driver.getWindowHandle();
        System.out.println("Window ID = " + driver.getWindowHandle());

        //Lấy ra tất cả các Window ID của tất cả các tab / window
        driver.getWindowHandles();

        //Lấy ra source code của page hiện tại
        driver.getPageSource();
        System.out.println("Page Source Code = " + driver.getPageSource());

        WebDriver.TargetLocator switchTo = driver.switchTo();

        //Alert
        driver.switchTo().alert();

        //Frame - iFrame
        //Switch vào Frame/iFrame
        driver.switchTo().frame("");

        //Switch ra trang cha trở lại (trường hợp trang cha chỉ có 1 frame)
        driver.switchTo().defaultContent();

        //Switch từ frame con ra frame cha, nhiều frame lồng nhau
        driver.switchTo().parentFrame();

        //Window - TAB
        driver.switchTo().window("");
        driver.switchTo().newWindow(WindowType.TAB).get("https://www.facebook.com/");   //Selenium version 4
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://www.facebook.com/");    //Selenium version 4

        //Set timeout để tìm element (áp dụng cho 2 hàm findElement/ findElements)
        //Trường hợp không tìm thấy sẽ chờ hết thời gian đã set rồi mới show lỗi
        driver.manage().timeouts().implicitlyWait(Duration.ofNanos(100));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(100));
        driver.manage().timeouts().implicitlyWait(Duration.ofHours(100));
        driver.manage().timeouts().implicitlyWait(Duration.ofDays(100));

        //Set timeout để chờ page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        //Set timeout để chờ cho đoạn code JS được thực thi thành công
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().getScriptTimeout(Duration.ofSeconds(30));

        //Cookie
        driver.manage().getCookies();

        //Browser screen: fullscreen - maximize - minimize
        driver.manage().window().maximize();
        driver.manage().window().minimize();
        driver.manage().window().fullscreen();

        //Set browser có kích thước chiều dài, chiều rông bao nhiêu (Responsive)
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().getSize();

        //Set browser tại ví trí nào đó có tọa độ trên màn hình
        driver.manage().window().setPosition(new Point(0, 10));
        driver.manage().window().getPosition();

        //Selenium log: Browser - Driver - Network - Performance - Server - Client
        driver.manage().logs().getAvailableLogTypes();
        driver.manage().logs().get(LogType.PERFORMANCE);
        driver.manage().logs().get(LogType.BROWSER);
        driver.manage().logs().get(LogType.CLIENT);
        driver.manage().logs().get(LogType.SERVER);
        driver.manage().logs().get(LogType.DRIVER);

        //Quay lại trang trước đó
        driver.navigate().back();

        //Chuyển tiếp trang trước đó
        driver.navigate().forward();

        //Refresh trang hiện tại
        driver.navigate().refresh();

        //Đi đến 1 URL
        driver.navigate().to("https://www.youtube.com/watch?v=bTLVe23UGlM&ab_channel=AutomationFC");
        driver.navigate().to(new URL("https://www.youtube.com/watch?v=bTLVe23UGlM&ab_channel=AutomationFC"));


    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}

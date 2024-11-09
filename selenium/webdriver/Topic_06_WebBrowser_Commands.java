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
        //driver.manage().timeouts().getScriptTimeout(Duration.ofSeconds(30));

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
        //driver.navigate().to(new URL("https://www.youtube.com/watch?v=bTLVe23UGlM&ab_channel=AutomationFC"));

    }

    @Test
    public void TC_02_Web_Element(){
        WebElement element = driver.findElement(By.cssSelector("input#search"));

        // Xóa dữ liệu ở trong 1 editable element (có thể nhập được)
        // textbox / text area / dropdown
        element.clear();

        // Nhập dữ liệu vào 1 editable element (nhập)
        element.sendKeys("Le Tien Dat");

        // 1 - Element cha dùng 1 loại locator - element con dùng 1 loại locator
        driver.findElement(By.
                cssSelector("form#search-form")).findElement(By.cssSelector("input#search"));

        // 2 - Cả cha và con đều chung 1 loại locator
        driver.findElement((By.cssSelector("form#search-form input#search")));

        // Tìm 1 element với locator là tham số truyền vào
        driver.findElement(By.cssSelector(""));

        // Tìm nhiều element với locator là tham số truyền vào
        driver.findElements(By.cssSelector(""));

        // Click lên clickable element
        // Button / Checkbox / Radio / Link / Image / Dropdown / Icon
        element.click();

        // Tương đương với submit thông tin gửi lên server
        // Giả lập hành vi enter của end user
        // Register / Login / Search / ...
        element.submit();

        // Verify thông tin hay dữ liệu đã action
        // Kiểm tra 1 element có hiển thị hay không?
        // Áp dụng cho tất cả các loại element
        element.isDisplayed();

        // Kiểm tra 1 element đã được chọn hay chưa?
        // Áp dụng: Checkbox / radio / dropdown
        element.isSelected();

        // Kiểm tra 1 element có cho phép thao tác lên hay không?
        // Cho phép sửa dữ liệu
        // true = được phép chỉnh sửa hay thao tác
        // false = bị disable
        // test tính năng phân quyền
        element.isEnabled();

        // Lấy dữ liệu

        // Lấy ra chiều rộng/ chiều cao của element
        element.getSize();

        // Lấy ra text của 1 element
        element.getText();
        element.getAttribute("placeholder");    // get text bằng attribute

        // Shadow DOM
        element.getShadowRoot();

        // Dev front end dùng nhiều hơn tester - ít khi dùng
        element.getAriaRole();
        element.getDomProperty("");
        element.getDomAttribute("");
        element.getAccessibleName();

        // Lấy ra thuộc tính liên quan tới CSS
        // Font / Color / Background / Font Size / ...
        element.getCssValue("font-size");

        // Lấy ra vị trí của Element (gốc trên bên trái) so với màn hình browser
        element.getLocation();

        // Tổng hợp của getLocation() và getSize()
        Rectangle elementRect = element.getRect();
        elementRect.getDimension(); // = với getSize()
        elementRect.getPoint(); // = với getLocation()

        // Lấy ra tên thẻ HTML của element
        element.getTagName();

        // Take Screenshot (chụp hình lỗi)
        element.getScreenshotAs(OutputType.FILE);
        element.getScreenshotAs(OutputType.BYTES);
        element.getScreenshotAs(OutputType.BASE64);

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
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

public class Topic_20_Shadow_DOM {
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
    public void TC_01_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/shadow-dom/");
        Thread.sleep(5000);

        System.out.println(driver.findElement(By.xpath("//a[@href='scroll.html']")).getText());

        // Tìm element chứa shadow root đầu tiên
        WebElement firstShadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));

        // Đi tới Shadow root
        SearchContext firstShadowRoot = firstShadowHostElement.getShadowRoot();

        // Đi tới element nằm trong Shadow root đó
        String shadowText = firstShadowRoot.findElement(By.cssSelector("a")).getText();
        System.out.println(shadowText);

        firstShadowRoot.findElement(By.cssSelector("input[type='text']")).sendKeys("Selenium");

        System.out.println(firstShadowRoot.findElement(By.cssSelector("span#shadow_content>span")).getText());

        WebElement secondShadowHostElement = firstShadowRoot.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext secondShadowRoot = secondShadowHostElement.getShadowRoot();
        String secondShadowText = secondShadowRoot.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        System.out.println(secondShadowText);
    }

    @Test
    public void TC_02_AppSpot() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        Thread.sleep(5000);

        // Tìm element chứa shadow root đầu tiên
        WebElement firstShadowHostElement = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']"));

        // Đi tới Shadow root
        SearchContext firstShadowRoot = firstShadowHostElement.getShadowRoot();

        // Đi tới element nằm trong Shadow root đó
        firstShadowRoot.findElement(By.cssSelector("input#input")).sendKeys("Harry Potter");
        Thread.sleep(5000);

        WebElement secondShadowHostElement = firstShadowRoot.findElement(By.cssSelector("app-toolbar.toolbar-bottom book-input-decorator"));
        SearchContext secondShadowRoot = secondShadowHostElement.getShadowRoot();
        secondShadowRoot.findElement(By.cssSelector("div.icon")).click();
        Thread.sleep(5000);

        WebElement bookExploreShadowHostElement = firstShadowRoot.findElement(By.cssSelector("book-explore._page"));
        SearchContext bookExploreShadowRoot = bookExploreShadowHostElement.getShadowRoot();

        List<WebElement> bookItemShadowHostElements = bookExploreShadowRoot.findElements(By.cssSelector("li>book-item"));

        for (WebElement element : bookItemShadowHostElements){
            SearchContext shadowRoot = element.getShadowRoot();
            System.out.println(shadowRoot.findElement(By.cssSelector("div.info h2.title")).getText());
        }


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

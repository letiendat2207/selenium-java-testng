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

public class Topic_21_iFrame {
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
    public void TC_01_ToiDiCodeDao() throws InterruptedException {
        driver.get("https://toidicodedao.com/");

        // switch to iFrame
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb-page.fb_iframe_widget iframe")));
        Assert.assertTrue(driver.findElement(By.cssSelector("body.plugin.gecko.win.x1.Locale_en_US")).isDisplayed());

        Assert.assertEquals(driver.findElement(By.cssSelector("div._1drq")).getText(), "403,881 followers");
    }

    @Test
    public void TC_02_Map() throws InterruptedException {
        driver.get("https://www.embedgooglemap.net/");
        Thread.sleep(3000);

        // switch to iFrame 1
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.gmap_canvas>iframe")));

        String addressName = driver.findElement(By.cssSelector("div.place-name")).getText();
        System.out.println(addressName);

        // switch to iFrame 2
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#mapDiv>div>div>iframe")));

        // switch ve lai iframe 1
        driver.switchTo().parentFrame();
        System.out.println(addressName);

        // switch ve lai HTML page
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input#s")).clear();
        driver.findElement(By.cssSelector("input#s")).sendKeys("325 Lanh Binh Thai");

    }

    @Test
    public void TC_03_FormSite() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey//");
        Thread.sleep(3000);

        // accept cookie
        driver.findElement(By.cssSelector("button.osano-cm-accept-all")).click();

        // click to show form
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        Thread.sleep(3000);

        // switch vào iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));

        // thao tác trong form
        new Select(driver.findElement(By.xpath("//label[contains(text(),'Year')]/following-sibling::select"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.xpath("//label[contains(text(),'Residence')]/following-sibling::select"))).selectByVisibleText("North Dorm");

        driver.findElement(By.xpath("//label[contains(text(),'Male')]")).click();
        Thread.sleep(3000);

        // click vào submit button
        driver.findElement(By.cssSelector("input#FSsubmit")).click();
        Thread.sleep(3000);

        // thoát khỏi iFrame
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        driver.findElement(By.cssSelector("button#login")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

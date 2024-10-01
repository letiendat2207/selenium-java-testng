package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_05_Xpath_Technical_05 {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Text() {

        driver.get("https://automationfc.github.io/basic-form/");

        //text()='...'
        driver.findElement(By.xpath("//h1[text()='Selenium WebDriver API']"));

        //contains(text(),'...')
        driver.findElement(By.xpath("//h5[contains(text(),'Hello World!')]"));

        //contains(.,'...')
        driver.findElement(By.xpath("//h5[contains(.,'- living in Viet Nam')]"));

        //contains(string(),'...')
        driver.findElement(By.xpath("//span[contains(string(),'(Ignore Me)')]"));

        //concat()
        driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What',\"'s happened?\")]"));

        //and - or
        driver.findElement(By.xpath("//input[@id='disable_password' and @type='password']"));
        driver.findElement(By.xpath("//input[@id='mail' or @name='user_email']"));

        //not
        //driver.findElement(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']"));

        //outside parent
        driver.findElement(By.xpath("(//div/img)[5]"));

    }

    @Test
    public void TC_02_Index() {

        driver.get("https://automationfc.github.io/jquery-selectable/");

        //position()
        driver.findElement(By.xpath("//ol[@id='selectable']/li[position()=12]"));

        //index
        driver.findElement(By.xpath("//ol[@id='selectable']/li[1]"));

        //last()
        driver.findElement(By.xpath("//ol[@id='selectable']/li[last()]"));

        //count()
        driver.findElement(By.xpath("//ol[@id='selectable']/li[count(//li)]"));

        //last()-1
        driver.findElement(By.xpath("//ol[@id='selectable']/li[last()-1]"));

        //count()-1
        driver.findElement(By.xpath("//ol[@id='selectable']/li[count(//li)-1]"));

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

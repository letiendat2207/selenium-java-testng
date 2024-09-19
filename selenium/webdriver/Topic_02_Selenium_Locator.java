package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    //ID
    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("txtSearch"));
    }

    //Class Name
    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("inputsearch2"));
    }

    //Name
    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("txtEmail"));
    }

    //Link
    @Test
    public void TC_04_Link() {
        driver.findElement(By.linkText("thỏa thuận sử dụng"));
    }

    //Partial Link
    @Test
    public void TC_05_Partial_Link() {
        driver.findElement(By.partialLinkText("chính sá"));
    }

    //Tag Name
    @Test
    public void TC_06_TagName() {
        driver.findElement(By.tagName("a"));
    }

    //CSS
    @Test
    public void TC_07_Css() {
        // Css vs ID
        driver.findElement(By.cssSelector("input[id='txtFirstname']"));
        driver.findElement(By.cssSelector("input#txtFirstname"));
        driver.findElement(By.cssSelector("#txtFirstname"));

        // Css vs Class
        driver.findElement(By.cssSelector("input[id='txtCPassword']"));
        driver.findElement(By.cssSelector("input#txtCPassword"));
        driver.findElement(By.cssSelector("#txtCPassword"));

        // Css vs Name
        driver.findElement(By.cssSelector("input[name='txtPhone']"));

        // Css vs TagName
        driver.findElements(By.cssSelector("input"));

        // Css vs Link
        driver.findElement(By.cssSelector("a[href='https://alada.vn/dieu-khoan-dich-vu.html']"));

        // Css vs Partial Link
        driver.findElement(By.cssSelector("a[href^='https://']"));
        driver.findElement(By.cssSelector("a[href$='dich-vu.html']"));
        driver.findElement(By.cssSelector("a[href*='alada.vn/dieu-khoan']"));
    }

    //Xpath
    @Test
    public void TC_08_Xpath() {
        // Xpath vs ID
        driver.findElement(By.xpath("//input[@id='txtPhone']"));

        // Xpath vs Class
        driver.findElement(By.xpath("//input[@class='inputsearch2']"));

        // Xpath vs Name
        driver.findElement(By.xpath("//input[@name='txtSearch']"));

        // Xpath vs TagName
        driver.findElements(By.xpath("//button"));

        // Xpath vs Link
        driver.findElement(By.xpath("//a[@href='https://alada.vn/chinh-sach-bao-mat.html']"));
        driver.findElement(By.xpath("//a[text()='chính sách']"));

        // Xpath vs Partial Link
        driver.findElement(By.xpath("//a[contains(@href, 'dieu-khoan-dich-vu')]"));
        driver.findElement(By.xpath("//a[starts-with(@href, 'https://alada.vn/')]"));
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

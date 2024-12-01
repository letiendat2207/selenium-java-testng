package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_10_HTML_Dropdown_List {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass() {

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:/Users/DAT/AppData/Local/Microsoft/Edge/User Data/");
        edgeOptions.addArguments("--profile-directory=Profile 3");
        driver = new EdgeDriver(edgeOptions);

//        driver = new EdgeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_HTML_DropdownList() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/register");
        Thread.sleep(10000);

        String firstName = "Dat";
        String lastName = "Le";
        String email = "dat" + new Random().nextInt(9999) + "@gmail.com";
        String companyName = "KMS";
        String password = "123456@X";
        String day = "22";
        String month = "July";
        String year = "1999";

        driver.findElement(By.cssSelector("input#gender-male")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).selectByVisibleText(day);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).selectByVisibleText(month);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).selectByVisibleText(year);

        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);

        driver.findElement(By.cssSelector("button#register-button")).click();
        Thread.sleep(10000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

        driver.findElement(By.xpath("//div[@class='header-links']//a[text()='My account']")).click();
        Thread.sleep(10000);

        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), day);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), year);

    }

    @Test
    public void TC_02_HTML_DropdownList() throws InterruptedException {
        String country = "Vietnam";
        String city = "HO CHI MINH";

        driver.get("https://rode.com/en/support/where-to-buy");
        Thread.sleep(10000);

        Assert.assertFalse(new Select(driver.findElement(By.cssSelector("select#country"))).isMultiple());

        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText(country);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys(city);

        driver.findElement(By.xpath("//button[text()='Search']")).click();
        Thread.sleep(10000);

        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div//h4"));
        Assert.assertEquals(dealers.size(), 16);

        for(WebElement element : dealers){
            System.out.println(element.getText());
        }

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

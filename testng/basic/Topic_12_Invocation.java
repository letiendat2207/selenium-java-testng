package basic;

import listeners.ReportNGListeners;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@Listeners(ReportNGListeners.class)
public class Topic_12_Invocation {
    WebDriver driver;
    Random rand;
    String firstName, lastName, emailAddress, password, fullName;
    Properties props = new Properties();
    FileOutputStream outputStrem;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void initialBrowser() throws FileNotFoundException {
        driver = new FirefoxDriver();
        rand = new Random();

        firstName = "Joe";
        lastName = "Biden";
        fullName = firstName + " " + lastName;
        password = "123456789";

        String path = projectPath + "\\dataTest\\user.properties";
        outputStrem = new FileOutputStream(path);
    }

    @Test(invocationCount = 3, timeOut = 20000)
    public void TC_01_Register() throws InterruptedException, IOException {
        driver.get("http://live.techpanda.org/index.php/");

        emailAddress = "joebiden" + rand.nextInt(99999) + "@gmail.com";

        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.cssSelector("button[title='Register']")).click();

        // Tuyệt đối
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "Thank you for registering with Main Website Store.");

        String contactInformationText = driver.findElement(By.xpath(
                "//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();

        // Tương đối
        Assert.assertTrue(contactInformationText.contains(fullName) && contactInformationText.contains(emailAddress));

        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();

        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), lastName);

        // Logout
        driver.findElement(By.cssSelector("div.account-cart-wrapper>a")).click();

        driver.findElement(By.xpath("//a[@title='Log Out']")).click();

        System.out.println("Email Address: " + emailAddress);
        System.out.println("Password: " + password);

        props.setProperty("email", emailAddress);
        props.setProperty("password", password);
        props.store(outputStrem, null);
    }

    @AfterClass
    public void afterClass() throws IOException {
        outputStrem.flush();
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}

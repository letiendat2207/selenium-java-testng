package webdriver;

import org.bouncycastle.asn1.cmp.Challenge;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.Random;
import java.time.Duration;

public class Topic_08_Textbox_TextArea_LiveTechPanda {

    WebDriver driver;
    Random rand = new Random();

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_TextBox_TextArea() throws InterruptedException {
        // Truy cập vào trang
        driver.get("http://live.techpanda.org/");

        // Click MY ACCOUNT link tại footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        // Verify url của Login Page
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

        // Click CREATE AN ACCOUNT button
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

        String firstName = "Le";
        String lastName = "Dat";
        String fullName = firstName + " " + lastName;
        String password = "12345678";
        String emailAddress = "datle" + rand.nextInt(0,99999) + "@gmail.com";
        String reviewThoughts = "This is very good to use\nI love to use this phone\nThis phone help me to take a picture";
        String summaryThoughts = "This is a good phone to buy";
        String nickName = "Jason Le";

        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(emailAddress);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmation")).sendKeys(password);

        driver.findElement(By.cssSelector("button[title='Register']")).click();

        try {
            Thread.sleep(3000); // Dừng 3 giây
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");


        String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();

        Assert.assertTrue(contactInformation.contains(fullName));
        Assert.assertTrue(contactInformation.contains(emailAddress));

        driver.findElement(By.cssSelector("li[class='level0 nav-1 first'] a.level0")).click();
        driver.findElement(By.cssSelector("h2.product-name a[title='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

        driver.findElement(By.cssSelector("input[id='Quality 1_5']")).click();

        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys(reviewThoughts);
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys(summaryThoughts);
        driver.findElement(By.cssSelector("input#nickname_field")).clear();
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys(nickName);

        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();

        try {
            Thread.sleep(3000); // Dừng 3 giây
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Your review has been accepted for moderation.");


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

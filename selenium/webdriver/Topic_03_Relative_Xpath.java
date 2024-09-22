package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Topic_03_Relative_Xpath {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }


    @Test
    public void TC_Nopcommerce_Register() {

        //Gender - Male
        driver.findElement(By.xpath("//input[@id='gender-male']"));

        //Gender - Female
        driver.findElement(By.xpath("//input[@id='gender-female']"));

        //First Name
        driver.findElement(By.xpath("//input[@id='FirstName']"));
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        //Last Name
        driver.findElement(By.xpath("//input[@id='LastName']"));
        driver.findElement(By.xpath("//input[@name='LastName']"));

        //Date Of Birth Day
        driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"));

        //Date Of Birth Month
        driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"));

        //Date Of Birth Year
        driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"));

        //Email
        driver.findElement(By.xpath("//input[@id='Email']"));
        driver.findElement(By.xpath("//input[@name='Email']"));

        //Company Name
        driver.findElement(By.xpath("//input[@id='Company']"));
        driver.findElement(By.xpath("//input[@name='Company']"));

        //Newsletter
        driver.findElement(By.xpath("//input[@id='Newsletter']"));

        //Password
        driver.findElement(By.xpath("//input[@id='Password']"));
        driver.findElement(By.xpath("//input[@name='Password']"));

        //Confirm Password
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
        driver.findElement(By.xpath("//input[@name='ConfirmPassword']"));

        //Register Button
        driver.findElement(By.xpath("//button[@id='register-button']"));
        driver.findElement(By.xpath("//button[@name='register-button']"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}



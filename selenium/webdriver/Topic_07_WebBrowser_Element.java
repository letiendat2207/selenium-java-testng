package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.time.Duration;

public class Topic_07_WebBrowser_Element {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Verify_Url() {
        // Truy cập vào trang
        driver.get("http://live.techpanda.org/");

        // Click MY ACCOUNT link tại footer
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Verify url của Login Page
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

        // Click CREATE AN ACCOUNT button
        driver.findElement(By.xpath("//a[@class='button']")).click();

        // Verify url của Register Page
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Verify_Title() {
        // Truy cập vào trang
        driver.get("http://live.techpanda.org/");

        // Click MY ACCOUNT link tại footer
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Verify title của Login Page
        Assert.assertEquals(driver.getTitle(), "Customer Login");

        // Click CREATE AN ACCOUNT button
        driver.findElement(By.xpath("//a[@class='button']")).click();

        // Verify title của Register Page
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_03_Navigate_Function() {
        // Truy cập vào trang
        driver.get("http://live.techpanda.org/");

        // Click MY ACCOUNT link tại footer
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Click CREATE AN ACCOUNT button
        driver.findElement(By.xpath("//a[@class='button']")).click();

        // Verify url của Register Page
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

        // Back lại trang Login Page
        driver.navigate().back();

        // Verify url của Login Page
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

        // Forward tới trang Register Page
        driver.navigate().forward();

        // Verify title của Register Page
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_04_Get_Page_Source_Code() {
        // Truy cập vào trang
        driver.get("http://live.techpanda.org/");

        // Click MY ACCOUNT link tại footer
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Verify Login Page chứa text
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        // Click CREATE AN ACCOUNT button
        driver.findElement(By.xpath("//a[@class='button']")).click();

        // Verify Register Page chứa text
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }

    @Test
    public void TC_05_Check_Element_Is_Displayed() {
        // Truy cập vào trang
        driver.get("http://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.cssSelector("input#mail")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Email textbox is displayed");
        } else {
            System.out.println("Email textbox is not displayed");
        }

        if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Age Under 18 Radio is displayed");
        } else {
            System.out.println("Age Under 18 Radio is not displayed");
        }

        if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Education TextArea is displayed");
        } else {
            System.out.println("Education TextArea is not displayed");
        }

        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            System.out.println("Name User 5 is displayed");
        } else {
            System.out.println("Name User 5 is not displayed");
        }
    }

    @Test
    public void TC_06_Check_Element_Is_Enable_Or_Disable() {
        // Truy cập vào trang
        driver.get("http://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.cssSelector("input#mail")).isEnabled()) {
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Email textbox is enabled");
        } else {
            System.out.println("Email textbox is disabled");
        }

        if (driver.findElement(By.cssSelector("input#under_18")).isEnabled()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Age Under 18 Radio is enabled");
        } else {
            System.out.println("Age Under 18 Radio is disabled");
        }

        if (driver.findElement(By.cssSelector("textarea#edu")).isEnabled()) {
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Education TextArea is enabled");
        } else {
            System.out.println("Education TextArea is disabled");
        }

        if (driver.findElement(By.cssSelector("select#job1")).isEnabled()) {
            System.out.println("Job Role 1 is enabled");
        } else {
            System.out.println("Job Role 1 is disabled");
        }

        if (driver.findElement(By.cssSelector("select#job2")).isEnabled()) {
            System.out.println("Job Role 2 is enabled");
        } else {
            System.out.println("Job Role 2 is disabled");
        }

        if (driver.findElement(By.cssSelector("input#development")).isEnabled()) {
            System.out.println("Development checkbox is enabled");
        } else {
            System.out.println("Development checkbox is disabled");
        }

        if (driver.findElement(By.cssSelector("input#slider-1")).isEnabled()) {
            System.out.println("Slider 1 is enabled");
        } else {
            System.out.println("Slider 1 is disabled");
        }

        if (driver.findElement(By.cssSelector("input#disable_password")).isEnabled()) {
            System.out.println("Password is enabled");
        } else {
            System.out.println("Password is disabled");
        }

        if (driver.findElement(By.cssSelector("input#radio-disabled")).isEnabled()) {
            System.out.println("Radio button is enabled");
        } else {
            System.out.println("Radio button is disabled");
        }

        if (driver.findElement(By.cssSelector("textarea#bio")).isEnabled()) {
            System.out.println("Biography text area is enabled");
        } else {
            System.out.println("Biography text area is disabled");
        }

        if (driver.findElement(By.cssSelector("select#job3")).isEnabled()) {
            System.out.println("Job 3 is enabled");
        } else {
            System.out.println("Job 3 is disabled");
        }

        if (driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled()) {
            System.out.println("Interests checkbox is enabled");
        } else {
            System.out.println("Interests checkbox is disabled");
        }

        if (driver.findElement(By.cssSelector("input#slider-2")).isEnabled()) {
            System.out.println("Slider 2 is enabled");
        } else {
            System.out.println("Slider 2 is disabled");
        }
    }

    @Test
    public void TC_07_Check_Element_Is_Selected() {
        // Truy cập vào trang
        driver.get("http://automationfc.github.io/basic-form/index.html");


        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.cssSelector("input#java")).click();

        if (driver.findElement(By.cssSelector("input#under_18")).isSelected()) {
            System.out.println("Checkbox under 18 is selected");
        } else {
            System.out.println("Checkbox under 18 is de-selected");
        }

        if (driver.findElement(By.cssSelector("input#java")).isSelected()) {
            System.out.println("Checkbox java is selected");
        } else {
            System.out.println("Checkbox java is de-selected");
        }

        driver.findElement(By.cssSelector("input#java")).click();

        if (driver.findElement(By.cssSelector("input#under_18")).isSelected()) {
            System.out.println("Checkbox under 18 is selected");
        } else {
            System.out.println("Checkbox under 18 is de-selected");
        }

        if (driver.findElement(By.cssSelector("input#java")).isSelected()) {
            System.out.println("Checkbox java is selected");
        } else {
            System.out.println("Checkbox java is de-selected");
        }

    }

    @Test
    public void TC_08_Register_Function_MailChimp() {
        // Truy cập vào trang
        driver.get("http://login.mailchimp.com/signup/");

        // Truyền tên email
        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("dat@gmail.com");

        // Truyền password - số
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123");

        // validate
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Truyền password - chữ thường
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("dat");

        // validate
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        // Truyền password - chữ hoa
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("DAT");

        // validate
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        // Truyền password - kí tự đặc biệt
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("@#$");

        // validate
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Truyền password - lớn hơn 8 ký tự
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("1234567Ld");

        // validate
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

    }

    @Test
    public void TC_09_Login_With_Empty_Email_And_Password() {
        // Truy cập vào trang
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#pass")).clear();
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");

    }

    @Test
    public void TC_10_Login_With_Invalid_Email() {
        // Truy cập vào trang
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("123434234@12312.123123");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_11_Login_With_Password_Less_Than_6_Chars() {
        // Truy cập vào trang
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_12_Login_With_Incorrect_Password() {
        // Truy cập vào trang
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123123123");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

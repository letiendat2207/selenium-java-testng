package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_09_TextBox_TextArea_OrangeHrmLive {
    WebDriver driver;
    Random rand = new Random();

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_TextBox_TextArea() throws InterruptedException {
        String usernameAdmin = "Admin";
        String passwordAdmin = "admin123";
        String firstName = "Dat";
        String lastName = "Le";
        String username = "Dat" + rand.nextInt(0,9999);
        String password = "123456@Xx";
        String number = "0700-88-6272-431";
        String comments = "This is very good\nI love that!";

        // Đi tới trang
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(10000);

        // Login vào trang web bằng account Admin
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(usernameAdmin);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(passwordAdmin);

        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
        Thread.sleep(7000);

        // Click vào PIM section
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        Thread.sleep(3000);

        // Click vào Add Employee section
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        Thread.sleep(3000);

        // Điền firstname và lastname cho account mới
        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);

        // Lưu employee id lại thành 1 biến
        String employeeId = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");

        // click vào toggle crear login details
        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();
        Thread.sleep(3000);

        // Điền username - password - confirm password cho account mới
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(username);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);

        // Click save button
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(10000);

        // Verify firstname, lastname và employee id
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeId);

        // Click vào Immigration section
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Thread.sleep(3000);

        // Click vào nút Add
        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
        Thread.sleep(3000);

        // Điền number và comments
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(number);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comments);

        // Click vào nút save
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(8000);

        // Click vào icon pencil để edit
        driver.findElement(By.xpath("//div[text()='Passport']/parent::div/following-sibling::div[last()]//button[last()]")).click();
        Thread.sleep(3000);

        // Verify Number và Comments đã nhập trước đó
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), number);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comments);

        // Click vào icon user account
        driver.findElement(By.cssSelector("img.oxd-userdropdown-img")).click();
        Thread.sleep(3000);

        // Logout ra khỏi account Admin
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Thread.sleep(7000);

        // Login vào lại bằng account mới tạo
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(username);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
        Thread.sleep(10000);

        // Click vào My Info section
        driver.findElement(By.xpath("//span[text()='My Info']")).click();
        Thread.sleep(10000);

        // Verify lại firstname/ last name và employee id
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeId);

        // Click vào Immigration section
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Thread.sleep(3000);

        // Click vào icon Pencil
        driver.findElement(By.xpath("//div[text()='Passport']/parent::div/following-sibling::div[last()]//button[last()]")).click();
        Thread.sleep(3000);

        // Verify Number và Comments
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), number);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comments);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

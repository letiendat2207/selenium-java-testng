package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Register {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }


    @Test
    public void Register_01_emptyData() {

        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Action
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        //Assertion
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập số điện thoại.");

    }

    @Test
    public void Register_02_invalidEmail() {

        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Action
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Le Tien Dat");    //name
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("123@456@789#");   //invalid email
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("123@456@789#");  //invalid confirm email
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456@X");    //password
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456@X");   //confirm password
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");  //phone number

        //click the register button
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        //Assertion
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");

    }

    @Test
    public void Register_03_incorrectConfirmEmail() {

        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Action
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Le Tien Dat");    //name
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("datle@gmail.com");   //email
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("datle@gmail.net");  //incorrect confirm email
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456@X");    //password
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456@X");   //confirm password
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");  //phone number

        //click the register button
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        //Assertion
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");

    }

    @Test
    public void Register_04_invalidPassword() {

        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Action
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Le Tien Dat");    //name
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("datle@gmail.com");   //email
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("datle@gmail.com");  //confirm email
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123");    //invalid password
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123");   //invalid confirm password
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");  //phone number

        //click the register button
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        //Assertion
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

    }

    @Test
    public void Register_05_incorrectConfirmPassword() {

        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Action
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Le Tien Dat");    //name
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("datle@gmail.com");   //email
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("datle@gmail.com");  //confirm email
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");    //password
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("1234567");   //incorrect confirm password
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");  //phone number

        //click the register button
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        //Assertion
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");

        //Action
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).clear();
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123");

        //Assertion
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void Register_06_invalidPhoneNumber() {

        //Arrange
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Case 1: phone number is less than 10 numbers
        //Action
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Le Tien Dat");    //name
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("datle@gmail.com");   //email
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("datle@gmail.com");  //confirm email
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");    //password
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");   //confirm password
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123");  //invalid phone number

        //click the register button
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        //Assertion
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");

        //Case 2: phone number is more than 11 numbers
        //Action
        driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789999999999");

        //Assertion
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");

        //Case 3: phone number does not start with the 0 number
        //Action
        driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("12345678911");

        //Assertion
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

        //Case 4: fill the characters into the phone number input
        //Action
        driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("abcdef");

        //Assertion
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập con số");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}



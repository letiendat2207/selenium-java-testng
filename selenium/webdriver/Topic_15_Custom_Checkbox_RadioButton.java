package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Custom_Checkbox_RadioButton {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Custom_Checkbox_Radio() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");
        Thread.sleep(5000);

        By registerRadio = By.cssSelector("input#id_new_user");

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
            driver.findElement(registerRadio));
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(registerRadio).isSelected());

        By registerCheckbox = By.cssSelector("input#id_accept_tos");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                driver.findElement(registerCheckbox));
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(registerCheckbox).isSelected());
    }

    @Test
    public void TC_02_Custom_Checkbox_Radio() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(5000);

        By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");

        Assert.assertEquals(driver.findElement(canThoRadio).getDomAttribute("aria-checked"), "false");

        driver.findElement(canThoRadio).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(canThoRadio).getDomAttribute("aria-checked"), "true");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

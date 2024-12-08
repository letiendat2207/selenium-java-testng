package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Editable_Dropdown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Csemantic_UI() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        customDropdownSelect("input.search", "div.visible span", "Benin");

        // so sánh để verify coi item đã chọn đúng chưa
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText().trim(), "Benin");
    }

    @Test
    public void TC_02_Huawei() throws InterruptedException {
        driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");
        customDropdownSelectHuawei("div[ht='input_emailregister_dropdown']", "input[ht='input_emailregister_search']", "ul.hwid-list-module.hwid-alpla-list li", "South Korea");

        // so sánh để verify coi item đã chọn đúng chưa
        Assert.assertEquals(driver.findElement(By.cssSelector("div[ht='input_emailregister_dropdown']>span")).getText(), "South Korea");
    }

    private void customDropdownSelect(String parentDropdown, String childDropdown, String itemText) {

        // click vào Dropdown parent
        driver.findElement(By.cssSelector(parentDropdown)).click();

        // chờ cho đến khi nào list item của dropdown parent xuất hiện ra
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childDropdown)));

        // tạo 1 list web element để chứa tất cả các thẻ item trong dropdown parent
        List<WebElement> dropdownList = driver.findElements(By.cssSelector(childDropdown));

        // vòng lặp đi qua từng thẻ item trong list trên
        for (WebElement element : dropdownList) {
            // nếu item nào bằng với expected item thì click vào và break khỏi vòng lặp
            if (element.getText().trim().equals(itemText)) {
                element.click();
                break;
            }
        }
    }

    private void customDropdownSelectHuawei(String parentDropdown, String editableSearch, String childDropdown, String itemText) throws InterruptedException {

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.
                elementToBeClickable(By.cssSelector(parentDropdown)));
        driver.findElement(By.cssSelector(parentDropdown)).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(editableSearch)).sendKeys(itemText);

        // chờ cho đến khi nào list item của dropdown parent xuất hiện ra
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childDropdown)));

        // tạo 1 list web element để chứa tất cả các thẻ item trong dropdown parent
        List<WebElement> dropdownList = driver.findElements(By.cssSelector(childDropdown));

        // vòng lặp đi qua từng thẻ item trong list trên
        for (WebElement element : dropdownList) {
            // nếu item nào bằng với expected item thì click vào và break khỏi vòng lặp
            if (element.getText().trim().equals(itemText)) {
                element.click();
                break;
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

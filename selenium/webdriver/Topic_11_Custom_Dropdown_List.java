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

public class Topic_11_Custom_Dropdown_List {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass() {
//        EdgeOptions edgeOptions = new EdgeOptions();
//        edgeOptions.addArguments("--user-data-dir=C:/Users/DAT/AppData/Local/Microsoft/Edge/User Data/");
//        edgeOptions.addArguments("--profile-directory=Profile 3");
//        driver = new EdgeDriver(edgeOptions);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Custom_DropdownList_JQuery() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        customDropdownSelect("span#speed-button", "div.ui-menu-item-wrapper", "Medium");
        customDropdownSelect("span#speed-button", "div.ui-menu-item-wrapper", "Slower");
        customDropdownSelect("span#speed-button", "div.ui-menu-item-wrapper", "Faster");
    }

    @Test
    public void TC_02_Custom_DropdownList_ReactJS() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        customDropdownSelect("div.divider", "div.visible span", "Elliot Fu");
        customDropdownSelect("div.divider", "div.visible span", "Jenny Hess");
        customDropdownSelect("div.divider", "div.visible span", "Stevie Feliciano");
    }

    @Test
    public void TC_03_Custom_DropdownList_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        customDropdownSelect("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
        customDropdownSelect("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
        customDropdownSelect("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
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
        for (WebElement element : dropdownList){
            // nếu item nào bằng với expected item thì click vào và break khỏi vòng lặp
            if(element.getText().equals(itemText)){
                element.click();
                break;
            }
        }

        // so sánh để verify coi item đã chọn đúng chưa
        Assert.assertEquals(driver.findElement(By.cssSelector(parentDropdown)).getText(), itemText);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

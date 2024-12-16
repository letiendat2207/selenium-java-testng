package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_14_Checkbox_RadioButton {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_DefaultCheckBox_Telerik() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        Thread.sleep(5000);

        // scroll xuống element cần test, phòng hờ trường hợp không hỉen thị element cần test
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.cssSelector("div#demo-runner")));

        By dualZoneCheckBox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        if(!driver.findElement(dualZoneCheckBox).isSelected()){
            driver.findElement(dualZoneCheckBox).click();
            Thread.sleep(2000);
        }
        Assert.assertTrue(driver.findElement(dualZoneCheckBox).isSelected());

        if(driver.findElement(dualZoneCheckBox).isSelected()){
            driver.findElement(dualZoneCheckBox).click();
            Thread.sleep(2000);
        }
        Assert.assertFalse(driver.findElement(dualZoneCheckBox).isSelected());
    }

    @Test
    public void TC_02_RadioButton_Telerik() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        Thread.sleep(5000);

        // scroll xuống element cần test, phòng hờ trường hợp không hỉen thị element cần test
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.cssSelector("div#demo-runner")));

        By twoPetrol = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

        if(!driver.findElement(twoPetrol).isSelected()){
            driver.findElement(twoPetrol).click();
            Thread.sleep(2000);
        }
        Assert.assertTrue(driver.findElement(twoPetrol).isSelected());
    }

    @Test
    public void TC_03_RadioButton_Angular() throws InterruptedException {
        driver.get("https://material.angular.io/components/radio/examples");
        Thread.sleep(3000);

        By summerCheckBox = By.xpath("//input[@value='Summer']");

        if(!driver.findElement(summerCheckBox).isSelected()){
            driver.findElement(summerCheckBox).click();
            Thread.sleep(2000);
        }
        Assert.assertTrue(driver.findElement(summerCheckBox).isSelected());
    }

    @Test
    public void TC_04_DefaultCheckbox_Angular() throws InterruptedException {
        driver.get("https://material.angular.io/components/checkbox/examples");
        Thread.sleep(3000);

        By checkedCheckBox = By.xpath("//label[text()='Checked']/parent::div//input");
        By indeterminateCheckBox = By.xpath("//label[text()='Indeterminate']/parent::div//input");

        if(!driver.findElement(checkedCheckBox).isSelected()){
            driver.findElement(checkedCheckBox).click();
            Thread.sleep(2000);
        }
        Assert.assertTrue(driver.findElement(checkedCheckBox).isSelected());

        if(!driver.findElement(indeterminateCheckBox).isSelected()){
            driver.findElement(indeterminateCheckBox).click();
            Thread.sleep(2000);
        }
        Assert.assertTrue(driver.findElement(indeterminateCheckBox).isSelected());

        if(driver.findElement(checkedCheckBox).isSelected()){
            driver.findElement(checkedCheckBox).click();
            Thread.sleep(2000);
        }
        Assert.assertFalse(driver.findElement(checkedCheckBox).isSelected());

        if(driver.findElement(indeterminateCheckBox).isSelected()){
            driver.findElement(indeterminateCheckBox).click();
            Thread.sleep(2000);
        }
        Assert.assertFalse(driver.findElement(indeterminateCheckBox).isSelected());
    }

    @Test
    public void TC_05_One_And_All_CheckBox() throws InterruptedException {
        driver.get("https://automationfc.github.io/multiple-fields/");
        Thread.sleep(3000);

        List<WebElement> allCheckBoxes = driver.findElements(By.cssSelector("div.form-input-wide input.form-checkbox"));

        for (WebElement eachCheckbox : allCheckBoxes){
            if(!eachCheckbox.isSelected()){
                eachCheckbox.click();
            }
        }

        for (WebElement eachCheckbox : allCheckBoxes){
            Assert.assertTrue(eachCheckbox.isSelected());
        }

        for (WebElement eachCheckbox : allCheckBoxes){
            if(eachCheckbox.isSelected()){
                eachCheckbox.click();
            }
        }

        for (WebElement eachCheckbox : allCheckBoxes){
            Assert.assertFalse(eachCheckbox.isSelected());
        }

        for (WebElement eachCheckbox : allCheckBoxes){
            if(eachCheckbox.getDomAttribute("value").equals("Heart Attack") && !eachCheckbox.isSelected()){
                eachCheckbox.click();
            }
        }

        for (WebElement eachCheckbox : allCheckBoxes){
            if(eachCheckbox.getDomAttribute("value").equals("Heart Attack")){
                Assert.assertTrue(eachCheckbox.isSelected());
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

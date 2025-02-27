package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.print.DocFlavor;
import java.time.Duration;
import java.util.Random;
import java.util.Set;

public class Topic_23_JavascriptExecutor {
    WebDriver driver;
    Select select;
    Actions action;
    JavascriptExecutor jsExecutor;
    Random rand = new Random();
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        emailAddress = "datle" + rand.nextInt(0,99999) + "@gmail.com";

    }

    @Test
    public void TC_01_TechPanda() throws InterruptedException {
        navigateToUrlByJS("http://live.techpanda.org/");

        String techPandaDomain = (String) executeForBrowser("return document.domain;");
        System.out.println(techPandaDomain);
        Assert.assertEquals(techPandaDomain, "live.techpanda.org");

        String techPandaURL = (String) executeForBrowser("return document.URL;");
        System.out.println(techPandaURL);
        Assert.assertEquals(techPandaURL, "http://live.techpanda.org/");

        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");

        hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//span[text()='Add to Cart']");
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//span[text()='Add to Cart']");

        Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

        String innerText = (String) executeForBrowser("return document.documentElement.innerText;");
        Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));

        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']//span"), "Samsung Galaxy was added to your shopping cart.");

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");

        String customerServiceTitle = (String) executeForBrowser("return document.title;");
        System.out.println(customerServiceTitle);
        Assert.assertEquals(customerServiceTitle, "Customer Service");

        scrollToElementOnTop("//input[@id='newsletter']");
        hightlightElement("//input[@id='newsletter']");
        sendkeyToElementByJS("//input[@id='newsletter']", emailAddress);
        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");

        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']//span"), "Thank you for your subscription.");

        navigateToUrlByJS("https://www.facebook.com/");
        String facebookDomain = (String) executeForBrowser("return document.domain;");
        System.out.println(facebookDomain);
        Assert.assertEquals(facebookDomain, "www.facebook.com");
    }

    @Test
    public void TC_02_HTML5_Validation() throws InterruptedException {
        driver.get("https://warranty.rode.com/register");

        // Cách 1 để validate message - Selenium 4.x
        //  WebElement elementName = driver.findElement(By.xpath("//input[@id='name']"));
        //  validationMessage = elementName.getDomProperty("validationMessage");
        //  Assert.assertEquals(validationMessage, "Please fill out this field.");

        // Cách 2 dùng JS - Selenium 3.x
        // click register - verify validation message cua name

        // click register - verify validation message
        driver.findElement(By.xpath("//button[contains(string(),'Register')]")).click();
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage("//input[@id='name']"), "Please fill out this field.");

        // dien name vao - click register - verify validation message cua email
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Dat Le");
        driver.findElement(By.xpath("//button[contains(string(),'Register')]")).click();
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please fill out this field.");

        // dien name va dien email sai - verify validation message
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("@dat2234@gmail@.c.om");
        driver.findElement(By.xpath("//button[contains(string(),'Register')]")).click();
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please enter an email address.");

        // dien name va dien email dung - verify validation message
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("dat234@gmail.com");
        driver.findElement(By.xpath("//button[contains(string(),'Register')]")).click();
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage("//input[@id='password']"), "Please fill out this field.");

        // dien password - verify validation message
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[contains(string(),'Register')]")).click();
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage("//input[@id='password_confirmation']"), "Please fill out this field.");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 4px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}

package pages;

import helpers.ElementLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AbstractPage {

    public WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public void Start(){
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
//        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.navigate().to("https://www.ryanair.com/ie/en/");
    }

    public void Stop(){
        this.driver.quit();
    }

    private WebElement GetElement(ElementLocator element, int time){

        WebElement webElement = null;
        WaitForElementToBeDisplayed(element, time);

        switch (element.getType()){
            case Id:
                webElement = this.driver.findElement(By.id(element.getLocator()));
                break;
            case CssSelector:
                webElement = this.driver.findElement(By.cssSelector(element.getLocator()));
                break;
            case XPath:
                webElement = this.driver.findElement(By.xpath(element.getLocator()));
            break;
        }
        return webElement;
    }

    private WebElement GetElement(ElementLocator element)
    {
        return GetElement(element, 20);
    }

    void SendKeys(ElementLocator element, String text){
        WebElement webElement = GetElement(element);
        webElement.clear();
        webElement.sendKeys(text);
    }

    void Click(ElementLocator element){
        WebElement webElement = GetElement(element);
        webElement.click();
    }

    void WaitForElementToBeDisplayed(ElementLocator element, int time){

        WebDriverWait wait = new WebDriverWait(this.driver, TimeUnit.SECONDS.toSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(element.ToBy()));
    }

    void WaitForElementToBeNotDisplayed(ElementLocator element, int time){

        WebDriverWait wait = new WebDriverWait(this.driver, TimeUnit.SECONDS.toSeconds(time));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element.ToBy()));
    }

    public boolean IsElementPresent(ElementLocator element){
        try
        {
            GetElement(element);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    boolean IsElementNotPresent(ElementLocator element){
        try
        {
            GetElement(element, 1);
            return false;
        }
        catch (Exception e)
        {
            return true;
        }
    }

    String GetValue(ElementLocator element){
        return GetElement(element).getAttribute("value");
    }

    String GetText(ElementLocator element){
        return GetElement(element).getText();
    }

    void SelectValue(ElementLocator element, String text){
        Select select = new Select(GetElement(element));
        select.selectByVisibleText(text);
    }

    void ScrollToElement(ElementLocator element){
        WebElement webElement = GetElement(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }
}

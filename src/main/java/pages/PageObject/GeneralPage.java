package pages.PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class GeneralPage {
    WebDriver driver;
    WebDriverWait wait;
    WebElement element;

    public GeneralPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigateBack(){
        driver.navigate().back();
    }

    protected void waitingForPageElement(By pageElement){
        wait.until(presenceOfElementLocated(pageElement));
    }

    protected List<WebElement> getElementList(By pageElement) {
        return driver.findElements(pageElement);
    }

    protected WebElement getElement(By pageElement) {
        return element = driver.findElement(pageElement);
    }

    protected void clickAction (By requiredElement) {
        driver.findElement(requiredElement).click();
    }

    protected void scrollMethod(By requiredElement) {
        waitingForPageElement(requiredElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(requiredElement));
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    protected void localImplicitWait(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    protected void closeBanner(By banner, By closeButton) {
        try {
            waitingForPageElement(banner);
            clickAction(closeButton);
        } catch (TimeoutException exception) {
            System.out.println("Banner handled");
        }
    }
}

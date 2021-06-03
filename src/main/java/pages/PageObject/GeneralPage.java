package pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
}

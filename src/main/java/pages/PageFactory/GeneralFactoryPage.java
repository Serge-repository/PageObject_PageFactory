package pages.PageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class GeneralFactoryPage {
    WebDriver driver;
    WebDriverWait wait;

    public GeneralFactoryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    protected void waitingForPageElement(WebElement pageElement) {
        wait.until(visibilityOf(pageElement));
    }

    protected void clickAction(WebElement requiredElement) {
        requiredElement.click();
    }

    protected List<WebElement> getElementList(List<WebElement> elementsList) {
        return elementsList;
    }

    protected WebElement getElement(WebElement pageElement) {
        return pageElement;
    }
    protected void scrollMethod(WebElement requiredElement) {
        waitingForPageElement(requiredElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(requiredElement));
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }
}

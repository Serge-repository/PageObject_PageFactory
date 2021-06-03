package pages.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class GeneralFactoryPage {
    WebDriver driver;
    WebDriverWait wait;

    public GeneralFactoryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
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
}

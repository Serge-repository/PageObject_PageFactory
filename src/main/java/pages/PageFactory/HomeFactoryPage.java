package pages.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomeFactoryPage extends GeneralFactoryPage {

    @FindBy(xpath = "(//a[contains(@href, 'computers-notebooks/c80253')])[2]")
    private WebElement notebooksAndComputers;
    @FindBy(css = "a[href='https://hard.rozetka.com.ua/monitors/c80089/'][class=menu__link]")
    private WebElement monitors;

    public HomeFactoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private void localImplicitWait() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void locateToDropdown() {
        waitingForPageElement(notebooksAndComputers);
        Actions actions = new Actions(driver);
        actions.moveToElement(notebooksAndComputers).perform();
        localImplicitWait();
    }

    public void clickOnMonitorsLink() {
        clickAction(monitors);
        localImplicitWait();
    }
}

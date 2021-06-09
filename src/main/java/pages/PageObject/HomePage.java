package pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends GeneralPage {

    private final By notebooksAndComputers = By.xpath("(//a[contains(@href, 'computers-notebooks/c80253')])[2]");
    private final By monitors = By.cssSelector("a[href='https://hard.rozetka.com.ua/monitors/c80089/'][class=menu__link]");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void locateToDropdown() {
        waitingForPageElement(notebooksAndComputers);
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(notebooksAndComputers)).perform();
        localImplicitWait();
    }

    public void clickOnMonitorsLink() {
        clickAction(monitors);
        localImplicitWait();
    }
}

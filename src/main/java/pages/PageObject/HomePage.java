package pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage extends GeneralPage {

    By notebooksAndComputers = By.xpath("(//a[contains(@href, 'computers-notebooks/c80253')])[2]");
    By monitors = By.cssSelector("a[href='https://hard.rozetka.com.ua/monitors/c80089/'][class=menu__link]");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private void localImplicitWait(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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

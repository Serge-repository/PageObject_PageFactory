package pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class ProductPage extends GeneralPage {

    By compareButton = By.xpath("//button[@class='compare-button ng-star-inserted']");
    By numberAddedToCompareList = By.xpath("//rz-icon-counter//child::span");
    By productTitle = By.cssSelector("h1[class='product__title']");
    By productPrice = By.cssSelector("p.product-prices__big");
    By productListPageHeader = By.cssSelector("h1[class='catalog-heading ng-star-inserted']");
    By compareIcon = By.cssSelector("li[class='header-actions__item header-actions__item--comparison'] button");
    By compareMonitorsLink = By.cssSelector("a.comparison-modal__link");
    By productPricesInCompareList = By.cssSelector("div[class='product__prices']");

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOnCompareButton() {
        clickAction(compareButton);
        waitingForPageElement(numberAddedToCompareList);
    }

    public boolean productAddedToCompare() {
        return driver.findElement(numberAddedToCompareList).isDisplayed();
    }

    public String getMonitorTitle() {
        return driver.findElement(productTitle).getText();
    }

    public int getMonitorPrice() {
        return Integer.parseInt(driver.findElement(productPrice).getText()
                .replace("&nbsp", "")
                .replaceAll(" ", "")
                .replace("₴", ""));
    }

    public void waitingForProductListPageHeader() {
        waitingForPageElement(productListPageHeader);
    }

    public void waitingForNumberTwoAppearNearCompareIcon() {
        wait.until(textToBePresentInElementLocated(numberAddedToCompareList, "2"));
    }

    public int getNumberOfAddedItems() {
        return Integer.parseInt(driver.findElement(numberAddedToCompareList).getText());
    }

    public void openComparePage() {
        clickAction(compareIcon);
        clickAction(compareMonitorsLink);
        waitingForPageElement(productPricesInCompareList);
    }
}
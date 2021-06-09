package pages.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ProductFactoryPage extends GeneralFactoryPage {

    @FindBy(xpath = "//button[@class='compare-button ng-star-inserted']")
    private WebElement compareButton;
    @FindBy(xpath = "//rz-icon-counter//child::span")
    private WebElement numberAddedToCompareList;
    @FindBy(css = "h1[class='product__title']")
    private WebElement productTitle;
    @FindBy(css = "p.product-prices__big")
    private WebElement productPrice;
    @FindBy(css = "h1[class='catalog-heading ng-star-inserted']")
    private WebElement productListPageHeader;
    @FindBy(css = "li[class='header-actions__item header-actions__item--comparison'] button")
    private WebElement compareIcon;
    @FindBy(css = "a.comparison-modal__link")
    private WebElement compareMonitorsLink;
    @FindBy(css = "div[class='product__prices']")
    private WebElement productPricesInCompareList;
    @FindBy(css = "span.product__code-accent")
    private WebElement productCode;

    public ProductFactoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOnCompareButton() {
        scrollMethod(productCode);
        clickAction(compareButton);
        waitingForPageElement(numberAddedToCompareList);
    }

    public boolean productAddedToCompare() {
        return numberAddedToCompareList.isDisplayed();
    }

    public String getMonitorTitle() {
        return productTitle.getText();
    }

    public int getMonitorPrice() {
        return Integer.parseInt(productPrice.getText()
                .replace("&nbsp", "")
                .replaceAll(" ", "")
                .replace("₴", ""));
    }

    public void waitingForProductListPageHeader() {
        waitingForPageElement(productListPageHeader);
    }

    public void waitingForNumberTwoAppearNearCompareIcon() {
        wait.until(textToBePresentInElement(numberAddedToCompareList, "2"));
    }

    public int getNumberOfAddedItems() {
        return Integer.parseInt(numberAddedToCompareList.getText());
    }

    public void openComparePage() {
        clickAction(compareIcon);
        clickAction(compareMonitorsLink);
        waitingForPageElement(productPricesInCompareList);
    }
}
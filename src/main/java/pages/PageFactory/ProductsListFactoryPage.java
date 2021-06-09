package pages.PageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.testng.Assert.assertTrue;

public class ProductsListFactoryPage extends GeneralFactoryPage {

    @FindBy(css = "a[class='tabs__link tabs__link--active']")
    private WebElement goodGeneralDescription;
    @FindBy(css = "span.goods-tile__price-value")
    private List<WebElement> pricesOfGoods;
    @FindBy(xpath = "//div[@class='exponea-banner exponea-popup-banner exponea-animate']")
    private WebElement subscribeBanner;
    @FindBy(css = "span.exponea-close-cross")
    private WebElement closeSubscribeBanner;
    @FindBy(name = "search")
    private WebElement searchBar;
    @FindBy(xpath = "//*[contains(@href, 'mobile-phones')]")
    private WebElement mobilePhonesSearchResults;
    @FindBy(xpath = "//*[contains(@href, 'mobile-phones')]")
    private WebElement mobilePhonesProducts;
    @FindBy(css = "label[for=Rozetka]")
    private WebElement seller;
    @FindBy(css = "label[for=Apple]")
    private WebElement labelApple;
    @FindBy(css = "label[for=Huawei]")
    private WebElement labelHuawei;
    @FindBy(css = "span.goods-tile__title")
    private List<WebElement> goodsTitle;
    @FindBy(css = "label[for=Samsung]")
    private WebElement samsungLabel;
    @FindBy(css = "input[formcontrolname=min]")
    private WebElement minPriceTextbox;
    @FindBy(css = "input[formcontrolname=max]")
    private WebElement maxPriceTextbox;
    @FindBy(xpath = "(//button[@type='submit'])[1]")
    private WebElement submitPriceButton;
    @FindBy(css = "input[name=searchline]")
    private WebElement manufacturerSearchLine;
    @FindBy(css = "p.checkbox-filter__glyph")
    private WebElement bigFirstLetterAfterUsingSearchBar;

    public ProductsListFactoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void setUpPriceLimits(String minimumPrice, String maximumPrice) {
        minPriceTextbox.clear();
        minPriceTextbox.sendKeys(minimumPrice);
        maxPriceTextbox.clear();
        maxPriceTextbox.sendKeys(maximumPrice);
        clickAction(submitPriceButton);
        wait.until(elementToBeClickable(submitPriceButton));
    }

    public void findElementByPrice(int comparedPrice) {
        IntStream.range(0, pricesOfGoods.size())
                .filter(i -> Integer.parseInt(pricesOfGoods.get(i).getText().replace(" ", "")) < comparedPrice)
                .findFirst()
                .ifPresent(i -> {
                    i++;
                    driver.findElement(By.xpath("(//span[@class='goods-tile__price-value']//preceding::a[2])" + "[" + i + "]")).click();
                });
        waitingForPageElement(goodGeneralDescription);
    }

    public void closeBanner() {
        try {
            waitingForPageElement(subscribeBanner);
            clickAction(closeSubscribeBanner);
        } catch (Exception exception) {
            System.out.println("No banner");
        }
    }

    public void samsungPhonesSearch() {
        searchBar.sendKeys("samsung" + Keys.ENTER);
        waitingForPageElement(mobilePhonesSearchResults);
        clickAction(mobilePhonesProducts);
        waitingForPageElement(seller);
    }

    public void scrollMethod() {
        waitingForPageElement(seller);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seller);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void scrollToSamsungLabel() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", samsungLabel);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void selectRequiredModels() {
        waitingForPageElement(labelApple);
        checkAppleLabel();
        clickAction(labelHuawei);
        waitingForPageElement(labelHuawei);
    }

    public List<WebElement> checkThatOnlySelectedPhonesAvailable() {
        return getElementList(goodsTitle);
    }

    public void checkThatOnlyOneSelectedPhoneAvailable(String model) {
        for (WebElement e : goodsTitle) {
            assertTrue(e.getText().contains(model));
        }
    }

    public void checkProductsPriceLimits(int minimumConditionPrice, int maximumConditionPrice) {
        for (WebElement elem : pricesOfGoods) {
            int goodPrice = Integer.parseInt(elem.getText().replace("&nbsp", "").replaceAll(" ", ""));
            assertTrue(goodPrice > minimumConditionPrice && goodPrice < maximumConditionPrice);
        }
    }

    public void uncheckSamsungLabel() {
        clickAction(samsungLabel);
    }

    public void checkAppleLabel() {
        clickAction(labelApple);
        waitingForPageElement(seller);
    }

    public void searchUsingProductSearchBar(String searchRequest) {
        manufacturerSearchLine.sendKeys(searchRequest);
        assertTrue(bigFirstLetterAfterUsingSearchBar.isDisplayed());
    }
}
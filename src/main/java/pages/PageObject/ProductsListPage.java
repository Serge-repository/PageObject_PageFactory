package pages.PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.testng.Assert.assertTrue;

public class ProductsListPage extends GeneralPage {

    By goodGeneralDescription = By.cssSelector("a[class='tabs__link tabs__link--active']");
    By pricesOfGoods = By.cssSelector("span.goods-tile__price-value");
    By subscribeBanner = By.xpath("//div[@class='exponea-banner exponea-popup-banner exponea-animate']");
    By closeSubscribeBanner = By.cssSelector("span.exponea-close-cross");
    By searchBar = By.name("search");
    By mobilePhonesSearchResults = By.xpath("//*[contains(@href, 'mobile-phones')]");
    By mobilePhonesProducts = By.xpath("//*[contains(@href, 'mobile-phones')]");
    By seller = By.cssSelector("label[for=Rozetka]");
    By labelApple = By.cssSelector("label[for=Apple]");
    By labelHuawei = By.cssSelector("label[for=Huawei]");
    By goodsTitle = By.cssSelector("span.goods-tile__title");
    By samsungLabel = By.cssSelector("label[for=Samsung]");
    By minPriceTextbox = By.cssSelector("input[formcontrolname=min]");
    By maxPriceTextbox = By.cssSelector("input[formcontrolname=max]");
    By submitPriceButton = By.xpath("(//button[@type='submit'])[1]");
    By manufacturerSearchLine = By.cssSelector("input[name=searchline]");
    By bigFirstLetterAfterUsingSearchBar = By.cssSelector("p.checkbox-filter__glyph");

    public ProductsListPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void setUpPriceLimits(String minimumPrice, String maximumPrice) {
        driver.findElement(minPriceTextbox).clear();
        driver.findElement(minPriceTextbox).sendKeys(minimumPrice);
        driver.findElement(maxPriceTextbox).clear();
        driver.findElement(maxPriceTextbox).sendKeys(maximumPrice);
        clickAction(submitPriceButton);
        wait.until(elementToBeClickable(submitPriceButton));
    }

    public void findElementByPrice(int comparedPrice) {
        IntStream.range(0, getElementList(pricesOfGoods).size())
                .filter(i -> Integer.parseInt(getElementList(pricesOfGoods).get(i).getText().replace(" ", "")) < comparedPrice)
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
        } catch (TimeoutException exception) {
            exception.printStackTrace();
        }
    }

    public void samsungPhonesSearch() {
        driver.findElement(searchBar).sendKeys("samsung" + Keys.ENTER);
        waitingForPageElement(mobilePhonesSearchResults);
        clickAction(mobilePhonesProducts);
        waitingForPageElement(seller);
    }

    public void scrollMethod() {
        waitingForPageElement(seller);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(seller));
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void scrollToSamsungLabel() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(samsungLabel));
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void selectRequiredModels() {
        waitingForPageElement(labelApple);
        checkAppleLabel();
        clickAction(labelHuawei);
        waitingForPageElement(labelHuawei);
    }

    public void checkThatOnlySelectedPhonesAvailable(String firstModel, String secondModel, String thirdModel) {
        for (WebElement e : getElementList(goodsTitle)) {
            assertTrue(e.getText().contains(firstModel) || e.getText().contains(secondModel) || e.getText().contains(thirdModel));
        }
    }

    public void checkThatOnlyOneSelectedPhoneAvailable(String model) {
        for (WebElement e : getElementList(goodsTitle)) {
            assertTrue(e.getText().contains(model));
        }
    }

    public void checkProductsPriceLimits(int minimumConditionPrice, int maximumConditionPrice) {
        for (WebElement elem : getElementList(pricesOfGoods)) {
            int goodPrice = Integer.parseInt(elem.getText().replace("&nbsp", "").replaceAll(" ", ""));
            assertTrue(goodPrice > minimumConditionPrice && goodPrice < maximumConditionPrice);
        }
    }

    public void uncheckSamsungLabel () {
        clickAction(samsungLabel);
    }

    public void checkAppleLabel () {
        clickAction(labelApple);
        waitingForPageElement(seller);
    }

    public void searchUsingProductSearchBar (String searchRequest) {
        driver.findElement(manufacturerSearchLine).sendKeys(searchRequest);
        assertTrue(driver.findElement(bigFirstLetterAfterUsingSearchBar).isDisplayed());
    }
}
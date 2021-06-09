package pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class ProductsListPage extends GeneralPage {

    private final By goodGeneralDescription = By.cssSelector("a[class='tabs__link tabs__link--active']");
    private final By pricesOfGoods = By.cssSelector("span.goods-tile__price-value");
    private final By subscribeBanner = By.xpath("(//span[contains(@class, 'exponea')])[1]");
    private final By closeSubscribeBanner = By.cssSelector("span.exponea-close-cross");
    private final By searchBar = By.name("search");
    private final By mobilePhonesSearchResults = By.xpath("//*[contains(@href, 'mobile-phones')]");
    private final By mobilePhonesProducts = By.xpath("//*[contains(@href, 'mobile-phones')]");
    private final By seller = By.cssSelector("label[for=Rozetka]");
    private final By labelApple = By.cssSelector("label[for=Apple]");
    private final By labelHuawei = By.cssSelector("label[for=Huawei]");
    private final By goodsTitle = By.cssSelector("span.goods-tile__title");
    private final By samsungLabel = By.cssSelector("label[for=Samsung]");
    private final By minPriceTextbox = By.cssSelector("input[formcontrolname=min]");
    private final By maxPriceTextbox = By.cssSelector("input[formcontrolname=max]");
    private final By submitPriceButton = By.xpath("(//button[@type='submit'])[1]");
    private final By manufacturerSearchLine = By.cssSelector("input[name=searchline]");
    private final By bigFirstLetterAfterUsingSearchBar = By.cssSelector("p.checkbox-filter__glyph");

    public ProductsListPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void setUpPriceLimits(String minimumPrice, String maximumPrice) {
        scrollMethod(samsungLabel);
        driver.findElement(minPriceTextbox).clear();
        driver.findElement(minPriceTextbox).sendKeys(minimumPrice);
        driver.findElement(maxPriceTextbox).clear();
        driver.findElement(maxPriceTextbox).sendKeys(maximumPrice);
        clickAction(submitPriceButton);
        wait.until(elementToBeClickable(submitPriceButton));
    }

    public void findElementByPrice(int comparedPrice) {
        closeBanner(subscribeBanner, closeSubscribeBanner);
        IntStream.range(0, getElementList(pricesOfGoods).size())
                .filter(i -> Integer.parseInt(getElementList(pricesOfGoods).get(i).getText().replace(" ", "")) < comparedPrice)
                .findFirst()
                .ifPresent(i -> {
                    i++;
                    driver.findElement(By.xpath("(//span[@class='goods-tile__price-value']//preceding::a[2])" + "[" + i + "]")).click();
                });
        waitingForPageElement(goodGeneralDescription);
    }

    public void samsungPhonesSearch() {
        driver.findElement(searchBar).sendKeys("samsung" + Keys.ENTER);
        waitingForPageElement(mobilePhonesSearchResults);
        clickAction(mobilePhonesProducts);
        waitingForPageElement(seller);
    }

    public void selectRequiredModels() {
        scrollMethod(seller);
        waitingForPageElement(labelApple);
        checkAppleLabel();
        clickAction(labelHuawei);
        waitingForPageElement(labelHuawei);
    }

    public List<WebElement> selectedManufacturersList() {
        return getElementList(goodsTitle);
    }

    public List<Integer> selectedManufacturersPriceList() {
        List<Integer> correctPrices = new ArrayList<>();
        getElementList(pricesOfGoods).stream()
                .mapToInt(s -> Integer.parseInt(s.getText().replace("&nbsp", "").replaceAll(" ", "")))
                .forEach(correctPrices::add);
        return correctPrices;
    }

    public void uncheckSamsungLabel() {
        scrollMethod(seller);
        clickAction(samsungLabel);
    }

    public void checkAppleLabel() {
        clickAction(labelApple);
        waitingForPageElement(seller);
    }

    public WebElement searchUsingProductSearchBar(String searchRequest) {
        driver.findElement(manufacturerSearchLine).sendKeys(searchRequest);
        return getElement(bigFirstLetterAfterUsingSearchBar);
    }
}
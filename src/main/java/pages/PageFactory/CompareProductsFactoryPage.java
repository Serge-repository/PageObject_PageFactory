package pages.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CompareProductsFactoryPage extends GeneralFactoryPage {

    WebDriver driver;

    @FindBy(css = "div[class='product__prices']")
    private List<WebElement> productPricesInCompareList;
    @FindBy(css = "div.product__heading a")
    private List<WebElement> productNamesInCompareList;
    @FindBy(xpath = "(//div[@class='product__price--old']//parent::div)[1]")
    private WebElement secondProductPrice;
    @FindBy(xpath = "(//div[@class='product__price--old']//parent::div)[3]")
    private WebElement firstProductPrice;

    public CompareProductsFactoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getAllProductPricesFromComparePage() {
        return productPricesInCompareList;
    }

    private List<WebElement> getAllProductNamesFromComparePage() {
        return productNamesInCompareList;
    }

    public String getFirstProductName() {
        return getAllProductNamesFromComparePage().get(1).getText();
    }

    public String getSecondProductName() {
        return getAllProductNamesFromComparePage().get(0).getText();
    }

    private String elementToTextPrice(WebElement element) {
        String result = element.getText().replaceAll("&nbsp", "")
                .replaceAll(" ", "")
                .replace("₴", "");
        if (result.length() > 4) {
            result = result.substring(5);
        }
        return result;
    }

    public int getSecondProductPrice() {
        return Integer.parseInt(elementToTextPrice(secondProductPrice));
    }

    public int getFirstProductPrice() {
        return Integer.parseInt(elementToTextPrice(firstProductPrice));
    }
}
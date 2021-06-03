package pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CompareProductsPage extends GeneralPage {

    WebDriver driver;

    By productPricesInCompareList = By.cssSelector("div[class='product__prices']");
    By productNamesInCompareList = By.cssSelector("div.product__heading a");
    By secondProductPrice = By.xpath("(//div[@class='product__price--old']//parent::div)[1]");
    By firstProductPrice = By.xpath("(//div[@class='product__price--old']//parent::div)[3]");

    public CompareProductsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public List<WebElement> getAllProductPricesFromComparePage() {
        return driver.findElements(productPricesInCompareList);
    }

    private List<WebElement> getAllProductNamesFromComparePage() {
        return driver.findElements(productNamesInCompareList);
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
        if (result.length() > 4){
            result = result.substring(5);
        }
        return result;
    }

    public int getSecondProductPrice (){
        return  Integer.parseInt(elementToTextPrice(getElement(secondProductPrice)));
    }

    public int getFirstProductPrice (){
        return  Integer.parseInt(elementToTextPrice(getElement(firstProductPrice)));
    }
}
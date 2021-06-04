package FactoryRunner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PageFactory.CompareProductsFactoryPage;
import pages.PageFactory.HomeFactoryPage;
import pages.PageFactory.ProductFactoryPage;
import pages.PageFactory.ProductsListFactoryPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Task1 {
    WebDriver driver;
    WebDriverWait wait;

    HomeFactoryPage homeFactoryPage;
    ProductsListFactoryPage productsListFactoryPage;
    ProductFactoryPage productFactoryPage;
    CompareProductsFactoryPage compareProductsFactoryPage;

    @BeforeClass
    public void actionsBeforeTestClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void actionsAfterTestClass() {
        driver.quit();
    }

    @BeforeMethod
    public void actionsBeforeTestMethod() {
        driver.get("https://rozetka.com.ua/");
        homeFactoryPage = new HomeFactoryPage(driver, wait);
        productsListFactoryPage = new ProductsListFactoryPage(driver, wait);
        productFactoryPage = new ProductFactoryPage(driver, wait);
        compareProductsFactoryPage = new CompareProductsFactoryPage(driver, wait);
    }

    @Test
    public void compareMonitors() {
        homeFactoryPage.locateToDropdown();
        homeFactoryPage.clickOnMonitorsLink();
        productsListFactoryPage.closeBanner();
        productsListFactoryPage.findElementByPrice(4000);
        productFactoryPage.clickOnCompareButton();
        assertTrue(productFactoryPage.productAddedToCompare());

        String monitorTitle = productFactoryPage.getMonitorTitle();
        int monitorPrice = productFactoryPage.getMonitorPrice();

        productFactoryPage.navigateBack();
        productFactoryPage.waitingForProductListPageHeader();
        productsListFactoryPage.findElementByPrice(monitorPrice);
        productFactoryPage.clickOnCompareButton();
        productFactoryPage.waitingForNumberTwoAppearNearCompareIcon();
        assertEquals(productFactoryPage.getNumberOfAddedItems(), 2);

        String secondMonitorTitle = productFactoryPage.getMonitorTitle();
        int secondMonitorPrice = productFactoryPage.getMonitorPrice();

        productFactoryPage.openComparePage();
        assertEquals((compareProductsFactoryPage.getAllProductPricesFromComparePage()).size(), 2);
        assertEquals(monitorTitle, compareProductsFactoryPage.getFirstProductName());
        assertEquals(secondMonitorTitle, compareProductsFactoryPage.getSecondProductName());
        assertEquals(secondMonitorPrice, compareProductsFactoryPage.getSecondProductPrice());
        assertEquals(monitorPrice, compareProductsFactoryPage.getFirstProductPrice());
    }
}
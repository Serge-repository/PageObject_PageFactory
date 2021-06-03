package ui_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PageObject.CompareProductsPage;
import pages.PageObject.HomePage;
import pages.PageObject.ProductPage;
import pages.PageObject.ProductsListPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class H24Task1 {
    WebDriver driver;
    WebDriverWait wait;

    HomePage homePage;
    ProductsListPage productsListPage;
    ProductPage productPage;
    CompareProductsPage compareProductsPage;

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
        homePage = new HomePage(driver, wait);
        productsListPage = new ProductsListPage(driver, wait);
        productPage = new ProductPage(driver, wait);
        compareProductsPage = new CompareProductsPage(driver, wait);
    }

    @Test
    public void compareMonitors() {
        homePage.locateToDropdown();
        homePage.clickOnMonitorsLink();
        productsListPage.closeBanner();
        productsListPage.findElementByPrice(4000);
        productPage.clickOnCompareButton();
        assertTrue(productPage.productAddedToCompare());

        String monitorTitle = productPage.getMonitorTitle();
        int monitorPrice = productPage.getMonitorPrice();

        productPage.navigateBack();
        productPage.waitingForProductListPageHeader();
        productsListPage.findElementByPrice(monitorPrice);
        productPage.clickOnCompareButton();
        productPage.waitingForNumberTwoAppearNearCompareIcon();
        assertEquals(productPage.getNumberOfAddedItems(), 2);

        String secondMonitorTitle = productPage.getMonitorTitle();
        int secondMonitorPrice = productPage.getMonitorPrice();

        productPage.openComparePage();
        assertEquals((compareProductsPage.getAllProductPricesFromComparePage()).size(), 2);
        assertEquals(monitorTitle, compareProductsPage.getFirstProductName());
        assertEquals(secondMonitorTitle, compareProductsPage.getSecondProductName());
        assertEquals(secondMonitorPrice, compareProductsPage.getSecondProductPrice());
        assertEquals(monitorPrice, compareProductsPage.getFirstProductPrice());
    }
}
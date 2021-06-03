package ui_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PageObject.ProductsListPage;

public class H24Task1_3 {
    WebDriver driver;
    WebDriverWait wait;

    ProductsListPage productsListPage;

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
        productsListPage = new ProductsListPage(driver, wait);
    }

    @Test
    public void searchByApple() {
        productsListPage.samsungPhonesSearch();
        productsListPage.scrollMethod();
        productsListPage.uncheckSamsungLabel();
        productsListPage.searchUsingProductSearchBar("Apple");
        productsListPage.checkAppleLabel();
        productsListPage.checkThatOnlyOneSelectedPhoneAvailable("Apple");
    }
}
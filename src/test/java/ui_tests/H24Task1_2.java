package ui_tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PageObject.TestBase;

import static org.testng.Assert.assertTrue;

public class H24Task1_2 extends TestBase {

    @BeforeClass
    public void actionsBeforeTestClass() {
        super.actionsBeforeTestClass();
    }

    @AfterClass
    public void actionsAfterTestClass() {
        super.actionsAfterTestClass();
    }

    @BeforeMethod
    public void actionsBeforeTestMethod() {
        super.actionsBeforeTestMethod();
    }

    @Test
    public void searchByPrice() {
        productsListPage.samsungPhonesSearch();
        productsListPage.setUpPriceLimits("5000", "15000");
        productsListPage.selectedManufacturersPriceList()
                .forEach(s -> assertTrue(s > 5000 && s < 15000, "Price is between 5k and 15k"));
    }
}

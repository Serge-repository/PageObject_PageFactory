package ui_tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PageObject.TestBase;

import static org.testng.Assert.assertTrue;

public class H24Task1_3 extends TestBase {

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
    public void searchByApple() {
        productsListPage.samsungPhonesSearch();
        productsListPage.uncheckSamsungLabel();
        assertTrue(productsListPage.searchUsingProductSearchBar("Apple").isDisplayed(), "Apple products selected");
        productsListPage.checkAppleLabel();
        productsListPage.selectedManufacturersList()
                .forEach(s -> assertTrue(s.getText().contains("Apple"), "Every product is Apple product"));
    }
}

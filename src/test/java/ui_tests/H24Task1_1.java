package ui_tests;

import org.testng.annotations.Test;
import pages.PageObject.TestBase;

import static org.testng.Assert.assertTrue;

public class H24Task1_1 extends TestBase {

    @Test
    public void searchByManufacturers() {
        productsListPage.samsungPhonesSearch();
        productsListPage.selectRequiredModels();
        productsListPage.selectedManufacturersList()
                .forEach(s -> assertTrue(s.getText().contains("Samsung") || s.getText().contains("Apple")
                        || s.getText().contains("Huawei"), "Samsung, Apple, Huawei selected"));
    }
}
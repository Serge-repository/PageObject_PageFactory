package FactoryRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PageFactory.TestBaseFactory;

import static org.testng.Assert.assertTrue;

public class H24Task2_1 extends TestBaseFactory {

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
    public void searchByManufacturers() {
        productsListFactoryPage.samsungPhonesSearch();
        productsListFactoryPage.selectRequiredModels();
        productsListFactoryPage.checkThatOnlySelectedPhonesAvailable()
                .forEach(s -> assertTrue(s.getText().contains("Samsung") || s.getText().contains("Apple")
                        || s.getText().contains("Huawei"), "Samsung, Apple, Huawei selected"));
    }
}
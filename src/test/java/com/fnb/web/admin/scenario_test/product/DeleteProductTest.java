package com.fnb.web.admin.scenario_test.product;

import Database.DataBase;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.product.combo.ComboManagementPage;
import com.fnb.web.admin.pages.product.combo.CreateComboPage;
import com.fnb.web.admin.pages.product.management.*;
import com.fnb.web.pos.pages.component.CheckOutOrderDialog;
import com.fnb.web.pos.pages.component.OpenLeftMenu;
import com.fnb.web.pos.pages.component.PosOrderDialog;
import com.fnb.web.pos.pages.InStorePage;
import com.fnb.web.setup.Setup;
import dataObject.Product.Product;
import dataObject.Product.ProductCategory;
import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DeleteProductTest extends CommonPages {
    private CreateProductPage createProductPage;
    private ProductManagementPage productManagementPage;
    private InStorePage inStorePage;
    private PosOrderDialog posOrderDialog;
    private CreateComboPage createComboPage;
    private ComboManagementPage comboManagementPage;
    private CheckOutOrderDialog checkOutOrderDialog;
    private Product productData;
    private ProductCategory productCategoryData;
    private String category;

    @BeforeClass
    public void BeforeClass() {
        createProductPage = new CreateProductPage(getDriver());
        productManagementPage = new ProductManagementPage(getDriver());
        createProductPage = new CreateProductPage(getDriver());
        createComboPage = new CreateComboPage(getDriver());
        comboManagementPage = new ComboManagementPage(getDriver());
        productData = getSetup().productData;
        productCategoryData = getSetup().productCategoryData;
        category = productCategoryData.getProductCategory().get(0).getName();
        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
    }

    @AfterClass
    public void AfterClass() {
        adminPage().helper.clearCookies();
    }

    @Test(testName = " FB-9615 : Verify system behavior after clicking on delete button")
    public void FB9615() throws SQLException, ClassNotFoundException {
        // Create a product with any kind of data
        String productName = createProductPage.createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());

        // Get product id in database
        DataBase dataBase = new DataBase();
        ResultSet data = dataBase.getData("" +
                "Select * from Product \n" +
                "Where Name = N'" + productName + "'");

        UUID idValue = dataBase.getFieldValue(data, "ID", UUID.class);

        data = dataBase.getData("Select Product.IsActive from Product\n" +
                "Where Id = '" + idValue + "'");
        boolean isActive = dataBase.getFieldValue(data, "IsActive", Boolean.class);
        // Verify the IsActive field (It should be 1 (true) because the product is in active)
        Assert.assertTrue(isActive, "The active field of " + productName + " is not set true");

        // Delete the product
        adminPage().helper.navigateToUrl(Setup.configObject.getUrlProductManagement());
        productManagementPage.deleteProduct(productName);

        data = dataBase.getData("Select Product.IsActive from Product\n" +
                "Where Id = '" + idValue + "'");
        isActive = dataBase.getFieldValue(data, "IsActive", Boolean.class);
        // Verify the IsActive field (It should be 0 (false) because the product is deleted (InActive))
        Assert.assertFalse(isActive, "This " + productName + " is deleted, but the active field is not set to 0 (false)");
    }

    @Test(testName = "FB-9616 : Verify system behavior after deleting product that is an order")
    public void FB9616() throws SQLException, ClassNotFoundException {
        // Create a product with any kind of data
        String productName = createProductPage.createAProductWithAnyTypeData(category);
        String adminTab = getDriver().getWindowHandle();

        // Open the new tab
        getDriver().switchTo().newWindow(WindowType.TAB);
        String posTab = getDriver().getWindowHandle();

        inStorePage = posPage().navigatetoInStorePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD, "Bình Thạnh");
        inStorePage.mainContentColumn
                .clickCategory(category)
                .clickAddToCard(productName);
        // Just handle for Payment Later
        inStorePage.rightCheckOutColumn.clickCreateOrder_PayLater();

        // Switch to admin tab
        getDriver().switchTo().window(adminTab);

        // Delete the product that have just been order
        productManagementPage.clickDeleteButton(productName);
        // Verify the content message
        String expectedContentMessage = "The product " + productName + " is related to open orders.\n" +
                "Please complete the order first!";
        Assert.assertEquals(productManagementPage.getCommonComponents().getDialogContentMessage(), expectedContentMessage, "The content message is not match");

        // Get the OrderID from the dialog
        String OrderId = adminPage().helper.getText(By.xpath("//div[@class='po-row-item']//a"));

        // Click "I got it" button
        Assert.assertEquals(adminPage().helper.getText(productManagementPage.getBtnPrimary()), "I got it!", "The button text is not match with expected. Please check");
        adminPage().helper.clickElement(productManagementPage.getBtnPrimary());
        // Deselect the product name
        productManagementPage.helper.refreshPage();

        // Switch to pos tab
        getDriver().switchTo().window(posTab);
        posOrderDialog = inStorePage.orderTypeBar.navigateToOrderList();
        checkOutOrderDialog = posOrderDialog.clickPay(OrderId);
        checkOutOrderDialog.clickPay();

        // Switch to admin tab
        getDriver().switchTo().window(adminTab);

        // Try to click the product again
        productManagementPage
                .deleteProduct(productName)
                .verifyProductIsDelete(productName);

        // Verify that the product that has just been deleted in admin is removed from pos web platform
        getDriver().switchTo().window(posTab);
        OpenLeftMenu openLeftMenu = new OpenLeftMenu(getDriver());
        adminPage().helper.clickElement(openLeftMenu.getBtnCollapse());
        inStorePage.mainContentColumn.clickCategory(category);
        Assert.assertFalse(adminPage().helper.isElementVisible(inStorePage.getProductEle(productName)), "The product is still visible in the category");
    }

    @Test(testName = "FB-11210 : Verify system behavior after deleting product that is an active combo")
    public void FB11210() {
        // Create a product
        String[] productName = {createProductPage.createAProductWithAnyTypeData(category)};

        // Create a combo which contains this product
        String comboName = createComboPage.createAActiveComboWithAnyTypeData(productName);

        // Delete the product that has just been added in the Combo
        adminPage().helper.navigateToUrl(Setup.configObject.getUrlProductManagement());
        productManagementPage.clickDeleteButton(productName[0]);
        // Verify the content message
        String expectedContentMessage = "The product " + productName[0] + " is related to an active combo.\n" +
                "Please stop the combo before deleting this product!";
        Assert.assertEquals(productManagementPage.getCommonComponents().getDialogContentMessage(), expectedContentMessage, "The content message is not match");

        // DeActive the combo
        adminPage().helper.navigateToUrl(Setup.configObject.getUrlComboManagement());
        comboManagementPage.deActiveCombo(comboName);

        // Try to delete the product again
        adminPage().helper.navigateToUrl(Setup.configObject.getUrlProductManagement());
        productManagementPage.deleteProduct(productName[0]);
    }

    // Sprint49
    @Test(testName = "FB-9610 : Verify delete confirmation when user click on delete button in product management list")
    public void FB9610() {
        // Create a product with any kind of data
        String productName = createProductPage.createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());

        // Delete the product
        productManagementPage.clickDeleteButton(productName);

        // Verify the message content: "Do you really want to delete product {product_name}. This action cannot be reverted!"
        String expectedMessage = "Do you really want to delete product " + productName + "?\n" +
                                            "This action cannot be reverted!";
        Assert.assertEquals(productManagementPage.getCommonComponents().getDialogContentMessage(), expectedMessage, "The message does not match with expected");

        // Reload page and clean data (delete this product)
        adminPage().helper.refreshPage();
        productManagementPage.deleteProduct(productName);
    }

    @Test(testName = "FB-9612 : Verify delete confirmation when user click on delete button in detail page")
    public void FB9612() {
        // Create a product with any kind of data
        String productName = createProductPage.createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());

        // Click on product --> product detail
        ProductDetailsPage productDetailsPage = productManagementPage.clickOnProduct(productName);
        adminPage().helper.clickElement(productDetailsPage.getBtnDelete());

        adminPage().helper.waitForElementToBeIntercepted();
        // Verify the message content: "Do you really want to delete product {product_name}. This action cannot be reverted!"
        String expectedMessage = "Do you really want to delete product ?\n" +
                                    "This action cannot be reverted!";
        Assert.assertEquals(productManagementPage.getCommonComponents().getDialogContentMessage(), expectedMessage, "The message does not match with expected");

        // Clean data
        productManagementPage.getCommonComponents().clickDeleteButton();
        productManagementPage.verifyProductIsDelete(productName);
    }

    @Test(testName = "FB-9611 : Verify delete confirmation when user click on delete button in edit product page")
    public void FB9611() {
        // Create a product with any kind of data
        String productName = createProductPage.createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());

        // Click on edit icon of product --> product detail
        ProductEditPage productEditPage = productManagementPage.clickProductEditIcon(productName);
        adminPage().helper.clickElement(productEditPage.getBtnDelete());

        adminPage().helper.waitForElementToBeIntercepted();
        // Verify the message content: "Do you really want to delete product {product_name}. This action cannot be reverted!"
        String expectedMessage = "Do you really want to delete product " + productName + "?\n" +
                                            "This action cannot be reverted!";
        Assert.assertEquals(productEditPage.getCommonComponents().getDialogContentMessage(), expectedMessage, "The message does not match with expected");

        // Clean data
        productEditPage.getCommonComponents().clickDeleteButton();
        productManagementPage.verifyProductIsDelete(productName);
    }
}

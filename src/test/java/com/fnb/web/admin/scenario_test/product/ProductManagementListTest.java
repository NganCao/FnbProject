package com.fnb.web.admin.scenario_test.product;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.PagesAdminSetup;
import com.fnb.web.admin.pages.home.HomePage;
import com.fnb.web.admin.pages.product.management.ProductManagementPage;
import com.fnb.web.admin.pages.store.staff.StaffManagementPage;
import com.fnb.web.setup.Setup;
import dataObject.Product.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static com.fnb.web.setup.Setup.configObject;
import static com.fnb.web.setup.Setup.productData;

// Sprint51
public class ProductManagementListTest extends CommonPages {
    Setup staffSetup;
    PagesAdminSetup staffAdminPage;
    HomePage staffHomePage;
    StaffManagementPage staffManagementPage_ForStaff;
    ProductManagementPage productManagementPage_ForStaff;
    private com.fnb.web.admin.pages.product.management.DataTest DataTest;
    private com.fnb.web.admin.pages.store.staff.DataTest staffDataTest;
    Helper helper;

    public PagesAdminSetup staffAdminPage() {
        return staffAdminPage;
    }

    public WebDriver driver2() {
        return staffSetup.driver;
    }

    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        helper = adminPage().helper;

        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
    }

    @Test(testName = "FB-12205 : Navigate to the Product management page by clicking on Product in the left menu")
    public void FB12205() {
        helper.navigateToUrl(configObject.getUrlBase());
        homePage().siderBar.clickMnuItemProduct();
    }

    @Test(testName = "FB-12442 : Verify the product data of product management page")
    public void FB12442() {
        // Create a product data test
        String productName = "product test" + helper.generateRandomNumber();
        homePage().helper.navigateToUrl(configObject.getUrlCreateProduct());
        createProductPage().createProducts(
                productName,
                productData.getProduct().get(1).getImage(),
                productData.getProduct().get(1).getTopping(),
                productData.getProduct().get(1).getOption(),
                productData.getProduct().get(1).getPrice(),
                productData.getProduct().get(1).getPlatforms(),
                productData.getProduct().get(1).getUnit(),
                productData.getProduct().get(1).getProductInventoryData(),
                productData.getProduct().get(1).getTax()
        );

        // Verify the data of the product in this page (product management page)
        // 1. Verify the price name and price value respectively
        List<Product.Information.Price> prices = productData.getProduct().get(1).getPrice();
        // If the product price have only one price, so we do not need to verify this
        if (prices.size() != 1) {
            for (int i=0; i < prices.size(); i++) {
                Double priceValue = helper.convertStringToDouble(prices.get(i).getPriceValue());
                String priceValueString = helper.formatDoubleToString(priceValue);
                String expectedPriceVariation = priceValueString + " - " + prices.get(i).getPriceName();

                Assert.assertTrue(helper.isElementVisible(By.xpath("("+commonComponents().getTableData(productName) + "//div[contains(@style, 'visibility: visible')]//span[text()='"+expectedPriceVariation+"']"+")[1]")), "Wrong price and value");
            }
        }
    }

    @Test(testName = " FB-12443 : Verify that we can search the product with lower case")
    public void FB12443() {
        homePage().helper.navigateToUrl(configObject.getUrlProductManagement());
        String cafeSuaAuto = productData.getProduct().get(2).getName();
        String lowerCaseProductName = cafeSuaAuto.toLowerCase();

        helper.clickElement(commonComponents().getSearchIcon());
        helper.clearText(productManagementPage().txtSearch);
        helper.enterText(productManagementPage().txtSearch, lowerCaseProductName);
        helper.waitForElementVisible(By.xpath(commonComponents().getTableData(cafeSuaAuto)), "The "+cafeSuaAuto+" is not visible");
    }

    @Test(testName = "FB-12444 : Verify that we can search the product with unsigned word")
    public void FB12444() {
        String cafeSuaAuto = productData.getProduct().get(2).getName();
        String unsignedWordProductName = helper.removeDiacritics(cafeSuaAuto);

        homePage().helper.navigateToUrl(configObject.getUrlProductManagement());
        helper.clickElement(commonComponents().getSearchIcon());
        helper.clearText(productManagementPage().txtSearch);
        helper.enterText(productManagementPage().txtSearch, unsignedWordProductName);
        helper.waitForElementVisible(By.xpath(commonComponents().getTableData(cafeSuaAuto)), "The "+cafeSuaAuto+" is not visible");
    }

    @Test(testName = "FB-12446 : Checking the result when splitting the normal product name into each word")
    public void FB12446() {
        String cafeSuaAuto = productData.getProduct().get(2).getName();
        String[] words = cafeSuaAuto.split("\\s+");
        for (int i=0; i < words.length; i++) {
            homePage().helper.navigateToUrl(configObject.getUrlProductManagement());
            helper.clickElement(commonComponents().getSearchIcon());
            helper.clearText(productManagementPage().txtSearch);
            helper.enterText(productManagementPage().txtSearch, words[i]);
            helper.waitForElementVisible(By.xpath(commonComponents().getTableData(cafeSuaAuto)), "The "+cafeSuaAuto+" is not visible");
        }
    }

    @Test(testName = "FB-12445 : Checking the result when splitting the lowercase of product name into each word")
    public void FB12445() {
        String cafeSuaAuto = productData.getProduct().get(2).getName();
        String lowerCaseProductName = cafeSuaAuto.toLowerCase();
        String[] words = lowerCaseProductName.split("\\s+");
        for (int i=0; i < words.length; i++) {
            homePage().helper.navigateToUrl(configObject.getUrlProductManagement());
            helper.clickElement(commonComponents().getSearchIcon());
            helper.clearText(productManagementPage().txtSearch);
            helper.enterText(productManagementPage().txtSearch, words[i]);
            helper.waitForElementVisible(By.xpath(commonComponents().getTableData(cafeSuaAuto)), "The "+cafeSuaAuto+" is not visible");
        }
    }

    @Test(testName = "FB-12447 : Checking the result when splitting the uppercase of product name into each word")
    public void FB12447() {
        String cafeSuaAuto = productData.getProduct().get(2).getName();
        String lowerCaseProductName = cafeSuaAuto.toUpperCase();
        String[] words = lowerCaseProductName.split("\\s+");
        for (int i=0; i < words.length; i++) {
            homePage().helper.navigateToUrl(configObject.getUrlProductManagement());
            helper.clickElement(commonComponents().getSearchIcon());
            helper.clearText(productManagementPage().txtSearch);
            helper.enterText(productManagementPage().txtSearch, words[i]);
            helper.waitForElementVisible(By.xpath(commonComponents().getTableData(cafeSuaAuto)), "The "+cafeSuaAuto+" is not visible");
        }
    }
}

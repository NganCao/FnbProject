package com.fnb.web.admin.scenario_test.product;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fnb.web.setup.Setup.*;

public class TaxonProductTest extends CommonPages {
    Helper helper;
    @BeforeClass
    public void beforeTest() {
        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
        helper = adminPage().helper;
    }
    @AfterClass
    public void clearCookie() {adminPage().helper.clearCookies();}

    // Sprint49
    @Test(testName = "FB-11362 : Verify tax on product create form")
    public void FB11362 () {
        //**Checkpoint: Check if there is an optional dropdown select option.
        helper.navigateToUrl(Setup.configObject.getUrlCreateProduct());
        helper.scrollToElementAtBottom(createProductPage().getSelSearchTax());
        helper.waitForElementVisible(createProductPage().getSelSearchTax());

        //**Checkpoint: Ensure that the placeholder reads “Please select Tax”.
        Assert.assertEquals(helper.getText(createProductPage().getPlaceHolderTax()), DataTest.TAX_PLACEHOLDER, "The tax placeholder is not matched with expected text");

        //**Checkpoint: Test if all local Taxes with type “Sell” are loadable options.
        //**Checkpoint: Check the taxes with the type "Import" are not loaded in the tax options
        for (int i=0 ; i<taxData.getTax().size(); i++) {
            if (taxData.getTax().get(i).getTaxType().equals("Selling")) {
                String formatTax = taxData.getTax().get(i).getName() + " " + "("+taxData.getTax().get(i).getPercentage()+"%)";
                createProductPage().selectTax(formatTax);
            }
            else {
                String formatTax = taxData.getTax().get(i).getName() + " " + "("+taxData.getTax().get(i).getPercentage()+"%)";
                helper.scrollToElementAtTop(createProductPage().getSelSearchTax());
                helper.enterText(createProductPage().getSelSearchTax(), formatTax);
                helper.waitForElementVisible(By.xpath("//div[@class='ant-empty-description' and normalize-space()='No data']"));
            }
        }
    }

    @Test(testName = "FB-11363 : Verify tax on product edit form")
    public void FB11363 () {
        //**Checkpoint: Confirm if the price component has an added field for Tax
        helper.navigateToUrl(Setup.configObject.getUrlProductManagement());

        //================== click edit product ==================
        productManagementPage().clickProductEditIcon(productData.getProduct().get(0).getName());
        productEditPage().clearAllFields();

        //================== Scroll to tax ==================
        helper.scrollToElementAtBottom(productEditPage().getSelSearchTax());
        helper.waitForElementVisible(productEditPage().getSelSearchTax());

        //**Checkpoint: Ensure that the placeholder reads “Please select Tax”
        Assert.assertEquals(helper.getText(productEditPage().getPlaceHolderTax()), DataTest.TAX_PLACEHOLDER, "The tax placeholder is not matched with expected text");

        //**Checkpoint: Test if all local Taxes with type “Sell” are loadable options.
        for (int i=0 ; i<taxData.getTax().size(); i++) {
            if (taxData.getTax().get(i).getTaxType().equals("Selling")) {
                String formatTax = taxData.getTax().get(i).getName() + " " + "("+taxData.getTax().get(i).getPercentage()+"%)";
                productEditPage().selectTax(formatTax);
            }
            else {
                String formatTax = taxData.getTax().get(i).getName() + " " + "("+taxData.getTax().get(i).getPercentage()+"%)";
                helper.scrollToElementAtTop(productEditPage().getSelSearchTax());
                helper.enterText(productEditPage().getSelSearchTax(), formatTax);
                helper.waitForElementVisible(By.xpath("//div[@class='ant-empty-description' and normalize-space()='No data']"));
            }
        }
    }

    @Test(testName = "FB-11364 : Verify if tax information can be added in format “(tax_name) (tax_value)%")
    public void FB11364() {
        helper.navigateToUrl(Setup.configObject.getUrlProductManagement());
        productManagementPage().clickOnProduct(productData.getProduct().get(0).getName());
        helper.waitForElementVisible(By.xpath("//span[normalize-space()='"+taxData.getTax().get(0).getName()+" ("+taxData.getTax().get(0).getPercentage()+" %)']"));
    }
}

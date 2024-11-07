package com.fnb.web.admin.scenario_test.product;

import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.pos.pages.InStorePage;
import com.fnb.web.setup.Setup;
import dataObject.Product.ProductCategory;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// Sprint49
public class ActiveDeactivateProductTest extends CommonPages {
    private ProductCategory productCategoryData;
    private String category;
    String posTab;
    String adminTab;

    @BeforeClass
    public void beforeClass() {
        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
        productCategoryData = getSetup().productCategoryData;

        // Get the specific created category data
        category = productCategoryData.getProductCategory().get(0).getName();

        // Handle tab for ADMIN and POS
        adminTab = getDriver().getWindowHandle();
        // Open the new tab
        getDriver().switchTo().newWindow(WindowType.TAB);
        posTab = getDriver().getWindowHandle();
    }

    @AfterClass
    public void afterClass() {
        adminPage().helper.switchTab(adminTab);
        adminPage().helper.clearCookies();
    }

    @BeforeMethod
    public void beforeMethod() {
        adminPage().helper.switchTab(adminTab);
    }

    @Test(testName = "FB-11686 : Verify the active and deactivate in product detail")
    public void FB11275() {
        // ** Case:  FB-11275 : Visit the product detail page and confirm if the product status is active
        // Create a product with any kind of data
        String productName = createProductPage().createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());
        commonComponents().waitSuccessToastHidden();

        productManagementPage().clickOnProduct(productName);

        // Check the product status is Active
        Assert.assertEquals(productDetailsPage().StatusText(), DataTest.ACTIVE_STATUS, "The status is not correct.");

        productDetailsPage()
                //.clickDropDownButton()
                .clickDeactivateButton();
        commonComponents().waitSuccessToast();
        commonComponents().waitSuccessToastHidden();

        // Check the product status is Inactive
        Assert.assertEquals(productDetailsPage().StatusText(), DataTest.INACTIVE_STATUS, "The status is not correct.");

        // Check after click CTA button Deativate --> button change to Active
        //productDetailsPage().clickDropDownButton();
        productDetailsPage().helper.waitForElementVisible(productDetailsPage().getBtnActive());

        // ** Case: FB-11277 : Visit the product detail page and confirm if the product status is inactive
        productDetailsPage().helper.navigateToUrl(Setup.configObject.getUrlProductManagement());

        productManagementPage().clickOnProduct(productName);

        // Check the product status is Inactivate
        Assert.assertEquals(productDetailsPage().StatusText(), DataTest.INACTIVE_STATUS, "The status is not correct.");

        productDetailsPage()
                //.clickDropDownButton()
                .clickActiveButton();
        commonComponents().waitSuccessToast();
        commonComponents().waitSuccessToastHidden();

        // Check the product status is Active
        Assert.assertEquals(productDetailsPage().StatusText(), DataTest.ACTIVE_STATUS, "The status is not correct.");

        // Check after click CTA button Deativate --> button change to Active
        //productDetailsPage().clickDropDownButton();
        productDetailsPage().helper.waitForElementVisible(productDetailsPage().getBtnDeactivate());
    }

    @Test(testName = "FB-11687 : Verify active and deactivate in edit product")
    public void FB11687() {
        // **Case: FB-11276 : Navigate to the edit product page and check if there’s a CTA button to deactivate
        // Create a product with any kind of data
        String productName = createProductPage().createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());
        commonComponents().waitSuccessToastHidden();

        // Click edit product
        productManagementPage().clickProductEditIcon(productName);

        // Check the product status is Active
        Assert.assertEquals(productDetailsPage().StatusText(), DataTest.ACTIVE_STATUS, "The status is not correct.");

        productDetailsPage()
                //.clickDropDownButton()
                .clickDeactivateButton();
        commonComponents().waitSuccessToast();
        commonComponents().waitSuccessToastHidden();

        // Check the product status is Inactive
        Assert.assertEquals(productDetailsPage().StatusText(), DataTest.INACTIVE_STATUS, "The status is not correct.");

        // Check after click CTA button Deativate --> button change to Active
        //productDetailsPage().clickDropDownButton();
        productDetailsPage().helper.waitForElementVisible(productDetailsPage().getBtnActive());

        // **Case: FB-11279 : Verify through integration that the deactivated product is not listed on the POS but still exists in store
        adminPage().helper.switchTab(posTab);
        // Integration --> Check product will not be listed on the POS but still exist in store
        InStorePage inStorePage = posPage().navigatetoInStorePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD, "Bình Thạnh");
        inStorePage.mainContentColumn.clickCategory(category);
        Assert.assertFalse(inStorePage.helper.isElementVisible(inStorePage.getProductEle(productName)), "The product is still visible in the category");

        // **Case: FB-11278 : Navigate to edit product page and check if there’s a CTA button to activate.

        adminPage().helper.switchTab(adminTab);
        adminPage().helper.navigateToUrl(Setup.configObject.getUrlProductManagement());

        // Click edit product
        productManagementPage().clickProductEditIcon(productName);
        productManagementPage().helper.waitForElementVisible(productEditPage().getBtnCancel());

        // Check the product status is Active
        Assert.assertEquals(productDetailsPage().StatusText(), DataTest.INACTIVE_STATUS, "The status is not correct.");

        productDetailsPage()
                //.clickDropDownButton()
                .clickActiveButton();
        commonComponents().waitSuccessToast();
        commonComponents().waitSuccessToastHidden();

        // Check the product status is Inactive
        Assert.assertEquals(productDetailsPage().StatusText(), DataTest.ACTIVE_STATUS, "The status is not correct.");

        // Check after click CTA button Deativate --> button change to Active
        //productDetailsPage().clickDropDownButton();
        productDetailsPage().helper.waitForElementVisible(productDetailsPage().getBtnDeactivate());

        // **Case: FB-11280 : Verify through integration that activated products are listed on POS web
        adminPage().helper.switchTab(posTab);
        // Integration --> Check product will not be listed on the POS but still exist in store
        adminPage().helper.refreshPage();
        inStorePage.mainContentColumn.clickCategory(category);
        Assert.assertTrue(inStorePage.helper.isElementVisible(inStorePage.getProductEle(productName)), "The product is not visible in the category");
    }
}

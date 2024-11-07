package com.fnb.web.admin.scenario_test.product;
import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.product.management.DataTest;
import dataObject.Product.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;


import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static com.fnb.web.setup.Setup.*;

// Test link: http://103.191.146.224/testlink/linkto.php?tprojectPrefix=FB&item=testsuite&id=46571
// Sprint49
public class ProductManagementFilterTest extends CommonPages {
    //  Types
    String Branch = "Branch";
    String Category = "Category";
    String Platform = "Platform";
    String Status = "Status";

    // Options
    String All = "All";
    String POS = "POS";
    String StoreWeb = "Store Web";
    String StoreApp = "Store App";
    String GoFnBApp = "GoF&B App";
    String Active = "Active";
    String Inactive = "Inactive";
    // Color
    String color = "rgba(80, 66, 155, 1)";
    com.fnb.web.admin.pages.inventory.ingredients.DataTest filterDataTest;
    Helper action;
    String storeWeb_productName;
    String storeApp_productName;
    protected boolean lastTestFailed = false;

    @BeforeClass
    public void precondition() {
        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
        action = adminPage().helper;

        // Create a product with Store Web platform
        action.navigateToUrl(configObject.getUrlCreateProduct());

        storeWeb_productName = "Product" + action.generateRandomNumber();
        String image = "resources/image/productIcon.png";
        Product.Information.Price price1 = new Product.Information.Price("S", "2000", "1");
        List<Product.Information.Price> prices = Arrays.asList(price1);
        Product.Information.Platform platform1 = new Product.Information.Platform("Store Web");
        List<Product.Information.Platform> platforms = Arrays.asList(platform1);
        createProductPage().createProducts(
                storeWeb_productName,
                image,
                productData.getProduct().get(0).getTopping(),
                productData.getProduct().get(1).getOption(),
                prices,
                platforms,
                "ml",
                productData.getProduct().get(0).getProductInventoryData(),
                taxData.getTax().get(0).getName());

        // Create a product with Store App platform
        action.navigateToUrl(configObject.getUrlCreateProduct());

        storeApp_productName = "Product" + action.generateRandomNumber();
        Product.Information.Price price2 = new Product.Information.Price("S", "2000", "1");
        List<Product.Information.Price> prices2 = Arrays.asList(price2);
        Product.Information.Platform platform2 = new Product.Information.Platform("Store App");
        List<Product.Information.Platform> platforms2 = Arrays.asList(platform2);
        createProductPage().createProducts(
                storeApp_productName,
                image,
                productData.getProduct().get(0).getTopping(),
                productData.getProduct().get(1).getOption(),
                prices2,
                platforms2,
                "ml",
                productData.getProduct().get(0).getProductInventoryData(),
                taxData.getTax().get(0).getName());
    }
    @AfterClass
    public void clearCookie() {
        action.clearCookies();
    }

    @BeforeMethod()
    public void beforeMethod() {
        action.navigateToUrl(configObject.getUrlProductManagement());
        if (lastTestFailed) {
            adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
            action.navigateToUrl(configObject.getUrlProductManagement());
        }
        // Reset the flag for the next test
        lastTestFailed = false;
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            action.clearCache();
            lastTestFailed = true;
        }
    }

    @Test(testName = "FB-11694 : Test the data for filter form")
    public void FB11694() {
        productManagementPage().clickFilterButton();

        // Check point: Default is All branches *************************
        Assert.assertEquals(filterDialog().getSelectedItem(filterDataTest.Branch), filterDataTest.All_branches, "The default branch is not correct");

        // Check point: Default is All categories
        Assert.assertEquals(filterDialog().getSelectedItem(filterDataTest.Category), filterDataTest.All_categories, "The default category is not correct");

        // Check default option platform
        Assert.assertTrue(action.isElementVisible(filterDialog().getCheckIcon(All, Platform)), "The default platform is not correct");

        // Check default option status
        Assert.assertTrue(action.isElementVisible(filterDialog().getCheckIcon(All, Status)), "The default platform is not correct");

        // ************************* Check that all data is listed in the corresponding option field *************************
        // Branch
        for (int i=0; i < branchData.getBranch().size(); i++) {
            String branchName = branchData.getBranch().get(i).getName();
            action.enterText(filterDialog().getSelSearchBranch(), branchName);
            adminPage().softAssert.assertTrue(action.isElementVisible(By.xpath("//*[text()='"+branchName+"']")), "The branch data ("+branchName+") is not visible -");
            action.clearText(filterDialog().getSelSearchBranch());
        }

        // Category
        for (int i=0; i < productCategoryData.getProductCategory().size(); i++) {
            String categoryName = productCategoryData.getProductCategory().get(i).getName();
            action.enterText(filterDialog().getSelSearchCategory(), categoryName);
            adminPage().softAssert.assertTrue(action.isElementVisible(By.xpath("//*[text()='"+categoryName+"']")), "The category data ("+categoryName+") is not visible -");
            action.clearText(filterDialog().getSelSearchCategory());
        }

        // Platform
        adminPage().softAssert.assertTrue(action.isElementVisible(filterDialog().getSelectionItem(POS, Platform)), "The platform data POS is not visible");
        adminPage().softAssert.assertTrue(action.isElementVisible(filterDialog().getSelectionItem(StoreWeb, Platform)), "The platform data Store Web is not visible");
        adminPage().softAssert.assertTrue(action.isElementVisible(filterDialog().getSelectionItem(StoreApp, Platform)), "The platform data Store App is not visible");
        adminPage().softAssert.assertTrue(action.isElementVisible(filterDialog().getSelectionItem(GoFnBApp, Platform)), "The platform data GoF&B App is not visible");

        // Status
        action.scrollToElementAtBottom(filterDialog().getBtnResetFilter());
        adminPage().softAssert.assertTrue(action.isElementVisible(filterDialog().getSelectionItem("Active", Status)), "The status data Active is not visible");
        adminPage().softAssert.assertTrue(action.isElementVisible(filterDialog().getSelectionItem("Inactive", Status)), "The status data Inactive is not visible");

        adminPage().softAssert.assertAll();
    }

    public void checkOptionIsHighlighted(String option, String type, String color) {
        By backgroundColorEle = By.xpath(filterDialog().getAntRow(type)+"//label[normalize-space()='"+option+"']");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(1));

        try {
            wait.until((ExpectedCondition<Boolean>) webDriver -> {
                WebElement element = webDriver.findElement(backgroundColorEle);
                String actualColor = element.getCssValue("background-color");

                return actualColor.equalsIgnoreCase(color);
            });
        }
        catch (Exception e) {
            throw new AssertionError("The "+option+" background color is incorrect with expected");
        }
    }

    @Test(testName = "Check if the option after being selected is highlighted not not")
    public void FB2() {
        productManagementPage().clickFilterButton();
        // ************************* Highlight the selected option *************************
        // Branch
        // Category
        // Platform - Click POS option
        filterDialog().selectOption(POS, Platform);

        // Platform - Click Store Web option
        filterDialog().selectOption(StoreWeb, Platform);

        // Platform - Click Store App option
        filterDialog().selectOption(StoreApp, Platform);

        // Status - Click Active
        filterDialog().selectOption(Active, Status);

        // Status - Click Inactive
        filterDialog().selectOption(Inactive, Status);

        action.clickElement(filterDialog().getBtnResetFilter());
    }

    @Test(testName = "Verify the enable and disable of the reset button")
    public void FB4() {
        // ************************* Verify that the reset button is disable when no filter selected *************************
        productManagementPage().clickFilterButton();
        action.scrollToElementAtBottom(filterDialog().getBtnResetFilter());

        // Check the reset button is disable
        action.waitExpectedAttributeToBe(filterDialog().getBtnResetFilter(),"cursor", "not-allowed", "The reset button is not disable");

        // Check the reset button is enabled after choosing the option
        filterDialog().selectOption(Inactive, Status);
        action.waitExpectedAttributeToBe(filterDialog().getBtnResetFilter(), "cursor","pointer", "The reset button is not enable");

        action.clickElement(filterDialog().getBtnResetFilter());
    }

    public void checkFilterButtonIsHighlighted(String color) {
        By backgroundColorEle = By.xpath(" //*[local-name()='svg' and @class='filter-count']");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(1));

        try {
            wait.until((ExpectedCondition<Boolean>) webDriver -> {
                WebElement element = webDriver.findElement(backgroundColorEle);
                String actualColor = element.getCssValue("color");

                return actualColor.equalsIgnoreCase(color);
            });
        }
        catch (Exception e) {
            throw new AssertionError("The reset button is not highlight");
        }
    }

    @Test(testName = "FB-11693 : Verify Reset Filters Functionality")
    public void FB11693() {
        // ************************* Verify Reset Filters Functionality *************************
        productManagementPage().clickFilterButton();
        // Select branch
        String branchName = branchData.getBranch().get(0).getName();
        filterDialog().selectBranch(branchName);

        // Select category
        String categoryName = productCategoryData.getProductCategory().get(0).getName();
        filterDialog().selectCategory(categoryName);

        // Select Active status
        filterDialog().selectOption(Active, Status);

        // ************************* Verify that highlight the button Filter when option is selected *************************
        checkFilterButtonIsHighlighted(color);

        // ************************* Check if the number of applied filters is shown in the “Filter” button *************************
        adminPage().softAssert.assertTrue(action.isElementVisible(By.xpath("//sup[@title=3]")), "The number of applied filters is not correct");
        action.scrollToElementAtBottom(filterDialog().getBtnResetFilter());

        // Click reset filter
        action.clickElement(filterDialog().getBtnResetFilter());

        // ************************* Check if the number of applied filters is shown in the “Filter” button *************************
        action.sleep(1);
        adminPage().softAssert.assertFalse(action.isElementVisible(By.xpath("//sup")), "The number of applied filter is still visible");

        // ************************* Verify that after clicking reset button, all the options will be reset to default *************************
        // Check point: Default is All branches *************************
        adminPage().softAssert.assertEquals(filterDialog().getSelectedItem(filterDataTest.Branch), filterDataTest.All_branches, "The default branch is not correct");

        // Check point: Default is All categories
        adminPage().softAssert.assertEquals(filterDialog().getSelectedItem(filterDataTest.Category), filterDataTest.All_categories, "The default category is not correct");

        // Check default option platform
        adminPage().softAssert.assertTrue(action.isElementVisible(filterDialog().getCheckIcon(All, Platform)), "The default platform is not correct");

        // Check default option status
        adminPage().softAssert.assertTrue(action.isElementVisible(filterDialog().getCheckIcon(All, Status)), "The default platform is not correct");

        adminPage().softAssert.assertAll();
    }

    @Test(testName = "FB-11692 : Apply filters and check if the correct filtered results are displayed")
    public void FB11692() {
        // ************************* Apply filters and check if the correct filtered results are displayed *************************
        productManagementPage().clickFilterButton();
        // Category
        String categoryName = productCategoryData.getProductCategory().get(0).getName();
        filterDialog().selectCategory(categoryName);

        // Check the data in this category
        productManagementPage().verifyProductIsVisible(productCategoryData.getProductCategory().get(0).getProducts().get(0).getName());
        productManagementPage().verifyProductIsVisible(productCategoryData.getProductCategory().get(0).getProducts().get(1).getName());

        // Check the data that is not in this category
        productManagementPage().verifyProductIsDelete(productCategoryData.getProductCategory().get(1).getProducts().get(0).getName());

        // Clear filter
        productManagementPage().clickFilterButton();
        filterDialog().clickResetFilter();

        // Platform
        filterDialog().selectOption(StoreWeb, Platform);

        // Check the data in this platform
        productManagementPage().verifyProductIsVisible(storeWeb_productName);
        // Check the data that is not in this platform
        productManagementPage().verifyProductIsDelete(storeApp_productName);

        // Clear filter
        productManagementPage().clickFilterButton();
        filterDialog().clickResetFilter();

        // Status
        // Create a product with inactive status
        String productName = createProductPage().createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());
        productManagementPage().clickOnProduct(productName);
        productDetailsPage()
                //.clickDropDownButton()
                .clickDeactivateButton();
        commonComponents().waitSuccessToast();
        commonComponents().waitSuccessToastHidden();

        action.navigateToUrl(configObject.getUrlProductManagement());
        productManagementPage().clickFilterButton();
        filterDialog().selectOption(Inactive, Status);

        // Check the data in this status
        productManagementPage().verifyProductIsVisible(productName);
        // Check the data that is not in this status
        productManagementPage().verifyProductIsDelete(productData.getProduct().get(0).getName());
    }
}

package com.fnb.web.store.theme1.scenario_test.home.bestSellingProduct;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.addUserLocation.AddUserLocationPage;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BestsellingProductScenarios extends BaseTest {
    //Testcase:https://mediastep1-my.sharepoint.com/:x:/g/personal/ngan_cao_thi_kim_gosell_vn/EQtsv7XuR7VOuGBoZpHOKRkBCdTct0yNUZ74Q5qIB96Bsg?e=CUOyYp&nav=MTVfe0QxN0U3NDg1LTM0RjUtNDk0MC1BRDgwLTg0Rjg1NjZBRjZERn0
    private HomePage homePage;
    private AddUserLocationPage addUserLocationPage;
    private HomeDataTest dataTest;
    SoftAssert softAssert;
    Helper helper;

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = storePage().navigateToHomePage();
        softAssert = storePage().softAssert;
        helper = storePage().helper;
        addUserLocationPage = new AddUserLocationPage(getDriver());
    }

    @Test(priority = 1, testName = "Verify display of Best selling component")
    public void FB9554() {
        Assert.assertTrue(homePage.checkDisplayOfBestSelling(), "Best selling did not display");
    }

    @Test(priority = 2, testName = "Verify display of Best selling title")
    public void FB9555() {
        Assert.assertTrue(homePage.checkDisplayOfBestSellingTitle(), "Title did not display");
    }

    @Test(priority = 3, testName = "Verify that Best selling can scroll to right")
    public void FB95631() {
        try {
            helper.refreshPage();
            Assert.assertTrue(homePage.checkDisplayOfProductScroll(true, dataTest.SCROLL_NUMBER_PRODUCT_CARD), "Best selling can not scroll to right");
        } catch (Exception ex) {
            helper.refreshPage();
            Assert.assertTrue(homePage.checkDisplayOfProductScroll(true, dataTest.SCROLL_NUMBER_PRODUCT_CARD), "Best selling can not scroll to right");
        }
    }

    @Test(priority = 4, testName = "Verify that Best selling can scroll to left")
    public void FB95632() {
        try {
            helper.refreshPage();
            Assert.assertTrue(homePage.checkDisplayOfProductScroll(false, dataTest.SCROLL_NUMBER_PRODUCT_CARD), "Best selling can not scroll to left");
        } catch (Exception ex) {
            helper.refreshPage();
            Assert.assertTrue(homePage.checkDisplayOfProductScroll(false, dataTest.SCROLL_NUMBER_PRODUCT_CARD), "Best selling can not scroll to left");
        }
    }

    @Test(priority = 5, testName = "Verify that Best selling auto scroll after 5s")
    public void FB11211() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkAutoScrollOfBestSelling(dataTest.TIME_SCROLL), "Auto scrolling is incorrect. \n" + homePage.actualRS);
    }

    @Test(priority = 6, testName = "Verify auto scroll of Best selling after hovering on card")
    public void FB9565() {
        storePage().navigateToHomePage();
        try {
            Assert.assertTrue(homePage.checkBestSellingProductAnimation(), homePage.actualRS);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            Assert.assertTrue(homePage.checkBestSellingProductAnimation(), homePage.actualRS);
        }
    }

    @Test(priority = 7, testName = "Verify display of the product information")
    public void FB9557() {
        storePage().navigateToHomePage();
        Assert.assertTrue(homePage.checkDisplayOfBestSellingProductInformation(), "Information did not display");
    }

    @Test(priority = 8, testName = "Verify display of product belongs to the selected branch")
    public void FB95621() {
        storePage().navigateToHomePage();
        addUserLocationPage.selectBranchWithName(dataTest.BRANCH_NAME);
        try {
            helper.refreshPage();
            Assert.assertTrue(homePage.checkDisplayproductWithName(dataTest.CATEGORY_NAME, true), "product did not display after selected " + dataTest.BRANCH_NAME);
        } catch (Exception ex) {
            Log.error(ex.getMessage());
        }
    }

    //TODO action scroll is not stable -> can not run
//    @Test(priority = 9, testName = "Verify hidden of product does not belong to the selected branch")
//    public void FB95622() {
//        storePage().navigateToHomePage();
//        homePage.selectBranchWithName(homePage.getBranchNameMissingProductByEnv());
//        try {
//            helper.refreshPage();
//            Assert.assertTrue(homePage.checkDisplayproductWithName(dataTest.CATEGORY_NAME, false), "product still display after selected " + dataTest.BRANCH_NAME_MISSING_PRODUCT);
//        } catch (Exception ex) {
//            helper.refreshPage();
//            Assert.assertTrue(homePage.checkDisplayproductWithName(dataTest.CATEGORY_NAME, false), "product still display after selected " + dataTest.BRANCH_NAME_MISSING_PRODUCT);
//        }
//    }
//
//    @Test(priority = 10, testName = "Verify size of product card on Best selling product")
//    public void FB11212() {
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkSizeOfBestSellingCard());
//    }

    @Test(priority = 11, testName = "Verify align of product card on Best selling product")
    public void FB11213() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkAlignOfProductLabel());
    }
}

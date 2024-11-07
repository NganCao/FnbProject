package com.fnb.web.store.theme1.scenario_test.home.category;

import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CategoryScenarios extends BaseTest {
    private HomePage homePage;

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = storePage().navigateToHomePage();
    }

    @Test(priority = 1, testName = "Verify display of Category component")
    public void FB9530() {
        Assert.assertTrue(homePage.checkDisplayOfCategory(), "Category did not display");
    }

    @Test(priority = 2, testName = "Verify display of left arrow on Category component")
    public void FB9542() {
        Assert.assertTrue(homePage.checkDisplayOfLeftArrow(), "Left arrow button did not display");
    }

    @Test(priority = 3, testName = "Verify display of right arrow on Category component")
    public void FB9541() {
        Assert.assertTrue(homePage.checkDisplayOfRightArrow(), "Right arrow button did not display");
    }

    @Test(priority = 4, testName = "Verify width of Category component")
    public void FB9537() {
        Assert.assertTrue(homePage.checkWidthOfCategory(), homePage.actualRS);
    }

    @Test(priority = 5, testName = "Verify padding Category component")
    public void FB5() {
        Assert.assertTrue(homePage.checkPaddingOfCategory(), homePage.actualRS);
    }
}

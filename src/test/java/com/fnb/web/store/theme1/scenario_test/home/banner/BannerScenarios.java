package com.fnb.web.store.theme1.scenario_test.home.banner;

import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BannerScenarios extends BaseTest {
    //Testcase:https://mediastep1-my.sharepoint.com/:x:/g/personal/ngan_cao_thi_kim_gosell_vn/EQtsv7XuR7VOuGBoZpHOKRkBCdTct0yNUZ74Q5qIB96Bsg?e=CUOyYp&nav=MTVfe0QxN0U3NDg1LTM0RjUtNDk0MC1BRDgwLTg0Rjg1NjZBRjZERn0
    private HomePage homePage;
    private HomeDataTest homeDataTest;

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = storePage().navigateToHomePage();
    }

    @Test(priority = 1, testName = "Verify display of banner")
    public void FB9446() {
        Assert.assertTrue(homePage.checkDisplayOfBanner(), "Banner did not display");
    }

    @Test(priority = 2, testName = "Verify display of banner")
    public void FB9448() {
        Assert.assertTrue(homePage.checkDisplayOfBanner(), "Banner did not display");
    }

    @Test(priority = 3, testName = "Verify behavior of banner after 5s")
    public void FB9449() {
        Assert.assertTrue(homePage.checkDisplayBannerSliderAfter5s(), homePage.actualRS);
    }

    @Test(priority = 4, testName = "Verify when clicking on banner")
    public void FB9453() {
        Assert.assertTrue(homePage.checkBannerClickable(), homePage.actualRS);
    }

    @Test(priority = 5, testName = "Verify width of banner")
    public void FB9447() {
        storePage().navigateToHomePage();
        Assert.assertTrue(homePage.checkWidthOfBanner(), homePage.actualRS);
    }

    @Test(priority = 6, testName = "Verify display of banner after clicking dot to next slide")
    public void FB9450() {
        Assert.assertTrue(homePage.checkDisplayBannerAfterClicksDot(homeDataTest.MAXIMUM_NUMBER_BANNER));
    }
}

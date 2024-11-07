package com.fnb.web.store.theme1.scenario_test.home.footer;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FooterScenarios extends BaseTest {
    //Testcase:https://mediastep1-my.sharepoint.com/:x:/g/personal/ngan_cao_thi_kim_gosell_vn/EQtsv7XuR7VOuGBoZpHOKRkBCdTct0yNUZ74Q5qIB96Bsg?e=CUOyYp&nav=MTVfe0QxN0U3NDg1LTM0RjUtNDk0MC1BRDgwLTg0Rjg1NjZBRjZERn0
    private HomePage homePage;
    private HomeDataTest dataTest;
    Helper helper;

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = storePage().navigateToHomePage();
        helper = storePage().helper;
    }

    @Test(priority = 1, testName = "Verify display of Footer component")
    public void FB9577() {
        Assert.assertTrue(homePage.checkDisplayOfFooter(), "Footer did not display");
    }

    @Test(priority = 2, testName = "Verify display of Store Information")
    public void FB9578() {
        Assert.assertTrue(homePage.checkDisplayOfStoreInformation(), homePage.actualRS);
    }

    @Test(priority = 3, testName = "Verify display of Menu on Footer")
    public void FB9579() {
        Assert.assertTrue(homePage.checkDisplayOfMenuFooter(), homePage.actualRS);
    }

    @Test(priority = 4, testName = "Verify that Menu on Footer can be clicked")
    public void FB11219() {
        Assert.assertTrue(homePage.checkMenuFooterClickable(), homePage.actualRS);
    }

    @Test(priority = 5, testName = "Verify display Download app")
    public void FB9580() {
        Assert.assertTrue(homePage.checkDisplayOfDownloadInformation(), "Best selling did not display");
    }

    @Test(priority = 6, testName = "Verify clickable of Download app")
    public void FB11220() {
        Assert.assertTrue(homePage.checkDisplayOfDownloadInformationClickable(dataTest.APPLE_DL_URL, dataTest.GGPLAY_DL_URL), homePage.actualRS);
    }

    @Test(priority = 7, testName = "Verify display Business license")
    public void FB9582() {
        Assert.assertTrue(homePage.checkDisplayOfBusinessLicense(), homePage.actualRS);
    }

    @Test(priority = 8, testName = "Verify display of the social network")
    public void FB9581() {
        Assert.assertTrue(homePage.checkDisplayOfSocialMedia(), homePage.actualRS);
    }

    @Test(priority = 9, testName = "Verify clickable of Social media")
    public void FB11221() {
        Assert.assertTrue(homePage.checkSocialMediaClickable(), homePage.actualRS);
    }

    @Test(priority = 10, testName = "Verify display Copyright")
    public void FB9583() {
        Assert.assertTrue(homePage.checkDisplayOfCopyright(), homePage.actualRS);
    }

    @Test(priority = 11, testName = "Verify localization of Footer component")
    public void FB11222() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkLanguageOfFooter(), "Language is wrong\n" + homePage.actualRS);
    }
}
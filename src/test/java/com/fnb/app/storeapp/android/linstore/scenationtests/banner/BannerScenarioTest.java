package com.fnb.app.storeapp.android.linstore.scenationtests.banner;

import com.fnb.app.storeapp.android.linstore.pages.banner.BannerDataTest;
import com.fnb.app.storeapp.android.linstore.pages.banner.BannerPage;
import com.fnb.app.storeapp.android.linstore.pages.branch.BranchPage;
import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.setup.BaseTest;
import com.fnb.utils.helpers.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BannerScenarioTest extends BaseTest {
    BranchPage branchPage;
    LoginPageLinStore loginPageLinStore;
    BannerPage bannerPage;

    @BeforeClass
    public void navigateToPage() {
        branchPage = homePageLinStore.navigateToBranch();
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
        bannerPage = homePageLinStore.navigateToBannerPage();
    }

    @Test(priority = 0)
    public void clickBanner() {
        loginPageLinStore.splashScreen();
        bannerPage.clickBanner0();
        Log.info("Navigate To homepage");
    }

    @Test(priority = 1)
    public void clickBanner1() {
        bannerPage.clickBanner1();
        Log.info("Navigate to product-list");
    }

    @Test(priority = 2)
    public void clickBanner2() {
        Assert.assertTrue(bannerPage.checkDisplayBackBtn(), "Back button is not display");
        bannerPage.clickBanner2();
        Log.info("Navigate to blog page");
    }

    @Test(priority = 3)
    public void clickBanner3() {
        Assert.assertTrue(bannerPage.checkDisplayHomeIcon(), "Home Icon is not display");
        bannerPage.clickBanner3();
        Assert.assertEquals(bannerPage.getTextURL(), BannerDataTest.URL, "Testcase is fail");
        Log.info("Navigate to url");
        bannerPage.navigateBack();
    }

}

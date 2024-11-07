package com.fnb.app.storeapp.android.linstore.scenationtests.addressmanagement.userlocation;

import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.userlocation.UserLocationPage;
import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fnb.app.storeapp.android.linstore.pages.addressmanagement.userlocation.UserLocationDataTest.*;

public class UserLocationScenarioTest extends BaseTest {
    UserLocationPage userLocationPage;
    LoginPageLinStore loginPageLinStore;

    @BeforeClass
    public void navigateToPage() {
        userLocationPage = homePageLinStore.navigateToUserLocationPage();
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
    }

    @Test(priority = 0)
    public void verifyShowUserLocation() {
        loginPageLinStore.splashScreen();
        userLocationPage.clickDoNotAllow();
    }

    @Test(priority = 1)
    public void verifyCheckDisplayLanguageBtn() {
        Assert.assertTrue(userLocationPage.checkDisplayLanguageBtn(), "Testcase is fail");
    }

    @Test(priority = 2)
    public void verifyCheckDisplayNoLocationIcon() {
        Assert.assertTrue(userLocationPage.checkDisplayNoLocationIcon(), "Testcase is fail");
    }

    @Test(priority = 3)
    public void verifyCheckDisplayWhereAreYouNowText() {
        Assert.assertTrue(userLocationPage.checkDisplayWhereAreYouNowText(), "Testcase is fail");
    }

    @Test(priority = 4)
    public void verifyWhereAreYouNowText() {
        Assert.assertEquals(userLocationPage.getTextWhereAreYouNowText(), WHERE_ARE_YOU, "Testcase is fail");
    }

    @Test(priority = 5)
    public void verifyCheckDisplayTurnOnLocationText() {
        Assert.assertTrue(userLocationPage.checkDisplayTurnOnLocationText(), "Testcase is fail");
    }

    @Test(priority = 6)
    public void verifyTurnOnLocationText() {
        Assert.assertEquals(userLocationPage.getTextTurnOnLocationText(), TURN_ON_LOCATION, "Testcase is fail");
    }

    @Test(priority = 7)
    public void verifyCheckDisplayAllowLocationToFindBranchesText() {
        Assert.assertTrue(userLocationPage.checkDisplayAllowLocationToFindBranchesText(), "Testcase is fail");
    }

    @Test(priority = 8)
    public void verifyAllowLocationToFindBranchesText() {
        Assert.assertEquals(userLocationPage.getTextAllowLocationToFindBranchesText(), ALLOW_LOCATION, "Testcase is fail");
    }

    @Test(priority = 9)
    public void verifyCheckDisplayTryAgainBtn() {
        Assert.assertTrue(userLocationPage.checkDisplayTryAgainBtn(), "Testcase is fail");
        userLocationPage.clickTryAgainBtn();
    }

    @Test(priority = 10)
    public void verifyCheckDisplayInputUserAddressField() {
        Assert.assertTrue(userLocationPage.checkDisplayInputUserAddressField(), "Testcase is fail");
    }

    @Test(priority = 11)
    public void verifytPlaceHolderChooseAddress() {
        Assert.assertEquals(userLocationPage.getTextPlaceHolderChooseAddress(), CHOSE_ADDRESS, "Testcase is fail");
    }

    @Test(priority = 12)
    public void verifyCheckDisplaySearchIcon() {
        Assert.assertTrue(userLocationPage.checkDisplaySearchIcon(), "Testcase is fail");
    }

    @Test(priority = 13)
    public void verifyCheckDisplayPlaceHolderChooseAddress() {
        Assert.assertTrue(userLocationPage.checkDisplayPlaceHolderChooseAddress(), "Testcase is fail");
        userLocationPage.clickInputUserAddressField();
    }

    @Test(priority = 14)
    public void verifyCheckDisplayBackBtn() {
        Assert.assertTrue(userLocationPage.checkDisplayBackBtn(), "Testcase is fail");
    }

    @Test(priority = 15)
    public void verifyCheckDisplayTitle() {
        Assert.assertTrue(userLocationPage.checkDisplayTitle(), "Testcase is fail");
    }

    @Test(priority = 16)
    public void verifyCheckDisplayIconSearchMap() {
        Assert.assertTrue(userLocationPage.checkDisplayIconSearchMap(), "Testcase is fail");
    }

//    @Test(priority = 17)
//    public void verifyCheckDisplayGGIcon() {
//        Assert.assertTrue(userLocationPage.checkDisplayGGIcon(), "Testcase is fail");
//    }

    @Test(priority = 18)
    public void verifyCheckDisplaySearchResultMatchAddressText() {
        userLocationPage.inputSearchResultMatchAddressText(USER_ADDRESS);
        userLocationPage.clickSearchResultMatchAddressText();
        Assert.assertTrue(userLocationPage.checkDisplaySearchResultMatchAddressText(), "Testcase is fail");
    }

    @Test(priority = 19)
    public void verifyCheckDisplaySearchResultMatchAddress0() {
        Assert.assertTrue(userLocationPage.checkDisplaySearchResultMatchAddress0(), "Testcase is fail");
    }

    @Test(priority = 20)
    public void verifySearchResultMatchAddress0() {
        Assert.assertEquals(userLocationPage.getTextSearchResultMatchAddress0(), SEARCH_1, "Testcase is fail");
    }

    @Test(priority = 21)
    public void verifyCheckDisplaySearchResultMatchAddress1() {
        Assert.assertTrue(userLocationPage.checkDisplaySearchResultMatchAddress1(), "Testcase is fail");
    }

    @Test(priority = 22)
    public void verifySearchResultMatchAddress1() {
        Assert.assertEquals(userLocationPage.getTextSearchResultMatchAddress1(), SEARCH_2, "Testcase is fail");
        userLocationPage.clickSearchResultMatchAddress0();
    }
}

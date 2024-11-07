package com.fnb.web.store.theme1.scenario_test.home.header;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.login.DataTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HeaderScenarios extends BaseTest {
    private HomePage homePage;
    private HomeDataTest homeDataTest;
    private DataTest loginDataTest;
    Helper helper;
    SoftAssert softAssert;

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = storePage().navigateToHomePage();
        helper = storePage().helper;
        softAssert = storePage().softAssert;
    }

    @Test(priority = 1, testName = "Verify the display of header")
    public void FB9333() {
        helper.waitForJStoLoad();
        Assert.assertTrue(homePage.checkDisplayOfHeader(), "Verify the display of header");
    }

    @Test(priority = 2, testName = "Verify the height of header")
    public void FB9335() {
        Assert.assertTrue(homePage.checkHeightHeader(homeDataTest.HEADER_HEIGHT), "The height is incorrectly! Actual: " + homePage.actualRS);
    }

    @Test(priority = 3, testName = "Verify display of store logo")
    public void FB9338() {
        Assert.assertTrue(homePage.checkDisplayOfStoreLogo(), "Store logo did not display");
    }

    @Test(priority = 3, testName = "Verify display of menu")
    public void FB93391() {
        Assert.assertTrue(homePage.checkDisplayOfMenu(), "Menu did not display");
    }

    @Test(priority = 3, testName = "Verify display of menu - content")
    public void FB93392() {
        Assert.assertTrue(homePage.checkHeaderMenuItem(), homePage.actualRS);
    }

    @Test(priority = 4, testName = "Verify the size of the gap between a menu's rows")
    public void FB9340() {
        Assert.assertTrue(homePage.checkMenuGap(homeDataTest.MENU_GAP), "the gap between a menu's rows display wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 4, testName = "Verify display of 3-dot if menu has more than 5 items")
    public void FB9342() {
        Assert.assertTrue(homePage.checkDisplayOfDotsMenu(), "Submenu of 3-dot did not display");
    }

    @Test(priority = 4, testName = "Verify when user hovers on 3 dots")
    public void FB9344() {
        Assert.assertTrue(homePage.checkDotsMenuHover(), "Can not hover on 3-dot");
    }

    @Test(priority = 4, testName = "Verify when user clicks on Home")
    public void FB9347() {
        Assert.assertTrue(homePage.clickHomeMainMenu(homeDataTest.URL), "Can not click Home");
    }

    @Test(priority = 5, testName = "Verify the display of account icon on header")
    public void FB9349() throws InterruptedException {
        storePage().navigateToHomePage();
        Thread.sleep(500);
        Assert.assertTrue(homePage.checkDisplayOfAccountIcon(), "Account icon did not display");
    }

    @Test(priority = 6, testName = "Verify display of Cart icon")
    public void FB9352() {
        Assert.assertTrue(homePage.checkDisplayOfCartIcon(), "Cart icon did not display");
    }

    @Test(priority = 7, testName = "Verify display of header-rectangle")
    public void FB9372() {
        Assert.assertTrue(homePage.checkDisplayOfHeaderRectangle(), "Header Rectangle did not display");
    }

    @Test(priority = 8, testName = "Verify display of language")
    public void FB9373() {
        softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.checkDisplayOfLanguageFlagIcon(), "Flag language did not display");
        softAssert.assertTrue(homePage.checkDisplayOfLanguageLabel(), "Language label did not display");
        softAssert.assertTrue(homePage.checkDisplayOfLanguageArrow(), "Language arrow did not display");
        softAssert.assertAll();
    }

    @Test(priority = 8, testName = "Verify display of default language")
    public void FB9375() {
        Assert.assertTrue(homePage.checkDefaultLanguage(homeDataTest.DEFAULT_LANGUAGE), "Default language is incorrect. Actual: " + homePage.actualRS);
    }

    @Test(priority = 8, testName = "Verify display of language option when user clicks on language")
    public void FB9374() {
        homePage.clickLanguage();
        softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.checkDisplayLanguageDialog(), "Language dialog did not display");
        softAssert.assertTrue(homePage.checkDisplayOfNumberOptions(homeDataTest.NUMBER_LANGUAGE), "Default language is incorrect. Actual: " + homePage.actualRS);
        softAssert.assertTrue(homePage.checkDisplayOfVietnamOption(), "Vietname did not display");
        softAssert.assertTrue(homePage.checkDisplayOfEnglishOption(), "English did not display");
        softAssert.assertAll();
    }

    @Test(priority = 9, testName = "Verify display of cart dialog when cart is empty")
    public void FB9377() throws InterruptedException {
        homePage.clickLanguage();
        homePage.clickCartIcon();
        Thread.sleep(1500);
        softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.checkDisplayEmptyCartDialog(), "Empty cart dialog did not display");
        softAssert.assertTrue(homePage.checkDisplayEmptyPackageImage(), "Empty cart image did not display");
        softAssert.assertTrue(homePage.checkDisplayEmptyLabel(), "Empty cart message did not display");
        softAssert.assertAll();
    }

    @Test(priority = 10, testName = "Verify the display of account dialog when user does not login")
    public void FB9325() {
        homePage.clickCartIcon();
        homePage.clickAccountIcon();
        softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.checkDisplayOfLoginRegisterWithoutLogin(), "Login/Register did not display");
        softAssert.assertTrue(homePage.checkDisplayOfLoginRegisterIcon(), "Login/Register icon did not display");
        softAssert.assertAll();
    }

    @Test(priority = 10, testName = "Verify display of account dialog when changed language without login")
    public void FB93761() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkLanguageOfAddUserLocation(false, false), homePage.actualRS);
    }

    @Test(priority = 11, testName = "Verify the display of account dialog after user logged in")
    public void FB9326() {
        storePage().navigateToHomePage();
        homePage.loginSuccessfully(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD);
        softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.checkDisplayOfAvatar(), "After login, Avatar did not display");
        softAssert.assertTrue(homePage.checkDisplayOfName(), "After login, Name did not display");
        softAssert.assertTrue(homePage.checkDisplayOfEditProfile(), "After login, Edit profile CTA did not display");
        softAssert.assertTrue(homePage.checkDisplayOfOrderIcon(), "After login, Order icon did not display");
        softAssert.assertTrue(homePage.checkDisplayOfOrder(), "After login, OrderCTA did not display");
        softAssert.assertTrue(homePage.checkDisplayOfAddressListIcon(), "After login, Address list icon did not display");
        softAssert.assertTrue(homePage.checkDisplayOfAddressList(), "After login, Address list CTA did not display");
        softAssert.assertTrue(homePage.checkDisplayOfLogoutIcon(), "After login, Logout icon did not display");
        softAssert.assertTrue(homePage.checkDisplayOfLogout(), "After login, Logout CTA did not display");
        softAssert.assertAll();
    }

    @Test(priority = 11, testName = "Verify display of account dialog when changed language after login successfully")
    public void FB93762() {
        storePage().navigateToHomePage();
        Assert.assertTrue(homePage.checkLanguageOfAddUserLocation(true, false), homePage.actualRS);
    }

    @Test(priority = 12, testName = "Verify when user clicks on Edit profile")
    public void FB9328() {
        Assert.assertTrue(homePage.clickEditAccount(), "Page did not navigate to My profile page");
    }

    @Test(priority = 12, testName = "Verify when user clicks on Order")
    public void FB9329() {
        Assert.assertTrue(homePage.clickOrder(), "Page did not navigate to My order page");
    }

    @Test(priority = 12, testName = "Verify when user clicks on Address list")
    public void FB9330() {
        Assert.assertTrue(homePage.clickAddressList(), "Page did not navigate to My address list page");
    }

    @Test(priority = 12, testName = "Verify when user clicks on Logout")
    public void FB9331() {
        Assert.assertTrue(homePage.checkAfterLogout(), "Page did not navigate to Home page");
    }

    @Test(priority = 13, testName = "Verify display of cart dialog when cart has product(s)")
    public void FB93710() throws InterruptedException {
        helper.refreshPage();
        Assert.assertTrue(homePage.addToCartFromBestsellingProduct());
    }

    @Test(priority = 13, testName = "Verify display of cart dialog when cart has product(s)")
    public void FB93711() {
        storePage().navigateToHomePage();
        helper.pressPageUpAction();
        homePage.checkDisplayOfBanner();
        Assert.assertTrue(homePage.checkDisplayOfQuantityCart(), "Cart quantity did not display");
    }

    @Test(priority = 13, testName = "Verify display of cart dialog when cart has product(s)")
    public void FB93712() {
        homePage.clickCartIcon();
        Assert.assertTrue(homePage.checkCartCheckIcon(), "Check icon did not display");
    }

    @Test(priority = 13, testName = "Verify display of cart dialog when cart has product(s)")
    public void FB93713() {
        Assert.assertTrue(homePage.checkYourCartLabel(), "Your cart label did not display");
    }

    @Test(priority = 13, testName = "Verify display of cart dialog when cart has product(s)")
    public void FB93714() {
        Assert.assertTrue(homePage.checkProductCartList(), "Product list did not display");
    }

    @Test(priority = 13, testName = "Verify display of cart dialog when cart has product(s)")
    public void FB93715() {
        Assert.assertTrue(homePage.checkCheckoutButton(), "Checkout button did not display");
    }

    @Test(priority = 14, testName = "Verify language of cart with product when changed language")
    public void FB93763() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkLanguageOfAddUserLocation(false, true), homePage.actualRS);
    }
}

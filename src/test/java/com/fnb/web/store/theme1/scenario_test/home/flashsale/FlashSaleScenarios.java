package com.fnb.web.store.theme1.scenario_test.home.flashsale;

import com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader.*;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.store.theme1.pages.addUserLocation.AddUserLocationDataTest;
import com.fnb.web.store.theme1.pages.addUserLocation.AddUserLocationPage;
import com.fnb.web.store.theme1.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme1.pages.flashSale.FlashSaleDataTest;
import com.fnb.web.store.theme1.pages.flashSale.FlashSalePage;
import com.fnb.web.store.theme1.pages.home.PromotionDataTest;
import com.fnb.web.store.theme1.pages.login.DataTest;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.myOrder.MyOrderPage;
import com.fnb.web.store.theme1.pages.product_details.EditOrderPage;
import com.fnb.web.store.theme1.pages.product_details.ProductDetailsPage;
import com.fnb.web.store.theme1.pages.product_list.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static com.fnb.web.setup.Setup.*;

public class FlashSaleScenarios extends BaseTest {
    //Check on home page
    //Testcase:https://mediastep1-my.sharepoint.com/:x:/g/personal/ngan_cao_thi_kim_gosell_vn/EQtsv7XuR7VOuGBoZpHOKRkBCdTct0yNUZ74Q5qIB96Bsg?e=CUOyYp&nav=MTVfe0QxN0U3NDg1LTM0RjUtNDk0MC1BRDgwLTg0Rjg1NjZBRjZERn0
    private Helper helper;
    private SoftAssert softAssert;
    private HomePage homePage;
    private FlashSalePage flashSalePage;
    private AddUserLocationPage addUserLocationPage;
    private ProductDetailsPage productDetailsPage;
    private CheckoutPage checkoutPage;
    private EditOrderPage editOrder;
    private ProductListPage productListPage;
    private DataTest loginDataTest;
    private AddUserLocationDataTest addUserLocationDataTest;
    private FlashSaleDataTest flashSaleDataTest;
    private PromotionDataTest promotionDataTest;
    private MyOrderPage myOrderPage;
    private int addComingStartMinute = 20;
    private int addComingEndMinute = 20;
    private int addEndAfterStartMinute = 1;
    private int addEndAfterEndMinute = 15;
    private int addEndedStartMinute = 1;
    private int addEndedEndMinute = 1;
    private String comingFlashSaleName = "";
    private String endAfterFlashSaleName = "";
    private String oldTime = "";
    private String newTime = "";
    private String productName = "";
    private String checkoutURL = "";
    private String cartHandle = "";
    private int quantity = 0;
    private int maximumLimit = 0;
    private int cartQuantity = 0;
    private int remainingQuantity = 0;
    private int minimumPurchaseOrder = 0;
    private int price = 0;
    private int flashSalePrice = 0;
    private List<String> productFlashSale = new ArrayList<>();
    private String homeURL;
    private String orderURL = "";
    private String orderHandle = "";
    private String currentWindow = "";
    private String checkoutHandle = "";
    private String productListHandle = "";
    private String currentLanguage;
    private Boolean isFlashsale = false;
    private String productDetailURL = "";
    private String sizeName = "";
    private FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
    private List<Integer> toppingTotalPrice = new ArrayList<>();
    private List<String> productNotFlashSale = new ArrayList<>();
    private String createFlashSaleObj = "createFlashSale";
    private String createFlashSaleSpecialBranchObj = "createFlashSaleSpecialBranch";
    private String createFlashSaleMinimumPurchaseObj = "createFlashSaleMinimumPurchase";

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = storePage().navigateToHomePage();
        checkoutPage = new CheckoutPage(getDriver());
        myOrderPage = new MyOrderPage(getDriver());
        productDetailsPage = new ProductDetailsPage(getDriver());
        editOrder = new EditOrderPage(getDriver());
        helper = storePage().helper;
        addUserLocationPage = new AddUserLocationPage(getDriver());
        flashSalePage = new FlashSalePage(getDriver());
        productListPage = new ProductListPage(getDriver());
    }

    //create and check coming flash sale
    @Test(priority = 1, testName = "Verify display of Coming flash sale", description = "Check tab, display and css of main session")
    public void FB9455() {
        softAssert = new SoftAssert();
        softAssert.assertTrue(flashSalePage.checkTabFlashSaleAfterCreatedFlashSale(true, addComingStartMinute, addComingEndMinute, 2, false), "------ TC1:\nCheck Coming tab\n" + flashSalePage.actualRS);
        storePage().navigateToHomePage();
        softAssert.assertTrue(flashSalePage.checkDisplayOfMainSession(flashSalePage.flashSaleName, false, 2), "------ TC2:\nCheck main session of Coming tab\n" + flashSalePage.actualRS);
        softAssert.assertTrue(flashSalePage.checkCSSOfMainSession(), "------ TC3:\nCheck CSS Main session of Coming tab\n" + flashSalePage.actualRS);
        softAssert.assertAll();
    }

    //change and check end after
    @Test(priority = 2, testName = "Update flash sale time and check automatically change to EndAfter", description = "Update flash sale time and check automatically change to EndAfter")
    public void FB9456() {
        flashSalePage.updateTimeFlashSale(flashSalePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.waitTimeToChangeStatus(addEndAfterStartMinute), flashSalePage.actualRS);
    }

    @Test(priority = 3, testName = "Verify display of End after flash sale main session", description = "Check tab, display and css of main session")
    public void FB94561() {
        softAssert = new SoftAssert();
        softAssert.assertTrue(flashSalePage.checkTabFlashSaleAfterCreatedFlashSale(false, addEndAfterStartMinute, addEndAfterEndMinute, 1, false), "------ TC1:\nCheck End after tab\n" + flashSalePage.actualRS);
        helper.refreshPage();
        softAssert.assertTrue(flashSalePage.checkDisplayOfMainSession(flashSalePage.flashSaleName, true, 1), "------ TC2:\nCheck main session of End after tab\n" + flashSalePage.actualRS);
        helper.refreshPage();
        softAssert.assertTrue(flashSalePage.checkCSSOfMainSession(), "------ TC3:\nCheck CSS Main session of End after tab\n" + flashSalePage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 4, testName = "Verify display of Ended flash sale", description = "Check tab, display and css of main session")
    public void FB9457() {
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        flashSalePage.createFlashSale(addEndedStartMinute, addEndedEndMinute, false);
        helper.refreshPage();
        flashSalePage.checkDisplayOfFlashSaleComponent();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute + addEndedEndMinute);
        softAssert = new SoftAssert();
        softAssert.assertTrue(flashSalePage.checkTabFlashSaleAfterCreatedFlashSale(false, addEndedStartMinute, addEndedEndMinute, 0, false), "------ TC1:\nCheck Ended tab\n" + flashSalePage.actualRS);
        helper.refreshPage();
        softAssert.assertTrue(flashSalePage.checkDisplayOfMainSession(flashSalePage.flashSaleName, false, 0), "------ TC2:\nCheck main session of Ended tab\n" + flashSalePage.actualRS);
        helper.refreshPage();
        softAssert.assertTrue(flashSalePage.checkCSSOfMainSession(), "------ TC3:\nCheck CSS of Ended tab\n" + flashSalePage.actualRS);
        softAssert.assertAll();
    }

    //Create Coming flash sale
    @Test(priority = 5, testName = "Check tab active between Coming and Ended flash sale", description = "Check tab active between Coming and Ended flash sale")
    public void FB9484() {
        flashSalePage.createFlashSale(addComingStartMinute, addComingEndMinute, false);
        comingFlashSaleName = flashSalePage.flashSaleName;
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.checkActiveStatusTab(true, false, true), flashSalePage.actualRS);
    }

    //change and check end after
    @Test(priority = 6, testName = "Update flash sale time and check automatically change to EndAfter", description = "Update flash sale time and check automatically change to EndAfter")
    public void FB12() {
        flashSalePage.updateTimeFlashSale(flashSalePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
        endAfterFlashSaleName = flashSalePage.flashSaleName;
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.waitTimeToChangeStatus(addEndAfterStartMinute), flashSalePage.actualRS);
    }

    @Test(priority = 7, testName = "Check tab active between EndAfter and Ended flash sale", description = "Check tab active between EndAfter and Ended flash sale")
    public void FB9482() {
        Assert.assertTrue(flashSalePage.checkActiveStatusTab(true, true, false), flashSalePage.actualRS);
    }

    //create coming flash sale
    @Test(priority = 8, testName = "Check tab active between Coming, EndAfter and Ended flash sale", description = "Check tab active between Coming, EndAfter and Ended flash sale")
    public void FB9516() {
        flashSalePage.createFlashSale(addEndAfterStartMinute + addEndAfterEndMinute, addComingEndMinute, false);
        comingFlashSaleName = flashSalePage.flashSaleName;
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.checkActiveStatusTab(true, true, true), flashSalePage.actualRS);
    }

    @Test(priority = 9, testName = "Clear all flash sale", description = "Clear all flash sale")
    public void FB15() {
        flashSalePage.stopFlashSale(endAfterFlashSaleName);
        flashSalePage.deleteFlashSale(comingFlashSaleName);
    }

    //Coming flash sale
    @Test(priority = 10, testName = "Check priority display of Coming flash sale: coming 2 earlier than coming 1", description = "Check tab active between Coming and Ended flash sale")
    public void FB9518() {
        int addFirstComingMinutes = addComingStartMinute + addComingEndMinute;
        flashSalePage.createFlashSale(addFirstComingMinutes, addComingEndMinute, false); //adding 10 minutes to create flash sale will hide
        comingFlashSaleName = flashSalePage.flashSaleName;
        helper.refreshPage();
        oldTime = flashSalePage.getFlashSaleTabTime();
        flashSalePage.createFlashSale(addComingStartMinute, addComingStartMinute - 1, false); //make sure this flash sale will be created before the previous flash sale
        helper.refreshPage();
        newTime = flashSalePage.getFlashSaleTabTime();
        Assert.assertNotEquals(oldTime, newTime, "Old time: " + oldTime + " Expected: " + newTime);
    }

    @Test(priority = 11, testName = "Check priority display of Coming flash sale: coming 2 is later than coming 1", description = "Check tab active between Coming and Ended flash sale")
    public void FB9519() {
        flashSalePage.deleteFlashSale(comingFlashSaleName);
        comingFlashSaleName = flashSalePage.flashSaleName;
        oldTime = newTime;
        flashSalePage.createFlashSale(addComingStartMinute + addComingEndMinute, addComingEndMinute, false);
        helper.refreshPage();
        newTime = flashSalePage.getFlashSaleTabTime();
        Assert.assertEquals(oldTime, newTime, "Old time: " + oldTime + " Expected: " + newTime);
    }

    /**
     * Ended flash sale
     */
    @Test(priority = 12, testName = "Check priority display of Ended flash sale: Ended 1 earlier than ended 2", description = "Check priority display of Ended flash sale")
    public void FB9521() {
        //clear all flash sale
        flashSalePage.deleteFlashSale(comingFlashSaleName);
        flashSalePage.deleteFlashSale(flashSalePage.flashSaleName);
        flashSalePage.createFlashSale(addEndedStartMinute, addEndedEndMinute, false); //adding 10 minutes to create flash sale will hide
        helper.refreshPage();
        flashSalePage.checkDisplayOfFlashSaleComponent();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute + addEndedEndMinute);
        helper.refreshPage();
        oldTime = flashSalePage.getFlashSaleTabTime();
        flashSalePage.createFlashSale(addEndedStartMinute, addEndedEndMinute, false); //adding 10 minutes to create flash sale will hide
        comingFlashSaleName = flashSalePage.flashSaleName;
        helper.refreshPage();
        flashSalePage.checkDisplayOfFlashSaleComponent();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute + addEndedEndMinute);
        helper.refreshPage();
        newTime = flashSalePage.getFlashSaleTabTime();
        Assert.assertNotEquals(oldTime, newTime, "Old time: " + oldTime + " Expected: " + newTime);
    }

    //Create Coming flash sale on tomorrow
    @Test(priority = 13, testName = "Check display of Coming flash sale with next day", description = "Check tab active between Coming and Ended flash sale")
    public void FB9522() {
        int addFirstComingMinutes = 1440;
        flashSalePage.createFlashSale(addFirstComingMinutes, addComingEndMinute, false);
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.checkActiveStatusTab(true, false, false), flashSalePage.actualRS);
    }

    @Test(priority = 14, testName = "Check product has not belongs to selected branch", description = "Check tab active between Coming and Ended flash sale")
    public void FB95241() {
        flashSalePage.deleteFlashSale(flashSalePage.flashSaleName);
        flashSalePage.createFlashSale(addComingStartMinute, addComingEndMinute, true);
        Assert.assertTrue(flashSalePage.checkDisplayOfMainSessionAfterChangeBranch(flashSalePage.flashSaleName), flashSalePage.actualRS);
    }

    @Test(priority = 15, testName = "Check product belongs to selected branch", description = "Check tab active between Coming and Ended flash sale")
    public void FB95243() {
        storePage().navigateToHomePage();
        addUserLocationPage.selectBranchWithName(flashSalePage.getBranchNameMissingProductByEnv()); //change branch
        Assert.assertFalse(flashSalePage.checkDisplayOfMainSessionAfterChangeBranch(flashSalePage.flashSaleName), flashSalePage.actualRS);
        helper.refreshPage();
        addUserLocationPage.selectBranchWithName(flashSalePage.getBranchNameByEnv());
    }

    @Test(priority = 16, testName = "Check display of flash sale if it belongs to the selected branch(es)", description = "Check tab active between Coming and Ended flash sale")
    public void FB11929() {
        flashSalePage.deleteFlashSale(flashSalePage.flashSaleName);
        flashSalePage.createFlashSaleSpecialBranch(addComingStartMinute, addComingEndMinute);
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.checkActiveStatusTab(false, false, true), flashSalePage.actualRS);
    }

    @Test(priority = 17, testName = "Check display of flash sale if it did not belongs to the selected branch(es)", description = "Check display of flash sale if it did not belongs to the selected branch(es)")
    public void FB11930() {
        helper.refreshPage();
        addUserLocationPage.selectBranchWithName(flashSalePage.getBranchNameMissingProductByEnv());
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.checkActiveStatusTab(true, false, false), flashSalePage.actualRS);
    }

    //check localization Ended flash sale
    @Test(priority = 18, testName = "Check localization of Ended flash sale", description = "Clear all Coming flash sale")
    public void FB119311() {
        flashSalePage.deleteFlashSale(flashSalePage.flashSaleName);
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.checkLanguageOfFlashSale(true, false, false), flashSalePage.actualRS);
    }

    //change and check Coming
    @Test(priority = 19, testName = "Check localization of Coming", description = "Check localization of EndAfter")
    public void FB119312() {
        flashSalePage.createFlashSale(addComingStartMinute, addComingEndMinute, false);
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.checkLanguageOfFlashSale(true, false, true), flashSalePage.actualRS);
    }

    //change and check end after
    @Test(priority = 20, testName = "Check localization of EndAfter", description = "FB11931 - Check localization of EndAfter")
    public void FB119313() {
        flashSalePage.updateTimeFlashSale(flashSalePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.checkLanguageOfFlashSale(true, true, false), flashSalePage.actualRS);
    }

    @Test(priority = 21, testName = "Create coming flash sale, end after flash saler to verify clickable of Flash sale tab", description = "Create coming flash sale, end after flash sale")
    public void FB371() {
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        flashSalePage.createFlashSale(addComingStartMinute, addComingEndMinute, false);
        comingFlashSaleName = flashSalePage.flashSaleName;
        flashSalePage.createFlashSale(addEndAfterStartMinute, addEndAfterEndMinute, false);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.checkClickableOfFlashSaleTab(), flashSalePage.actualRS);
    }

    @Test(priority = 22, testName = "Verify display of flash sale product on cart when flash sale is in progress", description = "Verify display of flash sale product on cart when flash sale is in progress")
    public void FB39() {
        flashSalePage.deleteFlashSale(comingFlashSaleName);
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        flashSalePage.createFlashSaleNotFullVariations(addEndAfterStartMinute, 180);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        productName = flashSalePage.clickAddToCartFromFlashSale(flashSalePage.flashSaleName);
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.checkCartWhenFlashSale(productName, true), flashSalePage.actualRS);
    }

    @Test(priority = 23, testName = "Verify display of flash sale product on best selling product when flash sale is in progress", description = "Verify display of flash sale product on best selling product when flash sale is in progress")
    public void FB40() {
        helper.refreshPage();
        try {
            Assert.assertTrue(flashSalePage.checkBestSellingAllWhenFlashSale(flashSalePage.flashSaleName), flashSalePage.actualRS);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            Assert.assertTrue(flashSalePage.checkBestSellingAllWhenFlashSale(flashSalePage.flashSaleName), flashSalePage.actualRS);
        }
    }

    @Test(priority = 24, testName = "Checkout then verify flash sale tag when flash sale is in progress", description = "Checkout then verify flash sale tag when flash sale is in progress")
    public void FB41() {
        helper.refreshPage();
        softAssert = new SoftAssert();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, true, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        softAssert.assertTrue(checkoutPage.checkDisplayOfFlashSaleBorder(), "Flash sale border did not display");
        softAssert.assertTrue(checkoutPage.checkDisplayOfFlashSaleTag(), "Flash sale tag did not display");
        softAssert.assertTrue(checkoutPage.checkDisplayOfFlashSaleLabel(), "Flash sale promotion label did not display");
        softAssert.assertAll();
    }

    /**
     * stop flash sale by admin
     **/
    @Test(priority = 25, testName = "Check flash sale product on cart before reloaded", description = "Check flash sale product on cart before reloaded")
    public void FB42() {
        checkoutURL = helper.getCurrentURL();
        storePage().navigateToHomePage();
        currentWindow = helper.getCurrentWindow();
        helper.openNewTab(checkoutURL);
        helper.waitForURLContains(checkoutURL);
        checkoutHandle = helper.getCurrentWindow();
        helper.openNewTab(baseUrl);
        cartHandle = helper.getCurrentWindow();
        homePage.clickCartIcon();
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        Assert.assertTrue(flashSalePage.checkCartWhenFlashSale(productName, false), flashSalePage.actualRS);
    }

    @Test(priority = 26, testName = "Click out and click cart again to check flash sale product on cart", description = "Click out and click cart again to check flash sale product on cart")
    public void FB45() {
        helper.refreshPage();
        Assert.assertFalse(flashSalePage.checkCartWhenFlashSale(productName, true), flashSalePage.actualRS);
    }

    @Test(priority = 27, testName = "Switch to checkout tab and check checkout page before reloaded", description = "Switch to checkout tab and check checkout page before reloaded")
    public void FB46() {
        helper.switchToWindowHandle(checkoutHandle);
        softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.checkDisplayOfFlashSaleBorder(), "Flash sale border did not display");
        softAssert.assertTrue(checkoutPage.checkDisplayOfFlashSaleTag(), "Flash sale tag did not display");
        softAssert.assertTrue(checkoutPage.checkDisplayOfFlashSaleLabel(), "Flash sale promotion label did not display");
        softAssert.assertAll();
    }

    @Test(priority = 28, testName = "Check checkout after refresh", description = "Check checkout after refresh")
    public void FB47() {
        softAssert = new SoftAssert();
        helper.refreshPage();
        softAssert.assertFalse(checkoutPage.checkDisplayOfFlashSaleBorder(), "Flash sale border did not display");
        softAssert.assertFalse(checkoutPage.checkDisplayOfFlashSaleTag(), "Flash sale tag did not display");
        softAssert.assertFalse(checkoutPage.checkDisplayOfFlashSaleLabel(), "Flash sale promotion label did not display");
        softAssert.assertAll();
    }

    @Test(priority = 29, testName = "Check flash sale campaign before reload", description = "Check flash sale campaign before reload")
    public void FB48() {
        helper.switchToWindowHandle(currentWindow);
        Assert.assertTrue(flashSalePage.checkActiveStatusTab(true, true, false), "End after flash sale did not display");
    }

    @Test(priority = 30, testName = "Check flash sale campaign after reload", description = "Check flash sale campaign after reload")
    public void FB50() {
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.checkActiveStatusTab(true, false, false), flashSalePage.actualRS);
    }

    @Test(priority = 31, testName = "Close all tab except main tab", description = "Close all tab except main tab")
    public void closeTab() {
        helper.closeAllOpenTabExceptMainTab(currentWindow);
    }

    //TODO Action can't run well with best selling product -> should manual this case
//    @Test(priority = 32, testName = "Create coming flash sale then check best selling", description = "")
//    public void FB52() {
//        maximumLimit = 2;
//        quantity = 10;
//        cartQuantity = 5;
//        storePage().navigateToHomePage();
//        flashSalePage.createFlashSaleWithVariation(addComingStartMinute, addComingEndMinute, "M", maximumLimit, quantity);
//        helper.refreshPage();
//        Assert.assertFalse(flashSalePage.checkBestSellingAllWhenFlashSale(flashSalePage.flashSaleName), flashSalePage.actualRS);
//    }
//
//    @Test(priority = 33, testName = "Before reloading", description = "")
//    public void FB54() {
//        currentWindow = helper.getCurrentWindow();
//        String homeUrl = helper.getCurrentURL();
//        flashSalePage.updateTimeFlashSale(flashSalePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
//        helper.refreshPage();
//        helper.openNewTab(homeUrl);
//        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
//        helper.refreshPage();
//        helper.switchToWindowHandle(currentWindow);
//        Assert.assertFalse(flashSalePage.checkBestSellingAllWhenFlashSale(flashSalePage.flashSaleName), flashSalePage.actualRS); //Save time to check
//    }
//
//    @Test(priority = 34, testName = "After reloading", description = "")
//    public void FB55() {
//        helper.refreshPage();
//        Assert.assertTrue(flashSalePage.checkBestSellingAllWhenFlashSale(flashSalePage.flashSaleName), flashSalePage.actualRS);
//    }
//
//    @Test(priority = 35, testName = "Ended flash sale - Before reloading", description = "")
//    public void FB56() {
//        String homeUrl = helper.getCurrentURL();
//        flashSalePage.updateTimeFlashSale(flashSalePage.flashSaleName, addEndedStartMinute, addEndedEndMinute);
//        helper.refreshPage();
//        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
//        helper.refreshPage();
//        helper.openNewTab(homeUrl);
//        flashSalePage.waitTimeChangeStatus(addEndedEndMinute);
//        Assert.assertTrue(flashSalePage.checkBestSellingAllWhenFlashSale(flashSalePage.flashSaleName), flashSalePage.actualRS); //Save time to check because action class doesn't stable to handle swiper-slider
//    }
//
//    @Test(priority = 36, testName = "Check flash sale product when flash sale ended after reloading", description = "")
//    public void FB57() {
//        helper.refreshPage();
//        Assert.assertFalse(flashSalePage.checkBestSellingAllWhenFlashSale(flashSalePage.flashSaleName), flashSalePage.actualRS);
//    }
//
//    @Test(priority = 37, testName = "Stop created flash sale")
//    public void FB58() {
//        helper.closeAllOpenTabExceptMainTab(currentWindow);
//        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
//    }

    /**
     * Check remaining flash sale
     * Create end after flash sale to check remaining
     */
    @Test(priority = 40, testName = "Check remaining quantity on Flash sale campaign when remaining > limit > cart", description = "Limit flashsale quantity 2\nRemaining quantity 10\nCart quantity 1")
    public void FB1() {
        quantity = 10;
        maximumLimit = 2;
        remainingQuantity = quantity;
        cartQuantity = 1;
        storePage().navigateToHomePage();
        flashSalePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        try {
            flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        }
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, true, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, true);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 41, testName = "Check remaining when user cancel order when remaining > limit > cart", description = "Cancel order FB12364")
    public void FB12365() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
        remainingQuantity = quantity;
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), flashSalePage.actualRS);
    }

    @Test(priority = 42, testName = "Check remaining when user reorder when remaining > limit > cart")
    public void FB123661() {
        try {
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        }
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 43, testName = "Cancel Order FB12366")
    public void FB123662() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
    }

    @Test(priority = 44, testName = "Check remaining quantity on Flash sale campaign when remaining  > cart >= limit", description = "Limit flashsale quantity 2\nRemaining quantity 10\nCart quantity 3")
    public void FB12367() {
        quantity = 10;
        maximumLimit = 2;
        remainingQuantity = quantity;
        cartQuantity = 3;
        storePage().navigateToHomePage();
        flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        flashSalePage.checkoutWhenFlashSaleWithoutLogin(false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 45, testName = "Check remaining when user cancel order when remaining  > cart >= limit", description = "Cancel order FB12367")
    public void FB12368() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
        remainingQuantity = quantity;
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), flashSalePage.actualRS);
    }

    @Test(priority = 46, testName = "Check remaining when user reorder when remaining  > cart >= limit", description = "Cancel order FB12368")
    public void FB123691() {
        try {
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        }
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 46, testName = "Cancel order FB12369")
    public void FB123692() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
    }

    @Test(priority = 47, testName = "Check remaining quantity on Flash sale campaign when limit > remaining > cart", description = "\"Limit flashsale quantity 15\n" + "Remaining quantity 10\n" + "Cart quantity 6\"")
    public void FB12370() {
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        maximumLimit = 15;
        quantity = 10;
        remainingQuantity = quantity;
        cartQuantity = 6;
        storePage().navigateToHomePage();
        flashSalePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        storePage().navigateToHomePage();
        try {
            flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        }
        flashSalePage.checkoutWhenFlashSaleWithoutLogin(false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 48, testName = "Check remaining when user cancel order when limit > remaining > cart", description = "Cancel order FB12370")
    public void FB12371() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
        remainingQuantity = quantity;
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), flashSalePage.actualRS);
    }

    @Test(priority = 49, testName = "Check remaining when user reorder when limit > remaining > cart", description = "Cancel order FB12371")
    public void FB123721() {
        try {
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        }
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 49, testName = "Cancel order FB12372")
    public void FB123722() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
    }

    @Test(priority = 50, testName = "Check remaining quantity on Flash sale campaign when cart > limit > remaining", description = "\"Limit flashsale quantity 15\n" + "Remaining quantity 10\n" + "Cart quantity 20\"")
    public void FB12373() {
        maximumLimit = 15;
        quantity = 10;
        remainingQuantity = quantity;
        cartQuantity = 20;
        storePage().navigateToHomePage();
        flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        flashSalePage.checkoutWhenFlashSaleWithoutLogin(false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 51, testName = "Check remaining when user cancel order when cart > limit > remaining ", description = "Cancel order FB12373")
    public void FB12374() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
        remainingQuantity = quantity;
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), flashSalePage.actualRS);
    }

    @Test(priority = 52, testName = "Check remaining when user reorder when cart > limit > remaining ", description = "Cancel order FB12374")
    public void FB123751() {
        try {
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        }
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 52, testName = "Cancel order FB12375")
    public void FB123752() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
    }

    @Test(priority = 53, testName = "Check remaining quantity on Flash sale campaign when limit >= cart >= remaining", description = "\"Limit flashsale quantity 15\n" + "Remaining quantity 10\n" + "Cart quantity 10\"")
    public void FB12376() {
        maximumLimit = 15;
        quantity = 10;
        remainingQuantity = quantity;
        cartQuantity = 10;
        storePage().navigateToHomePage();
        flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        flashSalePage.checkoutWhenFlashSaleWithoutLogin(false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 54, testName = "Check remaining when user cancel order when limit > remaining > cart", description = "Cancel order FB12376")
    public void FB12377() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
        remainingQuantity = quantity;
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), flashSalePage.actualRS);
    }

    @Test(priority = 55, testName = "Check remaining when user reorder when limit > remaining > cart", description = "Cancel order FB12377")
    public void FB123781() {
        try {
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        }
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 56, testName = "Cancel order FB12378")
    public void FB123782() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
    }

    @Test(priority = 57, testName = "Check remaining quantity on Flash sale campaign when limit = remaining and limit > cart", description = "\"Limit flashsale quantity 10\n" + "Remaining quantity 10\n" + "Cart quantity 5\"")
    public void FB12379() {
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        maximumLimit = 10;
        quantity = 10;
        remainingQuantity = quantity;
        cartQuantity = 5;
        storePage().navigateToHomePage();
        flashSalePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        storePage().navigateToHomePage();
        flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        flashSalePage.checkoutWhenFlashSaleWithoutLogin(false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 58, testName = "Check remaining when user cancel order when limit = remaining and limit > cart", description = "Cancel order FB12376")
    public void FB12380() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
        remainingQuantity = quantity;
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), flashSalePage.actualRS);
    }

    @Test(priority = 59, testName = "Check remaining when user reorder when limit = remaining and limit > cart", description = "Cancel order FB12377")
    public void FB123811() {
        try {
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        }
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 59, testName = "Cancel order FB12381")
    public void FB123812() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
    }

    @Test(priority = 60, testName = "Check remaining quantity on Flash sale campaign when limit >= cart >= remaining", description = "\"Limit flashsale quantity 10\n" + "Remaining quantity 10\n" + "Cart quantity 11\"")
    public void FB12382() {
        maximumLimit = 10;
        quantity = 10;
        remainingQuantity = quantity;
        cartQuantity = 11;
        storePage().navigateToHomePage();
        flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        flashSalePage.checkoutWhenFlashSaleWithoutLogin(false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 61, testName = "Check remaining when user cancel order when limit > remaining > cart", description = "Cancel order FB12382")
    public void FB12383() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
        remainingQuantity = quantity;
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), flashSalePage.actualRS);
    }

    @Test(priority = 62, testName = "Check remaining when user reorder when limit > remaining > cart", description = "Cancel order FB12383")
    public void FB123841() {
        try {
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            orderURL = flashSalePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        }
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 62, testName = "Cancel order FB12384")
    public void FB123842() {
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
    }

    @Test(priority = 63, testName = "Check remaining quantity when user continues to create order", description = "\"1. Limit flashsale quantity 10. Quantity 20. Cart quantity 5\n" + "2. Limit flashsale quantity 10. Remaining quantity 15. Cart quantity 7\n" + "3. Limit flashsale quantity 10. Remaining quantity 8. Cart quantity 8\"")
    public void FB92() {
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        maximumLimit = 10;
        quantity = 20;
        remainingQuantity = quantity;
        cartQuantity = 5;
        storePage().navigateToHomePage();
        flashSalePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        storePage().navigateToHomePage();
        try {
            productName = flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        } catch (Exception exception) {
            helper.refreshPage();
            productName = flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        }
        flashSalePage.checkoutWhenFlashSaleWithoutLogin(false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 64, testName = "Check remaining quantity when user cancel order on new tab without reload", description = "2. Limit flashsale quantity 10. Remaining quantity 15. Cart quantity 7")
    public void FB123852() {
        remainingQuantity = quantity - cartQuantity;
        currentWindow = helper.getCurrentWindow();
        helper.openNewTab(orderURL);
        helper.waitForURLContains(orderURL);
        orderHandle = helper.getCurrentWindow();
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
        helper.switchToWindowHandle(currentWindow);
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 65, testName = "Check remaining quantity when user cancel order on new tab after reload")
    public void FB123853() {
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), flashSalePage.actualRS);
        System.out.println(remainingQuantity);
    }

    @Test(priority = 66, testName = "Add to cart 1")
    public void FB123854() {
        cartQuantity = 6;
        storePage().navigateToHomePage();
        productName = flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        flashSalePage.checkoutWhenFlashSaleWithoutLogin(false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        System.out.println(remainingQuantity);
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
        remainingQuantity = quantity - cartQuantity;
        System.out.println("remaining: " + remainingQuantity); //14
    }

    @Test(priority = 67, testName = "Add to cart 2")
    public void FB123855() {
        cartQuantity = 6;
        storePage().navigateToHomePage();
        productName = flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        flashSalePage.checkoutWhenFlashSaleWithoutLogin(false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(remainingQuantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 68, testName = "Add to cart 3")
    public void FB123856() {
        remainingQuantity = remainingQuantity - cartQuantity; //8
        System.out.println("remaining: " + remainingQuantity);
        cartQuantity = 8;
        storePage().navigateToHomePage();
        productName = flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        flashSalePage.checkoutWhenFlashSaleWithoutLogin(false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(remainingQuantity, maximumLimit, cartQuantity), flashSalePage.actualRS); //sold out
        System.out.println(remainingQuantity);
    }

    @Test(priority = 69, testName = "Check remaining quantity - sold out when user cancel order on new tab without reload", description = "Open checkout page on new tab")
    public void FB123857() {
        currentWindow = helper.getCurrentWindow();
        helper.openNewTab(orderURL);
        helper.waitForURLContains(orderURL);
        orderHandle = helper.getCurrentWindow();
        try {
            myOrderPage.cancelOrderWithURL(orderURL);
        } catch (Exception ex) {
            Log.error("Cannot cancel order. " + ex.getMessage());
        }
        helper.switchToWindowHandle(currentWindow);
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(remainingQuantity, maximumLimit, cartQuantity), flashSalePage.actualRS);
        System.out.println(remainingQuantity);
    }

    @Test(priority = 70, testName = "Check remaining quantity - sold out when user cancle order on new tab after reloading")
    public void FB123858() {
        helper.refreshPage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(remainingQuantity, maximumLimit, 0), flashSalePage.actualRS);
    }

    @Test(priority = 71, testName = "Add to cart àtẻ sold out flash sale")
    public void FB123859() {
        cartQuantity = 8;
        storePage().navigateToHomePage();
        productName = flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        flashSalePage.checkoutWhenFlashSaleWithoutLogin(false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        Assert.assertTrue(flashSalePage.checkRemainingQuantityFlashSale(0, maximumLimit, cartQuantity), flashSalePage.actualRS);
    }

    @Test(priority = 72, testName = "Check remaining quantity when user continues to create order")
    public void FB1238510() {
        cartQuantity = 10;
        storePage().navigateToHomePage();
        productName = flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        storePage().navigateToHomePage();
        Assert.assertFalse(flashSalePage.checkCartWhenFlashSale(productName, true), flashSalePage.actualRS);
    }

    @Test(priority = 73, testName = "Clear all data and browser tab")
    public void FB102() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        helper.refreshPage();
        helper.closeAllOpenTabExceptMainTab(currentWindow);
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
    }

    @Test(priority = 74, testName = "Check flash sale on cart when flash sale is coming", description = "Limit flashsale quantity 2. Quantity 10. Cart quantity 5")
    public void FB12562() {
        storePage().navigateToHomePage();
        maximumLimit = 2;
        quantity = 10;
        cartQuantity = 5;
        isFlashsale = false;
        String homeURL = helper.getCurrentURL();
        currentWindow = helper.getCurrentWindow();
        flashSalePage.createFlashSaleNotFullVariations(addComingStartMinute, addComingEndMinute);
        helper.refreshPage();
        productFlashSale = flashSalePage.clickAddToCartFlashSaleWithQuantityList(0, 1);
        productName = productFlashSale.get(0);
        storePage().navigateToHomePage();
        helper.openNewTab(homeURL);
        cartHandle = helper.getCurrentWindow();
        flashSaleProduct = flashSalePage.getProductByName(true, createFlashSaleObj, productFlashSale);
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, isFlashsale, true), flashSalePage.actualRS);
    }

    @Test(priority = 75, testName = "Check flash sale on cart when flash sale is end after before reload", description = "Update coming flash sale to end after")
    public void FB12563() {
        helper.switchToWindowHandle(currentWindow);
        flashSalePage.updateTimeFlashSale(flashSalePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        helper.switchToWindowHandle(cartHandle);
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, isFlashsale, true), flashSalePage.actualRS);
    }

    @Test(priority = 76, testName = "Check flash sale on cart when flash sale is end after after reload/close then click again")
    public void FB12564() {
        homePage.clickAccountIcon();
        isFlashsale = true;
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, isFlashsale, true), flashSalePage.actualRS);
    }

    @Test(priority = 77, testName = "Check flash sale on cart on new tab when flash sale is out of time before reload")
    public void FB12565() {
        helper.switchToWindowHandle(currentWindow);
        flashSalePage.updateTimeFlashSale(flashSalePage.flashSaleName, addEndedStartMinute, addEndedEndMinute);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute + addEndedEndMinute);
        helper.refreshPage();
        helper.switchToWindowHandle(cartHandle);
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, isFlashsale, true), flashSalePage.actualRS);
    }

    @Test(priority = 78, testName = "Check flash sale on cart on new tab when flash sale is out of time after reload")
    public void FB12566() {
        helper.refreshPage();
        isFlashsale = false;
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, isFlashsale, true), flashSalePage.actualRS);
    }

    @Test(priority = 79, testName = "Check flash sale on cart with product has many variations then add to cart except flash sale size", description = "Add to cart size L")
    public void FB12568() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        storePage().navigateToHomePage();
        maximumLimit = 2;
        quantity = 10;
        cartQuantity = 5;
        isFlashsale = false;
        flashSalePage.size = "M";
        flashSalePage.createFlashSaleWithVariation(addEndAfterStartMinute, addEndAfterEndMinute, "M", maximumLimit, quantity);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute);
        helper.refreshPage();
        try {
            productFlashSale = flashSalePage.clickAddToCartFlashSaleSelectSize("L", 1, 1);
        } catch (Exception exception) {
            storePage().navigateToHomePage();
            Log.info(exception.getMessage());
            productFlashSale = flashSalePage.clickAddToCartFlashSaleSelectSize("L", 1, 1);
        }
        System.out.println(productFlashSale);
        productName = productFlashSale.get(0);
        flashSaleProduct = flashSalePage.getProductByName(false, createFlashSaleObj, productFlashSale);
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, isFlashsale, true), flashSalePage.actualRS);
    }

    @Test(priority = 80, testName = "Check flash sale on checkout with product has many variations")
    public void FB49() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, isFlashsale, true, true, true), checkoutPage.actualRS);
    }

    @Test(priority = 81, testName = "Check flash sale on cart with product has many variations")
    public void FB12569() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        isFlashsale = true;
        productFlashSale = flashSalePage.clickAddToCartFlashSaleWithQuantityList(1, 1);
        System.out.println(productFlashSale);
        flashSaleProduct = flashSalePage.getProductByName(isFlashsale, createFlashSaleObj, productFlashSale);
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, isFlashsale, true), flashSalePage.actualRS);
    }

    @Test(priority = 82, testName = "Check flash sale on checkout with product has many variations - correct size")
    public void FB51() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, isFlashsale, true, false, false), checkoutPage.actualRS);
    }

    @Test(priority = 83, testName = "Check flash sale on cart with product has topping")
    public void FB12570() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        isFlashsale = true;
        try {
            productFlashSale = flashSalePage.clickAddToCartFlashSaleSelectTopping(0, 1);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = flashSalePage.clickAddToCartFlashSaleSelectTopping(0, 1);
        }
        System.out.println(productFlashSale);
        flashSaleProduct = flashSalePage.getProductByName(isFlashsale, createFlashSaleObj, productFlashSale);
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, isFlashsale, true), flashSalePage.actualRS);
    }

    @Test(priority = 84, testName = "Check flash sale on checkout with product has only topping")
    public void FB53() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, true, true, false, false), checkoutPage.actualRS);
    }

    @Test(priority = 85, testName = "Check flash sale on cart with product has size and topping then add to cart except flash sale size")
    public void FB12575() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        flashSalePage.size = "S";
        isFlashsale = false;
        try {
            productFlashSale = flashSalePage.clickAddToCartFlashSaleSelectSizeAndTopping("S", 2, 1);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = flashSalePage.clickAddToCartFlashSaleSelectSizeAndTopping("S", 2, 1);
        }
        System.out.println(productFlashSale);
        flashSaleProduct = flashSalePage.getProductByName(isFlashsale, createFlashSaleObj, productFlashSale);
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, isFlashsale, true), flashSalePage.actualRS);
    }

    @Test(priority = 86, testName = "Check flash sale on checkout with product has topping and size - incorrect size")
    public void FB61() {
        storePage().navigateToHomePage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, isFlashsale, true, false, false), checkoutPage.actualRS);
    }

    @Test(priority = 87, testName = "Check flash sale on cart with product has size and topping")
    public void FB12576() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartLogin(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        storePage().navigateToHomePage();
        isFlashsale = true;
        try {
            productFlashSale = flashSalePage.clickAddToCartFlashSaleSelectTopping(2, 1);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = flashSalePage.clickAddToCartFlashSaleSelectTopping(2, 1);
        }
        System.out.println(productFlashSale);
        flashSaleProduct = flashSalePage.getProductByName(isFlashsale, createFlashSaleObj, productFlashSale);
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, isFlashsale, true), flashSalePage.actualRS);
    }

    @Test(priority = 88, testName = "Check flash sale on checkout with product has size and topping - correct size")
    public void FB63() {
        storePage().navigateToHomePage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, isFlashsale, true, false, false), checkoutPage.actualRS);
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
    }

    @Test(priority = 89, testName = "Check flash sale on cart with product has topping and size")
    public void FB12577() {
        storePage().navigateToHomePage();
        isFlashsale = false;
        flashSalePage.size = "S";
        try {
            productFlashSale = flashSalePage.clickAddToCartFlashSaleSelectSizeAndTopping("S", 2, 1);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = flashSalePage.clickAddToCartFlashSaleSelectSizeAndTopping("S", 2, 1);
        }
        System.out.println(productFlashSale);
        flashSaleProduct = flashSalePage.getProductByName(isFlashsale, createFlashSaleObj, productFlashSale);
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, isFlashsale, true), flashSalePage.actualRS);
    }

    @Test(priority = 90, testName = "Check flash sale on checkout with product has topping, topping and size - incorrect size")
    public void FB67() {
        storePage().navigateToHomePage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, isFlashsale, true, false, false), checkoutPage.actualRS);
    }

    @Test(priority = 91, testName = "Check flash sale on cart with product has option, size and topping")
    public void FB12578() {
        helper.refreshPage();
        try {
            flashSalePage.checkoutThenClearCartWithoutLogin();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            flashSalePage.checkoutThenClearCartWithoutLogin();
        }
        storePage().navigateToHomePage();
        isFlashsale = true;
        try {
            productFlashSale = flashSalePage.clickAddToCartFlashSaleSelectTopping(3, 1);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = flashSalePage.clickAddToCartFlashSaleSelectTopping(3, 1);
        }
        System.out.println(productFlashSale);
        flashSaleProduct = flashSalePage.getProductByName(isFlashsale, createFlashSaleObj, productFlashSale);
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, isFlashsale, true), flashSalePage.actualRS);
    }

    @Test(priority = 92, testName = "Check flash sale on checkout with product has topping, option and size - correct size")
    public void FB69() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, isFlashsale, true, false, false), checkoutPage.actualRS);
    }

    @Test(priority = 93, testName = "Check flash sale on cart when users add to cart more than maximum limit, limit <= remaining quantity")
    public void FB12579() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        isFlashsale = true;
        try {
            productFlashSale = flashSalePage.clickAddToCartFlashSaleWithQuantityList(1, cartQuantity);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = flashSalePage.clickAddToCartFlashSaleWithQuantityList(1, cartQuantity);
        }
        System.out.println(cartQuantity);
        flashSaleProduct = flashSalePage.getProductByName(isFlashsale, createFlashSaleObj, productFlashSale);
        Assert.assertTrue(flashSalePage.checkCartQuantityWhenFlashSale(flashSaleProduct, cartQuantity, maximumLimit, true), flashSalePage.actualRS);
    }

    @Test(priority = 94, testName = "Add to checkout more than limited flash sale quantity (remaining quantity >= limit", description = "\tRemaining: 10\n" + "\tLimit: 2\n" + "\tAdd to cart: 5")
    public void FB71() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutQuantityWhenFlashSale(flashSaleProduct, cartQuantity, maximumLimit, quantity, false, true, false), checkoutPage.actualRS);
    }

    @Test(priority = 95, testName = "Clear product in cart then stop flash sale")
    public void FB72() {
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
    }

    @Test(priority = 96, testName = "Check flash sale on cart when users add to cart more than maximum limit, limit >= remaining quantity")
    public void FB12580() {
        maximumLimit = 10;
        quantity = 10;
        cartQuantity = 8;
        storePage().navigateToHomePage();
        flashSalePage.size = "M";
        flashSalePage.createFlashSaleWithVariation(addEndAfterStartMinute, addEndAfterEndMinute, "M", maximumLimit, quantity);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute);
        storePage().navigateToHomePage();
        productName = flashSalePage.clickAddToCartFromFlashSaleWithQuantity(1, cartQuantity);
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, true, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, true);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        System.out.println(flashSalePage.checkRemainingQuantityFlashSaleIndex(1, quantity, maximumLimit, cartQuantity));
        remainingQuantity = quantity - cartQuantity;
        cartQuantity = 5;
        storePage().navigateToHomePage();
        try {
            productFlashSale = flashSalePage.clickAddToCartFlashSaleWithQuantityList(1, cartQuantity);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = flashSalePage.clickAddToCartFlashSaleWithQuantityList(1, cartQuantity);
        }
        System.out.println(productFlashSale);
        flashSaleProduct = flashSalePage.getProductByName(true, createFlashSaleObj, productFlashSale);
        Assert.assertTrue(flashSalePage.checkCartQuantityWhenFlashSale(flashSaleProduct, cartQuantity, remainingQuantity, true), flashSalePage.actualRS);
    }

    @Test(priority = 97, testName = "Check quantity of flash sale product on Checkout page")
    public void FB74() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutQuantityWhenFlashSale(flashSaleProduct, cartQuantity, maximumLimit, remainingQuantity, false, true, false), checkoutPage.actualRS);
    }

    @Test(priority = 98, testName = "Clear all cart")
    public void FB75() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        helper.closeAllOpenTabExceptMainTab(currentWindow);
    }

    @Test(priority = 99, testName = "Check flash sale on checkout page on new tab when flash sale is out of time before clicking complete button", description = "Limit flashsale quantity 10. Quantity 2. Cart quantity 1")
    public void FB93() {
        quantity = 10;
        maximumLimit = 2;
        remainingQuantity = quantity;
        cartQuantity = 1;
        storePage().navigateToHomePage();
        flashSalePage.createFlashSale(addEndedStartMinute, 2, false);
        helper.refreshPage();
        homeURL = helper.getCurrentURL();
        currentWindow = helper.getCurrentWindow();
        helper.openNewTab(homeURL);
        productName = flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute);
        helper.refreshPage();
        currentLanguage = homePage.getCurrentLanguage();
        productFlashSale = checkoutPage.getProductFlashSaleInformation(productName);
        System.out.println(productFlashSale);
        isFlashsale = true;
        flashSaleProduct = flashSalePage.getProductByName(isFlashsale, createFlashSaleObj, productFlashSale);
        System.out.println("waiting time");
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute + 2);
        checkoutPage.clickCompleteBtn(addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        helper.sleep(2);
        softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), "------- TC1:\nCheckout - Click complete and check content dialog\n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.clickOkayBtn(), "------- TC2:\nCheckout - Click okay button then check the number of dialog is displaying\n" + checkoutPage.actualRS);
        softAssert.assertFalse(checkoutPage.checkPriceOfProduct(false, flashSaleProduct), "------- TC3:\nCheckout - Check price change from flash sale price to original price\n" + checkoutPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 100, testName = "Check flash sale on checkout page on new tab when flash sale has stopped by admin before Clicking complete button")
    public void FB97() {
        helper.closeAllOpenTabExceptMainTab(currentWindow);
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        cartQuantity = 1;
        storePage().navigateToHomePage();
        flashSalePage.createFlashSale(addEndAfterStartMinute, addEndAfterEndMinute, false);
        helper.refreshPage();
        homeURL = helper.getCurrentURL();
        currentWindow = helper.getCurrentWindow();
        helper.openNewTab(homeURL);
        productName = flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutHandle = helper.getCurrentWindow();
        checkoutURL = helper.getCurrentUrl();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        currentLanguage = homePage.getCurrentLanguage();
        productFlashSale = checkoutPage.getProductFlashSaleInformation(productName);
        System.out.println(productFlashSale);
        System.out.println("FB97 " + productFlashSale.size());
        System.out.println(flashSaleProduct);
        flashSaleProduct = flashSalePage.getProductByName(true, createFlashSaleObj, productFlashSale);
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        checkoutPage.clickCompleteBtn(addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), "------- TC1:\nCheckout - Click complete and check content dialog\n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.clickOkayBtn(), "------- TC2:\nCheckout - Click okay button then check the number of dialog is displaying\n" + checkoutPage.actualRS);
        softAssert.assertFalse(checkoutPage.checkPriceOfProduct(false, flashSaleProduct), "------- TC3:\nCheckout - Check price change from flash sale price to original price\n" + checkoutPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 101, testName = "Check flash sale on checkout page on new tab when flash sale is out of time before Clicking complete button")
    public void FB1001() {
        cartQuantity = 1;
        flashSalePage.createFlashSale(addEndAfterStartMinute, addEndAfterEndMinute, false);
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        currentLanguage = homePage.getCurrentLanguage();
        helper.refreshPage();
        productFlashSale = checkoutPage.getProductFlashSaleInformation(productName);
        System.out.println(productFlashSale);
        flashSaleProduct = flashSalePage.getProductByName(true, createFlashSaleObj, productFlashSale);
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        helper.refreshPage();
        softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), "------- TC1:\nCheckout - Click complete and check content dialog\n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.clickOkayBtn(), "------- TC2:\nCheckout - Click okay button then check the number of dialog is displaying\n" + checkoutPage.actualRS);
        softAssert.assertFalse(checkoutPage.checkPriceOfProduct(false, flashSaleProduct), "------- TC3:\nCheckout - Check price change from flash sale price to original price\n" + checkoutPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 102, testName = "Close all tabs")
    public void FB104() {
        helper.closeAllOpenTabExceptMainTab(currentWindow);
    }

    //maximum limit
    @Test(priority = 103, testName = "Check checkout page when user increase quantity more than maximum limit")
    public void FB105() {
        quantity = 5;
        maximumLimit = 3;
        cartQuantity = 3;
        storePage().navigateToHomePage();
        flashSalePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        productFlashSale = flashSalePage.clickAddToCartFlashSaleWithQuantityList(0, cartQuantity);
        System.out.println(productFlashSale);
        flashSaleProduct = flashSalePage.getProductByName(true, createFlashSaleObj, productFlashSale);
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, true, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        currentLanguage = homePage.getCurrentLanguage();
        checkoutPage.upQuantityWithElementProductFlashSale(1, maximumLimit, cartQuantity);
        softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.checkContentDialog(currentLanguage, promotionDataTest.PAGE, checkoutPage.getOverLimitedKeyList()), "-------TC1:\ncheckout page - Check over limit content of dialog\n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.clickOkayBtn(), "-------TC2:\nCheckout page - Check over limit - Click okay button then check the number of dialog is displaying\n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.checkCheckoutQuantityWhenFlashSale(flashSaleProduct, cartQuantity + 1, maximumLimit, quantity, false, false, false), "-------TC3:\nCheckout page - Check over limit - Check price change from flash sale price to original price\n" + checkoutPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 104, testName = "Stop flash sale")
    public void FB110() {
        checkoutPage.clearAllCart();
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
    }

    @Test(priority = 105, testName = "Check checkout page when user increase quantity more than remaining quantity")
    public void FB111() {
        quantity = 5;
        maximumLimit = 5;
        cartQuantity = 5;
        storePage().navigateToHomePage();
        flashSalePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        productFlashSale = flashSalePage.clickAddToCartFlashSaleWithQuantityList(0, cartQuantity);
        System.out.println(productFlashSale);
        flashSaleProduct = flashSalePage.getProductByName(true, createFlashSaleObj, productFlashSale);
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        currentLanguage = homePage.getCurrentLanguage();
        checkoutPage.upQuantityWithElementProductFlashSale(1, maximumLimit, cartQuantity);
        helper.sleep(1);
        softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.checkContentDialog(currentLanguage, promotionDataTest.PAGE, checkoutPage.getOverLimitedKeyList()), "------- TC1:\nCheckout page - Check over limit content of dialog\n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.clickOkayBtn(), "------- TC2:\nCheckout page - Check over limit - Click okay button then check the number of dialog is displaying\n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.checkCheckoutQuantityWhenFlashSale(flashSaleProduct, cartQuantity + 1, maximumLimit, quantity, false, false, false), "------- TC2:\nCheckout page - Check over limit - Check price change from flash sale price to original price\n" + checkoutPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 106, testName = "Check flash sale price when user click complete btn at flash sale 1 ended and flash sale 2 start, both of flash sale have the same product.")
    public void FB116() {
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        helper.refreshPage();
        quantity = 20;
        maximumLimit = 15;
        cartQuantity = 1;
        minimumPurchaseOrder = 250000;
        storePage().navigateToHomePage();
        flashSalePage.createFlashSaleWithMinimumPurchaseOrder(addEndAfterStartMinute, 60, maximumLimit, quantity, minimumPurchaseOrder);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        currentLanguage = homePage.getCurrentLanguage();
        productFlashSale = flashSalePage.clickAddToCartFlashSaleWithQuantityList(1, cartQuantity);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        flashSaleProduct = flashSalePage.getProductByName(true, createFlashSaleObj, productFlashSale);
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        Assert.assertTrue(checkoutPage.checkQuantity(false, cartQuantity), checkoutPage.actualRS);
    }

    @Test(priority = 107, testName = "Check total amount is bigger than minimum purchase order")
    public void FB117() {
        helper.refreshPage();
        price = helper.convertToInteger(productFlashSale.get(1));
        flashSalePrice = helper.convertToInteger(productFlashSale.get(2));
        Assert.assertTrue(checkoutPage.checkMaximumPurchase(minimumPurchaseOrder, cartQuantity, price, flashSalePrice), checkoutPage.actualRS);
    }

    @Test(priority = 108, testName = "Check total amount is less than minimum purchase order", description = "Decrease the number of product till total amount is less more than minimum purchase order")
    public void FB118() {
        Assert.assertTrue(checkoutPage.decreaseFlashSaleProductQuantity(), checkoutPage.actualRS);
    }

    @Test(priority = 109, testName = "Check total amount content of minimum purchase notification dialog")
    public void FB1191() {
        cartQuantity = checkoutPage.getCartQuantity() - 1;
        softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.checkContentDialog(currentLanguage, promotionDataTest.PAGE, checkoutPage.getMinimumPurchaseKeyList()), "TC1: \n Check content dialog \n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.clickOkayBtn(), "TC2: \n Check after clicking okay \n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.checkPriceOfProduct(false, flashSaleProduct), "TC3: \n Check price" + checkoutPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 110, testName = "Check total amount content of minimum purchase notification dialog")
    public void FB1192() {
        //check price of subtotal after decreasing product
        Assert.assertTrue(checkoutPage.checkMaximumPurchaseNoFlashSale(cartQuantity, price), checkoutPage.actualRS);
    }

    @Test(priority = 112, testName = "Create order successfully")
    public void FB122() {
        helper.refreshPage();
        checkoutPage.checkMaximumPurchase(minimumPurchaseOrder, cartQuantity, price, flashSalePrice);
        checkoutPage.checkoutAction(currentLanguage, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        Assert.assertTrue(checkoutPage.checkDisplayOfViewOrder(), "View order did not display");
    }

    @Test(priority = 113, testName = "View order and check subtotal")
    public void FB123() {
        checkoutPage.viewOrderAfterCheckout();
        Assert.assertTrue(myOrderPage.checkValueOfSubTotal(helper.formatCurrencyToThousand(checkoutPage.gettotalOriginalPrice())), myOrderPage.actualRS);
    }

    @Test(priority = 115, testName = "Check flash sale price when user click complete btn at flash sale 1 ended and flash sale 2 start, both of flash sale have the same product.")
    public void FB125() {
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        quantity = 20;
        maximumLimit = 15;
        cartQuantity = 3;
        int addMinus = 1;
        storePage().navigateToHomePage();
        FlashSaleProduct flashSaleProduct1;
        FlashSaleProduct flashSaleProduct2;
        flashSalePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addMinus, maximumLimit, quantity);
        String flashSaleKey = flashSalePage.flashSaleName;
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        currentLanguage = homePage.getCurrentLanguage();
        try {
            productName = flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            productName = flashSalePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        }
        productFlashSale = flashSalePage.getFlashsSaleProductInformation(flashSaleKey, productName);
        flashSaleProduct1 = flashSalePage.getProductByName(true, createFlashSaleObj, productFlashSale);
        flashSalePage.createFlashSaleWithQuantity(addEndAfterStartMinute + addMinus, addMinus, maximumLimit, quantity);
        productFlashSale = flashSalePage.getFlashsSaleProductInformation(flashSalePage.flashSaleName, productName);
        System.out.println(productFlashSale);
        flashSaleProduct2 = flashSalePage.getProductByName(true, createFlashSaleObj, productFlashSale);
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, true, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.checkPriceOfProduct(true, flashSaleProduct1), "---TC1:\n Check price when flash sale 1 is active. \n" + flashSalePage.actualRS);
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute + addMinus);
        helper.sleep(5);
        checkoutPage.clickCompleteBtn(addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        softAssert.assertTrue(checkoutPage.checkContentDialog(currentLanguage, promotionDataTest.PAGE, checkoutPage.getHasBeenChangedKeyList()), "---TC2:\n Check display of change price dialog when flash sale 2 is active and flash sale 1 is ended. \n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.clickOkayBtn(), "---TC3:\n Check behavior after clicking okay button. \n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.checkPriceOfProduct(true, flashSaleProduct2), "---TC4:\n Check price when flash sale 2 is active. \n" + checkoutPage.actualRS);
        softAssert.assertAll();
    }

    //Check product details, checkout, edit order
    @Test(priority = 117, testName = "Check flash sale on product details when flash sale is coming")
    public void FB1091() {
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        maximumLimit = 2;
        quantity = 10;
        cartQuantity = 5;
        currentWindow = helper.getCurrentWindow();
        flashSalePage.createFlashSaleNotFullVariations(addComingStartMinute, addComingEndMinute);
        helper.refreshPage();
        productFlashSale = flashSalePage.clickProductFlashSale(0);
        productName = productFlashSale.get(0);
        String productDetailURL = helper.getCurrentURL();
        storePage().navigateToHomePage();
        helper.openNewTab(productDetailURL);
        cartHandle = helper.getCurrentWindow();
        softAssert = new SoftAssert();
        softAssert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), "---------- TC1: \n " + productDetailsPage.actualRS);
        softAssert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, false), "-------- TC2: \n Check price on product details when flash sale is coming. \n" + productDetailsPage.actualRS);
        softAssert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(flashSalePage.flashSaleName, productName, false), "-------- TC3: \nCheck flash sale on related product when flash sale is coming \n" + productDetailsPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 119, testName = "Check flash sale on product details when flash sale is end after before reload", description = "Update coming flash sale to end after")
    public void FB1101() {
        helper.switchToWindowHandle(currentWindow);
        flashSalePage.updateTimeFlashSale(flashSalePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        helper.switchToWindowHandle(cartHandle);
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 120, testName = "Check price on product details when flash sale is coming before reload")
    public void FB1102() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    @Test(priority = 121, testName = "Check flash sale on related product when flash sale is coming before reload")
    public void FB225() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(flashSalePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    @Test(priority = 122, testName = "Check flash sale on product details when flash sale is end after after reload")
    public void FB1851() {
        helper.refreshPage();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 123, testName = "Check price on product details when flash sale is end after after reload")
    public void FB1112() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    @Test(priority = 124, testName = "Check flash sale on related product when flash sale is end after after reload")
    public void FB226() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(flashSalePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    @Test(priority = 125, testName = "Check flash sale on product details on new tab when flash sale is out of time before reload")
    public void FB1131() {
        helper.switchToWindowHandle(currentWindow);
        flashSalePage.updateTimeFlashSale(flashSalePage.flashSaleName, addEndedStartMinute, addEndedEndMinute);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute + addEndedEndMinute);
        helper.refreshPage();
        helper.switchToWindowHandle(cartHandle);
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 126, testName = "Check price on product details on new tab when flash sale is out of time before reload")
    public void FB1132() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    @Test(priority = 127, testName = "Check flash sale on related product on new tab when flash sale is out of time before reload")
    public void FB228() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(flashSalePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    @Test(priority = 128, testName = "Check flash sale on product details on new tab when flash sale is out of time after reload")
    public void FB1141() {
        helper.refreshPage();
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 129, testName = "Check price on product details when flash sale is coming")
    public void FB1142() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    @Test(priority = 130, testName = "Check flash sale on related product when flash sale is coming")
    public void FB229() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(flashSalePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    //Product and flash sale includes topping
    @Test(priority = 131, testName = "Check flash sale on product details with product has many variations")
    public void FB1161() {
        helper.closeAllOpenTabExceptMainTab(currentWindow);
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        storePage().navigateToHomePage();
        maximumLimit = 2;
        quantity = 10;
        cartQuantity = 5;
        flashSalePage.createFlashSaleWithVariation(addEndAfterStartMinute, addEndAfterEndMinute, "M", maximumLimit, quantity);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute);
        helper.refreshPage();
        productFlashSale = flashSalePage.clickProductFlashSale(1);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 132, testName = "Check price on product details with product has many variations")
    public void FB1162() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 133, testName = "Check flash sale on edit cart with product has many variations")
    public void FB1411() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        helper.sleep(1);
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 134, testName = "Check price on edit cart with product has many variations")
    public void FB1412() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 135, testName = "Check flash sale on edit order checkout with product has many variations")
    public void FB1421() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, true, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 136, testName = "Check price on edit order checkout with product has many variations")
    public void FB1422() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 137, testName = "Check flash sale on product details with product has many variations - incorrect size")
    public void FB1171() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        sizeName = "L";
        try {
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        }
        productName = productNotFlashSale.get(0);
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 138, testName = "Check price on product details with product has many variations - incorrect size")
    public void FB1172() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleSizeCondition(flashSalePage.flashSaleName, productName, false, sizeName), productDetailsPage.actualRS);
    }

    @Test(priority = 139, testName = "Check flash sale on edit cart with product has many variations - incorrect size")
    public void FB1431() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 140, testName = "Check price on edit cart with product has many variations - incorrect size")
    public void FB1432() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleSizeCondition(flashSalePage.flashSaleName, productName, false, cartQuantity, sizeName), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 141, testName = "Check flash sale on edit order checkout with product has many variations - incorrect size")
    public void FB1441() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 142, testName = "Check price on edit order checkout with product has many variations - incorrect size")
    public void FB1442() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleSizeCondition(flashSalePage.flashSaleName, productName, false, cartQuantity, sizeName), editOrder.actualRS);
    }

    @Test(priority = 143, testName = "Check flash sale on product details with product has topping - no select topping")
    public void FB1181() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = flashSalePage.clickProductFlashSale(0);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 144, testName = "Check price on product details with product has topping - no select topping")
    public void FB1182() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 145, testName = "Check flash sale on edit cart with product has topping - no select topping")
    public void FB1451() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 146, testName = "Check price on edit cart with product has topping - no select topping")
    public void FB1452() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 147, testName = "Check flash sale on edit order checkout with product has topping - no select topping")
    public void FB1461() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 148, testName = "Check price on edit order checkout with product has topping - no select topping")
    public void FB1462() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 149, testName = "Check flash sale on product details with product has topping - select topping")
    public void FB1183() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 150, testName = "Check price on product details with product has topping - select topping")
    public void FB1184() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, true, toppingTotalPrice, ""), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 151, testName = "Check flash sale on edit cart with product has topping - select topping")
    public void FB1453() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 152, testName = "Check price on edit cart with product has topping - select topping")
    public void FB1454() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 153, testName = "Check flash sale on edit order checkout with product has topping - select topping")
    public void FB1463() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 154, testName = "Check price on edit order checkout with product has topping - select topping")
    public void FB1464() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    @Test(priority = 155, testName = "Check flash sale on product details with product has size and topping")
    public void FB1231() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = flashSalePage.clickProductFlashSale(2);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 156, testName = "Check price on product details with product has size and topping")
    public void FB1232() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 157, testName = "Check flash sale on edit cart with product has size and topping")
    public void FB1551() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 158, testName = "Check price on edit cart with product has size and topping")
    public void FB1552() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 159, testName = "Check flash sale on edit order checkout with product has size and topping")
    public void FB1561() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 160, testName = "Check price on edit order checkout with product has size and topping")
    public void FB1562() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 161, testName = "Check flash sale on product details with product has size and topping - incorrect size select topping")
    public void FB1241() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        sizeName = "S";
        try {
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        }
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 162, testName = "Check price on product details with product has size and topping - incorrect size select topping")
    public void FB1242() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, false, toppingTotalPrice, sizeName), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 163, testName = "Check flash sale on edit cart with product has topping and size - incorrect size select topping")
    public void FB1571() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 164, testName = "Check price on edit cart with product has topping and size - incorrect size select topping")
    public void FB1572() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 165, testName = "Check flash sale on edit order checkout with product has topping and size - incorrect size select topping")
    public void FB1581() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 166, testName = "Check price on edit order checkout with product has topping and size - incorrect size select topping")
    public void FB1582() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    @Test(priority = 167, testName = "Check flash sale on product details with product has option, size and topping - no select topping")
    public void FB1245() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = flashSalePage.clickProductFlashSale(3);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 168, testName = "Check price on product details with product has option, size and topping - no select topping")
    public void FB1246() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 169, testName = "Check flash sale on edit cart with product has option, size and topping - no select topping")
    public void FB1591() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 170, testName = "Check price on edit cart with product has option, size and topping - no select topping")
    public void FB1592() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 171, testName = "Check flash sale on edit order checkout with product size and topping")
    public void FB1601() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 172, testName = "Check price on edit order checkout with product has option, size and topping - no select topping")
    public void FB1602() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 173, testName = "Check flash sale on product details with product has option, size and topping - select topping")
    public void FB1247() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 174, testName = "Check price on product details with product has option, size and topping - select topping")
    public void FB1248() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, true, toppingTotalPrice, ""), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 175, testName = "Check flash sale on edit cart with product has option, size and topping - select topping")
    public void FB1593() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 176, testName = "Check price on edit cart with product has option, size and topping - select topping")
    public void FB1594() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 177, testName = "Check flash sale on edit order checkout with product has option, size and topping - select topping")
    public void FB1603() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 178, testName = "Check price on edit order checkout with product has option, size and topping - select topping")
    public void FB1604() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    @Test(priority = 179, testName = "Check flash sale on product details with product has option, size and topping - incorrect size select topping")
    public void FB1249() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        sizeName = "S";
        try {
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        }
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 180, testName = "Check price on product details with product has option, size and topping - incorrect size select topping")
    public void FB12491() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, false, toppingTotalPrice, sizeName), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 181, testName = "Check flash sale on edit cart with product has option, size and topping - incorrect size select topping")
    public void FB1611() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 182, testName = "Check price on edit cart with product has option, size and topping - incorrect size select topping")
    public void FB1612() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 183, testName = "Check flash sale on edit order checkout with product has option, size and topping - incorrect size select topping")
    public void FB1621() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 184, testName = "Check price on edit order checkout with product has option, size and topping - incorrect size select topping")
    public void FB162() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    @Test(priority = 185, testName = "Stop flash sale")
    public void FB37() {
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
    }

    @Test(priority = 186, testName = "Check flash sale on product detail page on new tab when flash sale is out of time before Clicking complete button")
    public void FB1651() {
        quantity = 10;
        maximumLimit = 2;
        cartQuantity = 1;
        int endMinus = 1;
        storePage().navigateToHomePage();
        flashSalePage.createFlashSale(addEndedStartMinute, endMinus, false);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute);
        productFlashSale = flashSalePage.clickProductFlashSale(0);
        productName = productFlashSale.get(0);
        flashSaleProduct = flashSalePage.getProductByName(true, createFlashSaleObj, productFlashSale);
        productDetailURL = helper.getCurrentURL();
        //product details
        currentWindow = helper.getCurrentWindow();
        //edit cart
        helper.openNewTab(productDetailURL);
        orderHandle = helper.getCurrentWindow();
        productDetailsPage.addToCart();
        productDetailsPage.openEditCartFromCart();
        //checkout
        helper.openNewTab(productDetailURL);
        checkoutHandle = helper.getCurrentWindow();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        currentLanguage = homePage.getCurrentLanguage();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute + endMinus);
        Assert.assertTrue(editOrder.checkFlipCounterWhenFlashSaleEnded(), "flip zero did not display");
    }

    @Test(priority = 187, testName = "Click update button on edit order checkout")
    public void FB1652() {
        editOrder.clickUpdateBtn();
        helper.sleep(2);
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), checkoutPage.actualRS);
    }

    @Test(priority = 188, testName = "Click okay button then check the number of dialog is displaying")
    public void FB1653() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(), checkoutPage.actualRS);
    }

    @Test(priority = 189, testName = "Check price change from flash sale price to original price on checkout")
    public void FB1654() {
        helper.sleep(1);
        Assert.assertTrue(checkoutPage.checkPriceOfProduct(false, flashSaleProduct), checkoutPage.actualRS);
    }

    @Test(priority = 190, testName = "Check display of flip countdown zero on edit cart")
    public void FB1641() {
        helper.switchBackCurrentTab(orderHandle);
        Assert.assertTrue(editOrder.checkFlipCounterWhenFlashSaleEnded(), "Flip zero did not display");
    }

    @Test(priority = 191, testName = "Click update button on edit cart to check flash sale ended notification dialog")
    public void FB1642() {
        editOrder.clickUpdateBtn();
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), checkoutPage.actualRS);
    }

    @Test(priority = 192, testName = "Click okay button then check the number of dialog is displaying")
    public void FB1643() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(), checkoutPage.actualRS);
    }

    @Test(priority = 193, testName = "Check price change from flash sale price to original price on cart")
    public void FB1644() {
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, false, false), flashSalePage.actualRS);
    }

    @Test(priority = 194, testName = "Check display of flip countdown zero on product details")
    public void FB1631() {
        helper.switchBackCurrentTab(currentWindow);
        Assert.assertTrue(productDetailsPage.checkFlipCounterWhenFlashSaleEnded(), "Flip zero did not display");
    }

    @Test(priority = 195, testName = "Click add to cart button on product details")
    public void FB1632() {
        productDetailsPage.clickAddToCart();
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), checkoutPage.actualRS);
    }

    @Test(priority = 196, testName = "Click okay button then check the number of dialog is displaying")
    public void FB1633() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(), checkoutPage.actualRS);
    }

    @Test(priority = 197, testName = "Check price change from flash sale price to original price on product details")
    public void FB1634() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    //maximum limit
    //checkout
    @Test(priority = 198, testName = "Check notification on Edit order Checkout when quantity less than maximum limit")
    public void FB178() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        quantity = 5;
        maximumLimit = 3;
        cartQuantity = 2;
        storePage().navigateToHomePage();
        flashSalePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        productFlashSale = flashSalePage.clickProductFlashSale(0);
        productDetailURL = helper.getCurrentURL();
        //product details
        currentWindow = helper.getCurrentWindow();
        //edit cart
        helper.openNewTab(productDetailURL);
        orderHandle = helper.getCurrentWindow();
        productDetailsPage.addProductToCartWithQuantity(cartQuantity);
        productDetailsPage.openEditCartFromCart();
        //checkout
        helper.openNewTab(productDetailURL);
        checkoutHandle = helper.getCurrentWindow();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        currentLanguage = homePage.getCurrentLanguage();
        Assert.assertFalse(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    @Test(priority = 199, testName = "Check notification on Edit order Checkout when quantity equals maximum limit")
    public void FB169() {
        editOrder.clickUpQuantityWithElement(maximumLimit);
        Assert.assertFalse(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    @Test(priority = 200, testName = "Check notification on Edit order Checkout when user increase quantity more than maximum limit")
    public void FB1721() {
        editOrder.clickUpQuantityWithElement(maximumLimit + 1);
        Assert.assertTrue(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    @Test(priority = 201, testName = "Check content of notification")
    public void FB1722() {
        currentLanguage = homePage.getCurrentLanguage();
        Assert.assertTrue(editOrder.checkContentOfMaximumLimitNotify(currentLanguage, maximumLimit), editOrder.actualRS);
    }

    @Test(priority = 202, testName = "Check notification on Edit order Checkout when user reduces quantity more than maximum limit")
    public void FB175() {
        editOrder.clickDownQuantityWithElement(maximumLimit);
        helper.sleep(1);
        Assert.assertFalse(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    //cart
    @Test(priority = 203, testName = "Check notification on Edit cart when quantity less than maximum limit")
    public void FB179() {
        helper.switchToWindowHandle(orderHandle);
        Assert.assertFalse(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    @Test(priority = 204, testName = "Check notification on Edit cart when quantity equals maximum limit")
    public void FB170() {
        editOrder.clickUpQuantityWithElement(maximumLimit);
        Assert.assertFalse(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    @Test(priority = 205, testName = "Check notification on Edit cart when user increases quantity more than maximum limit")
    public void FB1731() {
        editOrder.clickUpQuantityWithElement(maximumLimit + 1);
        Assert.assertTrue(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    @Test(priority = 206, testName = "Check content of notification")
    public void FB1732() {
        Assert.assertTrue(editOrder.checkContentOfMaximumLimitNotify(currentLanguage, maximumLimit), editOrder.actualRS);
    }

    @Test(priority = 207, testName = "Check notification on Edit cart when user reduces quantity more than maximum limit")
    public void FB176() {
        editOrder.clickDownQuantityWithElement(maximumLimit);
        helper.sleep(1);
        Assert.assertFalse(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    //product details
    @Test(priority = 208, testName = "Check notification on Product details when quantity less than maximum limit")
    public void FB180() {
        helper.switchToWindowHandle(currentWindow);
        Assert.assertFalse(productDetailsPage.checkDisplayOfMaximumLimitNotify(), productDetailsPage.actualRS);
    }

    @Test(priority = 209, testName = "Check notification on Product details when quantity equals maximum limit")
    public void FB171() {
        productDetailsPage.clickUpQuantityWithElement(maximumLimit);
        Assert.assertFalse(productDetailsPage.checkDisplayOfMaximumLimitNotify(), "Maximum Limit Icon displayed");
    }

    @Test(priority = 210, testName = "Check notification on Product details when user increases quantity more than maximum limit")
    public void FB1741() {
        productDetailsPage.clickUpQuantityWithElement(maximumLimit + 1);
        Assert.assertTrue(productDetailsPage.checkDisplayOfMaximumLimitNotify(), productDetailsPage.actualRS);
    }

    @Test(priority = 211, testName = "Check content of notification")
    public void FB1742() {
        Assert.assertTrue(productDetailsPage.checkContentOfMaximumLimitNotify(maximumLimit), productDetailsPage.actualRS);
    }

    @Test(priority = 212, testName = "Check notification on Product details when user reduces quantity more than maximum limit")
    public void FB177() {
        productDetailsPage.clickDownQuantityWithElement(maximumLimit);
        helper.sleep(1);
        Assert.assertFalse(productDetailsPage.checkDisplayOfMaximumLimitNotify(), productDetailsPage.actualRS);
    }

    @Test(priority = 213, testName = "Check Localization of the maximum limit notification on Product details")
    public void FB1878() {
        helper.refreshPage();
        Assert.assertFalse(editOrder.checkLanguageOfMaximumLimitNotify(maximumLimit), productDetailsPage.actualRS);
    }

    @Test(priority = 214, testName = "Stop flash sale")
    public void FB462() {
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        helper.closeAllOpenTabExceptMainTab(currentWindow);
    }

    /**
     * Product and flash sale is not included topping
     */
    @Test(priority = 215, testName = "Create flash sale with special variation")
    public void FB463() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        maximumLimit = 2;
        quantity = 10;
        cartQuantity = 5;
        flashSalePage.createFlashSaleWithVariationWithoutTopping(addEndAfterStartMinute, addEndAfterEndMinute, "M", maximumLimit, quantity);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute);
        helper.refreshPage();
        storePage().navigateToHomePage();
        productFlashSale = flashSalePage.clickProductFlashSale(0);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 216, testName = "Check price on product details with product has topping - no select topping")
    public void FB2032() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 217, testName = "Check flash sale on edit cart with product has topping - no select topping")
    public void FB2101() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 218, testName = "Check price on edit cart with product has topping - no select topping")
    public void FB2102() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 219, testName = "Check flash sale on edit order checkout with product has topping - no select topping")
    public void FB2111() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        storePage().navigateToCheckoutTheme1();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 220, testName = "Check price on edit order checkout with product has topping - no select topping")
    public void FB2112() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 221, testName = "Check flash sale on product details with product has topping - select topping")
    public void FB2033() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 222, testName = "Check price on product details with product has topping - select topping")
    public void FB2034() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, true, toppingTotalPrice, ""), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 223, testName = "Check flash sale on edit cart with product has topping - select topping")
    public void FB2103() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 224, testName = "Check price on edit cart with product has topping - select topping")
    public void FB2104() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 225, testName = "Check flash sale on edit order checkout with product has topping - select topping")
    public void FB2113() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 226, testName = "Check price on edit order checkout with product has topping - select topping")
    public void FB2114() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    @Test(priority = 227, testName = "Check flash sale on product details with product has size and topping")
    public void FB2061() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = flashSalePage.clickProductFlashSale(2);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 228, testName = "Check price on product details with product has size and topping")
    public void FB2062() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 229, testName = "Check flash sale on edit cart with product has size and topping")
    public void FB2161() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 230, testName = "Check price on edit cart with product has size and topping")
    public void FB2162() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 231, testName = "Check flash sale on edit order checkout with product has size and topping")
    public void FB2171() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 232, testName = "Check price on edit order checkout with product has size and topping")
    public void FB2172() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 233, testName = "Check flash sale on product details with product has size and topping - incorrect size select topping")
    public void FB2071() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        sizeName = "S";
        try {
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        }
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 234, testName = "Check price on product details with product has size and topping - incorrect size select topping")
    public void FB2072() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, false, toppingTotalPrice, sizeName), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 235, testName = "Check flash sale on edit cart with product has topping and size - incorrect size select topping")
    public void FB2163() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 236, testName = "Check price on edit cart with product has topping and size - incorrect size select topping")
    public void FB2164() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 237, testName = "Check flash sale on edit order checkout with product has topping and size - incorrect size select topping")
    public void FB2173() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 238, testName = "Check price on edit order checkout with product has topping and size - incorrect size select topping")
    public void FB2174() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    @Test(priority = 239, testName = "Check flash sale on product details with product has topping, size and topping - no select topping")
    public void FB2081() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = flashSalePage.clickProductFlashSale(3);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 240, testName = "Check price on product details with product has option, size and topping - no select topping")
    public void FB2082() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 241, testName = "Check flash sale on edit cart with product has option, size and topping - no select topping")
    public void FB2201() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 242, testName = "Check price on edit cart with product has option, size and topping - no select topping")
    public void FB2202() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 243, testName = "Check flash sale on edit order checkout with product size and topping")
    public void FB2211() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 244, testName = "Check price on edit order checkout with product has option, size and topping - no select topping")
    public void FB2212() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 245, testName = "Check flash sale on product details with product has option, size and topping - select topping")
    public void FB2083() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 246, testName = "Check price on product details with product has option, size and topping - select topping")
    public void FB2084() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, true, toppingTotalPrice, ""), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 247, testName = "Check flash sale on edit cart with product has option, size and topping - select topping")
    public void FB2203() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 248, testName = "Check price on edit cart with product has option, size and topping - select topping")
    public void FB2204() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 249, testName = "Check flash sale on edit order checkout with product has option, size and topping - select topping")
    public void FB2213() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 250, testName = "Check price on edit order checkout with product has option, size and topping - select topping")
    public void FB2214() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    @Test(priority = 251, testName = "Check flash sale on product details with product has option, size and topping - incorrect size select topping")
    public void FB2085() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        sizeName = "S";
        try {
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        }
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 252, testName = "Check price on product details with product has option, size and topping - incorrect size select topping")
    public void FB2086() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, false, toppingTotalPrice, sizeName), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 253, testName = "Check flash sale on edit cart with product has option, size and topping - incorrect size select topping")
    public void FB2221() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 254, testName = "Check price on edit cart with product has option, size and topping - incorrect size select topping")
    public void FB2222() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 255, testName = "Check flash sale on edit order checkout with product has option, size and topping - incorrect size select topping")
    public void FB2231() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 256, testName = "Check price on edit order checkout with product has option, size and topping - incorrect size select topping")
    public void FB2232() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(flashSalePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    @Test(priority = 257, testName = "Stop flash sale")
    public void FB207() {
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
    }

    //Check on product list
    @Test(priority = 260, testName = "Check flash sale on product list when flash sale is coming")
    public void FB1852() {
        storePage().navigateToProductListTheme1();
        maximumLimit = 2;
        quantity = 10;
        flashSalePage.createFlashSaleWithVariation(addComingStartMinute, addComingEndMinute, "M", maximumLimit, quantity);
        currentWindow = helper.getCurrentWindow();
        helper.openNewTab(helper.getCurrentURL());
        productListPage.checkDisplayOfCategoryBanner();
        productListHandle = helper.getCurrentWindow();
        helper.refreshPage();
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(flashSalePage.flashSaleName, false), productListPage.actualRS);
    }

    @Test(priority = 261, testName = "Check flash sale on product list when flash sale is end after before reload")
    public void FB186() {
        helper.switchToWindowHandle(currentWindow);
        storePage().navigateToHomePage();
        flashSalePage.updateTimeFlashSale(flashSalePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        helper.switchToWindowHandle(productListHandle);
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(flashSalePage.flashSaleName, false), productListPage.actualRS);
    }

    @Test(priority = 263, testName = "Check flash sale on product list when flash sale is end after after reload")
    public void FB187() {
        helper.refreshPage();
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(flashSalePage.flashSaleName, true), productListPage.actualRS);
    }

    //Product
    @Test(priority = 264, testName = "Check flash sale product on cart when user clicks add to cart icon")
    public void FB188() {
        helper.refreshPage();
        Assert.assertTrue(productListPage.clickFlashSaleProductByName(flashSalePage.flashSaleName), productListPage.actualRS);
    }

    @Test(priority = 265, testName = "Check flash sale on product list on new tab when flash sale is out of time before reload")
    public void FB189() {
        storePage().navigateToProductListTheme1();
        helper.switchToWindowHandle(currentWindow);
        flashSalePage.updateTimeFlashSale(flashSalePage.flashSaleName, addEndedStartMinute, addEndedEndMinute);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute);
        helper.switchToWindowHandle(productListHandle);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndedStartMinute + addEndedEndMinute);
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(flashSalePage.flashSaleName, true), productListPage.actualRS);
    }

    @Test(priority = 266, testName = "Check flash sale on product list on new tab when flash sale is out of time after reload")
    public void FB190() {
        helper.refreshPage();
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(flashSalePage.flashSaleName, false), productListPage.actualRS);
    }

    //Check flash sale tag when stopped by admin
    @Test(priority = 267, testName = "Verify flash sale tag on Product list when stop flash sale without reloaded ")
    public void FB12013() {
        storePage().navigateToHomePage();
        flashSalePage.createFlashSaleNotFullVariations(addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        currentWindow = helper.getCurrentWindow();
        helper.openNewTab(helper.getCurrentUrl());
        storePage().navigateToProductListTheme1();
        helper.sleep(1);
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(flashSalePage.flashSaleName, true), productListPage.actualRS);
    }

    @Test(priority = 268, testName = "Verify flash sale tag on Product list when stop flash sale then reloaded ")
    public void FB12015() {
        helper.refreshPage();
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(flashSalePage.flashSaleName, false), productListPage.actualRS);
    }

    //Check flash sale with special branch
    //Check stop flash sale by admin on Edit order, product list, checkout
    //Testcase:https://mediastep1-my.sharepoint.com/:x:/g/personal/ngan_cao_thi_kim_gosell_vn/EQtsv7XuR7VOuGBoZpHOKRkBCdTct0yNUZ74Q5qIB96Bsg?e=CUOyYp&nav=MTVfe0QxN0U3NDg1LTM0RjUtNDk0MC1BRDgwLTg0Rjg1NjZBRjZERn0
    @Test(priority = 270, testName = "Verify flash sale tag on Product details when flash sale is not in the selected branch on product details page")
    public void FB2331() {
        cartQuantity = 1;
        flashSalePage.createFlashSaleSpecialBranch(addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        productFlashSale = flashSalePage.clickProductFlashSale(0);
        productName = productFlashSale.get(0);
        productDetailsPage.addProductToCartWithQuantity(cartQuantity);
        helper.refreshPage();
        addUserLocationPage.selectBranchWithName(flashSalePage.getBranchNameMissingProductByEnv());
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 271, testName = "Verify price on Product details when flash sale is not in the selected branch on product details page")
    public void FB2332() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    @Test(priority = 273, testName = "Verify flash sale tag on Product details when flash sale is not in the selected branch on related  product")
    public void FB234() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(flashSalePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    //edit cart
    @Test(priority = 274, testName = "Verify flash sale tag when flash sale is not in the selected branch on edit cart")
    public void FB2351() {
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), flashSalePage.actualRS);
    }

    @Test(priority = 275, testName = "Verify price when flash sale is not in the selected branch on edit cart")
    public void FB2352() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, false, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 276, testName = "Check flash sale on edit order checkout with product has many variations - incorrect size")
    public void FB2361() {
        helper.refreshPage();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, true, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 277, testName = "Check price on edit order checkout with product has many variations - incorrect size")
    public void FB23621() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, false, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 278, testName = "Verify flash sale tag on Product list when stop flash sale then reloaded ")
    public void FB23622() {
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
    }

    //flash sale has stopped by admin - click update button on edit order
    @Test(priority = 279, testName = "Check flash sale on product detail page on new tab when flash sale has stopped by admin before Clicking complete button")
    public void FB1681() {
        quantity = 10;
        maximumLimit = 2;
        cartQuantity = 1;
        storePage().navigateToHomePage();
        flashSalePage.createFlashSale(addEndAfterStartMinute, addEndAfterEndMinute, false);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        productFlashSale = flashSalePage.clickProductFlashSale(0);
        productName = productFlashSale.get(0);
        flashSaleProduct = flashSalePage.getProductByName(true, createFlashSaleObj, productFlashSale);
        productDetailURL = helper.getCurrentURL();
        //product details
        currentWindow = helper.getCurrentWindow();
        //edit cart
        helper.openNewTab(productDetailURL);
        orderHandle = helper.getCurrentWindow(); //product detail tab
        productDetailsPage.addToCart();
        productDetailsPage.openEditCartFromCart();
        //checkout
        helper.openNewTab(productDetailURL);
        checkoutHandle = helper.getCurrentWindow();
        helper.sleep(2);
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, true, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        currentLanguage = homePage.getCurrentLanguage();
        checkoutPage.openEditOrderFromCheckout();
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        softAssert = new SoftAssert();
        softAssert.assertTrue(editOrder.checkFlipCounterWhenFlashSale(), "flip count down did not displayed");
        softAssert.assertFalse(editOrder.checkFlipCounterWhenFlashSaleEnded(), "flip zero is displaying");
        softAssert.assertAll();
    }

    @Test(priority = 280, testName = "Check ended flash sale notification dialog after clicking Update button cart on Edit order - checkout")
    public void FB1682() {
        editOrder.clickUpdateBtn();
        softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), "-----TC1:\nCheck content of dialog\n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.clickOkayBtn(), "-----TC2:\nClick on OK button on dialog\n" + checkoutPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 282, testName = "Check price change from flash sale price to original price on checkout")
    public void FB1684() {
        Assert.assertTrue(checkoutPage.checkPriceOfProduct(false, flashSaleProduct), checkoutPage.actualRS);
    }

    @Test(priority = 283, testName = "Check display of flip countdown zero on edit cart - product detail page")
    public void FB1671() {
        helper.switchBackCurrentTab(orderHandle);
        Assert.assertFalse(editOrder.checkFlipCounterWhenFlashSaleEnded(), "Flip zero displayed");
    }

    @Test(priority = 284, testName = "Check ended flash sale notification dialog after clicking add to cart on Edit order - cart")
    public void FB1672() {
        editOrder.clickUpdateBtn();
        softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), "-----TC1:\nCheck content of dialog\n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.clickOkayBtn(), "-----TC2:\nClick on OK button on dialog\n" + checkoutPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 286, testName = "Check price change from flash sale price to original price on cart")
    public void FB1674() {
        Assert.assertTrue(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, false, false), flashSalePage.actualRS);
    }

    @Test(priority = 287, testName = "Check display of flip countdown zero on product details")
    public void FB1661() {
        helper.switchBackCurrentTab(currentWindow);
        Assert.assertFalse(productDetailsPage.checkFlipCounterWhenFlashSaleEnded(), "Flip zero displayed");
    }

    @Test(priority = 288, testName = "Check ended flash sale notification dialog after clicking add to cart on Product detail page")
    public void FB1662() {
        productDetailsPage.clickAddToCart();
        helper.sleep(2);
        softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), "---TC1:\nCheck the content of dialog\n" + checkoutPage.actualRS);
        softAssert.assertTrue(checkoutPage.clickOkayBtn(), "---TC2:\nClick okay button then check the number of dialog is displaying\n" + checkoutPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 290, testName = "Check price change from flash sale price to original price on product details")
    public void FB1664() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(flashSalePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    //Check flash sale tag when stopped by admin
    @Test(priority = 291, testName = "Verify flash sale tag on Edit Cart when stop flash sale without reloaded")
    public void FB181() {
        helper.refreshPage();
        flashSalePage.checkoutThenClearCartWithoutLogin();
        quantity = 10;
        maximumLimit = 2;
        cartQuantity = 1;
        storePage().navigateToHomePage();
        flashSalePage.createFlashSale(addEndAfterStartMinute, addEndAfterEndMinute, false);
        helper.refreshPage();
        flashSalePage.waitTimeChangeStatus(addEndAfterStartMinute);
        productFlashSale = flashSalePage.clickProductFlashSale(0);
        productName = productFlashSale.get(0);
        productDetailURL = helper.getCurrentURL();
        //product details
        currentWindow = helper.getCurrentWindow();
        //edit cart
        helper.openNewTab(productDetailURL);
        orderHandle = helper.getCurrentWindow();
        productDetailsPage.addToCart();
        productDetailsPage.openEditCartFromCart();
        //checkout
        helper.openNewTab(productDetailURL);
        checkoutHandle = helper.getCurrentWindow();
        flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        currentLanguage = homePage.getCurrentLanguage();
        flashSalePage.stopFlashSale(flashSalePage.flashSaleName);
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), "Flash sale did not display before reloading Checkout page");
    }

    @Test(priority = 292, testName = "Verify flash sale tag on Edit Cart when stop flash sale then reloaded")
    public void FB182() {
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), "Flash sale still display after reloading Checkout page");
    }

    @Test(priority = 293, testName = "Verify flash sale tag on Edit Order checkout when stop flash sale without reloaded")
    public void FB183() {
        helper.switchBackCurrentTab(orderHandle);
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), "Flash sale did not display before reloading edit cart");
    }

    @Test(priority = 294, testName = "Verify flash sale tag on Edit Order checkout when stop flash sale then reloaded")
    public void FB184() {
        helper.refreshPage();
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), "Flash sale still display after reloading edit cart");
    }

    @Test(priority = 295, testName = "Verify flash sale tag on Product details when stop flash sale without reloaded")
    public void FB12008() {
        helper.switchBackCurrentTab(currentWindow);
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), "Flash sale did not display before reloading edit cart");
    }

    @Test(priority = 296, testName = "Verify flash sale tag on related product when stop flash sale without reloaded")
    public void FB231() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(flashSalePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    @Test(priority = 297, testName = "Verify flash sale tag on Product details when stop flash sale then reloaded")
    public void FB232() {
        helper.refreshPage();
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), "Flash sale still display after reloading edit cart");
    }

    @Test(priority = 298, testName = "Verify flash sale tag on related product when stop flash sale without reloaded")
    public void FB233() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(flashSalePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }
}

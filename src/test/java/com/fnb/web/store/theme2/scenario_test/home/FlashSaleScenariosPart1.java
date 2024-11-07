package com.fnb.web.store.theme2.scenario_test.home;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme2.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme2.pages.home.HomeDataTest;
import com.fnb.web.store.theme2.pages.home.HomePage;
import com.fnb.web.store.theme2.pages.login.DataTest;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class FlashSaleScenariosPart1 extends BaseTest {
    //Check on home page
    // Testcase:https://mediastep1-my.sharepoint.com/:x:/g/personal/ngan_cao_thi_kim_gosell_vn/EQtsv7XuR7VOuGBoZpHOKRkBCdTct0yNUZ74Q5qIB96Bsg?e=CUOyYp&nav=MTVfe0QxN0U3NDg1LTM0RjUtNDk0MC1BRDgwLTg0Rjg1NjZBRjZERn0
    private Helper helper;
    private HomePage homePage;
    private CheckoutPage checkoutPage;
    private HomeDataTest homeDataTest;
    private DataTest loginDataTest;
    private SoftAssert softAssert;
    private int addComingStartMinute = 20;
    private int addComingEndMinute = 20;
    private int addEndAfterStartMinute = 1;
    private int addEndAfterEndMinute = 10;
    private int addEndedStartMinute = 1;
    private int addEndedEndMinute = 1;
    private String comingFlashSaleName = "";
    private String endAfterFlashSaleName = "";
    private String oldTime = "";
    private String newTime = "";
    private String productName = "";
    private String checkoutURL = "";
    private String currentWindow = "";
    private String checkoutHandle = "";
    private String cartHandle = "";
    private int quantity = 0;
    private int maximumLimit = 0;
    private int cartQuantity = 0;

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = storePage().navigateToHomePageTheme2();
        helper = storePage().helper;
        checkoutPage = new CheckoutPage(getDriver());
        softAssert = new SoftAssert();
    }


}

package com.fnb.web.store.theme2.scenario_test.login;

import com.fnb.utils.helpers.JsonReader;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme2.pages.addUserLocation.AddUserLocationDataTest;
import com.fnb.web.store.theme2.pages.checkout.CheckoutDataTest;
import com.fnb.web.store.theme2.pages.home.HomeDataTest;
import com.fnb.web.store.theme2.pages.login.CheckOutLoginPage;
import com.fnb.web.store.theme2.pages.login.DataTest;
import com.fnb.web.store.theme2.pages.product_details.ProductDetailsDataTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckoutLoginScenarioTest extends BaseTest {
    private CheckOutLoginPage checkOutLoginPage;
    private AddUserLocationDataTest addUserLocationDataTest;
    private ProductDetailsDataTest productDetailsDataTest;
    private JsonReader jsonReader;

    public CheckoutLoginScenarioTest() {
    }

    @BeforeClass
    public void NavigateToProductListPage() {
        checkOutLoginPage = storePage().navigateToCheckoutLoginTheme2();
    }

    @Test(priority = 1, testName = "Verify if user checkout from checkout page without login but account exists - Login by password")
    public void FB6987() {
        String url = "";
        if (jsonReader.getPlatform.equals("dev")) url = productDetailsDataTest.PRODUCT_DETAILS_URL_QA;
        else url = productDetailsDataTest.PRODUCT_DETAILS_URL;
        Assert.assertTrue(checkOutLoginPage.checkoutWithoutLoginOnProductList(url, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST), "Login dialog did not display");
    }
}

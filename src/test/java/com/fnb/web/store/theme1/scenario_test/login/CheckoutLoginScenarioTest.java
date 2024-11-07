package com.fnb.web.store.theme1.scenario_test.login;

import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.checkout.CheckoutDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.login.CheckOutLoginPage;
import com.fnb.web.store.theme1.pages.login.DataTest;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.product_details.ProductDetailsDataTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckoutLoginScenarioTest extends BaseTest {
    private CheckOutLoginPage checkOutLoginPage;

    @BeforeClass
    public void NavigateToProductListPage() {
        checkOutLoginPage = storePage().navigateToCheckoutLogin();
    }

    @Test(priority = 1, testName = "Verify if user checkout from checkout page without login but account exists - Login by password")
    public void FB6987() {
        Assert.assertTrue(checkOutLoginPage.checkoutWithoutLoginOnProductList(), "Login dialog did not display");
    }
}

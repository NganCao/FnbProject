package com.fnb.posapp.tests;

import com.fnb.posapp.base.BaseTest;
import com.fnb.posapp.components.LeftMenu;
import com.fnb.posapp.screens.CardDetailScreen;
import com.fnb.posapp.screens.DashboardScreen;
import com.fnb.posapp.screens.LoginScreen;
import com.fnb.posapp.screens.OrderScreen;
import com.fnb.web.admin.pages.PagesAdminSetup;
import com.fnb.web.admin.pages.product.management.CreateProductPage;
import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.posapp.base.BaseSetup.*;

public class OosTests extends BaseTest {
    protected Setup adminSetup;
    protected PagesAdminSetup adminPage;
    protected WebDriver driver() {
        return adminSetup.driver;
    }
    LoginScreen loginPage;
    DashboardScreen dashboardScreen;
    CreateProductPage createProductPage;
    CardDetailScreen cardDetailScreen;
    LeftMenu leftMenu;
    OrderScreen orderScreen;

    @BeforeClass
    public void precondition() throws AWTException, IOException {
        adminSetup = new Setup();
        adminSetup.setupDriverr("admin", "");
        adminPage = adminSetup.navigateToAdminPage();

        // Prepare data
        adminPage.navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
        createProductPage = new CreateProductPage(driver());
        createProductPage.prepareData();
    }

    @AfterClass
    public void tearDown() {
//        driver().quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginScreen(androidDriver);
        dashboardScreen = new DashboardScreen(androidDriver);
    }

    @Test(testName = "")
    public void FB() throws IOException {
        String branch = branchData.getBranch().get(0).getName();
        String category = productCategoryData.getProductCategory().get(0).getName();
        String productName = productData.getProduct().get(1).getName();
        String priceName = productData.getProduct().get(1).getPrice().get(1).getPriceName();
        String priceValue = helper.formatDoubleToString(helper.convertStringToDouble(productData.getProduct().get(1).getPrice().get(1).getPriceValue()));
        String optionName = optionData.getOptions().get(1).getName();
        String optionValue = optionData.getOptions().get(1).getOptionLevels().get(1).getName();

        loginPage.login("autostore@mailinator.com", "12345678", branch);
        dashboardScreen.selectCategory(category);
        cardDetailScreen = dashboardScreen.clickProduct(productName);
        cardDetailScreen.selectPriceName(priceName, priceValue);
        cardDetailScreen.selectOption(optionName, optionValue);
        for (int i=0; i < 10; i++) {
            cardDetailScreen.clickPlusIcon();
        }
        cardDetailScreen.clickAddToCart();

        String priceFirstValue = helper.formatDoubleToString(helper.convertStringToDouble(productData.getProduct().get(1).getPrice().get(0).getPriceValue()));
        for (int i=0; i < 10; i++) {
            dashboardScreen.clickPlusIcon_AddToCart(productName, priceFirstValue);
        }
        dashboardScreen.clickCreateOrder();

        leftMenu = dashboardScreen.clickHamburgerButton();
        orderScreen = leftMenu.clickOrderMenu();
        orderScreen.clickServingTab();
        String order = orderScreen.getFirstOrder();
        orderScreen.clickRefresh().clickTheNewEstOrder();
    }
}
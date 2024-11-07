package com.fnb.web.store.theme2.pages;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.store.theme2.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme2.pages.product_list.ProductListPage;
import com.fnb.web.store.theme2.pages.login.LoginPage;
import com.fnb.web.store.theme2.pages.product_details.EditOrderPage;
import com.fnb.web.store.theme2.pages.product_details.ProductDetailsPage;
import com.fnb.web.store.theme2.pages.home.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.fnb.web.setup.Setup.configObject;

public class CommonPagesTheme2 {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public HomePage homePage;
    public ProductDetailsPage productDetailsPage;
    public LoginPage loginPage;
    public EditOrderPage editOrderPage;
    public CheckoutPage checkoutPage;
    public ProductListPage productListPage;

    public CommonPagesTheme2(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        editOrderPage = new EditOrderPage(driver);
        checkoutPage = new CheckoutPage(driver);
        productListPage = new ProductListPage(driver);
    }
}

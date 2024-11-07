package com.fnb.web.store.theme1.pages;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.login.LoginPage;
import com.fnb.web.store.theme1.pages.product_details.EditOrderPage;
import com.fnb.web.store.theme1.pages.product_details.ProductDetailsPage;
import com.fnb.web.store.theme1.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme1.pages.product_list.ProductListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.fnb.web.setup.Setup.configObject;

public class CommonPagesTheme1 {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public LoginPage loginPage;
    public HomePage homePage;
    public ProductDetailsPage productDetailsPage;
    public ProductListPage productListPage;
    public CheckoutPage checkoutPage;
    public EditOrderPage editOrder;

    public CommonPagesTheme1(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        editOrder = new EditOrderPage(driver);
        checkoutPage = new CheckoutPage(driver);
        productListPage = new ProductListPage(driver);
    }
}

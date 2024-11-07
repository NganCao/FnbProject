package com.fnb.web.store;

import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme1.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.login.CheckOutLoginPage;
import com.fnb.web.store.theme1.pages.login.LoginPage;
import com.fnb.web.store.theme1.pages.product_list.ProductListPage;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class PagesStoreSetup extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;

    public PagesStoreSetup(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    //THEME1
    public HomePage navigateToHomePage() {
        String homeUrl = configObject.getUrlBase();
        System.out.println("Navigate to HomePage " + homeUrl);
        try {
            driver.get(homeUrl);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            driver.get(homeUrl);
        }
        return new HomePage(driver);
    }

    public LoginPage navigateToLoginTheme1() {
        String loginUrl = configObject.getUrlLogin();
        System.out.println("Navigate to Login Page " + loginUrl);
        try {
            driver.get(loginUrl);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            driver.get(loginUrl);
        }
        return new LoginPage(driver);
    }

    public ProductListPage navigateToProductListTheme1() {
        String productList = configObject.getUrlProductListStore();
        System.out.println("Navigate to Login Page " + productList);
        try {
            driver.get(productList);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            driver.get(productList);
        }
        return new ProductListPage(driver);
    }

    public CheckOutLoginPage navigateToCheckoutLogin() {
        String home = configObject.getUrlBase();
        System.out.println("Navigate to Login Page " + home);
        driver.get(baseUrl);
        return new CheckOutLoginPage(driver);
    }

    public CheckoutPage navigateToCheckoutTheme1() {
        String checkout = configObject.getUrlCheckout();
        System.out.println("Navigate to Login Page " + checkout);
        try {
            driver.get(checkout);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            driver.get(checkout);
        }
        return new CheckoutPage(driver);
    }

    //THEME2
    public com.fnb.web.store.theme2.pages.home.HomePage navigateToHomePageTheme2() {
        String homeUrl = configObject.getUrlBase();
        System.out.println("Navigate to HomePage " + homeUrl);
        try {
            driver.get(homeUrl);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            driver.get(homeUrl);
        }
        return new com.fnb.web.store.theme2.pages.home.HomePage(driver);
    }

    public com.fnb.web.store.theme2.pages.login.LoginPage navigateToLoginTheme2() {
        String loginUrl = configObject.getUrlLogin();
        System.out.println("Navigate to Login Page " + loginUrl);
        try {
            driver.get(loginUrl);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            driver.get(loginUrl);
        }
        return new com.fnb.web.store.theme2.pages.login.LoginPage(driver);
    }

    public com.fnb.web.store.theme2.pages.login.CheckOutLoginPage navigateToCheckoutLoginTheme2() {
        String productList = configObject.getUrlProductListStore();
        System.out.println("Navigate to Login Page " + productList);
        try {
            driver.get(productList);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            driver.get(productList);
        }
        return new com.fnb.web.store.theme2.pages.login.CheckOutLoginPage(driver);
    }

    public com.fnb.web.store.theme2.pages.product_list.ProductListPage navigateToProductListTheme2() {
        String productList = configObject.getUrlProductListStore();
        System.out.println("Navigate to Login Page " + productList);
        try {
            driver.get(productList);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            driver.get(productList);
        }
        return new com.fnb.web.store.theme2.pages.product_list.ProductListPage(driver);
    }

    public com.fnb.web.store.theme2.pages.checkout.CheckoutPage navigateToCheckoutTheme2() {
        String checkout = configObject.getUrlCheckout();
        System.out.println("Navigate to Login Page " + checkout);
        try {
            driver.get(checkout);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            driver.get(checkout);
        }
        return new com.fnb.web.store.theme2.pages.checkout.CheckoutPage(driver);
    }
}

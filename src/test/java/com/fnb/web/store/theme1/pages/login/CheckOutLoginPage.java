package com.fnb.web.store.theme1.pages.login;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme1.pages.checkout.CheckoutDataTest;
import com.fnb.web.store.theme1.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme1.pages.flashSale.FlashSalePage;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.product_details.ProductDetailsPage;
import com.fnb.web.store.theme1.pages.product_list.ProductListPage;
import com.fnb.web.store.theme1.pages.addUserLocation.AddUserLocationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CheckOutLoginPage extends Setup {
    private AddUserLocationPage addUserLocationPage;
    private DataTest dataTest;
    private HomeDataTest homeDataTest;
    private JsonReader jsonReader;
    public String actualRS;
    public String expectedRS;
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public static By loginBtnDialog = By.xpath("//div[contains(@class,\"confirmation-modal\")]//div[contains(@class,\"ant-modal-footer\")]//button");
    public static By loginDialog = By.xpath("//div[contains(@class,\"confirmation-modal\")]//div[contains(@class,\"ant-modal-footer\")]");

    public CheckOutLoginPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        addUserLocationPage = new AddUserLocationPage(driver);
    }

    public Boolean checkoutWithoutLoginOnProductList() {
        commonPagesTheme1().homePage.addToCartFromBestsellingProduct();
        try {
            helper.waitForJStoLoad();
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme1().homePage.cartCheckout));
            helper.clickBtn(driver.findElement(commonPagesTheme1().homePage.cartCheckout));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme1().homePage.cartCheckout));
            helper.clickByJS(driver.findElement(commonPagesTheme1().homePage.cartCheckout));
        }
        helper.waitForPresence(loginDialog);
        return helper.checkDisplayElement(loginBtnDialog);
    }

    public void checkoutWithLogin(String currentLanguage, String phone, String password, Boolean isEnterAddress, String address, int addressIndex, boolean checkout) {
        try {
            if (helper.visibleOfLocated(loginDialog)) {
                try {
                    helper.clickBtn(driver.findElement(loginBtnDialog));
                } catch (Exception ex) {
                    Log.info(ex.getMessage());
                    helper.clickByJS(driver.findElement(loginBtnDialog));
                }
                commonPagesTheme1().loginPage.submitLoginByPassword(phone, password);
            }
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            System.out.println("NO LOGIN");
        }
        if (isEnterAddress) {
            try {
                addUserLocationPage.onlyFillDeliveryAddressNoClear(address, addressIndex);
                System.out.println("address1");
                isEnterAddress = false;
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                helper.refreshPage();
                try {
                    addUserLocationPage.onlyFillDeliveryAddressNoClear(address, addressIndex);
                    System.out.println("address2");
                } catch (Exception ex) {
                    Log.info(ex.getMessage());
                }
            }
        }
        if (checkout)
            commonPagesTheme1().checkoutPage.checkoutAction(currentLanguage, isEnterAddress, address, addressIndex);
    }

    public String viewOrderAfterCheckoutWithLogin(String currentLanguage, String phone, String password, Boolean isEnterAddress, String address, int addressIndex) {
        checkoutWithLogin(currentLanguage, phone, password, isEnterAddress, address, addressIndex, true);
        try {
            helper.waitForPresence(commonPagesTheme1().checkoutPage.viewOrder);
        } catch (Exception ex) {
            Log.info(ex.getMessage());
        }
        try {
            helper.clickBtn(driver.findElement(commonPagesTheme1().checkoutPage.viewOrder));
        } catch (Exception ex) {
            Log.info(ex.getMessage());
            try {
                helper.clickByJS(driver.findElement(commonPagesTheme1().checkoutPage.viewOrder));
            } catch (Exception e) {
                Log.info(e.getMessage());
                helper.actionScrollAndClickToElement(driver.findElement(commonPagesTheme1().checkoutPage.viewOrder));
            }
        }
        System.out.println(driver.getCurrentUrl());
        if (!helper.waitForURLContains("my-profile/2/")) {
            try {
                helper.clickBtn(driver.findElement(commonPagesTheme1().checkoutPage.viewOrder));
            } catch (Exception ex) {
                Log.info(ex.getMessage());
                helper.clickByJS(driver.findElement(commonPagesTheme1().checkoutPage.viewOrder));
            }
            System.out.println("click again");
            helper.waitForURLContains("my-profile/2/");
        }
        return helper.getCurrentURL();
    }

    public void checkoutWithoutLogin(String currentLanguage, Boolean isEnterAddress, String address, int addressIndex) {
        commonPagesTheme1().checkoutPage.checkoutAction(currentLanguage, isEnterAddress, address, addressIndex);
    }
}
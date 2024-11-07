package com.fnb.web.store.theme2.pages.login;

import com.fnb.utils.helpers.ExtentTestManager;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme2.pages.addUserLocation.AddUserLocationPage;
import com.fnb.web.store.theme2.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme2.pages.home.HomePage;
import com.fnb.web.store.theme2.pages.product_details.ProductDetailsPage;
import com.fnb.web.store.theme2.pages.product_list.ProductListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckOutLoginPage extends Setup {
    public Helper helper;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    private WebDriver driver;
    private AddUserLocationPage addUserLocationPage;
    public String actualRS;
    public String expectedRS;
    public By loginBtnDialog = By.xpath("//div[@class=\"ant-modal-wrap modal_login_theme2\"]//button");
    public By loginDialog = By.xpath("//div[contains(@class,\"confirmation-modal\")]//div[contains(@class,\"ant-modal-footer\")]");

    public CheckOutLoginPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        addUserLocationPage = new AddUserLocationPage(driver);
    }

    /**
     * @param productURL   get URL to navigate to details page - will enhance after merging with admin platform
     * @param address      address
     * @param addressIndex index of address option - from 0
     * @return
     */
    public Boolean checkoutWithoutLoginOnProductList(String productURL, String address, int addressIndex) {
        helper.waitUtilElementVisible(driver.findElement(commonPagesTheme2().homePage.selectReceiver));
        addUserLocationPage.clickSelectAddress();
        addUserLocationPage.enterDeliveryAddress(address, addressIndex);
        commonPagesTheme2().productDetailsPage.navigateToProductDetailsWithURL(productURL);
        helper.waitForJStoLoad();
        helper.waitUtilElementVisible(driver.findElement(commonPagesTheme2().productDetailsPage.productHeader));
        WebElement e = driver.findElement(commonPagesTheme2().productDetailsPage.addToCartBTN);
        helper.waitForClickable(e);
        try {
            helper.clickBtn(e);
            helper.waitUtilElementVisible(helper.getElement(commonPagesTheme2().productListPage.addProductSuccessToast));
        } catch (Exception exception) {
            helper.clickByJS(e);
            helper.checkDisplayElement(commonPagesTheme2().productListPage.addProductSuccessToast);
        }
        helper.waitForJStoLoad();
        helper.pressPageUpAction();
        helper.waitForClickable(driver.findElement(commonPagesTheme2().homePage.cartIcon));
        try {
            helper.waitForClickable(driver.findElement(commonPagesTheme2().homePage.cartIcon));
            helper.clickBtn(driver.findElement(commonPagesTheme2().homePage.cartIcon));
        } catch (Exception exception) {
            helper.clickByJS(driver.findElement(commonPagesTheme2().homePage.cartIcon));
        }
        try {
            helper.waitForJStoLoad();
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme2().homePage.cartCheckout));
            helper.clickBtn(driver.findElement(commonPagesTheme2().homePage.cartCheckout));
        } catch (Exception exception) {
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme2().homePage.cartCheckout));
            helper.clickByJS(driver.findElement(commonPagesTheme2().homePage.cartCheckout));
        }
        return helper.checkDisplayElement(loginBtnDialog);
    }

    public void checkoutWithLogin(String currentLanguage, String phone, String password, Boolean isEnterAddress, String address, int addressIndex, boolean checkout) {
        if (helper.waitForPresence(loginBtnDialog)) {
            try {
                helper.waitUtilElementVisible(driver.findElement(loginDialog));
            } catch (Exception ex) {
                Log.info(ex.getMessage());
                helper.visibleOfLocated(loginBtnDialog);
            }
            try {
                helper.clickBtn(driver.findElement(loginBtnDialog));
            } catch (Exception ex) {
                Log.info(ex.getMessage());
                helper.clickByJS(driver.findElement(loginBtnDialog));
            }
            commonPagesTheme2().loginPage.submitLoginByPassword(phone, password);
        }
        if (isEnterAddress) {
            addUserLocationPage.checkDisplayOfDeliveryPanel();
            try {
                addUserLocationPage.onlyFillDeliveryAddressNoClear(address, addressIndex);
                isEnterAddress = false;
            } catch (Exception ex) {
                Log.info(ex.getMessage());
                System.out.println("No addess");
            }
        }
        if (checkout)
            commonPagesTheme2().checkoutPage.checkoutAction(currentLanguage, isEnterAddress, address, addressIndex);
    }

    public String viewOrderAfterCheckoutWithLogin(String currentLanguage, String phone, String password, Boolean isEnterAddress, String address, int addressIndex) {
        checkoutWithLogin(currentLanguage, phone, password, isEnterAddress, address, addressIndex, true);
        try {
            helper.waitForPresence(commonPagesTheme2().checkoutPage.viewOrder);
        } catch (Exception ex) {
            Log.info(ex.getMessage());
        }
        try {
            helper.clickBtn(driver.findElement(commonPagesTheme2().checkoutPage.viewOrder));
        } catch (Exception ex) {
            Log.info(ex.getMessage());
            try {
                helper.clickByJS(driver.findElement(commonPagesTheme2().checkoutPage.viewOrder));
            } catch (Exception e) {
                Log.info(e.getMessage());
                helper.actionScrollAndClickToElement(driver.findElement(commonPagesTheme2().checkoutPage.viewOrder));
            }
        }
        System.out.println(driver.getCurrentUrl());
        if (!helper.waitForURLContains("my-profile/2/")) {
            try {
                helper.clickBtn(driver.findElement(commonPagesTheme2().checkoutPage.viewOrder));
            } catch (Exception ex) {
                Log.info(ex.getMessage());
                helper.clickByJS(driver.findElement(commonPagesTheme2().checkoutPage.viewOrder));
            }
            System.out.println("click again");
            helper.waitForURLContains("my-profile/2/");
        }
        return helper.getCurrentURL();
    }

    public void checkoutWithoutLogin(String currentLanguage, Boolean isEnterAddress, String address, int addressIndex) {
        commonPagesTheme2().checkoutPage.checkoutAction(currentLanguage, isEnterAddress, address, addressIndex);
    }
}

package com.fnb.web.store.theme1.pages.myOrder;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme1.pages.checkout.CheckoutDataTest;
import com.fnb.web.store.theme1.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.my_profile.MyProfileDataTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;

public class MyOrderPage extends Setup {
    private static By cancelBtn = By.xpath("//button[contains(@class,\"order-detail-btn-cancel\")]");
    private static By reorderBtn = By.xpath("//div[contains(@class,\"order-detail-btn-re-order\")]");
    //confirm dialog
    private static By confirmModal = By.xpath("//div[contains(@class,\"confirmation-modal\")]");
    private static String confirmBTN = ".//button[2]";
    private static By toastMsg = By.xpath("//div[contains(@class,\"ant-notification-notice-message\")]");
    public String actualRS = "";
    public Helper helper;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    private WebDriver driver;
    private HomePage homePage;
    private CheckoutDataTest checkoutDataTest;
    private CheckoutPage checkoutPage;
    private JsonReader jsonReader;
    private MyProfileDataTest myProfileDataTest;
    private By confirmDialogContent = By.xpath("//div[contains(@class,\"confirmation-dialog-content\")]/span");
    private String cancelBTN = ".//button[1]";
    private By confirmButtonWithFooter = By.xpath("//div[contains(@class,\"confirmation-modal\")]//div[contains(@class,\"ant-modal-footer\")]//button");
    private By confirmButton2 = By.xpath("//div[contains(@class,\"confirmation-modal\")]//div[contains(@class,\"ant-modal-footer\")]//button[2]");
    //total
    private By subTotal = By.xpath("//div[contains(@class,\"order-detail-total-container\")]//div[contains(@class,\"order-detail-total-right\")]/b[1]");

    public MyOrderPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        homePage = new HomePage(driver);
        this.driver = driver;
    }

    public String getCurrentLanguage() {
        return helper.getCurrentLanguageHelper(homePage.languageTxt);
    }

    //order details
    public Boolean checkDisplayOfReorderBtn() {
        helper.pressEndAction();
        helper.waitForJStoLoad();
        return helper.checkDisplayElement(reorderBtn);
    }

    public Boolean cancelOrderWithURL(String url) {
        driver.navigate().to(url);
        try {
            helper.waitForPresence(homePage.footer);
            helper.scrollByJS(driver.findElement(homePage.footer));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.pressEndAction();
        }
        helper.waitForJStoLoad();
        helper.waitForPresence(cancelBtn);
        helper.visibleOfLocated(cancelBtn);
        try {
            helper.clickBtn(driver.findElement(cancelBtn));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.clickByJS(driver.findElement(cancelBtn));
        }
        helper.visibleOfLocated(confirmModal);
        helper.waitForClickable(driver.findElement(confirmModal).findElement(By.xpath(confirmBTN)));
        try {
            helper.clickBtn(driver.findElement(confirmModal).findElement(By.xpath(confirmBTN)));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.clickByJS(driver.findElement(confirmModal).findElement(By.xpath(confirmBTN)));
        }
        helper.waitForPresence(toastMsg);
        try {
            helper.pressEndAction();
            helper.waitForJStoLoad();
            helper.waitForPresence(reorderBtn);
            return helper.checkDisplayElement(reorderBtn);
        } catch (NoSuchElementException noSuchElementException) {
            Log.info(noSuchElementException.getMessage());
            return false;
        }
    }

    public CheckoutPage clickReOrderBtn(String currentLanguage) {
        clickReorderAction(currentLanguage);
        int loopNo = 3;
        while (!helper.waitForURLContains(checkoutDataTest.URL) && loopNo > 0) {
            clickReorderAction(currentLanguage);
            loopNo--;
        }
        System.out.println(helper.getCurrentUrl());
        return new CheckoutPage(driver);
    }

    private void clickReorderAction(String currentLanguage) {
        List<String> keyList = new ArrayList<>();
        keyList.add("myOrders");
        keyList.add("reOrderConfirmText");
        int loopNo = 5;
        while (helper.checkDisplayElement(reorderBtn) && loopNo > 0) {
            helper.refreshPage();
            helper.pressEndAction();
            helper.waitForJStoLoad();
            helper.waitForPresence(reorderBtn);
            helper.visibleOfLocated(reorderBtn);
            try {
                helper.clickBtn(driver.findElement(reorderBtn));
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                helper.clickByJS(driver.findElement(reorderBtn));
            }
            if (helper.checkDisplayElement(confirmModal)) {
                System.out.println(driver.findElement(confirmDialogContent).getAttribute("innerHTML"));
                System.out.println("Check dialog reorder: " + driver.findElement(confirmDialogContent).getAttribute("innerHTML").equals(jsonReader.localeReader(currentLanguage, myProfileDataTest.PAGE, keyList)));
                try {
                    helper.clickBtn(driver.findElement(confirmButton2));
                } catch (Exception clickException) {
                    Log.info(clickException.getMessage());
                    helper.clickByJS(driver.findElement(confirmButtonWithFooter));
                }
                break;
            }
            loopNo--;
        }
    }

    //total
    public Boolean checkDisplayOfSubToTal() {
        actualRS = "Subtotal did not display";
        return helper.checkDisplayElement(subTotal);
    }

    public Boolean checkValueOfSubTotal(String subtotal) {
        if (checkDisplayOfSubToTal()) return helper.checkText(driver.findElement(subTotal), subtotal);
        else {
            actualRS = "Subtotal did not display";
            return false;
        }
    }
}

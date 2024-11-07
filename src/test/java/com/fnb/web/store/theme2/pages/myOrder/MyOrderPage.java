package com.fnb.web.store.theme2.pages.myOrder;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme2.pages.checkout.CheckoutDataTest;
import com.fnb.web.store.theme2.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme2.pages.home.HomePage;
import com.fnb.web.store.theme2.pages.my_profile.MyProfileDataTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MyOrderPage extends Setup {
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
    //order management
    private By orderIDList = By.xpath("//span[contains(@class,\"my-order-code\")]");
    private static By cancelBtn = By.xpath("//button[contains(@class,\"cancel-order-by-status\")]");
    private static By reorderBtn = By.xpath("//button[contains(@class,\"btn-status-re-order\")]");
    //confirm dialog
    private static By confirmModal = By.xpath("//div[contains(@class,\"confirmation-modal\")]");
    private static String confirmBTN = ".//button[2]";
    private static By toastMsg = By.xpath("//div[contains(@class,\"ant-notification-notice-message\")]");
    private By confirmDialogContent = By.xpath("//div[contains(@class,\"confirmation-dialog-content\")]");
    private String cancelBTN = ".//button[1]";
    private By confirmButtonWithFooter = By.xpath("//div[contains(@class,\"confirmation-modal\")]//div[contains(@class,\"ant-modal-footer\")]//button");
    private By confirmButton2 = By.xpath("//div[contains(@class,\"confirmation-modal\")]//div[contains(@class,\"ant-modal-footer\")]//button[2]");
    //total
    private By subTotal = By.xpath("//div[contains(@class,\"total-order-container\")]/div[1]/div[@class=\"value\"]");

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

    //actions
    //order management
    public void clickOrderWithIndex(int index) {
        helper.presenceOfAllElementsLocatedBy(orderIDList);
        WebElement order = helper.getElementList(orderIDList).get(index);
        order.click();
    }

    public void viewFirstOrder() {
        helper.navigateTo(baseUrl + myProfileDataTest.ORDER_URL);
        clickFirstOrder();
    }

    public void clickFirstOrder() {
        helper.presenceOfAllElementsLocatedBy(orderIDList);
        WebElement order = helper.getElementList(orderIDList).get(0);
        order.click();
    }

    public Boolean cancelOrderWithURL(String url) {
        driver.navigate().to(url);
        try {
            clickFirstOrder();
        } catch (Exception exception) {
            helper.refreshPage();
            clickFirstOrder();
        }
        helper.waitForJStoLoad();
        helper.visibleOfLocated(cancelBtn);
        try {
            helper.clickBtn(driver.findElement(cancelBtn));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.clickByJS(driver.findElement(cancelBtn));
        }
        helper.visibleOfLocated(confirmModal);
        try {
            helper.waitForClickable(driver.findElement(confirmModal).findElement(By.xpath(confirmBTN)));
            helper.clickBtn(driver.findElement(confirmModal).findElement(By.xpath(confirmBTN)));
            helper.waitForPresence(toastMsg);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.clickByJS(driver.findElement(confirmModal).findElement(By.xpath(confirmBTN)));
            helper.waitForPresence(toastMsg);
        }
        try {
            helper.waitForJStoLoad();
            return helper.checkDisplayElement(reorderBtn);
        } catch (NoSuchElementException noSuchElementException) {
            Log.info(noSuchElementException.getMessage());
            return false;
        }
    }

    public CheckoutPage clickReOrderBtn(String currentLanguage) {
        clickReorderAction(currentLanguage);
        System.out.println(helper.getCurrentUrl());
        return new CheckoutPage(driver);
    }

    private void clickReorderAction(String currentLanguage) {
        List<String> keyList = new ArrayList<>();
        keyList.add("myOrders");
        keyList.add("reOrderConfirmText");
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

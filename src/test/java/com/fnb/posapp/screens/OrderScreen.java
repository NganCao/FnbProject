package com.fnb.posapp.screens;

import com.fnb.posapp.base.BaseSetup;
import com.fnb.utils.helpers.Helper;
import constants.PosAppFrameworkConstants;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.core.config.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderScreen extends BaseSetup {
    public Helper helper;
    public AndroidDriver androidDriver;
    public WebDriverWait wait;
    public Actions actions;

    public OrderScreen(AndroidDriver androidDriver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(PosAppFrameworkConstants.TIMEOUT));
        actions = new Actions(driver);
        helper = new Helper(androidDriver, wait, actions);
        this.androidDriver = androidDriver;
    }

    AppiumBy tabToConfirm = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\""+posAppLocale.getOrder().getToConfirm()+"\")");
    AppiumBy tabToServing = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\""+posAppLocale.getOrder().getProcessing()+"\")");
    AppiumBy tabCompleted = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\""+posAppLocale.getOrder().getCompleted()+"\")");
    AppiumBy tabCanceled = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\""+posAppLocale.getOrder().getCanceled()+"\")");
    AppiumBy tabDraft = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\""+posAppLocale.getOrder().getDraft()+"\")");

    public String getCardOrder() {
        return "(//android.widget.LinearLayout[\n" +
                " .//android.widget.TextView[@resource-id=\"com.gofnb.posapplication:id/txtTitleTotalBill\"] and \n" +
                " .//android.widget.TextView[@resource-id=\"com.gofnb.posapplication:id/txtTitleDish\"] and \n" +
                " .//android.widget.TextView[@resource-id=\"com.gofnb.posapplication:id/txtCode\"] and \n" +
                " not(.//android.widget.TextView[@text=\"POS Order\"])\n" +
                "])";
    }

    public OrderScreen clickToConfirmTab() {
        helper.clickElement(tabToConfirm);
        return this;
    }

    public OrderScreen clickServingTab() {
        helper.clickElement(tabToServing);
        return this;
    }

    public OrderScreen clickCompletedTab() {
        helper.clickElement(tabCompleted);
        return this;
    }

    public OrderScreen clickCanceledTab() {
        helper.clickElement(tabCanceled);
        return this;
    }

    public OrderScreen clickDraftTab() {
        helper.clickElement(tabDraft);
        return this;
    }

    public OrderScreen clickRefresh() {
        By element = By.xpath("(//android.view.ViewGroup[" +
                ".//android.view.ViewGroup[@content-desc=\"Serving\"] and .//android.view.ViewGroup[@content-desc=\"Refresh\"]])[last()]" +
                "//android.view.ViewGroup[@content-desc=\"Refresh\"]");
        helper.clickElement(element);
        return this;
    }

    public OrderDetail clickTheNewEstOrder() {
        By element = By.xpath(getCardOrder() + "[1]/*[1]");
        helper.clickElement(element);
        return new OrderDetail(driver);
    }

    public String getFirstOrder() {
        By element = By.xpath(getCardOrder() + "[1]//android.widget.TextView[@resource-id=\"com.gofnb.posapplication:id/txtCode\"]");
        return helper.getText(element);
    }
}

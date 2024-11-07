package com.fnb.posapp.components;

import com.fnb.posapp.base.BaseSetup;
import com.fnb.posapp.screens.OrderScreen;
import com.fnb.utils.helpers.Helper;
import constants.PosAppFrameworkConstants;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LeftMenu extends BaseSetup {
    public Helper helper;
    public AndroidDriver androidDriver;
    public WebDriverWait wait;
    public Actions actions;

    public LeftMenu(AndroidDriver androidDriver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(PosAppFrameworkConstants.TIMEOUT));
        actions = new Actions(driver);
        helper = new Helper(androidDriver, wait, actions);
        this.androidDriver = androidDriver;
    }

    AppiumBy mnuOrder = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\""+posAppLocale.getDashboard().getOrder()+"\")");

    public OrderScreen clickOrderMenu() {
        helper.clickElement(mnuOrder);
        return new OrderScreen(driver);
    }
}

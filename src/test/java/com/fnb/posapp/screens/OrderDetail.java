package com.fnb.posapp.screens;

import com.fnb.posapp.base.BaseSetup;
import com.fnb.utils.helpers.Helper;
import constants.PosAppFrameworkConstants;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderDetail extends BaseSetup {
    public Helper helper;
    public AndroidDriver androidDriver;
    public WebDriverWait wait;
    public Actions actions;

    public OrderDetail(AndroidDriver androidDriver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(PosAppFrameworkConstants.TIMEOUT));
        actions = new Actions(driver);
        helper = new Helper(androidDriver, wait, actions);
        this.androidDriver = androidDriver;
    }
}

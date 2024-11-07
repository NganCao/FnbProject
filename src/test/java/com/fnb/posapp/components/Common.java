package com.fnb.posapp.components;

import com.fnb.posapp.base.BaseSetup;
import com.fnb.utils.helpers.Helper;
import constants.PosAppFrameworkConstants;
import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Data
public class Common extends BaseSetup {
    public Helper helper;
    public AndroidDriver androidDriver;
    public WebDriverWait wait;
    public Actions actions;

    public Common(AndroidDriver androidDriver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(PosAppFrameworkConstants.TIMEOUT));
        actions = new Actions(driver);
        helper = new Helper(androidDriver, wait, actions);
        this.androidDriver = androidDriver;
    }

    private By btnRefresh = By.xpath("//android.view.ViewGroup[@content-desc=\""+posAppLocale.getCommon().getRefresh()+"\"]");

    public void selectCategory(String category) {
        
    }
}

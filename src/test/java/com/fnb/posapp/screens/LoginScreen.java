package com.fnb.posapp.screens;

import com.fnb.posapp.base.BaseSetup;
import com.fnb.posapp.components.Common;
import com.fnb.utils.helpers.Helper;
import constants.PosAppFrameworkConstants;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class LoginScreen extends BaseSetup {
    public Helper helper;
    public AndroidDriver androidDriver;
    public WebDriverWait wait;
    public Actions actions;
    public DashboardScreen dashboardScreen;

    public LoginScreen(AndroidDriver androidDriver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(PosAppFrameworkConstants.TIMEOUT));
        actions = new Actions(driver);
        helper = new Helper(androidDriver, wait, actions);
        this.androidDriver = androidDriver;
        dashboardScreen = new DashboardScreen(driver);
    }

    AppiumBy txtEmail = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").text(\""+posAppLocale.getLogin().getEnterUsername()+"\")");
    AppiumBy txtPassword = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").text(\""+posAppLocale.getLogin().getEnterPassword()+"\")");
    AppiumBy btnLogin = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").description(\"" + posAppLocale.getLogin().getTitle() + "\")");
    AppiumBy btnCheckInventory = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"" + posAppLocale.getInventory().getInventorychecking() + "\")");
    AppiumBy btnContinue = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").description(\"" + posAppLocale.getStartShift().getBtnContinue() + "\")");

    public void clickButtonLogin() {
        helper.clickElement(btnLogin);
    }

    public void selectBranch(String branchName) {
        By branch = By.xpath("//android.widget.TextView[@text=\""+branchName+"\"]");
        By scrollEle = By.xpath("//android.widget.ScrollView");
        swipeDownWithinElement(scrollEle, branch);
    }

    public void login(String userName, String password, String branchName) {
        helper.enterText(txtEmail, userName);
        helper.enterText(txtPassword, password);
        clickButtonLogin();
        selectBranch(branchName);
        verifyStartShift();
    }

    public void verifyStartShift() {
        boolean check = helper.isElementVisible(btnCheckInventory);
        if (check) {
            helper.clickElement(btnContinue);
            helper.waitForElementVisible(By.xpath("//android.widget.TextView[@text=\""+posAppLocale.getInventory().getWishYouhaveaSuccessfulDay()+"\"]"), "The text 'Wish you have a successful day!' is not visible");
            helper.clickElement(btnContinue);
            helper.waitForElementVisible(dashboardScreen.commonComponent.getBtnRefresh());
        }
        else {
            helper.waitForElementVisible(dashboardScreen.commonComponent.getBtnRefresh());
        }
    }

    public void swipeDownWithinElement(By by, By expectedEle) {
        WebElement element = helper.getTheVisibleElement(by);

        // Get the rectangle of the element
        Rectangle rect = element.getRect();
        int startX = rect.x + rect.width / 2;
        int startY = rect.y + (int) (rect.height * 0.8);
        int endY = rect.y + (int) (rect.height * 0.2);

        while (!helper.isElementVisible(expectedEle)) {
            HashMap<String, Object> swipeArgs = new HashMap<>();
            swipeArgs.put("elementId", ((RemoteWebElement) element).getId());
            swipeArgs.put("direction", "up");
//            swipeArgs.put("startX", startX);
//            swipeArgs.put("startY", startY);
//            swipeArgs.put("endX", startX);
//            swipeArgs.put("endY", endY);
            swipeArgs.put("percent", 0.2);

            driver.executeScript("mobile: swipeGesture", swipeArgs);
        }
        helper.clickElement(expectedEle);
    }
}

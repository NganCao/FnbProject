package com.fnb.app.posapp.autostore.pages.login;

import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageAutoStore extends BaseSetup {
    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;
    public String actualRS;
    public String branchName = "";
    private LoginDataTest loginDataTest;
    //Allow access
    @FindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    WebElement permissionController;
    //Update version
    @FindBy(xpath = "//android.widget.TextView[@text=\"Later\"]")
    WebElement laterUpdateVersionBtn;
    //Login
    //need improve with locale
    @FindBy(xpath = "//android.widget.EditText[@text=\"Enter your email\"]")
    WebElement emailPlaceHolder;
    //    @FindBy(xpath = "//android.widget.TextView[@text=\"Email\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]//android.widget.EditText")
    @FindBy(xpath = "((//android.widget.TextView[@text=\"Login\"])[1]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]//android.widget.EditText)[1]")
    WebElement emailInput;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Login, Email,  , Password,  \"]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/com.horcrux.svg.SvgView")
    WebElement emailClearIcon;
    @FindBy(xpath = "((//android.widget.TextView[@text=\"Login\"])[1]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]//android.widget.EditText)[2]")
    WebElement passwordInput;
    @FindBy(xpath = "//android.widget.EditText[contains(@text,\"•\")]")
    WebElement passwordEntered;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Remember me\"]/android.view.ViewGroup")
    WebElement rememberRadioBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Login\"]")
    WebElement loginBtn;
    //Branches
    @FindBy(xpath = "//android.widget.TextView[@text=\"" + LoginDataTest.STORE_NAME + "\"]")
    WebElement selectedStore;
    @FindBy(xpath = "//android.widget.TextView[@text=\"" + LoginDataTest.BRANCH_NAME + "\"]")
    WebElement selectedBranch;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView[1]")
    WebElement firstBranch;
    //start shift
    @FindBy(xpath = "//android.widget.TextView[@text=\"Let input initial amount to start shift\"]")
    WebElement startShiftLabel;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Continue\"]")
    WebElement continueBtn;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Start shift\"]")
    WebElement startShiftBtn;
    //dashboard
    @FindBy(id = "com.gofnb.posapplication:id/action_bar_root")
    WebElement dashboardLayout;

    public LoginPageAutoStore(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void skipPermissionController() {
        helper.sleep(1);
        try {
            permissionController.click();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
    }

    private void skipUpdateVersion() {
        helper.sleep(1);
        try {
            helper.clickBtn(laterUpdateVersionBtn);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
    }

    private void loginPOSApp(String email, String password) {
        helper.waitUtilElementVisible(emailInput);
        emailInput.clear();
        try {
            passwordEntered.clear();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        helper.enterData(emailInput, email);
        helper.enterData(passwordInput, password);
        helper.waitToElementClick(loginBtn);
    }

    private void selectBranch() {
//        if (helper.checkElementDisplay(selectedStore)) {
//            helper.clickBtn(selectedStore);
//        }
        String firstName = firstBranch.getText().trim();
        helper.sleep(2);
        try {
            scrollVerticalAndClick(loginDataTest.BRANCH_NAME);
            branchName = loginDataTest.BRANCH_NAME;
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            System.out.println("catch");
            scrollVerticalAndClick(firstName);
            branchName = firstBranch.getText();
        }
    }

    private void accessOnStartShift() {
        if (helper.checkElementDisplay(startShiftLabel)) {
            try {
                helper.waitToElementClick(continueBtn);
                try {
                    helper.sleep(1);
                    helper.clickBtn(startShiftBtn);
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                }
                try {
                    helper.sleep(1);
                    helper.clickBtn(startShiftBtn);
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
            }
        }
    }

    public Boolean checkLoginSuccessfully(String email, String password) {
        System.out.println("skip permision");
        Log.info("skip permision");
        skipPermissionController();
        System.out.println("skip update version");
        Log.info("skip update version");
        skipUpdateVersion();
        System.out.println("login: " + email + " / " + password);
        Log.info("login: " + email + " / " + password);
        loginPOSApp(email, password);
        helper.sleepHaftSec();
        System.out.println("select branch:");
        Log.info("select branch:");
        helper.sleepHaftSec();
        selectBranch();
        System.out.println(branchName);
        Log.info(branchName);
        System.out.println("start shift");
        Log.info("start shift");
        helper.sleepHaftSec();
        accessOnStartShift();
        return helper.checkElementDisplay(dashboardLayout);
    }

    //actions
    public void scroll() {

    }
}

package com.fnb.app.android.linstore.pages.loginPOS;

import com.fnb.app.setup.BaseSetup;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListPage;
import com.fnb.utils.helpers.Helper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPOSAppPage extends BaseSetup {
    private LoginPOSDataTest loginDataTest;
    private AddressManagementListPage addressManagementListPage;
    public String actualRS;
    private AndroidDriver driver;
    private WebDriverWait wait;
    private Helper helper;
    public String branchName = "";
    //Update version
    @FindBy(xpath = "//android.widget.TextView[@text=\"Later\"]")
    WebElement laterUpdateVersionBtn;
    //Login
    //need improve with locale
    @FindBy(xpath = "//android.widget.EditText[@text=\"Enter your email\"]")
    WebElement emailInput;
    @FindBy(xpath = "//android.widget.EditText[@text=\"Enter your password\"]")
    WebElement passwordInput;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Remember me\"]/android.view.ViewGroup")
    WebElement rememberRadioBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Login\"]")
    WebElement loginBtn;
    //Branches
    @FindBy(xpath = "//android.widget.TextView[@text=\"Phúc Long của Lin\"]")
    WebElement selectedStore;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Chi nhánh Automation\"]")
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
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[3]")
    WebElement accountIcon;

    public LoginPOSAppPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void skipUpdateVersion() {
        if (helper.checkElementDisplay(laterUpdateVersionBtn)) {
            helper.clickBtn(laterUpdateVersionBtn);
        }
    }

    public void scrollInScreen(WebElement elementA, WebElement elementB) {
        addressManagementListPage.swipeVertical(elementA, elementB);
    }

    private void loginPOSApp(String email, String password) {
        helper.waitUtilElementVisible(emailInput);
        helper.enterData(emailInput, email);
        helper.enterData(passwordInput, password);
        helper.waitToElementClick(loginBtn);
        helper.clickBtn(loginBtn);
    }

    private void selectBranch() {
        if (helper.checkElementDisplay(selectedStore)) {
            helper.clickBtn(selectedStore);
        }
        if (helper.checkElementDisplay(selectedBranch)) {
            helper.waitToElementClick(selectedBranch);
            scrollInScreen(firstBranch, selectedBranch);
            helper.clickBtn(selectedBranch);
            branchName = loginDataTest.BRANCH_NAME;
        } else {
            helper.waitToElementClick(firstBranch);
            helper.scrollByJS(firstBranch);
            branchName = firstBranch.getText();
            helper.clickBtn(firstBranch);
        }
        System.out.println(branchName);
    }

    private void accessOnStartShift() {
        if (helper.checkElementDisplay(startShiftLabel)) {
            helper.waitToElementClick(continueBtn);
            helper.clickBtn(continueBtn);
            helper.waitToElementClick(startShiftBtn);
        }
    }

    public Boolean checkLoginSuccessfully(String email, String password) {
        skipUpdateVersion();
        loginPOSApp(email, password);
        selectBranch();
        accessOnStartShift();
        return helper.checkElementDisplay(accountIcon);
    }
}

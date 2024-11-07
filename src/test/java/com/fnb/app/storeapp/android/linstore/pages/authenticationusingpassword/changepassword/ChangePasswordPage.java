package com.fnb.app.storeapp.android.linstore.pages.authenticationusingpassword.changepassword;

import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.fnb.app.storeapp.android.linstore.pages.authenticationusingpassword.changepassword.ChangePasswordDataTest.*;
import static com.fnb.utils.api.storeapp.pos.DataTest.*;


public class ChangePasswordPage extends BaseSetup {

    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;


    public ChangePasswordPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon4\"]")
    private WebElement profileBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"editProfileBtn\"]")
    private WebElement editProfileBtn;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"editProfileTitle\"]")
    private WebElement editProfileText;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"changePasswordField\"]")
    private WebElement changePassField;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"changePasswordFieldTitle\"]")
    private WebElement changePasswordFieldTitle;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"changePassTitle\"]")
    private WebElement changePassTitle;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"updateBtn\"]")
    private WebElement updatePassBtn;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"currentPasswordInput\"]")
    private WebElement currentPassInput;
    @FindBy(xpath = "(//android.view.ViewGroup[@resource-id=\"hidenIcon\"])[1]")
    private WebElement hidenIcon1;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"newPasswordInput\"]")
    private WebElement newPassInput;
    @FindBy(xpath = "(//android.view.ViewGroup[@resource-id=\"hidenIcon\"])[2]")
    private WebElement hidenIcon2;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"exitBtn\"]")
    private WebElement exitBtn;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"alertCurrentPass\"]")
    private WebElement currentPassAlert;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"alertNewPass\"]")
    private WebElement newPassAlert;


    public void navigateToEditProfile() {
        helper.waitToElementClick(profileBtn);
        helper.waitToElementClick(editProfileBtn);
    }
    public void clickCancelBtn(){
        helper.waitToElementClick(exitBtn);
    }

    public void navigateBack(int index) {
        for (int i = 0; i < index; i++) {
            try {
                driver.navigate().back();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String formatPass(String pass){
        return pass.replaceAll(".", "•");
    }

    public void clickExitBtn() {
        helper.waitToElementClick(exitBtn);
    }

    public void clickChangePassField() {
        helper.waitToElementClick(changePassField);
    }

    public void clickUpdatePassBtn() {
        helper.waitToElementClick(updatePassBtn);
    }

    public Boolean checkDisplayChangePassField() {
        return helper.checkElementDisplay(changePassField);
    }

    public Boolean checkDisplayChangePassTitle() {
        return helper.checkElementDisplay(changePassTitle);
    }

    public Boolean checkDisplayUpdateBtn() {
        return helper.checkElementDisplay(updatePassBtn);
    }

    public Boolean checkDisplayCurrentPassInput() {
        return helper.checkElementDisplay(currentPassInput);
    }

    public Boolean checkDisplayNewPassInput() {
        return helper.checkElementDisplay(newPassInput);
    }

    public Boolean checkDisplayAlertCurrentPass() {
        helper.waitToElementClear(currentPassInput);
        helper.waitToElementClick(updatePassBtn);
        return helper.checkElementDisplay(currentPassAlert);
    }

    public Boolean checkDisplayAlertNewPass() {
        helper.waitToElementClear(newPassInput);
        helper.waitToElementClick(newPassInput);
        return helper.checkElementDisplay(newPassAlert);
    }

    public Boolean checkDisplayHidenIconCurrentPass() {
        return helper.checkElementDisplay(newPassAlert);
    }

    public Boolean checkDisplayHidenIconNewPass() {
        helper.waitToElementClear(newPassInput);
        return helper.checkElementDisplay(newPassAlert);
    }

    public String checkTitleChangePassField() {
        return helper.waitToElementGetText(changePasswordFieldTitle);
    }

    public String checkTitleChangePass() {
        return helper.waitToElementGetText(changePassTitle);
    }

    public String checkTitleUpdateBtn() {
        return helper.waitToElementGetText(updatePassBtn);
    }

    public String checkPlaceHolderCurrentPass() {
        helper.waitToElementClear(currentPassInput);
        return helper.waitToElementGetText(currentPassInput);
    }

    public String checkPlaceHolderNewPass() {
        helper.waitToElementClear(newPassInput);
        return helper.waitToElementGetText(newPassInput);
    }

    public String checkAlertCurrentPassInput() {
        return helper.waitToElementGetText(currentPassAlert);
    }

    public String checkAlertNewPassInput() {
        return helper.waitToElementGetText(newPassAlert);
    }

    public String checkHiddenTextCurrentInput() {
        helper.waitToElementClear(currentPassInput);
        helper.waitToElementSendKey(currentPassInput, INVALID_PASS);
        return helper.waitToElementGetText(currentPassInput);
    }

    public String checkTextCurrentInput() {
        helper.waitToElementClear(currentPassInput);
        helper.waitToElementSendKey(currentPassInput, INVALID_PASS);
        helper.waitToElementClick(hidenIcon1);
        return helper.waitToElementGetText(currentPassInput);
    }

    public String checkHidenTextNewInput() {
        helper.waitToElementClear(newPassInput);
        helper.waitToElementSendKey(newPassInput, INVALID_PASS);
        return helper.waitToElementGetText(newPassInput);
    }

    public String checkTextNewInput() {
        helper.waitToElementClear(currentPassInput);
        helper.waitToElementSendKey(currentPassInput, INVALID_PASS);
        helper.waitToElementClick(hidenIcon2);
        return helper.waitToElementGetText(currentPassInput);
    }

    public void inputEmptyCurrentPass() {
        helper.waitToElementClear(currentPassInput);
        helper.waitToElementSendKey(newPassInput, PASSWORD);
        helper.waitToElementClick(updatePassBtn);
    }

    public void inputEmptyNewPass() {
        helper.waitToElementSendKey(currentPassInput, PASSWORD);
        helper.waitToElementClear(newPassInput);
        helper.waitToElementClick(updatePassBtn);
    }

    public void inputInvalidCurrentPass() {
        helper.waitToElementClear(currentPassInput);
        helper.waitToElementSendKey(currentPassInput, INVALID_PASS_1);
        helper.waitToElementSendKey(newPassInput, PASSWORD);
        helper.waitToElementClick(updatePassBtn);
    }

    public void inputInvalidNewPass() {
        helper.waitToElementClear(newPassInput);
        helper.waitToElementSendKey(newPassInput, INVALID_PASS_1);
    }

    public String alertInvalidCurrentPass() {
        helper.waitToElementClear(currentPassInput);
        helper.waitToElementSendKey(currentPassInput, INVALID_PASS_2);
        helper.waitToElementSendKey(newPassInput, PASSWORD);
        helper.waitToElementClick(updatePassBtn);
        return helper.waitToElementGetText(currentPassAlert);
    }

    public String alertInvalidNewPass() {
        helper.waitToElementSendKey(currentPassInput, INVALID_PASS_3);
        helper.waitToElementClear(newPassInput);
        helper.waitToElementSendKey(newPassInput, INVALID_PASS_2);
        helper.waitToElementClick(updatePassBtn);
        return helper.waitToElementGetText(newPassAlert);
    }

    public void updatePassword(String currentPass, String newPass) {
        helper.waitToElementClear(currentPassInput);
        helper.waitToElementSendKey(currentPassInput, currentPass);
        helper.waitToElementClear(newPassInput);
        helper.waitToElementSendKey(newPassInput, newPass);
    }
}

package com.fnb.app.storeapp.android.linstore.pages.authenticationusingpassword.updateinforaccount;

import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.Duration;
import java.util.Random;

import static com.fnb.app.storeapp.android.linstore.pages.authenticationusingpassword.updateinforaccount.UpdateInfoAccountDataTest.*;

public class UpdateInfoAccountPage extends BaseSetup {

    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;


    public UpdateInfoAccountPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    Random random = new Random();
    int randomNumber = random.nextInt(99) + 1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon4\"]")
    private WebElement profileBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"editProfileBtn\"]")
    private WebElement editProfileBtn;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"editProfileTitle\"]")
    private WebElement editProfileTitle;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"exitBtn\"]")
    private WebElement exitBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"avatarImg\"]")
    private WebElement avatarImg;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"uploadIcon\"]")
    private WebElement uploadIcon;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"saveBtn\"]")
    private WebElement saveBtn;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"firstNameField\"]")
    private WebElement firstNameField;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"lastNameField\"]")
    private WebElement lastNameField;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"countryCode\"]")
    private WebElement countryCode;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"phoneInputField\"]")
    private WebElement phoneInputField;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"emailInput\"]")
    private WebElement emailInput;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"dateOfBirthFieldText\"]")
    private WebElement dateOfBirthFieldText;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"dateOfBirthField\"]")
    private WebElement dateOfBirthField;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"genderOptions\"]")
    private WebElement genderOptions;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"genderOptionsTitle\"]")
    private WebElement genderOptionsTitle;
    @FindBy(xpath = "//android.widget.RadioButton[@content-desc=\"Nữ\"]")
    private WebElement femaleOptions;
    @FindBy(xpath = "//android.widget.RadioButton[@content-desc=\"Nam\"]")
    private WebElement maleOptions;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherOptions\"]")
    private WebElement otherOptions;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"changePasswordField\"]")
    private WebElement changePasswordField;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"deleteAccountField\"]")
    private WebElement deleteAccountField;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"firstNameAlert\"]")
    private WebElement firstNameAlert;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"phoneInputAlert\"]")
    private WebElement phoneInputAlert;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"emailInputAlert\"]")
    private WebElement emailInputAlert;
    @FindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button1\"]")
    private WebElement confirmButton;
    @FindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button2\"]")
    private WebElement cancelButton;

    public void navigateToUpdateInfo() {
        try {
            Thread.sleep(5000);
            helper.waitToElementClick(profileBtn);
            helper.waitToElementClick(editProfileBtn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void inputData() {
        helper.waitToElementClear(firstNameField);
        helper.waitToElementClear(phoneInputField);
        helper.waitToElementSendKey(emailInput, INVALID_EMAIL);
        helper.waitToElementClick(saveBtn);
    }

    public Boolean checkDisplayExitBtn() {
        return helper.checkElementDisplay(exitBtn);
    }

    public Boolean checkDisplayAvatar() {
        return helper.checkElementDisplay(avatarImg);
    }

    public Boolean checkDisplayUpload() {
        return helper.checkElementDisplay(uploadIcon);
    }

    public Boolean checkDisplayFirstName() {
        return helper.checkElementDisplay(firstNameField);
    }

    public Boolean checkDisplayAlertFirstName() {
        return helper.checkElementDisplay(firstNameAlert);
    }

    public Boolean checkDisplayLastName() {
        return helper.checkElementDisplay(lastNameField);
    }

    public Boolean checkDisplayCountryCode() {
        return helper.checkElementDisplay(countryCode);
    }

    public Boolean checkDisplayPhoneInput() {
        return helper.checkElementDisplay(phoneInputField);
    }

    public Boolean checkDisplayAlertPhoneInput() {
        return helper.checkElementDisplay(phoneInputAlert);
    }

    public Boolean checkDisplayEmail() {
        return helper.checkElementDisplay(emailInput);
    }

    public Boolean checkDisplayAlertEmail() {
        return helper.checkElementDisplay(emailInputAlert);
    }

    public Boolean checkDisplayDateOfBirth() {
        return helper.checkElementDisplay(dateOfBirthFieldText);
    }

    public Boolean checkDisplayGenderOption() {
        return helper.checkElementDisplay(genderOptions);
    }

    public String getTextEmailInputAlert(){
        return helper.waitToElementGetText(emailInputAlert);
    }

    public void updateInfo() {
        String firstName = USER_NAME + randomNumber;
        helper.waitToElementSendKey(firstNameField, firstName);
        helper.waitToElementSendKey(lastNameField, LAST_NAME);
        helper.waitToElementSendKey(phoneInputField, PHONE_DEFAULT);
        helper.waitToElementClear(emailInput);
        helper.waitToElementClick(saveBtn);
    }

    public String inputLongFirstName() {
        helper.waitToElementClear(firstNameField);
        helper.waitToElementSendKey(firstNameField, LONG_TEXT);
        return helper.waitToElementGetText(firstNameField);
    }

    public String inputLongLastName() {
        helper.waitToElementClear(lastNameField);
        helper.waitToElementSendKey(lastNameField, LONG_TEXT);
        return helper.waitToElementGetText(lastNameField);
    }

    public String inputInvalidEmail(){
        helper.waitToElementClear(emailInput);
        helper.waitToElementSendKey(emailInput, INVALID_EMAIL);
        helper.waitToElementClick(saveBtn);
        return helper.waitToElementGetText(emailInputAlert);
    }

    public void inputValidEmail(){
        helper.waitToElementClear(emailInput);
        helper.waitToElementSendKey(emailInput, VALID_EMAIL);
    }
    public void clickCancelInDateOfBirthField(){
        helper.waitToElementClick(dateOfBirthField);
        helper.waitToElementClick(cancelButton);
    }
    public void clickConfirmButtonInDateOfBirth(){
        helper.waitToElementClick(dateOfBirthField);
        helper.waitToElementClick(confirmButton);
    }
    public String getTextDateOfBirthField(){
        return helper.waitToElementGetText(dateOfBirthFieldText);
    }

    public void clickFemaleButton(){
        helper.waitToElementClick(femaleOptions);
    }

    public void clickMaleButton(){
        helper.waitToElementClick(maleOptions);
    }

    public String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
    }

    public void clickSaveBtn(){
        helper.waitToElementClick(saveBtn);
    }

    public void changePhoneNumber() {
        try {
            helper.waitToElementClear(phoneInputField);
            helper.waitToElementSendKey(phoneInputField, PHONE_NUMBER_1);
            helper.waitToElementClick(saveBtn);
            Thread.sleep(3000);
            driver.navigate().back();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

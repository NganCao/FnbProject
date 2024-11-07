package com.fnb.app.storeapp.android.linstore.pages.register;

import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPageLinStore extends BaseSetup {

    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;


    public RegisterPageLinStore(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon4\"]")
    private WebElement profileBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"loginSuccessToastMessage\"]")
    private WebElement toastMess;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"UserProfile5\"]")
    private WebElement loginProfileBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"loginwithOtpBtn\"]")
    private WebElement loginWithOtpBtn;
    @FindBy(xpath = "(//android.widget.TextView[@text=\"Đăng nhập\"])[2]")
    private WebElement loginTitle;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"countryCode\"]")
    private WebElement countryCode;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"phoneInput\"]")
    private WebElement phone;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"loginOtpBtn\"]")
    private WebElement loginBtnWithOTP;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"alertPhoneNotFill\"]")
    private WebElement phoneNotFillAlert;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"TitleHeader\"]")
    private WebElement registerAccountTitle;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"body\"]")
    private WebElement registerTitle;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"firstNameRegisterField\"]")
    private WebElement firstName;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AlertFirstName\"]")
    private WebElement alertFirstName;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"lastNameRegisterField\"]")
    private WebElement lastName;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"passwordRegisterField\"]")
    private WebElement pass;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AlertPassword\"]")
    private WebElement alertPass;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"hidenIcon\"]")
    private WebElement hidenIcon;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"emailRegisterField\"]")
    private WebElement email;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AlertEmail\"]")
    private WebElement alertEmail;
    @FindBy(xpath = "//android.view.View[@resource-id=\"radioButtons\"]")
    private WebElement genderRadioGroup;
//    @FindBy(xpath = "//android.widget.RadioButton[@resource-id=\"2\"]")
    @FindBy(xpath = "//android.widget.RadioButton[@content-desc=\"Nữ\"]")
    private WebElement femaleBtn;
    @FindBy(xpath = "//android.widget.RadioButton[@content-desc=\"Nam\"]")
    private WebElement maleBtn;
    @FindBy(xpath = "//android.widget.RadioButton[@content-desc=\"Khác\"]")
    private WebElement otherBtn;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"birthdayField\"]")
    private WebElement dayOfBirth;
    @FindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button1\"]")
    private WebElement confirmBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"createAccountBtn\"]")
    private WebElement createAccountBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"UserProfile4\"]")
    private WebElement logout;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"acceptBtn\"]")
    private WebElement acceptBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ruduceIcon\"]")
    private WebElement reduceIcon;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"plusIcon\"]")
    private WebElement plusIcon;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"registerLink\"]")
    private WebElement registerLink;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"registerPhoneBtn\"]")
    private WebElement registerPhoneBtn;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"registerPhoneText\"]")
    private WebElement registerPhoneText;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"deliveryHeader\"]")
    private WebElement deliveryBtn;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"backBtn\"]")
    private WebElement backBtn;

    public void navigateToLoginScreen() {
        helper.waitToElementClick(profileBtn);
        helper.waitToElementClick(loginProfileBtn);
    }

    public void navigateToLoginWithOTP(String phoneNumber) {
        helper.waitToElementClick(loginWithOtpBtn);
        helper.waitToElementClick(loginBtnWithOTP);
        helper.waitToElementClick(phone);
        helper.waitToElementSendKey(phone, phoneNumber);
        helper.waitToElementClick(loginBtnWithOTP);

    }

    public void navigateToRegisterLink(String phoneNumber) throws InterruptedException {
        helper.waitToElementClick(registerLink);
        Thread.sleep(2000);
        helper.waitToElementClick(phone);
        helper.waitToElementSendKey(phone, phoneNumber);
        helper.waitToElementClick(registerPhoneBtn);
    }

    public void navigateToDeliveryBtn() {
        helper.waitToElementClick(deliveryBtn);
    }

    public void inputNewPhoneNumberRegister(String phoneNumber) {
        helper.waitToElementClear(phone);
        helper.waitToElementSendKey(phone, phoneNumber);
        helper.waitToElementClick(registerPhoneBtn);
    }

    public Boolean checkDisplayRegisterAccountTitle() {
        return helper.checkElementDisplay(registerAccountTitle);
    }

    public Boolean checkDisplayRegisterTitle() {
        return helper.checkElementDisplay(registerTitle);
    }

    public Boolean checkDisplayRegisterText() {
        return helper.checkElementDisplay(registerPhoneText);
    }

    public Boolean checkDisplayFirstName() {
        return helper.checkElementDisplay(firstName);
    }

    public Boolean checkDisplayLastName() {
        return helper.checkElementDisplay(lastName);
    }

    public Boolean checkDisplayPass() {
        return helper.checkElementDisplay(pass);
    }

    public Boolean checkDisplayEmail() {
        return helper.checkElementDisplay(email);
    }

    public Boolean checkDisplayGenderRadio() {
        return helper.checkElementDisplay(genderRadioGroup);
    }

    public Boolean checkDisplayFemaleBtn() {
        return helper.checkElementDisplay(femaleBtn);
    }

    public Boolean checkDisplayMaleBtn() {
        return helper.checkElementDisplay(maleBtn);
    }

    public Boolean checkDisplayOtherBtn() {
        return helper.checkElementDisplay(otherBtn);
    }

    public Boolean checkDisplayDayOfBirth() {
        return helper.checkElementDisplay(dayOfBirth);
    }

    public Boolean checkDisplayCreateAccountBtn() {
        return helper.checkElementDisplay(createAccountBtn);
    }
    public Boolean checkDisplayToastMess() {
        return helper.checkElementDisplay(toastMess);
    }

    public void verifyValidInfo(String name, String passWord) {
        helper.waitToElementClear(firstName);
        helper.waitToElementSendKey(firstName, name);
        helper.waitToElementClear(pass);
        helper.waitToElementSendKey(pass, passWord);
        helper.waitToElementClear(email);
        helper.waitToElementClick(createAccountBtn);
    }

    public void verifyLogoutSuccess() {
        helper.waitToElementClick(profileBtn);
        helper.waitToElementClick(logout);
        helper.waitToElementClick(acceptBtn);
    }

    public void clickBackBtn(){
        try {
            Thread.sleep(2000);
            helper.waitToElementClick(backBtn);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public String generatePhoneNumberData() {
        return helper.phoneNumberData();
    }

    public void verifyFirstName(String name) {
        helper.waitToElementSendKey(firstName, name);
    }

    public String verifyFirstNameNotFill() {
        helper.waitToElementClear(firstName);
        helper.waitToElementClick(createAccountBtn);
        return helper.waitToElementGetText(alertFirstName);
    }

    public void verifyPassNotFill() {
        helper.waitToElementClick(pass);
        helper.waitToElementClear(pass);
        driver.navigate().back();
        helper.waitToElementClick(createAccountBtn);
    }

    public void verifyInvalidPass(String passWord) {
        helper.waitToElementSendKey(pass, passWord);
        helper.waitToElementClick(createAccountBtn);
    }

    public void verifyInvalidEmail(String emailInput) {
        helper.waitToElementClear(email);
        email.sendKeys(emailInput);
    }

    public String verifyInputLastName() {
        helper.waitToElementClear(lastName);
        lastName.sendKeys("Pham");
        return lastName.getText();
    }

    public Boolean verifyCheckGenderOptions() {
        helper.waitToElement(genderRadioGroup);
        helper.waitToElementClick(femaleBtn);
        return femaleBtn.getAttribute("checked").equals("true");
    }

    public Boolean verifyCheckMaleOptions() {
        helper.waitToElementClick(maleBtn);
        return wait.until(ExpectedConditions.attributeToBe(maleBtn, "checked", "true"));
    }

    public Boolean verifyCheckOthersOptions() {
        helper.waitToElementClick(otherBtn);
        return wait.until(ExpectedConditions.attributeToBe(otherBtn, "checked", "true"));
    }

    public Boolean verifyDateOfBirth() {
        helper.waitToElementClick(dayOfBirth);
        helper.waitToElementClick(confirmBtn);
        return wait.until(ExpectedConditions.attributeToBe(dayOfBirth, "checked", "true"));
    }

    public void verifyInputNameAndPass() {
        helper.waitToElementSendKey(firstName, "Wills");
        helper.waitToElementSendKey(pass, "123456");
        helper.waitToElementClick(createAccountBtn);
    }

    public String verifyAlertEmptyPass() {
        return helper.waitToElementGetText(alertPass);
    }

    public String verifyAlertInvalidPass() {
        return helper.waitToElementGetText(alertPass);
    }

    public String verifyAlertInvalidEmail() {
        return helper.waitToElementGetText(alertEmail);
    }


}

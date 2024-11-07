package com.fnb.app.storeapp.android.linstore.pages.login;

import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.api.storeapp.pos.Post_Login_Request;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import io.appium.java_client.android.AndroidDriver;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.fnb.app.storeapp.android.linstore.pages.login.LoginDataTest.FILE_PATH;
import static com.fnb.utils.api.storeapp.pos.DataTest.*;
import static com.fnb.utils.api.storeapp.pos.Post_Login_Request.checkApiLoginSuccess;

public class LoginPageLinStore extends BaseSetup {

    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;


    public LoginPageLinStore(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    Post_Login_Request login_request = new Post_Login_Request();
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon4\"]")
    private WebElement profileBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"UserProfile5\"]")
    private WebElement loginProfileBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"countryCode\"]")
    private WebElement countryCode;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"loginTitle\"]")
    private WebElement loginTitle;
    @FindBy(xpath = "//android.widget.EditText[@resource-id='phoneInput']")
    private WebElement phone;
    @FindBy(xpath = "//android.widget.EditText[@resource-id='passwordInput']")
    private WebElement pass;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"hidenIcon\"]")
    private WebElement hidenIcon;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"loginBtn\"]")
    private WebElement loginBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"loginwithOtpBtn\"]")
    private WebElement loginWithOtpBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"UserProfile4\"]")
    private WebElement logout;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"acceptBtn\"]")
    private WebElement acceptBtn;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"alertPhoneNotFill\"]")
    private WebElement phoneNotFillAlert;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"alertPassNotFill\"]")
    private WebElement passNotFillAlert;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
    private WebElement productsListBtn;
    @FindBy(xpath = "(//android.view.ViewGroup[@resource-id=\"ProductItem00\"])")
    private WebElement productItem;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"AddProductToCart\"]")
    private WebElement AddProductToCart;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"CartIcon\"]")
    private WebElement cart;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"order\"]")
    private WebElement order;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"deliveryHeader\"]")
    private WebElement deliveryBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ReduceIcon\"]")
    private WebElement reduceIcon;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"PlusIcon\"]")
    private WebElement plusIcon;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"registerLink\"]")
    private WebElement registerLink;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"CheckoutButton\"]")
    private WebElement CheckoutButton;
    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")
    private WebElement AcceptBtn;



    public String getTextRegister() {
        return helper.waitToElementGetText(registerLink);
    }

    public void splashScreen() {
        Log.info("splash screen...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitToLoadAPI() {
        Log.info("Wait to loading api...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void navigateToLoginScreen() {
        helper.waitToElementClick(profileBtn);
        helper.waitToElementClick(loginProfileBtn);
    }

    public void navigateToLoginSuccess() {
        helper.waitToElementClick(profileBtn);
        helper.waitToElementClick(loginProfileBtn);
        helper.waitToElementSendKey(phone, PHONE_NUMBER);
        helper.waitToElementSendKey(pass, PASSWORD);
        helper.waitToElementClick(loginBtn);
    }

    public void loginSuccess() {
        helper.waitToElementSendKey(phone, PHONE_NUMBER);
        helper.waitToElementSendKey(pass, PASSWORD);
        helper.waitToElementClick(loginBtn);
    }

    public void AcceptNotification(){
        helper.waitToElementClick(AcceptBtn);
    }

    public void navigateToDeliveryBtn() {
        helper.waitToElementClick(deliveryBtn);
    }

    public void navigateToCheckoutPage() {
        helper.waitToElementClick(productsListBtn);
        helper.waitToElementClick(productItem);
        helper.waitToElementClick(AddProductToCart);
        helper.waitToElementClick(cart);
        helper.waitToElementClick(order);
    }
    public void clickCheckoutButton(){
        helper.waitToElementClick(CheckoutButton);
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void clickLoginBtn() {
        helper.waitToElementClick(loginBtn);
    }

    public Boolean checkDisplayProfileBtn() {
        return helper.checkElementDisplay(profileBtn);
    }

    public Boolean checkDisplayCountryCode() {
        return helper.checkElementDisplay(countryCode);
    }

    public Boolean checkDisplayPhoneNumber() {
        return helper.checkElementDisplay(phone);
    }

    public Boolean checkDisplayLoginTitle() {
        return helper.checkElementDisplay(loginTitle);
    }

    public Boolean checkDisplayPass() {
        return helper.checkElementDisplay(pass);
    }

    public Boolean checkDisplayLoginWithOTP() {
        return helper.checkElementDisplay(loginWithOtpBtn);
    }

    public Boolean checkDisplayHiddenIcon() {
        return helper.checkElementDisplay(hidenIcon);
    }

    public Boolean checkDisplayLoginBtn() {
        return helper.checkElementDisplay(loginBtn);
    }

    public Boolean checkDisplayAlertPhoneNotFill() {
        return helper.checkElementDisplay(phoneNotFillAlert);
    }

    public Boolean checkDisplayAlertPassNotFill() {
        return helper.checkElementDisplay(passNotFillAlert);
    }

    public void verifyLoginSuccess(String phoneNumber, String password) {
        helper.waitToElementSendKey(phone, phoneNumber);
        helper.waitToElementSendKey(pass, password);
        helper.waitToElementClick(loginBtn);
    }

    public String verifyToastMessDisplay() {
        return helper.getLoginSuccessToast();
    }

    public void verifyLogoutSuccess() {
        helper.waitToElementClick(profileBtn);
        helper.waitToElementClick(logout);
        helper.waitToElementClick(acceptBtn);
    }
    public void clickAcceptBtn(){
        try {
            helper.waitToElementClick(AcceptBtn);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("can not click accept notification");
            Log.info("can not click accept notification");
            e.printStackTrace();
        }
    }

    public String verifyAlertPhoneNotFill() {
        return helper.waitToElementGetText(phoneNotFillAlert);
    }

    public String verifyAlertPassNotFill() {
        return helper.waitToElementGetText(passNotFillAlert);
    }

    public String verifyAlerWrongInfoLogin() {
        return helper.waitToElementGetText(passNotFillAlert);
    }

    public void verifyEmptyPhone() {
        helper.waitToElement(phone);
        helper.waitToElementClick(loginBtn);
    }

    public void verifyEmptyPass() {
        helper.waitToElement(phone);
        helper.waitToElementClick(loginBtn);
    }

    public void verifyWrongPhone(String phoneNumber) {
        helper.waitToElementSendKey(phone, phoneNumber);
        helper.waitToElementClick(loginBtn);
    }

    public void verifyWrongPass(String phoneNumber, String password) {
        helper.waitToElementSendKey(phone, phoneNumber);
        helper.waitToElementSendKey(pass, password);
        helper.waitToElementClick(loginBtn);
    }

    public void verifyPhoneNotRegister(String phoneNumber, String password) {
        helper.waitToElementSendKey(phone, phoneNumber);
        helper.waitToElementSendKey(pass, password);
        helper.waitToElementClick(loginBtn);
    }

    public void clearData() {
        helper.waitToElement(phone);
        phone.clear();
        helper.waitToElement(pass);
        pass.clear();
    }

    public void goToCart() {
        helper.waitToElementClick(cart);
    }

    public void decreaseCombo(int clickTimes){
        for (int i = 0; i < clickTimes; i++) {
            try {
                helper.waitToElementLocatedClick(reduceIcon);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void decreaseCombo1(int clickTimes) {
        try {
            By by = By.xpath("//android.view.ViewGroup[@resource-id=\"ruduceIcon\"]");
            for (int i = 0; i < clickTimes; i++) {
                wait.until(ExpectedConditions.presenceOfElementLocated(by));
                driver.findElement(by).click();
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void writeApiLoginSuccess() {
        response = checkApiLoginSuccess(PHONE_NUMBER, PHONE_CODE, PASSWORD, STORE_ID, BRANCH_ID);
        String data = response.asPrettyString();
        helper.writeFile(FILE_PATH, data);
    }

    public boolean checkApiLoginIsSuccess() {
        response = checkApiLoginSuccess(PHONE_NUMBER, PHONE_CODE, PASSWORD, STORE_ID, BRANCH_ID);
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.getBoolean("isSuccess");
    }

    public String checkApiPhoneNumber() {
        response = checkApiLoginSuccess(PHONE_NUMBER, PHONE_CODE, PASSWORD, STORE_ID, BRANCH_ID);
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.getString("data.customerInfo.phoneNumber");
    }
}



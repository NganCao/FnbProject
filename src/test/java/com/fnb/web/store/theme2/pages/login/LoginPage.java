package com.fnb.web.store.theme2.pages.login;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.HelperDataFaker;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme2.pages.home.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends Setup {
    private WebDriver driver;
    private HomePage homePage;
    private DataTest dataTest;
    private JsonReader jsonReader;
    private String currentLanguage = "Vi";
    public String actualRS = "";
    public String expectedRS = "";
    public static String currentWindow;
    private int failed = 0;
    //------------------- XPATH - located element
    private By backgroundImg = By.xpath("//div[@class=\"login-page-wrapper\"]");
    private By loginTitle = By.xpath("//div[contains(@class,\"login_title\")]");
    private By phoneIcon = By.xpath("//img[@class=\"login_phone_icon\"]");
    private By phoneNumber = By.xpath("//span[contains(@class,\"login_phone\")]/input");
    //    private By phoneNumberOTP = By.id("txtPhone");
    private By clearPhoneNumber = By.xpath("//span[contains(@class,\"anticon-close-circle\")]");
    private By loginBTN = By.xpath("//div[@class=\"login_button_box\"]/button");
    private By loginByPassword = By.xpath("//div[contains(@class,\"ant-tabs-nav-list\")]/div[1]");
    private By loginByOTP = By.xpath("//div[contains(@class,\"ant-tabs-nav-list\")]/div[2]");
    private By countryCode = By.xpath("//div[contains(@class,\"ant-select-show-arrow\")]");
    private By countryList = By.xpath("//div[contains(@class,\"rc-virtual-list-holder-inner\")]");
    private By countryListItem = By.xpath("//div[contains(@class,\"login_country_popup\")]//div[contains(@class,\"ant-select-item ant-select-item-option\")]");
    private By countryScrollThumbnail = By.xpath("//div[@class=\"rc-virtual-list-scrollbar-thumb\"]/parent::div");
    //-------------------LOGIN BY PASSWORD
    private By password = By.id("password");
    private By lockIcon = By.xpath("//img[@class=\"lock_icon\"]");
    private By eyeIcon = By.xpath("//div[@class=\"ant-input-password-icon\"]/*[name()=\"svg\"]"); //open pwd => stroke-linejoin="round"
    private By eyeIconPath2 = By.xpath("//div[@class=\"ant-input-password-icon\"]//*[name()=\"path\"][2]");
    private By phoneNumberHelp = By.id("userName_help");
    private By phoneNumberRequiredOTP = By.xpath("//div[contains(@class,\"login_phone_error\")]");
    private By passwordHelp = By.id("password_help");
    private By passwordWrongMsg = By.xpath("//span[@class=\"wrong-phone-or-password-text-theme2\"]");
    private By passwordWrongToastMsg = By.xpath("//div[@class=\"ant-message-notice-content\"]//span[2]");
    //LOGIN BY OTP
    private By loginOtpErrMsg = By.xpath("//div[contains(@class,\"login_otp_error\")]");
    private By otpFields = By.xpath("//div[@class=\"login_otp_numbers\"]");
    private By otpInput = By.xpath("//input[starts-with(@id,\"txtN\")]");
    private By verificationTitle = By.xpath("//div[@class=\"login_otp_title1\"]");
    private By verificationNotificationMsg = By.xpath("//div[@class=\"login_otp_title2\"]");
    private By verificationPhoneNumberMsg = By.xpath("//div[@class=\"login_otp_title3\"]");
    private By verificationResendOtpDes = By.xpath("//div[@class=\"login_otp_resend_title\"]");
    private By verificationResendBTN = By.xpath("//button[contains(@class,\"login_button_resend\")]");

    public Helper helper;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;

    public LoginPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    public String getCurrentLanguage() {
        helper.waitForPresence(commonPagesTheme2().homePage.languageTxt);
        helper.visibleOfLocated(commonPagesTheme2().homePage.languageTxt);
        return helper.getCurrentLanguageHelper(commonPagesTheme2().homePage.languageTxt);
    }

    public void refreshPage() {
        helper.refreshPage();
    }

    public void navigateToLogin() {
        driver.navigate().to(configObject.getUrlLogin());
    }

    public Boolean checkURL() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        expectedRS = dataTest.URL;
        actualRS = driver.getCurrentUrl();
        return helper.checkURL(dataTest.URL);
    }

    public Boolean checkBackgroundDisplay() {
        return helper.checkDisplayElement(backgroundImg);
    }

    public Boolean checkDisplayLoginByPasswordTab() {
        return helper.checkDisplayElement(loginByPassword);
    }

    public Boolean checkDisplayLoginByOTPTab() {
        return helper.checkDisplayElement(loginByOTP);
    }

    // Check display of element on Login by password
    public Boolean checkDisplayCountryCode() {
        return helper.checkDisplayElement(countryCode);
    }

    public Boolean checkDisplayPhoneNumber() {
        return helper.checkDisplayElement(phoneNumber);
    }

    public Boolean checkDisplayIconPhone() {
        return helper.checkDisplayElement(phoneIcon);
    }

    public Boolean checkDisplayPassword() {
        return helper.checkDisplayElement(password);
    }

    public Boolean checkDisplayIconLock() {
        return helper.checkDisplayElement(lockIcon);
    }

    public Boolean checkDisplayTitle() {
        return helper.checkDisplayElement(loginTitle);
    }

    public Boolean checkDisplayEyeIcon() {
        return helper.checkDisplayElement(eyeIcon);
    }

    public Boolean checkDisplayLoginBTN() {
        return helper.checkDisplayElement(loginBTN);
    }

    public Boolean checkDisplayCountryCodeList() {
        try {
            helper.clickBtn(driver.findElement(countryCode));
            String s = driver.findElement(countryCode).toString();
            Log.info("click on Country code" + s);
            helper.waitUtilElementVisible(driver.findElement(countryList));
            return (!driver.findElement(countryList).getAttribute("class").contains("ant-select-dropdown-hidden"));
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            return false;
        }

    }

    public Boolean checkDisplayCountryScroll() {
        return !driver.findElement(countryScrollThumbnail).getCssValue("visibility").equals("hidden");
    }

    public Boolean checkDisplayVerificationTitle() {
        return helper.checkDisplayElement(verificationTitle);
    }

    public Boolean checkDisplayVerificationDescription() {
        return helper.checkDisplayElement(verificationNotificationMsg);
    }

    public Boolean checkDisplayVerificationPhone() {
        return helper.checkDisplayElement(verificationPhoneNumberMsg);
    }

    public Boolean checkDisplayVerificationResendNotification() {
        return helper.checkDisplayElement(verificationNotificationMsg);
    }

    public Boolean checkDisplayVerificationResendDescription() {
        return helper.checkDisplayElement(verificationResendOtpDes);
    }

    public Boolean checkDisplayVerificationResend() {
        return helper.checkDisplayElement(verificationResendBTN);
    }

    public Boolean checkDisplayVerificationInputParent() {
        return helper.checkDisplayElement(otpFields);
    }

    public Boolean checkNumberVerificationOTPInput() {
        List<WebElement> list = helper.getElementList(otpInput);
        if (list.size() == 6) {
            return true;
        } else {
            expectedRS = "OTP has 6 inputs";
            actualRS = "OTP has " + list.size() + " inputs";
            return false;
        }
    }

    public Boolean checkDisplayRequiredPhoneNumberMsg() {
        return helper.checkDisplayElement(phoneNumberHelp);
    }

    public Boolean checkDisplayRequiredPhoneNumberMsgOTP() {
        return helper.checkDisplayElement(phoneNumberRequiredOTP);
    }

    public Boolean checkDisplayInvalidPhoneNumberMsg() {
        return helper.checkDisplayElement(phoneNumberHelp);
    }

    public Boolean checkDisplayInvalidPhoneNumberMsgOTP() {
        return helper.checkDisplayElement(phoneNumberRequiredOTP);
    }

    public Boolean checkDisplayRequiredPasswordMsg() {
        return helper.checkDisplayElement(passwordHelp);
    }

    public Boolean checkDisplayWrongLoginMsg() {
        return helper.checkDisplayElement(passwordWrongMsg);
    }

    public Boolean checkDefaultValueCountryCode() {
        helper.waitForPresence(countryCode);
        helper.visibleOfLocated(countryCode);
        actualRS = driver.findElement(countryCode).getText();
        helper.waitForTextToBe(driver.findElement(countryCode), dataTest.DEFAULT_COUNTRY_CODE);
        return driver.findElement(countryCode).getText().trim().equals(dataTest.DEFAULT_COUNTRY_CODE);
    }

    public Boolean checkDisplayDefaultLanguage() {
        return helper.checkDisplayDefaultLanguage(commonPagesTheme2().homePage.languageTxt, dataTest.DEFAULT_LANGUAGE);
    }

    public Boolean checkDisplayStoreLogo() {
        return helper.checkDisplayElement(commonPagesTheme2().homePage.storeLogo);
    }

    public Boolean checkMaximumPhoneNumberOTP() {
        actualRS = driver.findElement(phoneNumber).getAttribute("maxlength");
        return driver.findElement(phoneNumber).getAttribute("maxlength").equals(dataTest.MAXIMUM_LENGTH_PHONENUMBER);
    }

    public Boolean checkDefaultTypePassword() {
        actualRS = driver.findElement(password).getAttribute("type");
        return driver.findElement(password).getAttribute("type").equals(dataTest.DEFAULT_TYPE_PASSWORD);
    }

    public Boolean checkDefaultEyeIcon() {
        try {
            return driver.findElement(eyeIconPath2).getAttribute("d").contains(dataTest.OPEN_EYE_ICON_D);
        } catch (Exception exception) {
            return false;
        }
    }

    public Boolean checkTypePasswordOpenEyeIcon() {
        try {
            clickEyeIcon();
            actualRS = driver.findElement(password).getAttribute("type");
            return driver.findElement(password).getAttribute("type").equals(dataTest.CHANGE_TYPE_PASSWORD);
        } catch (Exception exception) {
            return false;
        }
    }

    public Boolean checkTypePasswordCloseEyeIcon() {
        try {
            clickEyeIcon();
            return driver.findElement(password).getAttribute("type").equals(dataTest.DEFAULT_TYPE_PASSWORD);
        } catch (Exception exception) {
            return false;
        }
    }

    public Boolean checkMinimumPassword(int length) {
        helper.waitForPresence(password);
        helper.visibleOfLocated(password);
        WebElement e = driver.findElement(password);
        String pwd = "";
        boolean rs = false;
        if (length > 0 && length < 6) {
            pwd = HelperDataFaker.generatePasswordRange(1, length);
            helper.enterData(e, pwd);
            helper.waitUtilElementVisible(driver.findElement(passwordHelp));
            return helper.checkDisplayElement(passwordHelp);
        } else {
            helper.enterData(e, dataTest.WRONG_PASSWORD);
            if (helper.checkDisplayElement(phoneNumberRequiredOTP) == true) {
                return false;
            } else {
                return true;
            }
        }
    }

    private void clickEyeIcon() {
        helper.waitUtilElementVisible(driver.findElement(eyeIcon));
        driver.findElement(eyeIcon).click();
    }

    public Boolean clickLoginByOTP() {
        helper.waitForJStoLoad();
        try {
            helper.waitForPresence(loginByOTP);
            helper.visibleOfLocated(loginByOTP);
            helper.waitUtilElementVisible(driver.findElement(loginByOTP));
            helper.clickBtn(driver.findElement(loginByOTP));
        } catch (Exception e) {
            Log.info(e.getMessage());
            helper.waitUtilElementVisible(driver.findElement(loginByOTP));
            helper.clickBtn(driver.findElement(loginByOTP));
        }
        expectedRS = dataTest.LOGIN_METHOD_ACTIVE;
        try {
            actualRS = driver.findElement(loginByOTP).getAttribute("class");
        } catch (Exception exception) {
            helper.refreshPage();
            helper.waitForPresence(loginByOTP);
            helper.visibleOfLocated(loginByOTP);
            helper.clickBtn(driver.findElement(loginByOTP));
            actualRS = driver.findElement(loginByOTP).getAttribute("class");
        }
        return actualRS.contains(expectedRS);
    }

    public Boolean clickLoginByPassword() {
        helper.waitForPresence(commonPagesTheme2().homePage.accountIcon);
        helper.visibleOfLocated(commonPagesTheme2().homePage.accountIcon);
        helper.waitUtilElementVisible(driver.findElement(loginByPassword));
        driver.findElement(loginByPassword).click();
        expectedRS = dataTest.LOGIN_METHOD_ACTIVE;
        actualRS = driver.findElement(loginByPassword).getAttribute("class");
        return actualRS.contains(expectedRS);
    }

    public void submitLoginByOTP(String phoneNumberData) {
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        helper.ctrlADeleteAction(driver.findElement(phoneNumber));
        helper.enterData(driver.findElement(phoneNumber), phoneNumberData);
        helper.clickBtn(driver.findElement(loginBTN));
    }

    //TODO feature for submit OTP
    public void submitOtp(String otp) {
        List<WebElement> otpInput = driver.findElements(otpFields);
        if (otp.length() == 6) {
            otpInput.get(0).sendKeys(otp);
        } else {
            System.out.println("Data input did not enough 6 character.");
        }
    }

    public void submitLoginByPassword(String phoneNumberData, String passwordData) {
        helper.waitForPresence(phoneNumber);
        helper.visibleOfLocated(phoneNumber);
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        helper.enterData(driver.findElement(phoneNumber), phoneNumberData);
        helper.enterData(driver.findElement(password), passwordData);
        helper.clickBtn(driver.findElement(loginBTN));
    }

    public Boolean clickLoginWithoutAllData() {
        helper.waitForJStoLoad();
        helper.waitForPresence(loginBTN);
        helper.visibleOfLocated(loginBTN);
        helper.clickBtn(driver.findElement(loginBTN));
        return helper.checkDisplayElement(phoneNumberHelp);
    }

    public Boolean clickLoginWithoutAllDataOTP() {
        helper.waitForJStoLoad();
        helper.waitForPresence(loginBTN);
        helper.visibleOfLocated(loginBTN);
        helper.clickBtn(driver.findElement(loginBTN));
        return checkDisplayRequiredPhoneNumberMsgOTP();
    }

    public Boolean typeBlankPhoneNumberWithoutClickLogin() {
        helper.waitForPresence(phoneNumber);
        helper.visibleOfLocated(phoneNumber);
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        helper.pressEnterAction(driver.findElement(phoneNumber));
        return helper.checkDisplayElement(phoneNumberHelp);
    }

    public Boolean typeBlankPhoneNumberWithoutClickLoginOTP() {
        helper.waitForPresence(phoneNumber);
        helper.visibleOfLocated(phoneNumber);
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        helper.pressEnterAction(driver.findElement(phoneNumber));
        return checkDisplayRequiredPhoneNumberMsgOTP();
    }

    public Boolean typeInvalidPhoneNumberWithoutClickLogin() {
        helper.waitForPresence(phoneNumber);
        helper.visibleOfLocated(phoneNumber);
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        helper.addingInnerJs(driver.findElement(phoneNumber), dataTest.INVALID_PHONENUMBER);
        helper.pressEnterAction(driver.findElement(phoneNumber));
        return checkDisplayInvalidPhoneNumberMsg();
    }

    public Boolean typeInvalidPhoneNumberWithoutClickLoginOTP() {
        helper.waitForPresence(phoneNumber);
        helper.visibleOfLocated(phoneNumber);
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        helper.addingInnerJs(driver.findElement(phoneNumber), dataTest.INVALID_PHONENUMBER);
        helper.pressEnterAction(driver.findElement(phoneNumber));
        return checkDisplayInvalidPhoneNumberMsgOTP();
    }

    public Boolean clickLoginWithInvalidPhoneNumber() {
        helper.waitForPresence(phoneNumber);
        helper.visibleOfLocated(phoneNumber);
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        helper.enterData(driver.findElement(phoneNumber), dataTest.INVALID_PHONENUMBER);
        helper.clickBtn(driver.findElement(loginBTN));
        return helper.checkDisplayElement(phoneNumberHelp);
    }

    public Boolean clickLoginWithInvalidPhoneNumberOTP() {
        helper.waitForPresence(phoneNumber);
        helper.visibleOfLocated(phoneNumber);
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        helper.enterData(driver.findElement(phoneNumber), dataTest.INVALID_PHONENUMBER);
        helper.clickBtn(driver.findElement(loginBTN));
        return checkDisplayInvalidPhoneNumberMsgOTP();
    }

    public Boolean typeBlankPasswordWithoutClickLogin() {
        helper.waitForPresence(password);
        helper.visibleOfLocated(password);
        helper.waitUtilElementVisible(driver.findElement(password));
        helper.enterData(driver.findElement(password), "a");
        helper.ctrlADeleteAction(driver.findElement(password));
        helper.waitUtilElementVisible(driver.findElement(passwordHelp));
        return checkDisplayRequiredPasswordMsg();
    }

    public Boolean checkDisplayRequiredPassword() {
        helper.waitForPresence(password);
        helper.visibleOfLocated(password);
        helper.waitUtilElementVisible(driver.findElement(password));
        helper.enterData(driver.findElement(phoneNumber), dataTest.PHONE_DATA);
        helper.clickBtn(driver.findElement(loginBTN));
        helper.waitUtilElementVisible(driver.findElement(passwordHelp));
        return checkDisplayRequiredPasswordMsg();
    }

    public Boolean clickLoginWithWrongPwd() {
        submitLoginByPassword(dataTest.PHONE_DATA, dataTest.WRONG_PASSWORD);
        try {
            helper.waitUtilElementVisible(driver.findElement(passwordWrongToastMsg));
            actualRS = driver.findElement(passwordWrongToastMsg).getText();
            if (actualRS.equals(dataTest.INCORRECT_TOAST_MESSAGE)) {
                actualRS = "Wrong message toast did not equal " + dataTest.INCORRECT_TOAST_MESSAGE;
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            actualRS = "Wrong message toast did not display";
            return false;
        }
    }

    public Boolean loginPasswordSuccessfully(String phone, String password) {
        submitLoginByPassword(phone, password);
        try {
            helper.waitForPresence(commonPagesTheme2().homePage.bannerTrack);
            helper.visibleOfLocated(commonPagesTheme2().homePage.bannerTrack);
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme2().homePage.bannerTrack));
            commonPagesTheme2().homePage.clickAccountIcon();
            return helper.visibleOfLocated(commonPagesTheme2().homePage.accountInfo);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    //TODO feature for submit wrong OTP
    public Boolean checkDisplayOtpErrMsg() {
        return helper.checkDisplayElement(loginOtpErrMsg);
    }

    //TODO feature for change country code
    public void changeCountryCode(String code, String country) {
        String xpath = "//span[text()=\"" + country
                + "\"]";
        driver.findElement(countryCode).click();
        helper.waitUtilElementVisible(driver.findElement(countryList));
        helper.scrollToElementByJS(driver.findElement(countryList));
    }

    //Check function of change language method

    /**
     * Create a general method to check UI when user changes language
     *
     * @param language // Vietnamese / English
     * @param element  // name of element
     * @param type     // text / placeholder : define type to get property
     * @param xpath
     * @param expected
     */
    private void commonCheck(String language, String element, String type, By xpath, String expected) {
        Boolean result = false;
        System.out.println("Verify the display of " + element);
        WebElement e = driver.findElement(xpath);
        if (type.equals("text")) {
            result = helper.checkText(e, expected);
        } else if (type.equals("placeholder")) {
            result = helper.checkPlaceHolder(e, expected);
        }
        if (result = false) {
            System.out.println("FAILED! Actual" + element + " is wrong: " + e.getText());
            failed++;
        } else {
            result = false;
            System.out.println(language + " - " + element + ": PASSED");
        }
    }

    private void checkTitleFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("login");
        commonCheck(language, "title", "text", loginTitle, jsonReader.localeReader(language
                , dataTest.PAGE, keyList));
    }

    private void checkPhoneNumberFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("enterYourPhoneNumber");
        commonCheck(language, "phoneNumber place-holder", "placeholder", phoneNumber
                , jsonReader.localeReader(language, dataTest.PAGE, keyList));
    }

    private void checkPasswordFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("enterYourPassword");
        commonCheck(language, "password place-holder", "placeholder", password
                , jsonReader.localeReader(language, dataTest.PAGE, keyList));
    }

    private void checkLoginBTNFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("login");
        commonCheck(language, "login", "text", loginBTN
                , jsonReader.localeReader(language, dataTest.PAGE, keyList));
    }

    private void checkLoginByOTPFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("loginByOTP");
        commonCheck(language, "login by OTP", "text", loginByOTP
                , jsonReader.localeReader(language, dataTest.PAGE, keyList));
    }

    private void checkLoginByPasswordFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("loginByPassword");
        commonCheck(language, "login by Password", "text", loginByPassword
                , jsonReader.localeReader(language, dataTest.PAGE, keyList));
    }

    private void checkDataLanguagePassword() {
        checkTitleFollowLanguage(currentLanguage);
        checkPhoneNumberFollowLanguage(currentLanguage);
        checkPasswordFollowLanguage(currentLanguage);
        checkLoginBTNFollowLanguage(currentLanguage);
        checkLoginByOTPFollowLanguage(currentLanguage);
    }

    private void checkDataLanguageOTP() {
        checkTitleFollowLanguage(currentLanguage);
        checkPhoneNumberFollowLanguage(currentLanguage);
        checkLoginBTNFollowLanguage(currentLanguage);
        checkLoginByPasswordFollowLanguage(currentLanguage);
    }

    private void checkRequiredPhoneNumberFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("enterYourPhoneNumber");
        commonCheck(language, "check Required Phonenumber", "text", loginTitle, jsonReader.localeReader(language
                , dataTest.PAGE, keyList));
    }

    private void checkRequiredPasswordFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("enterYourPassword");
        commonCheck(language, "check Required Password", "text", password, jsonReader.localeReader(language
                , dataTest.PAGE, keyList));
    }

    private void checkInvalidPhoneNumberFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("enterYourPhoneNumber");
        commonCheck(language, "check Invalid PhoneNumber", "text", phoneNumberHelp, jsonReader.localeReader(language
                , dataTest.PAGE, keyList));
    }

    private void checkIncorrectMessageFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("enterWrongPhoneOrPassword");
        commonCheck(language, "check Incorrect Message", "text", passwordWrongMsg, jsonReader.localeReader(language
                , dataTest.PAGE, keyList));
        keyList.clear();
        keyList.add("Your user name or password incorrect");
        commonCheck(language, "check Incorrect Toast", "text", passwordWrongToastMsg, jsonReader.localeReader(language
                , "", keyList));
        helper.waitInvisibleByLocated(passwordWrongToastMsg);
    }

    private void checkInvalidPasswordFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("enterYourPassword");
        commonCheck(language, "check Invalid Password", "text", loginTitle, jsonReader.localeReader(language
                , dataTest.PAGE, keyList));
    }

    private void checkAllMessageFollowLanguage() {
        // Blank/empty phone number + password
        if (clickLoginWithoutAllData() == true) {
            checkRequiredPhoneNumberFollowLanguage(currentLanguage);
            checkRequiredPasswordFollowLanguage(currentLanguage);
        } else {
            System.out.println("FAILED because all required message did not display");
        }
        refreshPage();
        //invalid phone number
        if (typeInvalidPhoneNumberWithoutClickLogin() == true) {
            checkInvalidPhoneNumberFollowLanguage(currentLanguage);
        } else {
            System.out.println("FAILED because Invalid phonenumber message did not display");
        }
        //invalid password
        if (checkMinimumPassword(5) == true) {
            checkInvalidPasswordFollowLanguage(currentLanguage);
        } else {
            System.out.println("FAILED because Invalid phonenumber message did not display");
        }
        refreshPage();
        //login unsuccessfully
        if (clickLoginWithWrongPwd() == true) {
            checkIncorrectMessageFollowLanguage(currentLanguage); //Except toast message because it has not multiple languages
        } else {
            System.out.println("FAILED because Invalid phonenumber message did not display");
        }
    }

    private String checkCurrentLanguage() {
        currentLanguage = getCurrentLanguage();
        String language = "";
        if (currentLanguage.toLowerCase().equals("Vi")) {
            language = "Vietnamese";
            System.out.println("Verify UI when language is Vietnamese");
        } else {
            language = "English";
            System.out.println("Verify UI when language is English");
        }
        return language;
    }

    private void checkUIFollowLanguageSteps() {
        checkDataLanguagePassword();
        refreshPage();
        clickLoginByOTP();
        checkDataLanguageOTP();
        refreshPage();
        checkAllMessageFollowLanguage();
    }

    /**
     * if one language check failed, this method will fail and won't continue check another language
     *
     * @return true / false
     */
    public Boolean checkChangeLanguage() {
        String language = checkCurrentLanguage();
        checkUIFollowLanguageSteps();
        if (failed > 0) {
            System.out.println("Verify UI when " + language + " is FAILED!");
            failed = 0;
            return false;
        } else {
            //Change to other language
            helper.changeMethodLanguage(commonPagesTheme2().homePage.languageSwitch, commonPagesTheme2().homePage.languageTxt, commonPagesTheme2().homePage.vietnameseOption, commonPagesTheme2().homePage.englishOption);
            language = checkCurrentLanguage();
            checkUIFollowLanguageSteps();
            if (failed > 0) {
                System.out.println("Verify UI when " + language + " is FAILED!");
                failed = 0;
                return false;
            } else {
                return true;
            }
        }
    }

    private void checkVerificationFollowLanguage() {
        List<String> keyList = new ArrayList<>();
        keyList.add("verification");
        commonCheck(currentLanguage, "Verififcation title", "text", verificationTitle
                , jsonReader.localeReader(currentLanguage, dataTest.PAGE, keyList));
        keyList.clear();
        keyList.add("otpSendToYourPhone");
        commonCheck(currentLanguage, "Verififcation Notification", "text", verificationNotificationMsg
                , jsonReader.localeReader(currentLanguage, dataTest.PAGE, keyList));
        keyList.clear();
        keyList.add("didNotReceiveOTP");
        commonCheck(currentLanguage, "Verififcation title", "text", verificationResendOtpDes
                , jsonReader.localeReader(currentLanguage, dataTest.PAGE, keyList));
        keyList.clear();
        keyList.add("reSend");
        commonCheck(currentLanguage, "Verififcation title", "text", verificationTitle
                , jsonReader.localeReader(currentLanguage, dataTest.PAGE, keyList));
    }

    public Boolean checkVerificationLanguage() {
        String language = checkCurrentLanguage();
        try {
            checkVerificationFollowLanguage();
            if (failed > 0) {
                System.out.println("Verify UI when " + language + " is FAILED!");
                failed = 0;
                return false;
            } else {
                //Change to other language
                helper.pressPageUpAction();
                helper.changeMethodLanguage(commonPagesTheme2().homePage.languageSwitch, commonPagesTheme2().homePage.languageTxt, commonPagesTheme2().homePage.vietnameseOption, commonPagesTheme2().homePage.englishOption);
                language = checkCurrentLanguage();
                checkVerificationFollowLanguage();
                if (failed > 0) {
                    System.out.println("Verify UI when " + language + " is FAILED!");
                    failed = 0;
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception exception) {
            actualRS = "OTP did not work. It showed error.\n" + exception.getMessage();
            Log.info(actualRS);
            return false;
        }
    }

    public void getCurrentWindow() {
        currentWindow = helper.getCurrentWindow();
    }

    public Boolean openNewTabAfterLoginPassed(String url, String page) {
        helper.closeAllOpenTabExceptMainTab(currentWindow);
        if (page.contains("login")) {
            helper.openNewTab(url + dataTest.URL);
        } else {
            helper.openNewTab(url);
        }
        try {
            helper.waitForPresence(commonPagesTheme2().homePage.accountIcon);
            helper.visibleOfLocated(commonPagesTheme2().homePage.accountIcon);
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme2().homePage.accountIcon));
            commonPagesTheme2().homePage.clickAccountIcon();
        } catch (Exception e) {
            Log.error(e.getMessage());
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme2().homePage.accountIcon));
            commonPagesTheme2().homePage.clickAccountIcon();
        }
        helper.waitForPresence(commonPagesTheme2().homePage.accountInfo);
        helper.visibleOfLocated(commonPagesTheme2().homePage.accountInfo);
        Boolean checkAccountInfo = helper.checkDisplayElement(commonPagesTheme2().homePage.accountInfo);
        Boolean checkURL = helper.checkURL(dataTest.URL); //Check contains login
        if (checkAccountInfo == true && checkURL == false) {
            return true;
        } else {
            if (checkAccountInfo == false) {
                actualRS = "Account Information did not display.";
            } else if (checkURL == true) {
                actualRS = "Page did not automatically navigate to Home page/Dashboard";
            } else {
                actualRS = "Account Information did not display.\nPage did not automatically navigate to Home page/Dashboard";
            }
            return false;
        }
    }

    /**
     * Method to check if the last login case is success -> logout
     */
    public void checkLoginIfError() {
        commonPagesTheme2().homePage.clickAccountIcon();
        if (helper.checkDisplayElement(commonPagesTheme2().homePage.accountLogout) == true) {
            helper.clickBtn(driver.findElement(commonPagesTheme2().homePage.accountLogout));
        }
    }
}

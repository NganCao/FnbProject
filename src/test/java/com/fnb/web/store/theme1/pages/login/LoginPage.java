package com.fnb.web.store.theme1.pages.login;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.HelperDataFaker;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme1.pages.home.HomePage;
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
    private JsonReader jsonReader;
    private DataTest dataTest;
    private String currentLanguage = "Vi";
    public String actualRS = "";
    public String expectedRS = "";
    public static String currentWindow;
    private int failed = 0;
    //------------------- XPATH - located element
    private By backgroundImg = By.xpath("//div[contains(@class,\"login_page_theme1_container user-select-none-for-admin\")]");
    private By loginTitle = By.xpath("//div[contains(@class,\"login_title\")]");
    private By phoneNumber = By.id("phoneNumber");
    private By clearPhoneNumber = By.xpath("//span[contains(@class,\"anticon-close-circle\")]");
    private By loginBTN = By.xpath("//button[contains(@class,\"login-button\")]");
    private By changeLoginMethod = By.xpath("//div[contains(@class,\"text-change-method-login\")]");
    private By countryCode = By.xpath("//div[contains(@class,\"ant-select-show-arrow\")]//span[contains(@class,\"ant-select-selection-item\")]");
    private By countryList = By.xpath("//div[contains(@class,\"rc-virtual-list-holder-inner\")]");
    private By countryListItem = By.xpath("//div[contains(@class,\"login_country_popup\")]//div[contains(@class,\"ant-select-item ant-select-item-option\")]");
    private By countryScrollThumbnail = By.xpath("//div[@class=\"rc-virtual-list-scrollbar-thumb\"]/parent::div");
    //-------------------LOGIN BY PASSWORD
    private By password = By.id("password");
    private By eyeIcon = By.xpath("//a[contains(@class,\"ant-input-password-icon\")]/*[name()=\"svg\"]"); //open pwd => stroke-linejoin="round"
    private By eyeIconPath2 = By.xpath("//a[contains(@class,\"ant-input-password-icon\")]/*[name()=\"svg\"]//*[name()=\"path\"][2]");
    //-------------------Msg
    private By phoneNumberHelp = By.xpath("//div[@id=\"phoneNumber_help\"]/div");
    private By passwordHelp = By.xpath("//div[@id=\"password_help\"]/div");
    private By passwordWrongMsg = By.xpath("//div[@class=\"error-message\"]");
    private By passwordWrongToastMsg = By.xpath("//div[@class=\"ant-message-notice-content\"]//span[2]");
    //LOGIN BY OTP
    private By loginOTPFirebaseErrorMsg = By.xpath("//div[contains(@class,\"login_firebase_error\")]");
    private By loginOtpErrMsg = By.xpath("//div[@class=\"login_otp_error\"]");
    private By backIcon = By.xpath("//img[contains(@class,\"login_back\")]");
    private By otpFields = By.id("otp");
    private By otpInput1 = By.xpath("//div[@id=\"otp\"]/input"); //6 items
    private By verificationTitle = By.xpath("//div[@class=\"login_otp_title1\"]");
    private By verificationNotificationMsg = By.xpath("//div[@class=\"login_otp_title2\"]");
    private By verificationPhoneNumberMsg = By.xpath("//div[@class=\"login_otp_title3\"]");
    private By verificationResendOtpDes = By.xpath("//div[@class=\"login_otp_resend_title\"]");
    private By verificationResendBTN = By.xpath("//div[@class=\"login_otp_resend1\"]");

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

    public static LoginPage createInstance() {
        // You can control the object creation process here
        // Maybe perform some checks or initialization steps
        return new LoginPage(createInstance().getDriver());
    }

    public String getCurrentLanguage() {
        helper.pressPageUpAction();
        helper.waitForPresence(commonPagesTheme1().homePage.languageTxt);
        helper.waitForPresence(commonPagesTheme1().homePage.languageTxt);
        return helper.getCurrentLanguageHelper(commonPagesTheme1().homePage.languageTxt);
    }

    public void navigateToLogin() {
        driver.navigate().to(configObject.getUrlLogin());
    }

    public void refreshPage() {
        helper.refreshPage();
    }

    public Boolean checkURL() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        expectedRS = dataTest.URL;
        actualRS = driver.getCurrentUrl();
        return helper.checkURL(dataTest.URL);
    }

    // Check display of element on Login by password
    public Boolean checkDisplayCountryCode() {
        return helper.checkDisplayElement(countryCode);
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

    public Boolean checkDisplayPhoneNumber() {
        return helper.checkDisplayElement(phoneNumber);
    }

    public Boolean checkDisplayPassword() {
        return helper.checkDisplayElement(password);
    }

    public Boolean checkDisplayTitle() {
        return helper.checkDisplayElement(loginTitle);
    }

    public Boolean checkDisplayEyeIcon() {
        return helper.checkDisplayElement(eyeIcon);
    }

    public Boolean checkDisplayChangeLoginMethhod() {
        return helper.checkDisplayElement(changeLoginMethod);
    }

    public Boolean checkDisplayLoginBTN() {
        return helper.checkDisplayElement(loginBTN);
    }

    public Boolean checkDisplayRequiredPhoneNumberMsg() {
        return helper.checkDisplayElement(phoneNumberHelp);
    }

    public Boolean checkDisplayInvalidPhoneNumberMsg() {
        return helper.checkDisplayElement(phoneNumberHelp);
    }

    public Boolean checkDisplayRequiredPasswordMsg() {
        return helper.checkDisplayElement(passwordHelp);
    }

    public Boolean checkDisplayWrongLoginMsg() {
        helper.waitForPresence(passwordWrongMsg);
        return helper.checkDisplayElement(passwordWrongMsg);
    }

    //check value default
    public Boolean checkDefaultValueCountryCode() {
        helper.waitForPresence(countryCode);
        actualRS = driver.findElement(countryCode).getText();
        helper.waitForTextToBe(driver.findElement(countryCode), dataTest.DEFAULT_COUNTRY_CODE);
        return driver.findElement(countryCode).getText().trim().equals(dataTest.DEFAULT_COUNTRY_CODE);
    }

    public Boolean checkDisplayDefaultLanguage() {
        return helper.checkDisplayDefaultLanguage(commonPagesTheme1().homePage.languageTxt, dataTest.DEFAULT_LANGUAGE);
    }

    public Boolean checkDisplayStoreLogo() {
        return helper.checkDisplayElement(commonPagesTheme1().homePage.storeLogo);
    }

    public Boolean checkMaximumPhoneNumber() {
        actualRS = driver.findElement(phoneNumber).getAttribute("maxlength").toString();
        return driver.findElement(phoneNumber).getAttribute("maxlength").equals(dataTest.MAXIMUM_LENGTH_PHONENUMBER);
    }

    public Boolean checkMinimumPassword(int length) {
        helper.waitForPresence(password);
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
            if (helper.checkDisplayElement(passwordHelp)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public Boolean checkInvalidPhoneNumber_12Numbers() {
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        WebElement phoneField = driver.findElement(phoneNumber);
        phoneField.sendKeys(dataTest.INVALID_MORE_12_NUMBERS);
        return checkDisplayInvalidPhoneNumberMsg();
    }

    public Boolean checkDefaultTypePassword() {
        actualRS = driver.findElement(password).getAttribute("type");
        return driver.findElement(password).getAttribute("type").equals(dataTest.DEFAULT_TYPE_PASSWORD);
    }

    public Boolean checkDefaultEyeIcon() {
        try {
            return driver.findElement(eyeIconPath2).getAttribute("d").contains(dataTest.OPEN_EYE_ICON_D);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            return false;
        }
    }

    public Boolean checkBackgroundDisplay() {
        return helper.checkDisplayElement(backgroundImg);
    }

    // Check display of element on Login by OTP
    public Boolean checkDisplayBackIcon() {
        return helper.checkDisplayElement(backIcon);
    }

    public Boolean checkDisplayVerificationTitle() {
        return helper.checkDisplayElement(verificationTitle);
    }

    public Boolean checkDisplayVerificationDes() {
        return helper.checkDisplayElement(verificationNotificationMsg);
    }

    public Boolean checkDisplayVerificationPhoneNumberMsg() {
        return helper.checkDisplayElement(verificationPhoneNumberMsg);
    }

    public Boolean checkDisplayVerificationResendDes() {
        return helper.checkDisplayElement(verificationResendOtpDes);
    }

    public Boolean checkDisplayVerificationResendCTA() {
        return helper.checkDisplayElement(verificationResendBTN);
    }

    public Boolean checkDisplayOtpInputParent() {
        return helper.checkDisplayElement(otpFields);
    }

    public Boolean checkNumberVerificationOtpInput() {
        List<WebElement> list = helper.getElementList(otpInput1);
        if (list.size() == 6) {
            return true;
        } else {
            expectedRS = "OTP has 6 inputs";
            actualRS = "OTP has " + list.size() + " inputs";
            return false;
        }
    }

    public void clickEyeIcon() {
        helper.waitUtilElementVisible(driver.findElement(eyeIcon));
        driver.findElement(eyeIcon).click();
    }

    public Boolean checkTypePasswordOpenEyeIcon() {
        try {
            clickEyeIcon();
            actualRS = driver.findElement(password).getAttribute("type");
            return driver.findElement(password).getAttribute("type").equals(dataTest.CHANGE_TYPE_PASSWORD);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            return false;
        }
    }

    public Boolean checkTypePasswordCloseEyeIcon() {
        try {
            clickEyeIcon();
            return driver.findElement(password).getAttribute("type").equals(dataTest.DEFAULT_TYPE_PASSWORD);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            return false;
        }
    }

    public Boolean clickLoginByOTP() {
        List<String> keyList = new ArrayList<>();
        keyList.add("loginByPassword");
        clickChangeLoginMethod();
        expectedRS = jsonReader.localeReader(getCurrentLanguage(), dataTest.PAGE, keyList);
        actualRS = driver.findElement(changeLoginMethod).getText();
        helper.waitUtilElementVisible(driver.findElement(changeLoginMethod));
        return driver.findElement(changeLoginMethod).getText().equals(expectedRS);
    }

    public Boolean clickLoginByPassword() {
        List<String> keyList = new ArrayList<>();
        keyList.add("loginByPhone");
        clickChangeLoginMethod();
        expectedRS = jsonReader.localeReader(getCurrentLanguage(), "loginPage", keyList);
        actualRS = driver.findElement(changeLoginMethod).getText();
        helper.waitUtilElementVisible(driver.findElement(changeLoginMethod));
        return driver.findElement(changeLoginMethod).getText().equals(expectedRS);
    }

    public void clickChangeLoginMethod() {
        helper.waitForPresence(changeLoginMethod);
        helper.visibleOfLocated(changeLoginMethod);
        WebElement e = driver.findElement(changeLoginMethod);
        helper.waitUtilElementVisible(e);
        helper.clickBtn(e);
    }

    public Boolean clickBackVerification() {
        helper.visibleOfLocated(backIcon);
        WebElement e = driver.findElement(backIcon);
        helper.clickBtn(e);
        Boolean check = checkDisplayPassword();
        if (check == true) {
            actualRS = "Password field displayed";
            return false;
        } else return true;
    }

    public void submitLoginByOTP(String phoneNumberData) {
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        helper.ctrlADeleteAction(driver.findElement(phoneNumber));
        helper.enterData(driver.findElement(phoneNumber), phoneNumberData);
        helper.clickBtn(driver.findElement(loginBTN));
    }

    //TODO feature for submit OTP
    public void submitOtp(String otp) {
        List<WebElement> otpInput = driver.findElements(otpInput1);
        if (otp.length() == 6) {
            otpInput.get(0).sendKeys(otp);
        } else {
            System.out.println("Data input did not enough 6 character.");
        }
    }

    public void submitLoginByPassword(String phoneNumberData, String passwordData) {
        helper.waitForPresence(phoneNumber);
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        helper.enterData(driver.findElement(phoneNumber), phoneNumberData);
        helper.enterData(driver.findElement(password), passwordData);
        helper.clickBtn(driver.findElement(loginBTN));
    }

    public Boolean clickLoginWithoutAllData() {
        helper.waitForPresence(loginBTN);
        helper.waitUtilElementVisible(driver.findElement(loginBTN));
        helper.clickBtn(driver.findElement(loginBTN));
        helper.waitUtilElementVisible(driver.findElement(phoneNumberHelp));
        return checkDisplayRequiredPhoneNumberMsg();
    }

    public Boolean typeBlankPhoneNumberWithoutClickLogin() {
        helper.waitForPresence(phoneNumber);
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        helper.enterData(driver.findElement(phoneNumber), "1");
        helper.clickBtn(driver.findElement(clearPhoneNumber));
        return helper.checkDisplayElement(phoneNumberHelp);
    }

    public Boolean typeInvalidPhoneNumberWithoutClickLogin() {
        helper.waitForPresence(phoneNumber);
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        helper.addingInnerJs(driver.findElement(phoneNumber), dataTest.INVALID_PHONENUMBER);
        helper.enterData(driver.findElement(phoneNumber), "1");
        helper.waitUtilElementVisible(driver.findElement(phoneNumberHelp));
        return checkDisplayInvalidPhoneNumberMsg();
    }

    public Boolean clickLoginWithInvalidPhoneNumber() {
        helper.waitForPresence(phoneNumber);
        helper.waitUtilElementVisible(driver.findElement(phoneNumber));
        helper.enterData(driver.findElement(phoneNumber), dataTest.INVALID_PHONENUMBER);
        helper.clickBtn(driver.findElement(loginBTN));
        helper.waitUtilElementVisible(driver.findElement(phoneNumberHelp));
        return checkDisplayInvalidPhoneNumberMsg();
    }

    public Boolean typeBlankPasswordWithoutClickLogin() {
        helper.waitForPresence(password);
        helper.waitUtilElementVisible(driver.findElement(password));
        helper.enterData(driver.findElement(password), "a");
        helper.ctrlADeleteAction(driver.findElement(password));
        helper.visibleOfLocated(passwordHelp);
        helper.waitUtilElementVisible(driver.findElement(passwordHelp));
        return checkDisplayRequiredPasswordMsg();
    }

    public Boolean checkDisplayRequiredPassword() {
        helper.waitForPresence(password);
        helper.waitUtilElementVisible(driver.findElement(password));
        helper.enterData(driver.findElement(phoneNumber), dataTest.PHONE_DATA);
        helper.clickBtn(driver.findElement(loginBTN));
        helper.waitUtilElementVisible(driver.findElement(passwordHelp));
        return checkDisplayRequiredPasswordMsg();
    }

    public Boolean clickLoginWithWrongPwd() {
        submitLoginByPassword(dataTest.PHONE_DATA, dataTest.WRONG_PASSWORD);
        return checkDisplayWrongLoginMsg();
    }

    public Boolean loginPasswordSuccessfully(String phone, String password) {
        submitLoginByPassword(phone, password);
        helper.waitForPresence(commonPagesTheme1().homePage.bannerTrack);
        helper.checkDisplayElementByElement(driver.findElement(commonPagesTheme1().homePage.bannerTrack));
        commonPagesTheme1().homePage.clickAccountIcon();
        try {
            return helper.visibleOfLocated(commonPagesTheme1().homePage.accountInfo);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            return false;
        }
    }

    //TODO feature for submit wrong OTP
    public Boolean checkDisplayOtpErrMsg() {
        return helper.checkDisplayElement(loginOtpErrMsg);
    }

    //TODO feature for change country code
    public void changeCountryCode(String code, String country) {
        String xpath = "//span[text()=\"" + country + "\"]";
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
        keyList.add("placeHolder");
        commonCheck(language, "phoneNumber place-holder", "placeholder", phoneNumber
                , jsonReader.localeReader(language, dataTest.PAGE, keyList));
    }

    private void checkPasswordFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("inputYourPassword");
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
        keyList.add("loginByPhone");
        commonCheck(language, "login by OTP", "text", changeLoginMethod
                , jsonReader.localeReader(language, dataTest.PAGE, keyList));
    }

    private void checkLoginByPasswordFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("loginByPassword");
        commonCheck(language, "login by Password", "text", changeLoginMethod
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
        keyList.add("pleaseEnterPhoneNumber");
        commonCheck(language, "check Required Phonenumber", "text", loginTitle, jsonReader.localeReader(language
                , dataTest.PAGE, keyList));
    }

    private void checkRequiredPasswordFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("pleaseEnterPhoneNumber");
        commonCheck(language, "check Required Password", "text", loginTitle, jsonReader.localeReader(language
                , dataTest.PAGE, keyList));
    }

    private void checkInvalidPhoneNumberFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("pleaseEnterPhoneNumber");
        commonCheck(language, "check Invalid PhoneNumber", "text", loginTitle, jsonReader.localeReader(language
                , dataTest.PAGE, keyList));
    }

    private void checkIncorrectMessageFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("pleaseEnterPhoneNumber");
        commonCheck(language, "check Incorrect Message", "text", loginTitle, jsonReader.localeReader(language
                , dataTest.PAGE, keyList));
    }

    private void checkInvalidPasswordFollowLanguage(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("pleaseEnterPhoneNumber");
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
        checkVerificationFollowLanguage();
        if (failed > 0) {
            System.out.println("Verify UI when " + language + " is FAILED!");
            failed = 0;
            return false;
        } else {
            //Change to other language
            helper.changeMethodLanguage(commonPagesTheme1().homePage.languageSwitch, commonPagesTheme1().homePage.languageTxt, commonPagesTheme1().homePage.vietnameseOption, commonPagesTheme1().homePage.englishOption);
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
    }

    private String checkCurrentLanguage() {
        currentLanguage = getCurrentLanguage();
        String language = "";
        if (currentLanguage.equals("Vi")) {
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
        clickChangeLoginMethod();
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
        System.out.println(language);
        checkUIFollowLanguageSteps();
        if (failed > 0) {
            System.out.println("Verify UI when " + language + " is FAILED!");
            failed = 0;
            return false;
        } else {
            //Change to other language
            helper.changeMethodLanguage(commonPagesTheme1().homePage.languageSwitch, commonPagesTheme1().homePage.languageTxt, commonPagesTheme1().homePage.vietnameseOption, commonPagesTheme1().homePage.englishOption);
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

    public void getCurrentWindow() {
        currentWindow = helper.getCurrentWindow();
    }

    public Boolean openNewTabAfterLoginPassed(String page) {
        String url = helper.getCurrentURL(); //baseURL
        helper.closeAllOpenTabExceptMainTab(currentWindow);
        if (page.contains("login")) {
            helper.openNewTab(url + dataTest.URL);
        } else {
            helper.openNewTab(url);
        }
        try {
            helper.waitForPresence(commonPagesTheme1().homePage.accountIcon);
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme1().homePage.accountIcon));
            commonPagesTheme1().homePage.clickAccountIcon();
        } catch (Exception e) {
            Log.error(e.getMessage());
            helper.refreshPage();
            helper.waitForPresence(commonPagesTheme1().homePage.accountIcon);
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme1().homePage.accountIcon));
            commonPagesTheme1().homePage.clickAccountIcon();
        }
        Boolean checkAccountInfo = helper.checkDisplayElement(commonPagesTheme1().homePage.accountInfo);
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
        helper.pressPageUpAction();
        commonPagesTheme1().homePage.clickAccountIcon();
        if (helper.checkDisplayElement(commonPagesTheme1().homePage.accountLogout) == true) {
            helper.clickBtn(driver.findElement(commonPagesTheme1().homePage.accountLogout));
        }
    }
}

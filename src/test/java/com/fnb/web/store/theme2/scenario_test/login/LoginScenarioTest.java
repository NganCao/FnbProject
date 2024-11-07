package com.fnb.web.store.theme2.scenario_test.login;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.HelperDataFaker;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme2.pages.home.HomePage;
import com.fnb.web.store.theme2.pages.login.DataTest;
import com.fnb.web.store.theme2.pages.login.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.fnb.web.setup.Setup.*;
import static org.testng.Assert.assertTrue;

public class LoginScenarioTest extends BaseTest {
    private LoginPage loginPage;
    private DataTest dataTest;
    private HomePage homePage;
    SoftAssert softAssert;
    Helper helper;

    @BeforeClass
    public void navigateToLoginPage() {
        loginPage = storePage().navigateToLoginTheme2();
        softAssert = storePage().softAssert;
        helper = storePage().helper;
    }

    @Test(priority = 1, testName = "Verify the login url")
    public void FB6716() {
        assertTrue(loginPage.checkURL()
                , "The current URL is failed!\nActual: " + loginPage.expectedRS + " did not contain actual url: " + loginPage.actualRS);
    }

    @Test(priority = 1, testName = "Verify the display of background")
    public void FB6718() {
        assertTrue(loginPage.checkBackgroundDisplay(), "Background did not display");
    }

    @Test(priority = 2, testName = "Verify the display of language default on login page")
    public void FB6724() {
        assertTrue(loginPage.checkDisplayDefaultLanguage()
                , "language default on login page displayed incorrectly.\nActual: " + loginPage.getCurrentLanguage() + "\nExpected: " + dataTest.DEFAULT_LANGUAGE);
    }

    @Test(priority = 3, testName = "Verify the display of store logo on login page")
    public void FB6728() {
        helper.refreshPage();
        assertTrue(loginPage.checkDisplayStoreLogo(), "Store logo on login page did not display");
    }

    @Test(priority = 4, testName = "Verify the display of Login title")
    public void FB6720() {
        System.out.println("Verify UI with login by password page");
        assertTrue(loginPage.checkDisplayTitle(), "Title did not display");
    }

    @Test(priority = 5, testName = "Verify the display of country code dropdown")
    public void FB6729() {
        assertTrue(loginPage.checkDisplayCountryCode(), "Country code dropdown did not display");
    }

    @Test(priority = 5, testName = "Verify the display of Country dropdown list")
    public void FB6736() {
        assertTrue(loginPage.checkDisplayCountryCodeList(), "Country dropdown list did not display");
    }

    @Test(priority = 5, testName = "Verify the display of country code scroll bar")
    public void FB7690() {
        assertTrue(loginPage.checkDisplayCountryScroll(), "Country code scroll bar did not display");
    }

    @Test(priority = 5, testName = "Verify the default value of Country code")
    public void FB6730() {
        loginPage.refreshPage();
        assertTrue(loginPage.checkDefaultValueCountryCode()
                , "Country code displayed incorrectly.\nActual: " + loginPage.actualRS + "\nExpected: " + dataTest.DEFAULT_COUNTRY_CODE);
    }

    @Test(priority = 6, testName = "Verify the display of phone icon")
    public void FB7693() {
        assertTrue(loginPage.checkDisplayIconPhone(), "PhoneNumber icon did not display");
    }

    @Test(priority = 6, testName = "Verify the display of phone number field")
    public void FB6732() {
        assertTrue(loginPage.checkDisplayPhoneNumber(), "PhoneNumber field did not display");
    }

    @Test(priority = 6, testName = "Verify the display of lock icon")
    public void FB7694() {
        assertTrue(loginPage.checkDisplayIconLock(), "Lock icon did not display");
    }

    @Test(priority = 7, testName = "Verify the display of password field")
    public void FB6733() {
        assertTrue(loginPage.checkDisplayPassword(), "Password field did not display");
    }

    @Test(priority = 7, testName = "Verify the default type of password filed")
    public void FB6747() {
        assertTrue(loginPage.checkDefaultTypePassword()
                , "Default type of password filed is incorrect.\nActual: " + loginPage.actualRS + "\nExpected: " + dataTest.DEFAULT_TYPE_PASSWORD);
    }

    @Test(priority = 8, testName = "Verify the display of eye icon")
    public void FB7675() {
        assertTrue(loginPage.checkDisplayEyeIcon(), "Eye icon did not display");
    }

    @Test(priority = 9, testName = "Verify the default value of eye icon")
    public void FB6737() {
        assertTrue(loginPage.checkDefaultEyeIcon(), "Default eye icon displayed incorrectly");
    }

    @Test(priority = 9, testName = "Verify the display of Login button")
    public void FB6741() {
        assertTrue(loginPage.checkDisplayLoginBTN(), "Login button did not display");
    }

    @Test(priority = 10, testName = "Verify the display of login By OTP tab")
    public void FB6740() {
        assertTrue(loginPage.checkDisplayLoginByOTPTab(), "\"Login By OTP\" tab did not display");
    }

    @Test(priority = 10, testName = "Verify the display of login By password tab")
    public void FB6773() {
        assertTrue(loginPage.checkDisplayLoginByPasswordTab(), "\"Login By Password\" tab did not display");
    }

    @Test(priority = 10, testName = "Verify when user clicks on \"Login by OTP\" CTA")
    public void FB6754() {
        assertTrue(loginPage.clickLoginByOTP()
                , "Clicking on \"Login by OTP\" failed!\nActual: " + loginPage.actualRS + "\nExpected: " + loginPage.expectedRS);
    }

    @Test(priority = 10, testName = "Verify when user clicks on \"Login by password\" CTA")
    public void FB6774() {
        assertTrue(loginPage.clickLoginByPassword()
                , "Clicking on \"Login by password\" failed.\nActual: " + loginPage.actualRS + "\nExpected: " + loginPage.expectedRS);
    }

    @Test(priority = 11, testName = "Verify the display of Login title when user is OTP page")
    public void FB6758() {
        // Clicking Login by OTP
        loginPage.clickLoginByOTP();
        assertTrue(loginPage.checkDisplayTitle(), "Title did not display on OTP");
    }

    @Test(priority = 12, testName = "Verify the display of country code dropdown when user is OTP page")
    public void FB7677() {
        assertTrue(loginPage.checkDisplayCountryCode(), "Country code dropdown did not display");
    }

    @Test(priority = 12, testName = "Verify the display of phone icon on OTP")
    public void FB7695() {
        assertTrue(loginPage.checkDisplayIconPhone(), "PhoneNumber icon on OTP did not display");
    }

    @Test(priority = 13, testName = "Verify the display of phone number field on OTP")
    public void FB6762() {
        assertTrue(loginPage.checkDisplayPhoneNumber(), "PhoneNumber field did not display");
    }

    @Test(priority = 13, testName = "Verify the max-length of phone number field")
    public void FB6755() {
        assertTrue(loginPage.checkMaximumPhoneNumberOTP()
                , "PhoneNumber max-length displayed incorrectly.\nActual: " + loginPage.actualRS + "\nExpected: " + dataTest.MAXIMUM_LENGTH_PHONENUMBER);
    }

    @Test(priority = 14, testName = "Verify the display of Login button on OTP")
    public void FB6771() {
        assertTrue(loginPage.checkDisplayLoginBTN(), "Login button on OTP did not display");
    }

    @Test(priority = 15, testName = "Verify the default value of Country code on OTP")
    public void FB6760() {
        assertTrue(loginPage.checkDefaultValueCountryCode()
                , "Default value of country code on OTP displayed incorrectly.\nActual: " + loginPage.actualRS + "\nExpected: " + dataTest.DEFAULT_COUNTRY_CODE);
    }

    @Test(priority = 15, testName = "Verify the display of Country dropdown list on OTP")
    public void FB6766() {
        assertTrue(loginPage.checkDisplayCountryCodeList(), "Country dropdown list on OTP did not display");
    }

    @Test(priority = 16, testName = "Verify the display of country code scroll bar on OTP")
    public void FB7691() {
        assertTrue(loginPage.checkDisplayCountryScroll(), "Country code scroll bar on OTP did not display");
    }

    @Test(priority = 17, testName = "Verify the display of Verification title")
    public void FB6782() {
        loginPage.submitLoginByOTP(HelperDataFaker.generatePhoneNumber());
        assertTrue(loginPage.checkDisplayVerificationTitle(), "Verification title did not display");
    }

    @Test(priority = 18, description = "Verify the display of Verification description")
    public void FB6785() {
        assertTrue(loginPage.checkDisplayVerificationDescription(), "Verification description did not display");
    }

    @Test(priority = 19, testName = "Verify the display of Verification description")
    public void FB7679() {
        assertTrue(loginPage.checkDisplayVerificationPhone(), "Verification phone-number did not display");
    }

    @Test(priority = 20, testName = "Verify the display of Verification otp input")
    public void FB7678() {
        assertTrue(loginPage.checkDisplayVerificationInputParent(), "OtP input did not display");
    }

    @Test(priority = 21, testName = "Verify the number of Verification otp input")
    public void FB6787() {
        assertTrue(loginPage.checkNumberVerificationOTPInput()
                , "The number OTP input displayed wrong.\nActual: " + loginPage.actualRS + "\nExpected: " + loginPage.expectedRS);
    }

    @Test(priority = 22, testName = "Verify the display of Verification resend description")
    public void FB6789() {
        assertTrue(loginPage.checkDisplayVerificationResendDescription(), "Verification resend description did not display");
    }

    @Test(priority = 23, testName = "Verify the display of Verification resend CTA")
    public void FB6790() {
        assertTrue(loginPage.checkDisplayVerificationResend(), "Resend CTA did not display");
    }

    @Test(priority = 26, testName = "Verify UI when user changes language")
    public void FB6725() {
        softAssert.assertTrue(loginPage.checkVerificationLanguage()
                , "Verification page when language is " + loginPage.getCurrentLanguage() + " displayed incorrectly");
        loginPage.refreshPage();
        softAssert.assertTrue(loginPage.checkChangeLanguage()
                , "UI when language is " + loginPage.getCurrentLanguage() + " displayed incorrectly");
        softAssert.assertAll();
    }

    @Test(priority = 35, testName = "Verify the display of required phoneNumber message")
    public void FB6743() {
        loginPage.refreshPage();
        assertTrue(loginPage.typeBlankPhoneNumberWithoutClickLogin(), "Required phoneNumber message did not display");
    }

    @Test(priority = 36, testName = "Verify the display of invalid phoneNumber message without clicking login button")
    public void FB7687() {
        loginPage.refreshPage();
        assertTrue(loginPage.typeInvalidPhoneNumberWithoutClickLogin(), "Invalid phoneNumber message did not display");
    }

    @Test(priority = 37, testName = "Verify type of password after clicking open eye icon")
    public void FB6739() {
        assertTrue(loginPage.checkTypePasswordOpenEyeIcon()
                , "Type of password displayed different text.\nActual: " + loginPage.actualRS + "\nExpected: " + dataTest.CHANGE_TYPE_PASSWORD);
    }

    @Test(priority = 38, testName = "Verify type of password after clicking close eye icon")
    public void FB6738() {
        assertTrue(loginPage.checkTypePasswordCloseEyeIcon()
                , "Type of password displayed different password.\nActual: " + loginPage.actualRS + "\nExpected: " + dataTest.DEFAULT_TYPE_PASSWORD);
    }

    @Test(priority = 39, testName = "Verify if user types blank on password field without clicking Login")
    public void FB6746() {
        loginPage.refreshPage();
        assertTrue(loginPage.typeBlankPasswordWithoutClickLogin(), "Required password message did not display");
    }

    @Test(priority = 40, testName = "Verify if user just clicks Login button, login failed and show all error message")
    public void FB6753() {
        loginPage.refreshPage();
        assertTrue(loginPage.clickLoginWithoutAllData(), "Required phoneNumber, password message did not display");
    }

    @Test(priority = 41, testName = "Verify if user clicks Login button without password")
    public void FB6781() {
        loginPage.refreshPage();
        assertTrue(loginPage.checkDisplayRequiredPassword(), "Required password message did not display after clicking on 'Login' button");
    }

    @Test(priority = 42, testName = "Verify the display of the limit characters of password message with 5 characters")
    public void FB6756() {
        loginPage.refreshPage();
        assertTrue(loginPage.checkMinimumPassword(5), "Limit characters of password message did not display");
    }

    @Test(priority = 43, testName = "Verify the display of invalid phoneNumber message after clicking on Login")
    public void FB7696() {
        storePage().navigateToLoginTheme2();
        assertTrue(loginPage.clickLoginWithInvalidPhoneNumber()
                , "Invalid phoneNumber message did not display.\nError message: " + loginPage.actualRS);
    }

    @Test(priority = 48, testName = "Verify if user login successfully without 0")
    public void FB7995() {
        loginPage.getCurrentWindow();
        loginPage.refreshPage();
        assertTrue(loginPage.loginPasswordSuccessfully(dataTest.PHONE_DATA, dataTest.PASSWORD), "Login failed!");
    }

    @Test(priority = 49, testName = "Verify the display of profile icon when user opens a new home tab after login successfully")
    public void FB7718() {
        loginPage.getCurrentWindow();
        assertTrue(loginPage.openNewTabAfterLoginPassed(baseUrl, "home"), loginPage.actualRS);
    }

    @Test(priority = 50, testName = "Verify the display of profile icon when user opens a new login tab after login successfully")
    public void FB7719() {
        assertTrue(loginPage.openNewTabAfterLoginPassed(baseUrl, dataTest.PAGE), loginPage.actualRS);
    }

    @Test(priority = 51, testName = "Verify if user login successfully with a number starting with 0")
    public void FB6952() {
        helper.switchBackCurrentTab(loginPage.currentWindow);
        helper.refreshPage();
        loginPage.checkLoginIfError();
        loginPage.navigateToLogin();
        assertTrue(loginPage.loginPasswordSuccessfully(dataTest.PHONE_DATA_WITH_0, dataTest.PASSWORD), "Login failed!");
    }

    @AfterClass
    public void logout() {
        helper.refreshPage();
        loginPage.checkLoginIfError();
    }
}

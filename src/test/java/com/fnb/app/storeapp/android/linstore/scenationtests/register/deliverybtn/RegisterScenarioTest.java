package com.fnb.app.storeapp.android.linstore.scenationtests.register.deliverybtn;

import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.storeapp.android.linstore.pages.register.RegisterPageLinStore;
import com.fnb.app.setup.BaseTest;
import com.fnb.utils.helpers.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.fnb.app.storeapp.android.linstore.pages.register.RegisterDataTestLinStore.*;
import static com.fnb.app.setup.BaseSetup.softAssert;
import static com.fnb.utils.api.storeapp.pos.DataTest.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RegisterScenarioTest extends BaseTest {
    RegisterPageLinStore registerPageLinStore;
    LoginPageLinStore loginPageLinStore;

    public String phone1;
    public String phone2;

    @BeforeClass
    public void navigateToPage() {
        registerPageLinStore = homePageLinStore.navigateLinStoreToCreateRegister();
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
        softAssert = new SoftAssert();
    }

    @Test(priority = 1)
    public void verifyUI8355() {
        Log.info("Running verify UI Register Account Title");
        loginPageLinStore.splashScreen();
        registerPageLinStore.navigateToDeliveryBtn();
        phone1 = registerPageLinStore.generatePhoneNumberData();
        System.out.println(phone1);
        registerPageLinStore.navigateToLoginWithOTP(phone1);
        softAssert.assertTrue(registerPageLinStore.checkDisplayRegisterAccountTitle(), "Verify testcase 8355 is fail - Register Account title is not display");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifyUI8356() {
        Log.info("Running verify UI Register Title");
        assertTrue(registerPageLinStore.checkDisplayRegisterTitle(), "Verify testcase 8356 is fail - Register Account title is not display");
    }

    @Test(priority = 3)
    public void verifyUI8357() {
        Log.info("Running verify UI First Name");
        assertTrue(registerPageLinStore.checkDisplayFirstName(), "Verify testcase 8357 is fail - First Name Field is not display");
    }

    @Test(priority = 4)
    public void verifyUI8358() {
        Log.info("Running verify UI Last Name");
        assertTrue(registerPageLinStore.checkDisplayLastName(), "Verify testcase 8358 is fail - Last Name Field is not display");
    }

    @Test(priority = 5)
    public void verifyUI8359() {
        Log.info("Running verify UI Password");
        assertTrue(registerPageLinStore.checkDisplayPass(), "Verify testcase 8359 is fail - Password Field is not display");
    }

    @Test(priority = 6)
    public void verifyUI8360() {
        Log.info("Running verify UI Email");
        assertTrue(registerPageLinStore.checkDisplayEmail(), "Verify testcase 8360 is fail - Email Field is not display");
    }

    @Test(priority = 7)
    public void verifyUI8361() {
        Log.info("Running verify UI Gender Radio Button");
        assertTrue(registerPageLinStore.checkDisplayGenderRadio(), "Verify testcase 8361 is fail - Gender Radio Button is not display");
    }

    @Test(priority = 8)
    public void verifyUI8362() {
        Log.info("Running verify UI Day of Birth");
        assertTrue(registerPageLinStore.checkDisplayDayOfBirth(), "Verify testcase 8362 is fail - Day of Birth field is not display");
    }

    @Test(priority = 9)
    public void verifyUI8363() {
        Log.info("Running verify UI Register Account Button");
        assertTrue(registerPageLinStore.checkDisplayCreateAccountBtn(), "Verify testcase 8363 is fail - Create Account is not display");
    }

    @Test(priority = 10)
    public void verifyFU8475() throws InterruptedException {
        Log.info("Running verify testcase alert when register success - 8475");
        registerPageLinStore.verifyValidInfo(NAME_USER, PASSWORD);
        registerPageLinStore.verifyLogoutSuccess();
        registerPageLinStore.navigateToLoginScreen();
        loginPageLinStore.verifyLoginSuccess(phone1, PASSWORD);
        assertTrue(registerPageLinStore.checkDisplayToastMess(), "Testcase 8475 register is Fail, Toast message is not display");
//        assertEquals(loginPageLinStore.verifyToastMessDisplay(), LOGIN_SUCCESS_ALERT, "Testcase 8475 register is Fail, Toast message is not match");
    }

    @Test(priority = 11)
    public void verifyFU8471() {
        Log.info("Running verify testcase alert when input invalid name user - 8471");
        registerPageLinStore.verifyLogoutSuccess();
        registerPageLinStore.navigateToDeliveryBtn();
        String phone = registerPageLinStore.generatePhoneNumberData();
        Log.info(phone);
        registerPageLinStore.navigateToLoginWithOTP(phone);
        registerPageLinStore.verifyFirstNameNotFill();
        assertEquals(registerPageLinStore.verifyFirstNameNotFill(), WRONG_NAME_ALERT, "Testcase 8471 input empty name is Fail, Alert is not match");
    }

    @Test(priority = 12)
    public void verifyFU8472() {
        Log.info("Running verify testcase alert when input invalid password - 8472");
        registerPageLinStore.verifyInvalidPass(INVALID_PASSWORD);
        assertEquals(registerPageLinStore.verifyAlertInvalidPass(), SHORT_PASS_ALERT, "Testcase 8472 input invalid password is Fail, Alert is not match");
    }

    @Test(priority = 13)
    public void verifyFU8473() {
        Log.info("Running verify testcase alert when input empty password - 8473");
        registerPageLinStore.verifyPassNotFill();
        assertEquals(registerPageLinStore.verifyAlertEmptyPass(), EMPTY_PASS_ALERT, "Testcase 8473 input empty password is Fail, Alert is not match");
    }

    @Test(priority = 14)
    public void verifyFU8474() {
        Log.info("Running verify testcase alert when input wrong email - 8474");
        registerPageLinStore.verifyInvalidEmail(INVALID_EMAIL);
        assertEquals(registerPageLinStore.verifyAlertInvalidEmail(), LOGIN_FAIL_ALERT, "Testcase 8474 input empty password is Fail, Alert is not match");
    }

    @Test(priority = 15)
    public void verifyFU8476() {
        Log.info("Running verify testcase verify input text in last name field - 8476");
        assertEquals(registerPageLinStore.verifyInputLastName(), LAST_NAME_USER, "Testcase 8476 verify text input in last name field is fail, text input is false");
    }

    @Test(priority = 16)
    public void verifyFU8477() {
        Log.info("Running verify testcase verify female options is checked - 8477");
        assertTrue(registerPageLinStore.verifyCheckGenderOptions(), "Testcase 8477 verify female options is checked is Fail");
    }

    @Test(priority = 17)
    public void verifyFU8478() {
        Log.info("Running verify testcase verify male options is checked - 8478");
        assertTrue(registerPageLinStore.verifyCheckMaleOptions(), "Testcase 8476 verify male options is checked is Fail");
    }

    @Test(priority = 18)
    public void verifyFU8479() {
        Log.info("Running verify testcase verify other gender options is checked - 8479");
        assertTrue(registerPageLinStore.verifyCheckOthersOptions(), "Testcase 8479 verify other gender options is checked is Fail");
    }

    @Test(priority = 19)
    public void verifyFU8480() {
        Log.info("Running verify testcase verify Date of Birth field is display - 8480");
        registerPageLinStore.verifyInvalidEmail(INVALID_EMAIL);
        assertEquals(registerPageLinStore.verifyAlertInvalidEmail(), LOGIN_FAIL_ALERT, "Testcase 8480 verify Date of Birth field is Fail, Date of Birth field is not display");
    }

    @Test(priority = 20)
    public void verifyFU8481() {
        Log.info("Running verify testcase Verify the failure message when Empty/blank name, password, wrong format email - 8481");
        softAssert.assertEquals(registerPageLinStore.verifyFirstNameNotFill(), WRONG_NAME_ALERT, "input empty firstname is Fail, Alert is not match");
        softAssert.assertEquals(registerPageLinStore.verifyAlertInvalidPass(), EMPTY_PASS_ALERT, "input empty password is Fail, Alert is not match");
        softAssert.assertEquals(registerPageLinStore.verifyAlertInvalidEmail(), LOGIN_FAIL_ALERT, "input empty email is Fail, Alert is not match");
        softAssert.assertAll();
    }

    @Test(priority = 21)
    public void verifyFU8482() {
        Log.info("Running verify testcase Verify the failure message when input valid name, password but wrong format email - 8482");
        registerPageLinStore.verifyInputNameAndPass();
        assertEquals(registerPageLinStore.verifyAlertInvalidEmail(), LOGIN_FAIL_ALERT, "Verify the failure message when input valid name, password but wrong format is Fail, Alert is not match");
    }

    //TODO : RegisterLink
    @Test(priority = 22)
    public void verifyFU8482R() throws InterruptedException {
        phone2 = registerPageLinStore.generatePhoneNumberData();
        Log.info("Running verify testcase alert when register success - 8475");
        registerPageLinStore.navigateBack(2);
        registerPageLinStore.navigateToDeliveryBtn();
        registerPageLinStore.navigateToRegisterLink(PHONE_NUMBER);
        registerPageLinStore.inputNewPhoneNumberRegister(phone2);
        registerPageLinStore.verifyValidInfo(NAME_USER, PASSWORD);
        registerPageLinStore.verifyLogoutSuccess();
        registerPageLinStore.navigateToLoginScreen();
        loginPageLinStore.verifyLoginSuccess(phone2, PASSWORD);
        assertTrue(registerPageLinStore.checkDisplayToastMess(), "Testcase FU8482R register is Fail, Toast message is not display");
//        assertEquals(loginPageLinStore.verifyToastMessDisplay(), LOGIN_SUCCESS_ALERT, "Testcase FU8482R is Fail, alert message is not match");
    }

    @Test(priority = 23)
    public void verifyUI8355R() throws InterruptedException {
        Log.info("Running verify UI Register Account Title");
        registerPageLinStore.verifyLogoutSuccess();
        registerPageLinStore.navigateToDeliveryBtn();
        phone2 = registerPageLinStore.generatePhoneNumberData();
        System.out.println(phone2);
        registerPageLinStore.navigateToRegisterLink(phone2);
        assertTrue(registerPageLinStore.checkDisplayRegisterAccountTitle(), "Verify testcase 8355 is fail - Register Account title is not display");
    }

    @Test(priority = 25)
    public void verifyUI8357R() {
        Log.info("Running verify UI First Name");
        assertTrue(registerPageLinStore.checkDisplayFirstName(), "Verify testcase 8357 is fail - First Name Field is not display");
    }

    @Test(priority = 26)
    public void verifyUI8358R() {
        Log.info("Running verify UI Last Name");
        assertTrue(registerPageLinStore.checkDisplayLastName(), "Verify testcase 8358 is fail - Last Name Field is not display");
    }

    @Test(priority = 27)
    public void verifyUI8359R() {
        Log.info("Running verify UI Password");
        assertTrue(registerPageLinStore.checkDisplayPass(), "Verify testcase 8359 is fail - Password Field is not display");
    }

    @Test(priority = 28)
    public void verifyUI8360R() {
        Log.info("Running verify UI Email");
        assertTrue(registerPageLinStore.checkDisplayEmail(), "Verify testcase 8360 is fail - Email Field is not display");
    }

    @Test(priority = 29)
    public void verifyUI8361R() {
        Log.info("Running verify UI Gender Radio Button");
        assertTrue(registerPageLinStore.checkDisplayGenderRadio(), "Verify testcase 8361 is fail - Gender Radio Button is not display");
    }

    @Test(priority = 30)
    public void verifyUI8362R() {
        Log.info("Running verify UI Day of Birth");
        assertTrue(registerPageLinStore.checkDisplayDayOfBirth(), "Verify testcase 8362 is fail - Day of Birth field is not display");
    }

    @Test(priority = 31)
    public void verifyUI8363R() {
        Log.info("Running verify UI Register Account Button");
        assertTrue(registerPageLinStore.checkDisplayCreateAccountBtn(), "Verify testcase 8363 is fail - Create Account is not display");
    }

    @Test(priority = 32)
    public void verifyFU8483R() {
        Log.info("Running verify testcase alert when input invalid name user - 8471");
        assertEquals(registerPageLinStore.verifyFirstNameNotFill(), WRONG_NAME_ALERT, "Testcase FU8483R is Fail, alert message is not match");
    }

    @Test(priority = 33)
    public void verifyFU8472R() {
        Log.info("Running verify testcase alert when input invalid password - 8472R - register link");
        registerPageLinStore.verifyInvalidPass(INVALID_PASSWORD);
        assertEquals(registerPageLinStore.verifyAlertInvalidPass(), SHORT_PASS_ALERT, "Testcase 8472R - register link input invalid password is Fail, Alert is not match");
    }

    @Test(priority = 34)
    public void verifyFU8473R() {
        Log.info("Running verify testcase alert when input empty password - 8473R- register link");
        registerPageLinStore.verifyPassNotFill();
        assertEquals(registerPageLinStore.verifyAlertEmptyPass(), EMPTY_PASS_ALERT, "Testcase 8473R- register link input empty password is Fail, Alert is not match");
    }

    @Test(priority = 35)
    public void verifyFU8474R() {
        Log.info("Running verify testcase alert when input wrong email - 8474");
        registerPageLinStore.verifyInvalidEmail(INVALID_PASSWORD);
        assertEquals(registerPageLinStore.verifyAlertInvalidEmail(), LOGIN_FAIL_ALERT, "Testcase 8474R- register link input empty password is Fail, Alert is not match");
    }

    @Test(priority = 36)
    public void verifyFU8476R() {
        Log.info("Running verify testcase verify input text in last name field - 8476");
        assertEquals(registerPageLinStore.verifyInputLastName(), LAST_NAME_USER, "Testcase 8476R- register link verify text input in last name field is fail, text input is false");
    }

    @Test(priority = 37)
    public void verifyFU8477R() {
        Log.info("Running verify testcase verify female options is checked - 8477");
        assertTrue(registerPageLinStore.verifyCheckGenderOptions(), "Testcase 8477R- register link verify female options is checked is Fail");
    }

    @Test(priority = 38)
    public void verifyFU8478R() {
        Log.info("Running verify testcase verify male options is checked - 8478");
        assertTrue(registerPageLinStore.verifyCheckMaleOptions(), "Testcase 8478R- register link verify male options is checked is Fail");
    }

    @Test(priority = 39)
    public void verifyFU8479R() {
        Log.info("Running verify testcase verify other gender options is checked - 8479");
        assertTrue(registerPageLinStore.verifyCheckOthersOptions(), "Testcase 8479R- register link verify other gender options is checked is Fail");
    }

    @Test(priority = 40)
    public void verifyFU8480R() {
        Log.info("Running verify testcase verify Date of Birth field is display - 8480");
        registerPageLinStore.verifyInvalidEmail(INVALID_EMAIL);
        assertEquals(registerPageLinStore.verifyAlertInvalidEmail(), LOGIN_FAIL_ALERT, "Testcase 8480R- register link verify Date of Birth field is Fail, Date of Birth field is not display");
    }

    @Test(priority = 41)
    public void verifyFU8481R() {
        Log.info("Running verify testcase Verify the failure message when Empty/blank name, password, wrong format email - 8481");
        softAssert.assertEquals(registerPageLinStore.verifyFirstNameNotFill(), WRONG_NAME_ALERT, "input empty firstname is Fail, Alert is not match");
        softAssert.assertEquals(registerPageLinStore.verifyAlertInvalidPass(), EMPTY_PASS_ALERT, "input empty password is Fail, Alert is not match");
        softAssert.assertEquals(registerPageLinStore.verifyAlertInvalidEmail(), LOGIN_FAIL_ALERT, "input empty email is Fail, Alert is not match");
        softAssert.assertAll();
    }

    @Test(priority = 42)
    public void verifyFU8484R() {
        Log.info("Running verify testcase Verify the failure message when input valid name, password but wrong format email - 8482");
        registerPageLinStore.verifyInputNameAndPass();
        assertEquals(registerPageLinStore.verifyAlertInvalidEmail(), LOGIN_FAIL_ALERT, "Verify the failure message when input valid name, password but wrong format is Fail, Alert is not match");
        registerPageLinStore.navigateBack(2);
    }
}

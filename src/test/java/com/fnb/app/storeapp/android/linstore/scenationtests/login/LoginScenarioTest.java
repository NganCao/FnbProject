package com.fnb.app.storeapp.android.linstore.scenationtests.login;

import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.setup.BaseTest;
import com.fnb.utils.helpers.Log;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fnb.app.storeapp.android.linstore.pages.login.LoginDataTest.*;
import static com.fnb.utils.api.storeapp.pos.DataTest.PASSWORD;
import static com.fnb.utils.api.storeapp.pos.DataTest.PHONE_NUMBER;


public class LoginScenarioTest extends BaseTest {

    LoginPageLinStore loginPageLinStore;

    @BeforeClass
    public void navigateToPage() {
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
    }

    @Test(priority = 1)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyUI() {
        loginPageLinStore.splashScreen();
        Log.info("Running verify UI login");
        loginPageLinStore.navigateToLoginScreen();
        loginPageLinStore.clickLoginBtn();
    }

    @Test(priority = 2)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyUI7313() {
        Log.info("Running verify UI login Title");
        Assert.assertTrue(loginPageLinStore.checkDisplayLoginTitle(), "Verify testcase 7313 is fail - Login title is not display");
    }

    @Test(priority = 3)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyUI7318() {
        Log.info("Running verify UI Country code");
        Assert.assertTrue(loginPageLinStore.checkDisplayCountryCode(), "Verify testcase 7318 is fail - Country code is not display");
    }

    @Test(priority = 4)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyUI7321() {
        Log.info("Running verify UI phone number field");
        Assert.assertTrue(loginPageLinStore.checkDisplayPhoneNumber(), "Verify testcase 7321 is fail - Phone number field is not display");
    }

    @Test(priority = 5)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyUI7326() {
        Log.info("Running verify UI Alert when user input wrong phone number");
        Assert.assertTrue(loginPageLinStore.checkDisplayAlertPhoneNotFill(), "Verify testcase 7326 is fail - Alert is not display");
    }

    @Test(priority = 6)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyUI7327() {
        Log.info("Running verify UI Alert when user input wrong password");
        Assert.assertTrue(loginPageLinStore.checkDisplayAlertPassNotFill(), "Verify testcase 7327 is fail - Alert is not display");
    }

    @Test(priority = 7)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7374() throws InterruptedException {
        Log.info("Running verify login success alert - 7473");
        loginPageLinStore.writeApiLoginSuccess();
//        loginPageLinStore.splashScreen();
//        loginPageLinStore.verifyLogoutSuccess();
//        loginPageLinStore.navigateToLoginScreen();
        loginPageLinStore.verifyLoginSuccess(PHONE_NUMBER, PASSWORD);
//        loginPageLinStore.AcceptNotification();
//        Assert.assertEquals(loginPageLinStore.verifyToastMessDisplay(), LOGIN_SUCCESS_ALERT, "Login Fail, Testcase 7473 is Fail");
        Assert.assertTrue(loginPageLinStore.checkApiLoginIsSuccess(), "Login Fail, Testcase 7473 is Fail");
        Assert.assertEquals(loginPageLinStore.checkApiPhoneNumber(), PHONE_NUMBER, "Login Fail, Testcase 7473 is Fail");
    }

    @Test(priority = 8)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyLogout() {
        Log.info("Running verify logout success");
        loginPageLinStore.verifyLogoutSuccess();
    }

    @Test(priority = 9)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7376() {
        Log.warn("Running verify testcase login fail with wrong country code - 7376 is Skip");
    }

    @Test(priority = 10)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7419() {
        loginPageLinStore.navigateToLoginScreen();
        Log.info("Running verify testcase login fail with wrong phone number - 7419");
        loginPageLinStore.verifyEmptyPhone();
        Assert.assertEquals(loginPageLinStore.verifyAlertPhoneNotFill(), LOGIN_FAIL_ALERT, "Testcase 7419 empty phone is Fail, Alert is not match");
        loginPageLinStore.verifyWrongPhone(WRONG_PHONE_NUMBER);
        Assert.assertEquals(loginPageLinStore.verifyAlertPhoneNotFill(), LOGIN_FAIL_ALERT, "Testcase 7419 wrong phone is Fail, Alert is not match");
        loginPageLinStore.clearData();
    }

    @Test(priority = 11)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7420() {
        Log.info("Running verify testcase login fail with wrong password - 7420");
        loginPageLinStore.verifyEmptyPass();
        Assert.assertEquals(loginPageLinStore.verifyAlertPassNotFill(), EMPTY_PASS_ALERT, "Testcase 7420 empty pass is Fail, Alert is not match");
        loginPageLinStore.verifyWrongPass(PHONE_NUMBER, WRONG_PHONE_NUMBER);
        Assert.assertEquals(loginPageLinStore.verifyAlerWrongInfoLogin(), WRONG_PASS_ALERT, "Testcase 7420 wrong pass is Fail, Alert is not match");
        loginPageLinStore.clearData();
    }

    @Test(priority = 12, testName = "Running verify testcase login success in checkout page - 7434")
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7422() {
        Log.info("Running verify testcase login fail with phone number not register yet - 7422");
        loginPageLinStore.verifyPhoneNotRegister("0981818181", "111111");
        Assert.assertEquals(loginPageLinStore.verifyAlerWrongInfoLogin(), WRONG_PASS_ALERT, "Testcase 7422 is Fail, Alert is not match");
        loginPageLinStore.clearData();
        loginPageLinStore.navigateBack();

    }

    @Test(priority = 13)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7425() throws InterruptedException {
        Log.info("Running verify testcase login success in delivery button - 7425");
        loginPageLinStore.navigateToDeliveryBtn();
        loginPageLinStore.verifyLoginSuccess(PHONE_NUMBER, PASSWORD);
//        Assert.assertEquals(loginPageLinStore.verifyToastMessDisplay(), LOGIN_SUCCESS_ALERT, "Login Fail, Testcase 7425 is Fail");
        Assert.assertTrue(loginPageLinStore.checkApiLoginIsSuccess(), "Login Fail, Testcase 7473 is Fail");
        Assert.assertEquals(loginPageLinStore.checkApiPhoneNumber(), PHONE_NUMBER, "Login Fail, Testcase 7473 is Fail");
    }

    @Test(priority = 14)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7427() {
        Log.warn("Running verify testcase login fail with wrong country code in delivery button - 7427is Skip");
        loginPageLinStore.verifyLogoutSuccess();
    }

    @Test(priority = 15)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7428() {
        Log.info("Running verify login fail with wrong phone number in delivery button - 7428");
        loginPageLinStore.navigateToDeliveryBtn();
        loginPageLinStore.verifyEmptyPhone();
        Assert.assertEquals(loginPageLinStore.verifyAlertPhoneNotFill(), LOGIN_FAIL_ALERT, "Testcase 7428 empty phone is Fail, Alert is not match");
        loginPageLinStore.verifyWrongPhone(WRONG_PHONE_NUMBER);
        Assert.assertEquals(loginPageLinStore.verifyAlertPhoneNotFill(), LOGIN_FAIL_ALERT, "Testcase 7428 wrong phone is Fail, Alert is not match");
        loginPageLinStore.clearData();
        loginPageLinStore.navigateBack();
    }

    @Test(priority = 16)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7429() {
        Log.info("Running verify login fail with wrong password in delivery button - 7429");
        loginPageLinStore.navigateToDeliveryBtn();
        loginPageLinStore.verifyEmptyPass();
        Assert.assertEquals(loginPageLinStore.verifyAlertPassNotFill(), EMPTY_PASS_ALERT, "Testcase 7429 empty pass is Fail, Alert is not match");
        loginPageLinStore.verifyWrongPass(PHONE_NUMBER, WRONG_PHONE_NUMBER);
        Assert.assertEquals(loginPageLinStore.verifyAlerWrongInfoLogin(), WRONG_PASS_ALERT, "Testcase 7429 wrong pass is Fail, Alert is not match");
        loginPageLinStore.clearData();
        loginPageLinStore.navigateBack();
    }

    @Test(priority = 17)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7431() {
        Log.info("Running verify testcase login fail with phone number not register yet in delivery button - 7431");
        loginPageLinStore.navigateToDeliveryBtn();
        loginPageLinStore.verifyPhoneNotRegister(PHONE_NUMBER_NOT_REGISTER, PASSWORD_1);
        Assert.assertEquals(loginPageLinStore.verifyAlerWrongInfoLogin(), WRONG_PASS_ALERT, "Testcase 7422 is Fail, Alert is not match");
        loginPageLinStore.clearData();
        loginPageLinStore.navigateBack();
    }

    @Test(priority = 18)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7436() {
        Log.info("Running verify testcase login fail with wrong country code in checkout page - 7436 is Skip");
    }

    @Test(priority = 19)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7437() {
        loginPageLinStore.navigateToCheckoutPage();
        Log.info("Running verify testcase login fail with wrong phone number in checkout page - 7437");
        loginPageLinStore.verifyEmptyPhone();
        Assert.assertEquals(loginPageLinStore.verifyAlertPhoneNotFill(), LOGIN_FAIL_ALERT, "Testcase 7437 empty phone is Fail, Alert is not match");
        loginPageLinStore.verifyWrongPhone(WRONG_PHONE_NUMBER);
        Assert.assertEquals(loginPageLinStore.verifyAlertPhoneNotFill(), LOGIN_FAIL_ALERT, "Testcase 7437 wrong phone is Fail, Alert is not match");
        loginPageLinStore.clearData();
    }

    @Test(priority = 20)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7438() {
        Log.info("Running verify testcase login fail with wrong password in checkout page - 7438");
        loginPageLinStore.verifyEmptyPass();
        Assert.assertEquals(loginPageLinStore.verifyAlertPassNotFill(), EMPTY_PASS_ALERT, "Testcase 7438 empty pass is Fail, Alert is not match");
        loginPageLinStore.verifyWrongPass(PHONE_NUMBER, WRONG_PASS_ALERT);
        Assert.assertEquals(loginPageLinStore.verifyAlerWrongInfoLogin(), WRONG_PASS_ALERT, "Testcase 7438 wrong pass is Fail, Alert is not match");
        loginPageLinStore.clearData();
    }

    @Test(priority = 21)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7440() {
        Log.info("Running verify testcase login fail with phone number not register yet in checkout page - 7440");
        loginPageLinStore.verifyPhoneNotRegister("0981818181", "111111");
        Assert.assertEquals(loginPageLinStore.verifyAlerWrongInfoLogin(), WRONG_PASS_ALERT, "Testcase 7440 is Fail, Alert is not match");
        loginPageLinStore.clearData();
    }

    @Test(priority = 22)
    @Description("Running verify testcase login success in checkout page - 7434")
    public void verifyFU7434() {
        Log.info("Running verify testcase login success in checkout page - 7434");
        loginPageLinStore.verifyLoginSuccess(PHONE_NUMBER, PASSWORD);
//        Assert.assertEquals(loginPageLinStore.verifyToastMessDisplay(), LOGIN_SUCCESS_ALERT, "Login Fail, Testcase 7434 is Fail");
        Assert.assertTrue(loginPageLinStore.checkApiLoginIsSuccess(), "Login Fail, Testcase 7473 is Fail");
        Assert.assertEquals(loginPageLinStore.checkApiPhoneNumber(), PHONE_NUMBER, "Login Fail, Testcase 7473 is Fail");
        loginPageLinStore.clickCheckoutButton();
        loginPageLinStore.splashScreen();
    }
}

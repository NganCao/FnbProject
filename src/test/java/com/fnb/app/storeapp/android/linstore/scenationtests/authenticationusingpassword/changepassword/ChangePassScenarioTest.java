package com.fnb.app.storeapp.android.linstore.scenationtests.authenticationusingpassword.changepassword;

import com.fnb.app.storeapp.android.linstore.pages.authenticationusingpassword.changepassword.ChangePasswordPage;
import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.setup.BaseTest;
import com.fnb.utils.helpers.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fnb.app.storeapp.android.linstore.pages.authenticationusingpassword.changepassword.ChangePasswordDataTest.*;
import static com.fnb.app.storeapp.android.linstore.pages.authenticationusingpassword.changepassword.ChangePasswordPage.*;
import static com.fnb.utils.api.storeapp.pos.DataTest.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ChangePassScenarioTest extends BaseTest {
    ChangePasswordPage changePasswordPage;
    LoginPageLinStore loginPageLinStore;


    @BeforeClass
    public void navigateToPage() {
        changePasswordPage = homePageLinStore.navigateLinStoreToChangePass();
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
    }

    @Test(priority = 1)
    public void verifyUI9750(){
        Log.info("Running Verify the display of change password field");
        loginPageLinStore.splashScreen();
        loginPageLinStore.navigateToLoginScreen();
        loginPageLinStore.verifyLoginSuccess(PHONE_DEFAULT, PASSWORD);
//        changePasswordPage.clickCancelBtn();
        changePasswordPage.navigateToEditProfile();
        assertTrue(changePasswordPage.checkDisplayChangePassField(), "Verify testcase 9750 is fail - change password field is not display");
    }

    @Test(priority = 2)
    public void verifyUI9751() {
        Log.info("Running Verify the Title of change password field");
        assertEquals(changePasswordPage.checkTitleChangePassField(), CHANGE_PASS_TITLE, "Verify testcase 9751 is fail - Verify the Title of change password field");
    }

    @Test(priority = 3)
    public void verifyUI9752() {
        Log.info("Running Verify the display of change password Title");
        changePasswordPage.clickChangePassField();
        changePasswordPage.clickUpdatePassBtn();
        assertTrue(changePasswordPage.checkDisplayChangePassTitle(), "Verify testcase 9752 is fail - Verify the display of change password Title");
    }

    @Test(priority = 4)
    public void verifyUI9753() {
        Log.info("Running Verify the Title of change password Title");
        assertEquals(changePasswordPage.checkTitleChangePass(), CHANGE_PASS_TITLE, "Verify testcase 9753 is fail - Verify the Title of change password Title");
    }

    @Test(priority = 5)
    public void verifyUI9754() {
        Log.info("Running Verify the display of current password field");
        assertTrue(changePasswordPage.checkDisplayCurrentPassInput(), "Verify testcase 9752 is fail - Verify the display of change password Title");
    }

    @Test(priority = 6)
    public void verifyUI9755() {
        Log.info("Running Verify the placeholder of current password field");
        assertEquals(changePasswordPage.checkPlaceHolderCurrentPass(), INPUT_PASS_ALERT, "Verify testcase 9753 is fail - Verify the placeholder of current password field");
    }

    @Test(priority = 7)
    public void verifyUI9756() {
        Log.info("Running Verify hide text, show dot icon when input password in current password field");
        //TODO: Need to handle this case then by API
        String hiddenPass = formatPass(INVALID_PASS);
        assertEquals(changePasswordPage.checkHiddenTextCurrentInput(), hiddenPass, "Verify testcase 9756 is fail - Verify hide text, show dot icon when input password in current password field");
    }

    @Test(priority = 8)
    public void verifyUI9757() {
        Log.info("Running Verify text when input current password field");
        assertEquals(changePasswordPage.checkTextCurrentInput(), INVALID_PASS, "Verify testcase 9757 is fail - Verify text when input current password field");
    }

    @Test(priority = 9)
    public void verifyUI9758() {
        Log.info("Running  Verify the display of Eye icon in current password field");
        assertTrue(changePasswordPage.checkDisplayHidenIconCurrentPass(), "Verify testcase 9753 is fail -  Verify the display of Eye icon in current password field");
    }

    @Test(priority = 10)
    public void verifyUI9759() {
        Log.info("Running Verify the display error validation messages when empty current password field ");
        assertTrue(changePasswordPage.checkDisplayAlertCurrentPass(), "Verify testcase 9759 is fail -  Verify the display error validation messages when empty current password field");
    }

    @Test(priority = 11)
    public void verifyUI9760() {
        Log.info("Running Verify error validation messages when input invalid current password field");
        assertEquals(changePasswordPage.alertInvalidCurrentPass(), WRONG_PASS_ALERT, "Verify testcase 9753 is fail - Verify the Title of change password Title");
    }

    @Test(priority = 12)
    public void verifyUI9761() {
        Log.info("Running Verify the display of new password field");
        assertTrue(changePasswordPage.checkDisplayNewPassInput(), "Verify testcase 9761 is fail - Verify the display of new password field");
    }

    @Test(priority = 13)
    public void verifyUI9762() {
        Log.info("Running Verify the placeholder of new password field");
        assertEquals(changePasswordPage.checkPlaceHolderNewPass(), PASS_PLACEHOLDER, "Verify testcase 9762 is fail - Verify the placeholder of current password field");
    }

    @Test(priority = 14)
    public void verifyUI9763() {
        Log.info("Running Verify hide text, show dot icon when input password in new password field");
        String hiddenPass = formatPass(INVALID_PASS);
        assertEquals(changePasswordPage.checkHidenTextNewInput(), hiddenPass, "Verify testcase 9763 is fail - Verify hide text, show dot icon when input password in new password field");
    }

    @Test(priority = 15)
    public void verifyUI9764() {
        Log.info("Running Verify text when input new password field");
        assertEquals(changePasswordPage.checkTextNewInput(), INVALID_PASS, "Verify testcase 9764 is fail - Verify text when input new password field");
    }

    @Test(priority = 16)
    public void verifyUI9765() {
        Log.info("Running Verify the display of Eye icon in new password field");
        assertTrue(changePasswordPage.checkDisplayHidenIconNewPass(), "Verify testcase 9765 is fail - Verify the display of Eye icon in new password field");
    }

    @Test(priority = 17)
    public void verifyUI9766() {
        Log.info("Running  Verify display error validation messages when empty new password field");
        assertTrue(changePasswordPage.checkDisplayAlertNewPass(), "Verify testcase 9766 is fail -  Verify display error validation messages when empty new password field");
    }

    @Test(priority = 18)
    public void verifyUI9767() {
        Log.info("Running Verify display error validation messages when input invalid new password field");
        assertEquals(changePasswordPage.alertInvalidNewPass(), INVALID_NEW_PASS_ALERT, "Verify testcase 9767 is fail - Verify display error validation messages when input invalid new password field");
    }

    @Test(priority = 19)
    public void verifyFU9769() {
        Log.info("Running Verify failure alert message when update empty current password");
        changePasswordPage.inputEmptyCurrentPass();
        assertEquals(changePasswordPage.checkAlertCurrentPassInput(), EMPTY_PASS_ALERT, "Verify testcase 9769 is fail - Verify failure alert message when update empty current password");
    }

    @Test(priority = 20)
    public void verifyFU9770() {
        Log.info("Running Verify failure alert message when update empty new password");
        changePasswordPage.inputEmptyNewPass();
        assertEquals(changePasswordPage.checkAlertNewPassInput(), EMPTY_NEW_PASS_ALERT, "Verify testcase 9769 is fail - Verify failure alert message when update empty new password");
    }

    @Test(priority = 21)
    public void verifyFU9771() {
        Log.info("Running Verify failure alert message when input invalid current password");
        changePasswordPage.inputInvalidCurrentPass();
        assertEquals(changePasswordPage.checkAlertCurrentPassInput(), WRONG_PASS_ALERT, "Verify testcase 9770 is fail - Verify failure alert message when input invalid current password");
    }

    @Test(priority = 22)
    public void verifyFU9772() {
        Log.info("Running Verify failure alert message when input invalid new password");
        changePasswordPage.inputInvalidNewPass();
        assertEquals(changePasswordPage.checkAlertNewPassInput(), INVALID_NEW_PASS_ALERT, "Verify testcase 9772 is fail - Verify failure alert message when input invalid new password");
    }

    @Test(priority = 23)
    public void verifyFU9773() {
        Log.info("Running Verify update password success");
        changePasswordPage.updatePassword(PASSWORD, NEW_PASS);
        changePasswordPage.clickUpdatePassBtn();
    }

    @Test(priority = 24)
    public void verifyLogout() {
        Log.info("Running Logout");
        changePasswordPage.clickExitBtn();
        loginPageLinStore.verifyLogoutSuccess();
    }

    @Test(priority = 25)
    public void verifyLoginWithNewPass(){
        Log.info("Running Login with new password");
        loginPageLinStore.navigateToLoginScreen();
        loginPageLinStore.verifyLoginSuccess(PHONE_DEFAULT, NEW_PASS);
    }

    @Test(priority = 26)
    public void verifyLoginWithNewPass2(){
        Log.info("Running update password");
        changePasswordPage.navigateToEditProfile();
        changePasswordPage.clickChangePassField();
        changePasswordPage.updatePassword(NEW_PASS, PASSWORD);
        changePasswordPage.clickUpdatePassBtn();
        changePasswordPage.clickExitBtn();
        loginPageLinStore.verifyLogoutSuccess();
        loginPageLinStore.navigateToLoginScreen();
        loginPageLinStore.verifyLoginSuccess(PHONE_DEFAULT, PASSWORD);
    }
}

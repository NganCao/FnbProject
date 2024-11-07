package com.fnb.app.storeapp.android.linstore.scenationtests.authenticationusingpassword.updateinforaccount;

import com.fnb.app.storeapp.android.linstore.pages.authenticationusingpassword.updateinforaccount.UpdateInfoAccountPage;
import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.setup.BaseTest;
import com.fnb.utils.helpers.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fnb.app.storeapp.android.linstore.pages.authenticationusingpassword.updateinforaccount.UpdateInfoAccountDataTest.*;
import static com.fnb.utils.api.storeapp.pos.DataTest.PASSWORD;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class UpdateInfoAccountScenarioTest extends BaseTest {
    UpdateInfoAccountPage updateInfoAccountPage;
    LoginPageLinStore loginPageLinStore;
    String original;
    String expected;

    @BeforeClass
    public void navigateToPage() {
        updateInfoAccountPage = homePageLinStore.navigateToUpdateInfoAccount();
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
    }

    @Test(priority = 1)
    public void verifyUI9774() {
        Log.info("Running  Verify the display of Exit button");
        loginPageLinStore.splashScreen();
        loginPageLinStore.navigateToLoginScreen();
        loginPageLinStore.verifyLoginSuccess(PHONE_DEFAULT, PASSWORD);
        updateInfoAccountPage.navigateToUpdateInfo();
        updateInfoAccountPage.inputData();
        assertTrue(updateInfoAccountPage.checkDisplayExitBtn(), "Verify testcase 9774 is fail -  Verify the display of Exit button");
    }

    @Test(priority = 2)
    public void verifyUI9775() {
        Log.info("Running  Verify the display of User's Avatar");
        assertTrue(updateInfoAccountPage.checkDisplayAvatar(), "Verify testcase 9775 is fail -  Verify the display of User's Avatar");
    }

    @Test(priority = 3)
    public void verifyUI9776() {
        Log.info("Running Verify the display of Upload Avatar icon");
        assertTrue(updateInfoAccountPage.checkDisplayUpload(), "Verify testcase 9776 is fail -   Verify the display of Upload Avatar icon");
    }

    @Test(priority = 4)
    public void verifyUI9777() {
        assertTrue(updateInfoAccountPage.checkDisplayFirstName(), "Verify testcase 9777 is fail");
    }

    @Test(priority = 5)
    public void verifyUI9778() {
        assertTrue(updateInfoAccountPage.checkDisplayLastName(), "Verify testcase 9778 is fail");
    }

    @Test(priority = 6)
    public void verifyUI9779() {
        assertTrue(updateInfoAccountPage.checkDisplayCountryCode(), "Verify testcase 9778 is fail");
    }

    @Test(priority = 7)
    public void verifyUI9780() {
        assertTrue(updateInfoAccountPage.checkDisplayPhoneInput(), "Verify testcase 9778 is fail");
    }

    @Test(priority = 8)
    public void verifyUI9781() {
        assertTrue(updateInfoAccountPage.checkDisplayAlertFirstName(), "Verify testcase 9778 is fail");
    }

    @Test(priority = 9)
    public void verifyUI9782() {
        assertTrue(updateInfoAccountPage.checkDisplayAlertPhoneInput(), "Verify testcase 9778 is fail");
    }

    @Test(priority = 10)
    public void verifyUI9783() {
        assertTrue(updateInfoAccountPage.checkDisplayEmail(), "Verify testcase 9778 is fail");
    }

    @Test(priority = 11)
    public void verifyUI9784() {
        assertTrue(updateInfoAccountPage.checkDisplayAlertEmail(), "Verify testcase 9778 is fail");
    }

    @Test(priority = 12)
    public void verifyUI9785() {
        assertTrue(updateInfoAccountPage.checkDisplayDateOfBirth(), "Verify testcase 9778 is fail");
    }

    @Test(priority = 13)
    public void verifyUI9786() {
        assertTrue(updateInfoAccountPage.checkDisplayGenderOption(), "Verify testcase 9778 is fail");
    }

    @Test(priority = 14)
    public void verifyUI9787() {
        assertTrue(updateInfoAccountPage.checkDisplayGenderOption(), "Verify testcase 9778 is fail");
    }

    @Test(priority = 15)
    public void verifyFU9797() {
        updateInfoAccountPage.updateInfo();
        updateInfoAccountPage.navigateToUpdateInfo();
    }

    @Test(priority = 16)
    public void verifyFU9798() {
        assertEquals(updateInfoAccountPage.inputLongFirstName(), LONG_TEXT_1, "Verify testcase 9778 is fail");
    }

    @Test(priority = 17)
    public void verifyFU9799() {
        assertEquals(updateInfoAccountPage.inputLongLastName(), LONG_TEXT_1, "Verify testcase 9779 is fail");
    }

    @Test(priority = 18)
    public void verifyFU9800() {
        assertEquals(updateInfoAccountPage.inputLongLastName(), LONG_TEXT_1, "Verify testcase 9779 is fail");
    }

    @Test(priority = 19)
    public void verifyFU9801() {
        assertEquals(updateInfoAccountPage.inputInvalidEmail(),INVALID_EMAIL_ALERT , "Verify testcase 9779 is fail");
        updateInfoAccountPage.inputValidEmail();
    }

    @Test(priority = 20)
    public void verifyFU9802() {
        original  = updateInfoAccountPage.getTextDateOfBirthField();
        updateInfoAccountPage.clickCancelInDateOfBirthField();
        updateInfoAccountPage.clickConfirmButtonInDateOfBirth();
        expected = updateInfoAccountPage.getTextDateOfBirthField();
//        assertEquals(updateInfoAccountPage.getTextDateOfBirthField(), updateInfoAccountPage.getCurrentDate());
        assertEquals(original, expected, "Verify testcase 9802 is fail ");
    }

    @Test(priority = 21)
    public void verifyFU9803() {
        updateInfoAccountPage.clickFemaleButton();
    }
    @Test(priority = 22)
    public void verifyFU9804() {
        updateInfoAccountPage.clickMaleButton();
        updateInfoAccountPage.clickSaveBtn();
    }
//    9800
    @Test(priority = 23)
    public void verifyFU9805() {
        updateInfoAccountPage.navigateToUpdateInfo();
        updateInfoAccountPage.changePhoneNumber();
    }

}

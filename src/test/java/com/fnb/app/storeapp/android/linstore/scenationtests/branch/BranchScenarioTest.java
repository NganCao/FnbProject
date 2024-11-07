package com.fnb.app.storeapp.android.linstore.scenationtests.branch;

import com.fnb.app.storeapp.android.linstore.pages.branch.BranchDataTest;
import com.fnb.app.storeapp.android.linstore.pages.branch.BranchPage;
import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.setup.BaseTest;
import com.fnb.utils.helpers.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.fnb.app.storeapp.android.linstore.pages.branch.BranchDataTest.*;
import static com.fnb.app.storeapp.android.linstore.pages.branch.BranchPage.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class BranchScenarioTest extends BaseTest {
    BranchPage branchPage;
    LoginPageLinStore loginPageLinStore;
    SoftAssert softAssert;
    static String currentBranchName;

    @BeforeClass
    public void navigateToPage() {
        branchPage = homePageLinStore.navigateToBranch();
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
        softAssert = new SoftAssert();
    }

    @Test(priority = 0)
    public void verifyShowBranchInformation() {
        Log.info("Running Verify function change Branch in home page");
        loginPageLinStore.splashScreen();
        branchPage.checkAPIBranchDetail();
        branchPage.checkAPIAllBranch();
    }

    @Test(priority = 1)
    public void VerifyTestCase01() {
        Log.info("Running Verify testcase change branch 01");
        branchPage.navigateToProductListPage();
        softAssert.assertEquals(branchPage.getTextBranchNameProductList(), "Chi nhánh " + checkApiBranchNameProductList(BranchDataTest.ADDRESS), "Verify testcase 01 is fail");
        softAssert.assertEquals(branchPage.getTextBranchAddressProductListText(), checkAPIBranchAddressProductList(BranchDataTest.ADDRESS), "Verify testcase 01 is fail");
//        softAssert.assertEquals(branchPage.getTextBranchDistanceProductList(), checkApiBranchDistanceProductList(BranchDataTest.ADDRESS), "Verify testcase 01 is fail");
        System.out.println(checkApiBranchAddressProductListText(BranchDataTest.ADDRESS));
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void VerifyTestCase02() {
        Log.info("Running Verify testcase check display text branch name product list");
        assertTrue(branchPage.checkDisplayBranchNameProductList(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 3)
    public void VerifyTestCase03() {
        Log.info("Running Verify testcase check display text branch address product list");
        assertTrue(branchPage.checkDisplayBranchAddressProductListText(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 4)
    public void VerifyTestCase04() {
        Log.info("Running Verify testcase check display text branch image product list");
        assertTrue(branchPage.checkDisplayBranchImgProductList(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 5)
    public void VerifyTestCase05() {
        Log.info("Running Verify testcase check display  branch image product list");
        assertTrue(branchPage.checkDisplayBranchImgProductList(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 6)
    public void VerifyTestCase06() {
        Log.info("Running Verify testcase check display branch icon distance product list");
        assertTrue(branchPage.checkDisplayBranchIconDistanceProductList(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 7)
    public void VerifyTestCase07() {
        Log.info("Running Verify testcase check display branch distance product list");
        assertTrue(branchPage.checkDisplayBranchDistanceProductList(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 8)
    public void VerifyTestCase08() {
        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayBranchDistanceProductListText(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 9)
    public void VerifyTestCase09() {
        branchPage.navigateToBranchManagement();
        branchPage.scrollScreen01();
        currentBranchName = branchPage.getTextBranchNameButton();
        assertEquals(currentBranchName, checkApiBranchNameProductList(BranchDataTest.ADDRESS), "Testcase 09 01 fail");
//        assertEquals(branchPage.getTextBranchAddressManagementText1(), checkApiBranchAddressProductListText(BranchDataTest.ADDRESS), "Testcase 09 02 fail");
//        softAssert.assertEquals(branchPage.getTextDistanceText(),branchPage.checkApiBranchDistanceProductList(BranchDataTest.ADDRESS), "Testcase check display text branch name product list fail");
//        assertEquals(branchPage.getTextPhoneNumberBranch(), checkApiBranchPhoneNumber(BranchDataTest.ADDRESS), "Testcase 09 03 fail");
//        Log.info("Running Verify testcase check display text branch distance product list");
//        assertTrue(branchPage.checkDisplayMapView(), "Testcase  fail");
    }
    @Test(priority = 9)
    public void VerifyTestCase090() {
//        branchPage.navigateToBranchManagement();
//        branchPage.scrollScreen01();
//        currentBranchName = branchPage.getTextBranchNameButton();
//        assertEquals(currentBranchName, checkApiBranchNameProductList(BranchDataTest.ADDRESS), "Testcase 09 01 fail");
        assertEquals(branchPage.getTextBranchAddressManagementText1(), checkApiBranchAddressProductListText(BranchDataTest.ADDRESS), "Testcase 09 02 fail");
//        softAssert.assertEquals(branchPage.getTextDistanceText(),branchPage.checkApiBranchDistanceProductList(BranchDataTest.ADDRESS), "Testcase check display text branch name product list fail");
//        assertEquals(branchPage.getTextPhoneNumberBranch(), checkApiBranchPhoneNumber(BranchDataTest.ADDRESS), "Testcase 09 03 fail");
//        Log.info("Running Verify testcase check display text branch distance product list");
//        assertTrue(branchPage.checkDisplayMapView(), "Testcase  fail");
    }

    @Test(priority = 9)
    public void VerifyTestCase091() {
//        branchPage.navigateToBranchManagement();
//        branchPage.scrollScreen01();
//        currentBranchName = branchPage.getTextBranchNameButton();
//        assertEquals(currentBranchName, checkApiBranchNameProductList(BranchDataTest.ADDRESS), "Testcase 09 01 fail");
//        assertEquals(branchPage.getTextBranchAddressManagementText1(), checkApiBranchAddressProductListText(BranchDataTest.ADDRESS), "Testcase 09 02 fail");
//        softAssert.assertEquals(branchPage.getTextDistanceText(),branchPage.checkApiBranchDistanceProductList(BranchDataTest.ADDRESS), "Testcase check display text branch name product list fail");
        assertEquals(branchPage.getTextPhoneNumberBranch(), checkApiBranchPhoneNumber(BranchDataTest.ADDRESS), "Testcase 09 03 fail");
//        Log.info("Running Verify testcase check display text branch distance product list");
//        assertTrue(branchPage.checkDisplayMapView(), "Testcase  fail");
    }
    @Test(priority = 9)
    public void VerifyTestCase092() {
//        branchPage.navigateToBranchManagement();
//        branchPage.scrollScreen01();
//        currentBranchName = branchPage.getTextBranchNameButton();
//        assertEquals(currentBranchName, checkApiBranchNameProductList(BranchDataTest.ADDRESS), "Testcase 09 01 fail");
//        assertEquals(branchPage.getTextBranchAddressManagementText1(), checkApiBranchAddressProductListText(BranchDataTest.ADDRESS), "Testcase 09 02 fail");
//        softAssert.assertEquals(branchPage.getTextDistanceText(),branchPage.checkApiBranchDistanceProductList(BranchDataTest.ADDRESS), "Testcase check display text branch name product list fail");
//        assertEquals(branchPage.getTextPhoneNumberBranch(), checkApiBranchPhoneNumber(BranchDataTest.ADDRESS), "Testcase 09 03 fail");
//        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayMapView(), "Testcase  fail");
    }

    @Test(priority = 10)
    public void VerifyTestCase10() {
        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayIconDropdownBranch(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 11)
    public void VerifyTestCase11() {
        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayIconHome(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 12)
    public void VerifyTestCase12() {
        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayBranchAddressManagementText1(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 13)
    public void VerifyTestCase13() {
        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayRatingText(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 15)
    public void VerifyTestCase15() {
        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayIconDistance(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 16)
    public void VerifyTestCase16() {
        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayDistanceText1(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 17)
    public void VerifyTestCase17() {
        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayContactTitle(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 18)
    public void VerifyTestCase18() {
        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayPhoneIcon1(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 19)
    public void VerifyTestCase19() {
        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayPhoneNumberBranch(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 20)
    public void VerifyTestCase20() {
        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayImgBackground(), "Testcase check display text branch name product list fail");
    }

    @Test(priority = 21)
    public void VerifyTestCase21() {
        Log.info("Running Verify testcase check display text branch distance product list");
        branchPage.clickIconDropdownBranch();
        assertTrue(branchPage.checkDisplayListIdOnBranch(), "Testcase check display text branch name product list fail");
        branchPage.selectBranchOptions();
        branchPage.scrollScreen01();
        System.out.println(branchPage.getTextBranchAddressManagementText1());
        currentBranchName = branchPage.getTextBranchNameButton();
        assertEquals(currentBranchName, checkApiBranchInfo(currentBranchName, KEY_STORE_NAME), "Testcase in change branch page 21 01 fail");
        assertEquals(branchPage.getTextBranchAddressManagementText1(), checkApiBranchInfo(currentBranchName, KEY_ADDRESS), "Testcase in change branch page 21 02 fail");
        assertEquals(branchPage.getTextPhoneNumberBranch(), checkApiBranchInfo(currentBranchName, KEY_PHONE_NUMBER), "Testcase in change branch page 21 03 fail");
        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayMapView(), "Testcase  fail");

    }

    @Test(priority = 22)
    public void VerifyTestCase22() {
        Log.info("Running Verify testcase check display text branch distance product list");
        branchPage.clickIconDropdownBranch();
        assertTrue(branchPage.checkDisplayListIdOnBranch(), "Testcase check display text branch name product list fail");
        branchPage.selectBranchOptions();
        branchPage.scrollScreen01();
        currentBranchName = branchPage.getTextBranchNameButton();
        assertEquals(branchPage.getTextBranchNameButton(), checkApiBranchInfo(currentBranchName, KEY_STORE_NAME), "Testcase in change branch page 21 01 fail");
        assertEquals(branchPage.getTextBranchAddressManagementText1(), checkApiBranchInfo(currentBranchName, KEY_ADDRESS), "Testcase in change branch page 21 02 fail");
        assertEquals(branchPage.getTextPhoneNumberBranch(), checkApiBranchInfo(currentBranchName, KEY_PHONE_NUMBER), "Testcase in change branch page 21 03 fail");
        Log.info("Running Verify testcase check display text branch distance product list");
        assertTrue(branchPage.checkDisplayMapView(), "Testcase  fail");

    }


    @Test(priority = 23)
    public void VerifyTestCase23() {
        Log.info("Running Verify testcase check display text branch distance product list");
        branchPage.clickChangeBranchButton();
        assertEquals(branchPage.getTextBranchNameProductList(), "Chi nhánh "+checkApiBranchProductList(currentBranchName, KEY_BRANCH_NAME), "Verify testcase 23 01 is fail");
        assertEquals(branchPage.getTextBranchAddressProductListText(), checkApiBranchProductList(currentBranchName, KEY_ADDRESS_INFO), "Verify testcase 23 01 is fail");
    }
}

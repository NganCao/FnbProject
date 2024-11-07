package com.fnb.app.storeapp.android.linstore.scenationtests.addressmanagement.homepage.pickup;

import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.pickup.PickUpManagementPage;
import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.storeapp.android.linstore.pages.loyaltypoint.LoyaltyPointPage;
import com.fnb.app.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.fnb.app.storeapp.android.linstore.pages.addressmanagement.pickup.PickUpManagementDataTest.ADDRESS;

public class PickUpManagementScenarioTest extends BaseTest {
    PickUpManagementPage pickUpManagementPage;
    LoginPageLinStore loginPageLinStore;
    AddressManagementListPage addressManagementListPage;
    LoyaltyPointPage loyaltyPointPage;
    public static String storeName;
    public static String addressUser;
    static SoftAssert softAssert;

    @BeforeClass
    public void navigateToPage() {
        pickUpManagementPage = homePageLinStore.navigateToPickUpManagementPage();
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
        addressManagementListPage = homePageLinStore.navigateToAddressManagementListPage();
        loyaltyPointPage = homePageLinStore.navigateToLoyaltyPointPage();
        softAssert = new SoftAssert();
    }

    @Test(priority = 0)
    public void verifyIconPickUp() {
        loginPageLinStore.splashScreen();
        pickUpManagementPage.clickDeliveryHeader();
        loginPageLinStore.loginSuccess();
        pickUpManagementPage.clickDeliveryHeader();
        Assert.assertTrue(pickUpManagementPage.checkDisplayIconPickUp(), "Test case fail");
    }

    @Test(priority = 1)
    public void verifyPickUpTitle() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayPickUpTitle(), "Test case fail");
    }

    @Test(priority = 2)
    public void verifyPickUpAddressText() {
        String s = pickUpManagementPage.getTextPickUpAddressText();
        String s1 = pickUpManagementPage.checkApiGetStoreName(ADDRESS);
        addressUser = addressManagementListPage.getTextDeliveryAddressText();
        System.out.println(addressUser);
        softAssert.assertTrue(pickUpManagementPage.checkDisplayPickUpAddressText(), "Test case fail");
        softAssert.assertEquals(s, s1, "test case is fail 2");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void verifyBranchTitle() {
        pickUpManagementPage.clickPickUpField();
        Assert.assertTrue(pickUpManagementPage.checkDisplayBranchTitle(), "Test case fail");
    }

    @Test(priority = 4)
    public void verifyCloseIcon() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayCloseIcon(), "Test case fail");
    }

    @Test(priority = 5)
    public void verifyNearestBranchTitle() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayNearestBranchTitle(), "Test case fail");
    }

    @Test(priority = 6)
    public void verifyNearestBranchToYou() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayNearestBranchToYou(), "Test case fail");
    }

    @Test(priority = 7)
    public void verifyCheckBranchIcon() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayCheckBranchIcon(), "Test case fail");
    }

    @Test(priority = 8)
    public void verifyNearestBranchName() {
        storeName = pickUpManagementPage.getTextNearestBranchName();
        Assert.assertTrue(pickUpManagementPage.checkDisplayNearestBranchName(), "Test case fail");
        Assert.assertEquals(pickUpManagementPage.getTextNearestBranchName(), pickUpManagementPage.checkApiGetNearestBranchInfo(ADDRESS, "storeName"));
    }

    @Test(priority = 9)
    public void verifyNearestBranchAddress() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayNearestBranchAddress(), "Test case fail");
        Assert.assertEquals(pickUpManagementPage.getTextNearestBranchAddress(), pickUpManagementPage.checkApiGetNearestAddress(ADDRESS), "testcase is fail");
    }

    @Test(priority = 10)
    public void verifyNearestBranchDistance() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayNearestBranchDistance(), "Test case fail");
//        Assert.assertEquals((String) pickUpManagementPage.getTextNearestBranchDistance(), pickUpManagementPage.checkApiGetNearestBranchInfo(ADDRESS, "distance"));
    }

    @Test(priority = 11)
    public void verifyOtherBranchTitle() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchTitle(), "Test case fail");
    }

    @Test(priority = 12)
    public void verifyOtherBranchName0() {
        storeName = pickUpManagementPage.getTextOtherBranchName0();
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName0(), "Test case fail");
        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
    }

    @Test(priority = 13)
    public void verifyOtherBranchAddress0() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress0(), "Test case fail");
        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress0(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
    }

    @Test(priority = 14)
    public void verifyOtherBranchDistance0() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance0(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance0(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
    }

    @Test(priority = 15)
    public void verifyOtherBranchName1() {
        storeName = pickUpManagementPage.getTextOtherBranchName1();
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName1(), "Test case fail");
        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
    }

    @Test(priority = 16)
    public void verifyOtherBranchAddress1() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress1(), "Test case fail");
        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress1(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
    }

    @Test(priority = 17)
    public void verifyOtherBranchDistance1() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance1(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance1(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
    }

    @Test(priority = 18)
    public void verifyOtherBranchName2() {
        storeName = pickUpManagementPage.getTextOtherBranchName2();
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName2(), "Test case fail");
        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
    }

    @Test(priority = 19)
    public void verifyOtherBranchAddress2() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress2(), "Test case fail");
        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress2(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
    }

    @Test(priority = 20)
    public void verifyOtherBranchDistance2() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance2(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance2(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
    }

    @Test(priority = 21)
    public void verifyOtherBranchName3() {
        pickUpManagementPage.scrollInBranch();
        storeName = pickUpManagementPage.getTextOtherBranchName3();
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName3(), "Test case fail");
        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
    }

    @Test(priority = 22)
    public void verifyOtherBranchAddress3() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress3(), "Test case fail");
        System.out.println(pickUpManagementPage.getTextOtherBranchAddress3());
        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress3(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
    }

    @Test(priority = 23)
    public void verifyOtherBranchDistance3() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance3(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance3(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
    }

    @Test(priority = 24)
    public void verifyOtherBranchName4() {
        storeName = pickUpManagementPage.getTextOtherBranchName4();
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName4(), "Test case fail");
        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
    }

    @Test(priority = 25)
    public void verifyOtherBranchAddress4() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress4(), "Test case fail");
        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress4(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));

    }

    @Test(priority = 26)
    public void verifyOtherBranchDistance4() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance4(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance4(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
    }

    @Test(priority = 27)
    public void verifyOtherBranchName5() {
        pickUpManagementPage.scrollInBranch1();
        storeName = pickUpManagementPage.getTextOtherBranchName5();
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName5(), "Test case fail");
        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
    }

    @Test(priority = 28)
    public void verifyOtherBranchAddress5() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress5(), "Test case fail");
        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress5(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
    }

    @Test(priority = 29)
    public void verifyOtherBranchDistance5() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance5(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance5(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
    }

    @Test(priority = 30)
    public void verifyOtherBranchName6() {
        storeName = pickUpManagementPage.getTextOtherBranchName6();
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName6(), "Test case fail");
        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
    }

    @Test(priority = 31)
    public void verifyOtherBranchAddress6() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress6(), "Test case fail");
        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress6(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
    }

    @Test(priority = 32)
    public void verifyOtherBranchDistance6() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance6(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance6(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
    }

    @Test(priority = 33)
    public void verifyOtherBranchName7() {
        storeName = pickUpManagementPage.getTextOtherBranchName7();
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName7(), "Test case fail");
        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
    }

    @Test(priority = 34)
    public void verifyOtherBranchAddress7() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress7(), "Test case fail");
        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress7(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
    }

    @Test(priority = 35)
    public void verifyOtherBranchDistance7() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance7(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance7(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
    }

    @Test(priority = 36)
    public void verifyOtherBranchName8() {
        pickUpManagementPage.scrollInBranch6To7();
        storeName = pickUpManagementPage.getTextOtherBranchName8();
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName8(), "Test case fail");
        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
    }

    @Test(priority = 37)
    public void verifyOtherBranchAddress8() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress8(), "Test case fail");
        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress8(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
    }

    @Test(priority = 38)
    public void verifyOtherBranchDistance8() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance8(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance8(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
    }

    @Test(priority = 39)
    public void verifyOtherBranchName9() {
        //TODO
        storeName = pickUpManagementPage.getTextOtherBranchName9();
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName9(), "Test case fail");
        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
    }

    @Test(priority = 40)
    public void verifyOtherBranchAddress9() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress9(), "Test case fail");
        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress9(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
    }

    @Test(priority = 41)
    public void verifyOtherBranchDistance9() {
//        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance9(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance9(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
    }

    @Test(priority = 42)
    public void verifyOtherBranchName10() {
        pickUpManagementPage.scrollInBranch8To9();
        storeName = pickUpManagementPage.getTextOtherBranchName10();
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName10(), "Test case fail");
        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
    }

    @Test(priority = 43)
    public void verifyOtherBranchAddress10() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress10(), "Test case fail");
        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress10(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
    }

    @Test(priority = 44)
    public void verifyOtherBranchDistance10() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance10(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance10(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
    }

    @Test(priority = 45)
    public void verifyOtherBranchName11() {
        storeName = pickUpManagementPage.getTextOtherBranchName11();
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName11(), "Test case fail");
        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
    }

    @Test(priority = 46)
    public void verifyOtherBranchAddress11() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress11(), "Test case fail");
        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress11(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
    }

    @Test(priority = 47)
    public void verifyOtherBranchDistance11() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance11(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance11(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
    }

//    @Test(priority = 48)
//    public void verifyOtherBranchName12() {
//        pickUpManagementPage.scrollInBranch2();
//        pickUpManagementPage.scrollInBranch3();
//        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName12(), "Test case fail");
//        storeName = pickUpManagementPage.getTextOtherBranchName12();
//        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
//    }
//
//    @Test(priority = 49)
//    public void verifyOtherBranchAddress12() {
//        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress12(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress12(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
//    }
//
//    @Test(priority = 50)
//    public void verifyOtherBranchDistance12() {
//        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance12(), "Test case fail");
////        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance12(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
//    }
//
//    @Test(priority = 51)
//    public void verifyOtherBranchName13() {
//        storeName = pickUpManagementPage.getTextOtherBranchName13();
//        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName13(), "Test case fail");
//        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
//    }
//
//    @Test(priority = 52)
//    public void verifyOtherBranchAddress13() {
//        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress13(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress13(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
//    }
//
//    @Test(priority = 53)
//    public void verifyOtherBranchDistance13() {
//        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance13(), "Test case fail");
////        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance13(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
//    }
//
//    @Test(priority = 54)
//    public void verifyOtherBranchName14() {
//        storeName = pickUpManagementPage.getTextOtherBranchName14();
//        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchName14(), "Test case fail");
//        Assert.assertEquals(storeName, pickUpManagementPage.checkApiGetBranchInfo(storeName, "storeName", addressUser));
//    }
//
//    @Test(priority = 55)
//    public void verifyOtherBranchAddress14() {
//        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchAddress14(), "Test case fail");
//        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchAddress14(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "address", addressUser));
//    }
//
//    @Test(priority = 56)
//    public void verifyOtherBranchDistance14() {
//        Assert.assertTrue(pickUpManagementPage.checkDisplayOtherBranchDistance14(), "Test case fail");
////        Assert.assertEquals(pickUpManagementPage.getTextOtherBranchDistance14(), pickUpManagementPage.checkApiGetBranchInfo(storeName, "distance", addressUser));
//    }
}

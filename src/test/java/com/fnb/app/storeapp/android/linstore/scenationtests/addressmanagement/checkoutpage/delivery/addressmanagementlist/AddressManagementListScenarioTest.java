package com.fnb.app.storeapp.android.linstore.scenationtests.addressmanagement.checkoutpage.delivery.addressmanagementlist;

import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addnewaddress.AddNewAddressPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListDataTest;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.pickup.PickUpManagementPage;
import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addnewaddress.AddNewAddressDataTest.KEY_ADDRESS;
import static com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addnewaddress.AddNewAddressDataTest.KEY_NAME;
import static com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListDataTest.*;
import static com.fnb.app.storeapp.android.linstore.pages.addressmanagement.pickup.PickUpManagementDataTest.DELIVERY_TYPE;

public class AddressManagementListScenarioTest extends BaseTest {
    AddressManagementListPage addressManagementListPage;
    LoginPageLinStore loginPageLinStore;
    PickUpManagementPage pickUpManagementPage;
    AddNewAddressPage addNewAddressPage;
    String userAddress;

    @BeforeClass
    public void navigateToPage() {
        addressManagementListPage = homePageLinStore.navigateToAddressManagementListPage();
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
        pickUpManagementPage = homePageLinStore.navigateToPickUpManagementPage();
        addNewAddressPage = homePageLinStore.navigateToAddNewPage();
    }

    @Test(priority = 0)
    public void verifyDeliveryDialog() {
        loginPageLinStore.splashScreen();
        pickUpManagementPage.navigateToCheckoutPage();
        pickUpManagementPage.changeOrderType();
        Assert.assertTrue(addressManagementListPage.checkDisplayDeliveryDialog(), "testcase is fail");
    }

    @Test(priority = 1)
    public void verifyDeliveryIcon() {
        Assert.assertTrue(addressManagementListPage.checkDisplayIconDelivery(), "testcase is fail");
    }

    @Test(priority = 2)
    public void verifyDeliveryTitleDialog() {
        Assert.assertTrue(addressManagementListPage.checkDisplayDeliveryTitle(), "testcase is fail");
    }

    @Test(priority = 4)
    public void verifyDeliveryAddressText() {
        userAddress = addressManagementListPage.getTextDeliveryAddressText();
        Assert.assertTrue(addressManagementListPage.checkDisplayDeliveryAddressText(), "testcase is fail");
    }

    @Test(priority = 5)
    public void verifyBackButton() {
        addressManagementListPage.clickDeliveryField();
        pickUpManagementPage.navigateChangeAddress();
        Assert.assertTrue(addressManagementListPage.checkDisplayBackButton(), "testcase is fail");
    }

    @Test(priority = 6)
    public void verifyDeliveryTitle() {
        Assert.assertTrue(addressManagementListPage.checkDisplayTitle(), "testcase is fail");
    }

    @Test(priority = 7)
    public void verifyIconSearchMap() {
        Assert.assertTrue(addressManagementListPage.checkDisplayIconSearchMap(), "testcase is fail");
    }

    @Test(priority = 8)
    public void verifySearchResultMatchAddressText() {
        Assert.assertTrue(addressManagementListPage.checkDisplaySearchResultMatchAddressText(), "testcase is fail");
    }

    @Test(priority = 9)
    public void verifySearchDeliveryIcon() {
        Assert.assertTrue(addressManagementListPage.checkDisplaySearchDeliveryIcon(), "testcase is fail");
    }

    @Test(priority = 10)
    public void verifyCurrentAddressField() {
        Assert.assertTrue(addressManagementListPage.checkDisplayCurrentAddressField(), "testcase is fail");
    }

    @Test(priority = 11)
    public void verifyIconGps() {
        Assert.assertTrue(addressManagementListPage.checkDisplayIconGps(), "testcase is fail");
    }

    @Test(priority = 12)
    public void verifyTextCurrentLocation() {
        Assert.assertTrue(addressManagementListPage.checkDisplayTextCurrentLocation(), "testcase is fail");
    }

    @Test(priority = 13)
    public void verifyTextUserAddress() {
        Assert.assertTrue(addressManagementListPage.checkDisplayTextUserAddress(), "testcase is fail");
    }

    @Test(priority = 14)
    public void verifyHomeAddressField0() {
        Assert.assertTrue(addressManagementListPage.checkDisplayHomeAddressField0(), "testcase is fail");
    }

    @Test(priority = 15)
    public void verifyIconHomeAddress0() {
        Assert.assertTrue(addressManagementListPage.checkDisplayIconHomeAddress0(), "testcase is fail");
    }

    @Test(priority = 16)
    public void verifyHomeAddressTitle0() {
        Assert.assertTrue(addressManagementListPage.checkDisplayHomeAddressTitle0(), "testcase is fail");
    }

    @Test(priority = 17)
    public void verifyWorkAddressField1() {
        Assert.assertTrue(addressManagementListPage.checkDisplayWorkAddressField1(), "testcase is fail");
    }

    @Test(priority = 18)
    public void verifyIconWorkAddress1() {
        Assert.assertTrue(addressManagementListPage.checkDisplayIconHomeAddress1(), "testcase is fail");
    }

    @Test(priority = 19)
    public void verifyWorkAddressTitle1() {
        Assert.assertTrue(addressManagementListPage.checkDisplayWorkAddressTitle1(), "testcase is fail");
    }

    @Test(priority = 20)
    public void verifyOtherAddressField2() {
        Assert.assertTrue(addressManagementListPage.checkDisplayOtherAddressField2(), "testcase is fail");
    }

    @Test(priority = 21)
    public void verifyIconOtherAddress2() {
        Assert.assertTrue(addressManagementListPage.checkDisplayIconOtherAddress2(), "testcase is fail");
    }

    @Test(priority = 22)
    public void verifyOtherAddressTitle2() {
        Assert.assertTrue(addressManagementListPage.checkDisplayOtherAddressTitle2(), "testcase is fail");
    }

    @Test(priority = 23)
    public void verifyTheNearestBranchToYouTitle() {
        Assert.assertTrue(addressManagementListPage.checkDisplayTheNearestBranchToYouTitle(), "testcase is fail");
    }

    @Test(priority = 24)
    public void verifyCheckBranchIcon() {
        Assert.assertTrue(addressManagementListPage.checkDisplayCheckBranchIcon(), "testcase is fail");
    }

    @Test(priority = 25)
    public void verifyCurrentBranchImg() {
        Assert.assertTrue(addressManagementListPage.checkDisplayCurrentBranchImg(), "testcase is fail");
    }

    @Test(priority = 26)
    public void verifyCurrentBranchName() {
        Assert.assertTrue(addressManagementListPage.checkDisplayCurrentBranchName(), "testcase is fail");
    }

    @Test(priority = 27)
    public void verifyCurrentBranchAddress() {
        Assert.assertTrue(addressManagementListPage.checkDisplayCurrentBranchAddress(), "testcase is fail");
        addressManagementListPage.scrollInBranch();
    }

    @Test(priority = 28)
    public void verifyCurrentBranchDistance() {
        Assert.assertTrue(addressManagementListPage.checkDisplayCurrentBranchDistance(), "testcase is fail");
    }


    @Test(priority = 30)
    public void verifyArrowDownIcon() {
        Assert.assertTrue(addressManagementListPage.checkDisplayArrowDownIcon(), "testcase is fail");
    }

    @Test(priority = 31)
    public void verifySearchResultMatchAddress0() {
        addressManagementListPage.fillToSearchResultMatchAddress();
        Assert.assertTrue(addressManagementListPage.checkDisplaySearchResultMatchAddress0(), "testcase is fail");
    }

    @Test(priority = 32)
    public void verifySearchResultMatchAddress1() {
        Assert.assertTrue(addressManagementListPage.checkDisplaySearchResultMatchAddress0(), "testcase is fail");
        addressManagementListPage.clearTextSearchResultMatchAddress();
        addressManagementListPage.scrollInBranch02();
    }

    @Test(priority = 33)
    public void verifyIconEditAnAddress() {
        addressManagementListPage.swipeHomeAddressField();
        Assert.assertTrue(addressManagementListPage.checkDisplayIconEditAnAddress(), "testcase is fail");
    }

    @Test(priority = 34)
    public void verifyIconDeleteAnAddress() {
        Assert.assertTrue(addressManagementListPage.checkDisplayIconDeleteAnAddress(), "testcase is fail");
    }

    @Test(priority = 35)
    public void verifyPlaceHolderSearchResultMatchAddressText() {
        Assert.assertEquals(addressManagementListPage.getTextSearchResultMatchAddressText(), "Delivery to?", "Test case fail");
    }

    @Test(priority = 36)
    public void verifyNameAddressHome() {
        String expectedResult = addressManagementListPage.checkApiUserAddress(NAME_ADDRESS_HOME, AddressManagementListDataTest.ADDRESS_KEY);
        String actualResult = addressManagementListPage.getHomeAddressText();
        Assert.assertEquals(actualResult, expectedResult, "Test case is fail");
    }

    @Test(priority = 37)
    public void verifyNameAddressWork() {
        String expectedResult = addressManagementListPage.checkApiUserAddress(NAME_ADDRESS_WORK, AddressManagementListDataTest.ADDRESS_KEY);
        String actualResult = addressManagementListPage.getWorkAddressText();
        Assert.assertEquals(actualResult, expectedResult, "Test case is fail");
    }

    @Test(priority = 38)
    public void verifyNameAddressOther() {
        String expectedResult = addressManagementListPage.checkApiUserAddress(NAME_ADDRESS_OTHER_1, AddressManagementListDataTest.ADDRESS_KEY);
        String actualResult = addressManagementListPage.getOtherAddressText();
        Assert.assertEquals(actualResult, expectedResult, "Test case is fail");
        addressManagementListPage.scrollInBranch();
    }

    @Test(priority = 39)
    public void verifyNearestStoreName() {
        Assert.assertEquals(addressManagementListPage.getTextCurrentBranchName(), addressManagementListPage.checkApiTheNearestStoreName(userAddress), "Test case is fail");
    }

    @Test(priority = 39)
    public void verifyNearestAddress() {
        Assert.assertEquals(addressManagementListPage.getTextCurrentBranchAddress(), addressManagementListPage.checkApiTheNearestAddress(userAddress), "Test case is fail");
    }

    @Test(priority = 39)
    public void verifyNearestDistance() {
        Assert.assertEquals(addressManagementListPage.getTextCurrentBranchDistance(), addressManagementListPage.checkApiTheNearestDistance(userAddress), "Test case is fail");
    }

    @Test(priority = 40)
    public void verifyCheckDeliveryType() {
        addressManagementListPage.clickHomeAddress();
        Assert.assertTrue(pickUpManagementPage.checkDisplayDeliveryPickUpText(), "Testcase is fail");
        String deliveryType = pickUpManagementPage.getTextDeliveryPickUpText();
        Assert.assertEquals(deliveryType, DELIVERY_TYPE, "Testcase is fail");
    }

    @Test(priority = 41)
    public void verifyCheckAddressName() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayLocationAddressText(), "Testcase is fail");
        String addressName = pickUpManagementPage.getTextLocationAddressText();
        Assert.assertEquals(addressName, addNewAddressPage.checkApiAddressDetail(addressName, KEY_NAME));
    }

    @Test(priority = 42)
    public void verifyCheckAddressUser() {
        Assert.assertTrue(pickUpManagementPage.checkDisplayLocationAddressText(), "Testcase is fail");
        String addressName = pickUpManagementPage.getTextLocationAddressText();
        String address = pickUpManagementPage.getTextLocationAddressText();
        Assert.assertEquals(address, addNewAddressPage.checkApiAddressDetail(addressName, KEY_ADDRESS));
    }


}

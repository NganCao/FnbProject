package com.fnb.app.storeapp.android.linstore.scenationtests.addressmanagement.checkoutpage.delivery.addnewaddress;

import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addnewaddress.AddNewAddressPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.pickup.PickUpManagementPage;
import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addnewaddress.AddNewAddressDataTest.*;

public class AddNewAddressScenarioTest extends BaseTest {
    AddNewAddressPage addNewAddressPage;
    LoginPageLinStore loginPageLinStore;
    AddressManagementListPage addressManagementListPage;
    PickUpManagementPage pickUpManagementPage;
    static String actualAddress;
    static String expectedAddress;
    static String expectedAddressDetail;
    static String expectedAddressNote;
    static String expectedAddressName;

    @BeforeClass
    public void navigateToPage() {
        addNewAddressPage = homePageLinStore.navigateToAddNewPage();
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
        addressManagementListPage = homePageLinStore.navigateToAddressManagementListPage();
        pickUpManagementPage = homePageLinStore.navigateToPickUpManagementPage();
    }

    @Test(priority = 0)
    public void verifyBackBtn() {
        pickUpManagementPage.navigateToCheckoutPage();
        pickUpManagementPage.changeOrderType();
        addressManagementListPage.clickDeliveryFieldInCheckoutPage();
        addNewAddressPage.ClickNewHomeAddress();
        Assert.assertTrue(addNewAddressPage.checkDisplayBackButton(), "Testcase is fail");
    }

    @Test(priority = 1)
    public void verifyTitle() {
        Assert.assertTrue(addNewAddressPage.checkDisplayTitle(), "Testcase is fail");
    }

    @Test(priority = 2)
    public void verifyIconSearch() {
        Assert.assertTrue(addNewAddressPage.checkDisplayIconSearch(), "Testcase is fail");
    }

    @Test(priority = 3)
    public void verifySearchResultMatchAddressText() {
        Assert.assertTrue(addNewAddressPage.checkDisplayDeliveryText(), "Testcase is fail");
    }

    @Test(priority = 4)
    public void verifyPlaceHolderSearchResultMatchAddressText() {
        Assert.assertEquals(addNewAddressPage.getTextPlaceHolderSearchResultMatchAddressText(), SEARCH_RESULT_MATCH_ADDRESS_TEXT, "Testcase is fail");
    }

    @Test(priority = 5)
    public void verifyIconGgSearch() {
        Assert.assertTrue(addNewAddressPage.checkDisplayIconGgSearch(), "Testcase is fail");
    }

    @Test(priority = 6)
    public void verifyTextAddressInMind() {
        Assert.assertTrue(addNewAddressPage.checkDisplayTextAddressInMind(), "Testcase is fail");
    }


    @Test(priority = 7)
    public void verifyTextSearchQuickAccess() {
        Assert.assertTrue(addNewAddressPage.checkDisplayTextSearchQuickAccess(), "Testcase is fail");
    }

    @Test(priority = 8)
    public void verifyImgNotFoundAddress() {
        Assert.assertTrue(addNewAddressPage.checkDisplayImgNotFoundAddress(), "Testcase is fail");
    }

    @Test(priority = 9)
    public void verifySearchResultMatchAddress0() {
        addNewAddressPage.clickAndFillAddress(ADDRESS_HOME);
        Assert.assertTrue(addNewAddressPage.checkDisplaySearchResultMatchAddress0(), "Testcase is fail");
        Assert.assertEquals(addNewAddressPage.getTextSearchResultMatchAddress0(), ADDRESS_SEARCH_0, "Testcase is fail");
    }

    @Test(priority = 10)
    public void verifySearchResultMatchAddress1() {
        Assert.assertTrue(addNewAddressPage.checkDisplaySearchResultMatchAddress1(), "Testcase is fail");
        Assert.assertEquals(addNewAddressPage.getTextSearchResultMatchAddress1(), ADDRESS_SEARCH_1, "Testcase is fail");
        addNewAddressPage.clickSearchResultMatchAddress0();
    }

    @Test(priority = 11)
    public void verifyBackBtn02() {
        Assert.assertTrue(addNewAddressPage.checkDisplayBackButton(), "Testcase is fail");
    }

    @Test(priority = 12)
    public void verifyTitle02() {
        Assert.assertTrue(addNewAddressPage.checkDisplayTitle(), "Testcase is fail");
    }

    @Test(priority = 13)
    public void verifyAddressNameField() {
        Assert.assertTrue(addNewAddressPage.checkDisplayAddressNameField(), "Testcase is fail");
    }

    @Test(priority = 14)
    public void verifyInputAddressName() {
        Assert.assertTrue(addNewAddressPage.checkDisplayInputAddressName(), "Testcase is fail");
    }

    @Test(priority = 15)
    public void verifyAddressLocationField() {
        Assert.assertTrue(addNewAddressPage.checkDisplayAddressLocationField(), "Testcase is fail");
    }

    @Test(priority = 16)
    public void verifyViewLocationIcon() {
        Assert.assertTrue(addNewAddressPage.checkDisplayViewLocationIcon(), "Testcase is fail");
    }

    @Test(priority = 17)
    public void verifyLabelAddress() {
        Assert.assertTrue(addNewAddressPage.checkDisplayLabelAddress(), "Testcase is fail");
    }

    @Test(priority = 18)
    public void verifyIconAddressDetailField() {
        Assert.assertTrue(addNewAddressPage.checkDisplayAddressDetailField(), "Testcase is fail");
    }

    @Test(priority = 19)
    public void verifyInputAddressDetail() {
        Assert.assertTrue(addNewAddressPage.checkDisplayInputAddressDetail(), "Testcase is fail");
    }

    @Test(priority = 20)
    public void verifyAddressNoteField() {
        Assert.assertTrue(addNewAddressPage.checkDisplayAddressNoteField(), "Testcase is fail");
    }

    @Test(priority = 21)
    public void verifyInputNoteAddressField() {
        Assert.assertTrue(addNewAddressPage.checkDisplayInputNoteAddressField(), "Testcase is fail");
    }

    @Test(priority = 22)
    public void verifyOnSaveAddressButton() {
        Assert.assertTrue(addNewAddressPage.checkDisplayOnSaveAddressButton(), "Testcase is fail");
    }

    @Test(priority = 23)
    public void verifyViewSaveAddress() {
        Assert.assertTrue(addNewAddressPage.checkDisplayViewSaveAddress(), "Testcase is fail");
    }

    @Test(priority = 24)
    public void verifyCheckTextAddressNameField() {
        Assert.assertTrue(addNewAddressPage.checkTextAddressNameField(), "Testcase is fail");
    }

    @Test(priority = 25)
    public void verifyCheckTextAddressLocationField() {
        Assert.assertTrue(addNewAddressPage.checkTextAddressLocationField(), "Testcase is fail");
    }

    @Test(priority = 26)
    public void verifyPlaceHolderInputAddressDetail() {
        Assert.assertEquals(addNewAddressPage.getTextPlaceHolderInputAddressDetail(), INPUT_ADDRESS_DETAIL, "Testcase is fail");
    }

    @Test(priority = 27)
    public void verifyPlaceHolderInputNoteAddressField() {
        Assert.assertEquals(addNewAddressPage.getTextPlaceHolderInputNoteAddressField(), INPUT_NOTE_ADDRESS_FIELD, "Testcase is fail");
    }

    @Test(priority = 28)
    public void verifyTextOnSaveAddressBnt() {
        Assert.assertEquals(addNewAddressPage.getTextOnSaveAddressBnt(), SAVE_ADDRESS_BUTTON_TEXT, "Testcase is fail");
    }

    @Test(priority = 29)
    public void verifyAddHomeNewAddress() {
        actualAddress = addNewAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_0, "Testcase is fail");
        addNewAddressPage.clickOnSaveAddressButton();
        actualAddress = addNewAddressPage.getTextHomeAddressText0();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_0, "Testcase is fail");
        expectedAddress = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_ADDRESS);
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        addNewAddressPage.swipeInHomeAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }

    @Test(priority = 30)
    public void verifyAddHomeNewAddressWithDetail() {
        addressManagementListPage.clickDeliveryFieldInCheckoutPage();
        addNewAddressPage.ClickNewHomeAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_HOME);
        addNewAddressPage.clickSearchResultMatchAddress0();
        actualAddress = addNewAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_0, "Testcase is fail");
        addNewAddressPage.fillInputAddressDetail(ADDRESS_DETAIL);
        addNewAddressPage.clickOnSaveAddressButton();
        actualAddress = addNewAddressPage.getTextHomeAddressText0();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_0, "Testcase is fail");
        expectedAddress = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_ADDRESS);
        expectedAddressDetail = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_ADDRESS_DETAIL);
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(ADDRESS_DETAIL, expectedAddressDetail, "Testcase is fail");
        addNewAddressPage.swipeInHomeAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }

    @Test(priority = 31)
    public void verifyAddHomeNewAddressWithNote() {
        addressManagementListPage.clickDeliveryFieldInCheckoutPage();
        addNewAddressPage.ClickNewHomeAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_HOME);
        addNewAddressPage.clickSearchResultMatchAddress0();
        actualAddress = addNewAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_0, "Testcase is fail");
        addNewAddressPage.fillAddressNoteField(ADDRESS_NOTE);
        addNewAddressPage.clickOnSaveAddressButton();
        actualAddress = addNewAddressPage.getTextHomeAddressText0();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_0, "Testcase is fail");
        expectedAddress = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_ADDRESS);
        expectedAddressNote = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_NOTE);
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(ADDRESS_NOTE, expectedAddressNote, "Testcase is fail");
        addNewAddressPage.swipeInHomeAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }

    @Test(priority = 32)
    public void verifyAddHomeNewAddressWithDetailAndNote() {
        addressManagementListPage.clickDeliveryFieldInCheckoutPage();
        addNewAddressPage.ClickNewHomeAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_HOME);
        addNewAddressPage.clickSearchResultMatchAddress0();
        actualAddress = addNewAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_0, "Testcase is fail");
        addNewAddressPage.fillInputAddressDetail(ADDRESS_DETAIL);
        addNewAddressPage.fillAddressNoteField(ADDRESS_NOTE);
        addNewAddressPage.clickOnSaveAddressButton();
        actualAddress = addNewAddressPage.getTextHomeAddressText0();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_0, "Testcase is fail");
        expectedAddress = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_ADDRESS);
        expectedAddressDetail = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_ADDRESS_DETAIL);
        expectedAddressNote = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_NOTE);
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(ADDRESS_DETAIL, expectedAddressDetail, "Testcase is fail");
        Assert.assertEquals(ADDRESS_NOTE, expectedAddressNote, "Testcase is fail");
        addNewAddressPage.swipeInHomeAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }

    @Test(priority = 33)
    public void verifyAddWorkNewAddress() {
        addressManagementListPage.clickDeliveryFieldInCheckoutPage();
        addNewAddressPage.ClickNewWorkAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_WORK);
        addNewAddressPage.clickSearchResultMatchAddress0();
        actualAddress = addNewAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_2, "Testcase is fail");
        addNewAddressPage.clickOnSaveAddressButton();
        actualAddress = addNewAddressPage.getTextHomeAddressText1();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_2, "Testcase is fail");
        expectedAddress = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_ADDRESS);
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        addNewAddressPage.swipeInWorkAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }

    @Test(priority = 34)
    public void verifyAddNewWorkAddressWithDetail() {
        addressManagementListPage.clickDeliveryFieldInCheckoutPage();
        addNewAddressPage.ClickNewWorkAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_WORK);
        addNewAddressPage.clickSearchResultMatchAddress0();
        actualAddress = addNewAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_2, "Testcase is fail");
        addNewAddressPage.fillInputAddressDetail(ADDRESS_DETAIL);
        addNewAddressPage.clickOnSaveAddressButton();
        actualAddress = addNewAddressPage.getTextHomeAddressText1();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_2, "Testcase is fail");
        expectedAddress = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_ADDRESS);
        expectedAddressDetail = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_ADDRESS_DETAIL);
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(ADDRESS_DETAIL, expectedAddressDetail, "Testcase is fail");
        addNewAddressPage.swipeInWorkAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }

    @Test(priority = 35)
    public void verifyAddNewWorkNewAddressWithNote() {
        addressManagementListPage.clickDeliveryFieldInCheckoutPage();
        addNewAddressPage.ClickNewWorkAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_WORK);
        addNewAddressPage.clickSearchResultMatchAddress0();
        actualAddress = addNewAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_2);
        addNewAddressPage.fillAddressNoteField(ADDRESS_NOTE);
        addNewAddressPage.clickOnSaveAddressButton();
        actualAddress = addNewAddressPage.getTextHomeAddressText1();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_2, "Testcase is fail");
        expectedAddress = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_ADDRESS);
        expectedAddressNote = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_NOTE);
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(ADDRESS_NOTE, expectedAddressNote, "Testcase is fail");
        addNewAddressPage.swipeInWorkAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }

    @Test(priority = 36)
    public void verifyAddNewWorkAddressWithDetailAndNote() {
        addressManagementListPage.clickDeliveryFieldInCheckoutPage();
        addNewAddressPage.ClickNewWorkAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_WORK);
        addNewAddressPage.clickSearchResultMatchAddress0();
        actualAddress = addNewAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_2);
        addNewAddressPage.fillInputAddressDetail(ADDRESS_DETAIL);
        addNewAddressPage.fillAddressNoteField(ADDRESS_NOTE);
        addNewAddressPage.clickOnSaveAddressButton();
        actualAddress = addNewAddressPage.getTextHomeAddressText1();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_2, "Testcase is fail");
        expectedAddress = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_ADDRESS);
        expectedAddressDetail = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_ADDRESS_DETAIL);
        expectedAddressNote = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_NOTE);
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(ADDRESS_NOTE, expectedAddressNote, "Testcase is fail");
        Assert.assertEquals(ADDRESS_DETAIL, expectedAddressDetail, "Testcase is fail");
        addNewAddressPage.swipeInWorkAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }

    @Test(priority = 37)
    public void verifyAddNewOtherNewAddress() {
        addressManagementListPage.clickDeliveryFieldInCheckoutPage();
        addNewAddressPage.ClickNewOtherAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_OTHER);
        addNewAddressPage.clickSearchResultMatchAddress0();
        actualAddress = addNewAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_3, "Testcase is fail");
        addNewAddressPage.fillAddressName(ADDRESS_OTHER_NAME);
        addNewAddressPage.clickOnSaveAddressButton();
        actualAddress = addNewAddressPage.getTextHomeAddressText2();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_3, "Testcase is fail");
        expectedAddress = addNewAddressPage.checkApiAddressDetail(ADDRESS_OTHER_NAME, KEY_ADDRESS);
        expectedAddressName = addNewAddressPage.checkApiAddressDetail(ADDRESS_OTHER_NAME, KEY_NAME);
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(ADDRESS_OTHER_NAME, expectedAddressName, "Testcase is fail");
        addNewAddressPage.swipeInOthersAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }

    @Test(priority = 38)
    public void verifyAddNewOtherAddressWithDetail() {
        addressManagementListPage.clickDeliveryFieldInCheckoutPage();
        addNewAddressPage.ClickNewOtherAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_OTHER);
        addNewAddressPage.clickSearchResultMatchAddress0();
        actualAddress = addNewAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_3, "Testcase is fail");
        addNewAddressPage.fillAddressName(ADDRESS_OTHER_NAME);
        addNewAddressPage.fillInputAddressDetail(ADDRESS_DETAIL);
        addNewAddressPage.clickOnSaveAddressButton();
        actualAddress = addNewAddressPage.getTextHomeAddressText2();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_3, "Testcase is fail");
        expectedAddress = addNewAddressPage.checkApiAddressDetail(ADDRESS_OTHER_NAME, KEY_ADDRESS);
        expectedAddressName = addNewAddressPage.checkApiAddressDetail(ADDRESS_OTHER_NAME, KEY_NAME);
        expectedAddressDetail = addNewAddressPage.checkApiAddressDetail(ADDRESS_OTHER_NAME, KEY_ADDRESS_DETAIL);
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(ADDRESS_OTHER_NAME, expectedAddressName, "Testcase is fail");
        Assert.assertEquals(ADDRESS_DETAIL, expectedAddressDetail, "Testcase is fail");
        addNewAddressPage.swipeInOthersAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }

    @Test(priority = 39)
    public void verifyAddNewOtherAddressWithNote() {
        addressManagementListPage.clickDeliveryFieldInCheckoutPage();
        addNewAddressPage.ClickNewOtherAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_OTHER);
        addNewAddressPage.clickSearchResultMatchAddress0();
        actualAddress = addNewAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_3, "Testcase is fail");
        addNewAddressPage.fillAddressName(ADDRESS_OTHER_NAME);
        addNewAddressPage.fillAddressNoteField(ADDRESS_NOTE);
        addNewAddressPage.clickOnSaveAddressButton();
        actualAddress = addNewAddressPage.getTextHomeAddressText2();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_3, "Testcase is fail");
        expectedAddress = addNewAddressPage.checkApiAddressDetail(ADDRESS_OTHER_NAME, KEY_ADDRESS);
        expectedAddressName = addNewAddressPage.checkApiAddressDetail(ADDRESS_OTHER_NAME, KEY_NAME);
        expectedAddressNote = addNewAddressPage.checkApiAddressDetail(ADDRESS_OTHER_NAME, KEY_NOTE);
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(ADDRESS_OTHER_NAME, expectedAddressName, "Testcase is fail");
        Assert.assertEquals(ADDRESS_NOTE, expectedAddressNote, "Testcase is fail");
        addNewAddressPage.swipeInOthersAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }

    @Test(priority = 40)
    public void verifyAddNewOtherAddressWithDetailAndNote() {
        addressManagementListPage.clickDeliveryFieldInCheckoutPage();
        addNewAddressPage.ClickNewWorkAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_OTHER);
        addNewAddressPage.clickSearchResultMatchAddress0();
        actualAddress = addNewAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_3);
        addNewAddressPage.fillAddressName(ADDRESS_OTHER_NAME);
        addNewAddressPage.fillInputAddressDetail(ADDRESS_DETAIL);
        addNewAddressPage.fillAddressNoteField(ADDRESS_NOTE);
        addNewAddressPage.clickOnSaveAddressButton();
        actualAddress = addNewAddressPage.getTextHomeAddressText2();
        Assert.assertEquals(actualAddress, ADDRESS_SEARCH_3, "Testcase is fail");
        expectedAddressName = addNewAddressPage.checkApiAddressDetail(ADDRESS_OTHER_NAME, KEY_NAME);
        expectedAddress = addNewAddressPage.checkApiAddressDetail(ADDRESS_OTHER_NAME, KEY_ADDRESS);
        expectedAddressDetail = addNewAddressPage.checkApiAddressDetail(ADDRESS_OTHER_NAME, KEY_ADDRESS_DETAIL);
        expectedAddressNote = addNewAddressPage.checkApiAddressDetail(ADDRESS_OTHER_NAME, KEY_NOTE);
        Assert.assertEquals(ADDRESS_OTHER_NAME, expectedAddressName, "Testcase is fail");
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(ADDRESS_NOTE, expectedAddressNote, "Testcase is fail");
        Assert.assertEquals(ADDRESS_DETAIL, expectedAddressDetail, "Testcase is fail");
        addNewAddressPage.swipeInOthersAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }
}
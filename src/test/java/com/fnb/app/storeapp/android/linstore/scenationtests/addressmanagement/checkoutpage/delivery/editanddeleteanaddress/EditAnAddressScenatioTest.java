package com.fnb.app.storeapp.android.linstore.scenationtests.addressmanagement.checkoutpage.delivery.editanddeleteanaddress;

import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addnewaddress.AddNewAddressPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.editanaddress.EditAnAddressPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.pickup.PickUpManagementPage;
import com.fnb.app.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addnewaddress.AddNewAddressDataTest.*;
import static com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.editanaddress.EditAnAddressDataTest.*;
import static com.fnb.app.setup.BaseSetup.softAssert;

public class EditAnAddressScenatioTest extends BaseTest {
    EditAnAddressPage editAnAddressPage;
    AddNewAddressPage addNewAddressPage;
    PickUpManagementPage pickUpManagementPage;
    AddressManagementListPage addressManagementListPage;
    static String actualAddress;
    static String expectedAddress;
    static String expectedAddressDetail;
    static String expectedAddressNote;
    static String expectedAddressName;

    @BeforeClass
    public void navigateToPage() {
        editAnAddressPage = homePageLinStore.navigateToEditAnAddressPage();
        addNewAddressPage = homePageLinStore.navigateToAddNewPage();
        pickUpManagementPage = homePageLinStore.navigateToPickUpManagementPage();
        addressManagementListPage = homePageLinStore.navigateToAddressManagementListPage();
        softAssert = new SoftAssert();
    }

    @Test(priority = 1)
    public void checkDisplayBackBtn() {
        pickUpManagementPage.navigateToCheckoutPage();
        pickUpManagementPage.changeOrderType();
        addressManagementListPage.clickDeliveryField();
        editAnAddressPage.clickEditHomeAddress();
        Assert.assertTrue(addNewAddressPage.checkDisplayBackButton(), "Testcase is fail");
    }

    @Test(priority = 2)
    public void checkDisplayTitle() {
        Assert.assertTrue(addNewAddressPage.checkDisplayTitle(), "Testcase is fail");
    }

    @Test(priority = 3)
    public void checkDisplayAddressNameTitle() {
        Assert.assertTrue(editAnAddressPage.checkDisplayAddressNameTitle(), "Testcase is fail");
        Assert.assertTrue(editAnAddressPage.checkTextAddressNameTitle(), "Testcase is fail");
    }

    @Test(priority = 4)
    public void checkDisplayInputAddressName() {
        Assert.assertTrue(editAnAddressPage.checkDisplayInputAddressName(), "Testcase is fail");
        Assert.assertEquals(editAnAddressPage.getTextInputAddressName(), HOME_NAME_ADDRESS, "Testcase is fail");
    }

    @Test(priority = 5)
    public void checkDisplayAddressTitle() {
        Assert.assertTrue(editAnAddressPage.checkDisplayAddressTitle(), "Testcase is fail");
        Assert.assertTrue(editAnAddressPage.checkTextAddressNameTitle(), "Testcase is fail");
    }

    @Test(priority = 6)
    public void checkDisplayLocationIcon() {
        Assert.assertTrue(editAnAddressPage.checkDisplayLocationIcon(), "Testcase is fail");
    }

    @Test(priority = 7)
    public void checkDisplayLabelAddress() {
        Assert.assertTrue(editAnAddressPage.checkDisplayLabelAddress(), "Testcase is fail");
    }

    @Test(priority = 8)
    public void checkDisplayAddressDetailTitle() {
        Assert.assertTrue(editAnAddressPage.checkDisplayAddressDetailTitle(), "Testcase is fail");
    }

    @Test(priority = 9)
    public void checkDisplayInputAddressDetail() {
        Assert.assertTrue(editAnAddressPage.checkDisplayInputAddressDetail(), "Testcase is fail");
        Assert.assertEquals(editAnAddressPage.getTextInputAddressDetail(), INPUT_ADDRESS_DETAIL, "Testcase is fail");
    }

    @Test(priority = 10)
    public void checkDisplayAddressNoteTitle() {
        Assert.assertTrue(editAnAddressPage.checkDisplayAddressNoteTitle(), "Testcase is fail");
    }

    @Test(priority = 11)
    public void checkDisplayInputAddressNote() {
        Assert.assertTrue(editAnAddressPage.checkDisplayInputAddressNote(), "Testcase is fail");
        Assert.assertEquals(editAnAddressPage.getTextInputAddressNote(), INPUT_NOTE_ADDRESS_FIELD, "Testcase is fail");
    }

    @Test(priority = 12)
    public void checkDisplaySaveAddressBtn() {
        Assert.assertTrue(editAnAddressPage.checkDisplaySaveAddressBtn(), "Testcase is fail");
        Assert.assertEquals(editAnAddressPage.getTextSaveAddressBtn(), SAVE_ADDRESS_BUTTON_TEXT, "Testcase is fail");
    }

    @Test(priority = 13)
    public void verifyEditHomeAddress() {
        editAnAddressPage.changeAddress(EDIT_ADDRESS_HOME);
        actualAddress = editAnAddressPage.getTextLabelAddress();
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_0, "Testcase is fail");
        editAnAddressPage.clickSaveAddress();
        actualAddress = addNewAddressPage.getTextHomeAddressText0();
        expectedAddress = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_ADDRESS);
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_0, "Testcase is fail");
        softAssert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        softAssert.assertAll();
    }

    @Test(priority = 14)
    public void verifyEditHomeAddressWithAddressDetail() {
        editAnAddressPage.clickEditHomeAddress();
        editAnAddressPage.changeAddress(EDIT_ADDRESS_WORK);
        editAnAddressPage.fillInputAddressDetail(EDIT_ADDRESS_DETAIL);
        actualAddress = editAnAddressPage.getTextLabelAddress();
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_2, "Testcase is fail");
        editAnAddressPage.clickSaveAddress();
        actualAddress = addNewAddressPage.getTextHomeAddressText0();
        expectedAddress = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_ADDRESS);
        expectedAddressDetail = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_ADDRESS_DETAIL);
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_2, "Testcase is fail");
        softAssert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        softAssert.assertEquals(EDIT_ADDRESS_DETAIL, expectedAddressDetail, "Testcase is fail");
        softAssert.assertAll();
    }

    @Test(priority = 15)
    public void verifyEditHomeAddressWithNote() {
        editAnAddressPage.clickEditHomeAddress();
        editAnAddressPage.changeAddress(EDIT_ADDRESS_OTHER);
        editAnAddressPage.fillInputAddressNote(EDIT_ADDRESS_NOTE);
        actualAddress = editAnAddressPage.getTextLabelAddress();
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_3, "Testcase is fail");
        editAnAddressPage.clickSaveAddress();
        actualAddress = addNewAddressPage.getTextHomeAddressText0();
        expectedAddress = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_ADDRESS);
        expectedAddressNote = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_NOTE);
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_3, "Testcase is fail");
        softAssert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        softAssert.assertEquals(EDIT_ADDRESS_NOTE, expectedAddressNote, "Testcase is fail");
        softAssert.assertAll();
    }

    @Test(priority = 16)
    public void verifyEditHomeAddressWithNoteAndDetail() {
        editAnAddressPage.clickEditHomeAddress();
        editAnAddressPage.changeAddress(EDIT_ADDRESS_HOME_1);
        editAnAddressPage.fillInputAddressNote(EDIT_ADDRESS_NOTE_1);
        editAnAddressPage.fillInputAddressDetail(EDIT_ADDRESS_DETAIL_1);
        actualAddress = editAnAddressPage.getTextLabelAddress();
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_4, "Testcase is fail");
        editAnAddressPage.clickSaveAddress();
        actualAddress = addNewAddressPage.getTextHomeAddressText0();
        expectedAddress = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_ADDRESS);
        expectedAddressNote = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_NOTE);
        expectedAddressDetail = addNewAddressPage.checkApiAddressDetail(HOME_NAME_ADDRESS, KEY_ADDRESS_DETAIL);
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_4, "Testcase is fail");
        softAssert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        softAssert.assertEquals(EDIT_ADDRESS_NOTE_1, expectedAddressNote, "Testcase is fail");
        softAssert.assertEquals(EDIT_ADDRESS_DETAIL_1, expectedAddressDetail, "Testcase is fail");
        addNewAddressPage.swipeInHomeAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
        softAssert.assertAll();
    }

    //edit address Work

    @Test(priority = 17)
    public void verifyEditWorkAddress() {
        editAnAddressPage.clickEditWorkAddress();
        editAnAddressPage.changeAddress(EDIT_ADDRESS_HOME);
        actualAddress = editAnAddressPage.getTextLabelAddress();
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_0, "Testcase is fail");
        editAnAddressPage.clickSaveAddress();
        actualAddress = addNewAddressPage.getTextHomeAddressText1();
        expectedAddress = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_ADDRESS);
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_0, "Testcase is fail");
        softAssert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        softAssert.assertAll();
    }

    @Test(priority = 18)
    public void verifyEditWorkAddressWithAddressDetail() {
        editAnAddressPage.clickEditWorkAddress();
        editAnAddressPage.changeAddress(EDIT_ADDRESS_WORK);
        editAnAddressPage.fillInputAddressDetail(EDIT_ADDRESS_DETAIL);
        actualAddress = editAnAddressPage.getTextLabelAddress();
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_2, "Testcase is fail");
        editAnAddressPage.clickSaveAddress();
        actualAddress = addNewAddressPage.getTextHomeAddressText1();
        expectedAddress = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_ADDRESS);
        expectedAddressDetail = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_ADDRESS_DETAIL);
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_2, "Testcase is fail");
        softAssert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        softAssert.assertEquals(EDIT_ADDRESS_DETAIL, expectedAddressDetail, "Testcase is fail");
        softAssert.assertAll();
    }

    @Test(priority = 19)
    public void verifyEditWorkAddressWithNote() {
        editAnAddressPage.clickEditWorkAddress();
        editAnAddressPage.changeAddress(EDIT_ADDRESS_OTHER);
        editAnAddressPage.fillInputAddressNote(EDIT_ADDRESS_NOTE);
        actualAddress = editAnAddressPage.getTextLabelAddress();
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_3, "Testcase is fail");
        editAnAddressPage.clickSaveAddress();
        actualAddress = addNewAddressPage.getTextHomeAddressText1();
        expectedAddress = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_ADDRESS);
        expectedAddressNote = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_NOTE);
        softAssert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_3, "Testcase is fail");
        softAssert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        softAssert.assertEquals(EDIT_ADDRESS_NOTE, expectedAddressNote, "Testcase is fail");
        softAssert.assertAll();
    }

    @Test(priority = 20)
    public void verifyEditWorkAddressWithNoteAndDetail() {
        editAnAddressPage.clickEditWorkAddress();
        editAnAddressPage.changeAddress(EDIT_ADDRESS_HOME_1);
        editAnAddressPage.fillInputAddressNote(EDIT_ADDRESS_NOTE_1);
        editAnAddressPage.fillInputAddressDetail(EDIT_ADDRESS_DETAIL_1);
        actualAddress = editAnAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_4, "Testcase is fail");
        editAnAddressPage.clickSaveAddress();
        actualAddress = addNewAddressPage.getTextHomeAddressText1();
        expectedAddress = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_ADDRESS);
        expectedAddressNote = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_NOTE);
        expectedAddressDetail = addNewAddressPage.checkApiAddressDetail(WORK_NAME_ADDRESS, KEY_ADDRESS_DETAIL);
        Assert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_4, "Testcase is fail");
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(EDIT_ADDRESS_NOTE_1, expectedAddressNote, "Testcase is fail");
        Assert.assertEquals(EDIT_ADDRESS_DETAIL_1, expectedAddressDetail, "Testcase is fail");
        addNewAddressPage.swipeInWorkAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }

    //edit an Other Address

    @Test(priority = 21)
    public void verifyEditOtherAddress() {
        editAnAddressPage.clickEditOtherAddress();
        editAnAddressPage.changeAddress(EDIT_ADDRESS_HOME);
        editAnAddressPage.fillInputAddressName(EDIT_ADDRESS_OTHER_NAME_1);
        actualAddress = editAnAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_0, "Testcase is fail");
        editAnAddressPage.clickSaveAddress();
        actualAddress = addNewAddressPage.getTextHomeAddressText2();
        expectedAddress = addNewAddressPage.checkApiAddressDetail(EDIT_ADDRESS_OTHER_NAME_1, KEY_ADDRESS);
        expectedAddressName = addNewAddressPage.checkApiAddressDetail(EDIT_ADDRESS_OTHER_NAME_1, KEY_NAME);
        Assert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_0, "Testcase is fail");
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(EDIT_ADDRESS_OTHER_NAME_1, expectedAddressName, "Testcase is fail");
    }

    @Test(priority = 22)
    public void verifyEditOtherAddressWithAddressDetail() {
        editAnAddressPage.clickEditWorkAddress();
        editAnAddressPage.changeAddress(EDIT_ADDRESS_WORK);
        editAnAddressPage.fillInputAddressName(EDIT_ADDRESS_OTHER_NAME_2);
        editAnAddressPage.fillInputAddressDetail(EDIT_ADDRESS_DETAIL);
        actualAddress = editAnAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_2, "Testcase is fail");
        editAnAddressPage.clickSaveAddress();
        actualAddress = addNewAddressPage.getTextHomeAddressText2();
        expectedAddress = addNewAddressPage.checkApiAddressDetail(EDIT_ADDRESS_OTHER_NAME_2, KEY_ADDRESS);
        expectedAddressDetail = addNewAddressPage.checkApiAddressDetail(EDIT_ADDRESS_OTHER_NAME_2, KEY_ADDRESS_DETAIL);
        expectedAddressName = addNewAddressPage.checkApiAddressDetail(EDIT_ADDRESS_OTHER_NAME_2, KEY_NAME);
        Assert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_2, "Testcase is fail");
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(EDIT_ADDRESS_DETAIL, expectedAddressDetail, "Testcase is fail");
        Assert.assertEquals(EDIT_ADDRESS_OTHER_NAME_2, expectedAddressName, "Testcase is fail");
    }

    @Test(priority = 23)
    public void verifyEditOtherAddressWithNote() {
        editAnAddressPage.clickEditWorkAddress();
        editAnAddressPage.changeAddress(EDIT_ADDRESS_OTHER);
        editAnAddressPage.fillInputAddressName(EDIT_ADDRESS_OTHER_NAME_3);
        editAnAddressPage.fillInputAddressNote(EDIT_ADDRESS_NOTE);
        actualAddress = editAnAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_3, "Testcase is fail");
        editAnAddressPage.clickSaveAddress();
        actualAddress = addNewAddressPage.getTextHomeAddressText2();
        expectedAddress = addNewAddressPage.checkApiAddressDetail(EDIT_ADDRESS_OTHER_NAME_3, KEY_ADDRESS);
        expectedAddressName = addNewAddressPage.checkApiAddressDetail(EDIT_ADDRESS_OTHER_NAME_3, KEY_NAME);
        expectedAddressNote = addNewAddressPage.checkApiAddressDetail(EDIT_ADDRESS_OTHER_NAME_3, KEY_NOTE);
        Assert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_3, "Testcase is fail");
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(EDIT_ADDRESS_NOTE, expectedAddressNote, "Testcase is fail");
        Assert.assertEquals(EDIT_ADDRESS_OTHER_NAME_3, expectedAddressName, "Testcase is fail");
    }

    @Test(priority = 24)
    public void verifyEditOtherAddressWithNoteAndDetail() {
        editAnAddressPage.clickEditWorkAddress();
        editAnAddressPage.changeAddress(EDIT_ADDRESS_HOME_1);
        editAnAddressPage.fillInputAddressName(EDIT_ADDRESS_OTHER_NAME_4);
        editAnAddressPage.fillInputAddressNote(EDIT_ADDRESS_NOTE_1);
        editAnAddressPage.fillInputAddressDetail(EDIT_ADDRESS_DETAIL_1);
        actualAddress = editAnAddressPage.getTextLabelAddress();
        Assert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_4, "Testcase is fail");
        editAnAddressPage.clickSaveAddress();
        actualAddress = addNewAddressPage.getTextHomeAddressText2();
        expectedAddress = addNewAddressPage.checkApiAddressDetail(EDIT_ADDRESS_OTHER_NAME_4, KEY_ADDRESS);
        expectedAddressNote = addNewAddressPage.checkApiAddressDetail(EDIT_ADDRESS_OTHER_NAME_4, KEY_NOTE);
        expectedAddressDetail = addNewAddressPage.checkApiAddressDetail(EDIT_ADDRESS_OTHER_NAME_4, KEY_ADDRESS_DETAIL);
        expectedAddressName = addNewAddressPage.checkApiAddressDetail(EDIT_ADDRESS_OTHER_NAME_4, KEY_NAME);
        Assert.assertEquals(actualAddress, EDIT_ADDRESS_SEARCH_4, "Testcase is fail");
        Assert.assertEquals(actualAddress, expectedAddress, "Testcase is fail");
        Assert.assertEquals(EDIT_ADDRESS_NOTE_1, expectedAddressNote, "Testcase is fail");
        Assert.assertEquals(EDIT_ADDRESS_DETAIL_1, expectedAddressDetail, "Testcase is fail");
        Assert.assertEquals(EDIT_ADDRESS_OTHER_NAME_4, expectedAddressName, "Testcase is fail");
        addNewAddressPage.swipeInOthersAddress();
        addNewAddressPage.clickIconDeleteAnAddress();
        addNewAddressPage.clickAcceptButton();
    }
}

package com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.editanaddress;

import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addnewaddress.AddNewAddressPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListPage;
import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addnewaddress.AddNewAddressDataTest.*;
import static com.fnb.app.setup.BaseTest.homePageLinStore;


public class EditAnAddressPage extends BaseSetup {
    AddNewAddressPage addNewAddressPage = homePageLinStore.navigateToAddNewPage();
    LoginPageLinStore loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
    AddressManagementListPage addressManagementListPage = homePageLinStore.navigateToAddressManagementListPage();

    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;


    public EditAnAddressPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AddressNameTitle\"]")
    public WebElement AddressNameTitle;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"InputAddressName\"]")
    public WebElement InputAddressName;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AddressTitle\"]")
    public WebElement AddressTitle;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"AddressLocationField\"]")
    public WebElement AddressLocationField;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"LocationIcon\"]")
    public WebElement LocationIcon;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"LabelAddress\"]")
    public WebElement LabelAddress;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AddressDetailTitle\"]")
    public WebElement AddressDetailTitle;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"InputAddressDetail\"]")
    public WebElement InputAddressDetail;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AddressNoteTitle\"]")
    public WebElement AddressNoteTitle;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"InputAddressNote\"]")
    public WebElement InputAddressNote;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SaveAddressText\"]")
    public WebElement SaveAddressText;

    public EditAnAddressPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean checkDisplayTitle() {
        return helper.checkElementDisplay(addNewAddressPage.Title);
    }

    public boolean checkDisplayAddressNameTitle() {
        return helper.checkElementDisplay(AddressNameTitle);
    }

    public boolean checkDisplayAddressTitle() {
        return helper.checkElementDisplay(AddressTitle);
    }

    public boolean checkDisplayAddressLocationField() {
        return helper.checkElementDisplay(AddressLocationField);
    }

    public boolean checkDisplayLocationIcon() {
        return helper.checkElementDisplay(LocationIcon);
    }

    public boolean checkDisplayLabelAddress() {
        return helper.checkElementDisplay(LabelAddress);
    }

    public boolean checkDisplayAddressDetailTitle() {
        return helper.checkElementDisplay(AddressDetailTitle);
    }

    public boolean checkDisplayInputAddressDetail() {
        return helper.checkElementDisplay(InputAddressDetail);
    }

    public boolean checkDisplayAddressNoteTitle() {
        return helper.checkElementDisplay(AddressNoteTitle);
    }

    public boolean checkDisplayInputAddressNote() {
        return helper.checkElementDisplay(InputAddressNote);
    }

    public boolean checkDisplaySaveAddressBtn() {
        return helper.checkElementDisplay(SaveAddressText);
    }

    public boolean checkDisplayInputAddressName() {
        return helper.checkElementDisplay(InputAddressName);
    }

    public String getTextAddressNameTitle() {
        return helper.waitToElementGetText(AddressNameTitle);
    }

    public String getTextAddressTitle() {
        return helper.waitToElementGetText(AddressTitle);
    }

    public String getTextInputAddressName() {
        return helper.waitToElementGetText(InputAddressName);
    }

    public String getTextLabelAddress() {
        return helper.waitToElementGetText(LabelAddress);
    }

    public String getTextInputAddressDetail() {
        return helper.waitToElementGetText(InputAddressDetail);
    }

    public String getTextInputAddressNote() {
        return helper.waitToElementGetText(InputAddressNote);
    }

    public String getTextSaveAddressBtn() {
        return helper.waitToElementGetText(SaveAddressText);
    }

    public boolean checkTextAddressNameTitle() {
        return getTextAddressNameTitle().contains("*");
    }

    public boolean checkTextAddressTitle() {
        return getTextAddressTitle().contains("*");
    }

    public void fillInputAddressName(String addressName) {
        helper.waitToElementClear(InputAddressName);
        helper.waitToElementSendKey(InputAddressName, addressName);
    }

    public void fillInputAddressDetail(String addressDetail) {
        helper.waitToElementSendKey(InputAddressDetail, addressDetail);
    }

    public void fillInputAddressNote(String addressNote) {
        helper.waitToElementSendKey(InputAddressNote, addressNote);
    }

    public void clickSaveAddress() {
        try {
            helper.waitToElementClick(SaveAddressText);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void navigateToAddress() {
        loginPageLinStore.splashScreen();
        addressManagementListPage.clickDeliveryHeader();
        addressManagementListPage.clickDeliveryField();
    }

    public void createNewHomeAddress() {
        loginPageLinStore.splashScreen();
        addressManagementListPage.clickDeliveryHeader();
        addressManagementListPage.clickDeliveryField();
        addNewAddressPage.ClickNewHomeAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_HOME);
        addNewAddressPage.clickSearchResultMatchAddress0();
        addNewAddressPage.clickOnSaveAddressButton();
    }

    public void createNewWorkAddress() {
        addNewAddressPage.ClickNewWorkAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_WORK);
        addNewAddressPage.clickSearchResultMatchAddress0();
        addNewAddressPage.clickOnSaveAddressButton();
    }

    public void createNewOtherAddress() {
        addNewAddressPage.ClickNewOtherAddress();
        addNewAddressPage.clickAndFillAddress(ADDRESS_OTHER);
        addNewAddressPage.clickSearchResultMatchAddress0();
        addNewAddressPage.fillAddressName(ADDRESS_OTHER_NAME);
        addNewAddressPage.clickOnSaveAddressButton();
    }

    public void clickEditHomeAddress() {
        addNewAddressPage.swipeInHomeAddress();
        addNewAddressPage.clickIconEditAnAddress();
    }

    public void clickEditWorkAddress() {
        addNewAddressPage.swipeInWorkAddress();
        addNewAddressPage.clickIconEditAnAddress();
    }

    public void clickEditOtherAddress() {
        addNewAddressPage.swipeInOthersAddress();
        addNewAddressPage.clickIconEditAnAddress();
    }




    public void changeAddress(String address) {
        helper.waitToElementClick(AddressLocationField);
        addNewAddressPage.clickAndFillAddress(address);
        addNewAddressPage.clickSearchResultMatchAddress0();
    }
}

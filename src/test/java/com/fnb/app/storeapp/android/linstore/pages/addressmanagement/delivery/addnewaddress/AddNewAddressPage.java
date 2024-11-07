package com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addnewaddress;

import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.fnb.utils.api.storeapp.pos.Get_AddressUserRequest.checkApiGetAddressUser;

public class AddNewAddressPage extends BaseSetup {

    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;


    public AddNewAddressPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"menu-profile-icon-4\"]")
    public WebElement profileField;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"user-profile-2\"]")
    public WebElement myAddressField;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"iconEditAnAddress\"]")
    public WebElement iconEditAnAddress;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"iconDeleteAnAddress\"]")
    public WebElement iconDeleteAnAddress;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"acceptBtn\"]")
    public WebElement acceptBtn;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"HomeAddressText-0\"]")
    public WebElement HomeAddressText0;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"HomeAddressText-1\"]")
    public WebElement HomeAddressText1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"HomeAddressText-2\"]")
    public WebElement HomeAddressText2;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"HomeAddressText-3\"]")
    public WebElement HomeAddressText3;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"HomeAddressField-0\"]")
    public WebElement HomeAddressField0;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"HomeAddressField-1\"]")
    public WebElement HomeAddressField1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"HomeAddressField-2\"]")
    public WebElement HomeAddressField2;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"backBtn\"]")
    public WebElement backBtn;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"Title\"]")
    public WebElement Title;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"IconSearchMap\"]")
    public WebElement iconSearch;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"SearchResultMatchAddressText\"]")
    public WebElement SearchResultMatchAddressText;
    @FindBy(xpath = "//android.widget.TextView[@text=\"36 Linh Trung, Thủ Đức, Thành phố Hồ Chí Minh, Việt Nam\"]")
    public WebElement location1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SearchResultMatchAddress-0\"]")
    public WebElement SearchResultMatchAddress0;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SearchResultMatchAddress-1\"]")
    public WebElement SearchResultMatchAddress1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SearchResultMatchAddress-2\"]")
    public WebElement SearchResultMatchAddress2;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SearchResultMatchAddress-3\"]")
    public WebElement SearchResultMatchAddress3;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"IconGgMap\"]")
    public WebElement iconGgSearch;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"textAddressInMind\"]")
    public WebElement textAddressInMind;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"textsearchQuickAccess\"]")
    public WebElement textSearchQuickAccess;
    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ImgNotFoundAddress\"]")
    public WebElement ImgNotFoundAddress;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AddressNameField\"]")
    public WebElement AddressNameField;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"InputAddressName\"]")
    public WebElement InputAddressName;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AddressLocationField\"]")
    public WebElement AddressLocationField;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ViewLocationIcon\"]")
    public WebElement ViewLocationIcon;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"LabelAddress\"]")
    public WebElement LabelAddress;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AddressDetailField\"]")
    public WebElement AddressDetailField;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"InputAddressDetail\"]")
    public WebElement InputAddressDetail;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AddressNoteField\"]")
    public WebElement AddressNoteField;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"InputNoteAddressField\"]")
    public WebElement InputNoteAddressField;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"onSaveAddressButton\"]")
    public WebElement onSaveAddressButton;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"viewSaveAddress\"]")
    public WebElement viewSaveAddress;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"textSaveAddress\"]")
    public WebElement textSaveAddress;

    public boolean checkDisplayBackButton() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return helper.checkElementDisplay(backBtn);
    }

    public boolean checkDisplayTitle() {
        return helper.checkElementDisplay(Title);
    }

    public boolean checkDisplayIconSearch() {
        return helper.checkElementDisplay(iconSearch);
    }

    public boolean checkDisplayDeliveryText() {
        return helper.checkElementDisplay(SearchResultMatchAddressText);
    }

    public boolean checkDisplayIconGgSearch() {
        return helper.checkElementDisplay(iconGgSearch);
    }

    public boolean checkDisplayTextAddressInMind() {
        return helper.checkElementDisplay(textAddressInMind);
    }

    public boolean checkDisplayTextSearchQuickAccess() {
        return helper.checkElementDisplay(textSearchQuickAccess);
    }

    public boolean checkDisplayImgNotFoundAddress() {
        return helper.checkElementDisplay(ImgNotFoundAddress);
    }

    public boolean checkDisplayAddressNameField() {
        return helper.checkElementDisplay(AddressNameField);
    }

    public boolean checkDisplayInputAddressName() {
        return helper.checkElementDisplay(InputAddressName);
    }

    public boolean checkDisplayAddressLocationField() {
        return helper.checkElementDisplay(AddressLocationField);
    }

    public boolean checkDisplayViewLocationIcon() {
        return helper.checkElementDisplay(ViewLocationIcon);
    }

    public boolean checkDisplayLabelAddress() {
        return helper.checkElementDisplay(LabelAddress);
    }

    public boolean checkDisplayAddressDetailField() {
        return helper.checkElementDisplay(AddressDetailField);
    }

    public boolean checkDisplayInputAddressDetail() {
        return helper.checkElementDisplay(InputAddressDetail);
    }

    public boolean checkDisplayAddressNoteField() {
        return helper.checkElementDisplay(AddressNoteField);
    }

    public boolean checkDisplayInputNoteAddressField() {
        return helper.checkElementDisplay(InputNoteAddressField);
    }

    public boolean checkDisplayOnSaveAddressButton() {
        return helper.checkElementDisplay(onSaveAddressButton);
    }

    public boolean checkDisplayViewSaveAddress() {
        return helper.checkElementDisplay(viewSaveAddress);
    }

    public Boolean checkDisplaySearchResultMatchAddress0() {
        return helper.checkElementDisplay(SearchResultMatchAddress0);
    }

    public Boolean checkDisplaySearchResultMatchAddress1() {
        return helper.checkElementDisplay(SearchResultMatchAddress1);
    }

    public Boolean checkDisplaySearchResultMatchAddress2() {
        return helper.checkElementDisplay(SearchResultMatchAddress2);
    }

    public Boolean checkDisplaySearchResultMatchAddress3() {
        return helper.checkElementDisplay(SearchResultMatchAddress3);
    }

    public String getTextPlaceHolderSearchResultMatchAddressText() {
        return helper.waitToElementGetText(SearchResultMatchAddressText);
    }

    public String getTextLabelAddress() {
        return helper.waitToElementGetText(LabelAddress);
    }

    public String getTextPlaceHolderInputAddressName() {
        return helper.waitToElementGetText(InputAddressName);
    }

    public String getTextPlaceHolderInputAddressDetail() {
        return helper.waitToElementGetText(InputAddressDetail);
    }

    public String getTextPlaceHolderInputNoteAddressField() {
        return helper.waitToElementGetText(InputNoteAddressField);
    }

    public String getTextHomeAddressText0() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return helper.waitToElementGetText(HomeAddressText0);
    }

    public String getTextHomeAddressText1() {
        return helper.waitToElementGetText(HomeAddressText1);
    }

    public String getTextHomeAddressText2() {
        return helper.waitToElementGetText(HomeAddressText2);
    }

    public String getTextHomeAddressText3() {
        return helper.waitToElementGetText(HomeAddressText3);
    }

    public String getTextOnSaveAddressBnt() {
        return helper.waitToElementGetText(textSaveAddress);
    }

    public String getTextSearchResultMatchAddress0() {
        return helper.waitToElementGetText(SearchResultMatchAddress0);
    }

    public String getTextSearchResultMatchAddress1() {
        return helper.waitToElementGetText(SearchResultMatchAddress1);
    }

    public String getTextSearchResultMatchAddress2() {
        return helper.waitToElementGetText(SearchResultMatchAddress2);
    }

    public String getTextSearchResultMatchAddress3() {
        return helper.waitToElementGetText(SearchResultMatchAddress3);
    }

    public void clearTextInputAddressDetail() {
        helper.waitToElementClear(InputAddressDetail);
    }

    public void clearTextInputNoteAddressField() {
        helper.waitToElementClear(InputNoteAddressField);
    }

    public void ClickNewHomeAddress() {
        try {
            helper.waitToElementClick(HomeAddressField0);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ClickNewWorkAddress() {
        try {
            helper.waitToElementClick(HomeAddressField1);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ClickNewOtherAddress() {
        try {
            helper.waitToElementClick(HomeAddressField2);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTextAddressNameField() {
        return helper.waitToElementGetText(AddressNameField);
    }

    public Boolean checkTextAddressNameField() {
        return getTextAddressNameField().contains("*");
    }

    public String getTextAddressLocationField() {
        return helper.waitToElementGetText(AddressLocationField);
    }

    public Boolean checkTextAddressLocationField() {
        return getTextAddressLocationField().contains("*");
    }

    public void clickBackBtn() {
        helper.waitToElementClick(backBtn);
    }

    public void clickAndFillAddress(String address) {
        try {
            helper.waitToElementClick(SearchResultMatchAddressText);
            helper.waitToElementSendKey(SearchResultMatchAddressText, address);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickSearchResultMatchAddress0() {
        helper.waitToElementClick(SearchResultMatchAddress0);
    }

    public void fillInputAddressDetail(String addressDetail) {
        helper.waitToElementClick(InputAddressDetail);
        helper.waitToElementSendKey(InputAddressDetail, addressDetail);
    }

    public void fillAddressNoteField(String addressNote) {
        helper.waitToElementClick(InputNoteAddressField);
        helper.waitToElementSendKey(InputNoteAddressField, addressNote);
    }

    public void fillAddressName(String addressName) {
        helper.waitToElementClick(InputAddressName);
        helper.waitToElementSendKey(InputAddressName, addressName);
    }


    public void clickOnSaveAddressButton() {
        helper.waitToElementClick(onSaveAddressButton);
    }

    public void swipeHorizontal(WebElement elementA) {
        int startPointX = elementA.getLocation().getX() * 20;
        int startPointY = elementA.getLocation().getY();
        int anchor = (int) (startPointX * 0.1);
        new TouchAction<>(driver)
                .press(PointOption.point(startPointX, startPointY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(anchor, startPointY))
                .release()
                .perform();
    }

    public void swipeInHomeAddress() {
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"HomeAddressField-0\"]"));
        try {
            Thread.sleep(3000);
            swipeHorizontal(HomeAddressField0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void swipeInWorkAddress() {
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"HomeAddressField-1\"]"));
        swipeHorizontal(HomeAddressField1);
    }

    public void swipeInOthersAddress() {
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"HomeAddressField-2\"]"));
        swipeHorizontal(HomeAddressField2);
    }

    public void clickIconEditAnAddress() {
        helper.waitToElementClick(iconEditAnAddress);
    }

    public void clickIconDeleteAnAddress() {
        helper.waitToElementClick(iconDeleteAnAddress);
    }

    public void clickDeleteHomeAddress() {
        swipeInHomeAddress();
        clickIconDeleteAnAddress();
        clickAcceptButton();
    }

    public void clickDeleteWorkAddress() {
        swipeInWorkAddress();
        clickIconDeleteAnAddress();
        clickAcceptButton();
    }

    public void clickDeleteOtherAddress() {
        swipeInOthersAddress();
        clickIconDeleteAnAddress();
        clickAcceptButton();
    }


    public void fillInputEditAddressDetail() {
        helper.waitToElementClick(InputAddressDetail);
        helper.waitToElementSendKey(InputAddressDetail, "number 62");
    }

    public void clickAcceptButton() {
        helper.waitToElementClick(acceptBtn);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String verifyTitle() {
        return helper.waitToElementGetText(Title);
    }

    public void navigateToProfilePage() {
        helper.waitToElementClick(profileField);
        helper.waitToElementClick(myAddressField);
    }

    public String checkApiAddressDetail(String nameAddress, String key) {
        return checkApiGetAddressUser(nameAddress, key);
    }
}

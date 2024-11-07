package com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist;

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

import static com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListDataTest.*;
import static com.fnb.utils.api.storeapp.pos.Get_Branch_InformationRequest.getAddressStoreDetailByIdFromStoreApp;
import static com.fnb.utils.api.storeapp.pos.Get_Branch_InformationRequest.getDataFromBranchesByStoreId;
import static com.fnb.utils.api.storeapp.pos.Get_AddressUserRequest.checkApiGetAddressUser;

public class AddressManagementListPage extends BaseSetup {

    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;


    public AddressManagementListPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"deliveryHeader\"]")
    public WebElement deliverHeader;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"delivery\"]")
    public WebElement delivery;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"iconDelivery\"]")
    public WebElement iconDelivery;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"deliveryTitle\"]")
    public WebElement deliveryTitle;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"deliveryAddressText\"]")
    public WebElement deliveryAddressText;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"DeliveryArrowRightSmallIcon\"]")
    public WebElement DeliveryArrowRightSmallIcon;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"backBtn\"]")
    public WebElement backBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"pickUp\"]")
    public WebElement pickUp;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"iconPickUp\"]")
    public WebElement iconPickUp;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"pickUpTitle\"]")
    public WebElement pickUpTitle;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"pickUpAddressText\"]")
    public WebElement pickUpAddressText;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"PickUpArrowRightSmallIcon\"]")
    public WebElement PickUpArrowRightSmallIcon;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"backButtonTestID\"]")
    public WebElement backButtonTestID;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"Title\"]")
    public WebElement Title;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"titleTextTestID\"]")
    public WebElement titleTextTestID;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"IconSearchMap\"]")
    public WebElement IconSearchMap;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"SearchResultMatchAddressText\"]")
    public WebElement SearchResultMatchAddressText;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"SearchDeliveryIcon\"]")
    public WebElement SearchDeliveryIcon;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"CurrentAddressField\"]")
    public WebElement CurrentAddressField;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"IconGps\"]")
    public WebElement IconGps;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"textCurrentLocation\"]")
    public WebElement textCurrentLocation;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"textUserAddress\"]")
    public WebElement textUserAddress;
    @FindBy(xpath = "//android.widget.ScrollView[@resource-id=\"ListViewCustomerAddress\"]")
    public WebElement ListViewCustomerAddress;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"HomeAddressField-0\"]")
    public WebElement HomeAddressField0;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"iconHomeAddress-0\"]")
    public WebElement iconHomeAddress0;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"HomeAddressTitle-0\"]")
    public WebElement HomeAddressTitle0;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"HomeAddressText-0\"]")
    public WebElement HomeAddressText0;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"HomeAddressField-1\"]")
    public WebElement WorkAddressField1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"iconHomeAddress-1\"]")
    public WebElement iconWorkAddress1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"HomeAddressTitle-1\"]")
    public WebElement WorkAddressTitle1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"HomeAddressText-1\"]")
    public WebElement WorkAddressText1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"HomeAddressField-2\"]")
    public WebElement OtherAddressField2;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"iconHomeAddress-2\"]")
    public WebElement iconOtherAddress2;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"HomeAddressTitle-2\"]")
    public WebElement OtherAddressTitle2;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"HomeAddressText-2\"]")
    public WebElement OtherAddressText2;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"HomeAddressField-3\"]")
    public WebElement WorkAddressField3;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"iconHomeAddress-3\"]")
    public WebElement iconOtherAddress3;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"HomeAddressTitle-3\"]")
    public WebElement OtherAddressTitle3;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"theNearestBranchToYouTitle\"]")
    public WebElement theNearestBranchToYouTitle;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"CheckBranchIcon\"]")
    public WebElement CheckBranchIcon;
    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"CurrentBranchImg\"]")
    public WebElement CurrentBranchImg;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"CurrentBranchName\"]")
    public WebElement CurrentBranchName;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"CurrentBranchAddress\"]")
    public WebElement CurrentBranchAddress;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"CurrentBranchDistance\"]")
    public WebElement CurrentBranchDistance;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchesText\"]")
    public WebElement otherBranchesText;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"ArrowDownIcon\"]")
    public WebElement ArrowDownIcon;
    @FindBy(xpath = "//android.widget.ScrollView[@resource-id=\"SearchResultMatchAddress\"]")
    public WebElement SearchResultMatchAddress;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SearchResultMatchAddress-0\"]")
    public WebElement SearchResultMatchAddress0;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SearchResultMatchAddress-1\"]")
    public WebElement SearchResultMatchAddress1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SearchResultMatchAddress-2\"]")
    public WebElement SearchResultMatchAddress2;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SearchResultMatchAddress-3\"]")
    public WebElement SearchResultMatchAddress3;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SearchResultMatchAddress-4\"]")
    public WebElement SearchResultMatchAddress4;
    @FindBy(xpath = "(//android.view.ViewGroup[@resource-id=\"iconEditAnAddress\"])[1]")
    public WebElement iconEditAnAddress;
    @FindBy(xpath = "(//android.view.ViewGroup[@resource-id=\"iconDeleteAnAddress\"])[1]")
    public WebElement iconDeleteAnAddress;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"HandleChangeLocation\"]")
    public WebElement HandleChangeLocation;

    public void fillToSearchResultMatchAddress() {
        helper.waitToElementClick(SearchResultMatchAddressText);
        helper.waitToElementSendKey(SearchResultMatchAddressText, ADDRESS);
    }

    public void clickDeliveryHeader() {
        helper.waitToElementClick(deliverHeader);
    }

    public void clickDeliveryField() {
        helper.waitToElementClick(iconDelivery);
    }

    public void clickDeliveryFieldInCheckoutPage() {
        helper.waitToElementClick(iconDelivery);
        helper.waitToElementClick(HandleChangeLocation);
    }

    public void swipeVertical(WebElement elementA, WebElement elementB) {
        int width = driver.manage().window().getSize().getWidth();
        int endPointX = elementA.getLocation().getX();
        int endPointY = elementA.getLocation().getY();
        int startPointX = elementB.getLocation().getX();
        int startPointY = elementB.getLocation().getY();
        new TouchAction<>(driver)
                .press(PointOption.point(startPointX, startPointY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(endPointX, endPointY))
                .release()
                .perform();
    }


    public void swipeHorizontal(WebElement elementA) {
        try {
            int startPointX = elementA.getLocation().getX() * 20;
            int startPointY = elementA.getLocation().getY();
            int anchor = (int) (startPointX * 0.1);
            new TouchAction<>(driver)
                    .press(PointOption.point(startPointX, startPointY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(anchor, startPointY))
                    .release()
                    .perform();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void swipeHomeAddressField() {
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"HomeAddressField-0\"]"));
        swipeHorizontal(HomeAddressField0);
    }

    public void swipeWorkAddressField() {
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"HomeAddressField-1\"]"));
        swipeHorizontal(WorkAddressField1);
    }

    public void swipeOtherAddressField() {
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"HomeAddressField-2\"]"));
        swipeHorizontal(OtherAddressField2);
    }

    public void scrollInBranch() {
        helper.waitElementLocated1(By.xpath("//android.widget.TextView[@resource-id=\"theNearestBranchToYouTitle\"]"));
//        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"CurrentAddressField\"]"));
        helper.waitElementLocated1(By.xpath("//com.horcrux.svg.SvgView[@resource-id=\"backBtn\"]"));
//        swipeVertical(CurrentAddressField, theNearestBranchToYouTitle);
        swipeVertical(backBtn, theNearestBranchToYouTitle);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollInBranch02() {
        helper.waitElementLocated1(By.xpath("//android.widget.TextView[@resource-id=\"theNearestBranchToYouTitle\"]"));
        helper.waitElementLocated1(By.xpath("//com.horcrux.svg.SvgView[@resource-id=\"IconGps\"]"));
//        swipeVertical(theNearestBranchToYouTitle, IconGps);
        swipeVertical(theNearestBranchToYouTitle, backBtn);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollInBranch03() {
        helper.waitElementLocated1(By.xpath("//android.widget.TextView[@resource-id=\"theNearestBranchToYouTitle\"]"));
//        helper.waitElementLocated1(By.xpath("//com.horcrux.svg.SvgView[@resource-id=\"IconGps\"]"));
        swipeVertical(iconOtherAddress3, iconWorkAddress1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickElement(WebElement element){
        helper.waitToElementClick(element);
    };

    public void clickPickUpField() {
        helper.waitToElementClick(pickUp);
    }

    public void clickHomeAddress() {
        helper.waitToElementClick(HomeAddressField0);
    }

    public void navigateToAddNewHomeAddress() {
        helper.waitToElementClick(HomeAddressField0);
    }

    public boolean checkDisplayDeliveryDialog() {
        return helper.checkElementDisplay(delivery);
    }

    public boolean checkDisplayIconDelivery() {
        return helper.checkElementDisplay(iconDelivery);
    }

    public boolean checkDisplayDeliveryTitle() {
        return helper.checkElementDisplay(deliveryTitle);
    }

    public boolean checkDisplayDeliveryAddressText() {
        return helper.checkElementDisplay(deliveryAddressText);
    }

    public boolean checkDisplayDeliveryArrowRightSmallIcon() {
        return helper.checkElementDisplay(DeliveryArrowRightSmallIcon);
    }

    public boolean checkDisplayBackButton() {
        return helper.checkElementDisplay(backBtn);
    }

    public boolean checkDisplayIconPickUp() {
        return helper.checkElementDisplay(iconPickUp);
    }

    public boolean checkDisplayPickUpTitle() {
        return helper.checkElementDisplay(pickUpTitle);
    }

    public boolean checkDisplayPickUpAddressText() {
        return helper.checkElementDisplay(pickUpAddressText);
    }

    public boolean checkDisplayPickUpArrowRightSmallIcon() {
        return helper.checkElementDisplay(PickUpArrowRightSmallIcon);
    }

    public boolean checkDisplayBackButtonTestID() {
        return helper.checkElementDisplay(backButtonTestID);
    }

    public boolean checkDisplayTitle() {
        return helper.checkElementDisplay(Title);
    }

    public boolean checkDisplayIconSearchMap() {
        return helper.checkElementDisplay(IconSearchMap);
    }

    public boolean checkDisplaySearchResultMatchAddressText() {
        return helper.checkElementDisplay(SearchResultMatchAddressText);
    }

    public boolean checkDisplaySearchDeliveryIcon() {
        return helper.checkElementDisplay(SearchDeliveryIcon);
    }

    public boolean checkDisplayCurrentAddressField() {
        return helper.checkElementDisplay(CurrentAddressField);
    }

    public boolean checkDisplayIconGps() {
        return helper.checkElementDisplay(IconGps);
    }

    public boolean checkDisplayTextCurrentLocation() {
        return helper.checkElementDisplay(textCurrentLocation);
    }

    public boolean checkDisplayTextUserAddress() {
        return helper.checkElementDisplay(textUserAddress);
    }

    public boolean checkDisplayHomeAddressField0() {
        return helper.checkElementDisplay(iconHomeAddress0);
    }

    public boolean checkDisplayIconHomeAddress0() {
        return helper.checkElementDisplay(iconHomeAddress0);
    }

    public boolean checkDisplayHomeAddressTitle0() {
        return helper.checkElementDisplay(HomeAddressTitle0);
    }

    public boolean checkDisplayWorkAddressField1() {
        return helper.checkElementDisplay(iconHomeAddress0);
    }

    public boolean checkDisplayIconHomeAddress1() {
        return helper.checkElementDisplay(iconWorkAddress1);
    }

    public boolean checkDisplayWorkAddressTitle1() {
        return helper.checkElementDisplay(WorkAddressTitle1);
    }

    public boolean checkDisplayOtherAddressField2() {
        return helper.checkElementDisplay(OtherAddressField2);
    }

    public boolean checkDisplayIconOtherAddress2() {
        return helper.checkElementDisplay(iconOtherAddress2);
    }

    public boolean checkDisplayOtherAddressTitle2() {
        return helper.checkElementDisplay(OtherAddressTitle2);
    }

    public boolean checkDisplayTheNearestBranchToYouTitle() {
        return helper.checkElementDisplay(theNearestBranchToYouTitle);
    }

    public boolean checkDisplayCheckBranchIcon() {
        return helper.checkElementDisplay(CheckBranchIcon);
    }

    public boolean checkDisplayCurrentBranchImg() {
        return helper.checkElementDisplay(CurrentBranchImg);
    }

    public boolean checkDisplayCurrentBranchName() {
        return helper.checkElementDisplay(CurrentBranchName);
    }

    public boolean checkDisplayCurrentBranchAddress() {
        return helper.checkElementDisplay(CurrentBranchAddress);
    }

    public boolean checkDisplayCurrentBranchDistance() {
        return helper.checkElementDisplay(CurrentBranchDistance);
    }

    public boolean checkDisplayOtherBranchesText() {
        return helper.checkElementDisplay(otherBranchesText);
    }

    public boolean checkDisplayArrowDownIcon() {
        return helper.checkElementDisplay(ArrowDownIcon);
    }

    public boolean checkDisplaySearchResultMatchAddress0() {
        return helper.checkElementDisplay(SearchResultMatchAddress0);
    }

    public boolean checkDisplaySearchResultMatchAddress1() {
        return helper.checkElementDisplay(SearchResultMatchAddress1);
    }

    public boolean checkDisplaySearchResultMatchAddress2() {
        return helper.checkElementDisplay(SearchResultMatchAddress0);
    }

    public boolean checkDisplaySearchResultMatchAddress3() {
        return helper.checkElementDisplay(SearchResultMatchAddress0);
    }

    public boolean checkDisplaySearchResultMatchAddress4() {
        return helper.checkElementDisplay(SearchResultMatchAddress0);
    }

    public boolean checkDisplayIconEditAnAddress() {
        return helper.checkElementDisplay(iconEditAnAddress);
    }

    public boolean checkDisplayIconDeleteAnAddress() {
        return helper.checkElementDisplay(iconDeleteAnAddress);
    }

    public String getTextDeliveryTitle() {
        return helper.waitToElementGetText(deliveryTitle);
    }

    public String getTextDeliveryAddressText() {
        return helper.waitToElementGetText(deliveryAddressText);
    }

    public String getTextTextCurrentLocation() {
        return helper.waitToElementGetText(textCurrentLocation);
    }

    public String getTextUserAddress() {
        return helper.waitToElementGetText(textUserAddress);
    }

    public String getHomeAddressText() {
        return helper.waitToElementGetText(HomeAddressText0);
    }

    public String getWorkAddressText() {
        return helper.waitToElementGetText(WorkAddressText1);
    }

    public String getOtherAddressText() {
        return helper.waitToElementGetText(OtherAddressText2);
    }

    public String getTextHomeAddressTitle0() {
        return helper.waitToElementGetText(HomeAddressTitle0);
    }

    public String getTextHomeAddressTitle1() {
        return helper.waitToElementGetText(WorkAddressTitle1);
    }

    public String getTextHomeAddressTitle2() {
        return helper.waitToElementGetText(OtherAddressTitle2);
    }

    public String getTextTheNearestBranchToYouTitle() {
        return helper.waitToElementGetText(theNearestBranchToYouTitle);
    }

    public String getTextCurrentBranchName() {
        return helper.waitToElementGetText(CurrentBranchName);
    }

    public String getTextCurrentBranchAddress() {
        return helper.waitToElementGetText(CurrentBranchAddress);
    }

    public String getTextCurrentBranchDistance() {
        return helper.waitToElementGetText(CurrentBranchDistance);
    }

    public String getTextOtherBranchesText() {
        return helper.waitToElementGetText(otherBranchesText);
    }

    public String getTextSearchResultMatchAddressText() {
        return helper.waitToElementGetText(SearchResultMatchAddressText);
    }

    public void clearTextSearchResultMatchAddress() {
        helper.waitToElementClick(SearchResultMatchAddressText);
        helper.waitToElementClear(SearchResultMatchAddressText);
        driver.navigate().back();
    }

    public void navigateBack(){
        try {
            driver.navigate().back();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String checkApiUserAddress(String nameAddress, String key) {
        return checkApiGetAddressUser(nameAddress, key);
    }

    public String checkApiTheNearestStoreName(String address) {
        return getDataFromBranchesByStoreId(address, API_ADDRESS_KEY_STORE_NAME);
    }

    public String checkApiTheNearestAddress(String address) {
//        return getDataFromBranchesByStoreId(address, API_ADDRESS_KEY_ADDRESS);
        return getAddressStoreDetailByIdFromStoreApp(address);
    }

    public String checkApiTheNearestDistance(String address) {
        return getDataFromBranchesByStoreId(address, API_ADDRESS_KEY_DISTANCE);
    }
}

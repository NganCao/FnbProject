package com.fnb.app.storeapp.android.linstore.pages.addressmanagement.pickup;

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

import static com.fnb.app.storeapp.android.linstore.pages.addressmanagement.pickup.PickUpManagementDataTest.API_KEY_STORE_NAME;
import static com.fnb.app.storeapp.android.linstore.pages.addressmanagement.pickup.PickUpManagementDataTest.REPLACE_WORD;
import static com.fnb.utils.api.storeapp.pos.Get_Branch_InformationRequest.getAddressStoreDetailByIdFromStoreApp;
import static com.fnb.utils.api.storeapp.pos.Get_Branch_InformationRequest.getDataFromBranchesByStoreId;
import static com.fnb.utils.api.storeapp.pos.GetBranch_InformationRequest.getBranchInfo;


public class PickUpManagementPage extends BaseSetup {

    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;


    public PickUpManagementPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
    public WebElement menuProduct;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"productItem00\"]")
    public WebElement productItem0;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"AddProductToCart\"]")
    public WebElement AddProductToCart;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"CartIcon\"]")
    public WebElement cartIcon;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"order\"]")
    public WebElement order;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DeliveryPickUpText\"]")
    public WebElement DeliveryPickUpText;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ChangeOrderTypeBtn\"]")
    public WebElement ChangeOrderTypeBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"HandleChangeLocation\"]")
    public WebElement HandleChangeLocation;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"BranchNameText\"]")
    public WebElement BranchNameText;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"LocationAddressText\"]")
    public WebElement LocationAddressText;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ArrowRightIcon\"]")
    public WebElement ArrowRightIcon;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"deliveryHeader\"]")
    public WebElement deliverHeader;
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
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"branchTitle\"]")
    public WebElement branchTitle;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"closeIcon\"]")
    public WebElement closeIcon;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"nearestBranchTitle\"]")
    public WebElement nearestBranchTitle;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"nearestBranchToYou\"]")
    public WebElement nearestBranchToYou;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"checkBranchIcon\"]")
    public WebElement checkBranchIcon;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"nearestbranchName\"]")
    public WebElement nearestbranchName;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"nearestBranchAddress\"]")
    public WebElement nearestBranchAddress;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"nearestBranchDistance\"]")
    public WebElement nearestBranchDistance;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchTitle\"]")
    public WebElement otherBranchTitle;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-0\"]")
    public WebElement otherBranchOptions0;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-0\"]")
    public WebElement otherBranchName0;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-0\"]")
    public WebElement otherBranchAddress0;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-0\"]")
    public WebElement otherBranchDistance0;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-1\"]")
    public WebElement otherBranchOptions1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-1\"]")
    public WebElement otherBranchName1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-1\"]")
    public WebElement otherBranchAddress1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-1\"]")
    public WebElement otherBranchDistance1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-2\"]")
    public WebElement otherBranchOptions2;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-2\"]")
    public WebElement otherBranchName2;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-2\"]")
    public WebElement otherBranchAddress2;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-2\"]")
    public WebElement otherBranchDistance2;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-3\"]")
    public WebElement otherBranchOptions3;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-3\"]")
    public WebElement otherBranchName3;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-3\"]")
    public WebElement otherBranchAddress3;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-3\"]")
    public WebElement otherBranchDistance3;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-4\"]")
    public WebElement otherBranchOptions4;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-4\"]")
    public WebElement otherBranchName4;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-4\"]")
    public WebElement otherBranchAddress4;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-4\"]")
    public WebElement otherBranchDistance4;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-5\"]")
    public WebElement otherBranchOptions5;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-5\"]")
    public WebElement otherBranchName5;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-5\"]")
    public WebElement otherBranchAddress5;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-5\"]")
    public WebElement otherBranchDistance5;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-6\"]")
    public WebElement otherBranchOptions6;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-6\"]")
    public WebElement otherBranchName6;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-6\"]")
    public WebElement otherBranchAddress6;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-6\"]")
    public WebElement otherBranchDistance6;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-7\"]")
    public WebElement otherBranchOptions7;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-7\"]")
    public WebElement otherBranchName7;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-7\"]")
    public WebElement otherBranchAddress7;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-7\"]")
    public WebElement otherBranchDistance7;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-8\"]")
    public WebElement otherBranchOptions8;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-8\"]")
    public WebElement otherBranchName8;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-8\"]")
    public WebElement otherBranchAddress8;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-8\"]")
    public WebElement otherBranchDistance8;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-9\"]")
    public WebElement otherBranchOptions9;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-9\"]")
    public WebElement otherBranchName9;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-9\"]")
    public WebElement otherBranchAddress9;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-9\"]")
    public WebElement otherBranchDistance9;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-10\"]")
    public WebElement otherBranchOptions10;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-10\"]")
    public WebElement otherBranchName10;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-10\"]")
    public WebElement otherBranchAddress10;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-10\"]")
    public WebElement otherBranchDistance10;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-11\"]")
    public WebElement otherBranchOptions11;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-11\"]")
    public WebElement otherBranchName11;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-11\"]")
    public WebElement otherBranchAddress11;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-11\"]")
    public WebElement otherBranchDistance11;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-12\"]")
    public WebElement otherBranchOptions12;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-12\"]")
    public WebElement otherBranchName12;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-12\"]")
    public WebElement otherBranchAddress12;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-12\"]")
    public WebElement otherBranchDistance12;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-13\"]")
    public WebElement otherBranchOptions13;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-13\"]")
    public WebElement otherBranchName13;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-13\"]")
    public WebElement otherBranchAddress13;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-13\"]")
    public WebElement otherBranchDistance13;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"otherBranchOptions-14\"]")
    public WebElement otherBranchOptions14;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchName-14\"]")
    public WebElement otherBranchName14;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchAddress-14\"]")
    public WebElement otherBranchAddress14;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"otherBranchDistance-14\"]")
    public WebElement otherBranchDistance14;

    public PickUpManagementPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickDeliveryHeader() {
        helper.waitToElementClick(deliverHeader);
    }

    public void clickPickUpField() {
        helper.waitToElementClick(iconPickUp);
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

    public void scrollInBranch() {
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"otherBranchOptions-0\"]"));
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"otherBranchOptions-1\"]"));
        swipeVertical(otherBranchOptions0, otherBranchOptions1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollInBranch1() {
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"otherBranchOptions-3\"]"));
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"otherBranchOptions-4\"]"));
        swipeVertical(otherBranchOptions3, otherBranchName4);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollInBranch6To7() {
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"otherBranchOptions-6\"]"));
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"otherBranchOptions-7\"]"));
        swipeVertical(otherBranchOptions6, otherBranchName7);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollInBranch8To9() {
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"otherBranchOptions-8\"]"));
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"otherBranchOptions-9\"]"));
        swipeVertical(otherBranchOptions8, otherBranchName9);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollInBranch2() {
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"otherBranchOptions-10\"]"));
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"otherBranchOptions-11\"]"));
        swipeVertical(otherBranchName10, otherBranchName11);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollInBranch3() {
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"otherBranchOptions-12\"]"));
        helper.waitElementLocated1(By.xpath("//android.view.ViewGroup[@resource-id=\"otherBranchOptions-13\"]"));
        swipeVertical(otherBranchName12, otherBranchName13);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickAutomationBranch() {
        helper.waitToElementClick(otherBranchOptions8);
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkDisplayIconPickUp() {
        return helper.checkElementDisplay(iconPickUp);
    }

    public boolean checkDisplayChangeOrderType() {
        return helper.checkElementDisplay(ChangeOrderTypeBtn);
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

    public boolean checkDisplayBranchTitle() {
        return helper.checkElementDisplay(branchTitle);
    }

    public boolean checkDisplayCloseIcon() {
        return helper.checkElementDisplay(closeIcon);
    }

    public boolean checkDisplayNearestBranchTitle() {
        return helper.checkElementDisplay(nearestBranchTitle);
    }

    public boolean checkDisplayNearestBranchToYou() {
        return helper.checkElementDisplay(nearestBranchToYou);
    }

    public boolean checkDisplayCheckBranchIcon() {
        return helper.checkElementDisplay(checkBranchIcon);
    }

    public boolean checkDisplayNearestBranchName() {
        return helper.checkElementDisplay(nearestbranchName);
    }

    public boolean checkDisplayNearestBranchAddress() {
        return helper.checkElementDisplay(nearestBranchAddress);
    }

    public boolean checkDisplayNearestBranchDistance() {
        return helper.checkElementDisplay(nearestBranchDistance);
    }

    public boolean checkDisplayOtherBranchTitle() {
        return helper.checkElementDisplay(otherBranchTitle);
    }

    public boolean checkDisplayOtherBranchOptions0() {
        return helper.checkElementDisplay(otherBranchOptions0);
    }

    public boolean checkDisplayOtherBranchName0() {
        return helper.checkElementDisplay(otherBranchName0);
    }

    public boolean checkDisplayOtherBranchAddress0() {
        return helper.checkElementDisplay(otherBranchAddress0);
    }

    public boolean checkDisplayOtherBranchDistance0() {
        return helper.checkElementDisplay(otherBranchDistance0);
    }

    public boolean checkDisplayOtherBranchOptions1() {
        return helper.checkElementDisplay(otherBranchOptions1);
    }

    public boolean checkDisplayOtherBranchName1() {
        return helper.checkElementDisplay(otherBranchName1);
    }

    public boolean checkDisplayOtherBranchAddress1() {
        return helper.checkElementDisplay(otherBranchAddress1);
    }

    public boolean checkDisplayOtherBranchDistance1() {
        return helper.checkElementDisplay(otherBranchDistance1);
    }

    public boolean checkDisplayOtherBranchOptions2() {
        return helper.checkElementDisplay(otherBranchOptions2);
    }

    public boolean checkDisplayOtherBranchName2() {
        return helper.checkElementDisplay(otherBranchName2);
    }

    public boolean checkDisplayOtherBranchAddress2() {
        return helper.checkElementDisplay(otherBranchAddress2);
    }

    public boolean checkDisplayOtherBranchDistance2() {
        return helper.checkElementDisplay(otherBranchDistance2);
    }

    public boolean checkDisplayOtherBranchName3() {
        return helper.checkElementDisplay(otherBranchName3);
    }

    public boolean checkDisplayOtherBranchAddress3() {
        return helper.checkElementDisplay(otherBranchAddress3);
    }

    public boolean checkDisplayOtherBranchDistance3() {
        return helper.checkElementDisplay(otherBranchDistance3);
    }

    public boolean checkDisplayOtherBranchOptions4() {
        return helper.checkElementDisplay(otherBranchOptions4);
    }

    public boolean checkDisplayOtherBranchName4() {
        return helper.checkElementDisplay(otherBranchName4);
    }

    public boolean checkDisplayOtherBranchAddress4() {
        return helper.checkElementDisplay(otherBranchAddress4);
    }

    public boolean checkDisplayOtherBranchDistance4() {
        return helper.checkElementDisplay(otherBranchDistance4);
    }

    public boolean checkDisplayOtherBranchName5() {
        return helper.checkElementDisplay(otherBranchName5);
    }

    public boolean checkDisplayOtherBranchAddress5() {
        return helper.checkElementDisplay(otherBranchAddress5);
    }

    public boolean checkDisplayOtherBranchDistance5() {
        return helper.checkElementDisplay(otherBranchDistance5);
    }

    public boolean checkDisplayOtherBranchName6() {
        return helper.checkElementDisplay(otherBranchName6);
    }

    public boolean checkDisplayOtherBranchAddress6() {
        return helper.checkElementDisplay(otherBranchAddress6);
    }

    public boolean checkDisplayOtherBranchDistance6() {
        return helper.checkElementDisplay(otherBranchDistance6);
    }

    public boolean checkDisplayOtherBranchName7() {
        return helper.checkElementDisplay(otherBranchName7);
    }

    public boolean checkDisplayOtherBranchAddress7() {
        return helper.checkElementDisplay(otherBranchAddress7);
    }

    public boolean checkDisplayOtherBranchDistance7() {
        return helper.checkElementDisplay(otherBranchDistance7);
    }

    public boolean checkDisplayOtherBranchName8() {
        return helper.checkElementDisplay(otherBranchName8);
    }

    public boolean checkDisplayOtherBranchAddress8() {
        return helper.checkElementDisplay(otherBranchAddress8);
    }

    public boolean checkDisplayOtherBranchDistance8() {
        return helper.checkElementDisplay(otherBranchDistance8);
    }

    public boolean checkDisplayOtherBranchName9() {
        return helper.checkElementDisplay(otherBranchName9);
    }

    public boolean checkDisplayOtherBranchAddress9() {
        return helper.checkElementDisplay(otherBranchAddress9);
    }

    public boolean checkDisplayOtherBranchDistance9() {
        return helper.checkElementDisplay(otherBranchDistance9);
    }

    public boolean checkDisplayOtherBranchName10() {
        return helper.checkElementDisplay(otherBranchName10);
    }

    public boolean checkDisplayOtherBranchAddress10() {
        return helper.checkElementDisplay(otherBranchAddress10);
    }

    public boolean checkDisplayOtherBranchDistance10() {
        return helper.checkElementDisplay(otherBranchDistance10);
    }

    public boolean checkDisplayOtherBranchName11() {
        return helper.checkElementDisplay(otherBranchName11);
    }

    public boolean checkDisplayOtherBranchAddress11() {
        return helper.checkElementDisplay(otherBranchAddress11);
    }

    public boolean checkDisplayOtherBranchDistance11() {
        return helper.checkElementDisplay(otherBranchDistance11);
    }

    public boolean checkDisplayOtherBranchName12() {
        return helper.checkElementDisplay(otherBranchName12);
    }

    public boolean checkDisplayOtherBranchAddress12() {
        return helper.checkElementDisplay(otherBranchAddress12);
    }

    public boolean checkDisplayOtherBranchDistance12() {
        return helper.checkElementDisplay(otherBranchDistance12);
    }

    public boolean checkDisplayOtherBranchName13() {
        return helper.checkElementDisplay(otherBranchName13);
    }

    public boolean checkDisplayOtherBranchAddress13() {
        return helper.checkElementDisplay(otherBranchAddress13);
    }

    public boolean checkDisplayOtherBranchDistance13() {
        return helper.checkElementDisplay(otherBranchDistance13);
    }

    public boolean checkDisplayOtherBranchName14() {
        return helper.checkElementDisplay(otherBranchName14);
    }

    public boolean checkDisplayOtherBranchAddress14() {
        return helper.checkElementDisplay(otherBranchAddress14);
    }

    public boolean checkDisplayOtherBranchDistance14() {
        return helper.checkElementDisplay(otherBranchDistance14);
    }

    public boolean checkDisplayDeliveryPickUpText() {
        return helper.checkElementDisplay(DeliveryPickUpText);
    }

    public boolean checkDisplayBranchNameText() {
        return helper.checkElementDisplay(BranchNameText);
    }

    public boolean checkDisplayLocationAddressText() {
        return helper.checkElementDisplay(LocationAddressText);
    }

    public String getTextPickUpAddressText() {
        return helper.waitToElementGetText(pickUpAddressText);
    }

    public String getTextDeliveryPickUpText() {
        return helper.waitToElementGetText(DeliveryPickUpText);
    }

    public String getTextBranchNameText() {
        return helper.waitToElementGetText(BranchNameText);
    }

    public String getTextLocationAddressText() {
        return helper.waitToElementGetText(LocationAddressText);
    }

    public String getTextNearestBranchName() {
        return helper.waitToElementGetText(nearestbranchName);
    }

    public String getTextNearestBranchAddress() {
        return helper.waitToElementGetText(nearestBranchAddress);
    }

    public String getTextNearestBranchDistance() {
        return helper.waitToElementGetText(nearestBranchDistance);
    }

    public String getTextOtherBranchName0() {
        return helper.waitToElementGetText(otherBranchName0);
    }

    public String getTextOtherBranchAddress0() {
        return helper.waitToElementGetText(otherBranchAddress0);
    }

    public String getTextOtherBranchDistance0() {
        return helper.waitToElementGetText(otherBranchDistance0);
    }

    public String getTextOtherBranchName1() {
        return helper.waitToElementGetText(otherBranchName1);
    }

    public String getTextOtherBranchAddress1() {
        return helper.waitToElementGetText(otherBranchAddress1);
    }

    public String getTextOtherBranchDistance1() {
        return helper.waitToElementGetText(otherBranchDistance1);
    }

    public String getTextOtherBranchName2() {
        return helper.waitToElementGetText(otherBranchName2);
    }

    public String getTextOtherBranchAddress2() {
        return helper.waitToElementGetText(otherBranchAddress2);
    }

    public String getTextOtherBranchDistance2() {
        return helper.waitToElementGetText(otherBranchDistance2);
    }

    public String getTextOtherBranchName3() {
        return helper.waitToElementGetText(otherBranchName3);
    }

    public String getTextOtherBranchAddress3() {
        return helper.waitToElementGetText(otherBranchAddress3);
    }

    public String getTextOtherBranchDistance3() {
        return helper.waitToElementGetText(otherBranchDistance3);
    }

    public String getTextOtherBranchName4() {
        return helper.waitToElementGetText(otherBranchName4);
    }

    public String getTextOtherBranchAddress4() {
        return helper.waitToElementGetText(otherBranchAddress4);
    }

    public String getTextOtherBranchDistance4() {
        return helper.waitToElementGetText(otherBranchDistance4);
    }

    public String getTextOtherBranchName5() {
        return helper.waitToElementGetText(otherBranchName5);
    }

    public String getTextOtherBranchAddress5() {
        return helper.waitToElementGetText(otherBranchAddress5);
    }

    public String getTextOtherBranchDistance5() {
        return helper.waitToElementGetText(otherBranchDistance5);
    }

    public String getTextOtherBranchName7() {
        return helper.waitToElementGetText(otherBranchName7);
    }

    public String getTextOtherBranchAddress7() {
        return helper.waitToElementGetText(otherBranchAddress7);
    }

    public String getTextOtherBranchDistance7() {
        return helper.waitToElementGetText(otherBranchDistance7);
    }

    public String getTextOtherBranchName6() {
        return helper.waitToElementGetText(otherBranchName6);
    }

    public String getTextOtherBranchAddress6() {
        return helper.waitToElementGetText(otherBranchAddress6);
    }

    public String getTextOtherBranchDistance6() {
        return helper.waitToElementGetText(otherBranchDistance6);
    }

    public String getTextOtherBranchName8() {
        return helper.waitToElementGetText(otherBranchName8);
    }

    public String getTextOtherBranchAddress8() {
        return helper.waitToElementGetText(otherBranchAddress8);
    }

    public String getTextOtherBranchDistance8() {
        return helper.waitToElementGetText(otherBranchDistance8);
    }

    public String getTextOtherBranchName9() {
        return helper.waitToElementGetText(otherBranchName9);
    }

    public String getTextOtherBranchAddress9() {
        return helper.waitToElementGetText(otherBranchAddress9);
    }

    public String getTextOtherBranchDistance9() {
        return helper.waitToElementGetText(otherBranchDistance9);
    }

    public String getTextOtherBranchName10() {
        return helper.waitToElementGetText(otherBranchName10);
    }

    public String getTextOtherBranchAddress10() {
        return helper.waitToElementGetText(otherBranchAddress10);
    }

    public String getTextOtherBranchDistance10() {
        return helper.waitToElementGetText(otherBranchDistance10);
    }

    public String getTextOtherBranchName11() {
        return helper.waitToElementGetText(otherBranchName11);
    }

    public String getTextOtherBranchAddress11() {
        return helper.waitToElementGetText(otherBranchAddress11);
    }

    public String getTextOtherBranchDistance11() {
        return helper.waitToElementGetText(otherBranchDistance11);
    }

    public String getTextOtherBranchName12() {
        return helper.waitToElementGetText(otherBranchName12);
    }

    public String getTextOtherBranchAddress12() {
        return helper.waitToElementGetText(otherBranchAddress12);
    }

    public String getTextOtherBranchDistance12() {
        return helper.waitToElementGetText(otherBranchDistance12);
    }

    public String getTextOtherBranchName13() {
        return helper.waitToElementGetText(otherBranchName13);
    }

    public String getTextOtherBranchAddress13() {
        return helper.waitToElementGetText(otherBranchAddress13);
    }

    public String getTextOtherBranchDistance13() {
        return helper.waitToElementGetText(otherBranchDistance13);
    }

    public String getTextOtherBranchName14() {
        return helper.waitToElementGetText(otherBranchName14);
    }

    public String getTextOtherBranchAddress14() {
        return helper.waitToElementGetText(otherBranchAddress14);
    }

    public String getTextOtherBranchDistance14() {
        return helper.waitToElementGetText(otherBranchDistance14);
    }

    public String checkApiGetStoreName(String nameAddress) {
        return getDataFromBranchesByStoreId(nameAddress, API_KEY_STORE_NAME);
    }

    public String checkApiGetNearestBranchInfo(String nameAddress, String key) {
        return getDataFromBranchesByStoreId(nameAddress, key);
    }

    public String checkApiGetBranchInfo(String storeName, String key, String addressUser) {
        return getBranchInfo(storeName, key, addressUser);
    }

    public String checkApiGetNearestAddress(String address) {
        return getAddressStoreDetailByIdFromStoreApp(address);
    }

    public String expectedData(String data) {
        return data.replaceAll(REPLACE_WORD, "");
    }

    public void waitToClick(WebElement element) {
        try {
            helper.waitToElementClick(element);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void navigateToCheckoutPage() {
        waitToClick(menuProduct);
        waitToClick(productItem0);
        waitToClick(AddProductToCart);
        waitToClick(cartIcon);
        waitToClick(order);
    }

    public void changeOrderType() {
        helper.waitToElementClick(ChangeOrderTypeBtn);
    }

    public void navigateChangeAddress() {
        waitToClick(HandleChangeLocation);
    }

    public void clickClosedIcon() {
        helper.waitToElementClick(closeIcon);
    }

}

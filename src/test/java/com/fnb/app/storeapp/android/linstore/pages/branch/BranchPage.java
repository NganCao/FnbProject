package com.fnb.app.storeapp.android.linstore.pages.branch;

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

import static com.fnb.app.storeapp.android.linstore.pages.branch.BranchDataTest.FILE_PATH_BRANCH_ALL;
import static com.fnb.app.storeapp.android.linstore.pages.branch.BranchDataTest.FILE_PATH_BRANCH_DETAIL;
import static com.fnb.utils.api.storeapp.pos.Get_Branch_InformationRequest.*;

public class BranchPage extends BaseSetup {

    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;


    public BranchPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"branchNameProductList\"]")
    private WebElement branchNameProductList;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"branchAddressProductList\"]")
    private WebElement branchAddressProductList;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"branchAddressProductListText\"]")
    private WebElement branchAddressProductListText;
    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"branchImgProductList\"]")
    private WebElement branchImgProductList;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"branchIconDistanceProductList\"]")
    private WebElement branchIconDistanceProductList;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"branchDistanceProductList\"]")
    private WebElement branchDistanceProductList;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"branchDistanceProductListText\"]")
    private WebElement branchDistanceProductListText;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"backBtn\"]")
    private WebElement backBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MapView1\"]")
    private WebElement MapView1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"iconDropdownBranch\"]")
    private WebElement iconDropdownBranch;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"IconHome1\"]")
    private WebElement IconHome1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"branchAddressManagementText1\"]")
    private WebElement branchAddressManagementText1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ratingText1\"]")
    private WebElement ratingText1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"iconDistance1\"]")
    private WebElement iconDistance1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DistanceText1\"]")
    private WebElement DistanceText1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"contactTitle1\"]")
    private WebElement contactTitle1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"phoneIcon1\"]")
    private WebElement phoneIcon1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"phoneNumberBranch1\"]")
    private WebElement phoneNumberBranch1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"imgBackground1\"]")
    private WebElement imgBackground1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
    private WebElement productsListBtn;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"branchAddressManagement\"]")
    private WebElement branchAddressManagement;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]")
    private WebElement optionBranch2;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]")
    private WebElement optionBranch3;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]")
    private WebElement branchNameBtn2;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"SwitchBranch\"]")
    private WebElement changeBranchButton;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"TextSwitchBranch\"]")
    private WebElement changeBranchButtonText;
    @FindBy(xpath = "//android.widget.ScrollView[@resource-id=\"ListIdOnBranchOptions\"]")
    private WebElement ListIdOnBranchOptions;
    @FindBy(xpath = "//android.widget.ScrollView[@resource-id=\"ListIdOnBranchOptions\"][0]")
    private WebElement ListIdOnBranchOptions0;
    @FindBy(xpath = "//android.widget.ScrollView[@resource-id=\"ListIdOnBranchOptions\"][1]")
    private WebElement ListIdOnBranchOptions1;


    public void swipeVertical(WebElement elementA) {
        int width = driver.manage().window().getSize().getWidth();
        int anchor = (int) (width / 2);
        int startPointX = elementA.getLocation().getX();
        int startPointY = elementA.getLocation().getY();
        new TouchAction<>(driver)
                .press(PointOption.point(startPointX, startPointY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(anchor, anchor))
                .release()
                .perform();
    }

    public Boolean checkDisplayBranchNameProductList() {
        return helper.checkElementDisplay(branchNameProductList);
    }

    public Boolean checkDisplayBranchAddressProductList() {
        return helper.checkElementDisplay(branchAddressProductList);
    }

    public Boolean checkDisplayBranchAddressProductListText() {
        return helper.checkElementDisplay(branchAddressProductListText);
    }

    public Boolean checkDisplayBranchImgProductList() {
        return helper.checkElementDisplay(branchImgProductList);
    }

    public Boolean checkDisplayBranchIconDistanceProductList() {
        return helper.checkElementDisplay(branchIconDistanceProductList);
    }

    public Boolean checkDisplayBranchDistanceProductList() {
        return helper.checkElementDisplay(branchDistanceProductList);
    }

    public Boolean checkDisplayBranchDistanceProductListText() {
        return helper.checkElementDisplay(branchDistanceProductListText);
    }

    public Boolean checkDisplayBackBtn() {
        return helper.checkElementDisplay(backBtn);
    }

    public Boolean checkDisplayMapView() {
        return helper.checkElementDisplay(MapView1);
    }

    public Boolean checkDisplayIconDropdownBranch() {
        return helper.checkElementDisplay(iconDropdownBranch);
    }

    public Boolean checkDisplayIconHome() {
        return helper.checkElementDisplay(IconHome1);
    }

    public Boolean checkDisplayBranchAddressManagementText1() {
        return helper.checkElementDisplay(branchAddressManagementText1);
    }

    public Boolean checkDisplayRatingText() {
        return helper.checkElementDisplay(ratingText1);
    }

    public Boolean checkDisplayIconDistance() {
        return helper.checkElementDisplay(iconDistance1);
    }

    public Boolean checkDisplayDistanceText1() {
        return helper.checkElementDisplay(DistanceText1);
    }

    public Boolean checkDisplayContactTitle() {
        return helper.checkElementDisplay(contactTitle1);
    }

    public Boolean checkDisplayPhoneIcon1() {
        return helper.checkElementDisplay(phoneIcon1);
    }

    public Boolean checkDisplayPhoneNumberBranch() {
        return helper.checkElementDisplay(phoneNumberBranch1);
    }

    public Boolean checkDisplayImgBackground() {
        return helper.checkElementDisplay(imgBackground1);
    }

    public Boolean checkDisplayListIdOnBranch() {
        return helper.checkElementDisplay(ListIdOnBranchOptions);
    }

    public void navigateToProductListPage() {
        helper.waitToElementClick(productsListBtn);
    }

    public void navigateToBranchManagement() {
        try {
            helper.waitToElementClick(branchAddressProductList);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollScreen01() {
        try {
            helper.waitElementLocated1(By.xpath("//android.widget.TextView[@resource-id=\"branchAddressManagementText1\"]"));
            swipeVertical(branchAddressManagementText1);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickIconDropdownBranch() {
        helper.waitToElementClick(iconDropdownBranch);
    }

    public void selectBranchOptions() {
        helper.waitToElementClick(ListIdOnBranchOptions1);
    }

    public void clickChangeBranchButton() {
        try {
            helper.waitToElementClick(changeBranchButton);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickBranchAddressProductList() {
        try {
            helper.waitToElementClick(branchAddressProductList);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTextBranchNameButton() {
        return helper.waitToElementGetText(branchAddressManagement);
    }

    public String getTextBranchNameProductList() {
        return helper.waitToElementGetText(branchNameProductList);
    }

    public String getTextBranchAddressProductListText() {
        return helper.waitToElementGetText(branchAddressProductListText);
    }

    public String getTextBranchAddressManagementText1() {
        return helper.waitToElementGetText(branchAddressManagementText1);
    }

    public String getTextBranchDistanceProductList() {
        return helper.waitToElementGetText(branchDistanceProductList);
    }

    public String getTextBranchDistanceProductListText() {
        return helper.waitToElementGetText(branchDistanceProductListText);
    }

    public String getTextRatingText() {
        return helper.waitToElementGetText(ratingText1);
    }

    public String getTextDistanceText() {
        return helper.waitToElementGetText(DistanceText1);
    }

    public String getTextContactTitle() {
        return helper.waitToElementGetText(contactTitle1);
    }

    public String getTextPhoneNumberBranch() {
        return helper.waitToElementGetText(phoneNumberBranch1);
    }

    public void checkAPIBranchDetail() {
        response = getStoreDetailByIdFromStoreApp(BranchDataTest.ADDRESS_HOME);
        String data = response.asPrettyString();
        helper.writeFile(FILE_PATH_BRANCH_DETAIL, data);
    }

    public void checkAPIAllBranch() {
        response = getAllBranchesByStoreId();
        String data = response.asPrettyString();
        helper.writeFile(FILE_PATH_BRANCH_ALL, data);
    }

    public static String checkApiBranchNameProductList(String address) {
        return getDataFromBranchesByStoreId(address, "storeName");
    }
    public static String checkApiBranchProductList(String StoreName, String key) {
        return getBranchAddressProductList2(StoreName, key);
    }
    public static String checkApiBranchInfo(String storeName, String key){
        return getBranchInfoFromAllBranchInfo(storeName,key );
    }

    public static String checkApiBranchAddressProductListText(String address) {
        return getDataFromBranchesByStoreId(address, "address");
    }

    public static String checkAPIBranchAddressProductList(String address) {
        return getBranchAddressProductList(address);
    }

    public static String checkApiBranchDistanceProductList(String address) {
        return getDataFromBranchesByStoreId(address, "distance") + " km";
    }

    public static String checkApiBranchPhoneNumber(String address) {
        return getDataFromBranchesByStoreId(address, "phoneNumber");
    }
}

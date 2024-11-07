package com.fnb.app.storeapp.android.linstore.pages.addressmanagement.userlocation;

import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class UserLocationPage extends BaseSetup {

    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;


    public UserLocationPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"LanguageBtn\"]")
    public WebElement LanguageBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"LanguageIcon\"]")
    public WebElement LanguageIcon;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"LanguageBtnText\"]")
    public WebElement LanguageBtnText;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"NoLocationIcon\"]")
    public WebElement NoLocationIcon;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"WhereAreYouNowText\"]")
    public WebElement WhereAreYouNowText;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TurnOnLocationText\"]")
    public WebElement TurnOnLocationText;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AllowLocationToFindBranchesText\"]")
    public WebElement AllowLocationToFindBranchesText;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"TryAgainBtn\"]")
    public WebElement TryAgainBtn;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TryAgainText\"]")
    public WebElement TryAgainText;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"InputUserAddressField\"]")
    public WebElement InputUserAddressField;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"SearchIcon\"]")
    public WebElement SearchIcon;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"PlaceHolderChooseAddress\"]")
    public WebElement PlaceHolderChooseAddress;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"backBtn\"]")
    public WebElement backBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"Title\"]")
    public WebElement Title;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"IconSearchMap\"]")
    public WebElement IconSearchMap;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"SearchResultMatchAddressText\"]")
    public WebElement SearchResultMatchAddressText;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"IconGgMap\"]")
    public WebElement IconGgMap;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SearchResultMatchAddress-0\"]")
    public WebElement SearchResultMatchAddress0;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SearchResultMatchAddress-1\"]")
    public WebElement SearchResultMatchAddress1;
    @FindBy(xpath = "(//android.widget.TextView[@text=\"Turn on your location!\"])[2]")
    public WebElement TurnOnLocationPopup;
    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")
    public WebElement WhileUsingTheApp;
    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_one_time_button\"]")
    public WebElement OnThisTime;
    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_deny_and_dont_ask_again_button\"]")
    public WebElement DoNotAllow;


    public Boolean checkDisplayLanguageBtn() {
        return helper.checkElementDisplay(LanguageBtn);
    }

    public Boolean checkTurnOnLocationPopup() {
        return helper.checkElementDisplay(TurnOnLocationPopup);
    }

    public Boolean checkDisplayLanguageIcon() {
        return helper.checkElementDisplay(LanguageIcon);
    }

    public Boolean checkDisplayLanguageBtnText() {
        return helper.checkElementDisplay(LanguageBtnText);
    }

    public Boolean checkDisplayNoLocationIcon() {
        return helper.checkElementDisplay(NoLocationIcon);
    }

    public Boolean checkDisplayWhereAreYouNowText() {
        return helper.checkElementDisplay(WhereAreYouNowText);
    }

    public Boolean checkDisplayTurnOnLocationText() {
        return helper.checkElementDisplay(TurnOnLocationText);
    }

    public Boolean checkDisplayAllowLocationToFindBranchesText() {
        return helper.checkElementDisplay(AllowLocationToFindBranchesText);
    }

    public Boolean checkDisplayTryAgainBtn() {
        return helper.checkElementDisplay(TryAgainBtn);
    }

    public Boolean checkDisplayInputUserAddressField() {
        return helper.checkElementDisplay(InputUserAddressField);
    }

    public Boolean checkDisplaySearchIcon() {
        return helper.checkElementDisplay(SearchIcon);
    }

    public Boolean checkDisplayPlaceHolderChooseAddress() {
        return helper.checkElementDisplay(PlaceHolderChooseAddress);
    }

    public Boolean checkDisplayGGIcon() {
        return helper.checkElementDisplay(IconGgMap);
    }

    public Boolean checkDisplayIconSearchMap() {
        return helper.checkElementDisplay(IconSearchMap);
    }

    public Boolean checkDisplayBackBtn() {
        return helper.checkElementDisplay(backBtn);
    }

    public Boolean checkDisplayTitle() {
        return helper.checkElementDisplay(Title);
    }

    public Boolean checkDisplaySearchResultMatchAddressText() {
        return helper.checkElementDisplay(SearchResultMatchAddressText);
    }

    public Boolean checkDisplaySearchResultMatchAddress0() {
        return helper.checkElementDisplay(SearchResultMatchAddress0);
    }

    public Boolean checkDisplaySearchResultMatchAddress1() {
        return helper.checkElementDisplay(SearchResultMatchAddress1);
    }

    public String getTextWhereAreYouNowText() {
        return helper.waitToElementGetText(WhereAreYouNowText);
    }

    public String getTextTurnOnLocationText() {
        return helper.waitToElementGetText(TurnOnLocationText);
    }

    public String getTextAllowLocationToFindBranchesText() {
        return helper.waitToElementGetText(AllowLocationToFindBranchesText);
    }

    public String getTextPlaceHolderChooseAddress() {
        return helper.waitToElementGetText(PlaceHolderChooseAddress);
    }


    public String getTextSearchResultMatchAddress0() {
        return helper.waitToElementGetText(SearchResultMatchAddress0);
    }

    public String getTextSearchResultMatchAddress1() {
        return helper.waitToElementGetText(SearchResultMatchAddress1);
    }

    public void inputSearchResultMatchAddressText(String address) {
        helper.waitToElementSendKey(SearchResultMatchAddressText, address);
    }

    public void clickInputUserAddressField() {
        helper.waitToElementClick(InputUserAddressField);
    }

    public void clickSearchResultMatchAddressText() {
        helper.waitToElementClick(SearchResultMatchAddressText);
    }

    public void clickTryAgainBtn() {
        helper.waitToElementClick(TryAgainBtn);
    }

    public void clickWhileUsingTheApp() {
        helper.waitToElementClick(WhileUsingTheApp);
    }

    public void clickOnThisTime() {
        helper.waitToElementClick(OnThisTime);
    }

    public void clickDoNotAllow() {
        try {
            if (helper.checkElementDisplay(DoNotAllow)) {
                helper.waitToElementClick(DoNotAllow);
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void clickSearchResultMatchAddress0() {
        helper.waitToElementClick(SearchResultMatchAddress0);
    }
}

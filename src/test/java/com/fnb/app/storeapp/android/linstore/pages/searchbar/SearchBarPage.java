package com.fnb.app.storeapp.android.linstore.pages.searchbar;

import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchBarPage extends BaseSetup {

    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;
    static final String INPUT = "combo";
    static final String INPUT_1 = "combo product";
    static final String INPUT_2 = "product";

    public SearchBarPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"searchBarHomePage\"]")
    private WebElement searchBarField;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"backBtn\"]")
    private WebElement backBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"searchIcon\"]")
    private WebElement searchIcon;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"searchInputField\"]")
    private WebElement searchInputField;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"noStoreFoundText\"]")
    private WebElement noAnyResult;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"historyTitle\"]")
    private WebElement historyTitle;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"clearHistoryBtn\"]")
    private WebElement clearHistoryBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"historyTag-0\"]")
    private WebElement historyTag;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"recommendTitle\"]")
    private WebElement recommendTitle;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"recommendTag-0\"]")
    private WebElement tag0;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"recommendTag-1\"]")
    private WebElement tag1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"recommendTag-2\"]")
    private WebElement tag2;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"recommendTag-3\"]")
    private WebElement tag3;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"recommendTag-4\"]")
    private WebElement tag4;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"recommendTag-5\"]")
    private WebElement tag5;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"recommendTag-6\"]")
    private WebElement tag6;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"recommendTag-7\"]")
    private WebElement tag7;
    @FindBy(xpath = "//android.widget.ScrollView[@resource-id=\"listSearchResult\"]")
    private WebElement listSearchResult;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"itemInfoId-0\"]")
    private WebElement itemInfoId0;
    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"thumbnailId-0\"]")
    private WebElement thumbnailId;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"itemNameId-0\"]")
    private WebElement itemNameId;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"sellingPriceId-0\"]")
    private WebElement sellingPriceId;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"originalPriceid-0\"]")
    private WebElement originalPriceid;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"EmptySearchResultIcon\"]")
    private WebElement EmptySearchResultIcon;

//    public SearchBarPage() {
//        PageFactory.initElements(driver, this);
//    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public Boolean checkDisplaySeachIcon() {
        return helper.checkElementDisplay(searchIcon);
    }

    public Boolean checkDisplaySeachInputField() {
        return helper.checkElementDisplay(searchInputField);
    }

    public Boolean checkDisplayHistoryTitle() {
        return helper.checkElementDisplay(historyTitle);
    }

    public Boolean checkDisplayRecommendTitle() {
        return helper.checkElementDisplay(recommendTitle);
    }

    public Boolean checkDisplayTag0() {
        return helper.checkElementDisplay(tag0);
    }

    public Boolean checkDisplayTag1() {
        return helper.checkElementDisplay(tag1);
    }

    public Boolean checkDisplayTag2() {
        return helper.checkElementDisplay(tag2);
    }

    public Boolean checkDisplayTag3() {
        return helper.checkElementDisplay(tag3);
    }

    public Boolean checkDisplayTag4() {
        return helper.checkElementDisplay(tag4);
    }

    public Boolean checkDisplayTag5() {
        return helper.checkElementDisplay(tag5);
    }

    public Boolean checkDisplayTag6() {
        return helper.checkElementDisplay(tag6);
    }

    public Boolean checkDisplayTag7() {
        return helper.checkElementDisplay(tag7);
    }

    public Boolean checkDisplayHistoryTag() {
        return helper.checkElementDisplay(historyTag);
    }

    public Boolean checkDisplayItemInfoId0() {
        return helper.checkElementDisplay(itemInfoId0);
    }

    public Boolean checkDisplayThumbnailId0() {
        return helper.checkElementDisplay(thumbnailId);
    }

    public Boolean checkDisplayItemNameId() {
        return helper.checkElementDisplay(itemNameId);
    }

    public Boolean checkDisplaySellingPriceId() {
        return helper.checkElementDisplay(sellingPriceId);
    }

    public Boolean checkDisplayOriginalPriceId() {
        return helper.checkElementDisplay(originalPriceid);
    }

    public Boolean checkDisplayNoResult() {
        return helper.checkElementDisplay(noAnyResult);
    }

    public Boolean checkDisplayEmptySearchResultIcon() {
        return helper.checkElementDisplay(EmptySearchResultIcon);
    }


    public String checkTextNoResult() {
        return helper.waitToElementGetText(noAnyResult);
    }

    public void navigateToSearchItemField() {
        helper.waitToElementClick(searchBarField);
        helper.waitToElementSendKey(searchInputField, INPUT);
        helper.waitToElementClick(searchIcon);
//        navigateBack();
        navigateBack();
    }

    public void searchItemNoResult() {
        helper.waitToElementClear(searchInputField);
        helper.waitToElementSendKey(searchInputField, INPUT_1);
        helper.waitToElementClick(searchIcon);
    }

    public void searchItemResult() {
        helper.waitToElementClear(searchInputField);
        helper.waitToElementSendKey(searchInputField, INPUT_2);
        helper.waitToElementClick(searchIcon);
    }
}

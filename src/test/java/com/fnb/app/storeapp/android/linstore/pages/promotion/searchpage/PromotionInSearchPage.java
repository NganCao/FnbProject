package com.fnb.app.storeapp.android.linstore.pages.promotion.searchpage;

import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.fnb.app.setup.BaseTest.homePageLinStore;

public class PromotionInSearchPage extends BaseSetup {
    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;

    PromotionInSearchPage promotionInSearchPage;

    public PromotionInSearchPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        promotionInSearchPage = homePageLinStore.navigateToPromotionInSearchPage();
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"searchBarHomePage\"]")
    public WebElement searchBarHomePage;

    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"searchInputField\"]")
    public WebElement searchInputField;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"searchIcon\"]")
    public WebElement searchIcon;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
    public WebElement MenuProfileIcon1;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"itemInfoId-0\"]")
    public WebElement itemInfoId0;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"thumbnailId-0\"]")
    public WebElement thumbnailId0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"itemNameId-0\"]")
    public WebElement itemNameId0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"sellingPriceId-0\"]")
    public WebElement sellingPriceId0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"originalPriceid-0\"]")
    public WebElement originalPriceid0;

//    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
//    public WebElement MenuProfileIcon1;

//    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
//    public WebElement MenuProfileIcon1;
//
//    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
//    public WebElement MenuProfileIcon1;
//
//    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
//    public WebElement MenuProfileIcon1;
//
//    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
//    public WebElement MenuProfileIcon1;
//
//    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
//    public WebElement MenuProfileIcon1;
//
//    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
//    public WebElement MenuProfileIcon1;
//
//    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
//    public WebElement MenuProfileIcon1;
//
//    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
//    public WebElement MenuProfileIcon1;
//
//    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
//    public WebElement MenuProfileIcon1;
}

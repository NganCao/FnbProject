package com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion;

import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListPage;
import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import io.appium.java_client.android.AndroidDriver;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.fnb.app.setup.BaseTest.homePageLinStore;
import static com.fnb.utils.api.storeapp.pos.Get_DiscountCodeCheckout_Request.getValuePromotionCheckout;
import static com.fnb.utils.api.storeapp.pos.Get_DiscountCodeRequest.getCheckPromotion;
import static com.fnb.utils.api.storeapp.pos.Get_DiscountCodeRequest.getValuePromotion;
import static com.fnb.utils.api.storeapp.pos.Get_DiscountDetailRequest.*;
import static com.fnb.utils.api.storeapp.pos.Get_VoucherDetailRequest.*;
import static com.fnb.utils.api.storeapp.pos.Post_PromotionCheckout_Request.valueListPromotionCanApply;

public class PromotionProductListPage extends BaseSetup {

    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;
    AddressManagementListPage addressManagementListPage;
    public static final String UTC_ZONE = "UTC";
    public static final String VN_ZONE = "Asia/Ho_Chi_Minh";
    private static final DecimalFormat formatter = new DecimalFormat("#,###");

    public PromotionProductListPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        addressManagementListPage = homePageLinStore.navigateToAddressManagementListPage();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DeliveryPickUpText\"]")
    public WebElement DeliveryPickUpText;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Order summary\"]")
    public WebElement OrderSummary;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"UseOffersToGetDiscountsField\"]")
    public WebElement UseOffersToGetDiscountsField;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionApplyNameText0\"]")
    public WebElement PromotionApplyNameText0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionApplyNameText1\"]")
    public WebElement PromotionApplyNameText1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherApplyName0\"]")
    public WebElement VoucherApplyName0;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
    public WebElement ProductListBtn;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ComboName0\"]")
    public WebElement ComboName0;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ComboName1\"]")
    public WebElement ComboName1;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"PromotionSectionField\"]")
    public WebElement PromotionSectionField;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"StorePromotionTitle\"]")
    public WebElement StorePromotionTitle;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"PromotionApplyIcon0\"]")
    public WebElement PromotionApplyIcon0;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"PromotionApplyIcon1\"]")
    public WebElement PromotionApplyIcon1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionApplyingText0\"]")
    public WebElement PromotionApplyingText0;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionApplyingText1\"]")
    public WebElement PromotionApplyingText1;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"PromotionApplyPromotionTagIcon0\"]")
    public WebElement PromotionApplyPromotionTagIcon0;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"PromotionApplyPromotionTagIcon1\"]")
    public WebElement PromotionApplyPromotionTagIcon1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionApplyType0\"]")
    public WebElement PromotionApplyType0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionApplyAmount0\"]")
    public WebElement PromotionApplyAmount0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionApplyUnit0\"]")
    public WebElement PromotionApplyUnit0;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"PromotionApplyTimeIcon0\"]")
    public WebElement PromotionApplyTimeIcon0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionApplyExpTime0\"]")
    public WebElement PromotionApplyExpTime0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"MyVoucherList\"]")
    public WebElement MyVoucherList;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"VoucherApplypromotionTagIcon0\"]")
    public WebElement VoucherApplypromotionTagIcon0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherApplyCode0\"]")
    public WebElement VoucherApplyCode0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherApplyType0\"]")
    public WebElement VoucherApplyType0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherApplyAmout0\"]")
    public WebElement VoucherApplyAmout0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherApplyUnit0\"]")
    public WebElement VoucherApplyUnit0;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"VoucherApplyTimeIcon0\"]")
    public WebElement VoucherApplyTimeIcon0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherApplyExpTime0\"]")
    public WebElement VoucherApplyExpTime0;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"VoucherApplyBtn0\"]")
    public WebElement VoucherApplyBtn0;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"PromoIcon\"]")
    public WebElement PromoIcon;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionDiscount\"]")
    public WebElement PromotionDiscount;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"InfoVoucher\"]")
    public WebElement InfoVoucher;

    @FindBy(xpath = "(//android.view.View[@resource-id=\"TabBarOverView\"])[1]")
    public WebElement DiscountCodeTab;

    @FindBy(xpath = "(//android.view.View[@resource-id=\"TabBarOverView\"])[2]")
    public WebElement ApplicableVoucherTab;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"backBtn\"]")
    public WebElement backBtn;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"Title\"]")
    public WebElement Title;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ViewVoucher0\"]")
    public WebElement ViewVoucher0;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ViewVoucherItem0\"]")
    public WebElement ViewVoucherItem0;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"IconPromoPercent0\"]")
    public WebElement IconPromoPercent0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"NamePromo0\"]")
    public WebElement NamePromo0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherInfoBox0\"]")
    public WebElement VoucherInfoBox0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"GenerateCode0\"]")
    public WebElement GenerateCode0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"InfoPromo0\"]")
    public WebElement InfoPromo0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ExpiredDate0\"]")
    public WebElement ExpiredDate0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountAmount0\"]")
    public WebElement DiscountAmount0;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ViewVoucher1\"]")
    public WebElement ViewVoucher1;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ViewVoucherItem1\"]")
    public WebElement ViewVoucherItem1;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"IconPromoPercent1\"]")
    public WebElement IconPromoPercent1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"NamePromo1\"]")
    public WebElement NamePromo1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherInfoBox1\"]")
    public WebElement VoucherInfoBox1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"GenerateCode1\"]")
    public WebElement GenerateCode1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"InfoPromo1\"]")
    public WebElement InfoPromo1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ExpiredDate1\"]")
    public WebElement ExpiredDate1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountAmount1\"]")
    public WebElement DiscountAmount1;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ViewVoucher2\"]")
    public WebElement ViewVoucher2;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ViewVoucherItem2\"]")
    public WebElement ViewVoucherItem2;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"IconPromoPercent2\"]")
    public WebElement IconPromoPercent2;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"NamePromo2\"]")
    public WebElement NamePromo2;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherInfoBox2\"]")
    public WebElement VoucherInfoBox2;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"GenerateCode2\"]")
    public WebElement GenerateCode2;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"InfoPromo2\"]")
    public WebElement InfoPromo2;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ExpiredDate2\"]")
    public WebElement ExpiredDate2;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountAmount2\"]")
    public WebElement DiscountAmount2;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ViewVoucher3\"]")
    public WebElement ViewVoucher3;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ViewVoucherItem3\"]")
    public WebElement ViewVoucherItem3;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"IconPromoPercent3\"]")
    public WebElement IconPromoPercent3;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"NamePromo3\"]")
    public WebElement NamePromo3;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherInfoBox3\"]")
    public WebElement VoucherInfoBox3;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"GenerateCode3\"]")
    public WebElement GenerateCode3;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"InfoPromo3\"]")
    public WebElement InfoPromo3;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ExpiredDate3\"]")
    public WebElement ExpiredDate3;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountAmount3\"]")
    public WebElement DiscountAmount3;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ViewVoucher4\"]")
    public WebElement ViewVoucher4;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ViewVoucher5\"]")
    public WebElement ViewVoucher5;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ViewVoucherItem4\"]")
    public WebElement ViewVoucherItem4;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ViewVoucherItem5\"]")
    public WebElement ViewVoucherItem5;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"IconPromoPercent4\"]")
    public WebElement IconPromoPercent4;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"IconPromoPercent5\"]")
    public WebElement IconPromoPercent5;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"NamePromo4\"]")
    public WebElement NamePromo4;


    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"NamePromo5\"]")
    public WebElement NamePromo5;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherInfoBox4\"]")
    public WebElement VoucherInfoBox4;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherInfoBox5\"]")
    public WebElement VoucherInfoBox5;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"GenerateCode4\"]")
    public WebElement GenerateCode4;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"GenerateCode5\"]")
    public WebElement GenerateCode5;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"InfoPromo4\"]")
    public WebElement InfoPromo4;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"InfoPromo5\"]")
    public WebElement InfoPromo5;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ExpiredDate4\"]")
    public WebElement ExpiredDate4;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ExpiredDate5\"]")
    public WebElement ExpiredDate5;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountAmount4\"]")
    public WebElement DiscountAmount4;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountAmount5\"]")
    public WebElement DiscountAmount5;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"CloseDiscountDetailModal\"]")
    public WebElement CloseDiscountDetailModal;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"AvatarBox\"]")
    public WebElement AvatarBox;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountName\"]")
    public WebElement DiscountName;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountCode\"]")
    public WebElement DiscountCode;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountType\"]")
    public WebElement DiscountType;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ExpiredDate\"]")
    public WebElement ExpiredDate;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountValueText\"]")
    public WebElement DiscountValueText;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductCategoryTitle\"]")
    public WebElement ProductCategoryTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"BranchesTitle\"]")
    public WebElement BranchesTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountMaximumTitle\"]")
    public WebElement DiscountMaximumTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"MaximumDiscountAmount\"]")
    public WebElement MaximumDiscountAmount;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"StartDateTitle\"]")
    public WebElement StartDateTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"EndDateTitle\"]")
    public WebElement EndDateTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"StartDate\"]")
    public WebElement StartDate;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"EndDate\"]")
    public WebElement EndDate;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TermsAndConditionsTitle\"]")
    public WebElement TermsAndConditionsTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TermsAndCondition\"]")
    public WebElement TermsAndCondition;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"CouponConditionsTitle\"]")
    public WebElement CouponConditionsTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PlatformsTitle\"]")
    public WebElement PlatformsTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"IncludedTopping\"]")
    public WebElement IncludedTopping;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"LimitNumberOfTimesThisCouponCanBeUsedTitle\"]")
    public WebElement LimitNumberOfTimesThisCouponCanBeUsedTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"MaximumLimitCouponUse\"]")
    public WebElement MaximumLimitCouponUse;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"LimitOneUsedPerCustomer\"]")
    public WebElement LimitOneUsedPerCustomer;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductTitle\"]")
    public WebElement ProductTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"CouponCondition\"]")
    public WebElement CouponCondition;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AllBranchesText\"]")
    public WebElement AllBranchesText;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"IconDiscount\"]")
    public WebElement IconDiscount;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"ViewIconDiscount\"]")
    public WebElement ViewIconDiscount;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherName\"]")
    public WebElement VoucherName;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherType\"]")
    public WebElement VoucherType;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ViewDiscountAmount\"]")
    public WebElement ViewDiscountAmount;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AdditionalConditions\"]")
    public WebElement AdditionalConditions;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"BranchCheckIcon\"]")
    public WebElement BranchCheckIcon;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ApplyForAllBranches\"]")
    public WebElement ApplyForAllBranches;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"PurchaseCheckIcon\"]")
    public WebElement PurchaseCheckIcon;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"CheckboxPurchaseAmount\"]")
    public WebElement CheckboxPurchaseAmount;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherDetail\"]")
    public WebElement VoucherDetail;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"SpecificBranchesCheckIcon\"]")
    public WebElement SpecificBranchesCheckIcon;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SpecificBranchesTitle\"]")
    public WebElement SpecificBranchesTitle;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"Branch0\"]")
    public WebElement Branch0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"Branch1\"]")
    public WebElement Branch1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"Branch2\"]")
    public WebElement Branch2;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"Branch3\"]")
    public WebElement Branch3;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"Branch8\"]")
    public WebElement Branch8;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductCategoryName0\"]")
    public WebElement ProductCategoryName0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductCategoryName1\"]")
    public WebElement ProductCategoryName1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductCategoryName2\"]")
    public WebElement ProductCategoryName2;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductCategoryName3\"]")
    public WebElement ProductCategoryName3;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductCategoryName4\"]")
    public WebElement ProductCategoryName4;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"BranchName0\"]")
    public WebElement BranchName0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"BranchName1\"]")
    public WebElement BranchName1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"BranchName2\"]")
    public WebElement BranchName2;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"BranchName3\"]")
    public WebElement BranchName3;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"BranchName4\"]")
    public WebElement BranchName4;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"BranchName5\"]")
    public WebElement BranchName5;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"BranchName6\"]")
    public WebElement BranchName6;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"IncludedToppingCheckIcon\"]")
    public WebElement IncludedToppingCheckIcon;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TermsAndConditions0\"]")
    public WebElement TermsAndConditions0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductDiscountsApply\"]")
    public WebElement ProductDiscountsApply;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductDiscountsApply0\"]")
    public WebElement ProductDiscountsApply0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductDiscountsApply1\"]")
    public WebElement ProductDiscountsApply1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductDiscountsApply2\"]")
    public WebElement ProductDiscountsApply2;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductDiscountsApply3\"]")
    public WebElement ProductDiscountsApply3;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductDiscountsApply4\"]")
    public WebElement ProductDiscountsApply4;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductDiscountsApply5\"]")
    public WebElement ProductDiscountsApply5;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductCategoryDiscountsApply\"]")
    public WebElement ProductCategoryDiscountsApply;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductCategoryDiscountsApply0\"]")
    public WebElement ProductCategoryDiscountsApply0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductCategoryDiscountsApply1\"]")
    public WebElement ProductCategoryDiscountsApply1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductCategoryDiscountsApply2\"]")
    public WebElement ProductCategoryDiscountsApply2;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductCategoryDiscountsApply3\"]")
    public WebElement ProductCategoryDiscountsApply3;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductCategoryDiscountsApply4\"]")
    public WebElement ProductCategoryDiscountsApply4;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductCategoryDiscountsApply5\"]")
    public WebElement ProductCategoryDiscountsApply5;

    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")
    private WebElement AcceptBtn;

    public boolean checkDisplay(WebElement webElement) {
        try {
            return helper.checkElementDisplay(webElement);
        } catch (Exception e) {
            Log.fatal("Element is not Display" + PromotionProductListPage.class.getName());
            e.printStackTrace();
            return false;
        }

    }


    public void moveScreen() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Log.info("Error when move Screen" + PromotionProductListPage.class.getName());
            e.printStackTrace();
        }
    }

    public String getText(WebElement element) {
        try {
            return helper.waitToElementGetText(element);
        } catch (Exception e) {
            Log.info("Element can't get text" + PromotionProductListPage.class.getName());
            e.printStackTrace();
            return null;
        }
    }

    public void clickElement(WebElement element) {
        try {
            helper.waitToElementClick(element);
        } catch (Exception e) {
            Log.info("Element can't click" + PromotionProductListPage.class.getName());
            e.printStackTrace();
        }
    }

    public void clickButtonProductList() {
        try {
            if (helper.checkElementDisplay(AcceptBtn)) {
                helper.waitToElementClick(AcceptBtn);
            }
            helper.waitToElementClick(ProductListBtn);

        } catch (Exception e) {
            Log.info("Element can't click" + PromotionProductListPage.class.getName());
            e.printStackTrace();
        }
    }

    public void navigateBack() {
        driver.navigate().back();
    }


    public static String getValueDiscountCodeSection() {
        int s = getValuePromotion();
        if (getCheckPromotion("isPercentDiscount")) {
            return "Giảm " + s + "%";
        } else return "Giảm " + s + "đ";
    }

    public static String getDataFromDiscountCodeDetail(String discountName, String key) {
        return getDiscountCodeDetail(discountName, key);
    }

    public static String getDataFromVoucherDetail(String voucherName, String key) {
        return getVoucherDetail(voucherName, key);
    }

    public static String verifyDiscountCodeType(String discountName) {
        String discountCode = getDiscountCodeDetail(discountName, "discountCodeTypeId");
        return mapPromotionTypeToDescription(discountCode);
    }

    public static String verifyVoucherType(String voucherName) {
        String promotionTypeId = getVoucherDetail(voucherName, "promotionTypeId");
        return mapPromotionTypeToDescription(promotionTypeId);
    }

    public static String verifyDiscountCodeType1(String discountName) {
        String discountCodeTypeId = getStringValueDiscountCodeDetail(discountName, "discountCodeTypeId");
        return mapPromotionTypeToDescription(discountCodeTypeId);
    }

    public static String verifyVoucherType1(String voucherName) {
        String promotionTypeId = getStringValueVoucherDetail(voucherName, "promotionTypeId");
        return mapPromotionTypeToDescription(promotionTypeId);
    }

    public static String verifyVoucherType2(String key) {
        String valueListPromotionCanApply = getValueListPromotionCanApply(key);
        return mapPromotionTypeToDescription(valueListPromotionCanApply);
    }

    public static String verifyVoucherType3(String dicountName, String key) {
        String valueListPromotionCanApply = getValueDiscountCheckoutPage(dicountName, key);
        return mapPromotionTypeToDescription(valueListPromotionCanApply);
    }

    private static String mapPromotionTypeToDescription(String promotionTypeId) {
        switch (promotionTypeId) {
            case "0":
                return "Giảm giá trên tổng hóa đơn";
            case "1":
                return "Giảm giá cho sản phẩm";
            default:
                return "Giảm giá đối với danh mục sản phẩm";
        }
    }

    public static String formatDateString0(String inputDate) {
        return formatDateString(inputDate, "'Hết hạn: Thg 'MM dd, yyyy - HH:mm");
    }

    public static String formatDateString1(String inputDate) {
        return formatDateString(inputDate, "'Thg 'MM dd, yyyy HH:mm");
    }

    public static String formatDateString2(String inputDate) {
        return formatDateString(inputDate, "'Hết hạn: Thg 'MM dd, yyyy HH:mm");
    }

    public static String formatDateString3(String inputDate) {
        return formatDateString(inputDate, "'Hết hạn: Thg ' dd/MM/yyyy");
    }


    private static String formatDateString(String inputDate, String pattern) {
        System.out.println(inputDate);
        LocalDateTime utcDateTime = LocalDateTime.parse(inputDate, DateTimeFormatter.ISO_DATE_TIME);
        ZoneId utcZoneId = ZoneId.of(UTC_ZONE);
        ZoneId vnZoneId = ZoneId.of(VN_ZONE);
        ZonedDateTime utcZonedDateTime = ZonedDateTime.of(utcDateTime, utcZoneId);
        ZonedDateTime vnZonedDateTime = utcZonedDateTime.withZoneSameInstant(vnZoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return vnZonedDateTime.format(formatter);
    }

    public static String expiredDateDiscount(String discountName) {
        String inputDate = getDiscountCodeDetail(discountName, "endDate");
        return formatDateString2(inputDate);
    }

    public static String expiredDateVoucher(String voucherName) {
        String inputDate = getVoucherDetail(voucherName, "endDate");
        return formatDateString3(inputDate);
    }

    public static String expiredDateDetail(String discountName, String key) {
        String inputDate = getStringValueDiscountCodeDetail(discountName, key);
        return formatDateString1(inputDate);
    }

    public static String expiredDateDetail2(String discountName, String key) {
        String inputDate = getStringValueDiscountCodeDetail(discountName, key);
        return formatDateString0(inputDate);
    }

    public static String formatAmount(String percentNumber, String isPercentDiscount) {
        if (isPercentDiscount.equals("true")) {
            return percentNumber + "%";
        } else if (isPercentDiscount.equals("false")) {
            return percentNumber + "đ";
        }
        return null;
    }

    public static String discountAmount(String discountName) {
        String isPercentDiscount = getDiscountCodeDetail(discountName, "isPercentDiscount");
        String percentNumber = getDiscountCodeDetail(discountName, "percentNumber").replaceAll("\\..*$", "");
        return formatAmount(percentNumber, isPercentDiscount);
    }

    public static String voucherAmount(String voucherName) {
        String isPercentDiscount = getVoucherDetail(voucherName, "isPercentDiscount");
        String percentNumber = getVoucherDetail(voucherName, "percentNumber").replaceAll("\\..*$", "");
        return formatAmount(percentNumber, isPercentDiscount);
    }

    public static String discountAmountDetail(String discountName) {
        String isPercentDiscount = getStringValueDiscountCodeDetail(discountName, "isPercentDiscount");
        String percentNumber = getStringValueDiscountCodeDetail(discountName, "percentNumber").replaceAll("\\..*$", "");
        return formatAmount(percentNumber, isPercentDiscount);
    }

    public static String voucherAmountDetail(String discountName) {
        String isPercentDiscount = getStringValueVoucherDetail(discountName, "isPercentDiscount");
        String percentNumber = getStringValueVoucherDetail(discountName, "percentNumber").replaceAll("\\..*$", "");
        return formatAmount(percentNumber, isPercentDiscount);
    }

    public static String checkDiscountCodeDetail(String discountName, String key) {
        return getStringValueDiscountCodeDetail(discountName, key);
    }

    public static String checkVoucherDetail(String discountName, String key) {
        return getStringValueVoucherDetail(discountName, key);
    }

    public static String checkIncludedTopping(String discountName, String key) {
        String s = getStringValueDiscountCodeDetail(discountName, key);
        if (s == "true") {
            s = "Bao gồm Món thêm";
            return s;
        } else return null;
    }

    public static String checkProductCategoryName(String discountName, String key, String value) {
        return getDataDiscountCodeDetailArray2(discountName, "productCategories", key, value);
    }

    public static String checkVoucherProductCategoryName(String discountName, String key, String value) {
        return getDataVoucherDetailArray2(discountName, "productCategories", key, value);
    }

    public static String checkAllBranchName(String discountName) {
        return getStringValueDiscountCodeDetail(discountName, "isAllBranches");
    }

    public static String checkBranchName(String discountName, String key, String value) {
        return getDataDiscountCodeDetailArray2(discountName, "branches", key, value);
    }

    public static String checkVoucherBranchName(String discountName, String key, String value) {
        return getDataVoucherDetailArray2(discountName, "branches", key, value);
    }

    public void scrollInScreen(WebElement elementA, WebElement elementB) {
        addressManagementListPage.swipeVertical(elementA, elementB);
    }

    public static String getValueDiscountAmount(String discountName, String key) {
        String amount = getStringValueDiscountCodeDetail(discountName, key);
        return formatAmount(amount) + "đ";
    }

    public static String getValueDiscountAmountCanApply(String key) {
        String amount = getValueListPromotionCanApply(key);
        System.out.println(formatAmount(amount));
        return formatAmount(amount);
    }

    public static String getValueDiscountAmountCheckoutPage(String discountName, String key) {
        String amount = getValueDiscountCheckoutPage(discountName, key);
        return formatAmount(amount);
    }

    public static String getValueVoucherAmountCheckoutPage() {
        String amount = getValueListPromotionCanApply("percentNumber");
        if (amount.equals("0.0")) {
            amount = getValueListPromotionCanApply("maximumDiscountAmount");
        } else amount = amount;
        return formatAmount(amount);
    }

//    private static String formatAmount(String amount) {
//        int value = Integer.parseInt(amount.replaceAll("\\..*$", ""));
//        return formatter.format(value);
//    }

    private static String formatAmount(String amount) {
        int value;
        if (amount.contains(".")) {
            value = Integer.parseInt(amount.replaceAll("\\..*$", ""));
        } else {
            amount = amount.replaceAll(",.*", "");
            value = Integer.parseInt(amount);
        }
        System.out.println(value);
        return formatter.format(value);
    }

    private static String formatPlatformLabel(List<String> platformList) {
        String label = "Platform(";
        for (String platform : platformList) {
            label = label + platform;
        }
        return label + ")";
    }

    public static String getPlatformText(String discountName) {
//        Platforms(POS, GoF&B App, Store Web, Store App)
        Response response = DiscountCodeDetail(discountName);
        String jsonString = response.asString();
        List<String> platformList = new ArrayList<>();
        String txt = "";
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONObject discountCode = (JSONObject) jsonObject.get("discountCode");
            JSONArray platformArr = (JSONArray) discountCode.get("platforms");
            for (Object obj : platformArr) {
                JSONObject platform = (JSONObject) obj;
                platformList.add(platform.get("platformName").toString());
            }
            txt = formatPlatformLabel(platformList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return txt;
    }

    public static String getValueListPromotionCanApply(String key) {
        return valueListPromotionCanApply(key);
    }

    public static String checkIsPercent(String key) {
        return getValueListPromotionCanApply(key).equals("0.00") ? "đ" : "%";
    }

    public static String checkIsPercentCheckoutPage(String discountName, String key) {
        return getValueDiscountCheckoutPage(discountName, key).equals("false") ? "đ" : "%";
    }

    public static String getValueDiscountCheckoutPage(String discountName, String key) {
        return getValuePromotionCheckout(discountName, key);
    }
}

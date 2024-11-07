package com.fnb.app.storeapp.android.linstore.scenationtests.promotion.viewpromotion.productlist;

import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion.PromotionProductListPage;
import com.fnb.app.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion.PromotionProductListDataTest.*;
import static com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion.PromotionProductListPage.*;
import static com.fnb.utils.api.storeapp.pos.discountcampaign.Post_CreateVoucherCategory_Request.*;
import static com.fnb.utils.api.storeapp.pos.discountcampaign.Post_CreateVoucherProduct_Request.*;
import static com.fnb.utils.api.storeapp.pos.discountcampaign.Post_CreateVoucherTotal_Request.*;
import static com.fnb.utils.api.storeapp.pos.discountcampaign.Post_StopVoucher_Request.stopVoucher;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_CreateDiscountCodeCategory_Request.*;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_CreateDiscountCodeProduct_Request.*;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_CreateDiscountCodeTotal_Request.*;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_StopDiscountCode_Request.stopDiscountCode;


public class PromotionProductListScenarioTest extends BaseTest {

    PromotionProductListPage promotionProductListPage;
    LoginPageLinStore loginPageLinStore;
    static SoftAssert softAssert;
    static String expectedData;
    static String originalData;
    static String[] nameDiscounts = new String[6];
    static String[] codes = new String[6];
    static String[] types = new String[12];
    static String[] expDates = new String[12];
    static String[] expDateDetails = new String[12];
    static String[] values = new String[12];
    static String[] nameVouchers = new String[6];

    @BeforeClass
    public void navigateToPage() {
        promotionProductListPage = homePageLinStore.navigateToPromotionProductListPage();
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
        softAssert = new SoftAssert();
        createDiscountByApi();
        createVoucherByApi();
    }

    @AfterClass
    public void cleanUpDataApi() {
        endDiscount();
        endVoucher();
    }

    @Test(priority = 0)
    public void verifyDisplayPromotionSectionField() {
//        loginPageLinStore.splashScreen();
        loginPageLinStore.waitToLoadAPI();
        loginPageLinStore.navigateToLoginSuccess();
        loginPageLinStore.waitToLoadAPI();
//        loginPageLinStore.clickAcceptBtn();
        promotionProductListPage.clickButtonProductList();
        promotionProductListPage.scrollInScreen(promotionProductListPage.ComboName1, promotionProductListPage.ComboName0);
        promotionProductListPage.moveScreen();
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionSectionField), "Testcase is fail");
    }

    @Test(priority = 1)
    public void verifyDisplayPromoIcon() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromoIcon), "Testcase is fail");

    }

    @Test(priority = 2)
    public void verifyDisplayPromotionDiscount() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionDiscount), "Testcase is fail");
    }

    @Test(priority = 3)
    public void verifyValuesDiscountPromotion() {
        originalData = promotionProductListPage.getText(promotionProductListPage.PromotionDiscount);
        expectedData = getValueDiscountCodeSection();
        System.out.println(expectedData);
        Assert.assertEquals(originalData, expectedData, "Testcase is fai;");
    }

    @Test(priority = 4)
    public void verifyDisplayInfoVoucher() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.InfoVoucher), "Testcase is fail");
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.InfoVoucher), VIEW_MORE, "Testcase is fail");
        promotionProductListPage.clickElement(promotionProductListPage.PromotionSectionField);
        promotionProductListPage.moveScreen();
    }

    @Test(priority = 5)
    public void verifyDisplayBackBtn() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.backBtn), "Testcase is fail");
    }

    @Test(priority = 6)
    public void verifyDisplayTitle() {
        softAssert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.Title));
        softAssert.assertEquals(promotionProductListPage.getText(promotionProductListPage.Title), TITLE_VN, "Testcase is fail");
        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void verifyDisplayDiscountCodeTabView() {
        promotionProductListPage.clickElement(promotionProductListPage.DiscountCodeTab);
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.DiscountCodeTab), "Testcase is fail");
    }


    @Test(priority = 8)
    public void verifyDisplayViewVoucher0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ViewVoucher0), "Testcase is fail");
    }

    //TODO: Discount
    @Test(priority = 9)
    public void verifyDisplayViewVoucherItem0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ViewVoucherItem0), "Testcase is fail");
    }

    @Test(priority = 10)
    public void verifyDisplayIconPromoPercent0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.IconPromoPercent0), "Testcase is fail");
    }

    @Test(priority = 11)
    public void verifyDisplayNamePromo0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.NamePromo0), "Testcase is fail");
    }

    @Test(priority = 12)
    public void verifyGetTextNamePromo() {
        originalData = promotionProductListPage.getText(promotionProductListPage.NamePromo0);
        Assert.assertEquals(originalData, nameDiscounts[5], "Testcase is fail");
    }

    @Test(priority = 13)
    public void verifyCheckNamePromo0Api() {
        expectedData = getDataFromDiscountCodeDetail(nameDiscounts[5], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }


    @Test(priority = 14)
    public void verifyDisplayGenerateCode0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.GenerateCode0), "Testcase is fail");
    }

    @Test(priority = 15)
    public void verifyGetTextGenerateCode0() {
        originalData = promotionProductListPage.getText(promotionProductListPage.GenerateCode0);
        Assert.assertEquals(originalData, codes[5], "Testcase is fail");
    }

    @Test(priority = 16)
    public void verifyCheckGenerateCode0Api() {
        expectedData = getDataFromDiscountCodeDetail(nameDiscounts[5], KEY_CODE).toUpperCase();
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 17)
    public void verifyDisplayInfoPromo0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.InfoPromo0), "Testcase is fail");
    }

    @Test(priority = 18)
    public void verifyGetTextInfoPromo0() {
        originalData = promotionProductListPage.getText(promotionProductListPage.InfoPromo0);
        Assert.assertEquals(originalData, types[5], "Testcase is fail");
    }

    @Test(priority = 19)
    public void verifyCheckInfoPromo0Api() {
        expectedData = verifyDiscountCodeType(nameDiscounts[5]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 20)
    public void verifyDisplayExpiredDate0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ExpiredDate0), "Testcase is fail");
    }

    @Test(priority = 21)
    public void verifyGetTextExpiredDate0() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate0);
        Assert.assertEquals(originalData, expDateDetails[5], "Testcase is fail");
    }

    @Test(priority = 22)
    public void verifyCheckExpiredDate0Api() {
        expectedData = expiredDateDetail2(nameDiscounts[5], KEY_END_DATE);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 23)
    public void verifyDisplayDiscountAmount0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.DiscountAmount0), "Testcase is fail");
    }

    @Test(priority = 24)
    public void verifyGetTextDiscountAmount0() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountAmount0);
        Assert.assertEquals(originalData, values[5], "Testcase is fail");
    }

    @Test(priority = 25)
    public void verifyCheckDiscountAmount0Api() {
        expectedData = discountAmount(nameDiscounts[5]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    //TODO: Discount1
    @Test(priority = 26)
    public void verifyNamePromo1() {
        expectedData = nameDiscounts[4];
        originalData = promotionProductListPage.getText(promotionProductListPage.NamePromo1);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 27)
    public void verifyGenerateCode1() {
        expectedData = codes[4];
        originalData = promotionProductListPage.getText(promotionProductListPage.GenerateCode1);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 28)
    public void verifyInfoPromo1() {
        expectedData = types[4];
        originalData = promotionProductListPage.getText(promotionProductListPage.InfoPromo1);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 29)
    public void verifyExpiredDate1() {
        expectedData = expDateDetails[4];
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate1);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 30)
    public void verifyDiscountAmount1() {
        expectedData = values[4];
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountAmount1);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    //TODO: Discount2
    @Test(priority = 31)
    public void verifyNamePromo2() {
        expectedData = nameDiscounts[3];
        originalData = promotionProductListPage.getText(promotionProductListPage.NamePromo2);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 32)
    public void verifyGenerateCode2() {
        expectedData = codes[3];
        originalData = promotionProductListPage.getText(promotionProductListPage.GenerateCode2);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 33)
    public void verifyInfoPromo2() {
        expectedData = types[3];
        originalData = promotionProductListPage.getText(promotionProductListPage.InfoPromo2);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 34)
    public void verifyExpiredDate2() {
        expectedData = expDates[3];
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate2);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 35)
    public void verifyDiscountAmount2() {
        expectedData = values[3];
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountAmount2);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    //TODO: Discount3
    @Test(priority = 36)
    public void verifyNamePromo3() {
        expectedData = nameDiscounts[2];
        originalData = promotionProductListPage.getText(promotionProductListPage.NamePromo3);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 37)
    public void verifyGenerateCode3() {
        expectedData = codes[2];
        originalData = promotionProductListPage.getText(promotionProductListPage.GenerateCode3);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 38)
    public void verifyInfoPromo3() {
        expectedData = types[2];
        originalData = promotionProductListPage.getText(promotionProductListPage.InfoPromo3);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 39)
    public void verifyExpiredDate3() {
        expectedData = expDates[2];
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate3);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 40)
    public void verifyDiscountAmount3() {
        expectedData = values[2];
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountAmount3);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    //TODO: Discount4
    @Test(priority = 41)
    public void verifyNamePromo4() {
        promotionProductListPage.scrollInScreen(promotionProductListPage.NamePromo0, promotionProductListPage.NamePromo3);
        expectedData = nameDiscounts[1];
        originalData = promotionProductListPage.getText(promotionProductListPage.NamePromo4);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 42)
    public void verifyGenerateCode4() {
        expectedData = codes[1];
        originalData = promotionProductListPage.getText(promotionProductListPage.GenerateCode4);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 43)
    public void verifyInfoPromo4() {
        expectedData = types[1];
        originalData = promotionProductListPage.getText(promotionProductListPage.InfoPromo4);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 44)
    public void verifyExpiredDate4() {
        expectedData = expDates[1];
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate4);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 45)
    public void verifyDiscountAmount4() {
        expectedData = values[1];
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountAmount4);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    //TODO: Discount5
    @Test(priority = 46)
    public void verifyNamePromo5() {
        expectedData = nameDiscounts[0];
        originalData = promotionProductListPage.getText(promotionProductListPage.NamePromo5);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 47)
    public void verifyGenerateCode5() {
        expectedData = codes[0];
        originalData = promotionProductListPage.getText(promotionProductListPage.GenerateCode5);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 48)
    public void verifyInfoPromo5() {
        expectedData = types[0];
        originalData = promotionProductListPage.getText(promotionProductListPage.InfoPromo5);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 49)
    public void verifyExpiredDate5() {
        expectedData = expDates[0];
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate5);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 50)
    public void verifyDiscountAmount5() {
        expectedData = values[0];
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountAmount5);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }


    //TODO: Detail discount0
    @Test(priority = 51)
    public void verifyDisplayCloseDiscountDetailModal() {
        promotionProductListPage.scrollInScreen(promotionProductListPage.NamePromo5, promotionProductListPage.NamePromo3);
        promotionProductListPage.clickElement(promotionProductListPage.ViewVoucher0);
        promotionProductListPage.moveScreen();
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.CloseDiscountDetailModal), "Testcase is fail");
    }

    @Test(priority = 52)
    public void verifyDisplayAvatarBox() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.AvatarBox), "Testcase is fail");
    }

    @Test(priority = 53)
    public void verifyDisplayDiscountName() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.DiscountName), "Testcase is fail");
    }

    @Test(priority = 54)
    public void verifyGetTextDiscountName() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountName);
        Assert.assertEquals(originalData, nameDiscounts[5], "Testcase is fail");
    }

    @Test(priority = 55)
    public void verifyCheckDiscountNameApi() {
        expectedData = checkDiscountCodeDetail(nameDiscounts[5], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 56)
    public void verifyDisplayDiscountCode() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.DiscountCode), "Testcase is fail");
    }

    @Test(priority = 57)
    public void verifyGetTextDiscountCode() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountCode);
        Assert.assertEquals(originalData, codes[5], "Testcase is fail");
    }

    @Test(priority = 58)
    public void verifyCheckDiscountCodeApi() {
        expectedData = checkDiscountCodeDetail(nameDiscounts[5], KEY_CODE).toUpperCase();
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 59)
    public void verifyDisplayDiscountType() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.DiscountType), "Testcase is fail");
    }

    @Test(priority = 60)
    public void verifyGetTextDiscountType() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountType);
        Assert.assertEquals(originalData, types[5], "Testcase is fail");
    }

    @Test(priority = 61)
    public void verifyCheckDiscountTypeApi() {
        expectedData = verifyDiscountCodeType1(nameDiscounts[5]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 62)
    public void verifyDisplayExpiredDate() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ExpiredDate), "Testcase is fail");
    }

    @Test(priority = 63)
    public void verifyGetTextExpiredDate() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate);
        Assert.assertEquals(originalData, expDates[5], "Testcase is fail");
    }

    @Test(priority = 64)
    public void verifyCheckExpiredDateApi() {
        expectedData = expiredDateDiscount(nameDiscounts[5]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 65)
    public void verifyDisplayDiscountValueText() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.DiscountValueText), "Testcase is fail");
    }

    @Test(priority = 66)
    public void verifyGetTextDiscountValueText() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountValueText);
        Assert.assertEquals(originalData, values[5], "Testcase is fail");
    }

    @Test(priority = 67)
    public void verifyCheckDiscountValueTextApi() {
        expectedData = discountAmountDetail(nameDiscounts[5]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 68)
    public void verifyDisplayProductCategoryTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ProductCategoryTitle), "Testcase is fail");
    }

    @Test(priority = 69)
    public void verifyGetTextProductCategoryTitle() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryTitle);
        Assert.assertEquals(originalData, PRODUCT_CATEGORY_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 70)
    public void verifyCheckProductCategoryName0Api() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryName0);
        expectedData = checkProductCategoryName(nameDiscounts[5], KEY_PRODUCT_CATEGORY_NAME, PRODUCT_CATEGORY_NAME_0);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

//    @Test(priority = 46)
//    public void verifyCheckProductCategoryName1Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryName1);
//        expectedData = checkProductCategoryName(nameDiscounts[0], KEY_PRODUCT_CATEGORY_NAME, PRODUCT_CATEGORY_NAME_1);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }

    @Test(priority = 71)
    public void verifyDisplayBranchesTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.BranchesTitle), "Testcase is fail");
    }

    @Test(priority = 72)
    public void verifyGetTextBranchesTitle() {
        originalData = promotionProductListPage.getText(promotionProductListPage.BranchesTitle);
        Assert.assertEquals(originalData, BRANCH_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 73)
    public void verifyCheckAllBranchNameApi() {
        originalData = promotionProductListPage.getText(promotionProductListPage.AllBranchesText);
        expectedData = checkAllBranchName(nameDiscounts[5]);
        Assert.assertEquals(originalData, BRANCH_TITLE_VN_1, "Testcase is fail");
    }
//
//    @Test(priority = 49)
//    public void verifyCheckBranchName0Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.BranchName0);
//        expectedData = checkBranchName(nameDiscounts[0], KEY_BRANCH_NAME, BRANCH_NAME_0);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }
//
//    @Test(priority = 49)
//    public void verifyCheckBranchName2Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.BranchName2);
//        expectedData = checkBranchName(NAME_PROMOTIONS_0, KEY_BRANCH_NAME, BRANCH_NAME_2);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }
//
//    @Test(priority = 50)
//    public void verifyCheckBranchName1Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.BranchName1);
//        expectedData = checkBranchName(NAME_PROMOTIONS_0, KEY_BRANCH_NAME, BRANCH_NAME_1);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }

    @Test(priority = 74)
    public void verifyDisplayDiscountMaximumTitle() {
        promotionProductListPage.scrollInScreen(promotionProductListPage.ProductCategoryTitle, promotionProductListPage.TermsAndConditionsTitle);
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.DiscountMaximumTitle));
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountMaximumTitle);
        Assert.assertEquals(originalData, DISCOUNT_MAXIMUM_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 75)
    public void verifyMaximumDiscountAmountApi() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.MaximumDiscountAmount));
        originalData = promotionProductListPage.getText(promotionProductListPage.MaximumDiscountAmount);
        expectedData = getValueDiscountAmount(nameDiscounts[5], KEY_DISCOUNT_AMOUNT);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 76)
    public void verifyDisplayStartDateTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.StartDateTitle));
        originalData = promotionProductListPage.getText(promotionProductListPage.StartDateTitle);
        Assert.assertEquals(originalData, START_DATE_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 77)
    public void verifyDisplayEndDateTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.EndDateTitle));
        originalData = promotionProductListPage.getText(promotionProductListPage.EndDateTitle);
        Assert.assertEquals(originalData, END_DATE_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 78)
    public void verifyStartDateApi() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.StartDate));
        originalData = promotionProductListPage.getText(promotionProductListPage.StartDate);
        expectedData = expiredDateDetail(nameDiscounts[5], KEY_START_DATE);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 79)
    public void verifyEndDateApi() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.EndDate));
        originalData = promotionProductListPage.getText(promotionProductListPage.EndDate);
        expectedData = expiredDateDetail(nameDiscounts[5], KEY_END_DATE);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 80)
    public void verifyDisplayTermsAndConditionsTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.TermsAndConditionsTitle));
        originalData = promotionProductListPage.getText(promotionProductListPage.TermsAndConditionsTitle);
        Assert.assertEquals(originalData, TERMS_CONDITION_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 81)
    public void verifyTermsAndConditionApi() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.TermsAndCondition));
        originalData = promotionProductListPage.getText(promotionProductListPage.TermsAndCondition);
        expectedData = checkDiscountCodeDetail(nameDiscounts[5], KEY_TERMS);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 82)
    public void verifyDisplayCouponConditionsTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.CouponConditionsTitle));
        originalData = promotionProductListPage.getText(promotionProductListPage.CouponConditionsTitle);
        Assert.assertEquals(originalData, COUPON_CONDITIONS_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 83)
    public void verifyIncludedToppingApi() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.IncludedTopping));
        originalData = promotionProductListPage.getText(promotionProductListPage.IncludedTopping);
        expectedData = checkIncludedTopping(nameDiscounts[5], KEY_INCLUDED_TOPPING);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 84)
    public void verifyDisplayLimitNumberOfTimesThisCouponCanBeUsedTitleTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.LimitNumberOfTimesThisCouponCanBeUsedTitle));
        originalData = promotionProductListPage.getText(promotionProductListPage.LimitNumberOfTimesThisCouponCanBeUsedTitle);
        Assert.assertEquals(originalData, LIMITED_TIME_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 85)
    public void verifyMaximumLimitCouponUseApi() {
        promotionProductListPage.scrollInScreen(promotionProductListPage.StartDateTitle, promotionProductListPage.CouponConditionsTitle);
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.MaximumLimitCouponUse));
        originalData = promotionProductListPage.getText(promotionProductListPage.MaximumLimitCouponUse);
        expectedData = checkDiscountCodeDetail(nameDiscounts[5], KEY_COUPONS_USE);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 86)
    public void verifyDisplayLimitOneUsedPerCustomerTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.LimitOneUsedPerCustomer));
        originalData = promotionProductListPage.getText(promotionProductListPage.LimitOneUsedPerCustomer);
        Assert.assertEquals(originalData, COUPON_LIMITED_TIME_TITLE_VN, "Testcase is fail");
    }
    //TODO: Discount detail1

    @Test(priority = 87)
    public void verifyGetTextDiscountName1() {
        promotionProductListPage.navigateBack();
        promotionProductListPage.moveScreen();
        promotionProductListPage.clickElement(promotionProductListPage.ViewVoucher1);
        promotionProductListPage.moveScreen();
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountName);
        Assert.assertEquals(originalData, nameDiscounts[4], "Testcase is fail");
    }

    @Test(priority = 88)
    public void verifyCheckDiscountNameApi1() {
        expectedData = checkDiscountCodeDetail(nameDiscounts[4], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 89)
    public void verifyGetTextDiscountCode1() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountCode);
        Assert.assertEquals(originalData, codes[4], "Testcase is fail");
    }

    @Test(priority = 90)
    public void verifyCheckDiscountCodeApi1() {
        expectedData = checkDiscountCodeDetail(nameDiscounts[4], KEY_CODE).toUpperCase();
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 91)
    public void verifyGetTextDiscountType1() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountType);
        Assert.assertEquals(originalData, types[4], "Testcase is fail");
    }

    @Test(priority = 92)
    public void verifyCheckDiscountTypeApi1() {
        expectedData = verifyDiscountCodeType1(nameDiscounts[4]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 93)
    public void verifyGetTextExpiredDate1() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate);
        Assert.assertEquals(originalData, expDates[4], "Testcase is fail");
    }

    @Test(priority = 94)
    public void verifyCheckExpiredDateApi1() {
        expectedData = expiredDateDiscount(nameDiscounts[4]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 95)
    public void verifyGetTextDiscountValueText1() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountValueText);
        Assert.assertEquals(originalData, values[4], "Testcase is fail");
    }

    @Test(priority = 96)
    public void verifyCheckDiscountValueTextApi1() {
        expectedData = discountAmountDetail(nameDiscounts[4]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 97)
    public void verifyGetTextProductCategoryTitle1() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryTitle);
        Assert.assertEquals(originalData, PRODUCT_CATEGORY_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 98)
    public void verifyCheckProductCategoryName1Api() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryName0);
        expectedData = checkProductCategoryName(nameDiscounts[4], KEY_PRODUCT_CATEGORY_NAME, PRODUCT_CATEGORY_NAME_0);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

//    @Test(priority = 46)
//    public void verifyCheckProductCategoryName1Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryName1);
//        expectedData = checkProductCategoryName(nameDiscounts[0], KEY_PRODUCT_CATEGORY_NAME, PRODUCT_CATEGORY_NAME_1);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }


    @Test(priority = 99)
    public void verifyGetTextBranchesTitle1() {
        originalData = promotionProductListPage.getText(promotionProductListPage.BranchesTitle);
        Assert.assertEquals(originalData, BRANCH_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 100)
    public void verifyCheckAllBranchNameApi1() {
        originalData = promotionProductListPage.getText(promotionProductListPage.AllBranchesText);
        expectedData = checkAllBranchName(nameDiscounts[4]);
        Assert.assertEquals(originalData, BRANCH_TITLE_VN_1, "Testcase is fail");
    }

    //TODO: Discount detail2
    @Test(priority = 101)
    public void verifyGetTextDiscountName2() {
        promotionProductListPage.navigateBack();
        promotionProductListPage.moveScreen();
        promotionProductListPage.clickElement(promotionProductListPage.ViewVoucher2);
        promotionProductListPage.moveScreen();
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountName);
        Assert.assertEquals(originalData, nameDiscounts[3], "Testcase is fail");
    }

    @Test(priority = 102)
    public void verifyCheckDiscountNameApi2() {
        expectedData = checkDiscountCodeDetail(nameDiscounts[3], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 103)
    public void verifyGetTextDiscountCode2() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountCode);
        Assert.assertEquals(originalData, codes[3], "Testcase is fail");
    }

    @Test(priority = 104)
    public void verifyCheckDiscountCodeApi2() {
        expectedData = checkDiscountCodeDetail(nameDiscounts[3], KEY_CODE).toUpperCase();
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 105)
    public void verifyGetTextDiscountType2() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountType);
        Assert.assertEquals(originalData, types[3], "Testcase is fail");
    }

    @Test(priority = 106)
    public void verifyCheckDiscountTypeApi2() {
        expectedData = verifyDiscountCodeType1(nameDiscounts[3]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 107)
    public void verifyGetTextExpiredDate2() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate);
        Assert.assertEquals(originalData, expDateDetails[3], "Testcase is fail");
    }

    @Test(priority = 108)
    public void verifyCheckExpiredDateApi2() {
        expectedData = expiredDateDiscount(nameDiscounts[3]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 109)
    public void verifyGetTextDiscountValueText2() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountValueText);
        Assert.assertEquals(originalData, values[3], "Testcase is fail");
    }

    @Test(priority = 110)
    public void verifyCheckDiscountValueTextApi2() {
        expectedData = discountAmountDetail(nameDiscounts[3]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 111)
    public void verifyGetTextProductCategoryTitle2() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryTitle);
        Assert.assertEquals(originalData, PRODUCT_CATEGORY_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 112)
    public void verifyCheckProductCategoryName2Api() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryName0);
        expectedData = checkProductCategoryName(nameDiscounts[3], KEY_PRODUCT_CATEGORY_NAME, PRODUCT_CATEGORY_NAME_0);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

//    @Test(priority = 46)
//    public void verifyCheckProductCategoryName1Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryName1);
//        expectedData = checkProductCategoryName(nameDiscounts[0], KEY_PRODUCT_CATEGORY_NAME, PRODUCT_CATEGORY_NAME_1);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }


    @Test(priority = 113)
    public void verifyGetTextBranchesTitle2() {
        originalData = promotionProductListPage.getText(promotionProductListPage.BranchesTitle);
        Assert.assertEquals(originalData, BRANCH_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 114)
    public void verifyCheckAllBranchNameApi2() {
        originalData = promotionProductListPage.getText(promotionProductListPage.AllBranchesText);
        expectedData = checkAllBranchName(nameDiscounts[3]);
        Assert.assertEquals(originalData, BRANCH_TITLE_VN_1, "Testcase is fail");
    }

    //TODO: Discount detail3
    @Test(priority = 115)
    public void verifyGetTextDiscountName3() {
        promotionProductListPage.navigateBack();
        promotionProductListPage.moveScreen();
        promotionProductListPage.clickElement(promotionProductListPage.ViewVoucher3);
        promotionProductListPage.moveScreen();
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountName);
        Assert.assertEquals(originalData, nameDiscounts[2], "Testcase is fail");
    }

    @Test(priority = 116)
    public void verifyCheckDiscountNameApi3() {
        expectedData = checkDiscountCodeDetail(nameDiscounts[2], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 117)
    public void verifyGetTextDiscountCode3() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountCode);
        Assert.assertEquals(originalData, codes[2], "Testcase is fail");
    }

    @Test(priority = 118)
    public void verifyCheckDiscountCodeApi3() {
        expectedData = checkDiscountCodeDetail(nameDiscounts[2], KEY_CODE).toUpperCase();
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 119)
    public void verifyGetTextDiscountType3() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountType);
        Assert.assertEquals(originalData, types[2], "Testcase is fail");
    }

    @Test(priority = 120)
    public void verifyCheckDiscountTypeApi3() {
        expectedData = verifyDiscountCodeType1(nameDiscounts[2]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 121)
    public void verifyGetTextExpiredDate3() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate);
        Assert.assertEquals(originalData, expDateDetails[2], "Testcase is fail");
    }

    @Test(priority = 122)
    public void verifyCheckExpiredDateApi3() {
        expectedData = expiredDateDiscount(nameDiscounts[2]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 123)
    public void verifyGetTextDiscountValueText3() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountValueText);
        Assert.assertEquals(originalData, values[2], "Testcase is fail");
    }

    @Test(priority = 124)
    public void verifyCheckDiscountValueTextApi3() {
        expectedData = discountAmountDetail(nameDiscounts[2]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 125)
    public void verifyGetTextProductCategoryTitle3() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryTitle);
        Assert.assertEquals(originalData, PRODUCT_CATEGORY_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 126)
    public void verifyCheckProductCategoryName3Api() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryName0);
        expectedData = checkProductCategoryName(nameDiscounts[2], KEY_PRODUCT_CATEGORY_NAME, PRODUCT_CATEGORY_NAME_0);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

//    @Test(priority = 46)
//    public void verifyCheckProductCategoryName1Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryName1);
//        expectedData = checkProductCategoryName(nameDiscounts[0], KEY_PRODUCT_CATEGORY_NAME, PRODUCT_CATEGORY_NAME_1);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }


    @Test(priority = 127)
    public void verifyGetTextBranchesTitle3() {
        originalData = promotionProductListPage.getText(promotionProductListPage.BranchesTitle);
        Assert.assertEquals(originalData, BRANCH_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 128)
    public void verifyCheckAllBranchNameApi3() {
        originalData = promotionProductListPage.getText(promotionProductListPage.AllBranchesText);
        expectedData = checkAllBranchName(nameDiscounts[2]);
        Assert.assertEquals(originalData, BRANCH_TITLE_VN_1, "Testcase is fail");
    }

    //TODO: Discount detail4
    @Test(priority = 129)
    public void verifyGetTextDiscountName4() {
        promotionProductListPage.navigateBack();
        promotionProductListPage.moveScreen();
        promotionProductListPage.scrollInScreen(promotionProductListPage.NamePromo0, promotionProductListPage.NamePromo3);
        promotionProductListPage.clickElement(promotionProductListPage.ViewVoucher4);
        promotionProductListPage.moveScreen();
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountName);
        Assert.assertEquals(originalData, nameDiscounts[1], "Testcase is fail");
    }

    @Test(priority = 130)
    public void verifyCheckDiscountNameApi4() {
        expectedData = checkDiscountCodeDetail(nameDiscounts[1], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 131)
    public void verifyGetTextDiscountCode4() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountCode);
        Assert.assertEquals(originalData, codes[1], "Testcase is fail");
    }

    @Test(priority = 132)
    public void verifyCheckDiscountCodeApi4() {
        expectedData = checkDiscountCodeDetail(nameDiscounts[1], KEY_CODE).toUpperCase();
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 133)
    public void verifyGetTextDiscountType4() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountType);
        Assert.assertEquals(originalData, types[1], "Testcase is fail");
    }

    @Test(priority = 134)
    public void verifyCheckDiscountTypeApi4() {
        expectedData = verifyDiscountCodeType1(nameDiscounts[1]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 135)
    public void verifyGetTextExpiredDate4() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate);
        Assert.assertEquals(originalData, expDateDetails[1], "Testcase is fail");
    }

    @Test(priority = 136)
    public void verifyCheckExpiredDateApi4() {
        expectedData = expiredDateDiscount(nameDiscounts[1]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 137)
    public void verifyGetTextDiscountValueText4() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountValueText);
        Assert.assertEquals(originalData, values[1], "Testcase is fail");
    }

    @Test(priority = 138)
    public void verifyCheckDiscountValueTextApi4() {
        expectedData = discountAmountDetail(nameDiscounts[1]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 139)
    public void verifyGetTextProductCategoryTitle4() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryTitle);
        Assert.assertEquals(originalData, PRODUCT_CATEGORY_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 139)
    public void verifyCheckProductCategoryName4Api() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryName0);
        expectedData = checkProductCategoryName(nameDiscounts[1], KEY_PRODUCT_CATEGORY_NAME, PRODUCT_CATEGORY_NAME_0);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

//    @Test(priority = 46)
//    public void verifyCheckProductCategoryName1Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryName1);
//        expectedData = checkProductCategoryName(nameDiscounts[0], KEY_PRODUCT_CATEGORY_NAME, PRODUCT_CATEGORY_NAME_1);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }


    @Test(priority = 140)
    public void verifyGetTextBranchesTitle4() {
        originalData = promotionProductListPage.getText(promotionProductListPage.BranchesTitle);
        Assert.assertEquals(originalData, BRANCH_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 141)
    public void verifyCheckAllBranchNameApi4() {
        originalData = promotionProductListPage.getText(promotionProductListPage.AllBranchesText);
        expectedData = checkAllBranchName(nameDiscounts[1]);
        Assert.assertEquals(originalData, BRANCH_TITLE_VN_1, "Testcase is fail");
    }

    //TODO: Discount detail5

    @Test(priority = 142)
    public void verifyGetTextDiscountName5() {
        promotionProductListPage.navigateBack();
        promotionProductListPage.moveScreen();
        promotionProductListPage.clickElement(promotionProductListPage.ViewVoucher5);
        promotionProductListPage.moveScreen();
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountName);
        Assert.assertEquals(originalData, nameDiscounts[0], "Testcase is fail");
    }

    @Test(priority = 143)
    public void verifyCheckDiscountNameApi5() {
        expectedData = checkDiscountCodeDetail(nameDiscounts[0], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 144)
    public void verifyGetTextDiscountCode5() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountCode);
        Assert.assertEquals(originalData, codes[0], "Testcase is fail");
    }

    @Test(priority = 145)
    public void verifyCheckDiscountCodeApi5() {
        expectedData = checkDiscountCodeDetail(nameDiscounts[0], KEY_CODE).toUpperCase();
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 146)
    public void verifyGetTextDiscountType5() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountType);
        Assert.assertEquals(originalData, types[0], "Testcase is fail");
    }

    @Test(priority = 147)
    public void verifyCheckDiscountTypeApi5() {
        expectedData = verifyDiscountCodeType1(nameDiscounts[0]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 148)
    public void verifyGetTextExpiredDate5() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate);
        Assert.assertEquals(originalData, expDateDetails[0], "Testcase is fail");
    }

    @Test(priority = 149)
    public void verifyCheckExpiredDateApi5() {
        expectedData = expiredDateDiscount(nameDiscounts[0]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 150)
    public void verifyGetTextDiscountValueText5() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountValueText);
        Assert.assertEquals(originalData, values[0], "Testcase is fail");
    }

    @Test(priority = 151)
    public void verifyCheckDiscountValueTextApi5() {
        expectedData = discountAmountDetail(nameDiscounts[0]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 152)
    public void verifyGetTextProductCategoryTitle5() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryTitle);
        Assert.assertEquals(originalData, PRODUCT_CATEGORY_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 153)
    public void verifyCheckProductCategoryName5Api() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryName0);
        expectedData = checkProductCategoryName(nameDiscounts[0], KEY_PRODUCT_CATEGORY_NAME, PRODUCT_CATEGORY_NAME_0);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

//    @Test(priority = 46)
//    public void verifyCheckProductCategoryName1Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryName1);
//        expectedData = checkProductCategoryName(nameDiscounts[0], KEY_PRODUCT_CATEGORY_NAME, PRODUCT_CATEGORY_NAME_1);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }


    @Test(priority = 154)
    public void verifyGetTextBranchesTitle5() {
        originalData = promotionProductListPage.getText(promotionProductListPage.BranchesTitle);
        Assert.assertEquals(originalData, BRANCH_TITLE_VN, "Testcase is fail");
    }

    @Test(priority = 155)
    public void verifyCheckAllBranchNameApi5() {
        originalData = promotionProductListPage.getText(promotionProductListPage.AllBranchesText);
        expectedData = checkAllBranchName(nameDiscounts[0]);
        Assert.assertEquals(originalData, BRANCH_TITLE_VN_1, "Testcase is fail");
    }

    //TODO: switch tab voucher
    // voucher
    @Test(priority = 156)
    public void verifyDisplayApplicableVoucherTab() {
        promotionProductListPage.navigateBack();
        promotionProductListPage.clickElement(promotionProductListPage.ApplicableVoucherTab);
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ApplicableVoucherTab));
    }

    @Test(priority = 157)
    public void verifyDisplayViewApplicableVoucher0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ViewVoucher0), "Testcase is fail");
    }

    @Test(priority = 158)
    public void verifyDisplayViewApplicableVoucherItem0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ViewVoucherItem0), "Testcase is fail");
    }

    @Test(priority = 159)
    public void verifyDisplayApplicableVoucherIconPromoPercent0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.IconPromoPercent0), "Testcase is fail");
    }

    @Test(priority = 160)
    public void verifyDisplayApplicableVoucherNamePromo0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.VoucherInfoBox0), "Testcase is fail");
    }

    @Test(priority = 161)
    public void verifyGetTextApplicableVoucherNamePromo() {
        originalData = promotionProductListPage.getText(promotionProductListPage.VoucherInfoBox0);
        Assert.assertEquals(originalData, nameVouchers[5], "Testcase is fail");
    }

    @Test(priority = 162)
    public void verifyCheckApplicableVoucherNamePromo0Api() {
        expectedData = getDataFromVoucherDetail(nameVouchers[5], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }


    @Test(priority = 163)
    public void verifyDisplayApplicableVoucherInfoPromo0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.InfoPromo0), "Testcase is fail");
    }

    @Test(priority = 164)
    public void verifyGetTextApplicableVoucherInfoPromo0() {
        originalData = promotionProductListPage.getText(promotionProductListPage.InfoPromo0);
        Assert.assertEquals(originalData, types[11], "Testcase is fail");
    }

    @Test(priority = 165)
    public void verifyCheckApplicableVoucherInfoPromo0Api() {
        expectedData = verifyVoucherType(nameVouchers[5]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 166)
    public void verifyDisplayApplicableVoucherExpiredDate0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ExpiredDate0), "Testcase is fail");
    }

    @Test(priority = 167)
    public void verifyGetTextApplicableVoucherExpiredDate0() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate0);
        Assert.assertEquals(originalData, expDateDetails[11], "Testcase is fail");
    }

    @Test(priority = 168)
    public void verifyCheckApplicableVoucherExpiredDate0Api() {
        expectedData = expiredDateVoucher(nameVouchers[5]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 169)
    public void verifyDisplayApplicableVoucherDiscountAmount0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.DiscountAmount0), "Testcase is fail");
    }

    @Test(priority = 170)
    public void verifyGetTextApplicableVoucherDiscountAmount0() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountAmount0);
        Assert.assertEquals(originalData, values[11], "Testcase is fail");
    }

    @Test(priority = 171)
    public void verifyCheckApplicableVoucherDiscountAmount0Api() {
        expectedData = voucherAmount(nameVouchers[5]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }
    //TODO: view voucher1

    @Test(priority = 172)
    public void verifyGetTextApplicableVoucherNamePromo1() {
        originalData = promotionProductListPage.getText(promotionProductListPage.VoucherInfoBox1);
        Assert.assertEquals(originalData, nameVouchers[4], "Testcase is fail");
    }

    @Test(priority = 173)
    public void verifyCheckApplicableVoucherNamePromoApi1() {
        expectedData = getDataFromVoucherDetail(nameVouchers[4], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }


    @Test(priority = 174)
    public void verifyGetTextApplicableVoucherInfoPromo1() {
        originalData = promotionProductListPage.getText(promotionProductListPage.InfoPromo1);
        Assert.assertEquals(originalData, types[10], "Testcase is fail");
    }

    @Test(priority = 175)
    public void verifyCheckApplicableVoucherInfoPromoApi1() {
        expectedData = verifyVoucherType(nameVouchers[4]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }


    @Test(priority = 176)
    public void verifyGetTextApplicableVoucherExpiredDate1() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate1);
        Assert.assertEquals(originalData, expDateDetails[10], "Testcase is fail");
    }

    @Test(priority = 177)
    public void verifyCheckApplicableVoucherExpiredDateApi1() {
        expectedData = expiredDateVoucher(nameVouchers[4]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 178)
    public void verifyGetTextApplicableVoucherDiscountAmount1() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountAmount1);
        Assert.assertEquals(originalData, values[10], "Testcase is fail");
    }

    @Test(priority = 179)
    public void verifyCheckApplicableVoucherDiscountAmountApi1() {
        expectedData = voucherAmount(nameVouchers[4]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    //TODO: view voucher2

    @Test(priority = 180)
    public void verifyGetTextApplicableVoucherNamePromo2() {
        originalData = promotionProductListPage.getText(promotionProductListPage.VoucherInfoBox2);
        Assert.assertEquals(originalData, nameVouchers[3], "Testcase is fail");
    }

    @Test(priority = 181)
    public void verifyCheckApplicableVoucherNamePromoApi2() {
        expectedData = getDataFromVoucherDetail(nameVouchers[3], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }


    @Test(priority = 182)
    public void verifyGetTextApplicableVoucherInfoPromo2() {
        originalData = promotionProductListPage.getText(promotionProductListPage.InfoPromo2);
        Assert.assertEquals(originalData, types[9], "Testcase is fail");
    }

    @Test(priority = 183)
    public void verifyCheckApplicableVoucherInfoPromoApi2() {
        expectedData = verifyVoucherType(nameVouchers[3]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 184)
    public void verifyGetTextApplicableVoucherExpiredDate2() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate2);
        Assert.assertEquals(originalData, expDateDetails[9], "Testcase is fail");
    }

    @Test(priority = 185)
    public void verifyCheckApplicableVoucherExpiredDateApi2() {
        expectedData = expiredDateVoucher(nameVouchers[3]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }


    @Test(priority = 186)
    public void verifyGetTextApplicableVoucherDiscountAmount2() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountAmount2);
        Assert.assertEquals(originalData, values[9], "Testcase is fail");
    }

    @Test(priority = 187)
    public void verifyCheckApplicableVoucherDiscountAmountApi2() {
        expectedData = voucherAmount(nameVouchers[3]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    //TODO: view voucher3

    @Test(priority = 188)
    public void verifyGetTextApplicableVoucherNamePromo3() {
        originalData = promotionProductListPage.getText(promotionProductListPage.VoucherInfoBox3);
        Assert.assertEquals(originalData, nameVouchers[2], "Testcase is fail");
    }

    @Test(priority = 189)
    public void verifyCheckApplicableVoucherNamePromoApi3() {
        expectedData = getDataFromVoucherDetail(nameVouchers[2], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 190)
    public void verifyGetTextApplicableVoucherInfoPromo3() {
        originalData = promotionProductListPage.getText(promotionProductListPage.InfoPromo3);
        Assert.assertEquals(originalData, types[8], "Testcase is fail");
    }

    @Test(priority = 191)
    public void verifyCheckApplicableVoucherInfoPromoApi3() {
        expectedData = verifyVoucherType(nameVouchers[2]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 192)
    public void verifyGetTextApplicableVoucherExpiredDate3() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate3);
        Assert.assertEquals(originalData, expDateDetails[8], "Testcase is fail");
    }

    @Test(priority = 193)
    public void verifyCheckApplicableVoucherExpiredDateApi3() {
        expectedData = expiredDateVoucher(nameVouchers[2]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 194)
    public void verifyGetTextApplicableVoucherDiscountAmount3() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountAmount3);
        Assert.assertEquals(originalData, values[8], "Testcase is fail");
    }

    @Test(priority = 195)
    public void verifyCheckApplicableVoucherDiscountAmountApi3() {
        expectedData = voucherAmount(nameVouchers[2]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    //TODO: view voucher4


    @Test(priority = 196)
    public void verifyGetTextApplicableVoucherNamePromo4() {
        promotionProductListPage.scrollInScreen(promotionProductListPage.ViewVoucher0, promotionProductListPage.ViewVoucher3);
        originalData = promotionProductListPage.getText(promotionProductListPage.VoucherInfoBox4);
        Assert.assertEquals(originalData, nameVouchers[1], "Testcase is fail");
    }

    @Test(priority = 197)
    public void verifyCheckApplicableVoucherNamePromoApi4() {
        expectedData = getDataFromVoucherDetail(nameVouchers[1], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }


    @Test(priority = 198)
    public void verifyGetTextApplicableVoucherInfoPromo4() {
        originalData = promotionProductListPage.getText(promotionProductListPage.InfoPromo4);
        Assert.assertEquals(originalData, types[7], "Testcase is fail");
    }

    @Test(priority = 199)
    public void verifyCheckApplicableVoucherInfoPromoApi4() {
        expectedData = verifyVoucherType(nameVouchers[1]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }


    @Test(priority = 200)
    public void verifyGetTextApplicableVoucherExpiredDate4() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate4);
        Assert.assertEquals(originalData, expDateDetails[7], "Testcase is fail");
    }

    @Test(priority = 201)
    public void verifyCheckApplicableVoucherExpiredDateApi4() {
        expectedData = expiredDateVoucher(nameVouchers[1]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 202)
    public void verifyGetTextApplicableVoucherDiscountAmount4() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountAmount4);
        Assert.assertEquals(originalData, values[7], "Testcase is fail");
    }

    @Test(priority = 203)
    public void verifyCheckApplicableVoucherDiscountAmountApi4() {
        expectedData = voucherAmount(nameVouchers[1]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    //TODO: view voucher5

    @Test(priority = 204)
    public void verifyGetTextApplicableVoucherNamePromo5() {
        originalData = promotionProductListPage.getText(promotionProductListPage.VoucherInfoBox5);
        Assert.assertEquals(originalData, nameVouchers[0], "Testcase is fail");
    }

    @Test(priority = 205)
    public void verifyCheckApplicableVoucherNamePromoApi5() {
        expectedData = getDataFromVoucherDetail(nameVouchers[0], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 206)
    public void verifyGetTextApplicableVoucherInfoPromo5() {
        originalData = promotionProductListPage.getText(promotionProductListPage.InfoPromo5);
        Assert.assertEquals(originalData, types[6], "Testcase is fail");
    }

    @Test(priority = 207)
    public void verifyCheckApplicableVoucherInfoPromoApi5() {
        expectedData = verifyVoucherType(nameVouchers[0]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 208)
    public void verifyGetTextApplicableVoucherExpiredDate5() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate5);
        Assert.assertEquals(originalData, expDateDetails[6], "Testcase is fail");
    }

    @Test(priority = 209)
    public void verifyCheckApplicableVoucherExpiredDateApi5() {
        expectedData = expiredDateVoucher(nameVouchers[0]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }


    @Test(priority = 210)
    public void verifyGetTextApplicableVoucherDiscountAmount5() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountAmount5);
        Assert.assertEquals(originalData, values[6], "Testcase is fail");
    }

    @Test(priority = 211)
    public void verifyCheckApplicableVoucherDiscountAmountApi5() {
        expectedData = voucherAmount(nameVouchers[0]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    //Voucher Details
    @Test(priority = 212)
    public void verifyDisplayVoucherViewIconDiscount() {
        promotionProductListPage.scrollInScreen(promotionProductListPage.ViewVoucher5, promotionProductListPage.ViewVoucher2);
        promotionProductListPage.clickElement(promotionProductListPage.ViewVoucher0);
        promotionProductListPage.moveScreen();
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ViewIconDiscount), "Testcase is fail");
    }

    @Test(priority = 213)
    public void verifyDisplayVoucherName() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.VoucherName), "Testcase is fail");
    }

    @Test(priority = 214)
    public void verifyGetTextVoucherName() {
        originalData = promotionProductListPage.getText(promotionProductListPage.VoucherName);
        Assert.assertEquals(originalData, nameVouchers[5], "Testcase is fail");
    }

    @Test(priority = 215)
    public void verifyCheckVoucherNameApi() {
        expectedData = checkVoucherDetail(nameVouchers[5], KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 216)
    public void verifyDisplayVoucherType() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.VoucherType), "Testcase is fail");
    }

    @Test(priority = 217)
    public void verifyGetTextVoucherType() {
        originalData = promotionProductListPage.getText(promotionProductListPage.VoucherType);
        Assert.assertEquals(originalData, types[11], "Testcase is fail");
    }

    @Test(priority = 218)
    public void verifyCheckVoucherTypeApi() {
        expectedData = verifyVoucherType1(nameVouchers[5]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 219)
    public void verifyDisplayExpiredDateVoucher() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ExpiredDate), "Testcase is fail");
    }

    @Test(priority = 220)
    public void verifyGetTextExpiredDateExpiredDate() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate);
        Assert.assertEquals(originalData, expDates[11], "Testcase is fail");
    }

    @Test(priority = 221)
    public void verifyCheckExpiredDateExpiredDateApi() {
        expectedData = expiredDateVoucher(nameVouchers[5]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 222)
    public void verifyDisplayViewDiscountAmount() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ViewDiscountAmount), "Testcase is fail");
    }

    @Test(priority = 223)
    public void verifyGetTextViewDiscountAmount() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ViewDiscountAmount);
        Assert.assertEquals(originalData, values[11], "Testcase is fail");
    }

    @Test(priority = 224)
    public void verifyCheckViewDiscountAmountApi() {
        expectedData = voucherAmountDetail(nameVouchers[5]);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

    @Test(priority = 225)
    public void verifyDisplayProductCategoryDiscountsApply() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ProductCategoryDiscountsApply), "Testcase is fail");
    }

    @Test(priority = 226)
    public void verifyGetTextProductCategoryDiscountsApply() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryDiscountsApply);
        Assert.assertEquals(originalData, VOUCHER_PRODUCT_CATEGORY_TITLE, "Testcase is fail");
    }

    @Test(priority = 227)
    public void verifyCheckProductCategoryDiscountsApply0Api() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryDiscountsApply0);
        expectedData = "• " + checkVoucherProductCategoryName(nameVouchers[5], KEY_NAME, PRODUCT_CATEGORY_NAME_0);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

//    @Test(priority = 95)
//    public void verifyCheckProductCategoryDiscountsApply1Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryDiscountsApply1);
//        expectedData = "• " + checkVoucherProductCategoryName(NAME_VOUCHER_0, KEY_NAME, PRODUCT_CATEGORY_NAME_1);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }
//
//    @Test(priority = 96)
//    public void verifyCheckProductCategoryDiscountsApply2Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.ProductCategoryDiscountsApply2);
//        expectedData = "• " + checkVoucherProductCategoryName(NAME_VOUCHER_0, KEY_NAME, PRODUCT_CATEGORY_NAME_2);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }

    @Test(priority = 228)
    public void verifyDisplaySpecificBranchesCheckIcon() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.SpecificBranchesCheckIcon), "Testcase is fail");
    }

    @Test(priority = 229)
    public void verifyDisplaySpecificBranchesTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.SpecificBranchesTitle), "Testcase is fail");
    }

    @Test(priority = 230)
    public void verifyGetTextSpecificBranchesTitle() {
        originalData = promotionProductListPage.getText(promotionProductListPage.SpecificBranchesTitle);
        Assert.assertEquals(originalData, VOUCHER_BRANCH_TITLE, "Testcase is fail");
    }

    @Test(priority = 231)
    public void verifyCheckBranch0Api() {
        originalData = promotionProductListPage.getText(promotionProductListPage.Branch0);
        expectedData = checkVoucherBranchName(nameVouchers[5], KEY_VOUCHER_BRANCH_NAME, BRANCH_NAME_0);
        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
    }

//    @Test(priority = 101)
//    public void verifyCheckBranch1Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.Branch1);
//        expectedData = checkVoucherBranchName(NAME_VOUCHER_0, KEY_VOUCHER_BRANCH_NAME, BRANCH_NAME_1);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }
//
//    @Test(priority = 102)
//    public void verifyCheckBranch2Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.Branch2);
//        expectedData = checkVoucherBranchName(NAME_VOUCHER_0, KEY_VOUCHER_BRANCH_NAME, VOUCHER_BRANCH_NAME_5);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }
//
//    @Test(priority = 103)
//    public void verifyCheckBranch3Api() {
//        originalData = promotionProductListPage.getText(promotionProductListPage.Branch3);
//        expectedData = checkVoucherBranchName(NAME_VOUCHER_0, KEY_VOUCHER_BRANCH_NAME, VOUCHER_BRANCH_NAME_3);
//        Assert.assertEquals(originalData, expectedData, "Testcase is fail");
//    }

    @Test(priority = 232)
    public void verifyDisplayVoucherTermsAndConditionsTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.TermsAndConditionsTitle));
        originalData = promotionProductListPage.getText(promotionProductListPage.TermsAndConditionsTitle);
        Assert.assertEquals(originalData, TERMS_CONDITION_TITLE_EN, "Testcase is fail");
    }

    @Test(priority = 233)
    public void verifyDisplayVoucherTermsAndConditions0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.TermsAndConditions0));
        originalData = promotionProductListPage.getText(promotionProductListPage.TermsAndConditions0);
        expectedData = getDataFromVoucherDetail(nameVouchers[5], KEY_TERMS);
        Assert.assertEquals(originalData, originalData, "Testcase is fail");
    }

    @Test(priority = 234)
    public void verifyDisplayVoucherAdditionalConditionsTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.AdditionalConditions));
        originalData = promotionProductListPage.getText(promotionProductListPage.AdditionalConditions);
        Assert.assertEquals(originalData, ADDITIONAL_CONDITION_TITLE, "Testcase is fail");
    }

    @Test(priority = 235)
    public void verifyDisplayIncludedToppingCheckIcon() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.IncludedToppingCheckIcon), "Testcase is fail");
    }

    @Test(priority = 236)
    public void verifyDisplayIncludedToppingTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.IncludedTopping));
        originalData = promotionProductListPage.getText(promotionProductListPage.IncludedTopping);
        Assert.assertEquals(originalData, INCLUDED_TOPPING_TITLE, "Testcase is fail");
    }

    public void createDiscountByApi() {
        //Create Discount code by API
        createDiscountCodeTotalBillByApi(0, false);
        nameDiscounts[0] = nameDiscount();
        codes[0] = discountCode();
        types[0] = discountCodeTotalType();
        expDates[0] = endDateTotalDiscount();
        expDateDetails[0] = endDateTotalDiscountDetail();
        values[0] = value();
        createDiscountCodeTotalBillByApi(0, true);
        nameDiscounts[1] = nameDiscount();
        codes[1] = discountCode();
        types[1] = discountCodeTotalType();
        expDates[1] = endDateTotalDiscount();
        expDateDetails[1] = endDateTotalDiscountDetail();
        values[1] = value();
        createDiscountCodeProductByApi(1, false);
        nameDiscounts[2] = nameDiscountProduct();
        codes[2] = discountCodeProduct();
        types[2] = discountCodeProductType();
        expDates[2] = endDateProductDiscount();
        expDateDetails[2] = endDateProductDiscountDetail();
        values[2] = valueProductDiscount();
        createDiscountCodeProductByApi(1, true);
        nameDiscounts[3] = nameDiscountProduct();
        codes[3] = discountCodeProduct();
        types[3] = discountCodeProductType();
        expDates[3] = endDateProductDiscount();
        expDateDetails[3] = endDateProductDiscountDetail();
        values[3] = valueProductDiscount();
        createDiscountCodeCategoryByApi(2, false);
        nameDiscounts[4] = nameDiscountCategory();
        codes[4] = discountCodeCategory();
        types[4] = discountCodeCategoryType();
        expDates[4] = endDateCategoryDiscount();
        expDateDetails[4] = endDateCategoryDiscountDetail();
        values[4] = valueCategoryDiscount();
        createDiscountCodeCategoryByApi(2, true);
        nameDiscounts[5] = nameDiscountCategory();
        codes[5] = discountCodeCategory();
        types[5] = discountCodeCategoryType();
        expDates[5] = endDateCategoryDiscount();
        expDateDetails[5] = endDateCategoryDiscountDetail();
        values[5] = valueCategoryDiscount();
    }

    public void createVoucherByApi() {
        //Create Voucher by API
        createVoucherTotalBillByApi(0, false);
        nameVouchers[0] = nameVoucher();
        types[6] = voucherTotalType();
        expDates[6] = endDateTotalVoucher();
        values[6] = valueTotalVoucher();
        createVoucherTotalBillByApi(0, true);
        nameVouchers[1] = nameVoucher();
        types[7] = voucherTotalType();
        expDates[7] = endDateTotalVoucher();
        values[7] = valueTotalVoucher();
        createVoucherProductByApi(1, false);
        nameVouchers[2] = nameVoucherProduct();
        types[8] = voucherProductType();
        expDates[8] = endDateProductVoucher();
        values[8] = valueProductVoucher();
        createVoucherProductByApi(1, true);
        nameVouchers[3] = nameVoucherProduct();
        types[9] = voucherProductType();
        expDates[9] = endDateProductVoucher();
        values[9] = valueProductVoucher();
        createVoucherCategoryByApi(2, false);
        nameVouchers[4] = nameVoucherCategory();
        types[10] = voucherCategoryType();
        expDates[10] = endDateCategoryVoucher();
        values[10] = valueCategoryVoucher();
        createVoucherCategoryByApi(2, true);
        nameVouchers[5] = nameVoucherCategory();
        types[11] = voucherCategoryType();
        expDates[11] = endDateCategoryVoucher();
        values[11] = valueCategoryVoucher();
    }

    public void endDiscount() {
        stopDiscountCode(nameDiscounts[0]);
        stopDiscountCode(nameDiscounts[1]);
        stopDiscountCode(nameDiscounts[2]);
        stopDiscountCode(nameDiscounts[3]);
        stopDiscountCode(nameDiscounts[4]);
        stopDiscountCode(nameDiscounts[5]);
    }

    public void endVoucher() {
        stopVoucher(nameVouchers[0]);
        stopVoucher(nameVouchers[1]);
        stopVoucher(nameVouchers[2]);
        stopVoucher(nameVouchers[3]);
        stopVoucher(nameVouchers[4]);
        stopVoucher(nameVouchers[5]);
    }
}

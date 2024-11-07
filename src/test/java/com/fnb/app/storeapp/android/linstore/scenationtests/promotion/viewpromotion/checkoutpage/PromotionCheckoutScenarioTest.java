package com.fnb.app.storeapp.android.linstore.scenationtests.promotion.viewpromotion.checkoutpage;

import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.pickup.PickUpManagementPage;
import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion.PromotionProductListPage;
import com.fnb.app.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion.PromotionProductListDataTest.*;
import static com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion.PromotionProductListPage.*;

public class PromotionCheckoutScenarioTest extends BaseTest {
    PromotionProductListPage promotionProductListPage;
    LoginPageLinStore loginPageLinStore;
    PickUpManagementPage pickUpManagementPage;
    SoftAssert softAssert;
    static String expectedData;
    static String originalData;
    static String voucherName;
    static String discountName;

    @BeforeClass
    public void navigateToPage() {
        promotionProductListPage = homePageLinStore.navigateToPromotionProductListPage();
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
        pickUpManagementPage = homePageLinStore.navigateToPickUpManagementPage();
        softAssert = new SoftAssert();
    }

    @Test(priority = 0)
    public void verifyNavigateToCheckoutPage() {
        loginPageLinStore.splashScreen();
        pickUpManagementPage.navigateToCheckoutPage();
        promotionProductListPage.scrollInScreen(promotionProductListPage.DeliveryPickUpText, promotionProductListPage.OrderSummary);
        promotionProductListPage.clickElement(promotionProductListPage.UseOffersToGetDiscountsField);
        promotionProductListPage.moveScreen();
    }

    @Test(priority = 1)
    public void verifyDisplayBackBtnPromotionPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.backBtn), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 2)
    public void verifyDisplayTitlePromotionPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.Title), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.Title), TITLE, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 3)
    public void verifyDisplayStorePromotionTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.StorePromotionTitle), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.StorePromotionTitle), STORE_PROMOTION_TITLE, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 4)
    public void verifyDisplayPromotionApplyTimeIcon0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyTimeIcon0), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 5)
    public void verifyDisplayPromotionApplyingText0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyingText0), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.PromotionApplyingText0), APPLYING_TITLE, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 6)
    public void verifyDisplayPromotionApplyPromotionTagIcon0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyPromotionTagIcon0), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 7)
    public void verifyDisplayPromotionApplyNameText0CheckoutPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyNameText0), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.PromotionApplyNameText0), getValueListPromotionCanApply(KEY_NAME), LOG_TESTCASE_FAIL);
        voucherName = getValueListPromotionCanApply(KEY_NAME);
    }

    @Test(priority = 8)
    public void verifyDisplayPromotionApplyType0CheckoutPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyType0), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.PromotionApplyType0), verifyVoucherType2(KEY_DISCOUNT_TYPE), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 9)
    public void verifyDisplayPromotionApplyAmount0CheckoutPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyAmount0), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.PromotionApplyAmount0), getValueVoucherAmountCheckoutPage(), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 10)
    public void verifyDisplayPromotionApplyUnit0CheckoutPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyUnit0), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.PromotionApplyUnit0), checkIsPercentCheckoutPage(voucherName, KEY_IS_PERCENT_DISCOUNT), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 11)
    public void verifyDisplayPromotionApplyTimeIcon0CheckoutPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyTimeIcon0), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 12)
    public void verifyDisplayPromotionApplyExpTime0CheckoutPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyExpTime0), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.PromotionApplyExpTime0), formatDateString0(getValueDiscountCheckoutPage(voucherName, KEY_END_DATE)), LOG_TESTCASE_FAIL);
    }


    @Test(priority = 24)
    public void verifyDisplayMyVoucherList() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.MyVoucherList), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.MyVoucherList), MY_VOUCHER_TITLE, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 14)
    public void verifyDisplayVoucherApplypromotionTagIcon0CheckoutPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.VoucherApplypromotionTagIcon0), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 15)
    public void verifyDisplayVoucherApplyName0CheckoutPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.VoucherApplyName0), LOG_TESTCASE_FAIL);
        discountName = promotionProductListPage.getText(promotionProductListPage.VoucherApplyName0);
        Assert.assertEquals(discountName, getValueDiscountCheckoutPage(discountName, KEY_NAME), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 16)
    public void verifyDisplayVoucherApplyCode0CheckoutPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.VoucherApplyCode0), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.VoucherApplyCode0), getValueDiscountCheckoutPage(discountName, KEY_CODE), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 17)
    public void verifyDisplayVoucherApplyType0CheckoutPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.VoucherApplyType0), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.VoucherApplyType0), verifyVoucherType3(discountName, KEY_DISCOUNT_TYPE), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 20)
    public void verifyDisplayVoucherApplyAmount0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.VoucherApplyAmout0), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.VoucherApplyAmout0), getValueDiscountAmountCanApply(KEY_DISCOUNT_AMOUNT), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 21)
    public void verifyDisplayVoucherApplyUnit0CheckoutPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.VoucherApplyUnit0), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.VoucherApplyUnit0), checkIsPercent(KEY_PERCENT_NUMBER), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 22)
    public void verifyDisplayVoucherApplyTimeIcon0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.VoucherApplyTimeIcon0), LOG_TESTCASE_FAIL);
    }

    //TODO

    @Test(priority = 23)
    public void verifyDisplayVoucherApplyExpTime0CheckoutPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyExpTime0), LOG_TESTCASE_FAIL);
        String s = getValueDiscountCheckoutPage(discountName, KEY_END_DATE);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.PromotionApplyExpTime0), formatDateString0(s), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 23)
    public void verifyDisplayVoucherApplyBtn0CheckoutPage() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.VoucherApplyBtn0), LOG_TESTCASE_FAIL);
        promotionProductListPage.clickElement(promotionProductListPage.PromotionApplyNameText0);

    }


    //TODO
    @Test(priority = 25)
    public void verifyDisplayVoucherName() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.VoucherName), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 26)
    public void verifyGetTextVoucherName() {
        originalData = promotionProductListPage.getText(promotionProductListPage.VoucherName);
        Assert.assertEquals(originalData, NAME_VOUCHER_CHECKOUT_0, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 27)
    public void verifyCheckVoucherNameApi() {
        originalData = promotionProductListPage.getText(promotionProductListPage.VoucherName);
        voucherName = originalData;
        expectedData = checkVoucherDetail(voucherName, KEY_NAME);
        Assert.assertEquals(originalData, expectedData, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 28)
    public void verifyDisplayVoucherType() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.VoucherType), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 29)
    public void verifyCheckVoucherTypeApi() {
        originalData = promotionProductListPage.getText(promotionProductListPage.VoucherType);
        expectedData = verifyVoucherType1(voucherName);
        Assert.assertEquals(originalData, expectedData, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 30)
    public void verifyDisplayExpiredDateVoucher() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ExpiredDate), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 31)
    public void verifyCheckExpiredDateExpiredDateApi() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate);
        expectedData = expiredDateVoucher(voucherName);
        Assert.assertEquals(originalData, expectedData, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 32)
    public void verifyDisplayViewDiscountAmount() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ViewDiscountAmount), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 33)
    public void verifyGetTextViewDiscountAmount() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ViewDiscountAmount);
        Assert.assertEquals(originalData, DISCOUNT_AMOUNT_VOUCHER_0, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 34)
    public void verifyDisplayVoucherTermsAndConditionsTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.TermsAndConditionsTitle));
        originalData = promotionProductListPage.getText(promotionProductListPage.TermsAndConditionsTitle);
        Assert.assertEquals(originalData, TERMS_CONDITION_TITLE_EN, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 35)
    public void verifyDisplayVoucherTermsAndConditions0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.TermsAndConditions0));
        originalData = promotionProductListPage.getText(promotionProductListPage.TermsAndConditions0);
        expectedData = "• " + getDataFromVoucherDetail(voucherName, KEY_TERMS);
        Assert.assertEquals(originalData, originalData, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 36)
    public void verifyDisplayVoucherAdditionalConditionsTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.AdditionalConditions));
        originalData = promotionProductListPage.getText(promotionProductListPage.AdditionalConditions);
        Assert.assertEquals(originalData, ADDITIONAL_CONDITION_TITLE, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 37)
    public void verifyDisplayPurchaseCheckIcon() {
        promotionProductListPage.scrollInScreen(promotionProductListPage.BranchesTitle, promotionProductListPage.StartDateTitle);
    }

    @Test(priority = 38)
    public void verifyDisplayIncludedToppingCheckIcon() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.IncludedToppingCheckIcon), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 39)
    public void verifyDisplayIncludedTopping() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.IncludedTopping), LOG_TESTCASE_FAIL);
        Assert.assertEquals(promotionProductListPage.getText(promotionProductListPage.IncludedTopping), INCLUDED_TOPPING_TITLE, LOG_TESTCASE_FAIL);
        promotionProductListPage.navigateBack();
        promotionProductListPage.clickElement(promotionProductListPage.VoucherApplyName0);
    }

    @Test(priority = 40)
    public void verifyDisplayCloseDiscountDetailModal() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.CloseDiscountDetailModal), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 41)
    public void verifyDisplayAvatarBox() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.AvatarBox), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 42)
    public void verifyDisplayDiscountName() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.DiscountName), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 43)
    public void verifyCheckDiscountNameApi() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountName);
        discountName = originalData;
        expectedData = checkDiscountCodeDetail(discountName, KEY_NAME);
        Assert.assertEquals(originalData, expectedData, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 44)
    public void verifyDisplayDiscountCode() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.DiscountCode), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 45)
    public void verifyGetTextDiscountCode() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountCode);
        Assert.assertEquals(originalData, GENERATE_CODE_BILLS, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 46)
    public void verifyCheckDiscountCodeApi() {
        expectedData = checkDiscountCodeDetail(discountName, KEY_CODE).toUpperCase();
        Assert.assertEquals(originalData, expectedData, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 47)
    public void verifyDisplayDiscountType() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.DiscountType), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 48)
    public void verifyGetTextDiscountType() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountType);
        Assert.assertEquals(originalData, INFO_PROMOTIONS_BILLS, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 49)
    public void verifyCheckDiscountTypeApi() {
        expectedData = verifyDiscountCodeType1(discountName);
        Assert.assertEquals(originalData, expectedData, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 50)
    public void verifyDisplayExpiredDate() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.ExpiredDate), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 51)
    public void verifyGetTextExpiredDate() {
        originalData = promotionProductListPage.getText(promotionProductListPage.ExpiredDate);
        Assert.assertEquals(originalData, EXPIRED_DATE_DETAIL_0, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 52)
    public void verifyCheckExpiredDateApi() {
        expectedData = expiredDateDiscount(discountName);
        Assert.assertEquals(originalData, expectedData, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 53)
    public void verifyDisplayDiscountValueText() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.DiscountValueText), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 54)
    public void verifyGetTextDiscountValueText() {
        originalData = promotionProductListPage.getText(promotionProductListPage.DiscountValueText);
        Assert.assertEquals(originalData, DISCOUNT_AMOUNT_BILLS, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 55)
    public void verifyDisplayBranchesTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.BranchesTitle), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 56)
    public void verifyGetTextBranchesTitle() {
        originalData = promotionProductListPage.getText(promotionProductListPage.BranchesTitle);
        Assert.assertEquals(originalData, BRANCH_TITLE, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 57)
    public void verifyDisplayStartDateTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.StartDateTitle));
        originalData = promotionProductListPage.getText(promotionProductListPage.StartDateTitle);
        Assert.assertEquals(originalData, START_DATE_TITLE, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 58)
    public void verifyDisplayEndDateTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.EndDateTitle));
        originalData = promotionProductListPage.getText(promotionProductListPage.EndDateTitle);
        Assert.assertEquals(originalData, END_DATE_TITLE, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 59)
    public void verifyStartDateApi() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.StartDate));
        originalData = promotionProductListPage.getText(promotionProductListPage.StartDate);
        expectedData = expiredDateDetail(discountName, KEY_START_DATE);
        Assert.assertEquals(originalData, expectedData, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 60)
    public void verifyEndDateApi() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.EndDate));
        originalData = promotionProductListPage.getText(promotionProductListPage.EndDate);
        expectedData = expiredDateDetail(discountName, KEY_END_DATE);
        Assert.assertEquals(originalData, expectedData, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 61)
    public void verifyDisplayTermsAndConditionsTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.TermsAndConditionsTitle));
        originalData = promotionProductListPage.getText(promotionProductListPage.TermsAndConditionsTitle);
        Assert.assertEquals(originalData, TERMS_CONDITION_TITLE_EN, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 62)
    public void verifyTermsAndConditionApi() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.TermsAndCondition));
        originalData = promotionProductListPage.getText(promotionProductListPage.TermsAndCondition);
        expectedData = checkDiscountCodeDetail(discountName, KEY_TERMS);
        Assert.assertEquals(originalData, expectedData, LOG_TESTCASE_FAIL);
        promotionProductListPage.scrollInScreen(promotionProductListPage.BranchesTitle, promotionProductListPage.StartDateTitle);
    }

    @Test(priority = 63)
    public void verifyDisplayCouponConditionsTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.CouponConditionsTitle));
        originalData = promotionProductListPage.getText(promotionProductListPage.CouponConditionsTitle);
        Assert.assertEquals(originalData, COUPON_CONDITIONS_TITLE_EN, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 64)
    public void verifyDisplayLimitNumberOfTimesThisCouponCanBeUsedTitleTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.LimitNumberOfTimesThisCouponCanBeUsedTitle));
        originalData = promotionProductListPage.getText(promotionProductListPage.LimitNumberOfTimesThisCouponCanBeUsedTitle);
        Assert.assertEquals(originalData, LIMITED_TIME_TITLE, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 65)
    public void verifyDisplayLimitOneUsedPerCustomerTitle() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.LimitOneUsedPerCustomer));
        originalData = promotionProductListPage.getText(promotionProductListPage.LimitOneUsedPerCustomer);
        Assert.assertEquals(originalData, COUPON_LIMITED_TIME_TITLE, LOG_TESTCASE_FAIL);
        promotionProductListPage.navigateBack();
    }
}

package com.fnb.app.storeapp.android.linstore.scenationtests.promotion.applyproductpromotion.onlyproduct;

import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addnewaddress.AddNewAddressPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListPage;
import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.storeapp.android.linstore.pages.promotion.applyproductpromotion.ApplyProductPromotionPage;
import com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion.PromotionProductListPage;
import com.fnb.app.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.fnb.app.storeapp.android.linstore.pages.promotion.applyproductpromotion.ApplyProductPromotionDataTest.*;
import static com.fnb.app.storeapp.android.linstore.pages.promotion.applyproductpromotion.ApplyProductPromotionPage.*;
import static com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion.PromotionProductListDataTest.*;
import static com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion.PromotionProductListPage.*;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_CreateDiscountCodeProduct_Request.*;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_CreateDiscountCodeTotal_Request.*;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_StopDiscountCode_Request.stopDiscountCode;

public class ApplyProductAndSpecificProductScenarioTest extends BaseTest {
    ApplyProductPromotionPage applyProductPromotionPage;
    PromotionProductListPage promotionProductListPage;
    AddressManagementListPage addressManagementListPage;
    LoginPageLinStore loginPageLinStore;
    AddNewAddressPage addNewAddressPage;
    SoftAssert softAssert;
    static String voucherName;
    static String discountName;
    static String name1;
    static String name2;
    static String code1;
    static String code2;
    static String totalCamPainByPercent;
    static String totalCodeByPercent;
    static String totalCamPainByMoney;
    static String totalCodeByMoney;
    static String totalDiscount;
    static String feeAndTax;

    @BeforeClass
    public void navigateToPage() {
        applyProductPromotionPage = homePageLinStore.navigateToApplyProductPromotionPage();
        promotionProductListPage = homePageLinStore.navigateToPromotionProductListPage();
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
        addressManagementListPage = homePageLinStore.navigateToAddressManagementListPage();
        addNewAddressPage = homePageLinStore.navigateToAddNewPage();
        softAssert = new SoftAssert();
    }

    @Test(priority = 0)
    public void verifyPromotionApplyIcon0() {
        createDiscountCodeTotalBillByApi(0, true);
        createDiscountCodeProductByApi(1, true);
        discountName = nameDiscountProduct();
        name1 = nameDiscountProduct();
        System.out.println(name1);
        code1 = discountCodeProduct();
        name2 = nameDiscount();
        System.out.println(name2);
        code2 = discountCode();
//        TODO: Run this code for regression test
//        applyProductPromotionPage.productList();
//        applyProductPromotionPage.addOnlyProductToCart();
//        applyProductPromotionPage.clickElement(applyProductPromotionPage.CartIcon);
//        applyProductPromotionPage.clickElement(applyProductPromotionPage.OrderBtn);
//        applyProductPromotionPage.navigateToPage();
//        applyProductPromotionPage.scrollInScreen(applyProductPromotionPage.DeliveryPickUpText, applyProductPromotionPage.OrderSummary);
//        applyProductPromotionPage.clickElement(applyProductPromotionPage.UseOffersToGetDiscountsText);
        //TODO: Run this code for smoke test
        loginPageLinStore.splashScreen();
        loginPageLinStore.navigateToLoginSuccess();
        addressManagementListPage.clickDeliveryHeader();
        addressManagementListPage.clickDeliveryField();
        addNewAddressPage.ClickNewHomeAddress();
        applyProductPromotionPage.scrollInScreen(applyProductPromotionPage.ProductItem02, applyProductPromotionPage.ProductItem01);
        applyProductPromotionPage.clickElement(applyProductPromotionPage.branchAddressProductList);
        applyProductPromotionPage.clickElement(applyProductPromotionPage.iconDropdownBranch);
        applyProductPromotionPage.clickElement(applyProductPromotionPage.SelectBranch);
        applyProductPromotionPage.clickElement(applyProductPromotionPage.SwitchBranch);
        applyProductPromotionPage.addOnlyProductToCart();
        applyProductPromotionPage.clickElement(applyProductPromotionPage.CartIcon);
        applyProductPromotionPage.clickElement(applyProductPromotionPage.OrderBtn);
        applyProductPromotionPage.scrollInScreen(applyProductPromotionPage.DeliveryPickUpText, applyProductPromotionPage.OrderSummary);
        applyProductPromotionPage.clickElement(applyProductPromotionPage.UseOffersToGetDiscountsText);

        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyIcon0), "Testcase 0 is fail");
    }

    @Test(priority = 1)
    public void verifyPromotionApplyingText0() {
        applyProductPromotionPage.scrollInScreen(promotionProductListPage.MyVoucherList, promotionProductListPage.StorePromotionTitle);
        softAssert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyingText0), "Testcase 1 is fail");
        softAssert.assertEquals(promotionProductListPage.getText(promotionProductListPage.PromotionApplyingText0), APPLYING_TITLE, LOG_TESTCASE_FAIL);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifyPromotionApplyPromotionTagIcon0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyPromotionTagIcon0), "Testcase 2 is fail");
    }

    @Test(priority = 3)
    public void verifyPromotionApplyNameText0() {
        voucherName = promotionProductListPage.getText(promotionProductListPage.PromotionApplyNameText0);
        softAssert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyNameText0), LOG_TESTCASE_FAIL);
        softAssert.assertEquals(promotionProductListPage.getText(promotionProductListPage.PromotionApplyNameText0), getValueListPromotionCanApply(KEY_NAME), LOG_TESTCASE_FAIL);
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void verifyPromotionApplyAmount0() {
        softAssert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyAmount0), LOG_TESTCASE_FAIL);
        softAssert.assertEquals(promotionProductListPage.getText(promotionProductListPage.PromotionApplyAmount0), getValueVoucherAmountCheckoutPage(), LOG_TESTCASE_FAIL);
        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void verifyPromotionApplyTimeIcon0() {
        Assert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyTimeIcon0), LOG_TESTCASE_FAIL);
    }

    @Test(priority = 7)
    public void verifyPromotionApplyExpTime0() {
        softAssert.assertTrue(promotionProductListPage.checkDisplay(promotionProductListPage.PromotionApplyExpTime0), LOG_TESTCASE_FAIL);
        softAssert.assertEquals(promotionProductListPage.getText(promotionProductListPage.PromotionApplyExpTime0), formatDateString0(getValueListPromotionCanApply(KEY_END_DATE)), LOG_TESTCASE_FAIL);
        softAssert.assertAll();
    }

    @Test(priority = 8)
    public void verifyApplyBtn() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.VoucherApplyBtn0), "Testcase is fail");
    }

    @Test(priority = 9)
    public void verifyToastSuccessMessage() {
        applyProductPromotionPage.clickElement(applyProductPromotionPage.VoucherApplyBtn0);
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.toastAnimatedContainer), "Testcase is fail");
    }

    @Test(priority = 10)
    public void verifyVoucherApplyIcon0() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.VoucherApplyIcon0), "Testcase is fail");
    }

    @Test(priority = 11)
    public void verifyVoucherApplyText0() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.VoucherApplyText0), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.VoucherApplyText0), APPLYING_TITLE, LOG_TESTCASE_FAIL);
    }

    @Test(priority = 12)
    public void verifyVoucherApplyBtn0() {
        discountName = applyProductPromotionPage.getText(applyProductPromotionPage.VoucherApplyName0);
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.VoucherApplyBtn0), "Testcase is fail");
        applyProductPromotionPage.clickElement(applyProductPromotionPage.VoucherApplyBtn0);
        applyProductPromotionPage.clickElement(applyProductPromotionPage.VoucherApplyBtn0);

    }

    @Test(priority = 12)
    public void verifyVoucherApplyBtnText2() {
        applyProductPromotionPage.scrollInScreen(promotionProductListPage.StorePromotionTitle, promotionProductListPage.MyVoucherList);
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.VoucherApplyBtn1), "Testcase is fail");
        applyProductPromotionPage.clickElement(applyProductPromotionPage.VoucherApplyBtn1);
        applyProductPromotionPage.navigateToPage();
//        applyProductPromotionPage.scrollInScreen(applyProductPromotionPage.VoucherApplyName1,applyProductPromotionPage.VoucherApplyName2);
//        applyProductPromotionPage.clickElement(applyProductPromotionPage.VoucherApplyBtnText6);
        applyProductPromotionPage.clickElement(applyProductPromotionPage.backBtn);
    }


    @Test(priority = 13)
    public void verifyCheckoutTotalPriceIcon() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.CheckoutTotalPriceIcon), "Testcase is fail");
    }

    @Test(priority = 14)
    public void verifyPaymentSummaryText() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PaymentSummaryText), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PaymentSummaryText), PAYMENT_SUMARY_TEXT, "Testcase is fail");
    }

    @Test(priority = 14)
    public void verifySubTotalOrder() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.SubTotalOrder), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.SubTotalOrder), SUB_TOTAL_ORDER, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 15)
    public void verifyOriginalPrice() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.OriginalPrice), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.OriginalPrice), applyProductPromotionPage.getOriginalPriceOnlyProduct(), "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 16)
    public void verifyDiscountCodeText() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.DiscountCodeText), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.DiscountCodeText), DISCOUNT_CODE_TEXT, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 16)
    public void verifyToolTipIcon() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.ToolTipIcon), "Testcase is fail");
        applyProductPromotionPage.clickElement(applyProductPromotionPage.ToolTipIcon);
    }

    @Test(priority = 17)
    public void verifyDiscountTitle() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.DiscountTitle), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.DiscountTitle), DISCOUNT_CODE_TITLE, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 18)
    public void verifyPromotionTitle0() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionTitle0), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionTitle0), PROMOTION_TITLE_0, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 19)
    public void verifyPromotionTitle1() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionTitle1), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionTitle1), PROMOTION_TITLE_1, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 20)
    public void verifyPromotionText00() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionText00), "Testcase is fail");
//        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionText00), discountName, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 20)
    public void verifyPromotionText01() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionText01), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionText01), discountName, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 21)
    public void verifyPromotionValue00() {
//        totalCodeByPercent = priceApplyTotalDiscountCodePercent();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionValue00), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue00), "-" + priceApplyProductDiscountCodeMoney() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 21)
    public void verifyPromotionValue01() {
//        totalCodeByPercent = priceApplyTotalDiscountCodePercent();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionValue01), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue01), "-" + priceApplyTotalPercentAndProductMoneyDiscountCodePercent() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 22)
    public void verifyPromotionText10() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionText10), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionText10), voucherName, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 23)
    public void verifyPromotionValue10() {
        totalCamPainByPercent = priceApplyTotalDiscountCampaignPercent();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionValue10), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue10), "-" + priceApplyTotalPercentAndProductMoneyDiscountCampaignPercent() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 24)
    public void verifyMemberShipValue() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.memberShipValue10), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.memberShipValue10), "-" + discountMembershipTotalPercentAndProductMoney() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 25)
    public void verifyDiscountValue() {
        applyProductPromotionPage.navigateBack();
        totalDiscount = totalDiscount();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.DiscountValue), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.DiscountValue), "-" + totalDiscountTotalPercentAndProductMoney() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 26)
    public void verifyDiscountCodeTagIcon0() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.DiscountCodeTagIcon0), "Testcase is fail");
    }

    @Test(priority = 27)
    public void verifyDiscountCode0() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.DiscountCode0), "Testcase is fail");
//        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.DiscountCode0), getValueProductMoney(DISCOUNT_KEY_CODE), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.DiscountCode0), code1, "Testcase is fail");
//        Assert.assertAll();
    }

    @Test(priority = 28)
    public void verifyRemoveDiscountIcon0() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.RemoveDiscountIcon0), "Testcase is fail");
    }

    @Test(priority = 29)
    public void verifyFeeAndTaxText() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.FeeAndTaxText), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.FeeAndTaxText), FEE_AND_TAX_TEXT, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 30)
    public void verifyFeeAndTaxValue() {
        feeAndTax = applyProductPromotionPage.getText(applyProductPromotionPage.FeeAndTaxValue);
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.FeeAndTaxValue), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.FeeAndTaxValue), FEE_AND_TAX_VALUE, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 31)
    public void verifyTotalText() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.TotalText), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.TotalText), TOTAL_TITLE, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 32)
    public void verifyTotalValue() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.TotalValue), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.TotalValue), totalPriceTotalPercentAndProductMoney() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 33)
    public void verifyCheckoutButton() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.CheckoutButton), "Testcase is fail");
        applyProductPromotionPage.clickElement(applyProductPromotionPage.CheckoutButton);
    }
    //order  detail

    @Test(priority = 34)
    public void verifyOrderItemTitle() {
        applyProductPromotionPage.scrollInScreen(applyProductPromotionPage.backBtn, applyProductPromotionPage.OrderItemTitle);
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.OrderItemTitle), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.OrderItemTitle), ORDER_ITEM_TITLE, "Testcase is fail");

    }

    @Test(priority = 35)
    public void verifyPaymentDetailTitle() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PaymentDetailTitle), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PaymentDetailTitle), PAYMENT_DETAIL_TITLE, "Testcase is fail");
    }

    @Test(priority = 36)
    public void verifySubTotalOrderText() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.SubTotalOrderText), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.SubTotalOrderText), SUBTOTAL_ORDER_TEXT, "Testcase is fail");
    }

    @Test(priority = 37)
    public void verifySubTotalOrderValue() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.SubTotalOrderValue), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.SubTotalOrderValue), applyProductPromotionPage.getOriginalPriceOnlyProduct(), "Testcase is fail");
        applyProductPromotionPage.scrollInScreen(applyProductPromotionPage.OrderItemTitle, applyProductPromotionPage.SubTotalOrderValue);
    }

    @Test(priority = 38)
    public void verifyDiscountValueOfOrderText() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.DiscountValueOfOrderText), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.DiscountValueOfOrderText), DISCOUNT_VALUE_OF_ORDER_TEXT, "Testcase is fail");
    }

    @Test(priority = 39)
    public void verifyShowToolTipIconDiscount() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.ShowToolTipIconDiscount), "Testcase is fail");
        applyProductPromotionPage.clickElement(applyProductPromotionPage.ShowToolTipIconDiscount);
    }

    //

    @Test(priority = 40)
    public void verifyDiscountTitleOrderDetail() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.DiscountTitle), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.DiscountTitle), DISCOUNT_CODE_TITLE, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 41)
    public void verifyPromotionTitle0OrderDetail() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionTitle0), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionTitle0), PROMOTION_TITLE_0, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 42)
    public void verifyPromotionTitle1OrderDetail() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionTitle1), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionTitle1), PROMOTION_TITLE_1, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 43)
    public void verifyPromotionText00OrderDetail() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionText00), "Testcase is fail");
//        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionText00), discountName, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 44)
    public void verifyPromotionValue00OrderDetail() {
        totalCodeByPercent = priceApplyTotalDiscountCodePercent();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionValue00), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue00), "-" + priceApplyProductDiscountCodeMoney() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 21)
    public void verifyPromotionValue01OrderDetail() {
//        totalCodeByPercent = priceApplyTotalDiscountCodePercent();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionValue01), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue01), "-" + priceApplyTotalPercentAndProductMoneyDiscountCodePercent() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 45)
    public void verifyPromotionText10OrderDetail() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionText10), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionText10), voucherName, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 46)
    public void verifyPromotionValue10OrderDetail() {
        totalCamPainByPercent = priceApplyTotalDiscountCampaignPercent();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionValue10), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue10), "-" + priceApplyTotalPercentAndProductMoneyDiscountCampaignPercent() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 47)
    public void verifyMemberShipValueOrderDetail() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.memberShipValue10), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.memberShipValue10), "-" + discountMembershipTotalPercentAndProductMoney() + "đ", "Testcase is fail");
        applyProductPromotionPage.navigateBack();
//        softAssert.assertAll();
    }

    @Test(priority = 48)
    public void verifyTotalDiscountAmount() {
        totalDiscount = totalDiscount();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.TotalDiscountAmount), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.TotalDiscountAmount), "-" + totalDiscountTotalPercentAndProductMoney() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 49)
    public void verifyFeeAndTaxTextOrderDetail() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.FeeAndTaxText), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.FeeAndTaxText), FEE_AND_TAX_TEXT_ORDER_DETAIL, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 50)
    public void verifyFeeAndTaxValueOrderDetail() {
        feeAndTax = applyProductPromotionPage.getText(applyProductPromotionPage.FeeAndTaxValue);
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.FeeAndTaxValue), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.FeeAndTaxValue), FEE_AND_TAX_VALUE, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 51)
    public void verifyShippingFeeText() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.ShippingFeeText), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.ShippingFeeText), SHIPPING_FEE_TEXT, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 52)
    public void verifyShippingFeeValue() {
//        feeAndTax = applyProductPromotionPage.getText(applyProductPromotionPage.ShippingFeeValue);
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.ShippingFeeValue), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.ShippingFeeValue), SHIPPING_FEE_VALUE, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 53)
    public void verifyTotalPriceOrderText() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.TotalPriceOrderText), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.TotalPriceOrderText), TOTAL_TITLE_ORDER_DETAIL, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 54)
    public void verifyTotalPriceOrderValue() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.TotalPriceOrderValue), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.TotalPriceOrderValue), totalPriceTotalPercentAndProductMoney() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 55)
    public void verifyButtonCancelOrder() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.ButtonCancelOrder), "Testcase is fail");
        applyProductPromotionPage.clickElement(applyProductPromotionPage.ButtonCancelOrder);
        applyProductPromotionPage.clickElement(applyProductPromotionPage.acceptBtn);
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.OrderIsCanceledSuccessfully), "Testcase is fail");
        applyProductPromotionPage.navigateToPage();
        applyProductPromotionPage.clickElement(applyProductPromotionPage.backBtn);
        applyProductPromotionPage.navigateToPage();
        applyProductPromotionPage.clickElement(applyProductPromotionPage.backBtn);
        stopDiscountCode(name1);
        stopDiscountCode(name2);
//        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.TotalPriceOrderValue), totalPrice() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

}


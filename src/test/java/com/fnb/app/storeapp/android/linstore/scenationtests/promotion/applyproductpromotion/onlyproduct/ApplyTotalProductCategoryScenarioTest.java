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
import static com.fnb.app.storeapp.android.linstore.pages.promotion.applyproductpromotion.ApplyProductPromotionDataTest.TOTAL_TITLE_ORDER_DETAIL;
import static com.fnb.app.storeapp.android.linstore.pages.promotion.applyproductpromotion.ApplyProductPromotionPage.*;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_CreateDiscountCodeCategory_Request.*;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_CreateDiscountCodeProduct_Request.*;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_CreateDiscountCodeTotal_Request.*;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_StopDiscountCode_Request.stopDiscountCode;

public class ApplyTotalProductCategoryScenarioTest extends BaseTest {
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
    static String name3;
    static String code1;
    static String code2;
    static String code3;
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
//        applyProductPromotionPage.productList();
//        applyProductPromotionPage.addOnlyProductToCart();
//        applyProductPromotionPage.clickElement(applyProductPromotionPage.CartIcon);
//        applyProductPromotionPage.clickElement(applyProductPromotionPage.OrderBtn);
//        applyProductPromotionPage.navigateToPage();
//        applyProductPromotionPage.scrollInScreen(applyProductPromotionPage.DeliveryPickUpText, applyProductPromotionPage.OrderSummary);
//        applyProductPromotionPage.clickElement(applyProductPromotionPage.UseOffersToGetDiscountsText);
        createDiscountCodeTotalBillByApi(0, true);
        createDiscountCodeProductByApi(1, true);
        createDiscountCodeCategoryByApi(2, true);
        discountName = nameDiscountProduct();
        System.out.println(discountName);
        name1 = nameDiscount();
        System.out.println(name1);
        code1 = discountCode();
        name2 = nameDiscountProduct();
        System.out.println(name2);
        code2 = discountCodeProduct();
        name3 = nameDiscountCategory();
        System.out.println(name3);
        code3 = discountCodeCategory();
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

    @Test(priority = 12)
    public void verifyVoucherApplyBtn0() {
        applyProductPromotionPage.scrollInScreen(promotionProductListPage.MyVoucherList, promotionProductListPage.StorePromotionTitle);
        voucherName = promotionProductListPage.getText(promotionProductListPage.PromotionApplyNameText0);
        discountName = applyProductPromotionPage.getText(applyProductPromotionPage.VoucherApplyName0);
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.VoucherApplyBtn0), "Testcase is fail");
        applyProductPromotionPage.clickElement(applyProductPromotionPage.VoucherApplyBtn0);
//        applyProductPromotionPage.clickElement(applyProductPromotionPage.VoucherApplyBtn0);

    }

    @Test(priority = 12)
    public void verifyVoucherApplyBtnText2() {
        applyProductPromotionPage.scrollInScreen(promotionProductListPage.StorePromotionTitle, promotionProductListPage.MyVoucherList);
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.VoucherApplyBtn1), "Testcase is fail");
        applyProductPromotionPage.clickElement(applyProductPromotionPage.VoucherApplyBtn1);
        applyProductPromotionPage.scrollInScreen(applyProductPromotionPage.VoucherApplyName0,applyProductPromotionPage.VoucherApplyName1);
        applyProductPromotionPage.clickElement(applyProductPromotionPage.VoucherApplyBtnText2);
        applyProductPromotionPage.clickElement(applyProductPromotionPage.backBtn);
    }


    @Test(priority = 15)
    public void verifyOriginalPrice() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.OriginalPrice), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.OriginalPrice), applyProductPromotionPage.getOriginalPriceOnlyProduct(), "Testcase is fail");
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


    @Test(priority = 20)
    public void verifyPromotionText00() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionText00), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionText00), name3, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 20)
    public void verifyPromotionText01() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionText01), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionText01), name2, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 20)
    public void verifyPromotionText02() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionText02), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionText02), name1, "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 21)
    public void verifyPromotionValue00() {
//        totalCodeByPercent = priceApplyTotalDiscountCodePercent();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionValue00), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue00), "-" + priceApplyCategoryDiscountCodePercent() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 21)
    public void verifyPromotionValue01() {
//        totalCodeByPercent = priceApplyTotalDiscountCodePercent();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionValue01), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue01), "-" + priceApplyProductDiscountCodePercent() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 22)
    public void verifyPromotionValue02() {
//        totalCodeByPercent = priceApplyTotalDiscountCodePercent();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionValue02), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue02), "-" + priceTotalPercentAfterApplyCategoryAndProductDiscountCodePercent() + "đ", "Testcase is fail");
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
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue10), "-" + priceApplyTotalAfterApplyCategoryAndProductDiscountCampaignPercent() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 24)
    public void verifyMemberShipValue() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.memberShipValue11), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.memberShipValue11), "-" + discountMembershipTotalProductCategory() + "đ", "Testcase is fail");

//        softAssert.assertAll();
    }

    @Test(priority = 25)
    public void verifyDiscountValue() {
        applyProductPromotionPage.navigateBack();
        totalDiscount = totalDiscountTotalProductCategory();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.DiscountValue), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.DiscountValue), "-" + totalDiscountTotalProductCategory() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }


    @Test(priority = 27)
    public void verifyDiscountCode0() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.DiscountCode0), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.DiscountCode0), code3, "Testcase is fail");
//        Assert.assertAll();
    }

    @Test(priority = 27)
    public void verifyDiscountCode1() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.DiscountCode1), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.DiscountCode1), code2, "Testcase is fail");
//        Assert.assertAll();
    }

    @Test(priority = 27)
    public void verifyDiscountCode2() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.DiscountCode2), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.DiscountCode2), code1, "Testcase is fail");
//        Assert.assertAll();
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
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.TotalValue), totalPriceTotalProductCategory() + "đ", "Testcase is fail");
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

    @Test(priority = 37)
    public void verifySubTotalOrderValue() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.SubTotalOrderValue), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.SubTotalOrderValue), applyProductPromotionPage.getOriginalPriceOnlyProduct(), "Testcase is fail");
        applyProductPromotionPage.scrollInScreen(applyProductPromotionPage.OrderItemTitle, applyProductPromotionPage.SubTotalOrderValue);
    }

    @Test(priority = 39)
    public void verifyShowToolTipIconDiscount() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.ShowToolTipIconDiscount), "Testcase is fail");
        applyProductPromotionPage.clickElement(applyProductPromotionPage.ShowToolTipIconDiscount);
    }


    @Test(priority = 44)
    public void verifyPromotionValue00OrderDetail() {
        totalCodeByPercent = priceApplyCategoryDiscountCodePercent();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionValue00), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue00), "-" + priceApplyCategoryDiscountCodePercent() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 44)
    public void verifyPromotionValue01OrderDetail() {
//        totalCodeByPercent = priceApplyTotalDiscountCodePercent();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionValue01), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue01), "-" + priceApplyProductDiscountCodePercent() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 44)
    public void verifyPromotionValue02OrderDetail() {
//        totalCodeByPercent = priceApplyTotalDiscountCodePercent();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionValue02), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue02), "-" + priceTotalPercentAfterApplyCategoryAndProductDiscountCodePercent() + "đ", "Testcase is fail");
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
        totalCamPainByPercent = priceApplyTotalAfterApplyCategoryAndProductDiscountCampaignPercent();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.PromotionValue10), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.PromotionValue10), "-" + priceApplyTotalAfterApplyCategoryAndProductDiscountCampaignPercent() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

    @Test(priority = 47)
    public void verifyMemberShipValueOrderDetail() {
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.memberShipValue11), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.memberShipValue11), "-" + discountMembershipTotalProductCategory() + "đ", "Testcase is fail");
        applyProductPromotionPage.navigateBack();
//        softAssert.assertAll();
    }

    @Test(priority = 48)
    public void verifyTotalDiscountAmount() {
        totalDiscount = totalDiscountTotalProductCategory();
        Assert.assertTrue(applyProductPromotionPage.checkDisplay(applyProductPromotionPage.TotalDiscountAmount), "Testcase is fail");
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.TotalDiscountAmount), "-" + totalDiscountTotalProductCategory() + "đ", "Testcase is fail");
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
        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.TotalPriceOrderValue), totalPriceTotalProductCategory() + "đ", "Testcase is fail");
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
        stopDiscountCode(name3);
//        Assert.assertEquals(applyProductPromotionPage.getText(applyProductPromotionPage.TotalPriceOrderValue), totalPrice() + "đ", "Testcase is fail");
//        softAssert.assertAll();
    }

}
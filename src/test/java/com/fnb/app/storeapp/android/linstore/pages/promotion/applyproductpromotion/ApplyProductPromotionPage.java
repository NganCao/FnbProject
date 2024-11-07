package com.fnb.app.storeapp.android.linstore.pages.promotion.applyproductpromotion;

import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListPage;
import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.api.storeapp.pos.typeofproduct.discountcampaign.totalbill.Read_TotalBill_Voucher;
import com.fnb.utils.api.storeapp.pos.typeofproduct.discountcode.specificcategory.Read_Specific_Category_Code;
import com.fnb.utils.api.storeapp.pos.typeofproduct.discountcode.specificproduct.Read_Specific_Product_Code;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.fnb.app.setup.BaseTest.homePageLinStore;
import static com.fnb.utils.api.storeapp.pos.typeofproduct.discountcode.totalbill.Read_TotalBill_Code.readValueMoney;
import static com.fnb.utils.api.storeapp.pos.typeofproduct.discountcode.totalbill.Read_TotalBill_Code.readValuePercent;
import static com.fnb.utils.api.storeapp.pos.typeofproduct.onlyproduct.ReadProductData.readProduct;

public class ApplyProductPromotionPage extends BaseSetup {
    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;
    static String priceOnlyProduct;
    static String discountPercent;
    static String discountMoney;
    AddressManagementListPage addressManagementListPage;

    public ApplyProductPromotionPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        addressManagementListPage = homePageLinStore.navigateToAddressManagementListPage();
//        ReadProductData = new ReadProductData();
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        priceOnlyProduct = readProduct("price");
    }

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MenuProfileIcon1\"]")
    public WebElement MenuProfileIcon1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ComboName0\"]")
    public WebElement CategoryAllBranch;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ProductItem00\"]")
    public WebElement ProductItem00;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ProductItem01\"]")
    public WebElement ProductItem01;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ProductItem02\"]")
    public WebElement ProductItem02;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ProductItem03\"]")
    public WebElement ProductItem03;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ProductItem07\"]")
    public WebElement ProductItem07;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"NormalViewImage00\"]")
    public WebElement NormalViewImage00;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductName00\"]")
    public WebElement ProductName00;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ProductNameOfCombo00\"]")
    public WebElement ProductNameOfCombo00;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PriceAfterDiscount00\"]")
    public WebElement PriceAfterDiscount00;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductImg01\"]")
    public WebElement ProductImg01;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductName01\"]")
    public WebElement ProductName01;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PriceAfterDiscount01\"]")
    public WebElement PriceAfterDiscount01;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductImg02\"]")
    public WebElement ProductImg02;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductName02\"]")
    public WebElement ProductName02;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PriceAfterDiscount02\"]")
    public WebElement PriceAfterDiscount02;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductImg03\"]")
    public WebElement ProductImg03;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductName03\"]")
    public WebElement ProductName03;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PriceAfterDiscount03\"]")
    public WebElement PriceAfterDiscount03;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductImg04\"]")
    public WebElement ProductImg04;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductName04\"]")
    public WebElement ProductName04;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PriceAfterDiscount04\"]")
    public WebElement PriceAfterDiscount04;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductImg05\"]")
    public WebElement ProductImg05;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductName05\"]")
    public WebElement ProductName05;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PriceAfterDiscount05\"]")
    public WebElement PriceAfterDiscount05;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductImg06\"]")
    public WebElement ProductImg06;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductName06\"]")
    public WebElement ProductName06;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PriceAfterDiscount06\"]")
    public WebElement PriceAfterDiscount06;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductImg07\"]")
    public WebElement ProductImg07;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"ProductName07\"]")
    public WebElement ProductName07;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PriceAfterDiscount07\"]")
    public WebElement PriceAfterDiscount07;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"DiscountCodeTagIcon0\"]")
    public WebElement DiscountCodeTagIcon0;

    //===============================

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"AddProductToCart\"]")
    public WebElement AddProductToCart;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"CartIcon\"]")
    public WebElement CartIcon;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"order\"]")
    public WebElement OrderBtn;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DeliveryPickUpText\"]")
    public WebElement DeliveryPickUpText;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Tóm tắt đơn\"]")
    public WebElement OrderSummary;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"UseOffersToGetDiscountsText\"]")
    public WebElement UseOffersToGetDiscountsText;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"backBtn\"]")
    public WebElement backBtn;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionApplyExpTime0\"]")
    public WebElement PromotionApplyExpTime0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherApplyName0\"]")
    public WebElement VoucherApplyName0;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"VoucherApplyBtn0\"]")
    public WebElement VoucherApplyBtn0;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"VoucherApplyBtn1\"]")
    public WebElement VoucherApplyBtn1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"VoucherApplyBtn2\"]")
    public WebElement VoucherApplyBtn2;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"VoucherApplyIcon0\"]")
    public WebElement VoucherApplyIcon0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherApplyText0\"]")
    public WebElement VoucherApplyText0;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"toastAnimatedContainer\"]")
    public WebElement toastAnimatedContainer;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"CheckoutTotalPriceIcon\"]")
    public WebElement CheckoutTotalPriceIcon;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PaymentSummaryText\"]")
    public WebElement PaymentSummaryText;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SubTotalOrder\"]")
    public WebElement SubTotalOrder;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"OriginalPrice\"]")
    public WebElement OriginalPrice;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountCodeText\"]")
    public WebElement DiscountCodeText;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"ToolTipIcon\"]")
    public WebElement ToolTipIcon;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountValue\"]")
    public WebElement DiscountValue;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountCode0\"]")
    public WebElement DiscountCode0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountCode1\"]")
    public WebElement DiscountCode1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountCode2\"]")
    public WebElement DiscountCode2;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"RemoveDiscountIcon0\"]")
    public WebElement RemoveDiscountIcon0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"FeeAndTaxText\"]")
    public WebElement FeeAndTaxText;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"FeeAndTaxValue\"]")
    public WebElement FeeAndTaxValue;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TotalText\"]")
    public WebElement TotalText;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TotalValue\"]")
    public WebElement TotalValue;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"CheckoutButton\"]")
    public WebElement CheckoutButton;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountTitle\"]")
    public WebElement DiscountTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionTitle0\"]")
    public WebElement PromotionTitle0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionText00\"]")
    public WebElement PromotionText00;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionText01\"]")
    public WebElement PromotionText01;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionText02\"]")
    public WebElement PromotionText02;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionText10\"]")
    public WebElement PromotionText10;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionValue00\"]")
    public WebElement PromotionValue00;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionValue01\"]")
    public WebElement PromotionValue01;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionValue02\"]")
    public WebElement PromotionValue02;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionValue10\"]")
    public WebElement PromotionValue10;
    //TODO: Membership
    @FindBy(xpath = "//android.widget.TextView[@text=\"-20,000đ\"]")
    public WebElement memberShipValue;
    @FindBy(xpath = "//android.widget.TextView[@text=\"-16,000đ\"]")
    public WebElement memberShipValue10;
    @FindBy(xpath = "//android.widget.TextView[@text=\"-10,000đ\"]")
    public WebElement memberShipValue11;
    @FindBy(xpath = "//android.widget.TextView[@text=\"-20,250đ\"]")
    public WebElement memberShipValue2;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PromotionTitle1\"]")
    public WebElement PromotionTitle1;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"OrderItemTitle\"]")
    public WebElement OrderItemTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountPrice0\"]")
    public WebElement DiscountPrice0;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PaymentDetailTitle\"]")
    public WebElement PaymentDetailTitle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SubTotalOrderText\"]")
    public WebElement SubTotalOrderText;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"SubTotalOrderValue\"]")
    public WebElement SubTotalOrderValue;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ShowPromotionApply\"]")
    public WebElement ShowPromotionApply;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"DiscountValueOfOrderText\"]")
    public WebElement DiscountValueOfOrderText;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"ShowToolTipIconDiscount\"]")
    public WebElement ShowToolTipIconDiscount;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TotalDiscountAmount\"]")
    public WebElement TotalDiscountAmount;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"FeeAndTaxText\"]")
    public WebElement FeeAndTaxTextOrderDetail;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ShippingFeeText\"]")
    public WebElement ShippingFeeText;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ShippingFeeValue\"]")
    public WebElement ShippingFeeValue;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TotalPriceOrderText\"]")
    public WebElement TotalPriceOrderText;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TotalPriceOrderValue\"]")
    public WebElement TotalPriceOrderValue;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PaymentMethodText\"]")
    public WebElement PaymentMethodText;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PaymentMethodName\"]")
    public WebElement PaymentMethodName;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PaymentStatusNameText\"]")
    public WebElement PaymentStatusNameText;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PaymentStatus\"]")
    public WebElement PaymentStatus;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ButtonCancelOrder\"]")
    public WebElement ButtonCancelOrder;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"acceptBtn\"]")
    public WebElement acceptBtn;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"OrderIsCanceledSuccessfully\"]")
    public WebElement OrderIsCanceledSuccessfully;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ReOrderBtn\"]")
    public WebElement ReOrderBtn;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"deliveryHeader\"]")
    public WebElement deliveryHeader;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"branchAddressProductList\"]")
    public WebElement branchAddressProductList;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"iconDropdownBranch\"]")
    public WebElement iconDropdownBranch;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Go Food And Beverage\"]")
    public WebElement SelectBranch;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"SwitchBranch\"]")
    public WebElement SwitchBranch;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherApplyName1\"]")
    public WebElement VoucherApplyName1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherApplyName2\"]")
    public WebElement VoucherApplyName2;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"VoucherApplyName3\"]")
    public WebElement VoucherApplyName3;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherApplyBtnText6\"]")
    public WebElement VoucherApplyBtnText6;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"VoucherApplyBtnText2\"]")
    public WebElement VoucherApplyBtnText2;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.view.ViewGroup/com.horcrux.svg.SvgView")
    public WebElement AddIconTopping;


    public boolean checkDisplay(WebElement webElement) {
        try {
            return helper.checkElementDisplay(webElement);
        } catch (Exception e) {
            Log.info("Element is not Display" + ApplyProductPromotionPage.class.getName());
            e.printStackTrace();
            return false;
        }

    }

    public void navigateToPage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollInScreen(WebElement elementA, WebElement elementB) {
        try {
//            swipeVertical(DeliveryPickUpText, OrderSummary);
            swipeVertical(elementA, elementB);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Log.info("Error when move Screen" + ApplyProductPromotionPage.class.getName());
            e.printStackTrace();
        }
    }

    public void scrollInScreen1(WebElement elementA, WebElement elementB) {
        try {

//            swipeVertical(DeliveryPickUpText, OrderSummary);
            swipeVertical(elementA, elementB);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Log.info("Error when move Screen" + ApplyProductPromotionPage.class.getName());
            e.printStackTrace();
        }
    }

    public String getText(WebElement element) {
        try {
            return helper.waitToElementGetText(element);
        } catch (Exception e) {
            Log.info("Element can't get text" + ApplyProductPromotionPage.class.getName());
            e.printStackTrace();
            return null;
        }
    }


    public void clickElement(WebElement element) {
        try {
            helper.waitToElementClick(element);
        } catch (Exception e) {
            Log.info("Element can't click" + ApplyProductPromotionPage.class.getName());
            e.printStackTrace();
        }
    }

    public void navigateBack() {
        try {
            Thread.sleep(500);
            driver.navigate().back();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void swipeVertical(WebElement elementA, WebElement elementB) {
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

    public void productList() {
        clickElement(MenuProfileIcon1);
    }

    public void addOnlyProductToCart() {
        clickElement(ProductItem00);
        clickElement(AddProductToCart);
    }

    public void addProductToppingToCart(){
        scrollInScreen(ProductItem00, ProductItem02);
        clickElement(ProductItem07);
        clickElement(AddIconTopping);
        clickElement(AddProductToCart);
    }

    public String getOriginalPriceOnlyProduct() {
        String formattedString = String.format("%,d", Long.parseLong(priceOnlyProduct)).replace('.', ',');
        return formattedString + "đ";
    }

    public static String priceApplyTotalDiscountCodePercent() {
        priceOnlyProduct = readProduct("price");
        discountPercent = readValuePercent("percentNumber");
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long percentDiscount = Long.parseLong(discountPercent);
        Double percent = percentDiscount * 0.01;
        Long expPrice = (long) (oriPrice * percent);
        String formattedString = String.format("%,d", expPrice).replace('.', ',');
        return formattedString;
    }

    public static String priceApplyTotalDiscountCodePercentTopping() {
//        priceOnlyProduct = readProduct("price");
        priceOnlyProduct = "110,000";
        discountPercent = readValuePercent("percentNumber");
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long percentDiscount = Long.parseLong(discountPercent);
        Double percent = percentDiscount * 0.01;
        Long expPrice = (long) (oriPrice * percent);
        String formattedString = String.format("%,d", expPrice).replace('.', ',');
        return formattedString;
    }

    public static String priceApplyProductDiscountCodePercent() {
        priceOnlyProduct = readProduct("price");
        discountPercent = Read_Specific_Product_Code.readValuePercent("percentNumber");
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long percentDiscount = Long.parseLong(discountPercent);
        Double percent = percentDiscount * 0.01;
        Long expPrice = (long) (oriPrice * percent);
        String formattedString = String.format("%,d", expPrice).replace('.', ',');
        return formattedString;
    }

    public static String priceApplyCategoryDiscountCodePercent() {
        priceOnlyProduct = readProduct("price");
        discountPercent = Read_Specific_Category_Code.readValuePercent("percentNumber");
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long percentDiscount = Long.parseLong(discountPercent);
        Double percent = percentDiscount * 0.01;
        Long expPrice = (long) (oriPrice * percent);
        String formattedString = String.format("%,d", expPrice).replace('.', ',');
        return formattedString;
    }

    public static String priceApplyTotalPercentAndProductMoneyDiscountCodePercent() {
        priceOnlyProduct = readProduct("price");
        discountPercent = readValuePercent("percentNumber");
        discountMoney = Read_Specific_Product_Code.readValueMoney("maximumDiscountAmount");
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long moneyProduct = Long.parseLong(discountMoney.replace(",", ""));
        Long percentDiscount = Long.parseLong(discountPercent);
        Double percent = percentDiscount * 0.01;
        Long expPrice = oriPrice - moneyProduct;
        Long expPrice1 = (long) (expPrice * percent);
        String formattedString = String.format("%,d", expPrice1).replace('.', ',');
        return formattedString;
    }

    public static String priceTotalPercentAfterApplyCategoryAndProductDiscountCodePercent() {
        priceOnlyProduct = readProduct("price");
        String discountPercent = readValuePercent("percentNumber");
        String categoryDiscount = priceApplyCategoryDiscountCodePercent();
        String productDiscount = priceApplyProductDiscountCodePercent();
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long moneyProduct = Long.parseLong(productDiscount.replace(",", ""));
        Long moneyCategory = Long.parseLong(categoryDiscount.replace(",", ""));
        Long percentDiscount = Long.parseLong(discountPercent);
        Double percent = percentDiscount * 0.01;
        Long expPrice = oriPrice - moneyProduct - moneyCategory;
        Long expPriceDiscounted = (long) (expPrice * percent);
        String formattedString = String.format("%,d", expPriceDiscounted).replace('.', ',');
        System.out.println(formattedString);
        return formattedString;
    }

    public static String priceApplyTotalDiscountCampaignPercent() {
        priceOnlyProduct = readProduct("price");
        discountPercent = Read_TotalBill_Voucher.readValuePercent("percentNumber");
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long percentDiscount = Long.parseLong(discountPercent);
        Double percent = percentDiscount * 0.01;
        Long expPrice = (long) (oriPrice * percent);
        String formattedString = String.format("%,d", expPrice).replace('.', ',');
        return formattedString;
    }

    public static String priceApplyTotalAfterApplyCategoryAndProductDiscountCampaignPercent() {
        priceOnlyProduct = readProduct("price");
        String discountPercent = Read_TotalBill_Voucher.readValuePercent("percentNumber");
        String categoryDiscount = priceApplyCategoryDiscountCodePercent();
        String productDiscount = priceApplyProductDiscountCodePercent();
        Long moneyProduct = Long.parseLong(productDiscount.replace(",", ""));
        Long moneyCategory = Long.parseLong(categoryDiscount.replace(",", ""));
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long percentDiscount = Long.parseLong(discountPercent);
        Double percent = percentDiscount * 0.01;
        Long expPrice = oriPrice - moneyProduct - moneyCategory;
        Long expPriceDiscounted = (long) (expPrice * percent);
        String formattedString = String.format("%,d", expPriceDiscounted).replace('.', ',');
        System.out.println(formattedString);
        return formattedString;
    }

    public static String priceApplyTotalDiscountCampaignPercentWithDiscountMoney() {
        priceOnlyProduct = readProduct("price");
        discountPercent = Read_TotalBill_Voucher.readValuePercent("percentNumber");
        discountMoney = readValueMoney("maximumDiscountAmount");
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long moneyDiscount = Long.parseLong(discountMoney.replace(",", ""));
        Long percentVoucher = Long.parseLong(discountPercent);
        Long price = (oriPrice - moneyDiscount);
        Double percent = percentVoucher * 0.01;
        Long expPrice = (long) (price * percent);
        String formattedString = String.format("%,d", expPrice).replace('.', ',');
        return formattedString;
    }

    public static String priceApplyTotalPercentAndProductMoneyDiscountCampaignPercent() {
        priceOnlyProduct = readProduct("price");
        discountPercent = Read_TotalBill_Voucher.readValuePercent("percentNumber");
        discountMoney = Read_Specific_Product_Code.readValueMoney("maximumDiscountAmount");
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long moneyProduct = Long.parseLong(discountMoney.replace(",", ""));
        Long percentDiscount = Long.parseLong(discountPercent);
        Double percent = percentDiscount * 0.01;
        Long expPrice = oriPrice - moneyProduct;
        Long expPrice1 = (long) (expPrice * percent);
        String formattedString = String.format("%,d", expPrice1).replace('.', ',');
        return formattedString;
    }

    public static String discountMembership() {
        Long code1 = Long.parseLong(priceApplyTotalDiscountCodePercent().replace(",", ""));
        Long code2 = Long.parseLong(priceApplyTotalDiscountCampaignPercent().replace(",", ""));
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long price = oriPrice - (code1 + code2);
        Long expPrice = (long) (price * 0.25);
        String formattedString = String.format("%,d", expPrice).replace('.', ',');
        return formattedString;
    }

    public static String discountMembershipWithTotalMoney() {
        Long code1 = Long.parseLong(priceApplyTotalDiscountCodeMoney().replace(",", ""));
        Long code2 = Long.parseLong(priceApplyTotalDiscountCampaignPercentWithDiscountMoney().replace(",", ""));
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long price = oriPrice - (code1 + code2);
        Long expPrice = (long) (price * 0.25);
        String formattedString = String.format("%,d", expPrice).replace('.', ',');
        return formattedString;
    }

    public static String discountMembershipTotalPercentAndProductMoney() {
        Long code1 = Long.parseLong(priceApplyProductDiscountCodeMoney().replace(",", ""));
        Long code2 = Long.parseLong(priceApplyTotalPercentAndProductMoneyDiscountCodePercent().replace(",", ""));
        Long code3 = Long.parseLong(priceApplyTotalPercentAndProductMoneyDiscountCampaignPercent().replace(",", ""));
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long price = oriPrice - (code1 + code2 + code3);
        Long expPrice = (long) (price * 0.25);
        String formattedString = String.format("%,d", expPrice).replace('.', ',');
        return formattedString;
    }

    public static String discountMembershipTotalProductCategory() {
        Long code1 = Long.parseLong(priceApplyCategoryDiscountCodePercent().replace(",", ""));
        Long code2 = Long.parseLong(priceApplyProductDiscountCodePercent().replace(",", ""));
        Long code3 = Long.parseLong(priceTotalPercentAfterApplyCategoryAndProductDiscountCodePercent().replace(",", ""));
        Long code4 = Long.parseLong(priceApplyTotalAfterApplyCategoryAndProductDiscountCampaignPercent().replace(",", ""));
        Long oriPrice = Long.parseLong(priceOnlyProduct);
        Long price = oriPrice - (code1 + code2 + code3 + code4);
        Long expPrice = (long) (price * 0.25);
        String formattedString = String.format("%,d", expPrice).replace('.', ',');
        return formattedString;
    }

    public static String totalDiscount() {
        Long code1 = Long.parseLong(priceApplyTotalDiscountCodePercent().replace(",", ""));
        Long code2 = Long.parseLong(priceApplyTotalDiscountCampaignPercent().replace(",", ""));
        Long code3 = Long.parseLong(discountMembership().replace(",", ""));
        Long total = code1 + code2 + code3;
//        Long total = code2 + code3;
        String formattedString = String.format("%,d", total).replace('.', ',');
        return formattedString;
    }

    public static String totalDiscountMoney() {
        Long code1 = Long.parseLong(priceApplyTotalDiscountCodeMoney().replace(",", ""));
        Long code2 = Long.parseLong(priceApplyTotalDiscountCampaignPercentWithDiscountMoney().replace(",", ""));
        Long code3 = Long.parseLong(discountMembershipWithTotalMoney().replace(",", ""));
        Long total = code1 + code2 + code3;
//        Long total = code2 + code3;
        String formattedString = String.format("%,d", total).replace('.', ',');
        return formattedString;
    }

    public static String totalDiscountTotalPercentAndProductMoney() {
        Long code1 = Long.parseLong(priceApplyProductDiscountCodeMoney().replace(",", ""));
        Long code2 = Long.parseLong(priceApplyTotalPercentAndProductMoneyDiscountCodePercent().replace(",", ""));
        Long code3 = Long.parseLong(priceApplyTotalPercentAndProductMoneyDiscountCampaignPercent().replace(",", ""));
        Long code4 = Long.parseLong(discountMembershipTotalPercentAndProductMoney().replace(",", ""));
        Long total = code1 + code2 + code3 + code4;
        String formattedString = String.format("%,d", total).replace('.', ',');
        return formattedString;
    }

    public static String totalDiscountTotalProductCategory() {
        Long code1 = Long.parseLong(priceApplyCategoryDiscountCodePercent().replace(",", ""));
        Long code2 = Long.parseLong(priceApplyProductDiscountCodePercent().replace(",", ""));
        Long code3 = Long.parseLong(priceTotalPercentAfterApplyCategoryAndProductDiscountCodePercent().replace(",", ""));
        Long code4 = Long.parseLong(priceApplyTotalAfterApplyCategoryAndProductDiscountCampaignPercent().replace(",", ""));
        Long code5 = Long.parseLong(discountMembershipTotalProductCategory().replace(",", ""));
        Long total = code1 + code2 + code3 + code4 + code5;
        String formattedString = String.format("%,d", total).replace('.', ',');
        return formattedString;
    }

    public static String totalPrice() {
        Long discount = Long.parseLong(totalDiscount().replace(",", ""));
        Long oriPrice = Long.parseLong(priceOnlyProduct.replace(",", ""));
        Long fee = Long.valueOf(5000);
        Long total = oriPrice - discount + fee;
        String formattedString = String.format("%,d", total).replace('.', ',');
        return formattedString;
    }

    public static String totalPriceTotalMoney() {
        Long discount = Long.parseLong(totalDiscountMoney().replace(",", ""));
        Long oriPrice = Long.parseLong(priceOnlyProduct.replace(",", ""));
        Long fee = Long.valueOf(5000);
        Long total = oriPrice - discount + fee;
        String formattedString = String.format("%,d", total).replace('.', ',');
        return formattedString;
    }

    public static String totalPriceTotalPercentAndProductMoney() {
        Long discount = Long.parseLong(totalDiscountTotalPercentAndProductMoney().replace(",", ""));
        Long oriPrice = Long.parseLong(priceOnlyProduct.replace(",", ""));
        Long fee = Long.valueOf(5000);
        Long total = oriPrice - discount + fee;
        String formattedString = String.format("%,d", total).replace('.', ',');
        return formattedString;
    }

    public static String totalPriceTotalProductCategory() {
        Long discount = Long.parseLong(totalDiscountTotalProductCategory().replace(",", ""));
        Long oriPrice = Long.parseLong(priceOnlyProduct.replace(",", ""));
        Long fee = Long.valueOf(5000);
        Long total = oriPrice - discount + fee;
        String formattedString = String.format("%,d", total).replace('.', ',');
        return formattedString;
    }

    public static String getValueTotalPercent(String key) {
        String value = readValuePercent(key);
        return value;
    }

    public static String getValueProductMoney(String key) {
        String value = Read_Specific_Product_Code.readValuePercent(key);
        return value;
    }

    public static String priceApplyTotalDiscountCodeMoney() {
        discountMoney = readValueMoney("maximumDiscountAmount");
        return discountMoney;
    }

    public static String priceApplyProductDiscountCodeMoney() {
        discountMoney = Read_Specific_Product_Code.readValueMoney("maximumDiscountAmount");
        return discountMoney;
    }

    public static void main(String[] args) {
//        System.out.println(priceApplyProductDiscountCodeMoney());
//        System.out.println(priceApplyTotalPercentAndProductMoneyDiscountCodePercent());
//        System.out.println(priceApplyTotalPercentAndProductMoneyDiscountCampaignPercent());
        System.out.println(totalPriceTotalProductCategory());
    }

}

package com.fnb.web.pos.scenario_test;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.crm.customer.CustomerDetailPage;
import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.pos.pages.SettingDialog;
import com.fnb.web.pos.pages.component.*;
import com.fnb.web.pos.pages.InStorePage;
import dataObject.Integration.CustomerMembership;
import dataObject.Integration.MaterialInventoryHistory;
import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.fnb.web.setup.Setup.*;

public class posTest extends CommonPages {
    private Helper action;
    private InStorePage inStorePage;
    private ProductCartDetail productCartDetail;
    private RightCheckOutColumn rightCheckOutColumn;
    private OpenLeftMenu openLeftMenu;
    private SettingDialog settingDialog;

    // Data
    String binhthanh_branch;
    String category;
    String customerName;
    String customerPhone;

    // Product information 1
    String cafeDen_product;
    String cafeDen_product_M_priceName;
    String cafeDen_product_S_priceName;
    String cafeDen_product_M_priceValue;
    String cafeDen_product_S_priceValue;
    String cafeDen_product_caPhePhaSan_Ingredient;
    String cafeDen_product_caPhePhaSan_Ingredient_M_recipe;
    String cafeDen_product_caPhePhaSan_Ingredient_S_recipe;
    String cafeDen_product_cafe_optionName;
    String cafeDen_product_cafe_optionNam_vuaLevel;
    String cafeDen_product_cafe_optionNam_itLevel;
    String cafeDen_product_cafe_optionName_vuaLevel_quota;
    String cafeDen_product_cafe_optionName_itLevel_quota;

    // Product information 2
    String cafeSua_product;
    String cafeSua_product_priceValue;
    String cafeSua_product_cafe_optionName;
    String cafeSua_product_cafe_optionName_vuaLevel;
    String cafeSua_product_cafe_optionName_vuaLevel_quota;
    String cafeSua_product_caPhePhaSan_Ingredient_recipe;
    String davien_option;
    String davien_nhieu_levelOption;

    // Topping information
    String Thach_topping_price;

    String posTab; String adminTab;

    // Point configuration
    String EarningPoint_ExchangeRate = "1000";
    String RedeemPoint_ExchangeRate = "100";

    @BeforeClass
    public void BeforeClass() throws IOException {
        action = adminPage().helper;
        // Get admin tab
        adminTab = getDriver().getWindowHandle();

        productCartDetail = new ProductCartDetail(getDriver());
        rightCheckOutColumn = new RightCheckOutColumn(getDriver());

        // Prepare data
        binhthanh_branch = branchData.getBranch().get(0).getName();
        category = productCategoryData.getProductCategory().get(0).getName();
        customerName = customerData.getCustomers().get(0).getFirstName();
        customerPhone = customerData.getCustomers().get(0).getPhone();

        // Product information 1
        cafeDen_product = productData.getProduct().get(1).getName();
        cafeDen_product_caPhePhaSan_Ingredient = productData.getProduct().get(1).getProductInventoryData().get(1).getListProductPriceMaterials().get(0).getMaterial();
        cafeDen_product_caPhePhaSan_Ingredient_M_recipe = productData.getProduct().get(1).getProductInventoryData().get(1).getListProductPriceMaterials().get(0).getQuantity();
        cafeDen_product_caPhePhaSan_Ingredient_S_recipe = productData.getProduct().get(1).getProductInventoryData().get(0).getListProductPriceMaterials().get(0).getQuantity();

        cafeDen_product_M_priceName = productData.getProduct().get(1).getPrice().get(1).getPriceName();
        cafeDen_product_S_priceName = productData.getProduct().get(1).getPrice().get(0).getPriceName();

        cafeDen_product_M_priceValue = productData.getProduct().get(1).getPrice().get(1).getPriceValue();
        cafeDen_product_S_priceValue = productData.getProduct().get(1).getPrice().get(0).getPriceValue();

        cafeDen_product_cafe_optionName = productData.getProduct().get(1).getOption().get(0).getName();
        cafeDen_product_cafe_optionNam_vuaLevel = optionData.getOptions().get(1).getOptionLevels().get(1).getName();
        cafeDen_product_cafe_optionNam_itLevel = optionData.getOptions().get(1).getOptionLevels().get(0).getName();

        cafeDen_product_cafe_optionName_vuaLevel_quota = optionData.getOptions().get(1).getOptionLevels().get(1).getQuota();
        cafeDen_product_cafe_optionName_itLevel_quota = optionData.getOptions().get(1).getOptionLevels().get(0).getQuota();

        // Product information 2
        cafeSua_product = productData.getProduct().get(2).getName();
        cafeSua_product_priceValue = productData.getProduct().get(2).getPrice().get(0).getPriceValue();
        String cafeSua_product_caPhePhaSan_Ingredient = productData.getProduct().get(2).getProductInventoryData().get(0).getListProductPriceMaterials().get(0).getMaterial();
        cafeSua_product_caPhePhaSan_Ingredient_recipe = productData.getProduct().get(1).getProductInventoryData().get(0).getListProductPriceMaterials().get(0).getQuantity();
        cafeSua_product_cafe_optionName = productData.getProduct().get(2).getOption().get(0).getName();
        cafeSua_product_cafe_optionName_vuaLevel = optionData.getOptions().get(1).getOptionLevels().get(1).getName();
        cafeSua_product_cafe_optionName_vuaLevel_quota = optionData.getOptions().get(1).getOptionLevels().get(1).getQuota();

        // Thach topping
        Thach_topping_price = toppingData.getTopping().get(0).getPrice();

        davien_option = productData.getProduct().get(2).getOption().get(1).getName();
        davien_nhieu_levelOption = optionData.getOptions().get(0).getOptionLevels().get(2).getName();

        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);

        // Prepare test data
        // createProductPage().prepareData();
        adminService().changeTheQuantityOfIngredient(cafeDen_product_caPhePhaSan_Ingredient, binhthanh_branch, "1000000");

        // =========================== Operation config  // ===========================
        homePage().siderBar.clickButtonConfiguration();
        configurationPage().clickOperationTab();
        operationTab().choosePayFirst();
        operationTab().turnOnKitchen();
        operationTab().turnOnOOS();

        // Config bill
        configurationPage().clickBillAndTicketsTab();
        billAndTicketTab().selectSmallBillSize();

        // =========================== Point configuration  // ===========================
        homePage().siderBar.clickMnuCRM().clickPointConfiguration();
        pointConfigurationPage()
                .turnOnLoyaltyPointConfiguration()
                .enterEarningPoint_ExchangePoint(EarningPoint_ExchangeRate)
                .enterRedeemPoint_ExchangePoint(RedeemPoint_ExchangeRate)
                .clickSaveChanges();
//
        getDriver().switchTo().newWindow(WindowType.TAB);
        posTab = getDriver().getWindowHandle();
        inStorePage = posPage().navigatetoInStorePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD, binhthanh_branch);
//
        // =========================== Config printer // ===========================
        String customer_receipt = posLocalization.getSetting().getBillType().getCustomerReceipt();
        String k58_billSize = "K58 | Width 58mm";
        String usb_deviceType = posLocalization.getSetting().getDeviceType().getUsbPrinter();
        openLeftMenu = inStorePage.orderTypeBar.clickBtnExpand();
        settingDialog = openLeftMenu.clickSetting();
        settingDialog
                .clickAddNewDevice()
                .selectBillType(customer_receipt)
                .selectBillSize(k58_billSize)
                .selectDeviceType(usb_deviceType)
                .selectDeviceName("XP-58")
                .clickCreate()
                .clickCloseIcon();
        openLeftMenu.clickCollapseIcon();
    }

    @AfterClass
    public void AfterClass() {
    }

    @AfterMethod
    public void AfterTest() {
        action.switchTab(posTab);
        action.navigateToUrl(configObjectPos.getEnv());
    }

    public Integer extractNumberFromString(String text) {
        String numericString = text.replaceAll("[^\\d]", "");
        int number = Integer.parseInt(numericString);
        return  number;
    }

    public Double calculateTheIngredientWhichWillBeDeducted(String productSizeQuantity, String ingredientRecipe, String optionLevel) {
        return (action.convertStringToDouble(productSizeQuantity) * action.convertStringToDouble(ingredientRecipe)) * action.convertStringToDouble(optionLevel) / 100;
    }

    @Test
    public void SCENARIO1() {
        // Order product
        Integer cafeDen_product_quantity = 2;
        inStorePage.mainContentColumn
                .clickCategory(category)
                .selectCustomerByCustomerPhone(customerName, customerPhone)
                .clickProduct(cafeDen_product);
        productCartDetail
                .increaseQuantityProduct(cafeDen_product_quantity)
                .selectPrice(cafeDen_product_M_priceName)
                .selectOption(cafeDen_product_cafe_optionName, cafeDen_product_cafe_optionNam_vuaLevel)
                .clickPlusBtnIconTopping()
                .clickAddToCart();

        Integer cafeSua_product_quantity = 2;
        inStorePage.mainContentColumn.clickProduct(cafeSua_product);
        productCartDetail
                .increaseQuantityProduct(cafeSua_product_quantity)
                .selectOption(davien_option, davien_nhieu_levelOption)
                .selectOption(cafeSua_product_cafe_optionName, cafeSua_product_cafe_optionName_vuaLevel)
                .clickAddToCart();

        // Check price for each item in cart
        // === Check total amount for each item
        Double actual_totalAmountOfCafeDen = inStorePage.rightCheckOutColumn.getTotalAmountOfItem(cafeDen_product, cafeDen_product_M_priceName);
        Double expected_totalAmountOfCafeDen = action.convertStringToDouble(cafeDen_product_M_priceValue)*cafeDen_product_quantity + action.convertStringToDouble(Thach_topping_price)*2;
        Assert.assertEquals(actual_totalAmountOfCafeDen, expected_totalAmountOfCafeDen, "The total amount of this product is not the same, Please check!");

        Double actual_totalAmountOfCafeSua = inStorePage.rightCheckOutColumn.getTotalAmountOfItem(cafeSua_product, "");
        Double expected_totalAmountOfCafeSua = action.convertStringToDouble(cafeSua_product_priceValue)*cafeSua_product_quantity;
        Assert.assertEquals(actual_totalAmountOfCafeSua, expected_totalAmountOfCafeSua, "The total amount of this product is not the same, Please check!");

        // === Check subtotal
        Double actualSubtotal = inStorePage.rightCheckOutColumn.getSubtotalAmount();
        Double expectedSubtotal =
                          action.convertStringToDouble(cafeDen_product_M_priceValue)*cafeDen_product_quantity
                        + action.convertStringToDouble(cafeSua_product_priceValue)*cafeSua_product_quantity
                        + action.convertStringToDouble(Thach_topping_price)*2; // We add topping to x2(cafeSua)
        Assert.assertEquals(actualSubtotal, expectedSubtotal, "The subTotal is not the same, Please check !");

        // Calculate the point
        String totalString = rightCheckOutColumn.helper.getText(By.cssSelector(".price-total"));
        Integer totalAmount = extractNumberFromString(totalString);
        Integer earningPoint = totalAmount/Integer.parseInt(EarningPoint_ExchangeRate);

        // Calculate ingredient quantity which will be deducted
        // === Cà phê pha sẵn of Phin Đen Đá
        Double expectedIngredient1QuantityWhichWillBeDeducted = calculateTheIngredientWhichWillBeDeducted(Integer.toString(cafeDen_product_quantity), cafeDen_product_caPhePhaSan_Ingredient_M_recipe, cafeDen_product_cafe_optionName_vuaLevel_quota);
        // === Cà phê pha sẵn of Phin Sữa Đá
        Double expectedIngredient2QuantityWhichWillBeDeducted = calculateTheIngredientWhichWillBeDeducted(Integer.toString(cafeSua_product_quantity), cafeSua_product_caPhePhaSan_Ingredient_recipe, cafeSua_product_cafe_optionName_vuaLevel_quota);
        Double expectedTotalIngredientWhichWillBeDeducted = expectedIngredient1QuantityWhichWillBeDeducted + expectedIngredient2QuantityWhichWillBeDeducted;

        // Get the customer rank to check discount (if customer reached the customer rank)
        String customerRank = inStorePage.rightCheckOutColumn.getCustomerRank();

        // Switch back to admin to get the current point of the customer
        action.switchTab(adminTab);
        homePage().siderBar.clickMnuCRM().clickCustomer();
        CustomerDetailPage customerDetailPage = customerManagementPage().clickCustomer(customerName);
        String beforePoint = customerDetailPage.getRewardPoint();

        // Back to admin to check the current ingredient
        Double before_ingredient_Quantity = adminService().getQuantityIngredient(cafeDen_product_caPhePhaSan_Ingredient, binhthanh_branch);

        // Back to admin to get the information of customer rank
        CustomerMembership customerMembership = adminService().getCustomerMemberShip(customerRank);
        Double percentDiscount = customerMembership.getDiscount();
        Double maximumDiscount = customerMembership.getMaximumDiscount();

        // Check discount in cart (membership discount)
        action.switchTab(posTab);

        inStorePage.rightCheckOutColumn.clickDiscount_ArrowIcon();
        Double actualPromotionCustomerValue = inStorePage.rightCheckOutColumn.getPromotionValueCustomer();
        Double expectedPromotionCustomerValue = (actualSubtotal * percentDiscount) / 100;
        if (expectedPromotionCustomerValue >= maximumDiscount) {
            expectedPromotionCustomerValue = maximumDiscount;
        }
        Assert.assertEquals(actualPromotionCustomerValue, expectedPromotionCustomerValue, "The promotion customer value is not the same, please check!");

        // Payment
        CheckOutOrderDialog checkOutOrderDialog = rightCheckOutColumn.clickCreateOrder_PayFirst();
        String orderID = checkOutOrderDialog.getOrderID();
        checkOutOrderDialog.clickPay();

        // Navigate to order list to complete order
        PosOrderDialog posOrderDialog = inStorePage.orderTypeBar.navigateToOrderList();
        posOrderDialog.clickComplete(orderID);

        // Back to admin to check if the point added to customer or not
        int expectedRewardPoint = earningPoint + (int) (action.convertStringToDouble(beforePoint));
        action.switchTab(adminTab);
        homePage().siderBar.clickMnuCRM().clickCustomer();
        customerManagementPage().clickCustomer(customerName);
        int afterPoint = (int) (action.convertStringToDouble(customerDetailPage.getRewardPoint()));
        Assert.assertEquals(afterPoint, expectedRewardPoint, "The point earned is not correct");

        // Check the OOS of ingredient
        homePage().siderBar.clickMnuItemInventory().clickIngredientsMenu();
        materialManagementPage().clickOnMaterial(cafeDen_product_caPhePhaSan_Ingredient);
        Double after_Actual_Quantity = materialDetailPage().getTheCurrentQuantityStockOfIngredient(binhthanh_branch);
        Double after_Expected_Ingredient_Quantity = before_ingredient_Quantity - expectedTotalIngredientWhichWillBeDeducted;
        Assert.assertEquals(after_Actual_Quantity, after_Expected_Ingredient_Quantity, "The OOS have some not the same, Please check");

        // Check the Inventory history
        MaterialInventoryHistory materialInventoryHistory = adminService().getAMaterialInventoryHistory(cafeDen_product_caPhePhaSan_Ingredient, orderID);
        Double previous = action.convertStringToDouble(materialInventoryHistory.getPreviousQuantity());
        Double remain = action.convertStringToDouble(materialInventoryHistory.getRemain());
        Double change = action.convertStringToDouble(materialInventoryHistory.getChange());

        Assert.assertEquals(before_ingredient_Quantity, previous, "The previous quantity is not consistent, Please check!");
        Assert.assertEquals(after_Expected_Ingredient_Quantity, remain, "The remain quantity is not consistent, Please check!");
        Assert.assertEquals(expectedTotalIngredientWhichWillBeDeducted * (-1), change, "The change quantity is not consistent, Please check");
    }

    @Test
    public void SCENARIO1_2() {
        /** Select instore for service type */
        inStorePage.orderTypeBar.selectInStore_OrderType();

        /** Search product to add item to cart */
        ProductCartDetail productCartDetail = inStorePage.mainContentColumn.searchAndSelectProduct(cafeDen_product);
        // Order product
        Integer cafeDen_product_sizeM_quantity = 4;
        productCartDetail
                .increaseQuantityProduct(cafeDen_product_sizeM_quantity)
                .selectPrice(cafeDen_product_M_priceName)
                .selectOption(cafeDen_product_cafe_optionName, cafeDen_product_cafe_optionNam_vuaLevel)
                .clickPlusBtnIconTopping()
                .clickAddToCart();

        Integer cafeSua_product_quantity = 5;
        productCartDetail = inStorePage.mainContentColumn.searchAndSelectProduct(cafeSua_product);
        productCartDetail
                .increaseQuantityProduct(cafeSua_product_quantity)
                .selectOption(davien_option, davien_nhieu_levelOption)
                .selectOption(cafeSua_product_cafe_optionName, cafeSua_product_cafe_optionName_vuaLevel)
                .clickAddToCart();

        /** Reduce some item in the cart */
        inStorePage.rightCheckOutColumn
                .decreaseItem(cafeDen_product, cafeDen_product_M_priceName, 1)
                .decreaseItem(cafeSua_product, "", 2);
        // Update quantity
        cafeDen_product_sizeM_quantity = cafeDen_product_sizeM_quantity - 1;
        cafeSua_product_quantity = cafeSua_product_quantity - 2;

        /** Quick add item to cart */
        Integer cafeDen_product_sizeS_quantity = 1;
        inStorePage.mainContentColumn.clickAddToCard(cafeDen_product); // This will add the cafeDen_product size S (default size)

        /** Search customer */
        /** Select customer in search result */
        inStorePage.mainContentColumn.selectCustomerByCustomerPhone(customerName, customerPhone);

        /** Use customer point to get discount * (Skip)/

        /** Click CTA "Create order" on the Cart */
        CheckOutOrderDialog checkOutOrderDialog = rightCheckOutColumn.clickCreateOrder_PayFirst();

        /** Close the dialog Paymen to back to the Cart */
        checkOutOrderDialog.clickCloseIcon();

        /** Reduce some item in the cart*/
        inStorePage.rightCheckOutColumn
                .decreaseItem(cafeDen_product, cafeDen_product_M_priceName, 1)
                .decreaseItem(cafeSua_product, "",1);
        cafeDen_product_sizeM_quantity = cafeDen_product_sizeM_quantity - 1;
        cafeSua_product_quantity = cafeSua_product_quantity - 1;

        // Check price for each item in cart
        // === Check total amount for each item ===
        Double actual_totalAmountOfCafeDenSizeM = inStorePage.rightCheckOutColumn.getTotalAmountOfItem(cafeDen_product, cafeDen_product_M_priceName);
        Double expected_totalAmountOfCafeDen = action.convertStringToDouble(cafeDen_product_M_priceValue)*cafeDen_product_sizeM_quantity + action.convertStringToDouble(Thach_topping_price)*cafeDen_product_sizeM_quantity;
        Assert.assertEquals(actual_totalAmountOfCafeDenSizeM, expected_totalAmountOfCafeDen, "The total amount of Cafe den size M is not the same. Please check");

        Double actual_totalAmountOfCafeSua = inStorePage.rightCheckOutColumn.getTotalAmountOfItem(cafeSua_product, "");
        Double expected_totalAmountOfCafeSua = action.convertStringToDouble(cafeSua_product_priceValue)*cafeSua_product_quantity;
        Assert.assertEquals(actual_totalAmountOfCafeSua, expected_totalAmountOfCafeSua, "The total amount of cafe sua is not the same. Please check");

        Double actual_totalAmountOfCafeDenSizeS  = inStorePage.rightCheckOutColumn.getTotalAmountOfItem(cafeDen_product, cafeDen_product_S_priceName);
        Double expected_totalAmountOfCafeDenSizeS = action.convertStringToDouble(cafeDen_product_S_priceValue)*cafeDen_product_sizeS_quantity;
        Assert.assertEquals(actual_totalAmountOfCafeDenSizeS, expected_totalAmountOfCafeDenSizeS, "The total amount of order is not the same. Please check");

        // === Check subtotal
        Double actualSubtotal = inStorePage.rightCheckOutColumn.getSubtotalAmount();
        Double expectedSubtotal =
                action.convertStringToDouble(cafeDen_product_M_priceValue) * cafeDen_product_sizeM_quantity
                        + action.convertStringToDouble(cafeSua_product_priceValue) * cafeSua_product_quantity
                        + action.convertStringToDouble(Thach_topping_price) * 2 // We add topping to x2(cafeSua)
                        + action.convertStringToDouble(cafeDen_product_S_priceValue) * cafeDen_product_sizeS_quantity;
        Assert.assertEquals(actualSubtotal, expectedSubtotal, "The subTotal is not the same, Please check !");

        Double total = inStorePage.rightCheckOutColumn.getTotal();
        Double tax = inStorePage.rightCheckOutColumn.getTaxValue();
        Double actual_TotalPrice_WithoutTax = total - tax;

        // Calculate the point
        int earningPoint = (int) (total/Double.parseDouble(EarningPoint_ExchangeRate));

        // Calculate ingredient quantity which will be deducted
        // === Cà phê pha sẵn of Phin Đen Đá
        Double expectedIngredient1QuantityWhichWillBeDeducted = calculateTheIngredientWhichWillBeDeducted(Integer.toString(cafeDen_product_sizeM_quantity), cafeDen_product_caPhePhaSan_Ingredient_M_recipe, cafeDen_product_cafe_optionName_vuaLevel_quota);
        // === Cà phê pha sẵn of Phin Sữa Đá
        Double expectedIngredient2QuantityWhichWillBeDeducted = calculateTheIngredientWhichWillBeDeducted(Integer.toString(cafeSua_product_quantity), cafeSua_product_caPhePhaSan_Ingredient_recipe, cafeSua_product_cafe_optionName_vuaLevel_quota);
        // === Cà phê pha sẵn of Phin Sữa Đá
        Double expectedIngredient3QuantityWhichWillBeDeducted = calculateTheIngredientWhichWillBeDeducted(Integer.toString(cafeDen_product_sizeS_quantity), cafeSua_product_caPhePhaSan_Ingredient_recipe, cafeDen_product_cafe_optionName_itLevel_quota);
        Double expected_TotalIngredient_WhichWillBeDeducted = expectedIngredient1QuantityWhichWillBeDeducted + expectedIngredient2QuantityWhichWillBeDeducted + expectedIngredient3QuantityWhichWillBeDeducted;

        // Get the customer rank to check discount (if customer reached the customer rank)
        String customerRank = inStorePage.rightCheckOutColumn.getCustomerRank();

        // Switch back to admin to get the current point of the customer
        action.switchTab(adminTab);
        Double beforePoint = adminService().getRewardPoint(customerPhone);

        // Back to admin to get the information of customer rank
        CustomerMembership customerMembership = adminService().getCustomerMemberShip(customerRank);
        Double percentDiscount = customerMembership.getDiscount();
        Double maximumDiscount = customerMembership.getMaximumDiscount();

        // Back to admin to check the current ingredient
        Double before_ingredient_Quantity = adminService().getQuantityIngredient(cafeDen_product_caPhePhaSan_Ingredient, binhthanh_branch);

        // Check discount in cart (membership discount)
        action.switchTab(posTab);

        // Compare promotion customer value
        inStorePage.rightCheckOutColumn.clickDiscount_ArrowIcon();
        Double actualPromotionCustomerValue = inStorePage.rightCheckOutColumn.getPromotionValueCustomer();
        Double expectedPromotionCustomerValue = (actualSubtotal * percentDiscount) / 100;
        if (expectedPromotionCustomerValue >= maximumDiscount) {
            expectedPromotionCustomerValue = maximumDiscount;
        }
        Assert.assertEquals(actualPromotionCustomerValue, expectedPromotionCustomerValue, "The promotion customer value is not the same, please check!");

        /** Click CTA "Create order" on the Cart again*/
        checkOutOrderDialog = rightCheckOutColumn.clickCreateOrder_PayFirst();

        /** Pay the order by Cash */
        String orderID = checkOutOrderDialog.getOrderID();
        checkOutOrderDialog
                .selectPaymentMethod(CheckOutOrderDialog.paymentMethod.Cash)
                .clickPay();

        /** Print the Order receipt */
        // Navigate to order list to complete order
        PosOrderDialog posOrderDialog = inStorePage.orderTypeBar.navigateToOrderList();
        posOrderDialog.clickComplete(orderID);

        // Check reward point
        action.switchTab(adminTab);
        int expectedRewardPoint = earningPoint + (int) beforePoint.doubleValue();
        adminService.AssertionRewardPoint(customerName, expectedRewardPoint);

        // Check the OOS of ingredient
        Double after_Expected_Ingredient_Quantity = before_ingredient_Quantity - expected_TotalIngredient_WhichWillBeDeducted;
        adminService.AssertionOOSIngredient(cafeDen_product_caPhePhaSan_Ingredient, binhthanh_branch, after_Expected_Ingredient_Quantity);

        // Check inventory history
        adminService.AssertionInventoryHistory(cafeDen_product_caPhePhaSan_Ingredient, orderID, before_ingredient_Quantity, after_Expected_Ingredient_Quantity, expected_TotalIngredient_WhichWillBeDeducted);
    }

    @Test
    public void SCENARIO2_1() {
        /** Select instore for service type */
        inStorePage.orderTypeBar.selectInStore_OrderType();

        /** Scan barcode to add item to cart * (Skip)/

        /** Search product to add item to cart */
        ProductCartDetail productCartDetail = inStorePage.mainContentColumn.searchAndSelectProduct(cafeDen_product);
        // Order product
        Integer cafeDen_product_sizeM_quantity = 4;
        productCartDetail
                .increaseQuantityProduct(cafeDen_product_sizeM_quantity)
                .selectPrice(cafeDen_product_M_priceName)
                .selectOption(cafeDen_product_cafe_optionName, cafeDen_product_cafe_optionNam_vuaLevel)
                .clickPlusBtnIconTopping()
                .clickAddToCart();

        Integer cafeSua_product_quantity = 5;
        productCartDetail = inStorePage.mainContentColumn.searchAndSelectProduct(cafeSua_product);
        productCartDetail
                .increaseQuantityProduct(cafeSua_product_quantity)
                .selectOption(davien_option, davien_nhieu_levelOption)
                .selectOption(cafeSua_product_cafe_optionName, cafeSua_product_cafe_optionName_vuaLevel)
                .clickAddToCart();

        /** Reduce some item in the cart */
        inStorePage.rightCheckOutColumn
                .decreaseItem(cafeDen_product, cafeDen_product_M_priceName, 1)
                .decreaseItem(cafeSua_product, "", 2);
        // Update quantity
        cafeDen_product_sizeM_quantity = cafeDen_product_sizeM_quantity - 1;
        cafeSua_product_quantity = cafeSua_product_quantity - 2;

        /** Quick add item to cart */
        Integer cafeDen_product_sizeS_quantity = 1;
        inStorePage.mainContentColumn.clickAddToCard(cafeDen_product); // This will add the cafeDen_product size S (default size)

        // Check price for each item in cart
        // === Check total amount for each item ===
        Double actual_totalAmountOfCafeDenSizeM = inStorePage.rightCheckOutColumn.getTotalAmountOfItem(cafeDen_product, cafeDen_product_M_priceName);
        Double expected_totalAmountOfCafeDen = action.convertStringToDouble(cafeDen_product_M_priceValue)*cafeDen_product_sizeM_quantity + action.convertStringToDouble(Thach_topping_price)*cafeDen_product_sizeM_quantity;
        Assert.assertEquals(actual_totalAmountOfCafeDenSizeM, expected_totalAmountOfCafeDen, "The total amount is not the same. Please check");

        Double actual_totalAmountOfCafeSua = inStorePage.rightCheckOutColumn.getTotalAmountOfItem(cafeSua_product, "");
        Double expected_totalAmountOfCafeSua = action.convertStringToDouble(cafeSua_product_priceValue)*cafeSua_product_quantity;
        Assert.assertEquals(actual_totalAmountOfCafeSua, expected_totalAmountOfCafeSua, "The total amount is not the same. Please check");

        Double actual_totalAmountOfCafeDenSizeS  = inStorePage.rightCheckOutColumn.getTotalAmountOfItem(cafeDen_product, cafeDen_product_S_priceName);
        Double expected_totalAmountOfCafeDenSizeS = action.convertStringToDouble(cafeDen_product_S_priceValue)*cafeDen_product_sizeS_quantity;
        Assert.assertEquals(actual_totalAmountOfCafeDenSizeS, expected_totalAmountOfCafeDenSizeS, "The total amount is not the same. Please check");

        // === Check subtotal
        Double actualSubtotal = inStorePage.rightCheckOutColumn.getSubtotalAmount();
        Double expectedSubtotal =
                action.convertStringToDouble(cafeDen_product_M_priceValue) * cafeDen_product_sizeM_quantity
                        + action.convertStringToDouble(cafeSua_product_priceValue) * cafeSua_product_quantity
                        + action.convertStringToDouble(Thach_topping_price) * cafeDen_product_sizeM_quantity
                        + action.convertStringToDouble(cafeDen_product_S_priceValue) * cafeDen_product_sizeS_quantity;
        Assert.assertEquals(actualSubtotal, expectedSubtotal, "The subTotal is not the same, Please check !");

        Double tax = inStorePage.rightCheckOutColumn.getTaxValue();

        // Calculate ingredient quantity which will be deducted
        // === Cà phê pha sẵn of Phin Đen Đá
        Double expectedIngredient1QuantityWhichWillBeDeducted = calculateTheIngredientWhichWillBeDeducted(Integer.toString(cafeDen_product_sizeM_quantity), cafeDen_product_caPhePhaSan_Ingredient_M_recipe, cafeDen_product_cafe_optionName_vuaLevel_quota);
        // === Cà phê pha sẵn of Phin Sữa Đá
        Double expectedIngredient2QuantityWhichWillBeDeducted = calculateTheIngredientWhichWillBeDeducted(Integer.toString(cafeSua_product_quantity), cafeSua_product_caPhePhaSan_Ingredient_recipe, cafeSua_product_cafe_optionName_vuaLevel_quota);
        // === Cà phê pha sẵn of Phin Sữa Đá
        Double expectedIngredient3QuantityWhichWillBeDeducted = calculateTheIngredientWhichWillBeDeducted(Integer.toString(cafeDen_product_sizeS_quantity), cafeSua_product_caPhePhaSan_Ingredient_recipe, cafeDen_product_cafe_optionName_itLevel_quota);
        Double expected_TotalIngredient_WhichWillBeDeducted = expectedIngredient1QuantityWhichWillBeDeducted + expectedIngredient2QuantityWhichWillBeDeducted + expectedIngredient3QuantityWhichWillBeDeducted;

        // Switch back to admin to get the current point of the customer
        action.switchTab(adminTab);
        Double beforePoint = adminService().getRewardPoint(customerPhone);

        // Back to admin to check the current ingredient
        Double before_ingredient_Quantity = adminService().getQuantityIngredient(cafeDen_product_caPhePhaSan_Ingredient, binhthanh_branch);

        // Check discount in cart (membership discount)
        action.switchTab(posTab);

        /** Search customer */
        /** Select customer in search result */
        inStorePage.mainContentColumn.selectCustomerByCustomerPhone(customerName, customerPhone);

        // Get the customer rank to check discount (if customer reached the customer rank)
        String customerRank = inStorePage.rightCheckOutColumn.getCustomerRank();

        // Calculate the point
        Double total = inStorePage.rightCheckOutColumn.getTotal();
        int earningPoint = (int) (total/Double.parseDouble(EarningPoint_ExchangeRate));

        // Back to admin to get the information of customer rank
        action.switchTab(adminTab);
        CustomerMembership customerMembership = adminService().getCustomerMemberShip(customerRank);
        Double percentDiscount = customerMembership.getDiscount();
        Double maximumDiscount = customerMembership.getMaximumDiscount();

        // Compare promotion customer value
        action.switchTab(posTab);
        inStorePage.rightCheckOutColumn.clickDiscount_ArrowIcon();
        Double actualPromotionCustomerValue = inStorePage.rightCheckOutColumn.getPromotionValueCustomer();
        Double expectedPromotionCustomerValue = (actualSubtotal * percentDiscount) / 100;
        if (expectedPromotionCustomerValue >= maximumDiscount) {
            expectedPromotionCustomerValue = maximumDiscount;
        }
        Assert.assertEquals(actualPromotionCustomerValue, expectedPromotionCustomerValue, "The promotion customer value is not the same, please check!");

        /** Use customer point to get discount * (Skip) /

        /** Click CTA "Create order" on the Cart */
        CheckOutOrderDialog checkOutOrderDialog = rightCheckOutColumn.clickCreateOrder_PayFirst();

        /** Pay the order by Bank Transfer */
        String orderID = checkOutOrderDialog.getOrderID();
        checkOutOrderDialog
                .selectPaymentMethod(CheckOutOrderDialog.paymentMethod.BankTransfer)
                .clickReceivedMoney();
        //  Complate order
        PosOrderDialog posOrderDialog = inStorePage.orderTypeBar.navigateToOrderList();
        posOrderDialog.clickComplete(orderID);

        // Check reward point
        action.switchTab(adminTab);
        int expectedRewardPoint = earningPoint + (int) beforePoint.doubleValue();
        adminService.AssertionRewardPoint(customerName, expectedRewardPoint);

        // Check the OOS of ingredient
        Double after_Expected_Ingredient_Quantity = before_ingredient_Quantity - expected_TotalIngredient_WhichWillBeDeducted;
        adminService.AssertionOOSIngredient(cafeDen_product_caPhePhaSan_Ingredient, binhthanh_branch, after_Expected_Ingredient_Quantity);

        // Check inventory history
        adminService.AssertionInventoryHistory(cafeDen_product_caPhePhaSan_Ingredient, orderID, before_ingredient_Quantity, after_Expected_Ingredient_Quantity, expected_TotalIngredient_WhichWillBeDeducted);

        /** Print the Order receipt (Skip) */
    }

    @Test
    public void SCENARIO2_2() {
        /** Select instore for service type */
        inStorePage.orderTypeBar.selectInStore_OrderType();

        /** Search product to add item to cart */
        ProductCartDetail productCartDetail = inStorePage.mainContentColumn.searchAndSelectProduct(cafeDen_product);
        // Order product
        Integer cafeDen_product_sizeM_quantity = 4;
        productCartDetail
                .increaseQuantityProduct(cafeDen_product_sizeM_quantity)
                .selectPrice(cafeDen_product_M_priceName)
                .selectOption(cafeDen_product_cafe_optionName, cafeDen_product_cafe_optionNam_vuaLevel)
                .clickPlusBtnIconTopping()
                .clickAddToCart();

        Integer cafeSua_product_quantity = 5;
        productCartDetail = inStorePage.mainContentColumn.searchAndSelectProduct(cafeSua_product);
        productCartDetail
                .increaseQuantityProduct(cafeSua_product_quantity)
                .selectOption(davien_option, davien_nhieu_levelOption)
                .selectOption(cafeSua_product_cafe_optionName, cafeSua_product_cafe_optionName_vuaLevel)
                .clickAddToCart();

        /** Reduce some item in the cart */
        inStorePage.rightCheckOutColumn
                .decreaseItem(cafeDen_product, cafeDen_product_M_priceName, 1)
                .decreaseItem(cafeSua_product, "", 2);
        // Update quantity
        cafeDen_product_sizeM_quantity = cafeDen_product_sizeM_quantity - 1;
        cafeSua_product_quantity = cafeSua_product_quantity - 2;

        /** Quick add item to cart */
        Integer cafeDen_product_sizeS_quantity = 1;
        inStorePage.mainContentColumn.clickAddToCard(cafeDen_product); // This will add the cafeDen_product size S (default size)

        /** Search customer */
        /** Select customer in search result */
        inStorePage.mainContentColumn.selectCustomerByCustomerPhone(customerName, customerPhone);

        /** Use customer point to get discount * (Skip)/

         /** Click CTA "Create order" on the Cart */
        CheckOutOrderDialog checkOutOrderDialog = rightCheckOutColumn.clickCreateOrder_PayFirst();

        /** Close the dialog Paymen to back to the Cart */
        checkOutOrderDialog.clickCloseIcon();

        /** Reduce some item in the cart*/
        inStorePage.rightCheckOutColumn
                .decreaseItem(cafeDen_product, cafeDen_product_M_priceName, 1)
                .decreaseItem(cafeSua_product, "",1);
        cafeDen_product_sizeM_quantity = cafeDen_product_sizeM_quantity - 1;
        cafeSua_product_quantity = cafeSua_product_quantity - 1;

        // Check price for each item in cart
        // === Check total amount for each item ===
        Double actual_totalAmountOfCafeDenSizeM = inStorePage.rightCheckOutColumn.getTotalAmountOfItem(cafeDen_product, cafeDen_product_M_priceName);
        Double expected_totalAmountOfCafeDen = action.convertStringToDouble(cafeDen_product_M_priceValue)*cafeDen_product_sizeM_quantity + action.convertStringToDouble(Thach_topping_price)*cafeDen_product_sizeM_quantity;
        Assert.assertEquals(actual_totalAmountOfCafeDenSizeM, expected_totalAmountOfCafeDen, "The total amount is not the same. Please check");

        Double actual_totalAmountOfCafeSua = inStorePage.rightCheckOutColumn.getTotalAmountOfItem(cafeSua_product, "");
        Double expected_totalAmountOfCafeSua = action.convertStringToDouble(cafeSua_product_priceValue)*cafeSua_product_quantity;
        Assert.assertEquals(actual_totalAmountOfCafeSua, expected_totalAmountOfCafeSua, "The total amount is not the same. Please check");

        Double actual_totalAmountOfCafeDenSizeS  = inStorePage.rightCheckOutColumn.getTotalAmountOfItem(cafeDen_product, cafeDen_product_S_priceName);
        Double expected_totalAmountOfCafeDenSizeS = action.convertStringToDouble(cafeDen_product_S_priceValue)*cafeDen_product_sizeS_quantity;
        Assert.assertEquals(actual_totalAmountOfCafeDenSizeS, expected_totalAmountOfCafeDenSizeS, "The total amount is not the same. Please check");

        // === Check subtotal
        Double actualSubtotal = inStorePage.rightCheckOutColumn.getSubtotalAmount();
        Double expectedSubtotal =
                action.convertStringToDouble(cafeDen_product_M_priceValue) * cafeDen_product_sizeM_quantity
                        + action.convertStringToDouble(cafeSua_product_priceValue) * cafeSua_product_quantity
                        + action.convertStringToDouble(Thach_topping_price) * 2 // We add topping to x2(cafeSua)
                        + action.convertStringToDouble(cafeDen_product_S_priceValue) * cafeDen_product_sizeS_quantity;
        Assert.assertEquals(actualSubtotal, expectedSubtotal, "The subTotal is not the same, Please check !");

        Double total = inStorePage.rightCheckOutColumn.getTotal();
        Double tax = inStorePage.rightCheckOutColumn.getTaxValue();
        Double actual_TotalPrice_WithoutTax = total - tax;

        // Calculate the point
        int earningPoint = (int) (total/Double.parseDouble(EarningPoint_ExchangeRate));

        // Calculate ingredient quantity which will be deducted
        // === Cà phê pha sẵn of Phin Đen Đá
        Double expectedIngredient1QuantityWhichWillBeDeducted = calculateTheIngredientWhichWillBeDeducted(Integer.toString(cafeDen_product_sizeM_quantity), cafeDen_product_caPhePhaSan_Ingredient_M_recipe, cafeDen_product_cafe_optionName_vuaLevel_quota);
        // === Cà phê pha sẵn of Phin Sữa Đá
        Double expectedIngredient2QuantityWhichWillBeDeducted = calculateTheIngredientWhichWillBeDeducted(Integer.toString(cafeSua_product_quantity), cafeSua_product_caPhePhaSan_Ingredient_recipe, cafeSua_product_cafe_optionName_vuaLevel_quota);
        // === Cà phê pha sẵn of Phin Sữa Đá
        Double expectedIngredient3QuantityWhichWillBeDeducted = calculateTheIngredientWhichWillBeDeducted(Integer.toString(cafeDen_product_sizeS_quantity), cafeSua_product_caPhePhaSan_Ingredient_recipe, cafeDen_product_cafe_optionName_itLevel_quota);
        Double expected_TotalIngredient_WhichWillBeDeducted = expectedIngredient1QuantityWhichWillBeDeducted + expectedIngredient2QuantityWhichWillBeDeducted + expectedIngredient3QuantityWhichWillBeDeducted;

        // Get the customer rank to check discount (if customer reached the customer rank)
        String customerRank = inStorePage.rightCheckOutColumn.getCustomerRank();

        // Switch back to admin to get the current point of the customer
        action.switchTab(adminTab);
        Double beforePoint = adminService().getRewardPoint(customerPhone);

        // Back to admin to get the information of customer rank
        CustomerMembership customerMembership = adminService().getCustomerMemberShip(customerRank);
        Double percentDiscount = customerMembership.getDiscount();
        Double maximumDiscount = customerMembership.getMaximumDiscount();

        // Back to admin to check the current ingredient
        Double before_ingredient_Quantity = adminService().getQuantityIngredient(cafeDen_product_caPhePhaSan_Ingredient, binhthanh_branch);

        // Check discount in cart (membership discount)
        action.switchTab(posTab);

        // Compare promotion customer value
        inStorePage.rightCheckOutColumn.clickDiscount_ArrowIcon();
        Double actualPromotionCustomerValue = inStorePage.rightCheckOutColumn.getPromotionValueCustomer();
        Double expectedPromotionCustomerValue = (actualSubtotal * percentDiscount) / 100;
        if (expectedPromotionCustomerValue >= maximumDiscount) {
            expectedPromotionCustomerValue = maximumDiscount;
        }
        Assert.assertEquals(actualPromotionCustomerValue, expectedPromotionCustomerValue, "The promotion customer value is not the same, please check!");

        /** Click CTA "Create order" on the Cart again*/
        checkOutOrderDialog = rightCheckOutColumn.clickCreateOrder_PayFirst();

        /** Pay the order by Cash */
        String orderID = checkOutOrderDialog.getOrderID();
        checkOutOrderDialog
                .selectPaymentMethod(CheckOutOrderDialog.paymentMethod.BankTransfer)
                .clickReceivedMoney();

        /** Print the Order receipt */
        // Navigate to order list to complete order
        PosOrderDialog posOrderDialog = inStorePage.orderTypeBar.navigateToOrderList();
        posOrderDialog.clickComplete(orderID);

        // Check reward point
        action.switchTab(adminTab);
        int expectedRewardPoint = earningPoint + (int) beforePoint.doubleValue();
        adminService.AssertionRewardPoint(customerName, expectedRewardPoint);

        // Check the OOS of ingredient
        Double after_Expected_Ingredient_Quantity = before_ingredient_Quantity - expected_TotalIngredient_WhichWillBeDeducted;
        adminService.AssertionOOSIngredient(cafeDen_product_caPhePhaSan_Ingredient, binhthanh_branch, after_Expected_Ingredient_Quantity);

        // Check inventory history
        adminService.AssertionInventoryHistory(cafeDen_product_caPhePhaSan_Ingredient, orderID, before_ingredient_Quantity, after_Expected_Ingredient_Quantity, expected_TotalIngredient_WhichWillBeDeducted);
    }
}
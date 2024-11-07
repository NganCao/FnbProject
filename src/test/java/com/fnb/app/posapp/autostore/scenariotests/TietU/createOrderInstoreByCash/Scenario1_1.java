package com.fnb.app.posapp.autostore.scenariotests.TietU.createOrderInstoreByCash;

import com.fnb.app.posapp.autostore.pages.createorder.CreateOrderPage;
import com.fnb.app.posapp.autostore.pages.customer.CustomerDataTest;
import com.fnb.app.posapp.autostore.pages.customer.CustomerPage;
import com.fnb.app.posapp.autostore.pages.dashboard.DashboardPage;
import com.fnb.app.posapp.autostore.pages.dashboard.DataTest;
import com.fnb.app.posapp.autostore.pages.integration.POSAPPHelper;
import com.fnb.app.posapp.autostore.pages.inventorycheck.InventoryCheckPage;
import com.fnb.app.posapp.autostore.pages.login.LoginDataTest;
import com.fnb.app.posapp.autostore.pages.login.LoginPageAutoStore;
import com.fnb.app.posapp.autostore.pages.order.OrderDataTest;
import com.fnb.app.posapp.autostore.pages.order.OrderDetailPage;
import com.fnb.app.posapp.autostore.pages.order.OrderDetailPage.Customer;
import com.fnb.app.posapp.autostore.pages.order.OrderManagementPage;
import com.fnb.app.posapp.autostore.pages.payment.PaymentDataTest;
import com.fnb.app.posapp.autostore.pages.payment.PaymentDialogPage;
import com.fnb.app.posapp.autostore.pages.setting.SettingDataTest;
import com.fnb.app.posapp.autostore.pages.setting.SettingPage;
import com.fnb.app.setup.BaseTest;
import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.integration.AdminService;
import com.fnb.web.admin.pages.PagesAdminSetup;
import com.fnb.web.setup.Setup;
//import dataObject.Inventory.MaterialInventoryHistory;
import org.json.simple.JSONArray;
import dataObject.Integration.MaterialInventoryHistory;
import dataObject.Integration.CustomerMembership;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fnb.app.posapp.autostore.pages.createorder.CreateOrderPage.*;
import static com.fnb.app.posapp.autostore.pages.inventorycheck.InventoryCheckPage.*;
import static com.fnb.utils.api.posapp.pos.helpers.readjsonfile.UpdateMaterialToJson.updateMaterialQuantityToJson;

public class Scenario1_1 extends BaseTest {
    //https://mediastep1.sharepoint.com/:x:/s/GoFnB-BC.PodQCAutomationCloud/EbjRRvYQU_FDjylL4Sh9WVkBa0-XlsjSYhhqxvzzqA1vBw?e=M9bBo1&nav=MTVfezAwMDAwMDAwLTAwMDEtMDAwMC0wMDAwLTAwMDAwMDAwMDAwMH0
    private LoginPageAutoStore loginPageAutoStore;
    private LoginDataTest loginDataTest;
    private DashboardPage dashboardPage;
    private CustomerPage customerPage;
    private CustomerDataTest customerDataTest;
    private OrderManagementPage orderManagementPage;
    private OrderDetailPage orderDetailPage;
    private OrderDataTest orderDataTest;
    private PaymentDialogPage paymentDialogPage;
    private PaymentDataTest paymentDataTest;
    private DataTest dataTest;
    private CreateOrderPage createOrderPage;
    private InventoryCheckPage inventoryCheckPage;
    private Helper helper;
    private String totalAmount;
    private String subTotalAmount = "";
    private String rankDiscountAmount = "";
    private String orderId = "";
    private Customer customer;
    private String oldRewardPoint = "";
    private String usedPoint = "";
    private static double davien = 0;
    private static double thachraucauAuto = 0;
    private static double botcafe = 0;
    private static double updateDavien = 0;
    private static double updateBotCafe = 0;
    private static double updateThachAuto = 0;
    public Setup adminSetup;
    public PagesAdminSetup adminPage;
    private AdminService adminService;
    private MaterialInventoryHistory materialInventoryHistory;
    private CustomerMembership customerMembership;
    private Boolean ast;

    @BeforeClass
    public void navigateToPage() throws IOException {
        adminSetup = new Setup();
        adminSetup.setupDriver("admin", "theme1");
        adminPage = adminSetup.navigateToAdminPage();
        adminPage.navigateToHomePage("autostore@mailinator.com", "12345678");
        adminService = new AdminService(adminSetup.getDriver());

        loginPageAutoStore = homePageAutoStore.navigateToLoginPageAutoStore();
        dashboardPage = homePageAutoStore.navigateToDashboard();
        helper = new Helper(getDriver());
        customerPage = homePageAutoStore.navigateToCustomerPage();
        createOrderPage = homePageAutoStore.navigateToCreateOrderPage();
        paymentDialogPage = homePageAutoStore.navigateToPaymentDialogPage();
        orderDetailPage = homePageAutoStore.navigateToOrderDetailPage();
        orderManagementPage = homePageAutoStore.navigateToOrderManagementPage();
        inventoryCheckPage = homePageAutoStore.navigateToInventoryCheckPage();

        materialInventoryHistory = adminService.getAMaterialInventoryHistory("Đá viên", "I0623");
        davien = adminService.getQuantityIngredient("Đá viên", "Automation");
        thachraucauAuto = adminService.getQuantityIngredient("Thạch rau câu auto", "Automation");
        botcafe = adminService.getQuantityIngredient("bột cafe bao dùng k hết", "Automation");
    }

    @AfterClass
    public void quiteWebDriver() {
        adminSetup.tearDownDriver();
    }

    @Test(priority = 0)
    public void prepareDataInventory() {
        String s = materialInventoryHistory.getBranchName();
        System.out.println(s);
    }

//    @Test(priority = 1, testName = "Login POS APP")
//    public void step0() {
//        Assert.assertTrue(loginPageAutoStore.checkLoginSuccessfully(loginDataTest.EMAIL, loginDataTest.PASSWORD), "Login failed!");
//    }

    @Test(priority = 1, testName = "Setting printer")
    public void step01() {
        dashboardPage.clickHamburgerMenuMenu();
        helper.sleep(1);
        SettingPage settingPage = dashboardPage.clickSettingMenu();
        helper.sleep(2);
        //Bluetooth
//        String deviceType = SettingDataTest.BLUETOOTH;
//        String deviceName = SettingDataTest.BLUETOOTH_DEVICE_NAME;
//        Assert.assertTrue(settingPage.settingPrinter(deviceType, deviceName), "setting failed!");
        //Sunmi
        Assert.assertTrue(settingPage.settingPrinterPOS(), "setting failed!");
    }

    @Test(priority = 1, testName = "1\tSelect instore for service type")
    public void step1() {
        helper.sleep(1);
        dashboardPage.gotoDashBoard();
        helper.sleep(2);
        //get new config
        dashboardPage.clickRefreshButton();
        helper.sleep(2);
        dashboardPage.productCartList = new ArrayList<>();
        helper.sleep(1);
        System.out.println("-------------------- 1\tSelect instore for service type");
        Assert.assertTrue(dashboardPage.selectServiceType(dataTest.INSTORE), dashboardPage.actualRS);
    }

    @Test(priority = 2, testName = "2\tScan barcode to add item to cart -> change to click product then add to cart with topping option")
    public void step2() {
        System.out.println("-------------------- 2\tScan barcode to add item to cart -> change to click product then add to cart with topping option");
        System.out.println("0");
        helper.sleep(2);
        System.out.println("00");
        dashboardPage.selectCategoryByText(dataTest.CATEGORY_NAME);
        System.out.println("1");
        helper.sleep(2);
        dashboardPage.clickProductByProductName(dataTest.PRODUCT_NAME);
        System.out.println("2");
        helper.sleep(2);
        dashboardPage.clickAddToCartOnDetail(true, dataTest.PRODUCT_NAME, true, true, true);
        System.out.println("3");
        helper.sleep(2);
        Assert.assertTrue(dashboardPage.checkProductOnCart(), dashboardPage.actualRS);
    }

    @Test(priority = 3, testName = "3\tSearch product to add item to cart")
    public void step3() {
        helper.sleep(1);
        System.out.println("-------------------- 3\tSearch product to add item to cart");
        String productName = dashboardPage.searchProductByName(dataTest.SEARCH_PRODUCT);
        dashboardPage.upQuantityOnDetail(2);
        helper.sleepHaftSec();
        dashboardPage.clickAddToCartOnDetail(true, productName, false, false, false);
        helper.sleep(2);
        Assert.assertTrue(dashboardPage.checkProductOnCart(), dashboardPage.actualRS);
    }

    @Test(priority = 4, testName = "4\tReduce some item in the cart")
    public void step4() {
        helper.sleep(1);
        System.out.println("-------------------- 4\tReduce some item in the cart");
        List<DashboardPage.ProductCart> updatedCart = dashboardPage.reduceProductOnCart(2);
        helper.sleep(1);
        Assert.assertTrue(dashboardPage.compareCartListWithNewCart(updatedCart), dashboardPage.actualRS);
    }

    @Test(priority = 5, testName = "5\tQuick add item to cart")
    public void step5() {
        helper.sleep(1);
        dashboardPage.selectCategoryByText(dataTest.CATEGORY_NAME2);
        System.out.println(1);
        helper.sleep(3);
        System.out.println(2);
        dashboardPage.clickAddToCart();
        System.out.println(3);
        helper.sleep(2);
        System.out.println(4);
        Assert.assertTrue(dashboardPage.checkProductOnCart(), dashboardPage.actualRS);
    }

    @Test(priority = 6, testName = "6\tSearch customer + 7\tSelect customer in search result")
    public void step6() {
        subTotalAmount = dashboardPage.subTotalStr;
        System.out.println(subTotalAmount);
        System.out.println("-------------------- 6\tSearch customer + 7\tSelect customer in search result");
        helper.sleep(1);
        dashboardPage.clickCustomer();
        Assert.assertTrue(customerPage.selectCustomerWithPhone(CustomerDataTest.CUSTOMER_PHONE), customerPage.actualRS);
    }

    @Test(priority = 7, testName = "8\tUse customer point to get discount")
    public void step8() {
        customer = new Customer();
        System.out.println("-------------------- 8\tUse customer point to get discount");
        SoftAssert softAssert = new SoftAssert();
        String subtotal = subTotalAmount;
        String expTotalAmount = createOrderPage.totalAmount(subtotal, customerDataTest.CUSTOMER_PHONE);
        helper.sleep(1);
        createOrderPage.clickElement(createOrderPage.ToggleButton);
        helper.sleep(1);
        //point
        String expectedPoint = appliedPoint(subtotal, customerDataTest.CUSTOMER_PHONE);
        usedPoint = expectedPoint;
        customer.setName(customerPage.customerName);
        customer.setPhone(customerDataTest.CUSTOMER_PHONE);
        helper.sleep(1);
        String originalPoint = createOrderPage.getTextAppliedPoint(subTotalAmount);
        ast = helper.checkValueEquals("Point is wrong...", originalPoint, expectedPoint);
        softAssert.assertTrue(ast);
        //available point
        String expAvailablePoint = availablePoint(customerDataTest.CUSTOMER_PHONE) + " Availables";
        String orgAvailablePoint = createOrderPage.getTextAvailablePoint();
        ast = helper.checkValueEquals("Available Point is wrong...", orgAvailablePoint, expAvailablePoint);
        softAssert.assertTrue(ast);
        createOrderPage.clickTotalAmountBtn(subTotalAmount);
        //subtotal price
        String subTotalPriceInDialog = createOrderPage.getTextSubtotalPriceInDialog(subTotalAmount);
        ast = helper.checkValueEquals("subtotal price in dialog is wrong...", subtotal, subTotalPriceInDialog);
        softAssert.assertTrue(ast);
        //Rank discount
        String expRankDiscount = rankNameDiscount(customerDataTest.CUSTOMER_PHONE);
        String orgRankDiscount = createOrderPage.getTextMembership();
        customer.setRankName(createOrderPage.getRankName());
        ast = helper.checkValueEquals("Rank discount in dialog is wrong...", orgRankDiscount, expRankDiscount);
        softAssert.assertTrue(ast);
        //Rank discount value
        String orgRankDiscountValue = createOrderPage.getTextRankDiscountValue(subTotalAmount);
        String expRankDiscountValue = rankNameDiscountValue(subtotal, customerDataTest.CUSTOMER_PHONE);
        customer.setRankDiscount(createOrderPage.getRankDiscount());
        ast = helper.checkValueEquals("Rank discount value in dialog is wrong...", orgRankDiscountValue, expRankDiscountValue);
        softAssert.assertTrue(ast);
        //Point used
        String orgPointUser = createOrderPage.getTextUsedPoint(subTotalAmount);
        String expPointUser = "• Used " + expectedPoint + " points";
        ast = helper.checkValueEquals("Point used is wrong...", orgPointUser, expPointUser);
        softAssert.assertTrue(ast);
        //Point used value
        String orgPointUserValue = createOrderPage.getTextPointUsedValue(subTotalAmount);
        String expPointUserValue = "-" + expectedPoint + ",000đ";
        ast = helper.checkValueEquals("Point used valued is wrong...", orgPointUserValue, expPointUserValue);
        softAssert.assertTrue(ast);
        //user get point
        String expUserGetPoint = createOrderPage.getEarPoint(customerDataTest.CUSTOMER_PHONE, expTotalAmount, expectedPoint + ",000đ");
        String orgUserGetPoint = createOrderPage.getTextCustomerName(subTotalAmount, expUserGetPoint);
        customer.setReceivedPoint(expUserGetPoint);
        ast = orgUserGetPoint.contains(expUserGetPoint);
        softAssert.assertTrue(ast, "user get point is wrong. Actual: " + orgUserGetPoint + " Expected: " + expUserGetPoint);
        createOrderPage.navigateBack();
        //Total amount
        String orgTotalAmount = createOrderPage.getTextTotalAmount(subTotalAmount);
        rankDiscountAmount = expRankDiscountValue;
        totalAmount = expTotalAmount;
        ast = helper.checkValueEquals("Total amount is wrong...", orgTotalAmount, expTotalAmount);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        //TODO get reward point before creating an order
        oldRewardPoint = createOrderPage.getRewardPoint(customerDataTest.CUSTOMER_PHONE);
//        oldRewardPoint = String.valueOf(adminService.getRewardPoint(customerDataTest.CUSTOMER_PHONE));
        System.out.println("oldRewardPoint " + oldRewardPoint);
        addScreenshotToReport(adminSetup.getDriver(), ast, helper.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 9, testName = "9\tClick CTA \"Create order\" on the Cart")
    public void step9() {
        System.out.println("-------------------- 9\tClick CTA \"Create order\" on the Cart");
        helper.sleep(1);
        System.out.println(davien);
        System.out.println(botcafe);
        System.out.println(thachraucauAuto);
        //TODO check inventory
        List<DashboardPage.ProductCart> productCartList = dashboardPage.productCartList;
        JSONArray materialUsing = calculateMaterialUsing(productCartList, "Vịt thơm - all", "Vịt thơm - size", "Mê Sữa Đá");
        JSONArray materialUsing1 = calculateMaterialUsing(productCartList, "Vịt thơm - all", "Vịt thơm - size", "Mê Sữa Đá");
        //update material to json from admin
        updateMaterialQuantityToJson("Đá viên", davien);
        updateMaterialQuantityToJson("bột cafe bao dùng k hết", botcafe);
        updateMaterialQuantityToJson("Thạch rau câu auto", thachraucauAuto);
        dashboardPage.clickCreateOrder();
        softAssert = new SoftAssert();

        //calculate material using and degree this material
        //Đá viên
        JSONArray updateQuantityDaVien =  updateQuantity(materialUsing, "Đá viên", davien);
        updateDavien = getMaterialQuantity(updateQuantityDaVien, "Đá viên");
        System.out.println("updateDavien"+updateDavien);
        //Bột cafe
        JSONArray updateQuantityBotCafe =updateQuantity(materialUsing, "bột cafe bao dùng k hết", botcafe);
        updateBotCafe = getMaterialQuantity(updateQuantityBotCafe, "bột cafe bao dùng k hết");
        System.out.println("updateBotCafe"+ updateBotCafe);
        //Thạch auto
        JSONArray updateQuantityThachRauCau =updateQuantity(materialUsing, "Thạch rau câu auto", thachraucauAuto);
        updateThachAuto = getMaterialQuantity(updateQuantityThachRauCau, "Thạch rau câu auto");
        System.out.println("updateThachAuto"+ updateThachAuto);
        updateMaterialQuantityToJson("Đá viên", davien);

        updateMaterialQuantityToJson("bột cafe bao dùng k hết", updateDavien);
        updateMaterialQuantityToJson("bột cafe bao dùng k hết", updateBotCafe);
        updateMaterialQuantityToJson("Thạch rau câu auto", updateThachAuto);
        //check customer reward point -> don't receive
        String newRewardPoint = createOrderPage.getRewardPoint(customerDataTest.CUSTOMER_PHONE);
//        String newRewardPoint = String.valueOf(adminService.getRewardPoint(customerDataTest.CUSTOMER_PHONE));
        System.out.println("newRewardPoint " + newRewardPoint);
        softAssert.assertEquals(oldRewardPoint, newRewardPoint);
        softAssert.assertAll();
    }

    @Test(priority = 10, testName = "10\tPay the order by Cash")
    public void step10() {
        System.out.println("-------------------- 10\tPay the order by Cash");
        helper.sleep(3);
        orderId = paymentDialogPage.getOrderId();
        softAssert = new SoftAssert();
        ast = paymentDialogPage.checkPayAmountCash(totalAmount);
        addScreenshotToReport(getDriver(), ast, "check Pay Amount Cash " + paymentDialogPage.actualRS);
        softAssert.assertTrue(ast);
        ast = paymentDialogPage.checkReceiveMoneyAmountCash(totalAmount);
        addScreenshotToReport(getDriver(), ast, "check Receive Money Amount Cash " + paymentDialogPage.actualRS);
        softAssert.assertTrue(ast);
        paymentDialogPage.clickPayBtnWithCash();
        helper.sleep(2);
        orderDetailPage.showPCList(dashboardPage.productCartList);
        softAssert.assertAll();
    }


    @Test(priority = 11, testName = "11\tCheck information on order management list - Completed tab")
    public void step110() {
        System.out.println("-------------------- 11\tPrint the Order receipt in Order management");
        dashboardPage.clickHamburgerMenuMenu();
        helper.sleep(1);
        dashboardPage.clickOrderMenu();
        helper.sleepHaftSec();
        orderManagementPage.clickCompletedTab();
        helper.sleepHaftSec();
        orderManagementPage.clickInstoreFilter();
        helper.sleep(2);
        System.out.println(orderId);
        Assert.assertTrue(orderManagementPage.checkOrderDetail(orderId, orderDataTest.COMPLETED_STATUS, totalAmount, dashboardPage.quantityCartTotal), orderManagementPage.actualRS);
    }

//    @Test(priority = 12, testName = "11\tPrint the Order receipt in Order management")
//    public void step111() {
//        System.out.println("-------------------- 11\tPrint the Order receipt in Order management");
//        orderManagementPage.clickPrintButton();
//    }

    @Test(priority = 13, testName = "11\tCheck order detail")
    public void step112() {
        System.out.println("-------------------- 11\tCheck order detail");
        helper.sleep(1);
        float promotionTotalAmount = helper.convertToFloat(subTotalAmount) - helper.convertToFloat(totalAmount);
        //order detail
        orderManagementPage.clickOrderById(orderId);
        System.out.println(orderDetailPage.checkOrderIdHeader(orderId));
        helper.sleep(1);
        Map<String, String> priceDetail = new HashMap<>();
        priceDetail.put("subtotal", subTotalAmount);
        priceDetail.put("total", totalAmount);
        priceDetail.put("promotion", helper.formatCurrencyToThousand(promotionTotalAmount));
        Map<String, String> configMap = new HashMap<>();
        configMap.put(paymentDataTest.PAYMENT_METHOD_KEY, "first");
        configMap.put(paymentDataTest.KITCHEN_KEY, "no");
        helper.sleep(1);
        orderDetailPage.showPCList(dashboardPage.productCartList);
        helper.sleep(1);
        //write bill
        POSAPPHelper posappHelper = new POSAPPHelper(getDriver());
        posappHelper.writeBillOrderFile("1_1_" + posappHelper.FILE_PATH, dashboardPage.productCartList, customer, paymentDataTest.PAYMENT_BY_CASH, subTotalAmount, String.valueOf(dashboardPage.quantityCartTotal), helper.formatCurrencyToThousand(promotionTotalAmount), totalAmount);
        helper.sleep(1);
        Assert.assertTrue(orderDetailPage.checkOrderDetail(dashboardPage.productCartList, priceDetail, dashboardPage.quantityCartTotal, orderDataTest.COMPLETED_STATUS, paymentDataTest.PAYMENT_BY_CASH, configMap), orderDetailPage.actualRS);
    }

    @Test(priority = 14, testName = "11\tCheck the attached customer on order detail")
    public void verifyCustomer() {
        System.out.println("-------------------- 11\tCheck the attached customer on order detail");
        softAssert = new SoftAssert();
        ast = orderDetailPage.checkCustomerInfo(customer.getName(), customer.getPhone(), customer.getRankName(), customer.getRankDiscount(), customer.getReceivedPoint());
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast, orderDetailPage.actualRS);
        String newRewardPoint = createOrderPage.getRewardPoint(customerDataTest.CUSTOMER_PHONE);
//        String newRewardPoint = String.valueOf(adminService.getRewardPoint(customerDataTest.CUSTOMER_PHONE));
        System.out.println("oldRewardPoint " + oldRewardPoint);
        softAssert.assertNotEquals(oldRewardPoint, newRewardPoint);
        // new point = oldRewardPoint + receivedPoint - usedPoint
        ast = createOrderPage.checkRewardPoint(customerDataTest.CUSTOMER_PHONE, oldRewardPoint, customer.getReceivedPoint(), usedPoint);
//        createOrderPage.getExpectedRewardPoint(customerDataTest.CUSTOMER_PHONE, oldRewardPoint, customer.getReceivedPoint(), usedPoint);
//        String expectedPoint = createOrderPage.getExpectedRewardPoint(customerDataTest.CUSTOMER_PHONE, oldRewardPoint, customer.getReceivedPoint(), usedPoint);
//        ast = helper.checkValueEquals("check 1", newRewardPoint, expectedPoint);
        addScreenshotToReport(adminSetup.getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast, createOrderPage.actualRS);
        softAssert.assertAll();
    }

//    @Test(priority = 15, testName = "Click on Print")
//    public void print() {
//        System.out.println("print");
//        orderDetailPage.clickPrintButton();
//        helper.sleep(3);
//    }

//    @Test(priority = 16, testName = "Delete setting")
//    public void deleteSetting() {
//        //back
//        orderDetailPage.clickBackButton();
//        dashboardPage.clickHamburgerMenuMenu();
//        helper.sleep(1);
//        SettingPage settingPage = dashboardPage.clickSettingMenu();
//        settingPage.deleteDeviceWithBillType(SettingDataTest.BILL_TYPE);
//    }
}
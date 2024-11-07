package com.fnb.app.posapp.autostore.scenariotests.TietU.createOrderInstoreByCash;

import com.fnb.app.posapp.autostore.pages.createorder.CreateOrderPage;
import com.fnb.app.posapp.autostore.pages.customer.CustomerDataTest;
import com.fnb.app.posapp.autostore.pages.customer.CustomerPage;
import com.fnb.app.posapp.autostore.pages.dashboard.DashboardPage;
import com.fnb.app.posapp.autostore.pages.dashboard.DataTest;
import com.fnb.app.posapp.autostore.pages.integration.POSAPPHelper;
import com.fnb.app.posapp.autostore.pages.login.LoginDataTest;
import com.fnb.app.posapp.autostore.pages.login.LoginPageAutoStore;
import com.fnb.app.posapp.autostore.pages.order.OrderDataTest;
import com.fnb.app.posapp.autostore.pages.order.OrderDetailPage;
import com.fnb.app.posapp.autostore.pages.order.OrderDetailPage.*;
import com.fnb.app.posapp.autostore.pages.order.OrderManagementPage;
import com.fnb.app.posapp.autostore.pages.payment.PaymentDataTest;
import com.fnb.app.posapp.autostore.pages.payment.PaymentDialogPage;
import com.fnb.app.setup.BaseTest;
import com.fnb.utils.helpers.Helper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fnb.app.posapp.autostore.pages.createorder.CreateOrderPage.*;
import static com.fnb.app.posapp.autostore.pages.createorder.CreateOrderPage.rankNameDiscountValue;
import static com.fnb.app.setup.BaseSetup.softAssert;

public class Scenario1_2 extends BaseTest {
    //https://mediastep1.sharepoint.com/:x:/s/GoFnB-BC.PodQCAutomationCloud/EbjRRvYQU_FDjylL4Sh9WVkBa0-XlsjSYhhqxvzzqA1vBw?e=M9bBo1&nav=MTVfezAwMDAwMDAwLTAwMDEtMDAwMC0wMDAwLTAwMDAwMDAwMDAwMH0
    private LoginPageAutoStore loginPageAutoStore;
    private LoginDataTest loginDataTest;
    private DashboardPage dashboardPage;
    private CreateOrderPage createOrderPage;
    private CustomerPage customerPage;
    private CustomerDataTest customerDataTest;
    private OrderManagementPage orderManagementPage;
    private OrderDetailPage orderDetailPage;
    private OrderDataTest orderDataTest;
    private PaymentDialogPage paymentDialogPage;
    private PaymentDataTest paymentDataTest;
    private DataTest dataTest;
    private Helper helper;
    private String orderId = "";
    private SoftAssert softAssert;
    private String totalAmount = "";

    private String subTotalAmount = "";
    private String rankDiscountAmount = "";
    private Customer customer;
    private String oldRewardPoint = "";
    private String usedPoint = "";
    private Boolean ast;

    @BeforeClass
    public void navigateToPage() {
        loginPageAutoStore = homePageAutoStore.navigateToLoginPageAutoStore();
        dashboardPage = homePageAutoStore.navigateToDashboard();
        helper = new Helper(getDriver());
        customerPage = homePageAutoStore.navigateToCustomerPage();
        createOrderPage = homePageAutoStore.navigateToCreateOrderPage();
        paymentDialogPage = homePageAutoStore.navigateToPaymentDialogPage();
        orderDetailPage = homePageAutoStore.navigateToOrderDetailPage();
        orderManagementPage = homePageAutoStore.navigateToOrderManagementPage();
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
        helper.sleep(1);
        dashboardPage.clickAddToCart();
        helper.sleep(3);
        Assert.assertTrue(dashboardPage.checkProductOnCart(), dashboardPage.actualRS);
    }

    @Test(priority = 6, testName = "6\tSearch customer + 7\tSelect customer in search result")
    public void step6() {
        subTotalAmount = dashboardPage.subTotalStr;
        System.out.println(subTotalAmount);
        System.out.println("-------------------- 6\tSearch customer + 7\tSelect customer in search result");
        dashboardPage.clickCustomer();
        Assert.assertTrue(customerPage.selectCustomerWithPhone(customerDataTest.CUSTOMER_PHONE), customerPage.actualRS);
    }

    @Test(priority = 8, testName = "8\tUse customer point to get discount")
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
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        //available point
        String expAvailablePoint = availablePoint(customerDataTest.CUSTOMER_PHONE) + " Availables";
        String orgAvailablePoint = createOrderPage.getTextAvailablePoint();
        ast = helper.checkValueEquals("Available Point is wrong...", orgAvailablePoint, expAvailablePoint);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        createOrderPage.clickTotalAmountBtn(subTotalAmount);
        //subtotal price
        String subTotalPriceInDialog = createOrderPage.getTextSubtotalPriceInDialog(subTotalAmount);
        ast = helper.checkValueEquals("subtotal price in dialog is wrong...", subtotal, subTotalPriceInDialog);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        //Rank discount
        String expRankDiscount = rankNameDiscount(customerDataTest.CUSTOMER_PHONE);
        String orgRankDiscount = createOrderPage.getTextMembership();
        customer.setRankName(createOrderPage.getRankName());
        ast = helper.checkValueEquals("Rank discount in dialog is wrong...", orgRankDiscount, expRankDiscount);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        //Rank discount value
        String orgRankDiscountValue = createOrderPage.getTextRankDiscountValue(subTotalAmount);
        String expRankDiscountValue = rankNameDiscountValue(subtotal, customerDataTest.CUSTOMER_PHONE);
        customer.setRankDiscount(createOrderPage.getRankDiscount());
        ast = helper.checkValueEquals("Rank discount value in dialog is wrong...", orgRankDiscountValue, expRankDiscountValue);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        //Point used
        String orgPointUser = createOrderPage.getTextUsedPoint(subTotalAmount);
        String expPointUser = "• Used " + expectedPoint + " points";
        ast = helper.checkValueEquals("Point used is wrong...", orgPointUser, expPointUser);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        //Point used value
        String orgPointUserValue = createOrderPage.getTextPointUsedValue(subTotalAmount);
        String expPointUserValue = "-" + expectedPoint + ",000đ";
        ast = helper.checkValueEquals("Point used valued is wrong...", orgPointUserValue, expPointUserValue);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        //user get point
        String expUserGetPoint = createOrderPage.getEarPoint(customerDataTest.CUSTOMER_PHONE, expTotalAmount, expectedPoint + ",000đ");
        String orgUserGetPoint = createOrderPage.getTextCustomerName(subTotalAmount, expUserGetPoint);
        customer.setReceivedPoint(expUserGetPoint);
        ast = orgUserGetPoint.contains(expUserGetPoint);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
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
        softAssert.assertAll();
    }

    @Test(priority = 9, testName = "9\tClick CTA \"Create order\" on the Cart")
    public void step9() {
        System.out.println("-------------------- 9\tClick CTA \"Create order\" on the Cart");
        dashboardPage.clickCreateOrder();
        helper.sleep(1);
    }

    @Test(priority = 10, testName = "10\tClose the dialog Payment to back to the Cart")
    public void step10() {
        System.out.println("-------------------- 10\tClose the dialog Payment to back to the Cart");
        paymentDialogPage.clickClosePaymentDialog();
        helper.sleep(1);
        Assert.assertTrue(dashboardPage.checkProductOnCart(), dashboardPage.actualRS);
    }

    @Test(priority = 11, testName = "11\tQuick add item to cart")
    public void step11() {
        System.out.println("-------------------- 11\tQuick add item to cart");
        dashboardPage.selectCategoryByText(dataTest.CATEGORY_NAME);
        helper.sleep(1);
        dashboardPage.clickAddToCart();
        helper.sleep(3);
        Assert.assertTrue(dashboardPage.checkProductOnCart(), dashboardPage.actualRS);
    }

    @Test(priority = 12, testName = "12\tReduce some item in the cart")
    public void step121() {
        System.out.println("-------------------- 12\tReduce some item in the cart");
        List<DashboardPage.ProductCart> updatedCart = dashboardPage.reduceProductOnCart(4);
        helper.sleep(1);
        System.out.println(dashboardPage.quantityCartTotal);
        Assert.assertTrue(dashboardPage.compareCartListWithNewCart(updatedCart), dashboardPage.actualRS);
    }

    @Test(priority = 12, testName = "12\tCheck used point and receive point")
    public void step122() {
        subTotalAmount = dashboardPage.subTotalStr;
        customer = new Customer();
        System.out.println("-------------------- 8\tUse customer point to get discount");
        SoftAssert softAssert = new SoftAssert();
        String subtotal = subTotalAmount;
        String expTotalAmount = createOrderPage.totalAmount(subtotal, customerDataTest.CUSTOMER_PHONE);
        helper.sleep(1);
        //point
        String expectedPoint = appliedPoint(subtotal, customerDataTest.CUSTOMER_PHONE);
        usedPoint = expectedPoint;
        customer.setName(customerPage.customerName);
        customer.setPhone(customerDataTest.CUSTOMER_PHONE);
        helper.sleep(1);
        String originalPoint = createOrderPage.getTextAppliedPoint(subTotalAmount);
        ast = helper.checkValueEquals("Point is wrong...", originalPoint, expectedPoint);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        //available point
        String expAvailablePoint = availablePoint(customerDataTest.CUSTOMER_PHONE) + " Availables";
        String orgAvailablePoint = createOrderPage.getTextAvailablePoint();
        ast = helper.checkValueEquals("Available Point is wrong...", orgAvailablePoint, expAvailablePoint);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        createOrderPage.clickTotalAmountBtn(subTotalAmount);
        //subtotal price
        String subTotalPriceInDialog = createOrderPage.getTextSubtotalPriceInDialog(subTotalAmount);
        ast = helper.checkValueEquals("subtotal price in dialog is wrong...", subtotal, subTotalPriceInDialog);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        //Rank discount
        String expRankDiscount = rankNameDiscount(customerDataTest.CUSTOMER_PHONE);
        String orgRankDiscount = createOrderPage.getTextMembership();
        customer.setRankName(createOrderPage.getRankName());
        ast = helper.checkValueEquals("Rank discount in dialog is wrong...", orgRankDiscount, expRankDiscount);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        //Rank discount value
        String orgRankDiscountValue = createOrderPage.getTextRankDiscountValue(subTotalAmount);
        String expRankDiscountValue = rankNameDiscountValue(subtotal, customerDataTest.CUSTOMER_PHONE);
        customer.setRankDiscount(createOrderPage.getRankDiscount());
        ast = helper.checkValueEquals("Rank discount value in dialog is wrong...", orgRankDiscountValue, expRankDiscountValue);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        //Point used
        String orgPointUser = createOrderPage.getTextUsedPoint(subTotalAmount);
        String expPointUser = "• Used " + expectedPoint + " points";
        ast = helper.checkValueEquals("Point used is wrong...", orgPointUser, expPointUser);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        //Point used value
        String orgPointUserValue = createOrderPage.getTextPointUsedValue(subTotalAmount);
        String expPointUserValue = "-" + expectedPoint + ",000đ";
        ast = helper.checkValueEquals("Point used valued is wrong...", orgPointUserValue, expPointUserValue);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
        softAssert.assertTrue(ast);
        //user get point
        String expUserGetPoint = createOrderPage.getEarPoint(customerDataTest.CUSTOMER_PHONE, expTotalAmount, expectedPoint + ",000đ");
        String orgUserGetPoint = createOrderPage.getTextCustomerName(subTotalAmount, expUserGetPoint);
        customer.setReceivedPoint(expUserGetPoint);
        ast = orgUserGetPoint.contains(expUserGetPoint);
        addScreenshotToReport(getDriver(), ast, helper.actualRS);
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
        softAssert.assertAll();
    }
//
//    @Test(priority = 13, testName = "13\tClick CTA \"Create order\" on the Cart again")
//    public void step13() {
//        System.out.println("------------------- 13\tClick CTA \"Create order\" on the Cart again");
//        helper.sleep(1);
//        dashboardPage.clickCreateOrder();
//        softAssert = new SoftAssert();
//        //TODO check inventory
//        //check customer reward point -> don't receive
//        String newRewardPoint = createOrderPage.getRewardPoint(customerDataTest.CUSTOMER_PHONE);
//        softAssert.assertEquals(oldRewardPoint, newRewardPoint);
//        softAssert.assertAll();
//    }
//
//    @Test(priority = 14, testName = "14\tPay the order by Cash")
//    public void step14() {
//        System.out.println("-------------------- 14\tPay the order by Cash");
//        helper.sleep(3);
//        orderId = paymentDialogPage.getOrderId();
//        softAssert = new SoftAssert();
//        softAssert.assertTrue(paymentDialogPage.checkPayAmountCash(totalAmount));
//        softAssert.assertTrue(paymentDialogPage.checkReceiveMoneyAmountCash(totalAmount));
//        paymentDialogPage.clickPayBtnWithCash();
//        helper.sleep(2);
//        orderDetailPage.showPCList(dashboardPage.productCartList);
//        softAssert.assertAll();
//    }
//
//    @Test(priority = 15, testName = "15\tCheck information on order management list - Completed tab")
//    public void step15() {
//        System.out.println("-------------------- 15\tCheck information on order management list - Completed tab");
//        dashboardPage.clickHamburgerMenuMenu();
//        dashboardPage.clickOrderMenu();
//        helper.sleepHaftSec();
//        orderManagementPage.clickCompletedTab();
//        helper.sleepHaftSec();
//        orderManagementPage.clickInstoreFilter();
//        helper.sleep(2);
//        System.out.println(orderId);
//        Assert.assertTrue(orderManagementPage.checkOrderDetail(orderId, orderDataTest.COMPLETED_STATUS, totalAmount, dashboardPage.quantityCartTotal), orderManagementPage.actualRS);
//    }
//
//    @Test(priority = 16, testName = "15\tPrint the Order receipt in Order management")
//    public void step16() {
//        System.out.println("-------------------- 15\tPrint the Order receipt in Order management");
//        orderManagementPage.clickPrintButton();
//    }
//
//    @Test(priority = 17, testName = "15\tCheck order on order detail")
//    public void step17() {
//        System.out.println("-------------------- 15\tCheck information on order detail");
//        helper.sleep(1);
//        float promotionTotalAmount = helper.convertToFloat(subTotalAmount) - helper.convertToFloat(totalAmount);
//        //order detail
//        orderManagementPage.clickOrderById(orderId);
//        System.out.println(orderDetailPage.checkOrderIdHeader(orderId));
//        helper.sleep(1);
//        Map<String, String> priceDetail = new HashMap<>();
//        priceDetail.put("subtotal", subTotalAmount);
//        priceDetail.put("total", totalAmount);
//        priceDetail.put("promotion", helper.formatCurrencyToThousand(promotionTotalAmount));
//        Map<String, String> configMap = new HashMap<>();
//        configMap.put(paymentDataTest.PAYMENT_METHOD_KEY, "first");
//        configMap.put(paymentDataTest.KITCHEN_KEY, "no");
//        helper.sleep(1);
//        orderDetailPage.showPCList(dashboardPage.productCartList);
//        helper.sleep(1);
//        //write bill
//        POSAPPHelper posappHelper = new POSAPPHelper(getDriver());
//        posappHelper.writeBillOrderFile("1_2_" + posappHelper.FILE_PATH, dashboardPage.productCartList, customer, paymentDataTest.PAYMENT_BY_CASH, subTotalAmount, String.valueOf(dashboardPage.quantityCartTotal), helper.formatCurrencyToThousand(promotionTotalAmount), totalAmount);
//        helper.sleep(1);
//        Assert.assertTrue(orderDetailPage.checkOrderDetail(dashboardPage.productCartList, priceDetail, dashboardPage.quantityCartTotal, orderDataTest.COMPLETED_STATUS, paymentDataTest.PAYMENT_BY_BANK_TRANSFER, configMap), orderDetailPage.actualRS);
//    }
//
//    @Test(priority = 18, testName = "15\tCheck the attached customer on order detail")
//    public void verifyCustomer() {
//        System.out.println("-------------------- 15\tCheck the attached customer on order detail");
//        softAssert = new SoftAssert();
//        softAssert.assertTrue(orderDetailPage.checkCustomerInfo(customer.getName(), customer.getPhone(), customer.getRankName(), customer.getRankDiscount(), customer.getReceivedPoint()), orderDetailPage.actualRS);
//        String newRewardPoint = createOrderPage.getRewardPoint(customerDataTest.CUSTOMER_PHONE);
//        softAssert.assertNotEquals(oldRewardPoint, newRewardPoint);
//        // new point = oldRewardPoint + receivedPoint - usedPoint
//        softAssert.assertTrue(createOrderPage.checkRewardPoint(customerDataTest.CUSTOMER_PHONE, oldRewardPoint, customer.getReceivedPoint(), usedPoint), "wrong point");
//        softAssert.assertAll();
//    }
//
//    @Test(priority = 19, testName = "Click on Print")
//    public void print() {
//        System.out.println("print");
//        orderDetailPage.clickPrintButton();
//    }
}

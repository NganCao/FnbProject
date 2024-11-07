package com.fnb.app.posapp.autostore.pages.createorder;

import com.fnb.app.posapp.autostore.pages.dashboard.DashboardPage;
import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.admin.integration.AdminService;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.fnb.app.posapp.autostore.pages.customer.CustomerDataTest.CUSTOMER_PHONE;
import static com.fnb.utils.api.posapp.admin.getTokenAdmin.getMaxDiscount;
import static com.fnb.utils.api.posapp.pos.Get_Customer_Point.*;


public class CreateOrderPage extends BaseSetup {
    WebDriverWait wait;
    AndroidDriver driver;
    DashboardPage dashboardPage;
    //    String Subtotal = "";
    String totalAmount = "";
    public String actualRS;
    public static List<String> rankInfo;

    public CreateOrderPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        dashboardPage = navigateToAutoStore().navigateToDashboard();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")
    public WebElement AllowBtn;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Later\"]")
    public WebElement LaterBtn;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Login\"]")
    public WebElement LoginBtn;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Automation\"]")
    public WebElement AutomationBranch;

    @FindBy(xpath = "(//android.widget.FrameLayout[@resource-id=\"com.gofnb.posapplication:id/cvBtnAddCart\"])[1]")
    public WebElement AddProduct1;

    @FindBy(xpath = "(//android.widget.FrameLayout[@resource-id=\"com.gofnb.posapplication:id/cvBtnAddCart\"])[2]")
    public WebElement AddProduct2;
    //Product1
    @FindBy(xpath = "(//android.widget.TextView[@text=\"Products only\"])[2]")
    public WebElement ProductName1;

    @FindBy(xpath = "(//android.widget.TextView[@text=\"100,000\"])[5]")
    public WebElement ProductPrice1;
    //Product2
    @FindBy(xpath = "(//android.widget.TextView[@text=\"Products + size\"])[2]")
    public WebElement ProductName2;

    @FindBy(xpath = "(//android.widget.TextView[@text=\"100,000\"])[6]")
    public WebElement ProductPrice2;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Customer\"]")
    public WebElement CustomerIcon;

    @FindBy(xpath = "//android.widget.EditText[@text=\"Search by customer name, phone\"]")
    public WebElement CustomerInputField;

    @FindBy(xpath = "//android.widget.TextView[@text=\" Wills\"]")
    public WebElement CustomerNameInSearchField;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Subtotal (2 items)\"]")
    public WebElement SubtotalOrder;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Total amount, 150,000\"]")
    public WebElement TotalAmountList;

    @FindBy(xpath = "//android.widget.TextView[@text=\"150,000\"]")
    public WebElement TotalAmount;

    @FindBy(xpath = "//android.widget.TextView[@text=\" Wills will receive 150 points for completing the order.\"]")
    public WebElement PointReceive;

    @FindBy(xpath = "//android.widget.TextView[@text=\" Wills\"]")
    public WebElement CustomerNameInCart;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ToggleButton\"]")
    public WebElement ToggleButton;

    public String textXpath = "//android.widget.TextView[@text=\"";

    public String contentDescXpath = "//android.view.ViewGroup[@content-desc=\"";


    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Create order\"]")
    public WebElement createOrderBtn;

    public void clickElement(WebElement element) {
        try {
            helper.waitToElementClick(element);
        } catch (Exception e) {
            Log.info("Element can't click" + CreateOrderPage.class.getName());
            e.printStackTrace();
        }
    }

    public void clickTotalAmountBtn(String subtotal) {
//        String subtotal = dashboardPage.getSubTotalStr();
//        Subtotal = subtotal;
        String totalAmountBtn = totalAmount(subtotal, CUSTOMER_PHONE);
        WebElement element = driver.findElement(By.xpath(contentDescXpath + "Total amount, " + totalAmountBtn + "\"]"));
        try {
            helper.waitToElementClick(element);
        } catch (Exception e) {
            Log.info("Element can't click" + CreateOrderPage.class.getName());
            e.printStackTrace();
        }
    }

    public void navigateBack() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            Log.info("Element can't click" + CreateOrderPage.class.getName());
            e.printStackTrace();
        }
    }

    public String getText(WebElement element) {
        try {
            return helper.waitToElementGetText(element);
        } catch (Exception e) {
            Log.info("Element can't get text" + CreateOrderPage.class.getName());
            e.printStackTrace();
            return null;
        }
    }

    public String getTextAppliedPoint(String subtotal) {
//        String subtotal = dashboardPage.subTotalStr;
        System.out.println("sub " + subtotal);//dashboardPage.getSubTotalStr();
        String appliedPoint = appliedPoint(subtotal, CUSTOMER_PHONE);
        System.out.println("apli" + appliedPoint);
        System.out.println(textXpath + appliedPoint + "pts" + "\"]");
        try {
            WebElement element = driver.findElement(By.xpath(textXpath + appliedPoint + "\"]"));
            return helper.waitToElementGetText(element);
        } catch (Exception e) {
            Log.info("Element can't get text" + CreateOrderPage.class.getName());
            e.printStackTrace();
            return null;
        }
    }


    public String getTextAvailablePoint() {
        String availablePoint = availablePoint(CUSTOMER_PHONE);
        WebElement element = driver.findElement(By.xpath(textXpath + availablePoint + " Availables" + "\"]"));
        try {
            return helper.waitToElementGetText(element);
        } catch (Exception e) {
            Log.info("Element can't get text" + CreateOrderPage.class.getName());
            e.printStackTrace();
            return null;
        }
    }

    public String getTextCustomerName(String subtotal, String point) {
        //    @FindBy(xpath = "//android.widget.TextView[@text=\"La POP Chi Chi will receive 92 points for completing the order.\"]")
        String customerName = getCustomerName();
        System.out.println(textXpath + customerName + " will receive " + point + " points for completing the order." + "\"]");
        try {
            WebElement element = driver.findElement(By.xpath(textXpath + customerName + " will receive " + point + " points for completing the order." + "\"]"));
            return helper.waitToElementGetText(element);
        } catch (Exception e) {
            Log.info("Element can't get text" + CreateOrderPage.class.getName());
            e.printStackTrace();
            return null;
        }
    }

    public String getTextSubtotalPriceInDialog(String subtotal) {
        WebElement element = driver.findElement(By.xpath(textXpath + subtotal + "\"]"));
        try {
            return helper.waitToElementGetText(element);
        } catch (Exception e) {
            Log.info("Element can't get text" + CreateOrderPage.class.getName());
            e.printStackTrace();
            return null;
        }
    }

    public String getTextMembership() {
        //    @FindBy(xpath = "//android.widget.TextView[@text=\"• platium member (25%)\"]")
        String rank = lazyLoadCustomer("membership", CUSTOMER_PHONE);
        String discountValue = lazyLoadCustomer("discount", CUSTOMER_PHONE).replace(".0", "");
        WebElement element = driver.findElement(By.xpath(textXpath + "• " + rank + " (" + discountValue + "%)" + "\"]"));
        try {
            return helper.waitToElementGetText(element);
        } catch (Exception e) {
            Log.info("Element can't get text" + CreateOrderPage.class.getName());
            e.printStackTrace();
            return null;
        }
    }

    public String getTextRankDiscountValue(String subtotal) {
        //    @FindBy(xpath = "//android.widget.TextView[@text=\"-43,222đ\"]")
        String rankDiscountValue = rankNameDiscountValue(subtotal, CUSTOMER_PHONE);
        WebElement element = driver.findElement(By.xpath(textXpath + rankDiscountValue + "\"]"));
        try {
            return helper.waitToElementGetText(element);
        } catch (Exception e) {
            Log.info("Element can't get text" + CreateOrderPage.class.getName());
            e.printStackTrace();
            return null;
        }
    }

    public String getTextUsedPoint(String subtotal) {
        //    @FindBy(xpath = "//android.widget.TextView[@text=\"• Used 129 points\"]")
        String expectedPoint = appliedPoint(subtotal, CUSTOMER_PHONE);
        String expPointUser = "• Used " + expectedPoint + " points";
        WebElement element = driver.findElement(By.xpath(textXpath + expPointUser + "\"]"));
        try {
            return helper.waitToElementGetText(element);
        } catch (Exception e) {
            Log.info("Element can't get text" + CreateOrderPage.class.getName());
            e.printStackTrace();
            return null;
        }
    }

    public String getTextPointUsedValue(String subtotal) {
        //    @FindBy(xpath = "//android.widget.TextView[@text=\"-129,000đ\"]")
        String expectedPoint = appliedPoint(subtotal, CUSTOMER_PHONE);
        String expPointUserValue = "-" + expectedPoint + ",000đ";
        WebElement element = driver.findElement(By.xpath(textXpath + expPointUserValue + "\"]"));
        try {
            return helper.waitToElementGetText(element);
        } catch (Exception e) {
            Log.info("Element can't get text" + CreateOrderPage.class.getName());
            e.printStackTrace();
            return null;
        }
    }

    public String getTextTotalAmount(String subtotal) {
        //    @FindBy(xpath = "//android.widget.TextView[@text=\"666\"]")
        String totalAmount = totalAmount(subtotal, CUSTOMER_PHONE);
        WebElement element = driver.findElement(By.xpath(textXpath + totalAmount + "\"]"));
        try {
            return helper.waitToElementGetText(element);
        } catch (Exception e) {
            Log.info("Element can't get text" + CreateOrderPage.class.getName());
            e.printStackTrace();
            return null;
        }
    }

    public void waitToLoadData() {
        Log.info("splash screen...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendKey(WebElement element, String value) {
        try {
            helper.waitToElementSendKey(element, value);
        } catch (Exception e) {
            Log.info("Element can't send key" + CreateOrderPage.class.getName());
            e.printStackTrace();
        }
    }

    public static String rankNameDiscount(String phoneNumber) {
        String rankName = lazyLoadCustomer("membership", phoneNumber);
        double discountPercentage = Double.parseDouble(lazyLoadCustomer("discount", phoneNumber));
        rankInfo = new ArrayList<>();
        int intDiscount = (int) discountPercentage;
        rankInfo.add(rankName);
        rankInfo.add(intDiscount + "%");
        String rankNameDiscountText = "• " + rankName + " (" + intDiscount + "%)";
        return rankNameDiscountText;
    }

    public String getRankName() {
        return rankInfo.get(0);
    }

    public String getRankDiscount() {
        return rankInfo.get(1);
    }

    public static String rankNameDiscountValue(String subtotal, String phoneNumber) {
        String subtotal0 = convertSubtotal(subtotal);
        double subtotal1 = Double.parseDouble(subtotal0);
        double rankDiscount = calculateRankDiscount(subtotal1, phoneNumber);
        String rankDiscountString = formatCurrency(rankDiscount);
        String value = "-" + rankDiscountString + "đ";
        return value;
    }

    // lấy customerName theo sđt
    public static String getCustomerName() {
        String customerName = lazyLoadCustomer("customerName", CUSTOMER_PHONE);
        return customerName.trim();
    }

    // Tính toán chiết khấu dựa trên hạng
    public static double calculateRankDiscount(double subtotal, String phoneNumber) {
        double discountPercentage = Double.parseDouble(lazyLoadCustomer("discount", phoneNumber));
        double maxDiscount = Double.parseDouble(getMaxDiscount(lazyLoadCustomer("membership", phoneNumber)));
        double discount = subtotal * (discountPercentage / 100);
        if (discount > maxDiscount) {
            discount = maxDiscount;
        }
        return discount;
    }

    // Tính toán điểm có thể sử dụng
    public static double calculateAppliedPoint(double subtotal, double discount, double availablePoint) {
        double desiredPoint = subtotal - discount;
        if (availablePoint >= desiredPoint / 1000) {
            return desiredPoint / 1000;
        } else if (availablePoint > 0) {
            return availablePoint;
        } else {
            System.out.println("Available points are zero.");
            return 0;
        }
    }

    public static String appliedPoint(String subtotal, String phoneNumber) {
        String subtotal0 = convertSubtotal(subtotal);
        double subtotal1 = Double.parseDouble(subtotal0);
        double rankDiscount = calculateRankDiscount(subtotal1, phoneNumber);
        double availablePoint = Double.parseDouble(calculatePointCanUse("availablePoint", phoneNumber));
        double appliedPoint = calculateAppliedPoint(subtotal1, rankDiscount, availablePoint);
        int pointInt = (int) appliedPoint;
        String appliedPointString = String.valueOf(pointInt).replace(".0", "");
        return appliedPointString;
    }

    // Tính toán tổng số tiền sau khi áp dụng chiết khấu và điểm
    public static double calculateTotalAmount(double subtotal, double discount, double point) {
        double totalAmount = subtotal - discount - point;
        return Math.max(totalAmount, 0);
    }

    public static String formatCurrency(double amount) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###");
        return formatter.format(amount);
    }

    public static String availablePoint(String phoneNumber) {
        double availablePoint = Double.parseDouble(calculatePointCanUse("availablePoint", phoneNumber));
        String stringAvailablePoint = String.valueOf(availablePoint).replace(".0", "");
        int intAvailablePoint = Integer.parseInt(stringAvailablePoint);
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###");
        return formatter.format(intAvailablePoint);
    }

    public static String convertSubtotal(String subtotalStr) {
        String subtotal = subtotalStr.replace(",", "");
        return subtotal;
    }


    public static void calculatePoint(String subtotalStr, String phoneNumber) {
        // parse string subtotal to double
        double subtotal = Double.parseDouble(subtotalStr);

        // Tính toán chiết khấu hạng
        double rankDiscount = calculateRankDiscount(subtotal, phoneNumber);
        String rankDiscountString = formatCurrency(rankDiscount);
        System.out.println("Rank Discount: " + rankDiscountString + "đ");

        // Giả định điểm khả dụng từ API (ví dụ: 0)
        double availablePoint = Double.parseDouble(calculatePointCanUse("availablePoint", phoneNumber));
        System.out.println("Available Point: " + availablePoint);

        // Tính toán điểm áp dụng
        double appliedPoint = calculateAppliedPoint(subtotal, rankDiscount, availablePoint);
        int point = (int) appliedPoint;
        double pointDouble = point;
//        String appliedPointString = String.valueOf(point).replace(".0", "");
        String appliedPointString = String.valueOf(point);
        System.out.println("Applied Point: " + appliedPointString);

        // Tính toán tổng số tiền
        double totalAmount = calculateTotalAmount(subtotal, rankDiscount, pointDouble * 1000);
//        System.out.println(subtotal + "-" + rankDiscount + "-" + appliedPoint * 1000);
        System.out.println(subtotal + "-" + rankDiscount + "-" + pointDouble * 1000);
        System.out.println("Total amount: " + totalAmount + "đ");
    }

    public String totalAmount(String subtotal, String phoneNumber) {
        String subtotal0 = convertSubtotal(subtotal);
        double subtotal1 = Double.parseDouble(subtotal0);
        double rankDiscount = calculateRankDiscount(subtotal1, phoneNumber);
        double availablePoint = Double.parseDouble(calculatePointCanUse("availablePoint", phoneNumber));
        double appliedPoint = calculateAppliedPoint(subtotal1, rankDiscount, availablePoint);
        int point = (int) appliedPoint;
        double pointDouble = point;
        double totalAmount = calculateTotalAmount(subtotal1, rankDiscount, pointDouble * 1000);
        return helper.formatCurrencyToThousand(totalAmount);
    }

    public String getRewardPoint(String phoneNumber) {
        return getCurrentRewardPoint(phoneNumber);
    }

    public Boolean checkRewardPoint(String phoneNumber, String oldRewardPoint, String addPoint, String usedPointStr) {
        double currentRewardPoint = helper.convertToDouble(getRewardPoint(phoneNumber));
        double olddPoint = helper.convertToDouble(oldRewardPoint);
        double receivedPoint = helper.convertToDouble(addPoint);
        double usedPoint = helper.convertToDouble(usedPointStr);
        double expectedPoint = olddPoint + receivedPoint - usedPoint;
        actualRS = "Wrong get point. Actual: " + currentRewardPoint + " Expected: " + expectedPoint;
        return (expectedPoint == currentRewardPoint);
    }

    public String getExpectedRewardPoint(String phoneNumber, String oldRewardPoint, String addPoint, String usedPointStr) {
        double olddPoint = helper.convertToDouble(oldRewardPoint);
        double receivedPoint = helper.convertToDouble(addPoint);
        double usedPoint = helper.convertToDouble(usedPointStr);
        double expectedPoint = olddPoint + receivedPoint - usedPoint;
        System.out.println(expectedPoint);
        return String.valueOf(expectedPoint);
    }

    public String getEarPoint(String phoneNumber, String totalAmount, String usedPointAmount) {
        double earningPointConfig = Double.parseDouble(calculatePointCanUse("earningPointExchangeValue", phoneNumber));
        double total = helper.convertToDouble(totalAmount);
        double point = helper.convertToDouble(usedPointAmount);
        double receivedPoint = (total + point) / earningPointConfig;
        int p = (int) Math.floor(receivedPoint);
        String s = String.valueOf(p);
        return s;
    }

    public static void main(String[] args) {
        String subtotal = convertSubtotal("123,999");
        String phoneNumber = "942741230";
    }
}

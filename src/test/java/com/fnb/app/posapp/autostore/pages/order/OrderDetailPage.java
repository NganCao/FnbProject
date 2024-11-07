package com.fnb.app.posapp.autostore.pages.order;

import com.fnb.app.posapp.autostore.pages.dashboard.DashboardPage.*;
import com.fnb.app.posapp.autostore.pages.payment.PaymentDataTest;
import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.api.posapp.admin.helpers.JsonAPIAdminReader.*;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDetailPage extends BaseSetup {
    private AndroidDriver driver;
    private WebDriverWait wait;
    private PaymentDataTest paymentDataTest;
    private OrderDataTest orderDataTest;
    public String actualRS;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.TextView")
    private WebElement orderId;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/com.horcrux.svg.SvgView")
    private WebElement backBtn;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Order status:\"]/following-sibling::android.view.ViewGroup/android.widget.TextView")
    private WebElement orderStatus;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Type:\"]/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    private WebElement serviceType;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Platform:\"]/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    private WebElement platformType;
    @FindBy(xpath = "(//android.widget.TextView[@text=\"Created time:\"])[1]/following-sibling::android.widget.TextView")
    private WebElement createTime;
    //items
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[3]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]")
    private WebElement itemFirst;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Order detail\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")
    private List<WebElement> productItemsList;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Order detail\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView")
    private List<WebElement> productNameList;
    //todo improve xpath for multi topping
    @FindBy(xpath = "//android.widget.TextView[@text=\"Order detail\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    private List<WebElement> productToppingList;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Order detail\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView")
    private List<WebElement> productOptionList;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Order detail\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.TextView")
    private List<WebElement> productQuantityList;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Order detail\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.TextView")
    private List<WebElement> productPriceList;
    //customer
    @FindBy(xpath = "//android.widget.TextView[@text=\"Order detail\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView")
    private WebElement customerName;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Order detail\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    private WebElement customerRank;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Order detail\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView")
    private WebElement customerPhone;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Order detail\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView")
    private WebElement customerRankDiscount;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Order detail\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView")
    private WebElement customerReceivedPoint;
    //price
    @FindBy(xpath = "//android.widget.TextView[@text=\"Subtotal\"]/parent::android.view.ViewGroup/following-sibling::android.widget.TextView")
    private WebElement subTotalElement;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Subtotal\"]/following-sibling::android.widget.TextView")
    private WebElement totalItemElement;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Promotion\"]/following-sibling::android.widget.TextView")
    private WebElement promotionAmountElement;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Fee & Tax\"]/following-sibling::android.widget.TextView")
    private WebElement feeTaxElement;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Payment method\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView")
    private WebElement paymentMethodElement;
    @FindBy(xpath = "//android.widget.TextView[contains(@text,\"TOTAL\")]")
    private WebElement totalAmountTitleElement;
    @FindBy(xpath = "//android.widget.TextView[contains(@text,\"TOTAL(\")]/following-sibling::android.widget.TextView")
    private WebElement totalAmountValueElement;
    @FindBy(xpath = "//android.widget.TextView[@text=\"PRINT BILL\"]")
    private WebElement printBillBtn;

    public OrderDetailPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean checkOrderIdHeader(String orderIdStr) {
        String xpath = "(//android.widget.TextView[@text=\"" + orderIdStr + "\"])[2]";
        return helper.checkDisplayElement(By.xpath(xpath));
    }

    //actions
    public void clickPrintButton() {
        helper.waitToElementClick(printBillBtn);
    }

    public void clickBackButton() {
        helper.waitToElementClick(backBtn);
    }

    //format topping
    private String formatToppingName(Topping topping) {
        return topping.getQuantity() + " x " + topping.getName();
    }

    public void showPCList(List<ProductCart> productCartList) {
        for (ProductCart pc : productCartList) {
            System.out.println(pc.toString());
        }
    }

    public ProductCart getProductCart(String name, String size, float totalValue, int quantity, String toppingName, int toppingQuantity, String optionStr) {
        ProductCart productCart = new ProductCart();
        productCart.setName(name);
        productCart.setPrice(totalValue);
        productCart.setQuantity(quantity);
        productCart.setTopping(toppingName);
        productCart.setToppingQuantity(toppingQuantity);
        productCart.setOption(optionStr);
        return productCart;
    }

    public ProductCart getProductItemsUiList(int index) {
        int toppingQuantityValue = 0;
        String productName = "", productQuantity = "", productPrice = "", toppingName = "", option = "", quantity = "", toppingQuantity = "", productNameFormatted = "", quantityFormatted = "", sizeFormatted = "", toppingNameFormatted = "", toppingQuantityFormatted = "";
        //get Cart item on UI
        if (productName.contains("(") && productName.contains(")")) {
            productNameFormatted = productName.substring(0, productName.indexOf("(") - 1);
            sizeFormatted = productName.substring(productName.indexOf("(") + 1, productName.indexOf(")")).trim();
        } else {
            sizeFormatted = "";
            productNameFormatted = productName;
        }
        productQuantity = productQuantityList.get(index).getText();
        quantityFormatted = productQuantity.replace("x", "").trim();
        productPrice = productPriceList.get(index).getText();
        try {
            toppingName = productToppingList.get(index).getText();
            toppingNameFormatted = toppingName.substring(toppingName.indexOf("x") + 1).trim();
            toppingQuantity = toppingName.substring(0, toppingName.indexOf("x")).trim();
            toppingQuantityValue = Integer.parseInt(toppingQuantity);
        } catch (Exception e) {
            Log.info(e.getMessage());
            toppingNameFormatted = "";
            toppingQuantityValue = 0;
        }
        try {
            option = productOptionList.get(index).getText();
        } catch (Exception e) {
            Log.info(e.getMessage());
            option = "";
        }
        return getProductCart(productNameFormatted, sizeFormatted, helper.convertToFloat(productPrice), Integer.parseInt(quantityFormatted), toppingNameFormatted, toppingQuantityValue, option);
    }

    private Boolean checkItemListWithEachProductCart(List<ProductCart> productCartList) {
        int size = productItemsList.size();
        List<Boolean> flag = new ArrayList<>();
        ProductCart productCart;
        int totalQuantityItems = 0;
        float subtotalUI = 0, subtotalCart = 0, subtotal = 0;
        for (int i = 0; i < size; i++) {
            productCart = getProductItemsUiList(i);
            totalQuantityItems = totalQuantityItems + productCart.getQuantity();
            System.out.println("totalQuantityItems " + totalQuantityItems);
            subtotalCart = subtotalCart + productCart.getPrice();
            for (ProductCart pc : productCartList) {
                subtotalUI = subtotalUI + pc.getPrice();
                if (pc.getName().equalsIgnoreCase(productCart.getName())) {
                    if (pc.getSize().equalsIgnoreCase(productCart.getSize())) {
                        if (pc.getTopping().equalsIgnoreCase(productCart.getTopping())) {
                            if (pc.getToppingQuantity() == productCart.getToppingQuantity()) {
                                if (pc.getOption().equalsIgnoreCase(productCart.getOption())) {
                                    subtotal = subtotal + productCartList.get(i).getPrice();
                                    flag.add(helper.checkValueEquals("price", productCart.getPrice(), pc.getPrice()));
                                    actualRS = actualRS + helper.actualRS;
                                    flag.add(helper.checkValueEquals(pc.toString() + "\nsubtotal", pc.getQuantity(), productCart.getQuantity()));
                                    actualRS = actualRS + helper.actualRS;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return !flag.contains(false);
    }

    //todo check price
    public Boolean checkOrderDetail(List<ProductCart> productCartList, Map<String, String> priceDetail, int quantityTotal, String status, String paymentMethod, Map<String, String> paymentConfig) {
        List<Boolean> flag = new ArrayList<>();
        int size = productItemsList.size();
        actualRS = "Check order detail: \n";
        //check the number of items are displaying
        flag.add(helper.checkValueEquals("size", size, productCartList.size()));
        actualRS = actualRS + helper.actualRS;
        //check item information
        flag.add(checkItemListWithEachProductCart(productCartList));
        //check subtotal
        flag.add(checkValueOfSubtotal(priceDetail.get("subtotal")));
        actualRS = actualRS + helper.actualRS;
        System.out.println("111ádcg");
        flag.add(checkValueOfTotal(priceDetail.get("total")));
        actualRS = actualRS + helper.actualRS;
        //check order status
        flag.add(checkValueOfOrderStatus(status));
        actualRS = actualRS + helper.actualRS;
        //check created time
        //check total quantity
        flag.add(checkValueOfTotalQuantity(quantityTotal));
        actualRS = actualRS + helper.actualRS;
        //check promotion
        flag.add(checkValueOfPromotion(priceDetail.get("promotion")));
        actualRS = actualRS + helper.actualRS;
        //check fee
        //check Payment method
        flag.add(helper.checkText(paymentMethodElement, paymentMethod));
        actualRS = actualRS + helper.actualRS;
        //check paid status follow payment and kitchen
        flag.add(checkPayStatusOfOrder(status, paymentConfig));
        return !flag.contains(false);
    }

    public Boolean checkValueOfOrderStatus(String status) {
        return helper.checkText(orderStatus, status);
    }

    public Boolean checkValueOfSubtotal(String expectedSubTotal) {
        return helper.checkText(subTotalElement, expectedSubTotal);
    }

    public Boolean checkValueOfTotal(String expectedTotal) {
        System.out.println("totalAmountValueElement " + totalAmountValueElement.getText());
        return helper.checkText(totalAmountValueElement, expectedTotal);
    }

    public Boolean checkValueOfPromotion(String expectedPromotion) {
        System.out.println("expectedPromotion " + promotionAmountElement.getText());
        return helper.checkText(promotionAmountElement, expectedPromotion);
    }

    public Boolean checkValueOfTotalQuantity(int quantityTotal) {
        String quantityTxt = totalItemElement.getText().trim();
        System.out.println("quantityTxt \"" + quantityTxt + "\"");
        String totalQuantityFormatted = "(" + quantityTotal + " Items)";
        System.out.println("totalQuantityFormatted \"" + totalQuantityFormatted + "\"");
        if (helper.checkValueEquals("total Quantity", quantityTxt, totalQuantityFormatted)) return true;
        else return false;
    }

    public Boolean checkPayStatusOfOrder(String status, Map<String, String> paymentConfig) {
        List<Boolean> flag = new ArrayList<>();
        String totalTxt = totalAmountTitleElement.getText();
        String paidStatus = totalTxt.substring(totalTxt.indexOf("(") + 1, totalTxt.indexOf(")"));
        System.out.println(paidStatus);
        if (status.toLowerCase().contains("complete")) {
            if (paymentConfig.get(paymentDataTest.PAYMENT_METHOD_KEY).equalsIgnoreCase("first")) {
                if (paidStatus.equalsIgnoreCase(orderDataTest.PAID)) {
                    flag.add(true);
                } else {
                    flag.add(false);
                    actualRS = actualRS + paymentConfig.get(paymentDataTest.PAYMENT_METHOD_KEY) + "/" + paymentConfig.get(paymentDataTest.KITCHEN_KEY) + "\n" + "Wrong subtotal. Actual: " + paidStatus + " Expected: " + orderDataTest.PAID + "\n";
                }
            } else {
                if (paymentConfig.get(paymentDataTest.KITCHEN_KEY).equalsIgnoreCase("no")) {
                    if (paidStatus.equalsIgnoreCase(orderDataTest.UNPAID)) {
                        flag.add(true);
                    } else {
                        flag.add(false);
                        actualRS = actualRS + paymentConfig.get(paymentDataTest.PAYMENT_METHOD_KEY) + "/" + paymentConfig.get(paymentDataTest.KITCHEN_KEY) + "\n" + "Wrong subtotal. Actual: " + paidStatus + " Expected: " + orderDataTest.UNPAID + "\n";
                    }
                }
            }
        }
        return !flag.contains(false);
    }

    public Boolean checkCustomerInfo(String expectedCustomerName, String expectedPhoneNumber, String expectedRankName, String expectedRankDiscount, String expectedReceivedPoint) {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        flag.add(helper.checkText(customerName, expectedCustomerName));
        actualRS = actualRS + helper.actualRS + "\n";
        flag.add(helper.checkText(customerRank, expectedRankName));
        actualRS = actualRS + helper.actualRS + "\n";
        flag.add(helper.checkText(customerPhone, expectedPhoneNumber));
        actualRS = actualRS + helper.actualRS + "\n";
        flag.add(helper.checkText(customerRankDiscount, expectedRankDiscount));
        actualRS = actualRS + helper.actualRS + "\n";
        //La POP Chi Chi have received 123 points for this order.
        String hasReceivedPointStr = expectedCustomerName + " have received " + expectedReceivedPoint + " points for this order.";
        flag.add(helper.checkText(customerReceivedPoint, hasReceivedPointStr));
        actualRS = actualRS + helper.actualRS + "\n";
        return !flag.contains(false);
    }

    public static class Customer {
        private String name;
        private String phone;
        private String rankName;
        private String rankDiscount;
        private String receivedPoint;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRankName() {
            return rankName;
        }

        public void setRankName(String rankName) {
            this.rankName = rankName;
        }

        public String getRankDiscount() {
            return rankDiscount;
        }

        public void setRankDiscount(String rankDiscount) {
            this.rankDiscount = rankDiscount;
        }

        public String getReceivedPoint() {
            return receivedPoint;
        }

        public void setReceivedPoint(String receivedPointMsg) {
            this.receivedPoint = receivedPointMsg;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", rankName='" + rankName + '\'' +
                    ", rankDiscount='" + rankDiscount + '\'' +
                    ", receivedPoint='" + receivedPoint + '\'' +
                    '}';
        }
    }
}

package com.fnb.app.posapp.autostore.pages.payment;

import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PaymentDialogPage extends BaseSetup {
    private AndroidDriver driver;
    private PaymentDataTest paymentDataTest;
    public String orderId;
    public String actualRS;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Checkout order\"]")
    private WebElement checkoutOrderHeading;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Checkout order\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView")
    private WebElement closeDialog;
    //payment method
    @FindBy(xpath = "//android.widget.TextView[@text=\"Cash\"]")
    private WebElement cash;
    @FindBy(xpath = "//android.widget.TextView[@text=\"MoMo\"]")
    private WebElement moMo;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Bank transfer\"]")
    private WebElement bankTransfer;
    //price
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]")
    private WebElement priceInput;
    //Cash
    @FindBy(xpath = "//android.widget.TextView[@text=\"Order No.\"]/following-sibling::android.widget.TextView")
    private WebElement orderNo;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Received money\"]/following-sibling::android.widget.TextView")
    private WebElement receivedMoney;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Pay\"]/following-sibling::android.widget.TextView")
    private WebElement payAmount;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Change\"]/following-sibling::android.widget.TextView")
    private WebElement changeAmount;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"0, Pay\"]")
    private WebElement payBtn;
    //Bank transfer
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Received money\"]")
    private WebElement receivedMoneyBtn;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Personal methods\"]")
    private WebElement personalPaymentTitle;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Personal methods\"]/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")
    private List<WebElement> paymentPersonalList;
    //rêcived money

    public PaymentDialogPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //check
    public Boolean checkDisplayOfReceivedMoneyCash() {
        return helper.checkElementDisplay(receivedMoney);
    }

    public Boolean checkDisplayOfPayCash() {
        return helper.checkElementDisplay(payAmount);
    }

    //cash
    public Boolean checkReceiveMoneyAmountCash(String amount) {
        if (checkDisplayOfReceivedMoneyCash()) {
            actualRS = "Actual: " + receivedMoney.getText() + " Expected: " + amount;
            return helper.checkText(receivedMoney, amount);
        } else {
            actualRS = "Received money did not display with CASH";
            return false;
        }
    }

    public Boolean checkPayAmountCash(String amount) {
        if (checkDisplayOfReceivedMoneyCash()) {
            actualRS = "Actual: " + payAmount.getText() + " Expected: " + amount;
            return helper.checkText(payAmount, amount);
        } else {
            actualRS = "Pay money did not display with CASH";
            return false;
        }
    }

    public String getOrderId() {
        if (helper.checkElementDisplay(orderNo)) {
            orderId = "#" + orderNo.getText().trim();
            return orderId;
        } else {
            actualRS = "order number did not display.";
            return "";
        }
    }

    //actions
    public void clickPayBtnWithCash() {
        helper.waitToElementClick(cash);
        helper.waitToElementClick(payBtn);
    }

    public void clickPayBtnWithBankTransfer() {
        helper.waitToElementClick(bankTransfer);
        helper.waitToElementClick(receivedMoneyBtn);
    }

    public void clickClosePaymentDialog() {
        try {
            helper.clickBtn(closeDialog);
        } catch (Exception exception) {
            Log.error("Can not click close btn. \n" + exception.getMessage());
        }
    }
}

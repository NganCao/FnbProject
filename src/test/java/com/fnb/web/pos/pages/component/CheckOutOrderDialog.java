package com.fnb.web.pos.pages.component;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.Header;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.admin.pages.configuration.ConfigurationPage;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckOutOrderDialog extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public SiderBar siderBar;
    private Header header;
    private ConfigurationPage configurationPage;

    public CheckOutOrderDialog(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        siderBar = new SiderBar(driver);
        header = new Header(driver);
        configurationPage = new ConfigurationPage(driver);
    }
    private By btnCheckOut = By.cssSelector(".btn-pay-box");
    private By paymentSuccessDialog = By.xpath("//div[contains(@class, 'payment-success-modal')]");
    private By btnNewOrder = By.cssSelector(".btn-new-order");
    private By iconClose = By.xpath("//button[contains(@class, 'close')]");

    public void clickPay() {
        helper.smartWait();
        helper.clickElement(btnCheckOut);
        helper.waitForElementVisible(paymentSuccessDialog);
        helper.clickElement(btnNewOrder);
    }

    public void clickCloseIcon() {
        helper.clickElement(iconClose);
    }

    public enum paymentMethod {
        Cash("Cash"),
        BankTransfer("Bank transfer"),
        mPOS("mPOS");

        private final String displayName;
        paymentMethod(String displayName) {
            this.displayName = displayName;
        }
        public String getDisplayName() {
            return displayName;
        }
    }

    public CheckOutOrderDialog selectPaymentMethod(paymentMethod paymentMethod) {
        By paymentMethodElement = By.xpath("//*[contains(@class, 'payment-method-row')]//*[text()='"+paymentMethod.getDisplayName()+"']");
        helper.clickElement(paymentMethodElement);
        return this;
    }

    public String getOrderID() {
        String orderIdText = helper.getText(By.xpath("(//*[contains(text(), '#')])[last()]"));
        return orderIdText.replace("#", "");
    }

    public void clickReceivedMoney() {
        helper.clickElement(By.cssSelector(".btn-bank-transfer"));
        helper.waitForElementVisible(paymentSuccessDialog);
        helper.clickElement(btnNewOrder);
    }
}

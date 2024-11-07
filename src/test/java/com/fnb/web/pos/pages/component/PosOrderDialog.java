package com.fnb.web.pos.pages.component;

import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

@Data
public class PosOrderDialog extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public PosOrderDialog(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    private By tabToConfirm = By.xpath("//label[contains(@class, 'ant-segmented-item') and normalize-space()='To confirm']");
    private By tabServing = By.xpath("//label[contains(@class, 'ant-segmented-item') and normalize-space()='Serving']");
    private By tabCompleted = By.xpath("//label[contains(@class, 'ant-segmented-item') and normalize-space()='Completed']");
    private By tabCanceled = By.xpath("//label[contains(@class, 'ant-segmented-item') and normalize-space()='Canceled']");
    private By tabDraft = By.xpath("//label[contains(@class, 'ant-segmented-item') and normalize-space()='Draft']");

    public String getOrderCard(String orderId) {
        String locator = "//div[contains(@class, 'ant-col-xs-24') and contains(normalize-space(), '"+orderId+"')]";
        return locator;
    }

    public CheckOutOrderDialog clickPay(String orderId) {
        helper.clickElement(By.xpath(getOrderCard(orderId) + "//button[normalize-space()]"));
        return new CheckOutOrderDialog(driver);
    }

    public PosOrderDialog clickComplete(String orderID) {
        // Click the first order to complete
        helper.clickElement(By.xpath("(//*[.//*[text()='Complete'] and .//*[text()='"+orderID+"']])[last()]//button"));
        // Wait success toast message
        helper.isElementVisible(By.xpath("//*[text()='"+posLocalization.getToastMessage().getCompleteOrderSuccess()+"']"));
        return this;
    }

    public String getOrderID() {
        // Get the first orderID
        return helper.getText(By.xpath("(//*[text()='#'])[1]")).substring(1);
    }
}

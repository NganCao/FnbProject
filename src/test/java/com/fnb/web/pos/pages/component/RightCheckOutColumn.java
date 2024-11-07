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
public class RightCheckOutColumn extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public RightCheckOutColumn(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    private By btnCreateOrder = By.xpath("//button[contains(@class, 'btn-payment-order')]");
    private By successDialog = By.xpath("//div[contains(@class, 'ant-message-custom-content')]");
    public void clickCreateOrder_PayLater() {
        helper.clickElement(btnCreateOrder);
        helper.waitForElementVisible(successDialog);
        helper.waitElementIsRemoved(successDialog);
    }

    public CheckOutOrderDialog clickCreateOrder_PayFirst() {
        helper.clickElement(btnCreateOrder);
        return new CheckOutOrderDialog(driver);
    }

    public Double extractNumberFromString(String text) {
        String numericString = text.replaceAll("[^\\d]", "");
        Double number = Double.parseDouble(numericString);
        return  number;
    }

    public Double getSubtotalAmount() {
        String stringSubTotal = helper.getText(By.cssSelector(".price-subtotal"));
        return extractNumberFromString(stringSubTotal);
    }

    public Double getTotalAmountOfItem(String productName, String priceName) {
        String stringTotalAmountItem;
        if (priceName.equals("")) {
            stringTotalAmountItem = helper.getText(By.xpath("//div[contains(@class, 'content-product-item') and .//*[text()='"+productName+"']]//*[contains(@class, 'currency-product-item')]"));
        }
        else {
            stringTotalAmountItem = helper.getText(By.xpath("//div[contains(@class, 'content-product-item') and .//*[text()='"+productName+"'] and .//*[text()='"+priceName+"']]//*[contains(@class, 'currency-product-item')]"));
        }
        return extractNumberFromString(stringTotalAmountItem);
    }

    public String getCustomerRank() {
        return helper.getText(By.cssSelector(".group-customer-rank"));
    }

    public RightCheckOutColumn clickDiscount_ArrowIcon() {
        helper.clickElement(By.xpath("//*[contains(@class, 'text-discount')]//*[name()='svg' and contains(@class, 'arrow-icon')]"));
        return this;
    }

    public Double getPromotionValueCustomer() {
        return extractNumberFromString(helper.getText(By.cssSelector(".promotion-value-custom")));
    }

    public RightCheckOutColumn decreaseItem(String productName, String priceName, int quantity) {
        if (priceName.equals("")) {
            By iconMinus = By.xpath("//div[contains(@class, 'content-product-item') and .//*[text()='"+productName+"']]//button[contains(@class, 'decrease')]");
            for (int i=0; i < quantity ;  i++) {
                helper.sleep(2);
                helper.clickElement(iconMinus);
                helper.sleep(2);
            }
        }
        else {
            By iconMinus = By.xpath("//div[contains(@class, 'content-product-item') and .//*[text()='"+productName+"'] and .//*[text()='"+priceName+"']]//button[contains(@class, 'decrease')]");
            for (int i=0; i < quantity ;  i++) {
                helper.sleep(2);
                helper.clickElement(iconMinus);
                helper.sleep(2);
            }
        }
        return this;
    }

    public RightCheckOutColumn increaseItem(String productName, String priceName, int quantity) {
        if (priceName.equals("")) {
            By iconPlus = By.xpath("//div[contains(@class, 'content-product-item') and .//*[text()='"+productName+"']]//button[contains(@class, 'increase')]");
            for (int i=0; i < quantity ;  i++) {
                helper.sleep(2);
                helper.clickElement(iconPlus);
                helper.sleep(2);
            }
        }
        else {
            By iconPlus = By.xpath("//div[contains(@class, 'content-product-item') and .//*[text()='"+productName+"'] and .//*[text()='"+priceName+"']]//button[contains(@class, 'increase')]");
            for (int i=0; i < quantity ;  i++) {
                helper.sleep(2);
                helper.clickElement(iconPlus);
                helper.sleep(2);
            }
        }

        return this;
    }

    public Double getTotal() {
        String cleanedString = helper.getText(By.cssSelector(".price-total")).replace("Đ", "").trim();
        // Now extract the number
        String[] parts = cleanedString.split(" ");
        String numberString = parts[parts.length - 1];
        return helper.convertStringToDouble(numberString);
    }

    public Double getTaxValue() {
        String originalString =  helper.getText(By.xpath("//*[contains(@class, 'text-tax')]/following-sibling::div"));
        String cleanedString = originalString.replaceAll("[+đ]", "").trim();

        // Now extract the number
        String[] parts = cleanedString.split(" ");
        String numberString = parts[parts.length - 1];
        return helper.convertStringToDouble(numberString);
    }
}

package com.fnb.web.admin.pages.store.promotion;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import dataObject.Product.Product;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CreateFlashSaleCampaignPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;

    public CreateFlashSaleCampaignPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    private By txtCampaignName = By.xpath("//input[@id='flashSale_name']");
    private By txtStartDate = By.xpath("//input[@id='flashSale_startDate']");
    private By txtStartTime = By.xpath("//input[@id='flashSale_startTime']");
    private By txtEndTime = By.xpath("//input[@id='flashSale_endTime']");
    private By txtTermsAndConditions = By.xpath("//textarea[@id='text-area-scroll']");
    private By selSearchProduct = By.xpath("//input[./ancestor::*[contains(@class, 'selection-search')]]");

    public void enterCampaignName(String campaignName) {
        helper.enterText(txtCampaignName, campaignName);
    }

    public void enterTermsAndConditions(String termAndCondition) {
        helper.enterText(txtTermsAndConditions, termAndCondition);
    }

    public void selectStartDate(String startDate) {
        helper.enterText(txtStartDate, startDate);
        helper.getWebElement(txtStartDate).sendKeys(Keys.ENTER);
    }

    public void selectStartTime(String startTime) {
        helper.enterText(txtStartTime, startTime);
        helper.getWebElement(txtStartTime).sendKeys(Keys.ENTER);
    }

    public void selectEndTime(String endTime) {
        helper.enterText(txtEndTime, endTime);
        helper.getWebElement(txtEndTime).sendKeys(Keys.ENTER);
    }

    @Data
    public static class Product {
        private String productName;
        private String productPrice;
        private String flashSalePrice;
        private String flashSaleQuantity;

        // Constructor
        public Product(String productName, String productPrice, String flashSalePrice, String flashSaleQuantity) {
            this.productName = productName;
            this.productPrice = productPrice;
            this.flashSalePrice = flashSalePrice;
            this.flashSaleQuantity = flashSaleQuantity;
        }
    }
    // If the products have multiple price
    // For example "Cafe sữa" have three size S M L
    // We choose size S so the xpath will be: //div[@title='Cafe sữa Auto' and text()='S']
    public void selectProduct(Product[] products) {
        for (int i=0; i < products.length; i++) {
            String productName = products[i].getProductName();
            String productPrice = products[i].getProductPrice();

            helper.getWebElement(selSearchProduct).sendKeys(productName);
            helper.clickElement(By.xpath("//div[@title='"+productName+"' and text()='"+productPrice+"']"));
        }
        helper.getWebElement(selSearchProduct).sendKeys(Keys.ESCAPE);
    }

    public String getTableData(String productName) {
        return "//div[normalize-space()='"+productName+"' and @class='column-flash-sale-name']/ancestor::tr";
    }
    public void enterFlashSalePrice(Product[] products) {
        for (int i=0; i < products.length; i++) {
            String productName = products[i].getProductName();
            if (!products[i].getProductName().equals(products[i].getProductPrice())) {
                productName = products[i].getProductName() + " - " + products[i].getProductPrice();
            }
            By productRow = By.xpath(""+getTableData(productName)+"//input[contains(@id, 'flashSalePrice')]");

            helper.scrollToElementAtMiddle(productRow);
            helper.enterText(productRow, products[i].flashSalePrice);
        }
    }

    public void enterFlashSaleQuantity(Product[] products) {
        for (int i=0; i < products.length; i++) {
            String productName = products[i].getProductName();
            if (!products[i].getProductName().equals(products[i].getProductPrice())) {
                productName = products[i].getProductName() + " - " + products[i].getProductPrice();
            }
            By productRow = By.xpath(""+getTableData(productName)+"//input[contains(@id, 'flashSaleQuantity')]");

            helper.scrollToElementAtMiddle(productRow);
            helper.enterText(productRow, products[i].flashSaleQuantity);
        }
    }

    public void enterMaximumLimit(Product[] products) {
        for (int i=0; i < products.length; i++) {

        }
    }

    public void selectCouponConditions() {

    }

    public void clickSave() {
        helper.scrollToElementAtBottom(commonComponents.getBtnSave());
        helper.clickElement(commonComponents.getBtnSave());
        commonComponents.waitSuccessToast();
        commonComponents.waitSuccessToastHidden();
    }

    public String createAFlashSaleCampaign(String campaignName, String termsAndConditions, String startDate, String startTime, String endTime, Product[] products) {
        enterCampaignName(campaignName);
        enterTermsAndConditions(termsAndConditions);
        selectStartDate(startDate);
        selectStartTime(startTime);
        selectEndTime(endTime);
        selectProduct(products);
        enterFlashSalePrice(products);
        enterFlashSaleQuantity(products);
        clickSave();
        return campaignName;
    }
}

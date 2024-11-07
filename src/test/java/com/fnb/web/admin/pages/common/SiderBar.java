package com.fnb.web.admin.pages.common;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Data
public class SiderBar extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;

    public SiderBar(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    private By selectedItems = By.xpath("//li[contains(@class, 'selected')]");
    private By mnuItemHome = By.xpath("//li[contains(@data-menu-id, 'home')]//span");
    private By mnuItemProduct = By.xpath("//div[contains(@data-menu-id, 'product')]");
    private By mnuItemInventory = By.xpath("//div[contains(@data-menu-id, 'inventory')]");
    private By mnuItemCombo = By.xpath("//li[contains(@data-menu-id, 'combo')]");
    private By mnuItemOption = By.xpath("//li[contains(@data-menu-id, 'option')]");
    private By mnuItemIngredients = By.xpath("//li[contains(@data-menu-id, 'inventory.material') and not(contains(@data-menu-id, 'material-'))]");
    private By mnuItemReport = By.xpath("//div[contains(@data-menu-id, 'report')]");
    private By mnuItemRevenue = By.xpath("//li[contains(@data-menu-id, 'revenue') and not(contains(@data-menu-id, 'staffrevenue'))]");
    private By mnuItemTransaction = By.xpath("//li[contains(@data-menu-id, 'transaction')]");
    private By mnuItemCustomer = By.xpath("//li[contains(@data-menu-id, 'customer')]");
    private By mnuItemInventoryHistory = By.xpath("//li[contains(@data-menu-id, 'inventory-history')]");
    private By mnuItemProductCategory = By.xpath("//li[contains(@data-menu-id, 'product.category')]");
    private By mnuItemMaterialCategory = By.xpath("//li[contains(@data-menu-id, 'material-category')]");
    private By mnuItemSupplier = By.xpath("//li[contains(@data-menu-id, 'supplier')]");
    private By mnuItemInventoryControl = By.xpath("//li[contains(@data-menu-id, 'control')]");
    private By mnuItemManagement = By.xpath("//li[contains(@data-menu-id, 'management')]");
    private By mnuItemCRM = By.xpath("//*[contains(@data-menu-id, 'crm')]");
    private By mnuItemCRM_Customer = By.xpath("//*[contains(@data-menu-id, 'customer.management')]");
    private By mnuItemCustomerSegment = By.xpath("//*[contains(@data-menu-id, 'segment')]");
    private By mnuItemMembership = By.xpath("//*[contains(@data-menu-id, 'membership')]");
    private By mnuItemPointConfiguration = By.xpath("//*[contains(@data-menu-id, 'point')]");
    private By mnuItemAreaTable = By.xpath("//*[contains(@data-menu-id, 'area-table')]");
    private By mnuItemStore = By.xpath("//*[contains(@data-menu-id, 'store') and not(contains(@data-menu-id, '.store.'))]");
    private By mnuItemPromotion = By.xpath("//*[contains(@data-menu-id, 'promotion')]");
    private By mnuItemFeeAndTax = By.xpath("//*[contains(@data-menu-id, 'fee-tax')]");
    private By mnuItemMarketing = By.xpath("(//*[contains(@data-menu-id, 'marketing')])[1]");
    private By mnuItemQrOder = By.xpath("//*[contains(@data-menu-id, 'qrOrder')]");
    private By mnuItemEmailCampaign = By.xpath("//*[contains(@data-menu-id, 'emailCampaign')]");
    private By mnuItemNotificationCampaign = By.xpath("//*[contains(@data-menu-id, 'notificationCampaign')]");
    private By btnConfiguration = By.xpath("//span[@class='title-setting']");
    private String expandElementString = "//*[contains(@class, 'expand')]";

    public void clickButtonConfiguration() {
        helper.clickElement(btnConfiguration);
    }

    public void click(By by, int expectedNumberOfElements, String message, int retries) {
        boolean conditionMet = false;
        for (int attempt = 0; attempt < retries; attempt++) {
            try {
                helper.clickElement(by);
                wait.until(ExpectedConditions.numberOfElementsToBe(selectedItems, expectedNumberOfElements));
                conditionMet = true;
                break; // Condition met, exit the loop
            } catch (Exception e) {
                // Log the exception or retry message if necessary
                System.out.println("Attempt " + (attempt + 1) + " failed, retrying...");
            }
        }
        if (!conditionMet) {
            Assert.fail(message);
        }
    }

    public SiderBar clickMnuItemProduct() {
        String expandElementString = helper.getTheXpathStringFromBy(mnuItemProduct) + getExpandElementString();
        if (!helper.isElementVisible(By.xpath(expandElementString))) {
            click(mnuItemProduct, 2, "Can not see the Product menu", 3);
        }
        return this;
    }

    public SiderBar clickMnuItemStore() {
        String expandElementString = helper.getTheXpathStringFromBy(mnuItemStore) + getExpandElementString();
        if (!helper.isElementVisible(By.xpath(expandElementString))) {
            click(mnuItemStore, 2, "Can not see the Store menu", 3);

        }
        return this;
    }

    public SiderBar clickMnuItemInventory() {
        String expandElementString = helper.getTheXpathStringFromBy(mnuItemInventory) + getExpandElementString();
        if (!helper.isElementVisible(By.xpath(expandElementString))) {
            click(mnuItemInventory, 2, "Can not see the Inventory menu", 3);
        }
        return this;
    }

    public SiderBar clickComboMenu() {
        helper.clickElement(mnuItemCombo);
        helper.waitForNumberOfElements(selectedItems, 2);
        return this;
    }

    public SiderBar clickOptionMenu() {
        helper.clickElement(mnuItemOption);
        helper.waitForNumberOfElements(selectedItems, 2);
        return this;
    }

    public SiderBar clickIngredientsMenu() {
        helper.clickElement(mnuItemIngredients);
        helper.waitForNumberOfElements(selectedItems, 2);
        return this;
    }

    public SiderBar clickReportMenu() {
        String expandElementString = helper.getTheXpathStringFromBy(mnuItemReport) + getExpandElementString();
        if (!helper.isElementVisible(By.xpath(expandElementString))) {
            click(mnuItemReport, 2, "Can not see the Report menu", 3);

        }
        return this;
    }

    public SiderBar clickRevenueMenu() {
        helper.clickElement(mnuItemRevenue);
        helper.waitForNumberOfElements(selectedItems, 2);
        return this;
    }

    public SiderBar clickTransactionMenu() {
        helper.clickElement(mnuItemTransaction);
        helper.waitForNumberOfElements(selectedItems, 2);
        return this;
    }

    public SiderBar clickCustomerMenu() {
        helper.clickElement(mnuItemCustomer);
        helper.waitForNumberOfElements(selectedItems, 2);
        return this;
    }

    public SiderBar clickInventoryHistory() {
        helper.clickElement(mnuItemInventoryHistory);
        helper.waitForNumberOfElements(selectedItems, 2);
        return this;
    }

    public SiderBar clickProductCategory() {
        helper.clickElement(mnuItemProductCategory);
        helper.waitForNumberOfElements(selectedItems, 2);
        return this;
    }

    public SiderBar clickMaterialCategory() {
        helper.clickElement(mnuItemMaterialCategory);
        helper.waitForNumberOfElements(selectedItems, 2);
        return this;
    }

    public SiderBar clickSupplier() {
        helper.clickElement(mnuItemSupplier);
        helper.waitForNumberOfElements(selectedItems, 2);
        return this;
    }

    public SiderBar clickInventoryControl() {
        helper.clickElement(mnuItemInventoryControl);
        helper.waitForNumberOfElements(selectedItems, 2);
        return this;
    }

    public SiderBar clickHome() {
        helper.clickElement(mnuItemHome);
        return this;
    }

    public SiderBar clickManagement() {
        helper.clickElement(mnuItemManagement);
        helper.waitForNumberOfElements(selectedItems, 2);
        return this;
    }

    public SiderBar clickMnuCRM() {
        String expandElementString = helper.getTheXpathStringFromBy(mnuItemCRM) + getExpandElementString();
        if (!helper.isElementVisible(By.xpath(expandElementString))) {
            click(mnuItemCRM, 2, "Can not see the CRM menu", 3);

        }
        return this;
    }

    public SiderBar clickCustomer() {
        helper.clickElement(mnuItemCRM_Customer);
        return this;
    }

    public SiderBar clickCustomerSegment() {
        helper.clickElement(mnuItemCustomerSegment);
        return this;
    }

    public SiderBar clickMembership() {
        helper.clickElement(mnuItemMembership);
        return this;
    }

    public SiderBar clickPointConfiguration() {
        helper.clickElement(mnuItemPointConfiguration);
        return this;
    }

    public SiderBar clickAreaTable() {
        helper.clickElement(mnuItemAreaTable);
        return this;
    }

    public SiderBar clickPromotion() {
        helper.clickElement(mnuItemPromotion);
        return this;
    }

    public SiderBar clickFeeAndTax() {
        helper.clickElement(mnuItemFeeAndTax);
        return this;
    }

    public SiderBar clickMnuItemMarketing() {
        String expandElementString = helper.getTheXpathStringFromBy(mnuItemMarketing) + getExpandElementString();
        if (!helper.isElementVisible(By.xpath(expandElementString))) {
            click(mnuItemMarketing, 2, "Can not see the Marketing menu", 3);
        }
        return this;
    }

    public SiderBar clickQrOrder() {
        helper.clickElement(mnuItemQrOder);
        return this;
    }

    public SiderBar clickEmailCampaign() {
        helper.clickElement(mnuItemEmailCampaign);
        return this;
    }

    public SiderBar clickNotificationCampaign() {
        helper.clickElement(mnuItemNotificationCampaign);
        return this;
    }
}

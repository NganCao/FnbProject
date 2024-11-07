package com.fnb.web.admin.pages.inventory.inventory_control;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.Header;
import com.fnb.web.admin.pages.inventory.inventory_control.purchase_order.PurchaseOrderManagementPage;
import com.fnb.web.admin.pages.inventory.inventory_control.transfer_material.TransferMaterialManagementPage;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Data
public class InventoryControlPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public Header header;
    public CommonComponents commonComponents;

    public InventoryControlPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        header = new Header(driver);
        commonComponents = new CommonComponents(driver);
        this.driver = driver;
    }

    private By tabPurchaseOrder = By.xpath("//*[contains(@id, 'purchase-order') and @role='tab']");
    private By tabTransferMaterial = By.xpath("//*[contains(@id, 'transfer-material') and @role='tab']");
    private By tabUnavailableIngredients = By.xpath("");
    private By titleTransferIngredients = By.xpath("//*[text()='"+adminLocalization.getTransferMaterial().getTitle()+"']");
    private By titlePurchaseOrders = By.xpath("//*[text()='"+adminLocalization.getPurchaseOrder().getPurchaseOrderManagement()+"']");

    public PurchaseOrderManagementPage clickPurchaseOrderTab() {
        helper.clickElement(tabPurchaseOrder);
        helper.waitForElementVisible(titlePurchaseOrders, "The purchase orders title is not visible");
        return new PurchaseOrderManagementPage(driver);
    }

    public TransferMaterialManagementPage clickTransferMaterialTab() {
        helper.clickElement(tabTransferMaterial);
        helper.waitForElementVisible(titleTransferIngredients, "The transfer ingredient title is not visible");
        return new TransferMaterialManagementPage(driver);
    }
}

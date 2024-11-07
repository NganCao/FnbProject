package com.fnb.web.admin.pages.inventory.inventory_control.purchase_order;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.Header;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Data
public class PurchaseOrderManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public Header header;
    public SiderBar siderBar;

    public PurchaseOrderManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        header = new Header(driver);
        siderBar = new SiderBar(driver);
        this.driver = driver;
    }

    private By btnAddNew = By.xpath("//*[text()='"+adminLocalization.getPurchaseOrder().getPurchaseOrderManagement()+"']/following::*[text()='"+adminLocalization.getButton().getAddNew()+"'][1]");

    public CreatePurchaseOrderPage clickBtnAddNew() {
        helper.clickElement(btnAddNew);
        return new CreatePurchaseOrderPage(driver);
    }

    public void clickTheFirstPurchaseOrder() {
        helper.clickElement(By.xpath("(//tr)[3]"));
        helper.waitForURLContains("detail-purchase-order");
    }
}

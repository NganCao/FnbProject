package com.fnb.web.admin.pages.inventory.inventory_control.purchase_order;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.Header;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
@Data
public class CreatePurchaseOrderPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public Header header;
    public CommonComponents commonComponents;
    private By selSearchSupplier() {
        return commonComponents.getSelectionSearch(ElementData.Supplier_PlaceHolder);
    }
    private By selSearchDestination() {
        return commonComponents.getSelectionSearch(ElementData.Destination_PlaceHolder);
    }

    public CreatePurchaseOrderPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        header = new Header(driver);
        commonComponents = new CommonComponents(driver);
        this.driver = driver;
    }
    By selSearchMaterialInformation = By.xpath("//*[text()='"+adminLocalization.getPurchaseOrder().getMaterialInformation()+"']/following::input[1]");

    public CreatePurchaseOrderPage clickCreate() {
        helper.clickElement(commonComponents.getBtnAddNew());
        return this;
    }
    public CreatePurchaseOrderPage selectSupplier(String supplierName) {
        helper.enterText(selSearchSupplier(), supplierName);
        helper.clickText(supplierName);
        return this;
    }
    public CreatePurchaseOrderPage selectDestination(String destinationName) {
        helper.enterText(selSearchDestination(), destinationName);
        helper.clickText(destinationName);
        return this;
    }
    public CreatePurchaseOrderPage selectMaterial(String name_sku_Material) {
        helper.enterText(selSearchMaterialInformation, name_sku_Material);
        helper.clickElement(By.xpath("//div[@name='"+name_sku_Material+"']"));
        return this;
    }
    public CreatePurchaseOrderPage enterQuantity(String materialName, String quantity) {
        helper.enterText(By.xpath(commonComponents.getTableData(materialName) + "//div[contains(@class, 'quantity-material')]//input"), quantity);
        return this;
    }
    public CreatePurchaseOrderPage enterImportUnit(String materialName, String importUnit) {
        helper.enterText(By.xpath(commonComponents.getTableData(materialName) + "//div[contains(@class, 'ant-select-show-arrow')]//input"), importUnit);
        helper.clickText(importUnit);
        return this;
    }
    public CreatePurchaseOrderPage enterImportPrice(String materialName, String importPrice) {
        helper.enterText(By.xpath(commonComponents.getTableData(materialName) + "//div[contains(@class, 'input-material-price')]//input"), importPrice);
        return this;
    }
    public CreatePurchaseOrderPage fillMaterialInformation(String materialName, String quantity, String importUnit, String importPrice) {
        helper.scrollToElementAtMiddle(selSearchMaterialInformation);
        selectMaterial(materialName);
        enterQuantity(materialName, quantity);
        enterImportUnit(materialName, importUnit);
        enterImportPrice(materialName, importPrice);
        return this;
    }
    public void createAnPurchaseOrder(String supplier, String destination, String materialName, String quantity, String importUnit, String importPrice) {
        selectSupplier(supplier);
        selectDestination(destination);
        fillMaterialInformation(materialName, quantity, importUnit, importPrice);
        helper.scrollToElementAtBottom(commonComponents.getBtnAddNew());
        clickCreate();
        commonComponents.waitSuccessToast();
    }
}

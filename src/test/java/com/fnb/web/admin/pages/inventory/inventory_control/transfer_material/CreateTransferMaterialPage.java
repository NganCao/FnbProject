package com.fnb.web.admin.pages.inventory.inventory_control.transfer_material;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.Header;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateTransferMaterialPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public Header header;
    public CommonComponents commonComponents;
    private By btnCreate = By.xpath("//button[contains(@class, 'btn-add')]");

    private By selSearchFrom() {
        return commonComponents.getSelectionSearch(ElementData.From_PlaceHolder);
    }

    private By selSearchDestination() {
        return commonComponents.getSelectionSearch(ElementData.Destination_PlaceHolder);
    }

    private By searchMaterialInformation() {
        return commonComponents.getSelectionSearch(ElementData.MaterialInformation_PlaceHolder);
    }

    public CreateTransferMaterialPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        header = new Header(driver);
        commonComponents = new CommonComponents(driver);
        this.driver = driver;
    }

    public CreateTransferMaterialPage clickCreate() {
        helper.clickElement(btnCreate);
        return this;
    }

    public CreateTransferMaterialPage selectFrom(String from) {
        helper.enterText(selSearchFrom(), from);
        helper.clickText(from);
        return this;
    }

    public CreateTransferMaterialPage selectDestination(String destination) {
        helper.enterText(selSearchDestination(), destination);
        helper.clickText(destination);
        return this;
    }

    public CreateTransferMaterialPage selectMaterial(String name_sku_Material) {
        helper.enterText(searchMaterialInformation(), name_sku_Material);
        commonComponents.clickDivName(name_sku_Material);
        return this;
    }

    public CreateTransferMaterialPage enterQuantity(String materialName, String quantity) {
        helper.enterText(By.xpath(commonComponents.getTableData(materialName) + "//input[contains(@class, 'umber-input')]"), quantity);
        return this;
    }

    public CreateTransferMaterialPage selectTransferUnit(String materialName, String unit) {
        helper.enterText(By.xpath(commonComponents.getTableData(materialName) + "//input[@type='search']"), unit);
        commonComponents.clickDivName(unit);
        return this;
    }

    public CreateTransferMaterialPage fillMaterialInformation(String materialName, String quantity, String transferUnit) {
        helper.scrollToElementAtMiddle(searchMaterialInformation());
        selectMaterial(materialName);
        enterQuantity(materialName, quantity);
        selectTransferUnit(materialName, transferUnit);
        return this;
    }

    public TransferMaterialManagementPage clickAddNew() {
        helper.scrollToElementAtBottom(commonComponents.getBtnAddNew());
        helper.clickElement(commonComponents.getBtnAddNew());
        commonComponents.waitSuccessToast();
        return new TransferMaterialManagementPage(driver);
    }

    public void createAnTransferMaterial(String From, String Destination, String materialName, String quantity, String transferUnit) {
        selectFrom(From);
        selectDestination(Destination);
        fillMaterialInformation(materialName, quantity, transferUnit);
        clickAddNew();
    }

    public void clickFirstTransferMaterial() {
        helper.clickElement(By.xpath("(//tr)[3]"));
    }
}

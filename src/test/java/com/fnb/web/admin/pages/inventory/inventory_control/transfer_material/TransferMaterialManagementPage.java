package com.fnb.web.admin.pages.inventory.inventory_control.transfer_material;

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
public class TransferMaterialManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public Header header;
    public CommonComponents commonComponents;

    public TransferMaterialManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        header = new Header(driver);
        commonComponents = new CommonComponents(driver);
        this.driver = driver;
    }
    private By btnAddNew = By.xpath("//*[text()='"+adminLocalization.getTransferMaterial().getTitle()+"']/following::*[text()='"+adminLocalization.getButton().getAddNew()+"'][1]");

    public CreateTransferMaterialPage clickAddNew() {
        helper.clickElement(btnAddNew);
        return new CreateTransferMaterialPage(driver);
    }

    public DetailTransferMaterialPage clickTheFirstTransferMaterial() {
        By firstRowElement = By.xpath("//*[text()='"+adminLocalization.getTransferMaterial().getTitle()+"']/following::tr[3]");
        helper.clickElement(firstRowElement);
        return new DetailTransferMaterialPage(driver);
    }
}

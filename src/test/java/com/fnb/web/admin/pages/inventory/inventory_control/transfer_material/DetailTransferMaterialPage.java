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
public class DetailTransferMaterialPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public Header header;
    public CommonComponents commonComponents;

    public DetailTransferMaterialPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        header = new Header(driver);
        commonComponents = new CommonComponents(driver);
        this.driver = driver;
    }

    private By dropDownSelectOption = By.xpath("//*[contains(@class, 'ant-dropdown-link')]");
    private By selectionEdit = By.xpath("//*[text()='"+adminLocalization.getButton().getEdit()+"']");
    private By selectionCancel = By.xpath("//*[text()='"+adminLocalization.getButton().getCancel()+"']");
    private By selectionPrint = By.xpath("//*[text()='"+adminLocalization.getButton().getPrint()+"']");
    private By btnApprove = By.xpath("//button[normalize-space()='"+adminLocalization.getButton().getApprove()+"']");
    private By btnDeliver = By.xpath("//button[normalize-space()='"+adminLocalization.getTransferMaterial().getStatusButton().getDeliver()+"']");
    private By btnComplete = By.xpath("//button[normalize-space()='"+adminLocalization.getTransferMaterial().getStatusButton().getComplete()+"']");

    public DetailTransferMaterialPage clickSelectOption() {
        helper.clickElement(dropDownSelectOption);
        return this;
    }

    public DetailTransferMaterialPage clickApprove() {
        helper.clickElement(btnApprove);
        commonComponents.waitSuccessToast();
        return this;
    }

    public DetailTransferMaterialPage clickDeliver() {
        helper.clickElement(btnDeliver);
        commonComponents.waitSuccessToast();
        return this;
    }

    public DetailTransferMaterialPage clickComplete() {
        helper.clickElement(btnComplete);
        commonComponents.waitSuccessToast();
        return this;
    }
}

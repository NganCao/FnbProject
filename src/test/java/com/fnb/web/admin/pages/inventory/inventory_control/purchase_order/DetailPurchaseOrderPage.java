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
public class DetailPurchaseOrderPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public Header header;
    public CommonComponents commonComponents;
    private By btnApprove = By.xpath("//button[normalize-space()='"+ElementData.Approve+"']");
    private By dropDownSelectOption = By.xpath("//button[normalize-space()='"+adminLocalization.getButton().getMoreAction()+"']");
    private By editIcon = By.xpath("//button[normalize-space()='"+adminLocalization.getButton().getEdit()+"']");
    private By btnCancel = By.xpath("//*[normalize-space()='"+adminLocalization.getButton().getCancel()+"']");

    public DetailPurchaseOrderPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        header = new Header(driver);
        commonComponents = new CommonComponents(driver);
        this.driver = driver;
    }
    public DetailPurchaseOrderPage clickSelectOption() {
        helper.clickElement(dropDownSelectOption);
        return this;
    }
}

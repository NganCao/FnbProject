package com.fnb.web.admin.pages.store.areaTable;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Data
public class AddNewAreaDialog extends AreaTableManagementPage{
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public CommonComponents commonComponents;

    public AddNewAreaDialog(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }
    private String txtAreaName = "//*[text()='"+adminLocalization.getArea().getAreaName()+"']/following::input[1]";

    public void enterAreaName(String areaName) {
        helper.getTheVisibleElement(txtAreaName).sendKeys(areaName);
    }

    public void clickCreateTheArea() {
        helper.clickElement(By.xpath("//*[text()='"+adminLocalization.getArea().getAddNew()+"']"));
    }

    public void clickCancel() {
        helper.clickElement(By.xpath("//*[text()='"+adminLocalization.getArea().getAddNewArea()+"']/following::*[text()='"+adminLocalization.getButton().getCancel()+"']"));
    }
    //*[text()='Add New Area']/following::*[text()='Cancel']]
    public void createArea(String branch, String areaName) {
        selectBranch(branch);
        clickAddNewArea();
        enterAreaName(areaName);
        clickCreateTheArea();
        commonComponents.waitSuccessToast();
        commonComponents.waitSuccessToastHidden();
    }
}

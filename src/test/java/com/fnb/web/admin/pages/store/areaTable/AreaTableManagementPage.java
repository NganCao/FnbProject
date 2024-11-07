package com.fnb.web.admin.pages.store.areaTable;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

@Data
public class AreaTableManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public CommonComponents commonComponents;

    public AreaTableManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    private By selSearchBranch = By.xpath("//input[./ancestor::span[contains(@class, 'selection-search')]]");
    private By btnAddNewForArea = By.xpath("//button[normalize-space()='"+adminLocalization.getArea().getAddNewArea()+"']");
    private By btnAddNewTable = By.xpath("(//button[normalize-space()='"+adminLocalization.getAreaTable().getTableForm().getTitleAddNewTable()+"'])[1]");
    private By lnkAddNewTable = By.xpath("//a[normalize-space()='"+adminLocalization.getAreaTable().getTableForm().getTitleAddNewTable()+"']");
    private By tabTable = By.xpath("//div[@id='fnb-wrapper-tab-pane-tab-area-table']");
    private By tabArea = By.xpath("//div[@id='fnb-wrapper-tab-pane-tab-area']");
    private By tooltipDeleteArea = By.xpath("//*[text()='"+adminLocalization.getArea().getDeleteCategory()+"']");
    private By tooltipEditArea = By.xpath("//*[text()='"+adminLocalization.getArea().getEditCategory()+"']");
    private By tooltipViewAreaDetail = By.xpath("//*[text()='"+adminLocalization.getArea().getViewCategory()+"']");

    public void selectBranch(String branch) {
        commonComponents.searchAndClickForSelSearchInput(selSearchBranch, branch);
    }

    public AddNewAreaDialog clickAddNewArea() {
        helper.getTheVisibleElement(btnAddNewForArea).click();
        return new AddNewAreaDialog(driver);
    }

    public AddNewTableDialog clickAddNewTable() {
        helper.scrollToElementAtBottom(btnAddNewTable);
        helper.getTheVisibleElement(btnAddNewTable).click();
        return new AddNewTableDialog(driver);
    }

    public void clickLinkAddNewTable() {
        helper.clickElement(lnkAddNewTable);
    }

    public AreaTableManagementPage selectAreaItem(String areaName) {
        helper.smartWait();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        int retries = 3;
        By expectedElement = By.xpath("//*[text()='"+areaName+"']/following::*[text()='Table Name']");

        boolean conditionMet = false;
        for (int attempt = 0; attempt < retries; attempt++) {
            try {
                helper.clickElement(By.xpath("//*[@role='tab' and .//*[contains(normalize-space(),'"+areaName+"')]]"));
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(expectedElement));
                conditionMet = true;
                break;
            } catch (Exception e) {
                Log.error("Attempt " + (attempt + 1) + " failed, retrying...");
            }
        }
        if (!conditionMet) {
            Assert.fail("Can not select Area Item");
        }
        return this;
    }

    public AreaTableManagementPage clickThreeDot_Option(String areaName) {
        helper.smartWait();
        helper.clickElement(By.xpath("(//*[@role='tab' and .//*[contains(normalize-space(),'"+areaName+"')]]//*[name()='svg'])[last()]"));
        return this;
    }
}

package com.fnb.web.admin.pages.store.areaTable;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Data
public class AddNewTableDialog extends AreaTableManagementPage{
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public CommonComponents commonComponents;

    public AddNewTableDialog(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }
    private By getSelSearchArea = By.xpath("//*[text()='"+adminLocalization.getArea().getArea()+"']/following::input[1]");
    private By txtTableName = By.xpath("//*[text()='"+adminLocalization.getAreaTable().getTableName()+"']/following::input[1]");
    private By txtSeat = By.xpath("//input[@id='minQuantity']");

    public void selectArea(String areValue) {
        commonComponents.searchAndClickForSelSearchInput(getSelSearchArea, areValue);
    }

    public void enterTableName(String tableName) {
        WebElement tableNameWebElement = helper.getTheVisibleElement(helper.getTheXpathStringFromBy(txtTableName));
        tableNameWebElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        tableNameWebElement.sendKeys(Keys.BACK_SPACE);
        tableNameWebElement.sendKeys(tableName);
    }

    public void clickCreateTheTable() {
        helper.clickElement(By.xpath("//*[text()='"+adminLocalization.getAreaTable().getAddNew()+"']"));
    }

    public void enterSeatNumber(String seat) {
        helper.enterText(txtSeat, seat);
    }

    public void clickCancel() {
        helper.getTheVisibleElement("//*[text()='"+adminLocalization.getAreaTable().getTableForm().getTitleAddNewTable()+"']/following::*[text()='"+adminLocalization.getButton().getCancel()+"']").click();
    }

    public void createTable(String tableName, String area, String seat) {
        selectArea(area);
        enterTableName(tableName);
        enterSeatNumber(seat);
        clickCreateTheTable();
        commonComponents.waitSuccessToast();
        commonComponents.waitSuccessToastHidden();
    }
}

package com.fnb.web.admin.pages.common;

import com.fnb.web.admin.pages.store.promotion.PromotionManagementPage;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

@Data
public class CommonComponents extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public Header header;
    private By notificationContentMessage = By.xpath("//div[@class='text-content-notification']");
    private By confirmDeleteContentMessage = By.xpath("//div[contains(@class, 'delete-confirm-modal')]//p");
    private By btnDelete = By.xpath("//button[normalize-space()='"+adminLocalization.getButton().getDelete()+"']");
    public By btnDiscard = By.xpath("//div[@class='ant-modal-content']//button[not(contains(@class, 'dangerous'))]");
    public By btnIgnore = By.xpath("//button[normalize-space()='" + adminLocalization.getLeaveDialog().getIgnore() + "']");
    public By btnConfirmLeave = By.xpath("//button[normalize-space()='" + adminLocalization.getLeaveDialog().getConfirmLeave() + "']");
    private By successDialog = By.xpath("//div[contains(@class, 'ant-message-success')]");
    private By errorDialog = By.xpath("//div[contains(@class, 'ant-message-error')]");
    private By btnBackToHomepage = By.xpath("//button[@class='button-bg']");
    private By btnAddNew = By.xpath("//button[normalize-space()='"+adminLocalization.getButton().getAddNew()+"']");
    private By btnImport = By.xpath("//*[name()='svg']//*[name()='path' and @d='" + CommonElementData.Description_Svg_Import + "']");
    private By btnExport = By.xpath("//*[name()='svg']//*[name()='path' and @d='" + CommonElementData.Description_Svg_Export + "']");
    private By btnDeleteIcon = By.xpath("//button[contains(@class, 'delete-button') and not(ancestor::div[contains(@class, 'sibling')])]");
    private By btnEditIcon = By.xpath("//button[.//*[name()='svg']//*[name()='path' and @d='"+CommonElementData.EditIcon_Svg+"']]");
    private By searchIcon = By.xpath("//span[@class='ant-input-prefix']");
    private By txtSearch = By.xpath("//div[@class='search-bar']//input");
    private By filterIcon = By.xpath("//span//*[name()='svg' and contains(@class, 'filter')]");
    private By clearIcon = By.xpath("//span[contains(@class, 'close-circle')]");
    private By btnCreate = By.xpath("//button[@id='btn-add-new']");
    private By btnAddNewWithoutPlusIcon = By.xpath("//button[normalize-space()='Add new' and not(.//*[name()='svg'])]");
    private By btnCancel = By.xpath("//button[normalize-space()='"+adminLocalization.getButton().getCancel()+"']");
    private By btnUpdate = By.xpath("//button[normalize-space()='"+adminLocalization.getButton().getUpdate()+"']");
    private By btnSave = By.xpath("//button[normalize-space()='"+adminLocalization.getButton().getSave()+"']");
    private By btnStopIcon = By.xpath("//button[.//*[name()='svg']//*[name()='path' and @d='"+CommonElementData.StopIcon_Svg+"']]");

    public CommonComponents(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        header = new Header(driver);
        this.driver = driver;
    }

    public String getDialogContentMessage() {
        String message = "";
        if (helper.isElementVisible(notificationContentMessage)) {
            message = helper.getText(notificationContentMessage);
        }
        if (helper.isElementVisible(confirmDeleteContentMessage)) {
            message = helper.getText(confirmDeleteContentMessage);
        }
        return message;
    }

    public String getContentConfirmationDialog() {
        By dialogBody = By.xpath("//div[@class='ant-modal-body']");
        return helper.getText(dialogBody);
    }

    public String getContentErrorDialog() {
        return helper.getText(errorDialog);
    }

    public void clickDeleteButton() {
        helper.clickElement(btnDelete);
    }

    public void waitSuccessToast() {
        helper.waitForElementVisible(successDialog);
    }

    public void waitSuccessToastHidden() {
        helper.waitElementIsRemoved(successDialog);
    }

    public void clickBtnDiscard() {
        helper.clickElement(btnDiscard);
    }

    public void clickIgnore() {
        helper.getTheVisibleElement(btnIgnore).click();
    }

    public void clickBtnConfirmLeave() {
        helper.clickElement(btnConfirmLeave);
    }

    public String getTableData(String name) {
        return "//*[text()='" + name + "' and not(ancestor::div[contains(@class, 'sibling')])]/ancestor:: tr[contains(@class, 'ant-table-row')]";
    }

    public void logout() {
        header.clickLogOut();
        helper.waitForUrl(configObject.getUrlLogin());
    }

    public void clickBackToHomepage() {
        helper.clickElement(btnBackToHomepage);
    }

    public String getSelectionSearch_StringXpath(String placeHolderName) {
        return "//span[text()='" + placeHolderName + "' and contains(@class, 'placeholder')]/preceding-sibling::span//input";
    }

    public By getSelectionSearch(String placeHolderName) {
        return By.xpath(getSelectionSearch_StringXpath(placeHolderName));
    }

    public void clickDivName(String divName) {
        helper.clickElement(By.xpath("//div[@name='" + divName + "']"));
    }

    public void selectItem(String nameItem) {
        By rowData = By.xpath(getTableData(nameItem) + "//span[contains(@class, 'ant-checkbox') and not(contains(@class, 'inner'))]");
        helper.getTheVisibleElement(rowData).click();
        Assert.assertNotNull(helper.getTheVisibleElement(getTableData(nameItem) + "//*[contains(@class, 'checkbox-checked')]"), "The item is not selected -");
    }

    public void clickClearIcon() {
        helper.clickElement(clearIcon);
    }

    public String theOrderOfColumn(String columnName) {
        return "count(//thead//th[contains(normalize-space(), '"+columnName+"')]//preceding-sibling::th)+1";
    }

    public void searchAndClickForSelSearchInput(By inputElement, String inputValue) {
        helper.getTheVisibleElement(helper.getTheXpathStringFromBy(inputElement)).sendKeys(inputValue);
        selectOptionInDropDownOfSearchSelect(inputValue);
    }

    public void selectOptionInDropDownOfSearchSelect(String inputValue) {
        helper.getTheVisibleElement("//div[contains(@class, 'ant-select-dropdown')]//*[text()='" + inputValue + "']").click();
    }

    public void searchItem(String item) {
        By rowData = By.xpath(getTableData(item) + "//span[contains(@class, 'ant-checkbox') and not(contains(@class, 'inner'))]");
        Boolean check = helper.isElementVisible(rowData);

        helper.getTheVisibleElement(searchIcon).click();
        helper.getTheVisibleElement(txtSearch).sendKeys(item);

        // Handle stale element when it appears before the next action
        if (check) {
            try {
                helper.waitElementIsRemoved(rowData, 2);
            }
            catch (Exception e) {}
        }
    }

    public void clickDeleteIcon() {
        helper.getTheVisibleElement(getBtnDeleteIcon()).click();
    }

    public void clickEditIcon() {
        helper.getTheVisibleElement(getBtnEditIcon()).click();
    }

    public void clickStopIcon() {
        helper.getTheVisibleElement(btnStopIcon).click();
    }
}

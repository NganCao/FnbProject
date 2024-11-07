package com.fnb.web.admin.pages.store.feeandtax;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Data
public class FeeManagementPage extends FeeAndTaxCommon {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;
    public FeeManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }
    By btnAddNew = By.xpath("//*[text()='"+adminLocalization.getFeeAndTax().getFeeManagement()+"']/following::button[1]");

    public TaxManagementPage clickTaxTab() {
        helper.clickElement(btnTabTax);
        return new TaxManagementPage(driver);
    }

    public FeeManagementPage selectItem(String feeName) {
        commonComponents.selectItem(feeName);
        return this;
    }

    public AddNewFeeDialog clickAddNewButton() {
        helper.clickElement(commonComponents.getBtnAddNew());
        helper.waitForElementVisible(By.xpath("(//div[@role='dialog'])[1]"), "The add new fee dialog is not shown");
        return new AddNewFeeDialog();
    }

    @Data
    public class AddNewFeeDialog {
        private By txtName = By.xpath("//input[@id='basic_fee_name']");
        private By txtValue = By.xpath("//input[@id='basic_fee_value']");
        private By optPercentage = By.xpath("//*[contains(@class, 'percent-option')]");
        private By optCurrency = By.xpath("//*[contains(@class, 'currency-option')]");
        private By txtStartDate = By.xpath("//input[@id='basic_fee_StartDate']");
        private By txtEndDate = By.xpath("//input[@id='basic_fee_EndDate']");
        private By selSearchBranch = By.xpath("//input[@id='basic_fee_feeBranchIds']");

        public void selectStartDate(String startDate) {
            helper.getWebElement(txtStartDate).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            helper.getWebElement(txtStartDate).sendKeys(Keys.BACK_SPACE);
            helper.enterText(txtStartDate, startDate);
            helper.getWebElement(txtStartDate).sendKeys(Keys.ENTER);
        }

        public void selectEndDate(String endDate) {
            helper.enterText(txtEndDate, endDate);
            helper.getWebElement(txtEndDate).sendKeys(Keys.ENTER);
        }

        public void selectBranch(String branch) {
            helper.getWebElement(selSearchBranch).sendKeys(branch);
            helper.getTheVisibleElement("//div[contains(@class, 'ant-select-dropdown')]//*[text()='" + branch + "']").click();
            helper.getWebElement(selSearchBranch).sendKeys(Keys.ESCAPE);
        }

        public void clickAddNew() {
            helper.getTheVisibleElement(commonComponents.getBtnAddNewWithoutPlusIcon()).click();
            commonComponents.waitSuccessToast();
        }

        public String addFee(String name, String value, String startDate, String endDate, String branch) {
            helper.enterText(txtName, name);
            helper.enterText(txtValue, value);
            selectStartDate(startDate);
            selectEndDate(endDate);
            selectBranch(branch);
            clickAddNew();
            return name;
        }

        public void clickCancel() {
            helper.clickElement(commonComponents.getBtnCancel());
        }
    }
}

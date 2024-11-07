package com.fnb.web.admin.pages.store.feeandtax;

import com.fnb.web.admin.pages.common.CommonComponents;
import lombok.Data;
import org.openqa.selenium.By;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

@Data
public class TaxManagementPage extends FeeAndTaxCommon {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;

    public TaxManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }
    By btnAddNew = By.xpath("//*[text()='"+adminLocalization.getFeeAndTax().getTax().getTaxManagement()+"']/following::button[1]");

    public AddNewTaxDialog clickAddNewButton() {
        helper.clickElement(btnAddNew);
        AddNewTaxDialog addNewTaxDialog = new AddNewTaxDialog();
        helper.waitForElementPresent(By.xpath("//div[contains(@class, 'ant-fade')]"));
        return addNewTaxDialog;
    }

    public TaxManagementPage selectTax(String taxName) {
        commonComponents.selectItem(taxName);
        return this;
    }

    @Data
    public class AddNewTaxDialog {
        private By txtTaxName = By.xpath("//input[@id='basic_tax_name']");
        private By txtValue = By.xpath("//input[@id='basic_tax_percentage']");
        private By radioButtonSellingTax = By.xpath("//label[normalize-space()='Selling Tax']//input[@type='radio']");
        private By radioButtonImportedTax = By.xpath("//label[normalize-space()='Imported Tax']//input[@type='radio']");
        private By btnAddNew = By.xpath("//button[@type='submit']");

        public void clickCancel() {
            helper.clickElement(commonComponents.getBtnCancel());
        }

        public void addTax(String taxName, String percentage, String taxType) {
            helper.sleep(1);
            helper.enterText(txtTaxName, taxName);
            helper.enterText(txtValue, percentage);
            helper.waitExpectedAttributeToBe(txtTaxName, "value",taxName, "The tax name is not yet set");
            helper.waitExpectedAttributeToBe(txtValue, "value",percentage, "The percentage is not yet set");

            if (taxType.equals("Selling")) {
                if(!helper.getWebElement(radioButtonSellingTax).isSelected()) {
                    helper.getWebElement(radioButtonSellingTax).click();
                }
            }
            else {
                if(!helper.getWebElement(radioButtonImportedTax).isSelected()) {
                    helper.getWebElement(radioButtonImportedTax).click();
                }
            }
            helper.clickElement(commonComponents.getBtnAddNewWithoutPlusIcon());
            commonComponents.waitSuccessToast();
            commonComponents.waitSuccessToastHidden();
        }
    }
}

package com.fnb.web.admin.pages.product.options;

import com.fnb.web.admin.pages.common.SiderBar;
import dataObject.Product.Options;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.List;

@Data
public class OptionManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public OptionManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        siderBar = new SiderBar(driver);
    }
    SiderBar siderBar;
    private By btnAddNew = By.xpath("//button[@id='btn-add-new']");
    private By toastSuccessMessage = By.xpath("//div[contains(@class, 'ant-message-success')]");
    private By toastErrorMessage = By.xpath("//div[contains(@class, 'ant-message-error')]");
    private By txtSearch = By.xpath("//div[@class='search-bar']//input");
    private By btnDelete = By.xpath("(//button[contains(@class, 'ant-btn-dangerous')])[1]");
    private By iconClear = By.xpath("//span[@class='ant-input-clear-icon']");

    public class AddNewOptionDialog extends Setup{
        private By txtOptionName = By.xpath("//input[@id='basic_name']");
        private By btnAddOptionLevel = By.xpath("//div[@role='dialog']//button[@id='btn-add-new']");
        private By btnCancel = By.xpath("//div[@role='dialog']//button[contains(@class, 'cancel')]");
        private By btnAdd = By.xpath("//button[normalize-space()='"+DataTest.Add_Button+"']");
        private By dpdnMaterial = By.xpath("//div[@role='dialog']//input[@role='combobox']");

        public void selectMaterial(String materialName) {
            helper.enterText(dpdnMaterial,materialName);
            By materialSelectItem = By.xpath("//div[@name='"+materialName+"' and contains(@class, 'option')]");
            helper.waitForElementVisible(materialSelectItem, "The material select option is not displayed");
            helper.clickElement(materialSelectItem);
            helper.smartWait();
        }

        public void clickAddOptionLevel() {
            helper.clickElement(btnAddOptionLevel);
        }

        public void setLevelAndQuota(int index, String levelName, String quota) {
            By txtLevel = By.xpath("//input[@id='basic_optionLevels_"+index+"_name']");
            By txtQuota = By.xpath("//input[@id='basic_optionLevels_"+index+"_quota']");

            helper.enterText(txtLevel, levelName);
            helper.enterText(txtQuota, quota);
        }

        public void addNewOption(String optionName, String material, List<Options.Information.optionLevels> optionLevels) {
            helper.enterText(txtOptionName, optionName);
            selectMaterial(material);
            for (int i=0; i<optionLevels.size(); i++) {
                clickAddOptionLevel();
                setLevelAndQuota(i, optionLevels.get(i).getName(), optionLevels.get(i).getQuota());
            }
            helper.clickElement(btnAdd);
            helper.waitForElementVisible(toastSuccessMessage);
        }
    }

    public AddNewOptionDialog openAddNewOption() {
        helper.clickElement(btnAddNew);
        return new AddNewOptionDialog();
    }

    public void deleteOption(String optionName) {
        helper.clearText(txtSearch);
        By eptOptionRow = By.xpath("(//span[contains(text(), '"+optionName+"')]/ancestor::tr//div[@class='fnb-table-action-icon'])[2]");

        // Get the height of the table to compare after the search results are populated
        int heigh = helper.getWebElement(By.xpath("//div[@class='fnb-table-wrapper hide-pagination-options']//div[@class='ant-row']")).getSize().getHeight();
        helper.enterText(txtSearch, optionName);

        // Wait for the search results to complete
        helper.waitForElementHeightToChange(By.xpath("//div[@class='fnb-table-wrapper hide-pagination-options']//div[@class='ant-row']"), heigh);

        helper.clickElement(eptOptionRow);
        helper.clickElement(btnDelete);
        helper.clickElement(iconClear);
        helper.waitForElementVisible(By.xpath("(//div[contains(@class, 'ant-message-success')])[1]"));
    }
}






























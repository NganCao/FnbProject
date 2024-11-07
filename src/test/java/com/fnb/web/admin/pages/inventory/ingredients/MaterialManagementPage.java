package com.fnb.web.admin.pages.inventory.ingredients;

import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

@Data
public class MaterialManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public SiderBar siderBar;
    public CommonComponents commonComponents;

    public MaterialManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        siderBar = new SiderBar(driver);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }
    private By txtSearch = By.xpath("//div[@class='search-bar']//input");
    private By btnDelete = By.xpath("//button[contains(@class, 'ant-btn-dangerous')]");
    private By toastSuccessMessage = By.xpath("//div[contains(@class, 'ant-message-success')]");
    private By iconClear = By.xpath("//span[@class='ant-input-clear-icon']");
    private By btnAddNew = By.xpath("//button[@id='btn-add-new']");
    private By btnImport = By.xpath("//a[normalize-space()='"+ DataTest.Import+"']");
    private By btnExport = By.xpath("//a[normalize-space()='"+DataTest.Export+"']");

    public void deleteMaterial(String materialName) {
        helper.clearText(txtSearch);
        helper.enterText(txtSearch, materialName);
        helper.clickElement(By.xpath("(//a[contains(text(),'"+materialName+"')]/ancestor::tr//div[@class='fnb-table-action-icon'])[2]"));
        helper.clickElement(btnDelete);
        helper.waitForElementVisible(By.xpath("//div[contains(@class, 'ant-message-success')]//span[contains(text(), '"+materialName+"')]"));
        helper.clickElement(iconClear);
    }

    public String getMaterialData(String materialName, MaterialColumnName materialColumnName) {
        return helper.getText(By.xpath(""+commonComponents.getTableData(materialName)+"//td["+commonComponents.theOrderOfColumn(materialColumnName.getDisplayName())+"]"));
    }

    public By getDeleteIconEle(String materialName) {
        return By.xpath("("+getMaterialTableRow(materialName)+"//div[@class='fnb-table-action-icon'])[2]");
    }

    public String getMaterialTableRow(String materialName) {
        String xpathString = "//a[contains(text(),'" + materialName + "')]/ancestor::tr";
        return xpathString;
    }

    public MaterialManagementPage enterMaterialName(String materialName) {
        helper.clickElement(commonComponents.getSearchIcon());
        helper.clearText(txtSearch);
        helper.enterText(txtSearch, materialName);
        helper.smartWait();
        return this;
    }

    public MaterialManagementPage clickFilterButton() {
        helper.clickElement(commonComponents.getFilterIcon());
        // Wait until the filter popover shown
        helper.waitForElementVisible(By.xpath("//div[@class='content-filter']"));
        return this;
    }

    public MaterialManagementPage verifyMaterialIsVisible(String materialName) {
        helper.clickElement(commonComponents.getSearchIcon());
        helper.clearText(txtSearch);
        helper.enterText(txtSearch, materialName);
        helper.waitForElementVisible(By.xpath(commonComponents.getTableData(materialName)), "The "+materialName+" is not visible");
        helper.clickElement(commonComponents.getClearIcon());
        return this;
    }

    public MaterialManagementPage verifyMaterialNotVisible(String materialName) {
        helper.clickElement(commonComponents.getSearchIcon());
        helper.clearText(txtSearch);
        helper.enterText(txtSearch, materialName);
        helper.waitForElementInVisible(By.xpath(commonComponents.getTableData(materialName)), "The "+materialName+" is still visible");
        helper.clickElement(commonComponents.getClearIcon());
        return this;
    }

    public MaterialManagementPage clickClearIcon() {
        helper.clickElement(iconClear);
        return this;
    }

    public CreateMaterialPage clickOnMaterial(String materialName) {
        helper.clickElement(commonComponents.getSearchIcon());
        helper.clearText(txtSearch);
        helper.enterText(txtSearch, materialName);
        helper.clickElement(By.xpath(commonComponents.getTableData(materialName) + "//*[text()='"+materialName+"']"));
        return new CreateMaterialPage(driver);
    }
}

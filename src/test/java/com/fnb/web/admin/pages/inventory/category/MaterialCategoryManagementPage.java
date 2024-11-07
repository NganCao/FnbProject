package com.fnb.web.admin.pages.inventory.category;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import dataObject.Inventory.MaterialCategory;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

@Data
public class MaterialCategoryManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;

    public MaterialCategoryManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    private By txtName = By.xpath("//input[@id='basic_name']");
    private By selSearchMaterial = By.xpath("//*[text()='"+adminLocalization.getMaterialCategory().getSelectMaterial()+"']/following::input[@type='search'][1]");
    private By btnAdd = By.xpath("//*[text()='"+adminLocalization.getMaterialCategory().getCreate()+"']/following::*[text()='"+adminLocalization.getButton().getAdd()+"'][1]");

    public MaterialCategoryManagementPage clickAddNew() {
        helper.clickElement(commonComponents.getBtnAddNew());
        return this;
    }

    public MaterialCategoryManagementPage enterCategoryName(String categoryName) {
        helper.enterText(txtName, categoryName);
        return this;
    }

    public MaterialCategoryManagementPage selectMaterial(String material) {
        commonComponents.searchAndClickForSelSearchInput(selSearchMaterial, material);
        return this;
    }

    public MaterialCategoryManagementPage clickAdd() {
        helper.clickElement(btnAdd);
        commonComponents.waitSuccessToast();
        return this;
    }

    public String createMaterialCategory(String materialCategoryName, String[] materialNames) {
        enterCategoryName(materialCategoryName);
        for (int i=0; i < materialNames.length; i++) {
            selectMaterial(materialNames[i]);
        }
        clickAdd();
        return materialCategoryName;
    }
}

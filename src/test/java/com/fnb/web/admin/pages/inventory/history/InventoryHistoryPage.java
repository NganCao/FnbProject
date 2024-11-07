package com.fnb.web.admin.pages.inventory.history;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.Header;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.setup.Setup;
import dataObject.Integration.MaterialInventoryHistory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InventoryHistoryPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public SiderBar siderBar;
    private Header header;
    private CommonComponents commonComponents;

    public InventoryHistoryPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        siderBar = new SiderBar(driver);
        header = new Header(driver);
        commonComponents = new CommonComponents(driver);
    }


    //
    private String getFieldData(String ingredient, String orderID, String field) {
        return helper.getText(By.xpath("//tr[.//*[text()='"+ingredient+"'] and .//*[text()='"+orderID+"']]//td[count(//*[text()='"+field+"']/ancestor::th/preceding-sibling::th) + 1]"));
    }

    public MaterialInventoryHistory getAMaterialHistory(String ingredient, String orderID) {
        MaterialInventoryHistory materialInventoryHistory = new MaterialInventoryHistory();
        materialInventoryHistory.setIngredient(getFieldData(ingredient, orderID, "Ingredient"));
        materialInventoryHistory.setCategory(getFieldData(ingredient, orderID, "Category"));
        materialInventoryHistory.setBranchName(getFieldData(ingredient, orderID, "Branch"));
        materialInventoryHistory.setBaseUnitName(getFieldData(ingredient, orderID, "Unit"));
        materialInventoryHistory.setPreviousQuantity(getFieldData(ingredient, orderID, "Previous quantity"));
        materialInventoryHistory.setChange(getFieldData(ingredient, orderID, "Change"));
        materialInventoryHistory.setRemain(getFieldData(ingredient, orderID, "Remain"));
        materialInventoryHistory.setReference(getFieldData(ingredient, orderID, "Reference"));
        return materialInventoryHistory;
    }
}

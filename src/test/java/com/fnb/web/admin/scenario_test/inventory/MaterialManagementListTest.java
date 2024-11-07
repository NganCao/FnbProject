package com.fnb.web.admin.scenario_test.inventory;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.PagesAdminSetup;
import com.fnb.web.admin.pages.home.HomePage;
import com.fnb.web.admin.pages.inventory.ingredients.MaterialColumnName;
import com.fnb.web.admin.pages.inventory.ingredients.MaterialManagementPage;
import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.admin.pages.product.management.ProductManagementPage;
import com.fnb.web.admin.pages.store.staff.StaffManagementPage;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.*;

// Sprint51
public class MaterialManagementListTest extends CommonPages {
    Setup staffSetup;
    PagesAdminSetup staffAdminPage;
    HomePage staffHomePage;
    StaffManagementPage staffManagementPage_ForStaff;
    ProductManagementPage productManagementPage_ForStaff;
    com.fnb.web.admin.pages.store.staff.DataTest staffDataTest;
    MaterialManagementPage materialManagementPage_ForStaff;
    static String skuName;
    static String materialNameStatic;

    public PagesAdminSetup staffAdminPage() {
        return staffAdminPage;
    }

    public WebDriver driver2() {
        return staffSetup.driver;
    }

    Helper action;

    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        action = adminPage().helper;
        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
    }

    @AfterClass
    public void afterClass() {
        // Admin will check the view material permission (reset data)
        homePage().helper.navigateToUrl(configObject.getUrlStaffManagement());
        staffManagementPage()
                .clickPermissionGroupTab()
                .checkFullPermission("Option permission")
                .clickUpdate();
    }

    @Test(testName = "FB-12448 : Check if clicking on the “Material” menu navigates to the Material management page", priority = 2)
    public void FB12448() {
        homePage().siderBar.clickMnuItemInventory();
        action.waitForUrl(configObject.getUrlMaterialManagement());
    }

    @Test(testName = "FB-12449 : Verify that the following columns are visible", priority = 2)
    public void FB12449() {
        homePage().helper.navigateToUrl(configObject.getUrlMaterialManagement());
        Assert.assertTrue(action.isElementVisible(By.xpath("//thead//th[normalize-space()='" + MaterialColumnName.MATERIAL_COLUMN_NAME.getDisplayName() + "']")), "The material name is not visible");
        Assert.assertTrue(action.isElementVisible(By.xpath("//thead//th[normalize-space()='" + MaterialColumnName.SKU_COLUMN_NAME.getDisplayName() + "']")), "The material sku is not visible");
        Assert.assertTrue(action.isElementVisible(By.xpath("//thead//th[normalize-space()='" + MaterialColumnName.QUANTITY_COLUMN_NAME.getDisplayName() + "']")), "The material quantity is not visible");
        Assert.assertTrue(action.isElementVisible(By.xpath("//thead//th[normalize-space()='" + MaterialColumnName.COST_COLUMN_NAME.getDisplayName() + "']")), "The material cost is not visible");
        Assert.assertTrue(action.isElementVisible(By.xpath("//thead//th[normalize-space()='" + MaterialColumnName.STATUS_COLUMN_NAME.getDisplayName() + "']")), "The material status is not visible");
    }

    @Test(testName = "FB-12450 : Verify the material data of product management page", priority = 1)
    public void FB12450() {
        // Create materail
        materialNameStatic = "MaterialTest" + action.generateRandomNumber();
        skuName = "SKU" + action.generateRandomNumber();
        String baseUnit = "gam";
        String costPerUnit = "500000";
        String quantity1String = "1000";
        String quantity2String = "2000";
        String binhThanhBranch = branchData.getBranch().get(0).getName();
        String GoFoodBranch = branchData.getBranch().get(1).getName();

        homePage().helper.navigateToUrl(configObject.getUrlCreateMaterial());

        createMaterialPage().helper.enterText(createMaterialPage().getTxtMaterialName(), materialNameStatic);
        createMaterialPage().chooseBaseUnit(baseUnit);
        createMaterialPage().enterSKU(skuName);
        createMaterialPage().helper.enterText(createMaterialPage().getTxtCostPerUnit(), costPerUnit);
        createMaterialPage().enterQuantityForBranch(binhThanhBranch, quantity1String);
        createMaterialPage().enterQuantityForBranch(GoFoodBranch, quantity2String);
        createMaterialPage().clickAddNew();

        // After we created the material, we will verify the data in the material management page
        // 1.Verify the name
        Assert.assertEquals(materialManagementPage().getMaterialData(materialNameStatic, MaterialColumnName.MATERIAL_COLUMN_NAME), materialNameStatic);
        // 2.Verify the SKU
        Assert.assertEquals(materialManagementPage().getMaterialData(materialNameStatic, MaterialColumnName.SKU_COLUMN_NAME), skuName);
        // 3.Verify the quantity (We have two quantity, so we need to take the sum of quantity1String and quantity2String)
        double quantity1Double = Double.parseDouble(quantity1String);
        double quantity2IntDouble = Double.parseDouble(quantity2String);
        double expectedQuantityInt = quantity1Double + quantity2IntDouble;
        String expectedQuantityString = action.formatDoubleToString(expectedQuantityInt) + " " + baseUnit;
        Assert.assertEquals(materialManagementPage().getMaterialData(materialNameStatic, MaterialColumnName.QUANTITY_COLUMN_NAME), expectedQuantityString);
        // 4.Verify cost
        String expectedCost = action.formatDoubleToString(action.convertStringToDouble(costPerUnit)) + " " + "VND/" + baseUnit;
        Assert.assertEquals(materialManagementPage().getMaterialData(materialNameStatic, MaterialColumnName.COST_COLUMN_NAME), expectedCost);
    }

    @Test(testName = "FB-12464 : Verify that we can search the material with lower case", priority = 2)
    public void FB12464() {
        homePage().helper.navigateToUrl(configObject.getUrlMaterialManagement());
        String materialName = materialData.getMaterial().get(0).getName();
        String lowerCaseMaterialName = materialName.toLowerCase();

        materialManagementPage().enterMaterialName(lowerCaseMaterialName);
        action.waitForElementVisible(By.xpath(commonComponents().getTableData(materialName)));
    }

    @Test(testName = "FB-12486 : Checking the result when splitting the lowercase material name into each word", priority = 2)
    public void FB12486() {
        String materialName = materialData.getMaterial().get(0).getName();
        String lowerCaseMaterialName = materialName.toLowerCase();

        String[] words = lowerCaseMaterialName.split("\\s+");
        for (int i=0; i < words.length; i++) {
            homePage().helper.navigateToUrl(configObject.getUrlMaterialManagement());
            materialManagementPage().enterMaterialName(words[i]);
            action.waitForElementVisible(By.xpath(commonComponents().getTableData(materialName)));
        }
    }

    @Test(testName = "FB-12467 : Verify that we can search the material with uppercase", priority = 2)
    public void FB12467() {
        homePage().helper.navigateToUrl(configObject.getUrlMaterialManagement());
        String materialName = materialData.getMaterial().get(0).getName();
        String upperCaseMaterialName = materialName.toUpperCase();

        materialManagementPage().enterMaterialName(upperCaseMaterialName);
        action.waitForElementVisible(By.xpath(commonComponents().getTableData(materialName)));
    }

    @Test(testName = "FB-12487 : Checking the result when splitting the uppercase material name into each word", priority = 2)
    public void FB12487() {
        String materialName = materialData.getMaterial().get(0).getName();
        String upperCaseMaterialName = materialName.toUpperCase();

        String[] words = upperCaseMaterialName.split("\\s+");
        for (int i=0; i < words.length; i++) {
            homePage().helper.navigateToUrl(configObject.getUrlMaterialManagement());
            materialManagementPage().enterMaterialName(words[i]);
            action.waitForElementVisible(By.xpath(commonComponents().getTableData(materialName)));
        }
    }

    @Test(testName = "FB-12469 : Verify that we can search the material with unsigned word", priority = 2)
    public void FB12469() {
        homePage().helper.navigateToUrl(configObject.getUrlMaterialManagement());
        String materialName = materialData.getMaterial().get(0).getName();
        String unsignedWordMaterialName = action.removeDiacritics(materialName);

        materialManagementPage().enterMaterialName(unsignedWordMaterialName);
        action.waitForElementVisible(By.xpath(commonComponents().getTableData(materialName)));
    }

    @Test(testName = "FB-12488 : Checking the result when splitting the unsigned material name into each wor", priority = 2)
    public void FB12488() {
        String materialName = materialData.getMaterial().get(0).getName();
        String unsignedWordMaterialName = action.removeDiacritics(materialName);

        String[] words = unsignedWordMaterialName.split("\\s+");
        for (int i=0; i < words.length; i++) {
            homePage().helper.navigateToUrl(configObject.getUrlMaterialManagement());
            materialManagementPage().enterMaterialName(words[i]);
            action.waitForElementVisible(By.xpath(commonComponents().getTableData(materialName)));
        }
    }

    @Test(testName = " FB-12472 : Checking the result when splitting the normal material name into each word", priority = 2)
    public void FB12472() {
        String materialName = materialData.getMaterial().get(0).getName();
        String[] words = materialName.split("\\s+");
        for (int i=0; i < words.length; i++) {
            homePage().helper.navigateToUrl(configObject.getUrlMaterialManagement());
            materialManagementPage().enterMaterialName(words[i]);
            action.waitForElementVisible(By.xpath(commonComponents().getTableData(materialName)));
        }
    }

    @Test(testName = "FB-12495 : Verify that we can search the material with SKU", priority = 2)
    public void FB12495() {
        homePage().helper.navigateToUrl(configObject.getUrlMaterialManagement());
        // skuName and materialNameStatic was created in another test;
        materialManagementPage().enterMaterialName(skuName);
        action.waitForElementVisible(By.xpath(commonComponents().getTableData(materialNameStatic)));
    }

    @Test(testName = "FB-12497 : Verify that we can search the material with lowercase SKU", priority = 2)
    public void FB12497() {
        homePage().helper.navigateToUrl(configObject.getUrlMaterialManagement());
        String lowerCaseSKU = skuName.toLowerCase();
        materialManagementPage().enterMaterialName(lowerCaseSKU);
        action.waitForElementVisible(By.xpath(commonComponents().getTableData(materialNameStatic)));
    }
}

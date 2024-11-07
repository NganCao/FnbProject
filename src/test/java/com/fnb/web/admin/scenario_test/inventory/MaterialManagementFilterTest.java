package com.fnb.web.admin.scenario_test.inventory;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.inventory.ingredients.DataTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.fnb.web.setup.Setup.*;

// Sprint51
public class MaterialManagementFilterTest extends CommonPages {
    Helper helper;
    com.fnb.web.admin.pages.product.management.DataTest loginData;
    @BeforeClass
    public void beforeTest() {
        helper = adminPage().helper;
        adminPage().navigateToHomePage(loginData.INPUT_EMAIL, loginData.INPUT_PASSWORD);
    }
    @BeforeMethod
    public void beforeMethod() {
        adminPage().helper.navigateToUrl(configObject.getUrlMaterialManagement());
    }

    @Test(testName = "FB-12556 : Check if there is a button to open the filter dialog on the Material Management page")
    public void FB12556() {
        //  Click on button filter to open filter dialog
        materialManagementPage().clickFilterButton();
    }

    @Test(testName = "FB-12557 : Verify the default option of all fields")
    public void FB12557() {
        //  Click on button filter to open filter dialog
        materialManagementPage().clickFilterButton();

        // Branch: Default is All branches
        Assert.assertEquals(filterDialog().getSelectedItem(DataTest.Branch), DataTest.All_branches, "The default branch is not correct");

        // Category: Default is All categories
        Assert.assertEquals(filterDialog().getSelectedItem(DataTest.Category), DataTest.All_categories, "The default category is not correct");

        // Unit: Default is All units
        Assert.assertEquals(filterDialog().getSelectedItem(DataTest.Unit), DataTest.All_units, "The default unit is not correct");

        // Status: Default is All
        Assert.assertTrue(helper.isElementVisible(filterDialog().getCheckIcon(DataTest.All, DataTest.Status)));
    }

    @Test(testName = "FB-12582 : Verify that all data loads after selecting the field")
    public void FB12582() {
        //  Click on button filter to open filter dialog
        materialManagementPage().clickFilterButton();

        // Branch
        for (int i = 0; i < branchData.getBranch().size(); i++) {
            String branchName = branchData.getBranch().get(i).getName();
            helper.enterText(filterDialog().getSelSearchBranch(), branchName);
            Assert.assertTrue(adminPage().helper.isElementVisible(By.xpath("//*[text()='"+branchName+"']")), "The branch data (" + branchName + ") is not visible");
            helper.clearText(filterDialog().getSelSearchBranch());
        }

        // Category
        // PTODO

        // Unit
        for (int i = 0; i < materialData.getMaterial().size(); i++) {
            String unitName = materialData.getMaterial().get(i).getUnit();
            helper.enterText(filterDialog().getSelSearchCUnit(), unitName);
            Assert.assertTrue(adminPage().helper.isElementVisible(By.xpath("//*[text()='"+unitName+"']")), "The unit data (" + unitName + ") is not visible");
            helper.clearText(filterDialog().getSelSearchCUnit());
        }

        // Status
        helper.scrollToElementAtBottom(filterDialog().getBtnResetFilter());
        Assert.assertTrue(helper.isElementVisible(filterDialog().getSelectionItem(DataTest.Active, DataTest.Status)), "The status data Active is not visible");
        Assert.assertTrue(helper.isElementVisible(filterDialog().getSelectionItem(DataTest.Inactive, DataTest.Status)), "The status data Inactive is not visible");
    }

    @Test(testName = "FB-12617 : Check if the number of applied filters is shown in the “Filter” button")
    public void FB12617() {
        //  Click on button filter to open filter dialog
        materialManagementPage().clickFilterButton();

        filterDialog().selectBranch(branchData.getBranch().get(0).getName());
        filterDialog().selectUnit(materialData.getMaterial().get(0).getUnit());
        filterDialog().selectOption(DataTest.Inactive, DataTest.Status);

        Assert.assertTrue(adminPage().helper.isElementVisible(By.xpath("//sup[@title=3]")), "The number of applied filters is not correct");
        helper.scrollToElementAtBottom(filterDialog().getBtnResetFilter());

        // Click reset filter
        adminPage().helper.clickElement(filterDialog().getBtnResetFilter());
    }

    @Test(testName = "FB-12618 : Verify that after clicking reset button, all the options will be reset to default")
    public void FB12618() {
        // Click on button filter to open filter dialog
        materialManagementPage().clickFilterButton();

        // Select option of all filters
        filterDialog().selectBranch(branchData.getBranch().get(0).getName());
        filterDialog().selectUnit(materialData.getMaterial().get(0).getUnit());
        filterDialog().selectOption(DataTest.Inactive, DataTest.Status);

        // Click reset filter
        adminPage().helper.clickElement(filterDialog().getBtnResetFilter());

        // Branch: Default is All branches
        Assert.assertEquals(filterDialog().getSelectedItem(DataTest.Branch), DataTest.All_branches, "The default branch is not correct");

        // Category: Default is All categories
        Assert.assertEquals(filterDialog().getSelectedItem(DataTest.Category), DataTest.All_categories, "The default category is not correct");

        // Unit: Default is All units
        Assert.assertEquals(filterDialog().getSelectedItem(DataTest.Unit), DataTest.All_units, "The default unit is not correct");

        // Status: Default is All
        Assert.assertTrue(helper.isElementVisible(filterDialog().getCheckIcon(DataTest.All, DataTest.Status)));
    }

    @Test(testName = "FB-12629 : Verify that the number of filters applied in the filter button remains visible after searching for the material name")
    public void FB12629() {
        // Click on button filter to open filter dialog
        materialManagementPage().clickFilterButton();

        // Select branch
        filterDialog().selectBranch(branchData.getBranch().get(0).getName());
        // After selecting branch, the verify the number of filter option applied
        Assert.assertTrue(adminPage().helper.isElementVisible(By.xpath("//sup[@title=1]")), "The number of applied filters is not correct");

        // Enter the material name
        materialManagementPage().enterMaterialName(materialData.getMaterial().get(0).getName());

        // Verify if the number of filter option applied
        Assert.assertTrue(adminPage().helper.isElementVisible(By.xpath("//sup[@title=1]")), "The number of applied filters is not correct/ The number of applied filters is hidden");
    }

    @Test(testName = "FB-12621 : Apply filters and check if the correct filtered results are displayed (Branch)")
    public void FB12621_1() {
        // Click on button filter to open filter dialog
        materialManagementPage().clickFilterButton();

        // Check data for branch
        String GoFoodAndBeverage_Branch = branchData.getBranch().get(1).getName();
        String Material_BelongToGoFoodAndBeverage_Branch = materialData.getMaterial().get(5).getName();
        String Material_NotBelongToGoFoodAndBeverage_Branch = materialData.getMaterial().get(0).getName();

        // Check data belong to the branch, and not belong to the branch
        filterDialog().selectBranch(GoFoodAndBeverage_Branch);
        materialManagementPage().verifyMaterialIsVisible(Material_BelongToGoFoodAndBeverage_Branch);
        materialManagementPage().verifyMaterialNotVisible(Material_NotBelongToGoFoodAndBeverage_Branch);

        // Click clear filter and verify if the data reload
        materialManagementPage().clickFilterButton();
        filterDialog().clickResetFilter();

        materialManagementPage().verifyMaterialIsVisible(Material_BelongToGoFoodAndBeverage_Branch);
        materialManagementPage().verifyMaterialIsVisible(Material_NotBelongToGoFoodAndBeverage_Branch);

        // Clear filter
        materialManagementPage().clickFilterButton();
        filterDialog().helper.clickElement(filterDialog().getBtnResetFilter());
    }

    @Test(testName = "FB-12621.2 : Apply filters and check if the correct filtered results are displayed (Unit)")
    public void FB12621_2() {
        // Click on button filter to open filter dialog
        materialManagementPage().clickFilterButton();

        // Check data for unit
        String gamUnit = materialData.getMaterial().get(2).getUnit();
        String material1_BelongTo_GamUnit = materialData.getMaterial().get(2).getName();
        String material2_BelongTo_GamUnit = materialData.getMaterial().get(3).getName();
        String material3_NotBelongTo_GamUnit = materialData.getMaterial().get(0).getName();

        filterDialog().selectUnit(gamUnit);

        // Check data belong to gamUnit, and the data not belong to gamUnit
        materialManagementPage().verifyMaterialIsVisible(material1_BelongTo_GamUnit);
        materialManagementPage().verifyMaterialIsVisible(material2_BelongTo_GamUnit);
        materialManagementPage().verifyMaterialNotVisible(material3_NotBelongTo_GamUnit);

        // Click clear filter and verify if the data reload
        materialManagementPage().clickFilterButton();
        filterDialog().helper.clickElement(filterDialog().getBtnResetFilter());

        materialManagementPage().verifyMaterialIsVisible(material1_BelongTo_GamUnit);
        materialManagementPage().verifyMaterialIsVisible(material2_BelongTo_GamUnit);
        materialManagementPage().verifyMaterialIsVisible(material3_NotBelongTo_GamUnit);
    }
}

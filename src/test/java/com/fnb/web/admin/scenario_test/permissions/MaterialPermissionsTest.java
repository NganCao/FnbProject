package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.configObject;

// Sprint52
public class MaterialPermissionsTest extends PermissionBaseTest {
    
    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staffDataTest.Staff2_Email;
        passwordStaff = staffDataTest.Staff2_Password;
        groupPermission = GroupPermission.Material;
        groupName = staffDataTest.OPTION_PERMISSION2;
        adminPage().navigateToHomePage(emailStaff, passwordStaff);
    }

    @Test(testName = "Check 'Export material' permission")
    public void FB1() {
        permission = Permission.Export_material;
        // Will full permission, check if staff can see 'Export' button
        homePage().siderBar.clickMnuItemInventory().clickIngredientsMenu();
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnExport()), "The 'Export' button is not visible while the staff have full permissions");

        // Admin will uncheck 'Export' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Export_material);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickIngredientsMenu();

        // Check permission
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnExport()), "The 'Export Material' button is visible while the staff do not have 'Export' permission");

        resetPermission();
    }

    @Test(testName = "Check 'Import material' permission")
    public void FB2() {
        permission = Permission.Import_material;
        // Will full permission, check if staff can see 'Import' button
        homePage().siderBar.clickMnuItemInventory().clickIngredientsMenu();
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnImport()), "The 'Import material' button is not visible while the staff have full permissions");

        // Admin will uncheck 'Import' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Import_material);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickIngredientsMenu();

        // Check permission
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnImport()), "The 'Import material' button is visible while the staff do not have 'Import' permission");

        resetPermission();
    }

    @Test(testName = "Check 'Activate material' permission")
    public void FB3() {
        permission = Permission.Activate_material;
        // Create a material (test data for this case)
        String materialName = createMaterialPage().createAMaterialWithAnyTypeData();

        // Click on created material
        materialManagementPage().clickOnMaterial(materialName);
        materialDetailPage()
                //.clickDropDownButton()
                .clickDeactivateButton();
        commonComponents().waitSuccessToast();
        commonComponents().waitSuccessToastHidden();

        // Navigate to material management page, click on product, and click dropdown button too check the 'Activate' material
        materialManagementPage().siderBar.clickIngredientsMenu();
        materialManagementPage().clickOnMaterial(materialName);
        //materialDetailPage().clickDropDownButton();

        // Verify that we can see the 'Active' button
        Assert.assertTrue(actionStaff.isElementVisible(materialDetailPage().getBtnActive()), "The 'Active' button is not visible while we have 'Active material' permission");

        // Admin will uncheck 'Activate material' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Activate_material);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickIngredientsMenu();

        // Navigate to product management page, click on product, and click dropdown button too check the 'Activate' product
        materialManagementPage()
                .clickOnMaterial(materialName);

        // Check if the staff can see the 'Activate' button (When staff does not have 'Activate' permission, the dropdown button is hidden)
        if (actionStaff.isElementVisible(materialDetailPage().getBtnDropDown())) {
            //materialDetailPage().clickDropDownButton();
            Assert.assertFalse(actionStaff.isElementVisible(materialDetailPage().getBtnActive()), "The 'Activate' button is still visible while the staff does not have this permission (Activate material)");
        }
        else {
            Assert.assertFalse(actionStaff.isElementVisible(materialDetailPage().getBtnActive()), "The 'Activate' button is still visible while the staff does not have this permission (Activate material)");
        }

        resetPermission();
    }

    // Bug: Bug 51553: [Admin][Material] Deactivate Material: The user can see the deactivate button even if they do not have the 'Deactivate Material' permission
    @Test(testName = "Check 'Deactivate material")
    public void FB4() {
        permission = Permission.Deactivate_material;
        // Create a material (test data for this case)
        String materialName = createMaterialPage().createAMaterialWithAnyTypeData();

        // Navigate to material management page, click on product, and click dropdown button too check the 'Deactivate' material
        materialManagementPage().siderBar.clickIngredientsMenu();
        materialManagementPage().clickOnMaterial(materialName);
        //materialDetailPage().clickDropDownButton();

        // Verify that we can see the 'Active' button
        Assert.assertTrue(actionStaff.isElementVisible(materialDetailPage().getBtnDeactivate()), "The 'Deactivate' button is not visible while we have 'Deactivate material' permission");

        // Admin will uncheck 'Deactivate material' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Deactivate_material);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().helper.navigateToUrl(configObject.getUrlMaterialManagement());

        // Navigate to product management page, click on product, and click dropdown button too check the 'Activate' product
        materialManagementPage()
                .clickOnMaterial(materialName);

        // Check if the staff can see the 'Deactivate' button (When staff does not have 'Deactivate' permission, the dropdown button is hidden)
        if (actionStaff.isElementVisible(materialDetailPage().getBtnDropDown())) {
            //materialDetailPage().clickDropDownButton();
            Assert.assertFalse(actionStaff.isElementVisible(materialDetailPage().getBtnDeactivate()), "The 'Deactivate' button is still visible while the staff does not have this permission (Deactivate material)");
        }
        else {
            Assert.assertFalse(actionStaff.isElementVisible(materialDetailPage().getBtnDeactivate()), "The 'Deactivate' button is still visible while the staff does not have this permission (Deactivate material)");
        }

        resetPermission();
    }
}

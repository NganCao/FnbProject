package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.*;

// Sprint55
public class TransferMaterialPermissionTest extends PermissionBaseTest {
    String BinhThanh_Branch;
    String GoFoodAndBeverage_Branch;
    String material;
    String unitOfMaterial;

    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staffDataTest.Staff2_Email;
        passwordStaff = staffDataTest.Staff2_Password;
        groupPermission = GroupPermission.Transfer_material;
        groupName = staffDataTest.OPTION_PERMISSION2;
        adminPage().navigateToHomePage(emailStaff, passwordStaff);

        // Data
        BinhThanh_Branch = branchData.getBranch().get(0).getName();
        GoFoodAndBeverage_Branch = branchData.getBranch().get(1).getName();
        material = materialData.getMaterial().get(0).getName();
        unitOfMaterial = materialData.getMaterial().get(0).getUnit();

        // Create a transfer material
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();
        transferMaterialManagementPage().clickAddNew();
        createTransferMaterialPage().createAnTransferMaterial(
                BinhThanh_Branch,
                GoFoodAndBeverage_Branch,
                material,
                "1",
                unitOfMaterial
        );
    }

    @Test(testName = "FB-14161 : Check 'View transfer material' permission")
    public void FB14161() {
        permission = Permission.View_transfer_material;
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();

        // Check if staff can see the 'Transfer Material' menu
        Assert.assertTrue(actionStaff.isElementVisible(inventoryControlPage().getTitleTransferIngredients()), "The staff can not see 'Transfer Ingredients' title while staff have full permission");

        // Admin will uncheck the 'View transfer material' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();

        inventoryControlPage().helper.clickElement(inventoryControlPage().getTabTransferMaterial());
        // Check permission
        Assert.assertFalse(actionStaff.isElementVisible(inventoryControlPage().getTitleTransferIngredients()), "The staff can not see 'Transfer Ingredients' title while staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-14162 : Check 'Create new transfer material' permission")
    public void FB14162() {
        permission = Permission.Create_new_transfer_material;
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();

        // Check if the staff can see the Add new button or not
        Assert.assertTrue(actionStaff.isElementVisible(transferMaterialManagementPage().getBtnAddNew()), "The staff can not see the add new button while they have this permission");

        transferMaterialManagementPage().clickAddNew();
        actionStaff.waitForUrl(configObject.getUrlCreateTransferMaterial());

        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();

        // Check new permission
        Assert.assertFalse(actionStaff.isElementVisible(transferMaterialManagementPage().getBtnAddNew()), "The staff still see the add new button while they do not have this permission");
        // Try to navigate directly
        actionStaff.navigateTo(configObject.getUrlCreateTransferMaterial());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "FB-14163 : Check 'Edit transfer material' permission")
    public void FB14163() {
        permission = Permission.Edit_transfer_material;
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();

        detailTransferMaterialPage = transferMaterialManagementPage().clickTheFirstTransferMaterial();
        detailTransferMaterialPage.clickSelectOption();

        // Check the staff can see the 'Edit' icon
        Assert.assertTrue(actionStaff.isElementVisible(detailTransferMaterialPage.getSelectionEdit()), "The 'Edit' selection is not visible");

        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();
        transferMaterialManagementPage.clickTheFirstTransferMaterial();
        detailTransferMaterialPage.clickSelectOption();

        // Check
        Assert.assertFalse(actionStaff.isElementVisible(detailTransferMaterialPage.getSelectionEdit()), "The 'Edit' selection is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-14164 : Check 'Cancel transfer material' permission")
    public void FB14164() {
        permission = Permission.Cancel_transfer_material;
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();

        detailTransferMaterialPage = transferMaterialManagementPage().clickTheFirstTransferMaterial();
        detailTransferMaterialPage.clickSelectOption();

        // Check the staff can see the 'Cancel' selection
        Assert.assertTrue(actionStaff.isElementVisible(detailTransferMaterialPage.getSelectionCancel()), "The 'Cancel' selection is not visible");

        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();
        transferMaterialManagementPage.clickTheFirstTransferMaterial();
        detailTransferMaterialPage.clickSelectOption();

        // Check
        Assert.assertFalse(actionStaff.isElementVisible(detailTransferMaterialPage.getSelectionCancel()), "The 'Cancel' selection is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-14165 : Check 'Approve transfer material' permission", priority = 1)
    public void FB14165() {
        permission = Permission.Approve_transfer_material;

        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();
        detailTransferMaterialPage = transferMaterialManagementPage.clickTheFirstTransferMaterial();
        Assert.assertTrue(actionStaff.isElementVisible(detailTransferMaterialPage.getBtnApprove()), "The 'Approve' button is not visible while the staff have full permission");

        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();

        // Re-check permission
        detailTransferMaterialPage = transferMaterialManagementPage().clickTheFirstTransferMaterial();
        Assert.assertFalse(actionStaff.isElementVisible(detailTransferMaterialPage.getBtnApprove()), "The 'Approve' button is still visible while the staff does not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-14166 : Check 'Deliver transfer material' permission", priority = 2)
    public void FB14166() {
        permission = Permission.Deliver_transfer_material;

        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();
        detailTransferMaterialPage = transferMaterialManagementPage.clickTheFirstTransferMaterial();
        detailTransferMaterialPage.clickApprove();

        Assert.assertTrue(actionStaff.isElementVisible(detailTransferMaterialPage.getBtnDeliver()), "The 'Deliver' button is not visible while the staff have full permission");

        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();

        // Re-check permission
        detailTransferMaterialPage = transferMaterialManagementPage().clickTheFirstTransferMaterial();
        Assert.assertFalse(actionStaff.isElementVisible(detailTransferMaterialPage.getBtnDeliver()), "The 'Deliver' button is still visible while the staff does not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-14167 : Check 'Complete transfer material' permission", priority = 3)
    public void FB14167() {
        permission = Permission.Complete_transfer_material;

        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();
        detailTransferMaterialPage = transferMaterialManagementPage.clickTheFirstTransferMaterial();
        if (actionStaff.isElementVisible(detailTransferMaterialPage.getBtnDeliver())) {
            detailTransferMaterialPage.clickDeliver();
        }
        else {
            detailTransferMaterialPage.clickApprove().clickDeliver();
        }

        Assert.assertTrue(actionStaff.isElementVisible(detailTransferMaterialPage.getBtnComplete()), "The 'Complete' button is not visible while the staff have full permission");

        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickTransferMaterialTab();
        // Re-check permission
        detailTransferMaterialPage = transferMaterialManagementPage().clickTheFirstTransferMaterial();
        Assert.assertFalse(actionStaff.isElementVisible(detailTransferMaterialPage.getBtnComplete()), "The 'Complete' button is still visible while the staff does not have this permission");

        resetPermission();
    }
}

package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.*;

public class PurchaseOrderPermissionTest extends PermissionBaseTest {
    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staffDataTest.Staff3_Email;
        passwordStaff = staffDataTest.Staff3_Password;
        groupPermission = GroupPermission.Purchase_order;
        groupName = staffDataTest.OPTION_PERMISSION3;
        adminPage().navigateToHomePage(emailStaff, passwordStaff);

        // Create an purchase order
        actionStaff.navigateToUrl(configObject.getUrlCreatePurchaseOrder());
        String supplier = supplierData.getSuppliers().get(0).getName();
        String destination = branchData.getBranch().get(1).getName();
        String material = materialData.getMaterial().get(0).getName();
        String importUnit = materialData.getMaterial().get(0).getUnit();

        createPurchaseOrderPage().createAnPurchaseOrder(supplier, destination, material, "2000", importUnit, "2000");
        adminPage().helper.refreshPage();
    }

    @Test(testName = "Check 'Approve purchase' permission")
    public void FB1() {
        permission = Permission.Approve_purchase;

        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        purchaseOrderManagementPage().clickTheFirstPurchaseOrder();
        Assert.assertTrue(actionStaff.isElementVisible(detailPurchaseOrderPage().getBtnApprove()), "The 'Approve' button is not visible while the staff have full permission");

        // Admin will uncheck 'Approve purchase' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();

        // Check permission if the staff can see the 'Approve' button
        purchaseOrderManagementPage().clickTheFirstPurchaseOrder();
        Assert.assertFalse(actionStaff.isElementVisible(detailPurchaseOrderPage().getBtnApprove()), "The 'Approve' button is still visible while the staff does not have 'Approve purchase' permission");

        resetPermission();
    }

    @Test(testName = "Check 'Create purchase' permission")
    public void FB2() {
        permission = Permission.Create_purchase;
        // Verify the staff can see the 'Add new' button
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The 'Add new' is not visible when the staff have full permissions");

        // Admin will uncheck 'Create purchase' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();

        // Verify the staff can see the 'Add new' button
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The 'Add new' is still visible when the staff does not have 'Create purchase' permission");

        resetPermission();
    }

    @Test(testName = "Check 'View purchase' permission")
    public void FB3() {
        permission = Permission.View_purchase;
        // Verify staff can see the 'Purchase' menu
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        inventoryControlPage().clickPurchaseOrderTab();
        Assert.assertTrue(actionStaff.isElementVisible(purchaseOrderManagementPage().getBtnAddNew()), "The 'View purchase' menu is not visible");

        // Admin will uncheck 'View purchase' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check permissions
        homePage().siderBar.clickMnuItemInventory().clickInventoryHistory();
        Assert.assertFalse(actionStaff.isElementVisible(inventoryControlPage().getTabPurchaseOrder()), "The 'View purchase' menu is still visible while the staff does not have permission");

        resetPermission();
    }

    @Test(testName = "Check 'Edit purchase' permission")
    public void FB4() {
        permission = Permission.Edit_purchase;
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        purchaseOrderManagementPage().clickTheFirstPurchaseOrder();
        detailPurchaseOrderPage().clickSelectOption();
        // Check the staff can see the 'Edit' icon
        Assert.assertTrue(actionStaff.isElementVisible(detailPurchaseOrderPage().getEditIcon()), "The 'Edit' icon is not visible");

        // Admin will uncheck 'Edit purchase' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check permissions
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        purchaseOrderManagementPage().clickTheFirstPurchaseOrder();
        detailPurchaseOrderPage().clickSelectOption();
        // Check the staff can see the 'Edit' icon
        Assert.assertFalse(actionStaff.isElementVisible(detailPurchaseOrderPage().getEditIcon()), "The 'Edit' icon is still visible while the staff does not have 'Edit purchase'");

        resetPermission();
    }

    @Test(testName = "Check 'Cancel purchase' permission")
    public void FB5() {
        permission = Permission.Cancel_purchase;
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        purchaseOrderManagementPage().clickTheFirstPurchaseOrder();
        detailPurchaseOrderPage().clickSelectOption();
        // Check the staff can see the 'Cancel' icon button
        Assert.assertTrue(actionStaff.isElementVisible(detailPurchaseOrderPage().getBtnCancel()), "The 'Cancel' icon is not visible");

        // Admin will uncheck 'Cancel purchase' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check permissions
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        purchaseOrderManagementPage().clickTheFirstPurchaseOrder();
        detailPurchaseOrderPage().clickSelectOption();
        // Check the staff can see the 'Cancel' icon
        Assert.assertFalse(actionStaff.isElementVisible(detailPurchaseOrderPage().getBtnCancel()), "The 'Cancel' icon is still visible while the staff does not have 'Cancel purchase'");

        resetPermission();
    }

    @Test(testName = "Check 'Import goods' permission")
    public void FB6() {
        permission = Permission.Import_goods;
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();
        // Verify the staff can see the 'Import' button
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnImport()), "The 'Import' button is not visible while the staff have full permissions");

        // Admin will uncheck 'Import goods' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickInventoryControl();

        // Check permission
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnImport()), "The 'Import' button is still visible while the staff does not have 'Import goods' permission");

        resetPermission();
    }
}

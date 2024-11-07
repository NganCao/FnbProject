package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.configObject;
import static com.fnb.web.setup.Setup.supplierData;

public class SupplierPermissionsTest extends PermissionBaseTest {
    
    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staffDataTest.Staff2_Email;
        passwordStaff = staffDataTest.Staff2_Password;
        groupPermission = GroupPermission.Supplier;
        groupName = staffDataTest.OPTION_PERMISSION2;
        adminPage().navigateToHomePage(emailStaff, passwordStaff);
    }

    @Test(testName = "Check 'View supplier' permission")
    public void FB1() {
        permission = Permission.View_supplier;

        // Check if staff can navigate to supplier management
        homePage().siderBar.clickMnuItemInventory();
        Assert.assertTrue(actionStaff.isElementVisible(homePage().siderBar.getMnuItemSupplier()), "The staff can not see 'Supplier' menu while staff have full permission");
        homePage().siderBar.clickSupplier();
        actionStaff.waitForUrl(configObject.getUrlSupplierManagement());

        actionStaff.navigateToUrl(configObject.getUrlSupplierManagement());

        // Admin will uncheck 'View supplier' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check if the staff can see the 'Supplier' menu and if the staff can navigate to Supplier management
        homePage().siderBar.clickMnuItemInventory();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemSupplier()), "The staff still see 'Supplier' menu while staff does not have 'View supplier' permission");

        actionStaff.navigateTo(configObject.getUrlSupplierManagement());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "Check 'Delete supplier' permission")
    public void FB2() {
        permission = Permission.Delete_supplier;

        String supplier = supplierData.getSuppliers().get(0).getName();
        homePage().siderBar.clickMnuItemInventory().clickSupplier();
        // Check if there is a 'Delete supplier' icon
        commonComponents().selectItem(supplier);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is not visible while the staff have full permissions");

        // Admin will uncheck 'Delete supplier' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check if the staff can see the 'Supplier' menu and if the staff can navigate to Supplier management
        homePage().siderBar.clickMnuItemInventory().clickSupplier();
        commonComponents().selectItem(supplier);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is still visible while the staff does not have 'Delete supplier' permission");

        resetPermission();
    }

    @Test(testName = "Check 'Edit supplier' permission")
    public void FB3() {
        permission = Permission.Edit_supplier;

        String supplier = supplierData.getSuppliers().get(0).getName();
        homePage().siderBar.clickMnuItemInventory().clickSupplier();
        commonComponents().selectItem(supplier);
        // Check if there is a 'Edit supplier' icon
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit' icon is not visible while the staff have full permissions");

        // Admin will uncheck 'Delete supplier' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check if the staff can see the 'Supplier' menu and if the staff can navigate to Supplier management
        homePage().siderBar.clickMnuItemInventory().clickSupplier();
        commonComponents().selectItem(supplier);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit' icon is still visible while the staff does not have 'Edit supplier' permission");

        resetPermission();
    }

    @Test(testName = "Check 'Create supplier' permission")
    public void FB4() {
        permission = Permission.Create_supplier;

        homePage().siderBar.clickMnuItemInventory().clickSupplier();
        Assert.assertTrue(actionStaff.isElementVisible(supplierManagementPage().getBtnAddNew()), "The 'Add New' button is not visible when the staff have full permissions");

        // Admin will uncheck 'Create supplier' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check the permission
        homePage().siderBar.clickMnuItemInventory().clickSupplier();
        Assert.assertFalse(actionStaff.isElementVisible(supplierManagementPage().getBtnAddNew()), "The 'Add New' button is still visible when the staff does not have 'Create supplier' permission");

        resetPermission();
    }
}

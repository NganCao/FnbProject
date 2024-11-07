package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.*;

public class CategoryPermissionsTest extends PermissionBaseTest {
    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staffDataTest.Staff4_Email;
        passwordStaff = staffDataTest.Staff4_Password;
        groupPermission = GroupPermission.Category;
        groupName = staffDataTest.OPTION_PERMISSION4;
        adminPage().navigateToHomePage(emailStaff, passwordStaff);
    }

    @Test(testName = "Check 'Edit product category' permission")
    public void FB1() {
        permission = Permission.Edit_product_category;
        homePage().siderBar.clickMnuItemProduct().clickProductCategory();

        // Check if there is a 'Delete' icon
        String category = productCategoryData.getProductCategory().get(0).getName();
        commonComponents().selectItem(category);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit' icon is not visible while the staff have full permissions");

        // Admin will uncheck 'Edit product category' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemProduct().clickProductCategory();

        // Check if the staff can see the 'Delete' icon
        commonComponents().selectItem(category);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit' icon is still visible while the staff does not have 'Edit product category' permission");

        resetPermission();
    }

    @Test(testName = "Check 'Edit material category' permission")
    public void FB2() {
        permission = Permission.Edit_material_category;
        homePage().siderBar.clickMnuItemInventory().clickMaterialCategory();

        // Check if there is a 'Delete' icon
        String category = materialCategoryData.getMaterialCategories().get(0).getName();
        commonComponents().selectItem(category);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit material category' icon is not visible while the staff have full permissions");

        // Admin will uncheck 'Edit product category' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickMaterialCategory();
        commonComponents().selectItem(category);

        // Check if the staff can see the 'Delete' icon
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit material category' icon is still visible while the staff does not have 'Edit material category' permission");

        resetPermission();
    }

    @Test(testName = "Check 'Create_product_category' permission")
    public void FB3() {
        permission = Permission.Create_product_category;
        homePage().siderBar.clickMnuItemProduct().clickProductCategory();

        // Check if there is a 'Add new' button
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The 'Add new' button is not visible while the staff have full permissions");

        // Admin will uncheck 'Create product category' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemProduct().clickProductCategory();

        // Check if there is a 'Add new' button
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The 'Add new' button is still visible while the staff does not have 'Create product category' permission");

        resetPermission();
    }

    @Test(testName = "Check 'Create material category' permission")
    public void FB4() {
        permission = Permission.Create_material_category;
        homePage().siderBar.clickMnuItemInventory().clickMaterialCategory();

        // Check if there is a 'Add new' button
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The 'Add new' button is not visible while the staff have full permissions");

        // Admin will uncheck 'Create product category' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickMaterialCategory();

        // Check if there is a 'Add new' button
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The 'Add new' button is still visible while the staff does not have 'Create material category' permission");

        resetPermission();
    }

    @Test(testName = "Check 'View product category' permission")
    public void FB5() {
        permission = Permission.View_product_category;
        // Check if the staff can click the 'Category' menu and can navigate to 'Product Category' url
        homePage().siderBar.clickMnuItemProduct();
        Assert.assertTrue(actionStaff.isElementVisible(homePage().siderBar.getMnuItemProductCategory()), "The product category is not visible");

        homePage().siderBar.clickProductCategory();
        actionStaff.waitForUrl(configObject.getUrlProductCategoryManagement());

        // Admin will uncheck 'View product category' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check permission
        homePage().siderBar.clickMnuItemProduct();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemProductCategory()), "The product category is still visible while the staff does not have 'View product category' permission");

        homePage().helper.navigateTo(configObject.getUrlProductCategoryManagement());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "Check 'View material category' permission")
    public void FB6() {
        permission = Permission.View_material_category;

        // Check if the staff can click the 'Category' menu and can navigate to 'Product Category' url
        homePage().siderBar.clickMnuItemInventory();
        Assert.assertTrue(actionStaff.isElementVisible(homePage().siderBar.getMnuItemMaterialCategory()), "The material category menu is not visible");

        homePage().siderBar.clickMaterialCategory();
        actionStaff.waitForUrl(configObject.getUrlMaterialCategoryManagement());

        // Admin will uncheck 'View material category' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check permission
        homePage().siderBar.clickMnuItemInventory();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemMaterialCategory()), "The material category menu is still visible while the staff does not have 'View material category' permission");

        homePage().helper.navigateTo(configObject.getUrlMaterialCategoryManagement());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "Check 'Delete material category' permission")
    public void FB7() {
        permission = Permission.Delete_material_category;
        homePage().siderBar.clickMnuItemInventory().clickMaterialCategory();

        // Check if there is a 'Delete' icon
        String category = materialCategoryData.getMaterialCategories().get(0).getName();
        commonComponents().selectItem(category);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete material category' icon is not visible while the staff have full permissions");

        // Admin will uncheck 'Edit product category' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemInventory().clickMaterialCategory();
        commonComponents().selectItem(category);

        // Check if the staff can see the 'Delete' icon
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete material category' icon is still visible while the staff does not have 'Delete material category' permission");

        resetPermission();
    }

    @Test(testName = "Check 'Delete product category' permission")
    public void FB8() {
        permission = Permission.Delete_product_category;
        homePage().siderBar.clickMnuItemProduct().clickProductCategory();

        // Check if there is a 'Delete' icon
        String category = productCategoryData.getProductCategory().get(0).getName();
        commonComponents().selectItem(category);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete product category' icon is not visible while the staff have full permissions");

        // Admin will uncheck 'Delete product category' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemProduct().clickProductCategory();
        commonComponents().selectItem(category);

        // Check if the staff can see the 'Delete' icon
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete product category' icon is still visible while the staff does not have 'Delete product category' permission");

        resetPermission();
    }
}

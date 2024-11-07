package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.*;

// Sprint52
public class ProductPermissionsTest extends PermissionBaseTest {

    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staffDataTest.Staff1_Email;
        passwordStaff = staffDataTest.Staff1_Password;
        groupPermission = GroupPermission.Product;
        groupName = staffDataTest.OPTION_PERMISSION1;
        adminPage().navigateToHomePage(emailStaff, passwordStaff);
    }

    @Test(testName = "Check 'Create product' permission")
    public void FB1() {
        permission = Permission.Create_product;
        homePage().siderBar.clickMnuItemProduct().clickManagement();

        // Check if there is a 'Add new' product
        actionStaff.waitForElementVisible(commonComponents().getBtnAddNew(), "The add new button is not visible when the staff have the permission");

        // Admin will uncheck 'Create product' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Create_product);

        // A fter permission is updated, the staff will try to click 'Add New' button
        productManagementPage().clickAddNewButton();
        productManagementPage().clickAddNewFoodOrBeverage();
        productManagementPage().helper.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemProduct().clickManagement();

        // Check if the staff see the 'Add new' product
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The create product is visible while the staff does not have that permission");

        resetPermission();
    }

    @Test(testName = "Check 'Delete option' permission")
    public void FB2() {
        permission = Permission.Delete_option;
        homePage().siderBar.clickMnuItemProduct().clickOptionMenu();

        // Check if there is a 'Delete' icon
        String option = optionData.getOptions().get(0).getName();
        commonComponents().selectItem(option);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The delete icon is not visible when the staff have full permissions");
        // Admin will uncheck 'Delete option' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Delete_option);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemProduct().clickOptionMenu();

        // Check if the staff can see the 'Delete' icon or not
        commonComponents().selectItem(option);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The delete icon is still visible when the staff does not have 'Delete option' permission");

        resetPermission();
    }

    @Test(testName = "Check 'Create combo' permission")
    public void FB3() {
        permission = Permission.Create_combo;
        homePage().siderBar.clickMnuItemProduct().clickComboMenu();

        // Check if there is a 'Add new' combo in Combo Management page
        actionStaff.waitForElementVisible(commonComponents().getBtnAddNew(), "The add new button is not visible when the staff have the 'Create combo' permission");

        // Admin will uncheck 'Create product' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Create_combo);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemProduct().clickComboMenu();

        // Check if the staff can see the 'Add new' icon or not
        actionStaff.waitForElementInVisible(commonComponents().getBtnAddNew(), "The add new button is visible when the staff does not have the 'Create combo' permission");

        resetPermission();
    }

    @Test(testName = "Check 'Edit combo' and 'Delete combo'  permission")
    public void FB4() {
        permission = Permission.Edit_combo;
        // Prepare a schedule Combo
        String[] productName = {productData.getProduct().get(2).getName()};
        String comboName = createComboPage().createAScheduleComboWithAnyTypeData(productName);

        commonComponents().selectItem(comboName);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit' icon is not visible");

        // Admin will uncheck 'Edit combo' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Edit_combo);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemProduct().clickComboMenu();

        // Check if the staff can see the 'Edit' icon or not
        commonComponents().selectItem(comboName);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'delete' icon is still visible while the staff does not have 'Edit combo' permission");

        // Admin will uncheck 'Edit combo' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Delete_combo);

        // Staff will log out and login back to get new permission
        commonComponents().logout();
        adminPage().navigateToHomePage(emailStaff, passwordStaff);
        homePage().siderBar.clickMnuItemProduct().clickComboMenu();

        // Staff has been set unchecked both edit and delete Combo, so both edit and delete icon is not visible
        commonComponents().selectItem(comboName);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete and edit' icon is still visible while the staff does not have 'Edit combo and Delete combo' permission");
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'delete and edit' icon is still visible while the staff does not have 'Edit combo and Delete combo' permission");

        staffManagementPage_admin
                .clickPermissionGroupTab()
                .clickGroupName(groupName)
                .checkAPermission(groupPermission, Permission.Delete_combo)
                .clickUpdate();

        resetPermission();
    }

    @Test(testName = "Check 'Activate product' permission")
    public void FB5() {
        permission = Permission.Active_product;
        // Create a product data
        String productName = createProductPage().createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());

        // Click on created product
        productManagementPage().clickOnProduct(productName);
        productDetailsPage()
                //.clickDropDownButton()
                .clickDeactivateButton();
        commonComponents().waitSuccessToast();
        commonComponents().waitSuccessToastHidden();;

        // Navigate to product management page, click on product, and click dropdown button too check the 'Activate' product
        productDetailsPage().siderBar.clickManagement();
        productManagementPage()
                .clickOnProduct(productName);
                //.clickDropDownButton();

        // Verify that we can see the 'Active' button
        Assert.assertTrue(actionStaff.isElementVisible(productDetailsPage().getBtnActive()), "The 'Active' button is not visible while we have 'Active product' permission");

        // Admin will uncheck 'Activate product' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Active_product);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemProduct().clickManagement();

        // Navigate to product management page, click on product, and click dropdown button too check the 'Activate' product
        productManagementPage()
                .clickOnProduct(productName);

        // Check if the staff can see the 'Activate' button (When staff does not have 'Activate' permission, the dropdown button is hidden)
        //Assert.assertFalse(actionStaff.isElementVisible(productDetailsPage().getBtnDropDown()), "The dropdown button is still visible");
        Assert.assertFalse(actionStaff.isElementVisible(productDetailsPage().getBtnActive()), "The 'Activate' button is still visible while the staff does not have this permission");

        resetPermission();
    }

    @Test(testName = "Check 'Deactivate product' permission")
    // Bug: Bug 51277: [Admin][Product] Deactivate Product: The user can deactivate a product even if they do not have the 'Activate' permission
    public void FB6() {
        permission = Permission.Deactivate_product;
        // Create a product data
        String productName = createProductPage().createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());

        // Navigate to product management page, click on product, and click dropdown button too check the 'Deactivate' product
        productManagementPage()
                .clickOnProduct(productName);

        // Verify that we can see the 'Active' button
        Assert.assertTrue(actionStaff.isElementVisible(productDetailsPage().getBtnDeactivate()), "The 'Deactivate' button is not visible while we have 'Deactivate product' permission");

        // Admin will uncheck 'Deactivate product' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Deactivate_product);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemProduct().clickManagement();

        // Navigate to product management page, click on product, and click dropdown button too check the 'Activate' product
        productManagementPage()
                .clickOnProduct(productName);

        // Check if the staff can see the 'Activate' button (When staff does not have 'Activate' permission, the dropdown button is hidden)
        Assert.assertFalse(actionStaff.isElementVisible(productDetailsPage().getBtnDeactivate()), "The 'Deactivate' button is still visible while the staff does not have this permission");

        resetPermission();
    }


    @Test(testName = "Check 'Edit option' permission")
    public void FB7() {
        permission = Permission.Edit_option;
        homePage().siderBar.clickMnuItemProduct().clickOptionMenu();

        // Check if there is 'Edit' icon
        commonComponents().selectItem(optionData.getOptions().get(0).getName());
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'Edit' icon is not visible while the staff have full permissions");

        // Admin will uncheck 'Edit option' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Edit_option);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemProduct().clickOptionMenu();

        // Check if the staff can see the 'Edit' icon or not
        commonComponents().selectItem(optionData.getOptions().get(0).getName());
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'Edit' icon is not visible while the staff does not have full 'Edit option' permission");

        resetPermission();
    }

    @Test(testName = "Check 'View combo' permission")
    public void FB8() {
        permission = Permission.View_combo;
        // With full permission, staff will check if they can click on 'Combo' menu and navigate to combo management page or not
        homePage().siderBar.clickMnuItemProduct().clickComboMenu();
        homePage().helper.waitForUrl(configObject.getUrlComboManagement());

        homePage().helper.navigateToUrl(configObject.getUrlComboManagement());

        // Admin will uncheck 'View combo' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.View_combo);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check the permission
        // Check the menu Combo
        homePage().siderBar.clickMnuItemProduct();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemCombo()), "The combo menu is still visible while they do not have 'View combo' permission");

        // Try to navigate to combo management page
        homePage().helper.navigateTo(configObject.getUrlComboManagement());
        homePage().helper.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }


    @Test(testName = "Check 'Create option' permission")
    public void FB9() {
        permission = Permission.Create_option;
        // With full permission, the staff can see the 'Add new' button
        homePage().siderBar.clickMnuItemProduct().clickOptionMenu();
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The 'Add new' button is not visible while they have full permissions");

        // Admin will uncheck 'Create option' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Create_option);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemProduct().clickOptionMenu();

        // Check permission
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The 'Add new' button is visible while they do not have 'Create option'");

        resetPermission();
    }

    @Test(testName = "Check 'View option' permission")
    public void FB10() {
        permission = Permission.View_option;
        // With full permission, staff will check if they can click on 'Option' menu and navigate to option management page or not
        homePage().siderBar.clickMnuItemProduct().clickOptionMenu();
        homePage().helper.waitForUrl(configObject.getUrlOptionManagement());

        homePage().helper.navigateToUrl(configObject.getUrlComboManagement());

        // Admin will uncheck 'View option' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.View_option);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check the permission
        // Check the menu Combo
        homePage().siderBar.clickMnuItemProduct();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemOption()), "The option menu is still visible while they do not have 'View option' permission");

        // Try to navigate to combo management page
        homePage().helper.navigateTo(configObject.getUrlOptionManagement());
        homePage().helper.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "Check 'Stop combo' permission")
    public void FB12() {
        permission = Permission.Stop_combo;
        // Prepare a schedule Combo
        String[] productName = {productData.getProduct().get(2).getName()};
        String comboName = createComboPage().createAActiveComboWithAnyTypeData(productName);

        // With full permissions, verify that staff can see the 'Stop combo' icon
        commonComponents().selectItem(comboName);
        Assert.assertTrue(actionStaff.isElementVisible(comboManagementPage().getStopComboIcon()), "The stop combo icon is not visible when staff have full permissions");

        // Admin will uncheck 'Stop combo' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.Stop_combo);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check permission
        homePage().siderBar.clickMnuItemProduct().clickComboMenu();
        commonComponents().selectItem(comboName);
        Assert.assertFalse(actionStaff.isElementVisible(comboManagementPage().getStopComboIcon()), "The stop combo icon is visible when staff does not have 'Stop combo' permission");

        resetPermission();
    }
}

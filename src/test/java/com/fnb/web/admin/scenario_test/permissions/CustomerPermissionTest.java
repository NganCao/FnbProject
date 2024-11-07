package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.*;

public class CustomerPermissionTest extends PermissionBaseTest {
    
    private String customerName;
    private String memberShip = "Silver member";

    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        customerName = customerData.getCustomers().get(0).getFirstName() + " " + customerData.getCustomers().get(0).getLastName();
        emailStaff = staffDataTest.Staff2_Email;
        passwordStaff = staffDataTest.Staff2_Password;
        groupPermission = GroupPermission.Customer;
        groupName = staffDataTest.OPTION_PERMISSION2;
        adminPage().navigateToHomePage(emailStaff, passwordStaff);
    }

    @Test(testName = "FB-13829 : Check 'View customer' permission")
    public void FB13829() {
        permission = Permission.View_customer;

        // Check if the staff can navigate to customer
        homePage().siderBar.clickMnuCRM();
        Assert.assertTrue(actionStaff.isElementVisible(homePage().siderBar.getMnuItemCRM_Customer()), "The staff can not see 'Customer' menu while staff have full permission");
        actionStaff.navigateToUrl(configObject.getUrlCustomerManagement());

        // Admin will uncheck 'View customer' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check if the staff can see the 'Customer' menu and if the staff can navigate to Customer management
        homePage().siderBar.clickMnuCRM();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemCRM_Customer()), "The staff still see 'Customer' menu while staff do not have this permission");

        actionStaff.navigateTo(configObject.getUrlCustomerManagement());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "FB-13847 : Check 'Delete customer' permission")
    public void FB13847() {
        permission = Permission.Delete_customer;
        homePage().siderBar.clickMnuCRM();

        // Check if the staff can see the 'Delete' icon or not
        commonComponents().searchItem(customerName);
        commonComponents().selectItem(customerName);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is not visible while the staff have full permissions");

        // Click delete to check if we can see the Delete dialog
        actionStaff.clickElement(commonComponents().getBtnDeleteIcon());
        actionStaff.verifIfTheTextVisible(adminLocalization.getLeaveDialog().getConfirmDelete());
        actionStaff.clickElement(commonComponents().getBtnIgnore());

        // Admin will uncheck 'Delete customer' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuCRM();

        // Check will check the permission again by checking the delete icon
        actionStaff.clickElement(commonComponents().getSearchIcon());
        actionStaff.enterText(commonComponents().getTxtSearch(), customerName);
        commonComponents().selectItem(customerName);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13848 : Check 'Create customer' permission")
    public void FB13848() {
        permission = Permission.Create_customer;
        homePage().siderBar.clickMnuCRM();

        // Check if the staff can see the Add new button or not
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The staff can not see the add new butotn while they have this permission");
        // Click add new button and verify the url
        actionStaff.clickElement(commonComponents().getBtnAddNew());
        actionStaff.waitForUrl(configObject.getUrlCreateCustomer());
        // Try to navigate directly by url
        actionStaff.navigateToUrl(configObject.getUrlCreateCustomer());

        createNewCustomerPage().siderBar.clickCustomerMenu();

        // Admin will uncheck the 'Create customer' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuCRM();

        // Check the 'Create new' permission
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The staff still see the add new button while they do not have this permission");

        actionStaff.navigateTo(configObject.getUrlCreateCustomer());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "FB-13849 : Check 'Edit customer' permission")
    public void FB13849() {
        permission = Permission.Edit_customer;
        homePage().siderBar.clickMnuCRM();

        // Check if the staff can edit the customer
        commonComponents().searchItem(customerName);
        commonComponents().selectItem(customerName);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The staff can not see edit icon while they have this permission");

        // Try to click edit button
        actionStaff.clickElement(commonComponents().getBtnEditIcon());
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnUpdate()), "Can not navigate to edit customer url");

        // Admin will uncheck the 'Edit customer' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuCRM();

        // Check
        commonComponents().searchItem(customerName);
        commonComponents().selectItem(customerName);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The staff still see edit icon while they do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13850 : Check 'Create membership level' permission")
    public void FB13850() {
        permission = Permission.Create_membership_level;
        homePage().siderBar.clickMnuCRM().clickMembership();

        // Check if the staff can see the Add new button or not
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The staff can not see the add new butotn while they have this permission");
        // Click add new button and verify the url
        actionStaff.clickElement(commonComponents().getBtnAddNew());
        actionStaff.waitForUrl(configObject.getUrlCreateMembership());
        // Try to navigate directly by url
        actionStaff.navigateToUrl(configObject.getUrlCreateMembership());

        // Admin will uncheck the 'Create membership level' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuCRM().clickMembership();

        // Check the 'Create membership level' permission
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The staff still see the add new button while they do not have this permission");
        // Try to navigate directly
        actionStaff.navigateTo(configObject.getUrlCreateMembership());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "FB-13851 : Check 'View loyalty point' permission")
    public void FB13851() {
        permission = Permission.View_loyalty_point;
        homePage().siderBar.clickMnuCRM();

        // Check if the staff can see the point configuration
        Assert.assertTrue(actionStaff.isElementVisible(customerManagementPage().siderBar.getMnuItemPointConfiguration()), "The staff can not see the 'point configuration' while they have this permission to view");
        // Try to click the menu
        customerManagementPage().siderBar.clickPointConfiguration();
        actionStaff.waitForUrl(configObject.getUrlPointConfiguration());

        // Admin will uncheck the 'Create membership level' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuCRM();

        // Check
        Assert.assertFalse(actionStaff.isElementVisible(customerManagementPage().siderBar.getMnuItemPointConfiguration()), "The staff still see the 'point configuration' tab while they do not have this permission to view");

        resetPermission();
    }

    @Test(testName = "FB-13852 : Check 'Create segment' permission")
    public void FB13852() {
        permission = Permission.Create_segment;
        homePage().siderBar.clickMnuCRM().clickCustomerSegment();

        // Check if the staff can see the Add new button or not
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The staff can not see the add new butotn while they have this permission");
        // Click add new button and verify the url
        actionStaff.clickElement(commonComponents().getBtnAddNew());
        actionStaff.waitForUrl(configObject.getUrlCreateCustomerSegment());
        // Try to navigate directly by url
        actionStaff.navigateToUrl(configObject.getUrlCreateCustomerSegment());

        // Admin will uncheck the 'Create membership level' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuCRM().clickCustomerSegment();

        // Check the 'Create membership level' permission
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The staff still see the add new button while they do not have this permission");
        // Try to navigate directly
        actionStaff.navigateTo(configObject.getUrlCreateCustomerSegment());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "FB-13853 : Check 'Edit segment' permission")
    public void FB13853() {
        permission = Permission.Edit_segment;

        // Create a customer segment
        String customerSegment = createCustomerSegmentPage().createAnyKindOfCustomerSegment();

        // Check if the staff can edit the customer
        commonComponents().searchItem(customerSegment);
        commonComponents().selectItem(customerSegment);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The staff can not see edit icon while they have this permission");

        // Try to click edit button
        actionStaff.clickElement(commonComponents().getBtnEditIcon());
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnSave()), "Can not navigate to edit customer segment url");

        // Admin will uncheck the 'Edit customer' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuCRM().clickCustomerSegment();

        // Check
        commonComponents().searchItem(customerSegment);
        commonComponents().selectItem(customerSegment);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The staff still see edit icon while they do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13889 : Check 'Delete segment' permission")
    public void FB13889() {
        permission = Permission.Delete_segment;

        // Create a customer segment
        String customerSegment = createCustomerSegmentPage().createAnyKindOfCustomerSegment();

        homePage().siderBar.clickMnuCRM().clickCustomerSegment();

        // Check if the staff can see the 'Delete' icon or not
        commonComponents().selectItem(customerSegment);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is not visible while the staff have this permission");

        // Click delete to check if we can see the Delete dialog
        actionStaff.clickElement(commonComponents().getBtnDeleteIcon());
        actionStaff.verifIfTheTextVisible(adminLocalization.getLeaveDialog().getConfirmDelete());
        actionStaff.clickElement(commonComponents().getBtnIgnore());

        // Admin will uncheck 'Delete customer' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuCRM().clickCustomerSegment();

        // Check will check the permission again by checking the delete icon
        commonComponents().selectItem(customerSegment);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13854 : Check 'View segment' permission")
    public void FB13854() {
        permission = Permission.View_segment;

        // Check if the staff can navigate to customer
        homePage().siderBar.clickMnuCRM();
        Assert.assertTrue(actionStaff.isElementVisible(homePage().siderBar.getMnuItemCustomerSegment()), "The staff can not see 'Customer Segment' menu while staff have full permission");
        actionStaff.navigateToUrl(configObject.getUrlCustomerSegmentManagement());

        // Admin will uncheck 'View customer' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check if the staff can see the 'Customer' menu and if the staff can navigate to Customer management
        homePage().siderBar.clickMnuCRM();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemCustomerSegment()), "The staff still see 'Customer Segment' menu while staff do not have this permission");

        actionStaff.navigateTo(configObject.getUrlCustomerSegmentManagement());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "FB-13882 : Check 'Delete membership level' permission")
    public void FB13882() {
        // String memberShip = createMembershipPage().createAnyKindOfDataMembershipLevel();

        permission = Permission.Delete_membership_level;
        homePage().siderBar.clickMnuCRM().clickMembership();

        // Check if the staff can see the 'Delete' icon or not
        commonComponents().selectItem(memberShip);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is not visible while the staff have this permission");

        // Click delete to check if we can see the Delete dialog
        actionStaff.clickElement(commonComponents().getBtnDeleteIcon());
        actionStaff.verifIfTheTextVisible(adminLocalization.getLeaveDialog().getConfirmDelete());
        actionStaff.clickElement(commonComponents().getBtnIgnore());

        // Admin will uncheck 'Delete customer' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuCRM().clickMembership();

        // Check will check the permission again by checking the delete icon
        commonComponents().selectItem(memberShip);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13883 : Check 'Edit membership level' permission")
    public void FB13883() {
        permission = Permission.Edit_membership_level;
        homePage().siderBar.clickMnuCRM().clickMembership();

        // Check if the staff can edit the customer
        commonComponents().selectItem(memberShip);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The staff can not see edit icon while they have this permission");

        // Try to click edit button
        actionStaff.clickElement(commonComponents().getBtnEditIcon());
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnSave()), "Can not navigate to edit membership url");

        // Admin will uncheck the 'Edit customer' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuCRM().clickMembership();

        // Check
        commonComponents().selectItem(memberShip);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The staff still see edit icon while they do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13884 : Check 'View membership level' permission")
    public void FB13884() {
        permission = Permission.View_membership_level;

        // Check if the staff can navigate to customer
        homePage().siderBar.clickMnuCRM();
        Assert.assertTrue(actionStaff.isElementVisible(homePage().siderBar.getMnuItemMembership()), "The staff can not see 'Membership' menu while staff have this permission");
        actionStaff.navigateToUrl(configObject.getUrlMembership());

        // Admin will uncheck 'View customer' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check if the staff can see the 'Customer' menu and if the staff can navigate to Customer management
        homePage().siderBar.clickMnuCRM();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemMembership()), "The staff still see 'Membership' menu while staff do not have this permission");

        actionStaff.navigateTo(configObject.getUrlMembership());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }
}

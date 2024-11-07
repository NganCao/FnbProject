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
public class ReportPermissionsTest extends PermissionBaseTest {

    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staffDataTest.Staff3_Email;
        passwordStaff = staffDataTest.Staff3_Password;
        groupPermission = GroupPermission.Report;
        groupName = staffDataTest.OPTION_PERMISSION3;
        adminPage().navigateToHomePage(emailStaff, passwordStaff);
    }

    @Test(testName = "Check 'View revenue report' permission")
    public void FB1() {
        permission = Permission.View_revenue_report;

        // Verify with full permission, the staff can click on 'Revenue' menu and can navigate to 'Revenue' page
        homePage().siderBar.clickReportMenu().clickRevenueMenu();
        actionStaff.waitForUrl(configObject.getUrlReportRevenuePage());
        actionStaff.navigateToUrl(configObject.getUrlReportRevenuePage());

        // Admin will uncheck 'View revenue report' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.View_revenue_report);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check if the staff can see the 'Revenue' icon or not
        // Verify icon
        homePage().siderBar.clickReportMenu();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemRevenue()), "The 'Revenue' icon is visible while the staff does not have 'View revenue report' permission");
        // Verify by navigating
        homePage().helper.navigateTo(configObject.getUrlReportRevenuePage());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "Check 'View shift report' permission")
    public void FB2() {
        permission = Permission.View_shift_report;
        // Verify with full permission, the staff can see the shift tab or not
        homePage().helper.navigateToUrl(configObject.getUrlReportTransaction());
        Assert.assertTrue(actionStaff.isElementVisible(transactionPage().getTabShift()), "The 'Shift' tab is not visible while the staff have full permissions");

        // Admin will uncheck 'View shift report' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, Permission.View_shift_report);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check permission
        homePage().siderBar.clickReportMenu().clickTransactionMenu();
        Assert.assertFalse(actionStaff.isElementVisible(transactionPage().getTabShift()), "The 'Shift' tab is still visible while the staff does not have full permission (View shift report)");

        resetPermission();
    }

    @Test(testName = "Check 'Export revenue report' permission")
    public void FB3() {
        permission = Permission.Export_revenue_report;
        // Verify with full permission, the staff can see the export button or not
        homePage().helper.navigateToUrl(configObject.getUrlReportRevenuePage());
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnExport()), "The 'Export' button is not visible while the staff have full permissions");

        // Admin will uncheck 'Export revenue report' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check permission
        homePage().siderBar.clickReportMenu().clickRevenueMenu();
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnExport()), "The 'Export revenue report' button is still visible while the staff does not have 'Export revenue report' permission");

        resetPermission();
    }

    @Test(testName = "Check 'View customer report' permission")
    public void FB4() {
        permission = Permission.View_customer_report;
        // Verify with full permission, the staff can click on 'Customer' menu and can navigate to 'Customer' page
        // Verify menu
        homePage().siderBar.clickReportMenu();
        Assert.assertTrue(actionStaff.isElementVisible(homePage().siderBar.getMnuItemCustomer()), "The 'Customer' menu is not visible while the staff have the full permissions");

        homePage().siderBar.clickCustomerMenu();
        actionStaff.waitForUrl(configObject.getUrlReportCustomer());

        // Verify navigating
        actionStaff.navigateToUrl(configObject.getUrlReportCustomer());

        // Admin will uncheck 'View customer report' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check permission
        homePage().siderBar.clickReportMenu();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemCustomer()), "The 'Customer' menu is still visible while the staff does not have the 'View customer report' permission");

        actionStaff.navigateTo(configObject.getUrlReportCustomer());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "Check 'View order report' permission")
    public void FB5() {
        permission = Permission.View_order_report;
        // Verify with full permission, the staff can see the 'Order' tab
        homePage().siderBar.clickReportMenu().clickTransactionMenu();
        Assert.assertTrue(actionStaff.isElementVisible(transactionPage().getTabOrder()), "The 'Order' tab is not visible while the staff have full permissions");

        // Admin will uncheck 'View customer report' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Navigate to Transaction page
        homePage().siderBar.clickReportMenu().clickTransactionMenu();
        Assert.assertFalse(actionStaff.isElementVisible(transactionPage().getTabOrder()), "The 'Order' tab is still visible while the staff does not have (View order report) permission");

        resetPermission();
    }

    @Test(testName = "Check 'View sold product report' permission")
    public void FB6() {
        permission = Permission.View_sold_product_report;
        // Verify with full permission, the staff can see the 'Product' tab
        homePage().siderBar.clickReportMenu().clickTransactionMenu();
        Assert.assertTrue(actionStaff.isElementVisible(transactionPage().getTabProduct()), "The 'Product' tab is not visible while the staff have full permissions");

        // Admin will uncheck 'View sold product report' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Navigate to Transaction page
        homePage().siderBar.clickReportMenu().clickTransactionMenu();
        Assert.assertFalse(actionStaff.isElementVisible(transactionPage().getTabProduct()), "The 'Product' tab is still visible while the staff does not have (View sold product report) permission");

        resetPermission();
    }

    @Test(testName = "Check 'View reservation report' permission")
    public void FB7() {
        permission = Permission.View_reservation_report;
        // Verify with full permission, the staff can see the 'Reservation' tab
        homePage().siderBar.clickReportMenu().clickTransactionMenu();
        Assert.assertTrue(actionStaff.isElementVisible(transactionPage().getTabReservation()), "The 'Reservation' tab is not visible while the staff have full permissions");

        // Admin will uncheck 'View sold product report' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Navigate to Transaction page
        homePage().siderBar.clickReportMenu().clickTransactionMenu();
        Assert.assertFalse(actionStaff.isElementVisible(transactionPage().getTabReservation()), "The 'Reservation' tab is still visible while the staff does not have (View sold product report) permission");

        resetPermission();
    }

    @Test(testName = "Check 'Export sold product report' permission")
    public void FB8() {
        permission = Permission.Export_sold_product_report;
        // Verify with full permission, the staff can see the 'Export' tab
        homePage().siderBar.clickReportMenu().clickTransactionMenu();
        transactionPage().clickProductTab();
        transactionPage().scrollTo(transactionPage().getTitleSoldProduct());
        Assert.assertTrue(actionStaff.isElementVisible(transactionPage().getBtnExport()), "The 'Export' button is not visible while the staff have full permissions");

        // Admin will uncheck 'Export sold product report' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check permissions
        homePage().siderBar.clickReportMenu().clickTransactionMenu();
        transactionPage().clickProductTab();
        transactionPage().scrollTo(transactionPage().getTitleSoldProduct());
        Assert.assertFalse(actionStaff.isElementVisible(transactionPage().getBtnExport()), "The 'Export' button is still visible while the staff does not have 'Export sold product report' permission");

        resetPermission();
    }
}

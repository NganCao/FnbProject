package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import com.fnb.web.pos.pages.InStorePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.adminLocalization;
import static com.fnb.web.setup.Setup.branchData;

public class ShiftPermissionTest extends  PermissionBaseTest{
    private String branch;
    private InStorePage inStorePage;

    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staffDataTest.Staff3_Email;
        passwordStaff = staffDataTest.Staff3_Password;
        groupPermission = GroupPermission.Shift;
        groupName = staffDataTest.OPTION_PERMISSION3;

        // Login to POS (This step is a condition to start a shift if there are no shifts)
        branch = branchData.getBranch().get(0).getName();
        inStorePage = posPage().navigatetoInStorePage(emailStaff, passwordStaff, branch);

        // Login to Admin
        adminPage().navigateToHomePage(emailStaff, passwordStaff);
    }

    @Test(testName = "FB-14574 : Check 'View shift' permission")
    public void FB14574() {
        permission = Permission.View_shift;
        homePage().siderBar.clickReportMenu().clickTransactionMenu();
        transactionPage().clickShiftTab();
        transactionPage().clickTheFirstShiftHasPermission();

        // Verify that staff can see the detail shift page
        String shiftDetailTitle = adminLocalization.getReport().getShiftDetail().getTitle();
        Assert.assertTrue(actionStaff.isElementVisible(By.xpath("//*[text()='"+shiftDetailTitle+"']"), 2), "The staff can not see go to detail page while they have this permission to view shift");

        // Admin will uncheck the 'Delete tax' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        logOutAndLogin();
        homePage().siderBar.clickReportMenu().clickTransactionMenu();
        transactionPage().clickShiftTab();
        transactionPage().clickTheFirstShiftNoPermission();

        // Re-check permission
        Assert.assertFalse(actionStaff.isElementVisible(By.xpath("//*[text()='"+shiftDetailTitle+"']"), 2), "The staff still can go to detail page while they do not have this permission to view shift");

        resetPermission();
    }
}

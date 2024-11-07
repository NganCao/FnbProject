package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.configObject;

public class InventoryHistoryPermissionTest extends PermissionBaseTest {
    
    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staffDataTest.Staff4_Email;
        passwordStaff = staffDataTest.Staff4_Password;
        groupPermission = GroupPermission.Inventory_history;
        groupName = staffDataTest.OPTION_PERMISSION4;
        adminPage().navigateToHomePage(emailStaff, passwordStaff);
    }

    @Test(testName = "Check 'View inventory history' permission")
    public void FB1() {
        permission = Permission.View_inventory_history;
        // Verify with full permission, the staff can see the 'Inventory History' menu icon and can navigate to 'Inventory History' page
        homePage().siderBar.clickMnuItemInventory().clickInventoryHistory();
        actionStaff.waitForUrl(configObject.getUrlInventoryHistory());

        actionStaff.navigateToUrl(configObject.getUrlInventoryHistory());

        // Admin will uncheck 'Export sold product report' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();

        // Check permissions
        homePage().siderBar.clickMnuItemInventory();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemInventoryHistory()), "The 'Inventory History' menu tab is still visible while the staff does not have 'View inventory history' permission");

        homePage().helper.navigateTo(configObject.getUrlInventoryHistory());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }
}

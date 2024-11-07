package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.web.admin.pages.store.areaTable.AddNewAreaDialog;
import com.fnb.web.admin.pages.store.areaTable.AddNewTableDialog;
import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.*;

public class AreaTablePermission extends PermissionBaseTest {
    private String area;
    private String table;
    private AddNewTableDialog addNewTableDialog;
    private AddNewAreaDialog addNewAreaDialog;

    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staffDataTest.Staff3_Email;
        passwordStaff = staffDataTest.Staff3_Password;
        groupPermission = GroupPermission.Area_table;
        groupName = staffDataTest.OPTION_PERMISSION3;
        adminPage().navigateToHomePage(emailStaff, passwordStaff);

        area = areaTableData.getAreas().get(0).getAreaName();
        table = areaTableData.getAreas().get(0).getTables().get(0).getTableName();
    }

    @Test(testName = "FB-13954 : Check 'Create area & table' permission")
    public void FB13954() {
        permission = Permission.Create_area_table;
        homePage().siderBar.clickMnuItemStore().clickAreaTable();

        // Check if the staff can see the Add new button or not (both Area and Table tab)
        Assert.assertTrue(actionStaff.isElementVisible(areaTableManagementPage().getBtnAddNewForArea()), "The staff can not see the add new button while they have this permission");
        Assert.assertTrue(actionStaff.isElementVisible(areaTableManagementPage().getBtnAddNewTable()), "The staff can not see the add new button while they have this permission");

        // Try to click 'Add new' button
        // Area
        addNewAreaDialog = areaTableManagementPage().clickAddNewArea();
        addNewAreaDialog.clickCancel();

        // Table
        addNewTableDialog = areaTableManagementPage().clickAddNewTable();
        addNewTableDialog.clickCancel();

        // Admin will uncheck the 'Create area & table' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemStore().clickAreaTable();

        // Check new permission
        Assert.assertFalse(actionStaff.isElementVisible(areaTableManagementPage().getBtnAddNewForArea()), "The staff still see the add new button while they do not have this permission");

        Assert.assertFalse(actionStaff.isElementVisible(areaTableManagementPage().getBtnAddNewTable()), "The staff still see the add new button while they do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13955 : Check 'Delete area & table' permission")
    public void FB13955() {
        permission = Permission.Delete_area_table;
        homePage().siderBar.clickMnuItemStore().clickAreaTable();

        // Check if the staff can see the 'Delete' icon or not
        // Area
        areaTableManagementPage().selectAreaItem(area);
        areaTableManagementPage().clickThreeDot_Option(area);

        Assert.assertTrue(actionStaff.isElementVisible(areaTableManagementPage().getTooltipDeleteArea()), "The 'delete' tooltip is not visible while the staff have this permission");

        // Table
        commonComponents().selectItem(table);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is not visible while the staff have this permission");

        // Admin will uncheck the 'Create area & table' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemStore().clickAreaTable();

        // Check new permission
        // Area
        areaTableManagementPage().selectAreaItem(area);
        Assert.assertFalse(actionStaff.isElementVisible(areaTableManagementPage().getTooltipDeleteArea()), "The 'delete' tooltip is still visible while the staff do not have this permission");
        // Table
        commonComponents().selectItem(table);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13956 : Check 'Edit area & table' permission")
    public void FB13956() {
        permission = Permission.Edit_area_table;
        homePage().siderBar.clickMnuItemStore().clickAreaTable();

        // Check if the staff can see the 'Edit' icon or not
        // Area
        areaTableManagementPage().selectAreaItem(area);
        areaTableManagementPage().clickThreeDot_Option(area);
        Assert.assertTrue(actionStaff.isElementVisible(areaTableManagementPage().getTooltipEditArea()), "The 'edit' tooltip is not visible while the staff have this permission");

        // Table
        commonComponents().selectItem(table);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents.getBtnEditIcon()), "The edit icon is not visible, please check");

        // Admin will uncheck the 'Create area & table' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemStore().clickAreaTable();

        // Check permission
        // Area
        areaTableManagementPage().selectAreaItem(area);
        areaTableManagementPage().clickThreeDot_Option(area);
        Assert.assertFalse(actionStaff.isElementVisible(areaTableManagementPage().getTooltipEditArea()), "The 'edit' tooltip is still visible while the staff do not have this permission");

        // Table
        commonComponents().selectItem(table);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13957 : Check 'View area & table' permission")
    public void FB13957() {
        permission = Permission.View_area_table;
        homePage().siderBar.clickMnuItemStore();

        // Check if staff can see the 'Area & Table' menu
        Assert.assertTrue(actionStaff.isElementVisible(homePage().siderBar.getMnuItemAreaTable()), "The staff can not see 'Area & table' menu while staff have full permission");

        // Admin will uncheck the 'Create area & table' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // Staff will log out and login back to get new permission
        logOutAndLogin();
        homePage().siderBar.clickMnuItemStore();

        // Check permission
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemAreaTable()), "The staff still see 'Area & table' menu while staff do not have this permission");

        resetPermission();
    }
}

package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import com.fnb.web.pos.pages.component.OpenLeftMenu;
import com.fnb.web.pos.pages.InStorePage;
import com.fnb.web.pos.pages.KitchenPage;
import com.fnb.web.pos.pages.LoginPosPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.branchData;
import static com.fnb.web.setup.Setup.configObject;

public class PosPermissionTests extends CommonPages {
    protected Permission permission;
    protected String emailStaff;
    protected String passwordStaff;
    protected GroupPermission groupPermission;
    protected String groupName;
    protected com.fnb.web.admin.pages.store.staff.DataTest staffDataTest;
    private String posTab;
    private String adminTab;
    private String branch;
    private LoginPosPage loginPosPage;
    private InStorePage inStorePage;
    private OpenLeftMenu openLeftMenu;
    private KitchenPage kitchenPage;
    private Helper action;
    private com.fnb.web.admin.pages.store.staff.DataTest staff;
    private boolean lastTestFailed = false;

    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staff.Staff4_Email;
        passwordStaff = staff.Staff4_Password;
        groupPermission = GroupPermission.Point_of_Sale_POS;
        groupName = staffDataTest.OPTION_PERMISSION4;
        loginPosPage = new LoginPosPage(getDriver());
        kitchenPage = new KitchenPage(getDriver());

        action = adminPage().helper;
        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
        adminPage().helper.navigateToUrl(configObject.getUrlStaffManagement());

        // Handle tab for ADMIN and POS
        adminTab = getDriver().getWindowHandle();
        // Open the new tab
        getDriver().switchTo().newWindow(WindowType.TAB);
        posTab = getDriver().getWindowHandle();

        // Login to POS
        branch = branchData.getBranch().get(0).getName();
        inStorePage = posPage().navigatetoInStorePage(emailStaff, passwordStaff, branch);
    }

    @BeforeMethod
    public void beforeTest() {
        if (lastTestFailed) {
            adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
            adminPage().helper.navigateToUrl(configObject.getUrlStaffManagement());

            // Handle tab for ADMIN and POS
            adminTab = getDriver().getWindowHandle();
            // Open the new tab
            getDriver().switchTo().newWindow(WindowType.TAB);
            posTab = getDriver().getWindowHandle();

            // Login to POS
            inStorePage = posPage().navigatetoInStorePage(emailStaff, passwordStaff, branch);
        }
        // Reset the flag for the next test
        lastTestFailed = false;
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            resetPermission();
            lastTestFailed = true;
            getDriver().get("chrome://settings/clearBrowserData");
            getDriver().findElement(By.xpath("//settings-ui")).sendKeys(Keys.TAB);
            getDriver().findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
        }
    }

    private void resetPermission() {
        action.switchTab(adminTab);
        // Admin will check the view material permission (prepare data)
        staffManagementPage()
                .clickPermissionGroupTab()
                .clickGroupName(groupName)
                .checkAPermission(groupPermission, permission)
                .clickUpdate();
    }

    @Test(testName = "FB-14750 : Check 'Cashier' permission")
    public void FB14750() {
        permission = Permission.Cashier;
        // Check if the user can order or not
        openLeftMenu = inStorePage.orderTypeBar.clickBtnExpand();
        Assert.assertTrue(action.isElementVisible(openLeftMenu.getBtnOrder()), "The Order button is not visible");

        action.switchTab(adminTab);
        // Admin will uncheck 'Cashier' permission
        staffManagementPage().unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // POS staff will logout and login back to check the new permission
        action.switchTab(posTab);
        openLeftMenu.logOut();

        loginPosPage.enterUserName(emailStaff).enterPassword(passwordStaff).clickLoginButton().selectBranchByName(branch);
        Assert.assertTrue(action.waitForURLContains("kitchen"), "The staff still can navigate to Cashier page");

        resetPermission();
        action.switchTab(posTab);
        openLeftMenu = kitchenPage.clickIconExpand();
        openLeftMenu.logOut();
        inStorePage = posPage().navigatetoInStorePage(emailStaff, passwordStaff, branch);
    }

    @Test(testName = "FB-14751 : Check 'Kitchen' permission")
    public void FB14751() {
        permission = Permission.Kitchen;
        openLeftMenu = inStorePage.orderTypeBar.clickBtnExpand();
        Assert.assertTrue(action.isElementVisible(openLeftMenu.getBtnKitchen()), "The Order button is not visible");
        kitchenPage = openLeftMenu.clickKitchen();
        Assert.assertTrue(action.isElementVisible(kitchenPage.getTabDish()), "The staff can not go to the kitchen page");

        action.switchTab(adminTab);
        // Admin will uncheck 'Cashier' permission
        staffManagementPage().unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        // POS staff will logout and login back to check the new permission
        action.switchTab(posTab);
        openLeftMenu = kitchenPage.clickIconExpand();
        openLeftMenu.logOut();
        inStorePage = posPage().navigatetoInStorePage(emailStaff, passwordStaff, branch);

        openLeftMenu = inStorePage.orderTypeBar.clickBtnExpand();
        Assert.assertFalse(action.isElementVisible(openLeftMenu.getBtnKitchen()), "The Order button is still visible while they do not have this permission");

        resetPermission();
        openLeftMenu.logOut();
        inStorePage = posPage().navigatetoInStorePage(emailStaff, passwordStaff, branch);
    }
}

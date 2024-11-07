package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import com.fnb.web.pos.pages.ReservationPage;
import com.fnb.web.pos.pages.component.OpenLeftMenu;
import com.fnb.web.pos.pages.InStorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

import static com.fnb.web.setup.Setup.*;
public class ReservationPermissionTest extends CommonPages {
    private String posTab;
    private String adminTab;
    private String branch;
    private Permission permission;
    private InStorePage inStorePage;
    private OpenLeftMenu openLeftMenu;
    private ReservationPage reservationPage;
    private Helper action;
    private com.fnb.web.admin.pages.store.staff.DataTest staff;
    private boolean lastTestFailed = false;

    @BeforeClass
    public void beforeClass() throws IOException {
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
        inStorePage = posPage().navigatetoInStorePage(staff.Staff3_Email, staff.Staff3_Password, branch);
    }

    @AfterClass
    public void afterClass() {
        adminPage().helper.clearCookies();
    }

    @BeforeMethod
    public void beforeMethod() {
        if (lastTestFailed) {
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
            inStorePage = posPage().navigatetoInStorePage(staff.Staff3_Email, staff.Staff3_Password, branch);
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
                .clickGroupName(staff.OPTION_PERMISSION3)
                .checkAPermission(GroupPermission.Reservation, permission)
                .clickUpdate();
    }

    private void logOutAndLoginBack_POS() {
        // Refresh pos page and logout login
        action.switchTab(posTab);
        action.refreshPage();
        inStorePage.orderTypeBar.logOut();
        inStorePage = posPage().navigatetoInStorePage(staff.Staff3_Email, staff.Staff3_Password, branch);
    }

    @Test(testName = "FB-13578 : Check 'View reservation' permission")
    public void FB13578() {
        permission = Permission.View_reservation;

        // POS staff will check if they can see the Reservation
        openLeftMenu = inStorePage.orderTypeBar.clickBtnExpand();
        Assert.assertTrue(action.isElementVisible(openLeftMenu.getBtnReservation()), "The Reservation button is not visible");
        action.switchTab(adminTab);

        // Admin will uncheck 'View reservation' permission
        staffManagementPage().unCheckAPermission_FullFlow(staff.OPTION_PERMISSION3, GroupPermission.Reservation, permission);

        // POS staff will logout and login back to check the new permission
        action.switchTab(posTab);
        openLeftMenu.logOut();
        inStorePage = posPage().navigatetoInStorePage(staff.Staff3_Email, staff.Staff3_Password, branch);
        inStorePage.orderTypeBar.clickBtnExpand();
        Assert.assertFalse(action.isElementVisible(openLeftMenu.getBtnReservation()), "The Reservation button is still visible while they do not have this permission");

        resetPermission();
        logOutAndLoginBack_POS();
    }

    @Test(testName = "FB-13579 : Check 'Create new reservation' permission")
    public void FB13579() {
        permission = Permission.Create_new_reservation;

        // POS staff will check if they can create a new reservation
        action.switchTab(posTab);
        openLeftMenu = inStorePage.orderTypeBar.clickBtnExpand();
        ReservationPage reservationPage = openLeftMenu.clickReservation();
        Assert.assertTrue(action.isElementVisible(reservationPage.getBtnCreateReservation()), "The create reservation button is not visible while the pos staff have full permission");

        // Click 'Create reservation' to see if we can click okay or not
        reservationPage.clickCreateReservation();

        // Admin will uncheck 'Create reservation' permission
        action.switchTab(adminTab);
        staffManagementPage().unCheckAPermission_FullFlow(staff.OPTION_PERMISSION3, GroupPermission.Reservation, permission);

        // POS staff will logout and login back to check the new permission
        action.switchTab(posTab);
        reservationPage.clickCloseIcon();
        reservationPage.clickBackIcon();
        inStorePage.orderTypeBar.logOut();
        inStorePage = posPage().navigatetoInStorePage(staff.Staff3_Email, staff.Staff3_Password, branch);

        // Check
        inStorePage.orderTypeBar.clickBtnExpand();
        openLeftMenu.clickReservation();
        Assert.assertFalse(action.isElementVisible(reservationPage.getBtnCreateReservation()), "The create reservation button is still visible while the pos staff does not have 'Create new reservation' permission");

        reservationPage.clickBackIcon();

        resetPermission();
        logOutAndLoginBack_POS();
    }

    @Test(testName = "FB-13580 : Check 'Edit reservation' permission")
    public void FB13580() {
        permission = Permission.Edit_reservation;

        // Navigate to reservation page
        openLeftMenu = inStorePage.orderTypeBar.clickBtnExpand();
        reservationPage = openLeftMenu.clickReservation();
        reservationPage.clickCreateReservation();

        //  Create a new reservation
        reservationPage.createNewReservation(
                customerData.getCustomers().get(0).getPhone(),
                areaTableData.getAreas().get(1).getAreaName(),
                areaTableData.getAreas().get(1).getTables().get(1).getTableName(),
                "2"
        );

        // Try to click the reservation id to see if we can edit or not
        reservationPage.clickFirstReservationId();
        Assert.assertTrue(action.isElementVisible(By.xpath("//*[text()='"+posLocalization.getReserTable().getEditReservation()+"']")), "The staff can not edit the reservation while they have this permission");

        // Admin will uncheck 'Create reservation' permission
        action.switchTab(adminTab);
        staffManagementPage().unCheckAPermission_FullFlow(staff.OPTION_PERMISSION3, GroupPermission.Reservation, permission);

        // POS staff will logout and login back to check the new permission
        action.switchTab(posTab);
        reservationPage.clickCloseIcon();
        reservationPage.clickBackIcon();
        inStorePage.orderTypeBar.logOut();
        inStorePage = posPage().navigatetoInStorePage(staff.Staff3_Email, staff.Staff3_Password, branch);

        // Check
        inStorePage.orderTypeBar.clickBtnExpand();
        openLeftMenu.clickReservation();
        reservationPage.clickFirstReservationId();
        Assert.assertFalse(action.isElementVisible(By.xpath("//*[text()='"+posLocalization.getReserTable().getEditReservation()+"']")), "The staff still click edit the reservation while they do not have this permission");

        reservationPage.clickBackIcon();
        resetPermission();
        logOutAndLoginBack_POS();
    }

    @Test(testName = "FB-13805 : Check 'Cancel reservation' permission")
    public void FB13805() {
        permission = Permission.Cancel_reservation;

        // Navigate to reservation page
        openLeftMenu = inStorePage.orderTypeBar.clickBtnExpand();
        reservationPage = openLeftMenu.clickReservation();
        reservationPage.clickCreateReservation();

        //  Create a new reservation
        reservationPage.createNewReservation(
                customerData.getCustomers().get(0).getPhone(),
                areaTableData.getAreas().get(1).getAreaName(),
                areaTableData.getAreas().get(1).getTables().get(1).getTableName(),
                "2"
        );

        reservationPage.clickArrivalTimeColum();
        // Verify that staff can cancel the reservation or not
        Assert.assertTrue(action.isElementVisible(reservationPage.getBtnFirstCancelIcon()), "The staff can not see the cancel icon while they have this permission");
        reservationPage.clickFirstReservationId();
        Assert.assertTrue(action.isElementVisible(reservationPage.getBtnCancel()), "The staff can not see the cancel button while they have this permission");

        // Admin will uncheck 'Create reservation' permission
        action.switchTab(adminTab);
        staffManagementPage().unCheckAPermission_FullFlow(staff.OPTION_PERMISSION3, GroupPermission.Reservation, permission);

        // POS staff will logout and login back to check the new permission
        action.switchTab(posTab);
        reservationPage.clickCloseIcon();
        reservationPage.clickBackIcon();
        inStorePage.orderTypeBar.logOut();
        inStorePage = posPage().navigatetoInStorePage(staff.Staff3_Email, staff.Staff3_Password, branch);

        // Check
        inStorePage.orderTypeBar.clickBtnExpand();
        openLeftMenu.clickReservation();
        reservationPage.clickArrivalTimeColum();

        // Verify that staff can cancel the reservation or not
        Assert.assertFalse(action.isElementVisible(reservationPage.getBtnFirstCancelIcon()), "The staff still see the cancel icon while they do not have this permission");
        reservationPage.clickFirstReservationId();
        Assert.assertFalse(action.isElementVisible(reservationPage.getBtnCancel()), "The staff stil see the cancel button while they do not have this permission");

        reservationPage.clickCloseIcon();
        reservationPage.clickBackIcon();
        resetPermission();
        logOutAndLoginBack_POS();
    }
}

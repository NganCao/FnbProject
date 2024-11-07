package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.admin.pages.store.feeandtax.FeeManagementPage;
import com.fnb.web.admin.pages.store.feeandtax.TaxManagementPage;
import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.*;

// Sprint55
public class FeeAndTaxPermissionTest extends PermissionBaseTest{
    private FeeManagementPage.AddNewFeeDialog addNewFeeDialog;
    private TaxManagementPage taxManagementPage;
    private String activeFee;
    private String scheduleFee;
    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staffDataTest.Staff3_Email;
        passwordStaff = staffDataTest.Staff3_Password;
        groupPermission = GroupPermission.Fee_tax;
        groupName = staffDataTest.OPTION_PERMISSION3;
        adminPage().navigateToHomePage(emailStaff, passwordStaff);

        actionStaff.navigateToUrl(configObject.getUrlFeeTaxManagement());
        addNewFeeDialog = feeManagementPage().clickAddNewButton();

        // Add active fee
        activeFee = addNewFeeDialog.addFee(
                "ActiveFee" + actionStaff.generateRandomNumber(),
                "20",
                actionStaff.getCurrentDate(),
                actionStaff.getNextDay(),
                branchData.getBranch().get(0).getName()
        );

        actionStaff.refreshPage();

        addNewFeeDialog = feeManagementPage().clickAddNewButton();
        // Add schedule fee
        scheduleFee = addNewFeeDialog.addFee(
                "ScheduleFee" + actionStaff.generateRandomNumber(),
                "20",
                actionStaff.getANumberofNextDay(3),
                actionStaff.getANumberofNextDay(3),
                branchData.getBranch().get(0).getName());
    }

    public void resetTheFlowToGetNewPermission() {
        logOutAndLogin();
        homePage().siderBar.clickMnuItemStore().clickFeeAndTax();
    }

    @Test(testName = "FB-14477 : Check 'Delete tax' permission")
    public void FB14477() {
        permission = Permission.Delete_tax;
        homePage().siderBar.clickMnuItemStore().clickFeeAndTax();

        taxManagementPage = feeManagementPage().clickTaxTab();
        taxManagementPage.selectTax(taxData.getTax().get(0).getName());

        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is not visible while the staff have this permission");
        taxManagementPage.commonComponents.clickDeleteIcon();

        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDelete()), "The 'delete' button is not visible while the staff have this permission");
        taxManagementPage.commonComponents.clickIgnore();

        // Admin will uncheck the 'Delete tax' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        // Check new permission
        taxManagementPage = feeManagementPage().clickTaxTab();
        taxManagementPage.selectTax(taxData.getTax().get(0).getName());
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-14478 : Check 'Stop fee' permission")
    public void FB14478() {
        permission = Permission.Stop_fee;
        homePage().siderBar.clickMnuItemStore().clickFeeAndTax();

        feeManagementPage().selectItem(activeFee);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnStopIcon()), "The 'stop' icon is not visible while the staff have this permission");

        promotionManagementPage().commonComponents.clickStopIcon();
        promotionManagementPage().commonComponents.clickIgnore();

        // Admin will uncheck 'Stop fee' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        // Check new permission
        feeManagementPage().selectItem(activeFee);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnStopIcon()), "The 'stop' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = " FB-14479 : Check 'View tax' permission")
    public void FB14479() {
        permission = Permission.View_tax;
        homePage().siderBar.clickMnuItemStore().clickFeeAndTax();
        taxManagementPage = feeManagementPage().clickTaxTab();
        Assert.assertTrue(actionStaff.isElementVisible(By.xpath("//*[text()='"+taxData.getTax().get(0).getName()+"']")), "The staff can not see 'Tax' data while staff have full permission");

        // Admin will uncheck 'View tax' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        resetTheFlowToGetNewPermission();

        // Recheck permission
        taxManagementPage = feeManagementPage().clickTaxTab();
        Assert.assertFalse(actionStaff.isElementVisible(By.xpath("//*[text()='"+taxData.getTax().get(0).getName()+"']")), "The staff still see 'Tax' data while staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-14480 : Check 'View fee' permission")
    public void FB14480() {
        permission = Permission.View_fee;
        homePage().siderBar.clickMnuItemStore().clickFeeAndTax();
        Assert.assertTrue(actionStaff.isElementVisible(By.xpath("//*[text()='"+scheduleFee+"']")), "The staff can not see 'Fee' data while staff have full permission");

        // Admin will uncheck 'View fee' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        resetTheFlowToGetNewPermission();

        // Recheck permission
        Assert.assertFalse(actionStaff.isElementVisible(By.xpath("//*[text()='"+activeFee+"']")), "The staff still see 'Fee' data while staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-14481 : Check 'Create tax' permission")
    public void FB14481() {
        permission = Permission.Create_tax;
        homePage().siderBar.clickMnuItemStore().clickFeeAndTax();
        taxManagementPage = feeManagementPage().clickTaxTab();

        Assert.assertTrue(actionStaff.isElementVisible(taxManagementPage.getBtnAddNew()), "The staff can not see the add new button while they have this permission");
        addNewTaxDialog = taxManagementPage.clickAddNewButton();
        addNewTaxDialog.clickCancel();

        // Admin will uncheck 'Create tax' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        taxManagementPage = feeManagementPage().clickTaxTab();
        Assert.assertFalse(actionStaff.isElementVisible(taxManagementPage.getBtnAddNew()), "The staff still see the add new button while they have this permission");

        resetPermission();
    }

    @Test(testName = "FB-14482 : Check 'Create fee' permission")
    public void FB14482() {
        permission = Permission.Create_fee;
        homePage().siderBar.clickMnuItemStore().clickFeeAndTax();

        Assert.assertTrue(actionStaff.isElementVisible(feeManagementPage().getBtnAddNew()), "The staff can not see the add new button while they have this permission");
        addNewFeeDialog = feeManagementPage().clickAddNewButton();
        addNewFeeDialog.clickCancel();

        // Admin will uncheck 'Create fee' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        Assert.assertFalse(actionStaff.isElementVisible(taxManagementPage.getBtnAddNew()), "The staff still see the add new button while they have this permission");

        resetPermission();
    }

    @Test(testName = "FB-14483 : Check 'Delete fee' permission")
    public void FB14483() {
        permission = Permission.Delete_fee;
        homePage().siderBar.clickMnuItemStore().clickFeeAndTax();

        feeManagementPage().selectItem(scheduleFee);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is not visible while the staff have this permission");

        feeManagementPage().commonComponents.clickDeleteIcon();
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDelete()), "The 'delete' button is not visible while the staff have this permission");
        promotionManagementPage().commonComponents.clickIgnore();

        // Admin will uncheck the 'Delete fee' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        // Recheck new permission
        feeManagementPage().selectItem(scheduleFee);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is still visible while the staff do not have this permission");

        resetPermission();
    }
}

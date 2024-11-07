package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.web.admin.pages.marketing.emailCampaign.CreateEmailCampaignPage;
import com.fnb.web.admin.pages.marketing.notificationCampaign.CreateNotificationCampaignPage;
import com.fnb.web.admin.pages.marketing.qrOrder.CreateQrOrderPage;
import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.*;

public class MarketingPermissionTest extends PermissionBaseTest{
    String activeQrCode;
    String scheduleQrCode;
    String emailCampaign;
    String notificationCampaign;

    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staffDataTest.Staff2_Email;
        passwordStaff = staffDataTest.Staff2_Password;
        groupPermission = GroupPermission.Marketing;
        groupName = staffDataTest.OPTION_PERMISSION2;
        adminPage().navigateToHomePage(emailStaff, passwordStaff);

        // Create active QR OrderManagement
        String qrOrderName = "ActiveOrOrder" + actionStaff.generateRandomNumber();
        String binhThanhBranch = branchData.getBranch().get(0).getName();
        CreateQrOrderPage.ServiceType serviceType = CreateQrOrderPage.ServiceType.INSTORE;
        String area = areaTableData.getAreas().get(0).getAreaName();
        String table = areaTableData.getAreas().get(0).getTables().get(0).getTableName();
        String validFromDate = actionStaff.getCurrentDate();
        String validUntilDate = actionStaff.getCurrentDate();

        actionStaff.navigateToUrl(configObject.getUrlCreateQrOrder());
        activeQrCode = createQrOrderPage().createAQrOder(
                qrOrderName,
                binhThanhBranch,
                serviceType,
                area, table,
                validFromDate, validUntilDate
        );

        // Create schedule QR OrderManagement
        String ScheduleOrderName = "ScheduleOrOrder" + actionStaff.generateRandomNumber();
        validFromDate = actionStaff.getNextDay();
        validUntilDate = actionStaff.getNextDay();
        actionStaff.navigateToUrl(configObject.getUrlCreateQrOrder());
        scheduleQrCode = createQrOrderPage().createAQrOder(
                ScheduleOrderName,
                binhThanhBranch,
                serviceType,
                area, table,
                validFromDate, validUntilDate);

        // Create Email Campaign
        String name = "EmailCampaign" + actionStaff.generateRandomNumber();
        String sendingTime = actionStaff.getCurrentDate();
        String subject = "Test permiission";
        CreateEmailCampaignPage.SendTo sendTo = CreateEmailCampaignPage.SendTo.SENDING_TO_EMAIL_ADDRESS;
        String email = "test" + actionStaff.generateRandomNumber() + "@mailinator.com";

        actionStaff.navigateToUrl(configObject.getUrlCreateEmailCampaign());
        emailCampaign = createEmailCampaignPage().createEmailCampaign(
                name, sendingTime, subject, sendTo, email
        );

        // Create notification campaign
        String notificationCampaignName = "Notification" + actionStaff.generateRandomNumber();
        CreateNotificationCampaignPage.SendingType sendingType = CreateNotificationCampaignPage.SendingType.SEND_BY_EVENT;

        actionStaff.navigateToUrl(configObject.getUrlCreateNotificationCampaign());
        notificationCampaign = createNotificationCampaignPage().createNotificationCampaign(
                notificationCampaignName,
                sendingType,
                "Title testing",
                "Testing message content");

        homePage().siderBar.clickHome();
    }

    @Test(testName = "FB-14675 : Check 'Edit email campaign' permission")
    public void FB14675() {
        permission = Permission.Edit_email_campaign;
        homePage().siderBar.clickMnuItemMarketing().clickEmailCampaign();
        emailCampaignManagementPage()
                .searchEmailCampaign(emailCampaign)
                .selectEmailCampaign(emailCampaign)
                .clickEdit();
        Assert.assertTrue(actionStaff.waitForURLContains("email-campaign/edit"), "The staff can not go to the email campaign detail");

        // Admin will uncheck the 'Edit email campaign' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        logOutAndLogin();

        // Check new permission
        homePage().siderBar.clickMnuItemMarketing().clickEmailCampaign();
        emailCampaignManagementPage()
                .searchEmailCampaign(emailCampaign)
                .selectEmailCampaign(emailCampaign);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents.getBtnEditIcon()), "The staff still see the edit icon when they do not have the permission to edit");

        resetPermission();
    }

    @Test(testName = "FB-14677 : Check 'Delete QR Code' permission")
    public void FB14677() {
        permission = Permission.Delete_QR_Code;
        homePage().siderBar.clickMnuItemMarketing().clickQrOrder();
        qrOrderManagementPage()
                .searchQrOrder(scheduleQrCode)
                .selectQrOrder(scheduleQrCode)
                .clickDeleteIcon()
                .clickIgnore();
        // Admin will uncheck the 'Delete QR Code' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        logOutAndLogin();

        homePage().siderBar.clickMnuItemMarketing().clickQrOrder();
        qrOrderManagementPage()
                .searchQrOrder(scheduleQrCode)
                .selectQrOrder(scheduleQrCode);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents.getBtnDeleteIcon()), "The staff still see the delete icon while they doi not have permission to delete");

        resetPermission();
    }

    @Test(testName = "FB-14678 : Check ' View QR Code' permission")
    public void FB14678() {
        permission = Permission.View_QR_Code;
        // Check if the staff can click the 'QR ORder' menu and can navigate to 'Order Management' url
        homePage().siderBar.clickMnuItemMarketing();
        Assert.assertTrue(actionStaff.isElementVisible(homePage().siderBar.getMnuItemQrOder()), "The QR order menu is not visible");

        homePage().siderBar.clickQrOrder();
        actionStaff.waitForUrl(configObject.getUrlQrOrderManagement());

        // Admin will uncheck 'View QR Code' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        logOutAndLogin();

        // Re-check permission
        homePage().siderBar.clickMnuItemMarketing();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemQrOder()), "The QR order menu is still visible while the staff do not have permission to view Qr Order Management");

        homePage().helper.navigateTo(configObject.getUrlQrOrderManagement());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "FB-14679 : Check 'Stop notification campaign' permission")
    public void FB14679() {
        permission = Permission.Stop_notification_campaign;
        homePage().siderBar.clickMnuItemMarketing().clickNotificationCampaign();
        notificationManagementPage().searchNotification(notificationCampaign).selectNotification(notificationCampaign);
        Assert.assertTrue(actionStaff.isElementVisible(notificationManagementPage().getIconStop()), "The 'stop' icon is not visible while the staff have this permission");

        notificationManagementPage().clickStopIcon();
        notificationManagementPage().commonComponents.clickIgnore();

        // Admin will uncheck 'Stop notification campaign' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        logOutAndLogin();

        // Recheck permission
        homePage().siderBar.clickMnuItemMarketing().clickNotificationCampaign();
        notificationManagementPage().searchNotification(notificationCampaign).selectNotification(notificationCampaign);
        Assert.assertFalse(actionStaff.isElementVisible(notificationManagementPage().getIconStop()), "The 'stop' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-14680 : Check ' Edit QR Code' permission")
    public void FB14680() {
        permission = Permission.Edit_QR_Code;
        homePage().siderBar.clickMnuItemMarketing().clickQrOrder();
        qrOrderManagementPage()
                .searchQrOrder(scheduleQrCode)
                .selectQrOrder(scheduleQrCode)
                .clickEdit();
        Assert.assertTrue(actionStaff.waitForURLContains("qr-order/edit"), "The staff can not go to the QR order detail");

        // Admin will uncheck the 'Edit QR Code' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        logOutAndLogin();

        // Check new permission
        homePage().siderBar.clickMnuItemMarketing().clickQrOrder();
        qrOrderManagementPage()
                .searchQrOrder(scheduleQrCode)
                .selectQrOrder(scheduleQrCode);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents.getBtnEditIcon()), "The staff still see the edit icon when they do not have the permission to edit");

        resetPermission();
    }

    @Test(testName = "FB-14681 : Check 'Create email campaign' permission")
    public void FB14681() {
        permission = Permission.Create_email_campaign;
        homePage().siderBar.clickMnuItemMarketing().clickEmailCampaign();
        emailCampaignManagementPage().clickAddNew();
        actionStaff.waitForUrl(configObject.getUrlCreateEmailCampaign());

        // Admin will uncheck the 'Create email campaign' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        logOutAndLogin();

        // Recheck permission
        homePage().siderBar.clickMnuItemMarketing().clickEmailCampaign();
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents.getBtnAddNew()), "The add new button is still visible while the staff does not have permission to add");

        actionStaff.navigateTo(configObject.getUrlCreateEmailCampaign());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "FB-14682 : Check 'Create notification campaign' permission")
    public void FB14682() {
        permission = Permission.Create_notification_campaign;
        homePage().siderBar.clickMnuItemMarketing().clickNotificationCampaign();
        notificationManagementPage().clickAddNew();
        actionStaff.waitForUrl(configObject.getUrlCreateNotificationCampaign());

        // Admin will uncheck the 'Create notification campaign' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        logOutAndLogin();

        // Recheck permission
        homePage().siderBar.clickMnuItemMarketing().clickNotificationCampaign();
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents.getBtnAddNew()), "The add new button is still visible while the staff does not have permission to add");

        actionStaff.navigateTo(configObject.getUrlCreateNotificationCampaign());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "FB-14683 : Check 'View notification campaign' permission")
    public void FB14683() {
        permission = Permission.View_notification_campaign;
        // Check if the staff can click the 'Notification campaign' menu and can navigate to 'Notification campaign' url
        homePage().siderBar.clickMnuItemMarketing();
        Assert.assertTrue(actionStaff.isElementVisible(homePage().siderBar.getMnuItemNotificationCampaign()), "The Notification campaign menu is not visible");

        homePage().siderBar.clickNotificationCampaign();
        actionStaff.waitForUrl(configObject.getUrlNotificationCampaignManagement());

        // Admin will uncheck 'View notification campaign' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        logOutAndLogin();

        // Recheck permission
        homePage().siderBar.clickMnuItemMarketing();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemNotificationCampaign()), "The Notification campaign menu is still visible while the staff does not have permission to view Notification campaign");

        homePage().helper.navigateTo(configObject.getUrlNotificationCampaignManagement());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "FB-14684 : Check 'Edit notification campaign' permission")
    public void FB14684() {
        permission = Permission.Edit_notification_campaign;
        homePage().siderBar.clickMnuItemMarketing().clickNotificationCampaign();
        notificationManagementPage()
                .searchNotification(notificationCampaign)
                .selectNotification(notificationCampaign)
                .clickEdit();
        Assert.assertTrue(actionStaff.waitForURLContains("marketing/notification-campaign/edit"), "The staff can not go to the notification campaign detail");

        // Admin will uncheck the 'Edit notification campaign' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        logOutAndLogin();

        // Re-check permission
        homePage().siderBar.clickMnuItemMarketing().clickNotificationCampaign();
        notificationManagementPage()
                .searchNotification(notificationCampaign)
                .selectNotification(notificationCampaign);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents.getBtnEditIcon()), "The staff still see the edit icon when they do not have the permission to edit");

        resetPermission();
    }

    @Test(testName = "FB-14687 : Check 'Stop QR Code' permission")
    public void FB14687() {
        permission = Permission.Stop_QR_Code;
        homePage().siderBar.clickMnuItemMarketing().clickQrOrder();
        qrOrderManagementPage().searchQrOrder(activeQrCode).selectQrOrder(activeQrCode);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnStopIcon()), "The 'stop' icon is not visible while the staff have this permission");

        qrOrderManagementPage().commonComponents.clickStopIcon();
        qrOrderManagementPage().commonComponents.clickIgnore();

        // Admin will uncheck 'Stop QR Code' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        logOutAndLogin();

        // Recheck permission
        homePage().siderBar.clickMnuItemMarketing().clickQrOrder();
        qrOrderManagementPage().searchQrOrder(activeQrCode).selectQrOrder(activeQrCode);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnStopIcon()), "The 'stop' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-14688 : Check 'Create QR Code' permission")
    public void FB14688() {
        permission = Permission.Create_QR_Code;
        homePage().siderBar.clickMnuItemMarketing().clickQrOrder();
        qrOrderManagementPage().clickAddNew();
        actionStaff.waitForUrl(configObject.getUrlCreateQrOrder());

        // Admin will uncheck the 'Create QR Code' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        logOutAndLogin();

        // Recheck permission
        homePage().siderBar.clickMnuItemMarketing().clickQrOrder();
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents.getBtnAddNew()), "The add new button is still visible while the staff does not have permission to add");

        actionStaff.navigateTo(configObject.getUrlCreateQrOrder());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = "FB-14689 : Check 'View email campaign' permission")
    public void FB14689() {
        permission = Permission.View_email_campaign;
        // Check if the staff can click the 'Email campaign' menu and can navigate to 'Email campaign' url
        homePage().siderBar.clickMnuItemMarketing();
        Assert.assertTrue(actionStaff.isElementVisible(homePage().siderBar.getMnuItemEmailCampaign()), "The Email campaign menu is not visible");

        homePage().siderBar.clickEmailCampaign();
        actionStaff.waitForUrl(configObject.getUrlEmailCampaignManagement());

        // Admin will uncheck 'View Email campaign' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        logOutAndLogin();

        // Re-check permission
        homePage().siderBar.clickMnuItemMarketing();
        Assert.assertFalse(actionStaff.isElementVisible(homePage().siderBar.getMnuItemEmailCampaign()), "The Email campaign menu is still visible while the staff do not have permission to view Qr Order Management");

        homePage().helper.navigateTo(configObject.getUrlEmailCampaignManagement());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

//    @Test(testName = "FB-14685 : Check 'Delete notification campaign' permission")
//    public void FB14685() {}
//
//    @Test(testName = "FB-14686 : Check 'Stop email campaign' permission")
//    public void FB14686() {}
//
//    @Test(testName = " FB-14676 : Check 'Delete email campaign' permission")
//    public void FB14676() {}
}

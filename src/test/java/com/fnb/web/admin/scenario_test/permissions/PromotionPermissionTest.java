package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.web.admin.pages.store.promotion.CreateDiscountCampaignPage;
import com.fnb.web.admin.pages.store.promotion.CreateDiscountCodePage;
import com.fnb.web.admin.pages.store.promotion.CreateFlashSaleCampaignPage;
import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.*;

public class PromotionPermissionTest extends PermissionBaseTest {
    private String scheduleDiscountCode;
    private String activeDiscountCode;
    private String scheduleDiscountCampaign;
    private String activeDiscountCampaign;
    private String scheduleFlashSaleCampaign;
    private String activeFlashSaleCampaign;

    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        emailStaff = staffDataTest.Staff1_Email;
        passwordStaff = staffDataTest.Staff1_Password;
        groupPermission = GroupPermission.Promotion;
        groupName = staffDataTest.OPTION_PERMISSION1;
        adminPage().navigateToHomePage(emailStaff, passwordStaff);

        // Create active discount code
        homePage().helper.navigateToUrl(configObject.getUrlPromotionManagement());
        promotionManagementPage().clickDiscountCodeTab();
        CreateDiscountCodePage createDiscountCodePage = promotionManagementPage().clickPromoCodes();
        activeDiscountCode = createDiscountCodePage.createDiscountCode(
                "Active DiscountCode" + actionStaff.generateRandomNumber(),
                CreateDiscountCodePage.ApplicableType.Discount_on_total_bill,
                "20",
                "100",
                actionStaff.getCurrentDate(),
                actionStaff.getHourAndMinuteCustom(0, 2),
                actionStaff.getNextDay(),
                actionStaff.getHourAndMinuteCustom(0, 60)
        );

        // Create a active flash sale
        homePage().helper.navigateToUrl(configObject.getUrlPromotionManagement());
        promotionManagementPage().clickFlashSaleCampaignTab();

        CreateFlashSaleCampaignPage createFlashSaleCampaignPage = promotionManagementPage().clickAddNewFlashSale();
        // Declare products
        CreateFlashSaleCampaignPage.Product[] products = new CreateFlashSaleCampaignPage.Product[1];
        // Initialize each product
        // If product only one price, we will set product price the same with product name
        products[0] = new CreateFlashSaleCampaignPage.Product(
                productData.getProduct().get(0).getName(),
                productData.getProduct().get(0).getName(),
                "100",
                "100"
        );

        activeFlashSaleCampaign = createFlashSaleCampaignPage.createAFlashSaleCampaign(
                "Active FlashSale" + actionStaff.generateRandomNumber(),
                "Testing",
                actionStaff.getCurrentDate(),
                actionStaff.getHourAndMinuteCustom(0, 2),
                actionStaff.getHourAndMinuteCustom(0, 120),
                products
        );

        // Create active discount campaign
        homePage().helper.navigateToUrl(configObject.getUrlPromotionManagement());
        CreateDiscountCampaignPage createDiscountCampaignPage = promotionManagementPage().clickAddNewAutomaticDiscounts();
        activeDiscountCampaign = createDiscountCampaignPage.createDiscountCampaign(
                "Active DiscountCampaign" + actionStaff.generateRandomNumber(),
                CreateDiscountCampaignPage.ApplicableType.Discount_on_total_bill,
                "10",
                "1000",
                actionStaff.getCurrentDate(),
                actionStaff.getNextDay()
        );

        // Create schedule discount code
        homePage().helper.navigateToUrl(configObject.getUrlPromotionManagement());
        promotionManagementPage().clickDiscountCodeTab();
        createDiscountCodePage = promotionManagementPage().clickPromoCodes();
        scheduleDiscountCode = createDiscountCodePage.createDiscountCode(
                "Schedule DiscountCode" + actionStaff.generateRandomNumber(),
                CreateDiscountCodePage.ApplicableType.Discount_on_total_bill,
                "20",
                "100",
                actionStaff.getNextDay(),
                actionStaff.getHourAndMinuteCustom(8, 2),
                actionStaff.getANumberofNextDay(4),
                actionStaff.getHourAndMinuteCustom(8, 60)
        );

        // Create schedule flashSale campaign
        homePage().helper.navigateToUrl(configObject.getUrlPromotionManagement());
        promotionManagementPage().clickFlashSaleCampaignTab();

        createFlashSaleCampaignPage = promotionManagementPage().clickAddNewFlashSale();
        scheduleFlashSaleCampaign = createFlashSaleCampaignPage.createAFlashSaleCampaign(
                "Schedule FlashSale" + actionStaff.generateRandomNumber(),
                "Testing",
                actionStaff.getNextDay(),
                actionStaff.getHourAndMinuteCustom(8, 2),
                actionStaff.getHourAndMinuteCustom(8, 60),
                products
        );

        // Create schedule discount campaign
        homePage().helper.navigateToUrl(configObject.getUrlPromotionManagement());
        createDiscountCampaignPage = promotionManagementPage().clickAddNewAutomaticDiscounts();
        scheduleDiscountCampaign = createDiscountCampaignPage.createDiscountCampaign(
                "Schedule DiscountCampaign" + actionStaff.generateRandomNumber(),
                CreateDiscountCampaignPage.ApplicableType.Discount_on_total_bill,
                "10",
                "1000",
                actionStaff.getNextDay(),
                actionStaff.getNextDay());

        homePage().helper.refreshPage();
    }

    public void resetTheFlowToGetNewPermission() {
        commonComponents().logout();
        adminPage().navigateToHomePage(emailStaff, passwordStaff);
        homePage().siderBar.clickMnuItemStore().clickPromotion();
    }

    @Test(testName = "FB-13959 : Check 'Delete discount' permission", priority = 1)
    public void FB13959() {
        permission = Permission.Delete_discount;
        homePage().siderBar.clickMnuItemStore().clickPromotion();

        // Check if the staff can see the 'Delete' icon or not
        promotionManagementPage()
                .searchAPromotion(scheduleDiscountCampaign)
                .selectPromotion(scheduleDiscountCampaign);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is not visible while the staff have this permission");

        promotionManagementPage().commonComponents.clickDeleteIcon();
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDelete()), "The 'delete' button is not visible while the staff have this permission");
        promotionManagementPage().commonComponents.clickIgnore();

        // Admin will uncheck the 'Delete discount' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        // Check new permission
        promotionManagementPage()
                .searchAPromotion(scheduleDiscountCampaign)
                .selectPromotion(scheduleDiscountCampaign);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13960 : Check 'Edit discount' permission", priority = 2)
    public void FB13960() {
        permission = Permission.Edit_discount;
        homePage().siderBar.clickMnuItemStore().clickPromotion();

        // Check if the staff can see the 'Edit' icon or not
        promotionManagementPage()
                .searchAPromotion(scheduleDiscountCampaign)
                .selectPromotion(scheduleDiscountCampaign);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit' icon is not visible while the staff have this permission");

        promotionManagementPage().commonComponents.clickEditIcon();
        Assert.assertTrue(actionStaff.isElementVisible(By.xpath("//*[text()='"+adminLocalization.getPromotion().getEdit()+"']")));

        // Admin will uncheck 'Edit discount' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        // Check new permission
        promotionManagementPage()
                .searchAPromotion(scheduleDiscountCampaign)
                .selectPromotion(scheduleDiscountCampaign);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13962 : Check 'Stop discount' permission", priority = 3)
    public void FB13962() {
        permission = Permission.Stop_discount;
        homePage().siderBar.clickMnuItemStore().clickPromotion();

        // Check if the staff can see the 'Stop' icon or not
        promotionManagementPage()
                .searchAPromotion(activeDiscountCampaign)
                .selectPromotion(activeDiscountCampaign);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnStopIcon()), "The 'stop' icon is not visible while the staff have this permission");

        promotionManagementPage().commonComponents.clickStopIcon();
        promotionManagementPage().commonComponents.clickIgnore();

        // Admin will uncheck 'Stop discount' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        // Check new permission
        promotionManagementPage()
                .searchAPromotion(activeDiscountCampaign)
                .selectPromotion(activeDiscountCampaign);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnStopIcon()), "The 'stop' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13964 : Check 'Create discount' permission", priority = 4)
    public void FB13964() {
        permission = Permission.Create_discount;
        homePage().siderBar.clickMnuItemStore().clickPromotion();

        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The staff can not see the add new button while they have this permission");
        promotionManagementPage().helper.navigateToUrl(configObject.getUrlCreateDiscountCampaign());

        // Admin will uncheck 'Create discount' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        // Check new permission
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnAddNew()), "The staff still see the add new button while they do not have this permission");
        actionStaff.navigateTo(configObject.getUrlCreateDiscountCampaign());
        actionStaff.waitForUrl(configObject.getAccessRestrictedPage());
        commonComponents().clickBackToHomepage();

        resetPermission();
    }

    @Test(testName = " FB-13970 : Check 'View discount' permission", priority = 5)
    public void FB13970() {
        permission = Permission.View_discount;
        homePage().siderBar.clickMnuItemStore().clickPromotion();
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getSearchIcon()), "The staff can not see 'Search' icon while staff have full permission");

        // Admin will uncheck 'View discount' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        resetTheFlowToGetNewPermission();

        // Recheck permission
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getSearchIcon()), "The staff still see 'Search' icon while staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13958 : Check 'Stop flash Sale' permission", priority = 6)
    public void FB13958() {
        permission = Permission.Stop_flash_Sale;
        homePage().siderBar.clickMnuItemStore().clickPromotion();
        promotionManagementPage().clickFlashSaleCampaignTab();

        // Check if the staff can see the 'Stop' icon or not
        promotionManagementPage()
                .searchAPromotion(activeFlashSaleCampaign)
                .selectPromotion(activeFlashSaleCampaign);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnStopIcon()), "The 'stop' icon is not visible while the staff have this permission");
        promotionManagementPage().commonComponents.clickStopIcon();
        promotionManagementPage().commonComponents.clickIgnore();

        // Admin will uncheck 'Stop flash Sale' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        // Active DiscountCampaign240253354353
        // Check new permission
        promotionManagementPage().clickFlashSaleCampaignTab();
        promotionManagementPage()
                .searchAPromotion(activeFlashSaleCampaign)
                .selectPromotion(activeFlashSaleCampaign);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnStopIcon()), "The 'stop' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13961 : Check 'Delete flash Sale' permission", priority = 7)
    public void FB13961() {
        permission = Permission.Delete_flash_Sale;
        homePage().siderBar.clickMnuItemStore().clickPromotion();
        promotionManagementPage().clickFlashSaleCampaignTab();

        // Check if the staff can see the 'Delete' icon or not
        promotionManagementPage()
                .searchAPromotion(scheduleFlashSaleCampaign)
                .selectPromotion(scheduleFlashSaleCampaign);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is not visible while the staff have this permission");

        promotionManagementPage().commonComponents.clickDeleteIcon();
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDelete()), "The 'delete' button is not visible while the staff have this permission");
        promotionManagementPage().commonComponents.clickIgnore();

        // Admin will uncheck the 'Delete flash Sale' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        promotionManagementPage().clickFlashSaleCampaignTab();
        // Check new permission
        promotionManagementPage()
                .searchAPromotion(scheduleFlashSaleCampaign)
                .selectPromotion(scheduleFlashSaleCampaign);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13963 : Check 'Edit flash Sale' permission", priority = 8)
    public void FB13963() {
        permission = Permission.Edit_flash_Sale;
        homePage().siderBar.clickMnuItemStore().clickPromotion();
        promotionManagementPage().clickFlashSaleCampaignTab();

        // Check if the staff can see the 'Edit' icon or not
        promotionManagementPage()
                .searchAPromotion(scheduleFlashSaleCampaign)
                .selectPromotion(scheduleFlashSaleCampaign);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit' icon is not visible while the staff have this permission");

        promotionManagementPage().commonComponents.clickEditIcon();
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnSave()), "Can not navigate to edit page");

        // Admin will uncheck 'Edit flash Sale' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        // Check new permission
        promotionManagementPage().clickFlashSaleCampaignTab();
        promotionManagementPage()
                .searchAPromotion(scheduleFlashSaleCampaign)
                .selectPromotion(scheduleFlashSaleCampaign);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13969 : Check 'View flash sale' permission", priority = 9)
    public void FB13969() {
        permission = Permission.View_flash_sale;
        homePage().siderBar.clickMnuItemStore().clickPromotion();
        promotionManagementPage().clickFlashSaleCampaignTab();
        Assert.assertTrue(actionStaff.isElementVisible(By.xpath("//*[text()='"+activeFlashSaleCampaign+"']")), "The staff can not see the data while staff have full permission");

        // Admin will uncheck 'View flash sale' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        resetTheFlowToGetNewPermission();

        // Recheck permission
        promotionManagementPage().clickFlashSaleCampaignTab();
        Assert.assertFalse(actionStaff.isElementVisible(By.xpath("//*[text()='"+activeFlashSaleCampaign+"']")), "The staff can not see the data while staff have full permission");

        resetPermission();
    }

    @Test(testName = "FB-13965 : Check 'Delete discount code' permission", priority = 11)
    public void FB13965() {
        permission = Permission.Delete_discount_code;
        homePage().siderBar.clickMnuItemStore().clickPromotion();
        promotionManagementPage().clickDiscountCodeTab();

        // Check if the staff can see the 'Delete' icon or not
        promotionManagementPage()
                .searchAPromotion(scheduleDiscountCode)
                .selectPromotion(scheduleDiscountCode);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is not visible while the staff have this permission");

        promotionManagementPage().commonComponents.clickDeleteIcon();
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnDelete()), "The 'delete' button is not visible while the staff have this permission");
        promotionManagementPage().commonComponents.clickIgnore();

        // Admin will uncheck the 'Delete discount code' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        promotionManagementPage().clickDiscountCodeTab();
        // Check new permission
        promotionManagementPage()
                .searchAPromotion(scheduleDiscountCode)
                .selectPromotion(scheduleDiscountCode);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnDeleteIcon()), "The 'delete' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13966 : Check 'Edit discount code' permission", priority = 12)
    public void FB13966() {
        permission = Permission.Edit_discount_code;
        homePage().siderBar.clickMnuItemStore().clickPromotion();
        promotionManagementPage().clickDiscountCodeTab();

        // Check if the staff can see the 'Edit' icon or not
        promotionManagementPage()
                .searchAPromotion(scheduleDiscountCode)
                .selectPromotion(scheduleDiscountCode);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit' icon is not visible while the staff have this permission");

        promotionManagementPage().commonComponents.clickEditIcon();
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnSave()), "Can not navigate to edit page");

        // Admin will uncheck 'Edit flash Sale' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        // Check new permission
        promotionManagementPage().clickDiscountCodeTab();
        promotionManagementPage()
                .searchAPromotion(scheduleDiscountCode)
                .selectPromotion(scheduleDiscountCode);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnEditIcon()), "The 'edit' icon is still visible while the staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-13968 : Check 'View discount code' permission", priority = 14)
    public void FB13968() {
        permission = Permission.View_discount_code;
        homePage().siderBar.clickMnuItemStore().clickPromotion();
        promotionManagementPage().clickDiscountCodeTab();
        Assert.assertTrue(actionStaff.isElementVisible(By.xpath("//*[text()='"+activeDiscountCode+"']")), "The staff can not see the data while staff have full permission");

        // Admin will uncheck 'View discount code' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);
        resetTheFlowToGetNewPermission();

        // Recheck permission
        promotionManagementPage().clickDiscountCodeTab();
        Assert.assertTrue(actionStaff.isElementVisible(By.xpath("//*[text()='"+activeDiscountCode+"']")), "The staff still can see the data while staff do not have this permission");

        resetPermission();
    }

    @Test(testName = "FB-14159 : Check 'Stop discount code' permission", priority = 15)
    public void FB14159() {
        permission = Permission.Stop_discount_code;
        homePage().siderBar.clickMnuItemStore().clickPromotion();
        promotionManagementPage().clickDiscountCodeTab();

        // Check if the staff can see the 'Stop' icon or not
        promotionManagementPage()
                .searchAPromotion(activeDiscountCode)
                .selectPromotion(activeDiscountCode);
        Assert.assertTrue(actionStaff.isElementVisible(commonComponents().getBtnStopIcon()), "The 'stop' icon is not visible while the staff have this permission");
        promotionManagementPage().commonComponents.clickStopIcon();
        promotionManagementPage().commonComponents.clickIgnore();

        // Admin will uncheck 'Stop flash Sale' permission
        staffManagementPage_admin.unCheckAPermission_FullFlow(groupName, groupPermission, permission);

        resetTheFlowToGetNewPermission();

        // Active DiscountCampaign240253354353
        // Check new permission
        promotionManagementPage().clickDiscountCodeTab();
        promotionManagementPage()
                .searchAPromotion(activeDiscountCode)
                .selectPromotion(activeDiscountCode);
        Assert.assertFalse(actionStaff.isElementVisible(commonComponents().getBtnStopIcon()), "The 'stop' icon is still visible while the staff do not have this permission");

        resetPermission();
    }
}

package com.fnb.web.admin.scenario_test.permissions;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.PagesAdminSetup;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.home.HomePage;
import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import com.fnb.web.admin.pages.store.staff.StaffManagementPage;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.configObject;

public class PermissionBaseTest extends CommonPages {
    protected Setup adminSetup;
    protected PagesAdminSetup adminPage_admin;
    protected HomePage homePage_admin;
    protected StaffManagementPage staffManagementPage_admin;
    protected Helper actionAdmin;
    protected Helper actionStaff;
    protected com.fnb.web.admin.pages.store.staff.DataTest staffDataTest;
    protected Permission permission;
    protected String emailStaff;
    protected String passwordStaff;
    protected GroupPermission groupPermission;
    protected String groupName;
    protected WebDriver driver2() {
        return adminSetup.driver;
    }
    protected boolean lastTestFailed = false;

    public void resetPermission() {
        // Admin will check the view material permission (prepare data)
        staffManagementPage_admin
                .clickPermissionGroupTab()
                .clickGroupName(groupName)
                .checkAPermission(groupPermission, permission)
                .clickUpdate();

        // log out and log in to get permission from other cases
        if (actionStaff.getCurrentURL().equals(configObject.getAccessRestrictedPage())) {
            actionStaff.navigateToUrl(configObject.getUrlBase());
            commonComponents().logout();
            adminPage().navigateToHomePage(emailStaff, passwordStaff);
        }
        else {
            commonComponents().logout();
            adminPage().navigateToHomePage(emailStaff, passwordStaff);
        }
    }

    public void logOutAndLogin() {
        commonComponents().logout();
        adminPage().navigateToHomePage(emailStaff, passwordStaff);
    }

    @BeforeClass
    public void createInstance() throws IOException, AWTException {
        // Admin
        adminSetup = new Setup();
        adminSetup.setupDriver("admin", "");
        adminPage_admin = adminSetup.navigateToAdminPage();
        staffManagementPage_admin = new StaffManagementPage(driver2());
        homePage_admin = new HomePage(driver2());
        adminPage_admin.navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
        homePage_admin.helper.navigateToUrl(configObject.getUrlStaffManagement());
        actionAdmin = adminPage_admin.helper;

        // Staff
        actionStaff = adminPage().helper;
    }

    @BeforeMethod
    public void beforeTest() {
        if (lastTestFailed) {
            adminPage_admin.navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.STAFF_PASSWORD);
            homePage_admin.helper.navigateToUrl(configObject.getUrlStaffManagement());

            adminPage().navigateToHomePage(emailStaff, passwordStaff);
        }
        // Reset the flag for the next test
        lastTestFailed = false;
    }

    @AfterMethod
    public void afterTest(ITestResult result) {
        if (!result.isSuccess()) {
            resetPermission();
            lastTestFailed = true;
            getDriver().get("chrome://settings/clearBrowserData");
            getDriver().findElement(By.xpath("//settings-ui")).sendKeys(Keys.TAB);
            getDriver().findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);

            driver2().get("chrome://settings/clearBrowserData");
            driver2().findElement(By.xpath("//settings-ui")).sendKeys(Keys.TAB);
            driver2().findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
        }
    }

    @AfterClass
    public void afterClass() {
        driver2().quit();
    }
}

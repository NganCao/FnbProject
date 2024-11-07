package com.fnb.web.admin.pages.store.staff;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

@Data
public class StaffManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    private CommonComponents commonComponents;
    By checkBoxFullPermission = By.xpath("//span[normalize-space()='"+ GroupPermission.Full_Permission.getGroupPermission()+"']/preceding-sibling::span");
    By btnUpdate = By.xpath("//button[@type='submit']");

    public StaffManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    public StaffManagementPage clickUpdate() {
        helper.scrollToElementAtBottom(btnUpdate);
        helper.clickElement(btnUpdate);
        commonComponents.waitSuccessToast();
//        commonComponents.waitSuccessToastHidden();
        return this;
    }

    public StaffManagementPage clickStaffTab() {
        helper.clickElement(By.xpath("//div[@data-node-key='staff']"));
        return this;
    }

    public StaffManagementPage clickPermissionGroupTab() {
        helper.clickElement(By.xpath("//div[@data-node-key='permission-group']"));
        helper.waitForElementVisible(By.xpath("//th[normalize-space()='Group Name']"));
        return this;
    }

    public StaffManagementPage clickGroupName(String groupName) {
        commonComponents.selectItem(groupName);
        helper.clickElement(commonComponents.getBtnEditIcon());
        helper.waitForElementVisible(By.xpath("//*[text()='"+ElementData.Edit_Group_Permission_Title+"']"));
        return this;
    }

    public StaffManagementPage editPermission(String groupName, GroupPermission permission) {
        clickGroupName(groupName);

        return this;
    }

    public StaffManagementPage checkFullPermission(String groupName) {
        clickGroupName(groupName);
        if (!helper.isElementVisible(By.xpath("//span[normalize-space()='Full permission on GoF&B']/preceding-sibling::span[contains(@class, 'checked')]"))) {
            helper.clickElement(checkBoxFullPermission);
        }
        return this;
    }

    public StaffManagementPage uncheckFullPermission(String groupName) {
        clickGroupName(groupName);
        if (helper.isElementVisible(By.xpath("//span[normalize-space()='Full permission on GoF&B']/preceding-sibling::span[contains(@class, 'checked')]"))) {
            helper.clickElement(checkBoxFullPermission);
        }
        return this;
    }

    public By checkBoxPermissionEle(GroupPermission groupPermission, Permission permission) {
        return By.xpath("//div[contains(text(),'"+groupPermission.getGroupPermission()+"')]//ancestor::div[@class='ant-space-item']//div[normalize-space()='"+permission.getPermission()+"']/ancestor::span//preceding-sibling::span[contains(@class, 'checked')]");
    }

    public StaffManagementPage checkAPermission(GroupPermission groupPermission, Permission permission) {
        By groupPermissionEle = By.xpath("//div[contains(text(),'"+groupPermission.getGroupPermission()+"')]//ancestor::div[@class='ant-space-item']//span[@class='ant-collapse-header-text']");
        By permissionEle = By.xpath("//div[contains(text(),'"+groupPermission.getGroupPermission()+"')]//ancestor::div[@class='ant-space-item']//div[normalize-space()='"+permission.getPermission()+"']/ancestor::span//preceding-sibling::span");

        helper.scrollToElementAtMiddle(groupPermissionEle);
        helper.clickElement(groupPermissionEle);
        if (!helper.isElementVisible(checkBoxPermissionEle(groupPermission, permission))) {
            helper.clickElement(permissionEle);
            helper.waitForElementVisible(checkBoxPermissionEle(groupPermission, permission));
        }
        helper.clickElement(groupPermissionEle);
        return this;
    }

    public StaffManagementPage unCheckAPermission(GroupPermission groupPermission, Permission permission) {
        By groupPermissionEle = By.xpath("//div[contains(text(),'"+groupPermission.getGroupPermission()+"')]//ancestor::div[@class='ant-space-item']//span[@class='ant-collapse-header-text']");
        By permissionEle = By.xpath("//div[contains(text(),'"+groupPermission.getGroupPermission()+"')]//ancestor::div[@class='ant-space-item']//div[normalize-space()='"+permission.getPermission()+"']/ancestor::span//preceding-sibling::span");

        helper.scrollToElementAtMiddle(groupPermissionEle);
        helper.clickElement(groupPermissionEle);
        if (helper.isElementVisible(checkBoxPermissionEle(groupPermission, permission))) {
            helper.clickElement(permissionEle);
            helper.waitForElementInVisible(checkBoxPermissionEle(groupPermission, permission));
        }
        helper.clickElement(groupPermissionEle);
        return this;
    }

    public StaffManagementPage unCheckAPermission_FullFlow(String groupName, GroupPermission groupPermission, Permission permission) {
        clickPermissionGroupTab();
        clickGroupName(groupName);
        unCheckAPermission(groupPermission, permission);
        clickUpdate();
        return this;
    }
}

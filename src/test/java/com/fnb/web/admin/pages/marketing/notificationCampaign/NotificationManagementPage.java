package com.fnb.web.admin.pages.marketing.notificationCampaign;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Data
public class NotificationManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public CommonComponents commonComponents;

    public NotificationManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }
    private By iconStop = By.xpath("//button[.//*[name()='svg']/*[name()='circle']]");

    public NotificationManagementPage clickStopIcon(){
        helper.clickElement(iconStop);
        return this;
    }

    public NotificationManagementPage searchNotification(String campaignName) {
        commonComponents.searchItem(campaignName);
        return this;
    }

    public NotificationManagementPage selectNotification(String campaignName) {
        commonComponents.selectItem(campaignName);
        return this;
    }

    public void clickAddNew() {
        helper.clickElement(commonComponents.getBtnAddNew());
    }

    public void clickEdit() {
        helper.clickElement(commonComponents.getBtnEditIcon());
    }

    public NotificationManagementPage clickDeleteIcon() {
        helper.clickElement(commonComponents.getBtnDeleteIcon());
        return this;
    }

    public NotificationManagementPage clickIgnore() {
        helper.clickElement(commonComponents.getBtnIgnore());
        return this;
    }
}

package com.fnb.web.admin.pages.marketing.emailCampaign;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmailCampaignManagementPage extends Setup{
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public CommonComponents commonComponents;

    public EmailCampaignManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    public EmailCampaignManagementPage searchEmailCampaign(String campaignName) {
        commonComponents.searchItem(campaignName);
        return this;
    }

    public EmailCampaignManagementPage selectEmailCampaign(String campaignName) {
        commonComponents.selectItem(campaignName);
        return this;
    }

    public void clickEdit() {
        helper.clickElement(commonComponents.getBtnEditIcon());
    }

    public void clickAddNew() {
        helper.clickElement(commonComponents.getBtnAddNew());
    }
}

package com.fnb.web.admin.pages.marketing.notificationCampaign;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateNotificationCampaignPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public CommonComponents commonComponents;

    public CreateNotificationCampaignPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    public enum SendingType {
        SEND_BY_EVENT, SEND_BY_SPECIFIC_TIME, SEND_NOW
    }

    private By txtCampaignName = By.xpath("//input[@id='name']");
    private By checkBoxSendByEvent = By.xpath("//*[text()='"+adminLocalization.getMarketing().getNotificationCampaign().getSendingType().getSendByEvent()+"']/preceding-sibling::span");
    private By checkBoxSendBySpecificTime = By.xpath("//*[text()='"+adminLocalization.getMarketing().getNotificationCampaign().getSendingType().getSendBySpecificTime()+"']/preceding-sibling::span");
    private By checkBoxSendNow = By.xpath("//*[text()='"+adminLocalization.getMarketing().getNotificationCampaign().getSendingType().getSendNow()+"']/preceding-sibling::span");
    private By txtTitle = By.xpath("//input[@id='title']");
    private By txtMessageContent = By.xpath("//textarea[@placeholder='"+adminLocalization.getMarketing().getNotificationCampaign().getEnterMessageContent()+"']");
    public CreateNotificationCampaignPage enterCampaignName(String name) {
        helper.enterText(txtCampaignName, name);
        return this;
    }

    public CreateNotificationCampaignPage selectSendingType(SendingType sendingType) {
        switch (sendingType) {
            case SEND_BY_EVENT -> {
                helper.clickElement(checkBoxSendByEvent);
            }

            case SEND_BY_SPECIFIC_TIME -> {
                helper.clickElement(checkBoxSendBySpecificTime);
            }

            case SEND_NOW -> {
                helper.clickElement(checkBoxSendNow);
            }
        }
        return this;
    }

    public CreateNotificationCampaignPage enterTitle(String title) {
        helper.enterText(txtTitle, title);
        return this;
    }

    public CreateNotificationCampaignPage enterMessageContent(String messageContent) {
        helper.enterText(txtMessageContent, messageContent);
        return this;
    }

    public void clickAddNew() {
        helper.clickElement(commonComponents.getBtnAddNew());
        commonComponents.waitSuccessToast();
    }

    public String createNotificationCampaign(String name, SendingType sendingType, String title, String messageContent) {
        enterCampaignName(name);
        selectSendingType(sendingType);
        enterTitle(title);
        enterMessageContent(messageContent);
        clickAddNew();
        return name;
    }
}

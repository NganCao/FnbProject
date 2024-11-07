package com.fnb.web.admin.pages.marketing.emailCampaign;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import dataObject.Product.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CreateEmailCampaignPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;

    public CreateEmailCampaignPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    private By txtCampaignName = By.xpath("//input[@id='name']");
    private By inputSendingTime = By.xpath("//*[text()='"+adminLocalization.getMarketing().getEmailCampaign().getGeneralTab().getSendingTime()+"']//following::input[1]");
    private By txtSubject = By.xpath("//input[@id='emailSubject']");
    private By txtEmailAddress = By.xpath("//input[@id='emailAddress']");
    private By selSearchSendTo = By.xpath("//input[@id='emailCampaignType']");
    private By btnAddNew = By.xpath("//button[@id='btn-create-email-campaign']");

    public CreateEmailCampaignPage enterName(String name) {
        helper.enterText(txtCampaignName, name);
        return this;
    }

    public CreateEmailCampaignPage selectSendingTime(String sendingTime) {
        helper.getWebElement(inputSendingTime).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        helper.getWebElement(inputSendingTime).sendKeys(Keys.BACK_SPACE);
        helper.enterText(inputSendingTime, sendingTime);
        helper.getWebElement(inputSendingTime).sendKeys(Keys.ENTER);
        return this;
    }

    public CreateEmailCampaignPage enterSubject(String subject) {
        helper.enterText(txtSubject, subject);
        return this;
    }

    public enum SendTo {
        SENDING_TO_EMAIL_ADDRESS,
        SENDING_TO_CUSTOMER_GROUP
    }
    public CreateEmailCampaignPage selectSendTo(SendTo serviceType) {
        String sendToEmailAddress = adminLocalization.getMarketing().getEmailCampaign().getSendToEmailAddress();
        String sendToCustomerGroup = adminLocalization.getMarketing().getEmailCampaign().getSendToCustomerGroup();

        switch (serviceType) {
            case SENDING_TO_EMAIL_ADDRESS -> {
                commonComponents.searchAndClickForSelSearchInput(selSearchSendTo, sendToEmailAddress);
            }

            case SENDING_TO_CUSTOMER_GROUP -> {
                commonComponents.searchAndClickForSelSearchInput(selSearchSendTo, sendToCustomerGroup);
            }
        }
        return this;
    }

    public CreateEmailCampaignPage enterEmailAddress(String emailAddress) {
        helper.enterText(txtEmailAddress, emailAddress);
        return this;
    }

    public void clickAddNew() {
        helper.clickElement(btnAddNew);
        commonComponents.waitSuccessToast();
    }

    public String createEmailCampaign(String name, String sendingTime, String subject, SendTo sendTo, String emailAddress){
        enterName(name);
        selectSendingTime(sendingTime);
        enterSubject(subject);
        selectSendTo(sendTo);
        enterEmailAddress(emailAddress);
        clickAddNew();
        return name;
    }
}

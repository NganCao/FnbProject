package com.fnb.web.admin.pages.configuration;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.Header;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class OperationTab extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public SiderBar siderBar;
    private Header header;
    private ConfigurationPage configurationPage;
    private CommonComponents commonComponents;

    public OperationTab(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        siderBar = new SiderBar(driver);
        header = new Header(driver);
        commonComponents = new CommonComponents(driver);
        configurationPage = new ConfigurationPage(driver);
    }

    private By radioBtnPayFirst = By.xpath("//*[text()='"+adminLocalization.getConfiguration().getCashierScreen().getPayFirst()+"']/preceding::input[1]/..");
    private By radioBtnOrderFirstPayLater = By.xpath("//*[text()='"+adminLocalization.getConfiguration().getCashierScreen().getOrderFirstPayLater()+"']/preceding::input[1]/..");

    public OperationTab clickPayFirstRadioButton() {
        helper.clickElement(radioBtnPayFirst);
        return this;
    }

    public OperationTab clickOrderFirstPayLaterRadioButton() {
        helper.clickElement(radioBtnOrderFirstPayLater);
        return this;
    }

    public Boolean checkTheRadioOfPayFirst() {
        // Check this button is checked or not
        By checkedElement = By.xpath("//*[text()='"+adminLocalization.getConfiguration().getCashierScreen().getPayFirst()+"']/preceding::input[1]/ancestor::*[contains(@class, 'check')][1]");
        return helper.isElementVisible(checkedElement);
    }

    public Boolean checkTheRadioOfOrderFirstPayLater() {
        // Check this button is checked or not
        By checkedElement = By.xpath("//*[text()='"+adminLocalization.getConfiguration().getCashierScreen().getOrderFirstPayLater()+"']/preceding::input[1]/ancestor::*[contains(@class, 'check')][1]");
        return helper.isElementVisible(checkedElement);
    }

    public void choosePayFirst() {
        // Check if the radio button is checked or not
        if (!checkTheRadioOfPayFirst()) {
            clickPayFirstRadioButton();
            configurationPage.clickSaveChanges();
        }
    }

    public void chooseOrderFirstPayLater() {
        // Check if the radio button is checked or not
        if (!checkTheRadioOfOrderFirstPayLater()) {
            clickOrderFirstPayLaterRadioButton();
            configurationPage.clickSaveChanges();
        }
    }

    String divKitchen = "(//*[.//*[text()='"+adminLocalization.getConfiguration().getKitchenSystem().getTitle()+"'] and .//button])[last()]";
    public void turnOnKitchen() {
        if(!helper.isElementVisible(By.xpath(divKitchen + "//button[contains(@class, 'checked')]"))) {
            // Click switch button
            helper.clickElement(By.xpath(divKitchen + "//button"));
            commonComponents.waitSuccessToast();
        }
    }

    public void turnOffKitchen() {
        if(helper.isElementVisible(By.xpath(divKitchen + "//button[contains(@class, 'checked')]"))) {
            // Click switch button
            helper.clickElement(By.xpath(divKitchen + "//button"));
            commonComponents.waitSuccessToast();
        }
    }

    String divOOS = "(//*[.//*[text()='"+adminLocalization.getConfiguration().getCashierScreen().getAllowOrderOutOfStock()+"'] and .//button[@type='button']])[last()]";
    public void turnOnOOS() {
        if(!helper.isElementVisible(By.xpath(divOOS + "//button[contains(@class, 'checked')]"))) {
            // Click switch button
            helper.clickElement(By.xpath(divOOS + "//button"));
            configurationPage.clickSaveChanges();
        }
    }

    public void turnOffOOS() {
        if(helper.isElementVisible(By.xpath(divOOS + "//button[contains(@class, 'checked')]"))) {
            // Click switch button
            helper.clickElement(By.xpath(divOOS + "//button"));
            configurationPage.clickSaveChanges();
        }
    }
}

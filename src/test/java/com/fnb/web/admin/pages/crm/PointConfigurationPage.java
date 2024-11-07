package com.fnb.web.admin.pages.crm;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.admin.pages.configuration.ConfigurationPage;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class PointConfigurationPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;
    public SiderBar siderBar;
    public ConfigurationPage configurationPage;

    public PointConfigurationPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
        siderBar = new SiderBar(driver);
        configurationPage = new ConfigurationPage(driver);
    }

    private By txtExchangeRate_EarningPoint = By.xpath("//input[@id='loyaltyPointConfig-earningPointExchangeValue' and not(@disabled)]");
    private By txtExchangeRate_RedeemPoint = By.xpath("//input[@id='loyaltyPointConfig-redeemPointExchangeValue' and not(@disabled)]");
    private By switchPointConfig = By.xpath("//*[text()='Loyalty point configuration']//following::button[contains(@class, 'switch')][1]");

    public PointConfigurationPage turnOnLoyaltyPointConfiguration() {
        // Check the switch config button is on or off
        if (helper.isElementVisible(By.xpath("//*[text()='Loyalty point configuration']//following-sibling::button[contains(@aria-checked, 'false')][1]"))) {
            helper.clickElement(switchPointConfig);
        }
        return this;
    }

    public PointConfigurationPage turnOffLoyaltyPointConfiguration() {
        // Check the switch config button is on or off
        if (helper.isElementVisible(By.xpath("//*[text()='Loyalty point configuration']//following-sibling::button[contains(@aria-checked, 'true')][1]"))) {
            helper.clickElement(switchPointConfig);
        }
        return this;
    }

    public PointConfigurationPage enterEarningPoint_ExchangePoint(String point) {

        helper.getWebElement(txtExchangeRate_EarningPoint).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        helper.getWebElement(txtExchangeRate_EarningPoint).sendKeys(Keys.BACK_SPACE);
        helper.enterText(txtExchangeRate_EarningPoint, point);
        return this;
    }

    public PointConfigurationPage enterRedeemPoint_ExchangePoint(String point) {
        helper.getWebElement(txtExchangeRate_RedeemPoint).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        helper.getWebElement(txtExchangeRate_RedeemPoint).sendKeys(Keys.BACK_SPACE);
        helper.enterText(txtExchangeRate_RedeemPoint, point);
        return this;
    }

    public  PointConfigurationPage clickSaveChanges() {
        configurationPage.clickSaveChanges(); // Use this save changes button in Configuration page to avoid duplicate element
        return this;
    }
}

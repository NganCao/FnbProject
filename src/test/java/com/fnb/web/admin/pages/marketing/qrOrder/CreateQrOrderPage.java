package com.fnb.web.admin.pages.marketing.qrOrder;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CreateQrOrderPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;

    public CreateQrOrderPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    private By txtName = By.xpath("//input[@id='name']");
    private By selSearchBranch = By.xpath("//input[@id='branchId']");
    private By selSearchArea = By.xpath("//input[@id='areaId']");
    private By selSearchTable = By.xpath("//input[@id='tableId']");
    private By txtValidFromDate = By.xpath("//input[@id='startDate']");
    private By txtValidUntilDate = By.xpath("//input[@id='endDate']");
    private By selSearchServiceType = By.xpath("//input[@id='serviceTypeId']//ancestor::div[contains(@class, 'ant-form-item-control')][1]");
    public CreateQrOrderPage enterName(String campaignName) {
        helper.enterText(txtName, campaignName);
        return this;
    }

    public CreateQrOrderPage selectBranch(String branch) {
        commonComponents.searchAndClickForSelSearchInput(selSearchBranch, branch);
        return this;
    }

    public enum ServiceType {
        INSTORE, DELIVERY
    }

    public CreateQrOrderPage selectServiceType(ServiceType serviceType) {
        switch (serviceType) {
            case INSTORE -> {
                helper.clickElement(selSearchServiceType);
                helper.clickElement(By.xpath("//*[contains(@name, 'instore')]"));
            }

            case DELIVERY -> {
                helper.clickElement(selSearchServiceType);
                helper.clickElement(By.xpath("//*[contains(@name, 'delivery')]"));
            }
        }
        return this;
    }

    public CreateQrOrderPage selectArea(String area) {
        commonComponents.searchAndClickForSelSearchInput(selSearchArea, area);
        return this;
    }

    public CreateQrOrderPage selectTable(String table) {
        commonComponents.searchAndClickForSelSearchInput(selSearchTable, table);
        return this;
    }

    public CreateQrOrderPage selectValidFromDate(String validFromDate) {
        helper.getWebElement(txtValidFromDate).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        helper.getWebElement(txtValidFromDate).sendKeys(Keys.BACK_SPACE);
        helper.enterText(txtValidFromDate, validFromDate);
        helper.getWebElement(txtValidFromDate).sendKeys(Keys.ENTER);
        return this;
    }

    public CreateQrOrderPage selectValidUntilDate(String validUntilDate) {
        helper.getWebElement(txtValidUntilDate).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        helper.getWebElement(txtValidUntilDate).sendKeys(Keys.BACK_SPACE);
        helper.enterText(txtValidUntilDate, validUntilDate);
        helper.getWebElement(txtValidUntilDate).sendKeys(Keys.ENTER);
        return this;
    }

    public CreateQrOrderPage selectTarget() {
        return this;
    }

    public void clickAddNew() {
        helper.scrollToElementAtBottom(commonComponents.getBtnAddNew());
        helper.clickElement(commonComponents.getBtnAddNew());
        commonComponents.waitSuccessToast();
    }

    public String createAQrOder(String campaignName, String branch, ServiceType serviceType,String area, String table, String validFromDate, String validUntilDate) {
        enterName(campaignName);
        selectBranch(branch);
        selectServiceType(serviceType);
        switch (serviceType) {
            case INSTORE -> {
                selectArea(area);
                selectTable(table);
            }
        }
        selectValidFromDate(validFromDate);
        selectValidUntilDate(validUntilDate);
        clickAddNew();
        return campaignName;
    }
}

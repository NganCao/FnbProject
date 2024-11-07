package com.fnb.web.pos.pages;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.pos.pages.component.MainContentColumn;
import com.fnb.web.pos.pages.component.RightCheckOutColumn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.fnb.web.setup.Setup.configObject;
import static com.fnb.web.setup.Setup.posLocalization;

public class SettingDialog {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public MainContentColumn mainContentColumn;
    public RightCheckOutColumn rightCheckOutColumn;

    public SettingDialog(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        mainContentColumn = new MainContentColumn(driver);
        rightCheckOutColumn = new RightCheckOutColumn(driver);
    }

    private By btnAddNewDevice = By.xpath("//button[contains(@class, 'btn-add-new-device')]");
    private By btnCreate = By.xpath("//button[contains(@class,'save-device')]");
    private By closeIcon = By.xpath("//div[contains(@class, 'close-icon')]//*[name()='svg']");

    public void clickCloseIcon() {
        helper.clickElement(closeIcon);
    }

    public SettingDialog clickCreate() {
        helper.clickElement(btnCreate);

        // Wait the success toast message
        helper.waitForElementVisible(By.xpath("//div[contains(@class, 'notice-success ')]"));
        return this;
    }

    public SettingDialog clickAddNewDevice() {
        helper.clickElement((btnAddNewDevice));
        helper.sleep(1.5);
        return this;
    }

    public void selectOption(String optionTitle, String optionType) {
        // Click selection search
        helper.clickElement(By.xpath("//*[text()='"+optionTitle+"']//following::span[contains(@class, 'selection')][1]/.."));
        // Choose bill type
        helper.clickElement(By.xpath("//*[@title='"+optionType+"' and contains(@class, 'item-option')]"));
    }

    public SettingDialog selectBillType(String billType) {
        selectOption(posLocalization.getSetting().getBillType().getTitle(), billType);
        return this;
    }

    public SettingDialog selectBillSize(String billSize) {
        selectOption(posLocalization.getSetting().getBillSize().getTitle(), billSize);
        return this;
    }

    public SettingDialog selectDeviceType(String deviceType) {
        selectOption(posLocalization.getSetting().getDeviceType().getTitle(), deviceType);
        return this;
    }

    public SettingDialog selectDeviceName(String deviceName) {
        helper.smartWait();
        selectOption(posLocalization.getSetting().getDeviceName().getTitle(), deviceName);
        return this;
    }
}

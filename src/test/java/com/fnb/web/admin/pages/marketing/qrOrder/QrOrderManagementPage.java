package com.fnb.web.admin.pages.marketing.qrOrder;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QrOrderManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public CommonComponents commonComponents;

    public QrOrderManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    public QrOrderManagementPage searchQrOrder(String eventName) {
        commonComponents.searchItem(eventName);
        return this;
    }

    public QrOrderManagementPage selectQrOrder(String eventName) {
        commonComponents.selectItem(eventName);
        return this;
    }

    public QrOrderManagementPage clickDeleteIcon() {
        commonComponents.clickDeleteIcon();
        return this;
    }

    public QrOrderManagementPage clickIgnore() {
        commonComponents.clickIgnore();
        return this;
    }

    public void clickEdit() {
        commonComponents.clickEditIcon();
    }

    public void clickAddNew() {
        helper.clickElement(commonComponents.getBtnAddNew());
    }
}

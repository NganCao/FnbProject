package com.fnb.web.admin.pages.configuration;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.Header;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class BillAndTicketTab extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public SiderBar siderBar;
    private Header header;
    private ConfigurationPage configurationPage;

    public BillAndTicketTab(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        siderBar = new SiderBar(driver);
        header = new Header(driver);
        configurationPage = new ConfigurationPage(driver);
    }

    By receipts = By.xpath("//*[text()='"+adminLocalization.getConfiguration().getCashierScreen().getReceipts()+"' and @class='fnb-button-compound__title']");
    By selSearchSize = By.xpath("//p[@class='option-frame-size']");

    public BillAndTicketTab selectSmallBillSize() {
        helper.clickElement(selSearchSize);
        helper.getTheVisibleElement(By.xpath("//p[text()='"+adminLocalization.getReceipt().getSmallSizeOption()+"']")).click();
        if(helper.isElementVisible(configurationPage.getBtnSaveChanges())) {
            configurationPage.clickSaveChanges();
        }
        return this;
    }
}

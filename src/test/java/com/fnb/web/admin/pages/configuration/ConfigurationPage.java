package com.fnb.web.admin.pages.configuration;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.Header;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Data
public class ConfigurationPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public SiderBar siderBar;
    private Header header;
    private CommonComponents commonComponents;

    public ConfigurationPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        siderBar = new SiderBar(driver);
        header = new Header(driver);
        commonComponents = new CommonComponents(driver);
    }

    private By tabOperation = By.xpath("//span[text()='"+adminLocalization.getConfiguration().getOperation()+"']");
    private By tabBillAndTickets = By.xpath("//span[text()='"+adminLocalization.getConfiguration().getBillAndTickets()+"']");
    private By btnSaveChanges = By.xpath("(//*[text()='"+adminLocalization.getButton().getSaveChanges()+"'])[last()]");

    public OperationTab clickOperationTab() {
        helper.clickElement(tabOperation);
        return new OperationTab(driver);
    }

    public BillAndTicketTab clickBillAndTicketsTab() {
        helper.clickElement(tabBillAndTickets);
        return new BillAndTicketTab(driver);
    }

    public ConfigurationPage clickSaveChanges() {
        helper.getTheVisibleElement(btnSaveChanges).click();
        commonComponents.waitSuccessToast();
        return this;
    }
}

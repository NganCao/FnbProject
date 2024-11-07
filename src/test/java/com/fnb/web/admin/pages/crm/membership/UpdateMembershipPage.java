package com.fnb.web.admin.pages.crm.membership;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class UpdateMembershipPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;
    public SiderBar siderBar;

    public UpdateMembershipPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
        siderBar = new SiderBar(driver);
    }

    private By txtDiscount = By.xpath("//input[@id='basic_discount']");
    private By txtMaximumDiscount = By.xpath("//input[@id='basic_maximumDiscount']");

    public Double getDiscountValue() {
        return helper.convertStringToDouble(helper.getWebElement(txtDiscount).getAttribute("value"));
    }

    public Double getMaximumDiscount() {
        return helper.convertStringToDouble(helper.getWebElement(txtMaximumDiscount).getAttribute("value"));
    }
}

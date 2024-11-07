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

public class CreateMembershipPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;
    public SiderBar siderBar;

    public CreateMembershipPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
        siderBar = new SiderBar(driver);
    }

    private By txtName = By.xpath("//input[@id='basic_name']");
    private By txtAccumulatedPoint = By.xpath("//input[@id='basic_accumulatedPoint']");
    private By txtDiscount = By.xpath("//input[@id='basic_discount']");
    private By txtMaximumDiscount = By.xpath("//input[@id='basic_maximumDiscount']");

    public void enterName(String name) {
        helper.enterText(txtName, name);
    }

    public void enterAccumulatedPoint(String point) {
        helper.enterText(txtAccumulatedPoint, point);
    }

    public void enterDiscount(String discount) {
        helper.enterText(txtDiscount, discount);
    }

    public void enterMaximumDiscount(String maxDiscount) {
        helper.enterText(txtMaximumDiscount, maxDiscount);
    }

    public void clickSave() {
        helper.clickElement(commonComponents.getBtnSave());
        commonComponents.waitSuccessToast();
        commonComponents.waitSuccessToastHidden();
    }

    public String createAnyKindOfDataMembershipLevel() {
        String membershipLevelName = "Membershil Level" + helper.generateRandomNumber();

        helper.navigateToUrl(configObject.getUrlCreateMembership());
        enterName(membershipLevelName);
        enterAccumulatedPoint(helper.generateRandomNumber());
        enterDiscount("50");
        enterMaximumDiscount("100000");
        clickSave();
        return membershipLevelName;
    }
}

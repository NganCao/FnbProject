package com.fnb.web.admin.pages.crm.segment;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CreateCustomerSegmentPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;
    public SiderBar siderBar;

    public CreateCustomerSegmentPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
        siderBar = new SiderBar(driver);
    }

    private By txtName = By.xpath("//input[@id='name']");
    private By txtTime = By.xpath("//input[contains(@id, 'registrationTime')]");

    public void enterName(String segmentName) {
        helper.enterText(txtName, segmentName);
    }

    public void selectTime(String time) {
        helper.enterText(txtTime, time);
        helper.getWebElement(txtTime).sendKeys(Keys.ENTER);
    }

    public void clickSave() {
        helper.clickElement(commonComponents.getBtnSave());
        commonComponents.waitSuccessToast();
        commonComponents.waitSuccessToastHidden();
    }

    public String createAnyKindOfCustomerSegment() {
        String segmentName = "Segment" + helper.generateRandomNumber();
        String time = helper.getCurrentDate();

        helper.navigateToUrl(configObject.getUrlCreateCustomerSegment());
        enterName(segmentName);
        selectTime(time);
        helper.smartWait();
        clickSave();
        return segmentName;
    }
}

package com.fnb.web.admin.pages.crm.customer;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
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
public class CustomerManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;
    public SiderBar siderBar;

    public CustomerManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
        siderBar = new SiderBar(driver);
    }

    public CustomerDetailPage clickCustomer(String customerName) {
        helper.clickElement(By.xpath("//*[contains(text(), '"+customerName+"')]"));
        return new CustomerDetailPage(driver);
    }

    public CustomerDetailPage clickCustomerByPhone(String phoneNumber) {
        helper.clickElement(By.xpath("//tr[contains(@style, 'cursor')]"));
        return new CustomerDetailPage(driver);
    }
}

package com.fnb.web.admin.pages.crm.customer;

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

public class CustomerDetailPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;
    public SiderBar siderBar;

    public CustomerDetailPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
        siderBar = new SiderBar(driver);
    }

    public String getRewardPoint() {
        return helper.getText(By.cssSelector(".reward-point"));
    }

    public String getName() {
        return helper.getText(By.xpath("//*[text()='Name']//following-sibling::*"));
    }

    public String getLastName() {
        return helper.getText(By.xpath("//*[text()='Last Name']//following-sibling::*"));
    }

    public String getCountry() {
        return helper.getText(By.xpath("//*[text()='Country']//following-sibling::*"));
    }

    public String getAddress() {
        return helper.getText(By.xpath("//*[text()='Address']//following-sibling::*"));
    }

    public String getPhoneNumber() {
        return helper.getText(By.xpath("//*[text()='Phone number']//following-sibling::*"));
    }

    public String getCityProvince() {
        return helper.getText(By.xpath("//*[text()='City/ Province']//following-sibling::*"));
    }

    public String getEmail() {
        return helper.getText(By.xpath("//*[text()='Email']//following-sibling::*"));
    }

    public String getDistrict() {
        return helper.getText(By.xpath("//*[text()='District']//following-sibling::*"));
    }

    public String getBirthday() {
        return helper.getText(By.xpath("//*[text()='Birthday']//following-sibling::*"));
    }

    public String getWard() {
        return helper.getText(By.xpath("//*[text()='Ward']//following-sibling::*"));
    }

    public String getGender() {
        return helper.getText(By.xpath("//*[text()='Gender']//following-sibling::*"));
    }

    public String getRank() {
        return helper.getText(By.xpath("//*[text()='Rank']//following-sibling::*"));
    }

    public String getTotalOrder() {
        return helper.getText(By.xpath("//*[text()='Total Order']//following-sibling::*"));
    }

    public String getTotalMoney() {
        return helper.getText(By.xpath("//*[text()='Total Money']//following-sibling::*"));
    }
}

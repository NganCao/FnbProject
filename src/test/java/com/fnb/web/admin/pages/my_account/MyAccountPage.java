package com.fnb.web.admin.pages.my_account;

import com.fnb.web.admin.pages.packages.Platform;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class MyAccountPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public MyAccountPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    private By tabSubscription = By.xpath("//div[@class='fnb-tabs fnb-tabs-style-tzoid']//li[2]");
    private By btnViewServicePackage = By.xpath("//button[@type='button']");

    public void viewServicePackage() {
        helper.clickElement(tabSubscription);
        helper.clickElement(btnViewServicePackage);
    }

    public void clickTabSubscription() {
        helper.clickElement(tabSubscription);
    }

    public String getExpiryDate(Platform platform) {
        By expirySelector = By.xpath("//tr[td[normalize-space()='"+platform.getPlatformName()+"'] and//span[contains(@class, 'activated')]]//td[4]");
        return helper.getText(expirySelector);
    }
}

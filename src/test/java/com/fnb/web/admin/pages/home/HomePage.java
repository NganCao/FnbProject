package com.fnb.web.admin.pages.home;

import com.fnb.web.admin.pages.common.Header;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.admin.pages.my_account.MyAccountPage;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.testng.Assert;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class HomePage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public HomePage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        siderBar = new SiderBar(driver);
        header = new Header(driver);
    }
    public SiderBar siderBar;
    private Header header;
    private By titleHeading = By.xpath("//span[contains(@class, 'heading')]");

    public void verifyTitleHeading() {
        Assert.assertEquals(helper.waitForElementVisible(titleHeading).getText(), DataTest.Title_Header);
    }

    public void verifyUrl() {
        helper.verifyContains(helper.getCurrentUrl(), configObject.getUrlBase(), "The url not match");
    }

    public MyAccountPage goToMyAccount() {
        helper.navigateToUrl(configObject.getUrlBase());
        header.clickAvatar();
        header.clickBtnMyAccount();
        helper.verifyContains(helper.getCurrentUrl(), DataTest.PAGE_URL, "The url of Home page not match.");
        return new MyAccountPage(driver);
    }

    public void logOut() {
        driver.get(configObject.getUrlBase());
        header.clickLogOut();
        helper.waitForUrl(configObject.getUrlLogin());
    }
}

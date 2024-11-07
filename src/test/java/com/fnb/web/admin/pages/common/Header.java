package com.fnb.web.admin.pages.common;

import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class Header extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public Header(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }
    private By avatar = By.xpath("//span[contains(@class, 'ant-avatar-circle')]");
    private By btnMyAccount = By.xpath("//div[@class='pointer manage-account']");
    private By btnLogOut = By.xpath("//div[@class='pointer log-out-border']");

    public void clickAvatar() {
        helper.clickElement(avatar);
    }

    public void clickBtnMyAccount() {
        helper.clickElement(btnMyAccount);
    }

    public void clickLogOut() {
        helper.clickElement(avatar);
        helper.clickElement(btnLogOut);
    }
}

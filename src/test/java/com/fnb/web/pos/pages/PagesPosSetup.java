package com.fnb.web.pos.pages;

import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class PagesPosSetup extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public PagesPosSetup(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }
    By lblLogin = By.xpath("//h1[normalize-space()='Login']");

    public LoginPosPage navigateToLoginPage() {
        String loginUrl = configObject.getUrlLogin();
        System.out.println(loginUrl);
        driver.get(loginUrl);
        helper.waitForPageLoaded();
        helper.waitTextToBePresent(lblLogin, "Login");
        return new LoginPosPage(driver);
    }

    public RegisterPosPage navigateToRegisterPage() {
        String registerUrl = configObject.getUrlLogin();
        System.out.println(registerUrl);
        driver.get(registerUrl);
        return new RegisterPosPage(driver);
    }

    public InStorePage navigatetoInStorePage(String username, String password, String branchName) {
        LoginPosPage loginPosPage = new LoginPosPage(driver);
        loginPosPage.login(username, password, branchName);
        By successToastMessage = By.xpath("//div[contains(@class, 'message-success')]");
        helper.waitForElementVisible(successToastMessage);
        helper.waitForElementInVisible(successToastMessage);
        return new InStorePage(driver);
    }
}

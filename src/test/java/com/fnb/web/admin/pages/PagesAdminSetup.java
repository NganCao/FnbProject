package com.fnb.web.admin.pages;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.home.HomePage;
import com.fnb.web.admin.pages.login.DataTest;
import com.fnb.web.admin.pages.login.LoginPage;
import com.fnb.web.admin.pages.register.RegisterPage;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class PagesAdminSetup extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public PagesAdminSetup(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }
    By lblLogin = By.xpath("//*[text()='Login']");

    public LoginPage navigateToLoginPage() {
        String loginUrl = configObject.getUrlLogin();
        System.out.println(loginUrl);
        driver.get(loginUrl);
        helper.waitForPageLoaded();
        helper.waitTextToBePresent(lblLogin, "Login");
        return new LoginPage(driver);
    }

    public RegisterPage navigateToRegisterPage() {
        String registerUrl = configObject.getRegister();
        System.out.println(registerUrl);
        driver.get(registerUrl);
        helper.waitForPageLoaded();
        helper.waitTextToBePresent(By.xpath("//*[text()='"+DataTest.STORE_INFORMATION+"']"), DataTest.STORE_INFORMATION);
        return new RegisterPage(driver);
    }

    public HomePage navigateToHomePage(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.loginWithEmailAndPassword(email, password);
    }
}

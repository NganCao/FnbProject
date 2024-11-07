package com.fnb.web.admin.scenario_test.login;

import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.home.HomePage;
import com.fnb.web.admin.pages.login.DataTest;
import com.fnb.web.admin.pages.login.LoginPage;
import com.fnb.web.admin.pages.register.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginScenarioTest extends CommonPages {
    LoginPage loginPage;
    HomePage homePage;
    RegisterPage registerPage;
    @BeforeMethod
    public void navigateToPage() {
        loginPage = adminPage().navigateToLoginPage();
        homePage = homePage();
        registerPage = registerPage();
    }

    @Test(priority = 1, testName = " Verify placeholder of email and password textbox")
    public void UI() throws IOException, InterruptedException {
        Assert.assertEquals(loginPage.getEmailPlaceHolderValue(), DataTest.EMAIL_PLACEHOLDER);
        Assert.assertEquals(loginPage.getPasswordPlaceHolderValue(), DataTest.PASSWORD_PLACEHOLDER);
    }

    @Test(priority = 1, testName = " Verify Register link")
    public void FB6871() throws IOException, InterruptedException {
        loginPage.clickRegisterLink();
        registerPage.verifyHeaderDisplay(DataTest.STORE_INFORMATION);
    }

    @Test(priority = 2, testName = "Verify error message when inputting valid email, empty password")
    public void FB6803() throws IOException, InterruptedException {
        loginPage.enterEmail(DataTest.INPUT_EMAIL);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessagePassword(), DataTest.ERROR_ENTER_EMPTY_PASSWORD);
    }

    @Test(priority = 3, testName = "Verify error message when empty email, inputting valid password")
    public void FB6808() throws IOException, InterruptedException {
        loginPage.enterPassword(DataTest.INPUT_PASSWORD);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageUserName(), DataTest.ERROR_ENTER_EMPTY_EMAIL);
    }

    @Test(priority = 4, testName = "Verify error message when inputting invalid email & password")
    public void FB6810() throws IOException, InterruptedException {
        loginPage.enterEmail(DataTest.INPUT_INVALID_EMAIL);
        loginPage.enterPassword(DataTest.INPUT_INVALID_PASSWORD);
        loginPage.verifyNoPasswordAlert();
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageUserName(), DataTest.ERROR_INVALID_USERNAME);
    }

    @Test(priority = 5, testName = "Verify login successfull when inputting correct email&password (Account has only one store)")
    public void FB6820() throws IOException, InterruptedException {
        loginPage.enterEmail(DataTest.INPUT_EMAIL);
        loginPage.enterPassword(DataTest.INPUT_PASSWORD);
        loginPage.verifyNoUserAlert();
        loginPage.verifyNoPasswordAlert();
        loginPage.clickLoginButton();
        homePage.verifyTitleHeading();
        homePage.logOut();
    }

    @Test(priority = 6, testName = "Verify login successful when inputting correct email&password (Account has only many store)")
    public void FB6821() throws IOException, InterruptedException {
        loginPage.enterEmail(DataTest.MANYSTORE_MANYBRANCH_EMAIL);
        loginPage.enterPassword(DataTest.MANYSTORE_MANYBRANCH_PASSWORD);
        loginPage.verifyNoPasswordAlert();
        loginPage.clickLoginButton();
        loginPage.clickLoginButton();
        loginPage.detectScrollbar();
        loginPage.selectStoreByIndex(3);
        homePage().verifyTitleHeading();
        homePage.logOut();
    }

    @Test(priority = 7, testName = "Verify login success when inputting correct email&password (Account has not registered any packages)")
    public void FB6822() throws IOException, InterruptedException {
        // Prepare an account that have no package
        adminPage().navigateToRegisterPage();
        RegisterPage.InforAccount inforAccount = registerPage.registerAnAccount();

        adminPage().helper.refreshPage();
        loginPage.enterEmail(inforAccount.getEmail());
        loginPage.enterPassword(inforAccount.getPassword());
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getNotificationContent(), DataTest.NOTIFICATION_CONTENT);
    }

    @Test(priority = 8, testName = "Verify error message when empty the email and password")
    public void FB6931() throws IOException, InterruptedException {
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageUserName(), DataTest.ERROR_ENTER_EMPTY_EMAIL);
        Assert.assertEquals(loginPage.getErrorMessagePassword(), DataTest.ERROR_ENTER_EMPTY_PASSWORD);
    }

    @Test(priority = 9, testName = "Verify error message when inputting valid email & invalid password")
    public void FB7068() throws IOException, InterruptedException {
        loginPage.enterEmail(DataTest.INPUT_EMAIL);
        loginPage.verifyNoUserAlert();
        loginPage.enterPassword(DataTest.INPUT_INVALID_PASSWORD);
        loginPage.verifyNoPasswordAlert();
    }

    @Test(priority = 10, testName = "Verify error message when inputting invalid email & valid password")
    public void FB7069() throws IOException, InterruptedException {
        loginPage.enterEmail(DataTest.INPUT_INVALID_EMAIL);
        loginPage.enterPassword(DataTest.INPUT_PASSWORD);
        loginPage.verifyNoPasswordAlert();
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageUserName(), DataTest.ERROR_INVALID_USERNAME);
    }

    @AfterMethod
    public void afterTestMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            getDriver().get("chrome://settings/clearBrowserData");
            getDriver().findElement(By.xpath("//settings-ui")).sendKeys(Keys.TAB);
            getDriver().findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
        }
    }
}



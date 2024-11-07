package com.fnb.web.admin.scenario_test.packages;

import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.home.HomePage;
import com.fnb.web.admin.pages.login.LoginPage;
import com.fnb.web.admin.pages.my_account.MyAccountPage;
import com.fnb.web.admin.pages.packages.PackagePage;
import com.fnb.web.admin.pages.packages.PaymentDuration;
import com.fnb.web.admin.pages.packages.Platform;
import com.fnb.web.admin.pages.register.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PackageTest extends CommonPages {
    RegisterPage registerPage;
    LoginPage loginPage;
    PackagePage packagePage;
    MyAccountPage myAccountPage;
    HomePage homePage;
    RegisterPage.InforAccount inforAccount;

    @BeforeMethod
    public void navigateToPage() {
        registerPage = adminPage().navigateToRegisterPage();
        loginPage = loginPage();
        packagePage = packagePage();
        homePage = homePage();
    }

    @AfterMethod
    public void logOut(ITestResult result) {
        if (result.getStatus() != ITestResult.SUCCESS) {
            getDriver().get("chrome://settings/clearBrowserData");
            getDriver().findElement(By.xpath("//settings-ui")).sendKeys(Keys.TAB);
            getDriver().findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
        }
    }

    @Test(description = "Verify that displaying of Subscription date and Expiry date for WEB package")
    public void FB8550() {
        // Precondition (Register an account and approve the first package)
        inforAccount = packagePage.endToEndRegisterAccountAndThePackage(PaymentDuration.ONE_YEAR, Platform.WEB);
        homePage.logOut();

        // Move to profile --> subscription --> choose one more package
        homePage = loginPage.loginWithEmailAndPassword(inforAccount.getEmail(), inforAccount.getPassword());
        homePage.verifyUrl();
        myAccountPage = homePage.goToMyAccount();
        myAccountPage.viewServicePackage();
        packagePage.choosePaymentDuration(PaymentDuration.ONE_YEAR, Platform.WEB);
        packagePage.proceedPayment();
        packagePage.approveThePackage(packagePage.getOrderPackageId());
        inforAccount.setExpiryDate(packagePage.extendPackageSubScriptionDate(packagePage.getSubScriptionDate(), 2));

        // Navigate to check the actual expiry date
        packagePage.clickBackToDashboard();
        homePage.goToMyAccount();
        myAccountPage.clickTabSubscription();

        // Compare actual and expected expiry date
        Assert.assertEquals(myAccountPage.getExpiryDate(Platform.WEB), inforAccount.getExpiryDate());

        homePage.logOut();
    }

    @Test(description = "Verify that displaying of Subscription date and Expiry date for POS package")
    public void FB8551() {
        // Precondition (Register an account and approve the first package)
        inforAccount = packagePage.endToEndRegisterAccountAndThePackage(PaymentDuration.ONE_YEAR, Platform.POS);
        homePage.logOut();

        // Move to profile --> subscription --> choose one more package
        homePage = loginPage.loginWithEmailAndPassword(inforAccount.getEmail(), inforAccount.getPassword());
        homePage.verifyUrl();
        myAccountPage = homePage.goToMyAccount();
        myAccountPage.viewServicePackage();
        packagePage.choosePaymentDuration(PaymentDuration.ONE_YEAR, Platform.POS);
        packagePage.proceedPayment();
        packagePage.approveThePackage(packagePage.getOrderPackageId());
        inforAccount.setExpiryDate(packagePage.extendPackageSubScriptionDate(packagePage.getSubScriptionDate(), 2));

        // Navigate to check the actual expiry date
        packagePage.clickBackToDashboard();
        homePage.goToMyAccount();
        myAccountPage.clickTabSubscription();

        // Compare actual and expected expiry date
        Assert.assertEquals(myAccountPage.getExpiryDate(Platform.POS), inforAccount.getExpiryDate());

        homePage.logOut();
    }

    @Test(description = "Verify that displaying of Subscription date and Expiry date for APP package")
    public void FB8552() {
        // Precondition (Register an account and approve the first package)
        inforAccount = packagePage.endToEndRegisterAccountAndThePackage(PaymentDuration.ONE_YEAR, Platform.APP);
        homePage.logOut();

        // Move to profile --> subscription --> choose one more package
        homePage = loginPage.loginWithEmailAndPassword(inforAccount.getEmail(), inforAccount.getPassword());
        homePage.verifyUrl();
        myAccountPage = homePage.goToMyAccount();
        myAccountPage.viewServicePackage();
        packagePage.choosePaymentDuration(PaymentDuration.ONE_YEAR, Platform.APP);
        packagePage.proceedPayment();
        packagePage.approveThePackage(packagePage.getOrderPackageId());
        inforAccount.setExpiryDate(packagePage.extendPackageSubScriptionDate(packagePage.getSubScriptionDate(), 2));

        // Navigate to check the actual expiry date
        packagePage.clickBackToDashboard();
        homePage.goToMyAccount();
        myAccountPage.clickTabSubscription();

        // Compare actual and expected expiry date
        Assert.assertEquals(myAccountPage.getExpiryDate(Platform.APP), inforAccount.getExpiryDate());

        homePage.logOut();
    }
}
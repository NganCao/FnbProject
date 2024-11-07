package com.fnb.app.android.linstore.scenationtests.posApp;

import com.fnb.app.android.linstore.pages.loginPOS.LoginPOSAppPage;
import com.fnb.app.android.linstore.pages.loginPOS.LoginPOSDataTest;
import com.fnb.app.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPOSScenarios extends BaseTest {
    private LoginPOSAppPage loginPOSAppPage;
    private LoginPOSDataTest loginPOSDataTest;

    @BeforeClass
    public void navigateToPage() {
        loginPOSAppPage = homePageLinStore.navigateToPOSLogin();
    }

    @Test(priority = 0)
    public void loginSuccessfully() {
        Assert.assertTrue(loginPOSAppPage.checkLoginSuccessfully(loginPOSDataTest.EMAIL, loginPOSDataTest.PASSWORD), loginPOSAppPage.actualRS);
    }
}

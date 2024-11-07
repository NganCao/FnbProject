package com.fnb.app.storeapp.android.kemstore.scenationtests.login;

import com.fnb.app.storeapp.android.kemstore.pages.login.LoginPageKemStore;
import com.fnb.utils.helpers.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fnb.app.setup.BaseTest;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    LoginPageKemStore loginPageKemStore;
    @BeforeClass
    public void navigateToPage(){
        loginPageKemStore = homePageKemStore.navigateKemStoreToCreate();
    }
    @Test(priority = 1)
    public void sendInfoLogin() throws InterruptedException {
        loginPageKemStore.sendInfo();
        Log.info("Running testcase verify button display");
        String expToastMess = "Đăng nhập thành công";
//        assertEquals(loginPageKemStore.verifyToastMessDisplay(), expToastMess, "Toast message is not match");
    }

//    @Test(priority = 2)
//    public void sendLogout() throws InterruptedException {
//        loginPageKemStore.logoutAccount();
//        Log.info("Running logout testcase verify");
//    }
}

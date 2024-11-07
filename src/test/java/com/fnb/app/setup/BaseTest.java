package com.fnb.app.setup;

import com.fnb.app.posapp.autostore.pages.HomePageAutoStore;
import com.fnb.app.storeapp.android.kemstore.pages.HomePageKemStore;
import com.fnb.app.storeapp.android.linstore.pages.HomePageLinStore;
import com.fnb.utils.helpers.HelperListener;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

import java.io.IOException;

import static com.fnb.app.setup.BaseSetup.driver;

public class BaseTest {
    public static BaseSetup setup;
    public static HomePageLinStore homePageLinStore;
    public static HomePageKemStore homePageKemStore;
    public static HomePageAutoStore homePageAutoStore;

    public AndroidDriver getDriver() {
        return driver;
    }

    @Parameters({"platform", "storeName"})
    @BeforeSuite
    public void beforeSuite(@Optional("Android") String platform, @Optional("LinStore") String storeName) {
        setup = new BaseSetup();
        setup.deleteFile();
        setup.startAppiumServer(platform, storeName);
    }

    @Parameters({"platform", "storeName"})
    @BeforeTest
    public void initTest(@Optional("Android") String platform, @Optional("theme1") String storeName) throws IOException {
        setup.setupDriver(platform, storeName);
//        HelperListener.setDriver(getDriver(), platform, storeName);
        homePageLinStore = setup.navigateLinStore();
        homePageKemStore = setup.navigateKemStore();
        homePageAutoStore = setup.navigateToAutoStore();
    }

    @Parameters({"platform", "storeName"})
    @BeforeClass
    public void initClass(@Optional("Android") String platform, @Optional("theme1") String storeName) throws IOException {
        HelperListener.setDriver(getDriver(), platform, storeName);
    }

    @AfterMethod
    public void takeScreenshot() {
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        setup.tearDownDriver();
    }

    @AfterSuite
    public void afterSuite() {
        setup.stopAppiumServer();
    }
}

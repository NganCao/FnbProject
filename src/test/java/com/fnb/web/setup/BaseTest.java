package com.fnb.web.setup;

import com.fnb.utils.PropertyUtils;
import com.fnb.utils.helpers.HelperListener;
import com.fnb.utils.helpers.ScreenRecoderHelpers;
import com.fnb.web.admin.pages.PagesAdminSetup;
import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.pos.pages.PagesPosSetup;
import com.fnb.web.store.PagesStoreSetup;
import enums.ConfigProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.io.IOException;

import static com.fnb.web.setup.Setup.configObject;

public class BaseTest {
    public static ThreadLocal<Setup> setup = new ThreadLocal<>();
    public static ThreadLocal<PagesAdminSetup> adminPage = new ThreadLocal<>();
    public static ThreadLocal<PagesPosSetup> posPage = new ThreadLocal<>();
    public static ThreadLocal<PagesStoreSetup> storePage = new ThreadLocal<>();
    public ScreenRecoderHelpers screenRecoderHelpers;

    public WebDriver getDriver() {
        return getSetup().driver;
    }

    public Setup getSetup() {
        return setup.get();
    }

    public PagesAdminSetup adminPage() {
        return adminPage.get();
    }

    public PagesPosSetup posPage() {
        return posPage.get();
    }

    public PagesStoreSetup storePage() {
        return storePage.get();
    }

    @BeforeSuite
    public void beforeSuite() {
    }

//    @Parameters({"platform", "theme"})
    @BeforeClass
    public void initTestSetup(@Optional("theme1") String theme) throws AWTException, IOException {
        screenRecoderHelpers = new ScreenRecoderHelpers();
        String platform = "admin";
        setup.set(new Setup());
        getSetup().setupDriver(platform, theme);
        switch (platform) {
            case "pos":
                posPage.set(getSetup().navigateToPOSPage());
                HelperListener.setDriver(getDriver(), platform, theme);
                break;
            case "admin":
                adminPage.set(getSetup().navigateToAdminPage());
                posPage.set(getSetup().navigateToPOSPage());
                HelperListener.setDriver(getDriver(), platform, theme);
                break;
            case "store":
                storePage.set(getSetup().navigateToStorePage());
                HelperListener.setDriver(getDriver(), platform, theme);
                break;
        }
    }

    @AfterClass
    public void endSession() {
        getSetup().tearDownDriver();
    }

    @AfterTest
    public void tearDown() {

    }

    @BeforeMethod
    public void beforeRecording(ITestResult result) {
        if (PropertyUtils.getPropertyValue(ConfigProperties.RECORD_SCREEN).equalsIgnoreCase("yes")) {
            screenRecoderHelpers.startRecording(getTestName(result));
        }
    }

    @AfterMethod
    public void afterRecording(ITestResult result) throws InterruptedException {
        if (result.isSuccess()) {
            if (PropertyUtils.getPropertyValue(ConfigProperties.RECORD_SCREEN).equalsIgnoreCase("yes")) {
                screenRecoderHelpers.stopRecording(false);
            }
        }
        if (!result.isSuccess()) {
            if (PropertyUtils.getPropertyValue(ConfigProperties.RECORD_SCREEN).equalsIgnoreCase("yes")) {
                Thread.sleep(1000);
                screenRecoderHelpers.stopRecording(true);
            }
        }
    }

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }
}

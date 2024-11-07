package com.fnb.utils.helpers;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import static com.fnb.utils.helpers.ExtentManager.getExtentReports;

public class HelperListener implements ITestListener {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String platform;
    private static String theme;
    private HelperCapture captureHelpers;

    public HelperListener() {
    }

    public HelperListener(WebDriver driver, String platform, String theme) {
        this.platform = platform;
        this.theme = theme;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver, String platform, String theme) {
        HelperListener.driver.set(driver);
        HelperListener.platform = platform;
        HelperListener.theme = theme;
    }

    @Override
    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub
        getExtentReports().flush();
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
//        System.out.println("onStart");
        Log.info("Start test for " + context.getName() + " - " + context.getStartDate());
//        ExtentTestManager.saveToReport(platform, theme, result.getInstanceName(), result.getName(), result.getMethod().getDescription());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
        Log.error("Test failed but it is in defined success ratio " + result.getName());
        ExtentTestManager.logMessage("Test failed but it is in defined success ratio " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        captureHelpers = new HelperCapture(getDriver(), platform, theme);
        Log.error("Failed: " + result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName());
        ExtentTestManager.logMessage(Status.FAIL, "Test name: " + result.getName() + " - " + result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName());
        if (!result.getMethod().getDescription().equals(""))
            ExtentTestManager.logMessage(Status.FAIL, "Description: " + result.getMethod().getDescription());
        ExtentTestManager.logMessage(Status.FAIL, "Failure case name: " + result.getName());
        ExtentTestManager.logMessage(Status.FAIL, "Failure Message: : " + result.getThrowable().getMessage());
        ExtentTestManager.addScreenShot(getDriver(), Status.FAIL, result.getTestName());
        captureHelpers.takeFullScreenshot(result, result.getInstanceName(), result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        Log.warn(result.getName() + " test is skipped.");
        ExtentTestManager.logMessage(Status.SKIP, result.getName() + " test is skipped.");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        Log.info("Scenario\n" + result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName() + "\n" + result.getMethod().getDescription());
        String testName = result.getName() + "\n" + result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();
        ExtentTestManager.saveToReport(platform, theme, result.getInstanceName(), testName, result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        Log.info(result.getInstanceName() + " " + result.getName() + " is PASSED!");
        ExtentTestManager.logMessage(Status.PASS, "Test name: " + result.getName() + " - " + result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName());
        if (!result.getMethod().getDescription().equals(""))
            ExtentTestManager.logMessage(Status.PASS, "Description: " + result.getMethod().getDescription());
        ExtentTestManager.addScreenShot(getDriver(), Status.PASS, result.getTestName());
    }
}
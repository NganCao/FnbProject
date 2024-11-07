package com.fnb.utils.helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentManager.getExtentReports();

    public static ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest saveToReport(String platform, String theme, String scenarioName, String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        if (platform.equals("store")) {
            test.assignAuthor("Ngan Cao");
            test.assignCategory(platform.toUpperCase() + "/" + theme.toUpperCase() + "/" + scenarioName.substring(scenarioName.lastIndexOf(".") + 1));
        } else if (platform.equals("pos") || platform.equals("admin")) {
            test.assignAuthor("Phu Nguyen");
            test.assignCategory(platform.toUpperCase() + "/" + scenarioName.substring(scenarioName.lastIndexOf(".") + 1));
        } else {
            test.assignAuthor("Long Bui");
            test.assignCategory(platform.toUpperCase() + "/" + scenarioName.substring(scenarioName.lastIndexOf(".") + 1));
        }
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static void addScreenShot(WebDriver driver, Status status, String message) {
        try {
            String base64Image = "data:image/png;base64,"
                    + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            getTest().log(status, message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
        } catch (Exception exception) {
            Log.error(exception.getMessage());
        }
    }

    public static void logMessage(String message) {
        getTest().log(Status.INFO, message);
    }

    public static void logMessage(Status status, String message) {
        getTest().log(status, message);
    }
}

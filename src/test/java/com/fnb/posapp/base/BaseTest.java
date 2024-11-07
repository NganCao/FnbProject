package com.fnb.posapp.base;

import com.fnb.utils.helpers.Helper;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaseTest {
    public static BaseSetup setup;
    public static AndroidDriver androidDriver;
    public Helper helper;

    protected BaseTest() {
    }

    public static void runADBCommand(String command) {
        try {
            // Execute the ADB command
            Process process = Runtime.getRuntime().exec(command);

            // Read the output from the command
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the command to complete
            int exitCode = process.waitFor();
            System.out.println("Exited with code " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Parameters({"platform"})
    @BeforeSuite(alwaysRun = true)
    protected void beforeSuite(@Optional("Android") String platform) {
        setup = new BaseSetup();
        setup.startAppiumServer();
    }

    @BeforeMethod
    public void initTest() throws IOException {
        runADBCommand("adb uninstall io.appium.uiautomator2.server");
        androidDriver = setup.setupDriver();
        helper = setup.helper;
    }

    @AfterMethod
    public void tearDownDriver() throws InterruptedException {
        setup.tearDownDriver();
    }

    @AfterSuite
    public void tearDown() {
        setup.stopAppiumServer();
    }
}

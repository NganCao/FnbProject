package com.fnb.utils.helpers;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class HelperCapture {
    private WebDriver driver;
    private static String platform;
    private static String theme;
    private String imageRportFolder = System.getProperty("user.dir");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    private String folderScreenshot;

    public HelperCapture(WebDriver driver, String platform, String theme) {
        this.driver = driver;
        this.platform = platform;
        this.theme = theme;
    }

    //Use for other cases, even if the test case has not been completed, still keep the code to check the result status
    public void takeScreenshot(ITestResult result, String screenName) {
        //passed = SUCCESS & failed = FAILURE
        folderScreenshot = "\\Screenshot\\" + platform + "\\" + LocalDate.now();
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                File theDir = new File(imageRportFolder + folderScreenshot);
                if (!theDir.exists()) {
                    theDir.mkdirs(); //create new folder
                }
                FileHandler.copy(source, new File(imageRportFolder + folderScreenshot + "\\"
                        + dateFormat.format(new Date()) + "_" + screenName + ".png"));
                System.out.println("Screenshot taken: " + screenName);
                Reporter.log("Screenshot taken current URL: " + driver.getCurrentUrl(), true);
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    /**
     *
     * @param result //automatically get status test script
     * @param screenName //testCase name
     */
    public void takeFullScreenshot(ITestResult result, String scenarioPath, String screenName) {
        String scenario = scenarioPath.substring(scenarioPath.lastIndexOf(".") + 1);
        if (platform.equals("store")) {
            folderScreenshot = "\\Screenshot\\" + platform + "\\" + theme + "\\" + LocalDate.now() + "\\" + scenario;
        } else {
            folderScreenshot = "\\Screenshot\\" + platform + "\\" + LocalDate.now() + "\\" + scenario;
        }
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                File theDir = new File(imageRportFolder + folderScreenshot);
                if (!theDir.exists()) {
                    theDir.mkdirs(); //create new folder
                }
                BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ImageIO.write(image, "png", new File(imageRportFolder + folderScreenshot + "\\"
                        + dateFormat.format(new Date()) + "_" + screenName + ".png"));
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }
}

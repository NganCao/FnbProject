package com.fnb.app.setup;

import com.aventstack.extentreports.Status;
import com.fnb.app.posapp.autostore.pages.HomePageAutoStore;
import com.fnb.app.storeapp.android.kemstore.pages.HomePageKemStore;
import com.fnb.app.storeapp.android.linstore.pages.HomePageLinStore;
import com.fnb.utils.api.posapp.admin.helpers.APIAminService;
import com.fnb.utils.api.posapp.pos.helpers.APIPosService;
import com.fnb.utils.helpers.*;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Collections;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;

public class BaseSetup {
    public static WebDriverWait wait;
    public static Actions actions;
    public static SoftAssert softAssert;
    public static HelperListener listener;
    public static JsonReader.ConfigObject configObjectAndroid;
    public static AndroidDriver driver;
    public Helper helper;
    public static APIPosService apiPosService;
    public static APIAminService apiAminService;
    static DesiredCapabilities cap;
    static AppiumServiceBuilder builder;
    static AppiumDriverLocalService service;
    public static Response response = null;

    public static AndroidDriver getDriver() {
        return driver;
    }

    public void startAppiumServer(String platform, String storeApp) {
        configObjectAndroid = JsonReader.configObjectApp(platform, storeApp);
        builder = new AppiumServiceBuilder();
        builder.withIPAddress(configObjectAndroid.getRemoteHost()).usingPort(configObjectAndroid.getPort()).withAppiumJS(new File(configObjectAndroid.getAppiumJsPath())).usingDriverExecutable(new File(configObjectAndroid.getAppiumNodePath())).withArgument(BASEPATH, configObjectAndroid.getRemotePath()).withArgument(GeneralServerFlag.SESSION_OVERRIDE).withArgument(GeneralServerFlag.LOG_LEVEL, "debug");
        //.withArgument (GeneralServerFlag.LOG_LEVEL, "debug");
        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        Log.info("start server");
    }

    public void stopAppiumServer() {
        service.stop();
    }

    public void setupDriver(String platform, String theme) throws IOException {
        cap = new DesiredCapabilities();
        // config android device specific setup
        Log.info("Launching Android device...");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, configObjectAndroid.getDeviceName());
        cap.setCapability(MobileCapabilityType.UDID, configObjectAndroid.getUdid());
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, configObjectAndroid.getPlatformName());
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, configObjectAndroid.getPlatformVersion());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, configObjectAndroid.getAutomationName());
        cap.setCapability(MobileCapabilityType.NO_RESET, true);
        //App info
        cap.setCapability("appPackage", configObjectAndroid.getAppPackage());
        cap.setCapability("appActivity", configObjectAndroid.getAppActivity());
        //Create a URL obj for appium server
        String fullUrl = "http://" + configObjectAndroid.getRemoteHost().toString() + ":" + configObjectAndroid.getPort() + configObjectAndroid.getRemotePath().toString();
        Log.info("full url: " + fullUrl);
        URL appiumServer = new URL(fullUrl);
        // init driver
        driver = (new AndroidDriver(appiumServer, cap));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        helper = new Helper(driver, wait);
        actions = new Actions(driver);
        softAssert = new SoftAssert();
//        listener = new HelperListener(driver,platform, theme);
        apiAminService = new APIAminService(driver);
        apiPosService = new APIPosService(driver);
    }

    public HomePageLinStore navigateLinStore() {
        return new HomePageLinStore();
    }

    public HomePageKemStore navigateKemStore() {
        return new HomePageKemStore();
    }

    public HomePageAutoStore navigateToAutoStore() {
        return new HomePageAutoStore();
    }

    public void tearDownDriver() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    public String verifyToastMessDisplay() {
        return helper.getLoginSuccessToast();
    }

    public void deleteFile() {
        String f_path = "logs/app-properties.log";
        Path filePath = Paths.get(f_path);
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                Log.info("File log is deleted...");
            } else {
                Log.info("File log do not exist...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Log.info("Create new file log...");
        }
    }

    public void scrollVerticalAndClick(String text) {
        //Scroll till element which contains text If It is not visible on screen.
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" + ".scrollIntoView(new UiSelector().textMatches(\"" + text + "\").instance(0))")).click();
    }

    public void scrollVertical(String text) {
        //Scroll till element which contains text If It is not visible on screen.
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" + ".scrollIntoView(new UiSelector().textMatches(\"" + text + "\").instance(0))"));
    }

    public void swipeVertical(WebElement elementA, WebElement elementB) {
        int width = driver.manage().window().getSize().getWidth();
        int endPointX = elementA.getLocation().getX();
        int endPointY = elementA.getLocation().getY();
        int startPointX = elementB.getLocation().getX();
        int startPointY = elementB.getLocation().getY();
        new TouchAction<>(driver)
                .press(PointOption.point(startPointX, startPointY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(endPointX, endPointY))
                .release()
                .perform();
    }

    /**
     * click by element's location
     *
     * @param xPoint
     * @param yPoint
     */
    public void clickByCoordinates(int xPoint, int yPoint) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(xPoint, yPoint)).perform();
    }

    public static void tap(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1).addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y)).addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(new Pause(finger, Duration.ofMillis(150))).addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
        System.out.println("Tap with Coordinates");
    }

    public static Status getStatus(Boolean status) {
        if (!(status)) {
            return Status.FAIL;
        } else {
            return Status.PASS;
        }
    }

    public static void addScreenshotToReport(WebDriver driver, Boolean status, String message) {
        ExtentTestManager.addScreenShot(driver, getStatus(status), message);
    }
}

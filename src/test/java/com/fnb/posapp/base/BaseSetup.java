package com.fnb.posapp.base;

import com.fnb.utils.helpers.DataReader;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import constants.PosAppFrameworkConstants;
import dataObject.Crm.Customer;
import dataObject.Inventory.Material;
import dataObject.Inventory.MaterialCategory;
import dataObject.Inventory.Supplier;
import dataObject.Localization.AdminLocalization;
import dataObject.Localization.POSLocalization;
import dataObject.Localization.PosAppLocale;
import dataObject.Product.*;
import dataObject.Store.AreaTable;
import dataObject.Store.Branch;
import dataObject.Store.Tax;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.Platform;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;

public class BaseSetup {
    public static WebDriverWait wait;
    public static Actions actions;
    public static SoftAssert softAssert;
    public static AndroidDriver driver;
    public Helper helper;
    static DesiredCapabilities cap;
    static AppiumServiceBuilder builder;
    static AppiumDriverLocalService service;

    // Data
    public static PosAppLocale posAppLocale;
    public static Product productData;
    public static ProductCategory productCategoryData;
    public static Options optionData;
    public static Material materialData;
    public static Topping toppingData;
    public static SpecificCombo specificComboData;
    public static Tax taxData;
    public static Branch branchData;
    public static MaterialCategory materialCategoryData;
    public static Supplier supplierData;
    public static Customer customerData;
    public static AreaTable areaTableData;

    static boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        }
        return isServerRunning;
    }

    public void startAppiumServer() {
        builder = new AppiumServiceBuilder();
        builder.withIPAddress(PosAppFrameworkConstants.APPIUM_SERVER_HOST)
                .usingPort(PosAppFrameworkConstants.APPIUM_SERVER_PORT)
                .withAppiumJS(new File(PosAppFrameworkConstants.APPIUM_JS_PATH))
                .usingDriverExecutable(new File(PosAppFrameworkConstants.NODEJS_PATH))
                .withArgument(BASEPATH, PosAppFrameworkConstants.REMOTE_PATH)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        Log.info("Start server");

        // Get localization data
        posAppLocale = DataReader.read(PosAppLocale.class);
        materialData = DataReader.read(Material.class);
        productData = DataReader.read(Product.class);
        productCategoryData = DataReader.read(ProductCategory.class);
        optionData = DataReader.read(Options.class);
        toppingData = DataReader.read(Topping.class);
        specificComboData = DataReader.read(SpecificCombo.class);
        taxData = DataReader.read(Tax.class);
        branchData = DataReader.read(Branch.class);
        materialCategoryData = DataReader.read(MaterialCategory.class);
        supplierData = DataReader.read(Supplier.class);
        customerData = DataReader.read(Customer.class);
        areaTableData = DataReader.read(AreaTable.class);
    }

    public void stopAppiumServer() {
        service.stop();
    }

    public AndroidDriver setupDriver() throws IOException {
        cap = new DesiredCapabilities();
        // config android device specific setup
        Log.info("Launching Android device...");
        cap.setCapability("noReset", true);
        cap.setCapability("–session-override",true);
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, PosAppFrameworkConstants.DEVICE_NAME);
        cap.setCapability(MobileCapabilityType.UDID, PosAppFrameworkConstants.UDID);
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, PosAppFrameworkConstants.PLATFORM_VERSION);
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, PosAppFrameworkConstants.AUTOMATION_NAME);
        cap.setCapability(MobileCapabilityType.NO_RESET, false);
        cap.setCapability("autoGrantPermissions", true);
        cap.setCapability("newCommandTimeout", 10000);
        //App info
        cap.setCapability("appPackage", PosAppFrameworkConstants.APP_PACKAGE);
        cap.setCapability("appActivity", PosAppFrameworkConstants.APP_ACTIVITY);
        cap.setCapability(MobileCapabilityType.APP, "C:\\Users\\admin\\Downloads\\POSApp-envSTAG-v3.5.0.42.apk");

        //Create a URL obj for appium server
        String fullUrl = "http://" + PosAppFrameworkConstants.APPIUM_SERVER_HOST.toString() + ":" + PosAppFrameworkConstants.APPIUM_SERVER_PORT + PosAppFrameworkConstants.REMOTE_PATH.toString();
        Log.info("full url: " + fullUrl);
        URL appiumServer = new URL(fullUrl);
        // init driver
        driver = (new AndroidDriver(appiumServer, cap));
        wait = new WebDriverWait(driver, Duration.ofSeconds(PosAppFrameworkConstants.TIMEOUT));
        helper = new Helper(driver, wait);
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        return driver;
    }

    public void tearDownDriver() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}

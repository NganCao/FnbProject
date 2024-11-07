package constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PosAppFrameworkConstants {
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public static final String TEST_RESOURCES_DIR = PROJECT_PATH + File.separator + "src/test/resources";

    public static final String CONFIG_PROPERTIES_PATH = TEST_RESOURCES_DIR + File.separator + "config" + File.separator + "config.properties";

    public static final String EXPORT_VIDEO_PATH = "ExportData/Videos";

    public static final String APPIUM_SERVER_HOST = "127.0.0.1";

    public static final int APPIUM_SERVER_PORT = 4723;

    public static final String APPIUM_JS_PATH = "C:\\Users\\admin\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

    public static final String NODEJS_PATH = "C:\\Program Files\\nodejs\\node.exe";
    public static final String REMOTE_PATH = "/wd/hub";

    //========================================= APP INFOR //=========================================
    public static final String DEVICE_NAME = "sdk_gphone64_x86_64";
    public static final String UDID = "emulator-5554";
    public static final String PLATFORM_VERSION = "15";
    public static final String AUTOMATION_NAME = "UiAutomator2";
    public static final String APP_PACKAGE = "com.gofnb.posapplication";
    public static final String APP_ACTIVITY = "com.gofnb.posapplication.MainActivity";
    public static final int TIMEOUT = 60;

    //===============================================================================================
}

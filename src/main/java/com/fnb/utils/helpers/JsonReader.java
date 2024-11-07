package com.fnb.utils.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public static String getPlatform;
    public static String getTheme;
    public static String getEnviroment;
    public static String localePath = "src/main/java/com/fnb/utils/locales/";
    public static String enviroment;

    public static JsonObject readConfigFile(String configFile) {
        try {
            // Read the configuration file
            FileReader reader = new FileReader(configFile);
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ConfigObject configObject(String platform, String theme) {
        // Read the configuration file
        String configFile = "resources/config.json";
        JsonObject config = readConfigFile(configFile);
        // Create a Gson instance
        Gson gson = new Gson();
        // Create a JsonObject representing the object
        JsonObject jsonObject = new JsonObject();
        JsonObject webConfig = config.get(platform).getAsJsonObject();
        JsonObject themeObject = new JsonObject();
        String env = webConfig.get("env").getAsString();
        setPlatformInfo(platform, theme, env);
        enviroment = env;
        System.out.println("-----env-----: " + env);
        String api_env = "";
        if (!platform.equals("store")) {
            if (env.equals("prod")) {
                env = webConfig.get("prod").getAsString();
            } else if (env.equals("stag")) {
                env = webConfig.get("stag").getAsString();
                api_env = webConfig.get("stag_api").getAsString();
            } else {
                env = webConfig.get("dev").getAsString();
                api_env = webConfig.get("dev_api").getAsString();
            }
        } else {
            themeObject = webConfig.get(theme).getAsJsonObject();
            if (env.equals("prod")) {
                env = themeObject.get("prod").getAsString();
            } else if (env.equals("stag")) {
                env = themeObject.get("stag").getAsString();
            } else {
                env = themeObject.get("dev").getAsString();
            }
        }
        jsonObject.addProperty("env", env);
        jsonObject.addProperty("api_env", api_env);
        jsonObject.addProperty("timeOut", webConfig.get("timeout").getAsNumber());
        jsonObject.addProperty("browser", webConfig.get("browser").getAsString());
        jsonObject.addProperty("urlHome", env + webConfig.get("route").getAsJsonObject().get("home").getAsString());
        jsonObject.addProperty("urlLogin", env + webConfig.get("route").getAsJsonObject().get("login").getAsString());
        jsonObject.addProperty("pathScreenshot", webConfig.get("path").getAsJsonObject().get("screenshot").getAsString());
        jsonObject.addProperty("urlBase", env);
        jsonObject.addProperty("register", env + webConfig.get("route").getAsJsonObject().get("registerAccount").getAsString());
        jsonObject.addProperty("urlLoginStore", env + webConfig.get("route").getAsJsonObject().get("login").getAsString());
        jsonObject.addProperty("urlProductListStore", env + webConfig.get("route").getAsJsonObject().get("productManagement").getAsString());
        jsonObject.addProperty("urlProductManagement", env + webConfig.get("route").getAsJsonObject().get("productManagement").getAsString());

        switch (platform) {
            case "admin":
                jsonObject.addProperty("urlCreateProduct",
                        env + webConfig.get("route").getAsJsonObject().get("productCreate").getAsString());
                jsonObject.addProperty("urlCreateMaterial",
                        env + webConfig.get("route").getAsJsonObject().get("materialCreate").getAsString());
                jsonObject.addProperty("urlMaterialManagement",
                        env + webConfig.get("route").getAsJsonObject().get("materialManagement").getAsString());
                jsonObject.addProperty("urlProductCategoryManagement",
                        env + webConfig.get("route").getAsJsonObject().get("productCategoryCreate").getAsString());
                jsonObject.addProperty("urlOptionManagement",
                        env + webConfig.get("route").getAsJsonObject().get("optionManagement").getAsString());
                jsonObject.addProperty("urlCreateCombo",
                        env + webConfig.get("route").getAsJsonObject().get("comboCreate").getAsString());
                jsonObject.addProperty("urlComboManagement",
                        env + webConfig.get("route").getAsJsonObject().get("comboManagement").getAsString());
                jsonObject.addProperty("urlStaffManagement",
                        env + webConfig.get("route").getAsJsonObject().get("staffManagement").getAsString());
                jsonObject.addProperty("urlFeeTaxManagement",
                        env + webConfig.get("route").getAsJsonObject().get("feeAndTaxManagement").getAsString());
                jsonObject.addProperty("accessRestrictedPage",
                        env + webConfig.get("route").getAsJsonObject().get("accessRestricted").getAsString());
                jsonObject.addProperty("urlReportRevenuePage",
                        env + webConfig.get("route").getAsJsonObject().get("reportRevenue").getAsString());
                jsonObject.addProperty("urlReportTransaction",
                        env + webConfig.get("route").getAsJsonObject().get("reportTransaction").getAsString());
                jsonObject.addProperty("urlReportCustomer",
                        env + webConfig.get("route").getAsJsonObject().get("reportCustomer").getAsString());
                jsonObject.addProperty("urlInventoryHistory",
                        env + webConfig.get("route").getAsJsonObject().get("inventoryHistory").getAsString());
                jsonObject.addProperty("urlMaterialCategoryManagement",
                        env + webConfig.get("route").getAsJsonObject().get("materialCategory").getAsString());
                jsonObject.addProperty("urlSupplierManagement",
                        env + webConfig.get("route").getAsJsonObject().get("supplierManagement").getAsString());
                jsonObject.addProperty("urlPurchaseOrderManagement",
                        env + webConfig.get("route").getAsJsonObject().get("purchaseOrderManagement").getAsString());
                jsonObject.addProperty("urlCreatePurchaseOrder",
                        env + webConfig.get("route").getAsJsonObject().get("createPurchaseOrder").getAsString());
                jsonObject.addProperty("urlTransferManagement",
                        env + webConfig.get("route").getAsJsonObject().get("transferMaterialManagement").getAsString());
                jsonObject.addProperty("urlCreateTransferMaterial",
                        env + webConfig.get("route").getAsJsonObject().get("createTransferMaterial").getAsString());
                jsonObject.addProperty("urlCreateCustomer",
                        env + webConfig.get("route").getAsJsonObject().get("createCustomer").getAsString());
                jsonObject.addProperty("urlAreaTable",
                        env + webConfig.get("route").getAsJsonObject().get("areaTableManagement").getAsString());
                jsonObject.addProperty("urlCustomerManagement",
                        env + webConfig.get("route").getAsJsonObject().get("customerManagement").getAsString());
                jsonObject.addProperty("urlCustomerSegmentManagement",
                        env + webConfig.get("route").getAsJsonObject().get("customerSegmentManagement").getAsString());
                jsonObject.addProperty("urlMembership",
                        env + webConfig.get("route").getAsJsonObject().get("memberShip").getAsString());
                jsonObject.addProperty("urlPointConfiguration",
                        env + webConfig.get("route").getAsJsonObject().get("pointConfiguration").getAsString());
                jsonObject.addProperty("urlCreateMembership",
                        env + webConfig.get("route").getAsJsonObject().get("createMembership").getAsString());
                jsonObject.addProperty("urlCreateCustomerSegment",
                        env + webConfig.get("route").getAsJsonObject().get("createCustomerSegment").getAsString());
                jsonObject.addProperty("urlPromotionManagement",
                        env + webConfig.get("route").getAsJsonObject().get("promotionManagement").getAsString());
                jsonObject.addProperty("urlCreateDiscountCampaign",
                        env + webConfig.get("route").getAsJsonObject().get("createDiscountCampaign").getAsString());
                jsonObject.addProperty("urlCreateFlashSaleCampaign",
                        env + webConfig.get("route").getAsJsonObject().get("createFlashSaleCampaign").getAsString());
                jsonObject.addProperty("urlCreateDiscountCode",
                        env + webConfig.get("route").getAsJsonObject().get("createDiscountCode").getAsString());
                jsonObject.addProperty("urlQrOrderManagement",
                        env + webConfig.get("route").getAsJsonObject().get("qrOrderManagement").getAsString());
                jsonObject.addProperty("urlCreateQrOrder",
                        env + webConfig.get("route").getAsJsonObject().get("createQrOrder").getAsString());
                jsonObject.addProperty("urlEmailCampaignManagement",
                        env + webConfig.get("route").getAsJsonObject().get("emailCampaignManagement").getAsString());
                jsonObject.addProperty("urlCreateEmailCampaign",
                        env + webConfig.get("route").getAsJsonObject().get("createEmailCampaign").getAsString());
                jsonObject.addProperty("urlNotificationCampaignManagement",
                        env + webConfig.get("route").getAsJsonObject().get("notificationCampaignManagement").getAsString());
                jsonObject.addProperty("urlCreateNotificationCampaign",
                        env + webConfig.get("route").getAsJsonObject().get("createNotificationCampaign").getAsString());
                jsonObject.addProperty("urlTransferControl",
                        env + webConfig.get("route").getAsJsonObject().get("transferControl").getAsString());
                case "pos":

            default:
                System.out.println("Invalid Option");
        }

        //TODO
        String osName = "win";
        if (osName.contains("win")) {
            System.out.println("-----osName-----: " + osName);
            jsonObject.addProperty("pathWebDriverChrome", webConfig.get("web_driver_path").getAsJsonObject().get("win").getAsJsonObject().get("chrome").getAsString());
        } else if (osName.contains("nix") || osName.contains("nux")) {
            System.out.println("-----osName-----: " + osName);
            jsonObject.addProperty("pathWebDriverChrome", webConfig.get("web_driver_path").getAsJsonObject().get("linux").getAsJsonObject().get("chrome").getAsString());
        } else {
            // nothing
        }

        ConfigObject configObject = gson.fromJson(jsonObject, ConfigObject.class);
        // Return the object
        return configObject;
    }

    public static ConfigObject configObjectApp(String platform, String storeApp) {
        // Read the configuration file
        String configFile = "resources/config.json";
        JsonObject config = readConfigFile(configFile);
        // Create a Gson instance
        Gson gson = new Gson();

        // Create a JsonObject representing the object
        JsonObject jsonObject = new JsonObject();
        JsonObject androidConfig = config.get(platform).getAsJsonObject();
        JsonObject androidConfigEnv;
        String env = androidConfig.get("env").getAsString();
        getTheme = "theme1";
        getEnviroment = env;
        System.out.println("-----env-----: " + env);
        if (env.equals("prod")) {
            androidConfigEnv = androidConfig.get("prod").getAsJsonObject();
        } else if (env.equals("stag")) {
            androidConfigEnv = androidConfig.get("stag").getAsJsonObject();
        } else {
            androidConfigEnv = androidConfig.get("dev").getAsJsonObject();
        }
        jsonObject.addProperty("timeOut", androidConfig.get("timeout").getAsString());
        jsonObject.addProperty("platformName", androidConfig.get("capabilities").getAsJsonObject().get("platformName").getAsString());
        jsonObject.addProperty("platformVersion", androidConfig.get("capabilities").getAsJsonObject().get("platformVersion").getAsString());
        jsonObject.addProperty("deviceName", androidConfig.get("capabilities").getAsJsonObject().get("deviceName").getAsString());
        jsonObject.addProperty("udid", androidConfig.get("capabilities").getAsJsonObject().get("udid").getAsString());
        jsonObject.addProperty("automationName", androidConfig.get("capabilities").getAsJsonObject().get("automationName").getAsString());
        jsonObject.addProperty("port", androidConfig.get("configuration").getAsJsonObject().get("port").getAsString());
        jsonObject.addProperty("remoteHost", androidConfig.get("configuration").getAsJsonObject().get("remoteHost").getAsString());
        jsonObject.addProperty("remotePath", androidConfig.get("configuration").getAsJsonObject().get("remotePath").getAsString());
        jsonObject.addProperty("appiumNodePath", androidConfig.get("configuration").getAsJsonObject().get("appium_node_path").getAsString());
        jsonObject.addProperty("appiumJsPath", androidConfig.get("configuration").getAsJsonObject().get("appium_js_path").getAsString());

        jsonObject.addProperty("appPackage", androidConfigEnv.get(storeApp).getAsJsonObject().get("appPackage").getAsString());
        jsonObject.addProperty("appActivity", androidConfigEnv.get(storeApp).getAsJsonObject().get("appActivity").getAsString());

        ConfigObject configObjectApp = gson.fromJson(jsonObject, ConfigObject.class);
        // Return the object
        return configObjectApp;
    }

    @Data
    public class ConfigObject {
        private String env;
        private String api_env;
        private String browser;
        private Integer timeOut;
        private String urlHome;
        private String urlLogin;
        private String urlCreateProduct;
        private String urlCreateMaterial;
        private String urlMaterialManagement;
        private String urlProductManagement;
        private String urlOptionManagement;
        private String urlProductCategoryManagement;
        private String urlCreateCombo;
        private String urlComboManagement;
        private String urlFeeTaxManagement;
        private String urlStaffManagement;
        private String accessRestrictedPage;
        private String urlReportRevenuePage;
        private String urlReportTransaction;
        private String urlReportCustomer;
        private String urlInventoryHistory;
        private String urlMaterialCategoryManagement;
        private String urlSupplierManagement;
        private String urlPurchaseOrderManagement;
        private String urlCreatePurchaseOrder;
        private String urlTransferManagement;
        private String urlCreateTransferMaterial;
        private String urlCreateCustomer;
        private String urlAreaTable;
        private String urlCustomerManagement;
        private String urlCustomerSegmentManagement;
        private String urlCreateCustomerSegment;
        private String urlMembership;
        private String urlCreateMembership;
        private String urlPointConfiguration;
        private String urlPromotionManagement;
        private String urlCreateDiscountCampaign;
        private String urlCreateFlashSaleCampaign;
        private String urlCreateDiscountCode;
        private String urlQrOrderManagement;
        private String urlCreateQrOrder;
        private String urlEmailCampaignManagement;
        private String urlCreateEmailCampaign;
        private String urlNotificationCampaignManagement;
        private String urlCreateNotificationCampaign;
        private String urlTransferControl;
        private String pathScreenshot;
        private String pathWebDriverChrome;
        //store web
        private String urlCheckout;
        private String urlProductListStore;
        //App android
        private String platformName;
        private String platformVersion;
        private String deviceName;
        private String udid;
        private String automationName;
        private Integer port;
        private String remoteHost;
        private String remotePath;
        private String appiumNodePath;
        private String appPackage;
        private String appActivity;
        private String appiumJsPath;

        public String getAppiumJsPath() {
            return appiumJsPath;
        }

        public void setAppiumJsPath(String appiumJsPath) {
            this.appiumJsPath = appiumJsPath;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getPlatformName() {
            return platformName;
        }

        public void setPlatformName(String platformName) {
            this.platformName = platformName;
        }

        public String getPlatformVersion() {
            return platformVersion;
        }

        public void setPlatformVersion(String platformVersion) {
            this.platformVersion = platformVersion;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getUdid() {
            return udid;
        }

        public void setUdid(String udid) {
            this.udid = udid;
        }

        public String getAutomationName() {
            return automationName;
        }

        public void setAutomationName(String automationName) {
            this.automationName = automationName;
        }


        public String getRemoteHost() {
            return remoteHost;
        }

        public void setRemoteHost(String remoteHost) {
            this.remoteHost = remoteHost;
        }

        public String getRemotePath() {
            return remotePath;
        }

        public void setRemotePath(String remotePath) {
            this.remotePath = remotePath;
        }

        public String getAppiumNodePath() {
            return appiumNodePath;
        }

        public void setAppiumNodePath(String appiumNodePath) {
            this.appiumNodePath = appiumNodePath;
        }

        public String getAppPackage() {
            return appPackage;
        }

        public void setAppPackage(String appPackage) {
            this.appPackage = appPackage;
        }

        public String getAppActivity() {
            return appActivity;
        }

        public void setAppActivity(String appActivity) {
            this.appActivity = appActivity;
        }

        public String getUrlLoginStore1() {
            return urlLoginStore1;
        }

        public void setUrlLoginStore1(String urlLoginStore1) {
            this.urlLoginStore1 = urlLoginStore1;
        }

        private String urlLoginStore1;

        public String getUrlHomeStore1() {
            return urlHomeStore;
        }

        public void setUrlHomeStore1(String urlHomeStore1) {
            this.urlHomeStore = urlHomeStore1;
        }

        private String urlHomeStore;

        public String getUrlProductListStore() {
            return urlProductListStore;
        }

        public void setUrlProductListStore(String urlProductListStore) {
            this.urlProductListStore = urlProductListStore;
        }

        public String getUrlCheckout() {
            return urlCheckout;
        }

        public void setUrlCheckout(String urlCheckout) {
            this.urlCheckout = urlCheckout;
        }

        public String getRegister() {
            return register;
        }

        public void setRegister(String register) {
            this.register = register;
        }

        private String register;

        public String getUrlBase() {
            return urlBase;
        }

        public String getUrlCreateProduct() {
            return urlCreateProduct;
        }

        public String getUrlOptionManagement() {
            return urlOptionManagement;
        }

        public void setUrlBase(String urlBase) {
            this.urlBase = urlBase;
        }

        private String urlBase;

        public String getEnv() {
            return env;
        }

        public void setEnvDev(String env) {
            this.env = env;
        }

        public String getBrowser() {
            return browser;
        }

        public void setBrowser(String browser) {
            this.browser = browser;
        }

        public Integer getTimeOut() {
            return timeOut;
        }

        public void setTimeOut(Integer timeOut) {
            this.timeOut = timeOut;
        }

        public String getUrlHome() {
            return urlHome;
        }

        public void setUrlHome(String urlHome) {
            this.urlHome = urlHome;
        }

        public String getUrlLogin() {
            return urlLogin;
        }

        public void setUrlLogin(String urlLogin) {
            this.urlLogin = urlLogin;
        }

        public String getPathScreenshot() {
            return pathScreenshot;
        }

        public void setPathScreenshot(String pathScreenshot) {
            this.pathScreenshot = pathScreenshot;
        }

        public String getPathWebDriverChrome() {
            return pathWebDriverChrome;
        }

        public void setPathWebDriverChrome(String pathWebDriverChrome) {
            this.pathWebDriverChrome = pathWebDriverChrome;
        }

        public String getApiEnv() {
            return api_env;
        }

        public String getUrlCreateMaterial() {
            return urlCreateMaterial;
        }

        public String getUrlMaterialManagement() {
            return urlMaterialManagement;
        }

        public String getUrlProductManagement() {
            return urlProductManagement;
        }
    }

    public static void setPlatformInfo(String platform, String theme, String enviroment) {
        getPlatform = platform;
        getTheme = theme;
        getEnviroment = enviroment;
    }

    /**
     * Read json with multiple levels
     *
     * @param language current language
     * @param page     Ex: myProfile - jsonObject level1
     * @param keyList  include all the json objects you want to access
     *                 Ex: locales file - get edit profile : accountInfo -> editProfile
     * @return
     */
    public static String localeReader(String language, String page, List<String> keyList) {
        String configFile = getLocalePath(language);
        return jsonReaderMultipleLevels(configFile, page, keyList);
    }

    /**
     * @param configFile json file path
     * @param page       Ex: myProfile - jsonObject level1
     * @param keyList    include all the json objects you want to access
     *                   Ex: locales file - get edit profile : accountInfo -> editProfile
     *                   ** Use for attribute except arrays
     * @return
     */
    public static String jsonReaderMultipleLevels(String configFile, String page, List<String> keyList) {
        JsonObject config = JsonReader.readConfigFile(configFile);
        String value = "", key;
        if (keyList != null) {
            int size = keyList.size();
            JsonObject webConfig = config.get(page).getAsJsonObject();
            for (int i = 0; i < size; i++) {
                key = keyList.get(i);
                if (i == (size - 1)) {
                    value = webConfig.get(key).getAsString();
                } else {
                    webConfig = webConfig.get(key).getAsJsonObject();
                }
            }
        } else {
            value = config.get(page).getAsString();
        }
        return value;
    }

    public static String getLocalePath(String language) {
        String configFile;
        if (getPlatform.equals("store")) {
            configFile = localePath + getPlatform + "/" + getTheme + "/" + language.toLowerCase() + ".json";
        } else {
            configFile = localePath + getPlatform + "/" + language.toLowerCase() + ".json";
        }
        return configFile;
    }

    public static String getProductPath() {
        String configFile = "src/main/java/com/fnb/utils/api/pos/jsonSchema/theme1/productDetails.json";
        return configFile;
    }

    public static List<String> getProductInformationByName(String categoryName) {
        List<String> productName = new ArrayList<>();
        String configFile = getProductPath();
        JsonObject config = JsonReader.readConfigFile(configFile);
        JsonObject webConfig = config.get("productData").getAsJsonObject();
        JsonArray productArr = webConfig.getAsJsonArray("product");
        JsonObject productObject = new JsonObject();
        for (int i = 0; i < productArr.size(); i++) {
            productObject = productArr.get(i).getAsJsonObject();
            if (productObject.get("productCategoryName").getAsString().equals(categoryName)) {
                productName.add(productObject.get("name").getAsString());
            }
        }
        return productName;
    }
}

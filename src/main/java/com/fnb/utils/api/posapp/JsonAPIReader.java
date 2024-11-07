package com.fnb.utils.api.posapp;

import com.fnb.utils.helpers.JsonReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonAPIReader {
    private static String theme = "";
    private static String enviroment = "";
    public static String userNameLocal;
    public static String passwordLocal;

    public static ConfigAPIObject apiReader(String typeAPI) {
        // Read the configuration file
        String configFile = "src/main/java/com/fnb/utils/api/apiConfig.json";
        String url;
        JsonObject config = JsonReader.readConfigFile(configFile);
        // Create a Gson instance
        Gson gson = new Gson();
        // Create a JsonObject representing the object
        JsonObject jsonObject = new JsonObject();
        //Get url
        JsonObject urlConfig = config.get("url").getAsJsonObject();
        JsonObject typeAPIConfig = urlConfig.get(typeAPI).getAsJsonObject();
        theme = JsonReader.getTheme;
        enviroment = JsonReader.getEnviroment;
        if (enviroment.equals("prod")) {
            url = typeAPIConfig.get("prod").getAsString();
        } else if (enviroment.equals("stag")) {
            url = typeAPIConfig.get("stag").getAsString();
        } else {
            url = typeAPIConfig.get("dev").getAsString();
        }
        jsonObject.addProperty("url", url);
        //get user name, password
        JsonObject accountConfig = config.get("account").getAsJsonObject();
        JsonObject themeObject = accountConfig.get(theme).getAsJsonObject();
        JsonObject accountObject = themeObject.get(enviroment).getAsJsonObject();
        userNameLocal = accountObject.get("userName").getAsString();
        passwordLocal = accountObject.get("password").getAsString();
        System.out.println(userNameLocal + " " + passwordLocal);
        jsonObject.addProperty("userName", accountObject.get("userName").getAsString());
        jsonObject.addProperty("password", accountObject.get("password").getAsString());
        jsonObject.addProperty("storeIndex", accountObject.get("storeIndex").getAsString());
        ConfigAPIObject configObject = gson.fromJson(jsonObject, ConfigAPIObject.class);
        // Return the object
        return configObject;
    }

    //get config of api
    public class ConfigAPIObject {
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getStoreIndex() {
            return storeIndex;
        }

        public void setStoreIndex(String storeIndex) {
            this.storeIndex = storeIndex;
        }

        private String url;
        private String userName;
        private String password;
        private String storeId;
        private String accountId;
        private String storeIndex;
    }
}

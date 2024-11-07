package com.fnb.utils.api.storeapp.pos;

import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.fnb.utils.api.storeapp.pos.DataTest.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Get_Branch_InformationRequest {

    public static String getGoogleApiKey() {
        String URI = "https://api.stag-pos.beecowdeal.vn/api/store/get-google-api-key-by-store-id";
//        String storeId = "67de51aa-fbb9-4df7-9afc-80784cbf2c04";
        Response res = given()
                .baseUri(URI)
                .header("X-STORE-ID", STORE_ID)
                .accept("application/json")
                .when().get();
        res.then().statusCode(200);
        JsonPath jsonPath = res.jsonPath();
        return jsonPath.get("googleApiKey").toString();
    }

    public static Map<String, Double> getLatLongByAddress(String address) {
        Map<String, Double> location = new HashMap<>();
        String googleApiKey = getGoogleApiKey();
        String baseURL = "https://maps.googleapis.com/maps/api/geocode/json";
        Response response = given()
                .queryParam("address", address)
                .queryParam("key", googleApiKey)
                .get(baseURL);
        JsonPathConfig jsonConfig = new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL);
        BigDecimal lat = JsonPath.with(response.asString()).using(jsonConfig).get("results[0].geometry.location.lat");
        BigDecimal lng = JsonPath.with(response.asString()).using(jsonConfig).get("results[0].geometry.location.lng");
//        location.put("lat", (lat != null) ? lat.doubleValue() : null);
//        location.put("lng", (lng != null) ? lng.doubleValue() : null);
        location.put("lat", lat.doubleValue());
        location.put("lng", lng.doubleValue());
        return location;
    }

    public static Response getStoreDetailByIdFromStoreApp(String address) {
        Double latitude;
        Double longitude;
        if (address.equals("")) {
            latitude = null;
            longitude = null;
        } else {
            Map<String, Double> location = getLatLongByAddress(address);
            latitude = location.get("lat");
            longitude = location.get("lng");
        }
//        Map<String, Double> location = getLatLongByAddress(address);
//        Double latitude = location.get("lat");
//        Double longitude = location.get("lng");
        baseURI = "https://api.stag-admin.beecowdeal.vn/api/store/get-store-detail-by-id-from-store-app";
        return given()
                .header("Accept", "application/json")
                .param("storeAppId", STORE_APP_ID)
                .param("latitude", latitude)
                .param("longitude", longitude)
                .get();
    }

    public static Response getAllBranchesByStoreId() {
        String baseURL = "https://api.stag-admin.beecowdeal.vn/api/store/get-all-branches-by-store-id-or-branch-id";
        JSONObject requestBody = new JSONObject();
        requestBody.put("storeId", STORE_ID);
        return given().baseUri(baseURL)
                .header("Content-Type", "application/json")
                .when()
                .body(requestBody)
                .post();
    }

    public static String getDataStoreDetailByIdFromStoreApp(String address) {
        Double latitude;
        Double longitude;
        if (address.equals("")) {
            latitude = null;
            longitude = null;
        } else {
            Map<String, Double> location = getLatLongByAddress(address);
            latitude = location.get("lat");
            longitude = location.get("lng");
        }
//        Map<String, Double> location = getLatLongByAddress(address);
//        Double latitude = location.get("lat");
//        Double longitude = location.get("lng");
        String baseURL = "https://api.stag-admin.beecowdeal.vn/api/store/get-store-detail-by-id-from-store-app";
        Response response = given().baseUri(baseURL)
                .header("Accept", "application/json")
                .param("storeAppId", STORE_APP_ID)
                .param("latitude", latitude)
                .param("longitude", longitude)
                .get();
        JsonPath jsonPath = response.jsonPath();
        String branchName = jsonPath.get("storeDetail.branchName").toString();
        return branchName;
    }

    public static String getAddressStoreDetailByIdFromStoreApp(String address) {
        Double latitude;
        Double longitude;
        if (address.equals("")) {
            latitude = null;
            longitude = null;
        } else {
            Map<String, Double> location = getLatLongByAddress(address);
            latitude = location.get("lat");
            longitude = location.get("lng");
        }
//        Map<String, Double> location = getLatLongByAddress(address);
//        Double latitude = location.get("lat");
//        Double longitude = location.get("lng");
        String baseURL = "https://api.stag-admin.beecowdeal.vn/api/store/get-store-detail-by-id-from-store-app";
        Response response = given().baseUri(baseURL)
                .header("Accept", "application/json")
                .param("storeAppId", STORE_APP_ID)
                .param("latitude", latitude)
                .param("longitude", longitude)
                .get();
        JsonPath jsonPath = response.jsonPath();
        String addressBranch = jsonPath.get("storeDetail.branchAddress").toString();
        return addressBranch;
    }

    public static void main(String[] args) {
        String s = getAddressStoreDetailByIdFromStoreApp("");
        String s1 = getDataFromBranchesByStoreId("", "address");
        System.out.println(s);
        System.out.println(s1);
    }


    public static String getDataFromBranchesByStoreId(String address, String key) {
        String baseURL = "https://api.stag-admin.beecowdeal.vn/api/store/get-all-branches-by-store-id-or-branch-id";
        JSONObject requestBody = new JSONObject();
        Double latitude;
        Double longitude;
        if (address == "" ) {
            latitude = null;
            longitude = null;
        } else {
            Map<String, Double> location = getLatLongByAddress(address);
            latitude = location.get("lat");
            longitude = location.get("lng");
        }
        requestBody.put("storeId", STORE_ID);
        requestBody.put("latitude", latitude);
        requestBody.put("longitude", longitude);
        Response response = given().baseUri(baseURL)
                .header("Content-Type", "application/json")
                .when()
                .body(requestBody)
                .post();
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonString);
            String s1 = getDataStoreDetailByIdFromStoreApp(address);
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                String storeName = (String) jsonObject.get("storeName");
                if (storeName.toLowerCase().equals(s1.toLowerCase())) {
                    if (key == "distance") {
                        String value = String.valueOf(jsonObject.get(key));
                        if (value.equals("0.0")) {
                            value = "0";
                        }
                        return value;
                    } else {
                        return (String) jsonObject.get(key);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBranchAddressProductList(String address) {
        String baseURL = "https://api.stag-admin.beecowdeal.vn/api/product/get-product-categories-activated-in-store-app-by-store-id";
        String branchID = getDataFromBranchesByStoreId(address, "storeBranchId");
        Response response = given().baseUri(baseURL)
                .header("Content-Type", "application/json")
                .param("storeId", STORE_ID)
                .param("branchId", branchID)
                .when()
                .get();
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONObject storeObject = (JSONObject) jsonObject.get("store");
            return (String) storeObject.get("addressInfo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBranchAddressProductList2(String storeName, String key) {
        String baseURL = "https://api.stag-admin.beecowdeal.vn/api/product/get-product-categories-activated-in-store-app-by-store-id";
        String branchID = getBranchInfoFromAllBranchInfo(storeName, "storeBranchId");
        Response response = given().baseUri(baseURL)
                .header("Content-Type", "application/json")
                .param("storeId", STORE_ID)
                .param("branchId", branchID)
                .when()
                .get();
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONObject storeObject = (JSONObject) jsonObject.get("store");
            return (String) storeObject.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBranchInfoFromAllBranchInfo(String storeName, String key) {
        Response response = getAllBranchesByStoreId();
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonString);
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                String s1 = (String) jsonObject.get("storeName");
                if (storeName.equalsIgnoreCase(s1)) {
                    return (String) jsonObject.get(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

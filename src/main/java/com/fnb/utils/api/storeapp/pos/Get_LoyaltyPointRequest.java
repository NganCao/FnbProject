package com.fnb.utils.api.storeapp.pos;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.fnb.utils.api.storeapp.pos.DataTest.*;
import static com.fnb.utils.api.storeapp.pos.Post_Login_Request.checkApiLoginSuccess;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Get_LoyaltyPointRequest {
    public static String getCustomerInfoToken() {
        Response response = checkApiLoginSuccess(PHONE_NUMBER, PHONE_CODE, PASSWORD, STORE_ID, BRANCH_ID);
        JsonPath jsonPath = new JsonPath(response.asString());
        String token = "Bearer " + jsonPath.getString("data.token");
        return token;
    }

    public static String getCustomerInfo(String key) {
        String getCustomerInfo = "data.customerInfo." + key;
        Response response = checkApiLoginSuccess(PHONE_NUMBER, PHONE_CODE, PASSWORD, STORE_ID, BRANCH_ID);
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.getString(getCustomerInfo);
    }

    //Loyalty point
    public static String checkApiGetCustomerLoyaltyPoint(String key) {
        try {
            String customerId = getCustomerInfo(KEY_ID);
            String token = getCustomerInfoToken();
            String customerInfo = "customerLoyaltyPoint." + key;
            baseURI = "https://api.stag-admin.beecowdeal.vn/api/customer/get-customer-loyalty-point";
            Response response = given()
                    .param("storeId", STORE_ID)
                    .param("customerId", customerId)
                    .header("Content-Type", "application/json")
                    .header("Authorization", token)
                    .when()
                    .get();
            JsonPath jsonPath = new JsonPath(response.asString());
            return jsonPath.getString(customerInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Loyalty Point Detail
    public static String checkApiGetCustomerLoyaltyPointDetailModel(String key) {
        try {
            String customerId = getCustomerInfo(KEY_ID);
            String token = getCustomerInfoToken();
            JSONObject requestBody = new JSONObject();
            requestBody.put("storeId", STORE_ID);
            requestBody.put("customerId", customerId);
            baseURI = "https://api.stag-admin.beecowdeal.vn/api/customer/get-customer-overview-loyalty-point";
            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", token)
                    .when()
                    .body(requestBody)
                    .post();
            JsonPath jsonPath = new JsonPath(response.asString());
            String loyaltyPointModel = "customerLoyaltyPointModel." + key;
            return jsonPath.getString(loyaltyPointModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String checkApiGetCustomerLoyaltyPointDetail(String key) {
        try {
            String customerId = getCustomerInfo(KEY_ID);
            String token = getCustomerInfoToken();
            JSONObject requestBody = new JSONObject();
            requestBody.put("storeId", STORE_ID);
            requestBody.put("customerId", customerId);

            baseURI = "https://api.stag-admin.beecowdeal.vn/api/customer/get-customer-overview-loyalty-point";
            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", token)
                    .when()
                    .body(requestBody)
                    .post();
            JsonPath jsonPath = new JsonPath(response.asString());
            return jsonPath.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String checkApiGetCustomerMemberships(String key) {
        try {
            String customerId = getCustomerInfo(KEY_ID);
            String token = getCustomerInfoToken();
            JSONObject requestBody = new JSONObject();
            requestBody.put("storeId", STORE_ID);
            requestBody.put("customerId", customerId);

            baseURI = "https://api.stag-admin.beecowdeal.vn/api/customer/get-customer-overview-loyalty-point";
            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", token)
                    .when()
                    .body(requestBody)
                    .post();
            String jsonString = response.asString();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONArray customerMemberships = (JSONArray) jsonObject.get("customerMemberships");

            String s1 = checkApiGetCustomerLoyaltyPointDetailModel("name");
            for (Object obj : customerMemberships) {
                JSONObject membership = (JSONObject) obj;
                String name = (String) membership.get("name");
                if (name.equalsIgnoreCase(s1)) {
                    return String.valueOf(membership.get(key));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        checkApiGetCustomerLoyaltyPointDetail("expiryDate");
    }

}

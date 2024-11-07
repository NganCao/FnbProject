package com.fnb.utils.api.posapp.pos;

import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.math.BigDecimal;

import static com.fnb.utils.api.posapp.pos.DataTestPosApp.*;
import static com.fnb.utils.api.posapp.pos.Get_Login_Request.getToken;
import static io.restassured.RestAssured.given;

public class Get_Customer_Point {
    public static Response response;
    public static String token = getToken();

    /*
    key: id, code, customerName, customerPhone, membership, discount,
     */
    public static String lazyLoadCustomer(String key, String phoneNumber) {
        String baseURI = "https://api.stag-posapp.beecowdeal.vn/api/v3.5/customer/get-lazyload-customers-posapp";

        Response response = given().baseUri(baseURI).header("Content-Type", "application/json").header("Authorization", token).queryParam("keySearch", phoneNumber).queryParam("pageNumber", PAGE_NUMBER).queryParam("itemPerPage", ITEM_PER_PAGE).when().get();
        String jsonString = response.asString();
//        response.prettyPrint();
        try {
            JSONParser parser = new JSONParser();
            JSONObject responseObject = (JSONObject) parser.parse(jsonString);
            JSONArray customers = (JSONArray) responseObject.get("customers");
            if (customers != null && !customers.isEmpty()) {
                JSONObject customer = (JSONObject) customers.get(0);
                Object customerInfo = customer.get(key);
                return String.valueOf(customerInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String calculatePointCanUse(String key, String phoneNumber) {
        String idCustomer = lazyLoadCustomer("id", phoneNumber);
        String baseURI = "https://api.stag-posapp.beecowdeal.vn/api/v3.5/cart/calculator-point-can-use-request";
        JSONObject requestBody = new JSONObject();
        requestBody.put("customerId", idCustomer);
        Response response = given().baseUri(baseURI).header("Content-Type", "application/json").header("Authorization", token).when().body(requestBody).post();
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject responseObject = (JSONObject) parser.parse(jsonString);
            Object value = responseObject.get(key);
            return String.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrentRewardPoint(String phoneNumber) {
        String id = lazyLoadCustomer("id", phoneNumber);
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/v3.5/customer/get-customer-by-id";
        Response response = given().baseUri(baseURI).header("Content-Type", "application/json").header("Authorization", token).queryParam("id", id).when().get();
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.get("customer.rewardPoint").toString();
    }

    public static void main(String[] args) {
        String id = calculatePointCanUse("earningPointExchangeValue", "0981878486");
        String user = lazyLoadCustomer("membership", "09427");
        String discountRank = lazyLoadCustomer("discount", "09427");
        System.out.println(id);
        System.out.println(user);
        System.out.println(discountRank);
        System.out.println(getCurrentRewardPoint("0942741230"));
    }
}

package com.fnb.utils.api.posapp.admin;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.fnb.utils.api.posapp.pos.DataTestPosApp.*;
import static io.restassured.RestAssured.given;

public class getTokenAdmin {
    public static Response authenticate() {
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/v3.5/login/authenticate";
        JSONObject requestBody = new JSONObject();
        requestBody.put("region", 0);
        requestBody.put("username", "autostore@mailinator.com");
        requestBody.put("password", "12345678");
        requestBody.put("storeId", "aadc881c-b6c5-40a3-3395-08dc215aa354");
        requestBody.put("accountId", "35a718b4-20ea-4861-0064-08dc215aa354");
        return given().baseUri(baseURI)
                .header("Content-Type", "application/json")
                .when()
                .body(requestBody)
                .post();
    }

    public static String getToken() {
        Response response = authenticate();
        JsonPath jsonPath = new JsonPath(response.asString());
        String token = "Bearer " + jsonPath.getString("token");
        return token;
    }

    public static String getMaxDiscount(String name) {
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/v3.5/membership/get-memberships";
        String token = getToken();
        Response response = given().baseUri(baseURI)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .queryParam("pageNumber", PAGE_NUMBER)
                .queryParam("pageSize", PAGE_SIZE)
                .when()
                .get();
        String jsonString = response.asString();
//        response.prettyPrint();
        try {
            JSONParser parser = new JSONParser();
            JSONObject responseObject = (JSONObject) parser.parse(jsonString);
            JSONArray customerMemberships = (JSONArray) responseObject.get("customerMemberships");
            if (customerMemberships != null && !customerMemberships.isEmpty()) {
                for (Object obj : customerMemberships) {
                    JSONObject customerMembership = (JSONObject) obj;
                    if (name.equals(customerMembership.get("name"))) {
                        return String.valueOf(customerMembership.get("maximumDiscount"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getMaxDiscount("Silver member"));
    }
}


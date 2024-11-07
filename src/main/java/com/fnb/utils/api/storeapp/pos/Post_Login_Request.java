package com.fnb.utils.api.storeapp.pos;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Post_Login_Request {
    public static Response checkApiLoginSuccess(String phoneNumber, String countryCode, String passWord, String storeID, String branchID) {
        baseURI = "https://api.stag-admin.beecowdeal.vn/api/login/password";
        JSONObject requestBody = new JSONObject();
        requestBody.put("phoneNumber", phoneNumber);
        requestBody.put("phoneCode", countryCode);
        requestBody.put("password", passWord);
        requestBody.put("storeId", storeID);
        requestBody.put("branchId", branchID);
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(requestBody)
                .post();
    }

    public static Response checkApiLoginSuccessAdmin(String userName, String passWord, String storeId, String accountId){
        baseURI = "https://api.stag-admin.beecowdeal.vn/api/v3.3/login/authenticate";
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", passWord);
        requestBody.put("storeId", storeId);
        requestBody.put("accountId", accountId);
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(requestBody)
                .post();
    }
}

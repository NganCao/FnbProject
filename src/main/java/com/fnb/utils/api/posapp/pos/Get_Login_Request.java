package com.fnb.utils.api.posapp.pos;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static com.fnb.utils.api.posapp.pos.DataTestPosApp.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Get_Login_Request {

    public static Response loginInPosApp(String userName, String passWord, String branchID, String loginDateTime) {
        baseURI = "https://api.stag-posapp.beecowdeal.vn/api/v3.5/login/authenticate";
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("passWord", passWord);
        requestBody.put("branchID", branchID);
        requestBody.put("loginDateTime", loginDateTime);
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(requestBody)
                .post();
    }

    public static Response checkAPIGetToken(String userName, String passWord, String branchId, String loginDateTime,String isRememberAccount, String storeId, String remainExpireMinutes){
        baseURI = "https://api.stag-admin.beecowdeal.vn/api/v3.5/login/authenticate";
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("passWord", passWord);
        requestBody.put("branchId", branchId);
        requestBody.put("loginDateTime", loginDateTime);
        requestBody.put("isRememberAccount", isRememberAccount);
        requestBody.put("storeId", storeId);
        requestBody.put("remainExpireMinutes", remainExpireMinutes);
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(requestBody)
                .post();
    }

    public static String getToken() {
        Response response = checkAPIGetToken(USER_NAME_POS_APP, PASSWORD_POSAPP, BRANCH_ID,LOGIN_DATE_TIME, IS_REMEMBER_ACCOUNT, STORE_ID, REMAIN_EXPIRED_MINUTE );
//        response.prettyPrint();
        JsonPath jsonPath = new JsonPath(response.asString());
        String token = "Bearer " + jsonPath.getString("token");
//        System.out.println(token);
        return token;
    }


    public static void main(String[] args) {
        String s = getToken();
        System.out.println(s);
    }
}

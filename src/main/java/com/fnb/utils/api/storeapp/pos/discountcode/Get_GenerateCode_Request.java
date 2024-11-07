package com.fnb.utils.api.storeapp.pos.discountcode;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static com.fnb.utils.api.storeapp.pos.DataTest.*;
import static com.fnb.utils.api.storeapp.pos.Post_Login_Request.checkApiLoginSuccessAdmin;
import static io.restassured.RestAssured.given;

public class Get_GenerateCode_Request {
    public static String getCustomerAdminInfoToken() {
        Response response = checkApiLoginSuccessAdmin(USER_NAME, PASSWORD_ADMIN, STORE_ID_ADMIN, ACCOUNT_ID);
        JsonPath jsonPath = new JsonPath(response.asString());
        String token = "Bearer " + jsonPath.getString("token");
        return token;
    }

    public static String generateCode() {
        String token = getCustomerAdminInfoToken();
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/v3.4/discountCode/generate-discount-code-by-store-id";
        Response response = given().baseUri(baseURI)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get();
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.getString("discountCode");
    }

    public static void main(String[] args) {
        System.out.println(getCustomerAdminInfoToken());
    }
}

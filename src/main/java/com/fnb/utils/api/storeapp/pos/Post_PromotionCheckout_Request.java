package com.fnb.utils.api.storeapp.pos;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.fnb.utils.api.storeapp.pos.DataTest.*;
import static com.fnb.utils.api.storeapp.pos.Get_LoyaltyPointRequest.getCustomerInfoToken;
import static io.restassured.RestAssured.given;

public class Post_PromotionCheckout_Request {
    public static Response response;
    public static Response ListPromotionApply(){
        String baseURL = "https://api.stag-admin.beecowdeal.vn/api/promotion/get-list-promotion-can-apply";
        String token = getCustomerInfoToken();
        JSONObject requestBody = new JSONObject();
        JSONArray discountIdsArray = new JSONArray();
        discountIdsArray.add(DISCOUNT_ARRAY_ID);
        requestBody.put("discountIds", discountIdsArray);
        requestBody.put("storeId", STORE_ID);
        response = given().baseUri(baseURL)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .body(requestBody)
                .post();
        return response;
    }

    public static String dataPromotionApply(String key){
        response = ListPromotionApply();
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            boolean isCombined = (boolean) jsonObject.get("isDiscountCombined");
            return Boolean.toString(isCombined);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String valueListPromotionCanApply(String key){
        response = ListPromotionApply();
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);

            JSONObject promotion = (JSONObject) ((JSONArray) jsonObject.get("listPromotionCanApply")).get(0); // Assume only one promotion for simplicity
            Object value = promotion.get(key);

            return (value != null) ? String.valueOf(value) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

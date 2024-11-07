package com.fnb.utils.api.storeapp.pos;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.fnb.utils.api.storeapp.pos.DataTest.*;
import static com.fnb.utils.api.storeapp.pos.Get_LoyaltyPointRequest.getCustomerInfoToken;
import static io.restassured.RestAssured.given;

public class Get_DiscountCodeCheckout_Request {
    public static Response response;
    public static Response getDiscountCodeCheckout(){
        String baseURL = "https://api.stag-admin.beecowdeal.vn/api/discountcode/get-discount-code-by-account-id";
        response = given().baseUri(baseURL)
                .param("storeId", STORE_ID)
                .param("branchId", BRANCH_ID)
                .param("platformId", PLATFORM_ID)
                .when()
                .get();
        return response;
    }
    public static String getValuePromotionCheckout(String discountName, String key){
        response = getDiscountCodeCheckout();
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONArray discountCodes = (JSONArray) jsonObject.get("discountCode");
            String s1 = discountName;
            for (Object obj : discountCodes) {
                JSONObject discountCode = (JSONObject) obj;
                String name = (String) discountCode.get("name");
                if (name.toLowerCase().equals(s1.toLowerCase())) {
                    String value = String.valueOf(discountCode.get(key));
                    return value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {

    }
}

package com.fnb.utils.api.storeapp.pos.discountcode;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.security.PublicKey;

import static com.fnb.utils.api.storeapp.pos.discountcode.Get_GenerateCode_Request.getCustomerAdminInfoToken;
import static io.restassured.RestAssured.given;

public class Get_IdDiscountCode_Request {
    public static Response response;
    public static Response showListDiscountCode(){
        String token = getCustomerAdminInfoToken();
        response = given()
                .baseUri("https://api.stag-admin.beecowdeal.vn/api/v3.4/discountCode/get-all-discount-code")
                .header("Authorization", token)
                .when().get();
        return response;
    }
    public static String getIdDiscountCode(String nameDiscount){
        response = showListDiscountCode();
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONArray discountCodes = (JSONArray) jsonObject.get("discountCodes");
            String s1 = nameDiscount;
            for(Object obj: discountCodes){
                JSONObject discountCode = (JSONObject) obj;
                String name = (String) discountCode.get("name");
                if (name.toLowerCase().equals(s1.toLowerCase())) {
                    return String.valueOf(discountCode.get("id"));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String id = getIdDiscountCode("Discount code for total bill by money 226");
        System.out.println(id);
    }
}

package com.fnb.utils.api.storeapp.pos.discountcampaign;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.fnb.utils.api.storeapp.pos.discountcode.Get_GenerateCode_Request.getCustomerAdminInfoToken;
import static io.restassured.RestAssured.given;

public class Get_IdVoucher_Request {
    public static Response response;
    public static Response showListVoucher(){
        String token = getCustomerAdminInfoToken();
        response = given()
                .baseUri("https://api.stag-admin.beecowdeal.vn/api/v3.4/promotion/get-promotions")
                .header("Authorization", token)
                .when().get();
        return response;
    }
    public static String getIdVoucher(String nameVoucher){
        response = showListVoucher();
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONArray vouchers = (JSONArray) jsonObject.get("promotions");
            String s1 = nameVoucher;
            for(Object obj: vouchers){
                JSONObject voucher = (JSONObject) obj;
                String name = (String) voucher.get("name");
                if (name.toLowerCase().equals(s1.toLowerCase())) {
                    return String.valueOf(voucher.get("id"));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String id = getIdVoucher("Voucher for specific category by money 721");
        System.out.println(id);
    }
}

package com.fnb.utils.api.storeapp.pos;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.fnb.utils.api.storeapp.pos.Get_DiscountCodeRequest.currentDates;
import static com.fnb.utils.api.storeapp.pos.DataTest.*;
import static io.restassured.RestAssured.given;

public class Get_VoucherDetailRequest {
    public static Response response;

    public static String getVoucherDetail(String voucherName, String key) {
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/promotion/get-promotions-in-branch";
        String currentDate = currentDates();
        Response response = given()
                .baseUri(baseURI)
                .param("storeId", STORE_ID)
                .param("branchId", BRANCH_ID)
                .param("currentDate", currentDate)
                .header("Content-Type", "application/json")
                .header("platform-id", PLATFORM_ID)
                .when()
                .get();
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONArray discountCodes = (JSONArray) jsonObject.get("promotions");
            String s1 = voucherName;
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

    public static Response VoucherDetail(String voucherName) {
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/promotion/get-promotion-detail-by-id";
        String idVoucherCode = getVoucherDetail(voucherName, "id");
        response = given()
                .baseUri(baseURI)
                .param("storeId", STORE_ID)
                .param("promotionId", idVoucherCode)
                .header("Content-Type", "application/json")
                .header("platform-id", PLATFORM_ID)
                .when()
                .get();
        return response;
    }

    public static String getStringValueVoucherDetail(String voucherName, String key) {
        response = VoucherDetail(voucherName);
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONObject discountCode = (JSONObject) jsonObject.get("promotion");
            return String.valueOf(discountCode.get(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getArraysValueVoucherDetail(String voucherName, String keys, String key) {
        Response response = VoucherDetail(voucherName);
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONObject discountCode = (JSONObject) jsonObject.get("promotion");
            JSONArray productCategories = (JSONArray) discountCode.get(keys);
            for (Object obj : productCategories) {
                JSONObject productCategory = (JSONObject) obj;
                return String.valueOf(productCategory.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDataVoucherDetailArray2(String voucherName, String keys, String key, String value) {
        Response response = VoucherDetail(voucherName);
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONObject discountCode = (JSONObject) jsonObject.get("promotion");
            JSONArray productCategories = (JSONArray) discountCode.get(keys);
            for (Object obj : productCategories) {
                JSONObject productCategory = (JSONObject) obj;
                if (value.equals(productCategory.get(key))) {
                    return String.valueOf(productCategory.get(key));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

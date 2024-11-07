package com.fnb.utils.api.storeapp.pos;

import com.fnb.utils.helpers.Log;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.fnb.utils.api.storeapp.pos.Get_DiscountCodeRequest.*;
import static com.fnb.utils.api.storeapp.pos.DataTest.*;
import static io.restassured.RestAssured.given;

public class Get_DiscountDetailRequest {
    public static Response response;

    public static String getDiscountCodeDetail(String discountName, String key) {
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/discountcode/get-discount-codes-from-store-app-by-branch-id";
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
            JSONArray discountCodes = (JSONArray) jsonObject.get("discountCodes");
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
            Log.info("Error when getting data from Api..." + Get_DiscountDetailRequest.class.getName());
            e.printStackTrace();
        }
        return null;
    }

    public static Response DiscountCodeDetail(String discountName) {
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/discountcode/get-discount-code-detail-from-store-app-by-id";
        String idDiscountCode = getDiscountCodeDetail(discountName, "id");
        response = given()
                .baseUri(baseURI)
                .param("storeId", STORE_ID)
                .param("id", idDiscountCode)
                .header("Content-Type", "application/json")
                .header("platform-id", PLATFORM_ID)
                .when()
                .get();
        return response;
    }

    public static String getStringValueDiscountCodeDetail(String discountName, String key) {
        response = DiscountCodeDetail(discountName);
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONObject discountCode = (JSONObject) jsonObject.get("discountCode");
            return String.valueOf(discountCode.get(key));
        } catch (Exception e) {
            Log.info("Error when getting data from Api..." + Get_DiscountDetailRequest.class.getName());
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String s = getStringValueDiscountCodeDetail("Discount code for specific category by % 122", "isAllBranches");
        System.out.println(s);
    }

    public static String getArraysValueDiscountCodeDetail(String discountName, String keys, String key) {
        Response response = DiscountCodeDetail(discountName);
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONObject discountCode = (JSONObject) jsonObject.get("discountCode");
            JSONArray productCategories = (JSONArray) discountCode.get(keys);
            for (Object obj : productCategories) {
                JSONObject productCategory = (JSONObject) obj;
                return String.valueOf(productCategory.get(key));
            }
        } catch (Exception e) {
            Log.info("Error when getting data from Api..." + Get_DiscountDetailRequest.class.getName());
            e.printStackTrace();
        }
        return null;
    }

    public static String getDataDiscountCodeDetailArray2(String discountName, String keys, String key, String value) {
        Response response = DiscountCodeDetail(discountName);
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONObject discountCode = (JSONObject) jsonObject.get("discountCode");
            JSONArray productCategories = (JSONArray) discountCode.get(keys);
            for (Object obj : productCategories) {
                JSONObject productCategory = (JSONObject) obj;
                if (value.equals(productCategory.get(key))) {
                    return String.valueOf(productCategory.get(key));
                }
            }
        } catch (Exception e) {
            Log.info("Error when getting data from Api..." + Get_DiscountDetailRequest.class.getName());
            e.printStackTrace();
        }
        return null;
    }


}

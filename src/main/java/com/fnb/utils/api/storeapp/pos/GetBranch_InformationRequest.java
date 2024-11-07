package com.fnb.utils.api.storeapp.pos;

import com.fnb.utils.helpers.Log;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Map;

import static com.fnb.utils.api.storeapp.pos.DataTest.*;
import static com.fnb.utils.api.storeapp.pos.Get_Branch_InformationRequest.getLatLongByAddress;
import static io.restassured.RestAssured.given;

public class GetBranch_InformationRequest {
    public static String getBranchInfo(String storeName, String key, String addressUser) {
        String baseURL = "https://api.stag-admin.beecowdeal.vn/api/store/get-all-branches-by-store-id-or-branch-id";
        JSONObject requestBody = new JSONObject();
        Map<String, Double> location = getLatLongByAddress(addressUser);
        Double latitude = location.get("lat");
        Double longitude = location.get("lng");
        requestBody.put("storeId", STORE_ID);
        requestBody.put("latitude", latitude);
        requestBody.put("longitude", longitude);
        Response response = given().baseUri(baseURL)
                .header("Content-Type", "application/json")
                .when()
                .body(requestBody)
                .post();
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonString);
            for (Object obj : jsonArray) {
                JSONObject address = (JSONObject) obj;
                String Name = (String) address.get("storeName");
                if (Name.equals(storeName)) {
                    if (key == "distance") {
                        String value = String.valueOf(address.get(key));
                        if (value.equals("0.0")) {
                            value = "0";
                        }
                        System.out.println(value);
                        return value;
                    } else {
                        String value = (String) address.get(key);
                        System.out.println(value);
                        return value;
                    }
                }

            }
            Log.info("Không tìm thấy địa chỉ cho " + storeName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDetailBranchIdInfo(String storeName, String addressUser) {
        String baseURL = "https://api.stag-admin.beecowdeal.vn/api/product/get-product-categories-activated-in-store-app-by-store-id";
        String branchId = getBranchInfo(storeName, "storeBranchId", addressUser);
        Response response = given().baseUri(baseURL)
                .header("Content-Type", "application/json")
                .param("storeId", STORE_ID)
                .param("branchId", branchId)
                .when()
                .get();
        String jsonString = response.asString();
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
            JsonObject storeDetail = jsonObject.getAsJsonObject("store");
            String branchAddress = storeDetail.get("addressInfo").getAsString();
            System.out.println(branchAddress);
            return branchAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

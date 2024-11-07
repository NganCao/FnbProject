package com.fnb.utils.api.storeapp.pos;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.fnb.utils.api.storeapp.pos.Get_LoyaltyPointRequest.getCustomerInfoToken;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Get_AddressUserRequest {
    public static String checkApiGetAddressUser(String nameAddress, String key) {
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/account/get-account-addresses-by-account-id";
        String token = getCustomerInfoToken();
        Response response = given().baseUri(baseURI)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get();
        String jsonString = response.asString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject responseObject = (JSONObject) parser.parse(jsonString);
            JSONArray customerAddresses = (JSONArray) responseObject.get("customerAddresses");

            for (Object obj : customerAddresses) {
                JSONObject address = (JSONObject) obj;
                String storeName = (String) address.get("name");
                if (storeName.equals(nameAddress)) {
                    return (String) address.get(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

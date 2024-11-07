package com.fnb.utils.api.storeapp.pos;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.fnb.utils.api.storeapp.pos.DataTest.*;
import static io.restassured.RestAssured.given;

public class Get_DiscountCodeRequest {
    private static Response response;
    private static JsonPath jsonPath;
    private static String currentDate;

    public static String currentDates() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return now.format(formatter);
    }

    public static Response getPromotionByStore() {
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/v3.4/discountcode/get-promotion-discount-by-store";
        currentDate = currentDates();
        response = given()
                .baseUri(baseURI)
                .param("storeId", STORE_ID)
                .param("branchId", BRANCH_ID)
                .param("currentDate", currentDate)
                .header("Content-Type", "application/json")
                .header("platform-id", PLATFORM_ID)
                .when()
                .get();
        return response;
    }

    public static int getValuePromotion() {
        response = getPromotionByStore();
        jsonPath = new JsonPath(response.asString());
        return (int) jsonPath.getDouble("discount");
    }

    public static boolean getCheckPromotion(String key) {
        response = getPromotionByStore();
        jsonPath = new JsonPath(response.asString());
        return jsonPath.getBoolean(key);
    }
}

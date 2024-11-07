package com.fnb.utils.api.storeapp.pos;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class Get_Create_TokenRequest {
    public Response CheckAccountAlreadyExists(String countryCode, String phone){
        return given()
                .pathParam("CountryCode", countryCode)
                .pathParam("PhoneNumber", phone)
                .get();
    }
}

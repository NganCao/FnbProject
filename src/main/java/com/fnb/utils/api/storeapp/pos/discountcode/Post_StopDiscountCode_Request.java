package com.fnb.utils.api.storeapp.pos.discountcode;

import com.fnb.utils.helpers.Log;
import io.restassured.response.Response;

import static com.fnb.utils.api.storeapp.pos.discountcode.Get_GenerateCode_Request.getCustomerAdminInfoToken;
import static com.fnb.utils.api.storeapp.pos.discountcode.Get_IdDiscountCode_Request.getIdDiscountCode;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_CreateDiscountCodeCategory_Request.*;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_CreateDiscountCodeProduct_Request.createDiscountCodeProductByApi;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_CreateDiscountCodeProduct_Request.*;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_CreateDiscountCodeTotal_Request.createDiscountCodeTotalBillByApi;
import static com.fnb.utils.api.storeapp.pos.discountcode.Post_CreateDiscountCodeTotal_Request.nameDiscount;
import static io.restassured.RestAssured.given;

public class Post_StopDiscountCode_Request {
    public static Response stopDiscountCode(String name){
        String id = getIdDiscountCode(name);
        String token = getCustomerAdminInfoToken();
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/v3.4/discountCode/stop-discount-code-by-id/";
        Response response = given().baseUri(baseURI)
                .basePath(id)
                .header("authorization", token)
                .when()
                .post();
        System.out.println(response.getStatusCode());
        System.out.println("Stop discount code: "+ name + " successfully !");
        Log.info("Stop discount code: " + name + " successfully !");
        return response;
    }


    public static void main(String[] args) {
        createDiscountCodeTotalBillByApi(0, false);
        String name = nameDiscount();
        System.out.println(name);
        System.out.println("1");
        createDiscountCodeTotalBillByApi(0, true);
        String name1 = nameDiscount();
        System.out.println(name1);
        System.out.println("2");
        createDiscountCodeProductByApi(1, false);
        String name2 = nameDiscountProduct();
        System.out.println(name2);
        System.out.println("3");
        createDiscountCodeProductByApi(1, true);
        String name3 = nameDiscountProduct();
        System.out.println(name3);
        System.out.println("4");
        createDiscountCodeCategoryByApi(2, false);
        String name4 = nameDiscountCategory();
        System.out.println(name4);
        System.out.println("5");
        createDiscountCodeCategoryByApi(2, true);
        String name5 = nameDiscountCategory();
        System.out.println(name5);
        System.out.println("6");
        stopDiscountCode(name);
        stopDiscountCode(name1);
        stopDiscountCode(name2);
        stopDiscountCode(name3);
        stopDiscountCode(name4);
        stopDiscountCode(name5);
    }
}

package com.fnb.utils.api.storeapp.pos.discountcampaign;

import io.restassured.response.Response;

import static com.fnb.utils.api.storeapp.pos.discountcampaign.Get_IdVoucher_Request.getIdVoucher;
import static com.fnb.utils.api.storeapp.pos.discountcampaign.Post_CreateVoucherCategory_Request.createVoucherCategoryByApi;
import static com.fnb.utils.api.storeapp.pos.discountcampaign.Post_CreateVoucherCategory_Request.nameVoucherCategory;
import static com.fnb.utils.api.storeapp.pos.discountcode.Get_GenerateCode_Request.getCustomerAdminInfoToken;

import static io.restassured.RestAssured.given;

public class Post_StopVoucher_Request {
    public static Response stopVoucher(String name){
        String id = getIdVoucher(name);
        System.out.println(id);
        String token = getCustomerAdminInfoToken();
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/v3.4/promotion/stop-promotion-by-id/";
        Response response = given().baseUri(baseURI)
                .basePath(id)
                .header("authorization", token)
                .when()
                .post();
        response.prettyPrint();
        System.out.println(response.getStatusCode());
        return response;
    }

    public static void main(String[] args) {
//        System.out.println("1");
//        createDiscountCodeProductByApi(1, true);
//        createVoucherCategoryByApi(2, true);
//        System.out.println("2");
//        String name = nameVoucherCategory();
//        String code = discountCodeCategory();
//        System.out.println(name);
//        System.out.println(code);
        stopVoucher("Voucher for total bill by money 8");
    }
}

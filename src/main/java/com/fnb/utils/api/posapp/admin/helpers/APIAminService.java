package com.fnb.utils.api.posapp.admin.helpers;

import com.fnb.utils.api.posapp.JsonAPIReader;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static com.fnb.utils.api.posapp.admin.helpers.JsonAPIAdminReader.*;
import static io.restassured.RestAssured.given;

public class APIAminService {
    public static String token;
    public static String storeId;
    public static String accountId;
    public static int statusCode;
    public static JsonAPIReader.ConfigAPIObject configAPIObject;
    public static JsonAPIReader jsonAPIReader;
    public static JsonAPIAdminReader jsonAPIAdminReader;
    private static String url;
    private static String apiURL;
    private static String userName;
    private static String password;
    private static String storeIndex;
    public static String theme;
    private static String path = "src/main/java/com/fnb/utils/api/posapp/";
    private static String typeAPI = "admin";
    private static Helper helper;
    private static JsonReader.ConfigObject configObject;
    private WebDriver driver;
    private String platform;
    private int plusMinuteDefault = 30;
    private int plusMinuteEndTime = plusMinuteDefault + 30;

    public APIAminService(WebDriver driver) {
        this.driver = driver;
        helper = new Helper(driver);
        getApiURL();
    }

    public static String getFilePath(String fileName) {
        return typeAPI + "/jsonSchema/" + fileName;
    }

    public static void checkBeforeAuthenticate() {
        String fileName = "storeInformation.json";
        String filePath = typeAPI + "/jsonSchema/" + fileName;
        String apiPath = apiURL + "login/check-before-authenticate";
        String requestBody = "{\n" + "\"username\" : \"" + userName + "\" , \n" + "\"password\" : \"" + password + "\" , \n" + "}";
        Response response = given().baseUri(apiURL).contentType(ContentType.JSON).accept("application/json").body(requestBody).post(apiPath);
        helper.writeFile(path + "/" + filePath, response.asPrettyString());
        configAPIObject.setStoreId(response.jsonPath().get("stores[" + storeIndex + "].storeId"));
        configAPIObject.setAccountId(response.jsonPath().get("stores[" + storeIndex + "].accountId"));
        storeId = response.jsonPath().get("stores[" + storeIndex + "].storeId");
        accountId = response.jsonPath().get("stores[" + storeIndex + "].accountId");
    }

    public static String getBranchIdByName(String keySearch) {
        String apiPath = "branch/get-branch-managements";
        RequestSpecification request = given();
        request.baseUri(apiURL).basePath(apiPath).auth().oauth2(token).accept("application/json");
        request.queryParam("keySearch", keySearch);
        Response response = request.when().get();
        return response.jsonPath().get("branchManagements[0].id");
    }

    public static String getCategoryId(String productId) {
        String apiPath = "product/get-product-detail-data-by-id";
        RequestSpecification request = given();
        request.baseUri(apiURL).basePath(apiPath).auth().oauth2(token).accept("application/json");
        request.queryParam("productId", productId);
        Response response = request.when().get();
        return response.jsonPath().get("product.productCategoryId");
    }

    public static String getProductById(String productId) {
        String apiPath = "product/get-product-detail-data-by-id";
        RequestSpecification request = given();
        request.baseUri(apiURL).basePath(apiPath).auth().oauth2(token).accept("application/json");
        request.queryParam("productId", productId);
        Response response = request.when().get();
        String fileName = "getProductById.json";
        String filePath = typeAPI + "/jsonSchema/" + fileName;
        helper.writeFile(path + filePath, response.asPrettyString());
        return filePath;
    }

    public static List<Topping> getToppingListByProductId(String productName) {
        List<Topping> list = jsonAPIAdminReader.getToppingByProduct(productName);
        return list;
    }

    private static String getProductByFilterSearch(String keySearch) {
        String apiPath = "product/get-products-by-filter";
        RequestSpecification request = given();
        request.baseUri(apiURL).basePath(apiPath).auth().oauth2(token).accept("application/json");
        request.queryParam("keySearch", keySearch);
        Response response = request.when().get();
        String fileName = "getProductBySearching.json";
        String filePath = typeAPI + "/jsonSchema/" + fileName;
        helper.writeFile(path + filePath, response.asPrettyString());
        return filePath;
    }

    public static Product getProductBySearchName(String fullName) {
        String filePath = getProductByFilterSearch(fullName);
        System.out.println(filePath);
        return jsonAPIAdminReader.getProductBySearching(filePath, fullName);
    }

    public static String getCategoryIdByProductId(String productName) {
        Product product = getProductBySearchName(productName);
        return getCategoryId(product.getId());
    }

    public static String getPriceProductByName(String productName, String size) {
        String price = "";
        Product product = getProductBySearchName(productName.trim());
        List<ProductPrices> productPricesList = product.getProductPrices();
        if (productPricesList.size() > 1) {
            for (ProductPrices productPrice : productPricesList) {
                if (productPrice.getPriceName().equals(size)) {
                    price = helper.formatCurrencyToThousand(Math.round(productPrice.getPrice()));
                    break;
                }
            }
        } else price = helper.formatCurrencyToThousand(Math.round(productPricesList.get(0).getPrice()));
        return price;
    }

    public static String getFirstProductPriceByName(String productName) {
        String price = "";
        Product product = getProductBySearchName(productName.trim());
        List<ProductPrices> productPricesList = product.getProductPrices();
        price = helper.formatCurrencyToThousand(Math.round(productPricesList.get(0).getPrice()));
        return price;
    }

    //category
    public String getProductListFromCategoryId(String categoryId) {
        Product product;
        List<Product> productList = new ArrayList<>();
        String apiPath = "productCategory/get-product-category-by-id/" + categoryId;
        RequestSpecification request = given();
        request.baseUri(apiURL).basePath(apiPath).auth().oauth2(token).accept("application/json");
        Response response = request.when().get();
        String fileName = "getProductsListByCategory.json";
        String filePath = typeAPI + "/jsonSchema/" + fileName;
        helper.writeFile(path + filePath, response.asPrettyString());
        return filePath;
    }

    private void getApiURL() {
        configAPIObject = jsonAPIReader.apiReader(typeAPI);
        apiURL = configAPIObject.getUrl();
        userName = configAPIObject.getUserName();
        password = configAPIObject.getPassword();
        storeIndex = configAPIObject.getStoreIndex();
        getToken();
    }

    public String getToken() {
        checkBeforeAuthenticate();
        String apiPath = apiURL + "login/authenticate";
        String requestBody = "{\n" + "\"username\" : \"" + userName + "\" , \n" + "\"password\" : \"" + password + "\" , \n" + "\"storeId\" : \"" + storeId + "\" , \n" + "\"accountId\" : \"" + accountId + "\" , \n" + "}";
        Response response = given().baseUri(apiURL).contentType(ContentType.JSON).accept("application/json").body(requestBody).post(apiPath);
        token = response.jsonPath().get("token");
        return token;
    }
}

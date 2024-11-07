package com.fnb.utils.api.storeweb.admin.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fnb.utils.api.storeweb.JsonAPIReader;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader.*;
import static io.restassured.RestAssured.get;
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
    private static String path = "src/main/java/com/fnb/utils/api/storeweb/";
    private static String typeAPI = "admin";
    private static Helper helper;
    private static JsonReader.ConfigObject configObject;
    private WebDriver driver;
    private String platform;
    private int plusMinuteDefault = 30;
    private int plusMinuteEndTime = plusMinuteDefault + 30;

    public APIAminService(WebDriver driver, String platform, String theme, String url) {
        this.driver = driver;
        this.platform = platform;
        this.theme = theme;
        this.url = url;
        helper = new Helper(driver);
        getApiURL();
    }

    public static String getFilePath(String fileName) {
        return typeAPI + "/jsonSchema/" + theme + "/" + fileName;
    }

    public static void checkBeforeAuthenticate() {
        String fileName = "storeInformation.json";
        String filePath = typeAPI + "/jsonSchema/" + theme + "/" + fileName;
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

    private static String getProductByFilterSearch(String keySearch) {
        String apiPath = "product/get-products-by-filter";
        RequestSpecification request = given();
        request.baseUri(apiURL).basePath(apiPath).auth().oauth2(token).accept("application/json");
        request.queryParam("keySearch", keySearch);
        Response response = request.when().get();
        String fileName = "getProductBySearching.json";
        String filePath = typeAPI + "/jsonSchema/" + theme + "/" + fileName;
        helper.writeFile(path + filePath, response.asPrettyString());
        return filePath;
    }

    public static Product getProductBySearchName(String fullName) {
        String filePath = getProductByFilterSearch(fullName);
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
        String filePath = typeAPI + "/jsonSchema/" + theme + "/" + fileName;
        helper.writeFile(path + filePath, response.asPrettyString());
        return filePath;
    }

    //flash sale api

    /**
     * create flash sale for more products + all variations, no selected included topping, mimimum purchase order option
     *
     * @param productNameList
     * @param flashSaleName
     * @param startDate       UTC time
     * @param endDate         localtime
     * @return
     * @throws IOException
     */
    private static FlashSale createFlashSaleAPI(List<String> productNameList, String flashSaleName, LocalDateTime startDate, LocalDateTime endDate) throws IOException {
        Random random = new Random();
        FlashSale flashSale = new FlashSale();
        List<FlashSaleProduct> flashSaleProductList = new ArrayList<>();
        String apiPath = apiURL + "flashSale/create-flash-sale";
        String fileJsonPath = path + typeAPI + "/jsonSchema/" + theme + "/flashsale/flashSaleInformation.json";
        File jsonFile = new File(fileJsonPath);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonFile);
        List<Product> productList = new ArrayList<>();
        for (String productName : productNameList) {
            Product product = getProductBySearchName(productName);
            productList.add(product);
        }
        String startD = helper.getUTCTime(startDate);
        String startT = helper.getUTCTimeFormatISO(startDate);
        String endD = helper.getUTCTime(endDate);
        String endT = helper.getUTCTimeFormatISO(endDate);
        //change
        ((ObjectNode) jsonNode.get("createFlashSale").get("body").get("flashSale")).put("name", flashSaleName).put("startDate", startD).put("startTime", startT).put("endDate", endD).put("endTime", endT).put("isIncludedTopping", false);
        // Access "flashSaleProducts"
        ArrayNode flashSaleProductsArray = (ArrayNode) jsonNode.get("createFlashSale").get("body").get("flashSale").get("flashSaleProducts");
        flashSaleProductsArray.removeAll();
        //Access "products"
        ArrayNode productsArray = (ArrayNode) jsonNode.get("createFlashSale").get("body").get("flashSale").get("products");
        productsArray.removeAll();
        for (Product product : productList) {
            for (ProductPrices price : product.getProductPrices()) {
                FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
                //flashSaleProducts[]
                ObjectNode flashSaleProductsNode = objectMapper.createObjectNode();
                flashSaleProductsNode.put("id", price.getId());
                flashSaleProductsNode.put("name", product.getName());
                int priceInt = Math.round(price.getPrice());
                flashSaleProductsNode.put("price", priceInt);
                // Add priceFormat in productNode
                ObjectNode priceFormatNode = objectMapper.createObjectNode();
                ObjectNode propsNode = objectMapper.createObjectNode();
                propsNode.put("value", priceInt);
                //Hard - default
                propsNode.put("displayType", "text");
                propsNode.put("thousandSeparator", true);
                propsNode.put("decimalScale", 2);
                propsNode.put("decimalSeparator", ".");
                propsNode.put("thousandSpacing", "3");
                propsNode.put("fixedDecimalScale", false);
                propsNode.put("prefix", "");
                propsNode.put("suffix", "");
                propsNode.put("allowNegative", true);
                propsNode.put("isNumericString", false);
                propsNode.put("type", "text");
                priceFormatNode.set("props", propsNode);
                flashSaleProductsNode.set("priceFormat", priceFormatNode);
                int randomQuantity = random.nextInt(20) + 1;
                Boolean isSinglePrice;
                if (product.getProductPrices().size() > 1) {
                    isSinglePrice = false;
                    flashSaleProductsNode.put("productPriceName", price.getPriceName());
                } else {
                    isSinglePrice = true;
                }
                flashSaleProductsNode.put("isSinglePrice", isSinglePrice);
                flashSaleProductsNode.put("thumbnail", product.getThumbnail());
                int priceRandom = random.nextInt(Math.round(price.getPrice())) + 1;
                flashSaleProductsNode.put("flashSalePrice", priceRandom);
                flashSaleProductsNode.put("flashSaleQuantity", randomQuantity);
                int maximumLimit = random.nextInt(randomQuantity) + 1;
                flashSaleProductsNode.put("maximumLimit", maximumLimit);
                flashSaleProductsArray.add(flashSaleProductsNode);
                // products[]
                ObjectNode productsNode = objectMapper.createObjectNode();
                productsNode.put("flashSalePrice", priceRandom);
                productsNode.put("flashSaleQuantity", randomQuantity);
                productsNode.put("maximumLimit", maximumLimit);
                productsArray.add(productsNode);
                flashSaleProduct = setFlashSaleProduct(price.getId(), product.getName(), priceInt, priceRandom, randomQuantity, maximumLimit, isSinglePrice, price.getPriceName(), product.getThumbnail(), "");
                flashSaleProductList.add(flashSaleProduct);
            }
        }
        //add flashSale object
        flashSale = setFlashSale(flashSaleName, startD, startT, endD, endT, "", flashSaleProductList);
        // json Formatter and writer
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        objectWriter.writeValue(jsonFile, jsonNode);
        // Call api
        JsonNode flashSaleNode = jsonNode.path("createFlashSale").path("body");
        String jsonPartAsString = objectMapper.writeValueAsString(flashSaleNode);
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(jsonPartAsString).post(apiPath);
        System.out.println("Response Code: " + response.getStatusCode());
        if (response.getStatusCode() != 200) {
            response.jsonPath().prettyPrint();
        }
        statusCode = response.getStatusCode();
        return flashSale;
    }

    /**
     * Create flash sale with special branch(es)
     *
     * @param productNameList
     * @param flashSaleName
     * @param startDate
     * @param endDate
     * @param branchesNameList
     * @return
     * @throws IOException
     */
    private static FlashSale createFlashSaleSpecialBranchAPI(List<String> productNameList, String flashSaleName, LocalDateTime startDate, LocalDateTime endDate, List<String> branchesNameList) throws IOException {
        Random random = new Random();
        FlashSale flashSale = new FlashSale();
        List<FlashSaleProduct> flashSaleProductList = new ArrayList<>();
        String apiPath = apiURL + "flashSale/create-flash-sale";
        String fileJsonPath = path + typeAPI + "/jsonSchema/" + theme + "/flashsale/flashSaleInformation.json";
        File jsonFile = new File(fileJsonPath);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonFile);
        List<Product> productList = new ArrayList<>();
        for (String productName : productNameList) {
            Product product = getProductBySearchName(productName);
            productList.add(product);
        }
        String startD = helper.getUTCTime(startDate);
        String startT = helper.getUTCTimeFormatISO(startDate);
        String endD = helper.getUTCTime(endDate);
        String endT = helper.getUTCTimeFormatISO(endDate);
        //change
        ((ObjectNode) jsonNode.get("createFlashSaleSpecialBranch").get("body").get("flashSale")).put("name", flashSaleName).put("startDate", startD).put("startTime", startT).put("endDate", endD).put("endTime", endT).put("isIncludedTopping", false);
        // Access "flashSaleProducts"
        ArrayNode flashSaleProductsArray = (ArrayNode) jsonNode.get("createFlashSaleSpecialBranch").get("body").get("flashSale").get("flashSaleProducts");
        flashSaleProductsArray.removeAll();
        //Access "branchIds"
        ArrayNode flashSaleBranchIdsArray = (ArrayNode) jsonNode.get("createFlashSaleSpecialBranch").get("body").get("flashSale").get("branchIds");
        flashSaleBranchIdsArray.removeAll();
        //Access "products"
        ArrayNode productsArray = (ArrayNode) jsonNode.get("createFlashSaleSpecialBranch").get("body").get("flashSale").get("products");
        productsArray.removeAll();
        // branchIds[]
        for (String branchName : branchesNameList) {
            flashSaleBranchIdsArray.add(getBranchIdByName(branchName));
        }
        for (Product product : productList) {
            for (ProductPrices price : product.getProductPrices()) {
                FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
                //flashSaleProducts[]
                ObjectNode flashSaleProductsNode = objectMapper.createObjectNode();
                flashSaleProductsNode.put("id", price.getId());
                flashSaleProductsNode.put("name", product.getName());
                int priceInt = Math.round(price.getPrice());
                flashSaleProductsNode.put("price", priceInt);
                // Add priceFormat in productNode
                ObjectNode priceFormatNode = objectMapper.createObjectNode();
                ObjectNode propsNode = objectMapper.createObjectNode();
                propsNode.put("value", priceInt);
                //Hard - default
                propsNode.put("displayType", "text");
                propsNode.put("thousandSeparator", true);
                propsNode.put("decimalScale", 2);
                propsNode.put("decimalSeparator", ".");
                propsNode.put("thousandSpacing", "3");
                propsNode.put("fixedDecimalScale", false);
                propsNode.put("prefix", "");
                propsNode.put("suffix", "");
                propsNode.put("allowNegative", true);
                propsNode.put("isNumericString", false);
                propsNode.put("type", "text");
                priceFormatNode.set("props", propsNode);
                flashSaleProductsNode.set("priceFormat", priceFormatNode);
                int randomQuantity = random.nextInt(20) + 1;
                Boolean isSinglePrice;
                if (product.getProductPrices().size() > 1) {
                    isSinglePrice = false;
                    flashSaleProductsNode.put("productPriceName", price.getPriceName());
                } else {
                    isSinglePrice = true;
                }
                flashSaleProductsNode.put("isSinglePrice", isSinglePrice);
                flashSaleProductsNode.put("thumbnail", product.getThumbnail());
                int priceRandom = random.nextInt(Math.round(price.getPrice())) + 1;
                flashSaleProductsNode.put("flashSalePrice", priceRandom);
                flashSaleProductsNode.put("flashSaleQuantity", randomQuantity);
                int maximumLimit = random.nextInt(randomQuantity) + 1;
                flashSaleProductsNode.put("maximumLimit", maximumLimit);
                flashSaleProductsArray.add(flashSaleProductsNode);
                // products[]
                ObjectNode productsNode = objectMapper.createObjectNode();
                productsNode.put("flashSalePrice", priceRandom);
                productsNode.put("flashSaleQuantity", randomQuantity);
                productsNode.put("maximumLimit", maximumLimit);
                productsArray.add(productsNode);
                flashSaleProduct = setFlashSaleProduct(price.getId(), product.getName(), priceInt, priceRandom, randomQuantity, maximumLimit, isSinglePrice, price.getPriceName(), product.getThumbnail(), "");
                flashSaleProductList.add(flashSaleProduct);
            }
        }
        //add flashSale object
        flashSale = setFlashSale(flashSaleName, startD, startT, endD, endT, "", flashSaleProductList);
        // json Formatter and writer
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        objectWriter.writeValue(jsonFile, jsonNode);
        // Call api
        JsonNode flashSaleNode = jsonNode.path("createFlashSaleSpecialBranch").path("body");
        String jsonPartAsString = objectMapper.writeValueAsString(flashSaleNode);
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(jsonPartAsString).post(apiPath);
        System.out.println("Response Code: " + response.getStatusCode());
        if (response.getStatusCode() != 200) {
            response.jsonPath().prettyPrint();
        }
        statusCode = response.getStatusCode();
        return flashSale;
    }

    private static FlashSale createFlashSaleNotFullVariations(List<String> productNameList, String flashSaleName, LocalDateTime startDate, LocalDateTime endDate) throws IOException {
        Random random = new Random();
        FlashSale flashSale = new FlashSale();
        List<FlashSaleProduct> flashSaleProductList = new ArrayList<>();
        String apiPath = apiURL + "flashSale/create-flash-sale";
        String fileJsonPath = path + typeAPI + "/jsonSchema/" + theme + "/flashsale/flashSaleInformation.json";
        File jsonFile = new File(fileJsonPath);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonFile);
        List<Product> productList = new ArrayList<>();
        for (String productName : productNameList) {
            Product product = getProductBySearchName(productName);
            productList.add(product);
        }
        String startD = helper.getUTCTime(startDate);
        String startT = helper.getUTCTimeFormatISO(startDate);
        String endD = helper.getUTCTime(endDate);
        String endT = helper.getUTCTimeFormatISO(endDate);
        //change
        ((ObjectNode) jsonNode.get("createFlashSale").get("body").get("flashSale")).put("name", flashSaleName).put("startDate", startD).put("startTime", startT).put("endDate", endD).put("endTime", endT).put("isIncludedTopping", false);
        // Access "flashSaleProducts"
        ArrayNode flashSaleProductsArray = (ArrayNode) jsonNode.get("createFlashSale").get("body").get("flashSale").get("flashSaleProducts");
        flashSaleProductsArray.removeAll();
        //Access "products"
        ArrayNode productsArray = (ArrayNode) jsonNode.get("createFlashSale").get("body").get("flashSale").get("products");
        productsArray.removeAll();
        int count = 0;
        int getVariationRandomIndex = 0;
        for (Product product : productList) {
            if (product.getProductPrices().size() > 1) {
                getVariationRandomIndex = random.nextInt(product.getProductPrices().size() - 1) + 1;
                count++;
                if (count > product.getProductPrices().size() / 2) break;
            } else getVariationRandomIndex = 0;
            FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
            //flashSaleProducts[]
            ObjectNode flashSaleProductsNode = objectMapper.createObjectNode();
            flashSaleProductsNode.put("id", product.getProductPrices().get(getVariationRandomIndex).getId());
            flashSaleProductsNode.put("name", product.getName());
            int priceInt = Math.round(product.getProductPrices().get(getVariationRandomIndex).getPrice());
            flashSaleProductsNode.put("price", priceInt);
            // Add priceFormat in productNode
            ObjectNode priceFormatNode = objectMapper.createObjectNode();
            ObjectNode propsNode = objectMapper.createObjectNode();
            propsNode.put("value", priceInt);
            //Hard - default
            propsNode.put("displayType", "text");
            propsNode.put("thousandSeparator", true);
            propsNode.put("decimalScale", 2);
            propsNode.put("decimalSeparator", ".");
            propsNode.put("thousandSpacing", "3");
            propsNode.put("fixedDecimalScale", false);
            propsNode.put("prefix", "");
            propsNode.put("suffix", "");
            propsNode.put("allowNegative", true);
            propsNode.put("isNumericString", false);
            propsNode.put("type", "text");
            priceFormatNode.set("props", propsNode);
            flashSaleProductsNode.set("priceFormat", priceFormatNode);
            int randomQuantity = random.nextInt(20) + 1;
            Boolean isSinglePrice;
            if (product.getProductPrices().size() > 1) {
                isSinglePrice = false;
                flashSaleProductsNode.put("productPriceName", product.getProductPrices().get(getVariationRandomIndex).getPriceName());
            } else {
                isSinglePrice = true;
            }
            flashSaleProductsNode.put("isSinglePrice", isSinglePrice);
            flashSaleProductsNode.put("thumbnail", product.getThumbnail());
            int priceRandom = random.nextInt(Math.round(product.getProductPrices().get(getVariationRandomIndex).getPrice())) + 1;
            flashSaleProductsNode.put("flashSalePrice", priceRandom);
            flashSaleProductsNode.put("flashSaleQuantity", randomQuantity);
            int maximumLimit = random.nextInt(randomQuantity) + 1;
            flashSaleProductsNode.put("maximumLimit", maximumLimit);
            flashSaleProductsArray.add(flashSaleProductsNode);
            // products[]
            ObjectNode productsNode = objectMapper.createObjectNode();
            productsNode.put("flashSalePrice", priceRandom);
            productsNode.put("flashSaleQuantity", randomQuantity);
            productsNode.put("maximumLimit", maximumLimit);
            productsArray.add(productsNode);
            flashSaleProduct = setFlashSaleProduct(product.getProductPrices().get(getVariationRandomIndex).getId(), product.getName(), priceInt, priceRandom, randomQuantity, maximumLimit, isSinglePrice, product.getProductPrices().get(getVariationRandomIndex).getPriceName(), product.getThumbnail(), "");
            flashSaleProductList.add(flashSaleProduct);
        }
        //add flashSale object
        flashSale = setFlashSale(flashSaleName, startD, startT, endD, endT, "", flashSaleProductList);
        // json Formatter and writer
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        objectWriter.writeValue(jsonFile, jsonNode);
        // Call api
        JsonNode flashSaleNode = jsonNode.path("createFlashSale").path("body");
        String jsonPartAsString = objectMapper.writeValueAsString(flashSaleNode);
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(jsonPartAsString).post(apiPath);
        System.out.println("Response Code: " + response.getStatusCode());
        if (response.getStatusCode() != 200) {
            response.jsonPath().prettyPrint();
        }
        statusCode = response.getStatusCode();
        return flashSale;
    }

    private static FlashSale createFlashSaleNotFullVariationsWithQuantity(List<String> productNameList, String flashSaleName, int maximumLimit, int flashSaleQuantity, LocalDateTime startDate, LocalDateTime endDate) throws IOException {
        Random random = new Random();
        FlashSale flashSale = new FlashSale();
        List<FlashSaleProduct> flashSaleProductList = new ArrayList<>();
        String apiPath = apiURL + "flashSale/create-flash-sale";
        String fileJsonPath = path + typeAPI + "/jsonSchema/" + theme + "/flashsale/flashSaleInformation.json";
        File jsonFile = new File(fileJsonPath);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonFile);
        List<Product> productList = new ArrayList<>();
        for (String productName : productNameList) {
            Product product = getProductBySearchName(productName);
            productList.add(product);
        }
        String startD = helper.getUTCTime(startDate);
        String startT = helper.getUTCTimeFormatISO(startDate);
        String endD = helper.getUTCTime(endDate);
        String endT = helper.getUTCTimeFormatISO(endDate);
        //change
        ((ObjectNode) jsonNode.get("createFlashSale").get("body").get("flashSale")).put("name", flashSaleName).put("startDate", startD).put("startTime", startT).put("endDate", endD).put("endTime", endT).put("isIncludedTopping", false);
        // Access "flashSaleProducts"
        ArrayNode flashSaleProductsArray = (ArrayNode) jsonNode.get("createFlashSale").get("body").get("flashSale").get("flashSaleProducts");
        flashSaleProductsArray.removeAll();
        //Access "products"
        ArrayNode productsArray = (ArrayNode) jsonNode.get("createFlashSale").get("body").get("flashSale").get("products");
        productsArray.removeAll();
        int count = 0;
        int getVariationRandomIndex = 0;
        for (Product product : productList) {
            if (product.getProductPrices().size() > 1) {
                getVariationRandomIndex = random.nextInt(product.getProductPrices().size() - 1) + 1;
                count++;
                if (count > product.getProductPrices().size() / 2) break;
            } else getVariationRandomIndex = 0;
            FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
            //flashSaleProducts[]
            ObjectNode flashSaleProductsNode = objectMapper.createObjectNode();
            flashSaleProductsNode.put("id", product.getProductPrices().get(getVariationRandomIndex).getId());
            flashSaleProductsNode.put("name", product.getName());
            int priceInt = Math.round(product.getProductPrices().get(getVariationRandomIndex).getPrice());
            flashSaleProductsNode.put("price", priceInt);
            // Add priceFormat in productNode
            ObjectNode priceFormatNode = objectMapper.createObjectNode();
            ObjectNode propsNode = objectMapper.createObjectNode();
            propsNode.put("value", priceInt);
            //Hard - default
            propsNode.put("displayType", "text");
            propsNode.put("thousandSeparator", true);
            propsNode.put("decimalScale", 2);
            propsNode.put("decimalSeparator", ".");
            propsNode.put("thousandSpacing", "3");
            propsNode.put("fixedDecimalScale", false);
            propsNode.put("prefix", "");
            propsNode.put("suffix", "");
            propsNode.put("allowNegative", true);
            propsNode.put("isNumericString", false);
            propsNode.put("type", "text");
            priceFormatNode.set("props", propsNode);
            flashSaleProductsNode.set("priceFormat", priceFormatNode);
            Boolean isSinglePrice;
            if (product.getProductPrices().size() > 1) {
                isSinglePrice = false;
                flashSaleProductsNode.put("productPriceName", product.getProductPrices().get(getVariationRandomIndex).getPriceName());
            } else {
                isSinglePrice = true;
            }
            flashSaleProductsNode.put("isSinglePrice", isSinglePrice);
            flashSaleProductsNode.put("thumbnail", product.getThumbnail());
            int priceRandom = random.nextInt(Math.round(product.getProductPrices().get(getVariationRandomIndex).getPrice())) + 1;
            flashSaleProductsNode.put("flashSalePrice", priceRandom);
            flashSaleProductsNode.put("flashSaleQuantity", flashSaleQuantity);
            flashSaleProductsNode.put("maximumLimit", maximumLimit);
            flashSaleProductsArray.add(flashSaleProductsNode);
            // products[]
            ObjectNode productsNode = objectMapper.createObjectNode();
            productsNode.put("flashSalePrice", priceRandom);
            productsNode.put("flashSaleQuantity", flashSaleQuantity);
            productsNode.put("maximumLimit", maximumLimit);
            productsArray.add(productsNode);
            flashSaleProduct = setFlashSaleProduct(product.getProductPrices().get(getVariationRandomIndex).getId(), product.getName(), priceInt, priceRandom, flashSaleQuantity, maximumLimit, isSinglePrice, product.getProductPrices().get(getVariationRandomIndex).getPriceName(), product.getThumbnail(), "");
            flashSaleProductList.add(flashSaleProduct);
        }
        //add flashSale object
        flashSale = setFlashSale(flashSaleName, startD, startT, endD, endT, "", flashSaleProductList);
        // json Formatter and writer
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        objectWriter.writeValue(jsonFile, jsonNode);
        // Call api
        JsonNode flashSaleNode = jsonNode.path("createFlashSale").path("body");
        String jsonPartAsString = objectMapper.writeValueAsString(flashSaleNode);
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(jsonPartAsString).post(apiPath);
        System.out.println("Response Code: " + response.getStatusCode());
        if (response.getStatusCode() != 200) {
            response.jsonPath().prettyPrint();
        }
        statusCode = response.getStatusCode();
        return flashSale;
    }

    /**
     * Create flash sale with special variation, select included topping
     *
     * @param productNameList
     * @param flashSaleName
     * @param variation
     * @param isIncludedTopping
     * @param maximumLimit
     * @param flashSaleQuantity
     * @param startDate
     * @param endDate
     * @return
     * @throws IOException
     */
    private static FlashSale createFlashSaleWithVariation(List<String> productNameList, String flashSaleName, String variation, Boolean isIncludedTopping, int maximumLimit, int flashSaleQuantity, LocalDateTime startDate, LocalDateTime endDate) throws IOException {
        Random random = new Random();
        FlashSale flashSale = new FlashSale();
        List<FlashSaleProduct> flashSaleProductList = new ArrayList<>();
        String apiPath = apiURL + "flashSale/create-flash-sale";
        String fileJsonPath = path + typeAPI + "/jsonSchema/" + theme + "/flashsale/flashSaleInformation.json";
        File jsonFile = new File(fileJsonPath);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonFile);
        List<Product> productList = new ArrayList<>();
        for (String productName : productNameList) {
            Product product = getProductBySearchName(productName);
            productList.add(product);
        }
        String startD = helper.getUTCTime(startDate);
        String startT = helper.getUTCTimeFormatISO(startDate);
        String endD = helper.getUTCTime(endDate);
        String endT = helper.getUTCTimeFormatISO(endDate);
        //change
        ((ObjectNode) jsonNode.get("createFlashSale").get("body").get("flashSale")).put("name", flashSaleName).put("startDate", startD).put("startTime", startT).put("endDate", endD).put("endTime", endT).put("isIncludedTopping", isIncludedTopping);
        // Access "flashSaleProducts"
        ArrayNode flashSaleProductsArray = (ArrayNode) jsonNode.get("createFlashSale").get("body").get("flashSale").get("flashSaleProducts");
        flashSaleProductsArray.removeAll();
        //Access "products"
        ArrayNode productsArray = (ArrayNode) jsonNode.get("createFlashSale").get("body").get("flashSale").get("products");
        productsArray.removeAll();
        for (Product product : productList) {
            for (ProductPrices price : product.getProductPrices()) {
                FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
                //flashSaleProducts[]
                ObjectNode flashSaleProductsNode = objectMapper.createObjectNode();
                flashSaleProductsNode.put("id", price.getId());
                flashSaleProductsNode.put("name", product.getName());
                int priceInt = Math.round(price.getPrice());
                flashSaleProductsNode.put("price", priceInt);
                // Add priceFormat in productNode
                ObjectNode priceFormatNode = objectMapper.createObjectNode();
                ObjectNode propsNode = objectMapper.createObjectNode();
                propsNode.put("value", priceInt);
                //Hard - default
                propsNode.put("displayType", "text");
                propsNode.put("thousandSeparator", true);
                propsNode.put("decimalScale", 2);
                propsNode.put("decimalSeparator", ".");
                propsNode.put("thousandSpacing", "3");
                propsNode.put("fixedDecimalScale", false);
                propsNode.put("prefix", "");
                propsNode.put("suffix", "");
                propsNode.put("allowNegative", true);
                propsNode.put("isNumericString", false);
                propsNode.put("type", "text");
                priceFormatNode.set("props", propsNode);
                flashSaleProductsNode.set("priceFormat", priceFormatNode);
                Boolean isSinglePrice = false;
                if (product.getProductPrices().size() > 1) {
                    if (price.getPriceName().equals(variation)) {
                        isSinglePrice = false;
                        flashSaleProductsNode.put("productPriceName", price.getPriceName());
                        flashSaleProductsNode.put("isSinglePrice", isSinglePrice);
                        flashSaleProductsNode.put("thumbnail", product.getThumbnail());
                        int priceRandom = random.nextInt(Math.round(price.getPrice())) + 1;
                        flashSaleProductsNode.put("flashSalePrice", priceRandom);
                        flashSaleProductsNode.put("flashSaleQuantity", flashSaleQuantity);
                        flashSaleProductsNode.put("maximumLimit", maximumLimit);
                        flashSaleProductsArray.add(flashSaleProductsNode);
                        // products[]
                        ObjectNode productsNode = objectMapper.createObjectNode();
                        productsNode.put("flashSalePrice", priceRandom);
                        productsNode.put("flashSaleQuantity", flashSaleQuantity);
                        productsNode.put("maximumLimit", maximumLimit);
                        productsArray.add(productsNode);
                        flashSaleProduct = setFlashSaleProduct(price.getId(), product.getName(), priceInt, priceRandom, flashSaleQuantity, maximumLimit, isSinglePrice, price.getPriceName(), product.getThumbnail(), "");
                        flashSaleProductList.add(flashSaleProduct);
                    }
                } else {
                    flashSaleProductsNode.put("thumbnail", product.getThumbnail());
                    int priceRandom = random.nextInt(Math.round(price.getPrice())) + 1;
                    flashSaleProductsNode.put("flashSalePrice", priceRandom);
                    flashSaleProductsNode.put("flashSaleQuantity", flashSaleQuantity);
                    flashSaleProductsNode.put("maximumLimit", maximumLimit);
                    flashSaleProductsArray.add(flashSaleProductsNode);
                    // products[]
                    ObjectNode productsNode = objectMapper.createObjectNode();
                    productsNode.put("flashSalePrice", priceRandom);
                    productsNode.put("flashSaleQuantity", flashSaleQuantity);
                    productsNode.put("maximumLimit", maximumLimit);
                    productsArray.add(productsNode);
                    flashSaleProduct = setFlashSaleProduct(price.getId(), product.getName(), priceInt, priceRandom, flashSaleQuantity, maximumLimit, isSinglePrice, price.getPriceName(), product.getThumbnail(), "");
                    flashSaleProductList.add(flashSaleProduct);
                }

            }
        }
        //add flashSale object
        flashSale = setFlashSale(flashSaleName, startD, startT, endD, endT, "", flashSaleProductList);
        // json Formatter and writer
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        objectWriter.writeValue(jsonFile, jsonNode);
        // Call api
        JsonNode flashSaleNode = jsonNode.path("createFlashSale").path("body");
        String jsonPartAsString = objectMapper.writeValueAsString(flashSaleNode);
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(jsonPartAsString).post(apiPath);
        System.out.println("Response Code: " + response.getStatusCode());
        if (response.getStatusCode() != 200) {
            response.jsonPath().prettyPrint();
        }
        statusCode = response.getStatusCode();
        return flashSale;
    }

    private static FlashSale createFlashSaleMinimumPurchaseOrderAPI(List<String> productNameList, String flashSaleName, LocalDateTime startDate, LocalDateTime endDate, int maximumLimit, int flashSaleQuantity, int minimumPurchase) throws IOException {
        Random random = new Random();
        FlashSale flashSale = new FlashSale();
        List<FlashSaleProduct> flashSaleProductList = new ArrayList<>();
        String apiPath = apiURL + "flashSale/create-flash-sale";
        String fileJsonPath = path + typeAPI + "/jsonSchema/" + theme + "/flashsale/flashSaleInformation.json";
        File jsonFile = new File(fileJsonPath);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonFile);
        List<Product> productList = new ArrayList<>();
        for (String productName : productNameList) {
            Product product = getProductBySearchName(productName);
            productList.add(product);
        }
        String startD = helper.getUTCTime(startDate);
        String startT = helper.getUTCTimeFormatISO(startDate);
        String endD = helper.getUTCTime(endDate);
        String endT = helper.getUTCTimeFormatISO(endDate);
        //change
        ((ObjectNode) jsonNode.get("createFlashSaleMinimumPurchase").get("body").get("flashSale")).put("name", flashSaleName).put("startDate", startD).put("startTime", startT).put("endDate", endD).put("endTime", endT).put("isMinimumPurchaseAmount", true).put("minimumPurchaseAmount", minimumPurchase).put("isIncludedTopping", false);
        // Access "flashSaleProducts"
        ArrayNode flashSaleProductsArray = (ArrayNode) jsonNode.get("createFlashSaleMinimumPurchase").get("body").get("flashSale").get("flashSaleProducts");
        flashSaleProductsArray.removeAll();
        //Access "products"
        ArrayNode productsArray = (ArrayNode) jsonNode.get("createFlashSaleMinimumPurchase").get("body").get("flashSale").get("products");
        productsArray.removeAll();
        int count = 0;
        int getVariationRandomIndex = 0;
        for (Product product : productList) {
            if (product.getProductPrices().size() > 1) {
                getVariationRandomIndex = random.nextInt(product.getProductPrices().size() - 1) + 1;
                count++;
                if (count > product.getProductPrices().size() / 2) break;
            } else getVariationRandomIndex = 0;
            FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
            //flashSaleProducts[]
            ObjectNode flashSaleProductsNode = objectMapper.createObjectNode();
            flashSaleProductsNode.put("id", product.getProductPrices().get(getVariationRandomIndex).getId());
            flashSaleProductsNode.put("name", product.getName());
            int priceInt = Math.round(product.getProductPrices().get(getVariationRandomIndex).getPrice());
            flashSaleProductsNode.put("price", priceInt);
            // Add priceFormat in productNode
            ObjectNode priceFormatNode = objectMapper.createObjectNode();
            ObjectNode propsNode = objectMapper.createObjectNode();
            propsNode.put("value", priceInt);
            //Hard - default
            propsNode.put("displayType", "text");
            propsNode.put("thousandSeparator", true);
            propsNode.put("decimalScale", 2);
            propsNode.put("decimalSeparator", ".");
            propsNode.put("thousandSpacing", "3");
            propsNode.put("fixedDecimalScale", false);
            propsNode.put("prefix", "");
            propsNode.put("suffix", "");
            propsNode.put("allowNegative", true);
            propsNode.put("isNumericString", false);
            propsNode.put("type", "text");
            priceFormatNode.set("props", propsNode);
            flashSaleProductsNode.set("priceFormat", priceFormatNode);
            Boolean isSinglePrice;
            if (product.getProductPrices().size() > 1) {
                isSinglePrice = false;
                flashSaleProductsNode.put("productPriceName", product.getProductPrices().get(getVariationRandomIndex).getPriceName());
            } else {
                isSinglePrice = true;
            }
            flashSaleProductsNode.put("isSinglePrice", isSinglePrice);
            flashSaleProductsNode.put("thumbnail", product.getThumbnail());
            int priceRandom = random.nextInt(Math.round(product.getProductPrices().get(getVariationRandomIndex).getPrice())) + 1;
            flashSaleProductsNode.put("flashSalePrice", priceRandom);
            flashSaleProductsNode.put("flashSaleQuantity", flashSaleQuantity);
            flashSaleProductsNode.put("maximumLimit", maximumLimit);
            flashSaleProductsArray.add(flashSaleProductsNode);
            // products[]
            ObjectNode productsNode = objectMapper.createObjectNode();
            productsNode.put("flashSalePrice", priceRandom);
            productsNode.put("flashSaleQuantity", flashSaleQuantity);
            productsNode.put("maximumLimit", maximumLimit);
            productsArray.add(productsNode);
            flashSaleProduct = setFlashSaleProduct(product.getProductPrices().get(getVariationRandomIndex).getId(), product.getName(), priceInt, priceRandom, flashSaleQuantity, maximumLimit, isSinglePrice, product.getProductPrices().get(getVariationRandomIndex).getPriceName(), product.getThumbnail(), "");
            flashSaleProductList.add(flashSaleProduct);
        }
        //add flashSale object
        flashSale = setFlashSale(flashSaleName, startD, startT, endD, endT, "", flashSaleProductList);
        // json Formatter and writer
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        objectWriter.writeValue(jsonFile, jsonNode);
        // Call api
        JsonNode flashSaleNode = jsonNode.path("createFlashSaleMinimumPurchase").path("body");
        String jsonPartAsString = objectMapper.writeValueAsString(flashSaleNode);
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(jsonPartAsString).post(apiPath);
        System.out.println("Response Code: " + response.getStatusCode());
        if (response.getStatusCode() != 200) {
            response.jsonPath().prettyPrint();
        }
        statusCode = response.getStatusCode();
        return flashSale;
    }

    //create flash sale
    public static LocalDateTime createFlashSaleList(List<String> productFullName, String flashSaleName, int addStartMinute, int addEndMinus) {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime startDate = currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0);
        LocalDateTime endDate = currentDateTime.plusMinutes(addStartMinute + addEndMinus).withSecond(0).withNano(0);
        int loopNo = 10;
        try {
            createFlashSaleAPI(productFullName, flashSaleName, startDate, endDate);
            while (statusCode == 500 && loopNo > 0) {
                currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
                startDate = currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0);
                endDate = currentDateTime.plusMinutes(addStartMinute + addEndMinus).withSecond(0).withNano(0);
                createFlashSaleAPI(productFullName, flashSaleName, startDate, endDate);
                loopNo--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }

    public static LocalDateTime createFlashSaleWithSpecialBranch(List<String> productFullName, String flashSaleName, int addStartMinute, int addEndMinus, List<String> branchNameList) {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime startDate = currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0);
        LocalDateTime endDate = currentDateTime.plusMinutes(addStartMinute + addEndMinus).withSecond(0).withNano(0);
        int loopNo = 10;
        try {
            createFlashSaleSpecialBranchAPI(productFullName, flashSaleName, startDate, endDate, branchNameList);
            while (statusCode == 500 && loopNo > 0) {
                currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
                startDate = currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0);
                endDate = currentDateTime.plusMinutes(addStartMinute + addEndMinus).withSecond(0).withNano(0);
                createFlashSaleSpecialBranchAPI(productFullName, flashSaleName, startDate, endDate, branchNameList);
                loopNo--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }

    public static LocalDateTime createFlashSaleListNotFullVariations(List<String> productFullName, String flashSaleName, int addStartMinute, int addEndMinus) {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime startDate = currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0);
        LocalDateTime endDate = currentDateTime.plusMinutes(addStartMinute + addEndMinus).withSecond(0).withNano(0);
        int loopNo = 10;
        try {
            createFlashSaleNotFullVariations(productFullName, flashSaleName, startDate, endDate);
            while (statusCode == 500 && loopNo > 0) {
                currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
                startDate = currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0);
                endDate = currentDateTime.plusMinutes(addStartMinute + addEndMinus).withSecond(0).withNano(0);
                createFlashSaleNotFullVariations(productFullName, flashSaleName, startDate, endDate);
                loopNo--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }

    public static LocalDateTime createFlashSaleWithQuantity(List<String> productFullName, String flashSaleName, int maximumLimit, int flashSaleQuantity, int addStartMinute, int addEndMinus) {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime startDate = currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0);
        LocalDateTime endDate = currentDateTime.plusMinutes(addStartMinute + addEndMinus).withSecond(0).withNano(0);
        int loopNo = 10;
        try {
            createFlashSaleNotFullVariationsWithQuantity(productFullName, flashSaleName, maximumLimit, flashSaleQuantity, startDate, endDate);
            while (statusCode == 500 && loopNo > 0) {
                currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
                startDate = currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0);
                endDate = currentDateTime.plusMinutes(addStartMinute + addEndMinus).withSecond(0).withNano(0);
                createFlashSaleNotFullVariationsWithQuantity(productFullName, flashSaleName, maximumLimit, flashSaleQuantity, startDate, endDate);
                loopNo--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }

    public static LocalDateTime createFlashSaleWithVariation(List<String> productFullName, String flashSaleName, String variation, Boolean isIncludedTopping, int maximumLimit, int flashSaleQuantity, int addStartMinute, int addEndMinus) {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime startDate = currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0);
        LocalDateTime endDate = currentDateTime.plusMinutes(addStartMinute + addEndMinus).withSecond(0).withNano(0);
        int loopNo = 10;
        try {
            createFlashSaleWithVariation(productFullName, flashSaleName, variation, isIncludedTopping, maximumLimit, flashSaleQuantity, startDate, endDate);
            while (statusCode == 500 && loopNo > 0) {
                currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
                startDate = currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0);
                endDate = currentDateTime.plusMinutes(addStartMinute + addEndMinus).withSecond(0).withNano(0);
                createFlashSaleWithVariation(productFullName, flashSaleName, variation, isIncludedTopping, maximumLimit, flashSaleQuantity, startDate, endDate);
                loopNo--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }

    public static LocalDateTime createFlashSaleWithMinimumPurchaseOrder(List<String> productFullName, String flashSaleName, int addStartMinute, int addEndMinus, int maximumLimit, int flashSaleQuantity, int minimumPurchase) {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime startDate = currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0);
        LocalDateTime endDate = currentDateTime.plusMinutes(addStartMinute + addEndMinus).withSecond(0).withNano(0);
        int loopNo = 10;
        try {
            createFlashSaleMinimumPurchaseOrderAPI(productFullName, flashSaleName, startDate, endDate, maximumLimit, flashSaleQuantity, minimumPurchase);
            while (statusCode == 500 && loopNo > 0) {
                currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
                startDate = currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0);
                endDate = currentDateTime.plusMinutes(addStartMinute + addEndMinus).withSecond(0).withNano(0);
                createFlashSaleMinimumPurchaseOrderAPI(productFullName, flashSaleName, startDate, endDate, maximumLimit, flashSaleQuantity, minimumPurchase);
                loopNo--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }

    public static FlashSale getFlashSaleByFilterSearch(String keySearch) {
        String apiPath = "flashSale/get-all-flash-sale";
        FlashSale flashSale = new FlashSale();
        RequestSpecification request = given();
        request.baseUri(apiURL).basePath(apiPath).auth().oauth2(token).accept("application/json");
        request.queryParam("keySearch", keySearch);
        Response response = request.when().get();
        flashSale.setId(response.jsonPath().get("flashSales[0].id"));
        flashSale.setFlashSaleName(response.jsonPath().get("flashSales[0].name"));
        flashSale.setStartTime(response.jsonPath().get("flashSales[0].startTime"));
        flashSale.setEndTime(response.jsonPath().get("flashSales[0].endTime"));
        return flashSale;
    }

    public static void deleteFlashSaleById(String keySearch) {
        String apiPath = "flashSale/delete-flash-sale-by-id/";
        FlashSale flashSale = getFlashSaleByFilterSearch(keySearch);
        String id = flashSale.getId();
        RequestSpecification request = given();
        request.baseUri(apiURL).basePath(apiPath + id).auth().oauth2(token).accept("application/json");
        Response response = request.when().delete();
        if (response.getStatusCode() != 200) {
            System.out.println(response.getStatusCode());
            response.jsonPath().prettyPrint();
        }
    }

    private static int editTimeFlashSaleAPIHardCode(String flashSaleName, LocalDateTime startDate, LocalDateTime endDate) throws IOException {
        FlashSale flashSale = getFlashSaleByFilterSearch(flashSaleName);
        String apiPath = apiURL + "flashSale/edit-flash-sale";
        String fileJsonPath = path + typeAPI + "/jsonSchema/" + theme + "/flashsale/flashSaleInformation.json";
        File jsonFile = new File(fileJsonPath);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonFile);
        String startD = helper.getUTCTime(startDate);
        String startT = helper.getUTCTimeFormatISO(startDate);
        String endD = helper.getUTCTime(endDate);
        String endT = helper.getUTCTimeFormatISO(endDate);
        //get information from FlashSale
        ArrayNode createFlashSaleFlashSaleProductsArray = (ArrayNode) jsonNode.get("createFlashSale").get("body").get("flashSale").get("flashSaleProducts");
        ArrayNode createFlashSaleProductsArray = (ArrayNode) jsonNode.get("createFlashSale").get("body").get("flashSale").get("products");
        //change data
        ((ObjectNode) jsonNode.get("editFlashSale").get("body").get("flashSale")).put("startDate", startD).put("startTime", startT).put("endDate", endD).put("endTime", endT).put("id", flashSale.getId()).put("name", flashSale.getFlashSaleName());
        // Access "flashSaleProducts"
        ArrayNode flashSaleProductsArray = (ArrayNode) jsonNode.get("editFlashSale").get("body").get("flashSale").get("flashSaleProducts");
        flashSaleProductsArray.removeAll();
        //Access
        ArrayNode productsArray = (ArrayNode) jsonNode.get("editFlashSale").get("body").get("flashSale").get("products");
        productsArray.removeAll();
        for (JsonNode createFlashSaleProduct : createFlashSaleFlashSaleProductsArray) {
            flashSaleProductsArray.add(createFlashSaleProduct.deepCopy());
        }
        for (JsonNode createFlashSaleProduct : createFlashSaleProductsArray) {
            productsArray.add(createFlashSaleProduct.deepCopy());
        }
        // json Formatter and writer
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        objectWriter.writeValue(jsonFile, jsonNode);
        // Call api
        JsonNode flashSaleNode = jsonNode.path("editFlashSale").path("body");
        String jsonPartAsString = objectMapper.writeValueAsString(flashSaleNode);
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(jsonPartAsString).post(apiPath);
        System.out.println("Response Code: " + response.getStatusCode());
        if (response.getStatusCode() != 200) {
            response.jsonPath().prettyPrint();
        }
        return response.statusCode();
    }

    public static LocalDateTime editTimeOfFlashSale(String keySearch, int addStartMinute, int addEndMinus) {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.systemDefault()); //get server time
        LocalDateTime startDate = currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0);
        LocalDateTime endDate = currentDateTime.plusMinutes(addStartMinute + addEndMinus).withSecond(0).withNano(0);
        try {
            int statusCode = editTimeFlashSaleAPIHardCode(keySearch, startDate, endDate);
            if (statusCode == 500) editTimeFlashSaleAPIHardCode(keySearch, startDate, endDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentDateTime;
    }

    public static FlashSale getFlashSaleByIdForDetailPage(String flashSaleName) {
        String apiPath = "flashSale/get-flash-sale-by-id-for-detail-page/";
        FlashSale flashSale = getFlashSaleByFilterSearch(flashSaleName);
        String id = flashSale.getId();
        RequestSpecification request = given();
        request.baseUri(apiURL).basePath(apiPath + id).auth().oauth2(token).accept("application/json");
        Response response = request.when().get();
        String responseBody = response.getBody().asString();
        if (response.getStatusCode() != 200) {
            System.out.println(response.getStatusCode());
            response.jsonPath().prettyPrint();
            return null;
        } else return getFlashSaleInformation(responseBody, flashSale);
    }

    public static void stopFlashSaleById(String keySearch) {
        String apiPath = "flashSale/stop-flash-sale-by-id/";
        FlashSale flashSale = getFlashSaleByFilterSearch(keySearch);
        String id = flashSale.getId();
        RequestSpecification request = given();
        request.baseUri(apiURL).basePath(apiPath + id).auth().oauth2(token).accept("application/json");
        Response response = request.when().post();
        if (response.getStatusCode() != 200) {
            System.out.println(response.getStatusCode());
            response.jsonPath().prettyPrint();
        }
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

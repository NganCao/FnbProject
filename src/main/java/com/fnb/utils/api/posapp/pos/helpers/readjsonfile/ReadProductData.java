package com.fnb.utils.api.posapp.pos.helpers.readjsonfile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadProductData {
    public static final String FILE_PATH_PRODUCT_DATA = "C:\\Users\\admin\\Documents\\Automation\\selenium\\resources\\data\\product\\productData.json";

    public static void main(String[] args) {
        String productName = "Vịt thơm - all";
        String size = "M";
        String option = "Đá viên";
        String optionType = "Vừa";
        String topping = "Thạch topping";
        String quantity = "1";

//            JSONObject product = readProductDataFile(productName);
//            ProductSizePrice(product);
//            printProductDetails(product);
//            printProductInventoryData(product, size);
//            getProductQuantities(product, size);
//            String s = String.valueOf(getProductQuantities(product, size));
        String s = String.valueOf(getProductQuantities(productName, size));
        System.out.println(s);
//            printOptions(product);
//            String option1 = String.valueOf(printOptions(product));
//            System.out.println(option1);
//            System.out.println(String.valueOf(printToppings(product, topping)));
    }



    public static JSONObject readProductDataFile(String productName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(FILE_PATH_PRODUCT_DATA);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        reader.close();
//        System.out.println("Searching for product: " + productName);

        JSONArray productsArray = (JSONArray) jsonObject.get("product"); // Corrected key
        if (productsArray != null) {
            for (Object obj : productsArray) {
                JSONObject product = (JSONObject) obj;
//                System.out.println("Checking product: " + product.get("name"));
                if (productName.equals(product.get("name"))) {
//                    System.out.println("Product found: " + product);
                    return product;
                }
            }
        }
        return null;
    }

    public static void printProductDetails(JSONObject product, String size) {
        if (product != null) {
            System.out.println("Name: " + product.get("name"));
            System.out.println("Image: " + product.get("image"));

            ProductSizePrice(product);
            printPlatforms(product);
            printProductInventoryData(product, size);
            printOptions(product);
//            printToppings(product);
            printProductCategoryName(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void ProductSizePrice(JSONObject product) {
        System.out.println("Price: ");
        JSONArray priceArray = (JSONArray) product.get("price");
        for (Object priceObj : priceArray) {
            JSONObject price = (JSONObject) priceObj;
            System.out.println( price.get("priceName") + ": " + price.get("priceValue"));
        }
    }

    private static void printPlatforms(JSONObject product) {
        System.out.println("Platforms: ");
        JSONArray platformArray = (JSONArray) product.get("platforms");
        for (Object platformObj : platformArray) {
            JSONObject platform = (JSONObject) platformObj;
            System.out.println(" - " + platform.get("name"));
        }
    }

    private static void printProductInventoryData(JSONObject product, String size) {
        System.out.println("Product Inventory Data for size " + size + ": ");
        JSONArray productInventoryDataArray = (JSONArray) product.get("productInventoryData");

        for (Object productInventoryDataObj : productInventoryDataArray) {
            JSONObject productInventoryData = (JSONObject) productInventoryDataObj;
//            System.out.println("Checking inventory data: " + productInventoryData);
            if (size.equals(productInventoryData.get("size"))) {
                JSONArray listProductPriceMaterialsArray = (JSONArray) productInventoryData.get("listProductPriceMaterials");
                for (Object listProductPriceMaterialsObj : listProductPriceMaterialsArray) {
                    JSONObject materials = (JSONObject) listProductPriceMaterialsObj;
                    System.out.println(" - " + size + ": " + materials.get("material") + ", Quantity: " + materials.get("quantity"));
                }
            }
        }
    }


    public static JSONArray getProductQuantities(String productName, String size) {
        JSONArray resultArray = new JSONArray();
        JSONObject product = null;
        try {
            product = readProductDataFile(productName);
            JSONArray productInventoryDataArray = (JSONArray) product.get("productInventoryData");


            for (Object productInventoryDataObj : productInventoryDataArray) {
                JSONObject productInventoryData = (JSONObject) productInventoryDataObj;
                JSONArray listProductPriceMaterialsArray = (JSONArray) productInventoryData.get("listProductPriceMaterials");

                for (Object listProductPriceMaterialsObj : listProductPriceMaterialsArray) {
                    JSONObject materials = (JSONObject) listProductPriceMaterialsObj;
                    if (size.equals(materials.get("priceName"))) {
                        JSONObject quantityObject = new JSONObject();
                        quantityObject.put("material", materials.get("material"));
                        quantityObject.put("quantity", materials.get("quantity"));
                        resultArray.add(quantityObject);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return resultArray;
    }

    private static JSONArray printOptions(JSONObject product) {
        JSONArray optionArray = (JSONArray) product.get("option");
//        for (Object optionObj : optionArray) {
//            JSONObject option = (JSONObject) optionObj;
//        }
        return optionArray;
    }

//    private static String printToppings(JSONObject product,String topping) {
//        JSONArray toppingArray = (JSONArray) product.get("topping");
//        JSONObject toppingObject = null;
//        for (Object toppingObj : toppingArray) {
//            toppingObject = (JSONObject) toppingObj;
//            if (topping.equals(toppingObject)){
//                System.out.println(toppingObject);
//                return toppingObject;
//            }
//        }
//        return toppingObject;
//    }

    private static JSONObject printToppings(JSONObject product, String toppingName) {
        JSONArray toppingArray = (JSONArray) product.get("topping");
        for (Object toppingObj : toppingArray) {
            JSONObject toppingObject = (JSONObject) toppingObj;
            if (toppingName.equals(toppingObject.get("name"))) {
                return toppingObject;
            }
        }
        return null;
    }

    private static void printProductCategoryName(JSONObject product) {
        System.out.println("Product Category Name: " + product.get("productCategoryName"));
    }
}
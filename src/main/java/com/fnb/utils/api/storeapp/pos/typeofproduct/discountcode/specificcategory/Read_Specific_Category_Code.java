package com.fnb.utils.api.storeapp.pos.typeofproduct.discountcode.specificcategory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Read_Specific_Category_Code {
    //    public static final String FILE_PATH_PERCENT = "C:\\Users\\admin\\Documents\\Automation\\selenium\\src\\main\\java\\com\\fnb\\utils\\api\\storeapp\\pos\\typeofproduct\\discountcode\\specificproduct\\product_percent.json";
//    public static final String FILE_PATH_MONEY = "C:\\Users\\admin\\Documents\\Automation\\selenium\\src\\main\\java\\com\\fnb\\utils\\api\\storeapp\\pos\\typeofproduct\\discountcode\\specificproduct\\product_money.json";
    public static final String FILE_PATH_DISCOUNT_CODE_DATA = "C:\\Users\\admin\\Documents\\Automation\\selenium\\src\\main\\java\\com\\fnb\\utils\\api\\storeapp\\pos\\discountcode\\Data_DiscountCodeCategory.json";

    public static String readValuePercent(String key) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(FILE_PATH_DISCOUNT_CODE_DATA)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONObject promotion = (JSONObject) jsonObject.get("discountCode");

            Object value = promotion.get(key);
            if (value instanceof Long) {
                return String.format("%,d", value);
            } else if (value instanceof String) {
                return (String) value;
            } else {
                return value.toString();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readValueMoney(String key) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(FILE_PATH_DISCOUNT_CODE_DATA)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONObject promotion = (JSONObject) jsonObject.get("discountCode");

            Object value = promotion.get(key);
            if (value instanceof Long) {
                return String.format("%,d", value);
            } else if (value instanceof String) {
                return (String) value;
            } else {
                return value.toString();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String percentNumber = readValuePercent("percentNumber");
        String percentNumber1 = readValueMoney("maximumDiscountAmount");
        System.out.println("percentNumber: " + percentNumber);
        System.out.println("percentNumber: " + percentNumber1);
    }
}

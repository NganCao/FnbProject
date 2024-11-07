package com.fnb.utils.api.storeapp.pos.typeofproduct.onlyproduct;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadProductData {
    public static void main(String[] args) {
        String taxid = readProduct("price");
        String taxid1 = String.format("%,d", Long.parseLong(taxid)).replace('.', ',');
        String tax = String.valueOf(readProduct("price"));
        System.out.println(taxid1+"đ");
        System.out.println(tax+"123đ");
        String material = readProduct2("materials", "materialId");
        System.out.println(material);
        String material2 = readProduct2("materials", "quantity");
        System.out.println(material2);
    }
    public static final String FILE_PATH = "C:\\Users\\admin\\Documents\\Automation\\selenium\\src\\main\\java\\com\\fnb\\utils\\api\\storeapp\\pos\\typeofproduct\\onlyproduct\\Product.json";

    public static String readProduct(String key) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(FILE_PATH)) {

            JSONObject jsonObject  = (JSONObject) parser.parse(reader);
            return jsonObject.get(key).toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readProduct2(String key1, String key2) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(FILE_PATH)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray parents = (JSONArray) jsonObject.get(key1);

            for (Object obj : parents) {
                JSONObject material = (JSONObject) obj;
                Object value = material.get(key2);
                if (value != null) {
                    return value.toString();
                }
            }
            return null;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
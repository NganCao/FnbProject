package com.fnb.utils.api.posapp.pos.helpers.readjsonfile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadTopping {
    public static final String FILE_PATH_READ_TOPPING = "C:\\Users\\admin\\Documents\\Automation\\selenium\\resources\\data\\product\\topping.json";

    public static void main(String[] args) {
        String toppingName = "Thạch topping";
//        String toppingName = "Topping";
        String s = getQuantityTopping(toppingName);
        System.out.println(s);
        String s1 = getMaterialTopping(toppingName);;
        System.out.println(s1);
//            JSONObject toppingData = readToppingDataFile(toppingName);
//            if (toppingData != null) {
//                System.out.println("Quantity: " + toppingData.get("quantity"));
//                System.out.println("Material: " + toppingData.get("material"));
//            } else {
//                System.out.println("Không tìm thấy topping.");
//            }
    }

    public static String getQuantityTopping(String toppingName){
        try {
            JSONObject quantity = readToppingDataFile(toppingName);
            if (quantity != null) {
//                System.out.println("Quantity: " + quantity.get("quantity"));
                return String.valueOf(quantity.get("quantity"));
            } else {
                System.out.println("Không tìm thấy topping.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMaterialTopping(String toppingName){
        try {
            JSONObject material = readToppingDataFile(toppingName);
            if (material != null) {
//                System.out.println("Material: " + material.get("material"));
                return String.valueOf(material.get("material"));
            } else {
                System.out.println("Không tìm thấy topping.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject readToppingDataFile(String toppingName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(FILE_PATH_READ_TOPPING);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        reader.close();

        JSONArray toppingsArray = (JSONArray) jsonObject.get("topping");
        if (toppingsArray != null) {
            for (Object obj : toppingsArray) {
                JSONObject topping = (JSONObject) obj;
                if (toppingName.equals(topping.get("name"))) {
                    JSONArray productInventoryDataArray = (JSONArray) topping.get("productInventoryData");
                    if (productInventoryDataArray != null && !productInventoryDataArray.isEmpty()) {
                        return (JSONObject) productInventoryDataArray.get(0);
                    }
                }
            }
        }
        return null;
    }
}

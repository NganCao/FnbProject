package com.fnb.utils.api.posapp.pos.helpers.readjsonfile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadMaterial {
    public static final String FILE_PATH_MATERIAL = "C:\\Users\\admin\\Documents\\Automation\\selenium\\resources\\data\\inventory\\material.json";

    public static void main(String[] args) {
        String materialName = "bột cafe bao dùng k hết";
        String s = getQuantityMaterial(materialName);
        String s1 = getBranchName(materialName);
        System.out.println(s);
        System.out.println(s1);
    }

    public static String getQuantityMaterial(String materialName){
        try {
            JSONObject quantity = readMaterialDataFile(materialName);
            if (quantity != null) {
//                System.out.println("Quantity: " + quantity.get("quantity"));
                return String.valueOf(quantity.get("quantity"));
            } else {
                System.out.println("Không tìm thấy material.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBranchName(String materialName){
        try {
            JSONObject branch = readMaterialDataFile(materialName);
            if (branch != null) {
                return String.valueOf(branch.get("name"));
            } else {
                System.out.println("Không tìm thấy material.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject readMaterialDataFile(String materialName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(FILE_PATH_MATERIAL);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        reader.close();

        JSONArray materialsArray = (JSONArray) jsonObject.get("material");
        if (materialsArray != null) {
            for (Object obj : materialsArray) {
                JSONObject material = (JSONObject) obj;
                if (materialName.equals(material.get("name"))) {
                    JSONArray branchArray = (JSONArray) material.get("branch");
                    if (branchArray != null && !branchArray.isEmpty()) {
                        return (JSONObject) branchArray.get(0);
                    }
                }
            }
        }
        return null;
    }
}

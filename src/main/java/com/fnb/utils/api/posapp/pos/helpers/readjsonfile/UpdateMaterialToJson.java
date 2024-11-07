package com.fnb.utils.api.posapp.pos.helpers.readjsonfile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class UpdateMaterialToJson {
    private static final String FILE_PATH = "C:\\Users\\admin\\Documents\\Automation\\selenium\\resources\\data\\inventory\\materialUpdate.json";

    public static boolean updateMaterialQuantityToJson(String materialName, double quantity) {
        JSONParser parser = new JSONParser();
        try {
            // Read JSON file
            FileReader reader = new FileReader(FILE_PATH);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            reader.close();

            // Get materials array
            JSONArray materialsArray = (JSONArray) jsonObject.get("material");

            // Update quantity for the specified material
            boolean materialUpdated = false;
            for (Object materialObj : materialsArray) {
                JSONObject material = (JSONObject) materialObj;
                if (material.get("name").equals(materialName)) {
                    JSONArray branchesArray = (JSONArray) material.get("branch");
                    for (Object branchObj : branchesArray) {
                        JSONObject branch = (JSONObject) branchObj;
                        BigDecimal roundedQuantity;
                        if (quantity == 0) {
                            roundedQuantity = BigDecimal.ZERO;
                        } else {
                            roundedQuantity = BigDecimal.valueOf(quantity).setScale(2, RoundingMode.HALF_UP);
                        }
                        // Remove trailing .00 if the value is an integer
                        branch.put("quantity", roundedQuantity.stripTrailingZeros().toPlainString());
                    }
                    materialUpdated = true;
                    break;
                }
            }

            if (!materialUpdated) {
                System.out.println("Material not found in the JSON data.");
                return false;
            }

            // Convert JSONObject to Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            // Convert JSONObject to pretty printed JSON string
            String prettyJsonString = objectWriter.writeValueAsString(jsonObject);
            // Write data in JSON file
            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                writer.write(prettyJsonString);
            }
            return true;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        boolean result = updateMaterialQuantityToJson("Đá viên", 222);
        updateMaterialQuantityToJson("Thạch rau câu auto", 111);
        updateMaterialQuantityToJson("bột cafe bao dùng k hết", 333.22);
        System.out.println("Update successful: " + result);
    }
}
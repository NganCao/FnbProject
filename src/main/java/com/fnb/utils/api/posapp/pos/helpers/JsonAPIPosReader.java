package com.fnb.utils.api.posapp.pos.helpers;

import com.fnb.utils.api.posapp.pos.helpers.APIPosService;
import com.fnb.utils.helpers.JsonReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonAPIPosReader {
    private static APIPosService apiPosService;

    //all json file created by api
    private static String path = "src/main/java/com/fnb/utils/api/posapp/";

    public static Map<String, String> getDistanceReader(String fileName) {
        String configFile = path + fileName;
        JsonObject config = JsonReader.readConfigFile(configFile);
        JsonArray branches = config.getAsJsonArray("branchesByCustomerAddress");
        Map<String, String> distances = new HashMap<>();
        String distance;
        for (int i = 0; i < branches.size(); i++) {
            distance = branches.get(i).getAsJsonObject().get("distance").getAsString();
            float result = (float) (Math.round((Float.parseFloat(distance) / 1000) * 10.0) / 10.0);
            distances.put(branches.get(i).getAsJsonObject().get("branchName").getAsString(), String.valueOf(result));
        }
        return distances;
    }

    public static class Branch {
        private String branchName;
        private String branchAddress;

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getBranchAddress() {
            return branchAddress;
        }

        public void setBranchAddress(String branchAddress) {
            this.branchAddress = branchAddress;
        }

        public String getBranchDistance() {
            return branchDistance;
        }

        public void setBranchDistance(String branchDistance) {
            this.branchDistance = branchDistance;
        }

        private String branchDistance;

        @Override
        public String toString() {
            return "Name: \"" + branchName + "\"\nAddress: \"" + branchAddress + "\"\nDistance: \"" + branchDistance + "\"";
        }
    }

    public static List<Branch> getAllBranches(String fileName) {
        List<Branch> branches = new ArrayList<>();
        Branch branch;
        String configFile = path + fileName;
        String address;
        JsonObject config = JsonReader.readConfigFile(configFile);
        JsonArray branchesByCustomerAddress = config.getAsJsonArray("branchesByCustomerAddress");
        String distance;
        for (int i = 0; i < branchesByCustomerAddress.size(); i++) {
            branch = new Branch();
            //round distance and parse from float to string
            distance = branchesByCustomerAddress.get(i).getAsJsonObject().get("distance").getAsString().trim();
            float result = (float) (Math.round((Float.parseFloat(distance) / 1000) * 10.0) / 10.0);
            branch.setBranchName(branchesByCustomerAddress.get(i).getAsJsonObject().get("branchName").getAsString().trim());
            address = branchesByCustomerAddress.get(i).getAsJsonObject().get("branchAddress").getAsString().trim();
            if (address.contains("  ")) address = address.replace("  ", " ");
            branch.setBranchAddress(address);
            branch.setBranchDistance(String.valueOf(result));
            branches.add(branch);
        }
        return branches;
    }

    public static String getCurrencySymbol() {
        String configFile = path + "/" + apiPosService.getFilePath("storeConfig.json");
        JsonObject config = JsonReader.readConfigFile(configFile);
        return config.get("currencySymbol").getAsString();
    }

    public static String getCurrencyCode() {
        String configFile = path + "/" + apiPosService.getFilePath("storeConfig.json");
        JsonObject config = JsonReader.readConfigFile(configFile);
        String currencyCode = config.get("currencyCode").getAsString();
        if (currencyCode.equals("VND")) currencyCode = "VNĐ";
        return currencyCode;
    }
}

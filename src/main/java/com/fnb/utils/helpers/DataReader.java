package com.fnb.utils.helpers;

import dataObject.Crm.Customer;
import dataObject.Inventory.Material;
import dataObject.Inventory.MaterialCategory;
import dataObject.Inventory.Supplier;
import dataObject.Localization.AdminLocalization;
import dataObject.Localization.POSLocalization;
import dataObject.Localization.PosAppLocale;
import dataObject.Product.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dataObject.Store.AreaTable;
import dataObject.Store.Branch;
import dataObject.Store.Tax;
import org.openqa.selenium.json.Json;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DataReader {
    public static JsonObject readConfigFile(String configFile) {
        try {
            // Read the configuration file
            FileReader reader = new FileReader(configFile);
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T read(Class<T> dataType) {
        // Read the configuration file
        String configFile = null;

        if (dataType.equals(Product.class)) {
            configFile = "resources/data/product/productData.json";

        } else if (dataType.equals(ProductCategory.class)) {
            configFile = "resources/data/product/productCategory.json";

        } else if (dataType.equals(Options.class)) {
            configFile = "resources/data/product/option.json";

        } else if (dataType.equals(Material.class)) {
            configFile = "resources/data/inventory/material.json";

        } else if (dataType.equals(Topping.class)) {
            configFile = "resources/data/product/topping.json";

        } else if (dataType.equals(SpecificCombo.class)) {
            configFile = "resources/data/product/specificCombo.json";

        } else if (dataType.equals(Tax.class)) {
            configFile = "resources/data/store/tax.json";

        } else if (dataType.equals(Branch.class)) {
            configFile = "resources/data/store/branch.json";

        } else if (dataType.equals(MaterialCategory.class)) {
            configFile = "resources/data/inventory/materialCategories.json";

        } else if (dataType.equals(Supplier.class)) {
            configFile = "resources/data/inventory/supplier.json";

        } else if (dataType.equals(POSLocalization.class)) {
            configFile = "resources/localization/POS/en.json";
        }

        else if (dataType.equals(Customer.class)) {
            configFile = "resources/data/crm/customer.json";
        }

        else if (dataType.equals(AdminLocalization.class)) {
            configFile = "resources/localization/Admin/en.json";
        }

        else if (dataType.equals(AreaTable.class)) {
            configFile = "resources/data/store/area_table.json";
        }

        else if (dataType.equals(PosAppLocale.class)) {
            configFile = "resources/localization/PosApp/en.json";
        }

        JsonObject jsonObject = readConfigFile(configFile);
        // Create a Gson instance
        Gson gson = new Gson();

        String jsonData = null;
        try {
            jsonData = new String(Files.readAllBytes(Paths.get(configFile)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Parse JSON data into ProductData object
        T data = gson.fromJson(jsonData, dataType);

        return data;
    }
}

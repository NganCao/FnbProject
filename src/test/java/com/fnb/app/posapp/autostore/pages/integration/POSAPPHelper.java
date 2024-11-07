package com.fnb.app.posapp.autostore.pages.integration;

import com.fnb.app.posapp.autostore.pages.dashboard.DashboardPage.*;
import com.fnb.app.posapp.autostore.pages.order.OrderDetailPage.*;
import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class POSAPPHelper extends BaseSetup {
    AndroidDriver driver;
    public List<String> materialList;
    public static final String FILE_PATH = "billInformation.json";

    public POSAPPHelper(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void writeBillOrderFile(String fileName, List<ProductCart> productList, Customer customer, String paymentMethod, String subtotal, String totalItems, String discount, String totalAmount) {
        JsonObject billObject = new JsonObject();
        //customer
        billObject.addProperty("customer", customer.getName());
        billObject.addProperty("customer phone", customer.getPhone());
        billObject.addProperty("customer rank name", customer.getRankName());
        billObject.addProperty("customer rank discount", customer.getRankDiscount());
        JsonObject productObj;
        JsonArray productArrObj = new JsonArray();
        float total = 0;
        //product
        for (ProductCart pc : productList) {
            productObj = new JsonObject();
            productObj.addProperty("name", pc.getName());
            productObj.addProperty("size", pc.getSize());
            productObj.addProperty("quantity", pc.getQuantity());
            productObj.addProperty("price", pc.getPrice());
            productObj.addProperty("option", pc.getOption());
            productObj.addProperty("topping", pc.getTopping());
            productObj.addProperty("topping quantity", pc.getToppingQuantity());
            total = pc.getPrice() * pc.getQuantity();
            productObj.addProperty("product total", total);
            productArrObj.add(productObj);
        }
        System.out.println(111);
        billObject.add("products", productArrObj);
        //total amount
        billObject.addProperty("total items", totalItems);
        billObject.addProperty("subtotal", subtotal);
        billObject.addProperty("discount", discount);
        billObject.addProperty("payment method", paymentMethod);
        billObject.addProperty("total", totalAmount);

        String path = "src/main/java/com/fnb/utils/api/posapp/bill/" + fileName;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(path)) {
            // Write JSON object to file
            gson.toJson(billObject, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

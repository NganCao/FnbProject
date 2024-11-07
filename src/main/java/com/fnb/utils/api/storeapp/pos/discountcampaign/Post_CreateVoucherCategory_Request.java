package com.fnb.utils.api.storeapp.pos.discountcampaign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fnb.utils.helpers.Log;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.fnb.utils.api.storeapp.pos.discountcode.Get_GenerateCode_Request.generateCode;
import static com.fnb.utils.api.storeapp.pos.discountcode.Get_GenerateCode_Request.getCustomerAdminInfoToken;
import static com.fnb.utils.api.storeapp.pos.discountcampaign.Post_CreateVoucherTotal_Request.*;
import static io.restassured.RestAssured.given;

public class Post_CreateVoucherCategory_Request {

    public static final String FILE_PATH_DATA_VOUCHER_CATEGORY = "C:\\Users\\admin\\Documents\\Automation\\selenium\\src\\main\\java\\com\\fnb\\utils\\api\\storeapp\\pos\\discountcampaign\\Data_VoucherCategory.json";
    public static Response response;
    public static String name;
    public static String type;
    public static String endDate;
    public static String value;
    public static final String VOUCHER_TYPE = "Giảm giá đối với danh mục sản phẩm";
    public static final LocalDateTime currentDateTime = LocalDateTime.now();

    /**
     * @param voucherTypeId     = 0,1,2
     * @param isPercentDiscount = true, false
     * @return
     */
    public static String formatName(int voucherTypeId, boolean isPercentDiscount) {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        String type = Integer.toString(voucherTypeId);
        String percentString = Boolean.toString(isPercentDiscount);

        // Map to store discount code type descriptions
        Map<String, String> codeTypeDescriptions = new HashMap<>();
        codeTypeDescriptions.put("2true", "Voucher for specific category by % ");
        codeTypeDescriptions.put("2false", "Voucher for specific category by money ");
        // Generate discount code name based on voucherTypeId and isPercentDiscount
        String key = type + isPercentDiscount;
        name = codeTypeDescriptions.getOrDefault(key, "Voucher Discount Category ") + randomNumber;
        System.out.println(name);
        return name;
    }

    public static boolean WriteDataVoucherCategory(int voucherTypeId, boolean isPercentDiscount) {
        JSONParser parser = new JSONParser();
        String startDate = startDateFormat();
        String endDate = endDateFormat();
        name = formatName(voucherTypeId, isPercentDiscount);
        if (isPercentDiscount = true) {
            value = "30%";
        } else {
            value = "30,000đ";
        }
        try {
            // Read .json file
            FileReader reader = new FileReader(FILE_PATH_DATA_VOUCHER_CATEGORY);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            reader.close();
            // Chane data in .json file
            JSONObject voucher = (JSONObject) jsonObject.get("promotion");
            if (!isPercentDiscount) {
                voucher.put("percentNumber", 0);
            } else {
                voucher.put("percentNumber", 30);
            }
            voucher.put("name", name);
            voucher.put("voucherTypeId", voucherTypeId);
            voucher.put("isPercentDiscount", isPercentDiscount);
            voucher.put("maximumDiscountAmount", 30000);
            voucher.put("startDate", startDate);
            voucher.put("endDate", endDate);
            // Convert JSONObject to Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            // Convert JSONObject to pretty printed JSON string
            String prettyJsonString = objectWriter.writeValueAsString(jsonObject);
            // Write data in .json file
            FileWriter writer = new FileWriter(FILE_PATH_DATA_VOUCHER_CATEGORY);
            writer.write(prettyJsonString);
            writer.close();
            return true;
        } catch (IOException | ParseException e) {
            Log.fatal(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static void createVoucherCategoryByApi(int voucherTypeId, boolean isPercentDiscount) {
        //Read .json file
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        String token = getCustomerAdminInfoToken();
        System.out.println(token);
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/v3.4/promotion/create-promotion";
        try {
            boolean success = WriteDataVoucherCategory(voucherTypeId, isPercentDiscount);
            if (success) {
                System.out.println("File JSON is created!");
                Log.info("File JSON is created!");
                FileReader reader = new FileReader(FILE_PATH_DATA_VOUCHER_CATEGORY);
                jsonObject = (JSONObject) parser.parse(reader);
                response = given().baseUri(baseURI)
                        .header("Content-Type", "application/json")
                        .header("Authorization", token)
                        .when()
                        .body(jsonObject)
                        .post();
            } else {
                System.out.println("Error when create file JSON...");
                Log.error("Error when create file JSON...");
            }
        } catch (IOException | ParseException e) {
            Log.fatal(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String nameVoucherCategory() {
        return name;
    }

    public static String voucherCategoryType() {
        return VOUCHER_TYPE;
    }

    public static String endDateCategoryVoucher() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String formattedDateTime = "Hết hạn: " + currentDateTime.plusYears(1).format(formatter);
        return formattedDateTime;
    }

    public static String valueCategoryVoucher() {
        return value;
    }

    public static void main(String[] args) {
        createVoucherCategoryByApi(2, false);
    }

}

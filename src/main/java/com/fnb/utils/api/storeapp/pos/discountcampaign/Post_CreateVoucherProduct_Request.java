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

import static com.fnb.utils.api.storeapp.pos.discountcampaign.Post_CreateVoucherTotal_Request.endDateFormat;
import static com.fnb.utils.api.storeapp.pos.discountcampaign.Post_CreateVoucherTotal_Request.startDateFormat;
import static com.fnb.utils.api.storeapp.pos.discountcode.Get_GenerateCode_Request.getCustomerAdminInfoToken;
import static io.restassured.RestAssured.given;

public class Post_CreateVoucherProduct_Request {
    public static final String FILE_PATH_DATA_VOUCHER_PRODUCT = "C:\\Users\\admin\\Documents\\Automation\\selenium\\src\\main\\java\\com\\fnb\\utils\\api\\storeapp\\pos\\discountcampaign\\Data_VoucherProduct.json";
    public static Response response;
    public static String name;
    public static String type;
    public static String endDate;
    public static String value;
    public static final String VOUCHER_TYPE = "Giảm giá cho sản phẩm";
    public static LocalDateTime currentDateTime = LocalDateTime.now();

    /**
     * @param voucherTypeId     = 0,1,2
     * @param isPercentDiscount = true, false
     * @return
     */
    public static String formatName(int voucherTypeId, boolean isPercentDiscount) {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        String type = Integer.toString(voucherTypeId);
        // Map to store Voucher type descriptions
        Map<String, String> codeTypeDescriptions = new HashMap<>();
        codeTypeDescriptions.put("1true", "Voucher for specific product by % ");
        codeTypeDescriptions.put("1false", "Voucher for specific product by money ");
        // Generate voucher name based on voucherTypeId and isPercentDiscount
        String key = type + isPercentDiscount;
        name = codeTypeDescriptions.getOrDefault(key, "Voucher Discount") + randomNumber;
        return name;
    }

    public static boolean WriteDataVoucherProduct(int VoucherTypeId, boolean isPercentDiscount) {
        JSONParser parser = new JSONParser();
        String startDate = startDateFormat();
        String endDate = endDateFormat();
        name = formatName(VoucherTypeId, isPercentDiscount);
        if (isPercentDiscount = true) {
            value = "20%";
        } else {
            value = "20,000đ";
        }
        try {
            // Read .json file
            FileReader reader = new FileReader(FILE_PATH_DATA_VOUCHER_PRODUCT);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            reader.close();
            // Chane data in .json file
            JSONObject discountCode = (JSONObject) jsonObject.get("promotion");
            if (!isPercentDiscount) {
                discountCode.put("percentNumber", 0);
            } else {
                discountCode.put("percentNumber", 20);
            }
            discountCode.put("name", name);
            discountCode.put("VoucherTypeId", VoucherTypeId);
            discountCode.put("isPercentDiscount", isPercentDiscount);
            discountCode.put("maximumDiscountAmount", 20000);
            discountCode.put("startDate", startDate);
            discountCode.put("endDate", endDate);
            // Convert JSONObject to Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            // Convert JSONObject to pretty printed JSON string
            String prettyJsonString = objectWriter.writeValueAsString(jsonObject);
            // Write data in .json file
            FileWriter writer = new FileWriter(FILE_PATH_DATA_VOUCHER_PRODUCT);
            writer.write(prettyJsonString);
            writer.close();
            return true;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void createVoucherProductByApi(int voucherTypeId, boolean isPercentDiscount) {
        //Read .json file
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        String token = getCustomerAdminInfoToken();
        System.out.println(token);
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/v3.4/promotion/create-promotion";
        try {
            boolean success = WriteDataVoucherProduct(voucherTypeId, isPercentDiscount);
            if (success) {
                System.out.println("File JSON is created!");
                Log.info("File JSON is created!");
                FileReader reader = new FileReader(FILE_PATH_DATA_VOUCHER_PRODUCT);
                jsonObject = (JSONObject) parser.parse(reader);
                response = given().baseUri(baseURI)
                        .header("Content-Type", "application/json")
                        .header("Authorization", token)
                        .when()
                        .body(jsonObject)
                        .post();
                System.out.println(response.statusCode());
            } else {
                System.out.println("Error when create file JSON...");
                Log.error("Error when create file JSON...");
            }
        } catch (IOException | ParseException e) {
            Log.fatal(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String nameVoucherProduct() {
        return name;
    }

    public static String voucherProductType() {
        return VOUCHER_TYPE;
    }

    public static String endDateProductVoucher() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String formattedDateTime = "Hết hạn: " + currentDateTime.plusYears(1).format(formatter);
        return formattedDateTime;
    }

    public static String valueProductVoucher() {
        return value;
    }


    public static void main(String[] args) {
        createVoucherProductByApi(1, true);
    }
}

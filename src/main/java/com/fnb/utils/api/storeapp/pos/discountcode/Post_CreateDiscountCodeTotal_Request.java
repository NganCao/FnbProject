package com.fnb.utils.api.storeapp.pos.discountcode;

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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import java.util.HashMap;
import java.util.Map;

import static com.fnb.utils.api.storeapp.pos.discountcode.Get_GenerateCode_Request.generateCode;
import static com.fnb.utils.api.storeapp.pos.discountcode.Get_GenerateCode_Request.getCustomerAdminInfoToken;
import static io.restassured.RestAssured.given;

public class Post_CreateDiscountCodeTotal_Request {
    public static final String FILE_PATH_DATA_DISCOUNT_CODE = "C:\\Users\\admin\\Documents\\Automation\\selenium\\src\\main\\java\\com\\fnb\\utils\\api\\storeapp\\pos\\discountcode\\Data_DiscountCodeTotal.json";
    public static Response response;
    public static String name;
    public static String code;
    public static String type;
    public static String value;
    public static final String UTC_ZONE = "UTC";
    public static final String VN_ZONE = "Asia/Ho_Chi_Minh";
    public static final String GMT_ZONE = "GMT";
    public static final String DISCOUNT_TYPE = "Giảm giá trên tổng hóa đơn";
    public static final LocalDateTime currentDateTime = LocalDateTime.now();

    public static String startDateFormat() {
        LocalDateTime dateTimePlusOneMinute = currentDateTime.plusMinutes(1);
        ZoneId vnZoneId = ZoneId.of(VN_ZONE);
        ZonedDateTime vnZonedDateTime = ZonedDateTime.of(dateTimePlusOneMinute, vnZoneId);
        ZoneId gmtZoneId = ZoneId.of(GMT_ZONE);
        ZonedDateTime gmtZonedDateTime = vnZonedDateTime.withZoneSameInstant(gmtZoneId);
        return gmtZonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String endDateFormat() {
        LocalDateTime dateTimePlusOneMinute = currentDateTime.plusYears(1);
        ZoneId vnZoneId = ZoneId.of(VN_ZONE);
        ZonedDateTime vnZonedDateTime = ZonedDateTime.of(dateTimePlusOneMinute, vnZoneId);
        ZoneId gmtZoneId = ZoneId.of(GMT_ZONE);
        ZonedDateTime gmtZonedDateTime = vnZonedDateTime.withZoneSameInstant(gmtZoneId);
        return gmtZonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    public static String startTimeFormat() {
        // Get current time +1 minute
        LocalDateTime dateTimePlusOneMinute = currentDateTime.plusMinutes(1);
        ZoneId vnZoneId = ZoneId.of(VN_ZONE);
        ZonedDateTime vnZonedDateTime = ZonedDateTime.of(dateTimePlusOneMinute, vnZoneId);
        // convert time to UTC
        ZonedDateTime utcZonedDateTime = vnZonedDateTime.withZoneSameInstant(ZoneId.of(UTC_ZONE));
        // Format the ZonedDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String formattedDateTime = utcZonedDateTime.format(formatter);

        return formattedDateTime;
    }

    public static String endTimeFormat() {
        LocalDateTime dateTimePlusOneMinute = currentDateTime.plusYears(1);
        ZoneId vnZoneId = ZoneId.of(VN_ZONE);
        ZonedDateTime vnZonedDateTime = ZonedDateTime.of(dateTimePlusOneMinute, vnZoneId);
        ZonedDateTime utcZonedDateTime = vnZonedDateTime.withZoneSameInstant(ZoneId.of(UTC_ZONE));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String formattedDateTime = utcZonedDateTime.format(formatter);
        return formattedDateTime;
    }


    /**
     * @param discountCodeTypeId = 0,1,2
     * @param isPercentDiscount  = true, false
     * @return
     */
    public static String formatName(int discountCodeTypeId, boolean isPercentDiscount) {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        String type = Integer.toString(discountCodeTypeId);
        String percentString = Boolean.toString(isPercentDiscount);

        // Map to store discount code type descriptions
        Map<String, String> codeTypeDescriptions = new HashMap<>();
        codeTypeDescriptions.put("0true", "Discount code for total bill by % ");
        codeTypeDescriptions.put("0false", "Discount code for total bill by money ");
        // Generate discount code name based on discountCodeTypeId and isPercentDiscount
        String key = type + isPercentDiscount;
        name = codeTypeDescriptions.getOrDefault(key, "Discount code ") + randomNumber;
        return name;
    }

    public static boolean WriteDataDiscountCode(int discountCodeTypeId, boolean isPercentDiscount) {
        JSONParser parser = new JSONParser();
        code = generateCode();
        String startDate = startDateFormat();
        String startTime = startTimeFormat();
        String endDate = endDateFormat();
        String endTime = endTimeFormat();
        name = formatName(discountCodeTypeId, isPercentDiscount);
        if (isPercentDiscount = true) {
            value = "10%";
        } else {
            value = "10,000đ";
        }
        try {
            // Read .json file
            FileReader reader = new FileReader(FILE_PATH_DATA_DISCOUNT_CODE);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            reader.close();
            // Chane data in .json file
            JSONObject discountCode = (JSONObject) jsonObject.get("discountCode");
            if (!isPercentDiscount) {
                discountCode.put("percentNumber", 0);
            } else {
                discountCode.put("percentNumber", 10);
            }
            discountCode.put("name", name);
            discountCode.put("discountCodeTypeId", discountCodeTypeId);
            discountCode.put("isPercentDiscount", isPercentDiscount);
            discountCode.put("maximumDiscountAmount", 10000);
            discountCode.put("code", code);
            discountCode.put("startDate", startDate);
            discountCode.put("startTime", startTime);
            discountCode.put("endDate", endDate);
            discountCode.put("endTime", endTime);
            discountCode.put("termsAndCondition", "Total discount on order");
            // Convert JSONObject to Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();

            // Convert JSONObject to pretty printed JSON string
            String prettyJsonString = objectWriter.writeValueAsString(jsonObject);

            // Write data in .json file
            FileWriter writer = new FileWriter(FILE_PATH_DATA_DISCOUNT_CODE);
            writer.write(prettyJsonString);
            writer.close();

            return true;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void createDiscountCodeTotalBillByApi(int discountCodeTypeId, boolean isPercentDiscount) {
        //Read .json file
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        String token = getCustomerAdminInfoToken();
        System.out.println(token);
//        String name = formatName(discountCodeTypeId, isPercentDiscount);
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/v3.4/discountCode/create-discount-code";
        try {
            boolean success = WriteDataDiscountCode(discountCodeTypeId, isPercentDiscount);
            if (success) {
                System.out.println("File JSON is created!");
                Log.info("File JSON is created!");
                FileReader reader = new FileReader(FILE_PATH_DATA_DISCOUNT_CODE);
                jsonObject = (JSONObject) parser.parse(reader);
                response = given().baseUri(baseURI)
                        .header("Content-Type", "application/json")
                        .header("Authorization", token)
                        .when()
                        .body(jsonObject)
                        .post();
                System.out.println(response.statusCode());
                System.out.println("Created Discount code: " + name + " successfully !");
                Log.info("Created Discount code: " + name + " successfully !");
            } else {
                System.out.println("Error when create file JSON...");
                Log.error("Error when create file JSON...");
            }
        } catch (IOException | ParseException e) {
            Log.fatal(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String nameDiscount() {
        return name;
    }

    public static String discountCode() {
        return code;
    }

    public static String discountCodeTotalType() {
        return DISCOUNT_TYPE;
    }

    public static String endDateTotalDiscount() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd, YYYY - HH:mm");
        String formattedDateTime = "Hết hạn: Thg "+ currentDateTime.plusYears(1).format(formatter);
        return formattedDateTime;
    }
    public static String endDateTotalDiscountDetail() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd, YYYY - HH:mm");
        String formattedDateTime = "Hết hạn: Thg "+currentDateTime.plusYears(1).format(formatter);
        return formattedDateTime;
    }
    public static String value(){
        return value;
    }

    public static void main(String[] args) {
//        createDiscountCodeTotalBillByApi(0, false);
        String s = endDateTotalDiscount();
        System.out.println(s);
    }

}

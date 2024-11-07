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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.fnb.utils.api.storeapp.pos.discountcode.Get_GenerateCode_Request.getCustomerAdminInfoToken;
import static io.restassured.RestAssured.given;

public class Post_CreateVoucherTotal_Request {
    public static final String FILE_PATH_DATA_VOUCHER = "C:\\Users\\admin\\Documents\\Automation\\selenium\\src\\main\\java\\com\\fnb\\utils\\api\\storeapp\\pos\\discountcampaign\\Data_VoucherTotal.json";
    public static Response response;
    public static String name;
    public static String type;
    public static String endDate;
    public static String value;
    public static final String UTC_ZONE = "UTC";
    public static final String VN_ZONE = "Asia/Ho_Chi_Minh";
    public static final String GMT_ZONE = "GMT";
    public static final String VOUCHER_TYPE = "Giảm giá trên tổng hóa đơn";
    public static final LocalDateTime currentDateTime = LocalDateTime.now();

    public static String startDateFormat() {
        LocalDateTime dateTimePlusOneMinute = currentDateTime;
        ZoneId vnZoneId = ZoneId.of(VN_ZONE);
        ZonedDateTime vnZonedDateTime = ZonedDateTime.of(dateTimePlusOneMinute, vnZoneId);
        ZoneId gmtZoneId = ZoneId.of(GMT_ZONE);
        ZonedDateTime gmtZonedDateTime = vnZonedDateTime.withZoneSameInstant(gmtZoneId);
//        return gmtZonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return gmtZonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));
    }

    public static String endDateFormat() {
        LocalDateTime dateTimePlusOneMinute = currentDateTime.plusYears(1);
        ZoneId vnZoneId = ZoneId.of(VN_ZONE);
        ZonedDateTime vnZonedDateTime = ZonedDateTime.of(dateTimePlusOneMinute, vnZoneId);
        ZoneId gmtZoneId = ZoneId.of(GMT_ZONE);
        ZonedDateTime gmtZonedDateTime = vnZonedDateTime.withZoneSameInstant(gmtZoneId);
//        return gmtZonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return gmtZonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));
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
     * @param VoucherTypeId     = 0,1,2
     * @param isPercentDiscount = true, false
     * @return
     */
    public static String formatName(int VoucherTypeId, boolean isPercentDiscount) {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        String type = Integer.toString(VoucherTypeId);
        String percentString = Boolean.toString(isPercentDiscount);

        // Map to store discount code type descriptions
        Map<String, String> codeTypeDescriptions = new HashMap<>();
        codeTypeDescriptions.put("0true", "Voucher for total bill by % ");
        codeTypeDescriptions.put("0false", "Voucher for total bill by money ");
        // Generate discount code name based on VoucherTypeId and isPercentDiscount
        String key = type + isPercentDiscount;
        name = codeTypeDescriptions.getOrDefault(key, "Voucher Discount Total bill ") + randomNumber;
        System.out.println(name);
        return name;
    }

    public static boolean WriteDataVoucher(int VoucherTypeId, boolean isPercentDiscount) {
        JSONParser parser = new JSONParser();
        String startDate = startDateFormat();
        String endDate = endDateFormat();
        name = formatName(VoucherTypeId, isPercentDiscount);
        if (isPercentDiscount = true) {
            value = "10%";
        } else {
            value = "10,000đ";
        }
        try {
            // Read .json file
            FileReader reader = new FileReader(FILE_PATH_DATA_VOUCHER);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            reader.close();
            // Chane data in .json file
            JSONObject voucher = (JSONObject) jsonObject.get("promotion");
            if (!isPercentDiscount) {
                voucher.put("percentNumber", 0);
            } else {
                voucher.put("percentNumber", 10);
            }
            voucher.put("name", name);
            voucher.put("VoucherTypeId", VoucherTypeId);
            voucher.put("isPercentDiscount", isPercentDiscount);
            voucher.put("maximumDiscountAmount", 10000);
            voucher.put("startDate", startDate);
            voucher.put("endDate", endDate);
            // Convert JSONObject to Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            // Convert JSONObject to pretty printed JSON string
            String prettyJsonString = objectWriter.writeValueAsString(jsonObject);
            // Write data in .json file
            FileWriter writer = new FileWriter(FILE_PATH_DATA_VOUCHER);
            writer.write(prettyJsonString);
            writer.close();
            return true;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void createVoucherTotalBillByApi(int voucherTypeId, boolean isPercentDiscount) {
        //Read .json file
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        String token = getCustomerAdminInfoToken();
        System.out.println(token);
        String baseURI = "https://api.stag-admin.beecowdeal.vn/api/v3.4/promotion/create-promotion";
        try {
            boolean success = WriteDataVoucher(voucherTypeId, isPercentDiscount);
            if (success) {
                System.out.println("File JSON is created!");
                Log.info("File JSON is created!");
                FileReader reader = new FileReader(FILE_PATH_DATA_VOUCHER);
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
            e.printStackTrace();
        }
    }

    public static String nameVoucher() {
        return name;
    }

    public static String voucherTotalType() {
        return VOUCHER_TYPE;
    }

    public static String endDateTotalVoucher() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String formattedDateTime = "Hết hạn: " + currentDateTime.plusYears(1).format(formatter);
        return formattedDateTime;
    }

    public static String valueTotalVoucher() {
        return value;
    }

    public static void main(String[] args) {
//        createVoucherTotalBillByApi(0, false);
        String s = endDateTotalVoucher();
        System.out.println(s);
    }

}

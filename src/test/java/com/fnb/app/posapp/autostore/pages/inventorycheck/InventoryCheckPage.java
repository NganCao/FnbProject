package com.fnb.app.posapp.autostore.pages.inventorycheck;

import com.fnb.app.posapp.autostore.pages.dashboard.DashboardPage;
import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fnb.utils.api.posapp.pos.helpers.readjsonfile.ReadOption.*;
import static com.fnb.utils.api.posapp.pos.helpers.readjsonfile.ReadProductData.*;
import static com.fnb.utils.api.posapp.pos.helpers.readjsonfile.ReadTopping.*;

public class InventoryCheckPage extends BaseSetup {
    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;
    static DashboardPage dashboardPage;
    InventoryCheckPage inventoryCheckPage;

    public InventoryCheckPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        dashboardPage = navigateToAutoStore().navigateToDashboard();
//        inventoryCheckPage = navigateToAutoStore().navigateToInventoryCheckPage();
        PageFactory.initElements(driver, this);
    }

    public static void main(String[] args) {
        // Tạo danh sách các đối tượng ProductCart
        List<DashboardPage.ProductCart> productCartList = new ArrayList<>();
        productCartList.add(new DashboardPage.ProductCart("Vịt thơm - all", "M", 82000.0f, 1, "", "Đá viên (Vừa)", "Thạch topping"));
        productCartList.add(new DashboardPage.ProductCart("Vịt thơm - size", "S", 35000.0f, 1, "", "", ""));
        productCartList.add(new DashboardPage.ProductCart("Mê Sữa Đá", "", 39000.0f, 1, "", "", ""));

        String product = "Vịt thơm - all";

        // Gọi hàm processProductDetails
//        processProductDetails(productCartList, productName);
//        String s = getProductName(productCartList, productName);
        String productName = getProductName(productCartList, product);
        String productSize = getProductSize(productCartList, product);
        String productTopping = getProductTopping(productCartList, product);
        String productOption = getProductOptions(productCartList, product);
        String productQuantity = getProductQuantity(productCartList, product);
        System.out.println(getProductName(productCartList, product));
        System.out.println(getProductSize(productCartList, product));
        System.out.println(getProductTopping(productCartList, product));
        System.out.println(getProductOptions(productCartList, product));
        System.out.println(getProductQuantity(productCartList, product));
        //========================
        JSONArray materialsArray = getProductQuantities(productName, productSize);
        System.out.println("===========option=================");
        System.out.println(getQuotaOption(productOption));
        String s = getQuotaOption(productOption);
        System.out.println(getFirstPart(productOption));

        String s1 = getFirstPart(productOption);
        String s2 = calculateOptionProduct(materialsArray, s1, s);
        System.out.println(calculateOptionProduct(materialsArray, s1, s));
        //update quantity material
//        modifyMaterialsAndAddNew(materialsArray, s1, s2);
        modifyMaterials(materialsArray, s1, s2);
        System.out.println(materialsArray.toJSONString());
        System.out.println("============topping================");
        String quantityTopping = getQuantityTopping(productTopping);
//        String quantityTopping = "12";
        System.out.println(quantityTopping);
        String materialTopping = getMaterialTopping(productTopping);
//        String materialTopping = "NVL12";
        System.out.println(materialTopping);
        //=========================
        //Get Product Data

        //Return material and quantity using for product
//        String s = String.valueOf(getProductQuantities(productName, productSize));
//        System.out.println(s);


        //TODO: NVL and quantity
//        String materialName = "Đá viên";
//        JSONObject daVienObject = getMaterialObject(materialsArray, materialName);
//        System.out.println(daVienObject);
//
//        // Lấy giá trị quantity của vật liệu "Đá viên"
//        String daVienQuantity = getMaterialQuantity(daVienObject, materialName);
//        System.out.println("Quantity of " + materialName + ": " + daVienQuantity);
        //================================
        // Gọi hàm để sửa đổi materialsArray và thêm vật liệu mới
        modifyMaterialsAndAddNew(materialsArray, materialTopping, quantityTopping);

        // In mảng đã sửa đổi
        System.out.println(materialsArray.toJSONString());
        System.out.println("=======calculateMaterialUsing===========");
        JSONArray jsonArray = calculateMaterialUsing(productCartList, "Vịt thơm - all", "Vịt thơm - size", "Mê Sữa Đá");
        String o = calculateMaterialUsing(productCartList, "Vịt thơm - all", "Vịt thơm - size", "Mê Sữa Đá").toJSONString();
        System.out.println(o);
        System.out.println("======updateQuantity=======");
        System.out.println(updateQuantity(jsonArray, "Đá viên", 999.23333));
        System.out.println("========updateMaterialQuantityToJson=====");
        System.out.println(updateMaterialQuantityToJson(jsonArray, "Đá viên", 999.23333));
    }

    public static double getMaterialQuantity(JSONArray jsonArray, String materialName) {
        for (Object materialObj : jsonArray) {
            JSONObject material = (JSONObject) materialObj;
            if (material.get("material").equals(materialName)) {
                return Double.parseDouble((String) material.get("quantity"));
            }
        }
        return 0; // Return 0 if the material is not found
    }

    private static JSONArray updateMaterialQuantityToJson(JSONArray jsonArray, String material, double quantity) {
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            String materialInArray = (String) jsonObject.get("material");

            if (materialInArray.equals(material)) {
                // Use BigDecimal to handle quantity updates and rounding
                BigDecimal newQuantity = new BigDecimal(quantity);

                // Round to two decimal places if the updated quantity is not zero
                if (newQuantity.compareTo(BigDecimal.ZERO) != 0) {
                    newQuantity = newQuantity.setScale(2, RoundingMode.HALF_UP);
                }

                // Set the quantity string appropriately based on the updated quantity
                String updatedQuantityStr = newQuantity.compareTo(BigDecimal.ZERO) == 0 ? "0" : newQuantity.toString();
                if (updatedQuantityStr.endsWith(".00")) {
                    updatedQuantityStr = updatedQuantityStr.substring(0, updatedQuantityStr.length() - 3);
                }

                jsonObject.put("quantity", updatedQuantityStr);
                break;
            }
        }
        return jsonArray;
    }

    public static JSONArray updateQuantity(JSONArray jsonArray, String material, double quantity) {
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            String materialInArray = (String) jsonObject.get("material");

            if (materialInArray.equals(material)) {
                BigDecimal quantityInArray = new BigDecimal((String) jsonObject.get("quantity"));
                BigDecimal inputQuantity = new BigDecimal(quantity);
                BigDecimal updatedQuantity = inputQuantity.subtract(quantityInArray);

                if (updatedQuantity.compareTo(BigDecimal.ZERO) < 0) {
                    updatedQuantity = BigDecimal.ZERO;
                }

                // Round to two decimal places if the updated quantity is not zero
                if (updatedQuantity.compareTo(BigDecimal.ZERO) != 0) {
                    updatedQuantity = updatedQuantity.setScale(2, RoundingMode.HALF_UP);
                }

                // Set the quantity string appropriately based on the updated quantity
                jsonObject.put("quantity", updatedQuantity.compareTo(BigDecimal.ZERO) == 0 ? "0" : updatedQuantity.toString());
                break;
            }
        }
        return jsonArray;
    }

    public static JSONArray calculateMaterialUsing(List<DashboardPage.ProductCart> productCartList, String product1, String product2, String product3) {
        //handle data for product1: Vịt thơm - all
        String productName1 = getProductName(productCartList, product1);
        String productSize1 = getProductSize(productCartList, product1);
        String productTopping1 = getProductTopping(productCartList, product1);
        String productOption1 = getProductOptions(productCartList, product1);
        String productQuantity1 = getProductQuantity(productCartList, product1);
        JSONArray materialsArray = getProductQuantities(productName1, productSize1);
        //===========option for product1================="
        String quotaOption = getQuotaOption(productOption1);

        String firstPart = getFirstPart(productOption1);
        String calculateOptionProduct = calculateOptionProduct(materialsArray, firstPart, quotaOption);
        System.out.println("calculateOptionProduct111"+ calculateOptionProduct);
        modifyMaterials(materialsArray, firstPart, calculateOptionProduct);
        //============topping================
        String quantityTopping = getQuantityTopping(productTopping1);
        String materialTopping = getMaterialTopping(productTopping1);
        modifyMaterialsAndAddNew(materialsArray, materialTopping, quantityTopping);
        System.out.println(materialsArray.toJSONString());
        //handle data for product2:
        String productName2 = getProductName(productCartList, product2);
        String productSize2 = getProductSize(productCartList, product2);
        String productTopping2 = getProductTopping(productCartList, product2);
        String productOption2 = getProductOptions(productCartList, product2);
        String productQuantity2 = getProductQuantity(productCartList, product2);

        JSONArray materialsArray2 = getProductQuantities(productName2, productSize2);
        System.out.println("materialsArray2" + materialsArray2);
//        modifyMaterials();
        //ToDO: handle for Đá viên
//        modifyMaterials(materialsArray2, );
        //handle data for product3:
        String productName3 = getProductName(productCartList, product3);
        String productSize3 = getProductSize(productCartList, product3);
        String productTopping3 = getProductTopping(productCartList, product3);
        String productOption3 = getProductOptions(productCartList, product3);
        String productQuantity3 = getProductQuantity(productCartList, product3);
        JSONArray materialsArray3 = getProductQuantities(productName3, productSize3);
//        System.out.println("materialsArray3"+materialsArray3);
        JSONParser parser = new JSONParser();

        //            JSONArray jsonArray1 = (JSONArray) parser.parse(jsonArray1Str);
//            JSONArray jsonArray2 = (JSONArray) parser.parse(jsonArray2Str);
//            JSONArray materialsArray3 = (JSONArray) parser.parse(materialsArray3Str);

        mergeQuantities(materialsArray, materialsArray2);
        System.out.println("materialsArray.toJSONString()1111111111"+materialsArray.toJSONString());
        mergeQuantities(materialsArray, materialsArray3);

        System.out.println("materialsArray.toJSONString()22222222222"+materialsArray.toJSONString());
        return materialsArray;
    }

    private static void mergeQuantities(JSONArray targetArray, JSONArray sourceArray) {
        for (Object obj2 : sourceArray) {
            JSONObject jsonObject2 = (JSONObject) obj2;
            String material2 = (String) jsonObject2.get("material");
            int quantity2 = Integer.parseInt((String) jsonObject2.get("quantity"));

            boolean materialFound = false;
            for (Object obj1 : targetArray) {
                JSONObject jsonObject1 = (JSONObject) obj1;
                String material1 = (String) jsonObject1.get("material");

                if (material1.equals(material2)) {
                    int quantity1 = Integer.parseInt((String) jsonObject1.get("quantity"));
                    jsonObject1.put("quantity", String.valueOf(quantity1 + quantity2));
                    materialFound = true;
                    break;
                }
            }

            // If the material from sourceArray is not found in targetArray, add it
            if (!materialFound) {
                targetArray.add(jsonObject2);
            }
        }
    }

    private static String calculateOptionProduct(JSONArray materialsArray, String material, String quota) {
        JSONObject daVienObject = getMaterialObject(materialsArray, material);
        //update quantity of material
        String daVienQuantity = getMaterialQuantity(daVienObject, material);
        double quotaDouble = Double.parseDouble(quota);
        double quantityDouble = Double.parseDouble(daVienQuantity);
        double quantity = quantityDouble * (quotaDouble / 100);
        System.out.println("quantity11111" + quantity);
        BigDecimal roundedQuantity = new BigDecimal(quantity).setScale(2, RoundingMode.HALF_UP);
        String quantityResult = String.valueOf(roundedQuantity.doubleValue());
        double resultQuantity = roundedQuantity.doubleValue();
        if (resultQuantity < 0.01) {
            return "0";
        } else if (resultQuantity % 1 == 0) {
            System.out.println(resultQuantity);
            return String.valueOf((int) resultQuantity);
        } else {
            System.out.println(resultQuantity);
            System.out.println(resultQuantity);
            return String.valueOf(resultQuantity);
        }

    }

    private static void modifyMaterialsAndAddNew(JSONArray materialsArray, String material, String quantity) {
        // Tạo bản đồ điều chỉnh
        Map<String, Integer> adjustments = new HashMap<>();
//        adjustments.put("Đá viên", 5); // Tăng số lượng thêm 5
//        adjustments.put("bột cafe bao dùng k hết", -6); // Giảm số lượng 6

        // Sửa đổi mảng materialsArray dựa trên các điều chỉnh
//        applyAdjustments(materialsArray, adjustments);

        // Thêm vật liệu mới
//        materialsArray.add(createMaterialObject("bột auto", "5"));
        materialsArray.add(createMaterialObject(material, quantity));
    }

    private static void modifyMaterials(JSONArray materialsArray, String material, String quantity) {
        // Tạo bản đồ điều chỉnh
        Map<String, Integer> adjustments = new HashMap<>();
//        adjustments.put("Đá viên", 5); // Tăng số lượng thêm 5
//        adjustments.put("bột cafe bao dùng k hết", -6); // Giảm số lượng 6
        adjustments.put(material, Integer.parseInt(quantity));

        // Sửa đổi mảng materialsArray dựa trên các điều chỉnh
        applyAdjustments(materialsArray, adjustments);
    }

    private static JSONObject createMaterialObject(String material, String quantity) {
        JSONObject materialObject = new JSONObject();
        materialObject.put("material", material);
        materialObject.put("quantity", quantity);
        return materialObject;
    }
    //Input quantity and degree
    private static void applyAdjustments(JSONArray materialsArray, Map<String, Integer> adjustments) {
        for (Object obj : materialsArray) {
            JSONObject materialObject = (JSONObject) obj;
            String materialName = (String) materialObject.get("material");

            if (adjustments.containsKey(materialName)) {
                int currentQuantity = Integer.parseInt((String) materialObject.get("quantity"));
                int adjustmentValue = Math.abs(adjustments.get(materialName));
                if (currentQuantity > adjustmentValue) {
//                    adjustmentValue = currentQuantity - adjustmentValue;
                    adjustmentValue = adjustmentValue;
                } else adjustmentValue = 0;
                materialObject.put("quantity", String.valueOf(adjustmentValue));
            }
        }
    }
    //Input quantity and update to Json
    private static void applyAdjustments1(JSONArray materialsArray, Map<String, Integer> adjustments) {
        for (Object obj : materialsArray) {
            JSONObject materialObject = (JSONObject) obj;
            String materialName = (String) materialObject.get("material");

            if (adjustments.containsKey(materialName)) {
                int currentQuantity = Integer.parseInt((String) materialObject.get("quantity"));
                int adjustmentValue = Math.abs(adjustments.get(materialName));
                if (currentQuantity > adjustmentValue) {
                    adjustmentValue = currentQuantity;
                } else adjustmentValue = 0;
                materialObject.put("quantity", String.valueOf(adjustmentValue));
            }
        }
    }

    private static JSONObject getMaterialObject(JSONArray materialsArray, String materialName) {
        for (Object obj : materialsArray) {
            JSONObject materialObject = (JSONObject) obj;
            if (materialName.equals(materialObject.get("material"))) {
                return materialObject;
            }
        }
        return null; // Nếu không tìm thấy vật liệu nào khớp
    }

    private static String getMaterialQuantity(JSONObject materialObject, String materialName) {
        if (materialObject != null && materialName.equals(materialObject.get("material"))) {
            return (String) materialObject.get("quantity");
        }
        return null; // Nếu không tìm thấy quantity hoặc vật liệu không khớp
    }

    public static void processProductDetails(List<DashboardPage.ProductCart> productCartList, String productName) {
        DashboardPage.productCartList = productCartList;
//        System.out.println(productCartList);

        DashboardPage.ProductCart product = getProductDetails(productCartList, productName);

        // Kiểm tra và in ra thông tin của sản phẩm
        if (product != null) {
            System.out.println("Name: " + product.getName());
            System.out.println("Size: " + product.getSize());
            System.out.println("Topping: " + product.getTopping());
            System.out.println("Option: " + product.getOption());
            System.out.println("Quantity: " + product.getQuantity());
        } else {
            System.out.println("Không tìm thấy sản phẩm với tên '" + productName + "'.");
        }
    }

    public static String getProductName(List<DashboardPage.ProductCart> productCartList, String productName) {
        DashboardPage.productCartList = productCartList;

        DashboardPage.ProductCart product = getProductDetails(productCartList, productName);
        if (product != null) {
            return product.getName();
        } else System.out.println("Không tìm thấy sản phẩm với tên '" + productName + "'.");
        return null;
    }

    public static String getProductSize(List<DashboardPage.ProductCart> productCartList, String productName) {
        DashboardPage.productCartList = productCartList;

        DashboardPage.ProductCart product = getProductDetails(productCartList, productName);
        if (product != null) {
            return product.getSize();
        } else System.out.println("Không tìm thấy size sản phẩm với tên '" + productName + "'.");
        return null;
    }

    public static String getProductTopping(List<DashboardPage.ProductCart> productCartList, String productName) {
        DashboardPage.productCartList = productCartList;

        DashboardPage.ProductCart product = getProductDetails(productCartList, productName);
        if (product != null) {
            return product.getTopping();
        } else System.out.println("Không tìm thấy topping sản phẩm với tên '" + productName + "'.");
        return null;
    }

    public static String getProductOptions(List<DashboardPage.ProductCart> productCartList, String productName) {
        DashboardPage.productCartList = productCartList;

        DashboardPage.ProductCart product = getProductDetails(productCartList, productName);
        if (product != null) {
            return product.getOption();
        } else System.out.println("Không tìm thấy option sản phẩm với tên '" + productName + "'.");
        return null;
    }

    public static String getProductQuantity(List<DashboardPage.ProductCart> productCartList, String productName) {
        DashboardPage.productCartList = productCartList;

        DashboardPage.ProductCart product = getProductDetails(productCartList, productName);
        if (product != null) {
            return String.valueOf(product.getQuantity());
        } else System.out.println("Không tìm thấy số lượng sản phẩm với tên '" + productName + "'.");
        return null;
    }

    public static DashboardPage.ProductCart getProductDetails(List<DashboardPage.ProductCart> productCartList, String productName) {
        for (DashboardPage.ProductCart product : productCartList) {
            if (productName.equals(product.getName())) {
                return product;
            }
        }
        return null;
    }
}

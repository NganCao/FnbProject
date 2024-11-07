package com.fnb.utils.api.storeweb.admin.helpers;

import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.*;

public class JsonAPIAdminReader {
    private static String path = "src/main/java/com/fnb/utils/api/storeweb/";
    private static String typeAPI = "admin";
    private static APIAminService apiAminService;

    //product
    public static Product getProductBySearching(String filePath, String productName) {
        String configFile = path + filePath;
        JsonObject config = JsonReader.readConfigFile(configFile);
        JsonArray products = config.getAsJsonArray("products");
        JsonArray productPriceJS;
        Product product = new Product();
        ProductPrices productPrices;
        List<ProductPrices> priceList = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getAsJsonObject().get("name").getAsString().trim().equalsIgnoreCase(productName.trim())) {
                product = new Product();
                product.setId(products.get(i).getAsJsonObject().get("id").getAsString());
                product.setName(products.get(i).getAsJsonObject().get("name").getAsString());
                product.setActive(products.get(i).getAsJsonObject().get("isActive").getAsBoolean());
                product.setThumbnail(products.get(i).getAsJsonObject().get("thumbnail").getAsString());
                productPriceJS = products.get(i).getAsJsonObject().getAsJsonArray("productPrices");
                if (productPriceJS.size() > 1) {
                    for (int y = 0; y < productPriceJS.size(); y++) {
                        productPrices = new ProductPrices();
                        productPrices.setId(productPriceJS.get(y).getAsJsonObject().get("id").getAsString());
                        productPrices.setProductId(productPriceJS.get(y).getAsJsonObject().get("productId").getAsString());
                        productPrices.setPriceName(productPriceJS.get(y).getAsJsonObject().get("priceName").getAsString());
                        productPrices.setPrice(Float.valueOf(productPriceJS.get(y).getAsJsonObject().get("priceValue").getAsString())); //default to run
                        priceList.add(productPrices);
                    }
                } else {
                    productPrices = new ProductPrices();
                    productPrices.setId(productPriceJS.get(0).getAsJsonObject().get("id").getAsString());
                    productPrices.setProductId(productPriceJS.get(0).getAsJsonObject().get("productId").getAsString());
                    productPrices.setPriceName("");
                    productPrices.setPrice(Float.valueOf(productPriceJS.get(0).getAsJsonObject().get("priceValue").getAsString())); //default to run
                    priceList.add(productPrices);
                }
                product.setProductPrices(priceList);
            }
        }
        return product;
    }

    //category
    public List<Product> getProductListWithCategoryIdFromJson(String categoryId) {
        String configFile = path + apiAminService.getProductListFromCategoryId(categoryId);
        JsonObject config = JsonReader.readConfigFile(configFile);
        JsonObject productsCategory = config.getAsJsonObject("productCategory");
        JsonArray products = productsCategory.getAsJsonArray("products");
        String productName = "";
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productName = products.get(i).getAsJsonObject().get("name").getAsString();
            productList.add(apiAminService.getProductBySearchName(productName));
        }
        return productList;
    }

    public static FlashSale setFlashSale(String flashSaleName, String startDate, String startTime, String endDate, String endTime, String id, List<FlashSaleProduct> flashSaleProducts) {
        FlashSale flashSale = new FlashSale();
        flashSale.setEndDate(endDate);
        flashSale.setEndTime(endTime);
        flashSale.setStartDate(startDate);
        flashSale.setStartTime(startTime);
        flashSale.setFlashSaleName(flashSaleName);
        flashSale.setId(id);
        flashSale.setFlashSaleProduct(flashSaleProducts);
        return flashSale;
    }

    public static FlashSaleProduct setFlashSaleProduct(String id, String name, int price, int flashSalePrice, int flashSaleQuantity, int maximumLimit, Boolean isSinglePrice, String productPriceName, String thumbnail, String productId) {
        FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
        flashSaleProduct.setId(id);
        flashSaleProduct.setPrice(price);
        flashSaleProduct.setFlashSalePrice(flashSalePrice);
        flashSaleProduct.setFlashSaleQuantity(flashSaleQuantity);
        flashSaleProduct.setMaximumLimit(maximumLimit);
        flashSaleProduct.setSinglePrice(isSinglePrice);
        if (isSinglePrice) {
            flashSaleProduct.setProductPriceName(productPriceName);
            flashSaleProduct.setName(name);
        } else flashSaleProduct.setName(name + " (" + productPriceName + ")");
        ;
        flashSaleProduct.setThumbnail(thumbnail);
        flashSaleProduct.setProductId(productId);
        return flashSaleProduct;
    }

    public static FlashSale getFlashSaleInformation(String response, FlashSale flashSale) {
        String name = "";
        int price = 0;
        int flashSalePrice;
        int flashSaleQuantity;
        int maximumLimit;
        Boolean isSinglePrice = true;
        String productPriceName = "";
        String thumbnail = "";
        String productId = "";
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        JsonObject flashSaleObject = jsonResponse.get("flashSale").getAsJsonObject();
        JsonArray flashSaleProductsArray = flashSaleObject.get("products").getAsJsonArray();
        JsonArray productsObjectArray = jsonResponse.get("products").getAsJsonArray();
        FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
        List<FlashSaleProduct> flashSaleProductList = new ArrayList<>();
        String productPriceId;
        for (int i = 0; i < flashSaleProductsArray.size(); i++) {
            JsonObject flashSalePproduct = flashSaleProductsArray.get(i).getAsJsonObject();
            productPriceId = flashSalePproduct.get("productPriceId").getAsString();
            flashSalePrice = flashSalePproduct.get("flashSalePrice").getAsInt();
            flashSaleQuantity = flashSalePproduct.get("flashSaleQuantity").getAsInt();
            maximumLimit = flashSalePproduct.get("maximumLimit").getAsInt();
            for (int y = 0; y < productsObjectArray.size(); y++) {
                JsonObject product = productsObjectArray.get(y).getAsJsonObject();
                JsonArray productsPricesObjectArray = product.get("productPrices").getAsJsonArray();
                int size = productsPricesObjectArray.size();
                for (int t = 0; t < size; t++) {
                    JsonObject priceObject = productsPricesObjectArray.get(t).getAsJsonObject();
                    String priceId = priceObject.get("id").getAsString();
                    if (priceId.equals(productPriceId)) {
                        name = product.get("name").getAsString();
                        thumbnail = product.get("thumbnail").getAsString();
                        productId = priceObject.get("productId").getAsString();
                        price = priceObject.get("priceValue").getAsInt();
                        if (size > 1) {
                            isSinglePrice = false;
                            productPriceName = priceObject.get("priceName").getAsString();
                        } else {
                            isSinglePrice = true;
                        }
                    }
                }
            }
            flashSaleProduct = setFlashSaleProduct(productPriceId, name, price, flashSalePrice, flashSaleQuantity, maximumLimit, isSinglePrice, productPriceName, thumbnail, productId);
            flashSaleProductList.add(flashSaleProduct);
        }
        flashSale.setFlashSaleProduct(flashSaleProductList);
        return flashSale;
    }

    public static FlashSaleProduct getFlashSaleProductFromJson(Boolean isFlashSale, String typeFlashSaleObjectJson, List<String> productInformation, String size) {
        String theme = apiAminService.theme;
        String configFile = path + typeAPI + "/jsonSchema/" + theme + "/flashsale/flashSaleInformation.json";
        JsonObject config = JsonReader.readConfigFile(configFile);
        JsonObject flashSaleObject = config.get(typeFlashSaleObjectJson).getAsJsonObject().get("body").getAsJsonObject().get("flashSale").getAsJsonObject();
        JsonArray flashSaleProductsArray = flashSaleObject.get("flashSaleProducts").getAsJsonArray();
        FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
        Boolean isSinglePrice = false;
        if (isFlashSale) {
            for (int i = 0; i < flashSaleProductsArray.size(); i++) {
                JsonObject flashSaleProductObj = flashSaleProductsArray.get(i).getAsJsonObject();
                try {
                    isSinglePrice = flashSaleProductObj.get("isSinglePrice").getAsBoolean();
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                    isSinglePrice = true;
                }
                String productName = productInformation.get(0);
                if (productName.isBlank() || productName.isEmpty() || productName.equals(""))
                    productName = productInformation.get(0);
                //topping not isSinglePrice
                if (isSinglePrice == false) {
                    String priceName = "(" + flashSaleProductObj.get("productPriceName").getAsString().trim() + ")";
                    String productFlashSaleName = flashSaleProductObj.get("name").getAsString();
                    if (productName.replaceAll("\\s*\\(.*\\)", "").equalsIgnoreCase(productFlashSaleName)) {
                        if (productName.contains(priceName)) {
                            String nameSize = productFlashSaleName + " " + priceName;
                            flashSaleProduct.setName(nameSize);
                            flashSaleProduct.setPrice(Integer.parseInt(flashSaleProductObj.get("price").getAsString()));
                            flashSaleProduct.setFlashSalePrice(Integer.parseInt(flashSaleProductObj.get("flashSalePrice").getAsString()));
                            flashSaleProduct.setFlashSaleQuantity(Integer.parseInt(flashSaleProductObj.get("flashSaleQuantity").getAsString()));
                            flashSaleProduct.setMaximumLimit(Integer.parseInt(flashSaleProductObj.get("maximumLimit").getAsString()));
                            break;
                        }
                    }
                } else {
                    String jsonStr = flashSaleProductObj.get("name").getAsString();
                    String nameStr = productName.replaceAll("\\s*\\(.*\\)", "");
                    if (nameStr.equalsIgnoreCase(jsonStr)) {
                        flashSaleProduct.setName(jsonStr);
                        flashSaleProduct.setPrice(Integer.parseInt(flashSaleProductObj.get("price").getAsString()));
                        flashSaleProduct.setFlashSalePrice(Integer.parseInt(flashSaleProductObj.get("flashSalePrice").getAsString()));
                        flashSaleProduct.setFlashSaleQuantity(Integer.parseInt(flashSaleProductObj.get("flashSaleQuantity").getAsString()));
                        flashSaleProduct.setMaximumLimit(Integer.parseInt(flashSaleProductObj.get("maximumLimit").getAsString()));
                        break;
                    }
                }
            }
        } else {
            String nameStr = productInformation.get(0);
            String productName = nameStr.replaceAll("\\s*\\(.*\\)", "");
            String productPrice = productInformation.get(1);
            flashSaleProduct.setName(nameStr);
            String cleanedString = "";
            if (productPrice.endsWith("đ")) {
                cleanedString = productPrice.substring(0, productInformation.get(1).length() - 1);
            }
            //Delete ","
            int price = Integer.parseInt(cleanedString.replaceAll(",", ""));
            flashSaleProduct.setPrice(price);
        }
        flashSaleProduct.showAll();
        return flashSaleProduct;
    }

    public static class Product {
        private String id;
        private String name;
        private Boolean isActive;
        private List<ProductPrices> productPrices;
        private String categoryId;
        private String thumbnail;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Boolean getActive() {
            return isActive;
        }

        public void setActive(Boolean active) {
            isActive = active;
        }

        public List<ProductPrices> getProductPrices() {
            return productPrices;
        }

        public void setProductPrices(List<ProductPrices> productPrices) {
            this.productPrices = productPrices;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public void addProductPrice(ProductPrices productPrices) {
            this.productPrices.add(productPrices);
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public void showAll() {
            System.out.println("\tName: " + name + " - id: " + id);
            for (ProductPrices price : productPrices) {
                System.out.println("\tChild: " + price.toString());
            }
        }
    }

    public static class ProductPrices {
        private String id;
        private String productId;
        private String priceName;
        private Float price;
        private int position;
        private Boolean isApplyPromotion;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getPriceName() {
            return priceName;
        }

        public void setPriceName(String priceName) {
            this.priceName = priceName;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public Boolean getApplyPromotion() {
            return isApplyPromotion;
        }

        public void setApplyPromotion(Boolean applyPromotion) {
            isApplyPromotion = applyPromotion;
        }

        public String toString() {
            return "\nProductId: " + productId + " " + priceName + " price " + price;
        }
    }

    public static class FlashSale {
        private String flashSaleName;
        private String id;
        private String startDate;
        private String endDate;
        private String startTime;
        private String endTime;
        private List<FlashSaleProduct> flashSaleProduct;

        public String getFlashSaleName() {
            return flashSaleName;
        }

        public void setFlashSaleName(String flashSaleName) {
            this.flashSaleName = flashSaleName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public List<FlashSaleProduct> getFlashSaleProduct() {
            return flashSaleProduct;
        }

        public void setFlashSaleProduct(List<FlashSaleProduct> flashSaleProduct) {
            this.flashSaleProduct = flashSaleProduct;
        }

        public void addFlashSaleProduct(FlashSaleProduct flashSaleProduct) {
            this.flashSaleProduct.add(flashSaleProduct);
        }

        public void showAll() {
            System.out.println("\tName: " + flashSaleName + " - id: " + id + " Start Time: " + startTime + " End Time: " + endTime);
            for (FlashSaleProduct product : flashSaleProduct) {
                product.showAll();
            }
        }
    }

    public static class FlashSaleProduct {
        private String id;
        private String name;
        private int price;
        private int flashSalePrice;
        private int flashSaleQuantity;
        private int maximumLimit;
        private Boolean isSinglePrice;
        private String productPriceName;
        private String thumbnail;
        private String productId;

        public void showAll() {
            System.out.println("\tName: " + name + " - id: " + id + " price " + price + " flashsale price " + flashSalePrice);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getFlashSalePrice() {
            return flashSalePrice;
        }

        public void setFlashSalePrice(int flashSalePrice) {
            this.flashSalePrice = flashSalePrice;
        }

        public int getFlashSaleQuantity() {
            return flashSaleQuantity;
        }

        public void setFlashSaleQuantity(int flashSaleQuantity) {
            this.flashSaleQuantity = flashSaleQuantity;
        }

        public int getMaximumLimit() {
            return maximumLimit;
        }

        public void setMaximumLimit(int maximumLimit) {
            this.maximumLimit = maximumLimit;
        }

        public Boolean getSinglePrice() {
            return isSinglePrice;
        }

        public void setSinglePrice(Boolean singlePrice) {
            isSinglePrice = singlePrice;
        }

        public String getProductPriceName() {
            return productPriceName;
        }

        public void setProductPriceName(String productPriceName) {
            this.productPriceName = productPriceName;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }
    }

    public static class Topping {
        private String name;
        private String originalPrice;
        private String price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void showAll() {
            System.out.println("\tName: " + name + " - price: " + price + " original price: " + originalPrice);
        }
    }
}

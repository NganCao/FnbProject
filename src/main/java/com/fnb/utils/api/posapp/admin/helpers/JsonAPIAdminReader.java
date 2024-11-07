package com.fnb.utils.api.posapp.admin.helpers;

import com.fnb.utils.helpers.JsonReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class JsonAPIAdminReader {
    private static String path = "src/main/java/com/fnb/utils/api/posapp/";
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

    //Topping
    public static List<Topping> getToppingIdListByProduct(String filePath) {
        String configFile = path + filePath;
        List<Topping> toppingList = new ArrayList<>();
        JsonObject config = JsonReader.readConfigFile(configFile);
        JsonObject productJson = config.getAsJsonObject("product");
        JsonArray productToppings = productJson.getAsJsonArray("productToppings");
        Topping topping;
        String id = "";
        if (productToppings.size() > 0) {
            for (int i = 0; i < productToppings.size(); i++) {
                id = (productToppings.get(i).getAsJsonObject().get("id").getAsString());
                topping = getToppingIdListById(id);
                toppingList.add(topping);
            }
            return toppingList;
        } else return null;
    }

    public static Topping getToppingIdListById(String toppingId) {
        String filePath = apiAminService.getProductById(toppingId);
        String configFile = path + filePath;
        List<Topping> toppingList = new ArrayList<>();
        JsonObject config = JsonReader.readConfigFile(configFile);
        JsonObject productJson = config.getAsJsonObject("product");
        JsonArray productPrices = productJson.getAsJsonArray("productPrices");
        String price = productPrices.get(0).getAsJsonObject().get("priceValue").getAsString();
        Topping topping = new Topping();
        topping.setName(productJson.getAsJsonObject().get("name").getAsString());
        topping.setId(productJson.getAsJsonObject().get("id").getAsString());
        topping.setPrice(Float.parseFloat(price));
        toppingList.add(topping);
        return topping;
    }

    public static List<Topping> getToppingByProduct(String productName) {
        Product product = apiAminService.getProductBySearchName(productName);
        String filePath = apiAminService.getProductById(product.getId());
        System.out.println("filepath: " + filePath);
        List<Topping> list = getToppingIdListByProduct(filePath);
        return list;
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

    public static class Topping {
        private String name;
        private float originalPrice;
        private float price;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        private int quantity;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(float originalPrice) {
            this.originalPrice = originalPrice;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public void showAll() {
            System.out.println("\tName: " + name + " - price: " + price + " original price: " + originalPrice);
        }
    }
}

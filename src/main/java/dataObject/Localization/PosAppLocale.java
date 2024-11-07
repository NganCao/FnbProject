package dataObject.Localization;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PosAppLocale {
    @SerializedName("login")
    private Login login;
    @Data
    public static class Login {
        private String title;
        private String enterUsername;
        private String enterPassword;
    }

    //===========================================
    @SerializedName("inventory")
    private Inventory inventory;
    @Data
    public static class Inventory {
        private String inventorychecking;
        private String wishYouhaveaSuccessfulDay;
    }

    //===========================================
    @SerializedName("startShift")
    private StartShift startShift;
    @Data
    public static class StartShift {
        @SerializedName("continue")
        private String btnContinue;
    }

    //===========================================
    @SerializedName("common")
    private Common common;
    @Data
    public static class Common {
        private String refresh;
    }

    //======================================
    @SerializedName("dashboard")
    private Dashboard dashboard;
    @Data
    public static class Dashboard {
        private String saveDraft;
        private String createOrder;
        private String table;
        private String order;
    }

    //======================================
    @SerializedName("customer")
    private Customer customer;
    @Data
    public static class Customer {
        private String customerName;
    }

    //======================================
    @SerializedName("product")
    private product product;
    @Data
    public static class product {
        private String addToCart;
        private String youDoNotHaveAnyProduct;
    }

    //======================================
    @SerializedName("order")
    private order order;
    @Data
    public static class order {
        private String toConfirm;
        private String processing;
        private String completed;
        private String canceled;
        private String draft;
    }
}

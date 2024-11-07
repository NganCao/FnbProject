package dataObject.Localization;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class POSLocalization {
    @SerializedName("leftMenu")
    private LeftMenu leftMenu;
    @Data
    public static class LeftMenu {
        private String order;
        private String kitchen;
        private String setting;
    }

    //======================================
    @SerializedName("reserve")
    private Reserve reserve;
    @Data
    public static class Reserve {
        private String title;
        private String createReservation;
        private String arrivalTime;
    }

    //======================================
    @SerializedName("reserveTable")
    private ReserTable reserTable;
    @Data
    public static class ReserTable {
        private String selectCustomer;
        private String table;
        private String numberOfGuest;
        private String editReservation;
    }
    //======================================
    @SerializedName("button")
    private Button button;
    @Data
    public static class Button {
        private String cancel;
    }

    //======================================
    @SerializedName("kitchenManagement")
    private KitchenManagement kitchenManagement;
    @Data
    public static class KitchenManagement {
        private String order;
        private String dish;
    }

    //======================================
    @SerializedName("toastMessage")
    private ToastMessage toastMessage;
    @Data
    public static class ToastMessage {
        private String productAddToCartSuccess;
        private String completeOrderSuccess;
    }

    //======================================
    @SerializedName("productDetail")
    private ProductDetail productDetail;
    @Data
    public static class ProductDetail {
        private String option;
        private String topping;
    }

    //======================================
    @SerializedName("setting")
    private Setting setting;
    @Data
    public static class Setting {

        @SerializedName("billType")
        private BillType billType;
        @Data
        public static class BillType {
            private String title;
            private String customerReceipt;
        }

        @SerializedName("billSize")
        private BillSize billSize;
        @Data
        public static class BillSize {
            private String title;
        }

        @SerializedName("deviceType")
        private DeviceType deviceType;
        @Data
        public static class DeviceType {
            private String title;
            private String usbPrinter;
            private String lanPrinter;
        }

        @SerializedName("deviceName")
        private deviceName deviceName;
        @Data
        public static class deviceName {
            private String title;
        }
    }

    //======================================
    @SerializedName("posOrder")
    private posOrder posOrder;
    @Data
    public static class posOrder {
        private String takeAway;
        private String inStore;
        private String delivery;
    }
}

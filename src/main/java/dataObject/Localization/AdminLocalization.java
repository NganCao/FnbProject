package dataObject.Localization;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AdminLocalization {
    @SerializedName("customer")
    private Customer customer;

    @Data
    public static class Customer {
        @SerializedName("addNewForm")
        private AddNewForm addNewForm;

        @Data
        public static class AddNewForm {
            private String male;
            private String female;
            private String other;
        }
    }

    //======================================
    @SerializedName("form")
    private Form form;

    @Data
    public static class Form {
        private String country;
        private String province;
        private String district;
        private String ward;
    }

    //======================================
    @SerializedName("button")
    private Button button;

    @Data
    public static class Button {
        private String add;
        private String addNew;
        private String update;
        private String cancel;
        private String save;
        private String delete;
        private String edit;
        private String print;
        private String approve;
        private String moreAction;
        private String saveChanges;
    }

    //======================================
    @SerializedName("area")
    private Area area;

    @Data
    public static class Area {
        private String addNewArea;
        private String areaName;
        private String area;
        private String updateArea;
        private String addNew;
        private String viewCategory;
        private String deleteCategory;
        private String editCategory;
    }

    //======================================
    @SerializedName("areaTable")
    private AreaTable areaTable;

    @Data
    public static class AreaTable {
        private String tableName;
        private String addNew;

        @SerializedName("tableForm")
        private TableForm tableForm;

        @Data
        public static class TableForm {
            private String titleAddNewTable;
            private String updateTable;
        }
    }

    //======================================
    @SerializedName("leaveDialog")
    private LeaveDialog leaveDialog;

    @Data
    public static class LeaveDialog {
        private String ignore;
        private String confirmLeave;
        private String confirmDelete;
    }

    //======================================
    @SerializedName("promotion")
    private Promotion promotion;

    @Data
    public static class Promotion {
        @SerializedName("flashSale")
        private FlashSale flashSale;

        @Data
        public static class FlashSale {
            private String tabTitle;
            private String newCampaign;
            private String createTitle;
        }

        @SerializedName("discount")
        private Discount discount;

        @Data
        public static class Discount {
            private String tabTitle;
            private String total;
            private String product;
            private String productCategory;
        }

        private String applicableType;
        private String edit;
    }

    //======================================
    @SerializedName("discountCode")
    private DiscountCode discountCode;

    @Data
    public static class DiscountCode {
        private String title;
        private String btnNewDiscountCodeTitle;

        @SerializedName("formCreate")
        private FormCreate formCreate;

        @Data
        public static class FormCreate {
            private String generateCodes;
        }
    }

    //======================================
    @SerializedName("transferMaterial")
    private TransferMaterial transferMaterial;

    @Data
    public static class TransferMaterial {
        private String title;
        @SerializedName("statusButton")
        private StatusButton statusButton;

        @Data
        public static class StatusButton {
            private String approve;
            private String deliver;
            private String complete;
            private String cancel;
            private String edit;
            private String print;
        }
    }

    //======================================
    @SerializedName("feeAndTax")
    private FeeAndTax feeAndTax;

    @Data
    public static class FeeAndTax {
        private String feeManagement;
        @SerializedName("tax")
        private Tax tax;

        @Data
        public static class Tax {
            private String taxManagement;
        }
    }

    //======================================
    @SerializedName("report")
    private Report report;

    @Data
    public static class Report {
        @SerializedName("shift")
        private Shift shift;

        @Data
        public static class Shift {
            private String title;
            private String id;
        }

        @SerializedName("shiftDetail")
        private ShiftDetail shiftDetail;

        @Data
        public static class ShiftDetail {
            private String title;
        }
    }

    //======================================
    @SerializedName("createQrCode")
    private CreateQrCode createQrCode;

    @Data
    public static class CreateQrCode {
        private String serviceType;
    }

    //======================================
    @SerializedName("marketing")
    private Marketing marketing;

    @Data
    public static class Marketing {
        @SerializedName("notificationCampaign")
        private NotificationCampaign notificationCampaign;

        @Data
        public static class NotificationCampaign {
            private String enterMessageContent;
            @SerializedName("sendingType")
            private SendingType sendingType;

            @Data
            public static class SendingType {
                private String sendByEvent;
                private String sendBySpecificTime;
                private String sendNow;
            }
        }

        @SerializedName("emailCampaign")
        private EmailCampaign emailCampaign;

        @Data
        public static class EmailCampaign {
            private String sendToEmailAddress;
            private String sendToCustomerGroup;
            @SerializedName("generalTab")
            private GeneralTab generalTab;

            @Data
            public static class GeneralTab {
                private String sendingTime;
            }
        }
    }
    //======================================

    @SerializedName("productManagement")
    private ProductManagement productManagement;

    @Data
    public static class ProductManagement {
        private String btnMenuCreateProduct;
        private String btnMenuCreateTopping;
        private String includeTopping;
        @SerializedName("platform")
        private Platform platform;
        @Data
        public static class Platform {
            private String title;
        }

        @SerializedName("unit")
        private Unit unit;
        @Data
        public static class Unit {
            private String title;
            private String recipe;
        }

        @SerializedName("option")
        private Option option;
        @Data
        public static class Option {
            private String title;
        }

        @SerializedName("pricing")
        private Pricing pricing;
        @Data
        public static class Pricing {
            private String addPrice;
            private String pricesAndVariations;
        }
    }

    //======================================
    @SerializedName("materialCategory")
    private MaterialCategory materialCategory;
    @Data
    public static class MaterialCategory {
        private String selectMaterial;
        private String create;
    }


    //======================================
    @SerializedName("purchaseOrder")
    private PurchaseOrder purchaseOrder;
    @Data
    public static class PurchaseOrder {
        private String materialInformation;
        private String purchaseOrderManagement;
    }

    //======================================
    @SerializedName("material")
    private Material material;
    @Data
    public static class Material {
        private String setupQuantity;
        private String branchAndWarehouse;
        private String ingredientSearch;
        private String ingredientQuantityOfBranch;
    }

    //======================================
    @SerializedName("materialControl")
    private MaterialControl materialControl;
    @Data
    public static class MaterialControl {
        @SerializedName("createRemoveRequest")
        private CreateRemoveRequest createRemoveRequest;
        @Data
        public static class CreateRemoveRequest {
            private String searchByNameSku;
        }
    }

    //======================================
    @SerializedName("configuration")
    private Configuration configuration;
    @Data
    public static class Configuration {
        private String billAndTickets;
        private String operation;

        @SerializedName("cashierScreen")
        private CashierScreen cashierScreen;
        @Data
        public static class CashierScreen {
            private String payFirst;
            private String orderFirstPayLater;
            private String receipts;
            private String allowOrderOutOfStock;
        }

        @SerializedName("kitchenSystem")
        private kitchenSystem kitchenSystem;
        @Data
        public static class kitchenSystem {
            private String title;

        }
    }

    //======================================
    @SerializedName("receipt")
    private Receipt receipt;
    @Data
    public static class Receipt {
        private String selectOptionDescription;
        private String smallSizeOption;
    }

    //======================================
    @SerializedName("inventoryHistory")
    private Receipt inventoryHistory;
    @Data
    public static class inventoryHistory {
        private String previousQuantity;
    }
}

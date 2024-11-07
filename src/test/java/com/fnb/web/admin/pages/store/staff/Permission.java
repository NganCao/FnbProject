package com.fnb.web.admin.pages.store.staff;

public enum Permission {
    // Product
    View_product("View product"),
    Edit_product("Edit product"),
    Delete_product("Delete product"),
    Create_product("Create product"),
    Import_product("Import product"),
    Delete_option("Delete option"),
    Create_combo("Create combo"),
    Edit_combo("Edit combo"),
    Active_product("Activate product"),
    Edit_option("Edit option"),
    View_combo("View combo"),
    Create_option("Create option"),
    Delete_combo("Delete combo"),
    Deactivate_product("Deactivate product"),
    View_option("View option"),
    Stop_combo("Stop combo"),

    // Material
    Create_material("Create material"),
    View_material("View material"),
    Edit_material("Edit material"),
    Delete_material("Delete material"),
    Export_material("Export material"),
    Import_material("Import material"),
    Activate_material("Activate material"),
    Deactivate_material("Deactivate material"),

    // Report
    View_revenue_report("View revenue report"),
    View_shift_report("View shift report"),
    Export_top_customer_report("Export top customer report"),
    Export_revenue_report("Export revenue report"),
    Export_shift_report("Export_shift_report"),
    View_customer_report("View customer report"),
    View_order_report("View order report"),
    View_sold_product_report("View sold product report"),
    View_reservation_report("View reservation report"),
    Export_order_report("Export order report"),
    Export_sold_product_report("Export sold product report"),
    Export_reservation_report("Export reservation report"),

    // Inventory history
    View_inventory_history("View inventory history"),

    // Reserve table
    View_reservation("View reservation"),
    Create_new_reservation("Create new reservation"),
    Edit_reservation("Edit reservation"),
    Cancel_reservation("Cancel reservation"),

    // Category
    Edit_product_category("Edit product category"),
    Edit_material_category("Edit material category"),
    Create_product_category("Create product category"),
    Create_material_category("Create material category"),
    View_product_category("View product category"),
    Delete_material_category("Delete material category"),
    Delete_product_category("Delete product category"),
    View_material_category("View material category"),

    // Supplier
    Delete_supplier("Delete supplier"),
    View_supplier("View supplier"),
    Edit_supplier("Edit supplier"),
    Create_supplier("Create supplier"),

    // Purchase order
    Import_goods("Import goods"),
    Approve_purchase("Approve purchase order"),
    Edit_purchase("Edit purchase order"),
    Create_purchase("Create purchase order"),
    Cancel_purchase("Cancel purchase order"),
    View_purchase("View purchase order"),

    // Transfer material
    View_transfer_material("View transfer material"),
    Approve_transfer_material("Approve transfer material"),
    Create_new_transfer_material("Create new transfer material"),
    Deliver_transfer_material("Deliver transfer material"),
    Edit_transfer_material("Edit transfer material"),
    Complete_transfer_material("Complete transfer material"),
    Cancel_transfer_material("Cancel transfer material"),

    // Customer
    View_customer("View customer"),
    Delete_customer("Delete customer"),
    Create_membership_level("Create membership level"),
    View_loyalty_point("View loyalty point"),
    Create_customer("Create customer"),
    Create_segment("Create segment"),
    Edit_segment("Edit segment"),
    View_segment("View segment"),
    Delete_membership_level("Delete membership level"),
    Edit_membership_level("Edit membership level"),
    Delete_segment("Delete segment"),
    Edit_customer("Edit customer"),
    View_membership_level("View membership level"),

    // Area-Table
    Create_area_table("Create area & table"),
    Delete_area_table("Delete area & table"),
    Edit_area_table("Edit area & table"),
    View_area_table("View area & table"),

    // Promotion
    Stop_flash_Sale("Stop flash Sale"),
    Delete_discount("Delete discount"),
    Edit_discount("Edit discount"),
    Delete_flash_Sale("Delete flash Sale"),
    Stop_discount("Stop discount"),
    Edit_flash_Sale("Edit flash Sale"),
    Create_discount("Create discount"),
    Delete_discount_code("Delete discount code"),
    Edit_discount_code("Edit discount code"),
    Create_discount_code("Create discount code"),
    View_discount_code("View discount code"),
    Stop_discount_code("Stop discount code"),
    View_flash_sale("View flash sale"),
    View_discount("View discount"),
    Create_flash_sale("Create flash sale"),

    // Fee & tax
    Delete_tax("Delete tax"),
    Stop_fee("Stop fee"),
    View_tax("View tax"),
    View_fee("View fee"),
    Create_tax("Create tax"),
    Create_fee("Create fee"),
    Delete_fee("Delete fee"),

    // Shift
    View_shift("View shift"),

    // Marketing
    Edit_email_campaign("Edit email campaign"),
    Delete_email_campaign("Delete email campaign"),
    Delete_QR_Code("Delete QR Code"),
    View_QR_Code("View QR Code"),
    Stop_notification_campaign("Stop notification campaign"),
    Edit_QR_Code("Edit QR Code"),
    Create_email_campaign("Create email campaign"),
    Create_notification_campaign("Create notification campaign"),
    View_notification_campaign("View notification campaign"),
    Edit_notification_campaign("Edit notification campaign"),
    Delete_notification_campaign("Delete notification campaign"),
    Stop_email_campaign("Stop email campaign"),
    Stop_QR_Code("Stop QR Code"),
    Create_QR_Code("Create QR Code"),
    View_email_campaign("View email campaign"),

    // Pos
    Cashier("Cashier"),
    Kitchen("Kitchen");

    private final String permission;
    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

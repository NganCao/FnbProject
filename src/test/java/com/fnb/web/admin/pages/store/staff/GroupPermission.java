package com.fnb.web.admin.pages.store.staff;

public enum GroupPermission {
    Full_Permission("Full permission on GoF&B"),
    Product("Product"),
    Material("Material"),
    Report("Report"),
    Inventory_history("Inventory history"),
    Category("Category"),
    Supplier("Supplier"),
    Purchase_order("Purchase order"),
    Transfer_material("Transfer material"),
    Reservation("Reservation"),
    Customer("Customer"),
    Area_table("Area & table"),
    Promotion("Promotion"),
    Fee_tax("Fee & tax"),
    Shift("Shift"),
    Marketing("Marketing"),
    Point_of_Sale_POS("Point of Sale (POS)");

    private final String groupdPermission;
    GroupPermission(String groupPermission) {
        this.groupdPermission = groupPermission;
    }

    public String getGroupPermission() {
        return groupdPermission;
    }
}

package com.fnb.web.admin.pages.inventory.ingredients;

public enum MaterialColumnName {
    MATERIAL_COLUMN_NAME("Ingredient Name"),
    SKU_COLUMN_NAME("SKU"),
    QUANTITY_COLUMN_NAME("Total Quantity"),
    COST_COLUMN_NAME("Cost Per Unit"),
    STATUS_COLUMN_NAME("Status"),
    ACTION_COLUMN_NAME("Action");

    private final String displayName;

    MaterialColumnName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

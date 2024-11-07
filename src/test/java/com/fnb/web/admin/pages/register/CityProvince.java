package com.fnb.web.admin.pages.register;

public enum CityProvince {
    BINHDUONG("Bình Dương"),
    HOCHIMINH("Hồ Chí Minh");

    private final String displayName;

    CityProvince(String displayName) {
        this.displayName = displayName;
    }

    public String getCityProvinceName() {
        return displayName;
    }
}

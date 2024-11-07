package com.fnb.web.admin.pages.register;

public enum Country {
    VIETNAM("Viet Nam"),
    UNITED_STATE("United States"),
    CUBA("Cuba"),
    JAPAN("Japan");

    private final String displayName;
    Country(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}

package com.fnb.web.admin.pages.packages;

public enum Platform {
    POS("POS"),
    WEB("WEB"),
    APP("APP");

    private final String displayName;

    Platform(String displayName) {
        this.displayName = displayName;
    }

    public String getPlatformName() {
        return displayName;
    }
}

package com.fnb.web.admin.pages.packages;

public enum PaymentDuration {
    ONE_YEAR("1 year"),
    TWO_YEARS("2 years"),
    THREE_YEARS("3 years"),
    FIVE_YEARS("5 years"),
    TEN_YEARS("10 years");

    private final String year;

    PaymentDuration(String year) {
        this.year = year;
    }

    public String getDurationTime() {
        return year;
    }
}

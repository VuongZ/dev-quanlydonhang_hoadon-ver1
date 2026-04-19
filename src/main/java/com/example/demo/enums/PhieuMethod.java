package com.example.demo.enums;

public enum PhieuMethod {
    TIEN_MAT("Tiền Mặt"),
    CHUYEN_KHOAN("Chuyển Khoản"),
    KHAC("Khác");

    private final String value;
    PhieuMethod(String value) { this.value = value; }
    public String getValue() { return value; }

    @Override
    public String toString() { return value; }
}
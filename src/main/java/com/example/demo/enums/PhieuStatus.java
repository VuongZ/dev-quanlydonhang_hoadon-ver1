package com.example.demo.enums;

public enum PhieuStatus {
    KE_HOACH("Kế Hoạch"),
    HOAN_THANH("Hoàn Thành"),
    HUY("Hủy"),
    KHAC("Khác");

    private final String value;
    PhieuStatus(String value) { this.value = value; }
    public String getValue() { return value; }

    @Override
    public String toString() { return value; }
}
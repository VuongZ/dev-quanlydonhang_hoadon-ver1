package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PhieuType {

    PHIEU_THU("Phiếu Thu"),
    PHIEU_CHI("Phiếu Chi"),
    KHAC("Khác");

    private final String value;
    PhieuType(String value) { this.value = value; }
    @JsonValue
    public String getValue() { return value; }

    @Override
    public String toString() { return value; }
    @JsonCreator // Giúp Spring biết cách chuyển từ String "Phiếu Chi" sang Enum tương ứng
    public static PhieuType fromValue(String value) {
        for (PhieuType type : PhieuType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        // Tùy chọn: ném lỗi hoặc trả về null/KHAC nếu không khớp
        return null;
    }
}
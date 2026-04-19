package com.example.demo.convert;

import com.example.demo.enums.PhieuType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PhieuTypeConverter implements AttributeConverter<PhieuType, String> {

    @Override
    public String convertToDatabaseColumn(PhieuType type) {
        return type == null ? null : type.getValue();
    }

    @Override
    public PhieuType convertToEntityAttribute(String value) {
        if (value == null) return null;
        for (PhieuType t : PhieuType.values()) {
            if (t.getValue().equals(value)) return t;
        }
        throw new IllegalArgumentException("Unknown PhieuType: " + value);
    }
}
package com.example.demo.convert;

import com.example.demo.enums.PhieuMethod;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PhieuMethodConverter implements AttributeConverter<PhieuMethod, String> {

    @Override
    public String convertToDatabaseColumn(PhieuMethod method) {
        return method == null ? null : method.getValue();
    }

    @Override
    public PhieuMethod convertToEntityAttribute(String value) {
        if (value == null) return null;
        for (PhieuMethod m : PhieuMethod.values()) {
            if (m.getValue().equals(value)) return m;
        }
        throw new IllegalArgumentException("Unknown PhieuMethod: " + value);
    }
}
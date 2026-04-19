package com.example.demo.convert;

import com.example.demo.enums.PhieuStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PhieuStatusConverter implements AttributeConverter<PhieuStatus, String> {

    @Override
    public String convertToDatabaseColumn(PhieuStatus status) {
        return status == null ? null : status.getValue();
    }

    @Override
    public PhieuStatus convertToEntityAttribute(String value) {
        if (value == null) return null;
        for (PhieuStatus s : PhieuStatus.values()) {
            if (s.getValue().equals(value)) return s;
        }
        throw new IllegalArgumentException("Unknown PhieuStatus: " + value);
    }
}